package com.hps.integrator.applepay.ecv1;

public class PaymentData3DS
{
    // <editor-fold desc="private variables">
    private String mOnlinePaymentCryptogram;

    private String mPaymentDataType = "3DSecure";

    private String mEciIndicator = "";
    // </editor-fold>

    public PaymentData3DS(String onlinePaymentCryptogram, String eciIndicator)
    {
        mOnlinePaymentCryptogram = onlinePaymentCryptogram;
        mEciIndicator = eciIndicator;
    }

    public String getOnlinePaymentCryptogram()
    {
        return this.mOnlinePaymentCryptogram;
    }

    public String getPaymentDataTye()
    {
        return mPaymentDataType;
    }

    public String getEciIndicator() {
        return mEciIndicator;
    }
}