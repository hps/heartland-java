package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class CredentialDataType extends AttributeContainer implements KvmSerializable {

    public String DisplayName;

    public String ImpersonatedDisplayName;

    public CredentialDataType() {
    }

    public CredentialDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("ImpersonatedDisplayName")) {
            Object obj = soapObject.getProperty("ImpersonatedDisplayName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ImpersonatedDisplayName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ImpersonatedDisplayName = (String) obj;
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
            return ImpersonatedDisplayName != null ? ImpersonatedDisplayName : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 2;
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
            info.name = "ImpersonatedDisplayName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
