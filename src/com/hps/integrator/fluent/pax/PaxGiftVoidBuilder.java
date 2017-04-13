package com.hps.integrator.fluent.pax;

import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.fluent.HpsBuilderValidation;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.CurrencyType;
import com.hps.integrator.infrastructure.emums.PaxExtData;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.*;
import com.hps.integrator.terminals.pax.subgroups.*;

public class PaxGiftVoidBuilder extends HpsBuilderAbstract<PaxDevice, GiftResponse> {
    Integer referenceNumber;
    Integer transactionId;

    CurrencyType currency = CurrencyType.USD;

    public PaxGiftVoidBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    public PaxGiftVoidBuilder withTransactionId(int transactionId){
        this.transactionId = transactionId;
        return this;
    }
    public PaxGiftVoidBuilder withCurrency(CurrencyType currency) {
        this.currency = currency;
        return this;
    }

    public PaxGiftVoidBuilder(PaxDevice device) { super(device); }

    @Override
    public GiftResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();

        AccountRequest account = new AccountRequest();

        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());

        CashierSubGroup cashier = new CashierSubGroup();

        ExtDataSubGroup extData = new ExtDataSubGroup();
        extData.set(PaxExtData.HOST_REFERENCE_NUMBER, transactionId.toString());

        PaxMsgId messageId = currency == CurrencyType.USD ? PaxMsgId.T06_DO_GIFT : PaxMsgId.T08_DO_LOYALTY;
        return service.doGift(messageId, PaxTxnType.VOID, amounts, account, trace, cashier, extData);
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation("transactionIdIsNotNull", "Transaction ID is required"));
        addValidation(new HpsBuilderValidation("currencyIsNotNull", "Currency is required"));
    }

    private boolean transactionIdIsNotNull() {
        return transactionId != null && transactionId > 0;
    }
    private boolean currencyIsNotNull() {
        return currency != null;
    }
}
