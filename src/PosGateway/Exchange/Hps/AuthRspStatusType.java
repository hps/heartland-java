package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class AuthRspStatusType extends AttributeContainer implements KvmSerializable {

    public String RspCode;

    public String RspText;

    public String AuthCode;

    public String AVSRsltCode;

    public String CVVRsltCode;

    public String CPCInd;

    public String RefNbr;

    public BigDecimal AvailableBalance;

    public BigDecimal AuthAmt;

    public Enums.resultCodeActionType AVSResultCodeAction;

    public Enums.resultCodeActionType CVVResultCodeAction;

    public String CardType;

    public String AVSRsltText;

    public String CVVRsltText;

    public String TxnDescriptor;

    public String RecurringDataCode;

    public AuthRspStatusType() {
    }

    public AuthRspStatusType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("RspCode")) {
            Object obj = soapObject.getProperty("RspCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RspCode = (String) obj;
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
        if (soapObject.hasProperty("AVSRsltCode")) {
            Object obj = soapObject.getProperty("AVSRsltCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AVSRsltCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AVSRsltCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("CVVRsltCode")) {
            Object obj = soapObject.getProperty("CVVRsltCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVVRsltCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CVVRsltCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("CPCInd")) {
            Object obj = soapObject.getProperty("CPCInd");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CPCInd = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CPCInd = (String) obj;
            }
        }
        if (soapObject.hasProperty("RefNbr")) {
            Object obj = soapObject.getProperty("RefNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RefNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RefNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("AvailableBalance")) {
            Object obj = soapObject.getProperty("AvailableBalance");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AvailableBalance = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.AvailableBalance = (BigDecimal) obj;
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
        if (soapObject.hasProperty("AVSResultCodeAction")) {
            Object obj = soapObject.getProperty("AVSResultCodeAction");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AVSResultCodeAction = Enums.resultCodeActionType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.resultCodeActionType) {
                this.AVSResultCodeAction = (Enums.resultCodeActionType) obj;
            }
        }
        if (soapObject.hasProperty("CVVResultCodeAction")) {
            Object obj = soapObject.getProperty("CVVResultCodeAction");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVVResultCodeAction = Enums.resultCodeActionType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.resultCodeActionType) {
                this.CVVResultCodeAction = (Enums.resultCodeActionType) obj;
            }
        }
        if (soapObject.hasProperty("CardType")) {
            Object obj = soapObject.getProperty("CardType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardType = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardType = (String) obj;
            }
        }
        if (soapObject.hasProperty("AVSRsltText")) {
            Object obj = soapObject.getProperty("AVSRsltText");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AVSRsltText = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AVSRsltText = (String) obj;
            }
        }
        if (soapObject.hasProperty("CVVRsltText")) {
            Object obj = soapObject.getProperty("CVVRsltText");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVVRsltText = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CVVRsltText = (String) obj;
            }
        }
        if (soapObject.hasProperty("TxnDescriptor")) {
            Object obj = soapObject.getProperty("TxnDescriptor");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnDescriptor = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TxnDescriptor = (String) obj;
            }
        }
        if (soapObject.hasProperty("RecurringDataCode")) {
            Object obj = soapObject.getProperty("RecurringDataCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RecurringDataCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RecurringDataCode = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return RspCode != null ? RspCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return RspText != null ? RspText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AuthCode != null ? AuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return AVSRsltCode != null ? AVSRsltCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return CVVRsltCode != null ? CVVRsltCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CPCInd != null ? CPCInd : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return RefNbr != null ? RefNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return AvailableBalance != null ? AvailableBalance.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return AuthAmt != null ? AuthAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return AVSResultCodeAction != null ? AVSResultCodeAction.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return CVVResultCodeAction != null ? CVVResultCodeAction.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return CardType != null ? CardType : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return AVSRsltText != null ? AVSRsltText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return CVVRsltText != null ? CVVRsltText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return TxnDescriptor != null ? TxnDescriptor : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return RecurringDataCode != null ? RecurringDataCode : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 16;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
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
            info.name = "AVSRsltCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVVRsltCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCInd";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RefNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AvailableBalance";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AVSResultCodeAction";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVVResultCodeAction";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AVSRsltText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVVRsltText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnDescriptor";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RecurringDataCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
