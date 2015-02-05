package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosManageUsersReqType extends AttributeContainer implements KvmSerializable {

    public String MerchNbr;

    public PosManageUsersReqTypeUserActions UserActions;

    public PosManageUsersReqType() {
    }

    public PosManageUsersReqType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("MerchNbr")) {
            Object obj = soapObject.getProperty("MerchNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("UserActions")) {
            Object j = soapObject.getProperty("UserActions");
            this.UserActions = (PosManageUsersReqTypeUserActions) envelope.get(j, PosManageUsersReqTypeUserActions.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return MerchNbr != null ? MerchNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return UserActions != null ? UserActions : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PosManageUsersReqTypeUserActions.class;
            info.name = "UserActions";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
