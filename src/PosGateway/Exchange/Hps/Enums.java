package PosGateway.Exchange.Hps;

public class Enums {

    public enum currencyType {

        USD(0),

        POINTS(1);

        private int code;

        currencyType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static currencyType fromString(String str) {
            if (str.equals("USD"))
                return USD;
            if (str.equals("POINTS"))
                return POINTS;
            return null;
        }
    }

    public enum booleanType {

        N(0, "N"),

        Y(1, "Y"),

        ___(2, "");

        private int code;
        private String xmlValue;

        booleanType(int code, String xmlValue) {
            this.code = code;
            this.xmlValue = xmlValue;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return xmlValue;
        }

        public static booleanType fromString(String str) {
            if (str.equals("N"))
                return N;
            if (str.equals("Y"))
                return Y;
            if (str.equals(""))
                return ___;
            return null;
        }
    }

    public enum cvv2Status {

        ILLEGIBLE(0),

        NOTPRESENT(1);

        private int code;

        cvv2Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static cvv2Status fromString(String str) {
            if (str.equals("ILLEGIBLE"))
                return ILLEGIBLE;
            if (str.equals("NOTPRESENT"))
                return NOTPRESENT;
            return null;
        }
    }

    public enum TokenMappingType {

        UNIQUE(0),

        CONSTANT(1);

        private int code;

        TokenMappingType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static TokenMappingType fromString(String str) {
            if (str.equals("UNIQUE"))
                return UNIQUE;
            if (str.equals("CONSTANT"))
                return CONSTANT;
            return null;
        }
    }

    public enum tzoneConversionType {

        Merchant(0),

        UTC(1),

        Datacenter(2);

        private int code;

        tzoneConversionType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static tzoneConversionType fromString(String str) {
            if (str.equals("Merchant"))
                return Merchant;
            if (str.equals("UTC"))
                return UTC;
            if (str.equals("Datacenter"))
                return Datacenter;
            return null;
        }
    }

    public enum checkActionType {

        SALE(0),

        OVERRIDE(1),

        RETURN(2);

        private int code;

        checkActionType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static checkActionType fromString(String str) {
            if (str.equals("SALE"))
                return SALE;
            if (str.equals("OVERRIDE"))
                return OVERRIDE;
            if (str.equals("RETURN"))
                return RETURN;
            return null;
        }
    }

    public enum accountTypeType {

        CHECKING(0),

        SAVINGS(1);

        private int code;

        accountTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static accountTypeType fromString(String str) {
            if (str.equals("CHECKING"))
                return CHECKING;
            if (str.equals("SAVINGS"))
                return SAVINGS;
            return null;
        }
    }

    public enum dataEntryModeType {

        MANUAL(0),

        SWIPE(1);

        private int code;

        dataEntryModeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static dataEntryModeType fromString(String str) {
            if (str.equals("MANUAL"))
                return MANUAL;
            if (str.equals("SWIPE"))
                return SWIPE;
            return null;
        }
    }

    public enum checkTypeType {

        PERSONAL(0),

        BUSINESS(1),

        PAYROLL(2);

        private int code;

        checkTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static checkTypeType fromString(String str) {
            if (str.equals("PERSONAL"))
                return PERSONAL;
            if (str.equals("BUSINESS"))
                return BUSINESS;
            if (str.equals("PAYROLL"))
                return PAYROLL;
            return null;
        }
    }

    public enum prestigiousPropertyType {

        NOT_PARTICIPATING(0),

        LIMIT_500(1),

        LIMIT_1000(2),

        LIMIT_1500(3);

        private int code;

        prestigiousPropertyType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static prestigiousPropertyType fromString(String str) {
            if (str.equals("NOT_PARTICIPATING"))
                return NOT_PARTICIPATING;
            if (str.equals("LIMIT_500"))
                return LIMIT_500;
            if (str.equals("LIMIT_1000"))
                return LIMIT_1000;
            if (str.equals("LIMIT_1500"))
                return LIMIT_1500;
            return null;
        }
    }

    public enum advancedDepositTypeType {

        ASSURED_RESERVATION(0),

        CARD_DEPOSIT(1),

        PURCHASE(2),

        OTHER(3);

