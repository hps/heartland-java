package com.hps.integrator.terminals;

import com.hps.integrator.abstractions.IDeviceMessage;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsEnumUtils;

public class DeviceMessage implements IDeviceMessage {
    byte[] buffer;

    public DeviceMessage(byte[] buffer){
        this.buffer = buffer;
    }

    public byte[] getSendBuffer() { return this.buffer; }

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