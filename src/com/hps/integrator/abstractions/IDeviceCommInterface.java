package com.hps.integrator.abstractions;

import com.hps.integrator.infrastructure.HpsException;

public interface IDeviceCommInterface {
    void connect();
    void disconnect();
    byte[] send(IDeviceMessage message) throws HpsException;
    void setMessageSentHandler(IMessageSentInterface messageInterface);
}