        private int code;

        advancedDepositTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static advancedDepositTypeType fromString(String str) {
            if (str.equals("ASSURED_RESERVATION"))
                return ASSURED_RESERVATION;
            if (str.equals("CARD_DEPOSIT"))
                return CARD_DEPOSIT;
            if (str.equals("PURCHASE"))
                return PURCHASE;
            if (str.equals("OTHER"))
                return OTHER;
            return null;
        }
    }

    public enum amtTypeType {

        TOTAL_HEALTHCARE_AMT(0),

        SUBTOTAL_PRESCRIPTION_AMT(1),

        SUBTOTAL_VISION__OPTICAL_AMT(2),

        SUBTOTAL_CLINIC_OR_OTHER_AMT(3),

        SUBTOTAL_DENTAL_AMT(4);

        private int code;

        amtTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static amtTypeType fromString(String str) {
            if (str.equals("TOTAL_HEALTHCARE_AMT"))
                return TOTAL_HEALTHCARE_AMT;
            if (str.equals("SUBTOTAL_PRESCRIPTION_AMT"))
                return SUBTOTAL_PRESCRIPTION_AMT;
            if (str.equals("SUBTOTAL_VISION__OPTICAL_AMT"))
                return SUBTOTAL_VISION__OPTICAL_AMT;
            if (str.equals("SUBTOTAL_CLINIC_OR_OTHER_AMT"))
                return SUBTOTAL_CLINIC_OR_OTHER_AMT;
            if (str.equals("SUBTOTAL_DENTAL_AMT"))
                return SUBTOTAL_DENTAL_AMT;
            return null;
        }
    }

    public enum eCommerceType {

        ECOM(0),

        MOTO(1);

        private int code;

        eCommerceType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static eCommerceType fromString(String str) {
            if (str.equals("ECOM"))
                return ECOM;
            if (str.equals("MOTO"))
                return MOTO;
            return null;
        }
    }

    public enum emvChipConditionType {
        CHIP_FAILED_PREV_SUCCESS(0),

        CHIP_FAILED_PREV_FAILED(1);

        private int code;

        emvChipConditionType(int code ){
            this.code = code;
        }

        public int getCode(){
            return code;
        }


        public static emvChipConditionType fromString(String str)
        {
            if (str.equals("CHIP_FAILED_PREV_SUCCESS"))
                return CHIP_FAILED_PREV_SUCCESS;
            if (str.equals("CHIP_FAILED_PREV_FAILED"))
                return CHIP_FAILED_PREV_FAILED;
            return null;
        }
    }

    public enum TypeOfPaymentDataType {
        _3DSecure(0,"3DSecure");

        private int code;
        private java.lang.String xmlValue;

        TypeOfPaymentDataType(int code ,java.lang.String xmlValue){
            this.code = code;
            this.xmlValue=xmlValue;
        }

        public int getCode(){
            return code;
        }

        @Override
        public String toString() {
            return xmlValue;
        }

        public static TypeOfPaymentDataType fromString(String str)
        {
            if (str.equals("3DSecure"))
                return _3DSecure;
            return null;
        }
    }

    public enum EncodingType {
        base16(0),

        base64(1);

        private int code;

        EncodingType(int code ){
            this.code = code;
        }

        public int getCode(){
            return code;
        }


        public static EncodingType fromString(String str)
        {
            if (str.equals("base16"))
                return base16;
            if (str.equals("base64"))
                return base64;
            return null;
        }
    }

    public enum taxTypeType {

        NOTUSED(0),

        SALESTAX(1),

        TAXEXEMPT(2);

        private int code;

        taxTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static taxTypeType fromString(String str) {
            if (str.equals("NOTUSED"))
                return NOTUSED;
            if (str.equals("SALESTAX"))
                return SALESTAX;
            if (str.equals("TAXEXEMPT"))
                return TAXEXEMPT;
            return null;
        }
    }

    public enum EBTBalanceInquiryType {

        CASH(0),

        FOODSTAMP(1);

        private int code;

        EBTBalanceInquiryType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static EBTBalanceInquiryType fromString(String str) {
            if (str.equals("CASH"))
                return CASH;
            if (str.equals("FOODSTAMP"))
                return FOODSTAMP;
            return null;
        }
    }

