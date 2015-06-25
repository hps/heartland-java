package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditAddValueBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsAuthorization> {
    BigDecimal amount;
    boolean allowDuplicates = false;
    HpsCreditCard card;
    HpsTrackData trackData;
    String token;

    public CreditAddValueBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsAuthorization execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("PrePaidAddValue");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");

        Element cardData = Et.subElement(block1, "CardData");
        if(card != null)
            cardData.append(service.hydrateCardManualEntry(card, false, false));

        if(trackData != null)
            cardData.append(service.hydrateTrackData(trackData));

        if(token != null)
            cardData.append(service.hydrateTokenData(token, false, false));

        ElementTree response = service.submitTransaction(transaction);
        return new HpsAuthorization().fromElementTree(response);
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
        if(trackData != null) count++;
        if(token != null) count++;

        return count == 1;
    }

    public CreditAddValueBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public CreditAddValueBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }

    public CreditAddValueBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }

    public CreditAddValueBuilder withTrackData(HpsTrackData value) {
        this.trackData = value;
        return this;
    }

    public CreditAddValueBuilder withToken(String value) {
        this.token = value;
        return this;
    }
}
