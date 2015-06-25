package com.hps.integrator.entities.credit;

import java.math.BigDecimal;
import java.util.List;

public class HpsAutoSubstantiation {
    String merchantVerificationValue;
    boolean realTimeSubstantiation = false;
    HpsAdditionalAmount[] additionalAmounts;

    public String getMerchantVerificationValue() {
        return merchantVerificationValue;
    }

    public void setMerchantVerificationValue(String merchantVerificationValue) {
        this.merchantVerificationValue = merchantVerificationValue;
    }

    public boolean isRealTimeSubstantiation() {
        return realTimeSubstantiation;
    }

    public void setRealTimeSubstantiation(boolean realTimeSubstantiation) {
        this.realTimeSubstantiation = realTimeSubstantiation;
    }

    public HpsAdditionalAmount[] getAdditionalAmounts() {
        return additionalAmounts;
    }

    public void setAdditionalAmounts(HpsAdditionalAmount[] additionalAmounts) {
        this.additionalAmounts = additionalAmounts;
    }
}