    public enum paymentTypeType {

        OTHER(0),

        PREPAID(1),

        GIFTCARD(2),

        EBT(3),

        CREDIT(4),

        DEBIT(5),

        CHECK(6),

        CASH(7);

        private int code;

        paymentTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static paymentTypeType fromString(String str) {
            if (str.equals("OTHER"))
                return OTHER;
            if (str.equals("PREPAID"))
                return PREPAID;
            if (str.equals("GIFTCARD"))
                return GIFTCARD;
            if (str.equals("EBT"))
                return EBT;
            if (str.equals("CREDIT"))
                return CREDIT;
            if (str.equals("DEBIT"))
                return DEBIT;
            if (str.equals("CHECK"))
                return CHECK;
            if (str.equals("CASH"))
                return CASH;
            return null;
        }
    }

    public enum CardTypeType {

        VISA(0),

        MC(1),

        DISC(2),

        AMEX(3),

        GIFTCARD(4);

        private int code;

        CardTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static CardTypeType fromString(String str) {
            if (str.equals("VISA"))
                return VISA;
            if (str.equals("MC"))
                return MC;
            if (str.equals("DISC"))
                return DISC;
            if (str.equals("AMEX"))
                return AMEX;
            if (str.equals("GIFTCARD"))
                return GIFTCARD;
            return null;
        }
    }

    public enum attachmentTypeType {

        SIGNATURE_IMAGE(0),

        RECEIPT_IMAGE(1),

        CUSTOMER_IMAGE(2),

        PRODUCT_IMAGE(3),

        DOCUMENT(4);

        private int code;

        attachmentTypeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static attachmentTypeType fromString(String str) {
            if (str.equals("SIGNATURE_IMAGE"))
                return SIGNATURE_IMAGE;
            if (str.equals("RECEIPT_IMAGE"))
                return RECEIPT_IMAGE;
            if (str.equals("CUSTOMER_IMAGE"))
                return CUSTOMER_IMAGE;
            if (str.equals("PRODUCT_IMAGE"))
                return PRODUCT_IMAGE;
            if (str.equals("DOCUMENT"))
                return DOCUMENT;
            return null;
        }
    }

    public enum attachmentFormatType {

        PNG(0),

        JPG(1),

        TIF(2),

        BMP(3),

        PDF(4),

        DOCX(5),

        DOC(6),

        TXT(7),

        XLS(8),

        XLSX(9);

        private int code;

        attachmentFormatType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static attachmentFormatType fromString(String str) {
            if (str.equals("PNG"))
                return PNG;
            if (str.equals("JPG"))
                return JPG;
            if (str.equals("TIF"))
                return TIF;
            if (str.equals("BMP"))
                return BMP;
            if (str.equals("PDF"))
                return PDF;
            if (str.equals("DOCX"))
                return DOCX;
            if (str.equals("DOC"))
                return DOC;
            if (str.equals("TXT"))
                return TXT;
            if (str.equals("XLS"))
                return XLS;
            if (str.equals("XLSX"))
                return XLSX;
            return null;
        }
    }

    public enum resultCodeActionType {

        PROMPT(0),

        DECLINE(1),

        ACCEPT(2);

        private int code;

        resultCodeActionType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static resultCodeActionType fromString(String str) {
            if (str.equals("PROMPT"))
                return PROMPT;
            if (str.equals("DECLINE"))
                return DECLINE;
            if (str.equals("ACCEPT"))
                return ACCEPT;
            return null;
        }
    }

    public enum CardDataType_TrackData_method {

        swipe(0),

        proximity(1);

        private int code;

        CardDataType_TrackData_method(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static CardDataType_TrackData_method fromString(String str) {
            if (str.equals("swipe"))
                return swipe;
            if (str.equals("proximity"))
                return proximity;
            return null;
        }
    }

    public enum DestinationType_DataType {

        EMAIL_TO(0);

        private int code;

        DestinationType_DataType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static DestinationType_DataType fromString(String str) {
            if (str.equals("EMAIL_TO"))
                return EMAIL_TO;
            return null;
        }
    }

    public enum SearchCriteriaType_ServiceName {

        DebitSale(0),

