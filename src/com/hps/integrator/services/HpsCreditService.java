package com.hps.integrator.services;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.applepay.ecv1.PaymentData;
import com.hps.integrator.applepay.ecv1.PaymentData3DS;
import com.hps.integrator.entities.*;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.entities.payplan.HpsPayPlanSchedule;
import com.hps.integrator.fluent.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HpsCreditService extends HpsSoapGatewayService {

    public HpsCreditService() throws HpsException {
        super();
    }

    public HpsCreditService(IHpsServicesConfig servicesConfig) throws HpsException {
        super(servicesConfig);
    }

    public CreditAuthPaymentTypeBuilder authorize(BigDecimal amount) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        return new CreditAuthPaymentTypeBuilder(new CreditAuthBuilder(servicesConfig, amount));
    }

    public CreditChargePaymentTypeBuilder charge(BigDecimal amount) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        return new CreditChargePaymentTypeBuilder(new CreditChargeBuilder(servicesConfig, amount));
    }

    public CreditOfflineChargePaymentTypeBuilder offlineCharge(BigDecimal amount) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        return new CreditOfflineChargePaymentTypeBuilder(new CreditOfflineChargeBuilder(servicesConfig, amount));
    }

    public CreditOfflineAuthPaymentTypeBuilder offlineAuth(BigDecimal amount) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        return new CreditOfflineAuthPaymentTypeBuilder(new CreditOfflineAuthBuilder(servicesConfig, amount));
    }

    public CreditCaptureBuilder capture(int transactionId) throws HpsException {
        return new CreditCaptureBuilder(servicesConfig, transactionId);
    }

    public CreditEditUsingBuilder edit() throws HpsException {
        return new CreditEditUsingBuilder(new CreditEditBuilder(servicesConfig));
    }

    public CreditRefundUsingBuilder refund(BigDecimal amount) throws HpsException {
        return new CreditRefundUsingBuilder(new CreditRefundBuilder(servicesConfig, amount));
    }

    public CreditReverseUsingBuilder reverse(BigDecimal amount) throws HpsException {
        return new CreditReverseUsingBuilder(new CreditReverseBuilder(servicesConfig, amount));
    }

    public CreditVerifyUsingBuilder verify() throws HpsException {
        return new CreditVerifyUsingBuilder(new CreditVerifyBuilder(servicesConfig));
    }

    public CreditVoidBuilder voidTransaction(int transactionId) throws HpsException {
        return new CreditVoidBuilder(servicesConfig, transactionId);
    }

    public CreditRecurringPaymentTypeBuilder recurring(BigDecimal amount) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        return new CreditRecurringPaymentTypeBuilder(new CreditRecurringBuilder(servicesConfig, amount));
    }

    public CreditRecurringPaymentTypeBuilder recurring(BigDecimal amount, String scheduleId) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        return new CreditRecurringPaymentTypeBuilder(new CreditRecurringBuilder(servicesConfig, amount, scheduleId));
    };

    public CreditBalanceInquiryUsingBuilder prePaidBalanceInquiry() throws HpsException {
        return new CreditBalanceInquiryUsingBuilder(new CreditBalanceInquiryBuilder(servicesConfig));
    }

    public CreditAddValueUsingBuilder prePaidAddValue(BigDecimal amount) throws HpsException {
        return new CreditAddValueUsingBuilder(new CreditAddValueBuilder(servicesConfig, amount));
    }

    public CreditCpcEditBuilder cpcEdit(int transactionId) throws HpsException {
        return new CreditCpcEditBuilder(servicesConfig, transactionId);
    }

    public HpsReportTransactionDetails get(int transactionId) throws HpsException {
        if(transactionId <= 0) {
            throw new HpsInvalidRequestException("Invalid transaction ID.");
        }

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosReportTxnDetailReqType item = new PosReportTxnDetailReqType();
        item.TxnId = transactionId;
        transaction.ReportTxnDetail = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosReportTxnDetailRspType reportResponse = resp.Ver10.Transaction.ReportTxnDetail;
        HpsTransactionHeader header = this.hydrateTransactionHeader(resp.Ver10.Header);
        HpsReportTransactionDetails result = new HpsReportTransactionDetails(header);

        result.setTransactionID(reportResponse.GatewayTxnId);
        result.setOriginalTransactionId(reportResponse.OriginalGatewayTxnId);
        result.setAuthorizedAmount(reportResponse.Data.AuthAmt);
        result.setAuthorizationCode(reportResponse.Data.AuthCode);
        result.setAvsResultCode(reportResponse.Data.AVSRsltCode);
        result.setAvsResultText(reportResponse.Data.AVSRsltText);
        result.setCardType(reportResponse.Data.CardType);
        result.setMaskedCardNumber(reportResponse.Data.MaskedCardNbr);
        result.setDescriptor(reportResponse.Data.TxnDescriptor);
        result.setTransactionType(HpsTransaction.serviceNameToTransactionType(reportResponse.ServiceName));
        result.setTransactionDate(reportResponse.ReqUtcDT);
        result.setCpcIndicator(reportResponse.Data.CPCInd);
        result.setCvvResultCode(reportResponse.Data.CVVRsltCode);
        result.setCvvResultText(reportResponse.Data.CVVRsltText);
        result.setReferenceNumber(reportResponse.Data.RefNbr);
        result.setResponseCode(reportResponse.Data.RspCode);
        result.setResponseText(reportResponse.Data.RspText);

        String tokenizationMessage = reportResponse.Data.TokenizationMsg;

        if (tokenizationMessage != null) {
            result.setTokenData(new HpsTokenData(tokenizationMessage));
        }

        /* Set the additional transaction fields. It seems redundant to have a separate "details" object to
         * encapsulate these fields within a HpsReportTransactionDetails object. As such they are defined inside the
         * HpsReportTransactionDetails class directly.*/
        AdditionalTxnFieldsType additionalFields = reportResponse.Data.AdditionalTxnFields;
        if (additionalFields != null) {
            result.setCustomerId(additionalFields.CustomerID);
            result.setInvoiceNumber(additionalFields.InvoiceNbr);
            result.setMemo(additionalFields.Description);
        }

        String headerResponseCode = Integer.toString(resp.Ver10.Header.GatewayRspCode);
        String dataResponseCode = reportResponse.Data.RspCode;

        if (!headerResponseCode.equals("0") || (dataResponseCode != null && !dataResponseCode.equals("00"))) {
            HpsCreditExceptions exceptions = new HpsCreditExceptions();

            if (!headerResponseCode.equals("0")) {
                String headerResponseMsg = resp.Ver10.Header.GatewayRspMsg;
                exceptions.setHpsException(HpsGatewayResponseValidation.getException(Integer.parseInt(headerResponseCode), headerResponseMsg));
            }

            if (!dataResponseCode.equals("00")) {
                String dataResponseText = reportResponse.Data.RspText;
                exceptions.setHpsIssuerException(HpsIssuerResponseValidation.getException(transactionId, dataResponseCode, dataResponseText));
            }

            result.setExceptions(exceptions);
        }

        result.setResponseCode("00");
        result.setResponseText("");

        return result;
    }

    public List<HpsReportTransactionSummary> list(Date start, Date end) throws HpsException {
        return this.list(start, end, null);
    }

    public List<HpsReportTransactionSummary> list(Date start, Date end, HpsTransactionType filterBy) throws HpsException {
        HpsInputValidation.checkDateNotFuture(start, "start");
        HpsInputValidation.checkDateNotFuture(end, "end");

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosReportActivityReqType item = new PosReportActivityReqType();
        item.RptStartUtcDT = start;
        item.RptEndUtcDT = end;
        transaction.ReportActivity = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosReportActivityRspType reportResponse = resp.Ver10.Transaction.ReportActivity;
        String serviceName = (filterBy == null) ? "" : HpsTransaction.transactionTypeToServiceName(filterBy);
        List<HpsReportTransactionSummary> transactionList = new ArrayList<HpsReportTransactionSummary>();

        for (PosReportActivityRspTypeDetails charge : reportResponse.Details) {
            if (filterBy == null || charge.ServiceName.equals(serviceName)) {
                HpsReportTransactionSummary summary = new HpsReportTransactionSummary();
                summary.setTransactionID(charge.GatewayTxnId);
                summary.setOriginalTransactionId(charge.OriginalGatewayTxnId);
                summary.setMaskedCardNumber(charge.MaskedCardNbr);
                summary.setResponseCode(charge.IssuerRspCode);
                summary.setResponseText(charge.IssuerRspText);

                if (filterBy != null)
                    summary.setTransactionType(HpsTransaction.serviceNameToTransactionType(charge.ServiceName));

                String gatewayRspCode = Integer.toString(charge.GatewayRspCode);
                String issuerRspCode = charge.IssuerRspCode;

                if (!gatewayRspCode.equals("0") || (issuerRspCode != null && !issuerRspCode.equals("00"))) {
                    HpsCreditExceptions exceptions = new HpsCreditExceptions();

                    if (!gatewayRspCode.equals("0")) {
                        String gatewayRspMsg = charge.GatewayRspMsg;
                        exceptions.setHpsException(HpsGatewayResponseValidation.getException(Integer.parseInt(gatewayRspCode), gatewayRspMsg));
                    }

                    if (issuerRspCode != null && !issuerRspCode.equals("00")) {
                        String issuerRspMsg = charge.IssuerRspText;
                        exceptions.setHpsIssuerException(HpsIssuerResponseValidation.getException(charge.GatewayTxnId, issuerRspCode, issuerRspMsg));
                    }

                    summary.setExceptions(exceptions);
                }

                transactionList.add(summary);
            }
        }

        return transactionList;
    }

    public HpsCharge charge(BigDecimal amount, String currency, HpsCreditCard card, HpsCardHolder cardHolder, boolean allowDuplicates) throws HpsException {
        return charge(amount, currency, card, cardHolder, allowDuplicates, false, null, null, null, false);
    }

    public HpsCharge charge(BigDecimal amount, String currency, String token, HpsCardHolder cardHolder, boolean allowDuplicates) throws HpsException {
        return charge(amount, currency, token, cardHolder, allowDuplicates, false, null, null, null, false);
    }

    public HpsCharge charge(PaymentData paymentData, HpsCardHolder cardHolder, boolean allowDuplicates) throws HpsException {
        return charge(paymentData, cardHolder, allowDuplicates, false, null, null, null);
    }

    /**
     * The <b>credit sale</b> transaction authorizes a sale purchased with a credit card. The authorization in place
     * in the current open batch (should auto-close for e-commerce transactions). If a batch is not open, this
     * transaction will create an open batch.
     *
     * @param amount               The amount (in dollars).
     * @param currency             The currency (3-letter ISO code for currency).
     * @param card                 The credit card information.
     * @param cardHolder           The card holder information (used for AVS).
     * @param allowDuplicates      Indicates whether to allow duplicate transactions.
     * @param requestMultiUseToken Request a multi-use token.
     * @param descriptor           A description that is concatenated to a configurable merchant DBA name. The resulting string
     *                             is sent to the card issuer for the Merchant Name.
     * @param details              The transaction details.
     * @return The HPS Charge
     * @throws HpsException
     */
    public HpsCharge charge(BigDecimal amount, String currency, HpsCreditCard card, HpsCardHolder cardHolder, boolean allowDuplicates,
                            boolean requestMultiUseToken, String descriptor, HpsTransactionDetails details, HpsDirectMarketData directMarketData,
                            boolean cpcRequest) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditSaleReqType item = new PosCreditSaleReqType();
        CreditSaleReqBlock1Type block1 = new CreditSaleReqBlock1Type();

        block1.AllowDup = allowDuplicates ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        block1.Amt = amount;

        if (cardHolder != null)
            block1.CardHolderData = this.hydrateCardHolderData(cardHolder);

        CardDataType cardDataType = new CardDataType();

        cardDataType.TokenRequest = (requestMultiUseToken) ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        cardDataType.ManualEntry = this.hydrateCardManualEntry(card);
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        block1.DirectMktData = this.hydrateDirectMarketData(directMarketData);
        block1.CPCReq = (cpcRequest) ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        if (descriptor != null && descriptor.length() > 0) block1.TxnDescriptor = descriptor;

        block1.CardData = cardDataType;
        item.Block1 = block1;
        transaction.CreditSale = item;

        return this.submitCharge(transaction, amount, currency);
    }

    /**
     * The <b>credit sale</b> transaction authorizes a sale purchased with a credit card. The authorization in place
     * in the current open batch (should auto-close for e-commerce transactions). If a batch is not open, this
     * transaction will create an open batch.
     *
     * @param amount               The amount (in dollars).
     * @param currency             The currency (3-letter ISO code for currency).
     * @param token                The secure token.
     * @param cardHolder           The card holder information (used for AVS).
     * @param allowDuplicates      Indicates whether to allow duplicate transactions.
     * @param requestMultiUseToken Request a multi-use token.
     * @param descriptor           A description that is concatenated to a configurable merchant DBA name. The resulting string
     *                             is sent to the card issuer for the Merchant Name.
     * @param details              The transaction details.
     * @return The HPS Charge
     * @throws HpsException
     */
    public HpsCharge charge(BigDecimal amount, String currency, String token, HpsCardHolder cardHolder, boolean allowDuplicates,
                            boolean requestMultiUseToken, String descriptor, HpsTransactionDetails details,
                            HpsDirectMarketData directMarketData, boolean cpcRequest) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditSaleReqType item = new PosCreditSaleReqType();
        CreditSaleReqBlock1Type block1 = new CreditSaleReqBlock1Type();

        block1.AllowDup = allowDuplicates ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        block1.Amt = amount;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        block1.DirectMktData = this.hydrateDirectMarketData(directMarketData);
        block1.CPCReq = (cpcRequest) ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        if (descriptor != null && descriptor.length() > 0) block1.TxnDescriptor = descriptor;

        if (cardHolder != null) {
            block1.CardHolderData = this.hydrateCardHolderData(cardHolder);
        }

        CardDataType cardDataType = new CardDataType();
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        if (requestMultiUseToken) cardDataType.TokenRequest = Enums.booleanType.fromString("Y");

        cardDataType.TokenData = tokenData;
        block1.CardData = cardDataType;
        transaction.CreditSale = item;
        item.Block1 = block1;

        return this.submitCharge(transaction, amount, currency);
    }

    public HpsCharge charge(PaymentData paymentData, HpsCardHolder cardHolder, boolean allowDuplicates,
                            boolean requestMultiUseToken, String descriptor, HpsTransactionDetails details,
                            HpsDirectMarketData directMarketData) throws HpsException {
        BigDecimal amount = paymentData.getDollarAmount();

        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency("usd"); // TODO: this needs be parsed from the payment data

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditSaleReqType item = new PosCreditSaleReqType();
        CreditSaleReqBlock1Type block1 = new CreditSaleReqBlock1Type();

        block1.AllowDup = allowDuplicates ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        block1.Amt = amount;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        block1.DirectMktData = this.hydrateDirectMarketData(directMarketData);
        if (descriptor != null && descriptor.length() > 0) block1.TxnDescriptor = descriptor;

        if (cardHolder != null) {
            block1.CardHolderData = this.hydrateCardHolderData(cardHolder);
        }

        CardDataType cardDataType = new CardDataType();
        CardDataTypeManualEntry manualEntry = new CardDataTypeManualEntry();
        manualEntry.CardNbr = paymentData.getApplicationPrimaryAccountNumber();
        String expDate = paymentData.getApplicationExpirationDate();
        manualEntry.ExpMonth = Integer.parseInt(expDate.substring(2, 4));
        manualEntry.ExpYear = Integer.parseInt("20" + expDate.substring(0, 2));

        if (requestMultiUseToken) cardDataType.TokenRequest = Enums.booleanType.fromString("Y");

        cardDataType.ManualEntry = manualEntry;
        block1.SecureECommerce = this.hydrateSecureEcommerce(paymentData.getPaymentData());
        block1.CardData = cardDataType;
        transaction.CreditSale = item;
        item.Block1 = block1;

        return this.submitCharge(transaction, amount, "usd");
    }

    public HpsAccountVerify verify(HpsCreditCard card) throws HpsException {
        return this.verify(card, null, false);
    }

    public HpsAccountVerify verify(HpsCreditCard card, HpsCardHolder cardHolder) throws HpsException {
        return this.verify(card, cardHolder, false);
    }

    public HpsAccountVerify verify(HpsCreditCard card, HpsCardHolder cardHolder, boolean requestMultiUseToken) throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditAccountVerifyReqType item = new PosCreditAccountVerifyReqType();
        CreditAccountVerifyBlock1Type block1 = new CreditAccountVerifyBlock1Type();

        if (cardHolder != null)
            block1.CardHolderData = this.hydrateCardHolderData(cardHolder);

        Enums.booleanType multiUseToken = (requestMultiUseToken) ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        CardDataType cardData = new CardDataType();
        cardData.TokenRequest = multiUseToken;
        cardData.ManualEntry = this.hydrateCardManualEntry(card);

        block1.CardData = cardData;
        item.Block1 = block1;
        transaction.CreditAccountVerify = item;

        return submitVerify(transaction);
    }

    public HpsAccountVerify verify(String token) throws HpsException {
        return this.verify(token, null, false);
    }

    public HpsAccountVerify verify(String token, HpsCardHolder cardHolder) throws HpsException {
        return this.verify(token, cardHolder, false);
    }

    public HpsAccountVerify verify(String token, HpsCardHolder cardHolder, boolean requestMultiUseToken) throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditAccountVerifyReqType item = new PosCreditAccountVerifyReqType();
        CreditAccountVerifyBlock1Type block1 = new CreditAccountVerifyBlock1Type();

        if (cardHolder != null)
            block1.CardHolderData = this.hydrateCardHolderData(cardHolder);

        Enums.booleanType multiUseToken = (requestMultiUseToken) ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        CardDataType cardData = new CardDataType();
        cardData.TokenRequest = multiUseToken;

        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;
        cardData.TokenData = tokenData;

        block1.CardData = cardData;
        item.Block1 = block1;
        transaction.CreditAccountVerify = item;

        return submitVerify(transaction);
    }

    public HpsAuthorization authorize(BigDecimal amount, String currency, HpsCreditCard card, HpsCardHolder cardHolder, boolean allowDuplicates) throws HpsException {
        return this.authorize(amount, currency, card, cardHolder, allowDuplicates, false, null, null, false);
    }

    public HpsAuthorization authorize(BigDecimal amount, String currency, String token, HpsCardHolder cardHolder, boolean allowDuplicates) throws HpsException {
        return this.authorize(amount, currency, token, cardHolder, allowDuplicates, false, null, null, false);
    }

    public HpsAuthorization authorize(PaymentData paymentData, HpsCardHolder cardHolder, boolean allowDuplicates) throws HpsException {
        return this.authorize(paymentData, cardHolder, allowDuplicates, false, null, null);
    }

    /**
     * A <b>credit authorization</b> transaction authorizes a credit card transaction. The authorization is NOT placed
     * in the batch. The <b>credit authorization</b> transaction can be committed by using the capture method.
     *
     * @param amount               The amount (in dollars).
     * @param currency             The currency (3-letter ISO code for currency).
     * @param card                 The credit card information.
     * @param cardHolder           The card holder information (used for AVS).
     * @param allowDuplicates      Indicates whether to allow duplicate transactions.
     * @param requestMultiUseToken Request a multi-use token.
     * @param descriptor           A description that is concatenated to a configurable merchant DBA name. The resulting string
     *                             is sent to the card issuer for the Merchant Name.
     * @param details              The transaction details.
     * @param cpcRequest           Commercial card request.
     * @return The HPS Authorization
     * @throws HpsException
     */
    public HpsAuthorization authorize(BigDecimal amount, String currency, HpsCreditCard card, HpsCardHolder cardHolder, boolean allowDuplicates,
                                      boolean requestMultiUseToken, String descriptor, HpsTransactionDetails details, boolean cpcRequest) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditAuthReqType item = new PosCreditAuthReqType();
        CreditAuthReqBlock1Type block1 = new CreditAuthReqBlock1Type();
        CardDataType cardData = new CardDataType();

        block1.AllowDup = allowDuplicates ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        block1.Amt = amount;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        block1.CPCReq = (cpcRequest) ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        if (descriptor != null && descriptor.length() > 0) block1.TxnDescriptor = descriptor;
        if (cardHolder != null) block1.CardHolderData = this.hydrateCardHolderData(cardHolder);
        if (requestMultiUseToken) cardData.TokenRequest = Enums.booleanType.fromString("Y");

        cardData.ManualEntry = this.hydrateCardManualEntry(card);
        block1.CardData = cardData;
        item.Block1 = block1;
        transaction.CreditAuth = item;

        return this.submitAuthorize(transaction, amount, currency);
    }

    /**
     * A <b>credit authorization</b> transaction authorizes a credit card transaction. The authorization is NOT placed
     * in the batch. The <b>credit authorization</b> transaction can be committed by using the capture method.
     *
     * @param amount               The amount (in dollars).
     * @param currency             The currency (3-letter ISO code for currency).
     * @param token                The secure token.
     * @param cardHolder           The card holder information (used for AVS).
     * @param allowDuplicates      Indicates whether to allow duplicate transactions.
     * @param requestMultiUseToken Request a multi-use token.
     * @param descriptor           A description that is concatenated to a configurable merchant DBA name. The resulting string
     *                             is sent to the card issuer for the Merchant Name.
     * @param details              The transaction details.
     * @param cpcRequest           Commercial card request.
     * @return The HPS Authorization
     * @throws HpsException
     */
    public HpsAuthorization authorize(BigDecimal amount, String currency, String token, HpsCardHolder cardHolder, boolean allowDuplicates,
                                      boolean requestMultiUseToken, String descriptor, HpsTransactionDetails details, boolean cpcRequest) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditAuthReqType item = new PosCreditAuthReqType();
        CreditAuthReqBlock1Type block1 = new CreditAuthReqBlock1Type();
        CardDataType cardData = new CardDataType();

        block1.AllowDup = allowDuplicates ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        block1.Amt = amount;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        block1.CPCReq = (cpcRequest) ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        if (descriptor != null && descriptor.length() > 0) block1.TxnDescriptor = descriptor;

        if (cardHolder != null) block1.CardHolderData = this.hydrateCardHolderData(cardHolder);
        if (requestMultiUseToken) cardData.TokenRequest = Enums.booleanType.fromString("Y");

        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;
        cardData.TokenData = tokenData;
        block1.CardData = cardData;
        item.Block1 = block1;
        transaction.CreditAuth = item;

        return this.submitAuthorize(transaction, amount, currency);
    }

    public HpsAuthorization authorize(PaymentData paymentData, HpsCardHolder cardHolder, boolean allowDuplicates,
                                      boolean requestMultiUseToken, String descriptor, HpsTransactionDetails details) throws HpsException {
        BigDecimal amount = paymentData.getDollarAmount();

        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency("usd");

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditAuthReqType item = new PosCreditAuthReqType();
        CreditAuthReqBlock1Type block1 = new CreditAuthReqBlock1Type();
        CardDataType cardData = new CardDataType();

        block1.AllowDup = allowDuplicates ? Enums.booleanType.fromString("Y") : Enums.booleanType.fromString("N");
        block1.Amt = amount;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        if (descriptor != null && descriptor.length() > 0) block1.TxnDescriptor = descriptor;

        if (cardHolder != null) block1.CardHolderData = this.hydrateCardHolderData(cardHolder);
        if (requestMultiUseToken) cardData.TokenRequest = Enums.booleanType.fromString("Y");

        CardDataTypeManualEntry manualEntry = new CardDataTypeManualEntry();
        manualEntry.CardNbr = paymentData.getApplicationPrimaryAccountNumber();
        String expDate = paymentData.getApplicationExpirationDate();
        manualEntry.ExpMonth = Integer.parseInt(expDate.substring(2, 4));
        manualEntry.ExpYear = Integer.parseInt("20" + expDate.substring(0, 2));

        cardData.ManualEntry = manualEntry;
        block1.CardData = cardData;
        block1.SecureECommerce = this.hydrateSecureEcommerce(paymentData.getPaymentData());
        item.Block1 = block1;
        transaction.CreditAuth = item;

        return this.submitAuthorize(transaction, amount, "usd");
    }

    public HpsReportTransactionDetails captureTxn(int transactionId) throws HpsException {
        return this.captureTxn(transactionId, null, null, null);
    }

    public HpsReportTransactionDetails captureTxn(int transactionId, HpsDirectMarketData directMarketData) throws HpsException {
        return this.captureTxn(transactionId, null, null, directMarketData);
    }

    public HpsReportTransactionDetails captureTxn(int transactionId, BigDecimal amount) throws HpsException {
        return this.captureTxn(transactionId, amount, null, null);
    }

    public HpsReportTransactionDetails captureTxn(int transactionId, BigDecimal amount, HpsDirectMarketData directMarketData) throws HpsException {
        return this.captureTxn(transactionId, amount, null, directMarketData);
    }

    public HpsReportTransactionDetails captureTxn(int transactionId, BigDecimal amount, BigDecimal gratuity, HpsDirectMarketData directMarketData) throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditAddToBatchReqType item = new PosCreditAddToBatchReqType();
        item.GatewayTxnId = transactionId;
        item.Amt = amount;
        item.GratuityAmtInfo = gratuity;
        item.DirectMktData = hydrateDirectMarketData(directMarketData);
        transaction.CreditAddToBatch = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        return this.get(transactionId);
    }

    public HpsRefund refund(BigDecimal amount, String currency, HpsCreditCard card) throws HpsException {
        return this.refund(amount, currency, card, null, null);
    }

    public HpsRefund refund(BigDecimal amount, String currency, int transactionId) throws HpsException {
        return this.refund(amount, currency, transactionId, null, null);
    }

    public HpsRefund refund(BigDecimal amount, String currency, HpsCreditCard card, HpsCardHolder cardHolder) throws HpsException {
        return this.refund(amount, currency, card, cardHolder, null);
    }

    public HpsRefund refund(BigDecimal amount, String currency, int transactionId, HpsCardHolder cardHolder) throws HpsException {
        return this.refund(amount, currency, transactionId, cardHolder, null);
    }

    public HpsRefund refund(BigDecimal amount, String currency, HpsCreditCard card, HpsCardHolder cardHolder,
                            HpsTransactionDetails details) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditReturnReqType item = new PosCreditReturnReqType();
        CreditReturnReqBlock1Type block1 = new CreditReturnReqBlock1Type();
        CardDataType cardData = new CardDataType();

        block1.AllowDup = Enums.booleanType.fromString("Y");

        if (cardHolder != null)
            block1.CardHolderData = this.hydrateCardHolderData(cardHolder);

        cardData.ManualEntry = this.hydrateCardManualEntry(card);
        block1.CardData = cardData;
        block1.Amt = amount;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        item.Block1 = block1;
        transaction.CreditReturn = item;

        return this.submitRefund(transaction);
    }

    public HpsRefund refund(BigDecimal amount, String currency, int transactionId, HpsCardHolder cardHolder,
                            HpsTransactionDetails details) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditReturnReqType item = new PosCreditReturnReqType();
        CreditReturnReqBlock1Type block1 = new CreditReturnReqBlock1Type();
        block1.AllowDup = Enums.booleanType.fromString("Y");

        if (cardHolder != null)
            block1.CardHolderData = this.hydrateCardHolderData(cardHolder);

        block1.GatewayTxnId = transactionId;
        block1.Amt = amount;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        item.Block1 = block1;
        transaction.CreditReturn = item;

        return this.submitRefund(transaction);
    }

    public HpsReversal reverse(HpsCreditCard card, BigDecimal amount, String currency) throws HpsException {
        return this.reverse(card, amount, currency, null);
    }

    public HpsReversal reverse(int transactionId, BigDecimal amount, String currency) throws HpsException {
        return this.reverse(transactionId, amount, currency, null);
    }

    public HpsReversal reverse(HpsCreditCard card, BigDecimal amount, String currency,
                               HpsTransactionDetails details) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditReversalReqType item = new PosCreditReversalReqType();
        CreditReversalReqBlock1Type block1 = new CreditReversalReqBlock1Type();
        CardDataType cardData = new CardDataType();

        block1.Amt = amount;
        block1.CardData = cardData;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);
        cardData.ManualEntry = this.hydrateCardManualEntry(card);

        item.Block1 = block1;
        transaction.CreditReversal = item;
        return this.submitReverse(transaction);
    }

    public HpsReversal reverse(int transactionId, BigDecimal amount, String currency,
                               HpsTransactionDetails details) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditReversalReqType item = new PosCreditReversalReqType();
        CreditReversalReqBlock1Type block1 = new CreditReversalReqBlock1Type();

        block1.Amt = amount;
        block1.GatewayTxnId = transactionId;
        block1.AdditionalTxnFields = this.hydrateAdditionalTxnFields(details);

        item.Block1 = block1;
        transaction.CreditReversal = item;
        return this.submitReverse(transaction);
    }

    public HpsTransaction edit(int transactionId, BigDecimal amount) throws HpsException {
        return edit(transactionId, amount, BigDecimal.ZERO);
    }

    public HpsTransaction edit(int transactionId, BigDecimal amount, BigDecimal gratuity) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        if(transactionId <= 0) {
            throw new HpsInvalidRequestException("Invalid transaction ID.");
        }

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditTxnEditReqType item = new PosCreditTxnEditReqType();

        item.GatewayTxnId = transactionId;
        item.Amt = amount;
        item.GratuityAmtInfo = gratuity;

        transaction.CreditTxnEdit = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();

        this.processChargeGatewayResponse(resp, amount, "usd");
        PosResponseVer10Header header = resp.Ver10.Header;

        HpsTransaction result = new HpsTransaction(this.hydrateTransactionHeader(header));
        result.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        result.setResponseCode("00");
        result.setResponseText("");

        return result;
    }

    public HpsTransaction voidTxn(int transactionId) throws HpsException {
        if(transactionId <= 0) {
            throw new HpsInvalidRequestException("Invalid transaction ID.");
        }

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditVoidReqType item = new PosCreditVoidReqType();

        item.GatewayTxnId = transactionId;

        transaction.CreditVoid = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();

        HpsGatewayResponseValidation.checkGatewayResponse(resp);
        PosResponseVer10Header header = resp.Ver10.Header;

        HpsTransaction result = new HpsTransaction(this.hydrateTransactionHeader(header));
        result.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        result.setResponseCode("00");
        result.setResponseText("");

        return result;
    }

    public HpsTransaction cpcEdit(int transactionId, HpsCpcData cpcData) throws HpsException {
        if(transactionId <= 0) {
            throw new HpsInvalidRequestException("Invalid transaction ID.");
        }

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosCreditCPCEditReqType item = new PosCreditCPCEditReqType();

        item.GatewayTxnId = transactionId;
        item.CPCData = this.hydrateCpcData(cpcData);

        transaction.CreditCPCEdit = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();

        HpsGatewayResponseValidation.checkGatewayResponse(resp);
        PosResponseVer10Header header = resp.Ver10.Header;

        HpsTransaction result = new HpsTransaction(this.hydrateTransactionHeader(header));
        result.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        result.setResponseCode("00");
        result.setResponseText("");

        return result;
    }

    private CardHolderDataType hydrateCardHolderData(HpsCardHolder cardHolder) {
        CardHolderDataType result = new CardHolderDataType();

        result.CardHolderFirstName = cardHolder.getFirstName();
        result.CardHolderLastName = cardHolder.getLastName();
        result.CardHolderEmail = cardHolder.getEmail();
        result.CardHolderPhone = cardHolder.getPhone();
        result.CardHolderAddr = cardHolder.getAddress().getAddress();
        result.CardHolderCity = cardHolder.getAddress().getCity();
        result.CardHolderState = cardHolder.getAddress().getState();
        result.CardHolderZip = cardHolder.getAddress().getZip();

        return result;
    }

    private CardDataTypeManualEntry hydrateCardManualEntry(HpsCreditCard card) {
        CardDataTypeManualEntry result = new CardDataTypeManualEntry();
        String cvv = card.getCvv();

        result.CardNbr = card.getNumber();
        result.ExpMonth = card.getExpMonth();
        result.ExpYear = card.getExpYear();
        result.CVV2 = cvv;
        result.CardPresent = Enums.booleanType.fromString("N");
        result.ReaderPresent = Enums.booleanType.fromString("N");

        return result;
    }

    private AdditionalTxnFieldsType hydrateAdditionalTxnFields(HpsTransactionDetails details) {
        if (details == null) return null;

        AdditionalTxnFieldsType result = new AdditionalTxnFieldsType();
        if (details.getMemo() != null && details.getMemo().length() > 0) result.Description = details.getMemo();
        if (details.getInvoiceNumber() != null && details.getInvoiceNumber().length() > 0)
            result.InvoiceNbr = details.getInvoiceNumber();
        if (details.getCustomerId() != null && details.getCustomerId().length() > 0)
            result.CustomerID = details.getCustomerId();

        return result;
    }

    private DirectMktDataType hydrateDirectMarketData(HpsDirectMarketData directMarketData) {
        if(directMarketData == null) return null;

        DirectMktDataType result = new DirectMktDataType();
        result.DirectMktInvoiceNbr = directMarketData.getInvoiceNumber();
        result.DirectMktShipDay = directMarketData.getShipDay();
        result.DirectMktShipMonth = directMarketData.getShipMonth();

        return result;
    }

    private CPCDataType hydrateCpcData(HpsCpcData cpcData) {
        if(cpcData == null) return null;

        CPCDataType result = new CPCDataType();
        result.CardHolderPONbr = cpcData.getCardHolderPoNumber();
        result.TaxType = cpcData.getTaxType();
        result.TaxAmt = cpcData.getTaxAmount();

        return result;
    }

    private SecureECommerceType hydrateSecureEcommerce(PaymentData3DS paymentData) {
        if(paymentData == null) return null;

        SecureECommerceType result = new SecureECommerceType();
        result.TypeOfPaymentData = Enums.TypeOfPaymentDataType._3DSecure;

        result.PaymentData = new SecureECommerceTypePaymentData();
        result.PaymentData.value = paymentData.getOnlinePaymentCryptogram();
        result.PaymentData.encoding = Enums.EncodingType.base64;

        result.ECommerceIndicator = "5";

        return result;
    }

    private HpsCharge submitCharge(PosRequestVer10Transaction transaction, BigDecimal amount, String currency) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        this.processChargeGatewayResponse(resp, amount, currency);

        AuthRspStatusType creditSaleRsp = resp.Ver10.Transaction.CreditSale;
        this.processChargeIssuerResponse(creditSaleRsp.RspCode, creditSaleRsp.RspText, resp.Ver10.Header.GatewayTxnId, amount, currency);

        HpsCharge charge = new HpsCharge(this.hydrateTransactionHeader(resp.Ver10.Header));

        charge.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        charge.setAuthorizedAmount(creditSaleRsp.AuthAmt);
        charge.setAuthorizationCode(creditSaleRsp.AuthCode);
        charge.setAvsResultCode(creditSaleRsp.AVSRsltCode);
        charge.setAvsResultText(creditSaleRsp.AVSRsltText);
        charge.setCardType(creditSaleRsp.CardType);
        charge.setCpcIndicator(creditSaleRsp.CPCInd);
        charge.setCvvResultCode(creditSaleRsp.CVVRsltCode);
        charge.setCvvResultText(creditSaleRsp.CVVRsltText);
        charge.setReferenceNumber(creditSaleRsp.RefNbr);
        charge.setResponseCode(creditSaleRsp.RspCode);
        charge.setResponseText(creditSaleRsp.RspText);

        if (resp.Ver10.Header.TokenData != null) {
            HpsTokenData tokenData = new HpsTokenData();
            tokenData.setTokenRspCode(resp.Ver10.Header.TokenData.TokenRspCode);
            tokenData.setTokenRspMsg(resp.Ver10.Header.TokenData.TokenRspMsg);
            tokenData.setTokenValue(resp.Ver10.Header.TokenData.TokenValue);
            charge.setTokenData(tokenData);
        }

        return charge;
    }

    private HpsAuthorization submitAuthorize(PosRequestVer10Transaction transaction, BigDecimal amount, String currency) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        this.processChargeGatewayResponse(resp, amount, currency);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType authResponse = resp.Ver10.Transaction.CreditAuth;

        this.processChargeIssuerResponse(authResponse.RspCode, authResponse.RspText, header.GatewayTxnId, amount, currency);

        HpsAuthorization auth = new HpsAuthorization(this.hydrateTransactionHeader(header));

        auth.setTransactionID(header.GatewayTxnId);
        auth.setAvsResultCode(authResponse.AVSRsltCode);
        auth.setAvsResultText(authResponse.AVSRsltText);
        auth.setCvvResultCode(authResponse.CVVRsltCode);
        auth.setCvvResultText(authResponse.CVVRsltText);
        auth.setAuthorizationCode(authResponse.AuthCode);
        auth.setAuthorizedAmount(authResponse.AuthAmt);
        auth.setReferenceNumber(authResponse.RefNbr);
        auth.setResponseCode(authResponse.RspCode);
        auth.setResponseText(authResponse.RspText);
        auth.setCardType(authResponse.CardType);
        auth.setCpcIndicator(authResponse.CPCInd);

        if (header.TokenData != null) {
            HpsTokenData tokenData = new HpsTokenData();
            tokenData.setTokenRspCode(header.TokenData.TokenRspCode);
            tokenData.setTokenRspMsg(header.TokenData.TokenRspMsg);
            tokenData.setTokenValue(header.TokenData.TokenValue);
            auth.setTokenData(tokenData);
        }

        return auth;
    }

    private HpsRefund submitRefund(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        HpsRefund refund = new HpsRefund(this.hydrateTransactionHeader(header));
        refund.setTransactionID(header.GatewayTxnId);
        refund.setResponseCode("00");
        refund.setResponseText("");

        return refund;
    }

    private HpsReversal submitReverse(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType respReversal = resp.Ver10.Transaction.CreditReversal;
        HpsReversal reversal = new HpsReversal(this.hydrateTransactionHeader(header));

        reversal.setTransactionID(header.GatewayTxnId);
        reversal.setAvsResultCode(respReversal.AVSRsltCode);
        reversal.setAvsResultText(respReversal.AVSRsltText);
        reversal.setCpcIndicator(respReversal.CPCInd);
        reversal.setCvvResultCode(respReversal.CVVRsltCode);
        reversal.setCvvResultText(respReversal.CVVRsltText);
        reversal.setReferenceNumber(respReversal.RefNbr);
        reversal.setResponseCode(respReversal.RspCode);
        reversal.setResponseText(respReversal.RspText);

        return reversal;
    }

    private HpsAccountVerify submitVerify(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = this.doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        AuthRspStatusType creditVerifyRsp = resp.Ver10.Transaction.CreditAccountVerify;
        HpsAccountVerify accountVerify = new HpsAccountVerify(this.hydrateTransactionHeader(resp.Ver10.Header));

        accountVerify.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        accountVerify.setAvsResultCode(creditVerifyRsp.AVSRsltCode);
        accountVerify.setAvsResultText(creditVerifyRsp.AVSRsltText);
        accountVerify.setReferenceNumber(creditVerifyRsp.RefNbr);
        accountVerify.setResponseCode(creditVerifyRsp.RspCode);
        accountVerify.setResponseText(creditVerifyRsp.RspText);
        accountVerify.setCardType(creditVerifyRsp.CardType);
        accountVerify.setCpcIndicator(creditVerifyRsp.CPCInd);
        accountVerify.setCvvResultCode(creditVerifyRsp.CVVRsltCode);
        accountVerify.setCvvResultText(creditVerifyRsp.CVVRsltText);
        accountVerify.setAuthorizationCode(creditVerifyRsp.AuthCode);
        accountVerify.setAuthorizedAmount(creditVerifyRsp.AuthAmt);

        if(resp.Ver10.Header.TokenData != null) {
            HpsTokenData tokenData = new HpsTokenData();
            tokenData.setTokenRspCode(resp.Ver10.Header.TokenData.TokenRspCode);
            tokenData.setTokenRspMsg(resp.Ver10.Header.TokenData.TokenRspMsg);
            tokenData.setTokenValue(resp.Ver10.Header.TokenData.TokenValue);
            accountVerify.setTokenData(tokenData);
        }

        HpsIssuerResponseValidation.checkIssuerResponse(accountVerify.getTransactionID(),
                accountVerify.getResponseCode(), accountVerify.getResponseText());

        return accountVerify;
    }

    private void processChargeGatewayResponse(PosResponse resp, BigDecimal amount, String currency) throws HpsException {
        if(resp.Ver10.Header.GatewayRspCode == 0) return;
        if (resp.Ver10.Header.GatewayRspCode == 30) {
            try {
                this.reverse(resp.Ver10.Header.GatewayTxnId, amount, currency, null);
            } catch (Exception e) {
                throw new HpsGatewayException(HpsGatewayExceptionCodes.GatewayTimeoutReversalError,
                        "Error occurred while reversing a charge due to HPS gateway time-out.", e);
            }
        }

        HpsGatewayResponseValidation.checkGatewayResponse(resp);
    }

    private void processChargeIssuerResponse(String responseCode, String responseText, int transactionId, BigDecimal amount, String currency) throws HpsException {
        if (responseCode.equals("91")) {
            try {
                // try to reverse the transaction
                this.reverse(transactionId, amount, currency, null);
            } catch (HpsGatewayException e) {
                // if the transaction wasn't found; throw the original timeout exception.
                if (e.getDetails().getGatewayResponseCode() == 3) {
                    HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText);
                }

                throw new HpsIssuerException(transactionId, HpsIssuerExceptionCodes.IssuerTimeoutReversalError,
                        "Error occurred while reversing a charge due to HPS issuer time-out.", e);
            } catch (Exception e) {
                throw new HpsIssuerException(transactionId, HpsIssuerExceptionCodes.IssuerTimeoutReversalError,
                        "Error occurred while reversing a charge due to HPS issuer time-out.", e);
            }
        }

        HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText);
    }
}