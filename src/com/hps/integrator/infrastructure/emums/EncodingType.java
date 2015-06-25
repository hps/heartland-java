package com.hps.integrator.infrastructure.emums;

public enum EncodingType {
    base16("base16"),
    base64("base64");

    String value;
    EncodingType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
