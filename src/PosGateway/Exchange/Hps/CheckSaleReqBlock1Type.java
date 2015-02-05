package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class CheckSaleReqBlock1Type extends AttributeContainer implements KvmSerializable {

    public Enums.checkActionType CheckAction = Enums.checkActionType.SALE;

    public AccountInfoType AccountInfo;

    public Enums.dataEntryModeType DataEntryMode;

    public Enums.checkTypeType CheckType;

    public VerifyInfoType VerifyInfo;

    public BigDecimal Amt;

    public String SECCode;

    public ConsumerInfoType ConsumerInfo;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public String PaymentMethodKey;

    public RecurringDataType RecurringData;

    public String TokenValue;

    public CheckSaleReqBlock1Type() {
    }

    public CheckSaleReqBlock1Type(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("CheckAction")) {
            Object obj = soapObject.getProperty("CheckAction");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckAction = Enums.checkActionType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.checkActionType) {
                this.CheckAction = (Enums.checkActionType) obj;
            }
        }
        if (soapObject.hasProperty("AccountInfo")) {
            Object j = soapObject.getProperty("AccountInfo");
            this.AccountInfo = (AccountInfoType) envelope.get(j, AccountInfoType.class);
        }
        if (soapObject.hasProperty("DataEntryMode")) {
            Object obj = soapObject.getProperty("DataEntryMode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DataEntryMode = Enums.dataEntryModeType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.dataEntryModeType) {
                this.DataEntryMode = (Enums.dataEntryModeType) obj;
            }
        }
        if (soapObject.hasProperty("CheckType")) {
            Object obj = soapObject.getProperty("CheckType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CheckType = Enums.checkTypeType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.checkTypeType) {
                this.CheckType = (Enums.checkTypeType) obj;
            }
        }
        if (soapObject.hasProperty("VerifyInfo")) {
            Object j = soapObject.getProperty("VerifyInfo");
            this.VerifyInfo = (VerifyInfoType) envelope.get(j, VerifyInfoType.class);
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
        if (soapObject.hasProperty("SECCode")) {
            Object obj = soapObject.getProperty("SECCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SECCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SECCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("ConsumerInfo")) {
            Object j = soapObject.getProperty("ConsumerInfo");
            this.ConsumerInfo = (ConsumerInfoType) envelope.get(j, ConsumerInfoType.class);
        }
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
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
        if (soapObject.hasProperty("TokenValue")) {
            Object obj = soapObject.getProperty("TokenValue");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenValue = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TokenValue = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return CheckAction != null ? CheckAction.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return AccountInfo != null ? AccountInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return DataEntryMode != null ? DataEntryMode.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CheckType != null ? CheckType.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return VerifyInfo != null ? VerifyInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return SECCode != null ? SECCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return ConsumerInfo != null ? ConsumerInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return PaymentMethodKey != null ? PaymentMethodKey : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return RecurringData != null ? RecurringData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return TokenValue != null ? TokenValue : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckAction";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = AccountInfoType.class;
            info.name = "AccountInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DataEntryMode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = VerifyInfoType.class;
            info.name = "VerifyInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SECCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = ConsumerInfoType.class;
            info.name = "ConsumerInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PaymentMethodKey";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = RecurringDataType.class;
            info.name = "RecurringData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
