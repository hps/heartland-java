package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class PosReportSearchRspTypeDetails extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId = 0;

    public Integer DeviceId = 0;

    public String ServiceName;

    public java.util.Date TxnUtcDT;

    public String TxnStatus;

    public String SiteTrace;

    public String DebitCreditInd;

    public String SaleReturnInd;

    public String CardType;

    public String MaskedCardNbr;

    public BigDecimal Amt = BigDecimal.ZERO;

    public BigDecimal AuthAmt = BigDecimal.ZERO;

    public BigDecimal SettlementAmt = BigDecimal.ZERO;

    public BigDecimal CashbackAmtInfo = BigDecimal.ZERO;

    public BigDecimal GratuityAmtInfo = BigDecimal.ZERO;

    public String RspCode;

    public String RspText;

    public String AuthCode;

    public Enums.tzoneConversionType TzConversion = Enums.tzoneConversionType.Merchant;

    public java.util.Date TxnDT;

    public String ClerkID;

    public CredentialDataType CredentialData;

    public PosReportSearchRspTypeDetails() {
    }

    public PosReportSearchRspTypeDetails(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("DeviceId")) {
            Object obj = soapObject.getProperty("DeviceId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DeviceId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DeviceId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("ServiceName")) {
            Object obj = soapObject.getProperty("ServiceName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ServiceName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ServiceName = (String) obj;
            }
        }
        if (soapObject.hasProperty("TxnUtcDT")) {
            Object obj = soapObject.getProperty("TxnUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.TxnUtcDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("TxnStatus")) {
            Object obj = soapObject.getProperty("TxnStatus");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnStatus = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TxnStatus = (String) obj;
            }
        }
        if (soapObject.hasProperty("SiteTrace")) {
            Object obj = soapObject.getProperty("SiteTrace");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SiteTrace = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SiteTrace = (String) obj;
            }
        }
        if (soapObject.hasProperty("DebitCreditInd")) {
            Object obj = soapObject.getProperty("DebitCreditInd");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DebitCreditInd = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DebitCreditInd = (String) obj;
            }
        }
        if (soapObject.hasProperty("SaleReturnInd")) {
            Object obj = soapObject.getProperty("SaleReturnInd");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SaleReturnInd = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SaleReturnInd = (String) obj;
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
        if (soapObject.hasProperty("MaskedCardNbr")) {
            Object obj = soapObject.getProperty("MaskedCardNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MaskedCardNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MaskedCardNbr = (String) obj;
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
        if (soapObject.hasProperty("SettlementAmt")) {
            Object obj = soapObject.getProperty("SettlementAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SettlementAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SettlementAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("CashbackAmtInfo")) {
            Object obj = soapObject.getProperty("CashbackAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CashbackAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.CashbackAmtInfo = (BigDecimal) obj;
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
        if (soapObject.hasProperty("TzConversion")) {
            Object obj = soapObject.getProperty("TzConversion");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TzConversion = Enums.tzoneConversionType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.tzoneConversionType) {
                this.TzConversion = (Enums.tzoneConversionType) obj;
            }
        }
        if (soapObject.hasProperty("TxnDT")) {
            Object obj = soapObject.getProperty("TxnDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.TxnDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("ClerkID")) {
            Object obj = soapObject.getProperty("ClerkID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ClerkID = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ClerkID = (String) obj;
            }
        }
        if (soapObject.hasProperty("CredentialData")) {
            Object j = soapObject.getProperty("CredentialData");
            this.CredentialData = (CredentialDataType) envelope.get(j, CredentialDataType.class);
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
            return DeviceId;
        }
        if (propertyIndex == 2) {
            return ServiceName != null ? ServiceName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return TxnUtcDT;
        }
        if (propertyIndex == 4) {
            return TxnStatus != null ? TxnStatus : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return SiteTrace != null ? SiteTrace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return DebitCreditInd != null ? DebitCreditInd : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return SaleReturnInd != null ? SaleReturnInd : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return CardType != null ? CardType : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return MaskedCardNbr != null ? MaskedCardNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return AuthAmt != null ? AuthAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return SettlementAmt != null ? SettlementAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return CashbackAmtInfo != null ? CashbackAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return GratuityAmtInfo != null ? GratuityAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return RspCode != null ? RspCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 16) {
            return RspText != null ? RspText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 17) {
            return AuthCode != null ? AuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return TzConversion != null ? TzConversion.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 19) {
            return TxnDT;
        }
        if (propertyIndex == 20) {
            return ClerkID != null ? ClerkID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 21) {
            return CredentialData != null ? CredentialData : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 22;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ServiceName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnStatus";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SiteTrace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DebitCreditInd";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SaleReturnInd";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MaskedCardNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SettlementAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CashbackAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GratuityAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TzConversion";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ClerkID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 21) {
            info.type = CredentialDataType.class;
            info.name = "CredentialData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
