package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.ebt.HpsEbtAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentEbtService;

import java.math.BigDecimal;

public class EbtBenefitWithdrawalBuilder extends HpsBuilderAbstract<HpsFluentEbtService, HpsEbtAuthorization> {
    boolean allowDuplicates = false;
    BigDecimal amount;
    HpsCreditCard card;
    HpsCardHolder cardHolder;
    BigDecimal cashBack = new BigDecimal("0.00");
    String pinBlock;
    boolean requestMultiUseToken = false;
    HpsTrackData trackData;
    String token;
    String tokenParameters;

    public EbtBenefitWithdrawalBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withCardHolder(HpsCardHolder value) {
        this.cardHolder = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withCashBack(BigDecimal value) {
        this.cashBack = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withPinBlock(String value) {
        this.pinBlock = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withRequestMultiUseToken(boolean value) {
        this.requestMultiUseToken = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withTrackData(HpsTrackData value) {
        this.trackData = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public EbtBenefitWithdrawalBuilder withTokenParameters(String value) {
        this.tokenParameters = value;
        return this;
    }

    public EbtBenefitWithdrawalBuilder(HpsFluentEbtService service) { super(service); }

    public HpsEbtAuthorization execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("EBTCashBenefitWithdrawal");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");
        Et.subElement(block1, "CashBackAmount").text(cashBack.toString());

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
