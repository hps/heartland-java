package com.hps.integrator.tests.fluent;

import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.HpsArgumentException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCheckService;
import com.hps.integrator.tests.testdata.TestCheck;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Random;

public class CheckTests {
    HpsFluentCheckService service;

    public CheckTests() throws HpsException {
        this.service = new HpsFluentCheckService(TestServicesConfig.validServicesConfig());
    }

    @Test
    public void CheckSale() throws HpsException {
        HpsCheckResponse sale = this.service.sale(new BigDecimal("22"))
                .withCheck(TestCheck.certCheck())
                .execute();
        assertNotNull(sale);
        assertEquals("0", sale.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void CheckSaleNoAmount() throws HpsException {
        this.service.sale().withCheck(TestCheck.certCheck()).execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void CheckSaleNoCheck() throws HpsException {
        this.service.sale(new BigDecimal("23")).execute();
    }

    @Test
    public void CheckVoidWithTxnId() throws HpsException {
        HpsCheckResponse sale = this.service.sale(new BigDecimal("24"))
                .withCheck(TestCheck.certCheck())
                .execute();
        assertNotNull(sale);
        assertEquals("0", sale.getResponseCode());

        HpsCheckResponse voidResponse = this.service.checkVoid().withTransactionId(sale.getTransactionID()).execute();
        assertNotNull(voidResponse);
        assertEquals("0", voidResponse.getResponseCode());
    }

    @Test
    public void CheckVoidWithClientTxnId() throws HpsException {
        Integer clientTxnId = new Random().nextInt(999999999 - 100000000) + 100000000;

        HpsCheckResponse sale = this.service.sale(new BigDecimal("24"))
                .withCheck(TestCheck.certCheck())
                .withClientTransactionId(clientTxnId.toString())
                .execute();
        assertNotNull(sale);
        assertEquals("0", sale.getResponseCode());

        HpsCheckResponse voidResponse = this.service.checkVoid().withClientTransactionId(clientTxnId.toString()).execute();
        assertNotNull(voidResponse);
        assertEquals("0", voidResponse.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void CheckVoidWithMultipleTransactionIds() throws HpsException {
        this.service.checkVoid().withTransactionId(123456789).withClientTransactionId("987654321").execute();
    }
}
