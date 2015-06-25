package com.hps.integrator.infrastructure;

public class HpsArgumentException extends HpsException {
    public HpsArgumentException(String message) {
        super(message);
    }

    public HpsArgumentException(String message, Exception innerException) {
        super(message, innerException);
    }
}
