package com.hps.integrator.entities.altpayment;

import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsProcessorError;

import java.util.Dictionary;
import java.util.Hashtable;

public class HpsAltPaymentResponse extends HpsAuthorization {
    HpsProcessorError error;

    public HpsAltPaymentResponse fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);

        Element response = rsp.get("Transaction").firstChild();
        if(response.getInt("RspCode") != 0){
            Dictionary<String, String> error = nvpToArray(response.get("Processor").get("Response"));
            this.error = new HpsProcessorError();
            this.error.setCode(error.get("Code") != null ? error.get("Code") : null);
            this.error.setMessage(error.get("Message") != null ? error.get("Message") : null);
            this.error.setType(error.get("Type") != null ? error.get("Type") : null);
        }

        return this;
    }

    protected Dictionary<String, String> nvpToArray(Element pairs) {
        Dictionary<String, String> rvalue = new Hashtable<String, String>();
        for(Element pair: pairs.getAll())
            rvalue.put(pair.getString("Name"), pair.getString("Value"));
        return rvalue;
    }
}
