package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosResponse extends AttributeContainer implements KvmSerializable {

    public PosResponseVer10 Ver10;

    public String rootUrl;

    public PosResponse() {
    }

    public PosResponse(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Ver1.0")) {
            Object j = soapObject.getProperty("Ver1.0");
            this.Ver10 = (PosResponseVer10) envelope.get(j, PosResponseVer10.class);
        }


        if (inObj.hasAttribute("rootUrl")) {
            Object j = inObj.getAttribute("rootUrl");
            if (j != null) {
                rootUrl = j.toString();
            }
        }

    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Ver10 != null ? Ver10 : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PosResponseVer10.class;
            info.name = "Ver1.0";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }


    @Override
    public int getAttributeCount() {
        return 1;
    }

    @Override
    public void getAttributeInfo(int index, AttributeInfo info) {
        if (index == 0) {
            info.name = "rootUrl";
            info.namespace = "";
            info.setValue(rootUrl);
        }
    }
}
