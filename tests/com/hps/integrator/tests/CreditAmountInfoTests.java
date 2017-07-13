package com.hps.integrator.tests;

import com.hps.integrator.entities.credit.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class CreditAmountInfoTests {

	/* Positive Test cases */
	@Test
	public void testVisaConvenienceAmountForCharge() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal convenienceAmtInfo = new BigDecimal("140.50");

		HpsCharge charge = service.charge(new BigDecimal("50"), "usd", TestCreditCards.validAmex(),
				TestCardHolders.validCardHolder(), true, convenienceAmtInfo);

		assertNotEquals(null, charge);
		assertEquals("00", charge.getResponseCode());

		HpsReportTransactionDetails chargeReport = service.get(charge.getTransactionID());
		assertNotEquals(null, chargeReport);
		assertEquals(convenienceAmtInfo, chargeReport.getConvenienceAmount());
	}
	@Test
	public void testVisaShippingAmountForCharge() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal shippingAmtInfo = new BigDecimal("55.50");

		HpsCharge charge = service.charge(new BigDecimal("50"), "usd", TestCreditCards.validAmex(),
				TestCardHolders.validCardHolder(), true, null, shippingAmtInfo);

		assertNotEquals(null, charge);
		assertEquals("00", charge.getResponseCode());

		HpsReportTransactionDetails chargeReport = service.get(charge.getTransactionID());
		assertNotEquals(null, chargeReport);
		assertEquals(shippingAmtInfo, chargeReport.getShippingAmount());
	}
	@Test
	public void testVisaConvenienceAmountAndShippingAmountForCharge() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal convenienceAmtInfo = new BigDecimal("55.20");
		BigDecimal shippingAmtInfo = new BigDecimal("65.20");

		HpsCharge charge = service.charge(new BigDecimal("50"), "usd", TestCreditCards.validAmex(),
				TestCardHolders.validCardHolder(), true, convenienceAmtInfo, shippingAmtInfo);

		assertNotEquals(null, charge);
		assertEquals("00", charge.getResponseCode());

		HpsReportTransactionDetails chargeReport = service.get(charge.getTransactionID());
		assertNotEquals(null, chargeReport);
		assertEquals(convenienceAmtInfo, chargeReport.getConvenienceAmount());
		assertEquals(shippingAmtInfo, chargeReport.getShippingAmount());
	}
	@Test
	public void testVisaConvenienceAmountForAuthorize() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal shippingAmtInfo = new BigDecimal("75.50");

		HpsAuthorization auth = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(),
				TestCardHolders.validCardHolder(), true, null, shippingAmtInfo);

		assertNotEquals(null, auth);
		assertEquals("00", auth.getResponseCode());

		HpsReportTransactionDetails chargeReport = service.get(auth.getTransactionID());
		assertNotEquals(null, chargeReport);
		assertEquals(shippingAmtInfo, chargeReport.getShippingAmount());
	}
	@Test
	public void testVisaShippingAmountForAuthorize() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal shippingAmtInfo = new BigDecimal("55.50");

		HpsAuthorization auth = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(),
				TestCardHolders.validCardHolder(), true, null, shippingAmtInfo);

		assertNotEquals(null, auth);
		assertEquals("00", auth.getResponseCode());

		HpsReportTransactionDetails chargeReport = service.get(auth.getTransactionID());
		assertNotEquals(null, chargeReport);
		assertEquals(shippingAmtInfo, chargeReport.getShippingAmount());
	}
	@Test
	public void testVisaConvenienceAmountAndShippingAmountForAuthorize() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal convenienceAmtInfo = new BigDecimal("55.20");
		BigDecimal shippingAmtInfo = new BigDecimal("65.20");

		HpsAuthorization auth = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(),
				TestCardHolders.validCardHolder(), true, convenienceAmtInfo, shippingAmtInfo);

		assertNotEquals(null, auth);
		assertEquals("00", auth.getResponseCode());
		HpsReportTransactionDetails chargeReport = service.get(auth.getTransactionID());

		assertNotEquals(null, chargeReport);
		assertEquals(convenienceAmtInfo, chargeReport.getConvenienceAmount());
		assertEquals(shippingAmtInfo, chargeReport.getShippingAmount());
	}
	/* Negative Test cases */
	@Test(expected = HpsInvalidRequestException.class)
	public void testVisaInvalidConvenienceAmountForCharge() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal convenienceAmtInfo = new BigDecimal("-60");

		service.charge(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(),
				true, convenienceAmtInfo);

	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testVisaInvalidShippingAmountForCharge() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal shippingAmtInfo = new BigDecimal("-55");

		service.charge(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(),
				true, null, shippingAmtInfo);
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testInvalidVisaConvenienceAmountAndShippingAmountForCharge() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal convenienceAmtInfo = new BigDecimal("-55.20");
		BigDecimal shippingAmtInfo = new BigDecimal("-65.20");

		service.charge(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(),
				true, convenienceAmtInfo, shippingAmtInfo);
	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testVisaInvalidConvenienceAmountForAuthorize() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal shippingAmtInfo = new BigDecimal("-75");

		service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(),
				true, null, shippingAmtInfo);

	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testInvalidVisaShippingAmountForAuthorize() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal shippingAmtInfo = new BigDecimal("-55");

		service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(),
				true, null, shippingAmtInfo);

	}
	@Test(expected = HpsInvalidRequestException.class)
	public void testInvalidVisaConvenienceAmountAndShippingAmountForAuthorize() throws HpsException {

		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		BigDecimal convenienceAmtInfo = new BigDecimal("-55");
		BigDecimal shippingAmtInfo = new BigDecimal("-66");

		service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(),
				true, convenienceAmtInfo, shippingAmtInfo);
	}
}
