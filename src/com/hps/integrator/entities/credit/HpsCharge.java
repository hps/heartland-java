package com.hps.integrator.entities.credit;

import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.infrastructure.ElementTree;

public class HpsCharge extends HpsAuthorization {

    public HpsCharge() {}
	public HpsCharge(HpsTransactionHeader header) {
		super(header);
	}

    public HpsCharge fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
