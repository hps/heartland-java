package com.hps.integrator.infrastructure;

public class HpsGatewayException extends HpsException {
    private HpsGatewayExceptionCodes code;
    private HpsGatewayExceptionDetails details;

    public HpsGatewayException(HpsGatewayExceptionCodes code, String message) {
        super(message);
        this.setCode(code);
    }

    public HpsGatewayException(HpsGatewayExceptionCodes code, String message, Exception innerException) {
        super(message, innerException);
        this.setCode(code);
    }

    public HpsGatewayException(HpsGatewayExceptionCodes code, String message, int gatewayResponseCode,
                               String gatewayResponseMessage) {
        super(message);
        this.setCode(code);

        HpsGatewayExceptionDetails details = new HpsGatewayExceptionDetails();
        details.setGatewayResponseCode(gatewayResponseCode);
        details.setGatewayResponseMessage(gatewayResponseMessage);
        this.setDetails(details);
    }

    public HpsGatewayException(HpsGatewayExceptionCodes code, String message, int gatewayResponseCode,
                               String gatewayResponseMessage, Exception innerException) {
        super(message, innerException);
        this.setCode(code);

        HpsGatewayExceptionDetails details = new HpsGatewayExceptionDetails();
        details.setGatewayResponseCode(gatewayResponseCode);
        details.setGatewayResponseMessage(gatewayResponseMessage);
        this.setDetails(details);
    }

    public HpsGatewayExceptionCodes getCode() {
        return code;
    }

    public void setCode(HpsGatewayExceptionCodes code) {
        this.code = code;
    }

    public HpsGatewayExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(HpsGatewayExceptionDetails details) {
        this.details = details;
    }
}
