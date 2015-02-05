package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class GiftCardTotalsType extends AttributeContainer implements KvmSerializable {

    public Integer RspCode = 0;

    public String RspText;

    public Integer SaleCnt;

    public BigDecimal SaleAmt;

    public Integer ActivateCnt;

    public BigDecimal ActivateAmt;

    public Integer AddValueCnt;

    public BigDecimal AddValueAmt;

    public Integer VoidCnt;

    public BigDecimal VoidAmt;

    public Integer DeactivateCnt;

    public BigDecimal DeactivateAmt;

    public GiftCardTotalsType() {
    }

    public GiftCardTotalsType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("SaleCnt")) {
            Object obj = soapObject.getProperty("SaleCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SaleCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.SaleCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("SaleAmt")) {
            Object obj = soapObject.getProperty("SaleAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SaleAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SaleAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("ActivateCnt")) {
            Object obj = soapObject.getProperty("ActivateCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ActivateCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.ActivateCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("ActivateAmt")) {
            Object obj = soapObject.getProperty("ActivateAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ActivateAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ActivateAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("AddValueCnt")) {
            Object obj = soapObject.getProperty("AddValueCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AddValueCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.AddValueCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("AddValueAmt")) {
            Object obj = soapObject.getProperty("AddValueAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AddValueAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.AddValueAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("VoidCnt")) {
            Object obj = soapObject.getProperty("VoidCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.VoidCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.VoidCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("VoidAmt")) {
            Object obj = soapObject.getProperty("VoidAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.VoidAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.VoidAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("DeactivateCnt")) {
            Object obj = soapObject.getProperty("DeactivateCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DeactivateCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DeactivateCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DeactivateAmt")) {
            Object obj = soapObject.getProperty("DeactivateAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DeactivateAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.DeactivateAmt = (BigDecimal) obj;
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
            return SaleCnt != null ? SaleCnt : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return SaleAmt != null ? SaleAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return ActivateCnt != null ? ActivateCnt : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return ActivateAmt != null ? ActivateAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return AddValueCnt != null ? AddValueCnt : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return AddValueAmt != null ? AddValueAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return VoidCnt != null ? VoidCnt : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return VoidAmt != null ? VoidAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return DeactivateCnt != null ? DeactivateCnt : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return DeactivateAmt != null ? DeactivateAmt.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 12;
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
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "SaleCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SaleAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "ActivateCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ActivateAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "AddValueCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AddValueAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "VoidCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "VoidAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DeactivateCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DeactivateAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
