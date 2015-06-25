package com.hps.integrator.services.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.fluent.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.HpsSoapGatewayService;

import java.math.BigDecimal;

public class HpsFluentCheckService extends HpsSoapGatewayService {
    public HpsFluentCheckService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }

    public HpsFluentCheckService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        super(config, enableLogging);
    }

    public CheckOverrideBuilder override() {
        return new CheckOverrideBuilder(this);
    }

    public CheckRecurringBuilder recurring() {
        return new CheckRecurringBuilder(this);
    }

    public CheckRecurringBuilder recurring(BigDecimal amount) {
        return new CheckRecurringBuilder(this).withAmount(amount);
    }

    public CheckReturnBuilder returnCheck() {
        return new CheckReturnBuilder(this);
    }

    public CheckSaleBuilder sale() {
        return new CheckSaleBuilder(this);
    }

    public CheckSaleBuilder sale(BigDecimal amount) {
        return new CheckSaleBuilder(this).withAmount(amount);
    }

    public CheckVoidBuilder checkVoid(){
        return new CheckVoidBuilder(this);
    }

    public HpsCheckResponse buildTransaction(String action, HpsCheck check, BigDecimal amount) throws HpsException, HpsCheckException {
        return buildTransaction(action, check, amount, null, null, null);
    }

    public HpsCheckResponse buildTransaction(String action, HpsCheck check, BigDecimal amount, String clientTransactionId) throws HpsException {
        return buildTransaction(action, check, amount, clientTransactionId, null, null);
    }

    public HpsCheckResponse buildTransaction(String action, HpsCheck check, BigDecimal amount, String clientTransactionId, Boolean checkVerify) throws HpsException {
        return buildTransaction(action, check, amount, clientTransactionId, checkVerify, null);
    }

    public HpsCheckResponse buildTransaction(String action, HpsCheck check, BigDecimal amount, String clientTransactionId, Boolean checkVerify, Boolean achVerify) throws HpsException {
        if(amount != null)
            HpsInputValidation.checkAmount(amount);

        if(check.getSecCode().equals("CCD") && (check.getCheckHolder() == null || check.getCheckHolder().getCheckName() == null))
            throw new HpsInvalidRequestException(HpsExceptionCodes.MissingCheckName, "For SEC Code CCD the check name is required.");

        Element transaction = Et.element("CheckSale");
        Element block1 = Et.subElement(transaction, "Block1");

        if(amount != null)
            Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(this.hydrateCheckData(check));
        Et.subElement(block1, "CheckAction").text(action);
        Et.subElement(block1, "SECCode").text(check.getSecCode());

        if(checkVerify != null || achVerify != null){
            Element verifyElement = Et.subElement(block1, "VerifyInfo");
            if(checkVerify != null)
                Et.subElement(verifyElement, "CheckVerify").text(checkVerify ? "Y" : "N");
            if(achVerify != null)
                Et.subElement(verifyElement, "ACHVerify").text(achVerify ? "Y" : "N");
        }

        if(check.getCheckType() != null)
            Et.subElement(block1, "CheckType").text(check.getCheckType().getValue());
        if(check.getDataEntryMode() != null)
            Et.subElement(block1, "DataEntryMode").text(check.getDataEntryMode().getValue());
        if(check.getCheckHolder() != null)
            block1.append(this.hydrateConsumerInfo(check.getCheckHolder()));

        return this.submitTransaction(transaction, clientTransactionId);
    }

    public HpsCheckResponse submitTransaction(Element transaction) throws HpsException {
        return this.submitTransaction(transaction, null);
    }

    public HpsCheckResponse submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
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
}
