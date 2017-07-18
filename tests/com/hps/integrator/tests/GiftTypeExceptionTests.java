package com.hps.integrator.tests;

import org.junit.Test;

import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.HpsCardType;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

public class GiftTypeExceptionTests {

	/* Positive Test cases */
	@Test(expected = HpsException.class)
	public void testCardTypeGiftUnknownGiftError() throws HpsException {
		Integer transactionId = 101212;
		String responseCode = "11"; /* An unknown gift error has occurred. */
		String responseText = "DECLINE";
		HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText, HpsCardType.Gift);
	}

	@Test(expected = HpsException.class)
	public void testCardTypeGiftCardDeclined() throws HpsException {
		Integer transactionId = 101212;
		String responseCode = "12"; /* The card was declined" */
		String responseText = "DECLINE";
		HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText, HpsCardType.Gift);
	}

	@Test(expected = HpsException.class)
	public void testCardTypeGiftProcessingError() throws HpsException {
		Integer transactionId = 101212;
		String responseCode = "6"; /* "An error occurred while processing the card." */
		String responseText = "DECLINE";
		HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText, HpsCardType.Gift);
	}

	@Test(expected = HpsException.class)
	public void testCardTypeGiftInvalidCardData() throws HpsException {
		Integer transactionId = 101212;
		String responseCode = "3"; /* The card data is invalid." */
		String responseText = "DECLINE";
		HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText, HpsCardType.Gift);
	}

	@Test(expected = HpsException.class)
	public void testCardTypeGiftExpiredCard() throws HpsException {
		Integer transactionId = 101212;
		String responseCode = "4"; /* The card has expired." */
		String responseText = "DECLINE";
		HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText, HpsCardType.Gift);
	}
}
