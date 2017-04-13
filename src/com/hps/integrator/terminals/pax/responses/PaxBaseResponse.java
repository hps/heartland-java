package com.hps.integrator.terminals.pax.responses;

import com.hps.integrator.infrastructure.HpsMessageException;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.utils.HpsEnumUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;
import com.hps.integrator.terminals.TerminalResponse;
import com.hps.integrator.terminals.pax.subgroups.*;

import java.util.Arrays;

abstract class PaxBaseResponse extends TerminalResponse {
    protected PaxMsgId[] messageIds;
    protected byte[] buffer;

    protected HostResponse hostResponse;
    protected AmountResponse amountResponse;
    protected AccountResponse accountResponse;
    protected TraceResponse traceResponse;
    protected AvsResponse avsResponse;
    protected CommercialResponse commercialResponse;
    protected EcomSubGroup ecomResponse;
    protected ExtDataSubGroup extDataResponse;
    protected CheckSubGroup checkSubResponse;

    public PaxBaseResponse(byte[] buffer, PaxMsgId... messageIds) throws HpsMessageException {
        this.messageIds = messageIds;
        this.buffer = buffer;

        this.parseResponse(new MessageReader(buffer));
    }

    protected void parseResponse(MessageReader mr) throws HpsMessageException {
        ControlCodes code = mr.readCode();  //STX
        setStatus(mr.readToCode(ControlCodes.FS));
        setCommand(mr.readToCode(ControlCodes.FS));
        setVersion(mr.readToCode(ControlCodes.FS));
        setDeviceResponseCode(mr.readToCode(ControlCodes.FS));
        setDeviceResponseText(mr.readToCode(ControlCodes.FS));

        PaxMsgId msgId = HpsEnumUtils.parse(PaxMsgId.class, command);
        if(!Arrays.asList(messageIds).contains(msgId))
            throw new HpsMessageException(String.format("Unexpected message type received: %s", command));
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(byte b : buffer){
            if(HpsEnumUtils.isDefined(ControlCodes.class, b)){
                ControlCodes code = HpsEnumUtils.parse(ControlCodes.class, b);
                sb.append(code.toString());
            }
            else sb.append((char)b);
        }

        return sb.toString();
    }
}
