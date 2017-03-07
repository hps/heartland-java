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

public class DiscoverTests {
	
	@Test
	public void Discover_WhenCardIsOk_ShouldReturnValidResult() throws HpsException
	{
		HpsCharge response = this.chargeValidDiscover(new BigDecimal("50"));
		assertEquals("00", response.getResponseCode());
	}
	
	// avs tests

	@Test
	public void Discover_AvsResultCode_ShouldEqualA() throws HpsException
	{
		HpsCharge response = this.chargeValidDiscover(new BigDecimal("91.01"));
		assertEquals("A", response.getAvsResultCode());
	}
	
	@Test
	public void Discover_AvsResultCode_ShouldEqualN() throws HpsException
	{
		HpsCharge response = this.chargeValidDiscover(new BigDecimal("91.02"));
		assertEquals("N", response.getAvsResultCode());
	}
	
	@Test
	public void Discover_AvsResultCode_ShouldEqualR() throws HpsException
	{
		HpsCharge response = this.chargeValidDiscover(new BigDecimal("91.03"));
		assertEquals("R", response.getAvsResultCode());
	}
	
	@Test
	public void Discover_AvsResultCode_ShouldEqualU() throws HpsException
	{
		HpsCharge response = this.chargeValidDiscover(new BigDecimal("91.05"));
		assertEquals("U", response.getAvsResultCode());
	}
	
	@Test
	public void Discover_AvsResultCode_ShouldEqualY() throws HpsException
	{
		HpsCharge response = this.chargeValidDiscover(new BigDecimal("91.06"));
		assertEquals("Y", response.getAvsResultCode());
	}
	
	@Test
	public void Discover_AvsResultCode_ShouldEqualZ() throws HpsException
	{
		HpsCharge response = this.chargeValidDiscover(new BigDecimal("91.07"));
		assertEquals("Z", response.getAvsResultCode());
	}	
	
	// discover to visa 2nd
	
