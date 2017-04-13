package com.hps.integrator.infrastructure;

public class HpsConfigurationException extends HpsException {
    public HpsConfigurationException(String message) {
        super(message);
    }
    public HpsConfigurationException(String message, Exception innerException) {
        super(message, innerException);
    }
}
