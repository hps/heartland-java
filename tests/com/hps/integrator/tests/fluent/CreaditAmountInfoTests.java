package com.hps.integrator.tests.fluent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.math.BigDecimal;
import org.junit.Test;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.credit.HpsReportTransactionDetails;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.services.fluent.HpsFluentCreditService;


public class CreaditAmountInfoTests {

	private HpsFluentCreditService creditService;
	
	public CreaditAmountInfoTests() throws HpsException {
		HpsServicesConfig config = new HpsServicesConfig();

		config.setSecretAPIKey("skapi_cert_MTyMAQBiHVEAewvIzXVFcmUd2UcyBge_eCpaASUp0A");
		config.setDeveloperId("012345");
		config.setVersionNumber("0001");
		creditService = new HpsFluentCreditService(config, true);
	}
	/* Positive Test cases */
	@Test
	public void testChargeConvenienceAmount() throws HpsException {
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal convenienceAmtInfo = new BigDecimal("500.50");
        HpsCharge charge = creditService.charge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("500.50"))
                .execute();

        assertNotEquals(null, charge);
        assertEquals("00", charge.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(charge.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());
	}
	@Test
	public void testChargeShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal shippingAmtInfo = new BigDecimal("500.50");
        HpsCharge charge = creditService.charge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withShippingAmount(new BigDecimal("500.50"))
                .execute();

        assertNotEquals(null, charge);
        assertEquals("00", charge.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(charge.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(shippingAmtInfo,reportTransaction.getShippingAmount());
	}
	@Test
	public void testChargeConvenienceAndShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("500.50");
        BigDecimal shippingAmtInfo = new BigDecimal("600.50");
        
        HpsCharge charge = creditService.charge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("500.50"))
                .withShippingAmount(new BigDecimal("600.50"))
                .execute();

        assertNotEquals(null, charge);
        assertEquals("00", charge.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(charge.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());
        assertEquals(shippingAmtInfo,reportTransaction.getShippingAmount());
        
	}
	@Test
	public void testAuthorizeConvenienceAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("500.50");
        
        HpsAuthorization auth = creditService.authorize(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("500.50"))
                .execute();

        assertNotEquals(null, auth);
        assertEquals("00", auth.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(auth.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());

	}
	@Test
	public void testAuthorizeShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal shippingAmtInfo = new BigDecimal("65.20");
        
