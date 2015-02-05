package PosGateway.Exchange.Hps;

import org.ksoap2.serialization.*;

public class SecureECommerceTypePaymentData {

    public String value;

    public Enums.EncodingType encoding=Enums.EncodingType.base16;

    public SecureECommerceTypePaymentData() {
    }

    public SecureECommerceTypePaymentData(java.lang.Object paramObj, ExtendedSoapSerializationEnvelope envelope) {
	    if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;

        if(inObj!=null) {
            java.lang.Object j =(java.lang.Object)inObj;
            java.lang.Object obj=inObj;
            value = j.toString();
        }

        if (inObj.hasAttribute("encoding")) {
            java.lang.Object j = inObj.getAttribute("encoding");
            if (j != null) {
                encoding = Enums.EncodingType.fromString(j.toString());
            }
        }
    }

    public Object getSimpleValue() {
        Object j=this.value;
        TextSoapObject primitive = new TextSoapObject("http://Hps.Exchange.PosGateway", "PaymentData");
        primitive.setText(j!=null?j.toString():"");
        if (this.encoding != null) {
            AttributeInfo attrInfo = new AttributeInfo();
            attrInfo.setName("encoding");
            attrInfo.setValue(this.encoding);
            attrInfo.setNamespace("");
            primitive.addAttribute(attrInfo);
        }
        return primitive;
    }
}
