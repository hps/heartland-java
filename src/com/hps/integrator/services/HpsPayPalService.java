package com.hps.integrator.services;

import com.hps.integrator.infrastructure.HpsException;

public class HpsPayPalService extends HpsAltPaymentService {
    public HpsPayPalService(HpsServicesConfig config) throws HpsException {
        super(config);
        this.transactionType = "PAYPAL";
    }
    public HpsPayPalService(HpsServicesConfig config, boolean enableLogging) throws HpsException {
        super(config, enableLogging);
        this.transactionType = "PAYPAL";
    }
}
