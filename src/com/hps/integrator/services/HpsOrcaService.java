package com.hps.integrator.services;

import com.hps.integrator.entities.orca.HpsDeviceActivationKeyResponse;
import com.hps.integrator.entities.orca.HpsDeviceActivationResponse;

import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;

public class HpsOrcaService extends HpsRestGatewayService{
    private HpsOrcaServiceConfig mConfig;

    public HpsOrcaService(HpsOrcaServiceConfig configuration) {
        super(configuration);
        mConfig = configuration;
        configureBaseUrl(mConfig.IsTest);
    }

    private void configureBaseUrl(boolean isForTesting){
        String serviceUri = isForTesting ? "https://huds.test.e-hps.com/config-server/v1/" : "https://huds.prod.e-hps.com/config-server/v1/";
        mConfig.setServiceUri(serviceUri);
    }

    public HpsDeviceActivationResponse deviceActivationRequest(String merchantId, String email) throws Exception {

        if (mConfig.getDeviceId() <= 0)
            throw new HpsInvalidRequestException("DeviceId is required.");

        if (merchantId == null)
            throw new HpsInvalidRequestException("MerchantId is required.");

        if (email == null)
            throw new HpsInvalidRequestException("Email is required.");

        if (mConfig.ApplicationId == null)
            throw new HpsInvalidRequestException("ApplicationId is required.");

        HashMap<String, String> headers = new HashMap<String, String>();

        String usernamepair = String.format("%s:%s", mConfig.getUserName(), mConfig.getPassword());

        byte[] encoded = Base64.encodeBase64(usernamepair.getBytes());
        String auth = String.format("Basic %s", new String(encoded));
        headers.put("Authorization", auth);

        HashMap<String, String> payload = new HashMap<String, String>();
        payload.put("deviceId", Integer.toString(mConfig.getDeviceId()));
        payload.put("merchantId", merchantId);
        payload.put("applicationId", mConfig.ApplicationId);
        payload.put("hardwareTypeName", mConfig.HardwareTypeName);
        payload.put("softwareVersion", mConfig.SoftwareVersion);
        payload.put("configurationName", mConfig.ConfigurationName);
        payload.put("peripheralName", mConfig.PeripheralName);
        payload.put("peripheralSoftware", mConfig.PeripheralSoftware);
        payload.put("email", email);

        String response = doRequest("POST", "deviceActivation", payload, headers, null);
        return this.hydrateObject(response, HpsDeviceActivationResponse.class);
    }

    public HpsDeviceActivationKeyResponse activateDevice(String merchantId, String activationCode) throws Exception {
        if (merchantId == null)
            throw new HpsInvalidRequestException("MerchantId is required.");

        if (activationCode == null)
            throw new HpsInvalidRequestException("Activation Code is required.");

        if (mConfig.ApplicationId == null)
            throw new HpsInvalidRequestException("ApplicationId is required.");

        HashMap<String, String> qs = new HashMap<String, String>();

        qs.put("merchantId", merchantId);
        qs.put("applicationId", mConfig.ApplicationId);
        qs.put("activationCode", activationCode);

        String response = doRequest("GET", "deviceActivationKey", null, null, qs);
        return this.hydrateObject(response, HpsDeviceActivationKeyResponse.class);
    }

    public String getDeviceAPIKey() throws Exception {

        if (mConfig.getDeviceId() <= 0)
            throw new HpsInvalidRequestException("Device Id is required.");

        if (mConfig.getSiteId() <= 0)
            throw new HpsInvalidRequestException("Site Id is required.");

        if (mConfig.getLicenseId() <= 0)
            throw new HpsInvalidRequestException("License Id is required.");

        HashMap<String, String> headers = new HashMap<String, String>();
        String usernamepair = String.format("%s:%s", mConfig.getUserName(), mConfig.getPassword());
        byte[] encoded = Base64.encodeBase64(usernamepair.getBytes());
        String auth = String.format("Basic %s", new String(encoded));
        headers.put("Authentication", auth);
        headers.put("siteId", Integer.toString(mConfig.getSiteId()));
        headers.put("licenseId", Integer.toString(mConfig.getLicenseId()));
        headers.put("deviceId", Integer.toString(mConfig.getDeviceId()));

        String response = doRequest("POST", "deviceApiKey", null, headers, null);
        return response;
    }

    public String getDeviceParameters() throws Exception {

        if (mConfig.getDeviceId() <= 0)
            throw new HpsInvalidRequestException("DeviceId is required.");

        if (mConfig.ApplicationId == null)
            throw new HpsInvalidRequestException("ApplicationId is required.");

        if (mConfig.getSecretAPIKey() == null)
            throw new HpsInvalidRequestException("Secret API Key is required.");

        HashMap<String, String> headers = new HashMap<String, String>();

        String usernamepair = String.format("%s:", mConfig.getSecretAPIKey());
        byte[] encoded = Base64.encodeBase64(usernamepair.getBytes(),false);
        String auth = String.format("Basic %s", new String(encoded));
        headers.put("Authentication", auth);

        HashMap<String, String> qs = new HashMap<String, String>();
        qs.put("deviceId", Integer.toString(mConfig.getDeviceId()));
        qs.put("applicationId", mConfig.ApplicationId);
        qs.put("hardwareTypeName", mConfig.HardwareTypeName);

        String response = doRequest("GET", "deviceParameters", null, headers, qs);
        return response;
    }



}
