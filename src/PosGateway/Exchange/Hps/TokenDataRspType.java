package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class TokenDataRspType extends AttributeContainer implements KvmSerializable {

    public Integer TokenRspCode;

    public String TokenRspMsg;

    public String TokenValue;

    public TokenDataRspType() {
    }

    public TokenDataRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("TokenRspCode")) {
            Object obj = soapObject.getProperty("TokenRspCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenRspCode = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.TokenRspCode = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("TokenRspMsg")) {
            Object obj = soapObject.getProperty("TokenRspMsg");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenRspMsg = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TokenRspMsg = (String) obj;
            }
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
            return TokenRspCode != null ? TokenRspCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return TokenRspMsg != null ? TokenRspMsg : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return TokenValue != null ? TokenValue : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TokenRspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenRspMsg";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
