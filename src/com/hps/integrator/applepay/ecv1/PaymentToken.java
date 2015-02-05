package com.hps.integrator.applepay.ecv1;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hps.integrator.infrastructure.HpsException;
import org.bouncycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Arrays;

public class PaymentToken
{

    // <editor-fold desc="private variables">

    public static final String EMPTY_OR_NULL_JSON_STRING = "JSON provided is either a null or empty string";

    private final String mJsonTokenData;

    private String mData;

    private PaymentTokenHeader mHeader;

    private String mSignature;

    private String mVersion;

    // </editor-fold>

    public PaymentToken(String json) throws HpsException
    {
        if(json == null || json.length() == 0)
        {
            throw new HpsException(EMPTY_OR_NULL_JSON_STRING);
        }

        this.mJsonTokenData = json;
        this.hydrateFromJson();
    }

    public String getData() {
        return mData;
    }

    public PaymentTokenHeader getHeader() {
        return mHeader;
    }

    public String getSignature() {
        return mSignature;
    }

    public String getVersion() {
        return mVersion;
    }

    public boolean isValid(ECPrivateKey privateKey) throws HpsException {

        boolean result = false;

        /*try
        {
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
            ecdsaSign.initSign(privateKey);

            byte[] ephemeralKeyBytes = Base64.decode(this.getHeader().getEphemeralPublicKey());
            byte[] dataBytes = Base64.decode(this.getData());
            byte[] tranIdBytes = Base64.decode(this.getHeader().getTransactionId());
            byte[] signatureBytes = new byte[ephemeralKeyBytes.length + dataBytes.length + tranIdBytes.length];

            System.arraycopy(ephemeralKeyBytes, 0, signatureBytes, 0, ephemeralKeyBytes.length);
            System.arraycopy(dataBytes, 0, signatureBytes, ephemeralKeyBytes.length, dataBytes.length);
            System.arraycopy(tranIdBytes, 0, signatureBytes, ephemeralKeyBytes.length + dataBytes.length, tranIdBytes.length);

            // TODO: ApplicationData needs to be added if present

            ecdsaSign.update(signatureBytes);

            byte[] signature1 = Base64.decode(this.getSignature());
            byte[] signature2 = ecdsaSign.sign();

            result = Arrays.equals(signature1, signature2);
        }
        catch (NoSuchAlgorithmException| SignatureException| InvalidKeyException e)
        {
            throw new HpsException(e.getMessage(), e);
        }*/

        return result;
    }

    protected void hydrateFromJson() throws HpsException
    {
        try
        {
            JsonElement jelement = new JsonParser().parse(this.mJsonTokenData);
            JsonObject jobject = jelement.getAsJsonObject();

            this.mData = jobject.get("data").getAsString();

            JsonObject header = jobject.get("header").getAsJsonObject();
            String pubKey = header.get("ephemeralPublicKey").getAsString();
            String hash = header.get("publicKeyHash").getAsString();
            String tran = header.get("transactionId").getAsString();

            this.mHeader = new PaymentTokenHeader(pubKey,hash, tran);
            this.mSignature = jobject.get("signature").getAsString();
            this.mVersion = jobject.get("version").getAsString();
        }
        catch (Exception e)
        {
            throw new HpsException(e.getMessage(), e);
        }
    }
}