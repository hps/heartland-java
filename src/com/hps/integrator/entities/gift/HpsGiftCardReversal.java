package com.hps.integrator.entities.gift;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;

import java.math.BigDecimal;

public class HpsGiftCardReversal extends HpsTransaction {
    private String authorizationCode;
    private BigDecimal balanceAmount;

    public HpsGiftCardReversal(HpsTransactionHeader header) {
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
}
