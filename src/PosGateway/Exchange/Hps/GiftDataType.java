package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class GiftDataType extends AttributeContainer implements KvmSerializable {

    public Enums.currencyType GiftCurrency;

    public String GiftMaskedAlias;

    public BigDecimal TaxAmtInfo;

    public GiftDataType() {
    }

    public GiftDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("GiftCurrency")) {
            Object obj = soapObject.getProperty("GiftCurrency");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GiftCurrency = Enums.currencyType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.currencyType) {
                this.GiftCurrency = (Enums.currencyType) obj;
            }
        }
        if (soapObject.hasProperty("GiftMaskedAlias")) {
            Object obj = soapObject.getProperty("GiftMaskedAlias");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GiftMaskedAlias = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.GiftMaskedAlias = (String) obj;
            }
        }
        if (soapObject.hasProperty("TaxAmtInfo")) {
            Object obj = soapObject.getProperty("TaxAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TaxAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.TaxAmtInfo = (BigDecimal) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return GiftCurrency != null ? GiftCurrency.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return GiftMaskedAlias != null ? GiftMaskedAlias : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return TaxAmtInfo != null ? TaxAmtInfo.toString() : SoapPrimitive.NullSkip;
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
            info.name = "GiftCurrency";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GiftMaskedAlias";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TaxAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
