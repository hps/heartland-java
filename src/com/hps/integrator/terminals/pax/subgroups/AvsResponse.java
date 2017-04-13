package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IResponseSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

public class AvsResponse implements IResponseSubGroup {
    String avsResponseCode;
    String avsResponseMessage;

    public String getAvsResponseCode() {
        return avsResponseCode;
    }
    public String getAvsResponseMessage() {
        return avsResponseMessage;
    }

    public AvsResponse(MessageReader br) {
        String values = br.readToCode(ControlCodes.FS);
        if (HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] data = values.split("\\[US\\]");
        try {
            avsResponseCode = data[0];
            avsResponseMessage = data[1];
        }
        catch (IndexOutOfBoundsException e) {
            // Nom nom
        }
    }
}
