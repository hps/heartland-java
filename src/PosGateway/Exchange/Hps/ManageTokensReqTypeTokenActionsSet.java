package PosGateway.Exchange.Hps;

import org.ksoap2.serialization.*;

import java.util.Vector;
import java.util.Hashtable;


public class ManageTokensReqTypeTokenActionsSet extends Vector<NameValuePairType> implements KvmSerializable {

    public ManageTokensReqTypeTokenActionsSet() {
    }

    public ManageTokensReqTypeTokenActionsSet(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {
        if (inObj == null)
            return;
        SoapObject soapObject = (SoapObject) inObj;
        int size = soapObject.getPropertyCount();
        for (int i0 = 0; i0 < size; i0++) {
            Object obj = soapObject.getProperty(i0);
            if (obj != null && obj instanceof AttributeContainer) {
                AttributeContainer j = (AttributeContainer) soapObject.getProperty(i0);
                NameValuePairType j1 = (NameValuePairType) envelope.get(j, NameValuePairType.class);
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
        info.name = "Attribute";
        info.type = NameValuePairType.class;
        info.namespace = "http://Hps.Exchange.PosGateway";
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}