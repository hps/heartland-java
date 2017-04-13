package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IRequestSubGroup;
import com.hps.integrator.abstractions.IResponseSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxExtData;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class ExtDataSubGroup implements IRequestSubGroup, IResponseSubGroup {
    Dictionary<String, String> collection = new Hashtable<String, String>();

    public String get(PaxExtData key){
        String rvalue = collection.get(key.getValue());
        if(rvalue == null)
            return "";
        return rvalue;
    }

    public void set(PaxExtData key, String value) {
        collection.put(key.getValue(), value);
    }

    public ExtDataSubGroup() { }
    public ExtDataSubGroup(MessageReader br) {
        String values = br.readToCode(ControlCodes.ETX);
        if (HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] elements = values.split("\\[US\\]");
        for(String element: elements) {
            String[] kv = element.split("=");

            try {
                collection.put(kv[0].toUpperCase(), kv[1]);
            }
            catch (IndexOutOfBoundsException e) {
                // nom nom
            }
        }
    }

    public String getElementString() {
        StringBuilder sb = new StringBuilder();

        Enumeration<String> keys = collection.keys();
        while(keys.hasMoreElements()) {
            String key = keys.nextElement();
            sb.append(String.format("%s=%s%s", key, collection.get(key), (char)ControlCodes.US.getByte()));
        }

        return HpsStringUtils.trimEnd(sb.toString(), ControlCodes.US);
    }
}
