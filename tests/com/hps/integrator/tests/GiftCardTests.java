package com.hps.integrator.tests;

import com.hps.integrator.entities.gift.*;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsGiftCardService;
import com.hps.integrator.tests.testdata.TestGiftCards;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GiftCardTests {

    @Test
    public void GiftCard_ManualCard_ShouldActivate() throws HpsException {
        BigDecimal amount = new BigDecimal("100.00");
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardResponse rsp = service.activate(amount, "usd", TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldAddValue() throws HpsException {
        BigDecimal amount = new BigDecimal("100.00");
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardResponse rsp = service.addValue(amount, "usd", TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
    }

//    @Test
//    public void GiftCard_ManualCard_ShouldAlias() throws HpsException {
//        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
//        HpsGiftCardAlias rsp = service.alias(HpsGiftCardAliasAction.Add, TestGiftCards.Manual.validGiftCard(), "1234567890");
//        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
//    }

    @Test
    public void GiftCard_ManualCard_ShouldBalance() throws HpsException {
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardResponse rsp = service.cardBalance(TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldDeactivate() throws HpsException {
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardResponse rsp = service.deactivateCard(TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldReplace() throws HpsException {
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardResponse rsp = service.replaceCard(TestGiftCards.Manual.validGiftCard(), TestGiftCards.Manual.validGiftCard2());
        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldReward() throws HpsException {
        BigDecimal amount = new BigDecimal("100.00");
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardResponse rsp = service.reward(amount, "usd", TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldSale() throws HpsException {
        BigDecimal amount = new BigDecimal("10.00");
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardSale rsp = service.sale(amount, "usd", TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", rsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldVoid() throws HpsException {
        BigDecimal amount = new BigDecimal("10.00");
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardSale saleRsp = service.sale(amount, "usd", TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", saleRsp.getResponseCode(), "0");

        HpsGiftCardResponse voidRsp = service.voidTxn(saleRsp.getTransactionID());
        assertEquals("Response code should be '0'", voidRsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldReverseUsingTxnID() throws HpsException {
        BigDecimal amount = new BigDecimal("10.00");
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardSale saleRsp = service.sale(amount, "usd", TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", saleRsp.getResponseCode(), "0");

        HpsGiftCardResponse reverseRsp = service.reverse(saleRsp.getTransactionID(), amount, "usd");
        assertEquals("Response code should be '0'", reverseRsp.getResponseCode(), "0");
    }

    @Test
    public void GiftCard_ManualCard_ShouldReverseUsingGiftCard() throws HpsException {
        BigDecimal amount = new BigDecimal("10.00");
        HpsGiftCardService service = new HpsGiftCardService(TestServicesConfig.validServicesConfig());
        HpsGiftCardSale saleRsp = service.sale(amount, "usd", TestGiftCards.Manual.validGiftCard());
        assertEquals("Response code should be '0'", saleRsp.getResponseCode(), "0");

        HpsGiftCardResponse reverseRsp = service.reverse(TestGiftCards.Manual.validGiftCard(), amount, "usd");
        assertEquals("Response code should be '0'", reverseRsp.getResponseCode(), "0");
    }
}
