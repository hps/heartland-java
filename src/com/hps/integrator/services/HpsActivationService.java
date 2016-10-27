package com.hps.integrator.services;

import com.hps.integrator.entities.activation.HpsDeviceActivationKeyResponse;

import com.hps.integrator.infrastructure.HpsInvalidRequestException;

import java.util.HashMap;

public class HpsActivationService extends HpsRestGatewayService{
    private HpsActivationServiceConfig mConfig;

    public HpsActivationService(HpsActivationServiceConfig configuration) {
        super(configuration);
        mConfig = configuration;
        configureBaseUrl(mConfig.IsTest);
    }

    private void configureBaseUrl(boolean isForTesting){
        String serviceUri = isForTesting ? "https://huds.test.e-hps.com/config-server/v1/" : "https://huds.prod.e-hps.com/config-server/v1/";
        mConfig.setServiceUri(serviceUri);
    }

    public HpsDeviceActivationKeyResponse activateDevice(String merchantId, String activationCode) throws Exception {
        if (merchantId == null)
            throw new HpsInvalidRequestException("MerchantId is required.");

        if (activationCode == null)
            throw new HpsInvalidRequestException("Activation Code is required.");

        HashMap<String, String> qs = new HashMap<String, String>();
        qs.put("merchantId", merchantId);
        qs.put("activationCode", activationCode);

        String response = doRequest("GET", "deviceActivationKey", null, null, qs);
        return this.hydrateObject(response, HpsDeviceActivationKeyResponse.class);
    }


}
