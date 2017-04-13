package com.hps.integrator.terminals;

import com.hps.integrator.abstractions.IRequestSubGroup;
import com.hps.integrator.abstractions.IStringConstant;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.utils.MessageWriter;

public class TerminalUtilities {
    private static final String version = "1.35";

    private static String getElementString(Object[] elements) {
        StringBuilder sb = new StringBuilder();
        for(Object element: elements){
            if(element instanceof ControlCodes)
                sb.append((char)((ControlCodes) element).getByte());
            else if(element instanceof IRequestSubGroup)
                sb.append(((IRequestSubGroup) element).getElementString());
            else if(element instanceof String[]){
                for(String sub_element: (String[])element){
                    sb.append(ControlCodes.FS.getByte());
                    sb.append(sub_element);
                }
            }
            else if(element instanceof IStringConstant)
                sb.append(((IStringConstant) element).getValue());
            else sb.append(element);
        }

        return sb.toString();
    }

    private static DeviceMessage buildMessage(PaxMsgId messageId, String message){
        MessageWriter buffer = new MessageWriter();

        // Begin Message
        buffer.add(ControlCodes.STX);

        // Add Message Id
        buffer.add(messageId);
        buffer.add(ControlCodes.FS);

        // Add Version
        buffer.addRange(version.getBytes());
        buffer.add(ControlCodes.FS);

        // Add Message
        buffer.addRange(message.getBytes());

        // End the message
        buffer.add(ControlCodes.ETX);

        byte lrc = calculateLRC(buffer.toArray());
        buffer.add(lrc);

        return new DeviceMessage(buffer.toArray());
    }

    public static DeviceMessage buildRequest(PaxMsgId messageId, Object... elements){
        String message = getElementString(elements);
        return buildMessage(messageId, message);
    }

    public static byte calculateLRC(byte[] buffer) {
        byte lrc = (byte)0x00;
        for (int i = 1; i < buffer.length; i++)
            lrc = (byte)(lrc ^ buffer[i]);
        return lrc;
    }
}