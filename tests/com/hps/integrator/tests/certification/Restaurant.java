package com.hps.integrator.tests.certification;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.HpsEncryptionData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.entities.debit.HpsDebitAuthorization;
import com.hps.integrator.entities.ebt.HpsEbtAuthorization;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardAlias;
import com.hps.integrator.entities.gift.HpsGiftCardResponse;
import com.hps.integrator.entities.gift.HpsGiftCardSale;
import com.hps.integrator.fluent.CreditChargeBuilder;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsTrackDataMethod;
import com.hps.integrator.infrastructure.emums.GiftCardAliasAction;
import com.hps.integrator.infrastructure.emums.TaxTypeType;
import com.hps.integrator.services.HpsBatchService;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.services.fluent.HpsFluentCreditService;
import com.hps.integrator.services.fluent.HpsFluentDebitService;
import com.hps.integrator.services.fluent.HpsFluentEbtService;
import com.hps.integrator.services.fluent.HpsFluentGiftService;
// import javafx.util.converter.BigDecimalStringConverter;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Restaurant {
    HpsBatchService batchService;
    HpsFluentCreditService creditService;
    HpsFluentDebitService debitService;
    HpsFluentEbtService ebtService;
    HpsFluentGiftService giftService;

    boolean useTokens = true;

    private static String visaToken;
    private static String mastercardToken;
    private static String discoverToken;
    private static String amexToken;

    private static Integer test010TransactionId;
    private static Integer test014TransactionId;
    private static Integer test015TransactionId;
    private static Integer test017TransactionId;
    private static Integer test021TransactionId;
    private static Integer test023TransactionId;
    private static Integer test042TransactionId;
    private static Integer test066TransactionId;
    private static Integer test069TransactionId;
    private static Integer test105TransactionId;
    private static Integer test106TransactionId;

    public Restaurant() throws HpsException {
        HpsServicesConfig config = new HpsServicesConfig();
        config.setSecretAPIKey("skapi_cert_MbKPAQCG-1QA2wtK44AP7Jhi4osTUmCXcNGyUWAYyw");

        batchService = new HpsBatchService(config);
        creditService = new HpsFluentCreditService(config, true);
        debitService = new HpsFluentDebitService(config, true);
        ebtService = new HpsFluentEbtService(config, true);
        giftService = new HpsFluentGiftService(config, true);
    }

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

    /*
        CREDIT CARD FUNCTIONS
        CARD VERIFY
        ACCOUNT VERIFICATION
     */
    @Test
    public void test_001_CardVerifyVisa() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsAccountVerify response = creditService.verify()
                .withTrackData(trackData)
                .withRequestMultiUseToken(useTokens)
                .execute();
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void test_002_CardVerifyMastercardSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsAccountVerify response = creditService.verify()
                .withTrackData(trackData)
                .withRequestMultiUseToken(useTokens)
                .execute();
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void test_003_CardVerifyDiscover() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6011000990156527^DIS TEST CARD^25121011000062111401?;6011000990156527=25121011000062111401?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsAccountVerify response = creditService.verify()
                .withTrackData(trackData)
                .withRequestMultiUseToken(useTokens)
                .execute();
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    // Address Verification

    @Test
    public void test_004_CardVerifyAmex() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsAccountVerify response = creditService.verify()
                .withCard(card)
                .withCardHolder(cardHolder)
                .withRequestMultiUseToken(useTokens)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Balance Inquiry (for Prepaid)

    @Test
    public void test_005_BalanceInquiryVisa() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsAuthorization response = creditService.prepaidBalanceInquiry()
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // CREDIT SALE (For multi-use token only)

    @Test
    public void test_006_ChargeVisaSwipeToken() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.01"))
                .withTrackData(trackData)
                .withRequestMultiUseToken(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        visaToken = response.getTokenData().getTokenValue();
    }

    @Test
    public void test_007_ChargeMastercardSwipeToken() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.02"))
                .withTrackData(trackData)
                .withRequestMultiUseToken(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        mastercardToken = response.getTokenData().getTokenValue();
    }

    @Test
    public void test_008_ChargeDiscoverSwipeToken() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6011000990156527^DIS TEST CARD^25121011000062111401?;6011000990156527=25121011000062111401?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.03"))
                .withTrackData(trackData)
                .withRequestMultiUseToken(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        discoverToken = response.getTokenData().getTokenValue();
    }

    @Test
    public void test_009_ChargeAmexSwipeToken() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B3727 006992 51018^AMEX TEST CARD^2512990502700?;372700699251018=2512990502700?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.04"))
                .withTrackData(trackData)
                .withRequestMultiUseToken(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        amexToken = response.getTokenData().getTokenValue();
    }

    /*
        CREDIT SALE
        SWIPED
     */

    @Test
    public void test_010_ChargeVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.01"))
                .withTrackData(trackData)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        test010TransactionId = response.getTransactionID();
    }

    @Test
    public void test_011_ChargeMastercardSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.02"))
                .withTrackData(trackData)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_012_ChargeDiscoverSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6011000990156527^DIS TEST CARD^25121011000062111401?;6011000990156527=25121011000062111401?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.03"))
                .withTrackData(trackData)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_013_ChargeAmexSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B3727 006992 51018^AMEX TEST CARD^2512990502700?;372700699251018=2512990502700?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.04"))
                .withTrackData(trackData)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_014_ChargeJbcSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B3566007770007321^JCB TEST CARD^2512101100000000000000000064300000?;3566007770007321=25121011000000076435?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.05"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        test014TransactionId = response.getTransactionID();
    }

    @Test
    public void test_015_ChargeVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.06"))
                .withTrackData(trackData)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        test015TransactionId = response.getTransactionID();
    }

    // Manually Entered - Card Present

    @Test
    public void test_016_ChargeVisaManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("750241234");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("16.01"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_017_ChargeMasterCardManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("16.02"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        test017TransactionId = response.getTransactionID();
    }

    @Test
    public void test_018_ChargeDiscoverManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("750241234");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("16.03"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_019_ChargeAmexManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsCharge response = creditService.charge(new BigDecimal("16.04"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_020_ChargeJcbManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("3566007770007321");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("16.05"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_021_ChargeDiscoverManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("750241234");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("16.07"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        test021TransactionId = response.getTransactionID();
    }

    // Manually Entered - Card Not Present

    @Test
    public void test_022_ChargeVisaManualCardNotPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("750241234");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        CreditChargeBuilder builder = creditService.charge(new BigDecimal("17.01")).withCardHolder(cardHolder);

        if(useTokens)
            builder.withToken(visaToken);
        else builder.withCard(card);

        HpsCharge response = builder.execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_023_ChargeMasterCardManualCardNotPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        CreditChargeBuilder builder = creditService.charge(new BigDecimal("17.02")).withCardHolder(cardHolder);

        if(useTokens)
            builder.withToken(mastercardToken);
        else builder.withCard(card);

        HpsCharge response = builder.execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        test023TransactionId = response.getTransactionID();
    }

    @Test
    public void test_024_ChargeDiscoverManualCardNotPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("750241234");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6011000990156527");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        CreditChargeBuilder builder = creditService.charge(new BigDecimal("17.03")).withCardHolder(cardHolder);

        if(useTokens)
            builder.withToken(discoverToken);
        else builder.withCard(card);

        HpsCharge response = builder.execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_025_ChargeAmexManualCardNotPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        CreditChargeBuilder builder = creditService.charge(new BigDecimal("17.04")).withCardHolder(cardHolder);

        if(useTokens)
            builder.withToken(amexToken);
        else builder.withCard(card);

        HpsCharge response = builder.execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_026_ChargeJcbManualCardNotPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("3566007770007321");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("17.05"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Contactless

    @Test
    public void test_027_ChargeVisaContactless() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Proximity);

        HpsCharge response = creditService.charge(new BigDecimal("18.01"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_028_ChargeMastercardContactless() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Proximity);

        HpsCharge response = creditService.charge(new BigDecimal("18.02"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_029_ChargeDiscoverContactless() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6011000990156527^DIS TEST CARD^25121011000062111401?;6011000990156527=25121011000062111401?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Proximity);

        HpsCharge response = creditService.charge(new BigDecimal("18.03"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_030_ChargeAmexContactless() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B3727 006992 51018^AMEX TEST CARD^2512990502700?;372700699251018=2512990502700?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Proximity);

        HpsCharge response = creditService.charge(new BigDecimal("18.04"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // AUTHORIZATION

    @Test
    public void test_031_AuthorizeVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        // 031a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("15.08"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 031b capture
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_032_AuthorizeVisaSwipeAdditionalAuth() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        // 032a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("15.09"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 032b Additional Auth (restaurant only)

        // 032c Add to batch
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_033_AuthorizeMasterCardSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        // 033a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("15.10"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 033b capture
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    // AUTHORIZATION - Manually Entered, Card Present

    @Test
    public void test_034_AuthorizeVisaManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        // 034a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("16.08"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 034b capture
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_035_AuthorizeVisaManualCardPresentAdditionalAuth() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        // 035a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("16.09"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 035b Additional Auth (restaurant only)
        HpsAuthorization additionalResponse = creditService.additionalAuth(new BigDecimal("17.59"))
                .withTransactionId(response.getTransactionID())
                .execute();
        assertNotNull(additionalResponse);
        assertEquals("00", additionalResponse.getResponseCode());

        // 035c Add to batch
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_036_AuthorizeMasterCardManualCardPresent() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        // 036a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("16.10"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 036b capture
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    // AUTHORIZATION - Manually Entered, Card Not Present

    @Test
    public void test_037_AuthorizeVisaManual() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("750241234");
        address.setAddress("6860 Dallas Pkwy");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        // 034a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("17.08"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 034b capture
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test
    public void test_038_AuthorizeMasterCardManual() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        HpsAddress address = new HpsAddress("75024");
        address.setAddress("6860");
        cardHolder.setAddress(address);

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        // 036a authorize
        HpsAuthorization response = creditService.authorize(new BigDecimal("17.09"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        // 036b capture
        HpsTransaction captureResponse = creditService.capture(response.getTransactionID()).execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    // PARTIALLY APPROVED SALE (Required)

    @Test
    public void test_039_ChargeDiscoverSwipePartialApproval() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6011000990156527^DIS TEST CARD^25121011000062111401?;6011000990156527=25121011000062111401?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("40.00"))
                .withTrackData(trackData)
                .withAllowPartialAuth(true)
                .execute();
        assertNotNull(response);
        assertEquals("10", response.getResponseCode());
        assertEquals(new BigDecimal("40.00"), response.getAuthorizedAmount());
    }

    @Test
    public void test_040_ChargeVisaSwipePartialApproval() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("130.00"))
                .withTrackData(trackData)
                .withAllowPartialAuth(true)
                .execute();
        assertNotNull(response);
        assertEquals("10", response.getResponseCode());
        assertEquals(new BigDecimal("110.00"), response.getAuthorizedAmount());
    }

    @Test
    public void test_041_ChargeMasterCardManualPartialApproval() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("145.00"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withAllowPartialAuth(true)
                .execute();
        assertNotNull(response);
        assertEquals("10", response.getResponseCode());
        assertEquals(new BigDecimal("65.00"), response.getAuthorizedAmount());
    }

    @Test
    public void test_042_ChargeDiscoverSwipePartialApproval() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6011000990156527^DIS TEST CARD^25121011000062111401?;6011000990156527=25121011000062111401?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("155.00"))
                .withTrackData(trackData)
                .withAllowPartialAuth(true)
                .execute();
        assertNotNull(response);
        assertEquals("10", response.getResponseCode());
        assertEquals(new BigDecimal("100.00"), response.getAuthorizedAmount());
        test042TransactionId = response.getTransactionID();
    }

    /*
        SALE WITH GRATUITY
        Tip Edit (Tip at Settlement)
     */

    @Test
    public void test_043_ChargeVisaSwipeEditGratuity() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("15.11"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        HpsTransaction editResponse = creditService.edit(response.getTransactionID())
                .withAmount(new BigDecimal("18.11"))
                .withGratuity(new BigDecimal("3.00"))
                .execute();
        assertNotNull(editResponse);
        assertEquals("00", editResponse.getResponseCode());
    }

    @Test
    public void test_044_ChargeMasterCardManualEditGratuity() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("15.12"))
                .withCardHolder(cardHolder)
                .withCard(card)
                .withCardPresent(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        HpsTransaction editResponse = creditService.edit(response.getTransactionID())
                .withAmount(new BigDecimal("18.12"))
                .withGratuity(new BigDecimal("3.00"))
                .execute();
        assertNotNull(editResponse);
        assertEquals("00", editResponse.getResponseCode());
    }

    // Tip on Purchase

    @Test
    public void test_045_ChargeVisaManualGratuity() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("18.63"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .withGratuity(new BigDecimal("3.50"))
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_046_ChargeMasterCardSwipeGratuity() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("18.64"))
                .withTrackData(trackData)
                .withGratuity(new BigDecimal("3.50"))
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        HpsTransaction editResponse = creditService.edit(response.getTransactionID())
                .withAmount(new BigDecimal("18.12"))
                .withGratuity(new BigDecimal("3.00"))
                .execute();
        assertNotNull(editResponse);
        assertEquals("00", editResponse.getResponseCode());
    }

    // LEVEL II CORPORATE PURCHASE CARD

    @Test
    public void test_047_LevelIIVisaSwipeResponseB() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("112.34"))
                .withTrackData(trackData)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("B", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData("9876543210", TaxTypeType.NotUsed);
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_048_LevelIIVisaSwipeResponseR() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("123.45"))
                .withTrackData(trackData)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("R", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData(TaxTypeType.TaxExempt);
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_049_LevelIIVisaManualResponseS() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("134.56"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("S", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData("9876543210", TaxTypeType.SalesTax, new BigDecimal("1.0"));
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_050_LevelIIMasterCardSwipeResponseS() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("111.06"))
                .withTrackData(trackData)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("S", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData("9876543210", TaxTypeType.NotUsed);
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_051_LevelIIMasterCardManualResponseS() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("111.07"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("S", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData("9876543210", TaxTypeType.SalesTax, new BigDecimal("1.0"));
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_052_LevelIIMasterCardManualResponseS() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("123");

        HpsCharge response = creditService.charge(new BigDecimal("111.09"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("S", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData("9876543210", TaxTypeType.TaxExempt);
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_053_LevelIIAmexSwipeNoResponse() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B3727 006992 51018^AMEX TEST CARD^2512990502700?;372700699251018=2512990502700?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("111.10"))
                .withTrackData(trackData)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("0", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData(TaxTypeType.SalesTax, new BigDecimal("1.00"));
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_054_LevelIIAmexManualNoResponse() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsCharge response = creditService.charge(new BigDecimal("111.11"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("0", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData("9876543210", TaxTypeType.NotUsed);
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    @Test
    public void test_055_LevelIIAmexManualNoResponse() throws HpsException {
        HpsCardHolder cardHolder = new HpsCardHolder();
        cardHolder.setAddress(new HpsAddress("75024"));

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("372700699251018");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setCvv("1234");

        HpsCharge response = creditService.charge(new BigDecimal("111.12"))
                .withCard(card)
                .withCardHolder(cardHolder)
                .withCardPresent(true)
                .withCpcReq(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        assertEquals("0", response.getCpcIndicator());

        HpsCpcData cpcData = new HpsCpcData("9876543210", TaxTypeType.TaxExempt);
        HpsTransaction cpcResponse = creditService.cpcEdit(response.getTransactionID()).withCpcData(cpcData).execute();
        assertNotNull(cpcResponse);
        assertEquals("00", cpcResponse.getResponseCode());
    }

    // OFFLINE SALE / AUTHORIZATION

    @Test
    public void test_056_OfflineChargeVisaManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsTransaction response = creditService.offlineCharge(new BigDecimal("15.11"))
                .withCard(card)
                .withOfflineAuthCode("654321")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_056_OfflineAuthVisaManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsTransaction response = creditService.offlineAuth(new BigDecimal("15.11"))
                .withCard(card)
                .withOfflineAuthCode("654321")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // RETURN

    @Test
    public void test_057_ReturnMasterCard() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("5473500000000014");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsRefund response = creditService.refund(new BigDecimal("15.11"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_058_ReturnJcbTransactionId() throws HpsException {
        HpsRefund response = creditService.refund(new BigDecimal("15.05"))
                .withTransactionId(test014TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // ONLINE VOID / REVERSAL (Required)

    @Test
    public void test_059_ReversalVisa() throws HpsException {
        HpsReversal response = creditService.reverse(new BigDecimal("15.01"))
                .withTransactionId(test010TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_060_ReversalMasterCard() throws HpsException {
        HpsReversal response = creditService.reverse(new BigDecimal("16.02"))
                .withTransactionId(test017TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_061_ReversalMasterCard() throws HpsException {
        HpsReversal response = creditService.reverse(new BigDecimal("17.02"))
                .withTransactionId(test023TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_062_ReversalDiscover() throws HpsException {
        HpsReversal response = creditService.reverse(new BigDecimal("100.00"))
                .withTransactionId(test042TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_063_ReversalVisaPartial() throws HpsException {
        HpsReversal response = creditService.reverse(new BigDecimal("15.06"))
                .withAuthAmount(new BigDecimal("5.06"))
                .withTransactionId(test015TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_064_ReversalDiscoverPartial() throws HpsException {
        HpsReversal response = creditService.reverse(new BigDecimal("16.07"))
                .withAuthAmount(new BigDecimal("6.07"))
                .withTransactionId(test021TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // PIN DEBIT CARD FUNCTIONS

    @Test
    public void test_065_DebitSaleVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsDebitAuthorization response = debitService.sale(new BigDecimal("14.01"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_066_DebitSaleMasterCardSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1052711%B5473501000000014^MC TEST CARD^251200000000000000000000000000000000?|GVEY/MKaKXuqqjKRRueIdCHPPoj1gMccgNOtHC41ymz7bIvyJJVdD3LW8BbwvwoenI+|+++++++C4cI2zjMp|11;5473501000000014=25120000000000000000?|8XqYkQGMdGeiIsgM0pzdCbEGUDP|+++++++C4cI2zjMp|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsDebitAuthorization response = debitService.sale(new BigDecimal("14.02"))
                .withTrackData(trackData)
                .withPinBlock("F505AD81659AA42A3D123412324000AB")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
        test066TransactionId = response.getTransactionID();
    }

    @Test
    public void test_067_DebitSaleVisaSwipeCashBack() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsDebitAuthorization response = debitService.sale(new BigDecimal("14.03"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withCashBack(new BigDecimal("5.00"))
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // PARTIALLY APPROVED PURCHASE

    @Test
    public void test_068_DebitSaleMasterCardPartialApproval() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1052711%B5473501000000014^MC TEST CARD^251200000000000000000000000000000000?|GVEY/MKaKXuqqjKRRueIdCHPPoj1gMccgNOtHC41ymz7bIvyJJVdD3LW8BbwvwoenI+|+++++++C4cI2zjMp|11;5473501000000014=25120000000000000000?|8XqYkQGMdGeiIsgM0pzdCbEGUDP|+++++++C4cI2zjMp|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsDebitAuthorization response = debitService.sale(new BigDecimal("33.00"))
                .withTrackData(trackData)
                .withPinBlock("F505AD81659AA42A3D123412324000AB")
                .withAllowPartialAuth(true)
                .execute();
        assertNotNull(response);
        assertEquals("10", response.getResponseCode());
        assertEquals(new BigDecimal("22.00"), response.getAuthorizedAmount());
    }

    @Test
    public void test_069_DebitSaleVisaPartialApproval() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsDebitAuthorization response = debitService.sale(new BigDecimal("44.00"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withAllowPartialAuth(true)
                .execute();
        assertNotNull(response);
        assertEquals("10", response.getResponseCode());
        assertEquals(new BigDecimal("33.00"), response.getAuthorizedAmount());
        test069TransactionId = response.getTransactionID();
    }

    // RETURN

    @Test
    public void test_070_DebitReturnVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsDebitAuthorization response = debitService.refund(new BigDecimal("14.07"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // REVERSAL

    @Test
    public void test_071_DebitReversalMasterCard() throws HpsException {
        HpsDebitAuthorization response = debitService.reverse(new BigDecimal("14.02"))
                .withTransactionId(test066TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_072_DebitReversalVisa() throws HpsException {
        HpsDebitAuthorization response = debitService.reverse(new BigDecimal("33.00"))
                .withTransactionId(test069TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    /*
        ONE Card - GSB CARD FUNCTIONS
        Balance Inquiry
     */

    @Test
    public void test_073_BalanceInquiryGsbSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220572999800^   /                         ^49121010557010000016000000?F;6277220572999800=49121010557010000016?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsAuthorization response = creditService.prepaidBalanceInquiry()
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_074_BalanceInquiryGsbManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6277220572999800");
        card.setExpMonth(12);
        card.setExpYear(2049);

        HpsAuthorization response = creditService.prepaidBalanceInquiry()
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Add Value (LOAD)

    @Test
    public void test_075_AddValueGsbSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220572999800^   /                         ^49121010557010000016000000?F;6277220572999800=49121010557010000016?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsAuthorization response = creditService.prepaidAddValue(new BigDecimal("5.00"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_076_AddValueGsbManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6277220572999800");
        card.setExpMonth(12);
        card.setExpYear(2049);

        HpsAuthorization response = creditService.prepaidAddValue(new BigDecimal("5.00"))
                .withCard(card)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // SALE

    @Test
    public void test_077_ChargeGsbSwipeReversal() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220572999800^   /                         ^49121010557010000016000000?F;6277220572999800=49121010557010000016?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("2.05"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        HpsTransaction reversalResponse = creditService.reverse(new BigDecimal("2.05"))
                .withTransactionId(response.getTransactionID())
                .execute();
        assertNotNull(reversalResponse);
        assertEquals("00", reversalResponse.getResponseCode());
    }

    @Test
    public void test_078_ChargeGsbSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220572999800^   /                         ^49121010557010000016000000?F;6277220572999800=49121010557010000016?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("2.10"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_079_ChargeGsbSwipePartialReversal() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220572999800^   /                         ^49121010557010000016000000?F;6277220572999800=49121010557010000016?");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        HpsCharge response = creditService.charge(new BigDecimal("2.15"))
                .withTrackData(trackData)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        HpsTransaction reversalResponse = creditService.reverse(new BigDecimal("2.15"))
                .withAuthAmount(new BigDecimal("1.15"))
                .withTransactionId(response.getTransactionID())
                .execute();
        assertNotNull(reversalResponse);
        assertEquals("00", reversalResponse.getResponseCode());
    }

    /*
       EBT FUNCTIONS
        Food Stamp Purchase
     */

    @Test
    public void test_080_EbtfsPurchaseVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsEbtAuthorization response = ebtService.purchase(new BigDecimal("101.01"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_081_EbtfsPurchaseVisaManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.purchase(new BigDecimal("102.01"))
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Food Stamp Electronic Voucher (Manual Entry Only)

    @Test
    public void test_082_EbtVoucherPurchaseVisa() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.voucherPurchase(new BigDecimal("103.01"))
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withSerialNumber(123456789012345L)
                .withApprovalCode(123456)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Food Stamp Balance Inquiry

    @Test
    public void test_083_EbtfsReturnVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsEbtAuthorization response = ebtService.refund(new BigDecimal("104.01"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_084_EbtfsReturnVisaManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.refund(new BigDecimal("105.01"))
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Food Stamp Balance Inquiry

    @Test
    public void test_085_EbtBalanceInquiryVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsEbtAuthorization response = ebtService.balanceInquiry()
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withInquiryType("FOODSTAMP")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_086_EbtBalanceInquiryVisaManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.balanceInquiry()
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withCardPresent(true)
                .withReaderPresent(true)
                .withInquiryType("FOODSTAMP")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    /*
        assertEquals("00", response.getResponseCode());
        EBT CASH BENEFITS
        Cash Back Purchase
     */

    @Test
    public void test_087_EbtCashBackPurchaseVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsEbtAuthorization response = ebtService.cashBackPurchase(new BigDecimal("106.01"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withCashBack(new BigDecimal("5.00"))
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_088_EbtCashBackPurchaseVisaManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.cashBackPurchase(new BigDecimal("107.01"))
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withCashBack(new BigDecimal("5.00"))
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // No Cash Back Purchase

    @Test
    public void test_089_EbtCashBackPurchaseVisaSwipeNoCashBack() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsEbtAuthorization response = ebtService.cashBackPurchase(new BigDecimal("108.01"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_090_EbtCashBackPurchaseVisaManualNoCashBack() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.cashBackPurchase(new BigDecimal("109.01"))
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Cash Back Balance Inquiry

    @Test
    public void test_091_EbtBalanceInquiryVisaSwipeCash() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsEbtAuthorization response = ebtService.balanceInquiry()
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withInquiryType("CASH")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_092_EbtBalanceInquiryVisaManualCash() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.balanceInquiry()
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .withCardPresent(true)
                .withReaderPresent(true)
                .withInquiryType("CASH")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    // Bash Benefits Withdrawal

    @Test
    public void test_093_EbtBenefitWithDrawalVisaSwipe() throws HpsException {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=25120000000000000000?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");
        trackData.setEncryptionData(encryptionData);

        HpsEbtAuthorization response = ebtService.cashBackPurchase(new BigDecimal("110.01"))
                .withTrackData(trackData)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_094_EbtBenefitWithDrawalVisaManual() throws HpsException {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4012002000060016");
        card.setExpMonth(12);
        card.setExpYear(2025);

        HpsEbtAuthorization response = ebtService.cashBackPurchase(new BigDecimal("111.01"))
                .withCard(card)
                .withPinBlock("32539F50C245A6A93D123412324000AA")
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    /*
        HMS GIFT - REWARDS
        GIFT
        ACTIVATE
     */

    @Test
    public void test_095_ActivateGift1Swipe() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardResponse response = giftService.activate(new BigDecimal("6.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_096_ActivateGift2Manual() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.activate(new BigDecimal("7.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // ADD VALUE

    @Test
    public void test_097_AddValueGift1Swipe() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardResponse response = giftService.addValue(new BigDecimal("8.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_098_AddValueGift2Manual() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.activate(new BigDecimal("9.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // BALANCE INQUIRY

    @Test
    public void test_099_BalanceInquiryGift1Swipe() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardResponse response = giftService.balance()
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals(new BigDecimal("10.00"), response.getBalanceAmount());
    }

    @Test
    public void test_100_BalanceInquiryGift2Manual() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.balance()
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals(new BigDecimal("10.00"), response.getBalanceAmount());
    }

    // REPLACE / TRANSFER

    @Test
    public void test_101_ReplaceGift1Swipe() throws HpsException {
        HpsGiftCard oldCard = new HpsGiftCard();
        oldCard.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCard newCard = new HpsGiftCard();
        newCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.replace()
                .withOldCard(oldCard)
                .withNewCard(newCard)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_102_ReplaceGift2Manual() throws HpsException {
        HpsGiftCard newCard = new HpsGiftCard();
        newCard.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCard oldCard = new HpsGiftCard();
        oldCard.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.replace()
                .withOldCard(oldCard)
                .withNewCard(newCard)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // SALE / REDEEM

    @Test
    public void test_103_SaleGift1Swipe() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardSale response = giftService.sale(new BigDecimal("1.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_104_SaleGift2Manual() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000007");

        HpsGiftCardSale response = giftService.sale(new BigDecimal("2.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_105_SaleGift1VoidSwipe() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardSale response = giftService.sale(new BigDecimal("3.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        test105TransactionId = response.getTransactionID();
    }

    @Test
    public void test_106_SaleGift2ReversalManual() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000007");

        HpsGiftCardSale response = giftService.sale(new BigDecimal("4.00"))
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        test106TransactionId = response.getTransactionID();
    }

    // VOID

    @Test
    public void test_107_VoidGift() throws HpsException {
        HpsGiftCardResponse response = giftService.voidSale(test105TransactionId).execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // REVERSAL

    @Test
    public void test_108_ReversalGift() throws HpsException {
        HpsGiftCardResponse response = giftService.reverse(new BigDecimal("4.00"))
                .withTransactionId(test106TransactionId)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // DEACTIVATE

    @Test
    public void test_109_DeactivateGift1() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardResponse response = giftService.deactivate()
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // RECEIPTS MESSAGING

    @Test
    public void test_110_ReceiptsMessaging() {
        // PRINT AND SCAN RECEIPT FOR TEST 107
    }

    /*
        REWARDS
        BALANCE INQUIRY
     */

    @Test
    public void test_111_BalanceInquiryRewards1() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardResponse response = giftService.balance()
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals(new BigDecimal("0"), response.getPointsBalanceAmount());
    }

    @Test
    public void test_112_BalanceInquiryRewards2() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000007");

        HpsGiftCardResponse response = giftService.balance()
                .withCard(card)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals(new BigDecimal("0"), response.getPointsBalanceAmount());
    }

    // ALIAS

    @Test
    public void test_113_CreateAliasGift1() throws HpsException {
        HpsGiftCardAlias response = giftService.alias()
                .withAlias("9725550100")
                .withAction(GiftCardAliasAction.Create)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_114_CreateAliasGift2() throws HpsException {
        HpsGiftCardAlias response = giftService.alias()
                .withAlias("9725550100")
                .withAction(GiftCardAliasAction.Create)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_115_AddAliasGift1() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardAlias response = giftService.alias()
                .withCard(card)
                .withAlias("2145550199")
                .withAction(GiftCardAliasAction.Add)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_116_AddAliasGift2() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000007");

        HpsGiftCardAlias response = giftService.alias()
                .withCard(card)
                .withAlias("2145550199")
                .withAction(GiftCardAliasAction.Add)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_117_DeleteAliasGift1() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        HpsGiftCardAlias response = giftService.alias()
                .withCard(card)
                .withAlias("2145550199")
                .withAction(GiftCardAliasAction.Delete)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_999_CloseBatch() {
        try{
            HpsBatch response = this.batchService.closeBatch();
            if(response == null)
                fail("Response is null");
            System.out.println(String.format("Batch ID: %s", response.getId()));
            System.out.println(String.format("Sequence Number: %s", response.getSequenceNumber()));
        }
        catch(HpsException exc){
            fail(exc.getMessage());
        }
    }
}
