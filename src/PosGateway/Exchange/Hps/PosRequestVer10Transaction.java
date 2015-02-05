package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosRequestVer10Transaction extends AttributeContainer implements KvmSerializable {

    public PosGiftCardSaleReqType GiftCardSale;

    public PosPrePaidBalanceInquiryReqType PrePaidBalanceInquiry;

    public PosRecurringBillReqType RecurringBilling;

    public PosReportActivityReqType ReportActivity;

    public PosReportBatchDetailReqType ReportBatchDetail;

    public PosReportBatchHistoryReqType ReportBatchHistory;

    public PosReportBatchSummaryReqType ReportBatchSummary;

    public PosReportOpenAuthsReqType ReportOpenAuths;

    public PosReportSearchReqType ReportSearch;

    public PosReportTxnDetailReqType ReportTxnDetail;

    public PosSendReceiptReqType SendReceipt;

    public String TestCredentials;

    public PosDebitReversalReqType DebitReversal;

    public PosPrePaidAddValueReqType PrePaidAddValue;

    public String BatchClose;

    public String CancelImpersonation;

    public PosCashReturnReqType CashReturn;

    public PosCashSaleReqType CashSale;

    public PosCheckSaleReqType CheckSale;

    public PosCheckVoidReqType CheckVoid;

    public PosCreditAccountVerifyReqType CreditAccountVerify;

    public PosCreditAddToBatchReqType CreditAddToBatch;

    public PosCreditAdditionalAuthReqType CreditAdditionalAuth;

    public PosCreditAuthReqType CreditAuth;

    public PosCreditCPCEditReqType CreditCPCEdit;

    public PosCreditIncrementalAuthReqType CreditIncrementalAuth;

    public PosCreditOfflineAuthReqType CreditOfflineAuth;

    public PosCreditOfflineSaleReqType CreditOfflineSale;

    public PosCreditReturnReqType CreditReturn;

    public PosCreditReversalReqType CreditReversal;

    public PosCreditSaleReqType CreditSale;

    public PosCreditTxnEditReqType CreditTxnEdit;

    public PosCreditVoidReqType CreditVoid;

    public PosDebitAddValueReqType DebitAddValue;

    public PosDebitReturnReqType DebitReturn;

    public String Authenticate;

    public PosDebitSaleReqType DebitSale;

    public PosEBTBalanceInquiryReqType EBTBalanceInquiry;

    public PosEBTCashBackPurchaseReqType EBTCashBackPurchase;

    public PosEBTCashBenefitWithdrawalReqType EBTCashBenefitWithdrawal;

    public PosEBTFSPurchaseReqType EBTFSPurchase;

    public PosEBTFSReturnReqType EBTFSReturn;

    public PosEBTFSVoucherReqType EBTVoucherPurchase;

    public String EndToEndTest;

    public FindTransactionsReqType FindTransactions;

    public PosGetAttachmentReqType GetAttachments;

    public PosGetUserDeviceSettingsReqType GetUserDeviceSettings;

    public String GetUserSettings;

    public PosGiftCardActivateReqType GiftCardActivate;

    public PosGiftCardAddValueReqType GiftCardAddValue;

    public PosGiftCardAliasReqType GiftCardAlias;

    public PosGiftCardBalanceReqType GiftCardBalance;

    public String GiftCardCurrentDayTotals;

    public PosGiftCardDeactivateReqType GiftCardDeactivate;

    public String GiftCardPreviousDayTotals;

    public PosGiftCardReplaceReqType GiftCardReplace;

    public PosGiftCardReversalReqType GiftCardReversal;

    public PosGiftCardRewardReqType GiftCardReward;

    public PosAddAttachmentReqType AddAttachment;

    public PosGiftCardTipReqType GiftCardTip;

    public PosGiftCardVoidReqType GiftCardVoid;

    public PosImpersonateReqType Impersonate;

    public String InvalidateAuthentication;

    public ManageSettingsReqType ManageSettings;

    public ManageTokensReqType ManageTokens;

    public PosManageUsersReqType ManageUsers;

    public PosOverrideFraudDeclineReqType OverrideFraudDecline;

    public PosRequestVer10Transaction() {
    }

    public PosRequestVer10Transaction(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("GiftCardSale")) {
            Object j = soapObject.getProperty("GiftCardSale");
            this.GiftCardSale = (PosGiftCardSaleReqType) envelope.get(j, PosGiftCardSaleReqType.class);
        }
        if (soapObject.hasProperty("PrePaidBalanceInquiry")) {
            Object j = soapObject.getProperty("PrePaidBalanceInquiry");
            this.PrePaidBalanceInquiry = (PosPrePaidBalanceInquiryReqType) envelope.get(j, PosPrePaidBalanceInquiryReqType.class);
        }
        if (soapObject.hasProperty("RecurringBilling")) {
            Object j = soapObject.getProperty("RecurringBilling");
            this.RecurringBilling = (PosRecurringBillReqType) envelope.get(j, PosRecurringBillReqType.class);
        }
        if (soapObject.hasProperty("ReportActivity")) {
            Object j = soapObject.getProperty("ReportActivity");
            this.ReportActivity = (PosReportActivityReqType) envelope.get(j, PosReportActivityReqType.class);
        }
        if (soapObject.hasProperty("ReportBatchDetail")) {
            Object j = soapObject.getProperty("ReportBatchDetail");
            this.ReportBatchDetail = (PosReportBatchDetailReqType) envelope.get(j, PosReportBatchDetailReqType.class);
        }
        if (soapObject.hasProperty("ReportBatchHistory")) {
            Object j = soapObject.getProperty("ReportBatchHistory");
            this.ReportBatchHistory = (PosReportBatchHistoryReqType) envelope.get(j, PosReportBatchHistoryReqType.class);
        }
        if (soapObject.hasProperty("ReportBatchSummary")) {
            Object j = soapObject.getProperty("ReportBatchSummary");
            this.ReportBatchSummary = (PosReportBatchSummaryReqType) envelope.get(j, PosReportBatchSummaryReqType.class);
        }
        if (soapObject.hasProperty("ReportOpenAuths")) {
            Object j = soapObject.getProperty("ReportOpenAuths");
            this.ReportOpenAuths = (PosReportOpenAuthsReqType) envelope.get(j, PosReportOpenAuthsReqType.class);
        }
        if (soapObject.hasProperty("ReportSearch")) {
            Object j = soapObject.getProperty("ReportSearch");
            this.ReportSearch = (PosReportSearchReqType) envelope.get(j, PosReportSearchReqType.class);
        }
        if (soapObject.hasProperty("ReportTxnDetail")) {
            Object j = soapObject.getProperty("ReportTxnDetail");
            this.ReportTxnDetail = (PosReportTxnDetailReqType) envelope.get(j, PosReportTxnDetailReqType.class);
        }
        if (soapObject.hasProperty("SendReceipt")) {
            Object j = soapObject.getProperty("SendReceipt");
            this.SendReceipt = (PosSendReceiptReqType) envelope.get(j, PosSendReceiptReqType.class);
        }
        if (soapObject.hasProperty("TestCredentials")) {
            Object obj = soapObject.getProperty("TestCredentials");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.TestCredentials = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.TestCredentials = (String) obj;
            }
        }
        if (soapObject.hasProperty("DebitReversal")) {
            Object j = soapObject.getProperty("DebitReversal");
            this.DebitReversal = (PosDebitReversalReqType) envelope.get(j, PosDebitReversalReqType.class);
        }
        if (soapObject.hasProperty("PrePaidAddValue")) {
            Object j = soapObject.getProperty("PrePaidAddValue");
            this.PrePaidAddValue = (PosPrePaidAddValueReqType) envelope.get(j, PosPrePaidAddValueReqType.class);
        }
        if (soapObject.hasProperty("BatchClose")) {
            Object obj = soapObject.getProperty("BatchClose");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.BatchClose = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.BatchClose = (String) obj;
            }
        }
        if (soapObject.hasProperty("CancelImpersonation")) {
            Object obj = soapObject.getProperty("CancelImpersonation");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CancelImpersonation = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CancelImpersonation = (String) obj;
            }
        }
        if (soapObject.hasProperty("CashReturn")) {
            Object j = soapObject.getProperty("CashReturn");
            this.CashReturn = (PosCashReturnReqType) envelope.get(j, PosCashReturnReqType.class);
        }
        if (soapObject.hasProperty("CashSale")) {
            Object j = soapObject.getProperty("CashSale");
            this.CashSale = (PosCashSaleReqType) envelope.get(j, PosCashSaleReqType.class);
        }
        if (soapObject.hasProperty("CheckSale")) {
            Object j = soapObject.getProperty("CheckSale");
            this.CheckSale = (PosCheckSaleReqType) envelope.get(j, PosCheckSaleReqType.class);
        }
        if (soapObject.hasProperty("CheckVoid")) {
            Object j = soapObject.getProperty("CheckVoid");
            this.CheckVoid = (PosCheckVoidReqType) envelope.get(j, PosCheckVoidReqType.class);
        }
        if (soapObject.hasProperty("CreditAccountVerify")) {
            Object j = soapObject.getProperty("CreditAccountVerify");
            this.CreditAccountVerify = (PosCreditAccountVerifyReqType) envelope.get(j, PosCreditAccountVerifyReqType.class);
        }
        if (soapObject.hasProperty("CreditAddToBatch")) {
            Object j = soapObject.getProperty("CreditAddToBatch");
            this.CreditAddToBatch = (PosCreditAddToBatchReqType) envelope.get(j, PosCreditAddToBatchReqType.class);
        }
        if (soapObject.hasProperty("CreditAdditionalAuth")) {
            Object j = soapObject.getProperty("CreditAdditionalAuth");
            this.CreditAdditionalAuth = (PosCreditAdditionalAuthReqType) envelope.get(j, PosCreditAdditionalAuthReqType.class);
        }
        if (soapObject.hasProperty("CreditAuth")) {
            Object j = soapObject.getProperty("CreditAuth");
            this.CreditAuth = (PosCreditAuthReqType) envelope.get(j, PosCreditAuthReqType.class);
        }
        if (soapObject.hasProperty("CreditCPCEdit")) {
            Object j = soapObject.getProperty("CreditCPCEdit");
            this.CreditCPCEdit = (PosCreditCPCEditReqType) envelope.get(j, PosCreditCPCEditReqType.class);
        }
        if (soapObject.hasProperty("CreditIncrementalAuth")) {
            Object j = soapObject.getProperty("CreditIncrementalAuth");
            this.CreditIncrementalAuth = (PosCreditIncrementalAuthReqType) envelope.get(j, PosCreditIncrementalAuthReqType.class);
        }
        if (soapObject.hasProperty("CreditOfflineAuth")) {
            Object j = soapObject.getProperty("CreditOfflineAuth");
            this.CreditOfflineAuth = (PosCreditOfflineAuthReqType) envelope.get(j, PosCreditOfflineAuthReqType.class);
        }
        if (soapObject.hasProperty("CreditOfflineSale")) {
            Object j = soapObject.getProperty("CreditOfflineSale");
            this.CreditOfflineSale = (PosCreditOfflineSaleReqType) envelope.get(j, PosCreditOfflineSaleReqType.class);
        }
        if (soapObject.hasProperty("CreditReturn")) {
            Object j = soapObject.getProperty("CreditReturn");
            this.CreditReturn = (PosCreditReturnReqType) envelope.get(j, PosCreditReturnReqType.class);
        }
        if (soapObject.hasProperty("CreditReversal")) {
            Object j = soapObject.getProperty("CreditReversal");
            this.CreditReversal = (PosCreditReversalReqType) envelope.get(j, PosCreditReversalReqType.class);
        }
        if (soapObject.hasProperty("CreditSale")) {
            Object j = soapObject.getProperty("CreditSale");
            this.CreditSale = (PosCreditSaleReqType) envelope.get(j, PosCreditSaleReqType.class);
        }
        if (soapObject.hasProperty("CreditTxnEdit")) {
            Object j = soapObject.getProperty("CreditTxnEdit");
            this.CreditTxnEdit = (PosCreditTxnEditReqType) envelope.get(j, PosCreditTxnEditReqType.class);
        }
        if (soapObject.hasProperty("CreditVoid")) {
            Object j = soapObject.getProperty("CreditVoid");
            this.CreditVoid = (PosCreditVoidReqType) envelope.get(j, PosCreditVoidReqType.class);
        }
        if (soapObject.hasProperty("DebitAddValue")) {
            Object j = soapObject.getProperty("DebitAddValue");
            this.DebitAddValue = (PosDebitAddValueReqType) envelope.get(j, PosDebitAddValueReqType.class);
        }
        if (soapObject.hasProperty("DebitReturn")) {
            Object j = soapObject.getProperty("DebitReturn");
            this.DebitReturn = (PosDebitReturnReqType) envelope.get(j, PosDebitReturnReqType.class);
        }
        if (soapObject.hasProperty("Authenticate")) {
            Object obj = soapObject.getProperty("Authenticate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.Authenticate = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.Authenticate = (String) obj;
            }
        }
        if (soapObject.hasProperty("DebitSale")) {
            Object j = soapObject.getProperty("DebitSale");
            this.DebitSale = (PosDebitSaleReqType) envelope.get(j, PosDebitSaleReqType.class);
        }
        if (soapObject.hasProperty("EBTBalanceInquiry")) {
            Object j = soapObject.getProperty("EBTBalanceInquiry");
            this.EBTBalanceInquiry = (PosEBTBalanceInquiryReqType) envelope.get(j, PosEBTBalanceInquiryReqType.class);
        }
        if (soapObject.hasProperty("EBTCashBackPurchase")) {
            Object j = soapObject.getProperty("EBTCashBackPurchase");
            this.EBTCashBackPurchase = (PosEBTCashBackPurchaseReqType) envelope.get(j, PosEBTCashBackPurchaseReqType.class);
        }
        if (soapObject.hasProperty("EBTCashBenefitWithdrawal")) {
            Object j = soapObject.getProperty("EBTCashBenefitWithdrawal");
            this.EBTCashBenefitWithdrawal = (PosEBTCashBenefitWithdrawalReqType) envelope.get(j, PosEBTCashBenefitWithdrawalReqType.class);
        }
        if (soapObject.hasProperty("EBTFSPurchase")) {
            Object j = soapObject.getProperty("EBTFSPurchase");
            this.EBTFSPurchase = (PosEBTFSPurchaseReqType) envelope.get(j, PosEBTFSPurchaseReqType.class);
        }
        if (soapObject.hasProperty("EBTFSReturn")) {
            Object j = soapObject.getProperty("EBTFSReturn");
            this.EBTFSReturn = (PosEBTFSReturnReqType) envelope.get(j, PosEBTFSReturnReqType.class);
        }
        if (soapObject.hasProperty("EBTVoucherPurchase")) {
            Object j = soapObject.getProperty("EBTVoucherPurchase");
            this.EBTVoucherPurchase = (PosEBTFSVoucherReqType) envelope.get(j, PosEBTFSVoucherReqType.class);
        }
        if (soapObject.hasProperty("EndToEndTest")) {
            Object obj = soapObject.getProperty("EndToEndTest");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.EndToEndTest = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.EndToEndTest = (String) obj;
            }
        }
        if (soapObject.hasProperty("FindTransactions")) {
            Object j = soapObject.getProperty("FindTransactions");
            this.FindTransactions = (FindTransactionsReqType) envelope.get(j, FindTransactionsReqType.class);
        }
        if (soapObject.hasProperty("GetAttachments")) {
            Object j = soapObject.getProperty("GetAttachments");
            this.GetAttachments = (PosGetAttachmentReqType) envelope.get(j, PosGetAttachmentReqType.class);
        }
        if (soapObject.hasProperty("GetUserDeviceSettings")) {
            Object j = soapObject.getProperty("GetUserDeviceSettings");
            this.GetUserDeviceSettings = (PosGetUserDeviceSettingsReqType) envelope.get(j, PosGetUserDeviceSettingsReqType.class);
        }
        if (soapObject.hasProperty("GetUserSettings")) {
            Object obj = soapObject.getProperty("GetUserSettings");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GetUserSettings = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.GetUserSettings = (String) obj;
            }
        }
        if (soapObject.hasProperty("GiftCardActivate")) {
            Object j = soapObject.getProperty("GiftCardActivate");
            this.GiftCardActivate = (PosGiftCardActivateReqType) envelope.get(j, PosGiftCardActivateReqType.class);
        }
        if (soapObject.hasProperty("GiftCardAddValue")) {
            Object j = soapObject.getProperty("GiftCardAddValue");
            this.GiftCardAddValue = (PosGiftCardAddValueReqType) envelope.get(j, PosGiftCardAddValueReqType.class);
        }
        if (soapObject.hasProperty("GiftCardAlias")) {
            Object j = soapObject.getProperty("GiftCardAlias");
            this.GiftCardAlias = (PosGiftCardAliasReqType) envelope.get(j, PosGiftCardAliasReqType.class);
        }
        if (soapObject.hasProperty("GiftCardBalance")) {
            Object j = soapObject.getProperty("GiftCardBalance");
            this.GiftCardBalance = (PosGiftCardBalanceReqType) envelope.get(j, PosGiftCardBalanceReqType.class);
        }
        if (soapObject.hasProperty("GiftCardCurrentDayTotals")) {
            Object obj = soapObject.getProperty("GiftCardCurrentDayTotals");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GiftCardCurrentDayTotals = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.GiftCardCurrentDayTotals = (String) obj;
            }
        }
        if (soapObject.hasProperty("GiftCardDeactivate")) {
            Object j = soapObject.getProperty("GiftCardDeactivate");
            this.GiftCardDeactivate = (PosGiftCardDeactivateReqType) envelope.get(j, PosGiftCardDeactivateReqType.class);
        }
        if (soapObject.hasProperty("GiftCardPreviousDayTotals")) {
            Object obj = soapObject.getProperty("GiftCardPreviousDayTotals");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.GiftCardPreviousDayTotals = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.GiftCardPreviousDayTotals = (String) obj;
            }
        }
        if (soapObject.hasProperty("GiftCardReplace")) {
            Object j = soapObject.getProperty("GiftCardReplace");
            this.GiftCardReplace = (PosGiftCardReplaceReqType) envelope.get(j, PosGiftCardReplaceReqType.class);
        }
        if (soapObject.hasProperty("GiftCardReversal")) {
            Object j = soapObject.getProperty("GiftCardReversal");
            this.GiftCardReversal = (PosGiftCardReversalReqType) envelope.get(j, PosGiftCardReversalReqType.class);
        }
        if (soapObject.hasProperty("GiftCardReward")) {
            Object j = soapObject.getProperty("GiftCardReward");
            this.GiftCardReward = (PosGiftCardRewardReqType) envelope.get(j, PosGiftCardRewardReqType.class);
        }
        if (soapObject.hasProperty("AddAttachment")) {
            Object j = soapObject.getProperty("AddAttachment");
            this.AddAttachment = (PosAddAttachmentReqType) envelope.get(j, PosAddAttachmentReqType.class);
        }
        if (soapObject.hasProperty("GiftCardTip")) {
            Object j = soapObject.getProperty("GiftCardTip");
            this.GiftCardTip = (PosGiftCardTipReqType) envelope.get(j, PosGiftCardTipReqType.class);
        }
        if (soapObject.hasProperty("GiftCardVoid")) {
            Object j = soapObject.getProperty("GiftCardVoid");
            this.GiftCardVoid = (PosGiftCardVoidReqType) envelope.get(j, PosGiftCardVoidReqType.class);
        }
        if (soapObject.hasProperty("Impersonate")) {
            Object j = soapObject.getProperty("Impersonate");
            this.Impersonate = (PosImpersonateReqType) envelope.get(j, PosImpersonateReqType.class);
        }
        if (soapObject.hasProperty("InvalidateAuthentication")) {
            Object obj = soapObject.getProperty("InvalidateAuthentication");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.InvalidateAuthentication = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.InvalidateAuthentication = (String) obj;
            }
        }
        if (soapObject.hasProperty("ManageSettings")) {
            Object j = soapObject.getProperty("ManageSettings");
            this.ManageSettings = (ManageSettingsReqType) envelope.get(j, ManageSettingsReqType.class);
        }
        if (soapObject.hasProperty("ManageTokens")) {
            Object j = soapObject.getProperty("ManageTokens");
            this.ManageTokens = (ManageTokensReqType) envelope.get(j, ManageTokensReqType.class);
        }
        if (soapObject.hasProperty("ManageUsers")) {
            Object j = soapObject.getProperty("ManageUsers");
            this.ManageUsers = (PosManageUsersReqType) envelope.get(j, PosManageUsersReqType.class);
        }
        if (soapObject.hasProperty("OverrideFraudDecline")) {
            Object j = soapObject.getProperty("OverrideFraudDecline");
            this.OverrideFraudDecline = (PosOverrideFraudDeclineReqType) envelope.get(j, PosOverrideFraudDeclineReqType.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return GiftCardSale != null ? GiftCardSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return PrePaidBalanceInquiry != null ? PrePaidBalanceInquiry : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return RecurringBilling != null ? RecurringBilling : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return ReportActivity != null ? ReportActivity : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return ReportBatchDetail != null ? ReportBatchDetail : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return ReportBatchHistory != null ? ReportBatchHistory : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return ReportBatchSummary != null ? ReportBatchSummary : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return ReportOpenAuths != null ? ReportOpenAuths : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return ReportSearch != null ? ReportSearch : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return ReportTxnDetail != null ? ReportTxnDetail : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return SendReceipt != null ? SendReceipt : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return TestCredentials != null ? TestCredentials : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return DebitReversal != null ? DebitReversal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return PrePaidAddValue != null ? PrePaidAddValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return BatchClose != null ? BatchClose : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return CancelImpersonation != null ? CancelImpersonation : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 16) {
            return CashReturn != null ? CashReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 17) {
            return CashSale != null ? CashSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return CheckSale != null ? CheckSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 19) {
            return CheckVoid != null ? CheckVoid : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 20) {
            return CreditAccountVerify != null ? CreditAccountVerify : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 21) {
            return CreditAddToBatch != null ? CreditAddToBatch : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 22) {
            return CreditAdditionalAuth != null ? CreditAdditionalAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 23) {
            return CreditAuth != null ? CreditAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 24) {
            return CreditCPCEdit != null ? CreditCPCEdit : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 25) {
            return CreditIncrementalAuth != null ? CreditIncrementalAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 26) {
            return CreditOfflineAuth != null ? CreditOfflineAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 27) {
            return CreditOfflineSale != null ? CreditOfflineSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 28) {
            return CreditReturn != null ? CreditReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 29) {
            return CreditReversal != null ? CreditReversal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 30) {
            return CreditSale != null ? CreditSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 31) {
            return CreditTxnEdit != null ? CreditTxnEdit : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 32) {
            return CreditVoid != null ? CreditVoid : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 33) {
            return DebitAddValue != null ? DebitAddValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 34) {
            return DebitReturn != null ? DebitReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 35) {
            return Authenticate != null ? Authenticate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 36) {
            return DebitSale != null ? DebitSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 37) {
            return EBTBalanceInquiry != null ? EBTBalanceInquiry : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 38) {
            return EBTCashBackPurchase != null ? EBTCashBackPurchase : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 39) {
            return EBTCashBenefitWithdrawal != null ? EBTCashBenefitWithdrawal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 40) {
            return EBTFSPurchase != null ? EBTFSPurchase : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 41) {
            return EBTFSReturn != null ? EBTFSReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 42) {
            return EBTVoucherPurchase != null ? EBTVoucherPurchase : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 43) {
            return EndToEndTest != null ? EndToEndTest : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 44) {
            return FindTransactions != null ? FindTransactions : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 45) {
            return GetAttachments != null ? GetAttachments : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 46) {
            return GetUserDeviceSettings != null ? GetUserDeviceSettings : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 47) {
            return GetUserSettings != null ? GetUserSettings : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 48) {
            return GiftCardActivate != null ? GiftCardActivate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 49) {
            return GiftCardAddValue != null ? GiftCardAddValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 50) {
            return GiftCardAlias != null ? GiftCardAlias : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 51) {
            return GiftCardBalance != null ? GiftCardBalance : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 52) {
            return GiftCardCurrentDayTotals != null ? GiftCardCurrentDayTotals : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 53) {
            return GiftCardDeactivate != null ? GiftCardDeactivate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 54) {
            return GiftCardPreviousDayTotals != null ? GiftCardPreviousDayTotals : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 55) {
            return GiftCardReplace != null ? GiftCardReplace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 56) {
            return GiftCardReversal != null ? GiftCardReversal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 57) {
            return GiftCardReward != null ? GiftCardReward : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 58) {
            return AddAttachment != null ? AddAttachment : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 59) {
            return GiftCardTip != null ? GiftCardTip : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 60) {
            return GiftCardVoid != null ? GiftCardVoid : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 61) {
            return Impersonate != null ? Impersonate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 62) {
            return InvalidateAuthentication != null ? InvalidateAuthentication : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 63) {
            return ManageSettings != null ? ManageSettings : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 64) {
            return ManageTokens != null ? ManageTokens : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 65) {
            return ManageUsers != null ? ManageUsers : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 66) {
            return OverrideFraudDecline != null ? OverrideFraudDecline : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 67;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = PosGiftCardSaleReqType.class;
            info.name = "GiftCardSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PosPrePaidBalanceInquiryReqType.class;
            info.name = "PrePaidBalanceInquiry";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PosRecurringBillReqType.class;
            info.name = "RecurringBilling";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PosReportActivityReqType.class;
            info.name = "ReportActivity";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = PosReportBatchDetailReqType.class;
            info.name = "ReportBatchDetail";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PosReportBatchHistoryReqType.class;
            info.name = "ReportBatchHistory";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = PosReportBatchSummaryReqType.class;
            info.name = "ReportBatchSummary";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PosReportOpenAuthsReqType.class;
            info.name = "ReportOpenAuths";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PosReportSearchReqType.class;
            info.name = "ReportSearch";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PosReportTxnDetailReqType.class;
            info.name = "ReportTxnDetail";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PosSendReceiptReqType.class;
            info.name = "SendReceipt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TestCredentials";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PosDebitReversalReqType.class;
            info.name = "DebitReversal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PosPrePaidAddValueReqType.class;
            info.name = "PrePaidAddValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "BatchClose";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CancelImpersonation";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PosCashReturnReqType.class;
            info.name = "CashReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = PosCashSaleReqType.class;
            info.name = "CashSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = PosCheckSaleReqType.class;
            info.name = "CheckSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = PosCheckVoidReqType.class;
            info.name = "CheckVoid";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PosCreditAccountVerifyReqType.class;
            info.name = "CreditAccountVerify";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 21) {
            info.type = PosCreditAddToBatchReqType.class;
            info.name = "CreditAddToBatch";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 22) {
            info.type = PosCreditAdditionalAuthReqType.class;
            info.name = "CreditAdditionalAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 23) {
            info.type = PosCreditAuthReqType.class;
            info.name = "CreditAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 24) {
            info.type = PosCreditCPCEditReqType.class;
            info.name = "CreditCPCEdit";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 25) {
            info.type = PosCreditIncrementalAuthReqType.class;
            info.name = "CreditIncrementalAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 26) {
            info.type = PosCreditOfflineAuthReqType.class;
            info.name = "CreditOfflineAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 27) {
            info.type = PosCreditOfflineSaleReqType.class;
            info.name = "CreditOfflineSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 28) {
            info.type = PosCreditReturnReqType.class;
            info.name = "CreditReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 29) {
            info.type = PosCreditReversalReqType.class;
            info.name = "CreditReversal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 30) {
            info.type = PosCreditSaleReqType.class;
            info.name = "CreditSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 31) {
            info.type = PosCreditTxnEditReqType.class;
            info.name = "CreditTxnEdit";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 32) {
            info.type = PosCreditVoidReqType.class;
            info.name = "CreditVoid";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 33) {
            info.type = PosDebitAddValueReqType.class;
            info.name = "DebitAddValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 34) {
            info.type = PosDebitReturnReqType.class;
            info.name = "DebitReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 35) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Authenticate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 36) {
            info.type = PosDebitSaleReqType.class;
            info.name = "DebitSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 37) {
            info.type = PosEBTBalanceInquiryReqType.class;
            info.name = "EBTBalanceInquiry";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 38) {
            info.type = PosEBTCashBackPurchaseReqType.class;
            info.name = "EBTCashBackPurchase";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 39) {
            info.type = PosEBTCashBenefitWithdrawalReqType.class;
            info.name = "EBTCashBenefitWithdrawal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 40) {
            info.type = PosEBTFSPurchaseReqType.class;
            info.name = "EBTFSPurchase";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 41) {
            info.type = PosEBTFSReturnReqType.class;
            info.name = "EBTFSReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 42) {
            info.type = PosEBTFSVoucherReqType.class;
            info.name = "EBTVoucherPurchase";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 43) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EndToEndTest";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 44) {
            info.type = FindTransactionsReqType.class;
            info.name = "FindTransactions";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 45) {
            info.type = PosGetAttachmentReqType.class;
            info.name = "GetAttachments";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 46) {
            info.type = PosGetUserDeviceSettingsReqType.class;
            info.name = "GetUserDeviceSettings";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 47) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GetUserSettings";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 48) {
            info.type = PosGiftCardActivateReqType.class;
            info.name = "GiftCardActivate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 49) {
            info.type = PosGiftCardAddValueReqType.class;
            info.name = "GiftCardAddValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 50) {
            info.type = PosGiftCardAliasReqType.class;
            info.name = "GiftCardAlias";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 51) {
            info.type = PosGiftCardBalanceReqType.class;
            info.name = "GiftCardBalance";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 52) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GiftCardCurrentDayTotals";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 53) {
            info.type = PosGiftCardDeactivateReqType.class;
            info.name = "GiftCardDeactivate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 54) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "GiftCardPreviousDayTotals";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 55) {
            info.type = PosGiftCardReplaceReqType.class;
            info.name = "GiftCardReplace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 56) {
            info.type = PosGiftCardReversalReqType.class;
            info.name = "GiftCardReversal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 57) {
            info.type = PosGiftCardRewardReqType.class;
            info.name = "GiftCardReward";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 58) {
            info.type = PosAddAttachmentReqType.class;
            info.name = "AddAttachment";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 59) {
            info.type = PosGiftCardTipReqType.class;
            info.name = "GiftCardTip";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 60) {
            info.type = PosGiftCardVoidReqType.class;
            info.name = "GiftCardVoid";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 61) {
            info.type = PosImpersonateReqType.class;
            info.name = "Impersonate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 62) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "InvalidateAuthentication";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 63) {
            info.type = ManageSettingsReqType.class;
            info.name = "ManageSettings";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 64) {
            info.type = ManageTokensReqType.class;
            info.name = "ManageTokens";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 65) {
            info.type = PosManageUsersReqType.class;
            info.name = "ManageUsers";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 66) {
            info.type = PosOverrideFraudDeclineReqType.class;
            info.name = "OverrideFraudDecline";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
