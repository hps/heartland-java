package com.hps.integrator.entities.credit;

import com.hps.integrator.infrastructure.HpsIssuerException;
import com.hps.integrator.infrastructure.HpsException;

public class HpsCreditExceptions {

    private HpsIssuerException hpsIssuerException;
    private HpsException hpsException;

    public HpsIssuerException getHpsIssuerException() {
        return hpsIssuerException;
    }

    public void setHpsIssuerException(HpsIssuerException hpsIssuerException) {
        this.hpsIssuerException = hpsIssuerException;
    }

    public HpsException getHpsException() {
        return hpsException;
    }

    public void setHpsException(HpsException hpsException) {
        this.hpsException = hpsException;
    }
}
