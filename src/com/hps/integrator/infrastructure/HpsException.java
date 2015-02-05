package com.hps.integrator.infrastructure;

public class HpsException extends Exception {

    public HpsException(String message) {
        super(message);
    }

    public HpsException(String message, Exception innerException) {
        super(message, innerException);
    }
}
