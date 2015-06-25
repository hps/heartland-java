package com.hps.integrator.entities.credit;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.infrastructure.ElementTree;

public class HpsRefund extends HpsTransaction {
	public HpsRefund fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }
}
