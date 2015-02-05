package PosGateway.Exchange.Hps;

import java.util.Hashtable;
import org.ksoap2.serialization.*;
import java.math.BigDecimal;

public class CreditAuthReqBlock1Type extends AttributeContainer implements KvmSerializable {
    public Integer GatewayTxnId;

    public CardDataType CardData;

    public String PaymentMethodKey;

    public PaymentMethodKeyData PaymentMethodKeyData;

    public BigDecimal Amt=BigDecimal.ZERO;

    public BigDecimal GratuityAmtInfo;

    public Enums.booleanType CPCReq;

    public CardHolderDataType CardHolderData;

    public DirectMktDataType DirectMktData;

    public Enums.booleanType AllowDup;

    public LodgingDataType LodgingData;

    public AutoSubstantiationType AutoSubstantiation;

    public Enums.booleanType AllowPartialAuth;

    public Enums.eCommerceType Ecommerce;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public origTxnRefDataType OrigTxnRefData;

    public BigDecimal ConvenienceAmtInfo;

    public BigDecimal ShippingAmtInfo;

    public String TxnDescriptor;

    public BigDecimal SurchargeAmtInfo;

    public EMVDataType EMVData;

    public SecureECommerceType SecureECommerce;

    public CreditAuthReqBlock1Type () { }

