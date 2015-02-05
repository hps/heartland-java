package com.hps.integrator.infrastructure.validation;

import com.hps.integrator.infrastructure.HpsIssuerException;
import com.hps.integrator.infrastructure.HpsIssuerExceptionCodes;

import java.util.HashMap;

public class HpsIssuerResponseValidation {
    private static final HashMap<String, HpsIssuerExceptionCodes> issuerCodeToCreditExceptionCode;
    private static final HashMap<HpsIssuerExceptionCodes, String> creditExceptionCodeToMessage;
    static
    {
        String[] declinedCodes = new String[] {"02", "03", "04", "05", "41", "43", "44", "51", "56", "61", "62", "63", "65", "78"},
                processingErrorCodes = new String[] {"06", "07", "12", "15", "19", "12", "52", "53", "57", "58", "76", "77", "96", "EC"};

        issuerCodeToCreditExceptionCode = new HashMap<String, HpsIssuerExceptionCodes>();
        for (String code : declinedCodes) {
            issuerCodeToCreditExceptionCode.put(code, HpsIssuerExceptionCodes.CardDeclined);
        }

        for (String code : processingErrorCodes) {
            issuerCodeToCreditExceptionCode.put(code, HpsIssuerExceptionCodes.ProcessingError);
        }

        issuerCodeToCreditExceptionCode.put("13", HpsIssuerExceptionCodes.InvalidAmount);
        issuerCodeToCreditExceptionCode.put("14", HpsIssuerExceptionCodes.IncorrectNumber);
        issuerCodeToCreditExceptionCode.put("54", HpsIssuerExceptionCodes.ExpiredCard);
        issuerCodeToCreditExceptionCode.put("55", HpsIssuerExceptionCodes.InvalidPin);
        issuerCodeToCreditExceptionCode.put("75", HpsIssuerExceptionCodes.PinRetriesExceeded);
        issuerCodeToCreditExceptionCode.put("80", HpsIssuerExceptionCodes.InvalidExpiry);
        issuerCodeToCreditExceptionCode.put("86", HpsIssuerExceptionCodes.PinVerification);
        issuerCodeToCreditExceptionCode.put("91", HpsIssuerExceptionCodes.IssuerTimeout);
        issuerCodeToCreditExceptionCode.put("EB", HpsIssuerExceptionCodes.IncorrectCvc);
        issuerCodeToCreditExceptionCode.put("N7", HpsIssuerExceptionCodes.IncorrectCvc);

        creditExceptionCodeToMessage = new HashMap<HpsIssuerExceptionCodes, String>();
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.CardDeclined, "The card was declined");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.ProcessingError, "An error occurred while processing the card.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.InvalidAmount, "Must be greater than or equal 0.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.ExpiredCard, "The card has expired.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.InvalidPin, "The 4-digit pin is invalid.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.PinRetriesExceeded, "Maximum number of pin retries exceeded.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.InvalidExpiry, "Card expiration date is invalid.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.PinVerification, "Can't verify card pin number.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.IncorrectCvc, "The card's security code is incorrect.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.IssuerTimeout, "The card issuer timed-out.");
    }

    public static void checkIssuerResponse(int transactionId, String responseCode, String responseText) throws HpsIssuerException {
        HpsIssuerException e = getException(transactionId, responseCode, responseText);
        if(e != null) { throw e; }
    }

    public static HpsIssuerException getException(int transactionId, String responseCode, String responseText) throws HpsIssuerException {
        if (responseCode.equals("85") || responseCode.equals("00")) return null;

        HpsIssuerExceptionCodes code = issuerCodeToCreditExceptionCode.containsKey(responseCode) ? issuerCodeToCreditExceptionCode.get(responseCode) : null;
        if(code != null) {
            String msg = creditExceptionCodeToMessage.containsKey(code) ? creditExceptionCodeToMessage.get(code) : "Unknown issuer error.";
            return new HpsIssuerException(transactionId, code, msg, responseCode, responseText);
        } else {
            return new HpsIssuerException(transactionId, HpsIssuerExceptionCodes.UnknownCreditError,
                    "An unknown issuer error has occurred.", responseCode, responseText);
        }
    }
}