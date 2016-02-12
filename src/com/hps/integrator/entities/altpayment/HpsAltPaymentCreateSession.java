package com.hps.integrator.entities.altpayment;

import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

import java.util.Dictionary;

public class HpsAltPaymentCreateSession extends HpsAltPaymentResponse {
    private String sessionId;
    private String redirectUrl;

    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getRedirectUrl() {
        return redirectUrl;
    }
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public HpsAltPaymentCreateSession fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);

        Element session = rsp.get("Transaction").firstChild();
        if(session.has("Session")) {
            Dictionary<String, String> pairs = nvpToArray(session.get("Session"));
            this.setSessionId(pairs.get("SessionId"));
            this.setRedirectUrl(pairs.get("RedirectUrl"));
        }

        return this;
    }
}
