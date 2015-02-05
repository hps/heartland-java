package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosGetAttachmentReqType extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId = 0;

    public ArrayList<Enums.attachmentTypeType> AttachmentType = new ArrayList<Enums.attachmentTypeType>();

    public Boolean ReturnAttachmentTypesOnly;

    public Integer AttachmentDataId;

    public PosGetAttachmentReqType() {
    }

    public PosGetAttachmentReqType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
            int size = soapObject.getPropertyCount();
            this.AttachmentType = new ArrayList<Enums.attachmentTypeType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("AttachmentType")) {
                    Object j = info.getValue();
                    Enums.attachmentTypeType j1 = Enums.attachmentTypeType.fromString(j.toString());
                    this.AttachmentType.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("ReturnAttachmentTypesOnly")) {
            Object obj = soapObject.getProperty("ReturnAttachmentTypesOnly");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReturnAttachmentTypesOnly = new Boolean(j.toString());
                }
            } else if (obj != null && obj instanceof Boolean) {
                this.ReturnAttachmentTypesOnly = (Boolean) obj;
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
            return GatewayTxnId;
        }
        if (propertyIndex == 1) {
            return ReturnAttachmentTypesOnly != null ? ReturnAttachmentTypesOnly : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AttachmentDataId != null ? AttachmentDataId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +3 && propertyIndex < +3 + this.AttachmentType.size()) {
            return AttachmentType.get(propertyIndex - (+3));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3 + AttachmentType.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.BOOLEAN_CLASS;
            info.name = "ReturnAttachmentTypesOnly";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "AttachmentDataId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +3 && propertyIndex <= +3 + this.AttachmentType.size()) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AttachmentType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
