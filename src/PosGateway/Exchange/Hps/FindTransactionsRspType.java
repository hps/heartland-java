package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class FindTransactionsRspType extends AttributeContainer implements KvmSerializable {

    public ArrayList<TransactionDataType> Transactions = new ArrayList<TransactionDataType>();

    public Enums.tzoneConversionType TzConversion;

    public FindTransactionsRspType() {
    }

    public FindTransactionsRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Transactions")) {
            int size = soapObject.getPropertyCount();
            this.Transactions = new ArrayList<TransactionDataType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("Transactions")) {
                    Object j = info.getValue();
                    TransactionDataType j1 = (TransactionDataType) envelope.get(j, TransactionDataType.class);
                    this.Transactions.add(j1);
                }
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


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return TzConversion != null ? TzConversion.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +1 && propertyIndex < +1 + this.Transactions.size()) {
            return Transactions.get(propertyIndex - (+1));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 1 + Transactions.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TzConversion";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +1 && propertyIndex <= +1 + this.Transactions.size()) {
            info.type = TransactionDataType.class;
            info.name = "Transactions";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
