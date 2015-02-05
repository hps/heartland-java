package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class RecurringBillReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public CardDataType CardData;

    public BigDecimal Amt = BigDecimal.ZERO;

    public String PaymentMethodKey;

    public PaymentMethodKeyData PaymentMethodKeyData;

    public CardHolderDataType CardHolderData;

    public Enums.booleanType AllowDup;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public RecurringDataType RecurringData;

    public RecurringBillReqBlock1Type() {
    }

    public RecurringBillReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("PaymentMethodKey")) {
            Object obj = soapObject.getProperty("PaymentMethodKey");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PaymentMethodKey = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.PaymentMethodKey = (String) obj;
            }
        }
        if (soapObject.hasProperty("PaymentMethodKeyData")) {
            Object j = soapObject.getProperty("PaymentMethodKeyData");
            this.PaymentMethodKeyData = (PaymentMethodKeyData) envelope.get(j, PaymentMethodKeyData.class);
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
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
        }
        if (soapObject.hasProperty("RecurringData")) {
            Object j = soapObject.getProperty("RecurringData");
            this.RecurringData = (RecurringDataType) envelope.get(j, RecurringDataType.class);
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
            return PaymentMethodKey != null ? PaymentMethodKey : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return PaymentMethodKeyData != null ? PaymentMethodKeyData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return CardHolderData != null ? CardHolderData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return AllowDup != null ? AllowDup.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return RecurringData != null ? RecurringData : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 8;
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
            info.name = "PaymentMethodKey";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PaymentMethodKeyData.class;
            info.name = "PaymentMethodKeyData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = CardHolderDataType.class;
            info.name = "CardHolderData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowDup";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = RecurringDataType.class;
            info.name = "RecurringData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
