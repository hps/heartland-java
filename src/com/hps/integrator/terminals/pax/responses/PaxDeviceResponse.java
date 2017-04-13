package com.hps.integrator.terminals.pax.responses;

import com.hps.integrator.infrastructure.HpsMessageException;
import com.hps.integrator.infrastructure.emums.ApplicationCryptogramType;
import com.hps.integrator.infrastructure.emums.PaxExtData;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;

public class PaxDeviceResponse extends PaxBaseResponse {
    private String referenceNumber;
    private String hostReferenceNumber;

    public String getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    public String getHostReferenceNumber() {
        return hostReferenceNumber;
    }
    public void setHostReferenceNumber(String hostReferenceNumber) {
        this.hostReferenceNumber = hostReferenceNumber;
    }

    public PaxDeviceResponse(byte[] buffer, PaxMsgId... messageIds) throws HpsMessageException {
        super(buffer, messageIds);
    }

    protected void mapResponse() {
        // host response
        if(hostResponse != null) {
            setResponseCode(hostResponse.getHostResponseCode());
            setResponseText(hostResponse.getHostResponseMessage());
            setApprovalCode(hostResponse.getAuthCode());
            setHostReferenceNumber(hostResponse.getHostReferenceNumber());
        }

        // amount
        if(amountResponse != null) {
            setTransactionAmount(amountResponse.getApprovedAmount());
            setAmountDue(amountResponse.getAmountDue());
            setTipAmount(amountResponse.getTipAmount());
            setCashBackAmount(amountResponse.getCashBackAmount());
        }

        // account
        if(accountResponse != null) {
            setMaskedCardNumber(HpsStringUtils.padLeft(accountResponse.getAccountNumber(), 16, '*'));
            setEntryMethod(accountResponse.getEntryMode().toString());
            setExpirationDate(accountResponse.getExpireDate());
            setPaymentType(accountResponse.getCardType().replace('_', ' '));
            setCardHolderName(accountResponse.getCardHolder());
            setCvvResponseCode(accountResponse.getCvdApprovalCode());
            setCvvResponseText(accountResponse.getCvdMessage());
            setCardPresent(accountResponse.isCardPresent());
        }

        // trace data
        if(traceResponse != null) {
            setTerminalRefNumber(traceResponse.getTransactionNumber());
            setReferenceNumber(traceResponse.getReferenceNumber());
        }

        // avs
        if(avsResponse != null) {
            setAvsResponseCode(avsResponse.getAvsResponseCode());
            setAvsResponseText(avsResponse.getAvsResponseMessage());
        }

        // commercial data
        if(commercialResponse != null) {
            setTaxExempt(commercialResponse.isTaxExempt());
            setTaxExemptId(commercialResponse.getTaxExemptId());
        }

        // ext data
        if(extDataResponse != null){
            setTransactionId(Integer.parseInt(extDataResponse.get(PaxExtData.HOST_REFERENCE_NUMBER)));
            setToken(extDataResponse.get(PaxExtData.TOKEN));
            setCardBIN(extDataResponse.get(PaxExtData.CARD_BIN));
            setSignatureStatus(extDataResponse.get(PaxExtData.SIGNATURE_STATUS));

            // emv stuff
            setApplicationPreferredName(extDataResponse.get(PaxExtData.APPLICATION_PREFERRED_NAME));
            setApplicationLabel(extDataResponse.get(PaxExtData.APPLICATION_LABEL));
            setApplicationId(extDataResponse.get(PaxExtData.APPLICATION_ID));
            setApplicationCryptogramType(ApplicationCryptogramType.TC);
            setApplicationCryptogram(extDataResponse.get(PaxExtData.TRANSACTION_CERTIFICATE));
            setCustomerVerificationMethod(extDataResponse.get(PaxExtData.CUSTOMER_VERIFICATION_METHOD));
            setTerminalVerificationResults(extDataResponse.get(PaxExtData.TERMINAL_VERIFICATION_RESULTS));
        }

        setTransactionType(xlateTransactionType(Integer.parseInt(getTransactionType())));
    }

    protected String xlateTransactionType(int transType) {
        switch(transType) {
            case 0:
                return "MENU";
            case 1:
                return "SALE";
            case 2:
                return "RETURN";
            case 3:
                return "AUTH";
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                return "VOID";
            default:
                return "UNKNOWN";
        }
    }
}
