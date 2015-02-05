package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosReportActivityRspTypeHeader extends AttributeContainer implements KvmSerializable {

    public java.util.Date RptStartUtcDT;

    public java.util.Date RptEndUtcDT;

    public Integer SiteId = 0;

    public String MerchName;

    public Integer DeviceId;

    public Integer TxnCnt = 0;

    public PosReportActivityRspTypeHeader() {
    }

    public PosReportActivityRspTypeHeader(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("RptStartUtcDT")) {
            Object obj = soapObject.getProperty("RptStartUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RptStartUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.RptStartUtcDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("RptEndUtcDT")) {
            Object obj = soapObject.getProperty("RptEndUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RptEndUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.RptEndUtcDT = (java.util.Date) obj;
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
        if (soapObject.hasProperty("TxnCnt")) {
            Object obj = soapObject.getProperty("TxnCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.TxnCnt = (Integer) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return RptStartUtcDT;
        }
        if (propertyIndex == 1) {
            return RptEndUtcDT;
        }
        if (propertyIndex == 2) {
            return SiteId;
        }
        if (propertyIndex == 3) {
            return MerchName != null ? MerchName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return DeviceId != null ? DeviceId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return TxnCnt;
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
            info.name = "RptStartUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RptEndUtcDT";
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
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TxnCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
