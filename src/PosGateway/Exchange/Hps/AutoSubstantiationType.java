package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class AutoSubstantiationType extends AttributeContainer implements KvmSerializable {
    public AdditionalAmtType FirstAdditionalAmtInfo;

    public AdditionalAmtType SecondAdditionalAmtInfo;

    public AdditionalAmtType ThirdAdditionalAmtInfo;

    public AdditionalAmtType FourthAdditionalAmtInfo;

    public String MerchantVerificationValue;

    public Enums.booleanType RealTimeSubstantiation;

    public AutoSubstantiationType() {
    }

    public AutoSubstantiationType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("FirstAdditionalAmtInfo")) {
            Object j = soapObject.getProperty("FirstAdditionalAmtInfo");
            this.FirstAdditionalAmtInfo = (AdditionalAmtType) envelope.get(j, AdditionalAmtType.class);
        }
        if (soapObject.hasProperty("SecondAdditionalAmtInfo")) {
            Object j = soapObject.getProperty("SecondAdditionalAmtInfo");
            this.SecondAdditionalAmtInfo = (AdditionalAmtType) envelope.get(j, AdditionalAmtType.class);
        }
        if (soapObject.hasProperty("ThirdAdditionalAmtInfo")) {
            Object j = soapObject.getProperty("ThirdAdditionalAmtInfo");
            this.ThirdAdditionalAmtInfo = (AdditionalAmtType) envelope.get(j, AdditionalAmtType.class);
        }
        if (soapObject.hasProperty("FourthAdditionalAmtInfo")) {
            Object j = soapObject.getProperty("FourthAdditionalAmtInfo");
            this.FourthAdditionalAmtInfo = (AdditionalAmtType) envelope.get(j, AdditionalAmtType.class);
        }
        if (soapObject.hasProperty("MerchantVerificationValue")) {
            Object obj = soapObject.getProperty("MerchantVerificationValue");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MerchantVerificationValue = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MerchantVerificationValue = (String) obj;
            }
        }
        if (soapObject.hasProperty("RealTimeSubstantiation")) {
            Object obj = soapObject.getProperty("RealTimeSubstantiation");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RealTimeSubstantiation = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.RealTimeSubstantiation = (Enums.booleanType) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return FirstAdditionalAmtInfo != null ? FirstAdditionalAmtInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return SecondAdditionalAmtInfo != null ? SecondAdditionalAmtInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return ThirdAdditionalAmtInfo != null ? ThirdAdditionalAmtInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return FourthAdditionalAmtInfo != null ? FourthAdditionalAmtInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return MerchantVerificationValue != null ? MerchantVerificationValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return RealTimeSubstantiation != null ? RealTimeSubstantiation.toString() : SoapPrimitive.NullSkip;
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
            info.type = AdditionalAmtType.class;
            info.name = "FirstAdditionalAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = AdditionalAmtType.class;
            info.name = "SecondAdditionalAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = AdditionalAmtType.class;
            info.name = "ThirdAdditionalAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = AdditionalAmtType.class;
            info.name = "FourthAdditionalAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MerchantVerificationValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RealTimeSubstantiation";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
