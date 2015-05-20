package com.hps.integrator.tests.fluent;

import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CreditTests {

    @Test
    public void Auth_ShouldReturnOk() throws HpsException {
        BigDecimal amount = new BigDecimal("10");
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAuthorization charge = service.authorize(amount).withCard(TestCreditCards.validAmex()).withClientTransactionId(123).execute();

        assertEquals("00", charge.getResponseCode());
    }
}
