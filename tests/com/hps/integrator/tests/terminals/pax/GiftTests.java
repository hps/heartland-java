package com.hps.integrator.tests.terminals.pax;

import com.hps.integrator.abstractions.IMessageSentInterface;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.infrastructure.HpsArgumentException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.ConnectionModes;
import com.hps.integrator.infrastructure.emums.CurrencyType;
import com.hps.integrator.terminals.ConnectionConfig;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.GiftResponse;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class GiftTests {
    private PaxDevice device;

    public GiftTests() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.HTTP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
    }

    //<editor-fold desc="GiftSale">
    @Test
    public void sale() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]01[FS]1000[FS][FS]1[FS][FS][ETX]"));
            }
        });

        GiftResponse response = device.giftSale(1, new BigDecimal(10))
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void saleManual() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]01[FS]1000[FS]5022440000000000098[FS]2[FS][FS][ETX]"));
            }
        });
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");

        GiftResponse response = device.giftSale(2, new BigDecimal(10))
                .withCard(card)
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void saleManualWithInvoice() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]01[FS]1000[FS]5022440000000000098[FS]4[US]1234[FS][FS][ETX]"));
            }
        });
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");
        HpsTransactionDetails details = new HpsTransactionDetails(null, "1234", null);

        GiftResponse response = device.giftSale(4, new BigDecimal(10))
                .withCard(card)
                .withDetails(details)
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void saleManualWithLoyalty() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T08[FS]1.35[FS]01[FS]1000[FS]5022440000000000098[FS]5[FS][FS][ETX]"));
            }
        });
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");

        GiftResponse response = device.giftSale(5, new BigDecimal(10))
                .withCard(card)
                .withCurrency(CurrencyType.POINTS)
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void saleNoAmount() throws HpsException {
        device.giftSale(6)
                .withAmount(null)
                .execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void saleNoCurrency() throws HpsException {
        device.giftSale(7, new BigDecimal(10))
                .withCurrency(null)
                .execute();
    }
    //</editor-fold>

    //<editor-fold desc="GiftAddValue">
    @Test
    public void addValueManual() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");

        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]10[FS]1000[FS]5022440000000000098[FS]8[FS][FS][ETX]"));
            }
        });

        GiftResponse response = device.giftAddValue(8)
                .withCard(card)
                .withAmount(new BigDecimal(10))
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void addValueWithSwipe() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]10[FS]1000[FS][FS]9[FS][FS][ETX]"));
            }
        });

        GiftResponse response = device.giftAddValue(9)
                .withAmount(new BigDecimal(10))
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void addValueManualLoyalty() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T08[FS]1.35[FS]10[FS]1000[FS]5022440000000000098[FS]10[FS][FS][ETX]"));
            }
        });
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");

        GiftResponse response = device.giftAddValue(10)
                .withCard(card)
                .withAmount(new BigDecimal(10))
                .withCurrency(CurrencyType.POINTS)
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void addValueWithoutAmountThrowsException() throws HpsException {
        device.giftAddValue(11)
                .withAmount(null)
                .execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void addValueWithoutCurrencyThrowsException() throws HpsException {
        device.giftAddValue(12)
                .withCurrency(null)
                .execute();
    }
    //</editor-fold>

    //<editor-fold desc="GiftVoid">
    @Test
    public void voidManual() throws HpsException {
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");

        final GiftResponse saleResponse = device.giftSale(13, new BigDecimal(10)).withCard(card).execute();

        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]16[FS][FS][FS]14[FS][FS]HREF="+saleResponse.getTransactionId()+"[ETX]"));
            }
        });

        GiftResponse voidResponse = device.giftVoid(14)
                .withTransactionId(saleResponse.getTransactionId())
                .execute();

        assertNotNull(voidResponse);
        assertEquals("0", voidResponse.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void voidNoCurrency() throws HpsException {
        device.giftVoid(15)
                .withCurrency(null)
                .withTransactionId(1)
                .execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void voidNoTransactionId() throws HpsException {
        device.giftVoid(16)
                .withTransactionId(0)
                .withCurrency(CurrencyType.USD)
                .execute();
    }
    //</editor-fold>

    //<editor-fold desc="GiftBalance">
    @Test
    public void balance() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]23[FS][FS][FS]17[FS][FS][ETX]"));
            }
        });

        GiftResponse response = device.giftBalance(17)
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void balanceManual() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T06[FS]1.35[FS]23[FS][FS]5022440000000000098[FS]18[FS][FS][ETX]"));
            }
        });
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");

        GiftResponse response = device.giftBalance(18)
                .withCard(card)
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void balanceLoyaltyManual() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T08[FS]1.35[FS]23[FS][FS]5022440000000000098[FS]19[FS][FS][ETX]"));
            }
        });
        HpsGiftCard card = new HpsGiftCard();
        card.setCardNumber("5022440000000000098");

        GiftResponse response = device.giftBalance(19)
                .withCard(card)
                .withCurrency(CurrencyType.POINTS)
                .execute();

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void BalanceNoCurrency() throws HpsException {
        device.giftBalance(20)
                .withCurrency(null)
                .execute();
    }
    //</editor-fold>
}
