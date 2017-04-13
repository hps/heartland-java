package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IResponseSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

import java.math.BigDecimal;

public class AmountResponse implements IResponseSubGroup {
    BigDecimal approvedAmount;
    BigDecimal amountDue;
    BigDecimal tipAmount;
    BigDecimal cashBackAmount;
    BigDecimal merchantFee;
    BigDecimal taxAmount;
    BigDecimal balance1;
    BigDecimal balance2;

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }
    public BigDecimal getAmountDue() {
        return amountDue;
    }
    public BigDecimal getTipAmount() {
        return tipAmount;
    }
    public BigDecimal getCashBackAmount() {
        return cashBackAmount;
    }
    public BigDecimal getMerchantFee() {
        return merchantFee;
    }
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }
    public BigDecimal getBalance1() {
        return balance1;
    }
    public BigDecimal getBalance2() {
        return balance2;
    }

    public AmountResponse(MessageReader mr) {
        String values = mr.readToCode(ControlCodes.FS);
        if(HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] data = values.split("\\[US\\]");
        try{
            approvedAmount = HpsStringUtils.toAmount(data[0]);
            amountDue = HpsStringUtils.toAmount(data[1]);
            tipAmount = HpsStringUtils.toAmount(data[2]);
            cashBackAmount = HpsStringUtils.toAmount(data[3]);
            merchantFee = HpsStringUtils.toAmount(data[4]);
            taxAmount = HpsStringUtils.toAmount(data[5]);
            balance1 = HpsStringUtils.toAmount(data[6]);
            balance2 = HpsStringUtils.toAmount(data[7]);
        }
        catch(IndexOutOfBoundsException e){
            // Eating this
        }
    }
}
