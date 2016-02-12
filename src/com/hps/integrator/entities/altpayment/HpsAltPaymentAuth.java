package com.hps.integrator.entities.altpayment;

import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

public class HpsAltPaymentAuth extends HpsAltPaymentResponse {
    String status;
    String statusMessage;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public HpsAltPaymentAuth fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);

        Element authResponse = rsp.get("Transaction").firstChild();
        if(authResponse.has("Status"))
            this.status = authResponse.getString("Status");
        if(authResponse.has("StatusMessage"))
            this.statusMessage = authResponse.getString("StatusResponse");

        return this;
    }
}
