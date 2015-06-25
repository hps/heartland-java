package com.hps.integrator.fluent;

import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardSale;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentGiftService;

import java.math.BigDecimal;

public class GiftCardSaleBuilder extends HpsBuilderAbstract<HpsFluentGiftService, HpsGiftCardSale> {
    HpsGiftCard card;
    BigDecimal amount;
    String currency;
    BigDecimal gratuity;
    BigDecimal tax;

    public GiftCardSaleBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public GiftCardSaleBuilder withCard(HpsGiftCard value) {
        this.card = value;
        return this;
    }
    public GiftCardSaleBuilder withCurrency(String value) {
        this.currency = value;
        return this;
    }

    public GiftCardSaleBuilder(HpsFluentGiftService service) {
        super(service);
    }

    @Override
    public HpsGiftCardSale execute() throws HpsException {
        super.execute();

        HpsInputValidation.checkAmount(amount);
        currency = currency.toLowerCase();

        Element transaction = Et.element("GiftCardSale");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        block1.append(service.hydrateGiftCardData(card));

        if(currency.equals("usd") || currency.equals("points"))
            Et.subElement(block1, "Currency").text(currency.equals("usd") ? "USD" : "POINTS");

        if(gratuity != null)
            Et.subElement(block1, "GratuityAmtInfo").text(gratuity.toString());

        if(tax != null)
            Et.subElement(block1, "TaxAmtInfo").text(tax.toString());

        ElementTree response = service.submitTransaction(transaction);
        return new HpsGiftCardSale().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        this.addValidation(new HpsBuilderValidation("cardIsNotNull", "Card is required."));
        this.addValidation(new HpsBuilderValidation("currencyIsNotNull", "Currency is required."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }

    private boolean cardIsNotNull() { return this.card != null; }

    private boolean currencyIsNotNull() { return this.currency != null; }
}
