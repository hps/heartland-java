package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class DeviceType extends AttributeContainer implements KvmSerializable {

    public Integer DeviceId = 0;

    public String IndustryCode;

    public Integer DeviceTypeId = 0;

    public String DeviceDescription;

    public String CheckProcessor;

    public ArrayList<String> CheckSEC = new ArrayList<String>();

    public DeviceType() {
    }

    public DeviceType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("IndustryCode")) {
            Object obj = soapObject.getProperty("IndustryCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IndustryCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.IndustryCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("DeviceTypeId")) {
            Object obj = soapObject.getProperty("DeviceTypeId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DeviceTypeId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DeviceTypeId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DeviceDescription")) {
            Object obj = soapObject.getProperty("DeviceDescription");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DeviceDescription = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DeviceDescription = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckProcessor")) {
            Object obj = soapObject.getProperty("CheckProcessor");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckProcessor = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CheckProcessor = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckSEC")) {
            int size = soapObject.getPropertyCount();
            this.CheckSEC = new ArrayList<String>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("CheckSEC")) {
                    Object j = info.getValue();
                    String j1 = j.toString();
                    this.CheckSEC.add(j1);
                }
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
            return IndustryCode != null ? IndustryCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return DeviceTypeId;
        }
        if (propertyIndex == 3) {
            return DeviceDescription != null ? DeviceDescription : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return CheckProcessor != null ? CheckProcessor : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +5 && propertyIndex < +5 + this.CheckSEC.size()) {
            return CheckSEC.get(propertyIndex - (+5));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 5 + CheckSEC.size();
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
            info.name = "IndustryCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DeviceTypeId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DeviceDescription";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckProcessor";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +5 && propertyIndex <= +5 + this.CheckSEC.size()) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckSEC";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