    public CreditAuthReqBlock1Type (java.lang.Object paramObj,ExtendedSoapSerializationEnvelope envelope) {
        if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;


        SoapObject soapObject=(SoapObject)inObj;
        if (soapObject.hasProperty("GatewayTxnId"))
        {
            java.lang.Object obj = soapObject.getProperty("GatewayTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.GatewayTxnId = Integer.parseInt(j.toString());
                }
            }
            else if (obj!= null && obj instanceof Integer){
                this.GatewayTxnId = (Integer)obj;
            }
        }
        if (soapObject.hasProperty("CardData"))
        {
            java.lang.Object j = soapObject.getProperty("CardData");
            this.CardData = (CardDataType)envelope.get(j,CardDataType.class);
        }
        if (soapObject.hasProperty("PaymentMethodKey"))
        {
            java.lang.Object obj = soapObject.getProperty("PaymentMethodKey");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.PaymentMethodKey = j.toString();
                }
            }
            else if (obj!= null && obj instanceof String){
                this.PaymentMethodKey = (String)obj;
            }
        }
        if (soapObject.hasProperty("PaymentMethodKeyData"))
        {
            java.lang.Object j = soapObject.getProperty("PaymentMethodKeyData");
            this.PaymentMethodKeyData = (PaymentMethodKeyData)envelope.get(j,PaymentMethodKeyData.class);
        }
        if (soapObject.hasProperty("Amt"))
        {
            java.lang.Object obj = soapObject.getProperty("Amt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.Amt = new BigDecimal(j.toString());
                }
            }
            else if (obj!= null && obj instanceof BigDecimal){
                this.Amt = (BigDecimal)obj;
            }
        }
        if (soapObject.hasProperty("GratuityAmtInfo"))
        {
            java.lang.Object obj = soapObject.getProperty("GratuityAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.GratuityAmtInfo = new BigDecimal(j.toString());
                }
            }
            else if (obj!= null && obj instanceof BigDecimal){
                this.GratuityAmtInfo = (BigDecimal)obj;
            }
        }
        if (soapObject.hasProperty("CPCReq"))
        {
            java.lang.Object obj = soapObject.getProperty("CPCReq");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.CPCReq = Enums.booleanType.fromString(j.toString());
                }
            }
            else if (obj!= null && obj instanceof Enums.booleanType){
                this.CPCReq = (Enums.booleanType)obj;
            }
        }
        if (soapObject.hasProperty("CardHolderData"))
        {
            java.lang.Object j = soapObject.getProperty("CardHolderData");
            this.CardHolderData = (CardHolderDataType)envelope.get(j,CardHolderDataType.class);
        }
        if (soapObject.hasProperty("DirectMktData"))
        {
            java.lang.Object j = soapObject.getProperty("DirectMktData");
            this.DirectMktData = (DirectMktDataType)envelope.get(j,DirectMktDataType.class);
        }
        if (soapObject.hasProperty("AllowDup"))
        {
            java.lang.Object obj = soapObject.getProperty("AllowDup");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.AllowDup = Enums.booleanType.fromString(j.toString());
                }
            }
            else if (obj!= null && obj instanceof Enums.booleanType){
                this.AllowDup = (Enums.booleanType)obj;
            }
        }
        if (soapObject.hasProperty("LodgingData"))
        {
            java.lang.Object j = soapObject.getProperty("LodgingData");
            this.LodgingData = (LodgingDataType)envelope.get(j,LodgingDataType.class);
        }
        if (soapObject.hasProperty("AutoSubstantiation"))
        {
            java.lang.Object j = soapObject.getProperty("AutoSubstantiation");
            this.AutoSubstantiation = (AutoSubstantiationType)envelope.get(j,AutoSubstantiationType.class);
        }
        if (soapObject.hasProperty("AllowPartialAuth"))
        {
            java.lang.Object obj = soapObject.getProperty("AllowPartialAuth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.AllowPartialAuth = Enums.booleanType.fromString(j.toString());
                }
            }
            else if (obj!= null && obj instanceof Enums.booleanType){
                this.AllowPartialAuth = (Enums.booleanType)obj;
            }
        }
        if (soapObject.hasProperty("Ecommerce"))
        {
            java.lang.Object obj = soapObject.getProperty("Ecommerce");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.Ecommerce = Enums.eCommerceType.fromString(j.toString());
                }
            }
            else if (obj!= null && obj instanceof Enums.eCommerceType){
                this.Ecommerce = (Enums.eCommerceType)obj;
            }
        }
        if (soapObject.hasProperty("AdditionalTxnFields"))
        {
            java.lang.Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType)envelope.get(j,AdditionalTxnFieldsType.class);
        }
        if (soapObject.hasProperty("OrigTxnRefData"))
        {
            java.lang.Object j = soapObject.getProperty("OrigTxnRefData");
            this.OrigTxnRefData = (origTxnRefDataType)envelope.get(j,origTxnRefDataType.class);
        }
        if (soapObject.hasProperty("ConvenienceAmtInfo"))
        {
            java.lang.Object obj = soapObject.getProperty("ConvenienceAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.ConvenienceAmtInfo = new BigDecimal(j.toString());
                }
            }
            else if (obj!= null && obj instanceof BigDecimal){
                this.ConvenienceAmtInfo = (BigDecimal)obj;
            }
        }
        if (soapObject.hasProperty("ShippingAmtInfo"))
        {
            java.lang.Object obj = soapObject.getProperty("ShippingAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.ShippingAmtInfo = new BigDecimal(j.toString());
                }
            }
            else if (obj!= null && obj instanceof BigDecimal){
                this.ShippingAmtInfo = (BigDecimal)obj;
            }
        }
        if (soapObject.hasProperty("TxnDescriptor"))
        {
            java.lang.Object obj = soapObject.getProperty("TxnDescriptor");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.TxnDescriptor = j.toString();
                }
            }
            else if (obj!= null && obj instanceof String){
                this.TxnDescriptor = (String)obj;
            }
        }
        if (soapObject.hasProperty("SurchargeAmtInfo"))
        {
            java.lang.Object obj = soapObject.getProperty("SurchargeAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class))
            {
                SoapPrimitive j =(SoapPrimitive) obj;
                if(j.toString()!=null)
                {
                    this.SurchargeAmtInfo = new BigDecimal(j.toString());
                }
            }
            else if (obj!= null && obj instanceof BigDecimal){
                this.SurchargeAmtInfo = (BigDecimal)obj;
            }
        }
        if (soapObject.hasProperty("EMVData"))
        {
            java.lang.Object j = soapObject.getProperty("EMVData");
            this.EMVData = (EMVDataType)envelope.get(j,EMVDataType.class);
        }
        if (soapObject.hasProperty("SecureECommerce"))
        {
            java.lang.Object j = soapObject.getProperty("SecureECommerce");
            this.SecureECommerce = (SecureECommerceType)envelope.get(j,SecureECommerceType.class);
        }


    }

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.GatewayTxnId!=null?this.GatewayTxnId:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.CardData!=null?this.CardData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.PaymentMethodKey!=null?this.PaymentMethodKey:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.PaymentMethodKeyData!=null?this.PaymentMethodKeyData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.Amt!=null?this.Amt.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.GratuityAmtInfo!=null?this.GratuityAmtInfo.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.CPCReq!=null?this.CPCReq.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.CardHolderData!=null?this.CardHolderData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.DirectMktData!=null?this.DirectMktData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==9)
        {
            return this.AllowDup!=null?this.AllowDup.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==10)
        {
            return this.LodgingData!=null?this.LodgingData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==11)
        {
            return this.AutoSubstantiation!=null?this.AutoSubstantiation:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==12)
        {
            return this.AllowPartialAuth!=null?this.AllowPartialAuth.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==13)
        {
            return this.Ecommerce!=null?this.Ecommerce.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==14)
        {
            return this.AdditionalTxnFields!=null?this.AdditionalTxnFields:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==15)
        {
            return this.OrigTxnRefData!=null?this.OrigTxnRefData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==16)
        {
            return this.ConvenienceAmtInfo!=null?this.ConvenienceAmtInfo.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==17)
        {
            return this.ShippingAmtInfo!=null?this.ShippingAmtInfo.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==18)
        {
            return this.TxnDescriptor!=null?this.TxnDescriptor:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==19)
        {
            return this.SurchargeAmtInfo!=null?this.SurchargeAmtInfo.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==20)
        {
            return this.EMVData!=null?this.EMVData:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==21)
        {
            return this.SecureECommerce!=null?this.SecureECommerce:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 22;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "GatewayTxnId";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==1)
        {
            info.type = CardDataType.class;
            info.name = "CardData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PaymentMethodKey";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==3)
        {
            info.type = PaymentMethodKeyData.class;
            info.name = "PaymentMethodKeyData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GratuityAmtInfo";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCReq";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==7)
        {
            info.type = CardHolderDataType.class;
            info.name = "CardHolderData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==8)
        {
            info.type = DirectMktDataType.class;
            info.name = "DirectMktData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowDup";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==10)
        {
            info.type = LodgingDataType.class;
            info.name = "LodgingData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==11)
        {
            info.type = AutoSubstantiationType.class;
            info.name = "AutoSubstantiation";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==12)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowPartialAuth";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==13)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Ecommerce";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==14)
        {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==15)
        {
            info.type = origTxnRefDataType.class;
            info.name = "OrigTxnRefData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==16)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ConvenienceAmtInfo";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==17)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ShippingAmtInfo";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==18)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnDescriptor";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==19)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SurchargeAmtInfo";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==20)
        {
            info.type = EMVDataType.class;
            info.name = "EMVData";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
        if(propertyIndex==21)
        {
            info.type = SecureECommerceType.class;
            info.name = "SecureECommerce";
            info.namespace= "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

}
