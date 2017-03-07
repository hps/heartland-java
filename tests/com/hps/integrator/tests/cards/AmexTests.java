package com.hps.integrator.tests.cards;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.credit.HpsAccountVerify;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.entities.credit.HpsManageToken;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsIssuerException;
import com.hps.integrator.infrastructure.HpsIssuerExceptionCodes;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.tests.testdata.TestCardHolders;
import com.hps.integrator.tests.testdata.TestCreditCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AmexTests {

    @Test
    public void Amex_WhenCardIsOk_ShouldReturnValidResult() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("50"));
        assertEquals("00", response.getResponseCode());
    }

    // avs tests

    @Test
    public void Amex_AvsResultCode_ShouldEqualA() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.01"));
        assertEquals("A", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualN() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.02"));
        assertEquals("N", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualR() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.03"));
        assertEquals("R", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualS() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.04"));
        assertEquals("S", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualU() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.05"));
        assertEquals("U", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualW() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.06"));
        assertEquals("W", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualX() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.07"));
        assertEquals("X", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualY() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.08"));
        assertEquals("Y", response.getAvsResultCode());
    }

    @Test
    public void Amex_AvsResultCode_ShouldEqualZ() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("90.09"));
        assertEquals("Z", response.getAvsResultCode());
    }

    // cvv tests

    @Test
    public void Amex_CvvResultCode_ShouldEqualM() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("97.01"));
        assertEquals("M", response.getCvvResultCode());
    }

    @Test
    public void Amex_CvvResultCode_ShouldEqualN() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("97.02"));
        assertEquals("N", response.getCvvResultCode());
    }

    @Test
    public void Amex_CvvResultCode_ShouldEqualP() throws HpsException {
        HpsCharge response = this.chargeValidAmex(new BigDecimal("97.03"));
        assertEquals("P", response.getCvvResultCode());
    }

    // amex to visa 2nd

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateDenied() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.08"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
            assertEquals("51", e.getDetails().getIssuerResponseCode());
            assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateCardExpired() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.32"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.ExpiredCard, e.getCode());
            assertEquals("54", e.getDetails().getIssuerResponseCode());
            assertEquals("EXPIRED CARD", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicatePleaseCall() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.34"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
            assertEquals("02", e.getDetails().getIssuerResponseCode());
            assertEquals("CALL", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateInvalidMerchant() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.22"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
            assertEquals("03", e.getDetails().getIssuerResponseCode());
            assertEquals("TERM ID ERROR", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateInvalidAmount() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.27"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.InvalidAmount, e.getCode());
            assertEquals("13", e.getDetails().getIssuerResponseCode());
            assertEquals("AMOUNT ERROR", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    // BUG: Implementation was recently changed in cert for this test
    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateNoActionTaken() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.14"));
        } catch (HpsIssuerException e) {
            //assertEquals("processing_error, e.getCode());
            //assertEquals("76", e.getDetails().getIssuerResponseCode());
            //assertEquals("NO ACTION TAKEN", e.getDetails().getIssuerResponseText());
            assertEquals(HpsIssuerExceptionCodes.IncorrectNumber, e.getCode());
            assertEquals("14", e.getDetails().getIssuerResponseCode());
            assertEquals("CARD NO. ERROR", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateInvalidCvv2() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.23"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.IncorrectCvc, e.getCode());
            assertEquals("N7", e.getDetails().getIssuerResponseCode());
            assertEquals("CVV2 MISMATCH", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateInvalidOriginator() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.30"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
            assertEquals("58", e.getDetails().getIssuerResponseCode());
            assertEquals("SERV NOT ALLOWED", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateCardDeclined() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.25"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
            assertEquals("05", e.getDetails().getIssuerResponseCode());
            assertEquals("DECLINE", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateAccountCancelled() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.13"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
            assertEquals("78", e.getDetails().getIssuerResponseCode());
            assertEquals("NO ACCOUNT", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicateMerchantClose() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.12"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.ProcessingError, e.getCode());
            assertEquals("06", e.getDetails().getIssuerResponseCode());
            assertEquals("ERROR", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    @Test(expected = HpsIssuerException.class)
    public void Amex_ResponseCode_ShouldIndicatePickUpCard() throws HpsException {
        try {
            this.chargeValidAmex(new BigDecimal("10.04"));
        } catch (HpsIssuerException e) {
            assertEquals(HpsIssuerExceptionCodes.CardDeclined, e.getCode());
            assertEquals("44", e.getDetails().getIssuerResponseCode());
            assertEquals("HOLD-CALL", e.getDetails().getIssuerResponseText());
            throw e;
        }
    }

    // Verify, Authorize, Capture & Void

    @Test
    public void Amex_Verify_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validAmex(), TestCardHolders.validCardHolder(), true, false, false);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void Amex_VerifyWithToken_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAccountVerify response = service.verify(TestCreditCards.validAmex(), TestCardHolders.validCardHolder(), true, false, false);
        response = service.verify(response.getTokenData().getTokenValue(), TestCardHolders.validCardHolder());

        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void Amex_Authorize_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(), true);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void Amex_AuthorizeAndRequestToken_ShouldGetTokenAndReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig(), true);
        HpsAuthorization response = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(),
                TestCardHolders.validCardHolder(), true, true, null, null, false, false, false);
        assertEquals(0, response.getTokenData().getTokenRspCode());
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void Amex_Capture_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAuthorization auth = service.authorize(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(), true);
        assertEquals("00", auth.getResponseCode());

        HpsTransaction capture = service.captureTxn(auth.getTransactionID());
        assertEquals("00", capture.getResponseCode());
    }

    @Test
    public void Amex_Void_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsAuthorization charge = service.charge(new BigDecimal("50"), "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(), true);
        assertEquals("00", charge.getResponseCode());

        HpsTransaction voidResult = service.voidTxn(charge.getTransactionID());
        assertEquals("00", voidResult.getResponseCode());
    }

    @Test
    public void Amex_UpdateTokenExpiration_ShouldReturnOk() throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validCertMultiUseConfig());
        HpsManageToken manage = service.updateTokenExpiration(TestCreditCards.validAmexMUT(), 1, 2019);
        assertEquals("00", manage.getResponseCode());
    }

    private HpsCharge chargeValidAmex(BigDecimal amt) throws HpsException {
        HpsCreditService service = new HpsCreditService(TestServicesConfig.validServicesConfig());
        HpsCharge charge = service.charge(amt, "usd", TestCreditCards.validAmex(), TestCardHolders.validCardHolder(), true);
        assertNotNull(charge);
        return charge;
    }
}
