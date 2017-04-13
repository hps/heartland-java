package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IResponseSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

public class TraceResponse implements IResponseSubGroup {
    String transactionNumber;
    String referenceNumber;
    String timeStamp;

    public String getTransactionNumber() {
        return transactionNumber;
    }
    public String getReferenceNumber() {
        return referenceNumber;
    }
    public String getTimeStamp() {
        return timeStamp;
    }

    public TraceResponse(MessageReader br) {
        String values = br.readToCode(ControlCodes.FS);
        if (HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] data = values.split("\\[US\\]");
        try {
            this.transactionNumber = data[0];
            this.referenceNumber = data[1];
            this.timeStamp = data[2];
        }
        catch (IndexOutOfBoundsException e) {
            // nom nom
        }
    }
}
