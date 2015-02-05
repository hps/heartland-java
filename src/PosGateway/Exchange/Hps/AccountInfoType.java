package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class AccountInfoType extends AttributeContainer implements KvmSerializable {
    public String RoutingNumber;

    public String AccountNumber;

    public String CheckNumber;

    public String MICRData;

    public Enums.accountTypeType AccountType;

    public AccountInfoType() {
    }

    public AccountInfoType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {
        if (inObj == null)
            return;

        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("RoutingNumber")) {
            Object obj = soapObject.getProperty("RoutingNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RoutingNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RoutingNumber = (String) obj;
            }
        }

        if (soapObject.hasProperty("AccountNumber")) {
            Object obj = soapObject.getProperty("AccountNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AccountNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AccountNumber = (String) obj;
            }
        }

        if (soapObject.hasProperty("CheckNumber")) {
            Object obj = soapObject.getProperty("CheckNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CheckNumber = (String) obj;
            }
        }

        if (soapObject.hasProperty("MICRData")) {
            Object obj = soapObject.getProperty("MICRData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MICRData = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MICRData = (String) obj;
            }
        }

        if (soapObject.hasProperty("AccountType")) {
            Object obj = soapObject.getProperty("AccountType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AccountType = Enums.accountTypeType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.accountTypeType) {
                this.AccountType = (Enums.accountTypeType) obj;
            }
        }
    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return RoutingNumber != null ? RoutingNumber : SoapPrimitive.NullSkip;
        }

        if (propertyIndex == 1) {
            return AccountNumber != null ? AccountNumber : SoapPrimitive.NullSkip;
        }

        if (propertyIndex == 2) {
            return CheckNumber != null ? CheckNumber : SoapPrimitive.NullSkip;
        }

        if (propertyIndex == 3) {
            return MICRData != null ? MICRData : SoapPrimitive.NullSkip;
        }

        if (propertyIndex == 4) {
            return AccountType != null ? AccountType.toString() : SoapPrimitive.NullSkip;
        }

        return null;
    }


    @Override
    public int getPropertyCount() {
        return 5;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RoutingNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AccountNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MICRData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AccountType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }
}
