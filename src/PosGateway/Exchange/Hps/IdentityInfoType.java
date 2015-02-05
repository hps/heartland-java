package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class IdentityInfoType extends AttributeContainer implements KvmSerializable {

    public String SSNL4;

    public String DOBYear;

    public IdentityInfoType() {
    }

    public IdentityInfoType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("SSNL4")) {
            Object obj = soapObject.getProperty("SSNL4");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SSNL4 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SSNL4 = (String) obj;
            }
        }
        if (soapObject.hasProperty("DOBYear")) {
            Object obj = soapObject.getProperty("DOBYear");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DOBYear = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DOBYear = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return SSNL4 != null ? SSNL4 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return DOBYear != null ? DOBYear : SoapPrimitive.NullSkip;
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
            info.name = "SSNL4";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DOBYear";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
