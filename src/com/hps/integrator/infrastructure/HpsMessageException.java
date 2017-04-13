package com.hps.integrator.infrastructure;

public class HpsMessageException extends HpsException {
    public HpsMessageException(String message) {
        super(message);
    }
    public HpsMessageException(String message, Exception innerException) {
        super(message, innerException);
    }
}