package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PaymentMethodKeyData extends AttributeContainer implements KvmSerializable {

    public Integer ExpMonth;

    public Integer ExpYear;

    public String CVV2;

    public Enums.cvv2Status CVV2Status;

    public PaymentMethodKeyData() {
    }

    public PaymentMethodKeyData(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("ExpMonth")) {
            Object obj = soapObject.getProperty("ExpMonth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ExpMonth = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.ExpMonth = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("ExpYear")) {
            Object obj = soapObject.getProperty("ExpYear");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ExpYear = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.ExpYear = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("CVV2")) {
            Object obj = soapObject.getProperty("CVV2");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVV2 = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CVV2 = (String) obj;
            }
        }
        if (soapObject.hasProperty("CVV2Status")) {
            Object obj = soapObject.getProperty("CVV2Status");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVV2Status = Enums.cvv2Status.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.cvv2Status) {
                this.CVV2Status = (Enums.cvv2Status) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return ExpMonth != null ? ExpMonth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return ExpYear != null ? ExpYear : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return CVV2 != null ? CVV2 : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CVV2Status != null ? CVV2Status.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "ExpMonth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "ExpYear";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVV2";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVV2Status";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
