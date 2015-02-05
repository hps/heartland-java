package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class TransactionDataType extends AttributeContainer implements KvmSerializable {

    public Integer GatewayTxnId = 0;

    public Long ClientTxnId = 0L;

    public Integer GatewayRspCode = 0;

    public String GatewayRspMsg;

    public String CardType;

    public String MaskedCardNbr;

    public String CardSwiped;

    public CardHolderDataType CardHolderData;

    public Enums.paymentTypeType PaymentType = Enums.paymentTypeType.OTHER;

    public String ServiceName;

    public String TxnStatus;

    public String AuthCode;

    public BigDecimal Amt = BigDecimal.ZERO;

    public BigDecimal AuthAmt;

    public BigDecimal SettlementAmt = BigDecimal.ZERO;

    public BigDecimal GratuityAmtInfo;

    public String UserName;

    public String ClerkID;

    public java.util.Date RspDT;

    public String RspCode;

    public String RspText;

    public String IssTxnId;

    public String RefNbr;

    public Integer BatchSeqNbr;

    public String SiteTrace;

    public Integer OriginalGatewayTxnId;

    public String AcctDataSrc;

    public CPCDataType CPCData;

    public LodgingDataType LodgingData;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public CredentialDataType CredentialData;

    public String UniqueDeviceId;

    public BigDecimal ConvenienceAmtInfo;

    public BigDecimal ShippingAmtInfo;

    public String TxnDescriptor;

    public CheckDataType CheckData;

    public ArrayList<AttachmentInfoType> AttachmentInfo = new ArrayList<AttachmentInfoType>();

    public GiftDataType GiftData;

    public String PaymentMethodKey;

    public RecurringDataType RecurringData;

    public BigDecimal SurchargeAmtInfo;

    public CashDataType CashData;

    public String FraudRuleInfo;

    public Integer RepeatCount;

    public TransactionDataType() {
    }

    public TransactionDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("ClientTxnId")) {
            Object obj = soapObject.getProperty("ClientTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ClientTxnId = new Long(j.toString());
                }
            } else if (obj != null && obj instanceof Long) {
                this.ClientTxnId = (Long) obj;
            }
        }
        if (soapObject.hasProperty("GatewayRspCode")) {
            Object obj = soapObject.getProperty("GatewayRspCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GatewayRspCode = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.GatewayRspCode = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("GatewayRspMsg")) {
            Object obj = soapObject.getProperty("GatewayRspMsg");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GatewayRspMsg = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.GatewayRspMsg = (String) obj;
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
        if (soapObject.hasProperty("CardSwiped")) {
            Object obj = soapObject.getProperty("CardSwiped");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardSwiped = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardSwiped = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderData")) {
            Object j = soapObject.getProperty("CardHolderData");
            this.CardHolderData = (CardHolderDataType) envelope.get(j, CardHolderDataType.class);
        }
        if (soapObject.hasProperty("PaymentType")) {
            Object obj = soapObject.getProperty("PaymentType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PaymentType = Enums.paymentTypeType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.paymentTypeType) {
                this.PaymentType = (Enums.paymentTypeType) obj;
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
        if (soapObject.hasProperty("UserName")) {
            Object obj = soapObject.getProperty("UserName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.UserName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.UserName = (String) obj;
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
        if (soapObject.hasProperty("RspDT")) {
            Object obj = soapObject.getProperty("RspDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.RspDT = (java.util.Date) obj;
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
        if (soapObject.hasProperty("IssTxnId")) {
            Object obj = soapObject.getProperty("IssTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IssTxnId = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.IssTxnId = (String) obj;
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
        if (soapObject.hasProperty("BatchSeqNbr")) {
            Object obj = soapObject.getProperty("BatchSeqNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BatchSeqNbr = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.BatchSeqNbr = (Integer) obj;
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
        if (soapObject.hasProperty("OriginalGatewayTxnId")) {
            Object obj = soapObject.getProperty("OriginalGatewayTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.OriginalGatewayTxnId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.OriginalGatewayTxnId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("AcctDataSrc")) {
            Object obj = soapObject.getProperty("AcctDataSrc");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AcctDataSrc = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AcctDataSrc = (String) obj;
            }
        }
        if (soapObject.hasProperty("CPCData")) {
            Object j = soapObject.getProperty("CPCData");
            this.CPCData = (CPCDataType) envelope.get(j, CPCDataType.class);
        }
        if (soapObject.hasProperty("LodgingData")) {
            Object j = soapObject.getProperty("LodgingData");
            this.LodgingData = (LodgingDataType) envelope.get(j, LodgingDataType.class);
        }
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
        }
        if (soapObject.hasProperty("CredentialData")) {
            Object j = soapObject.getProperty("CredentialData");
            this.CredentialData = (CredentialDataType) envelope.get(j, CredentialDataType.class);
        }
        if (soapObject.hasProperty("UniqueDeviceId")) {
            Object obj = soapObject.getProperty("UniqueDeviceId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.UniqueDeviceId = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.UniqueDeviceId = (String) obj;
            }
        }
        if (soapObject.hasProperty("ConvenienceAmtInfo")) {
            Object obj = soapObject.getProperty("ConvenienceAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ConvenienceAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ConvenienceAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("ShippingAmtInfo")) {
            Object obj = soapObject.getProperty("ShippingAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ShippingAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ShippingAmtInfo = (BigDecimal) obj;
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
        if (soapObject.hasProperty("CheckData")) {
            Object j = soapObject.getProperty("CheckData");
            this.CheckData = (CheckDataType) envelope.get(j, CheckDataType.class);
        }
        if (soapObject.hasProperty("AttachmentInfo")) {
            int size = soapObject.getPropertyCount();
            this.AttachmentInfo = new ArrayList<AttachmentInfoType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("AttachmentInfo")) {
                    Object j = info.getValue();
                    AttachmentInfoType j1 = (AttachmentInfoType) envelope.get(j, AttachmentInfoType.class);
                    this.AttachmentInfo.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("GiftData")) {
            Object j = soapObject.getProperty("GiftData");
            this.GiftData = (GiftDataType) envelope.get(j, GiftDataType.class);
        }
        if (soapObject.hasProperty("PaymentMethodKey")) {
            Object obj = soapObject.getProperty("PaymentMethodKey");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PaymentMethodKey = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.PaymentMethodKey = (String) obj;
            }
        }
        if (soapObject.hasProperty("RecurringData")) {
            Object j = soapObject.getProperty("RecurringData");
            this.RecurringData = (RecurringDataType) envelope.get(j, RecurringDataType.class);
        }
        if (soapObject.hasProperty("SurchargeAmtInfo")) {
            Object obj = soapObject.getProperty("SurchargeAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SurchargeAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SurchargeAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("CashData")) {
            Object j = soapObject.getProperty("CashData");
            this.CashData = (CashDataType) envelope.get(j, CashDataType.class);
        }
        if (soapObject.hasProperty("FraudRuleInfo")) {
            Object obj = soapObject.getProperty("FraudRuleInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.FraudRuleInfo = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.FraudRuleInfo = (String) obj;
            }
        }
        if (soapObject.hasProperty("RepeatCount")) {
            Object obj = soapObject.getProperty("RepeatCount");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RepeatCount = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.RepeatCount = (Integer) obj;
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
            return ClientTxnId;
        }
        if (propertyIndex == 2) {
            return GatewayRspCode;
        }
        if (propertyIndex == 3) {
            return GatewayRspMsg != null ? GatewayRspMsg : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return CardType != null ? CardType : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return MaskedCardNbr != null ? MaskedCardNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return CardSwiped != null ? CardSwiped : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return CardHolderData != null ? CardHolderData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return PaymentType != null ? PaymentType.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return ServiceName != null ? ServiceName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return TxnStatus != null ? TxnStatus : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return AuthCode != null ? AuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return AuthAmt != null ? AuthAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return SettlementAmt != null ? SettlementAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return GratuityAmtInfo != null ? GratuityAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 16) {
            return UserName != null ? UserName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 17) {
            return ClerkID != null ? ClerkID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return RspDT;
        }
        if (propertyIndex == 19) {
            return RspCode != null ? RspCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 20) {
            return RspText != null ? RspText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 21) {
            return IssTxnId != null ? IssTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 22) {
            return RefNbr != null ? RefNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 23) {
            return BatchSeqNbr != null ? BatchSeqNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 24) {
            return SiteTrace != null ? SiteTrace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 25) {
            return OriginalGatewayTxnId != null ? OriginalGatewayTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 26) {
            return AcctDataSrc != null ? AcctDataSrc : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 27) {
            return CPCData != null ? CPCData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 28) {
            return LodgingData != null ? LodgingData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 29) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 30) {
            return CredentialData != null ? CredentialData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 31) {
            return UniqueDeviceId != null ? UniqueDeviceId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 32) {
            return ConvenienceAmtInfo != null ? ConvenienceAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 33) {
            return ShippingAmtInfo != null ? ShippingAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 34) {
            return TxnDescriptor != null ? TxnDescriptor : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 35) {
            return CheckData != null ? CheckData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 36) {
            return GiftData != null ? GiftData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 37) {
            return PaymentMethodKey != null ? PaymentMethodKey : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 38) {
            return RecurringData != null ? RecurringData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 39) {
            return SurchargeAmtInfo != null ? SurchargeAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 40) {
            return CashData != null ? CashData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 41) {
            return FraudRuleInfo != null ? FraudRuleInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 42) {
            return RepeatCount != null ? RepeatCount : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +43 && propertyIndex < +43 + this.AttachmentInfo.size()) {
            return AttachmentInfo.get(propertyIndex - (+43));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 43 + AttachmentInfo.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.LONG_CLASS;
            info.name = "ClientTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayRspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GatewayRspMsg";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MaskedCardNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardSwiped";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = CardHolderDataType.class;
            info.name = "CardHolderData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PaymentType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ServiceName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnStatus";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SettlementAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GratuityAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UserName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ClerkID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 21) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IssTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 22) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RefNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 23) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "BatchSeqNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 24) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SiteTrace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 25) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "OriginalGatewayTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 26) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AcctDataSrc";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 27) {
            info.type = CPCDataType.class;
            info.name = "CPCData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 28) {
            info.type = LodgingDataType.class;
            info.name = "LodgingData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 29) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 30) {
            info.type = CredentialDataType.class;
            info.name = "CredentialData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 31) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UniqueDeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 32) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ConvenienceAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 33) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ShippingAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 34) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnDescriptor";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 35) {
            info.type = CheckDataType.class;
            info.name = "CheckData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 36) {
            info.type = GiftDataType.class;
            info.name = "GiftData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 37) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PaymentMethodKey";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 38) {
            info.type = RecurringDataType.class;
            info.name = "RecurringData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 39) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SurchargeAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 40) {
            info.type = CashDataType.class;
            info.name = "CashData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 41) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "FraudRuleInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 42) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "RepeatCount";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +43 && propertyIndex <= +43 + this.AttachmentInfo.size()) {
            info.type = AttachmentInfoType.class;
            info.name = "AttachmentInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