        DebitReturn(1),

        CashReturn(2),

        CashSale(3),

        CreditOfflineSale(4),

        CreditOfflineAuth(5),

        CreditReturn(6),

        CreditAuth(7),

        CreditAdditionalAuth(8),

        CreditSale(9),

        CheckSale(10),

        CheckVoid(11),

        GiftCardActivate(12),

        GiftCardAddValue(13),

        GiftCardReplace(14),

        GiftCardReward(15),

        GiftCardSale(16),

        GiftCardTip(17),

        GiftCardReversal(18),

        GiftCardVoid(19),

        EBTFSPurchase(20),

        EBTFSReturn(21),

        EBTVoucherPurchase(22),

        EBTCashBackPurchase(23),

        EBTCashBenefitWithdrawal(24),

        OverrideFraudDecline(25),

        PrePaidAddValue(26),

        ReCurringBilling(27);

        private int code;

        SearchCriteriaType_ServiceName(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static SearchCriteriaType_ServiceName fromString(String str) {
            if (str.equals("DebitSale"))
                return DebitSale;
            if (str.equals("DebitReturn"))
                return DebitReturn;
            if (str.equals("CashReturn"))
                return CashReturn;
            if (str.equals("CashSale"))
                return CashSale;
            if (str.equals("CreditOfflineSale"))
                return CreditOfflineSale;
            if (str.equals("CreditOfflineAuth"))
                return CreditOfflineAuth;
            if (str.equals("CreditReturn"))
                return CreditReturn;
            if (str.equals("CreditAuth"))
                return CreditAuth;
            if (str.equals("CreditAdditionalAuth"))
                return CreditAdditionalAuth;
            if (str.equals("CreditSale"))
                return CreditSale;
            if (str.equals("CheckSale"))
                return CheckSale;
            if (str.equals("CheckVoid"))
                return CheckVoid;
            if (str.equals("GiftCardActivate"))
                return GiftCardActivate;
            if (str.equals("GiftCardAddValue"))
                return GiftCardAddValue;
            if (str.equals("GiftCardReplace"))
                return GiftCardReplace;
            if (str.equals("GiftCardReward"))
                return GiftCardReward;
            if (str.equals("GiftCardSale"))
                return GiftCardSale;
            if (str.equals("GiftCardTip"))
                return GiftCardTip;
            if (str.equals("GiftCardReversal"))
                return GiftCardReversal;
            if (str.equals("GiftCardVoid"))
                return GiftCardVoid;
            if (str.equals("EBTFSPurchase"))
                return EBTFSPurchase;
            if (str.equals("EBTFSReturn"))
                return EBTFSReturn;
            if (str.equals("EBTVoucherPurchase"))
                return EBTVoucherPurchase;
            if (str.equals("EBTCashBackPurchase"))
                return EBTCashBackPurchase;
            if (str.equals("EBTCashBenefitWithdrawal"))
                return EBTCashBenefitWithdrawal;
            if (str.equals("OverrideFraudDecline"))
                return OverrideFraudDecline;
            if (str.equals("PrePaidAddValue"))
                return PrePaidAddValue;
            if (str.equals("ReCurringBilling"))
                return ReCurringBilling;
            return null;
        }
    }

    public enum SearchCriteriaType_IssuerResult {

        A(0),

        D(1),

        P(2),

        F(3),

        FR(4);

        private int code;

        SearchCriteriaType_IssuerResult(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static SearchCriteriaType_IssuerResult fromString(String str) {
            if (str.equals("A"))
                return A;
            if (str.equals("D"))
                return D;
            if (str.equals("P"))
                return P;
            if (str.equals("F"))
                return F;
            if (str.equals("FR"))
                return FR;
            return null;
        }
    }

    public enum GiftCardAliasReqBlock1TypeAction {

        DELETE(0),

        ADD(1),

        CREATE(2);

        private int code;

        GiftCardAliasReqBlock1TypeAction(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static GiftCardAliasReqBlock1TypeAction fromString(String str) {
            if (str.equals("DELETE"))
                return DELETE;
            if (str.equals("ADD"))
                return ADD;
            if (str.equals("CREATE"))
                return CREATE;
            return null;
        }
    }
}