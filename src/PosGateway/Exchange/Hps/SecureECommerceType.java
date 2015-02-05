package PosGateway.Exchange.Hps;

import java.util.Hashtable;
import org.ksoap2.serialization.*;

public class SecureECommerceType extends AttributeContainer implements KvmSerializable
{
    
    public Enums.TypeOfPaymentDataType TypeOfPaymentData;
    
    public SecureECommerceTypePaymentData PaymentData;
    
    public String ECommerceIndicator;

    public SecureECommerceType ()
    {
    }

    public SecureECommerceType (java.lang.Object paramObj,ExtendedSoapSerializationEnvelope envelope)
    {
	    
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        SoapObject soapObject=(SoapObject)inObj;  
        if (soapObject.hasProperty("TypeOfPaymentData"))
        {	
	        java.lang.Object obj = soapObject.getProperty("TypeOfPaymentData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.TypeOfPaymentData = Enums.TypeOfPaymentDataType.fromString(j.toString());
                }
	        }
	        else if (obj!= null && obj instanceof Enums.TypeOfPaymentDataType){
                this.TypeOfPaymentData = (Enums.TypeOfPaymentDataType)obj;
            }    
        }
        if (soapObject.hasProperty("PaymentData"))
        {	
	        java.lang.Object obj = soapObject.getProperty("PaymentData");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.PaymentData = (SecureECommerceTypePaymentData)envelope.get(j,SecureECommerceTypePaymentData.class);
                }
	        }
        }
        if (soapObject.hasProperty("ECommerceIndicator"))
        {	
	        java.lang.Object obj = soapObject.getProperty("ECommerceIndicator");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.ECommerceIndicator = j.toString();
                }
	        }
	        else if (obj!= null && obj instanceof String){
                this.ECommerceIndicator = (String)obj;
            }    
        }


    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.TypeOfPaymentData!=null?this.TypeOfPaymentData.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.PaymentData!=null?this.PaymentData.getSimpleValue():null;
        }
        if(propertyIndex==2)
        {
            return this.ECommerceIndicator!=null?this.ECommerceIndicator:SoapPrimitive.NullSkip;
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
            info.name = "TypeOfPaymentData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==1)
        {
            info.type = SecureECommerceTypePaymentData.class;
            info.name = "PaymentData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ECommerceIndicator";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}
