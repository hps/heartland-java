package PosGateway.Exchange.Hps;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;
import org.kxml2.io.KXmlParser;
import org.kxml2.kdom.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import javax.xml.soap.Text;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Vector;
import java.io.StringReader;
import java.io.StringWriter;

public class ExtendedSoapSerializationEnvelope extends SoapSerializationEnvelope {
    static HashMap<String, Class> classNames = new HashMap<String, Class>();

    static {
        classNames.put("http://Hps.Exchange.PosGateway^^GPSCoordinatesType", GPSCoordinatesType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardSaleReqType", PosGiftCardSaleReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardSaleReqBlock1Type", GiftCardSaleReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardDataType", GiftCardDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^EncryptionDataType", EncryptionDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosPrePaidBalanceInquiryReqType", PosPrePaidBalanceInquiryReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PrePaidBalanceInquiryReqBlock1Type", PrePaidBalanceInquiryReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CardDataType_TokenData", CardDataTypeTokenData.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CardDataType_ManualEntry", CardDataTypeManualEntry.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CardDataType", CardDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^TokenParametersType", TokenParametersType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CardHolderDataType", CardHolderDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosRecurringBillReqType", PosRecurringBillReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^RecurringBillReqBlock1Type", RecurringBillReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PaymentMethodKeyData", PaymentMethodKeyData.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AdditionalTxnFieldsType", AdditionalTxnFieldsType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^RecurringDataType", RecurringDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportActivityReqType", PosReportActivityReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchDetailReqType", PosReportBatchDetailReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchHistoryReqType", PosReportBatchHistoryReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchSummaryReqType", PosReportBatchSummaryReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportOpenAuthsReqType", PosReportOpenAuthsReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportSearchReqType", PosReportSearchReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ReportSearchCriteriaType", ReportSearchCriteriaType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ReportSearchSiteTraceCriteriaType", ReportSearchSiteTraceCriteriaType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportTxnDetailReqType", PosReportTxnDetailReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosSendReceiptReqType", PosSendReceiptReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DestinationType", DestinationType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosDebitReversalReqType", PosDebitReversalReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DebitReversalReqBlock1Type", DebitReversalReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosPrePaidAddValueReqType", PosPrePaidAddValueReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PrePaidAddValueReqBlock1Type", PrePaidAddValueReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCashReturnReqType", PosCashReturnReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CashReturnReqBlock1Type", CashReturnReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCashSaleReqType", PosCashSaleReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CashSaleReqBlock1Type", CashSaleReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCheckSaleReqType", PosCheckSaleReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CheckSaleReqBlock1Type", CheckSaleReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AccountInfoType", AccountInfoType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^VerifyInfoType", VerifyInfoType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ConsumerInfoType", ConsumerInfoType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^IdentityInfoType", IdentityInfoType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCheckVoidReqType", PosCheckVoidReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CheckVoidReqBlock1Type", CheckVoidReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditAccountVerifyReqType", PosCreditAccountVerifyReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditAccountVerifyBlock1Type", CreditAccountVerifyBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditAddToBatchReqType", PosCreditAddToBatchReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^LodgingDataEditType", LodgingDataEditType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ExtraChargesDataType", ExtraChargesDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DirectMktDataType", DirectMktDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditAdditionalAuthReqType", PosCreditAdditionalAuthReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditAdditionalAuthReqBlock1Type", CreditAdditionalAuthReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditAuthReqType", PosCreditAuthReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditAuthReqBlock1Type", CreditAuthReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^LodgingDataType", LodgingDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AutoSubstantiationType", AutoSubstantiationType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AdditionalAmtType", AdditionalAmtType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^origTxnRefDataType", origTxnRefDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditCPCEditReqType", PosCreditCPCEditReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CPCDataType", CPCDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditIncrementalAuthReqType", PosCreditIncrementalAuthReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditIncrementalAuthReqBlock1Type", CreditIncrementalAuthReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditOfflineAuthReqType", PosCreditOfflineAuthReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditOfflineAuthReqBlock1Type", CreditOfflineAuthReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditOfflineSaleReqType", PosCreditOfflineSaleReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditOfflineSaleReqBlock1Type", CreditOfflineSaleReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditReturnReqType", PosCreditReturnReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditReturnReqBlock1Type", CreditReturnReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditReversalReqType", PosCreditReversalReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditReversalReqBlock1Type", CreditReversalReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditSaleReqType", PosCreditSaleReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CreditSaleReqBlock1Type", CreditSaleReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditTxnEditReqType", PosCreditTxnEditReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCreditVoidReqType", PosCreditVoidReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosDebitAddValueReqType", PosDebitAddValueReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DebitAddValueReqBlock1Type", DebitAddValueReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosDebitReturnReqType", PosDebitReturnReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DebitReturnReqBlock1Type", DebitReturnReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosDebitSaleReqType", PosDebitSaleReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DebitSaleReqBlock1Type", DebitSaleReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosEBTBalanceInquiryReqType", PosEBTBalanceInquiryReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^EBTBalanceInquiryReqBlock1Type", EBTBalanceInquiryReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosEBTCashBackPurchaseReqType", PosEBTCashBackPurchaseReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^EBTCashBackPurchaseReqBlock1Type", EBTCashBackPurchaseReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosEBTCashBenefitWithdrawalReqType", PosEBTCashBenefitWithdrawalReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^EBTCashBenefitWithdrawalReqBlock1Type", EBTCashBenefitWithdrawalReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosEBTFSPurchaseReqType", PosEBTFSPurchaseReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^EBTFSPurchaseReqBlock1Type", EBTFSPurchaseReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosEBTFSReturnReqType", PosEBTFSReturnReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^EBTFSReturnReqBlock1Type", EBTFSReturnReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosEBTFSVoucherReqType", PosEBTFSVoucherReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^EBTFSVoucherReqBlock1Type", EBTFSVoucherReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^FindTransactionsReqType", FindTransactionsReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^SearchCriteriaType", SearchCriteriaType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGetAttachmentReqType", PosGetAttachmentReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGetUserDeviceSettingsReqType", PosGetUserDeviceSettingsReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardActivateReqType", PosGiftCardActivateReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardActivateReqBlock1Type", GiftCardActivateReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardAddValueReqType", PosGiftCardAddValueReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardAddValueReqBlock1Type", GiftCardAddValueReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardAliasReqType", PosGiftCardAliasReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardAliasReqBlock1Type", GiftCardAliasReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardBalanceReqType", PosGiftCardBalanceReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardBalanceReqBlock1Type", GiftCardBalanceReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardDeactivateReqType", PosGiftCardDeactivateReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardDeactivateReqBlock1Type", GiftCardDeactivateReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardReplaceReqType", PosGiftCardReplaceReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardReplaceReqBlock1Type", GiftCardReplaceReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardReversalReqType", PosGiftCardReversalReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardReversalReqBlock1Type", GiftCardReversalReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardRewardReqType", PosGiftCardRewardReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardRewardReqBlock1Type", GiftCardRewardReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosAddAttachmentReqType", PosAddAttachmentReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AttachmentDataType", AttachmentDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardTipReqType", PosGiftCardTipReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardTipReqBlock1Type", GiftCardTipReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardVoidReqType", PosGiftCardVoidReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardVoidReqBlock1Type", GiftCardVoidReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosImpersonateReqType", PosImpersonateReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ManageSettingsReqType_SettingActions", ManageSettingsReqTypeSettingActions.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ManageSettingsReqType", ManageSettingsReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^NameValuePairType", NameValuePairType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ManageTokensReqType_TokenActions", ManageTokensReqTypeTokenActions.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ManageTokensReqType", ManageTokensReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosManageUsersReqType_UserActions_Query", PosManageUsersReqTypeUserActionsQuery.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosManageUsersReqType_UserActions", PosManageUsersReqTypeUserActions.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosManageUsersReqType", PosManageUsersReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosOverrideFraudDeclineReqType", PosOverrideFraudDeclineReqType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^OverrideFraudDeclineReqBlock1Type", OverrideFraudDeclineReqBlock1Type.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGetUserDeviceSettingsRspType", PosGetUserDeviceSettingsRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PermissionType", PermissionType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DeviceRspType", DeviceRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^DeviceType", DeviceType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AttachmentRspDataType", AttachmentRspDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^FindTransactionsRspType", FindTransactionsRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^TransactionDataType", TransactionDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CredentialDataType", CredentialDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CheckDataType", CheckDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AttachmentInfoType", AttachmentInfoType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftDataType", GiftDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CashDataType", CashDataType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardAddValueRspType", PosGiftCardAddValueRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCheckVoidRspType", PosCheckVoidRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CheckRspInfoType", CheckRspInfoType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosCheckSaleRspType", PosCheckSaleRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosBatchCloseRspType", PosBatchCloseRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosAuthenticateRspType", PosAuthenticateRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGetUserSettingsRspType_User", PosGetUserSettingsRspTypeUser.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGetUserSettingsRspType", PosGetUserSettingsRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^MidRspType", MidRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^MerchNbrRspType", MerchNbrRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^SiteRspType", SiteRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^LicenseRspType", LicenseRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^UserInfoRspType", UserInfoRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportTxnDetailRspType_Data", PosReportTxnDetailRspTypeData.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportTxnDetailRspType", PosReportTxnDetailRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AutoSubstantiationReportType", AutoSubstantiationReportType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^CheckDataInfoType", CheckDataInfoType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportSearchRspType_Header", PosReportSearchRspTypeHeader.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportSearchRspType_Details", PosReportSearchRspTypeDetails.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportSearchRspType", PosReportSearchRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportOpenAuthsRspType_Header", PosReportOpenAuthsRspTypeHeader.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportOpenAuthsRspType_Details", PosReportOpenAuthsRspTypeDetails.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportOpenAuthsRspType", PosReportOpenAuthsRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchSummaryRspType_Header", PosReportBatchSummaryRspTypeHeader.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchSummaryRspType_Details", PosReportBatchSummaryRspTypeDetails.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchSummaryRspType", PosReportBatchSummaryRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchHistoryRspType_Header", PosReportBatchHistoryRspTypeHeader.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchHistoryRspType_Details", PosReportBatchHistoryRspTypeDetails.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchHistoryRspType", PosReportBatchHistoryRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchDetailRspType_Header", PosReportBatchDetailRspTypeHeader.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchDetailRspType_Details", PosReportBatchDetailRspTypeDetails.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportBatchDetailRspType", PosReportBatchDetailRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportActivityRspType_Header", PosReportActivityRspTypeHeader.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportActivityRspType_Details", PosReportActivityRspTypeDetails.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosReportActivityRspType", PosReportActivityRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosManageUsersRspType", PosManageUsersRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosUserDetailRspType", PosUserDetailRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^RoleRspType", RoleRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^ManageSettingsRspType", ManageSettingsRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosImpersonateRspType_User", PosImpersonateRspTypeUser.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosImpersonateRspType", PosImpersonateRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardVoidRspType", PosGiftCardVoidRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardTipRspType", PosGiftCardTipRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardSaleRspType", PosGiftCardSaleRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardRewardRspType", PosGiftCardRewardRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardReversalRspType", PosGiftCardReversalRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardReplaceRspType", PosGiftCardReplaceRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardDeactivateRspType", PosGiftCardDeactivateRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardTotalsType", GiftCardTotalsType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardBalanceRspType", PosGiftCardBalanceRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardAliasRspType", PosGiftCardAliasRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^GiftCardDataRspType", GiftCardDataRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosGiftCardActivateRspType", PosGiftCardActivateRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^AuthRspStatusType", AuthRspStatusType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^TokenDataRspType", TokenDataRspType.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosRequest_Ver1.0_Header", PosRequestVer10Header.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosRequest_Ver1.0_Transaction", PosRequestVer10Transaction.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosRequest_Ver1.0", PosRequestVer10.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosRequest", PosRequest.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosResponse_Ver1.0_Header", PosResponseVer10Header.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosResponse_Ver1.0_Transaction", PosResponseVer10Transaction.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosResponse_Ver1.0", PosResponseVer10.class);
        classNames.put("http://Hps.Exchange.PosGateway^^PosResponse", PosResponse.class);
    }

