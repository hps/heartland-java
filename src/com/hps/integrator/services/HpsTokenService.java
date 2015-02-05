package com.hps.integrator.services;

import com.google.gson.Gson;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.serialization.HpsToken;
import org.apache.commons.codec.binary.Base64;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HpsTokenService {

    private String mPublicKey;
    private String mUrl;

    public HpsTokenService(String publicKey) {
        mPublicKey = publicKey;

        if (publicKey == null) {
            throw new IllegalArgumentException("publicKey can not be null");
        }

        String[] components = mPublicKey.split("_");

        if (components.length != 3) {
            throw new IllegalArgumentException("publicKey format invalid");
        }

        String env = components[1].toLowerCase();

        if (env.equals("prod")) {
            mUrl = "https://api.heartlandportico.com/SecureSubmit.v1/api/token";
        } else {
            mUrl = "https://posgateway.cert.secureexchange.net/Hps.Exchange.PosGateway.Hpf.v1/api/token";
        }
    }

    public HpsToken getToken(HpsCreditCard card) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) new URL(mUrl).openConnection();
        HpsToken result;

        byte[] creds = String.format("%s:", mPublicKey).getBytes();
        String auth = String.format("Basic %s", Base64.encodeBase64URLSafeString(creds));

        Gson gson = new Gson();
        String payload = gson.toJson(new HpsToken(card));

        byte[] bytes = payload.getBytes();

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.addRequestProperty("Authorization", auth);
        conn.addRequestProperty("Content-Type", "application/json");
        conn.addRequestProperty("Content-Length", String.format("%s", bytes.length));

        DataOutputStream requestStream = new DataOutputStream(conn.getOutputStream());
        requestStream.write(bytes);
        requestStream.flush();
        requestStream.close();

        try {
            InputStreamReader responseStream = new InputStreamReader(conn.getInputStream());
            result = gson.fromJson(responseStream, HpsToken.class);
            responseStream.close();
        } catch (IOException e) {
            if (conn.getResponseCode() == 400) {
                InputStreamReader errorStream = new InputStreamReader(conn.getErrorStream());
                result = gson.fromJson(errorStream, HpsToken.class);
                errorStream.close();
            } else {
                throw new IOException(e);
            }
        }

        return result;
    }
}
