package com.hps.integrator.infrastructure;

public class HpsAuthenticationException extends HpsException {
    private HpsExceptionCodes code;

	public HpsAuthenticationException(HpsExceptionCodes code, String message)
	{
        super(message);
        this.setCode(code);
	}

    public HpsExceptionCodes getCode() {
        return code;
    }

    public void setCode(HpsExceptionCodes code) {
        this.code = code;
    }
}
