package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class PosReportActivityRspTypeDetails extends AttributeContainer implements KvmSerializable {

    public Integer DeviceId = 0;

    public String UserName;

    public String ServiceName;

    public Integer GatewayTxnId = 0;

    public java.util.Date TxnUtcDT;

    public Integer OriginalGatewayTxnId = 0;

    public String SiteTrace;

    public Integer GatewayRspCode = 0;

    public String GatewayRspMsg;

    public String Status;

    public String IssuerRspCode;

    public String IssuerRspText;

    public String MaskedCardNbr;

    public BigDecimal Amt = BigDecimal.ZERO;

    public Enums.tzoneConversionType TzConversion = Enums.tzoneConversionType.Merchant;

    public java.util.Date TxnDT;

    public BigDecimal AuthAmt = BigDecimal.ZERO;

    public BigDecimal SettlementAmt = BigDecimal.ZERO;

    public String ClerkID;

    public CredentialDataType CredentialData;

    public String UniqueDeviceId;

    public PosReportActivityRspTypeDetails() {
    }

    public PosReportActivityRspTypeDetails(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
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
        if (soapObject.hasProperty("ServiceName")) {
            Object obj = soapObject.getProperty("ServiceName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ServiceName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ServiceName = (String) obj;
            }
        }
        if (soapObject.hasProperty("GatewayTxnId")) {
            Object obj = soapObject.getProperty("GatewayTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GatewayTxnId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.GatewayTxnId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("TxnUtcDT")) {
            Object obj = soapObject.getProperty("TxnUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.TxnUtcDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("OriginalGatewayTxnId")) {
            Object obj = soapObject.getProperty("OriginalGatewayTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.OriginalGatewayTxnId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.OriginalGatewayTxnId = (Integer) obj;
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
        if (soapObject.hasProperty("GatewayRspCode")) {
            Object obj = soapObject.getProperty("GatewayRspCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GatewayRspCode = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.GatewayRspCode = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("GatewayRspMsg")) {
            Object obj = soapObject.getProperty("GatewayRspMsg");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GatewayRspMsg = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.GatewayRspMsg = (String) obj;
            }
        }
        if (soapObject.hasProperty("Status")) {
            Object obj = soapObject.getProperty("Status");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Status = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Status = (String) obj;
            }
        }
        if (soapObject.hasProperty("IssuerRspCode")) {
            Object obj = soapObject.getProperty("IssuerRspCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IssuerRspCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.IssuerRspCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("IssuerRspText")) {
            Object obj = soapObject.getProperty("IssuerRspText");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IssuerRspText = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.IssuerRspText = (String) obj;
            }
        }
        if (soapObject.hasProperty("MaskedCardNbr")) {
            Object obj = soapObject.getProperty("MaskedCardNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MaskedCardNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MaskedCardNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("Amt")) {
            Object obj = soapObject.getProperty("Amt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Amt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.Amt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("TzConversion")) {
            Object obj = soapObject.getProperty("TzConversion");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TzConversion = Enums.tzoneConversionType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.tzoneConversionType) {
                this.TzConversion = (Enums.tzoneConversionType) obj;
            }
        }
        if (soapObject.hasProperty("TxnDT")) {
            Object obj = soapObject.getProperty("TxnDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.TxnDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("AuthAmt")) {
            Object obj = soapObject.getProperty("AuthAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AuthAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.AuthAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("SettlementAmt")) {
            Object obj = soapObject.getProperty("SettlementAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SettlementAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SettlementAmt = (BigDecimal) obj;
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
        if (soapObject.hasProperty("CredentialData")) {
            Object j = soapObject.getProperty("CredentialData");
            this.CredentialData = (CredentialDataType) envelope.get(j, CredentialDataType.class);
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


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return DeviceId;
        }
        if (propertyIndex == 1) {
            return UserName != null ? UserName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return ServiceName != null ? ServiceName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return GatewayTxnId;
        }
        if (propertyIndex == 4) {
            return TxnUtcDT;
        }
        if (propertyIndex == 5) {
            return OriginalGatewayTxnId;
        }
        if (propertyIndex == 6) {
            return SiteTrace != null ? SiteTrace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return GatewayRspCode;
        }
        if (propertyIndex == 8) {
            return GatewayRspMsg != null ? GatewayRspMsg : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return Status != null ? Status : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return IssuerRspCode != null ? IssuerRspCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return IssuerRspText != null ? IssuerRspText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return MaskedCardNbr != null ? MaskedCardNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return TzConversion != null ? TzConversion.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return TxnDT;
        }
        if (propertyIndex == 16) {
            return AuthAmt != null ? AuthAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 17) {
            return SettlementAmt != null ? SettlementAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return ClerkID != null ? ClerkID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 19) {
            return CredentialData != null ? CredentialData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 20) {
            return UniqueDeviceId != null ? UniqueDeviceId : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 21;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UserName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ServiceName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "OriginalGatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SiteTrace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayRspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GatewayRspMsg";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Status";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IssuerRspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IssuerRspText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MaskedCardNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TzConversion";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SettlementAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ClerkID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = CredentialDataType.class;
            info.name = "CredentialData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UniqueDeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
