package com.hps.integrator.entities.gift;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;

import java.math.BigDecimal;

public class HpsGiftCardSale extends HpsTransaction {
    private String authorizationCode;
    private BigDecimal splitTenderCardAmount;
    private BigDecimal splitTenderBalanceDue;
    private BigDecimal balanceAmount;
    private BigDecimal pointsBalanceAmount;

    public HpsGiftCardSale(HpsTransactionHeader header) {
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

    public BigDecimal getSplitTenderCardAmount() {
        return splitTenderCardAmount;
    }

    public void setSplitTenderCardAmount(BigDecimal splitTenderCardAmount) {
        this.splitTenderCardAmount = splitTenderCardAmount;
    }

    public BigDecimal getSplitTenderBalanceDue() {
        return splitTenderBalanceDue;
    }

    public void setSplitTenderBalanceDue(BigDecimal splitTenderBalanceDue) {
        this.splitTenderBalanceDue = splitTenderBalanceDue;
    }
}
