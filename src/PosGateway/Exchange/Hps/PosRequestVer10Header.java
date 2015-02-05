package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosRequestVer10Header extends AttributeContainer implements KvmSerializable {

    public Integer LicenseId;

    public Integer SiteId;

    public Integer DeviceId;

    public String UserName;

    public String Password;

    public String CredentialToken;

    public String SiteTrace;

    public String DeveloperID;

    public String VersionNbr;

    public String ClerkID;

    public GPSCoordinatesType GPSCoordinates;

    public Long ClientTxnId;

    public String UniqueDeviceId;

    public String SecretAPIKey;

    public PosRequestVer10Header() {
    }

    public PosRequestVer10Header(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("LicenseId")) {
            Object obj = soapObject.getProperty("LicenseId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.LicenseId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.LicenseId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("SiteId")) {
            Object obj = soapObject.getProperty("SiteId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SiteId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.SiteId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DeviceId")) {
            Object obj = soapObject.getProperty("DeviceId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DeviceId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DeviceId = (Integer) obj;
            }
        }
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
        if (soapObject.hasProperty("Password")) {
            Object obj = soapObject.getProperty("Password");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Password = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Password = (String) obj;
            }
        }
        if (soapObject.hasProperty("CredentialToken")) {
            Object obj = soapObject.getProperty("CredentialToken");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CredentialToken = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CredentialToken = (String) obj;
            }
        }
        if (soapObject.hasProperty("SiteTrace")) {
            Object obj = soapObject.getProperty("SiteTrace");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SiteTrace = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SiteTrace = (String) obj;
            }
        }
        if (soapObject.hasProperty("DeveloperID")) {
            Object obj = soapObject.getProperty("DeveloperID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DeveloperID = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DeveloperID = (String) obj;
            }
        }
        if (soapObject.hasProperty("VersionNbr")) {
            Object obj = soapObject.getProperty("VersionNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.VersionNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.VersionNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("ClerkID")) {
            Object obj = soapObject.getProperty("ClerkID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ClerkID = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ClerkID = (String) obj;
            }
        }
        if (soapObject.hasProperty("GPSCoordinates")) {
            Object j = soapObject.getProperty("GPSCoordinates");
            this.GPSCoordinates = (GPSCoordinatesType) envelope.get(j, GPSCoordinatesType.class);
        }
        if (soapObject.hasProperty("ClientTxnId")) {
            Object obj = soapObject.getProperty("ClientTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ClientTxnId = new Long(j.toString());
                }
            } else if (obj != null && obj instanceof Long) {
                this.ClientTxnId = (Long) obj;
            }
        }
        if (soapObject.hasProperty("UniqueDeviceId")) {
            Object obj = soapObject.getProperty("UniqueDeviceId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.UniqueDeviceId = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.UniqueDeviceId = (String) obj;
            }
        }
        if (soapObject.hasProperty("SecretAPIKey")) {
            Object obj = soapObject.getProperty("SecretAPIKey");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SecretAPIKey = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SecretAPIKey = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return LicenseId != null ? LicenseId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return SiteId != null ? SiteId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return DeviceId != null ? DeviceId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return UserName != null ? UserName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return Password != null ? Password : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CredentialToken != null ? CredentialToken : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return SiteTrace != null ? SiteTrace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return DeveloperID != null ? DeveloperID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return VersionNbr != null ? VersionNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return ClerkID != null ? ClerkID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return GPSCoordinates != null ? GPSCoordinates : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return ClientTxnId != null ? ClientTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return UniqueDeviceId != null ? UniqueDeviceId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return SecretAPIKey != null ? SecretAPIKey : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "LicenseId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "SiteId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UserName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Password";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CredentialToken";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SiteTrace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DeveloperID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "VersionNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ClerkID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = GPSCoordinatesType.class;
            info.name = "GPSCoordinates";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.LONG_CLASS;
            info.name = "ClientTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UniqueDeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SecretAPIKey";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
