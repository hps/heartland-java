package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosUserDetailRspType extends AttributeContainer implements KvmSerializable {

    public String UserName;

    public String DisplayName;

    public String FirstName;

    public String LastName;

    public String EmailAddress;

    public ArrayList<RoleRspType> Roles = new ArrayList<RoleRspType>();

    public PosUserDetailRspType() {
    }

    public PosUserDetailRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("UserName")) {
            Object obj = soapObject.getProperty("UserName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.UserName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.UserName = (String) obj;
            }
        }
        if (soapObject.hasProperty("DisplayName")) {
            Object obj = soapObject.getProperty("DisplayName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DisplayName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DisplayName = (String) obj;
            }
        }
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
        if (soapObject.hasProperty("Roles")) {
            int size = soapObject.getPropertyCount();
            this.Roles = new ArrayList<RoleRspType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Roles")) {
                    Object j = info.getValue();
                    RoleRspType j1 = (RoleRspType) envelope.get(j, RoleRspType.class);
                    this.Roles.add(j1);
                }
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return UserName != null ? UserName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return DisplayName != null ? DisplayName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return FirstName != null ? FirstName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return LastName != null ? LastName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return EmailAddress != null ? EmailAddress : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +5 && propertyIndex < +5 + this.Roles.size()) {
            return Roles.get(propertyIndex - (+5));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 5 + Roles.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UserName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DisplayName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FirstName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "LastName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EmailAddress";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +5 && propertyIndex <= +5 + this.Roles.size()) {
            info.type = RoleRspType.class;
            info.name = "Roles";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
