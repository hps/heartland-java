package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class FindTransactionsReqType extends AttributeContainer implements KvmSerializable {

    public SearchCriteriaType Criteria;

    public Enums.tzoneConversionType TzConversion;

    public Integer TxnId;

    public FindTransactionsReqType() {
    }

    public FindTransactionsReqType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Criteria")) {
            Object j = soapObject.getProperty("Criteria");
            this.Criteria = (SearchCriteriaType) envelope.get(j, SearchCriteriaType.class);
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
        if (soapObject.hasProperty("TxnId")) {
            Object obj = soapObject.getProperty("TxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.TxnId = (Integer) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Criteria != null ? Criteria : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return TzConversion != null ? TzConversion.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return TxnId != null ? TxnId : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = SearchCriteriaType.class;
            info.name = "Criteria";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TzConversion";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
