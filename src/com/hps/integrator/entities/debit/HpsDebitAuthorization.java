package com.hps.integrator.entities.debit;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

import java.math.BigDecimal;

public class HpsDebitAuthorization extends HpsTransaction {
    String authorizationCode;
    BigDecimal availableBalance;
    String avsResultCode;
    String cvvResultCode;
    String avsResultText;
    String cvvResultText;
    String cardType;
    BigDecimal authorizedAmount;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getAvsResultCode() {
        return avsResultCode;
    }

    public void setAvsResultCode(String avsResultCode) {
        this.avsResultCode = avsResultCode;
    }

    public String getCvvResultCode() {
        return cvvResultCode;
    }

    public void setCvvResultCode(String cvvResultCode) {
        this.cvvResultCode = cvvResultCode;
    }

    public String getAvsResultText() {
        return avsResultText;
    }

    public void setAvsResultText(String avsResultText) {
        this.avsResultText = avsResultText;
    }

    public String getCvvResultText() {
        return cvvResultText;
    }

    public void setCvvResultText(String cvvResultText) {
        this.cvvResultText = cvvResultText;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(BigDecimal authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public HpsDebitAuthorization fromElementTree(ElementTree rsp) {
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
