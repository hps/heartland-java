package com.hps.integrator.fluent;

import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardResponse;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentGiftService;

import java.math.BigDecimal;

public class GiftCardReverseBuilder extends HpsBuilderAbstract<HpsFluentGiftService, HpsGiftCardResponse> {
    HpsGiftCard card;
    BigDecimal amount;
    String currency;
    Integer transactionId;

    public GiftCardReverseBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public GiftCardReverseBuilder withCard(HpsGiftCard value) {
        this.card = value;
        return this;
    }
    public GiftCardReverseBuilder withCurrncy(String value) {
        this.currency = value;
        return this;
    }
    public GiftCardReverseBuilder withTransactionId(Integer value) {
        this.transactionId = value;
        return this;
    }

    public GiftCardReverseBuilder(HpsFluentGiftService service) {
        super(service);
    }

    @Override
    public HpsGiftCardResponse execute() throws HpsException {
        super.execute();

        HpsInputValidation.checkAmount(amount);

        Element transaction = Et.element("GiftCardReversal");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());

        if(card != null)
            block1.append(service.hydrateGiftCardData(card));
        else if(transactionId != null)
            Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());

        ElementTree response = service.submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        this.addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }

    private boolean onlyOnePaymentMethod(){
        int count = 0;
        if(card != null) count++;
        if(transactionId != null) count++;

        return count == 1;
    }
}
