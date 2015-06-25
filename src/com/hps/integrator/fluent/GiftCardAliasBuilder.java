package com.hps.integrator.fluent;

import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardAlias;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.GiftCardAliasAction;
import com.hps.integrator.services.fluent.HpsFluentGiftService;

public class GiftCardAliasBuilder extends HpsBuilderAbstract<HpsFluentGiftService, HpsGiftCardAlias> {
    HpsGiftCard card;
    String alias;
    GiftCardAliasAction action;

    public GiftCardAliasBuilder withCard(HpsGiftCard value) {
        this.card = value;
        return this;
    }
    public GiftCardAliasBuilder withAlias(String value) {
        this.alias = value;
        return this;
    }
    public GiftCardAliasBuilder withAction(GiftCardAliasAction value) {
        this.action = value;
        return this;
    }

    public GiftCardAliasBuilder(HpsFluentGiftService service) {
        super(service);
    }

    @Override
    public HpsGiftCardAlias execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("GiftCardAlias");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Action").text(action.getValue());
        Et.subElement(block1, "Alias").text(alias);
        if(card != null)
            block1.append(service.hydrateGiftCardData(card));

        ElementTree response = service.submitTransaction(transaction);
        return new HpsGiftCardAlias().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("aliasIsNotNull", "Alias is required."));
        this.addValidation(new HpsBuilderValidation("cardIsNotNull", "Card is required."));
        this.addValidation(new HpsBuilderValidation("actionIsNotNull", "Action is required."));
    }

    private boolean aliasIsNotNull(){
        return this.alias != null;
    }

    private boolean cardIsNotNull() {
        if(action != GiftCardAliasAction.Create)
            return this.card != null;
        else return this.card == null;
    }

    private boolean actionIsNotNull() {
        return this.action != null;
    }
}
