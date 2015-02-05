package PosGateway.Exchange.Hps;

import java.util.Hashtable;

import org.ksoap2.serialization.*;

public class PosResponseVer10Transaction extends AttributeContainer implements KvmSerializable {

    public AuthRspStatusType DebitReversal;

    public PosGiftCardActivateRspType GiftCardActivate;

    public PosGiftCardAliasRspType GiftCardAlias;

    public PosGiftCardBalanceRspType GiftCardBalance;

    public GiftCardTotalsType GiftCardCurrentDayTotals;

    public PosGiftCardDeactivateRspType GiftCardDeactivate;

    public GiftCardTotalsType GiftCardPreviousDayTotals;

    public PosGiftCardReplaceRspType GiftCardReplace;

    public PosGiftCardReversalRspType GiftCardReversal;

    public PosGiftCardRewardRspType GiftCardReward;

    public PosGiftCardSaleRspType GiftCardSale;

    public PosGiftCardTipRspType GiftCardTip;

    public PosGiftCardVoidRspType GiftCardVoid;

    public PosImpersonateRspType Impersonate;

    public String InvalidateAuthentication;

    public ManageSettingsRspType ManageSettings;

    public PosManageUsersRspType ManageUsers;

    public AuthRspStatusType PrePaidAddValue;

    public AuthRspStatusType PrePaidBalanceInquiry;

    public AuthRspStatusType RecurringBilling;

    public PosReportActivityRspType ReportActivity;

    public PosReportBatchDetailRspType ReportBatchDetail;

    public PosReportBatchHistoryRspType ReportBatchHistory;

    public PosReportBatchSummaryRspType ReportBatchSummary;

    public PosReportOpenAuthsRspType ReportOpenAuths;

    public PosReportSearchRspType ReportSearch;

    public PosReportTxnDetailRspType ReportTxnDetail;

    public String SendReceipt;

    public String TestCredentials;

    public String CreditReturn;

    public PosGetUserSettingsRspType GetUserSettings;

    public PosAuthenticateRspType Authenticate;

    public PosBatchCloseRspType BatchClose;

    public String CancelImpersonation;

    public String CashReturn;

    public String CashSale;

    public PosCheckSaleRspType CheckSale;

    public PosCheckVoidRspType CheckVoid;

    public AuthRspStatusType CreditAccountVerify;

    public String CreditAddToBatch;

    public AuthRspStatusType CreditAdditionalAuth;

    public PosGiftCardAddValueRspType GiftCardAddValue;

    public AuthRspStatusType CreditAuth;

    public String CreditCPCEdit;

    public AuthRspStatusType CreditIncrementalAuth;

    public String CreditOfflineAuth;

    public String CreditOfflineSale;

    public String AddAttachment;

    public AuthRspStatusType CreditReversal;

    public AuthRspStatusType CreditSale;

    public String CreditTxnEdit;

    public String CreditVoid;

    public AuthRspStatusType DebitAddValue;

    public AuthRspStatusType DebitReturn;

    public AuthRspStatusType DebitSale;

    public AuthRspStatusType EBTBalanceInquiry;

    public AuthRspStatusType EBTCashBackPurchase;

    public AuthRspStatusType EBTCashBenefitWithdrawal;

    public AuthRspStatusType EBTFSPurchase;

    public AuthRspStatusType EBTFSReturn;

    public AuthRspStatusType EBTVoucherPurchase;

    public String EndToEndTest;

    public FindTransactionsRspType FindTransactions;

    public PosGetAttachmentsRspType GetAttachments = new PosGetAttachmentsRspType();

    public PosGetUserDeviceSettingsRspType GetUserDeviceSettings;

    public PosResponseVer10Transaction() {
    }

