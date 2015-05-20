package com.hps.integrator.services;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckHolder;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.entities.check.HpsCheckResponseDetails;
import com.hps.integrator.fluent.CheckRecurringBuilder;
import com.hps.integrator.fluent.CheckSaleBuilder;
import com.hps.integrator.fluent.CheckVoidBuilder;
import com.hps.integrator.fluent.CheckVoidUsingBuilder;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HpsCheckService extends HpsSoapGatewayService {
    public HpsCheckService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }

    public CheckRecurringBuilder recurring() {
        return new CheckRecurringBuilder(servicesConfig);
    }

    public CheckRecurringBuilder recurring(BigDecimal amount) {
        return new CheckRecurringBuilder(servicesConfig).withAmount(amount);
    }

    public CheckSaleBuilder sale(Enums.checkActionType actionType) throws HpsException {
        return new CheckSaleBuilder(servicesConfig, actionType);
    }

    public CheckVoidUsingBuilder voidTransaction() throws HpsException {
        return new CheckVoidUsingBuilder(new CheckVoidBuilder(servicesConfig));
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
        return processTransaction(Enums.checkActionType.SALE, check, amount);
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
    public HpsCheckResponse sale(HpsCheck check, BigDecimal amount, long clientTransactionId) throws HpsException, HpsCheckException {
        return processTransaction(Enums.checkActionType.SALE, check, amount, clientTransactionId);
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
        return processTransaction(Enums.checkActionType.RETURN, check, amount);
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
    public HpsCheckResponse override(HpsCheck check, BigDecimal amount) throws HpsException, HpsCheckException {
        return processTransaction(Enums.checkActionType.OVERRIDE, check, amount);
    }

    private HpsCheckResponse processTransaction(Enums.checkActionType action, HpsCheck check, BigDecimal amount) throws HpsException, HpsCheckException {
        return processTransaction(action, check, amount, Long.valueOf(0));
    }

    private HpsCheckResponse processTransaction(Enums.checkActionType action, HpsCheck check, BigDecimal amount, Long ClientTransactionId) throws HpsException, HpsCheckException {
        HpsInputValidation.checkAmount(amount);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCheckSaleReqType item = new PosCheckSaleReqType();
        CheckSaleReqBlock1Type block1 = new CheckSaleReqBlock1Type();

        block1.Amt = amount;
        block1.CheckAction = action;
        block1.SECCode = check.getSecCode();
        block1.CheckType = check.getCheckType();
        block1.DataEntryMode = check.getDataEntryMode();

        AccountInfoType accountInfo = new AccountInfoType();
        accountInfo.AccountNumber = check.getAccountNumber();
        accountInfo.CheckNumber = check.getCheckNumber();
        accountInfo.MICRData = check.getMicrNumber();
        accountInfo.RoutingNumber = check.getRoutingNumber();
        accountInfo.AccountType = check.getAccountType();
        block1.AccountInfo = accountInfo;

        if(check.getCheckVerify()) {
            block1.VerifyInfo = new VerifyInfoType();
            block1.VerifyInfo.CheckVerify = Enums.booleanType.Y;
        }

        if (check.getCheckHolder() != null) {
            ConsumerInfoType consumerInfo = new ConsumerInfoType();

            HpsCheckHolder checkHolder = check.getCheckHolder();
            consumerInfo.Address1 = checkHolder.getAddress() != null ? checkHolder.getAddress().getAddress() : null;
            consumerInfo.City = checkHolder.getAddress() != null ? checkHolder.getAddress().getCity() : null;
            consumerInfo.State = checkHolder.getAddress() != null ? checkHolder.getAddress().getState() : null;
            consumerInfo.Zip = checkHolder.getAddress() != null ? checkHolder.getAddress().getZip() : null;
            consumerInfo.CheckName = checkHolder.getCheckName();
            consumerInfo.CourtesyCard = checkHolder.getCourtesyCard();
            consumerInfo.DLNumber = checkHolder.getDlNumber();
            consumerInfo.DLState = checkHolder.getDlState();
            consumerInfo.EmailAddress = checkHolder.getEmail();
            consumerInfo.FirstName = checkHolder.getFirstName();
            consumerInfo.LastName = checkHolder.getLastName();
            consumerInfo.PhoneNumber = checkHolder.getPhone();

            if(checkHolder.getSsn4() != null || checkHolder.getDobYear() != null) {
                consumerInfo.IdentityInfo = new IdentityInfoType();

                if(checkHolder.getSsn4() != null && !checkHolder.getSsn4().equals(""))
                    consumerInfo.IdentityInfo.SSNL4 = checkHolder.getSsn4();

                if(checkHolder.getDobYear() != null && !checkHolder.getDobYear().equals(""))
                    consumerInfo.IdentityInfo.DOBYear = checkHolder.getDobYear();
            }

            block1.ConsumerInfo = consumerInfo;
        }

        item.Block1 = block1;
        transaction.CheckSale = item;

        if (ClientTransactionId != 0) {
            this.clientTransactionId = ClientTransactionId;
        }

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosCheckSaleRspType saleRsp = resp.Ver10.Transaction.CheckSale;
        if (saleRsp.RspCode != 0) {
            throw new HpsCheckException(resp.Ver10.Header.GatewayTxnId, getResponseDetails(saleRsp.CheckRspInfo),
                    saleRsp.RspCode, saleRsp.RspMessage);
        }

        HpsCheckResponse response = new HpsCheckResponse(this.hydrateTransactionHeader(resp.Ver10.Header));
        response.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        response.setAuthorizationCode(saleRsp.AuthCode);
        response.setResponseCode(Integer.toString(saleRsp.RspCode));
        response.setResponseText(saleRsp.RspMessage);

        response.setDetails(getResponseDetails(saleRsp.CheckRspInfo));

        return response;
    }

    public HpsCheckResponse voidSale(int transactionId) throws HpsException, HpsCheckException {
        return voidSale(transactionId, 0);
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
    public HpsCheckResponse voidSale(int transactionId, long clientTransactionId) throws HpsException, HpsCheckException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCheckVoidReqType item = new PosCheckVoidReqType();
        CheckVoidReqBlock1Type block1 = new CheckVoidReqBlock1Type();

        if (transactionId != 0) {
            block1.GatewayTxnId = transactionId;
        }
        if (clientTransactionId != 0) {
            block1.ClientTxnId = clientTransactionId;
        }

        item.Block1 = block1;
        transaction.CheckVoid = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosCheckVoidRspType voidRsp = resp.Ver10.Transaction.CheckVoid;
        if (voidRsp.RspCode != 0) {
            throw new HpsCheckException(resp.Ver10.Header.GatewayTxnId, getResponseDetails(voidRsp.CheckRspInfo),
                    voidRsp.RspCode, voidRsp.RspMessage);
        }

        HpsCheckResponse response = new HpsCheckResponse(this.hydrateTransactionHeader(resp.Ver10.Header));
        response.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        response.setAuthorizationCode(voidRsp.AuthCode);
        response.setResponseCode(Integer.toString(voidRsp.RspCode));
        response.setResponseText(voidRsp.RspMessage);

        response.setDetails(getResponseDetails(voidRsp.CheckRspInfo));

        return response;
    }

    private List<HpsCheckResponseDetails> getResponseDetails(ArrayList<CheckRspInfoType> responseInfo) {
        List<HpsCheckResponseDetails> result = new ArrayList<HpsCheckResponseDetails>();

        for (CheckRspInfoType info : responseInfo) {
            HpsCheckResponseDetails newItem = new HpsCheckResponseDetails();
            newItem.setCode(info.Code);
            newItem.setFieldName(info.FieldName);
            newItem.setFieldNumber(info.FieldNumber);
            newItem.setMessage(info.Message);
            newItem.setMessageType(info.Type);
        }

        return result;
    }
}
