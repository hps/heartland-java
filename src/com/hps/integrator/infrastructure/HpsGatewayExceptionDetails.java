package com.hps.integrator.infrastructure;

public class HpsGatewayExceptionDetails {
    private int gatewayResponseCode;
    private String gatewayResponseMessage;

    public int getGatewayResponseCode() {
        return gatewayResponseCode;
    }

    public void setGatewayResponseCode(int gatewayResponseCode) {
        this.gatewayResponseCode = gatewayResponseCode;
    }

    public String getGatewayResponseMessage() {
        return gatewayResponseMessage;
    }

    public void setGatewayResponseMessage(String gatewayResponseMessage) {
        this.gatewayResponseMessage = gatewayResponseMessage;
    }
}
