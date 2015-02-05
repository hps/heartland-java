package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class AdditionalTxnFieldsType extends AttributeContainer implements KvmSerializable {

    public String Description;

    public String InvoiceNbr;

    public String CustomerID;

    public AdditionalTxnFieldsType() {
    }

    public AdditionalTxnFieldsType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;

        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Description")) {
            Object obj = soapObject.getProperty("Description");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Description = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Description = (String) obj;
            }
        }

        if (soapObject.hasProperty("InvoiceNbr")) {
            Object obj = soapObject.getProperty("InvoiceNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.InvoiceNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.InvoiceNbr = (String) obj;
            }
        }

        if (soapObject.hasProperty("CustomerID")) {
            Object obj = soapObject.getProperty("CustomerID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CustomerID = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CustomerID = (String) obj;
            }
        }
    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Description != null ? Description : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return InvoiceNbr != null ? InvoiceNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return CustomerID != null ? CustomerID : SoapPrimitive.NullSkip;
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
            info.name = "Description";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "InvoiceNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CustomerID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }
}