    public PosResponseVer10Transaction(AttributeContainer inObj, ExtendedSoapSerializationEnvelope envelope) {

        if (inObj == null)
            return;


        SoapObject soapObject = (SoapObject) inObj;
        if (soapObject.hasProperty("DebitReversal")) {
            Object j = soapObject.getProperty("DebitReversal");
            this.DebitReversal = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("GiftCardActivate")) {
            Object j = soapObject.getProperty("GiftCardActivate");
            this.GiftCardActivate = (PosGiftCardActivateRspType) envelope.get(j, PosGiftCardActivateRspType.class);
        }
        if (soapObject.hasProperty("GiftCardAlias")) {
            Object j = soapObject.getProperty("GiftCardAlias");
            this.GiftCardAlias = (PosGiftCardAliasRspType) envelope.get(j, PosGiftCardAliasRspType.class);
        }
        if (soapObject.hasProperty("GiftCardBalance")) {
            Object j = soapObject.getProperty("GiftCardBalance");
            this.GiftCardBalance = (PosGiftCardBalanceRspType) envelope.get(j, PosGiftCardBalanceRspType.class);
        }
        if (soapObject.hasProperty("GiftCardCurrentDayTotals")) {
            Object j = soapObject.getProperty("GiftCardCurrentDayTotals");
            this.GiftCardCurrentDayTotals = (GiftCardTotalsType) envelope.get(j, GiftCardTotalsType.class);
        }
        if (soapObject.hasProperty("GiftCardDeactivate")) {
            Object j = soapObject.getProperty("GiftCardDeactivate");
            this.GiftCardDeactivate = (PosGiftCardDeactivateRspType) envelope.get(j, PosGiftCardDeactivateRspType.class);
        }
        if (soapObject.hasProperty("GiftCardPreviousDayTotals")) {
            Object j = soapObject.getProperty("GiftCardPreviousDayTotals");
            this.GiftCardPreviousDayTotals = (GiftCardTotalsType) envelope.get(j, GiftCardTotalsType.class);
        }
        if (soapObject.hasProperty("GiftCardReplace")) {
            Object j = soapObject.getProperty("GiftCardReplace");
            this.GiftCardReplace = (PosGiftCardReplaceRspType) envelope.get(j, PosGiftCardReplaceRspType.class);
        }
        if (soapObject.hasProperty("GiftCardReversal")) {
            Object j = soapObject.getProperty("GiftCardReversal");
            this.GiftCardReversal = (PosGiftCardReversalRspType) envelope.get(j, PosGiftCardReversalRspType.class);
        }
        if (soapObject.hasProperty("GiftCardReward")) {
            Object j = soapObject.getProperty("GiftCardReward");
            this.GiftCardReward = (PosGiftCardRewardRspType) envelope.get(j, PosGiftCardRewardRspType.class);
        }
        if (soapObject.hasProperty("GiftCardSale")) {
            Object j = soapObject.getProperty("GiftCardSale");
            this.GiftCardSale = (PosGiftCardSaleRspType) envelope.get(j, PosGiftCardSaleRspType.class);
        }
        if (soapObject.hasProperty("GiftCardTip")) {
            Object j = soapObject.getProperty("GiftCardTip");
            this.GiftCardTip = (PosGiftCardTipRspType) envelope.get(j, PosGiftCardTipRspType.class);
        }
        if (soapObject.hasProperty("GiftCardVoid")) {
            Object j = soapObject.getProperty("GiftCardVoid");
            this.GiftCardVoid = (PosGiftCardVoidRspType) envelope.get(j, PosGiftCardVoidRspType.class);
        }
        if (soapObject.hasProperty("Impersonate")) {
            Object j = soapObject.getProperty("Impersonate");
            this.Impersonate = (PosImpersonateRspType) envelope.get(j, PosImpersonateRspType.class);
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
            this.ManageSettings = (ManageSettingsRspType) envelope.get(j, ManageSettingsRspType.class);
        }
        if (soapObject.hasProperty("ManageUsers")) {
            Object j = soapObject.getProperty("ManageUsers");
            this.ManageUsers = (PosManageUsersRspType) envelope.get(j, PosManageUsersRspType.class);
        }
        if (soapObject.hasProperty("PrePaidAddValue")) {
            Object j = soapObject.getProperty("PrePaidAddValue");
            this.PrePaidAddValue = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("PrePaidBalanceInquiry")) {
            Object j = soapObject.getProperty("PrePaidBalanceInquiry");
            this.PrePaidBalanceInquiry = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("RecurringBilling")) {
            Object j = soapObject.getProperty("RecurringBilling");
            this.RecurringBilling = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("ReportActivity")) {
            Object j = soapObject.getProperty("ReportActivity");
            this.ReportActivity = (PosReportActivityRspType) envelope.get(j, PosReportActivityRspType.class);
        }
        if (soapObject.hasProperty("ReportBatchDetail")) {
            Object j = soapObject.getProperty("ReportBatchDetail");
            this.ReportBatchDetail = (PosReportBatchDetailRspType) envelope.get(j, PosReportBatchDetailRspType.class);
        }
        if (soapObject.hasProperty("ReportBatchHistory")) {
            Object j = soapObject.getProperty("ReportBatchHistory");
            this.ReportBatchHistory = (PosReportBatchHistoryRspType) envelope.get(j, PosReportBatchHistoryRspType.class);
        }
        if (soapObject.hasProperty("ReportBatchSummary")) {
            Object j = soapObject.getProperty("ReportBatchSummary");
            this.ReportBatchSummary = (PosReportBatchSummaryRspType) envelope.get(j, PosReportBatchSummaryRspType.class);
        }
        if (soapObject.hasProperty("ReportOpenAuths")) {
            Object j = soapObject.getProperty("ReportOpenAuths");
            this.ReportOpenAuths = (PosReportOpenAuthsRspType) envelope.get(j, PosReportOpenAuthsRspType.class);
        }
        if (soapObject.hasProperty("ReportSearch")) {
            Object j = soapObject.getProperty("ReportSearch");
            this.ReportSearch = (PosReportSearchRspType) envelope.get(j, PosReportSearchRspType.class);
        }
        if (soapObject.hasProperty("ReportTxnDetail")) {
            Object j = soapObject.getProperty("ReportTxnDetail");
            this.ReportTxnDetail = (PosReportTxnDetailRspType) envelope.get(j, PosReportTxnDetailRspType.class);
        }
        if (soapObject.hasProperty("SendReceipt")) {
            Object obj = soapObject.getProperty("SendReceipt");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.SendReceipt = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.SendReceipt = (String) obj;
            }
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
        if (soapObject.hasProperty("CreditReturn")) {
            Object obj = soapObject.getProperty("CreditReturn");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditReturn = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CreditReturn = (String) obj;
            }
        }
        if (soapObject.hasProperty("GetUserSettings")) {
            Object j = soapObject.getProperty("GetUserSettings");
            this.GetUserSettings = (PosGetUserSettingsRspType) envelope.get(j, PosGetUserSettingsRspType.class);
        }
        if (soapObject.hasProperty("Authenticate")) {
            Object j = soapObject.getProperty("Authenticate");
            this.Authenticate = (PosAuthenticateRspType) envelope.get(j, PosAuthenticateRspType.class);
        }
        if (soapObject.hasProperty("BatchClose")) {
            Object j = soapObject.getProperty("BatchClose");
            this.BatchClose = (PosBatchCloseRspType) envelope.get(j, PosBatchCloseRspType.class);
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
            Object obj = soapObject.getProperty("CashReturn");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CashReturn = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CashReturn = (String) obj;
            }
        }
        if (soapObject.hasProperty("CashSale")) {
            Object obj = soapObject.getProperty("CashSale");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CashSale = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CashSale = (String) obj;
            }
        }
        if (soapObject.hasProperty("CheckSale")) {
            Object j = soapObject.getProperty("CheckSale");
            this.CheckSale = (PosCheckSaleRspType) envelope.get(j, PosCheckSaleRspType.class);
        }
        if (soapObject.hasProperty("CheckVoid")) {
            Object j = soapObject.getProperty("CheckVoid");
            this.CheckVoid = (PosCheckVoidRspType) envelope.get(j, PosCheckVoidRspType.class);
        }
        if (soapObject.hasProperty("CreditAccountVerify")) {
            Object j = soapObject.getProperty("CreditAccountVerify");
            this.CreditAccountVerify = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("CreditAddToBatch")) {
            Object obj = soapObject.getProperty("CreditAddToBatch");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditAddToBatch = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CreditAddToBatch = (String) obj;
            }
        }
        if (soapObject.hasProperty("CreditAdditionalAuth")) {
            Object j = soapObject.getProperty("CreditAdditionalAuth");
            this.CreditAdditionalAuth = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("GiftCardAddValue")) {
            Object j = soapObject.getProperty("GiftCardAddValue");
            this.GiftCardAddValue = (PosGiftCardAddValueRspType) envelope.get(j, PosGiftCardAddValueRspType.class);
        }
        if (soapObject.hasProperty("CreditAuth")) {
            Object j = soapObject.getProperty("CreditAuth");
            this.CreditAuth = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("CreditCPCEdit")) {
            Object obj = soapObject.getProperty("CreditCPCEdit");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditCPCEdit = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CreditCPCEdit = (String) obj;
            }
        }
        if (soapObject.hasProperty("CreditIncrementalAuth")) {
            Object j = soapObject.getProperty("CreditIncrementalAuth");
            this.CreditIncrementalAuth = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("CreditOfflineAuth")) {
            Object obj = soapObject.getProperty("CreditOfflineAuth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditOfflineAuth = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CreditOfflineAuth = (String) obj;
            }
        }
        if (soapObject.hasProperty("CreditOfflineSale")) {
            Object obj = soapObject.getProperty("CreditOfflineSale");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditOfflineSale = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CreditOfflineSale = (String) obj;
            }
        }
        if (soapObject.hasProperty("AddAttachment")) {
            Object obj = soapObject.getProperty("AddAttachment");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.AddAttachment = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.AddAttachment = (String) obj;
            }
        }
        if (soapObject.hasProperty("CreditReversal")) {
            Object j = soapObject.getProperty("CreditReversal");
            this.CreditReversal = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("CreditSale")) {
            Object j = soapObject.getProperty("CreditSale");
            this.CreditSale = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("CreditTxnEdit")) {
            Object obj = soapObject.getProperty("CreditTxnEdit");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditTxnEdit = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CreditTxnEdit = (String) obj;
            }
        }
        if (soapObject.hasProperty("CreditVoid")) {
            Object obj = soapObject.getProperty("CreditVoid");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                if (j.toString() != null) {
                    this.CreditVoid = j.toString();
                }
            } else if (obj != null && obj instanceof String) {
                this.CreditVoid = (String) obj;
            }
        }
        if (soapObject.hasProperty("DebitAddValue")) {
            Object j = soapObject.getProperty("DebitAddValue");
            this.DebitAddValue = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("DebitReturn")) {
            Object j = soapObject.getProperty("DebitReturn");
            this.DebitReturn = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("DebitSale")) {
            Object j = soapObject.getProperty("DebitSale");
            this.DebitSale = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("EBTBalanceInquiry")) {
            Object j = soapObject.getProperty("EBTBalanceInquiry");
            this.EBTBalanceInquiry = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("EBTCashBackPurchase")) {
            Object j = soapObject.getProperty("EBTCashBackPurchase");
            this.EBTCashBackPurchase = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("EBTCashBenefitWithdrawal")) {
            Object j = soapObject.getProperty("EBTCashBenefitWithdrawal");
            this.EBTCashBenefitWithdrawal = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("EBTFSPurchase")) {
            Object j = soapObject.getProperty("EBTFSPurchase");
            this.EBTFSPurchase = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("EBTFSReturn")) {
            Object j = soapObject.getProperty("EBTFSReturn");
            this.EBTFSReturn = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
        }
        if (soapObject.hasProperty("EBTVoucherPurchase")) {
            Object j = soapObject.getProperty("EBTVoucherPurchase");
            this.EBTVoucherPurchase = (AuthRspStatusType) envelope.get(j, AuthRspStatusType.class);
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
            this.FindTransactions = (FindTransactionsRspType) envelope.get(j, FindTransactionsRspType.class);
        }
        if (soapObject.hasProperty("GetAttachments")) {
            Object j = soapObject.getProperty("GetAttachments");
            this.GetAttachments = new PosGetAttachmentsRspType((AttributeContainer) j, envelope);
        }
        if (soapObject.hasProperty("GetUserDeviceSettings")) {
            Object j = soapObject.getProperty("GetUserDeviceSettings");
            this.GetUserDeviceSettings = (PosGetUserDeviceSettingsRspType) envelope.get(j, PosGetUserDeviceSettingsRspType.class);
        }


    }

    @Override
    public Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (propertyIndex == 0) {
            return DebitReversal != null ? DebitReversal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 1) {
            return GiftCardActivate != null ? GiftCardActivate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 2) {
            return GiftCardAlias != null ? GiftCardAlias : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 3) {
            return GiftCardBalance != null ? GiftCardBalance : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 4) {
            return GiftCardCurrentDayTotals != null ? GiftCardCurrentDayTotals : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 5) {
            return GiftCardDeactivate != null ? GiftCardDeactivate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 6) {
            return GiftCardPreviousDayTotals != null ? GiftCardPreviousDayTotals : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 7) {
            return GiftCardReplace != null ? GiftCardReplace : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 8) {
            return GiftCardReversal != null ? GiftCardReversal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 9) {
            return GiftCardReward != null ? GiftCardReward : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 10) {
            return GiftCardSale != null ? GiftCardSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 11) {
            return GiftCardTip != null ? GiftCardTip : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 12) {
            return GiftCardVoid != null ? GiftCardVoid : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 13) {
            return Impersonate != null ? Impersonate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 14) {
            return InvalidateAuthentication != null ? InvalidateAuthentication : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 15) {
            return ManageSettings != null ? ManageSettings : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 16) {
            return ManageUsers != null ? ManageUsers : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 17) {
            return PrePaidAddValue != null ? PrePaidAddValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 18) {
            return PrePaidBalanceInquiry != null ? PrePaidBalanceInquiry : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 19) {
            return RecurringBilling != null ? RecurringBilling : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 20) {
            return ReportActivity != null ? ReportActivity : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 21) {
            return ReportBatchDetail != null ? ReportBatchDetail : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 22) {
            return ReportBatchHistory != null ? ReportBatchHistory : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 23) {
            return ReportBatchSummary != null ? ReportBatchSummary : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 24) {
            return ReportOpenAuths != null ? ReportOpenAuths : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 25) {
            return ReportSearch != null ? ReportSearch : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 26) {
            return ReportTxnDetail != null ? ReportTxnDetail : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 27) {
            return SendReceipt != null ? SendReceipt : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 28) {
            return TestCredentials != null ? TestCredentials : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 29) {
            return CreditReturn != null ? CreditReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 30) {
            return GetUserSettings != null ? GetUserSettings : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 31) {
            return Authenticate != null ? Authenticate : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 32) {
            return BatchClose != null ? BatchClose : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 33) {
            return CancelImpersonation != null ? CancelImpersonation : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 34) {
            return CashReturn != null ? CashReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 35) {
            return CashSale != null ? CashSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 36) {
            return CheckSale != null ? CheckSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 37) {
            return CheckVoid != null ? CheckVoid : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 38) {
            return CreditAccountVerify != null ? CreditAccountVerify : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 39) {
            return CreditAddToBatch != null ? CreditAddToBatch : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 40) {
            return CreditAdditionalAuth != null ? CreditAdditionalAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 41) {
            return GiftCardAddValue != null ? GiftCardAddValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 42) {
            return CreditAuth != null ? CreditAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 43) {
            return CreditCPCEdit != null ? CreditCPCEdit : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 44) {
            return CreditIncrementalAuth != null ? CreditIncrementalAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 45) {
            return CreditOfflineAuth != null ? CreditOfflineAuth : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 46) {
            return CreditOfflineSale != null ? CreditOfflineSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 47) {
            return AddAttachment != null ? AddAttachment : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 48) {
            return CreditReversal != null ? CreditReversal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 49) {
            return CreditSale != null ? CreditSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 50) {
            return CreditTxnEdit != null ? CreditTxnEdit : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 51) {
            return CreditVoid != null ? CreditVoid : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 52) {
            return DebitAddValue != null ? DebitAddValue : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 53) {
            return DebitReturn != null ? DebitReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 54) {
            return DebitSale != null ? DebitSale : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 55) {
            return EBTBalanceInquiry != null ? EBTBalanceInquiry : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 56) {
            return EBTCashBackPurchase != null ? EBTCashBackPurchase : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 57) {
            return EBTCashBenefitWithdrawal != null ? EBTCashBenefitWithdrawal : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 58) {
            return EBTFSPurchase != null ? EBTFSPurchase : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 59) {
            return EBTFSReturn != null ? EBTFSReturn : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 60) {
            return EBTVoucherPurchase != null ? EBTVoucherPurchase : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 61) {
            return EndToEndTest != null ? EndToEndTest : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 62) {
            return FindTransactions != null ? FindTransactions : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 63) {
            return GetAttachments != null ? GetAttachments : SoapPrimitive.NullSkip;
        }
        if (propertyIndex == 64) {
            return GetUserDeviceSettings != null ? GetUserDeviceSettings : SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 65;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        if (propertyIndex == 0) {
            info.type = AuthRspStatusType.class;
            info.name = "DebitReversal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 1) {
            info.type = PosGiftCardActivateRspType.class;
            info.name = "GiftCardActivate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 2) {
            info.type = PosGiftCardAliasRspType.class;
            info.name = "GiftCardAlias";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 3) {
            info.type = PosGiftCardBalanceRspType.class;
            info.name = "GiftCardBalance";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 4) {
            info.type = GiftCardTotalsType.class;
            info.name = "GiftCardCurrentDayTotals";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 5) {
            info.type = PosGiftCardDeactivateRspType.class;
            info.name = "GiftCardDeactivate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 6) {
            info.type = GiftCardTotalsType.class;
            info.name = "GiftCardPreviousDayTotals";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 7) {
            info.type = PosGiftCardReplaceRspType.class;
            info.name = "GiftCardReplace";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 8) {
            info.type = PosGiftCardReversalRspType.class;
            info.name = "GiftCardReversal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 9) {
            info.type = PosGiftCardRewardRspType.class;
            info.name = "GiftCardReward";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 10) {
            info.type = PosGiftCardSaleRspType.class;
            info.name = "GiftCardSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 11) {
            info.type = PosGiftCardTipRspType.class;
            info.name = "GiftCardTip";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 12) {
            info.type = PosGiftCardVoidRspType.class;
            info.name = "GiftCardVoid";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 13) {
            info.type = PosImpersonateRspType.class;
            info.name = "Impersonate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 14) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "InvalidateAuthentication";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 15) {
            info.type = ManageSettingsRspType.class;
            info.name = "ManageSettings";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 16) {
            info.type = PosManageUsersRspType.class;
            info.name = "ManageUsers";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 17) {
            info.type = AuthRspStatusType.class;
            info.name = "PrePaidAddValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 18) {
            info.type = AuthRspStatusType.class;
            info.name = "PrePaidBalanceInquiry";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 19) {
            info.type = AuthRspStatusType.class;
            info.name = "RecurringBilling";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 20) {
            info.type = PosReportActivityRspType.class;
            info.name = "ReportActivity";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 21) {
            info.type = PosReportBatchDetailRspType.class;
            info.name = "ReportBatchDetail";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 22) {
            info.type = PosReportBatchHistoryRspType.class;
            info.name = "ReportBatchHistory";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 23) {
            info.type = PosReportBatchSummaryRspType.class;
            info.name = "ReportBatchSummary";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 24) {
            info.type = PosReportOpenAuthsRspType.class;
            info.name = "ReportOpenAuths";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 25) {
            info.type = PosReportSearchRspType.class;
            info.name = "ReportSearch";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 26) {
            info.type = PosReportTxnDetailRspType.class;
            info.name = "ReportTxnDetail";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 27) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "SendReceipt";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 28) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "TestCredentials";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 29) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 30) {
            info.type = PosGetUserSettingsRspType.class;
            info.name = "GetUserSettings";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 31) {
            info.type = PosAuthenticateRspType.class;
            info.name = "Authenticate";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 32) {
            info.type = PosBatchCloseRspType.class;
            info.name = "BatchClose";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 33) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CancelImpersonation";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 34) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CashReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 35) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CashSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 36) {
            info.type = PosCheckSaleRspType.class;
            info.name = "CheckSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 37) {
            info.type = PosCheckVoidRspType.class;
            info.name = "CheckVoid";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 38) {
            info.type = AuthRspStatusType.class;
            info.name = "CreditAccountVerify";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 39) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditAddToBatch";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 40) {
            info.type = AuthRspStatusType.class;
            info.name = "CreditAdditionalAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 41) {
            info.type = PosGiftCardAddValueRspType.class;
            info.name = "GiftCardAddValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 42) {
            info.type = AuthRspStatusType.class;
            info.name = "CreditAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 43) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditCPCEdit";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 44) {
            info.type = AuthRspStatusType.class;
            info.name = "CreditIncrementalAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 45) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditOfflineAuth";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 46) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditOfflineSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 47) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "AddAttachment";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 48) {
            info.type = AuthRspStatusType.class;
            info.name = "CreditReversal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 49) {
            info.type = AuthRspStatusType.class;
            info.name = "CreditSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 50) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditTxnEdit";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 51) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "CreditVoid";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 52) {
            info.type = AuthRspStatusType.class;
            info.name = "DebitAddValue";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 53) {
            info.type = AuthRspStatusType.class;
            info.name = "DebitReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 54) {
            info.type = AuthRspStatusType.class;
            info.name = "DebitSale";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 55) {
            info.type = AuthRspStatusType.class;
            info.name = "EBTBalanceInquiry";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 56) {
            info.type = AuthRspStatusType.class;
            info.name = "EBTCashBackPurchase";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 57) {
            info.type = AuthRspStatusType.class;
            info.name = "EBTCashBenefitWithdrawal";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 58) {
            info.type = AuthRspStatusType.class;
            info.name = "EBTFSPurchase";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 59) {
            info.type = AuthRspStatusType.class;
            info.name = "EBTFSReturn";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 60) {
            info.type = AuthRspStatusType.class;
            info.name = "EBTVoucherPurchase";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 61) {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "EndToEndTest";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 62) {
            info.type = FindTransactionsRspType.class;
            info.name = "FindTransactions";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 63) {
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "GetAttachments";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
        if (propertyIndex == 64) {
            info.type = PosGetUserDeviceSettingsRspType.class;
            info.name = "GetUserDeviceSettings";
            info.namespace = "http://Hps.Exchange.PosGateway";
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
