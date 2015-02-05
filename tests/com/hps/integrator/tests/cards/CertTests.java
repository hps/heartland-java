package com.hps.integrator.tests.cards;

import com.hps.integrator.entities.credit.HpsAccountVerify;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.entities.credit.HpsRefund;
import com.hps.integrator.entities.credit.HpsReversal;
import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.services.HpsBatchService;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CertTests {

    @Test
    public void A_Batch_ShouldClose_Ok() throws HpsException {
        try {
            HpsBatchService service = new HpsBatchService(TestServicesConfig.validServicesConfig());
            HpsBatch batch = service.closeBatch();
            assertNotNull(batch);
        } catch (HpsGatewayException e) {
            assertEquals(HpsGatewayExceptionCodes.NoOpenBatch, e.getCode());
        }
    }

    @Test
    public void B_Visa_ShouldCharge_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsCharge response = service.charge(new BigDecimal("17.01"), "usd", TestCreditCards.validVisa(), TestCardHolders.certCardHolderShortZip(), true);
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void C_MasterCard_ShouldCharge_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsCharge response = service.charge(new BigDecimal("17.02"), "usd", TestCreditCards.validMasterCard(), TestCardHolders.certCardHolderShortZipNoStreet(), true);
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void D_Discover_ShouldCharge_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsCharge response = service.charge(new BigDecimal("17.03"), "usd", TestCreditCards.validDiscover(), TestCardHolders.certCardHolderLongZipNoStreet(), true);
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void E_Amex_ShouldCharge_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsCharge response = service.charge(new BigDecimal("17.04"), "usd", TestCreditCards.validAmex(), TestCardHolders.certCardHolderShortZip(), true);
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void F_Jcb_ShouldCharge_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsCharge response = service.charge(new BigDecimal("17.05"), "usd", TestCreditCards.validJcb(), TestCardHolders.certCardHolderLongZip(), true);
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void G_Visa_ShouldVerify_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validVisa());
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void H_MasterCard_ShouldVerify_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validMasterCard());
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void I_Discover_ShouldVerify_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validDiscover());
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void J_Amex_Avs_ShouldBe_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validAmex(), TestCardHolders.certCardHolderShortZipNoStreet());
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void K_Mastercard_Return_ShouldBe_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsRefund response = service.refund(new BigDecimal("15.15"), "usd", TestCreditCards.validMasterCard(), TestCardHolders.certCardHolderShortZip());
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void L_Visa_ShouldReverse_Ok() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsReversal response = service.reverse(TestCreditCards.validVisa(), new BigDecimal("17.01"), "usd");
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

}
