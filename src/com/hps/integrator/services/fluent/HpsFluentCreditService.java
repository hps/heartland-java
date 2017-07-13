package com.hps.integrator.services.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionType;
import com.hps.integrator.entities.credit.HpsReportTransactionDetails;
import com.hps.integrator.entities.credit.HpsReportTransactionSummary;
import com.hps.integrator.fluent.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;
import com.hps.integrator.services.HpsSoapGatewayService;

import java.math.BigDecimal;

public class HpsFluentCreditService extends HpsSoapGatewayService {
    HpsTransactionType filterBy;

    public void setFilterBy(HpsTransactionType filter) { this.filterBy = filter; }

    public HpsFluentCreditService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }
    public HpsFluentCreditService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        super(config, enableLogging);
    }

    public HpsFluentCreditService withConfig(IHpsServicesConfig config) {
        this.servicesConfig = config;
        return this;
    }

    public CreditAuthBuilder authorize() {
        return this.authorize(null);
    }
    public CreditAuthBuilder authorize(BigDecimal amount) {
        return new CreditAuthBuilder(this).withAmount(amount).withCurrency("USD");
    }

    public CreditAdditionalAuthBuilder additionalAuth() {
        return this.additionalAuth(null);
    }
    public CreditAdditionalAuthBuilder additionalAuth(BigDecimal amount) {
        return new CreditAdditionalAuthBuilder(this).withAmount(amount);
    }

    public CreditCaptureBuilder capture() {
        return this.capture(null);
    }
    public CreditCaptureBuilder capture(Integer transactionId){
        return new CreditCaptureBuilder(this).withTransactionId(transactionId);
    }

    public CreditChargeBuilder charge() {
        return this.charge(null);
    }
    public CreditChargeBuilder charge(BigDecimal amount) {
        return new CreditChargeBuilder(this).withAmount(amount).withCurrency("USD");
    }

    public CreditCpcEditBuilder cpcEdit() {
        return this.cpcEdit(null);
    }
    public CreditCpcEditBuilder cpcEdit(Integer transactionId) {
        return new CreditCpcEditBuilder(this).withTransactionId(transactionId);
    }

    public CreditEditBuilder edit() {
        return this.edit(null);
    }
    public CreditEditBuilder edit(Integer transactionId) {
        return new CreditEditBuilder(this).withTransactionId(transactionId);
    }

    public CreditGetBuilder get(Integer transactionId) {
        return new CreditGetBuilder(this).withTransactionId(transactionId);
    }

    public CreditListBuilder list() {
        return new CreditListBuilder(this);
    }

    public CreditOfflineAuthBuilder offlineAuth() {
        return this.offlineAuth(null);
    }
    public CreditOfflineAuthBuilder offlineAuth(BigDecimal amount) {
        return new CreditOfflineAuthBuilder(this).withAmount(amount);
    }

    public CreditOfflineChargeBuilder offlineCharge(){
        return this.offlineCharge(null);
    }
    public CreditOfflineChargeBuilder offlineCharge(BigDecimal amount){
        return new CreditOfflineChargeBuilder(this).withAmount(amount);
    }

    public CreditRecurringBuilder recurring() {
        return this.recurring(null);
    }
    public CreditRecurringBuilder recurring(BigDecimal amount) {
        return new CreditRecurringBuilder(this).withAmount(amount);
    }

    public CreditRefundBuilder refund() {
        return this.refund(null);
    }
    public CreditRefundBuilder refund(BigDecimal amount) {
        return new CreditRefundBuilder(this).withAmount(amount);
    }

    public CreditReverseBuilder reverse() {
        return this.reverse(null);
    }
    public CreditReverseBuilder reverse(BigDecimal amount) {
        return new CreditReverseBuilder(this).withAmount(amount);
    }

    public CreditVerifyBuilder verify(){
        return new CreditVerifyBuilder(this);
    }

    public CreditVoidBuilder creditVoid() {
        return this.creditVoid(null);
    }
    public CreditVoidBuilder creditVoid(Integer transactionId) {
        return new CreditVoidBuilder(this).withTransactionId(transactionId);
    }

    public CreditBalanceInquiryBuilder prepaidBalanceInquiry() {
        return new CreditBalanceInquiryBuilder(this);
    }

    public CreditAddValueBuilder prepaidAddValue() {
        return this.prepaidAddValue(null);
    }
    public CreditAddValueBuilder prepaidAddValue(BigDecimal amount) {
        return new CreditAddValueBuilder(this).withAmount(amount);
    }

    public ElementTree submitTransaction(Element transaction) throws HpsException {
        return this.submitTransaction(transaction, null);
    }
    public ElementTree submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
        ElementTree rsp = this.doTransaction(transaction, clientTransactionId);

        BigDecimal amount = null;
        if(transaction.tag().equals("CreditSale") || transaction.tag().equals("CreditAuth")) {
            amount = new BigDecimal(transaction.getString("Amt"));
        }

        this.processGatewayResponse(rsp, transaction.tag(), amount);
        this.processIssuerResponse(rsp, transaction.tag(), amount);

        return rsp;
    }

    public void processIssuerResponse(ElementTree response, String expectedType, BigDecimal amount) throws HpsException {
        Integer transactionId = response.get("Header").getInt("GatewayTxnId");
        Element transaction = response.get(expectedType);

        if(transaction != null) {
            String responseCode = transaction.getString("RspCode");
            String responseText = transaction.getString("RspText");

            if(responseCode != null && !responseCode.equals("")) {
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
