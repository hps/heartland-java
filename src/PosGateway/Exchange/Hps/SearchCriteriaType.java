package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

import java.math.BigDecimal;

public class SearchCriteriaType extends AttributeContainer implements KvmSerializable {

    public java.util.Date StartUtcDT;

    public java.util.Date EndUtcDT;

    public String AuthCode;

    public String CardHolderLastName;

    public String CardHolderFirstName;

    public String CardNbrFirstSix;

    public String CardNbrLastFour;

    public String InvoiceNbr;

    public String CardHolderPONbr;

    public String CustomerID;

    public ArrayList<Enums.SearchCriteriaType_ServiceName> ServiceName = new ArrayList<Enums.SearchCriteriaType_ServiceName>();

    public ArrayList<Enums.paymentTypeType> PaymentType = new ArrayList<Enums.paymentTypeType>();

    public ArrayList<Enums.CardTypeType> CardType = new ArrayList<Enums.CardTypeType>();

    public Enums.SearchCriteriaType_IssuerResult IssuerResult;

    public BigDecimal SettlementAmt;

    public String IssTxnId;

    public String RefNbr;

    public String UserName;

    public String ClerkID;

    public Integer BatchSeqNbr;

    public Integer BatchId;

    public String SiteTrace;

    public String DisplayName;

    public Long ClientTxnId;

    public String UniqueDeviceId;

    public String AcctNbrLastFour;

    public String BankRoutingNbr;

    public String CheckNbr;

    public String CheckFirstName;

    public String CheckLastName;

    public String CheckName;

    public Enums.currencyType GiftCurrency;

    public String GiftMaskedAlias;

    public Enums.booleanType OneTime;

    public String PaymentMethodKey;

    public String ScheduleID;

    public SearchCriteriaType() {
    }

