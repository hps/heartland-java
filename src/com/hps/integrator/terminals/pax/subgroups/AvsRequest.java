package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IRequestSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;

public class AvsRequest implements IRequestSubGroup {
    String zipCode;
    String address;

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getElementString() {
        StringBuilder sb = new StringBuilder();
        sb.append(zipCode);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(address);

        return HpsStringUtils.trimEnd(sb.toString(), ControlCodes.US);
    }
}
