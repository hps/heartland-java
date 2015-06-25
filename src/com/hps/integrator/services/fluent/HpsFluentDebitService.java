package com.hps.integrator.services.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.debit.HpsDebitAuthorization;
import com.hps.integrator.fluent.DebitChargeBuilder;
import com.hps.integrator.fluent.DebitReturnBuilder;
import com.hps.integrator.fluent.DebitReverseBuilder;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;
import com.hps.integrator.services.HpsSoapGatewayService;

import java.math.BigDecimal;

public class HpsFluentDebitService extends HpsSoapGatewayService {
    public HpsFluentDebitService() throws HpsException { }
    public HpsFluentDebitService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }
    public HpsFluentDebitService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        super(config, enableLogging);
    }

    public HpsFluentDebitService withConfig(IHpsServicesConfig config) {
        this.servicesConfig = config;
        return this;
    }

    public DebitChargeBuilder sale() {
        return sale(null);
    }
    public DebitChargeBuilder sale(BigDecimal amount) {
        return new DebitChargeBuilder(this).withAmount(amount);
    }

    public DebitReturnBuilder refund() {
        return refund(null);
    }
    public DebitReturnBuilder refund(BigDecimal amount) {
        return new DebitReturnBuilder(this).withAmount(amount);
    }

    public DebitReverseBuilder reverse() {
        return reverse(null);
    }
    public DebitReverseBuilder reverse(BigDecimal amount) {
        return new DebitReverseBuilder(this).withAmount(amount);
    }

    public HpsDebitAuthorization submitTransaction(Element transaction) throws HpsException {
        return submitTransaction(transaction, null);
    }
    public HpsDebitAuthorization submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
        ElementTree rsp = doTransaction(transaction, clientTransactionId);

        BigDecimal amount = null;
        if(transaction.tag().equals("DebitSale"))
            amount = new BigDecimal(transaction.getString("Amt"));

        processGatewayResponse(rsp, transaction.tag(), amount);
        processIssuerResponse(rsp, transaction.tag(), amount);

        return new HpsDebitAuthorization().fromElementTree(rsp);
    }

    public void processIssuerResponse(ElementTree response, String expectedType, BigDecimal amount) throws HpsException {
        Integer transactionId = response.get("Header").getInt("GatewayTxnId");
        Element transaction = response.get(expectedType);

        if(transaction != null) {
            String responseCode = transaction.getString("RspCode");
            String responseText = transaction.getString("RspText");

            if(responseCode != null) {
                if(responseCode.equals("91")){
                    try{
                        this.reverse(amount).execute();
                    }
                    catch(HpsGatewayException e) {
                        if(e.getDetails().getGatewayResponseCode() == 3)
                            HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText);
                        throw new HpsCreditException(transactionId, HpsExceptionCodes.IssuerTimeoutReversal, "Error occurred while reversing a charge due to an issuer timeout.", e);
                    }
                    catch(HpsException e) {
                        throw new HpsCreditException(transactionId, HpsExceptionCodes.IssuerTimeoutReversal, "Error occurred while reversing a charge due to an issuer timeout.", e);
                    }
                }
                HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText);
            }
        }
    }

    public void processGatewayResponse(ElementTree response, String expectedType, BigDecimal amount) throws HpsException {
        String responseCode = response.get("Header").getString("GatewayRspCode");
        Integer transactionId = response.get("Header").getInt("GatewayTxnId");
        if(responseCode.equals("00"))
            return;

        if(responseCode.equals("30")){
            try{
                this.reverse(amount).withTransactionId(transactionId).execute();
            }
            catch(HpsException e) {
                throw new HpsGatewayException(HpsExceptionCodes.GatewayTimeoutReversalError, "Error occurred while reversing a charge due to a gateway timeout.", e);
            }
        }
        HpsGatewayResponseValidation.checkGatewayResponse(response, expectedType);
    }
}
