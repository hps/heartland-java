package com.hps.integrator.entities.credit;

import com.hps.integrator.entities.HpsTransactionHeader;

public class HpsRecurringBilling extends HpsAuthorization {
    public HpsRecurringBilling(HpsTransactionHeader header) {
        super(header);
    }
}
