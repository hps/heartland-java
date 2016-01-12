package com.hps.integrator.infrastructure.emums;

public enum TypeOfPaymentDataType {
    secure3d("3DSecure"),
    applePay("ApplePay");

    String value;
    TypeOfPaymentDataType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
