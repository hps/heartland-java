package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class DestinationType extends AttributeContainer implements KvmSerializable {

    public Enums.DestinationType_DataType DataType = Enums.DestinationType_DataType.EMAIL_TO;

    public String Data;

    public DestinationType() {
    }

    public DestinationType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("DataType")) {
            Object obj = soapObject.getProperty("DataType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DataType = Enums.DestinationType_DataType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.DestinationType_DataType) {
                this.DataType = (Enums.DestinationType_DataType) obj;
            }
        }
        if (soapObject.hasProperty("Data")) {
            Object obj = soapObject.getProperty("Data");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Data = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Data = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return DataType != null ? DataType.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Data != null ? Data : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DataType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Data";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
