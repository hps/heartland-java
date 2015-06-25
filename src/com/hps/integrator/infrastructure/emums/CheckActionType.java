package com.hps.integrator.infrastructure.emums;

public enum CheckActionType {
    sale("SALE"),
    override("OVERRIDE"),
    refund("RETURN");

    String value;
    CheckActionType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
