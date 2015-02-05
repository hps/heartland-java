package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosReportActivityRspType extends AttributeContainer implements KvmSerializable {

    public PosReportActivityRspTypeHeader Header;

    public ArrayList<PosReportActivityRspTypeDetails> Details = new ArrayList<PosReportActivityRspTypeDetails>();

    public PosReportActivityRspType() {
    }

    public PosReportActivityRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Header")) {
            Object j = soapObject.getProperty("Header");
            this.Header = (PosReportActivityRspTypeHeader) envelope.get(j, PosReportActivityRspTypeHeader.class);
        }
        if (soapObject.hasProperty("Details")) {
            int size = soapObject.getPropertyCount();
            this.Details = new ArrayList<PosReportActivityRspTypeDetails>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Details")) {
                    Object j = info.getValue();
                    PosReportActivityRspTypeDetails j1 = (PosReportActivityRspTypeDetails) envelope.get(j, PosReportActivityRspTypeDetails.class);
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
            info.type = PosReportActivityRspTypeHeader.class;
            info.name = "Header";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +1 && propertyIndex <= +1 + this.Details.size()) {
            info.type = PosReportActivityRspTypeDetails.class;
            info.name = "Details";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
