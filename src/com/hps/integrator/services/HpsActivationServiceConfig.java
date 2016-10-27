package com.hps.integrator.services;


import com.sun.org.apache.xpath.internal.operations.Bool;

public class HpsActivationServiceConfig extends HpsRestServiceConfig {

    public String ApplicationId;
    public String HardwareTypeName;
    public String SoftwareVersion;
    public String ConfigurationName;
    public String PeripheralName;
    public String PeripheralSoftware;

    public Boolean IsTest;

    public HpsActivationServiceConfig() {
        this.UatUrl = "https://huds.test.e-hps.com/config-server/v1/";
        this.CertUrl = "https://huds.test.e-hps.com/config-server/v1/";
        this.ProdUrl = "https://huds.prod.e-hps.com/config-server/v1/";

    }
}
