package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosManageUsersRspType extends AttributeContainer implements KvmSerializable {

    public ArrayList<PosUserDetailRspType> Users = new ArrayList<PosUserDetailRspType>();

    public ArrayList<DeviceType> Device = new ArrayList<DeviceType>();

    public PosManageUsersRspType() {
    }

    public PosManageUsersRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Users")) {
            int size = soapObject.getPropertyCount();
            this.Users = new ArrayList<PosUserDetailRspType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Users")) {
                    Object j = info.getValue();
                    PosUserDetailRspType j1 = (PosUserDetailRspType) envelope.get(j, PosUserDetailRspType.class);
                    this.Users.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("Device")) {
            int size = soapObject.getPropertyCount();
            this.Device = new ArrayList<DeviceType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Device")) {
                    Object j = info.getValue();
                    DeviceType j1 = (DeviceType) envelope.get(j, DeviceType.class);
                    this.Device.add(j1);
                }
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex >= +0 && propertyIndex < +0 + this.Users.size()) {
            return Users.get(propertyIndex - (+0));
        }
        if (propertyIndex >= +0 + this.Users.size() && propertyIndex < +0 + this.Users.size() + this.Device.size()) {
            return Device.get(propertyIndex - (+0 + this.Users.size()));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 0 + Users.size() + Device.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex >= +0 && propertyIndex <= +0 + this.Users.size()) {
            info.type = PosUserDetailRspType.class;
            info.name = "Users";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +0 + this.Users.size() && propertyIndex <= +0 + this.Users.size() + this.Device.size()) {
            info.type = DeviceType.class;
            info.name = "Device";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
