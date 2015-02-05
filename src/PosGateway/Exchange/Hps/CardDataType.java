package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class CardDataType extends AttributeContainer implements KvmSerializable {

    public CardDataTypeTrackData TrackData;

    public CardDataTypeTokenData TokenData;

    public CardDataTypeManualEntry ManualEntry;

    public EncryptionDataType EncryptionData;

    public Enums.booleanType TokenRequest;

    public TokenParametersType TokenParameters;

    public CardDataType() {
    }

    public CardDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("TrackData")) {
            Object obj = soapObject.getProperty("TrackData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TrackData = (CardDataTypeTrackData) envelope.get(j, CardDataTypeTrackData.class);
                }
            }
        }
        if (soapObject.hasProperty("TokenData")) {
            Object j = soapObject.getProperty("TokenData");
            this.TokenData = (CardDataTypeTokenData) envelope.get(j, CardDataTypeTokenData.class);
        }
        if (soapObject.hasProperty("ManualEntry")) {
            Object j = soapObject.getProperty("ManualEntry");
            this.ManualEntry = (CardDataTypeManualEntry) envelope.get(j, CardDataTypeManualEntry.class);
        }
        if (soapObject.hasProperty("EncryptionData")) {
            Object j = soapObject.getProperty("EncryptionData");
            this.EncryptionData = (EncryptionDataType) envelope.get(j, EncryptionDataType.class);
        }
        if (soapObject.hasProperty("TokenRequest")) {
            Object obj = soapObject.getProperty("TokenRequest");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenRequest = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.TokenRequest = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("TokenParameters")) {
            Object j = soapObject.getProperty("TokenParameters");
            this.TokenParameters = (TokenParametersType) envelope.get(j, TokenParametersType.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return TrackData != null ? TrackData.getSimpleValue() : null;
        }
        if (propertyIndex == 1) {
            return TokenData != null ? TokenData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return ManualEntry != null ? ManualEntry : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return EncryptionData != null ? EncryptionData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return TokenRequest != null ? TokenRequest.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return TokenParameters != null ? TokenParameters : SoapPrimitive.NullSkip;
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
            info.type = CardDataTypeTrackData.class;
            info.name = "TrackData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = CardDataTypeTokenData.class;
            info.name = "TokenData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = CardDataTypeManualEntry.class;
            info.name = "ManualEntry";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = EncryptionDataType.class;
            info.name = "EncryptionData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenRequest";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = TokenParametersType.class;
            info.name = "TokenParameters";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
