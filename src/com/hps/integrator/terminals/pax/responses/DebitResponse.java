package com.hps.integrator.terminals.pax.responses;

import com.hps.integrator.infrastructure.HpsMessageException;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.utils.MessageReader;
import com.hps.integrator.terminals.pax.subgroups.*;

public class DebitResponse extends PaxDeviceResponse {
    public DebitResponse(byte[] buffer) throws HpsMessageException {
        super(buffer, PaxMsgId.T03_RSP_DO_DEBIT);
    }

    @Override
    protected void parseResponse(MessageReader br) throws HpsMessageException {
        super.parseResponse(br);

        if (deviceResponseCode.equals("000000")) {
            hostResponse = new HostResponse(br);
            transactionType = br.readToCode(ControlCodes.FS);
            amountResponse = new AmountResponse(br);
            accountResponse = new AccountResponse(br);
            traceResponse = new TraceResponse(br);
            extDataResponse = new ExtDataSubGroup(br);

            mapResponse();
        }
    }

    @Override
    protected void mapResponse() {
        super.mapResponse();
    }
}
