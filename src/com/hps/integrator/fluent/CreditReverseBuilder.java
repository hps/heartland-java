package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.credit.HpsRefund;
import com.hps.integrator.entities.credit.HpsReversal;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditReverseBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsReversal> {
    BigDecimal amount;
    BigDecimal authAmount;
    String currency;
    HpsCreditCard card;
    String token;
    Integer transactionId;
    HpsTransactionDetails details;

    public CreditReverseBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public CreditReverseBuilder withCurrency(String value) {
        this.currency = value;
        return this;
    }
    public CreditReverseBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }
    public CreditReverseBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public CreditReverseBuilder withTransactionId(Integer value) {
        this.transactionId = value;
        return this;
    }
    public CreditReverseBuilder withAuthAmount(BigDecimal value) {
        this.authAmount = value;
        return this;
    }
    public CreditReverseBuilder withDetails(HpsTransactionDetails value) {
        this.details = value;
        return this;
    }

    public CreditReverseBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsReversal execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditReversal");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());

        if(authAmount != null)
            Et.subElement(block1, "AuthAmt").text(authAmount.toString());

        if(card != null) {
            Element cardData = Et.subElement(block1, "CardData");
            cardData.append(service.hydrateCardManualEntry(card, false, false));
        }
        else if(token != null) {
            Element cardData = Et.subElement(block1, "CardData");
            cardData.append(service.hydrateTokenData(token, false, false));
        }
        else if(transactionId != null)
            Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());

        if(details != null)
            block1.append(service.hydrateAdditionalTxnFields(details));

        String clientTransactionId = service.getClientTxnId(details);
        ElementTree response = service.submitTransaction(transaction, clientTransactionId);
        return new HpsReversal().fromElementTree(response);
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
        if(transactionId != null) count++;
        if(token != null) count++;

        return count == 1;
    }
}
