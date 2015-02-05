package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class ManageSettingsRspType extends AttributeContainer implements KvmSerializable {

    public ArrayOfNameValuePairType Site = new ArrayOfNameValuePairType();

    public ArrayOfNameValuePairType Device = new ArrayOfNameValuePairType();

    public ManageSettingsRspType() {
    }

    public ManageSettingsRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Site")) {
            Object j = soapObject.getProperty("Site");
            this.Site = new ArrayOfNameValuePairType((AttributeContainer) j, envelope);
        }
        if (soapObject.hasProperty("Device")) {
            Object j = soapObject.getProperty("Device");
            this.Device = new ArrayOfNameValuePairType((AttributeContainer) j, envelope);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Site != null ? Site : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Device != null ? Device : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "Site";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "Device";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
