package com.hps.integrator.tests.certification;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.entities.gift.*;
import com.hps.integrator.fluent.CreditChargeBuilder;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsExceptionCodes;
import com.hps.integrator.infrastructure.HpsGatewayException;
import com.hps.integrator.infrastructure.emums.GiftCardAliasAction;
import com.hps.integrator.infrastructure.emums.TaxTypeType;
import com.hps.integrator.services.HpsBatchService;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.services.fluent.HpsFluentCreditService;
import com.hps.integrator.services.fluent.HpsFluentGiftService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MOTO {
    private HpsBatchService batchService;
    private HpsFluentCreditService creditService;
    private HpsFluentGiftService giftService;
    private boolean _useTokens = true;

    private static String visaToken;
    private static String masterCardToken;
    private static String discoverToken;
    private static String amexToken;

    private static Integer test10TransactionId;
    private static Integer test20TransactionId;
    private static Integer test39TransactionId;
    private static Integer test52TransactionId;
    private static Integer test53TransactionId;

    public MOTO() throws HpsException {
        HpsServicesConfig config = new HpsServicesConfig();

        config.setSecretAPIKey("skapi_cert_MTyMAQBiHVEAewvIzXVFcmUd2UcyBge_eCpaASUp0A");
        config.setDeveloperId("012345");
        config.setVersionNumber("0001");

        batchService = new HpsBatchService(config);
        creditService = new HpsFluentCreditService(config, true);
        giftService = new HpsFluentGiftService(config, true);
    }

    // CARD VERIFY

    @Test
    public void test_000_CloseBatch() {
        try{
            HpsBatch response = this.batchService.closeBatch();
            if(response == null)
                fail("Response is null");
            System.out.println(String.format("Batch ID: %s", response.getId()));
            System.out.println(String.format("Sequence Number: %s", response.getSequenceNumber()));
        }
        catch(HpsException exc){
            if (!exc.getMessage().equals("Transaction was rejected because it requires a batch to be open."))
                fail(exc.getMessage());
        }
    }

    // Account Verification

    @Test
    public void test_001_verify_visa() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsAccountVerify response = creditService.verify()
                .withCard(card)
                .withRequestMultiUseToken(_useTokens)
                .execute();

        assertNotEquals(null, response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void test_002_verify_master_card() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsAccountVerify response = creditService.verify()
                .withCard(card)
                .withRequestMultiUseToken(_useTokens)
                .execute();

        assertNotEquals(null, response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void test_003_verify_discover() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress();
        address.setZip("75024");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsAccountVerify response = creditService.verify()
                .withCard(card)
                .withCardHolder(cardHolder)
                .withRequestMultiUseToken(_useTokens)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsAccountVerify response = creditService.verify()
                .withCard(card)
                .withCardHolder(cardHolder)
                .withRequestMultiUseToken(_useTokens)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // Balance Inquiry (for Prepaid Card)

    @Test
    public void test_005_balance_inquiry_visa() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsAuthorization response = creditService.prepaidBalanceInquiry()
                .withCard(card)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsAuthorization response = creditService.charge(BigDecimal.valueOf(13.01))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withRequestMultiUseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
        visaToken = response.getTokenData().getTokenValue();
    }

    @Test
    public void test_007_charge_master_card_token() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization response = creditService.charge(BigDecimal.valueOf(13.02))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withRequestMultiUseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
        masterCardToken = response.getTokenData().getTokenValue();
    }

    @Test
    public void test_008_charge_discover_token() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("750241234");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization response = creditService.charge(BigDecimal.valueOf(13.03))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withRequestMultiUseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
        discoverToken = response.getTokenData().getTokenValue();
    }

    @Test
    public void test_009_charge_amex_token() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsAuthorization response = creditService.charge(BigDecimal.valueOf(13.04))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withRequestMultiUseToken(true)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
        amexToken = response.getTokenData().getTokenValue();
    }

    // CREDIT SALE

    @Test
    public void test_010_charge_visa() throws HpsException, IOException {
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

        CreditChargeBuilder builder = creditService.charge(BigDecimal.valueOf(17.01));

        if(_useTokens) {
            builder.withToken(visaToken);
        } else {
            builder.withCard(card);
        }

        HpsCharge chargeResponse = builder
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        test10TransactionId = chargeResponse.getTransactionID();
    }

    @Test
    public void test_011_charge_master_card() throws HpsException, IOException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        CreditChargeBuilder builder = creditService.charge(BigDecimal.valueOf(17.02));

        if(_useTokens) {
            builder.withToken(masterCardToken);
        } else {
            builder.withCard(card);
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        CreditChargeBuilder builder = creditService.charge(BigDecimal.valueOf(17.03));

        if(_useTokens) {
            builder.withToken(discoverToken);
        } else {
            builder.withCard(card);
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        CreditChargeBuilder builder = creditService.charge(BigDecimal.valueOf(17.04));

        if(_useTokens) {
            builder.withToken(amexToken);
        } else {
            builder.withCard(card);
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("3566007770007321");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsCharge response = creditService.charge(BigDecimal.valueOf(17.05))
                .withCard(card)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization authResponse = creditService.authorize(BigDecimal.valueOf(17.06))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, authResponse);
        assertEquals("00", authResponse.getResponseCode());

        // test 015b Capture/AddToBatch
        HpsTransaction captureResponse = creditService.capture(authResponse.getTransactionID()).execute();

        assertNotEquals(null, captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_016_authorization_master_card() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("750241234");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization authResponse = creditService.authorize(BigDecimal.valueOf(17.07))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, authResponse);
        assertEquals("00", authResponse.getResponseCode());

        // test 016b Capture/AddToBatch
        HpsTransaction captureResponse = creditService.capture(authResponse.getTransactionID()).execute();

        assertNotEquals(null, captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_017_authorization_discover() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization authResponse = creditService.authorize(BigDecimal.valueOf(17.08))
                .withCard(card)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization response = creditService.charge(BigDecimal.valueOf(130))
                .withCard(card)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization response = creditService.charge(BigDecimal.valueOf(145))
                .withCard(card)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(155))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .withAllowPartialAuth(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals(chargeResponse.getResponseCode(), "10");

        assertNotEquals(null, chargeResponse.getAuthorizedAmount());
        assertEquals(BigDecimal.valueOf(100).setScale(2, BigDecimal.ROUND_CEILING), chargeResponse.getAuthorizedAmount());
        test20TransactionId = chargeResponse.getTransactionID();
    }

    // LEVEL II CORPORATE PURCHASE CARD

    @Test
    public void test_021_level_ii_response_b() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860 Dallas Pkwy");
        cardHolder.getAddress().setZip("750241234");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(112.34))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("B", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(TaxTypeType.NotUsed);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(112.34))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withAllowDuplicates(true)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("B", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxType(TaxTypeType.SalesTax);
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(123.45))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals(chargeResponse.getCpcIndicator(), "R");

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxType(TaxTypeType.TaxExempt);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(134.56))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(TaxTypeType.SalesTax);
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.06))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(TaxTypeType.NotUsed);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.07))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(TaxTypeType.SalesTax);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.08))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(TaxTypeType.SalesTax);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.09))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("S", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(TaxTypeType.TaxExempt);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.10))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(TaxTypeType.NotUsed);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.11))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(TaxTypeType.SalesTax);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.12))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxAmount(BigDecimal.valueOf(1).setScale(2, BigDecimal.ROUND_CEILING));
        cpcData.setTaxType(TaxTypeType.SalesTax);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsAuthorization chargeResponse = creditService.charge(BigDecimal.valueOf(111.13))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCpcReq(true)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        assertEquals("0", chargeResponse.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData();
        cpcData.setCardHolderPoNumber("9876543210");
        cpcData.setTaxType(TaxTypeType.TaxExempt);

        HpsTransaction cpcResponse = creditService.cpcEdit(chargeResponse.getTransactionID())
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsTransaction response = creditService.offlineCharge(BigDecimal.valueOf(17.10))
                .withCard(card)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsTransaction response = creditService.offlineAuth(BigDecimal.valueOf(17.10))
                .withCard(card)
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsTransaction response = creditService.refund(BigDecimal.valueOf(15.15))
                .withCard(card)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // ONLINE VOID / REVERSAL

    @Test
    public void test_035_VoidTest10() throws HpsException {
        HpsTransaction voidResponse = creditService.creditVoid(test10TransactionId).execute();

        assertNotEquals(null, voidResponse);
        assertEquals("00", voidResponse.getResponseCode());
    }

    @Test
    public void test_036_VoidTest20() throws HpsException {
        HpsTransaction voidResponse = creditService.creditVoid(test20TransactionId).execute();

        assertNotEquals(null, voidResponse);
        assertEquals("00", voidResponse.getResponseCode());
    }

    // ONE CARD - GSB CARD FUNCTIONS

    // BALANCE INQUIRY

    @Test
    public void test_037_balance_inquiry_gsb() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6277220572999800");
        card.setExpMonth(12);
        card.setExpYear(2049);

        HpsTransaction response = creditService.prepaidBalanceInquiry()
                .withCard(card)
                .withCardHolder(cardHolder)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // ADD VALUE

    @Test
    public void test_038_add_value_gsb() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220572999800^   /                         ^49121010557010000016000000?F;6277220572999800=49121010557010000016?");

        HpsTransaction response = creditService.prepaidAddValue(BigDecimal.valueOf(15.00))
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

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6277220572999800");
        card.setExpMonth(12);
        card.setExpYear(2049);

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsCharge chargeResponse = creditService.charge(BigDecimal.valueOf(2.05))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, chargeResponse);
        assertEquals("00", chargeResponse.getResponseCode());
        test39TransactionId = chargeResponse.getTransactionID();
    }

    @Test
    public void test_040_charge_gsb() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress());
        cardHolder.getAddress().setAddress("6860");
        cardHolder.getAddress().setZip("75024");

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6277220572999800");
        card.setExpMonth(12);
        card.setExpYear(2049);

        HpsDirectMarketData directMarketData = new HpsDirectMarketData();
        directMarketData.setInvoiceNumber("123456");

        HpsCharge response = creditService.charge(BigDecimal.valueOf(2.10))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withDirectMarketData(directMarketData)
                .execute();

        assertNotEquals(null, response);
        assertEquals("00", response.getResponseCode());
    }

    // ONLINE VOID / REVERSAL

    @Test
    public void test_041_VoidGsb() throws HpsException {
        HpsTransaction voidResponse = creditService.creditVoid(test39TransactionId).execute();
        assertNotEquals(null, voidResponse);
        assertEquals("00", voidResponse.getResponseCode());
    }

    // HMS GIFT - REWARDS

    // ACTIVATE

    @Test
    public void test_042_activate_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardResponse response = giftService.activate(BigDecimal.valueOf(6.00)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_043_activate_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.activate(BigDecimal.valueOf(7.00)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // LOAD / ADD VALUE

    @Test
    public void test_044_add_value_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardResponse response = giftService.addValue(BigDecimal.valueOf(8.00)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_045_add_value_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.addValue(BigDecimal.valueOf(8.00)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // BALANCE INQUIRY

    @Test
    public void test_046_balance_inquiry_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardResponse response = giftService.balance().withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    @Test
    public void test_047_balance_inquiry_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.balance().withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    // REPLACE / TRANSFER

    @Test
    public void test_048_replace_gift_1() throws HpsException {
        HpsGiftCard oldCard = new HpsGiftCard();
        oldCard.setCardNumber("5022440000000000098");

        HpsGiftCard newCard = new HpsGiftCard();
        newCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.replace().withOldCard(oldCard).withNewCard(newCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    @Test
    public void test_049_replace_gift_2() throws HpsException {
        HpsGiftCard newCard = new HpsGiftCard();
        newCard.setCardNumber("5022440000000000098");

        HpsGiftCard oldCard = new HpsGiftCard();
        oldCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.replace().withOldCard(oldCard).withNewCard(newCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_CEILING), response.getBalanceAmount());
    }

    // SALE / REDEEM

    @Test
    public void test_050_sale_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardSale response = giftService.sale(BigDecimal.valueOf(1.0)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_051_sale_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardSale response = giftService.sale(BigDecimal.valueOf(2.0)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_052_sale_gift_1_void() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardSale saleResponse = giftService.sale(BigDecimal.valueOf(3.0)).withCard(giftCard).execute();
        assertNotEquals(saleResponse, null);
        assertEquals("0", saleResponse.getResponseCode());
        test52TransactionId = saleResponse.getTransactionID();
    }

    @Test
    public void test_053_sale_gift_2_reversal() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardSale saleResponse = giftService.sale(BigDecimal.valueOf(4.0)).withCard(giftCard).execute();
        assertNotEquals(saleResponse, null);
        assertEquals("0", saleResponse.getResponseCode());
        test53TransactionId = saleResponse.getTransactionID();
    }

    // VOID

    @Test
    public void test_054_void_gift() throws HpsException {
        HpsGiftCardResponse voidResponse = giftService.voidSale(test52TransactionId).execute();
        assertNotEquals(null, voidResponse);
        assertEquals("0", voidResponse.getResponseCode());
    }

    // REVERSAL

    @Test
    public void test_055_reversal_gift() throws HpsException {
        HpsGiftCardResponse reverseResponse = giftService.reverse(BigDecimal.valueOf(4.0))
                .withTransactionId(test53TransactionId)
                .execute();

        assertNotEquals(reverseResponse, null);
        assertEquals(reverseResponse.getResponseCode(), "0");
    }

    @Test
    public void test_056_reversal_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.reverse(BigDecimal.valueOf(2.0))
                .withCard(giftCard)
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // DEACTIVATE

    @Test
    public void test_057_deactivate_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardResponse response = giftService.deactivate().withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // RECEIPTS MESSAGING

    // test_058_receipts_messaging: print and scan receipt for test 51

    // BALANCE INQUIRY

    @Test
    public void test_059_balance_inquiry_rewards_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardResponse response = giftService.balance().withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(0), response.getPointsBalanceAmount());
    }

    @Test
    public void test_060_balance_inquiry_rewards_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.balance().withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
        assertEquals(BigDecimal.valueOf(0), response.getPointsBalanceAmount());
    }

    // ALIAS

    @Test
    public void test_061_create_alias_gift_1() throws HpsException {
        HpsGiftCardAlias response = giftService.alias().withAction(GiftCardAliasAction.Create).withAlias("9725550100").execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_062_create_alias_gift_2() throws HpsException {
        HpsGiftCardAlias response = giftService.alias().withAction(GiftCardAliasAction.Create).withAlias("9725550100").execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_063_add_alias_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardAlias response = giftService.alias().withAction(GiftCardAliasAction.Add)
                .withAlias("2145550199")
                .withCard(giftCard)
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_064_add_alias_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardAlias response = giftService.alias().withAction(GiftCardAliasAction.Add).withAlias("2145550199")
                .withCard(giftCard)
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_065_delete_alias_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardAlias response = giftService.alias().withAction(GiftCardAliasAction.Delete).withAlias("2145550199")
                .withCard(giftCard)
                .execute();

        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // SALE / REDEEM

    @Test
    public void test_066_redeem_points_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardSale response = giftService.sale(BigDecimal.valueOf(100)).withCard(giftCard)
                .withCurrency("points")
                .execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_067_redeem_points_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardSale response = giftService.sale(BigDecimal.valueOf(200)).withCard(giftCard)
                .withCurrency("points")
                .execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_068_redeem_points_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setAlias("9725550100");

        HpsGiftCardSale response = giftService.sale(BigDecimal.valueOf(300)).withCard(giftCard)
                .withCurrency("points")
                .execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // REWARDS

    @Test
    public void test_069_rewards_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardResponse response = giftService.reward(BigDecimal.valueOf(10)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_070_rewards_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.reward(BigDecimal.valueOf(11)).withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // REPLACE / TRANSFER

    @Test
    public void test_071_replace_gift_1() throws HpsException {
        HpsGiftCard oldCard = new HpsGiftCard();
        oldCard.setCardNumber("5022440000000000098");

        HpsGiftCard newCard = new HpsGiftCard();
        newCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.replace().withOldCard(oldCard).withNewCard(newCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_072_replace_gift_2() throws HpsException {
        HpsGiftCard newCard = new HpsGiftCard();
        newCard.setCardNumber("5022440000000000098");

        HpsGiftCard oldCard = new HpsGiftCard();
        oldCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.replace().withOldCard(oldCard).withNewCard(newCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // DEACTIVATE

    @Test
    public void test_073_deactivate_gift_1() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000098");

        HpsGiftCardResponse response = giftService.deactivate().withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_074_deactivate_gift_2() throws HpsException {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.deactivate().withCard(giftCard).execute();
        assertNotEquals(null, response);
        assertEquals("0", response.getResponseCode());
    }

    // RECEIPTS MESSAGING

    // test_075_receipts_messaging: print and scan receipt for test 51

    // CLOSE BATCH

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    public void test_999_close_batch() throws HpsException {
        try{
            HpsBatch response = this.batchService.closeBatch();
            if(response == null)
                fail("Response is null");
            System.out.println(String.format("Batch ID: %s", response.getId()));
            System.out.println(String.format("Sequence Number: %s", response.getSequenceNumber()));
        }
        catch(HpsException exc){
            if (!exc.getMessage().equals("Transaction was rejected because it requires a batch to be open."))
                fail(exc.getMessage());
        }
    }
}
