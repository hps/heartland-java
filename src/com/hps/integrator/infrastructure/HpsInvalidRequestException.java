package com.hps.integrator.infrastructure;

public class HpsInvalidRequestException extends HpsException {
    private HpsExceptionCodes code;
    private String paramName;

    public HpsInvalidRequestException(String message) {
        super(message);
    }

    public HpsInvalidRequestException(HpsExceptionCodes code, String message) {
        super(message);
        this.setCode(code);
    }

    public HpsInvalidRequestException(HpsExceptionCodes code, String message, String paramName) {
        super(message);
        this.setCode(code);
        this.setParamName(paramName);
    }

    public HpsExceptionCodes getCode() {
        return code;
    }

    public void setCode(HpsExceptionCodes code) {
        this.code = code;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
