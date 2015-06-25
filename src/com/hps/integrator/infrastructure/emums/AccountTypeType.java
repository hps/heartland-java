package com.hps.integrator.infrastructure.emums;

public enum AccountTypeType {
    checking("CHECKING"),
    savings("SAVINGS");

    String value;
    AccountTypeType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
