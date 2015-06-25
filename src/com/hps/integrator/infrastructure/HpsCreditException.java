package com.hps.integrator.infrastructure;

public class HpsCreditException extends HpsException {
    Integer transactionId;
    HpsExceptionCodes code;
    HpsCreditExceptionDetails details;

    public HpsCreditException(Integer transactionId, HpsExceptionCodes code, String message) {
        this(transactionId, code, message, null);
    }
    public HpsCreditException(Integer transactionId, HpsExceptionCodes code, String message, Exception innerException) {
        super(message, innerException);

        this.transactionId = transactionId;
        this.code = code;
    }
    public HpsCreditException(Integer transactionId, HpsExceptionCodes code, String message, String issuerCode, String issuerMessage) {
        this(transactionId, code, message, issuerCode, issuerMessage, null);
    }
    public HpsCreditException(Integer transactionId, HpsExceptionCodes code, String message, String issuerCode, String issuerMessage, Exception innerException) {
        super(message, innerException);

        this.transactionId = transactionId;
        this.code = code;

        this.details = new HpsCreditExceptionDetails();
        this.details.setIssuerResponseCode(issuerCode);
        this.details.setIssuerResponseMessage(issuerMessage);
    }
}
