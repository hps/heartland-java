package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosReportOpenAuthsRspType extends AttributeContainer implements KvmSerializable {

    public PosReportOpenAuthsRspTypeHeader Header;

    public ArrayList<PosReportOpenAuthsRspTypeDetails> Details = new ArrayList<PosReportOpenAuthsRspTypeDetails>();

    public PosReportOpenAuthsRspType() {
    }

    public PosReportOpenAuthsRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Header")) {
            Object j = soapObject.getProperty("Header");
            this.Header = (PosReportOpenAuthsRspTypeHeader) envelope.get(j, PosReportOpenAuthsRspTypeHeader.class);
        }
        if (soapObject.hasProperty("Details")) {
            int size = soapObject.getPropertyCount();
            this.Details = new ArrayList<PosReportOpenAuthsRspTypeDetails>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Details")) {
                    Object j = info.getValue();
                    PosReportOpenAuthsRspTypeDetails j1 = (PosReportOpenAuthsRspTypeDetails) envelope.get(j, PosReportOpenAuthsRspTypeDetails.class);
                    this.Details.add(j1);
                }
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Header != null ? Header : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +1 && propertyIndex < +1 + this.Details.size()) {
            return Details.get(propertyIndex - (+1));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 1 + Details.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PosReportOpenAuthsRspTypeHeader.class;
            info.name = "Header";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +1 && propertyIndex <= +1 + this.Details.size()) {
            info.type = PosReportOpenAuthsRspTypeDetails.class;
            info.name = "Details";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
