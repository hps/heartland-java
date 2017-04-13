package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IRequestSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;

public class CommercialRequest implements IRequestSubGroup {
    String poNumber;
    String customerCode;
    String taxExempt;
    String taxExemptId;

    public String getPoNumber() {
        return poNumber;
    }
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    public String getTaxExempt() {
        return taxExempt;
    }
    public void setTaxExempt(String taxExempt) {
        this.taxExempt = taxExempt;
    }
    public String getTaxExemptId() {
        return taxExemptId;
    }
    public void setTaxExemptId(String taxExemptId) {
        this.taxExemptId = taxExemptId;
    }

    public String getElementString() {
        StringBuilder sb = new StringBuilder();
        sb.append(poNumber);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(customerCode);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(taxExempt);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(taxExemptId);

        return HpsStringUtils.trimEnd(sb.toString(), ControlCodes.US);
    }
}
