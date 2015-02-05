package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosGetUserSettingsRspTypeUser extends AttributeContainer implements KvmSerializable {

    public UserInfoRspType UserInfo;

    public String DefaultMerchNbr;

    public Integer DefaultDeviceId = 0;

    public ArrayList<PermissionType> DefaultPermissions = new ArrayList<PermissionType>();

    public PosGetUserSettingsRspTypeUser() {
    }

    public PosGetUserSettingsRspTypeUser(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("UserInfo")) {
            Object j = soapObject.getProperty("UserInfo");
            this.UserInfo = (UserInfoRspType) envelope.get(j, UserInfoRspType.class);
        }
        if (soapObject.hasProperty("DefaultMerchNbr")) {
            Object obj = soapObject.getProperty("DefaultMerchNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DefaultMerchNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DefaultMerchNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("DefaultDeviceId")) {
            Object obj = soapObject.getProperty("DefaultDeviceId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DefaultDeviceId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DefaultDeviceId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DefaultPermissions")) {
            int size = soapObject.getPropertyCount();
            this.DefaultPermissions = new ArrayList<PermissionType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("DefaultPermissions")) {
                    Object j = info.getValue();
                    PermissionType j1 = (PermissionType) envelope.get(j, PermissionType.class);
                    this.DefaultPermissions.add(j1);
                }
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return UserInfo != null ? UserInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return DefaultMerchNbr != null ? DefaultMerchNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return DefaultDeviceId;
        }
        if (propertyIndex >= +3 && propertyIndex < +3 + this.DefaultPermissions.size()) {
            return DefaultPermissions.get(propertyIndex - (+3));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3 + DefaultPermissions.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = UserInfoRspType.class;
            info.name = "UserInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DefaultMerchNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DefaultDeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +3 && propertyIndex <= +3 + this.DefaultPermissions.size()) {
            info.type = PermissionType.class;
            info.name = "DefaultPermissions";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