    private final String MsNs = "http://schemas.microsoft.com/2003/10/Serialization/";
    protected static final int QNAME_NAMESPACE = 0;
    private static final String TYPE_LABEL = "type";

    public ExtendedSoapSerializationEnvelope() {
        super(SoapEnvelope.VER10);
        implicitTypes = false;
        new MarshalGuid().register(this);
        new MarshalDate().register(this);
        new MarshalFloat().register(this);
    }

    @Override
    public void writeObjectBody(XmlSerializer writer, KvmSerializable obj) throws IOException {
        if(obj instanceof TextSoapObject)
            writer.text(((TextSoapObject)obj).getText());
        super.writeObjectBody(writer, obj);
    }

    @Override
    protected void writeProperty(XmlSerializer writer, Object obj, PropertyInfo type) throws IOException {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if (obj == null || obj == SoapPrimitive.NullNilElement) {
            writer.attribute(xsi, version >= VER12 ? NIL_LABEL : NULL_LABEL, "true");
            return;
        }
        Object[] qName = getInfo(null, obj);
        if (!type.multiRef && qName[2] == null) {
            if (!implicitTypes || (obj.getClass() != type.type && !(obj instanceof Vector) && type.type != String.class)) {
                String xmlName = Helper.getKeyByValue(classNames, obj.getClass());
                if (xmlName != null) {
                    String[] parts = xmlName.split("\\^\\^");
                    String prefix = writer.getPrefix(parts[0], true);
                    writer.attribute(xsi, TYPE_LABEL, prefix + ":" + parts[1]);
                } else {
                    String prefix = writer.getPrefix(type.namespace, true);
                    writer.attribute(xsi, TYPE_LABEL, prefix + ":" + obj.getClass().getSimpleName());
                }
            }
            //super.writeProperty(writer,obj,type);

            //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
            //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
            writeElement(writer, obj, type, qName[QNAME_MARSHAL]);
        } else {
            super.writeProperty(writer, obj, type);
        }
    }

