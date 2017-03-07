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

public class MasterCardTests {
	
	@Test
	public void MasterCard_WhenCardIsOk_ShouldReturnValidResult() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("50"));
		assertEquals("00", response.getResponseCode());
	}
	
	// avs tests
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualA() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.01"));
		assertEquals("A", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualN() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.02"));
		assertEquals("N", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualR() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.03"));
		assertEquals("R", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualS() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.04"));
		assertEquals("S", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualU() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.05"));
		assertEquals("U", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualW() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.06"));
		assertEquals("W", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualX() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.07"));
		assertEquals("X", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualY() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.08"));
		assertEquals("Y", response.getAvsResultCode());
	}
	
	@Test
	public void MasterCard_AvsResultCode_ShouldEqualZ() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("90.09"));
		assertEquals("Z", response.getAvsResultCode());
	}
	
	// cvv tests
	
	@Test
	public void MasterCard_CvvResultCode_ShouldEqualM() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("95.01"));
		assertEquals("M", response.getCvvResultCode());		
	}
	
	@Test
	public void MasterCard_CvvResultCode_ShouldEqualN() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("95.02"));
		assertEquals("N", response.getCvvResultCode());		
	}
	
	@Test
	public void MasterCard_CvvResultCode_ShouldEqualP() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("95.03"));
		assertEquals("P", response.getCvvResultCode());		
	}
	
	@Test
	public void MasterCard_CvvResultCode_ShouldEqualU() throws HpsException
	{
		HpsCharge response = this.chargeValidMasterCard(new BigDecimal("95.04"));
		assertEquals("U", response.getCvvResultCode());		
	}
	
	// mastercard to 8583
	
	@Test(expected = HpsIssuerException.class)
	public void Mastercard_ResponseCode_ShouldIndicateReferCardIssuer() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.34"));
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
	public void Mastercard_ResponseCode_ShouldIndicateTermIdError() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.22"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("03", e.getDetails().getIssuerResponseCode());
			assertEquals("TERM ID ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		} catch (HpsException e) {
            e.printStackTrace();
        }
    }
	
	@Test(expected = HpsIssuerException.class)
	public void Mastercard_ResponseCode_ShouldIndicateInvalidMerchant() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.01"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("04", e.getDetails().getIssuerResponseCode());
			assertEquals("HOLD-CALL", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Mastercard_ResponseCode_ShouldIndicateDoNotHonor() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.25"));
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
	public void Mastercard_ResponseCode_ShouldIndicateInvalidTransaction() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.26"));
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
	public void Mastercard_ResponseCode_ShouldIndicateInvalidAmount() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.27"));
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
	public void Mastercard_ResponseCode_ShouldIndicateInvalidCard() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.28"));
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
	public void Mastercard_ResponseCode_ShouldIndicateInvalidIssuer() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.18"));
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
	public void Mastercard_ResponseCode_ShouldIndicateLostCard() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.31"));
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
	public void Mastercard_ResponseCode_ShouldIndicateHoldCall() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.03"));
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
	public void Mastercard_ResponseCode_ShouldIndicateDecline() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.08"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("51", e.getDetails().getIssuerResponseCode());
			assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Mastercard_ResponseCode_ShouldIndicateExpiredCard() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.32"));
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
	public void Mastercard_ResponseCode_ShouldIndicateExceedsLimit() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.09"));
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
	public void Mastercard_ResponseCode_ShouldIndicateRestrictedCard() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.10"));
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
	public void Mastercard_ResponseCode_ShouldIndicateSecurityViolation() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.19"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("63", e.getDetails().getIssuerResponseCode());
			assertEquals("SEC VIOLATION", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Mastercard_ResponseCode_ShouldIndicateExceedsFreqLimit() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.11"));
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
	public void Mastercard_ResponseCode_ShouldIndicateCardNoError() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.14"));
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
	public void Mastercard_ResponseCode_ShouldIndicateInvalidAccount() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.06"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			//assertEquals("79", e.getDetails().getIssuerResponseCode());
			assertEquals("EC", e.getDetails().getIssuerResponseCode());
			assertEquals("CID FORMAT ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	//@Test(expected = HpsCreditException.class)
	@Test(expected = HpsIssuerException.class)
	public void Mastercard_ResponseCode_ShouldIndicateSwitchNotAvailable() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.33"));
		} 
		catch (HpsIssuerException e)
		{
			//assertEquals("processing_error, e.getCode());
			//assertEquals("14", e.getDetails().getIssuerResponseCode());
			assertEquals(HpsIssuerExceptionCodes.IssuerTimeout, e.getCode());
			assertEquals("91", e.getDetails().getIssuerResponseCode());
			assertEquals("NO REPLY", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Mastercard_ResponseCode_ShouldIndicateSystemError() throws HpsException
	{
		try 
		{
			this.chargeValidMasterCard(new BigDecimal("10.21"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("96", e.getDetails().getIssuerResponseCode());
			assertEquals("SYSTEM ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	// verify, authorize, refund, capture & void
	
	@Test
	public void Mastercard_Verify_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAccountVerify response = service.verify(TestCreditCards.validMasterCard(), TestCardHolders.validCardHolder());
		assertEquals("85", response.getResponseCode());
	}

    @Test
    public void Mastercard_VerifyWithToken_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validMasterCard(), TestCardHolders.validCardHolder(), true, false, false);
        response = service.verify(response.getTokenData().getTokenValue(), TestCardHolders.validCardHolder());

        assertEquals("85", response.getResponseCode());
    }
	
	@Test
	public void Mastercard_Authorize_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validMasterCard(),
                TestCardHolders.validCardHolder(), true);
		assertEquals("00", response.getResponseCode());
	}
	
	@Test
	public void Mastercard_AuthorizeAndRequestToken_ShouldGetTokenAndReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validMasterCard(),
                TestCardHolders.validCardHolder(), true, true, null, null, false, false, false);
		assertEquals(0, response.getTokenData().getTokenRspCode());
		assertEquals("00", response.getResponseCode());
	}
	
	@Test
	public void MasterCard_ShouldRefund_Ok() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization charge = service.charge(new BigDecimal("25.00"), "usd", TestCreditCards.validMasterCard(),
                TestCardHolders.validCardHolder(), true);
		HpsRefund refund = service.refund(new BigDecimal("25.00"), "usd", charge.getTransactionID());
		assertEquals("00", refund.getResponseCode());
	}
	
	@Test
	public void Mastercard_Capture_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization auth = service.authorize(new BigDecimal("25.00"), "usd", TestCreditCards.validMasterCard(),
                TestCardHolders.validCardHolder(), true);
		assertEquals("00", auth.getResponseCode());
		
		HpsTransaction capture = service.captureTxn(auth.getTransactionID());
		assertEquals("00", capture.getResponseCode());
	}

    @Test
    public void Mastercard_Void_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAuthorization charge = service.charge(new BigDecimal("50"), "usd", TestCreditCards.validMasterCard(), TestCardHolders.validCardHolder(), true);
        assertEquals("00", charge.getResponseCode());

        HpsTransaction voidResult = service.voidTxn(charge.getTransactionID());
        assertEquals("00", voidResult.getResponseCode());
    }

	@Test
	public void Mastercard_UpdateTokenExpiration_ShouldReturnOk() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validCertMultiUseConfig());
		HpsManageToken manage = service.updateTokenExpiration(TestCreditCards.validMasterCardMUT(), 1, 2019);
		assertEquals("00", manage.getResponseCode());
	}
	
	private HpsCharge chargeValidMasterCard(BigDecimal amt) throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsCharge charge = service.charge(amt, "usd", TestCreditCards.validMasterCard(),
                TestCardHolders.validCardHolder(), true);
		assertNotNull(charge);
		return charge;
	}
}
