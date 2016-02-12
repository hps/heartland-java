package com.hps.integrator.entities.altpayment;

import com.hps.integrator.infrastructure.ElementTree;

public class HpsAltPaymentVoid extends HpsAltPaymentResponse {
    public HpsAltPaymentVoid fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