    public SearchCriteriaType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("StartUtcDT")) {
            Object obj = soapObject.getProperty("StartUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.StartUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.StartUtcDT = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("EndUtcDT")) {
            Object obj = soapObject.getProperty("EndUtcDT");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.EndUtcDT = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.EndUtcDT = (java.util.Date) obj;
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
        if (soapObject.hasProperty("CardHolderLastName")) {
            Object obj = soapObject.getProperty("CardHolderLastName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderLastName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderLastName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderFirstName")) {
            Object obj = soapObject.getProperty("CardHolderFirstName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderFirstName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderFirstName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardNbrFirstSix")) {
            Object obj = soapObject.getProperty("CardNbrFirstSix");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardNbrFirstSix = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardNbrFirstSix = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardNbrLastFour")) {
            Object obj = soapObject.getProperty("CardNbrLastFour");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardNbrLastFour = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardNbrLastFour = (String) obj;
            }
        }
        if (soapObject.hasProperty("InvoiceNbr")) {
            Object obj = soapObject.getProperty("InvoiceNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.InvoiceNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.InvoiceNbr = (String) obj;
            }
        }
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
        if (soapObject.hasProperty("CustomerID")) {
            Object obj = soapObject.getProperty("CustomerID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CustomerID = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CustomerID = (String) obj;
            }
        }
        if (soapObject.hasProperty("ServiceName")) {
            int size = soapObject.getPropertyCount();
            this.ServiceName = new ArrayList<Enums.SearchCriteriaType_ServiceName>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("ServiceName")) {
                    Object j = info.getValue();
                    Enums.SearchCriteriaType_ServiceName j1 = Enums.SearchCriteriaType_ServiceName.fromString(j.toString());
                    this.ServiceName.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("PaymentType")) {
            int size = soapObject.getPropertyCount();
            this.PaymentType = new ArrayList<Enums.paymentTypeType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("PaymentType")) {
                    Object j = info.getValue();
                    Enums.paymentTypeType j1 = Enums.paymentTypeType.fromString(j.toString());
                    this.PaymentType.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("CardType")) {
            int size = soapObject.getPropertyCount();
            this.CardType = new ArrayList<Enums.CardTypeType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("CardType")) {
                    Object j = info.getValue();
                    Enums.CardTypeType j1 = Enums.CardTypeType.fromString(j.toString());
                    this.CardType.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("IssuerResult")) {
            Object obj = soapObject.getProperty("IssuerResult");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IssuerResult = Enums.SearchCriteriaType_IssuerResult.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.SearchCriteriaType_IssuerResult) {
                this.IssuerResult = (Enums.SearchCriteriaType_IssuerResult) obj;
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
        if (soapObject.hasProperty("BatchId")) {
            Object obj = soapObject.getProperty("BatchId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BatchId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.BatchId = (Integer) obj;
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
        if (soapObject.hasProperty("DisplayName")) {
            Object obj = soapObject.getProperty("DisplayName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DisplayName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DisplayName = (String) obj;
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
        if (soapObject.hasProperty("AcctNbrLastFour")) {
            Object obj = soapObject.getProperty("AcctNbrLastFour");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AcctNbrLastFour = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AcctNbrLastFour = (String) obj;
            }
        }
        if (soapObject.hasProperty("BankRoutingNbr")) {
            Object obj = soapObject.getProperty("BankRoutingNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BankRoutingNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.BankRoutingNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckNbr")) {
            Object obj = soapObject.getProperty("CheckNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CheckNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckFirstName")) {
            Object obj = soapObject.getProperty("CheckFirstName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckFirstName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CheckFirstName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckLastName")) {
            Object obj = soapObject.getProperty("CheckLastName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckLastName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CheckLastName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckName")) {
            Object obj = soapObject.getProperty("CheckName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CheckName = (String) obj;
            }
        }
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
        if (soapObject.hasProperty("OneTime")) {
            Object obj = soapObject.getProperty("OneTime");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.OneTime = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.OneTime = (Enums.booleanType) obj;
            }
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
        if (soapObject.hasProperty("ScheduleID")) {
            Object obj = soapObject.getProperty("ScheduleID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ScheduleID = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ScheduleID = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return StartUtcDT != null ? StartUtcDT : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return EndUtcDT != null ? EndUtcDT : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AuthCode != null ? AuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CardHolderLastName != null ? CardHolderLastName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return CardHolderFirstName != null ? CardHolderFirstName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CardNbrFirstSix != null ? CardNbrFirstSix : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return CardNbrLastFour != null ? CardNbrLastFour : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return InvoiceNbr != null ? InvoiceNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return CardHolderPONbr != null ? CardHolderPONbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return CustomerID != null ? CustomerID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return IssuerResult != null ? IssuerResult.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return SettlementAmt != null ? SettlementAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return IssTxnId != null ? IssTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return RefNbr != null ? RefNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return UserName != null ? UserName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return ClerkID != null ? ClerkID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 16) {
            return BatchSeqNbr != null ? BatchSeqNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 17) {
            return BatchId != null ? BatchId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return SiteTrace != null ? SiteTrace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 19) {
            return DisplayName != null ? DisplayName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 20) {
            return ClientTxnId != null ? ClientTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 21) {
            return UniqueDeviceId != null ? UniqueDeviceId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 22) {
            return AcctNbrLastFour != null ? AcctNbrLastFour : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 23) {
            return BankRoutingNbr != null ? BankRoutingNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 24) {
            return CheckNbr != null ? CheckNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 25) {
            return CheckFirstName != null ? CheckFirstName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 26) {
            return CheckLastName != null ? CheckLastName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 27) {
            return CheckName != null ? CheckName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 28) {
            return GiftCurrency != null ? GiftCurrency.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 29) {
            return GiftMaskedAlias != null ? GiftMaskedAlias : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 30) {
            return OneTime != null ? OneTime.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 31) {
            return PaymentMethodKey != null ? PaymentMethodKey : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 32) {
            return ScheduleID != null ? ScheduleID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +33 && propertyIndex < +33 + this.ServiceName.size()) {
            return ServiceName.get(propertyIndex - (+33));
        }
        if (propertyIndex >= +33 + this.ServiceName.size() && propertyIndex < +33 + this.ServiceName.size() + this.PaymentType.size()) {
            return PaymentType.get(propertyIndex - (+33 + this.ServiceName.size()));
        }
        if (propertyIndex >= +33 + this.ServiceName.size() + this.PaymentType.size() && propertyIndex < +33 + this.ServiceName.size() + this.PaymentType.size() + this.CardType.size()) {
            return CardType.get(propertyIndex - (+33 + this.ServiceName.size() + this.PaymentType.size()));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 33 + ServiceName.size() + PaymentType.size() + CardType.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "StartUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EndUtcDT";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderLastName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderFirstName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardNbrFirstSix";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardNbrLastFour";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "InvoiceNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderPONbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CustomerID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IssuerResult";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SettlementAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IssTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RefNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UserName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ClerkID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "BatchSeqNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "BatchId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SiteTrace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DisplayName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PropertyInfo.LONG_CLASS;
            info.name = "ClientTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 21) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "UniqueDeviceId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 22) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AcctNbrLastFour";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 23) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "BankRoutingNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 24) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 25) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckFirstName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 26) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckLastName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 27) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 28) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GiftCurrency";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 29) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GiftMaskedAlias";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 30) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "OneTime";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 31) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PaymentMethodKey";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 32) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ScheduleID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +33 && propertyIndex <= +33 + this.ServiceName.size()) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ServiceName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +33 + this.ServiceName.size() && propertyIndex <= +33 + this.ServiceName.size() + this.PaymentType.size()) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PaymentType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +33 + this.ServiceName.size() + this.PaymentType.size() && propertyIndex <= +33 + this.ServiceName.size() + this.PaymentType.size() + this.CardType.size()) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
