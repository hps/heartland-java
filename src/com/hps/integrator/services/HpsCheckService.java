package com.hps.integrator.services;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.entities.check.HpsCheckResponseDetails;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.emums.CheckActionType;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HpsCheckService extends HpsSoapGatewayService {
    public HpsCheckService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }

    /**
     * A <b>Sale</b> transaction is used to process a check sale using bank account information as the payment method.
     * <br></br><br></br>
     * <b>NOTE:</b> The Portico Gateway supports both GETI and HPS Colonnade for processing check transactions. While
     * the available services are the same regardless of the check processor, the services may have different behaviors.
     * For example, GETI-processed check Sale transactions support the ability to override a check Sale transaction
     * already presented as well as the ability to verify a check.
     *
     * @param check  The check information.
     * @param amount The amount of the sale.
     * @return The HPS check Response
     * @throws HpsException, HpsCheckException
     */
    public HpsCheckResponse sale(HpsCheck check, BigDecimal amount) throws HpsException, HpsCheckException {
        return processTransaction(CheckActionType.sale, check, amount);
    }

    /**
     * A <b>Sale</b> transaction is used to process a check sale using bank account information as the payment method.
     * <br></br><br></br>
     * <b>NOTE:</b> The Portico Gateway supports both GETI and HPS Colonnade for processing check transactions. While
     * the available services are the same regardless of the check processor, the services may have different behaviors.
     * For example, GETI-processed check Sale transactions support the ability to override a check Sale transaction
     * already presented as well as the ability to verify a check.
     *
     * @param check  The check information.
     * @param amount The amount of the sale.
     * @param clientTransactionId The client's transaction Id.
     * @return The HPS check Response
     * @throws HpsException, HpsCheckException
     */
    public HpsCheckResponse sale(HpsCheck check, BigDecimal amount, String clientTransactionId) throws HpsException, HpsCheckException {
        return processTransaction(CheckActionType.sale, check, amount, clientTransactionId);
    }

    /**
     * A <b>Sale</b> transaction is used to process a check return using bank account information.
     * <br></br><br></br>
     * <b>NOTE:</b> The Portico Gateway supports both GETI and HPS Colonnade for processing check transactions. While
     * the available services are the same regardless of the check processor, the services may have different behaviors.
     * For example, GETI-processed check Sale transactions support the ability to override a check Sale transaction
     * already presented as well as the ability to verify a check.
     *
     * @param check  The check information.
     * @param amount The amount of the sale.
     * @return The HPS check Response
     * @throws HpsException, HpsCheckException
     */
    public HpsCheckResponse refund(HpsCheck check, BigDecimal amount) throws HpsException, HpsCheckException {
        return processTransaction(CheckActionType.refund, check, amount);
    }

    /**
     * A <b>Sale</b> transaction is used to process a check override using bank account information.
     * <br></br><br></br>
     * <b>NOTE:</b> The Portico Gateway supports both GETI and HPS Colonnade for processing check transactions. While
     * the available services are the same regardless of the check processor, the services may have different behaviors.
     * For example, GETI-processed check Sale transactions support the ability to override a check Sale transaction
     * already presented as well as the ability to verify a check.
     *
     * @param check  The check information.
     * @param amount The amount of the sale.
     * @return The HPS check Response
     * @throws HpsException, HpsCheckException
     */
    public HpsCheckResponse override(HpsCheck check, BigDecimal amount) throws HpsException {
        return processTransaction(CheckActionType.override, check, amount);
    }

    private HpsCheckResponse processTransaction(CheckActionType action, HpsCheck check, BigDecimal amount) throws HpsException {
        return processTransaction(action, check, amount, null);
    }

    private HpsCheckResponse processTransaction(CheckActionType action, HpsCheck check, BigDecimal amount, String clientTransactionId) throws HpsException {
        if(amount != null)
            HpsInputValidation.checkAmount(amount);

        if(check.getSecCode().equals("CCD") && (check.getCheckHolder() == null || check.getCheckHolder().getCheckName() == null))
            throw new HpsInvalidRequestException(HpsExceptionCodes.MissingCheckName, "For SEC Code CCD the check name is required.");

        Element transaction = Et.element("CheckSale");
        Element block1 = Et.subElement(transaction, "Block1");

        if(amount != null)
            Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(this.hydrateCheckData(check));
        Et.subElement(block1, "CheckAction").text(action.getValue());
        Et.subElement(block1, "SECCode").text(check.getSecCode());

        if(check.getCheckVerify() != null){
            Element verifyElement = Et.subElement(block1, "VerifyInfo");
            Et.subElement(verifyElement, "CheckVerify").text(check.getCheckVerify() ? "Y" : "N");
        }

        if(check.getCheckType() != null)
            Et.subElement(block1, "CheckType").text(check.getCheckType().getValue());
        if(check.getDataEntryMode() != null)
            Et.subElement(block1, "DataEntryMode").text(check.getDataEntryMode().getValue());
        if(check.getCheckHolder() != null)
            block1.append(this.hydrateConsumerInfo(check.getCheckHolder()));

        return this.submitTransaction(transaction, clientTransactionId);
    }

    private HpsCheckResponse submitTransaction(Element transaction) throws HpsException {
        return this.submitTransaction(transaction, null);
    }

    private HpsCheckResponse submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
        ElementTree rsp = this.doTransaction(transaction, clientTransactionId);
        HpsGatewayResponseValidation.checkGatewayResponse(rsp, transaction.tag());

        HpsCheckResponse response = new HpsCheckResponse().fromElementTree(rsp);
        Element item = rsp.get(transaction.tag());
        if(item.getString("RspCode") == null || !item.getString("RspCode").equals("0"))
            throw new HpsCheckException(
                    rsp.get("Header").getInt("GatewayTxnId"),
                    response.getDetails(),
                    item.getInt("RspCode"),
                    item.getString("RspMessage")
            );

        return response;
    }

    public HpsCheckResponse voidSale(int transactionId) throws HpsException, HpsCheckException {
        return voidSale(transactionId, null);
    }

    /**
     * A <b>Void</b> transaction is used to cancel a previously successful sale transaction. The original sale transaction
     * can be identified by the GatewayTxnid of the original or by the ClientTxnId of the original if provided on the original
     * Sale transaction.
     *
     * @param transactionId The transaction ID of charge to void.
     * @return The HPS check Response
     * @throws HpsException, HpsCheckException
     */
    public HpsCheckResponse voidSale(Integer transactionId, String clientTransactionId) throws HpsException, HpsCheckException {
        Element transaction = Et.element("CheckVoid");
        Element block1 = Et.subElement(transaction, "Block1");

        if(transactionId != null)
            Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());
        else if(this.clientTransactionId != null)
            Et.subElement(block1, "ClientTxnId").text(clientTransactionId);

        return this.submitTransaction(transaction);
    }
}
