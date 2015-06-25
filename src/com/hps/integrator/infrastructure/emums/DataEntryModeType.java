package com.hps.integrator.infrastructure.emums;

public enum DataEntryModeType {
    manual("MANUAL"),
    swipe("SWIPE");

    String value;
    DataEntryModeType(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
