package com.hps.integrator.applepay.ecv1;

public class PaymentTokenHeader
{
    // <editor-fold desc="private variables">
    private final String mEphemeralPublicKey;

    private final String mPublicKeyHash;

    private final String mTransactionId;

    private final String mApplicationData;
    // </editor-fold>

    public PaymentTokenHeader(
            String ephemeralPublicKey,
            String publicKeyHash,
            String transactionId
    )
    {
        this.mEphemeralPublicKey = ephemeralPublicKey;
        this.mPublicKeyHash = publicKeyHash;
        this.mTransactionId = transactionId;
        this.mApplicationData = null;
    }

    public PaymentTokenHeader(
            String ephemeralPublicKey,
            String publicKeyHash,
            String transactionId,
            String applicationData
    )
    {
        this.mEphemeralPublicKey = ephemeralPublicKey;
        this.mPublicKeyHash = publicKeyHash;
        this.mTransactionId = transactionId;
        this.mApplicationData = applicationData;
    }

    public String getEphemeralPublicKey() {
        return mEphemeralPublicKey;
    }

    public String getPublicKeyHash() {
        return mPublicKeyHash;
    }

    public String getTransactionId() {
        return mTransactionId;
    }

    public String getApplicationData()
    {
        return this.mApplicationData;
    }

    public boolean hasApplicationData()
    {
        return this.mApplicationData != null && this.mApplicationData.length() > 0;
    }
}