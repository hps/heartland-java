package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class GiftCardTipReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId = 0;

    public BigDecimal GratuityAmtInfo = BigDecimal.ZERO;

    public Enums.currencyType Currency;

    public GiftCardTipReqBlock1Type() {
    }

    public GiftCardTipReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("GatewayTxnId")) {
            Object obj = soapObject.getProperty("GatewayTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GatewayTxnId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.GatewayTxnId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("GratuityAmtInfo")) {
            Object obj = soapObject.getProperty("GratuityAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GratuityAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.GratuityAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("Currency")) {
            Object obj = soapObject.getProperty("Currency");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Currency = Enums.currencyType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.currencyType) {
                this.Currency = (Enums.currencyType) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return GatewayTxnId;
        }
        if (propertyIndex == 1) {
            return GratuityAmtInfo != null ? GratuityAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return Currency != null ? Currency.toString() : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GratuityAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Currency";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
