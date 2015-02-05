package com.hps.integrator.infrastructure;

import com.hps.integrator.entities.check.HpsCheckResponseDetails;

import java.util.List;

public class HpsCheckException extends Exception {
    private int transactionId;
    private int code;
    private List<HpsCheckResponseDetails> details;

    public HpsCheckException(int transactionId, List<HpsCheckResponseDetails> details, int code, String message) {
        super(message);
        setTransactionId(transactionId);
        setDetails(details);
        setCode(code);
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<HpsCheckResponseDetails> getDetails() {
        return details;
    }

    public void setDetails(List<HpsCheckResponseDetails> details) {
        this.details = details;
    }
}
