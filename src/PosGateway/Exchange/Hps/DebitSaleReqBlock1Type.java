package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class DebitSaleReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public String TrackData;

    public BigDecimal Amt = BigDecimal.ZERO;

    public String PinBlock;

    public String TokenValue;

    public BigDecimal CashbackAmtInfo;

    public CardHolderDataType CardHolderData;

    public Enums.booleanType AllowDup;

    public LodgingDataType LodgingData;

    public Enums.booleanType AllowPartialAuth;

    public EncryptionDataType EncryptionData;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public DebitSaleReqBlock1Type() {
    }

    public DebitSaleReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
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
        if (soapObject.hasProperty("Amt")) {
            Object obj = soapObject.getProperty("Amt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Amt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.Amt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("PinBlock")) {
            Object obj = soapObject.getProperty("PinBlock");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PinBlock = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.PinBlock = (String) obj;
            }
        }
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
        if (soapObject.hasProperty("CashbackAmtInfo")) {
            Object obj = soapObject.getProperty("CashbackAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CashbackAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.CashbackAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderData")) {
            Object j = soapObject.getProperty("CardHolderData");
            this.CardHolderData = (CardHolderDataType) envelope.get(j, CardHolderDataType.class);
        }
        if (soapObject.hasProperty("AllowDup")) {
            Object obj = soapObject.getProperty("AllowDup");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AllowDup = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.AllowDup = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("LodgingData")) {
            Object j = soapObject.getProperty("LodgingData");
            this.LodgingData = (LodgingDataType) envelope.get(j, LodgingDataType.class);
        }
        if (soapObject.hasProperty("AllowPartialAuth")) {
            Object obj = soapObject.getProperty("AllowPartialAuth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AllowPartialAuth = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.AllowPartialAuth = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("EncryptionData")) {
            Object j = soapObject.getProperty("EncryptionData");
            this.EncryptionData = (EncryptionDataType) envelope.get(j, EncryptionDataType.class);
        }
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return TrackData != null ? TrackData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return PinBlock != null ? PinBlock : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return TokenValue != null ? TokenValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return CashbackAmtInfo != null ? CashbackAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CardHolderData != null ? CardHolderData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return AllowDup != null ? AllowDup.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return LodgingData != null ? LodgingData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return AllowPartialAuth != null ? AllowPartialAuth.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return EncryptionData != null ? EncryptionData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 11;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TrackData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PinBlock";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CashbackAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = CardHolderDataType.class;
            info.name = "CardHolderData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowDup";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = LodgingDataType.class;
            info.name = "LodgingData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowPartialAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = EncryptionDataType.class;
            info.name = "EncryptionData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
