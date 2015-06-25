package com.hps.integrator.entities.credit;

import com.hps.integrator.entities.HpsTokenData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

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
    private String transactionDescriptor;

    public HpsAuthorization() {}

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

    public String getTransactionDescriptor() {
        return transactionDescriptor;
    }

    public void setTransactionDescriptor(String transactionDescriptor) {
        this.transactionDescriptor = transactionDescriptor;
    }

    public HpsAuthorization fromElementTree(ElementTree rsp){
        Element authResponse = rsp.get("Transaction").firstChild();

        super.fromElementTree(rsp);

        if(authResponse.has("AuthCode"))
            this.setAuthorizationCode(authResponse.getString("AuthCode"));
        if(authResponse.has("AVSRsltCode"))
            this.setAvsResultCode(authResponse.getString("AVSRsltCode"));
        if(authResponse.has("AVSRsltText"))
            this.setAvsResultText(authResponse.getString("AVSRsltText"));
        if(authResponse.has("CVVRsltCode"))
            this.setCvvResultCode(authResponse.getString("CVVRsltCode"));
        if(authResponse.has("CVVRsltText"))
            this.setAvsResultText(authResponse.getString("CVVRsltText"));
        if(authResponse.has("AuthAmt"))
            this.setAuthorizedAmount(new BigDecimal(authResponse.getString("AuthAmt")));
        if(authResponse.has("CardType"))
            this.setCardType(authResponse.getString("CardType"));
        if(authResponse.has("TxnDescriptor"))
            this.setTransactionDescriptor(authResponse.getString("TxnDescriptor"));
        if(authResponse.has("CPCInd"))
            this.setCpcIndicator(authResponse.getString("CPCInd"));

        Element header = rsp.get("Header");
        if(header.has("TokenData")) {
            Element tokenData = header.get("TokenData");

            HpsTokenData token = new HpsTokenData();
            token.setTokenRspCode(tokenData.getInt("TokenRspCode"));
            token.setTokenRspMsg(tokenData.getString("TokenRspMsg"));
            token.setTokenValue(tokenData.getString("TokenValue"));
            this.setTokenData(token);
        }

        return this;
    }
}
