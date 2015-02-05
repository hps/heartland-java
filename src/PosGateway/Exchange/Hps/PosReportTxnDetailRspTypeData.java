package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

public class PosReportTxnDetailRspTypeData extends AttributeContainer implements KvmSerializable {

    public String TxnStatus;

    public String CardType;

    public String MaskedCardNbr;

    public String CardPresent;

    public String ReaderPresent;

    public String CardSwiped;

    public String DebitCreditInd;

    public String SaleReturnInd;

    public String CVVReq;

    public String CVVRsltCode;

    public BigDecimal Amt = BigDecimal.ZERO;

    public BigDecimal AuthAmt = BigDecimal.ZERO;

    public BigDecimal GratuityAmtInfo = BigDecimal.ZERO;

    public BigDecimal CashbackAmtInfo = BigDecimal.ZERO;

    public String CardHolderLastName;

    public String CardHolderFirstName;

    public String CardHolderAddr;

    public String CardHolderCity;

    public String CardHolderState;

    public String CardHolderZip;

    public String CardHolderPhone;

    public String CardHolderEmail;

    public String AVSRsltCode;

    public String CPCReq;

    public String CPCInd;

    public String RspCode;

    public String RspText;

    public String AuthCode;

    public String ReqACI;

    public String RspACI;

    public String MktSpecDataId;

    public String TxnCode;

    public String AcctDataSrc;

    public String AuthSrcCode;

    public String IssTxnId;

    public String IssValidationCode;

    public String CardHolderIdCode;

    public String NetworkIdCode;

    public String RefNbr;

    public Integer TxnSeqNbr = 0;

    public String DirectMktInvoiceNbr;

    public Integer DirectMktShipMonth = 0;

    public Integer DirectMktShipDay = 0;

    public String CPCCardHolderPONbr;

    public String CPCTaxType;

    public BigDecimal CPCTaxAmt = BigDecimal.ZERO;

    public BigDecimal SettlementAmt = BigDecimal.ZERO;

    public Enums.booleanType AllowPartialAuth = Enums.booleanType.N;

    public AutoSubstantiationReportType AutoSubstantiation;

    public AdditionalTxnFieldsType AdditionalTxnFields;

    public AttachmentRspDataType Attachment;

    public String AVSRsltText;

    public String CVVRsltText;

    public Enums.eCommerceType Ecommerce;

    public String TokenizationMsg;

    public String CryptoTypeIn;

    public String CryptoTypeOut;

    public String ClerkID;

    public CredentialDataType CredentialData;

    public LodgingDataType LodgingData;

    public BigDecimal ConvenienceAmtInfo;

    public BigDecimal ShippingAmtInfo;

    public String TxnDescriptor;

    public CheckDataInfoType CheckDataInfo;

    public ArrayList<AttachmentInfoType> AttachmentInfo = new ArrayList<AttachmentInfoType>();

    public GiftDataType GiftData;

    public String PaymentMethodKey;

    public RecurringDataType RecurringData;

    public BigDecimal SurchargeAmtInfo;

    public CashDataType CashData;

    public BigDecimal ReturnAmtInfo;

    public BigDecimal ReversalAmtInfo;

    public BigDecimal IncrementalAuthAmtInfo;

    public String AllowDup;

    public String SystemAuditTraceNumber;

    public java.util.Date LodgingCheckInDate;

    public java.util.Date LodgingCheckOutDate;

    public String ProcessorRefNum;

    public String ReversalIndicator;

    public String TokenRequested;

    public String TokenGenerated;

    public PosReportTxnDetailRspTypeData() {
    }

