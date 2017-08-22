package com.hps.integrator.tests.applepay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestData;
import com.hps.integrator.tests.testdata.TestServicesConfig;

public class ApplePayTests {

	private HpsFluentCreditService fluentCreditService = null;

	public ApplePayTests() throws HpsException {
		System.setProperty("https.protocols", "TLSv1.1,TLSv1.2");
        this.fluentCreditService = new HpsFluentCreditService(TestServicesConfig.validCertMultiUseConfig(), true);
	}

	/* Tests cases */
	@Test
	public void test_authorize_ApplePay_Amex_For_Fluent() throws HpsException {
		
		HpsAuthorization authorizeResponse = fluentCreditService.authorize(TestData.AmexPaymentData().getDollarAmount())
				.withPaymentData(TestData.AmexPaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, authorizeResponse);
		assertEquals("00", authorizeResponse.getResponseCode());

	}

	@Test
	public void test_authorize_ApplePay_McPay_For_Fluent() throws HpsException {
		
		HpsAuthorization authorizeResponse = fluentCreditService.authorize(TestData.McPaymentData().getDollarAmount())
				.withPaymentData(TestData.McPaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, authorizeResponse);
		assertEquals("00", authorizeResponse.getResponseCode());

	}

	@Test
	public void test_authorize_ApplePay_VisaPay_For_Fluent() throws HpsException {
		
		HpsAuthorization authorizeResponse = fluentCreditService.authorize(TestData.VisaPaymentData().getDollarAmount())
				.withPaymentData(TestData.VisaPaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, authorizeResponse);
		assertEquals("00", authorizeResponse.getResponseCode());

	}
	
	@Test
	public void test_authorize_ApplePay_Visa3D_For_Fluent() throws HpsException {
		
		HpsAuthorization authorizeResponse = fluentCreditService.authorize(TestData.Visa3DSecurePaymentData().getDollarAmount())
				.withPaymentData(TestData.Visa3DSecurePaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, authorizeResponse);
		assertEquals("00", authorizeResponse.getResponseCode());

	}

	@Test
	public void test_charge_ApplePay_McPay_For_Fluent() throws HpsException {
		
		HpsCharge chargeResponse = fluentCreditService.charge(TestData.McPaymentData().getDollarAmount())
				.withPaymentData(TestData.McPaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}

	@Test
	public void test_charge_ApplePay_Amex_For_Fluent() throws HpsException {
		
		HpsCharge chargeResponse = fluentCreditService.charge(TestData.AmexPaymentData().getDollarAmount())
				.withPaymentData(TestData.AmexPaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}

	@Test
	public void test_charge_ApplePay_Visa3D_For_Fluent() throws HpsException {
		
		HpsCharge chargeResponse = fluentCreditService.charge(TestData.Visa3DSecurePaymentData().getDollarAmount())
				.withPaymentData(TestData.VisaPaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}

	@Test
	public void test_charge_ApplePay_VisaPay_For_Fluent() throws HpsException {
		
		HpsCharge chargeResponse = fluentCreditService.charge(TestData.VisaPaymentData().getDollarAmount())
				.withPaymentData(TestData.VisaPaymentData())
				.withCardHolder(TestCardHolders.validCardHolder())
				.withAllowDuplicates(true)
				.execute();
		
		assertNotEquals(null, chargeResponse);
		assertEquals("00", chargeResponse.getResponseCode());
	}
}
