package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosReportActivityReqType extends AttributeContainer implements KvmSerializable {

    public java.util.Date RptStartUtcDT;

    public java.util.Date RptEndUtcDT;

    public Integer DeviceId;

    public Enums.tzoneConversionType TzConversion;

    public PosReportActivityReqType() {
    }

    public PosReportActivityReqType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return RptStartUtcDT != null ? RptStartUtcDT : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return RptEndUtcDT != null ? RptEndUtcDT : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return DeviceId != null ? DeviceId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return TzConversion != null ? TzConversion.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 4;
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
            info.name = "DeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TzConversion";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
