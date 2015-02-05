package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosManageUsersReqTypeUserActions extends AttributeContainer implements KvmSerializable {

    public PosManageUsersReqTypeUserActionsQuery Query;

    public PosManageUsersReqTypeUserActions() {
    }

    public PosManageUsersReqTypeUserActions(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Query")) {
            Object j = soapObject.getProperty("Query");
            this.Query = (PosManageUsersReqTypeUserActionsQuery) envelope.get(j, PosManageUsersReqTypeUserActionsQuery.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Query != null ? Query : SoapPrimitive.NullSkip;
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
            info.type = PosManageUsersReqTypeUserActionsQuery.class;
            info.name = "Query";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
