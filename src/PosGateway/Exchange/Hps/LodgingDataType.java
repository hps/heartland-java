package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class LodgingDataType extends AttributeContainer implements KvmSerializable {

    public Enums.prestigiousPropertyType PrestigiousPropertyLimit;

    public Enums.booleanType NoShow;

    public Enums.advancedDepositTypeType AdvancedDepositType;

    public LodgingDataEditType LodgingDataEdit;

    public Enums.booleanType PreferredCustomer;

    public LodgingDataType() {
    }

    public LodgingDataType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("PrestigiousPropertyLimit")) {
            Object obj = soapObject.getProperty("PrestigiousPropertyLimit");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PrestigiousPropertyLimit = Enums.prestigiousPropertyType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.prestigiousPropertyType) {
                this.PrestigiousPropertyLimit = (Enums.prestigiousPropertyType) obj;
            }
        }
        if (soapObject.hasProperty("NoShow")) {
            Object obj = soapObject.getProperty("NoShow");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.NoShow = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.NoShow = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("AdvancedDepositType")) {
            Object obj = soapObject.getProperty("AdvancedDepositType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AdvancedDepositType = Enums.advancedDepositTypeType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.advancedDepositTypeType) {
                this.AdvancedDepositType = (Enums.advancedDepositTypeType) obj;
            }
        }
        if (soapObject.hasProperty("LodgingDataEdit")) {
            Object j = soapObject.getProperty("LodgingDataEdit");
            this.LodgingDataEdit = (LodgingDataEditType) envelope.get(j, LodgingDataEditType.class);
        }
        if (soapObject.hasProperty("PreferredCustomer")) {
            Object obj = soapObject.getProperty("PreferredCustomer");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PreferredCustomer = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.PreferredCustomer = (Enums.booleanType) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return PrestigiousPropertyLimit != null ? PrestigiousPropertyLimit.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return NoShow != null ? NoShow.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return AdvancedDepositType != null ? AdvancedDepositType.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return LodgingDataEdit != null ? LodgingDataEdit : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return PreferredCustomer != null ? PreferredCustomer.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 5;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PrestigiousPropertyLimit";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "NoShow";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AdvancedDepositType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = LodgingDataEditType.class;
            info.name = "LodgingDataEdit";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PreferredCustomer";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
