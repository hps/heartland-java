package com.hps.integrator.terminals.pax.interfaces;

import com.hps.integrator.abstractions.*;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsMessageException;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;

public class PaxHttpInterface implements IDeviceCommInterface {
    ITerminalConfiguration _settings;
    HttpURLConnection _client;
    IMessageSentInterface onMessageSent;

    public void setMessageSentHandler(IMessageSentInterface messageInterface) {
        this.onMessageSent = messageInterface;
    }

    public PaxHttpInterface(ITerminalConfiguration settings) {
        this._settings = settings;
    }

    public void connect() {
        // not required for this connection mode
    }

    public void disconnect() {
        // not required for this connection mode
    }

    public byte[] send(IDeviceMessage message) throws HpsException {
        String payload = Base64.encodeBase64String(message.getSendBuffer()).replace("\r", "").replace("\n", "");

        String endpoint = String.format("http://%s:%d?%s", _settings.getIpAddress(), _settings.getPort(), payload);
        try {
            _client = (HttpURLConnection) new URL(endpoint).openConnection();
        } catch(IOException e) {
            throw new HpsException(e.getMessage(), e);
        }

        try{
            _client.setDoInput(true);
            _client.setDoOutput(true);
            _client.setRequestMethod("GET");
            _client.addRequestProperty("Content-Type", "text/xml; charset=UTF-8");

            if(onMessageSent != null)
                onMessageSent.messageSent(message.toString());

            InputStream responseStream = _client.getInputStream();
            return IOUtils.readFully(responseStream, _client.getContentLength(), true);
        } catch(IOException e){
            throw new HpsMessageException("Failed to send message. Check inner exception for more details.", e);
        }
    }
}
