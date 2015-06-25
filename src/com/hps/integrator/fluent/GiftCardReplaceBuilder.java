package com.hps.integrator.fluent;

import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardResponse;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentGiftService;

public class GiftCardReplaceBuilder extends HpsBuilderAbstract<HpsFluentGiftService, HpsGiftCardResponse> {
    HpsGiftCard oldCard;
    HpsGiftCard newCard;

    public GiftCardReplaceBuilder withNewCard(HpsGiftCard value) {
        this.newCard = value;
        return this;
    }
    public GiftCardReplaceBuilder withOldCard(HpsGiftCard value) {
        this.oldCard = value;
        return this;
    }

    public GiftCardReplaceBuilder(HpsFluentGiftService service) {
        super(service);
    }

    @Override
    public HpsGiftCardResponse execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("GiftCardReplace");
        Element block1 = Et.subElement(transaction, "Block1");
        block1.append(service.hydrateGiftCardData(oldCard, "OldCardData"));
        block1.append(service.hydrateGiftCardData(newCard, "NewCardData"));

        ElementTree response = service.submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("oldCardIsNotNull", "OldCard is required."));
        this.addValidation(new HpsBuilderValidation("newCardIsNotNull", "NewCard is required."));
    }

    private boolean oldCardIsNotNull() { return this.oldCard != null; }
    private boolean newCardIsNotNull() { return this.newCard != null; }
}
