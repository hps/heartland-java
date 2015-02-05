package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class CreditOfflineSaleReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public CardDataType CardData;

    public BigDecimal Amt = BigDecimal.ZERO;

    public BigDecimal GratuityAmtInfo;

    public Enums.booleanType CPCReq;

    public String OfflineAuthCode;

    public CardHolderDataType CardHolderData;

    public DirectMktDataType DirectMktData;

    public Enums.booleanType AllowDup;

    public LodgingDataType LodgingData;

    public AutoSubstantiationType AutoSubstantiation;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public Enums.eCommerceType Ecommerce;

    public BigDecimal ConvenienceAmtInfo;

    public BigDecimal ShippingAmtInfo;

    public BigDecimal SurchargeAmtInfo;

    public CreditOfflineSaleReqBlock1Type() {
    }

    public CreditOfflineSaleReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("CardData")) {
            Object j = soapObject.getProperty("CardData");
            this.CardData = (CardDataType) envelope.get(j, CardDataType.class);
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
        if (soapObject.hasProperty("GratuityAmtInfo")) {
            Object obj = soapObject.getProperty("GratuityAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GratuityAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.GratuityAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("CPCReq")) {
            Object obj = soapObject.getProperty("CPCReq");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CPCReq = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.CPCReq = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("OfflineAuthCode")) {
            Object obj = soapObject.getProperty("OfflineAuthCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.OfflineAuthCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.OfflineAuthCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderData")) {
            Object j = soapObject.getProperty("CardHolderData");
            this.CardHolderData = (CardHolderDataType) envelope.get(j, CardHolderDataType.class);
        }
        if (soapObject.hasProperty("DirectMktData")) {
            Object j = soapObject.getProperty("DirectMktData");
            this.DirectMktData = (DirectMktDataType) envelope.get(j, DirectMktDataType.class);
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
        if (soapObject.hasProperty("AutoSubstantiation")) {
            Object j = soapObject.getProperty("AutoSubstantiation");
            this.AutoSubstantiation = (AutoSubstantiationType) envelope.get(j, AutoSubstantiationType.class);
        }
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
        }
        if (soapObject.hasProperty("Ecommerce")) {
            Object obj = soapObject.getProperty("Ecommerce");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Ecommerce = Enums.eCommerceType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.eCommerceType) {
                this.Ecommerce = (Enums.eCommerceType) obj;
            }
        }
        if (soapObject.hasProperty("ConvenienceAmtInfo")) {
            Object obj = soapObject.getProperty("ConvenienceAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ConvenienceAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ConvenienceAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("ShippingAmtInfo")) {
            Object obj = soapObject.getProperty("ShippingAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ShippingAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ShippingAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("SurchargeAmtInfo")) {
            Object obj = soapObject.getProperty("SurchargeAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SurchargeAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SurchargeAmtInfo = (BigDecimal) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return CardData != null ? CardData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return GratuityAmtInfo != null ? GratuityAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CPCReq != null ? CPCReq.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return OfflineAuthCode != null ? OfflineAuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CardHolderData != null ? CardHolderData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return DirectMktData != null ? DirectMktData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return AllowDup != null ? AllowDup.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return LodgingData != null ? LodgingData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return AutoSubstantiation != null ? AutoSubstantiation : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return Ecommerce != null ? Ecommerce.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return ConvenienceAmtInfo != null ? ConvenienceAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return ShippingAmtInfo != null ? ShippingAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return SurchargeAmtInfo != null ? SurchargeAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 15;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = CardDataType.class;
            info.name = "CardData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GratuityAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCReq";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "OfflineAuthCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = CardHolderDataType.class;
            info.name = "CardHolderData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = DirectMktDataType.class;
            info.name = "DirectMktData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowDup";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = LodgingDataType.class;
            info.name = "LodgingData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = AutoSubstantiationType.class;
            info.name = "AutoSubstantiation";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Ecommerce";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ConvenienceAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ShippingAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SurchargeAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
