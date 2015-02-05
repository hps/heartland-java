package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosResponseVer10 extends AttributeContainer implements KvmSerializable {

    public PosResponseVer10Header Header;

    public PosResponseVer10Transaction Transaction;

    public PosResponseVer10() {
    }

    public PosResponseVer10(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {
        if (inObj == null)
            return;

        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Header")) {
            Object j = soapObject.getProperty("Header");
            this.Header = (PosResponseVer10Header) envelope.get(j, PosResponseVer10Header.class);
        }
        if (soapObject.hasProperty("Transaction")) {
            Object j = soapObject.getProperty("Transaction");
            this.Transaction = (PosResponseVer10Transaction) envelope.get(j, PosResponseVer10Transaction.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Header != null ? Header : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Transaction != null ? Transaction : SoapPrimitive.NullSkip;
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
            info.type = PosResponseVer10Header.class;
            info.name = "Header";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PosResponseVer10Transaction.class;
            info.name = "Transaction";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }
}
