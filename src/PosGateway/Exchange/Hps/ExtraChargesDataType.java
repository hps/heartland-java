package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class ExtraChargesDataType extends AttributeContainer implements KvmSerializable {

    public Enums.booleanType Restaurant;

    public Enums.booleanType GiftShop;

    public Enums.booleanType MiniBar;

    public Enums.booleanType Telephone;

    public Enums.booleanType Other;

    public Enums.booleanType Laundry;

    public ExtraChargesDataType() {
    }

    public ExtraChargesDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Restaurant")) {
            Object obj = soapObject.getProperty("Restaurant");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Restaurant = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.Restaurant = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("GiftShop")) {
            Object obj = soapObject.getProperty("GiftShop");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GiftShop = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.GiftShop = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("MiniBar")) {
            Object obj = soapObject.getProperty("MiniBar");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MiniBar = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.MiniBar = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("Telephone")) {
            Object obj = soapObject.getProperty("Telephone");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Telephone = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.Telephone = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("Other")) {
            Object obj = soapObject.getProperty("Other");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Other = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.Other = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("Laundry")) {
            Object obj = soapObject.getProperty("Laundry");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Laundry = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.Laundry = (Enums.booleanType) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Restaurant != null ? Restaurant.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return GiftShop != null ? GiftShop.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return MiniBar != null ? MiniBar.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return Telephone != null ? Telephone.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return Other != null ? Other.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return Laundry != null ? Laundry.toString() : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Restaurant";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GiftShop";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MiniBar";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Telephone";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Other";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Laundry";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
