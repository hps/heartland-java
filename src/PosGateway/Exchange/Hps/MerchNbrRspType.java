package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class MerchNbrRspType extends AttributeContainer implements KvmSerializable {
    public String MerchNbr;

    public String Name;

    public String Addr1;

    public String Addr2;

    public String City;

    public String State;

    public String Zip;

    public String CustomerSvcPhoneNbr;

    public MerchNbrRspType() {
    }

    public MerchNbrRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("MerchNbr")) {
            Object obj = soapObject.getProperty("MerchNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("Name")) {
            Object obj = soapObject.getProperty("Name");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Name = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Name = (String) obj;
            }
        }
        if (soapObject.hasProperty("Addr1")) {
            Object obj = soapObject.getProperty("Addr1");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Addr1 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Addr1 = (String) obj;
            }
        }
        if (soapObject.hasProperty("Addr2")) {
            Object obj = soapObject.getProperty("Addr2");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Addr2 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Addr2 = (String) obj;
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
        if (soapObject.hasProperty("CustomerSvcPhoneNbr")) {
            Object obj = soapObject.getProperty("CustomerSvcPhoneNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CustomerSvcPhoneNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CustomerSvcPhoneNbr = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return MerchNbr != null ? MerchNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Name != null ? Name : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return Addr1 != null ? Addr1 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return Addr2 != null ? Addr2 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return City != null ? City : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return State != null ? State : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return Zip != null ? Zip : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return CustomerSvcPhoneNbr != null ? CustomerSvcPhoneNbr : SoapPrimitive.NullSkip;
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
            info.name = "MerchNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Name";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Addr1";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Addr2";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "City";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "State";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Zip";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CustomerSvcPhoneNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
