package com.hps.integrator.services;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.*;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.GiftCardAliasAction;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

/**
 * The HPS gift card service.
 */
public class HpsGiftCardService extends HpsSoapGatewayService {
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
    public HpsGiftCardResponse activate(BigDecimal amount, String currency, HpsGiftCard giftCard) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        Element transaction = Et.element("GiftCardActivate");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(hydrateGiftCardData(giftCard));

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
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
    public HpsGiftCardResponse addValue(BigDecimal amount, String currency, HpsGiftCard giftCard) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        Element transaction = Et.element("GiftCardAddValue");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(hydrateGiftCardData(giftCard));

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
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
    public HpsGiftCardAlias alias(GiftCardAliasAction action, HpsGiftCard giftCard, String alias) throws HpsException {
        Element transaction = Et.element("GiftCardAlias");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Action").text(action.getValue());
        Et.subElement(block1, "Alias").text(alias);
        block1.append(hydrateGiftCardData(giftCard));

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardAlias().fromElementTree(response);
    }

    /**
     * A <b>Balance</b> transaction is used to check the balance of a gift card.
     *
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Balance
     * @throws HpsException
     */
    public HpsGiftCardResponse cardBalance(HpsGiftCard giftCard) throws HpsException {
        Element transaction = Et.element("GiftCardBalance");
        Element block1 = Et.subElement(transaction, "Block1");
        block1.append(hydrateGiftCardData(giftCard));

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
    }

    /**
     * A <b>Deactivate</b> transaction is used to permanently deactivate a gift card. Once deactivated
     * a gift card can no longer be used for any transactions, nor can it be re-activated.
     *
     * @param giftCard             The gift card information.
     * @return The HPS gift Card Deactivate
     * @throws HpsException
     */
    public HpsGiftCardResponse deactivateCard(HpsGiftCard giftCard) throws HpsException {
        Element transaction = Et.element("GiftCardBalance");
        Element block1 = Et.subElement(transaction, "Block1");
        block1.append(hydrateGiftCardData(giftCard));

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
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
    public HpsGiftCardResponse replaceCard(HpsGiftCard oldGiftCard, HpsGiftCard newGiftCard) throws HpsException {
        Element transaction = Et.element("GiftCardReplace");
        Element block1 = Et.subElement(transaction, "Block1");
        block1.append(hydrateGiftCardData(oldGiftCard, "OldCardData"));
        block1.append(hydrateGiftCardData(newGiftCard, "NewCardData"));

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
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
    public HpsGiftCardResponse reward(BigDecimal amount, String currency, HpsGiftCard giftCard, BigDecimal gratuity, BigDecimal tax) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        currency = currency.toLowerCase();

        Element transaction = Et.element("GiftCardReward");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(hydrateGiftCardData(giftCard));

        if(currency.equals("usd") || currency.equals("points"))
            Et.subElement(block1, "Currency").text(currency.equals("usd") ? "USD" : "POINTS");

        if(gratuity != null)
            Et.subElement(block1, "GratuityAmtInfo").text(gratuity.toString());

        if(tax != null)
            Et.subElement(block1, "TaxAmtInfo").text(tax.toString());

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
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
    public HpsGiftCardResponse reward(BigDecimal amount, String currency, HpsGiftCard giftCard) throws HpsException {
        return reward(amount, currency, giftCard, null, null);
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

        Element transaction = Et.element("GiftCardSale");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(hydrateGiftCardData(giftCard));

        if(currency.equals("usd") || currency.equals("points"))
            Et.subElement(block1, "Currency").text(currency.equals("usd") ? "USD" : "POINTS");

        if(gratuity != null)
            Et.subElement(block1, "GratuityAmtInfo").text(gratuity.toString());

        if(tax != null)
            Et.subElement(block1, "TaxAmtInfo").text(tax.toString());

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardSale().fromElementTree(response);
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
        return sale(amount, currency, giftCard, null, null);
    }

    /**
     * A <b>Void</b> transaction is used to cancel a previously approved Sale, Activate, Add Value, Deactivate, or Replace.
     * If successful, the gift card is credited with the amount of the sale.
     *
     * @param transactionId        Transaction identifier assigned by Portico Gateway of the Sale transaction to void.
     * @return The HPS gift Card Sale
     * @throws HpsException
     */
    public HpsGiftCardResponse voidTxn(Integer transactionId) throws HpsException {
        Element transaction = Et.element("GiftCardVoid");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
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
    public HpsGiftCardResponse reverse(HpsGiftCard giftCard, BigDecimal amount, String currency) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        Element transaction = Et.element("GiftCardReversal");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(hydrateGiftCardData(giftCard));

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
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
    public HpsGiftCardResponse reverse(Integer transactionId, BigDecimal amount, String currency) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        Element transaction = Et.element("GiftCardReversal");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());

        ElementTree response = submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
    }

    public ElementTree submitTransaction(Element transaction) throws HpsException {
        return submitTransaction(transaction, null);
    }
    public ElementTree submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
        ElementTree rsp = doTransaction(transaction, clientTransactionId);
        HpsGatewayResponseValidation.checkGatewayResponse(rsp, transaction.tag());
        HpsIssuerResponseValidation.checkIssuerResponse(
                rsp.get("Header").getInt("GatewayTxnId"),
                rsp.get(transaction.tag()).getString("RspCode"),
                rsp.get(transaction.tag()).getString("RspText")
        );

        return rsp;
    }
}
