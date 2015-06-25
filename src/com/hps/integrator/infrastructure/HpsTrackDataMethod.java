package com.hps.integrator.infrastructure;

public enum HpsTrackDataMethod {
    Swipe("swipe"),
    Proximity("proximity");

    String value;
    HpsTrackDataMethod(String value) { this.value = value; }
    public String getValue() { return this.value; }
}
