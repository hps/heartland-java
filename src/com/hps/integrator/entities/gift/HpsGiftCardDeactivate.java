package com.hps.integrator.entities.gift;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;

import java.math.BigDecimal;

public class HpsGiftCardDeactivate extends HpsTransaction {
    private String authorizationCode;
    private BigDecimal balanceAmount;
    private BigDecimal refundAmount;
    private BigDecimal pointsBalanceAmount;

    public HpsGiftCardDeactivate(HpsTransactionHeader header) {
        super(header);
    }

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

    public BigDecimal getRefundAmount() { return refundAmount; }

    public void setRefundAmount(BigDecimal refundAmount) { this.refundAmount = refundAmount; }
}
