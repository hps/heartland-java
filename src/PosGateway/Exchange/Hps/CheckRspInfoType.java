package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class CheckRspInfoType extends AttributeContainer implements KvmSerializable {

    public String Type;

    public String Code;

    public String Message;

    public String FieldNumber;

    public String FieldName;

    public CheckRspInfoType() {
    }

    public CheckRspInfoType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Type")) {
            Object obj = soapObject.getProperty("Type");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Type = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Type = (String) obj;
            }
        }
        if (soapObject.hasProperty("Code")) {
            Object obj = soapObject.getProperty("Code");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Code = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Code = (String) obj;
            }
        }
        if (soapObject.hasProperty("Message")) {
            Object obj = soapObject.getProperty("Message");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Message = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Message = (String) obj;
            }
        }
        if (soapObject.hasProperty("FieldNumber")) {
            Object obj = soapObject.getProperty("FieldNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.FieldNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.FieldNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("FieldName")) {
            Object obj = soapObject.getProperty("FieldName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.FieldName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.FieldName = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Type != null ? Type : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Code != null ? Code : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return Message != null ? Message : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return FieldNumber != null ? FieldNumber : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return FieldName != null ? FieldName : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Type";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Code";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Message";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FieldNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FieldName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
