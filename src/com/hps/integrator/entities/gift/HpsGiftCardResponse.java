package com.hps.integrator.entities.gift;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

import java.math.BigDecimal;

public class HpsGiftCardResponse extends HpsTransaction {
    private String authorizationCode;
    private BigDecimal balanceAmount;
    private BigDecimal pointsBalanceAmount;
    private String rewards;
    private String notes;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getPointsBalanceAmount() {
        return pointsBalanceAmount;
    }

    public void setPointsBalanceAmount(BigDecimal pointsBalanceAmount) {
        this.pointsBalanceAmount = pointsBalanceAmount;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public HpsGiftCardResponse fromElementTree(ElementTree rsp) {
        Element activationResponse = rsp.get("Transaction").firstChild();

        super.fromElementTree(rsp);
        this.setTransactionID(rsp.get("Header").getInt("GatewayTxnId"));
        this.setAuthorizationCode(activationResponse.getString("AuthCode"));
        if(activationResponse.has("BalanceAmt"))
            this.setBalanceAmount(new BigDecimal(activationResponse.getString("BalanceAmt")));
        if(activationResponse.has("PointsBalanceAmt"))
            this.setPointsBalanceAmount(new BigDecimal(activationResponse.getString("PointsBalanceAmt")));
        this.setRewards(activationResponse.getString("Rewards"));
        this.setNotes(activationResponse.getString("Notes"));
        this.setResponseCode(activationResponse.getString("RspCode"));
        this.setResponseText(activationResponse.getString("RspText"));

        return this;
    }
}
