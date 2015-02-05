package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;

public class PosReportBatchSummaryRspTypeDetails extends AttributeContainer implements KvmSerializable {

    public String CardType;

    public Integer CreditCnt = 0;

    public BigDecimal CreditAmt = BigDecimal.ZERO;

    public Integer DebitCnt = 0;

    public BigDecimal DebitAmt = BigDecimal.ZERO;

    public Integer SaleCnt = 0;

    public BigDecimal SaleAmt = BigDecimal.ZERO;

    public Integer ReturnCnt = 0;

    public BigDecimal ReturnAmt = BigDecimal.ZERO;

    public Integer TotalCnt = 0;

    public BigDecimal TotalAmt = BigDecimal.ZERO;

    public BigDecimal TotalGratuityAmtInfo = BigDecimal.ZERO;

    public PosReportBatchSummaryRspTypeDetails() {
    }

    public PosReportBatchSummaryRspTypeDetails(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("CardType")) {
            Object obj = soapObject.getProperty("CardType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardType = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardType = (String) obj;
            }
        }
        if (soapObject.hasProperty("CreditCnt")) {
            Object obj = soapObject.getProperty("CreditCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.CreditCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("CreditAmt")) {
            Object obj = soapObject.getProperty("CreditAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.CreditAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("DebitCnt")) {
            Object obj = soapObject.getProperty("DebitCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DebitCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DebitCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DebitAmt")) {
            Object obj = soapObject.getProperty("DebitAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DebitAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.DebitAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("SaleCnt")) {
            Object obj = soapObject.getProperty("SaleCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SaleCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.SaleCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("SaleAmt")) {
            Object obj = soapObject.getProperty("SaleAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SaleAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SaleAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("ReturnCnt")) {
            Object obj = soapObject.getProperty("ReturnCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReturnCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.ReturnCnt = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("ReturnAmt")) {
            Object obj = soapObject.getProperty("ReturnAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReturnAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ReturnAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("TotalCnt")) {
            Object obj = soapObject.getProperty("TotalCnt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TotalCnt = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.TotalCnt = (Integer) obj;
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
        if (soapObject.hasProperty("TotalGratuityAmtInfo")) {
            Object obj = soapObject.getProperty("TotalGratuityAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TotalGratuityAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.TotalGratuityAmtInfo = (BigDecimal) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return CardType != null ? CardType : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return CreditCnt;
        }
        if (propertyIndex == 2) {
            return CreditAmt != null ? CreditAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return DebitCnt;
        }
        if (propertyIndex == 4) {
            return DebitAmt != null ? DebitAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return SaleCnt;
        }
        if (propertyIndex == 6) {
            return SaleAmt != null ? SaleAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return ReturnCnt;
        }
        if (propertyIndex == 8) {
            return ReturnAmt != null ? ReturnAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return TotalCnt;
        }
        if (propertyIndex == 10) {
            return TotalAmt != null ? TotalAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return TotalGratuityAmtInfo != null ? TotalGratuityAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 12;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "CreditCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DebitCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DebitAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "SaleCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SaleAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "ReturnCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReturnAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TotalCnt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TotalAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TotalGratuityAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
