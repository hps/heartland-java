package com.hps.integrator.terminals.pax.responses;

import com.hps.integrator.infrastructure.HpsMessageException;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.utils.MessageReader;

public class InitializeResponse extends PaxDeviceResponse {
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }
    private void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public InitializeResponse(byte[] buffer) throws HpsMessageException {
        super(buffer, PaxMsgId.A01_RSP_INITIALIZE);
    }

    @Override
    protected void parseResponse(MessageReader mr) throws HpsMessageException {
        super.parseResponse(mr);
        setSerialNumber(mr.readToCode(ControlCodes.ETX));
    }
}
