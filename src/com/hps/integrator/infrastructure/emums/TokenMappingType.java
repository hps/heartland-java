package com.hps.integrator.infrastructure.emums;

public enum TokenMappingType {
    Unique("UNIQUE"),
    Constant("CONSTANT");

    String value;
    TokenMappingType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
