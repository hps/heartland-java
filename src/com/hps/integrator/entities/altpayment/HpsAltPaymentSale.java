package com.hps.integrator.entities.altpayment;

import com.hps.integrator.infrastructure.ElementTree;

public class HpsAltPaymentSale extends HpsAltPaymentAuth {
    public HpsAltPaymentSale fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
