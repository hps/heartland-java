package com.hps.integrator.infrastructure.emums;

public enum TaxTypeType {
    NotUsed("NOTUSED"),
    SalesTax("SALESTAX"),
    TaxExempt("TAXEXEMPT");

    String value;
    TaxTypeType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
