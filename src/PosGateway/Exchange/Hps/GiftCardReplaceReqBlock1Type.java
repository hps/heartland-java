package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class GiftCardReplaceReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public GiftCardDataType OldCardData;

    public GiftCardDataType NewCardData;

    public GiftCardReplaceReqBlock1Type() {
    }

    public GiftCardReplaceReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("OldCardData")) {
            Object j = soapObject.getProperty("OldCardData");
            this.OldCardData = (GiftCardDataType) envelope.get(j, GiftCardDataType.class);
        }
        if (soapObject.hasProperty("NewCardData")) {
            Object j = soapObject.getProperty("NewCardData");
            this.NewCardData = (GiftCardDataType) envelope.get(j, GiftCardDataType.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return OldCardData != null ? OldCardData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return NewCardData != null ? NewCardData : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = GiftCardDataType.class;
            info.name = "OldCardData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = GiftCardDataType.class;
            info.name = "NewCardData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
