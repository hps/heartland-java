package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosRequest extends AttributeContainer implements KvmSerializable {

    public PosRequestVer10 Ver10;

    public String clientType;

    public String clientVer;

    public PosRequest() {
    }

    public PosRequest(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Ver1.0")) {
            Object j = soapObject.getProperty("Ver1.0");
            this.Ver10 = (PosRequestVer10) envelope.get(j, PosRequestVer10.class);
        }


        if (inObj.hasAttribute("clientType")) {
            Object j = inObj.getAttribute("clientType");
            if (j != null) {
                clientType = j.toString();
            }
        }

        if (inObj.hasAttribute("clientVer")) {
            Object j = inObj.getAttribute("clientVer");
            if (j != null) {
                clientVer = j.toString();
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
            info.type = PosRequestVer10.class;
            info.name = "Ver1.0";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }


    @Override
    public int getAttributeCount() {
        return 2;
    }

    @Override
    public void getAttributeInfo(int index, AttributeInfo info) {
        if (index == 0) {
            info.name = "clientType";
            info.namespace = "";
            info.setValue(clientType);
        }
        if (index == 1) {
            info.name = "clientVer";
            info.namespace = "";
            info.setValue(clientVer);
        }
    }
}
