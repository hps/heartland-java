package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class DeviceRspType extends AttributeContainer implements KvmSerializable {

    public DeviceType Device;

    public ArrayOfNameValuePairType Settings = new ArrayOfNameValuePairType();

    public DeviceRspType() {
    }

    public DeviceRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Device")) {
            Object j = soapObject.getProperty("Device");
            this.Device = (DeviceType) envelope.get(j, DeviceType.class);
        }
        if (soapObject.hasProperty("Settings")) {
            Object j = soapObject.getProperty("Settings");
            this.Settings = new ArrayOfNameValuePairType((AttributeContainer) j, envelope);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Device != null ? Device : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Settings != null ? Settings : SoapPrimitive.NullSkip;
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
            info.type = DeviceType.class;
            info.name = "Device";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "Settings";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
