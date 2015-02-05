package com.hps.integrator.applepay.ecv1;

public class PaymentData3DS
{
    // <editor-fold desc="private variables">
    private String mOnlinePaymentCryptogram;

    private String mPaymentDataType = "3DSecure";
    // </editor-fold>

    public PaymentData3DS(String onlinePaymentCryptogram)
    {
        mOnlinePaymentCryptogram = onlinePaymentCryptogram;
    }

    public String getOnlinePaymentCryptogram()
    {
        return this.mOnlinePaymentCryptogram;
    }

    public String getPaymentDataTye()
    {
        return mPaymentDataType;
    }
}