package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosGetUserDeviceSettingsRspType extends AttributeContainer implements KvmSerializable {

    public String MerchNbr;

    public ArrayOfNameValuePairType Site = new ArrayOfNameValuePairType();

    public ArrayList<PermissionType> Permissions = new ArrayList<PermissionType>();

    public ArrayList<DeviceRspType> Devices = new ArrayList<DeviceRspType>();

    public PosGetUserDeviceSettingsRspType() {
    }

    public PosGetUserDeviceSettingsRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("Site")) {
            Object j = soapObject.getProperty("Site");
            this.Site = new ArrayOfNameValuePairType((AttributeContainer) j, envelope);
        }
        if (soapObject.hasProperty("Permissions")) {
            int size = soapObject.getPropertyCount();
            this.Permissions = new ArrayList<PermissionType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Permissions")) {
                    Object j = info.getValue();
                    PermissionType j1 = (PermissionType) envelope.get(j, PermissionType.class);
                    this.Permissions.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("Devices")) {
            int size = soapObject.getPropertyCount();
            this.Devices = new ArrayList<DeviceRspType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Devices")) {
                    Object j = info.getValue();
                    DeviceRspType j1 = (DeviceRspType) envelope.get(j, DeviceRspType.class);
                    this.Devices.add(j1);
                }
            }
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
            return Site != null ? Site : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +2 && propertyIndex < +2 + this.Permissions.size()) {
            return Permissions.get(propertyIndex - (+2));
        }
        if (propertyIndex >= +2 + this.Permissions.size() && propertyIndex < +2 + this.Permissions.size() + this.Devices.size()) {
            return Devices.get(propertyIndex - (+2 + this.Permissions.size()));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 2 + Permissions.size() + Devices.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "Site";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +2 && propertyIndex <= +2 + this.Permissions.size()) {
            info.type = PermissionType.class;
            info.name = "Permissions";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +2 + this.Permissions.size() && propertyIndex <= +2 + this.Permissions.size() + this.Devices.size()) {
            info.type = DeviceRspType.class;
            info.name = "Devices";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
