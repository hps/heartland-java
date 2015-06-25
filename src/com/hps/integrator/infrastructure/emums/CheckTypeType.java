package com.hps.integrator.infrastructure.emums;

public enum CheckTypeType {
    personal("PERSONAL"),
    business("BUSINESS"),
    payroll("PAYROLL");

    String value;
    CheckTypeType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
