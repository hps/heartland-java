package com.hps.integrator.services;


import java.util.HashMap;

public class HpsPayPlanServiceConfig extends HpsRestServiceConfig {
    public HpsPayPlanServiceConfig() {

        // Set urls
        this.CertUrl = "https://cert.api2.heartlandportico.com/Portico.PayPlan.v2/";
        this.ProdUrl = "https://api-cert.heartlandportico.com/payplan.v2/";
        this.UatUrl = "https://api-uat.heartlandportico.com/payplan.v2/";

    }



}
