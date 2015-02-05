package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class PosBatchCloseRspType extends AttributeContainer implements KvmSerializable {

    public Integer BatchId = 0;

    public Integer TxnCnt = 0;

    public BigDecimal TotalAmt = BigDecimal.ZERO;

    public Integer BatchSeqNbr = 0;

    public PosBatchCloseRspType() {
    }

    public PosBatchCloseRspType(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;

        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("BatchId")) {
            Object obj = soapObject.getProperty("BatchId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BatchId = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.BatchId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("TxnCnt")) {
            Object obj = soapObject.getProperty("TxnCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.TxnCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("TotalAmt")) {
            Object obj = soapObject.getProperty("TotalAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TotalAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.TotalAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("BatchSeqNbr")) {
            Object obj = soapObject.getProperty("BatchSeqNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BatchSeqNbr = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.BatchSeqNbr = (Integer) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return BatchId;
        }
        if (propertyIndex == 1) {
            return TxnCnt;
        }
        if (propertyIndex == 2) {
            return TotalAmt != null ? TotalAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return BatchSeqNbr;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "BatchId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TxnCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TotalAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "BatchSeqNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
