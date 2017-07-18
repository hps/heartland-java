package com.hps.integrator.infrastructure.validation;

import com.hps.integrator.infrastructure.HpsIssuerException;
import com.hps.integrator.infrastructure.HpsIssuerExceptionCodes;
import com.hps.integrator.infrastructure.emums.HpsCardType;

import java.util.HashMap;

public class HpsIssuerResponseValidation {
    private static final HashMap<String, HpsIssuerExceptionCodes> issuerCodeToCreditExceptionCode;
    private static final HashMap<HpsIssuerExceptionCodes, String> creditExceptionCodeToMessage;
    private static final HashMap<String, HpsIssuerExceptionCodes> issuerCodeToGiftExceptionCode;
    
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

        issuerCodeToGiftExceptionCode = new HashMap<String, HpsIssuerExceptionCodes>();
        issuerCodeToGiftExceptionCode.put("1",  HpsIssuerExceptionCodes.UnknownGiftError);
        issuerCodeToGiftExceptionCode.put("2",  HpsIssuerExceptionCodes.UnknownGiftError);
        issuerCodeToGiftExceptionCode.put("11", HpsIssuerExceptionCodes.UnknownGiftError);
        issuerCodeToGiftExceptionCode.put("13", HpsIssuerExceptionCodes.PartialApproval);
        issuerCodeToGiftExceptionCode.put("14", HpsIssuerExceptionCodes.InvalidPin);
        issuerCodeToGiftExceptionCode.put("3",  HpsIssuerExceptionCodes.InvalidCardData);
        issuerCodeToGiftExceptionCode.put("8",  HpsIssuerExceptionCodes.InvalidCardData);
        issuerCodeToGiftExceptionCode.put("4",  HpsIssuerExceptionCodes.ExpiredCard);
        issuerCodeToGiftExceptionCode.put("5",  HpsIssuerExceptionCodes.CardDeclined);
        issuerCodeToGiftExceptionCode.put("12", HpsIssuerExceptionCodes.CardDeclined);
        issuerCodeToGiftExceptionCode.put("6",  HpsIssuerExceptionCodes.ProcessingError);
        issuerCodeToGiftExceptionCode.put("7",  HpsIssuerExceptionCodes.ProcessingError);
        issuerCodeToGiftExceptionCode.put("9",  HpsIssuerExceptionCodes.InvalidAmount);
        issuerCodeToGiftExceptionCode.put("10", HpsIssuerExceptionCodes.InvalidAmount);
        
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
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.IssuerTimeout, "The card holder's bank is not replying " +
                "to the credit card transaction. Try waiting and then rerunning the transaction.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.IncorrectNumber, "Account number entered incorrectly " +
                "(bad swipe or mistyped). Verify account number and re-enter (or re-swipe if card is on hand).");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.UnknownIssuerError, "An unknown issuer error has occurred.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.UnknownGiftError, "An unknown gift error has occurred.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.PartialApproval, "The amount was partially approved.");
        creditExceptionCodeToMessage.put(HpsIssuerExceptionCodes.InvalidCardData, "The card data is invalid.");
    }

    public static void checkIssuerResponse(int transactionId, String responseCode, String responseText) throws HpsIssuerException {
        HpsIssuerException e = getException(transactionId, responseCode, responseText);
        if(e != null) { throw e; }       
    }
    public static void checkIssuerResponse(int transactionId, String responseCode, String responseText, HpsCardType cardType) throws HpsIssuerException {
        HpsIssuerException e = getException(transactionId, responseCode, responseText, cardType);
        if(e != null) { throw e; }       
    }

    public static HpsIssuerException getException(Integer transactionId, String responseCode, String responseText) {
        if (responseCode.equals("85") || responseCode.equals("10") || responseCode.equals("00") || responseCode.equals("0")) return null;

        HpsIssuerExceptionCodes code = issuerCodeToCreditExceptionCode.containsKey(responseCode) ? issuerCodeToCreditExceptionCode.get(responseCode) : null;
        if(code != null) {
            String msg = creditExceptionCodeToMessage.containsKey(code) ? creditExceptionCodeToMessage.get(code) : "Unknown issuer error.";
            return new HpsIssuerException(transactionId, code, msg, responseCode, responseText);
        } else {
            return new HpsIssuerException(transactionId, HpsIssuerExceptionCodes.UnknownCreditError,
                    "An unknown issuer error has occurred.", responseCode, responseText);
        }
    }
    public static HpsIssuerException getException(Integer transactionId, String responseCode, String responseText, HpsCardType cardType) throws HpsIssuerException {
		if (responseCode.equals("85") || responseCode.equals("10") || responseCode.equals("00") || responseCode.equals("0")) return null;

		HashMap<HpsCardType, String> cardTypehMap = new HashMap<HpsCardType, String>();
		cardTypehMap.put(HpsCardType.Gift, "Gift");
		cardTypehMap.put(HpsCardType.Credit, "Credit");

		if (cardTypehMap.get(cardType).equals("Credit")) {
			checkIssuerResponse(transactionId, responseCode, responseText);
		}
		if (cardTypehMap.get(cardType).equals("Gift")) {
			HpsIssuerExceptionCodes giftCode = issuerCodeToGiftExceptionCode.containsKey(responseCode) ? issuerCodeToGiftExceptionCode.get(responseCode) : null;
			if (giftCode != null) {
				String msg = creditExceptionCodeToMessage.containsKey(giftCode) ? creditExceptionCodeToMessage.get(giftCode): "Unknown issuer error.";
				return new HpsIssuerException(transactionId, giftCode, msg, responseCode, responseText);
			} else {
				return new HpsIssuerException(transactionId, HpsIssuerExceptionCodes.UnknownGiftError, "An unknown issuer error has occurred.", responseCode, responseText);
			}
		}
		 return new HpsIssuerException(transactionId, HpsIssuerExceptionCodes.UnknownIssuerError, "An unknown issuer error has occurred.", responseCode, responseText);
	}
}