	@Test(expected = HpsIssuerException.class)
	public void Discover_ResponseCode_ShouldIndicateReferCardIssuer() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.34"));
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
	public void Discover_ResponseCode_ShouldIndicateInvalidMerchant() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.22"));
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
	public void Discover_ResponseCode_ShouldIndicatePickUpCard() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.04"));
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
	public void Discover_ResponseCode_ShouldIndicateDoNotHonor() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.25"));
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
	public void Discover_ResponseCode_ShouldIndicateInvalidTransaction() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.26"));
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
	public void Discover_ResponseCode_ShouldIndicateInvalidAmount() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.27"));
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
	public void Discover_ResponseCode_ShouldIndicateInvalidCard() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.28"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.IncorrectNumber, e.getCode());
			assertEquals("14", e.getDetails().getIssuerResponseCode());
			assertEquals("CARD NO. ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
//	@Test(expected = HpsCreditException.class)
//	public void Discover_ResponseCode_ShouldIndicateInvalidIssuer() throws HpsException
//	{
//		try 
//		{
//			this.chargeValidDiscover(new BigDecimal("10.18"));
//		} 
//		catch (HpsCreditException e) 
//		{
//			assertEquals("processing_error, e.getCode());
//			assertEquals("15", e.getDetails().getIssuerResponseCode());
//			assertEquals("NO SUCH ISSUER", e.getDetails().getIssuerResponseText());
//			throw e;
//		}
//	}
	
	@Test(expected = HpsIssuerException.class)
	public void Discover_ResponseCode_ShouldIndicateSystemErrorReenter() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.29"));
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
	public void Discover_ResponseCode_ShouldIndicateMessageFormatError() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.06"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("EC", e.getDetails().getIssuerResponseCode());
			assertEquals("CID FORMAT ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Discover_ResponseCode_ShouldIndicateLostCard() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.31"));
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
	public void Discover_ResponseCode_ShouldIndicateInsufficientFunds() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.08"));
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
	public void Discover_ResponseCode_ShouldIndicateNoSavingAccount() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.17"));
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
	public void Discover_ResponseCode_ShouldIndicateExpiredCard() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.32"));
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
	public void Discover_ResponseCode_ShouldIndicateNoCardRecord() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.24"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("56", e.getDetails().getIssuerResponseCode());
			assertEquals("INVALID TRANS", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Discover_ResponseCode_ShouldIndicateTxnNotPermittedOnCard() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.20"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("57", e.getDetails().getIssuerResponseCode());
			assertEquals("SERV NOT ALLOWED", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Discover_ResponseCode_ShouldIndicateInvalidAcquirer() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.30"));
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
	public void Discover_ResponseCode_ShouldIndicateExceedsLimit() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.09"));
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
	public void Discover_ResponseCode_ShouldIndicateRestrictedCard() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.10"));
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
	public void Discover_ResponseCode_ShouldIndicateSecurityViolation() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.19"));
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
	public void Discover_ResponseCode_ShouldIndicateExceedsFreqLimit() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.11"));
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
	public void Discover_ResponseCode_ShouldIndicateNoToAccount() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.13"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
			assertEquals("78", e.getDetails().getIssuerResponseCode());
			assertEquals("NO ACCOUNT", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	@Test(expected = HpsIssuerException.class)
	public void Discover_ResponseCode_ShouldIndicateInvalidAccount() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.14"));
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
	public void Discover_ResponseCode_ShouldIndicateSystemError() throws HpsException
	{
		try 
		{
			this.chargeValidDiscover(new BigDecimal("10.21"));
		} 
		catch (HpsIssuerException e)
		{
			assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
			assertEquals("96", e.getDetails().getIssuerResponseCode());
			assertEquals("SYSTEM ERROR", e.getDetails().getIssuerResponseText());
			throw e;
		}
	}
	
	// verify, authorize, capture & void
	
	@Test
	public void Discover_Verify_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAccountVerify response = service.verify(TestCreditCards.validDiscover(), TestCardHolders.validCardHolder());
		assertEquals("85", response.getResponseCode());
	}

    @Test
    public void Discover_VerifyWithToken_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validDiscover(), TestCardHolders.validCardHolder(), true, false, false);
        response = service.verify(response.getTokenData().getTokenValue(), TestCardHolders.validCardHolder());

        assertEquals("85", response.getResponseCode());
    }
	
	@Test
	public void Discover_Authorize_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validDiscover(),
                TestCardHolders.validCardHolder(), true);
		assertEquals("00", response.getResponseCode());
	}
	
	@Test
	public void Discover_AuthorizeAndRequestToken_ShouldGetTokenAndReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validDiscover(),
                TestCardHolders.validCardHolder(), true, true, null, null, false, false, false);
		assertEquals(0, response.getTokenData().getTokenRspCode());
		assertEquals("00", response.getResponseCode());
	}
	
	@Test
	public void Discover_Capture_ShouldReturnOk() throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsAuthorization authResponse = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validDiscover(),
                TestCardHolders.validCardHolder(), true);
		assertEquals("00", authResponse.getResponseCode());
		
		HpsTransaction capture = service.captureTxn(authResponse.getTransactionID());
		assertEquals("00", capture.getResponseCode());
	}

    @Test
    public void Discover_Void_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAuthorization charge = service.charge(new BigDecimal("50"), "usd", TestCreditCards.validDiscover(), TestCardHolders.validCardHolder(), true);
        assertEquals("00", charge.getResponseCode());

        HpsTransaction voidResult = service.voidTxn(charge.getTransactionID());
        assertEquals("00", voidResult.getResponseCode());
    }

	@Test
	public void Discover_UpdateTokenExpiration_ShouldReturnOk() throws HpsException {
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validCertMultiUseConfig());
		HpsManageToken manage = service.updateTokenExpiration(TestCreditCards.validDiscoverMUT(), 1, 2019);
		assertEquals("00", manage.getResponseCode());
	}

	public HpsCharge chargeValidDiscover(BigDecimal amt) throws HpsException
	{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsCharge charge = service.charge(amt, "usd", TestCreditCards.validDiscover(),
                TestCardHolders.validCardHolder(), true, false, null, null, null, false, false, false);
		assertNotNull(charge);
		return charge;
	}
}
