package PosGateway.Exchange.Hps;

import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class EMVDataType extends AttributeContainer implements KvmSerializable
{

    public String EMVTagData;

    public Enums.emvChipConditionType EMVChipCondition;

    public String PINBlock;

    public EMVDataType ()
    {
    }

    public EMVDataType (java.lang.Object paramObj,ExtendedSoapSerializationEnvelope envelope)
    {

        if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        SoapObject soapObject=(SoapObject)inObj;
        if (soapObject.hasProperty("EMVTagData"))
        {
            java.lang.Object obj = soapObject.getProperty("EMVTagData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.EMVTagData = j.toString();
                }
            }
            else if (obj!= null && obj instanceof String){
                this.EMVTagData = (String)obj;
            }
        }
        if (soapObject.hasProperty("EMVChipCondition"))
        {
            java.lang.Object obj = soapObject.getProperty("EMVChipCondition");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.EMVChipCondition = Enums.emvChipConditionType.fromString(j.toString());
                }
            }
            else if (obj!= null && obj instanceof Enums.emvChipConditionType){
                this.EMVChipCondition = (Enums.emvChipConditionType)obj;
            }
        }
        if (soapObject.hasProperty("PINBlock"))
        {
            java.lang.Object obj = soapObject.getProperty("PINBlock");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.PINBlock = j.toString();
                }
            }
            else if (obj!= null && obj instanceof String){
                this.PINBlock = (String)obj;
            }
        }


    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.EMVTagData!=null?this.EMVTagData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.EMVChipCondition!=null?this.EMVChipCondition.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.PINBlock!=null?this.PINBlock:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EMVTagData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EMVChipCondition";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PINBlock";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}