    public PosReportTxnDetailRspTypeData(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("TxnStatus")) {
            Object obj = soapObject.getProperty("TxnStatus");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnStatus = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TxnStatus = (String) obj;
            }
        }
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
        if (soapObject.hasProperty("MaskedCardNbr")) {
            Object obj = soapObject.getProperty("MaskedCardNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MaskedCardNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MaskedCardNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardPresent")) {
            Object obj = soapObject.getProperty("CardPresent");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardPresent = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardPresent = (String) obj;
            }
        }
        if (soapObject.hasProperty("ReaderPresent")) {
            Object obj = soapObject.getProperty("ReaderPresent");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReaderPresent = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ReaderPresent = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardSwiped")) {
            Object obj = soapObject.getProperty("CardSwiped");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardSwiped = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardSwiped = (String) obj;
            }
        }
        if (soapObject.hasProperty("DebitCreditInd")) {
            Object obj = soapObject.getProperty("DebitCreditInd");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DebitCreditInd = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DebitCreditInd = (String) obj;
            }
        }
        if (soapObject.hasProperty("SaleReturnInd")) {
            Object obj = soapObject.getProperty("SaleReturnInd");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SaleReturnInd = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SaleReturnInd = (String) obj;
            }
        }
        if (soapObject.hasProperty("CVVReq")) {
            Object obj = soapObject.getProperty("CVVReq");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVVReq = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CVVReq = (String) obj;
            }
        }
        if (soapObject.hasProperty("CVVRsltCode")) {
            Object obj = soapObject.getProperty("CVVRsltCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVVRsltCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CVVRsltCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("Amt")) {
            Object obj = soapObject.getProperty("Amt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Amt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.Amt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("AuthAmt")) {
            Object obj = soapObject.getProperty("AuthAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AuthAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.AuthAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("GratuityAmtInfo")) {
            Object obj = soapObject.getProperty("GratuityAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GratuityAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.GratuityAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("CashbackAmtInfo")) {
            Object obj = soapObject.getProperty("CashbackAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CashbackAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.CashbackAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderLastName")) {
            Object obj = soapObject.getProperty("CardHolderLastName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderLastName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderLastName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderFirstName")) {
            Object obj = soapObject.getProperty("CardHolderFirstName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderFirstName = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderFirstName = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderAddr")) {
            Object obj = soapObject.getProperty("CardHolderAddr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderAddr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderAddr = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderCity")) {
            Object obj = soapObject.getProperty("CardHolderCity");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderCity = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderCity = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderState")) {
            Object obj = soapObject.getProperty("CardHolderState");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderState = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderState = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderZip")) {
            Object obj = soapObject.getProperty("CardHolderZip");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderZip = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderZip = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderPhone")) {
            Object obj = soapObject.getProperty("CardHolderPhone");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderPhone = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderPhone = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderEmail")) {
            Object obj = soapObject.getProperty("CardHolderEmail");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderEmail = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderEmail = (String) obj;
            }
        }
        if (soapObject.hasProperty("AVSRsltCode")) {
            Object obj = soapObject.getProperty("AVSRsltCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AVSRsltCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AVSRsltCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("CPCReq")) {
            Object obj = soapObject.getProperty("CPCReq");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CPCReq = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CPCReq = (String) obj;
            }
        }
        if (soapObject.hasProperty("CPCInd")) {
            Object obj = soapObject.getProperty("CPCInd");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CPCInd = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CPCInd = (String) obj;
            }
        }
        if (soapObject.hasProperty("RspCode")) {
            Object obj = soapObject.getProperty("RspCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RspCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("RspText")) {
            Object obj = soapObject.getProperty("RspText");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspText = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RspText = (String) obj;
            }
        }
        if (soapObject.hasProperty("AuthCode")) {
            Object obj = soapObject.getProperty("AuthCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AuthCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AuthCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("ReqACI")) {
            Object obj = soapObject.getProperty("ReqACI");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReqACI = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ReqACI = (String) obj;
            }
        }
        if (soapObject.hasProperty("RspACI")) {
            Object obj = soapObject.getProperty("RspACI");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RspACI = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RspACI = (String) obj;
            }
        }
        if (soapObject.hasProperty("MktSpecDataId")) {
            Object obj = soapObject.getProperty("MktSpecDataId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.MktSpecDataId = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.MktSpecDataId = (String) obj;
            }
        }
        if (soapObject.hasProperty("TxnCode")) {
            Object obj = soapObject.getProperty("TxnCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TxnCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("AcctDataSrc")) {
            Object obj = soapObject.getProperty("AcctDataSrc");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AcctDataSrc = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AcctDataSrc = (String) obj;
            }
        }
        if (soapObject.hasProperty("AuthSrcCode")) {
            Object obj = soapObject.getProperty("AuthSrcCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AuthSrcCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AuthSrcCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("IssTxnId")) {
            Object obj = soapObject.getProperty("IssTxnId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IssTxnId = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.IssTxnId = (String) obj;
            }
        }
        if (soapObject.hasProperty("IssValidationCode")) {
            Object obj = soapObject.getProperty("IssValidationCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IssValidationCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.IssValidationCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("CardHolderIdCode")) {
            Object obj = soapObject.getProperty("CardHolderIdCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CardHolderIdCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CardHolderIdCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("NetworkIdCode")) {
            Object obj = soapObject.getProperty("NetworkIdCode");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.NetworkIdCode = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.NetworkIdCode = (String) obj;
            }
        }
        if (soapObject.hasProperty("RefNbr")) {
            Object obj = soapObject.getProperty("RefNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.RefNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.RefNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("TxnSeqNbr")) {
            Object obj = soapObject.getProperty("TxnSeqNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnSeqNbr = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.TxnSeqNbr = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DirectMktInvoiceNbr")) {
            Object obj = soapObject.getProperty("DirectMktInvoiceNbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DirectMktInvoiceNbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.DirectMktInvoiceNbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("DirectMktShipMonth")) {
            Object obj = soapObject.getProperty("DirectMktShipMonth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DirectMktShipMonth = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DirectMktShipMonth = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("DirectMktShipDay")) {
            Object obj = soapObject.getProperty("DirectMktShipDay");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.DirectMktShipDay = Integer.parseInt(j.toString());
                }
            } else if (obj != null && obj instanceof Integer) {
                this.DirectMktShipDay = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("CPCCardHolderPONbr")) {
            Object obj = soapObject.getProperty("CPCCardHolderPONbr");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CPCCardHolderPONbr = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CPCCardHolderPONbr = (String) obj;
            }
        }
        if (soapObject.hasProperty("CPCTaxType")) {
            Object obj = soapObject.getProperty("CPCTaxType");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CPCTaxType = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CPCTaxType = (String) obj;
            }
        }
        if (soapObject.hasProperty("CPCTaxAmt")) {
            Object obj = soapObject.getProperty("CPCTaxAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CPCTaxAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.CPCTaxAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("SettlementAmt")) {
            Object obj = soapObject.getProperty("SettlementAmt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SettlementAmt = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SettlementAmt = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("AllowPartialAuth")) {
            Object obj = soapObject.getProperty("AllowPartialAuth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AllowPartialAuth = Enums.booleanType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.booleanType) {
                this.AllowPartialAuth = (Enums.booleanType) obj;
            }
        }
        if (soapObject.hasProperty("AutoSubstantiation")) {
            Object j = soapObject.getProperty("AutoSubstantiation");
            this.AutoSubstantiation = (AutoSubstantiationReportType) envelope.get(j, AutoSubstantiationReportType.class);
        }
        if (soapObject.hasProperty("AdditionalTxnFields")) {
            Object j = soapObject.getProperty("AdditionalTxnFields");
            this.AdditionalTxnFields = (AdditionalTxnFieldsType) envelope.get(j, AdditionalTxnFieldsType.class);
        }
        if (soapObject.hasProperty("Attachment")) {
            Object j = soapObject.getProperty("Attachment");
            this.Attachment = (AttachmentRspDataType) envelope.get(j, AttachmentRspDataType.class);
        }
        if (soapObject.hasProperty("AVSRsltText")) {
            Object obj = soapObject.getProperty("AVSRsltText");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AVSRsltText = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AVSRsltText = (String) obj;
            }
        }
        if (soapObject.hasProperty("CVVRsltText")) {
            Object obj = soapObject.getProperty("CVVRsltText");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CVVRsltText = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CVVRsltText = (String) obj;
            }
        }
        if (soapObject.hasProperty("Ecommerce")) {
            Object obj = soapObject.getProperty("Ecommerce");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Ecommerce = Enums.eCommerceType.fromString(j.toString());
                }
            } else if (obj != null && obj instanceof Enums.eCommerceType) {
                this.Ecommerce = (Enums.eCommerceType) obj;
            }
        }
        if (soapObject.hasProperty("TokenizationMsg")) {
            Object obj = soapObject.getProperty("TokenizationMsg");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenizationMsg = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TokenizationMsg = (String) obj;
            }
        }
        if (soapObject.hasProperty("CryptoTypeIn")) {
            Object obj = soapObject.getProperty("CryptoTypeIn");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CryptoTypeIn = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CryptoTypeIn = (String) obj;
            }
        }
        if (soapObject.hasProperty("CryptoTypeOut")) {
            Object obj = soapObject.getProperty("CryptoTypeOut");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CryptoTypeOut = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CryptoTypeOut = (String) obj;
            }
        }
        if (soapObject.hasProperty("ClerkID")) {
            Object obj = soapObject.getProperty("ClerkID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ClerkID = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ClerkID = (String) obj;
            }
        }
        if (soapObject.hasProperty("CredentialData")) {
            Object j = soapObject.getProperty("CredentialData");
            this.CredentialData = (CredentialDataType) envelope.get(j, CredentialDataType.class);
        }
        if (soapObject.hasProperty("LodgingData")) {
            Object j = soapObject.getProperty("LodgingData");
            this.LodgingData = (LodgingDataType) envelope.get(j, LodgingDataType.class);
        }
        if (soapObject.hasProperty("ConvenienceAmtInfo")) {
            Object obj = soapObject.getProperty("ConvenienceAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ConvenienceAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ConvenienceAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("ShippingAmtInfo")) {
            Object obj = soapObject.getProperty("ShippingAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ShippingAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ShippingAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("TxnDescriptor")) {
            Object obj = soapObject.getProperty("TxnDescriptor");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TxnDescriptor = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TxnDescriptor = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckDataInfo")) {
            Object j = soapObject.getProperty("CheckDataInfo");
            this.CheckDataInfo = (CheckDataInfoType) envelope.get(j, CheckDataInfoType.class);
        }
        if (soapObject.hasProperty("AttachmentInfo")) {
            int size = soapObject.getPropertyCount();
            this.AttachmentInfo = new ArrayList<AttachmentInfoType>();
            for (int i0 = 0; i0 < size; i0++) {
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i0, info);
                Object obj = info.getValue();
                if (obj != null && info.name.equals("AttachmentInfo")) {
                    Object j = info.getValue();
                    AttachmentInfoType j1 = (AttachmentInfoType) envelope.get(j, AttachmentInfoType.class);
                    this.AttachmentInfo.add(j1);
                }
            }
        }
        if (soapObject.hasProperty("GiftData")) {
            Object j = soapObject.getProperty("GiftData");
            this.GiftData = (GiftDataType) envelope.get(j, GiftDataType.class);
        }
        if (soapObject.hasProperty("PaymentMethodKey")) {
            Object obj = soapObject.getProperty("PaymentMethodKey");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.PaymentMethodKey = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.PaymentMethodKey = (String) obj;
            }
        }
        if (soapObject.hasProperty("RecurringData")) {
            Object j = soapObject.getProperty("RecurringData");
            this.RecurringData = (RecurringDataType) envelope.get(j, RecurringDataType.class);
        }
        if (soapObject.hasProperty("SurchargeAmtInfo")) {
            Object obj = soapObject.getProperty("SurchargeAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SurchargeAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.SurchargeAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("CashData")) {
            Object j = soapObject.getProperty("CashData");
            this.CashData = (CashDataType) envelope.get(j, CashDataType.class);
        }
        if (soapObject.hasProperty("ReturnAmtInfo")) {
            Object obj = soapObject.getProperty("ReturnAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReturnAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ReturnAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("ReversalAmtInfo")) {
            Object obj = soapObject.getProperty("ReversalAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReversalAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.ReversalAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("IncrementalAuthAmtInfo")) {
            Object obj = soapObject.getProperty("IncrementalAuthAmtInfo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.IncrementalAuthAmtInfo = new BigDecimal(j.toString());
                }
            } else if (obj != null && obj instanceof BigDecimal) {
                this.IncrementalAuthAmtInfo = (BigDecimal) obj;
            }
        }
        if (soapObject.hasProperty("AllowDup")) {
            Object obj = soapObject.getProperty("AllowDup");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AllowDup = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AllowDup = (String) obj;
            }
        }
        if (soapObject.hasProperty("SystemAuditTraceNumber")) {
            Object obj = soapObject.getProperty("SystemAuditTraceNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SystemAuditTraceNumber = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SystemAuditTraceNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("LodgingCheckInDate")) {
            Object obj = soapObject.getProperty("LodgingCheckInDate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.LodgingCheckInDate = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.LodgingCheckInDate = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("LodgingCheckOutDate")) {
            Object obj = soapObject.getProperty("LodgingCheckOutDate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.LodgingCheckOutDate = Helper.ConvertFromWebService(j.toString());
                }
            } else if (obj != null && obj instanceof java.util.Date) {
                this.LodgingCheckOutDate = (java.util.Date) obj;
            }
        }
        if (soapObject.hasProperty("ProcessorRefNum")) {
            Object obj = soapObject.getProperty("ProcessorRefNum");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ProcessorRefNum = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ProcessorRefNum = (String) obj;
            }
        }
        if (soapObject.hasProperty("ReversalIndicator")) {
            Object obj = soapObject.getProperty("ReversalIndicator");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.ReversalIndicator = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.ReversalIndicator = (String) obj;
            }
        }
        if (soapObject.hasProperty("TokenRequested")) {
            Object obj = soapObject.getProperty("TokenRequested");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenRequested = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TokenRequested = (String) obj;
            }
        }
        if (soapObject.hasProperty("TokenGenerated")) {
            Object obj = soapObject.getProperty("TokenGenerated");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TokenGenerated = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TokenGenerated = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return TxnStatus != null ? TxnStatus : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return CardType != null ? CardType : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return MaskedCardNbr != null ? MaskedCardNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return CardPresent != null ? CardPresent : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return ReaderPresent != null ? ReaderPresent : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return CardSwiped != null ? CardSwiped : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return DebitCreditInd != null ? DebitCreditInd : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return SaleReturnInd != null ? SaleReturnInd : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return CVVReq != null ? CVVReq : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return CVVRsltCode != null ? CVVRsltCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return Amt != null ? Amt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return AuthAmt != null ? AuthAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return GratuityAmtInfo != null ? GratuityAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return CashbackAmtInfo != null ? CashbackAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return CardHolderLastName != null ? CardHolderLastName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return CardHolderFirstName != null ? CardHolderFirstName : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 16) {
            return CardHolderAddr != null ? CardHolderAddr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 17) {
            return CardHolderCity != null ? CardHolderCity : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return CardHolderState != null ? CardHolderState : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 19) {
            return CardHolderZip != null ? CardHolderZip : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 20) {
            return CardHolderPhone != null ? CardHolderPhone : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 21) {
            return CardHolderEmail != null ? CardHolderEmail : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 22) {
            return AVSRsltCode != null ? AVSRsltCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 23) {
            return CPCReq != null ? CPCReq : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 24) {
            return CPCInd != null ? CPCInd : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 25) {
            return RspCode != null ? RspCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 26) {
            return RspText != null ? RspText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 27) {
            return AuthCode != null ? AuthCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 28) {
            return ReqACI != null ? ReqACI : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 29) {
            return RspACI != null ? RspACI : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 30) {
            return MktSpecDataId != null ? MktSpecDataId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 31) {
            return TxnCode != null ? TxnCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 32) {
            return AcctDataSrc != null ? AcctDataSrc : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 33) {
            return AuthSrcCode != null ? AuthSrcCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 34) {
            return IssTxnId != null ? IssTxnId : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 35) {
            return IssValidationCode != null ? IssValidationCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 36) {
            return CardHolderIdCode != null ? CardHolderIdCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 37) {
            return NetworkIdCode != null ? NetworkIdCode : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 38) {
            return RefNbr != null ? RefNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 39) {
            return TxnSeqNbr;
        }
        if (propertyIndex == 40) {
            return DirectMktInvoiceNbr != null ? DirectMktInvoiceNbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 41) {
            return DirectMktShipMonth;
        }
        if (propertyIndex == 42) {
            return DirectMktShipDay;
        }
        if (propertyIndex == 43) {
            return CPCCardHolderPONbr != null ? CPCCardHolderPONbr : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 44) {
            return CPCTaxType != null ? CPCTaxType : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 45) {
            return CPCTaxAmt != null ? CPCTaxAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 46) {
            return SettlementAmt != null ? SettlementAmt.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 47) {
            return AllowPartialAuth != null ? AllowPartialAuth.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 48) {
            return AutoSubstantiation != null ? AutoSubstantiation : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 49) {
            return AdditionalTxnFields != null ? AdditionalTxnFields : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 50) {
            return Attachment != null ? Attachment : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 51) {
            return AVSRsltText != null ? AVSRsltText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 52) {
            return CVVRsltText != null ? CVVRsltText : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 53) {
            return Ecommerce != null ? Ecommerce.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 54) {
            return TokenizationMsg != null ? TokenizationMsg : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 55) {
            return CryptoTypeIn != null ? CryptoTypeIn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 56) {
            return CryptoTypeOut != null ? CryptoTypeOut : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 57) {
            return ClerkID != null ? ClerkID : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 58) {
            return CredentialData != null ? CredentialData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 59) {
            return LodgingData != null ? LodgingData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 60) {
            return ConvenienceAmtInfo != null ? ConvenienceAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 61) {
            return ShippingAmtInfo != null ? ShippingAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 62) {
            return TxnDescriptor != null ? TxnDescriptor : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 63) {
            return CheckDataInfo != null ? CheckDataInfo : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 64) {
            return GiftData != null ? GiftData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 65) {
            return PaymentMethodKey != null ? PaymentMethodKey : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 66) {
            return RecurringData != null ? RecurringData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 67) {
            return SurchargeAmtInfo != null ? SurchargeAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 68) {
            return CashData != null ? CashData : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 69) {
            return ReturnAmtInfo != null ? ReturnAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 70) {
            return ReversalAmtInfo != null ? ReversalAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 71) {
            return IncrementalAuthAmtInfo != null ? IncrementalAuthAmtInfo.toString() : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 72) {
            return AllowDup != null ? AllowDup : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 73) {
            return SystemAuditTraceNumber != null ? SystemAuditTraceNumber : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 74) {
            return LodgingCheckInDate != null ? LodgingCheckInDate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 75) {
            return LodgingCheckOutDate != null ? LodgingCheckOutDate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 76) {
            return ProcessorRefNum != null ? ProcessorRefNum : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 77) {
            return ReversalIndicator != null ? ReversalIndicator : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 78) {
            return TokenRequested != null ? TokenRequested : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 79) {
            return TokenGenerated != null ? TokenGenerated : SoapPrimitive.NullSkip;
        }
        if (propertyIndex >= +80 && propertyIndex < +80 + this.AttachmentInfo.size()) {
            return AttachmentInfo.get(propertyIndex - (+80));
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 80 + AttachmentInfo.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnStatus";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MaskedCardNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardPresent";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReaderPresent";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardSwiped";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DebitCreditInd";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SaleReturnInd";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVVReq";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVVRsltCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Amt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GratuityAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CashbackAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderLastName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderFirstName";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderAddr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderCity";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderState";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderZip";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderPhone";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 21) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderEmail";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 22) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AVSRsltCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 23) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCReq";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 24) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCInd";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 25) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 26) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 27) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 28) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReqACI";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 29) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RspACI";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 30) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "MktSpecDataId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 31) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 32) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AcctDataSrc";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 33) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AuthSrcCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 34) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IssTxnId";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 35) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IssValidationCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 36) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CardHolderIdCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 37) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "NetworkIdCode";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 38) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "RefNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 39) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "TxnSeqNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 40) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "DirectMktInvoiceNbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 41) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DirectMktShipMonth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 42) {
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "DirectMktShipDay";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 43) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCCardHolderPONbr";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 44) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCTaxType";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 45) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CPCTaxAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 46) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SettlementAmt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 47) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowPartialAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 48) {
            info.type = AutoSubstantiationReportType.class;
            info.name = "AutoSubstantiation";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 49) {
            info.type = AdditionalTxnFieldsType.class;
            info.name = "AdditionalTxnFields";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 50) {
            info.type = AttachmentRspDataType.class;
            info.name = "Attachment";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 51) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AVSRsltText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 52) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CVVRsltText";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 53) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Ecommerce";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 54) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenizationMsg";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 55) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CryptoTypeIn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 56) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CryptoTypeOut";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 57) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ClerkID";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 58) {
            info.type = CredentialDataType.class;
            info.name = "CredentialData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 59) {
            info.type = LodgingDataType.class;
            info.name = "LodgingData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 60) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ConvenienceAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 61) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ShippingAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 62) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TxnDescriptor";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 63) {
            info.type = CheckDataInfoType.class;
            info.name = "CheckDataInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 64) {
            info.type = GiftDataType.class;
            info.name = "GiftData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 65) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "PaymentMethodKey";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 66) {
            info.type = RecurringDataType.class;
            info.name = "RecurringData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 67) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SurchargeAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 68) {
            info.type = CashDataType.class;
            info.name = "CashData";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 69) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReturnAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 70) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReversalAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 71) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "IncrementalAuthAmtInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 72) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AllowDup";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 73) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SystemAuditTraceNumber";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 74) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "LodgingCheckInDate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 75) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "LodgingCheckOutDate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 76) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ProcessorRefNum";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 77) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ReversalIndicator";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 78) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenRequested";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 79) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TokenGenerated";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex >= +80 && propertyIndex <= +80 + this.AttachmentInfo.size()) {
            info.type = AttachmentInfoType.class;
            info.name = "AttachmentInfo";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
