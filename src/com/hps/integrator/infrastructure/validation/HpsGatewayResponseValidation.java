package com.hps.integrator.infrastructure.validation;

import com.hps.integrator.infrastructure.*;

public class HpsGatewayResponseValidation {
    public static HpsException getException(int responseCode, String responseText) {
        switch (responseCode) {
            case 0:
                return null;
            case -2:
                return new HpsAuthenticationException(HpsExceptionCodes.AuthenticationError,
                        "Authentication error. Please double check your service configuration.");
            case 1:
                return new HpsGatewayException(HpsExceptionCodes.UnknownGatewayError, responseText, responseCode, responseText);
            case 3:
                return new HpsGatewayException(HpsExceptionCodes.InvalidOriginalTransaction, responseText, responseCode, responseText);
            case 5:
                return new HpsGatewayException(HpsExceptionCodes.NoOpenBatch, responseText, responseCode, responseText);
            case 12:
                return new HpsGatewayException(HpsExceptionCodes.InvalidCpcData, "Invalid CPC data.", responseCode, responseText);
            case 27:
            case 34:
            case 26:
            case 13:
                return new HpsGatewayException(HpsExceptionCodes.InvalidCardData, "Invalid card data.", responseCode, responseText);
            case 14:
                return new HpsGatewayException(HpsExceptionCodes.InvalidNumber, "The card number is not valid.", responseCode, responseText);
            case 30:
                return new HpsGatewayException(HpsExceptionCodes.GatewayTimeout, "Gateway timed out.", responseCode, responseText);
            default:
                return new HpsGatewayException(HpsExceptionCodes.UnknownGatewayError, responseText, responseCode, responseText);
        }
    }

    public static void checkGatewayResponse(ElementTree response, String tagName) throws HpsException {
        Element header = response.get("Header");
        HpsException e = getException(header.getInt("GatewayRspCode"), header.getString("GatewayRspMsg"));
        if (e != null) { throw e; }

        if(response.get("Transaction") != null && !response.get("Transaction").has(tagName))
            throw new HpsGatewayException(HpsExceptionCodes.UnexpectedGatewayResponse, "Unexpected response from HPS gateway.");
    }
}
