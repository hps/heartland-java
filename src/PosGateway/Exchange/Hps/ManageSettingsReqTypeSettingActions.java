package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class ManageSettingsReqTypeSettingActions extends AttributeContainer implements KvmSerializable {

    public ManageSettingsReqTypeSettingActionsSet Set = new ManageSettingsReqTypeSettingActionsSet();

    public ManageSettingsReqTypeSettingActionsQuery Query = new ManageSettingsReqTypeSettingActionsQuery();

    public ManageSettingsReqTypeSettingActions() {
    }

    public ManageSettingsReqTypeSettingActions(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("Set")) {
            Object j = soapObject.getProperty("Set");
            this.Set = new ManageSettingsReqTypeSettingActionsSet((AttributeContainer) j, envelope);
        }
        if (soapObject.hasProperty("Query")) {
            Object j = soapObject.getProperty("Query");
            this.Query = new ManageSettingsReqTypeSettingActionsQuery((AttributeContainer) j, envelope);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return Set != null ? Set : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return Query != null ? Query : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "Set";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "Query";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
