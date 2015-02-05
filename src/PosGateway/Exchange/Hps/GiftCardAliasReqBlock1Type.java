package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class GiftCardAliasReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public Enums.GiftCardAliasReqBlock1TypeAction Action = Enums.GiftCardAliasReqBlock1TypeAction.DELETE;

    public GiftCardDataType CardData;

    public String Alias;

    public GiftCardAliasReqBlock1Type() {
    }

    public GiftCardAliasReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Action")) {
            Object obj = soapObject.getProperty("Action");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Action = Enums.GiftCardAliasReqBlock1TypeAction.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.GiftCardAliasReqBlock1TypeAction) {
                this.Action = (Enums.GiftCardAliasReqBlock1TypeAction) obj;
            }
        }
        if (soapObject.hasProperty("CardData")) {
            Object j = soapObject.getProperty("CardData");
            this.CardData = (GiftCardDataType) envelope.get(j, GiftCardDataType.class);
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
    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Action != null ? Action.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return CardData != null ? CardData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return Alias != null ? Alias : SoapPrimitive.NullSkip;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Action";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = GiftCardDataType.class;
            info.name = "CardData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Alias";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
