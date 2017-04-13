package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IRequestSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;

public class TraceRequest implements IRequestSubGroup {
    String referenceNumber;
    String invoiceNumber;
    String authCode;
    String transactionNumber;
    String timeStamp;

    public String getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getAuthCode() {
        return authCode;
    }
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    public String getTransactionNumber() {
        return transactionNumber;
    }
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getElementString() {
        StringBuilder sb = new StringBuilder();
        sb.append(referenceNumber);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(invoiceNumber);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(authCode);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(transactionNumber);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(timeStamp);

        return HpsStringUtils.trimEnd(sb.toString(), ControlCodes.US);
    }
}
