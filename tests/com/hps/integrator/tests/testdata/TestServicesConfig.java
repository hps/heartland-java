package com.hps.integrator.tests.testdata;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.services.HpsServicesConfig;

public class TestServicesConfig {
	
	public static final String SERVICE_URI = "https://posgateway.cert.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";

    public static IHpsServicesConfig validServicesConfig() {
        HpsServicesConfig config = new HpsServicesConfig();
        config.setSecretAPIKey("skapi_cert_MYl2AQAowiQAbLp5JesGKh7QFkcizOP2jcX9BrEMqQ");
        config.setDeveloperId("123456");
        config.setVersionNumber("1234");
        config.setServiceUri(SERVICE_URI);

        return config;
    }

    public static IHpsServicesConfig validCertServicesConfigWithDescriptor() {
        HpsServicesConfig config = new HpsServicesConfig();
        config.setDeveloperId("123456");
        config.setVersionNumber("1234");
        config.setDeviceId(1520053);
        config.setLicenseId(20903);
        config.setPassword("$Test1234");
        config.setSiteId(20904);
        config.setSiteTrace("trace0001");
        config.setUserName("777700004597");
        config.setServiceUri(SERVICE_URI);

        return config;
    }

    public static IHpsServicesConfig validEGoldConfig() {
        HpsServicesConfig config = new HpsServicesConfig();
        config.setDeviceId(90911485);
        config.setLicenseId(95878);
        config.setPassword("$Test1234");
        config.setSiteId(95881);
        config.setUserName("777700778679");
        config.setServiceUri(SERVICE_URI);

        return config;
    }

    public static IHpsServicesConfig validCertMultiUseConfig() {
        HpsServicesConfig config = new HpsServicesConfig();
        config.setSecretAPIKey("skapi_cert_MYl2AQAowiQAbLp5JesGKh7QFkcizOP2jcX9BrEMqQ");
        config.setDeveloperId("002914");
        config.setVersionNumber("1510");
        config.setServiceUri(SERVICE_URI);

        return config;
    }
}