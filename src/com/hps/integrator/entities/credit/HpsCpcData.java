package com.hps.integrator.entities.credit;

import com.hps.integrator.infrastructure.emums.TaxTypeType;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class HpsCpcData {
    private String cardHolderPoNumber;
    private TaxTypeType taxType;
    private BigDecimal taxAmount;

    public String getCardHolderPoNumber() {
        return cardHolderPoNumber;
    }
    public void setCardHolderPoNumber(String cardHolderPoNumber) {
        checkPoNumber(cardHolderPoNumber);
        this.cardHolderPoNumber = cardHolderPoNumber;
    }
    public TaxTypeType getTaxType() {
        return taxType;
    }
    public void setTaxType(TaxTypeType taxType) {
        this.taxType = taxType;
    }
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(BigDecimal taxAmount) {
        checkTaxAmount(taxAmount);
        this.taxAmount = taxAmount;
    }

    public HpsCpcData() {
        this(null, null, null);
    }
    public  HpsCpcData(TaxTypeType taxType) {
        this(null, taxType, null);
    }
    public  HpsCpcData(String cardHolderPoNumber, TaxTypeType taxType) {
        this(cardHolderPoNumber, taxType, null);
    }
    public  HpsCpcData(TaxTypeType taxType, BigDecimal taxAmount) {
        this(null, taxType, taxAmount);
    }
    public  HpsCpcData(String cardHolderPoNumber, TaxTypeType taxType, BigDecimal taxAmount) {
        this.cardHolderPoNumber = cardHolderPoNumber;
        this.taxType = taxType;
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