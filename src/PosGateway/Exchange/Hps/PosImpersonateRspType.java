package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosImpersonateRspType extends AttributeContainer implements KvmSerializable {

    public ArrayList<MidRspType> MIDS = new ArrayList<MidRspType>();

    public PosImpersonateRspTypeUser User;

    public PosImpersonateRspType() {
    }

    public PosImpersonateRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("MIDS")) {
            int size = soapObject.getPropertyCount();
            this.MIDS = new ArrayList<MidRspType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("MIDS")) {
                    Object j = info.getValue();
                    MidRspType j1 = (MidRspType) envelope.get(j, MidRspType.class);
                    this.MIDS.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("User")) {
            Object j = soapObject.getProperty("User");
            this.User = (PosImpersonateRspTypeUser) envelope.get(j, PosImpersonateRspTypeUser.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return User != null ? User : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +1 && propertyIndex < +1 + this.MIDS.size()) {
            return MIDS.get(propertyIndex - (+1));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 1 + MIDS.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PosImpersonateRspTypeUser.class;
            info.name = "User";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +1 && propertyIndex <= +1 + this.MIDS.size()) {
            info.type = MidRspType.class;
            info.name = "MIDS";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
