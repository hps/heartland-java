package com.hps.integrator.tests.terminals.pax;

import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.ConnectionModes;
import com.hps.integrator.terminals.ConnectionConfig;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.InitializeResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ConnectionTests {
    private PaxDevice device;

    @Test
    public void TcpConnection() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.TCP_IP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
        InitializeResponse response = device.initialize();
        assertNotNull(response);
        assertEquals("OK", response.getDeviceResponseText());

        device.dispose();
    }

    @Test
    public void HttpConnection() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.HTTP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
        InitializeResponse response = device.initialize();
        assertNotNull(response);
        assertEquals("OK", response.getDeviceResponseText());

        device.dispose();
    }
}
