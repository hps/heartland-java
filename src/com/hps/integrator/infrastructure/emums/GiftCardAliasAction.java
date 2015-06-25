package com.hps.integrator.infrastructure.emums;

public enum GiftCardAliasAction {
    Delete("DELETE"),
    Add("ADD"),
    Create("CREATE");

    String value;
    GiftCardAliasAction(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
