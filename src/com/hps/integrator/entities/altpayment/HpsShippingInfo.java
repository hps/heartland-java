package com.hps.integrator.entities.altpayment;

import com.hps.integrator.entities.HpsAddress;

public class HpsShippingInfo {
    String name;
    HpsAddress address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HpsAddress getAddress() {
        return address;
    }

    public void setAddress(HpsAddress address) {
        this.address = address;
    }
}
