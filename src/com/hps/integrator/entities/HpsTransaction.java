package com.hps.integrator.entities;

/**
 * The HPS transaction.
 */
public class HpsTransaction {
    private HpsTransactionHeader mHeader;
    private int mTransactionID;
    private String mResponseCode;
    private String mResponseText;
    private String mReferenceNumber;

    public HpsTransaction() {}

    public HpsTransaction(HpsTransactionHeader header) {
        mHeader = header;
    }

    protected HpsTransactionHeader getHeader() {
        return mHeader;
    }

    protected void setHeader(HpsTransactionHeader header) {
        mHeader = header;
    }

    public int getTransactionID() {
        return mTransactionID;
    }

    public void setTransactionID(int transactionID) {
        this.mTransactionID = transactionID;
    }

    public String getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(String responseCode) {
        this.mResponseCode = responseCode;
    }

    public String getResponseText() {
        return mResponseText;
    }

    public void setResponseText(String responseText) {
        this.mResponseText = responseText;
    }

    public String getReferenceNumber() {
        return mReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.mReferenceNumber = referenceNumber;
    }

    public static String transactionTypeToServiceName(HpsTransactionType transactionType) {
        switch (transactionType) {
            case Authorize:
                return "CreditAuth";
            case Capture:
                return "CreditAddToBatch";
            case Charge:
                return "CreditSale";
            case Refund:
                return "CreditReturn";
            case Reverse:
                return "CreditReversal";
            case Verify:
                return "CreditAccountVerify";
            case List:
                return "ReportActivity";
            case Get:
                return "ReportTxnDetail";
            case Void:
                return "CreditVoid";
            case BatchClose:
                return "BatchClose";
            case SecurityError:
                return "SecurityError";
            default:
                return "";
        }
    }

    public static HpsTransactionType serviceNameToTransactionType(String serviceName) {
        if (serviceName.equals("CreditAddToBatch")) {
            return HpsTransactionType.Capture;
        } else if (serviceName.equals("CreditSale")) {
            return HpsTransactionType.Charge;
        } else if (serviceName.equals("CreditReturn")) {
            return HpsTransactionType.Refund;
        } else if (serviceName.equals("CreditReversal")) {
            return HpsTransactionType.Reverse;
        } else if (serviceName.equals("CreditAuth")) {
            return HpsTransactionType.Authorize;
        } else if (serviceName.equals("CreditAccountVerify")) {
            return HpsTransactionType.Verify;
        } else if (serviceName.equals("ReportActivity")) {
            return HpsTransactionType.List;
        } else if (serviceName.equals("ReportTxnDetail")) {
            return HpsTransactionType.Get;
        } else if (serviceName.equals("CreditVoid")) {
            return HpsTransactionType.Void;
        } else if (serviceName.equals("BatchClose")) {
            return HpsTransactionType.BatchClose;
        } else if (serviceName.equals("SecurityError")) {
            return HpsTransactionType.SecurityError;
        } else {
            return null;
        }
    }
}