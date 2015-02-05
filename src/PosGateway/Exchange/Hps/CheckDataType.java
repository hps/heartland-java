package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class CheckDataType extends AttributeContainer implements KvmSerializable {

    public AccountInfoType AccountInfo;

    public ConsumerInfoType ConsumerInfo;

    public Enums.dataEntryModeType DataEntryMode;

    public Enums.checkTypeType CheckType;

    public String SECCode;

    public Enums.checkActionType CheckAction = Enums.checkActionType.SALE;

    public CheckDataType() {
    }

    public CheckDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;

        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("AccountInfo")) {
            Object j = soapObject.getProperty("AccountInfo");
            this.AccountInfo = (AccountInfoType) envelope.get(j, AccountInfoType.class);
        }
        if (soapObject.hasProperty("ConsumerInfo")) {
            Object j = soapObject.getProperty("ConsumerInfo");
            this.ConsumerInfo = (ConsumerInfoType) envelope.get(j, ConsumerInfoType.class);
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


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return AccountInfo != null ? AccountInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return ConsumerInfo != null ? ConsumerInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return DataEntryMode != null ? DataEntryMode.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CheckType != null ? CheckType.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return SECCode != null ? SECCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CheckAction != null ? CheckAction.toString() : SoapPrimitive.NullSkip;
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
            info.type = AccountInfoType.class;
            info.name = "AccountInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = ConsumerInfoType.class;
            info.name = "ConsumerInfo";
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SECCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CheckAction";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
