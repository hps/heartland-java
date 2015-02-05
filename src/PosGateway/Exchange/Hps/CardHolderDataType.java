package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class CardHolderDataType extends AttributeContainer implements KvmSerializable {

    public String CardHolderFirstName;

    public String CardHolderLastName;

    public String CardHolderAddr;

    public String CardHolderCity;

    public String CardHolderState;

    public String CardHolderZip;

    public String CardHolderPhone;

    public String CardHolderEmail;

    public CardHolderDataType() {
    }

    public CardHolderDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("CardHolderFirstName")) {
            Object obj = soapObject.getProperty("CardHolderFirstName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderFirstName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderFirstName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderLastName")) {
            Object obj = soapObject.getProperty("CardHolderLastName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderLastName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderLastName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderAddr")) {
            Object obj = soapObject.getProperty("CardHolderAddr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderAddr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderAddr = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderCity")) {
            Object obj = soapObject.getProperty("CardHolderCity");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderCity = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderCity = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderState")) {
            Object obj = soapObject.getProperty("CardHolderState");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderState = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderState = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderZip")) {
            Object obj = soapObject.getProperty("CardHolderZip");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderZip = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderZip = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderPhone")) {
            Object obj = soapObject.getProperty("CardHolderPhone");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderPhone = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderPhone = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderEmail")) {
            Object obj = soapObject.getProperty("CardHolderEmail");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderEmail = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderEmail = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return CardHolderFirstName != null ? CardHolderFirstName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return CardHolderLastName != null ? CardHolderLastName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return CardHolderAddr != null ? CardHolderAddr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CardHolderCity != null ? CardHolderCity : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return CardHolderState != null ? CardHolderState : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CardHolderZip != null ? CardHolderZip : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return CardHolderPhone != null ? CardHolderPhone : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return CardHolderEmail != null ? CardHolderEmail : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderFirstName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderLastName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderAddr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderCity";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderState";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderZip";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderPhone";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderEmail";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
