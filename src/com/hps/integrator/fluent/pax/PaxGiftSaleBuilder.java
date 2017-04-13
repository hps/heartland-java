package com.hps.integrator.fluent.pax;

import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.fluent.HpsBuilderValidation;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.CurrencyType;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.*;
import com.hps.integrator.terminals.pax.subgroups.*;

import java.math.BigDecimal;

public class PaxGiftSaleBuilder extends HpsBuilderAbstract<PaxDevice, GiftResponse> {
    HpsGiftCard card;
    BigDecimal amount;
    BigDecimal gratuity;
    Integer referenceNumber;
    CurrencyType currency = CurrencyType.USD;
    HpsTransactionDetails details;

    public PaxGiftSaleBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    public PaxGiftSaleBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public PaxGiftSaleBuilder withGratuity(BigDecimal gratuity) {
        this.gratuity = gratuity;
        return this;
    }
    public PaxGiftSaleBuilder withCard(HpsGiftCard card) {
        this.card = card;
        return this;
    }
    public PaxGiftSaleBuilder withCurrency(CurrencyType currency) {
        this.currency = currency;
        return this;
    }
    public PaxGiftSaleBuilder withDetails(HpsTransactionDetails details) {
        this.details = details;
        return this;
    }

    public PaxGiftSaleBuilder(PaxDevice device) { super(device); }

    @Override
    public GiftResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();
        amounts.setTransactionAmount(HpsStringUtils.toNumeric(amount));
        amounts.setTipAmount(HpsStringUtils.toNumeric(gratuity));

        AccountRequest account = new AccountRequest();
        if (card != null) {
            account.setAccountNumber(card.getCardNumber());
        }

        // Trace Sub Group
        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());
        if (details != null) {
            trace.setInvoiceNumber(details.getInvoiceNumber());
        }

        CashierSubGroup cashier = new CashierSubGroup();
        ExtDataSubGroup extData = new ExtDataSubGroup();

        PaxMsgId messageId = currency == CurrencyType.USD ? PaxMsgId.T06_DO_GIFT : PaxMsgId.T08_DO_LOYALTY;
        return service.doGift(messageId, PaxTxnType.SALE_REDEEM, amounts, account, trace, cashier, extData);
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        addValidation(new HpsBuilderValidation("currencyTypeIsRequired", "Currency is required"));
    }

    private boolean amountIsNotNull() {
        return amount != null;
    }

    private boolean currencyTypeIsRequired() {
        return currency != null;
    }
}
