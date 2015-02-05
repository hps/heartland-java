package com.hps.integrator.applepay.ecv1;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hps.integrator.infrastructure.HpsException;

import java.math.BigDecimal;

public class PaymentData
{
    // <editor-fold desc="private variables">

    public static final String EMPTY_OR_NULL_JSON_STRING = "JSON provided is either a null or empty string";

    private final String mJsonPaymentToken;

    private String mApplicationExpirationDate;

    private String mApplicationPrimaryAccountNumber;

    private String mCurrencyCode;

    private String mDeviceManufacturerIdentifier;

    private PaymentData3DS mPaymentData;

    private String mTransactionAmount;

    // </editor-fold>

    public PaymentData(String json) throws HpsException
    {
        if(json == null || json.length() == 0)
        {
            throw new HpsException(EMPTY_OR_NULL_JSON_STRING);
        }

        this.mJsonPaymentToken = json;
        this.hydrateFromJson();
    }

    public String getApplicationExpirationDate() {
        return mApplicationExpirationDate;
    }

    public String getApplicationPrimaryAccountNumber() {
        return mApplicationPrimaryAccountNumber;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public String getDeviceManufacturerIdentifier() {
        return mDeviceManufacturerIdentifier;
    }

    public PaymentData3DS getPaymentData() {
        return mPaymentData;
    }

    public String getTransactionAmount() {
        return mTransactionAmount;
    }

    public BigDecimal getDollarAmount() {
        return new BigDecimal(getTransactionAmount()).movePointLeft(2);
    }

    protected void hydrateFromJson() throws HpsException
    {
        try
        {
            JsonElement jelement = new JsonParser().parse(this.mJsonPaymentToken);
            JsonObject jobject = jelement.getAsJsonObject();

            this.mApplicationExpirationDate = jobject.get("applicationExpirationDate").getAsString();
            this.mApplicationPrimaryAccountNumber = jobject.get("applicationPrimaryAccountNumber").getAsString();
            this.mCurrencyCode = jobject.get("currencyCode").getAsString();
            this.mDeviceManufacturerIdentifier = jobject.get("deviceManufacturerIdentifier").getAsString();

            JsonObject paymentData = jobject.getAsJsonObject("paymentData");
            String cryptogram = paymentData.get("onlinePaymentCryptogram").getAsString();

            this.mPaymentData = new PaymentData3DS(cryptogram);
            this.mTransactionAmount = jobject.get("transactionAmount").getAsString();
        }
        catch(Exception e)
        {
            throw new HpsException(e.getMessage(), e);
        }
    }
}