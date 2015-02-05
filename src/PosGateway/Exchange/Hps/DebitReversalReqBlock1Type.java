package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class DebitReversalReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId;

    public String TrackData;

    public BigDecimal Amt;

    public BigDecimal AuthAmt;

    public EncryptionDataType EncryptionData;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public DebitReversalReqBlock1Type() {
    }

    public DebitReversalReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("TrackData")) {
            Object obj = soapObject.getProperty("TrackData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TrackData = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TrackData = (String) obj;
            }
        }
        if (soapObject.hasProperty("Amt")) {
            Object obj = soapObject.getProperty("Amt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Amt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.Amt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("AuthAmt")) {
            Object obj = soapObject.getProperty("AuthAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AuthAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.AuthAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("EncryptionData")) {
            Object j = soapObject.getProperty("EncryptionData");
            this.EncryptionData = (EncryptionDataType) envelope.get(j, EncryptionDataType.class);
        }
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return GatewayTxnId != null ? GatewayTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return TrackData != null ? TrackData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return AuthAmt != null ? AuthAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return EncryptionData != null ? EncryptionData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 6;
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
            info.name = "TrackData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = EncryptionDataType.class;
            info.name = "EncryptionData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
