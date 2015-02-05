package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosAddAttachmentReqType extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId = 0;

    public ArrayList<AttachmentDataType> Block1 = new ArrayList<AttachmentDataType>();

    public PosAddAttachmentReqType() {
    }

    public PosAddAttachmentReqType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("Block1")) {
            int size = soapObject.getPropertyCount();
            this.Block1 = new ArrayList<AttachmentDataType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Block1")) {
                    Object j = info.getValue();
                    AttachmentDataType j1 = (AttachmentDataType) envelope.get(j, AttachmentDataType.class);
                    this.Block1.add(j1);
                }
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
        if (propertyIndex >= +1 && propertyIndex < +1 + this.Block1.size()) {
            return Block1.get(propertyIndex - (+1));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 1 + Block1.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +1 && propertyIndex <= +1 + this.Block1.size()) {
            info.type = AttachmentDataType.class;
            info.name = "Block1";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
