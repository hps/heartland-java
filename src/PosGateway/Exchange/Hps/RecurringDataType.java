package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class RecurringDataType extends AttributeContainer implements KvmSerializable {

    public String ScheduleID;

    public Enums.booleanType OneTime;

    public String RecurringDataCode;

    public RecurringDataType() {
    }

    public RecurringDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
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
        if (soapObject.hasProperty("RecurringDataCode")) {
            Object obj = soapObject.getProperty("RecurringDataCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RecurringDataCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RecurringDataCode = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return ScheduleID != null ? ScheduleID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return OneTime != null ? OneTime.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return RecurringDataCode != null ? RecurringDataCode : SoapPrimitive.NullSkip;
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
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ScheduleID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "OneTime";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RecurringDataCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
