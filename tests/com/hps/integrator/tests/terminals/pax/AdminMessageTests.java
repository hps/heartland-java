package com.hps.integrator.tests.terminals.pax;

import com.hps.integrator.abstractions.IMessageSentInterface;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsMessageException;
import com.hps.integrator.infrastructure.emums.ConnectionModes;
import com.hps.integrator.terminals.ConnectionConfig;
import com.hps.integrator.terminals.pax.PaxDevice;

import com.hps.integrator.terminals.pax.responses.InitializeResponse;
import com.hps.integrator.terminals.pax.responses.PaxDeviceResponse;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AdminMessageTests {
    PaxDevice device;

    public AdminMessageTests() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.HTTP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
    }

    @Test
    public void initialize() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]A00[FS]1.35[FS][ETX]"));
            }
        });

        InitializeResponse response = device.initialize();
        assertNotNull(response);
        assertEquals("OK", response.getDeviceResponseText());
        assertNotNull(response.getSerialNumber());
    }

    @Test(expected = HpsMessageException.class)
    public void cancel() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]A14[FS]1.35[FS][ETX]"));
            }
        });

        device.cancel();
    }

    @Test
    public void reset() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]A16[FS]1.35[FS][ETX]"));
            }
        });

        PaxDeviceResponse response = device.reset();
        assertNotNull(response);
        assertEquals("OK", response.getDeviceResponseText());
    }

    @Test @Ignore
    public void reboot() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]A26[FS]1.35[FS][ETX]"));
            }
        });

        PaxDeviceResponse response = device.reboot();
        assertNotNull(response);
        assertEquals("OK", response.getDeviceResponseText());
    }
}
