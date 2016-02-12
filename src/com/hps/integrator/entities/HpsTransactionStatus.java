package com.hps.integrator.entities;

import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

public class HpsTransactionStatus extends HpsTransaction {
    String originalGatewayResponseCode;
    String originalGatewayResponseText;
    String originalResponseCode;
    String originalResponseText;
    String transactionStatus;
    String originalTransactionId;
    Object altPayment;
    String timezoneConversion;

    public String getOriginalGatewayResponseCode() {
        return originalGatewayResponseCode;
    }
    public void setOriginalGatewayResponseCode(String originalGatewayResponseCode) {
        this.originalGatewayResponseCode = originalGatewayResponseCode;
    }
    public String getOriginalGatewayResponseText() {
        return originalGatewayResponseText;
    }
    public void setOriginalGatewayResponseText(String originalGatewayResponseText) {
        this.originalGatewayResponseText = originalGatewayResponseText;
    }
    public String getOriginalResponseCode() {
        return originalResponseCode;
    }
    public void setOriginalResponseCode(String originalResponseCode) {
        this.originalResponseCode = originalResponseCode;
    }
    public String getOriginalResponseText() {
        return originalResponseText;
    }
    public void setOriginalResponseText(String originalResponseText) {
        this.originalResponseText = originalResponseText;
    }
    public String getTransactionStatus() {
        return transactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public String getOriginalTransactionId() {
        return originalTransactionId;
    }
    public void setOriginalTransactionId(String originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }
    public Object getAltPayment() {
        return altPayment;
    }
    public void setAltPayment(Object altPayment) {
        this.altPayment = altPayment;
    }
    public String getTimezoneConversion() {
        return timezoneConversion;
    }
    public void setTimezoneConversion(String timezoneConversion) {
        this.timezoneConversion = timezoneConversion;
    }

    public HpsTransactionStatus() {
        super();
    }

    public HpsTransactionStatus fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);

        Element statusResponse = rsp.get("Transaction").firstChild();
        this.originalGatewayResponseCode = statusResponse.has("GatewayRspCode") ? statusResponse.getString("GatewayRspCode") : null;
        this.originalGatewayResponseText = statusResponse.has("GatewayRspMsg") ? statusResponse.getString("GatewayRspMsg") : null;
        this.originalResponseCode = statusResponse.has("RspCode") ? statusResponse.getString("RspCode") : null;
        this.originalResponseText = statusResponse.has("RspText") ? statusResponse.getString("RspText") : null;
        this.transactionStatus = statusResponse.has("TxnStatus") ? statusResponse.getString("TxnStatus") : null;
        this.originalTransactionId = statusResponse.has("GatewayTxnId") ? statusResponse.getString("GatewayTxnId") : null;
        this.altPayment = statusResponse.has("AltPayment") ? statusResponse.getString("AltPayment") : null;
        this.timezoneConversion = statusResponse.has("TzConversion") ? statusResponse.getString("TzConversion") : null;

        return this;
    }
}
