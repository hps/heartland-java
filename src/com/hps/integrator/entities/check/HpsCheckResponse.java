package com.hps.integrator.entities.check;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;

import java.util.List;

public class HpsCheckResponse extends HpsTransaction {
    public HpsCheckResponse(HpsTransactionHeader header) {
        super(header);
    }

    private String authorizationCode;
    private String customerId;
    private List<HpsCheckResponseDetails> details;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<HpsCheckResponseDetails> getDetails() {
        return details;
    }

    public void setDetails(List<HpsCheckResponseDetails> details) {
        this.details = details;
    }
}
