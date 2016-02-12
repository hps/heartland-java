package com.hps.integrator.entities.altpayment;

import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.infrastructure.ElementTree;

public class HpsAltPaymentAddToBatch extends HpsAuthorization {
    public HpsAltPaymentAddToBatch fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
