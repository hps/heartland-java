package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosCheckSaleRspType extends AttributeContainer implements KvmSerializable {

    public Integer RspCode = 0;

    public String RspMessage;

    public String AuthCode;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public ArrayList<CheckRspInfoType> CheckRspInfo = new ArrayList<CheckRspInfoType>();

    public PosCheckSaleRspType() {
    }

    public PosCheckSaleRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

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
        if (soapObject.hasProperty("RspMessage")) {
            Object obj = soapObject.getProperty("RspMessage");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspMessage = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RspMessage = (String) obj;
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
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
        }
        if (soapObject.hasProperty("CheckRspInfo")) {
            int size = soapObject.getPropertyCount();
            this.CheckRspInfo = new ArrayList<CheckRspInfoType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("CheckRspInfo")) {
                    Object j = info.getValue();
                    CheckRspInfoType j1 = (CheckRspInfoType) envelope.get(j, CheckRspInfoType.class);
                    this.CheckRspInfo.add(j1);
                }
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
            return RspMessage != null ? RspMessage : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AuthCode != null ? AuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +4 && propertyIndex < +4 + this.CheckRspInfo.size()) {
            return CheckRspInfo.get(propertyIndex - (+4));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 4 + CheckRspInfo.size();
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
            info.name = "RspMessage";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +4 && propertyIndex <= +4 + this.CheckRspInfo.size()) {
            info.type = CheckRspInfoType.class;
            info.name = "CheckRspInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
