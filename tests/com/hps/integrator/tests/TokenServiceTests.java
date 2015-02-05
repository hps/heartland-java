package com.hps.integrator.tests;

import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.serialization.HpsError;
import com.hps.integrator.entities.serialization.HpsToken;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.services.HpsTokenService;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TokenServiceTests {

	private HpsTokenService mService = new HpsTokenService("pkapi_cert_P6dRqs1LzfWJ6HgGVZ");

	@Test(expected = IllegalArgumentException.class)
	public void PublicKey_Null_Key_Throws_IllegalArgumentException()
	{
		new HpsTokenService(null);			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void PublicKey_Empty_Key_Throws_IllegalArgumentException()
	{
		new HpsTokenService("");		
	}
	
	@Test(expected = IOException.class)
	public void PublicKey_Bad_Key_Throws_IOException() throws IOException
	{
		new HpsTokenService("pkapi_foo_foo").getToken(TestCreditCards.validVisa());			
	}

	@Test
	public void Validation_Invalid_Number_Returns_Error() throws IOException
	{
		HpsCreditCard card = new HpsCreditCard();
		card.setNumber("1");
		
		HpsToken response = mService.getToken(card);
		HpsError error = response.getError();
		
		assertNotNull(error);
		assertEquals("Unexpected Error Code", "2", error.getCode());
		assertEquals("Unexpected Error Param", "card.number", error.getParam());
		assertEquals("Unexpected Error Message", "Card number is invalid.", error.getMessage());
	}
	
	@Test
	public void Validation_Invalid_Number_Long_Returns_Error()  throws IOException
	{
		HpsCreditCard card = new HpsCreditCard();
		card.setNumber("11111111111111111111111111111111111");
		
		HpsToken response = mService.getToken(card);
		HpsError error = response.getError();
		
		assertNotNull(error);
		assertEquals("Unexpected Error Code", "2", error.getCode());
		assertEquals("Unexpected Error Param", "card.number", error.getParam());
		assertEquals("Unexpected Error Message", "Card number is invalid.", error.getMessage());
	}
	
	@Test
	public void Validation_Invalid_ExpMonth_Low_Returns_Error() throws IOException
	{
		HpsCreditCard card = TestCreditCards.validVisa();
		card.setExpMonth(0);
		
		HpsToken response = mService.getToken(card);
		HpsError error = response.getError();
		
		assertNotNull(error);
		assertEquals("Unexpected Error Code", "2", error.getCode());
		assertEquals("Unexpected Error Param", "card.exp_month", error.getParam());
		assertEquals("Unexpected Error Message", "Card expiration month is invalid.", error.getMessage());
	}
	
	@Test
	public void Validation_Invalid_ExpMonth_High_Returns_Error() throws IOException
	{
		HpsCreditCard card = TestCreditCards.validVisa();
		card.setExpMonth(13);
		
		HpsToken response = mService.getToken(card);
		HpsError error = response.getError();
		
		assertNotNull(error);
		assertEquals("Unexpected Error Code", "2", error.getCode());
		assertEquals("Unexpected Error Param", "card.exp_month", error.getParam());
		assertEquals("Unexpected Error Message", "Card expiration month is invalid.", error.getMessage());
	}	
	
	@Test
	public void Validation_Invalid_ExpYear_Returns_Error()  throws IOException
	{
		HpsCreditCard card = TestCreditCards.validVisa();
		card.setExpYear(12);
		
		HpsToken response = mService.getToken(card);
		HpsError error = response.getError();
		
		assertNotNull(error);
		assertEquals("Unexpected Error Code", "2", error.getCode());
		assertEquals("Unexpected Error Param", "card.exp_year", error.getParam());
		assertEquals("Unexpected Error Message", "Card expiration year is invalid.", error.getMessage());
	}
	
	@Test
	public void Validation_Invalid_ExpYear_Under_2000_Returns_Error() throws IOException
	{
		HpsCreditCard card = TestCreditCards.validVisa();
		card.setExpYear(1999);
		
		HpsToken response = mService.getToken(card);
		HpsError error = response.getError();
		
		assertNotNull(error);
		assertEquals("Unexpected Error Code", "2", error.getCode());
		assertEquals("Unexpected Error Param", "card.exp_year", error.getParam());
		assertEquals("Unexpected Error Message", "Card expiration year is invalid.", error.getMessage());
	}

	@Test
	public void TokenResult_Valid_Error_Should_Be_Null() throws IOException
	{
		HpsToken response = mService.getToken(TestCreditCards.validVisa());			
		assertNull(response.getError());		
	}

	@Test
	public void TokenResult_Valid_Token_Data_Should_Be_Present() throws IOException
	{		
		HpsToken response = mService.getToken(TestCreditCards.validVisa());			
		assertNotNull(response.getTokenValue());
		assertNotNull(response.getTokenType());
		assertNotNull(response.getTokenExpire());
	}
	
	@Test
	public void Integration_Can_Obtain_And_Charge_Token() throws IOException, HpsException
	{
		HpsToken token = mService.getToken(TestCreditCards.validVisa());	
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsCharge charge = service.charge(new BigDecimal("1.00"), "usd", token.getTokenValue(),
                TestCardHolders.validCardHolder(), true);
		assertNotNull(charge.getAuthorizationCode());
	}
}
