package com.hps.integrator.entities.altpayment;

import com.hps.integrator.infrastructure.ElementTree;

public class HpsAltPaymentReturn extends HpsAltPaymentResponse {
    public HpsAltPaymentReturn fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
