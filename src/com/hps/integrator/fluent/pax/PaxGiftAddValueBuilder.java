package com.hps.integrator.fluent.pax;

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

public class PaxGiftAddValueBuilder extends HpsBuilderAbstract<PaxDevice, GiftResponse> {
    BigDecimal amount;
    HpsGiftCard card;
    Integer referenceNumber;
    CurrencyType currency = CurrencyType.USD;

    public PaxGiftAddValueBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    public PaxGiftAddValueBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public PaxGiftAddValueBuilder withCard(HpsGiftCard card) {
        this.card = card;
        return this;
    }
    public PaxGiftAddValueBuilder withCurrency(CurrencyType currency) {
        this.currency = currency;
        return this;
    }

    public PaxGiftAddValueBuilder(PaxDevice device) { super(device); }

    @Override
    public GiftResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();
        amounts.setTransactionAmount(HpsStringUtils.toNumeric(amount));

        AccountRequest account = new AccountRequest();
        if (card != null) {
            account.setAccountNumber(card.getCardNumber());
        }

        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());
        CashierSubGroup cashier = new CashierSubGroup();
        ExtDataSubGroup extData = new ExtDataSubGroup();

        PaxMsgId messageId = currency == CurrencyType.USD ? PaxMsgId.T06_DO_GIFT : PaxMsgId.T08_DO_LOYALTY;
        return service.doGift(messageId, PaxTxnType.ADD, amounts, account, trace, cashier, extData);
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required"));
        addValidation(new HpsBuilderValidation("currencyTypeIsRequired", "Currency is required"));
    }

    private boolean amountIsNotNull() {
        return amount != null;
    }

    private boolean currencyTypeIsRequired() {
        return currency != null;
    }
}