        HpsAuthorization auth = creditService.authorize(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withShippingAmount(new BigDecimal("65.20"))
                .execute();

        assertNotEquals(null, auth);
        assertEquals("00", auth.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(auth.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(shippingAmtInfo,reportTransaction.getShippingAmount());

	}
	@Test
	public void testAuthorizeConvenienceAndShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("500.50");
        BigDecimal shippingAmtInfo = new BigDecimal("600.50");
        
        HpsAuthorization auth = creditService.authorize(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("500.50"))
                .withShippingAmount(new BigDecimal("600.50"))
                .execute();

        assertNotEquals(null, auth);
        assertEquals("00", auth.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(auth.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());
        assertEquals(shippingAmtInfo,reportTransaction.getShippingAmount());
	}
	@Test
	public void testOfflineChargeConvenienceAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("55.20");
        
		HpsTransaction charge=  creditService.offlineCharge(BigDecimal.valueOf(17.10))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
        		.withAmount(new BigDecimal("100"))
        		.withConvenienceAmount(new BigDecimal("55.20"))
        		.withDirectMarketData(directMarketData)
                .withAllowDuplicates(true)
                .execute();

        assertNotEquals(null, charge);
        assertEquals("00", charge.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(charge.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());
	}
	@Test
	public void testOfflineChargeShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal shippingAmtInfo = new BigDecimal("65.20");
        
        HpsTransaction charge = creditService.offlineCharge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withShippingAmount(new BigDecimal("65.20"))
                .execute();

        assertNotEquals(null, charge);
        assertEquals("00", charge.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(charge.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(shippingAmtInfo,reportTransaction.getShippingAmount());
	}
	@Test
	public void testOfflineChargeConvenienceAmountAndShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("55.20");
		BigDecimal shippingAmtInfo = new BigDecimal("55.20");
        
		HpsTransaction charge=  creditService.offlineCharge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("55.20"))
                .withShippingAmount(new BigDecimal("55.20"))
                .execute();

        assertNotEquals(null, charge);
        assertEquals("00", charge.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(charge.getTransactionID()).execute();       
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());
        assertEquals(shippingAmtInfo,reportTransaction.getShippingAmount());
	}
	@Test
	public void testOfflineAuthConvenienceAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("55.20");
        
		HpsTransaction auth=  creditService.offlineAuth(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("55.20"))
                .execute();

        assertNotEquals(null, auth);
        assertEquals("00", auth.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(auth.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());        
	}
	@Test
	public void testOfflineAuthShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("55.20");
        
		HpsTransaction auth=  creditService.offlineAuth(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("55.20"))
                .execute();

        assertNotEquals(null, auth);
        assertEquals("00", auth.getResponseCode());
        
        HpsReportTransactionDetails reportTransaction = creditService.get(auth.getTransactionID()).execute();
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());
	}
	@Test
	public void testOfflineAuthConvenienceAmountAndShippingAmount() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("55.20");
		BigDecimal shippingAmtInfo = new BigDecimal("56.20");
        
		HpsTransaction auth=  creditService.offlineAuth(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(new BigDecimal("55.20"))
                .withShippingAmount(new BigDecimal("56.20"))
                .execute();

        assertNotEquals(null, auth);
        assertEquals("00", auth.getResponseCode());
        HpsReportTransactionDetails reportTransaction = creditService.get(auth.getTransactionID()).execute();
        
        assertNotEquals(null, reportTransaction);
        assertEquals(convenienceAmtInfo,reportTransaction.getConvenienceAmount());
        assertEquals(shippingAmtInfo,reportTransaction.getShippingAmount());      
	} 
	/*  Negative Test cases */
    @Test(expected = HpsInvalidRequestException.class)
	public void testChargeConvenienceAmountInvalid() throws HpsException {
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal convenienceAmtInfo = new BigDecimal("-100");
        creditService.charge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testChargeShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal shippingAmtInfo = new BigDecimal("-100");
        creditService.charge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withShippingAmount(shippingAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testChargeConvenienceAndShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
        BigDecimal shippingAmtInfo = new BigDecimal("-200");
        
        creditService.charge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("20"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .withShippingAmount(shippingAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testAuthorizeConvenienceAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
        
        creditService.authorize(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testAuthorizeShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal shippingAmtInfo = new BigDecimal("-50");
        
        creditService.authorize(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withShippingAmount(shippingAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testAuthorizeConvenienceAndShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
        BigDecimal shippingAmtInfo = new BigDecimal("-200");
        
        creditService.authorize(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .withShippingAmount(shippingAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testOfflineChargeConvenienceAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
        
		creditService.offlineCharge(BigDecimal.valueOf(17.10))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
        		.withAmount(new BigDecimal("100"))
        		.withDirectMarketData(directMarketData)
                .withAllowDuplicates(true)
                .withConvenienceAmount(convenienceAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testOfflineChargeShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        BigDecimal shippingAmtInfo = new BigDecimal("-100");
        
        creditService.offlineCharge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withShippingAmount(shippingAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testOfflineChargeConvenienceAmountAndShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
		BigDecimal shippingAmtInfo = new BigDecimal("-200");
        
		creditService.offlineCharge(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .withShippingAmount(shippingAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testOfflineAuthConvenienceAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
        
		creditService.offlineAuth(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testOfflineAuthShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
        
		creditService.offlineAuth(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .execute();
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testOfflineAuthConvenienceAmountAndShippingAmountInvalid() throws HpsException {
		
		HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");
        
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

		BigDecimal convenienceAmtInfo = new BigDecimal("-100");
		BigDecimal shippingAmtInfo = new BigDecimal("-200");
        
		creditService.offlineAuth(BigDecimal.valueOf(17.06))
                .withAmount(new BigDecimal("50.40"))
        		.withCard(card)
        		.withOfflineAuthCode("654321")
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withDirectMarketData(directMarketData)
                .withConvenienceAmount(convenienceAmtInfo)
                .withShippingAmount(shippingAmtInfo)
                .execute();
	} /* Ending Negative Test cases */
}
