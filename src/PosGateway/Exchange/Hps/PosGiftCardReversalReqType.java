package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosGiftCardReversalReqType extends AttributeContainer implements KvmSerializable {

    public GiftCardReversalReqBlock1Type Block1;

    public PosGiftCardReversalReqType() {
    }

    public PosGiftCardReversalReqType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Block1")) {
            Object j = soapObject.getProperty("Block1");
            this.Block1 = (GiftCardReversalReqBlock1Type) envelope.get(j, GiftCardReversalReqBlock1Type.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Block1 != null ? Block1 : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = GiftCardReversalReqBlock1Type.class;
            info.name = "Block1";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
