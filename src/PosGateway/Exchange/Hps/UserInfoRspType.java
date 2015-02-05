package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class UserInfoRspType extends AttributeContainer implements KvmSerializable {

    public String DisplayName;

    public String FirstName;

    public String LastName;

    public UserInfoRspType() {
    }

    public UserInfoRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("DisplayName")) {
            Object obj = soapObject.getProperty("DisplayName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DisplayName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DisplayName = (String) obj;
            }
        }
        if (soapObject.hasProperty("FirstName")) {
            Object obj = soapObject.getProperty("FirstName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.FirstName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.FirstName = (String) obj;
            }
        }
        if (soapObject.hasProperty("LastName")) {
            Object obj = soapObject.getProperty("LastName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.LastName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.LastName = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return DisplayName != null ? DisplayName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return FirstName != null ? FirstName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return LastName != null ? LastName : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DisplayName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FirstName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "LastName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
