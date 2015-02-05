package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class DirectMktDataType extends AttributeContainer implements KvmSerializable {

    public String DirectMktInvoiceNbr;

    public Integer DirectMktShipMonth = 0;

    public Integer DirectMktShipDay = 0;

    public DirectMktDataType() {
    }

    public DirectMktDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("DirectMktInvoiceNbr")) {
            Object obj = soapObject.getProperty("DirectMktInvoiceNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DirectMktInvoiceNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DirectMktInvoiceNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("DirectMktShipMonth")) {
            Object obj = soapObject.getProperty("DirectMktShipMonth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DirectMktShipMonth = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DirectMktShipMonth = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DirectMktShipDay")) {
            Object obj = soapObject.getProperty("DirectMktShipDay");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DirectMktShipDay = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DirectMktShipDay = (Integer) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return DirectMktInvoiceNbr != null ? DirectMktInvoiceNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return DirectMktShipMonth;
        }
        if (propertyIndex == 2) {
            return DirectMktShipDay;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DirectMktInvoiceNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DirectMktShipMonth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DirectMktShipDay";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
