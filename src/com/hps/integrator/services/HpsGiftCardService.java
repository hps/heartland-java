package com.hps.integrator.services;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.*;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsGiftCardAliasAction;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

/**
 * The HPS gift card service.
 */
public class HpsGiftCardService extends HpsService {
    public HpsGiftCardService() throws HpsException {
        super();
    }

    public HpsGiftCardService(IHpsServicesConfig servicesConfig) throws HpsException {
        super(servicesConfig);
    }

    /**
     * An <b>activate</b> transaction is used to activate a gift card and assign an initial amount to
     * it. The gift card must not have been previously activated.
     *
     * @param amount               The amount (in dollars).
     * @param currency             The currency (3-letter ISO code for currency).
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Activation
     * @throws HpsException
     */
    public HpsGiftCardActivate activate(BigDecimal amount, String currency, HpsGiftCard giftCard) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardActivateReqType item = new PosGiftCardActivateReqType();
        GiftCardActivateReqBlock1Type block1 = new GiftCardActivateReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        block1.Currency = Enums.currencyType.USD;
        block1.Amt = amount;
        item.Block1 = block1;
        transaction.GiftCardActivate = item;

        return this.submitActivation(transaction);
    }

    /**
     * An <b>AddValue</b> transaction is used to add an amount to the value of an active gift card.
     *
     * @param amount               The amount (in dollars).
     * @param currency             The currency (3-letter ISO code for currency).
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Add Value
     * @throws HpsException
     */
    public HpsGiftCardAddValue addValue(BigDecimal amount, String currency, HpsGiftCard giftCard) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardAddValueReqType item = new PosGiftCardAddValueReqType();
        GiftCardAddValueReqBlock1Type block1 = new GiftCardAddValueReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        item.Block1 = block1;
        transaction.GiftCardAddValue = item;

        return this.submitAddValue(transaction);
    }

    /**
     * A <b>Alias</b> transaction is used to manage an account number alias, such as a phone number,
     * for a stored value account. The transaction can be used to <i>Add</i> an alias to an existing account,
     * <i>Delete</i> an alias from an existing account or <i>Create</i> a new stored value account and add an alias
     * to the new account.
     *
     * @param action               Type of Alias action requested.
     * @param giftCard             The gift card information.
     * @param alias                Alternate identifier used to reference a stored value account.
     * @return The HPS gift Card Alias
     * @throws HpsException
     */
    public HpsGiftCardAlias alias(HpsGiftCardAliasAction action, HpsGiftCard giftCard, String alias) throws HpsException {
        Enums.GiftCardAliasReqBlock1TypeAction gatewayAction = Enums.GiftCardAliasReqBlock1TypeAction.ADD;
        if(action == HpsGiftCardAliasAction.Create) {
            gatewayAction = Enums.GiftCardAliasReqBlock1TypeAction.CREATE;
        } else if (action == HpsGiftCardAliasAction.Delete) {
            gatewayAction = Enums.GiftCardAliasReqBlock1TypeAction.DELETE;
        }

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardAliasReqType item = new PosGiftCardAliasReqType();
        GiftCardAliasReqBlock1Type block1 = new GiftCardAliasReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        block1.Action = gatewayAction;
        block1.Alias = alias;

        item.Block1 = block1;
        transaction.GiftCardAlias = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardAliasRspType aliasRsp = resp.Ver10.Transaction.GiftCardAlias;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(aliasRsp.RspCode), aliasRsp.RspText);
        HpsGiftCardAlias aliasResult = new HpsGiftCardAlias(this.hydrateTransactionHeader(resp.Ver10.Header));

        HpsGiftCard responseCard = new HpsGiftCard();
        responseCard.setNumber(aliasRsp.CardData.CardNbr);

        aliasResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        aliasResult.setGiftCard(responseCard);
        aliasResult.setResponseCode(Integer.toString(aliasRsp.RspCode));
        aliasResult.setResponseText(aliasRsp.RspText);

        return aliasResult;
    }

    /**
     * A <b>Balance</b> transaction is used to check the balance of a gift card.
     *
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Balance
     * @throws HpsException
     */
    public HpsGiftCardBalance balance(HpsGiftCard giftCard) throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardBalanceReqType item = new PosGiftCardBalanceReqType();
        GiftCardBalanceReqBlock1Type block1 = new GiftCardBalanceReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        item.Block1 = block1;
        transaction.GiftCardBalance = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardBalanceRspType balanceRsp = resp.Ver10.Transaction.GiftCardBalance;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(balanceRsp.RspCode), balanceRsp.RspText);

        HpsGiftCardBalance balanceResult = new HpsGiftCardBalance(this.hydrateTransactionHeader(resp.Ver10.Header));
        balanceResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        balanceResult.setAuthorizationCode(balanceRsp.AuthCode);
        balanceResult.setBalanceAmount(balanceRsp.BalanceAmt);
        balanceResult.setPointsBalanceAmount(balanceRsp.PointsBalanceAmt);
        balanceResult.setRewards(balanceRsp.Rewards);
        balanceResult.setNotes(balanceRsp.Notes);
        balanceResult.setResponseCode(Integer.toString(balanceRsp.RspCode));
        balanceResult.setResponseText(balanceRsp.RspText);

        return balanceResult;
    }

    /**
     * A <b>Deactivate</b> transaction is used to permanently deactivate a gift card. Once deactivated
     * a gift card can no longer be used for any transactions, nor can it be re-activated.
     *
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Deactivate
     * @throws HpsException
     */
    public HpsGiftCardDeactivate deactivate(HpsGiftCard giftCard) throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardDeactivateReqType item = new PosGiftCardDeactivateReqType();
        GiftCardDeactivateReqBlock1Type block1 = new GiftCardDeactivateReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        item.Block1 = block1;
        transaction.GiftCardDeactivate = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardDeactivateRspType deactivateRsp = resp.Ver10.Transaction.GiftCardDeactivate;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(deactivateRsp.RspCode), deactivateRsp.RspText);

        HpsGiftCardDeactivate deactivateResult = new HpsGiftCardDeactivate(this.hydrateTransactionHeader(resp.Ver10.Header));
        deactivateResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        deactivateResult.setAuthorizationCode(deactivateRsp.AuthCode);
        deactivateResult.setPointsBalanceAmount(deactivateRsp.PointsBalanceAmt);
        deactivateResult.setResponseCode(Integer.toString(deactivateRsp.RspCode));
        deactivateResult.setResponseText(deactivateRsp.RspText);

        return deactivateResult;
    }

    /**
     * A <b>Replace</b> transaction is used to replace an active gift card with another gift card.
     * Once replaced, the old gift card is permanently deactivated. The new gift card is assigned
     * the current amount of the old gift card.
     *
     * @param oldGiftCard             The old gift card information.
     * @param newGiftCard             The new gift card information.
     * @return The HPS gift Card Replace
     * @throws HpsException
     */
    public HpsGiftCardReplace replace(HpsGiftCard oldGiftCard, HpsGiftCard newGiftCard) throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardReplaceReqType item = new PosGiftCardReplaceReqType();
        GiftCardReplaceReqBlock1Type block1 = new GiftCardReplaceReqBlock1Type();

        block1.OldCardData = hydrateGiftCardData(oldGiftCard);
        block1.NewCardData = hydrateGiftCardData(newGiftCard);
        item.Block1 = block1;
        transaction.GiftCardReplace = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardReplaceRspType replaceRsp = resp.Ver10.Transaction.GiftCardReplace;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(replaceRsp.RspCode), replaceRsp.RspText);

        HpsGiftCardReplace replaceResult = new HpsGiftCardReplace(this.hydrateTransactionHeader(resp.Ver10.Header));
        replaceResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        replaceResult.setAuthorizationCode(replaceRsp.AuthCode);
        replaceResult.setBalanceAmount(replaceRsp.BalanceAmt);
        replaceResult.setPointsBalanceAmount(replaceRsp.PointsBalanceAmt);
        replaceResult.setResponseCode(Integer.toString(replaceRsp.RspCode));
        replaceResult.setResponseText(replaceRsp.RspText);

        return replaceResult;
    }

    /**
     * A <b>Reward</b> transaction is used to add rewards to a stored value account when a
     * purchase is made with a credit card, debit card, cash or other form of payment. The
     * Reward transaction amount is not deduced from or loaded to the stored value account,
     * but is used to determine the potential rewards that may be added to the account
     * based on the merchant’s loyalty and rewards program.
     *
     * @param amount               The amount (in dollars).
     * @param currency             Identifies the currency of a financial transaction ("usd" or "points")
     * @param giftCard             The gift card information.
     * @param gratuity             The portion of the purchase amount that is a gratuity
     * @param tax                  The portion of the purchase amount that is a tax
     * @return The HPS gift Card Reward
     * @throws HpsException
     */
    public HpsGiftCardReward reward(BigDecimal amount, String currency, HpsGiftCard giftCard, BigDecimal gratuity, BigDecimal tax) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        currency = currency.toLowerCase();

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardRewardReqType item = new PosGiftCardRewardReqType();
        GiftCardRewardReqBlock1Type block1 = new GiftCardRewardReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        block1.Amt = amount;

        if(currency.equals("usd") || currency.equals("points")) {
            block1.Currency = currency.equals("usd") ? Enums.currencyType.USD : Enums.currencyType.POINTS;
        }

        if (gratuity != null && gratuity.compareTo(BigDecimal.ZERO) > 0) { block1.GratuityAmtInfo  = gratuity; }
        if (tax != null && tax.compareTo(BigDecimal.ZERO) > 0) { block1.TaxAmtInfo = tax; }

        item.Block1 = block1;
        transaction.GiftCardReward = item;

        return submitReward(transaction);
    }

    /**
     * A <b>Reward</b> transaction is used to add rewards to a stored value account when a
     * purchase is made with a credit card, debit card, cash or other form of payment. The
     * Reward transaction amount is not deduced from or loaded to the stored value account,
     * but is used to determine the potential rewards that may be added to the account
     * based on the merchant’s loyalty and rewards program.
     *
     * @param amount               The amount (in dollars).
     * @param currency             Identifies the currency of a financial transaction ("usd" or "points")
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Reward
     * @throws HpsException
     */
    public HpsGiftCardReward reward(BigDecimal amount, String currency, HpsGiftCard giftCard) throws HpsException {
        return reward(amount, currency, giftCard, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /**
     * A <b>Sale</b> transaction is used to record a sale against the gift card. If successful, the card
     * amount is subtracted from the gift card amount.
     *
     * @param amount               The amount (in dollars).
     * @param currency             Identifies the currency of a financial transaction ("usd" or "points")
     * @param giftCard             The gift card information.
     * @param gratuity             The portion of the purchase amount that is a gratuity
     * @param tax                  The portion of the purchase amount that is a tax
     * @return The HPS gift Card Sale
     * @throws HpsException
     */
    public HpsGiftCardSale sale(BigDecimal amount, String currency, HpsGiftCard giftCard, BigDecimal gratuity, BigDecimal tax) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        currency = currency.toLowerCase();

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardSaleReqType item = new PosGiftCardSaleReqType();
        GiftCardSaleReqBlock1Type block1 = new GiftCardSaleReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        block1.Amt = amount;

        if(currency.equals("usd") || currency.equals("points")) {
            block1.Currency = currency.equals("usd") ? Enums.currencyType.USD : Enums.currencyType.POINTS;
        }

        if (gratuity != null && gratuity.compareTo(BigDecimal.ZERO) > 0) { block1.GratuityAmtInfo = gratuity; }
        if (tax != null && tax.compareTo(BigDecimal.ZERO) > 0) { block1.TaxAmtInfo = tax; }

        item.Block1 = block1;
        transaction.GiftCardSale = item;

        return submitSale(transaction);
    }

    /**
     * A <b>Sale</b> transaction is used to record a sale against the gift card. If successful, the card
     * amount is subtracted from the gift card amount.
     *
     * @param amount               The amount (in dollars).
     * @param currency             Identifies the currency of a financial transaction ("usd" or "points")
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Sale
     * @throws HpsException
     */
    public HpsGiftCardSale sale(BigDecimal amount, String currency, HpsGiftCard giftCard) throws HpsException {
        return sale(amount, currency, giftCard, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /**
     * A <b>Void</b> transaction is used to cancel a previously approved Sale, Activate, Add Value, Deactivate, or Replace.
     * If successful, the gift card is credited with the amount of the sale.
     *
     * @param transactionId        Transaction identifier assigned by Portico Gateway of the Sale transaction to void.
     * @return The HPS gift Card Sale
     * @throws HpsException
     */
    public HpsGiftCardVoid voidTransaction(int transactionId) throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardVoidReqType item = new PosGiftCardVoidReqType();
        GiftCardVoidReqBlock1Type block1 = new GiftCardVoidReqBlock1Type();

        block1.GatewayTxnId = transactionId;
        item.Block1 = block1;
        transaction.GiftCardVoid = item;

        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardVoidRspType voidRsp = resp.Ver10.Transaction.GiftCardVoid;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(voidRsp.RspCode), voidRsp.RspText);

        HpsGiftCardVoid voidResult = new HpsGiftCardVoid(this.hydrateTransactionHeader(resp.Ver10.Header));
        voidResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        voidResult.setAuthorizationCode(voidRsp.AuthCode);
        voidResult.setBalanceAmount(voidRsp.BalanceAmt);
        voidResult.setPointsBalanceAmount(voidRsp.PointsBalanceAmt);
        voidResult.setNotes(voidRsp.Notes);
        voidResult.setResponseCode(Integer.toString(voidRsp.RspCode));
        voidResult.setResponseText(voidRsp.RspText);

        return voidResult;
    }

    /**
     * A <b>Reverse</b> transaction is used to cancel a previously approved Sale, Activate, or AddValue
     * from the current open batch. If successful, the gift card balance is restored to the amount prior
     * to the transaction being reversed.
     *
     * @param giftCard             The gift card information.
     * @param amount               The amount (in dollars).
     * @param currency             Identifies the currency of a financial transaction ("usd" or "points")
     * @return The HPS gift Card Sale
     * @throws HpsException
     */
    public HpsGiftCardReversal reverse(HpsGiftCard giftCard, BigDecimal amount, String currency) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardReversalReqType item = new PosGiftCardReversalReqType();
        GiftCardReversalReqBlock1Type block1 = new GiftCardReversalReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.GiftCardReversal = item;

        return submitReversal(transaction);
    }

    /**
     * A <b>Reverse</b> transaction is used to cancel a previously approved Sale, Activate, or AddValue
     * from the current open batch. If successful, the gift card balance is restored to the amount prior
     * to the transaction being reversed.
     *
     * @param transactionId        Transaction identifier assigned by Portico Gateway of the transaction to be reversed.
     * @param amount               The amount (in dollars).
     * @param currency             Identifies the currency of a financial transaction ("usd" or "points")
     * @return The HPS gift Card Sale
     * @throws HpsException
     */
    public HpsGiftCardReversal reverse(int transactionId, BigDecimal amount, String currency) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        PosGiftCardReversalReqType item = new PosGiftCardReversalReqType();
        GiftCardReversalReqBlock1Type block1 = new GiftCardReversalReqBlock1Type();

        block1.GatewayTxnId = transactionId;
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.GiftCardReversal = item;

        return submitReversal(transaction);
    }

    private HpsGiftCardActivate submitActivation(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardActivateRspType activationRsp = resp.Ver10.Transaction.GiftCardActivate;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(activationRsp.RspCode), activationRsp.RspText);

        HpsGiftCardActivate activation = new HpsGiftCardActivate(this.hydrateTransactionHeader(resp.Ver10.Header));
        activation.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        activation.setAuthorizationCode(activationRsp.AuthCode);
        activation.setBalanceAmount(activationRsp.BalanceAmt);
        activation.setPointsBalanceAmount(activationRsp.PointsBalanceAmt);
        activation.setRewards(activationRsp.Rewards);
        activation.setNotes(activationRsp.Notes);
        activation.setResponseCode(Integer.toString(activationRsp.RspCode));
        activation.setResponseText(activationRsp.RspText);

        return activation;
    }

    private HpsGiftCardAddValue submitAddValue(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardAddValueRspType addValueRsp = resp.Ver10.Transaction.GiftCardAddValue;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(addValueRsp.RspCode), addValueRsp.RspText);

        HpsGiftCardAddValue addValue = new HpsGiftCardAddValue(this.hydrateTransactionHeader(resp.Ver10.Header));
        addValue.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        addValue.setAuthorizationCode(addValueRsp.AuthCode);
        addValue.setBalanceAmount(addValueRsp.BalanceAmt);
        addValue.setPointsBalanceAmount(addValueRsp.PointsBalanceAmt);
        addValue.setRewards(addValueRsp.Rewards);
        addValue.setNotes(addValueRsp.Notes);
        addValue.setResponseCode(Integer.toString(addValueRsp.RspCode));
        addValue.setResponseText(addValueRsp.RspText);

        return addValue;
    }

    private HpsGiftCardReward submitReward(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardRewardRspType rewardRsp = resp.Ver10.Transaction.GiftCardReward;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(rewardRsp.RspCode), rewardRsp.RspText);

        HpsGiftCardReward reward = new HpsGiftCardReward(this.hydrateTransactionHeader(resp.Ver10.Header));
        reward.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        reward.setAuthorizationCode(rewardRsp.AuthCode);
        reward.setBalanceAmount(rewardRsp.BalanceAmt);
        reward.setPointsBalanceAmount(rewardRsp.PointsBalanceAmt);
        reward.setResponseCode(Integer.toString(rewardRsp.RspCode));
        reward.setResponseText(rewardRsp.RspText);

        return reward;
    }

    private HpsGiftCardSale submitSale(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardSaleRspType saleRsp = resp.Ver10.Transaction.GiftCardSale;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(saleRsp.RspCode), saleRsp.RspText);

        HpsGiftCardSale sale = new HpsGiftCardSale(this.hydrateTransactionHeader(resp.Ver10.Header));
        sale.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        sale.setAuthorizationCode(saleRsp.AuthCode);
        sale.setBalanceAmount(saleRsp.BalanceAmt);
        sale.setSplitTenderCardAmount(saleRsp.SplitTenderCardAmt);
        sale.setSplitTenderBalanceDue(saleRsp.SplitTenderBalanceDueAmt);
        sale.setPointsBalanceAmount(saleRsp.PointsBalanceAmt);
        sale.setResponseCode(Integer.toString(saleRsp.RspCode));
        sale.setResponseText(saleRsp.RspText);

        return sale;
    }

    private HpsGiftCardReversal submitReversal(PosRequestVer10Transaction transaction) throws HpsException {
        this.transaction = transaction;
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardReversalRspType reversalRsp = resp.Ver10.Transaction.GiftCardReversal;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(reversalRsp.RspCode), reversalRsp.RspText);

        HpsGiftCardReversal reversal = new HpsGiftCardReversal(this.hydrateTransactionHeader(resp.Ver10.Header));
        reversal.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        reversal.setAuthorizationCode(reversalRsp.AuthCode);
        reversal.setBalanceAmount(reversalRsp.BalanceAmt);
        reversal.setResponseCode(Integer.toString(reversalRsp.RspCode));
        reversal.setResponseText(reversalRsp.RspText);

        return reversal;
    }

    private GiftCardDataType hydrateGiftCardData(HpsGiftCard giftCard)
    {
        GiftCardDataType cardDataType = new GiftCardDataType();

        if(giftCard.isTrackData()) {
            cardDataType.TrackData = giftCard.getNumber();
        } else {
            cardDataType.CardNbr = giftCard.getNumber();
        }

        if(giftCard.getEncryptionData() != null) {
            EncryptionDataType encryptionData = new EncryptionDataType();
            encryptionData.EncryptedTrackNumber = giftCard.getEncryptionData().getEncryptedTrackNumber();
            encryptionData.KSN = giftCard.getEncryptionData().getKsn();
            encryptionData.KTB = giftCard.getEncryptionData().getKsn();
            encryptionData.Version = giftCard.getEncryptionData().getVersion();
            cardDataType.EncryptionData = encryptionData;
        }

        return cardDataType;
    }
}
