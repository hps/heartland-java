package com.hps.integrator.tests;

import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;

public class SecretKeyTests {

	@Test
	public void can_charge_with_secret_key() throws HpsException{
		HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
		HpsCharge charge = service.charge(new BigDecimal("1.00"), "usd", TestCreditCards.validVisa(),
                TestCardHolders.certCardHolderShortZipNoStreet(), true);
		assertNotNull(charge);
	}
}
