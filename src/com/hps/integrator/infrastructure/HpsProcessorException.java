package com.hps.integrator.infrastructure;

public class HpsProcessorException extends HpsException {
    String transactionId;
    String code;
    HpsProcessorExceptionDetails details;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HpsProcessorExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(HpsProcessorExceptionDetails details) {
        this.details = details;
    }

    public HpsProcessorException(String transactionId, String code, String message) {
        this(transactionId, code, message, null, null, null);
    }
    public HpsProcessorException(String transactionId, String code, String message, String processorCode, String processorMessage) {
        this(transactionId, code, message, processorCode, processorMessage, null);
    }
    public HpsProcessorException(String transactionId, String code, String message, String processorCode, String processorMessage, Exception innerException) {
        super(message, innerException);

        this.transactionId = transactionId;
        this.code = code;

        if(processorCode != null || processorMessage != null) {
            this.details = new HpsProcessorExceptionDetails();
            this.details.setProcessorResponseCode(processorCode);
            this.details.setProcessorResponseText(processorMessage);
        }
    }
}
