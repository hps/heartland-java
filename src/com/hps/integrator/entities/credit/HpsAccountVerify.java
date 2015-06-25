package com.hps.integrator.entities.credit;

import com.hps.integrator.infrastructure.ElementTree;

public class HpsAccountVerify extends HpsAuthorization {
	public HpsAccountVerify fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