    public SoapObject GetExceptionDetail(Element detailElement) {
        Element errorElement = detailElement.getElement(0);
        return GetSoapObject(errorElement);
    }

    public SoapObject GetSoapObject(Element detailElement) {
        try {
            XmlSerializer xmlSerializer = XmlPullParserFactory.newInstance().newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            detailElement.write(xmlSerializer);
            xmlSerializer.flush();

            XmlPullParser xpp = new KXmlParser();
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);

            xpp.setInput(new StringReader(writer.toString()));
            xpp.nextTag();
            SoapObject soapObj = new SoapObject(detailElement.getNamespace(), detailElement.getName());
            readSerializable(xpp, soapObj);
            return soapObj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object GetHeader(Element detailElement) {
        if (detailElement.getText(0) != null) {
            return new SoapPrimitive(detailElement.getNamespace(), detailElement.getName(), detailElement.getText(0));
        }

        return GetSoapObject(detailElement);
    }


    public Object get(Object soap, Class cl) {
        if (soap == null) {
            return null;
        }
        try {
            if (soap instanceof Vector) {
                Constructor ctor = cl.getConstructor(Vector.class, ExtendedSoapSerializationEnvelope.class);
                return ctor.newInstance(soap, this);
            }
            {
                if (soap instanceof SoapObject) {
                    String key = String.format("%s^^%s", ((SoapObject) soap).getNamespace(), ((SoapObject) soap).getName());
                    if (classNames.containsKey(key)) {
                        cl = classNames.get(key);
                    }
                }
                Constructor ctor = cl.getConstructor(AttributeContainer.class, ExtendedSoapSerializationEnvelope.class);
                return ctor.newInstance(soap, this);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
} 

