package com.hps.integrator.entities;

/**
 * The transaction details.
 */
public class HpsTransactionDetails {

    private String memo;
    private String invoiceNumber;
    private String customerId;

    /**
     * Constructor
     * @param memo A free-form field (for merchant reporting/record-keeping purposes only).
     * @param invoiceNumber This will not be used at settlement. (for Merchant reporting/record-keeping purposes only).
     * @param customerId This is intended to be the customer identification. (for Merchant reporting/record-keeping
     *                   purposes only).
     */
    public HpsTransactionDetails(String memo, String invoiceNumber, String customerId) {
        this.memo = memo;
        this.invoiceNumber = invoiceNumber;
        this.customerId = customerId;
    }

    public String getMemo() {
        return memo;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getCustomerId() {
        return customerId;
    }
}
