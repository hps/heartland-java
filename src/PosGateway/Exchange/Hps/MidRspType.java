package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class MidRspType extends AttributeContainer implements KvmSerializable {

    public MerchNbrRspType MerchNbr;

    public SiteRspType Site;

    public LicenseRspType License;

    public ArrayList<DeviceRspType> Devices = new ArrayList<DeviceRspType>();

    public MidRspType() {
    }

    public MidRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("MerchNbr")) {
            Object j = soapObject.getProperty("MerchNbr");
            this.MerchNbr = (MerchNbrRspType) envelope.get(j, MerchNbrRspType.class);
        }
        if (soapObject.hasProperty("Site")) {
            Object j = soapObject.getProperty("Site");
            this.Site = (SiteRspType) envelope.get(j, SiteRspType.class);
        }
        if (soapObject.hasProperty("License")) {
            Object j = soapObject.getProperty("License");
            this.License = (LicenseRspType) envelope.get(j, LicenseRspType.class);
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
        if (propertyIndex == 2) {
            return License != null ? License : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +3 && propertyIndex < +3 + this.Devices.size()) {
            return Devices.get(propertyIndex - (+3));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3 + Devices.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = MerchNbrRspType.class;
            info.name = "MerchNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = SiteRspType.class;
            info.name = "Site";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = LicenseRspType.class;
            info.name = "License";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +3 && propertyIndex <= +3 + this.Devices.size()) {
            info.type = DeviceRspType.class;
            info.name = "Devices";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
