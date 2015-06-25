package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

public class CreditBalanceInquiryBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsAuthorization> {
    HpsCreditCard card;
    HpsCardHolder cardHolder;
    HpsTrackData trackData;
    String token;

    public CreditBalanceInquiryBuilder withCard(HpsCreditCard card) {
        this.card = card;
        return this;
    }
    public CreditBalanceInquiryBuilder withCardHolder(HpsCardHolder cardHolder) {
        this.cardHolder = cardHolder;
        return this;
    }
    public CreditBalanceInquiryBuilder withTrackData(HpsTrackData trackData) {
        this.trackData = trackData;
        return this;
    }
    public CreditBalanceInquiryBuilder withToken(String token) {
        this.token = token;
        return this;
    }

    public CreditBalanceInquiryBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsAuthorization execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("PrePaidBalanceInquiry");
        Element block1 = Et.subElement(transaction, "Block1");

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
        this.addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
    }

    private boolean onlyOnePaymentMethod(){
        int count = 0;
        if(card != null) count++;
        if(trackData != null) count++;
        if(token != null) count++;

        return count == 1;
    }
}
