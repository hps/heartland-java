package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.ebt.HpsEbtAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentEbtService;

import java.math.BigDecimal;

public class EbtRefundBuilder extends HpsBuilderAbstract<HpsFluentEbtService, HpsEbtAuthorization> {
    boolean allowDuplicates = false;
    BigDecimal amount;
    HpsCreditCard card;
    HpsCardHolder cardHolder;
    String pinBlock;
    boolean requestMultiUseToken = false;
    HpsTrackData trackData;
    String token;
    String tokenParameters;

    public EbtRefundBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }
    public EbtRefundBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public EbtRefundBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }
    public EbtRefundBuilder withCardHolder(HpsCardHolder value) {
        this.cardHolder = value;
        return this;
    }
    public EbtRefundBuilder withPinBlock(String value) {
        this.pinBlock = value;
        return this;
    }
    public EbtRefundBuilder withRequestMultiUseToken(boolean value) {
        this.requestMultiUseToken = value;
        return this;
    }
    public EbtRefundBuilder withTrackData(HpsTrackData value) {
        this.trackData = value;
        return this;
    }
    public EbtRefundBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public EbtRefundBuilder withTokenParameters(String value) {
        this.tokenParameters = value;
        return this;
    }

    public EbtRefundBuilder(HpsFluentEbtService service) { super(service); }

    public HpsEbtAuthorization execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("EBTFSReturn");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");

        Element cardData = Et.subElement(block1, "CardData");
        if(card != null) {
            cardData.append(service.hydrateCardManualEntry(card, false, false));
            if(card.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(card.getEncryptionData()));
        }
        if(trackData != null) {
            cardData.append(service.hydrateTrackData(trackData));
            if(trackData.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(trackData.getEncryptionData()));
        }
        if(token != null)
            cardData.append(service.hydrateTokenData(token, false, false));

        Et.subElement(cardData, "TokenRequest").text(requestMultiUseToken ? "Y" : "N");
        Et.subElement(block1, "PinBlock").text(pinBlock);

        return service.submitTransaction(transaction);
    }

    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        this.addValidation(new HpsBuilderValidation("pinBlockIsNotNull", "Pin block is required."));
        this.addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }

    private boolean pinBlockIsNotNull() { return this.pinBlock != null; }

    private boolean onlyOnePaymentMethod(){
        int count = 0;
        if(trackData != null) count++;
        if(card != null) count++;
        if(token != null) count++;

        return count == 1;
    }
}
