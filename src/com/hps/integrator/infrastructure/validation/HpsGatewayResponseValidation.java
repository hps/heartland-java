package com.hps.integrator.infrastructure.validation;

import PosGateway.Exchange.Hps.PosResponse;
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
                return new HpsGatewayException(HpsGatewayExceptionCodes.UnknownGatewayError, responseText, responseCode, responseText);
            case 3:
                return new HpsGatewayException(HpsGatewayExceptionCodes.InvalidOriginalTransaction, responseText, responseCode, responseText);
            case 5:
                return new HpsGatewayException(HpsGatewayExceptionCodes.NoOpenBatch, responseText, responseCode, responseText);
            case 12:
                return new HpsGatewayException(HpsGatewayExceptionCodes.InvalidCpcData, "Invalid CPC data.", responseCode, responseText);
            case 13:
                return new HpsGatewayException(HpsGatewayExceptionCodes.InvalidCardData, "Invalid card data.", responseCode, responseText);
            case 14:
                return new HpsGatewayException(HpsGatewayExceptionCodes.InvalidNumber, "The card number is not valid.", responseCode, responseText);
            case 30:
                return new HpsGatewayException(HpsGatewayExceptionCodes.GatewayTimeout, "Gateway timed out.", responseCode, responseText);
            default:
                return new HpsGatewayException(HpsGatewayExceptionCodes.UnknownGatewayError, responseText, responseCode, responseText);
        }
    }

    public static void checkGatewayResponse(PosResponse response) throws HpsException {
        HpsException e = getException(response.Ver10.Header.GatewayRspCode,
                response.Ver10.Header.GatewayRspMsg);
        if (e != null) { throw e; }
    }
}
