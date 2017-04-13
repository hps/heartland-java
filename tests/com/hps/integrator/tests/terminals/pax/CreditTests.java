package com.hps.integrator.tests.terminals.pax;

import com.hps.integrator.abstractions.IMessageSentInterface;
import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.infrastructure.HpsArgumentException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.ConnectionModes;
import com.hps.integrator.terminals.ConnectionConfig;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.CreditResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CreditTests {
    private PaxDevice device;
    private String rec_message;

    public CreditTests() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.HTTP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
    }

    @Test
    public void sale() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T00[FS]1.35[FS]01[FS]1000[FS][US][US][US][US][US]1[FS]1[FS][FS][FS][FS][FS][ETX]"));
            }
        });

        CreditResponse response = device.creditSale(1, new BigDecimal(10))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void saleManual() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T00[FS]1.35[FS]01[FS]1100[FS]4005554444444460[US]1217[US]123[US][US][US]1[FS]1[FS]95124[US]1 Heartland Way[FS][FS][FS][FS][ETX]"));
            }
        });

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4005554444444460");
        card.setExpMonth(12);
        card.setExpYear(17);
        card.setCvv("123");

        HpsAddress address = new HpsAddress();
        address.setAddress("1 Heartland Way");
        address.setZip("95124");

        CreditResponse response = device.creditSale(1, new BigDecimal(11))
                .withAllowDuplicates(true)
                .withCard(card)
                .withAddress(address)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void saleNoAmount() throws HpsException {
        device.creditSale(1).execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void saleWithMultiplePayments() throws HpsException {
        device.creditSale(1)
                .withCard(new HpsCreditCard())
                .withToken("123456789")
                .execute();
    }

    @Test
    public void authCapture() throws HpsException {
        rec_message = "[STX]T00[FS]1.35[FS]03[FS]1200[FS][US][US][US][US][US]1[FS]1[FS][FS][FS][FS][FS][ETX]";
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith(rec_message));
            }
        });

        CreditResponse response = device.creditAuth(1, new BigDecimal("12"))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());

        rec_message = String.format("[STX]T00[FS]1.35[FS]04[FS]1200[FS][FS]2[FS][FS][FS][FS][FS]HREF=%s[ETX]", response.getTransactionId());
        CreditResponse captureResponse = device.creditCapture(2, new BigDecimal("12"))
                .withTransactionId(response.getTransactionId())
                .execute();
        assertNotNull(captureResponse);
        assertEquals("00", captureResponse.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void authNoAmount() throws HpsException {
        device.creditAuth(1).execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void authWithMultiplePayments() throws HpsException {
        device.creditAuth(1)
                .withCard(new HpsCreditCard())
                .withToken("123456789")
                .execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void captureNoTransactionId() throws HpsException {
        device.creditCapture(1).execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void authNoAuthCode() throws HpsException {
        device.creditAuth(1, new BigDecimal(13))
                .withTransactionId(1234567)
                .execute();
    }

    @Test
    public void returnByTransactionId() throws HpsException {
        rec_message = "[STX]T00[FS]1.35[FS]01[FS]1600[FS]4005554444444460[US]1217[US]123[US][US][US]1[FS]1[FS]95124[US]1 Heartland Way[FS][FS][FS][FS]TOKENREQUEST=1[ETX]";
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith(rec_message));
            }
        });

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4005554444444460");
        card.setExpMonth(12);
        card.setExpYear(17);
        card.setCvv("123");

        HpsAddress address = new HpsAddress();
        address.setAddress("1 Heartland Way");
        address.setZip("95124");

        CreditResponse saleResponse = device.creditSale(1, new BigDecimal(16))
                .withCard(card)
                .withAddress(address)
                .withAllowDuplicates(true)
                .withRequestMultiUseToken(true)
                .execute();
        assertNotNull(saleResponse);
        assertEquals("00", saleResponse.getResponseCode());

        rec_message = String.format("[STX]T00[FS]1.35[FS]02[FS]1600[FS][FS]2[FS][FS][FS][FS][FS]HREF=%s[ETX]", saleResponse.getTransactionId());
        CreditResponse returnResponse = device.creditReturn(2, new BigDecimal(16))
                .withTransactionId(saleResponse.getTransactionId())
                .execute();
        assertNotNull(returnResponse);
        assertEquals("00", returnResponse.getResponseCode());
    }

    @Test
    public void returnByCard() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T00[FS]1.35[FS]02[FS]1400[FS]4005554444444460[US]1217[FS]2[FS][FS][FS][FS][FS][ETX]"));
            }
        });

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4005554444444460");
        card.setExpMonth(12);
        card.setExpYear(17);
        card.setCvv("123");

        CreditResponse returnResponse = device.creditReturn(2, new BigDecimal(14))
                .withCard(card)
                .execute();
        assertNotNull(returnResponse);
        assertEquals("00", returnResponse.getResponseCode());
    }

    @Test
    public void returnByToken() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T00[FS]1.35[FS]02[FS]1400[FS][FS]2[FS][FS][FS][FS][FS]TOKEN=GLl8b708JHBbLdMfHf6H4460[ETX]"));
            }
        });

        String token = "GLl8b708JHBbLdMfHf6H4460";
        CreditResponse returnResponse = device.creditReturn(2, new BigDecimal(14))
                .withToken(token)
                .execute();
        assertNotNull(returnResponse);
        assertEquals("00", returnResponse.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void returnNoAmount() throws HpsException {
        device.creditReturn(1).execute();
    }

    @Test(expected = HpsArgumentException.class)
    public void returnWithMultiplePayments() throws HpsException {
        device.creditReturn(1)
                .withCard(new HpsCreditCard())
                .withToken("123456789")
                .execute();
    }

    @Test
    public void verify() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T00[FS]1.35[FS]24[FS][FS][FS]1[FS][FS][FS][FS][FS][ETX]"));
            }
        });

        CreditResponse response = device.creditVerify(1).execute();
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void verifyManual() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T00[FS]1.35[FS]24[FS][FS]4005554444444460[US]1217[FS]1[FS]95124[US]1 Heartland Way[FS][FS][FS][FS][ETX]"));
            }
        });

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4005554444444460");
        card.setExpMonth(12);
        card.setExpYear(17);
        card.setCvv("123");

        HpsAddress address = new HpsAddress();
        address.setAddress("1 Heartland Way");
        address.setZip("95124");

        CreditResponse response = device.creditVerify(1)
                .withCard(card)
                .withAddress(address)
                .execute();
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
    }

    @Test
    public void tokenize() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]T00[FS]1.35[FS]32[FS][FS][FS]1[FS][FS][FS][FS][FS][ETX]"));
            }
        });

        CreditResponse response = device.creditVerify(1)
                .withRequestMultiUseToken(true)
                .execute();
        assertNotNull(response);
        assertEquals("85", response.getResponseCode());
        assertNotNull(response.getToken());
    }

    @Test
    public void creditVoid() throws HpsException {
        rec_message = "[STX]T00[FS]1.35[FS]01[FS]1600[FS]4005554444444460[US]1217[US]123[US][US][US]1[FS]1[FS]95124[US]1 Heartland Way[FS][FS][FS][FS]TOKENREQUEST=1[ETX]";
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith(rec_message));
            }
        });

        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("4005554444444460");
        card.setExpMonth(12);
        card.setExpYear(17);
        card.setCvv("123");

        HpsAddress address = new HpsAddress();
        address.setAddress("1 Heartland Way");
        address.setZip("95124");

        CreditResponse saleResponse = device.creditSale(1, new BigDecimal(16))
                .withCard(card)
                .withAddress(address)
                .withAllowDuplicates(true)
                .withRequestMultiUseToken(true)
                .execute();
        assertNotNull(saleResponse);
        assertEquals("00", saleResponse.getResponseCode());

        rec_message = String.format("[STX]T00[FS]1.35[FS]16[FS][FS][FS]2[FS][FS][FS][FS][FS]HREF=%s[ETX]", saleResponse.getTransactionId());
        CreditResponse returnResponse = device.creditVoid(2)
                .withTransactionId(saleResponse.getTransactionId())
                .execute();
        assertNotNull(returnResponse);
        assertEquals("00", returnResponse.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void voidNoTransactionId() throws HpsException {
        device.creditVoid(1).execute();
    }
}
