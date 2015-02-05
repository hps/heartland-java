package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class LodgingDataEditType extends AttributeContainer implements KvmSerializable {

    public String FolioNumber;

    public Integer Duration;

    public java.util.Date CheckInDate;

    public java.util.Date CheckOutDate;

    public BigDecimal Rate;

    public ExtraChargesDataType ExtraCharges;

    public BigDecimal ExtraChargeAmtInfo;

    public LodgingDataEditType() {
    }

    public LodgingDataEditType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("FolioNumber")) {
            Object obj = soapObject.getProperty("FolioNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.FolioNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.FolioNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("Duration")) {
            Object obj = soapObject.getProperty("Duration");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Duration = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.Duration = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("CheckInDate")) {
            Object obj = soapObject.getProperty("CheckInDate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckInDate = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.CheckInDate = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("CheckOutDate")) {
            Object obj = soapObject.getProperty("CheckOutDate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckOutDate = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.CheckOutDate = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("Rate")) {
            Object obj = soapObject.getProperty("Rate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Rate = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.Rate = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("ExtraCharges")) {
            Object j = soapObject.getProperty("ExtraCharges");
            this.ExtraCharges = (ExtraChargesDataType) envelope.get(j, ExtraChargesDataType.class);
        }
        if (soapObject.hasProperty("ExtraChargeAmtInfo")) {
            Object obj = soapObject.getProperty("ExtraChargeAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ExtraChargeAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ExtraChargeAmtInfo = (BigDecimal) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return FolioNumber != null ? FolioNumber : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Duration != null ? Duration : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return CheckInDate != null ? CheckInDate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CheckOutDate != null ? CheckOutDate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return Rate != null ? Rate.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return ExtraCharges != null ? ExtraCharges : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return ExtraChargeAmtInfo != null ? ExtraChargeAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 7;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FolioNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "Duration";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckInDate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckOutDate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Rate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = ExtraChargesDataType.class;
            info.name = "ExtraCharges";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ExtraChargeAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
