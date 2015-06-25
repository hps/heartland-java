package com.hps.integrator.fluent;

public class HpsBuilderValidation {
    private String callback;
    private String exceptionMessage;

    public String getCallback() {
        return callback;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public HpsBuilderValidation(String callback, String exceptionMessage) {
        this.callback = callback;
        this.exceptionMessage = exceptionMessage;
    }
}
