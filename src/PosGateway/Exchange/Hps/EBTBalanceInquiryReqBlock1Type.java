package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class EBTBalanceInquiryReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public CardDataType CardData;

    public BigDecimal Amt = BigDecimal.ZERO;

    public String PinBlock;

    public Enums.EBTBalanceInquiryType BalanceInquiryType = Enums.EBTBalanceInquiryType.CASH;

    public EBTBalanceInquiryReqBlock1Type() {
    }

    public EBTBalanceInquiryReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("BalanceInquiryType")) {
            Object obj = soapObject.getProperty("BalanceInquiryType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BalanceInquiryType = Enums.EBTBalanceInquiryType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.EBTBalanceInquiryType) {
                this.BalanceInquiryType = (Enums.EBTBalanceInquiryType) obj;
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
            return PinBlock != null ? PinBlock : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return BalanceInquiryType != null ? BalanceInquiryType.toString() : SoapPrimitive.NullSkip;
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
            info.name = "BalanceInquiryType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
