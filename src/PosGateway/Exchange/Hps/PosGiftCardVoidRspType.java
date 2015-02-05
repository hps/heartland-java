package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class PosGiftCardVoidRspType extends AttributeContainer implements KvmSerializable {

    public Integer RspCode = 0;

    public String RspText;

    public String AuthCode;

    public BigDecimal BalanceAmt;

    public BigDecimal PointsBalanceAmt;

    public String Notes;

    public PosGiftCardVoidRspType() {
    }

    public PosGiftCardVoidRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("RspCode")) {
            Object obj = soapObject.getProperty("RspCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspCode = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.RspCode = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("RspText")) {
            Object obj = soapObject.getProperty("RspText");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspText = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RspText = (String) obj;
            }
        }
        if (soapObject.hasProperty("AuthCode")) {
            Object obj = soapObject.getProperty("AuthCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AuthCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AuthCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("BalanceAmt")) {
            Object obj = soapObject.getProperty("BalanceAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BalanceAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.BalanceAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("PointsBalanceAmt")) {
            Object obj = soapObject.getProperty("PointsBalanceAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PointsBalanceAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.PointsBalanceAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("Notes")) {
            Object obj = soapObject.getProperty("Notes");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Notes = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Notes = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return RspCode;
        }
        if (propertyIndex == 1) {
            return RspText != null ? RspText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AuthCode != null ? AuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return BalanceAmt != null ? BalanceAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return PointsBalanceAmt != null ? PointsBalanceAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return Notes != null ? Notes : SoapPrimitive.NullSkip;
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
            info.name = "RspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "BalanceAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PointsBalanceAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Notes";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
