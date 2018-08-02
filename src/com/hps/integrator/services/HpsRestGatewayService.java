package com.hps.integrator.services;

import com.google.gson.Gson;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.infrastructure.HpsException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public abstract class HpsRestGatewayService {

    int limit = -1;
    int offset = -1;

    IHpsServicesConfig servicesConfig;

    protected HpsRestGatewayService(IHpsServicesConfig config) {
        if(config != null) {
            this.servicesConfig = config;
        }
    }

    protected String doRequest(String verb, String endpoint) throws HpsException {
        return this.doRequest(verb, endpoint, null, null, null);
    }
    protected String doRequest(String verb, String endpoint, Object data) throws HpsException {
        return this.doRequest(verb, endpoint, data, null, null);

    }
    protected String doRequest(String verb, String endpoint, Object data, HashMap<String, String> additionalHeaders, HashMap<String, String> queryStringParameters) throws HpsException {
        HttpsURLConnection conn;
        String mUrl = servicesConfig.getServiceUri();


        try {
            String queryString = null;
            //Query string
            if(queryStringParameters != null) {

                StringBuilder sb = new StringBuilder();
                sb.append("?");
                for (Map.Entry<String, String> entry : queryStringParameters.entrySet()) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(String.format("%s=%s",
                            URLEncoder.encode(entry.getKey(), "UTF-8"),
                            URLEncoder.encode(entry.getValue(), "UTF-8")
                    ));
                }
                queryString = sb.toString();
                conn = (HttpsURLConnection)new URL(mUrl + endpoint + queryString).openConnection();
            }else{
                conn = (HttpsURLConnection)new URL(mUrl + endpoint).openConnection();
            }


        }
        catch (IOException e) { throw new HpsException(e.getMessage(), e); }

        Gson gson = new Gson();
        String result = "";

        try {

            conn.setDoInput(true);
            conn.setRequestMethod(verb);
            conn.addRequestProperty("Content-Type", "application/json");

            //Headers
            if(additionalHeaders != null) {
                for (Map.Entry<String, String> entry : additionalHeaders.entrySet()) {
                    conn.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            //Payload
            if (!verb.equals("GET"))
            {
                if(data != null) {
                    String payload = gson.toJson(data);
                    System.out.println(payload);

                    byte[] bytes = payload.getBytes();

                    conn.setDoOutput(true);
                    conn.addRequestProperty("Content-Length", String.format("%s", bytes.length));

                    DataOutputStream requestStream = new DataOutputStream(conn.getOutputStream());
                    requestStream.write(bytes);
                    requestStream.flush();
                    requestStream.close();
                }
            }

            InputStream responseStream = conn.getInputStream();
            result += readFully(responseStream);
            System.out.println(result);
            responseStream.close();

            return result;
        }
        catch (IOException e) {
            try {
                if (conn.getResponseCode() == 400) {
                    InputStream errorStream = conn.getErrorStream();
                    String errorMessage = readFully(errorStream);
                    errorStream.close();

                    throw new HpsException(errorMessage, e);
                } else {
                    throw new HpsException(e.getMessage(), e);
                }
            } catch (IOException ie) { throw new HpsException(e.getMessage(), ie); }
        }
    }

    private String readFully(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        Reader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
        int c;
        while((c = reader.read()) != -1)
            sb.append((char)c);
        return sb.toString();
    }

    protected <T> T hydrateObject(String data, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(data, clazz);
    }
}
