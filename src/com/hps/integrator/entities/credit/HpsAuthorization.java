package com.hps.integrator.entities.credit;

import com.hps.integrator.entities.HpsTokenData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;

import java.math.BigDecimal;

public class HpsAuthorization extends HpsTransaction {

    private String avsResultCode;
    private String cvvResultCode;
    private String avsResultText;
    private String cvvResultText;
    private String cpcIndicator;
    private String authorizationCode;
    private BigDecimal authorizedAmount;
    private String cardType;
    private HpsTokenData tokenData;

    public HpsAuthorization(HpsTransactionHeader header) {
        super(header);
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

    public String getCpcIndicator() {
        return cpcIndicator;
    }

    public void setCpcIndicator(String cpcIndicator) {
        this.cpcIndicator = cpcIndicator;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public BigDecimal getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(BigDecimal bigDecimal) {
        this.authorizedAmount = bigDecimal;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public HpsTokenData getTokenData() {
        return tokenData;
    }

    public void setTokenData(HpsTokenData tokenData) {
        this.tokenData = tokenData;
    }
}
