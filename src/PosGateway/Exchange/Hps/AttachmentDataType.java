package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class AttachmentDataType extends AttributeContainer implements KvmSerializable {

    public Enums.attachmentTypeType AttachmentType = Enums.attachmentTypeType.SIGNATURE_IMAGE;

    public String AttachmentData;

    public Enums.attachmentFormatType AttachmentFormat = Enums.attachmentFormatType.PNG;

    public String AttachmentName;

    public AttachmentDataType() {
    }

    public AttachmentDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {
        if (inObj == null)
            return;

        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("AttachmentType")) {
            Object obj = soapObject.getProperty("AttachmentType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AttachmentType = Enums.attachmentTypeType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.attachmentTypeType) {
                this.AttachmentType = (Enums.attachmentTypeType) obj;
            }
        }
        if (soapObject.hasProperty("AttachmentData")) {
            Object obj = soapObject.getProperty("AttachmentData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AttachmentData = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AttachmentData = (String) obj;
            }
        }
        if (soapObject.hasProperty("AttachmentFormat")) {
            Object obj = soapObject.getProperty("AttachmentFormat");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AttachmentFormat = Enums.attachmentFormatType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.attachmentFormatType) {
                this.AttachmentFormat = (Enums.attachmentFormatType) obj;
            }
        }
        if (soapObject.hasProperty("AttachmentName")) {
            Object obj = soapObject.getProperty("AttachmentName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AttachmentName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AttachmentName = (String) obj;
            }
        }
    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return AttachmentType != null ? AttachmentType.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return AttachmentData != null ? AttachmentData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AttachmentFormat != null ? AttachmentFormat.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return AttachmentName != null ? AttachmentName : SoapPrimitive.NullSkip;
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
            info.name = "AttachmentType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentFormat";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }
}
