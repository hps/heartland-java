package com.hps.integrator.infrastructure;

public class HpsCreditExceptionDetails {
    String issuerResponseCode;
    String issuerResponseMessage;

    public String getIssuerResponseCode() {
        return issuerResponseCode;
    }

    public void setIssuerResponseCode(String issuerResponseCode) {
        this.issuerResponseCode = issuerResponseCode;
    }

    public String getIssuerResponseMessage() {
        return issuerResponseMessage;
    }

    public void setIssuerResponseMessage(String issuerResponseMessage) {
        this.issuerResponseMessage = issuerResponseMessage;
    }
}
