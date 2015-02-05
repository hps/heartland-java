package PosGateway.Exchange.Hps;

import org.ksoap2.serialization.*;

public class CardDataTypeTrackData {

    public String value;

    public Enums.CardDataType_TrackData_method method = Enums.CardDataType_TrackData_method.swipe;

    public CardDataTypeTrackData() {
    }

    public CardDataTypeTrackData(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        if (inObj != null) {
            Object j = (Object) inObj;
            Object obj = inObj;
            value = j.toString();
        }

        if (inObj.hasAttribute("method")) {
            Object j = inObj.getAttribute("method");
            if (j != null) {
                method = Enums.CardDataType_TrackData_method.fromString(j.toString());
            }
        }

    }

    public Object getSimpleValue() {
        Object j = this.value;
        SoapPrimitive primitive = new SoapPrimitive("http://Hps.Exchange.PosGateway", "value", j != null ? j.toString() : "");
        if (this.method != null) {
            AttributeInfo attrInfo = new AttributeInfo();
            attrInfo.setName("method");
            attrInfo.setValue(this.method);
            attrInfo.setNamespace("");
            primitive.addAttribute(attrInfo);
        }
        return primitive;
    }
}
