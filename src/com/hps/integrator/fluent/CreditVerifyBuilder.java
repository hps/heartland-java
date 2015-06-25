package com.hps.integrator.fluent;

import com.hps.integrator.applepay.ecv1.PaymentData;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditVerifyBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsAccountVerify> {
    private HpsCreditCard card;
    private String token;
    private HpsTrackData trackData;
    private HpsCardHolder cardHolder;
    private boolean requestMultiUseToken = false;
    private boolean cardPresent = false;
    private boolean readerPresent = false;
    private String clientTransactionId;

    public CreditVerifyBuilder withCard(HpsCreditCard card){
        this.card = card;
        return this;
    }
    public CreditVerifyBuilder withToken(String token){
        this.token = token;
        return this;
    }
    public CreditVerifyBuilder withTrackData(HpsTrackData trackData){
        this.trackData = trackData;
        return this;
    }
    public CreditVerifyBuilder withCardHolder(HpsCardHolder cardHolder){
        this.cardHolder = cardHolder;
        return this;
    }
    public CreditVerifyBuilder withRequestMultiUseToken(boolean requestMultiUseToken){
        this.requestMultiUseToken = requestMultiUseToken;
        return this;
    }
    public CreditVerifyBuilder withCardPresent(boolean cardPresent){
        this.cardPresent = cardPresent;
        return this;
    }
    public CreditVerifyBuilder withReaderPresent(boolean readerPresent){
        this.readerPresent = readerPresent;
        return this;
    }
    public CreditVerifyBuilder withClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public CreditVerifyBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsAccountVerify execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditAccountVerify");
        Element block1 = Et.subElement(transaction, "Block1");

        if(cardHolder != null)
            block1.append(service.hydrateCardHolder(cardHolder));

        Element cardData = Et.subElement(block1, "CardData");
        if(card != null) {
            cardData.append(service.hydrateCardManualEntry(card, cardPresent, readerPresent));
            if(card.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(card.getEncryptionData()));
        }
        else if(token != null)
            cardData.append(service.hydrateTokenData(token, cardPresent, readerPresent));
        else if(trackData != null) {
            cardData.append(service.hydrateTrackData(trackData));
            if(trackData.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(trackData.getEncryptionData()));
        }
        Et.subElement(cardData, "TokenRequest").text(requestMultiUseToken ? "Y" : "N");

        ElementTree response = service.submitTransaction(transaction, clientTransactionId);
        return new HpsAccountVerify().fromElementTree(response);
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
