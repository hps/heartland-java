package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class AttachmentRspDataType extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId;

    public String AttachmentType;

    public String AttachmentData;

    public String AttachmentFormat;

    public Integer Height;

    public Integer Width;

    public String AttachmentName;

    public Integer AttachmentDataId = 0;

    public AttachmentRspDataType() {
    }

    public AttachmentRspDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("AttachmentType")) {
            Object obj = soapObject.getProperty("AttachmentType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AttachmentType = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AttachmentType = (String) obj;
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
                    this.AttachmentFormat = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AttachmentFormat = (String) obj;
            }
        }
        if (soapObject.hasProperty("Height")) {
            Object obj = soapObject.getProperty("Height");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Height = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.Height = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("Width")) {
            Object obj = soapObject.getProperty("Width");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Width = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.Width = (Integer) obj;
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
        if (soapObject.hasProperty("AttachmentDataId")) {
            Object obj = soapObject.getProperty("AttachmentDataId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AttachmentDataId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.AttachmentDataId = (Integer) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return GatewayTxnId != null ? GatewayTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return AttachmentType != null ? AttachmentType : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AttachmentData != null ? AttachmentData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return AttachmentFormat != null ? AttachmentFormat : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return Height != null ? Height : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return Width != null ? Width : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return AttachmentName != null ? AttachmentName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return AttachmentDataId;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 8;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentFormat";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "Height";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "Width";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "AttachmentDataId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
