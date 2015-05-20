package com.hps.integrator.entities.payplan;

public class HpsPayPlanAmount {
    String value;
    String currency;

    public String getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public HpsPayPlanAmount(String value) {
        this.value = value;
        this.currency = "USD";
    }
    public HpsPayPlanAmount(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }
}
