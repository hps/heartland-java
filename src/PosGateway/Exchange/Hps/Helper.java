package PosGateway.Exchange.Hps;

import java.text.SimpleDateFormat;
import java.util.*;

import org.ksoap2.serialization.*;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

public class Helper {
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Object getAttribute(AttributeContainer obj, String name, String namespace) {
        for (int i = 0; i < obj.getAttributeCount(); i++) {
            AttributeInfo info = new AttributeInfo();
            obj.getAttributeInfo(i, info);
            if (info.name.equals(name) && info.namespace.equals(namespace)) {
                return info.getValue();
            }
        }
        return null;
    }

    public static Element convertToHeader(Object obj, String namespace, String name) {
        Element parentElement = new Element().createElement(namespace, name);
        if (obj == null) {
            return parentElement;
        }
        if (obj instanceof KvmSerializable) {
            KvmSerializable soapObject = (KvmSerializable) obj;
            for (int i = 0; i < soapObject.getPropertyCount(); i++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i, new Hashtable(), info);
                info.setValue(soapObject.getProperty(i));
                Element el1 = convertToHeader(info.getValue(), info.getNamespace(), info.getName());
                parentElement.addChild(Node.ELEMENT, el1);
            }
        } else {
            String value = obj.toString();
            if (obj instanceof Date) {
                Date date = (Date) obj;
                value = getDateFormat().format(date);
            }
            parentElement.addChild(Node.TEXT, value);
        }
        return parentElement;
    }

    public static Element findOutHeader(String name, SoapSerializationEnvelope envelope) {
        if (envelope.headerIn == null) {
            return null;
        }
        for (int i = 0; i < envelope.headerIn.length; i++) {
            Element elem = envelope.headerIn[i];
            if (elem.getName().equals(name) && elem.getChildCount() > 0)
                return elem;
        }
        return null;
    }

    public static Object convertToSoapObject(Element element) {
        if (element.getChildCount() == 0 || (element.getChildCount() == 1 && !(element.getChild(0) instanceof Element))) {
            SoapPrimitive primitive = new SoapPrimitive(element.getNamespace(), element.getName(), element.getChildCount() == 1 ? element.getText(0) : null);
            return primitive;
        } else {
            SoapObject obj = new SoapObject(element.getNamespace(), element.getName());
            for (int i = 0; i < element.getChildCount(); i++) {
                Element childElement = element.getElement(i);
                Object childObject = convertToSoapObject(childElement);
                if (childObject instanceof SoapObject) {
                    SoapObject soapObj = (SoapObject) childObject;
                    obj.addProperty(soapObj.getName(), childObject);
                } else {
                    SoapPrimitive primitive = (SoapPrimitive) childObject;
                    obj.addProperty(primitive.getName(), primitive);
                }
            }
            return obj;
        }
    }

    public static Date ConvertFromWebService(String strDate) {
        String[] formats = new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSS",
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss",
                "yyyy-MM-dd'T'HH:mm",
                "yyyy-MM-dd"
        };
        for (String frm : formats) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(frm, Locale.US);
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                return format.parse(strDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format;
    }


    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static ArrayList<PropertyInfo> getProperties(SoapObject soapObject, String name) {
        ArrayList<PropertyInfo> list = new ArrayList<PropertyInfo>();
        int size = soapObject.getPropertyCount();
        for (int i0 = 0; i0 < size; i0++) {
            PropertyInfo info = new PropertyInfo();
            soapObject.getPropertyInfo(i0, info);
            Object obj = info.getValue();
            if (info.name.equals(name)) {
                list.add(info);
            }
        }
        return list;
    }

    public static UUID emptyGuid() {
        return new UUID(0, 0);
    }
}