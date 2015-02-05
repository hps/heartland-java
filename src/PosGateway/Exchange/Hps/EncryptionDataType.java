package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class EncryptionDataType extends AttributeContainer implements KvmSerializable {

    public String Version;

    public String EncryptedTrackNumber;

    public String KTB;

    public String KSN;

    public EncryptionDataType() {
    }

    public EncryptionDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Version")) {
            Object obj = soapObject.getProperty("Version");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Version = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Version = (String) obj;
            }
        }
        if (soapObject.hasProperty("EncryptedTrackNumber")) {
            Object obj = soapObject.getProperty("EncryptedTrackNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.EncryptedTrackNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.EncryptedTrackNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("KTB")) {
            Object obj = soapObject.getProperty("KTB");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.KTB = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.KTB = (String) obj;
            }
        }
        if (soapObject.hasProperty("KSN")) {
            Object obj = soapObject.getProperty("KSN");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.KSN = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.KSN = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Version != null ? Version : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return EncryptedTrackNumber != null ? EncryptedTrackNumber : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return KTB != null ? KTB : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return KSN != null ? KSN : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Version";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EncryptedTrackNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "KTB";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "KSN";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
