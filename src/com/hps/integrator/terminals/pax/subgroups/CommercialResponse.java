package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IResponseSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

public class CommercialResponse implements IResponseSubGroup {
    String poNumber;
    String customerCode;
    boolean taxExempt;
    String taxExemptId;

    public String getPoNumber() {
        return poNumber;
    }
    public String getCustomerCode() {
        return customerCode;
    }
    public boolean isTaxExempt() {
        return taxExempt;
    }
    public String getTaxExemptId() {
        return taxExemptId;
    }

    public CommercialResponse(MessageReader br) {
        String values = br.readToCode(ControlCodes.FS);
        if (HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] data = values.split("\\[US\\]");
        try {
            this.poNumber = data[0];
            this.customerCode = data[1];
            this.taxExempt = data[2].equals("1");
            this.taxExemptId = data[3];
        }
        catch (IndexOutOfBoundsException e) {
            // nom nom
        }
    }
}
