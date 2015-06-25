package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.ebt.HpsEbtAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentEbtService;

import java.math.BigDecimal;

public class EbtBalanceInquiryBuilder extends HpsBuilderAbstract<HpsFluentEbtService, HpsEbtAuthorization> {
    BigDecimal amount;
    HpsCreditCard card;
    boolean cardPresent = false;
    String inquiryType;
    String pinBlock;
    boolean readerPresent = false;
    boolean requestMultiUseToken = false;
    HpsTrackData trackData;
    String token;
    String tokenParameters;

    public EbtBalanceInquiryBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withCardPresent(boolean value) {
        this.cardPresent = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withInquiryType(String value) {
        this.inquiryType = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withPinBlock(String value) {
        this.pinBlock = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withReaderPresent(boolean value) {
        this.readerPresent = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withRequestMultiUseToken(boolean value) {
        this.requestMultiUseToken = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withTrackData(HpsTrackData value) {
        this.trackData = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public EbtBalanceInquiryBuilder withTokenParameters(String value) {
        this.tokenParameters = value;
        return this;
    }

    public EbtBalanceInquiryBuilder(HpsFluentEbtService service) { super(service); }

    public HpsEbtAuthorization execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("EBTBalanceInquiry");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());

        Element cardData = Et.subElement(block1, "CardData");
        if(card != null) {
            cardData.append(service.hydrateCardManualEntry(card, cardPresent, readerPresent));
            if(card.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(card.getEncryptionData()));
        }
        if(trackData != null) {
            cardData.append(service.hydrateTrackData(trackData));
            if(trackData.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(trackData.getEncryptionData()));
        }
        if(token != null)
            cardData.append(service.hydrateTokenData(token, cardPresent, readerPresent));

        Et.subElement(cardData, "TokenRequest").text(requestMultiUseToken ? "Y" : "N");
        Et.subElement(block1, "BalanceInquiryType").text(inquiryType);
        Et.subElement(block1, "PinBlock").text(pinBlock);

        return service.submitTransaction(transaction);
    }

    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        this.addValidation(new HpsBuilderValidation("inquiryTypeIsNotNull", "Inquiry type is required."));
        this.addValidation(new HpsBuilderValidation("pinBlockIsNotNull", "Pin block is required."));
        this.addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }

    private boolean inquiryTypeIsNotNull() { return this.inquiryType != null; }

    private boolean pinBlockIsNotNull() { return this.pinBlock != null; }

    private boolean onlyOnePaymentMethod(){
        int count = 0;
        if(trackData != null) count++;
        if(card != null) count++;
        if(token != null) count++;

        return count == 1;
    }
}
