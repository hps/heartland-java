package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosReportBatchSummaryReqType extends AttributeContainer implements KvmSerializable {

    public Integer BatchId;

    public java.util.Date RptStartUtcDT;

    public java.util.Date RptEndUtcDT;

    public Integer BatchSeqNbr;

    public String ClerkID;

    public PosReportBatchSummaryReqType() {
    }

    public PosReportBatchSummaryReqType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("BatchId")) {
            Object obj = soapObject.getProperty("BatchId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BatchId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.BatchId = (Integer) obj;
            }
        }
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
        if (soapObject.hasProperty("BatchSeqNbr")) {
            Object obj = soapObject.getProperty("BatchSeqNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BatchSeqNbr = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.BatchSeqNbr = (Integer) obj;
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


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return BatchId != null ? BatchId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return RptStartUtcDT != null ? RptStartUtcDT : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return RptEndUtcDT != null ? RptEndUtcDT : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return BatchSeqNbr != null ? BatchSeqNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return ClerkID != null ? ClerkID : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "BatchId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RptStartUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RptEndUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "BatchSeqNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ClerkID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
