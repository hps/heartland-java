package com.hps.integrator.tests.certification;

import PosGateway.Exchange.Hps.Enums;
import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.entities.gift.*;
import com.hps.integrator.fluent.CreditChargeBuilder;
import com.hps.integrator.fluent.CreditChargePaymentTypeBuilder;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsGatewayException;
import com.hps.integrator.infrastructure.HpsGatewayExceptionCodes;
import com.hps.integrator.services.HpsBatchService;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.services.HpsGiftCardService;
import com.hps.integrator.services.HpsServicesConfig;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ECommerce {
    private HpsBatchService _batchService;
    private HpsCreditService _creditService;
    private HpsGiftCardService _giftService;
    private boolean _useTokens = true;

    public ECommerce() throws HpsException {
        HpsServicesConfig config = new HpsServicesConfig();

        config.setSecretAPIKey("skapi_cert_MTyMAQBiHVEAewvIzXVFcmUd2UcyBge_eCpaASUp0A");
        config.setDeveloperId("012345");
        config.setVersionNumber("0001");

        _batchService = new HpsBatchService(config);
        _creditService = new HpsCreditService(config);
        _giftService = new HpsGiftCardService(config);
    }

    // CARD VERIFY

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    public void test_000_close_batch() throws HpsException {
        System.out.println("\033[32mclosing open batch...");

        try {
            HpsBatch batch = _batchService.close().execute();
            assertNotEquals(batch, null);

            System.out.printf("\033[32mbatch id: %d\n", batch.getId());
            System.out.printf("\033[32msequence number: %d\n", batch.getSequenceNumber());
        } catch (HpsGatewayException ex) {
            if (ex.getCode() != HpsGatewayExceptionCodes.NoOpenBatch) {
                throw ex;
            } else {
                System.out.println("\033[33mno open batch found");
            }
        }
    }

    // Account Verification

    @Test
    public void test_001_verify_visa() throws HpsException {
        HpsAccountVerify response = _creditService.verify()
                .withCard(TestData.visaCard(false))
                .withRequestMultiuseToken(_useTokens)
                .execute();

        assertNotEquals(null, response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void test_002_verify_master_card() throws HpsException {
        HpsAccountVerify response = _creditService.verify()
                .withCard(TestData.masterCard(false))
                .withRequestMultiuseToken(_useTokens)
                .execute();

        assertNotEquals(null, response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void test_003_verify_discover() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setZip("75024");

        HpsAccountVerify response = _creditService.verify()
                .withCard(TestData.discoverCard(false))
                .withCardHolder(cardHolder)
                .withRequestMultiuseToken(_useTokens)
                .execute();

        assertNotEquals(null, response);
        assertEquals("85", response.getResponseCode());
    }

    // Address Verification

    @Test
    public void test_004_verify_amex() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setZip("75024");

        HpsAccountVerify response = _creditService.verify()
                .withCard(TestData.amexCard(false))
                .withCardHolder(cardHolder)
                .withRequestMultiuseToken(_useTokens)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // Balance Inquiry (for Prepaid Card)

    @Test
    public void test_005_balance_inquiry_visa() throws HpsException {
        HpsAuthorization response = _creditService.prePaidBalanceInquiry()
                .withCard(TestData.visaCard(false))
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // CREDIT SALE (For Multi-Use Token Only)

    @Test
    public void test_006_charge_visa_token() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization response = _creditService.charge(BigDecimal.valueOf(13.01))
                .withCard(TestData.visaCard(false))
                .withCardHolder(cardHolder)
                .withRequestMultiuseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_007_charge_master_card_token() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization response = _creditService.charge(BigDecimal.valueOf(13.02))
                .withCard(TestData.masterCard(true))
                .withCardHolder(cardHolder)
                .withRequestMultiuseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_008_charge_discover_token() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("750241234");

        HpsAuthorization response = _creditService.charge(BigDecimal.valueOf(13.03))
                .withCard(TestData.discoverCard(true))
                .withCardHolder(cardHolder)
                .withRequestMultiuseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_009_charge_amex_token() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization response = _creditService.charge(BigDecimal.valueOf(13.04))
                .withCard(TestData.amexCard(true))
                .withCardHolder(cardHolder)
                .withRequestMultiuseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // CREDIT SALE

    @Test
    public void test_010_charge_visa() throws HpsException, IOException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        CreditChargePaymentTypeBuilder typeBuilder = _creditService.charge(BigDecimal.valueOf(17.01));
        CreditChargeBuilder builder;

        if(_useTokens) {
            builder = typeBuilder.withToken(TestData.visaMultiUseToken(TestData.Industry.ecommerce));
        } else {
            builder = typeBuilder.withCard(TestData.visaCard(true));
        }

        HpsCharge chargeResponse = builder
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());

        // TEST 35 ONLINE VOID
        HpsTransaction voidResponse = _creditService.voidTransaction(chargeResponse.getTransactionID()).execute();

        assertNotEquals(null, voidResponse);
        assertEquals("00", voidResponse.getResponseCode());
    }

    @Test
    public void test_011_charge_master_card() throws HpsException, IOException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        CreditChargePaymentTypeBuilder typeBuilder = _creditService.charge(BigDecimal.valueOf(17.02));
        CreditChargeBuilder builder;

        if(_useTokens) {
            builder = typeBuilder.withToken(TestData.masterCardMultiUseToken(TestData.Industry.ecommerce));
        } else {
            builder = typeBuilder.withCard(TestData.masterCard(true));
        }

        HpsCharge chargeResponse = builder
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
    }

    @Test
    public void test_012_charge_discover() throws HpsException, IOException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("750241234");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        CreditChargePaymentTypeBuilder typeBuilder = _creditService.charge(BigDecimal.valueOf(17.03));
        CreditChargeBuilder builder;

        if(_useTokens) {
            builder = typeBuilder.withToken(TestData.discoverMultiUseToken(TestData.Industry.ecommerce));
        } else {
            builder = typeBuilder.withCard(TestData.discoverCard(true));
        }

        HpsCharge response = builder
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_013_charge_amex() throws HpsException, IOException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        CreditChargePaymentTypeBuilder typeBuilder = _creditService.charge(BigDecimal.valueOf(17.04));
        CreditChargeBuilder builder;

        if(_useTokens) {
            builder = typeBuilder.withToken(TestData.amexMultiUseToken(TestData.Industry.ecommerce));
        } else {
            builder = typeBuilder.withCard(TestData.amexCard(true));
        }

        HpsCharge response = builder
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_014_charge_jcb() throws HpsException, IOException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("750241234");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsCharge response = _creditService.charge(BigDecimal.valueOf(17.05))
                .withCard(TestData.jcbCard(true))
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // AUTHORIZATION

    @Test
    public void test_015_authorization_visa() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization authResponse = _creditService.authorize(BigDecimal.valueOf(17.06))
                .withCard(TestData.visaCard(true))
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, authResponse);
        assertEquals("00", authResponse.getResponseCode());

        // test 015b Capture/AddToBatch
        HpsTransaction captureResponse = _creditService.capture(authResponse.getTransactionID()).execute();

        assertNotEquals(null, captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_016_authorization_master_card() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("750241234");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization authResponse = _creditService.authorize(BigDecimal.valueOf(17.07))
                .withCard(TestData.masterCard(true))
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, authResponse);
        assertEquals("00", authResponse.getResponseCode());

        // test 016b Capture/AddToBatch
        HpsTransaction captureResponse = _creditService.capture(authResponse.getTransactionID()).execute();

        assertNotEquals(null, captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_017_authorization_discover() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization authResponse = _creditService.authorize(BigDecimal.valueOf(17.08))
                .withCard(TestData.discoverCard(true))
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, authResponse);
        assertEquals("00", authResponse.getResponseCode());

        // test 016b Capture/AddToBatch
        // do not capture
    }

    // PARTIALLY - APPROVED SALE

    @Test
    public void test_018_partial_approval_visa() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization response = _creditService.charge(BigDecimal.valueOf(130))
                .withCard(TestData.visaCard(true))
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .withAllowPartialAuth(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("10", response.getResponseCode());

        assertNotEquals(null, response.getAuthorizedAmount());
        assertEquals(BigDecimal.valueOf(110).setScale(2, BigDecimal.ROUND_CEILING), response.getAuthorizedAmount());
    }

    @Test
    public void test_019_partial_approval_discover() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization response = _creditService.charge(BigDecimal.valueOf(145))
                .withCard(TestData.discoverCard(true))
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .withAllowPartialAuth(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("10", response.getResponseCode());

        assertNotEquals(null, response.getAuthorizedAmount());
        assertEquals(BigDecimal.valueOf(65).setScale(2, BigDecimal.ROUND_CEILING), response.getAuthorizedAmount());
    }

    @Test
    public void test_020_partial_approval_master_card() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(155))
                .withCard(TestData.masterCard(true))
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .withAllowPartialAuth(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals(chargeResponse.getResponseCode(), "10");

        assertNotEquals(null, chargeResponse.getAuthorizedAmount());
        assertEquals(BigDecimal.valueOf(100).setScale(2, BigDecimal.ROUND_CEILING), chargeResponse.getAuthorizedAmount());

        // TEST 36 ONLINE VOID
        HpsTransaction voidResponse = _creditService.voidTransaction(chargeResponse.getTransactionID()).execute();

        assertNotEquals(null, voidResponse);
        assertEquals("00", voidResponse.getResponseCode());
    }

    // LEVEL II CORPORATE PURCHASE CARD

    @Test
    public void test_021_level_ii_response_b() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("750241234");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(112.34))
                .withCard(TestData.visaCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("B", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(Enums.taxTypeType.NOTUSED);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_022_level_ii_response_b() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("750241234");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(112.34))
                .withCard(TestData.visaCard(true))
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("B", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxType(Enums.taxTypeType.SALESTAX);
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_023_level_ii_response_r() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(123.45))
                .withCard(TestData.visaCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals(chargeResponse.getCpcIndicator(), "R");

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxType(Enums.taxTypeType.TAXEXEMPT);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_024_level_ii_response_s() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(134.56))
                .withCard(TestData.visaCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(Enums.taxTypeType.SALESTAX);
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_025_level_ii_response_s() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.06))
                .withCard(TestData.masterCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(Enums.taxTypeType.NOTUSED);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_026_level_ii_response_s() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.07))
                .withCard(TestData.masterCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(Enums.taxTypeType.SALESTAX);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_027_level_ii_response_s() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.08))
                .withCard(TestData.masterCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(Enums.taxTypeType.SALESTAX);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_028_level_ii_response_s() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.09))
                .withCard(TestData.masterCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(Enums.taxTypeType.TAXEXEMPT);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_029_level_ii_no_response() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.10))
                .withCard(TestData.amexCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(Enums.taxTypeType.NOTUSED);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_030_level_ii_no_response() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("750241234");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.11))
                .withCard(TestData.amexCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(Enums.taxTypeType.SALESTAX);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_031_level_ii_no_response() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.12))
                .withCard(TestData.amexCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(Enums.taxTypeType.SALESTAX);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_032_level_ii_no_response() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsAuthorization chargeResponse = _creditService.charge(BigDecimal.valueOf(111.13))
                .withCard(TestData.amexCard(true))
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(Enums.taxTypeType.TAXEXEMPT);

        HpsTransaction cpcResponse = _creditService.cpcEdit(chargeResponse.getTransactionID())
                .withCpcData(cpcData)
                .execute();

        assertNotEquals(null, cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    // PRIOR / VOICE AUTHORIZATION

    @Test
    public void test_033_offline_sale() throws HpsException {
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsTransaction response = _creditService.offlineCharge(BigDecimal.valueOf(17.10))
                .withCard(TestData.visaCard(true))
                .withOfflineAuthCode("654321")
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_033_offline_authorization() throws HpsException {
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsTransaction response = _creditService.offlineAuth(BigDecimal.valueOf(17.10))
                .withCard(TestData.visaCard(true))
                .withOfflineAuthCode("654321")
                .withDirectMarketData(directMarketData)
                .withAllowDuplicates(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // RETURN

    @Test
    public void test_034_offline_credit_return() throws HpsException {
        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsTransaction response = _creditService.refund(BigDecimal.valueOf(15.15))
                .withCard(TestData.masterCard(true))
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // ONLINE VOID / REVERSAL

    // test_035_void_test_10: SEE TEST 10
    // test_036_void_test_20: SEE TEST 20

    // ONE CARD - GSB CARD FUNCTIONS

    // BALANCE INQUIRY

    @Test
    public void test_037_balance_inquiry_gsb() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsTransaction response = _creditService.prePaidBalanceInquiry()
                .withCard(TestData.gsbCardECommerce())
                .withCardHolder(cardHolder)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // ADD VALUE

    @Test
    public void test_038_add_value_gsb() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220330000248^ TEST CARD^49121010000000000694?;6277220330000248=49121010000000000694?");

        HpsTransaction response = _creditService.prePaidAddValue(BigDecimal.valueOf(15.00))
                .withTrackData(trackData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // SALE

    @Test
    public void test_039_charge_gsb() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsCharge chargeResponse = _creditService.charge(BigDecimal.valueOf(2.05))
                .withCard(TestData.gsbCardECommerce())
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());

        // VOID TRANSACTION

        HpsTransaction voidResponse = _creditService.voidTransaction(chargeResponse.getTransactionID()).execute();
        assertNotEquals(null, voidResponse);
        assertEquals("00", voidResponse.getResponseCode());
    }

    @Test
    public void test_040_charge_gsb() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsCharge response = _creditService.charge(BigDecimal.valueOf(2.10))
                .withCard(TestData.gsbCardECommerce())
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // ONLINE VOID / REVERSAL

    // test_041_void_gsb: SEE TEST 39

    // HMS GIFT - REWARDS

    // ACTIVATE

    @Test
    public void test_042_activate_gift_1() throws HpsException {
        HpsGiftCardActivate response = _giftService.activate(BigDecimal.valueOf(6.00), TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_043_activate_gift_2() throws HpsException {
        HpsGiftCardActivate response = _giftService.activate(BigDecimal.valueOf(7.00), TestData.giftCard2()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // LOAD / ADD VALUE

    @Test
    public void test_044_add_value_gift_1() throws HpsException {
        HpsGiftCardAddValue response = _giftService.addValue(BigDecimal.valueOf(8.00), TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_045_add_value_gift_2() throws HpsException {
        HpsGiftCardAddValue response = _giftService.addValue(BigDecimal.valueOf(8.00), TestData.giftCard2()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // BALANCE INQUIRY

    @Test
    public void test_046_balance_inquiry_gift_1() throws HpsException {
        HpsGiftCardBalance response = _giftService.balance(TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    @Test
    public void test_047_balance_inquiry_gift_2() throws HpsException {
        HpsGiftCardBalance response = _giftService.balance(TestData.giftCard2()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    // REPLACE / TRANSFER

    @Test
    public void test_048_replace_gift_1() throws HpsException {
        HpsGiftCardReplace response = _giftService.replace(TestData.giftCard1(), TestData.giftCard2()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    @Test
    public void test_049_replace_gift_2() throws HpsException {
        HpsGiftCardReplace response = _giftService.replace(TestData.giftCard2(), TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    // SALE / REDEEM

    @Test
    public void test_050_sale_gift_1() throws HpsException {
        HpsGiftCardSale response = _giftService.sale(TestData.giftCard1(), BigDecimal.valueOf(1.0)).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_051_sale_gift_2() throws HpsException {
        HpsGiftCardSale response = _giftService.sale(TestData.giftCard2(), BigDecimal.valueOf(2.0)).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_052_sale_gift_1_void() throws HpsException {
        HpsGiftCardSale saleResponse = _giftService.sale(TestData.giftCard1(), BigDecimal.valueOf(3.0)).execute();
        assertNotEquals(saleResponse, null);
        assertEquals("0", saleResponse.getResponseCode());

        // VOID TRANSACTION
        HpsGiftCardVoid voidResponse = _giftService.voidTransaction(saleResponse.getTransactionID()).execute();
        assertNotEquals(null, voidResponse);
        assertEquals("0", voidResponse.getResponseCode());
    }

    @Test
    public void test_053_sale_gift_2_reversal() throws HpsException {
        HpsGiftCardSale saleResponse = _giftService.sale(TestData.giftCard2(), BigDecimal.valueOf(4.0)).execute();
        assertNotEquals(saleResponse, null);
        assertEquals("0", saleResponse.getResponseCode());

        // REVERSE TRANSACTION
        HpsGiftCardReversal reverseResponse = _giftService.reverse(BigDecimal.valueOf(4.0))
                .usingTransactionId(saleResponse.getTransactionID())
                .execute();

        assertNotEquals(reverseResponse, null);
        assertEquals(reverseResponse.getResponseCode(), "0");
    }

    // VOID

    // test_054_void_gift: SEE TEST 52

    // REVERSAL

    // test_055_reversal_gift: SEE TEST 53

    @Test
    public void test_056_reversal_gift_2() throws HpsException {
        HpsGiftCardReversal response = _giftService.reverse(BigDecimal.valueOf(2.0))
                .usingGiftCard(TestData.giftCard2())
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // DEACTIVATE

    @Test
    public void test_057_deactivate_gift_1() throws HpsException {
        HpsGiftCardDeactivate response = _giftService.deactivate(TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // RECEIPTS MESSAGING

    // test_058_receipts_messaging: print and scan receipt for test 51

    // BALANCE INQUIRY

    @Test
    public void test_059_balance_inquiry_rewards_1() throws HpsException {
        HpsGiftCardBalance response = _giftService.balance(TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(0), response.getPointsBalanceAmount());
    }

    @Test
    public void test_060_balance_inquiry_rewards_2() throws HpsException {
        HpsGiftCardBalance response = _giftService.balance(TestData.giftCard2()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(0), response.getPointsBalanceAmount());
    }

    // ALIAS

    @Test
    public void test_061_create_alias_gift_1() throws HpsException {
        HpsGiftCardAlias response = _giftService.alias(Enums.GiftCardAliasReqBlock1TypeAction.CREATE, "9725550100").execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_062_create_alias_gift_2() throws HpsException {
        HpsGiftCardAlias response = _giftService.alias(Enums.GiftCardAliasReqBlock1TypeAction.CREATE, "9725550100").execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_063_add_alias_gift_1() throws HpsException {
        HpsGiftCardAlias response = _giftService.alias(Enums.GiftCardAliasReqBlock1TypeAction.ADD, "2145550199")
                .withCard(TestData.giftCard1())
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_064_add_alias_gift_2() throws HpsException {
        HpsGiftCardAlias response = _giftService.alias(Enums.GiftCardAliasReqBlock1TypeAction.ADD, "2145550199")
                .withCard(TestData.giftCard2())
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_065_delete_alias_gift_1() throws HpsException {
        HpsGiftCardAlias response = _giftService.alias(Enums.GiftCardAliasReqBlock1TypeAction.DELETE, "2145550199")
                .withCard(TestData.giftCard1())
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // SALE / REDEEM

    @Test
    public void test_066_redeem_points_gift_1() throws HpsException {
        HpsGiftCardSale response = _giftService.sale(TestData.giftCard1(), BigDecimal.valueOf(100))
                .withCurrency(Enums.currencyType.POINTS)
                .execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_067_redeem_points_gift_2() throws HpsException {
        HpsGiftCardSale response = _giftService.sale(TestData.giftCard2(), BigDecimal.valueOf(200))
                .withCurrency(Enums.currencyType.POINTS)
                .execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_068_redeem_points_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setAlias("9725550100");

        HpsGiftCardSale response = _giftService.sale(giftCard, BigDecimal.valueOf(300))
                .withCurrency(Enums.currencyType.POINTS)
                .execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // REWARDS

    @Test
    public void test_069_rewards_gift_1() throws HpsException {
        HpsGiftCardReward response = _giftService.reward(TestData.giftCard1(), BigDecimal.valueOf(10)).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_070_rewards_gift_2() throws HpsException {
        HpsGiftCardReward response = _giftService.reward(TestData.giftCard2(), BigDecimal.valueOf(11)).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // REPLACE / TRANSFER

    @Test
    public void test_071_replace_gift_1() throws HpsException {
        HpsGiftCardReplace response = _giftService.replace(TestData.giftCard1(), TestData.giftCard2()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_072_replace_gift_2() throws HpsException {
        HpsGiftCardReplace response = _giftService.replace(TestData.giftCard2(), TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // DEACTIVATE

    @Test
    public void test_073_deactivate_gift_1() throws HpsException {
        HpsGiftCardDeactivate response = _giftService.deactivate(TestData.giftCard1()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_074_deactivate_gift_2() throws HpsException {
        HpsGiftCardDeactivate response = _giftService.deactivate(TestData.giftCard2()).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // RECEIPTS MESSAGING

    // test_075_receipts_messaging: print and scan receipt for test 51

    // CLOSE BATCH

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    public void test_999_close_batch() throws HpsException {
        System.out.println("\033[32mclosing open batch...");

        try {
            HpsBatch batch = _batchService.close().execute();
            assertNotEquals(batch, null);

            System.out.printf("\033[32mbatch id: %d\n", batch.getId());
            System.out.printf("\033[32msequence number: %d\n", batch.getSequenceNumber());
        } catch (HpsGatewayException ex) {
            if (ex.getCode() != HpsGatewayExceptionCodes.NoOpenBatch) {
                throw ex;
            } else {
                System.out.println("\033[33mno open batch");
            }
        }
    }
}
