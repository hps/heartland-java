package com.hps.integrator.terminals.pax.responses;

import com.hps.integrator.infrastructure.HpsMessageException;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.utils.MessageReader;
import com.hps.integrator.terminals.pax.subgroups.HostResponse;

public class BatchCloseResponse extends PaxDeviceResponse {
    private String totalCount;
    private String totalAmount;
    private String timeStamp;
    private String tid;
    private String mid;
    private String batchNumber;

    public String getTotalCount() {
        return totalCount;
    }
    public String getTotalAmount() {
        return totalAmount;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public String getTid() {
        return tid;
    }
    public String getMid() {
        return mid;
    }
    public String getBatchNumber() {
        return batchNumber;
    }

    public BatchCloseResponse(byte[] buffer) throws HpsMessageException {
        super(buffer, PaxMsgId.B01_RSP_BATCH_CLOSE);
    }

    @Override
    protected void parseResponse(MessageReader mr) throws HpsMessageException {
        super.parseResponse(mr);

        hostResponse = new HostResponse(mr);
        totalCount = mr.readToCode(ControlCodes.FS);
        totalAmount = mr.readToCode(ControlCodes.FS);
        timeStamp = mr.readToCode(ControlCodes.FS);
        tid = mr.readToCode(ControlCodes.FS);
        mid = mr.readToCode(ControlCodes.ETX);

        if(this.hostResponse != null)
            this.batchNumber = this.hostResponse.getBatchNumber();
    }
}
