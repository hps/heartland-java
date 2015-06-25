package com.hps.integrator.entities.ebt;

import com.hps.integrator.entities.debit.HpsDebitAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

import java.math.BigDecimal;

public class HpsEbtAuthorization extends HpsDebitAuthorization {
    public HpsEbtAuthorization fromElementTree(ElementTree rsp) {
        Element saleResponse = rsp.get("Transaction").firstChild();

        super.fromElementTree(rsp);
        this.setAuthorizationCode(saleResponse.getString("AuthCode"));
        this.setAvsResultCode(saleResponse.getString("AVSRsltCode"));
        this.setAvsResultText(saleResponse.getString("AVSRsltText"));
        this.setCvvResultCode(saleResponse.getString("CVVRsltCode"));
        this.setCvvResultText(saleResponse.getString("CVVRsltText"));
        this.setCardType(saleResponse.getString("CardType"));
        if(saleResponse.has("AvailableBalance"))
            this.setAvailableBalance(new BigDecimal(saleResponse.getString("AvailableBalance")));
        if(saleResponse.has("AuthAmt"))
            this.setAuthorizedAmount(new BigDecimal(saleResponse.getString("AuthAmt")));

        return this;
    }
}
