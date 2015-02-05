package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class CreditAccountVerifyBlock1Type extends AttributeContainer implements KvmSerializable {

    public CardDataType CardData;

    public CardHolderDataType CardHolderData;

    public String PaymentMethodKey;

    public PaymentMethodKeyData PaymentMethodKeyData;

    public CreditAccountVerifyBlock1Type() {
    }

    public CreditAccountVerifyBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("CardData")) {
            Object j = soapObject.getProperty("CardData");
            this.CardData = (CardDataType) envelope.get(j, CardDataType.class);
        }
        if (soapObject.hasProperty("CardHolderData")) {
            Object j = soapObject.getProperty("CardHolderData");
            this.CardHolderData = (CardHolderDataType) envelope.get(j, CardHolderDataType.class);
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


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return CardData != null ? CardData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return CardHolderData != null ? CardHolderData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return PaymentMethodKey != null ? PaymentMethodKey : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return PaymentMethodKeyData != null ? PaymentMethodKeyData : SoapPrimitive.NullSkip;
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
            info.type = CardDataType.class;
            info.name = "CardData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = CardHolderDataType.class;
            info.name = "CardHolderData";
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
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
