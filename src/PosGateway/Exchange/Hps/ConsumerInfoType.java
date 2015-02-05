package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class ConsumerInfoType extends AttributeContainer implements KvmSerializable {

    public String FirstName;

    public String LastName;

    public String CheckName;

    public String Address1;

    public String Address2;

    public String City;

    public String State;

    public String Zip;

    public String PhoneNumber;

    public String EmailAddress;

    public String DLState;

    public String DLNumber;

    public String CourtesyCard;

    public IdentityInfoType IdentityInfo;

    public ConsumerInfoType() {
    }

    public ConsumerInfoType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("FirstName")) {
            Object obj = soapObject.getProperty("FirstName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.FirstName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.FirstName = (String) obj;
            }
        }
        if (soapObject.hasProperty("LastName")) {
            Object obj = soapObject.getProperty("LastName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.LastName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.LastName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckName")) {
            Object obj = soapObject.getProperty("CheckName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CheckName = (String) obj;
            }
        }
        if (soapObject.hasProperty("Address1")) {
            Object obj = soapObject.getProperty("Address1");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Address1 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Address1 = (String) obj;
            }
        }
        if (soapObject.hasProperty("Address2")) {
            Object obj = soapObject.getProperty("Address2");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Address2 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Address2 = (String) obj;
            }
        }
        if (soapObject.hasProperty("City")) {
            Object obj = soapObject.getProperty("City");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.City = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.City = (String) obj;
            }
        }
        if (soapObject.hasProperty("State")) {
            Object obj = soapObject.getProperty("State");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.State = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.State = (String) obj;
            }
        }
        if (soapObject.hasProperty("Zip")) {
            Object obj = soapObject.getProperty("Zip");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Zip = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Zip = (String) obj;
            }
        }
        if (soapObject.hasProperty("PhoneNumber")) {
            Object obj = soapObject.getProperty("PhoneNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PhoneNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.PhoneNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("EmailAddress")) {
            Object obj = soapObject.getProperty("EmailAddress");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.EmailAddress = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.EmailAddress = (String) obj;
            }
        }
        if (soapObject.hasProperty("DLState")) {
            Object obj = soapObject.getProperty("DLState");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DLState = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DLState = (String) obj;
            }
        }
        if (soapObject.hasProperty("DLNumber")) {
            Object obj = soapObject.getProperty("DLNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DLNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DLNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("CourtesyCard")) {
            Object obj = soapObject.getProperty("CourtesyCard");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CourtesyCard = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CourtesyCard = (String) obj;
            }
        }
        if (soapObject.hasProperty("IdentityInfo")) {
            Object j = soapObject.getProperty("IdentityInfo");
            this.IdentityInfo = (IdentityInfoType) envelope.get(j, IdentityInfoType.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return FirstName != null ? FirstName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return LastName != null ? LastName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return CheckName != null ? CheckName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return Address1 != null ? Address1 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return Address2 != null ? Address2 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return City != null ? City : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return State != null ? State : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return Zip != null ? Zip : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return PhoneNumber != null ? PhoneNumber : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return EmailAddress != null ? EmailAddress : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return DLState != null ? DLState : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return DLNumber != null ? DLNumber : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return CourtesyCard != null ? CourtesyCard : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return IdentityInfo != null ? IdentityInfo : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 14;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FirstName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "LastName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Address1";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Address2";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "City";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "State";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Zip";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PhoneNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EmailAddress";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DLState";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DLNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CourtesyCard";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = IdentityInfoType.class;
            info.name = "IdentityInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
