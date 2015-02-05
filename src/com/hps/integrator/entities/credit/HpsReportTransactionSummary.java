package com.hps.integrator.entities.credit;

import com.hps.integrator.abstractions.IHpsReportTransaction;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.entities.HpsTransactionType;

import java.util.Date;

public class HpsReportTransactionSummary extends HpsTransaction implements
        IHpsReportTransaction {

    private float amount;
    private int originalTransactionId;
    private String maskedCardNumber;
    private HpsTransactionType transactionType;
    private Date transactionDate;
    private HpsCreditExceptions exceptions;

    public HpsReportTransactionSummary() {}

    public HpsReportTransactionSummary(HpsTransactionHeader header) {
        super(header);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getOriginalTransactionId() {
        return originalTransactionId;
    }

    public void setOriginalTransactionId(int originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public void setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    public HpsTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(HpsTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public HpsCreditExceptions getExceptions() {
        return exceptions;
    }

    public void setExceptions(HpsCreditExceptions exceptions) {
        this.exceptions = exceptions;
    }
}