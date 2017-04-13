package com.hps.integrator.terminals;

import com.hps.integrator.abstractions.ITerminalConfiguration;
import com.hps.integrator.infrastructure.HpsConfigurationException;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.emums.*;

public class ConnectionConfig implements ITerminalConfiguration {
    ConnectionModes connectionMode;
    BaudRate baudRate;
    Parity parity;
    StopBits stopBits;
    DataBits dataBits;
    int timeout;
    String ipAddress;
    int port;

    public ConnectionModes getConnectionMode() {
        return connectionMode;
    }
    public void setConnectionMode(ConnectionModes connectionModes) {
        this.connectionMode = connectionModes;
    }
    public BaudRate getBaudRate() {
        return baudRate;
    }
    public void setBaudRate(BaudRate baudRate) {
        this.baudRate = baudRate;
    }
    public Parity getParity() {
        return parity;
    }
    public void setParity(Parity parity) {
        this.parity = parity;
    }
    public StopBits getStopBits() {
        return stopBits;
    }
    public void setStopBits(StopBits stopBits) {
        this.stopBits = stopBits;
    }
    public DataBits getDataBits() {
        return dataBits;
    }
    public void setDataBits(DataBits dataBits) {
        this.dataBits = dataBits;
    }
    public int getTimeout() {
        return timeout;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public ConnectionConfig(){
        setTimeout(1000);
    }

    public void validate() throws HpsConfigurationException {
        if(connectionMode == ConnectionModes.TCP_IP || connectionMode == ConnectionModes.HTTP) {
            if(HpsStringUtils.isNullOrEmpty(ipAddress))
                throw new HpsConfigurationException("IpAddress is required for TCP or HTTP communication modes.");
            if(port == 0)
                throw new HpsConfigurationException("Port is required for TCP or HTTP communication modes.");
        }
    }
}