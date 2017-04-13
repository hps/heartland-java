package com.hps.integrator.abstractions;

import com.hps.integrator.infrastructure.HpsConfigurationException;
import com.hps.integrator.infrastructure.emums.*;

public interface ITerminalConfiguration {
    ConnectionModes getConnectionMode();
    void setConnectionMode(ConnectionModes connectionMode);
    String getIpAddress();
    void setIpAddress(String ipAddress);
    int getPort();
    void setPort(int port);
    BaudRate getBaudRate();
    void setBaudRate(BaudRate baudRate);
    Parity getParity();
    void setParity(Parity parity);
    StopBits getStopBits();
    void setStopBits(StopBits stopBits);
    DataBits getDataBits();
    void setDataBits(DataBits dataBits);
    int getTimeout();
    void setTimeout(int timeout);
    void validate() throws HpsConfigurationException;
}
