package com.hps.integrator.entities.credit;

import com.hps.integrator.abstractions.IHpsReportTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.entities.HpsTransactionType;

import java.util.Date;

public class HpsReportTransactionDetails extends HpsAuthorization implements
        IHpsReportTransaction {

    private int originalTransactionId;
    private String maskedCardNumber;
    private HpsTransactionType transactionType;
    private Date transactionDate;
    private String descriptor;
    private String memo;
    private String invoiceNumber;
    private String customerId;
    private HpsCreditExceptions exceptions;

    public HpsReportTransactionDetails(HpsTransactionHeader header) {
        super(header);
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

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}