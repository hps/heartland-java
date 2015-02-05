package com.hps.integrator.tests.checks;

import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsCheckService;
import com.hps.integrator.tests.testdata.TestCheck;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CheckTests {

    @Test
    public void check_ShouldSale() throws HpsException, HpsCheckException {
        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.approve(), new BigDecimal(5.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void check_ShouldDecline() throws HpsException, HpsCheckException {
        try {
            HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
            service.sale(TestCheck.decline(), new BigDecimal(5.00));
            Assert.fail("The transaction should have thrown an HpsCheckException.");
        } catch (HpsCheckException ex) {
            assertEquals(ex.getCode(), 1);
        }
    }

    @Test
    public void check_ShouldThrowHpsCheckException() throws HpsException, HpsCheckException {
        try {
            HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
            service.sale(TestCheck.invalidCheckHolder(), new BigDecimal(5.00));
            Assert.fail("The transaction should have thrown an HpsCheckException.");
        } catch (HpsCheckException ex) {
            Assert.assertEquals(ex.getCode(), 1);
        }
    }

    @Test
    public void check_SaleAndVoidWithClientTxnId() throws HpsException, HpsCheckException {
        long clientTransactionId = 10244201;
        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.approve(), new BigDecimal("5.00"), clientTransactionId);
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
        HpsCheckResponse voidResponse = service.voidSale(0, clientTransactionId);
        assertNotNull(voidResponse);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void check_ShouldVoid() throws HpsException, HpsCheckException {
        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse saleResponse = service.sale(TestCheck.approve(), new BigDecimal(5.00));
        HpsCheckResponse voidResponse = service.voidSale(saleResponse.getTransactionID());
        assertNotNull(voidResponse);
        assertEquals("0", voidResponse.getResponseCode());
    }
}
