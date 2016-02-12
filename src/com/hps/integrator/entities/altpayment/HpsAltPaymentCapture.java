package com.hps.integrator.entities.altpayment;

import com.hps.integrator.infrastructure.ElementTree;

public class HpsAltPaymentCapture extends HpsAltPaymentAuth {
    public HpsAltPaymentCapture fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
