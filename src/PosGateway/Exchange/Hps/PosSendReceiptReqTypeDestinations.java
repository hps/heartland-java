package PosGateway.Exchange.Hps;

import org.ksoap2.serialization.*;

import java.util.Vector;
import java.util.Hashtable;


public class PosSendReceiptReqTypeDestinations extends Vector<DestinationType> implements KvmSerializable {

    public PosSendReceiptReqTypeDestinations() {
    }

    public PosSendReceiptReqTypeDestinations(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {
        if (inObj == null)
            return;
        SoapObject soapObject = (SoapObject) inObj;
        int size = soapObject.getPropertyCount();
        for (int i0 = 0; i0 < size; i0++) {
            Object obj = soapObject.getProperty(i0);
            if (obj != null && obj instanceof AttributeContainer) {
                AttributeContainer j = (AttributeContainer) soapObject.getProperty(i0);
                DestinationType j1 = (DestinationType) envelope.get(j, DestinationType.class);
                add(j1);
            }
        }

    }

    @Override
    public Object getProperty(int arg0) {
        return this.get(arg0);
    }

    @Override
    public int getPropertyCount() {
        return this.size();
    }

    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        info.name = "Destination";
        info.type = DestinationType.class;
        info.namespace = "http://Hps.Exchange.PosGateway";
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}