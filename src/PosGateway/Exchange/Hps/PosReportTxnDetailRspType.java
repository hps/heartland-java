package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosReportTxnDetailRspType extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId = 0;

    public Long ClientTxnId = 0L;

    public Integer SiteId = 0;

    public String MerchName;

    public Integer DeviceId = 0;

    public String UserName;

    public String ServiceName;

    public Integer GatewayRspCode = 0;

    public String GatewayRspMsg;

    public java.util.Date ReqUtcDT;

    public java.util.Date ReqDT;

    public java.util.Date RspUtcDT;

    public java.util.Date RspDT;

    public String SiteTrace;

    public Integer OriginalGatewayTxnId = 0;

    public String MerchNbr;

    public Integer TermOrdinal = 0;

    public String MerchAddr1;

    public String MerchAddr2;

    public String MerchCity;

    public String MerchState;

    public String MerchZip;

    public String MerchPhone;

    public Enums.tzoneConversionType TzConversion = Enums.tzoneConversionType.Merchant;

    public String UniqueDeviceId;

    public PosReportTxnDetailRspTypeData Data;

    public PosReportTxnDetailRspType() {
    }

    public PosReportTxnDetailRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
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
        if (soapObject.hasProperty("MerchName")) {
            Object obj = soapObject.getProperty("MerchName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchName = (String) obj;
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
        if (soapObject.hasProperty("ReqUtcDT")) {
            Object obj = soapObject.getProperty("ReqUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReqUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.ReqUtcDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("ReqDT")) {
            Object obj = soapObject.getProperty("ReqDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReqDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.ReqDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("RspUtcDT")) {
            Object obj = soapObject.getProperty("RspUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.RspUtcDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("RspDT")) {
            Object obj = soapObject.getProperty("RspDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.RspDT = (java.util.Date) obj;
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
        if (soapObject.hasProperty("TermOrdinal")) {
            Object obj = soapObject.getProperty("TermOrdinal");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TermOrdinal = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.TermOrdinal = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("MerchAddr1")) {
            Object obj = soapObject.getProperty("MerchAddr1");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchAddr1 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchAddr1 = (String) obj;
            }
        }
        if (soapObject.hasProperty("MerchAddr2")) {
            Object obj = soapObject.getProperty("MerchAddr2");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchAddr2 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchAddr2 = (String) obj;
            }
        }
        if (soapObject.hasProperty("MerchCity")) {
            Object obj = soapObject.getProperty("MerchCity");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchCity = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchCity = (String) obj;
            }
        }
        if (soapObject.hasProperty("MerchState")) {
            Object obj = soapObject.getProperty("MerchState");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchState = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchState = (String) obj;
            }
        }
        if (soapObject.hasProperty("MerchZip")) {
            Object obj = soapObject.getProperty("MerchZip");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchZip = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchZip = (String) obj;
            }
        }
        if (soapObject.hasProperty("MerchPhone")) {
            Object obj = soapObject.getProperty("MerchPhone");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchPhone = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchPhone = (String) obj;
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
        if (soapObject.hasProperty("Data")) {
            Object j = soapObject.getProperty("Data");
            this.Data = (PosReportTxnDetailRspTypeData) envelope.get(j, PosReportTxnDetailRspTypeData.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return GatewayTxnId;
        }
        if (propertyIndex == 1) {
            return ClientTxnId;
        }
        if (propertyIndex == 2) {
            return SiteId;
        }
        if (propertyIndex == 3) {
            return MerchName != null ? MerchName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return DeviceId;
        }
        if (propertyIndex == 5) {
            return UserName != null ? UserName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return ServiceName != null ? ServiceName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return GatewayRspCode;
        }
        if (propertyIndex == 8) {
            return GatewayRspMsg != null ? GatewayRspMsg : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return ReqUtcDT;
        }
        if (propertyIndex == 10) {
            return ReqDT;
        }
        if (propertyIndex == 11) {
            return RspUtcDT;
        }
        if (propertyIndex == 12) {
            return RspDT;
        }
        if (propertyIndex == 13) {
            return SiteTrace != null ? SiteTrace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return OriginalGatewayTxnId;
        }
        if (propertyIndex == 15) {
            return MerchNbr != null ? MerchNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 16) {
            return TermOrdinal;
        }
        if (propertyIndex == 17) {
            return MerchAddr1 != null ? MerchAddr1 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return MerchAddr2 != null ? MerchAddr2 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 19) {
            return MerchCity != null ? MerchCity : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 20) {
            return MerchState != null ? MerchState : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 21) {
            return MerchZip != null ? MerchZip : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 22) {
            return MerchPhone != null ? MerchPhone : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 23) {
            return TzConversion != null ? TzConversion.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 24) {
            return UniqueDeviceId != null ? UniqueDeviceId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 25) {
            return Data != null ? Data : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 26;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.LONG_CLASS;
            info.name = "ClientTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "SiteId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UserName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ServiceName";
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
            info.name = "ReqUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReqDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SiteTrace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "OriginalGatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TermOrdinal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchAddr1";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchAddr2";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchCity";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchState";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 21) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchZip";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 22) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchPhone";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 23) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TzConversion";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 24) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UniqueDeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 25) {
            info.type = PosReportTxnDetailRspTypeData.class;
            info.name = "Data";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
