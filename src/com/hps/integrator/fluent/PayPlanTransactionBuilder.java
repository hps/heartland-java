package com.hps.integrator.fluent;

import com.google.gson.Gson;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.infrastructure.HpsException;
import org.apache.commons.codec.binary.Base64;
import sun.misc.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class PayPlanTransactionBuilder<TBuilder, TExecutionResult> extends TransactionBuilder<TExecutionResult> {
    int limit = -1, offset = -1;

    final String CERT_URL = "";
    final String PROD_URL = "";
    final String UAT_URL = "https://api-uat.heartlandportico.com/payplan.v2/";

    String url = UAT_URL;

    protected PayPlanTransactionBuilder(IHpsServicesConfig config) {
        super(config);

        String key = config.getSecretAPIKey();

        if(key != null) {
            String[] components = config.getSecretAPIKey().split("_");
            String env = components[1].toLowerCase();

            if (env.equals("prod")) {
                this.url = this.PROD_URL;
            } else if (env.equals("cert")) {
                this.url = this.CERT_URL;
            }
        }
    }

    protected abstract TBuilder getBuilder();

    public void setPagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    private void resetPagination() {
        this.limit = -1;
        this.offset = -1;
    }

    protected String doTransaction(String verb, String endpoint) throws HpsException {
        return this.doTransaction(verb, endpoint, null);
    }

    protected String doTransaction(String verb, String endpoint, Object data) throws HpsException {
        HttpsURLConnection conn;
        try {
            String queryString = "";
            if(this.limit != -1 && this.offset != -1) {
                queryString += "?limit=" + this.limit;
                queryString += "&offset=" + this.offset;
            }

            conn = (HttpsURLConnection)new URL(this.url + endpoint + queryString).openConnection();
        }
        catch (IOException e) { throw new HpsException(e.getMessage(), e); }

        Gson gson = new Gson();
        String result = "";

        try {
            byte[] encoded = Base64.encodeBase64(servicesConfig.getSecretAPIKey().getBytes());
            String auth = String.format("Basic %s", new String(encoded));

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(verb);
            conn.addRequestProperty("Authorization", auth);
            conn.addRequestProperty("Content-Type", "application/json");

            if(data != null) {
                String payload = gson.toJson(data);
                System.out.println(payload);

                byte[] bytes = payload.getBytes();

                conn.addRequestProperty("Content-Length", String.format("%s", bytes.length));

                DataOutputStream requestStream = new DataOutputStream(conn.getOutputStream());
                requestStream.write(bytes);
                requestStream.flush();
                requestStream.close();
            }

            InputStream responseStream = conn.getInputStream();
            result += new String(IOUtils.readFully(responseStream, conn.getContentLength(), true));
            System.out.println(result);
            responseStream.close();
            resetPagination();

            return result;
        }
        catch (IOException e) {
            try {
                if (conn.getResponseCode() == 400) {
                    InputStream errorStream = conn.getErrorStream();
                    String errorMessage = new String(IOUtils.readFully(errorStream, errorStream.available(), false));
                    errorStream.close();

                    throw new HpsException(errorMessage, e);
                } else {
                    throw new HpsException(e.getMessage(), e);
                }
            } catch (IOException ie) { throw new HpsException(e.getMessage(), ie); }
        }
    }

    protected <T> T hydrateObject(String data, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(data, clazz);
    }
}
