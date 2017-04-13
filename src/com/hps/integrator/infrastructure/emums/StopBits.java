package com.hps.integrator.infrastructure.emums;

public enum StopBits {
    One(1),
    Two(2);

    int value;
    StopBits(int value){ this.value = value; }
    public int getValue() { return this.value; }
}
