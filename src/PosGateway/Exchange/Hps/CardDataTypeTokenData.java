package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class CardDataTypeTokenData extends AttributeContainer implements KvmSerializable {

    public String TokenValue;

    public Integer ExpMonth;

    public Integer ExpYear;

    public Enums.booleanType CardPresent;

    public Enums.booleanType ReaderPresent;

    public String CVV2;

    public Enums.cvv2Status CVV2Status;

    public CardDataTypeTokenData() {
    }

    public CardDataTypeTokenData(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("ExpMonth")) {
            Object obj = soapObject.getProperty("ExpMonth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ExpMonth = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.ExpMonth = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("ExpYear")) {
            Object obj = soapObject.getProperty("ExpYear");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ExpYear = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.ExpYear = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("CardPresent")) {
            Object obj = soapObject.getProperty("CardPresent");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardPresent = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.CardPresent = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("ReaderPresent")) {
            Object obj = soapObject.getProperty("ReaderPresent");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReaderPresent = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.ReaderPresent = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("CVV2")) {
            Object obj = soapObject.getProperty("CVV2");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVV2 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CVV2 = (String) obj;
            }
        }
        if (soapObject.hasProperty("CVV2Status")) {
            Object obj = soapObject.getProperty("CVV2Status");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVV2Status = Enums.cvv2Status.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.cvv2Status) {
                this.CVV2Status = (Enums.cvv2Status) obj;
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
            return ExpMonth != null ? ExpMonth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return ExpYear != null ? ExpYear : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CardPresent != null ? CardPresent.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return ReaderPresent != null ? ReaderPresent.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CVV2 != null ? CVV2 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return CVV2Status != null ? CVV2Status.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 7;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "ExpMonth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "ExpYear";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardPresent";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReaderPresent";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVV2";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVV2Status";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
