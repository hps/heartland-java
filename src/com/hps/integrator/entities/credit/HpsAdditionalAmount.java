package com.hps.integrator.entities.credit;

import com.hps.integrator.infrastructure.emums.AdditionalAmountType;

import java.math.BigDecimal;

public class HpsAdditionalAmount {
    AdditionalAmountType amountType;
    BigDecimal amount;

    public HpsAdditionalAmount(){
        this(null, null);
    }
    public HpsAdditionalAmount(AdditionalAmountType  amountType){
        this(amountType, null);
    }
    public HpsAdditionalAmount(AdditionalAmountType amountType, BigDecimal amount) {
        this.amountType = amountType;
        this.amount = amount;
    }

    public AdditionalAmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AdditionalAmountType amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
