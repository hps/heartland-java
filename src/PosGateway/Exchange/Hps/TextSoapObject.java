package PosGateway.Exchange.Hps;

import org.ksoap2.serialization.SoapObject;

public class TextSoapObject extends SoapObject {
    public static final String TAG = TextSoapObject.class.getSimpleName();

    public TextSoapObject(String namespace,  String name){
        super(namespace, name);
    }

    public String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() { return this.text; }
}
