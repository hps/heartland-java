package com.hps.integrator.tests.terminals.pax;

import com.hps.integrator.abstractions.IMessageSentInterface;
import com.hps.integrator.infrastructure.HpsArgumentException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.ConnectionModes;
import com.hps.integrator.terminals.ConnectionConfig;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.DebitResponse;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DebitTests {
    private PaxDevice device;
    private String rec_message;


    public DebitTests() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.HTTP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
    }

    @Test
    public void sale() throws HpsException {
        rec_message = "[STX]T02[FS]1.35[FS]01[FS]1000[FS][US][US][US][US][US]1[FS]5[FS][FS][ETX]";
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith(rec_message));
            }
        });

        DebitResponse response = device.debitSale(5, new BigDecimal(10))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test(expected = HpsArgumentException.class)
    public void DebitSaleBlankAmount() throws HpsException {
        device.debitSale(5)
                .withAllowDuplicates(true)
                .execute();
    }

    @Test
    public void DebitSale33PartialAuth22() throws HpsException {

        rec_message = "[STX]T02[FS]1.35[FS]01[FS]3300[FS][US][US][US][US][US]1[FS]5[FS][FS][ETX]";
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith(rec_message));
            }
        });

        DebitResponse response = device.debitSale(5, new BigDecimal(33))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals("10", response.getResponseCode());
        assertEquals(new BigDecimal(22), response.getTransactionAmount());
        assertEquals(new BigDecimal(11), response.getAmountDue());
    }

    @Test
    public void DebitReturn() throws HpsException {

        rec_message = "[STX]T02[FS]1.35[FS]02[FS]1000[FS][FS]6[FS][FS][ETX]";
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith(rec_message));
            }
        });

        DebitResponse response = device.debitReturn(6, new BigDecimal(10))
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void DebitReturn10ByTransactionId() throws HpsException {
        DebitResponse response = device.debitSale(5, new BigDecimal(10))
                .withAllowDuplicates(true)
                .execute();

        rec_message = String.format("[STX]T02[FS]1.35[FS]02[FS]1000[FS][FS]6[FS][FS]HREF=%s[ETX]", response.getTransactionId());
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith(rec_message));
            }
        });

        DebitResponse response2 = device.debitReturn(6, new BigDecimal(10))
                .withTransactionId(response.getTransactionId())
                .execute();
        assertNotNull(response2);
        assertEquals("00", response2.getResponseCode());
    }


    @Test(expected = HpsArgumentException.class)
    public void DebitReturnBlankAmount() throws HpsException {
        device.debitReturn(5)
                .execute();
    }
}
