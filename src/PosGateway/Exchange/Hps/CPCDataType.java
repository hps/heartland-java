package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class CPCDataType extends AttributeContainer implements KvmSerializable {

    public String CardHolderPONbr;

    public Enums.taxTypeType TaxType;

    public BigDecimal TaxAmt;

    public CPCDataType() {
    }

    public CPCDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("CardHolderPONbr")) {
            Object obj = soapObject.getProperty("CardHolderPONbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderPONbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderPONbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("TaxType")) {
            Object obj = soapObject.getProperty("TaxType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TaxType = Enums.taxTypeType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.taxTypeType) {
                this.TaxType = (Enums.taxTypeType) obj;
            }
        }
        if (soapObject.hasProperty("TaxAmt")) {
            Object obj = soapObject.getProperty("TaxAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TaxAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.TaxAmt = (BigDecimal) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return CardHolderPONbr != null ? CardHolderPONbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return TaxType != null ? TaxType.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return TaxAmt != null ? TaxAmt.toString() : SoapPrimitive.NullSkip;
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
            info.name = "CardHolderPONbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TaxType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TaxAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
