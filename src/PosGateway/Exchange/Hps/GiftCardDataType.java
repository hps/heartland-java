package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class GiftCardDataType extends AttributeContainer implements KvmSerializable {

    public String TokenValue;

    public String CardNbr;

    public String TrackData;

    public String Alias;

    public EncryptionDataType EncryptionData;

    public String PIN;

    public GiftCardDataType() {
    }

    public GiftCardDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("TokenValue")) {
            Object obj = soapObject.getProperty("TokenValue");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenValue = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TokenValue = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardNbr")) {
            Object obj = soapObject.getProperty("CardNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("TrackData")) {
            Object obj = soapObject.getProperty("TrackData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TrackData = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TrackData = (String) obj;
            }
        }
        if (soapObject.hasProperty("Alias")) {
            Object obj = soapObject.getProperty("Alias");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Alias = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Alias = (String) obj;
            }
        }
        if (soapObject.hasProperty("EncryptionData")) {
            Object j = soapObject.getProperty("EncryptionData");
            this.EncryptionData = (EncryptionDataType) envelope.get(j, EncryptionDataType.class);
        }
        if (soapObject.hasProperty("PIN")) {
            Object obj = soapObject.getProperty("PIN");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PIN = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.PIN = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return TokenValue != null ? TokenValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return CardNbr != null ? CardNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return TrackData != null ? TrackData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return Alias != null ? Alias : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return EncryptionData != null ? EncryptionData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return PIN != null ? PIN : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TrackData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Alias";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = EncryptionDataType.class;
            info.name = "EncryptionData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PIN";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
