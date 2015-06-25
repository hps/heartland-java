package com.hps.integrator.infrastructure;

public class HpsGatewayException extends HpsException {
    private HpsExceptionCodes code;
    private HpsGatewayExceptionDetails details;

    public HpsGatewayException(HpsExceptionCodes code, String message) {
        super(message);
        this.setCode(code);
    }

    public HpsGatewayException(HpsExceptionCodes code, String message, Exception innerException) {
        super(message, innerException);
        this.setCode(code);
    }

    public HpsGatewayException(HpsExceptionCodes code, String message, int gatewayResponseCode,
                               String gatewayResponseMessage) {
        super(message);
        this.setCode(code);

        HpsGatewayExceptionDetails details = new HpsGatewayExceptionDetails();
        details.setGatewayResponseCode(gatewayResponseCode);
        details.setGatewayResponseMessage(gatewayResponseMessage);
        this.setDetails(details);
    }

    public HpsGatewayException(HpsExceptionCodes code, String message, int gatewayResponseCode,
                               String gatewayResponseMessage, Exception innerException) {
        super(message, innerException);
        this.setCode(code);

        HpsGatewayExceptionDetails details = new HpsGatewayExceptionDetails();
        details.setGatewayResponseCode(gatewayResponseCode);
        details.setGatewayResponseMessage(gatewayResponseMessage);
        this.setDetails(details);
    }

    public HpsExceptionCodes getCode() {
        return code;
    }

    public void setCode(HpsExceptionCodes code) {
        this.code = code;
    }

    public HpsGatewayExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(HpsGatewayExceptionDetails details) {
        this.details = details;
    }
}
