package com.hps.integrator.fluent.pax;

import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.fluent.HpsBuilderValidation;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.CurrencyType;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.*;
import com.hps.integrator.terminals.pax.subgroups.*;

public class PaxGiftBalanceBuilder extends HpsBuilderAbstract<PaxDevice, GiftResponse> {
    HpsGiftCard card;
    Integer referenceNumber;
    CurrencyType currency = CurrencyType.USD;

    public PaxGiftBalanceBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    public PaxGiftBalanceBuilder withCard(HpsGiftCard card) {
        this.card = card;
        return this;
    }
    public PaxGiftBalanceBuilder withCurrency(CurrencyType currency) {
        this.currency = currency;
        return this;
    }

    public PaxGiftBalanceBuilder(PaxDevice device) { super(device); }

    @Override
    public GiftResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();

        AccountRequest account = new AccountRequest();
        if (card != null) {
            account.setAccountNumber(card.getCardNumber());
        }

        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());

        CashierSubGroup cashier = new CashierSubGroup();
        ExtDataSubGroup extData = new ExtDataSubGroup();

        PaxMsgId messageId = currency == CurrencyType.USD ? PaxMsgId.T06_DO_GIFT : PaxMsgId.T08_DO_LOYALTY;
        return service.doGift(messageId, PaxTxnType.BALANCE, amounts, account, trace, cashier, extData);
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation("currencyTypeIsRequired", "Currency is required"));
    }

    private boolean currencyTypeIsRequired() {
        return currency != null;
    }
}
