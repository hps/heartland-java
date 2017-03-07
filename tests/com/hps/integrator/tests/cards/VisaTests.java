package com.hps.integrator.tests.cards;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.infrastructure.HpsIssuerException;
import com.hps.integrator.infrastructure.HpsIssuerExceptionCodes;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VisaTests {
	
	@Test
	public void Visa_WhenCardIsOk_ShouldReturnValidResult() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("50"));
		assertEquals("00", response.getResponseCode());
	}
	
	// avs tests
	
	@Test
	public void Visa_AvsResultCode_ShouldEqualB() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("91.01"));
		assertEquals("B", response.getAvsResultCode());
	}
	
	@Test
	public void Visa_AvsResultCode_ShouldEqualC() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("91.02"));
		assertEquals("C", response.getAvsResultCode());
	}
	
	@Test
	public void Visa_AvsResultCode_ShouldEqualD() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("91.03"));
		assertEquals("D", response.getAvsResultCode());
	}
	
	@Test
	public void Visa_AvsResultCode_ShouldEqualI() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("91.05"));
		assertEquals("I", response.getAvsResultCode());
	}
	
	@Test
	public void Visa_AvsResultCode_ShouldEqualM() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("91.06"));
		assertEquals("M", response.getAvsResultCode());
	}
	
	@Test
	public void Visa_AvsResultCode_ShouldEqualP() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("91.07"));
		assertEquals("P", response.getAvsResultCode());
	}
	
	// cvv tests
	
	@Test
	public void Visa_CvvResultCode_ShouldEqualM() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("96.01"));
		assertEquals("M", response.getCvvResultCode());
	}
	
	@Test
	public void Visa_CvvResultCode_ShouldEqualN() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("96.02"));
		assertEquals("N", response.getCvvResultCode());
	}
	
	@Test
	public void Visa_CvvResultCode_ShouldEqualP() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("96.03"));
		assertEquals("P", response.getCvvResultCode());
	}
	
	@Test
	public void Visa_CvvResultCode_ShouldEqualS() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("96.04"));
		assertEquals("S", response.getCvvResultCode());
	}
	
	@Test
	public void Visa_CvvResultCode_ShouldEqualU() throws HpsException
	{
		HpsCharge response = this.chargeValidVisa(new BigDecimal("96.05"));
		assertEquals("U", response.getCvvResultCode());
	}
	
	// visa to visa 2nd
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateReferCardIssuer() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.34"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("02", e.getDetails().getIssuerResponseCode());
			assertEquals("CALL", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateInvalidMerchant() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.22"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("03", e.getDetails().getIssuerResponseCode());
			assertEquals("TERM ID ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicatePickUpCard() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.04"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("44", e.getDetails().getIssuerResponseCode());
			assertEquals("HOLD-CALL", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateDoNotHonor() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.25"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("05", e.getDetails().getIssuerResponseCode());
			assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateInvalidTransaction() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.26"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("12", e.getDetails().getIssuerResponseCode());
			assertEquals("INVALID TRANS", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateInvalidAmount() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.27"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.InvalidAmount, e.getCode());
			assertEquals("13", e.getDetails().getIssuerResponseCode());
			assertEquals("AMOUNT ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateInvalidCard() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.28"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.IncorrectNumber, e.getCode());
			assertEquals("14", e.getDetails().getIssuerResponseCode());
			assertEquals("CARD NO. ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateInvalidIssuer() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.18"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("15", e.getDetails().getIssuerResponseCode());
			assertEquals("NO SUCH ISSUER", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateSystemErrorReenter() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.29"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("19", e.getDetails().getIssuerResponseCode());
			assertEquals("RE ENTER", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateLostCard() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.31"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("41", e.getDetails().getIssuerResponseCode());
			assertEquals("HOLD-CALL", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateHotCardPickUp() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.03"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("43", e.getDetails().getIssuerResponseCode());
			assertEquals("HOLD-CALL", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateInsufficientFunds() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.08"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			//assertEquals("05", e.getDetails().getIssuerResponseCode());
			assertEquals("51", e.getDetails().getIssuerResponseCode());
			assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateNoCheckAccount() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.16"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("52", e.getDetails().getIssuerResponseCode());
			assertEquals("NO CHECK ACCOUNT", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateNoSavingAccount() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.17"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("53", e.getDetails().getIssuerResponseCode());
			assertEquals("NO SAVE ACCOUNT", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateExpiredCard() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.32"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ExpiredCard, e.getCode());
			assertEquals("54", e.getDetails().getIssuerResponseCode());
			assertEquals("EXPIRED CARD", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateTxnNotPermittedOnCard() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.20"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.UnknownCreditError, e.getCode());
			assertEquals("R1", e.getDetails().getIssuerResponseCode());
			assertEquals("STOP RECURRING", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateInvalidAcquirer() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.30"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("58", e.getDetails().getIssuerResponseCode());
			assertEquals("SERV NOT ALLOWED", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateExceedsLimit() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.09"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("61", e.getDetails().getIssuerResponseCode());
			assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateRestrictedCard() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.10"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("62", e.getDetails().getIssuerResponseCode());
			assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateSecurityViolation() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.11"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("65", e.getDetails().getIssuerResponseCode());
			assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateCheckDigitErr() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.05"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.IncorrectCvc, e.getCode());
			assertEquals("EB", e.getDetails().getIssuerResponseCode());
			assertEquals("CHECK DIGIT ERR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsException.class)
	public void Visa_ResponseCode_ShouldIndicateSwitchNotAvailable() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.33"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.IssuerTimeout, e.getCode());
			assertEquals("91", e.getDetails().getIssuerResponseCode());
			assertEquals("NO REPLY", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateSystemError() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.21"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("96", e.getDetails().getIssuerResponseCode());
			assertEquals("SYSTEM ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Visa_ResponseCode_ShouldIndicateCvv2Mismatch() throws HpsException
	{
		try 
		{
			this.chargeValidVisa(new BigDecimal("10.23"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.IncorrectCvc, e.getCode());
			assertEquals("N7", e.getDetails().getIssuerResponseCode());
			assertEquals("CVV2 MISMATCH", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	// verify, authorize & capture
	
	@Test
	public void Visa_Verify_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAccountVerify response = service.verify(TestCreditCards.validVisa(), TestCardHolders.validCardHolder());
		assertEquals("85", response.getResponseCode());
	}

    @Test
    public void Visa_VerifyWithToken_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validVisa(), TestCardHolders.validCardHolder(), true, false, false);
        response = service.verify(response.getTokenData().getTokenValue(), TestCardHolders.validCardHolder());

        assertEquals("85", response.getResponseCode());
    }
	
	@Test
	public void Visa_Authorize_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validVisa(),
                TestCardHolders.validCardHolder(), true);
		assertEquals("00", response.getResponseCode());
	}
	
	@Test
	public void Visa_AuthorizeAndRequestToken_ShouldGetTokenAndReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validVisa(),
                TestCardHolders.validCardHolder(), true, true, null, null, false, false, false);
		assertEquals(0, response.getTokenData().getTokenRspCode());
		assertEquals("00", response.getResponseCode());
	}
	
	@Test
	public void Visa_ShouldRefund_Ok() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization charge = service.charge(new BigDecimal("25.00"), "usd", TestCreditCards.validVisa(),
                TestCardHolders.validCardHolder(), true);
		HpsRefund refund = service.refund(new BigDecimal("25.00"), "usd", charge.getTransactionID());
		assertEquals("00", refund.getResponseCode());
	}
	
	@Test
	public void Visa_Capture_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization auth = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validVisa(),
                TestCardHolders.validCardHolder(), true);
		assertEquals("00", auth.getResponseCode());
		
		HpsTransaction capture = service.captureTxn(auth.getTransactionID());
		assertEquals("00", capture.getResponseCode());		
	}

    @Test
    public void Visa_Void_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAuthorization charge = service.charge(new BigDecimal("50"), "usd", TestCreditCards.validVisa(), TestCardHolders.validCardHolder(), true);
        assertEquals("00", charge.getResponseCode());

        HpsTransaction voidResult = service.voidTxn(charge.getTransactionID());
        assertEquals("00", voidResult.getResponseCode());
    }

	@Test
	public void Visa_UpdateTokenExpiration_ShouldReturnOk() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validCertMultiUseConfig());
		HpsManageToken manage = service.updateTokenExpiration(TestCreditCards.validVisaMUT(), 1, 2019);
		assertEquals("00", manage.getResponseCode());
	}
	
	private HpsCharge chargeValidVisa(BigDecimal amt) throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsCharge charge = service.charge(amt, "usd", TestCreditCards.validVisa(),
                TestCardHolders.validCardHolder(), true);
		assertNotNull(charge);
		return charge;
	}
}
