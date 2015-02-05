package com.hps.integrator.entities.credit;

import PosGateway.Exchange.Hps.Enums;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class HpsCpcData {
    private String cardHolderPoNumber;
    private Enums.taxTypeType taxType;
    private BigDecimal taxAmount;

    public String getCardHolderPoNumber() {
        return cardHolderPoNumber;
    }

    public void setCardHolderPoNumber(String cardHolderPoNumber) {
        checkPoNumber(cardHolderPoNumber);
        this.cardHolderPoNumber = cardHolderPoNumber;
    }

    public Enums.taxTypeType getTaxType() {
        return taxType;
    }

    public void setTaxType(Enums.taxTypeType taxType) {
        this.taxType = taxType;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        checkTaxAmount(taxAmount);
        this.taxAmount = taxAmount;
    }

    private void checkPoNumber(String poNumber){
        if(poNumber.length() > 17){
            throw new IllegalArgumentException("cardHolderPoNumber can't be greater than 17 characters");
        }
    }

    private void checkTaxAmount(BigDecimal taxAmt){
        if(!Pattern.matches("^(\\d{0,10})(\\.\\d{2})?$", taxAmt.toString())){
            throw new IllegalArgumentException("taxAmt must be <= 12 digits (10 before the decimal and 2 after).");
        }
    }
}