package com.hps.integrator.fluent;

import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardResponse;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentGiftService;

public class GiftCardBalanceBuilder extends HpsBuilderAbstract<HpsFluentGiftService, HpsGiftCardResponse> {
    HpsGiftCard card;

    public GiftCardBalanceBuilder withCard(HpsGiftCard value) {
        this.card = value;
        return this;
    }

    public GiftCardBalanceBuilder(HpsFluentGiftService service) {
        super(service);
    }

    @Override
    public HpsGiftCardResponse execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("GiftCardBalance");
        Element block1 = Et.subElement(transaction, "Block1");
        block1.append(service.hydrateGiftCardData(card));

        ElementTree response = service.submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("cardIsNotNull", "Card is required."));
    }

    private boolean cardIsNotNull() { return this.card != null; }
}
