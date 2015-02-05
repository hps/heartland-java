package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class GiftCardDataRspType extends AttributeContainer implements KvmSerializable {

    public String CardNbr;

    public String Alias;

    public String PIN;

    public GiftCardDataRspType() {
    }

    public GiftCardDataRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
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
            return CardNbr != null ? CardNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Alias != null ? Alias : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return PIN != null ? PIN : SoapPrimitive.NullSkip;
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
            info.name = "CardNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Alias";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PIN";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
