package com.hps.integrator.tests.terminals.pax;

import com.hps.integrator.abstractions.IMessageSentInterface;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.ConnectionModes;
import com.hps.integrator.terminals.ConnectionConfig;
import com.hps.integrator.terminals.pax.PaxDevice;

import com.hps.integrator.terminals.pax.responses.BatchCloseResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BatchMessageTests {
    private PaxDevice device;

    public BatchMessageTests() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.HTTP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
    }

    @Test
    public void batchClose() throws HpsException {
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                assertNotNull(message);
                assertTrue(message.startsWith("[STX]B00[FS]1.35[FS]"));
            }
        });

        BatchCloseResponse response = device.batchClose();
        assertNotNull(response);
        assertEquals("OK", response.getDeviceResponseText());
        assertNotNull(response.getTotalCount());
        assertNotNull(response.getTotalAmount());
        assertNotNull(response.getTimeStamp());
        assertNotNull(response.getTid());
        assertNotNull(response.getMid());
    }
}
