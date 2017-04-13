package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IRequestSubGroup;
import com.hps.integrator.abstractions.IResponseSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

public class CashierSubGroup implements IRequestSubGroup, IResponseSubGroup {
    String clerkId;
    String shiftId;

    public String getClerkId() {
        return clerkId;
    }
    public void setClerkId(String clerkId) {
        this.clerkId = clerkId;
    }
    public String getShiftId() {
        return shiftId;
    }
    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public CashierSubGroup() { }
    public CashierSubGroup(MessageReader br) {
        String values = br.readToCode(ControlCodes.FS);
        if (HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] data = values.split("\\[US\\]");
        try {
            clerkId = data[0];
            shiftId = data[1];
        }
        catch (IndexOutOfBoundsException e) {
            // nom nom
        }
    }

    public String getElementString() {
        StringBuilder sb = new StringBuilder();
        sb.append(clerkId);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(shiftId);

        return HpsStringUtils.trimEnd(sb.toString(), ControlCodes.US);
    }
}
