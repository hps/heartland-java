package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.credit.HpsRefund;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditRefundBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsRefund> {
    BigDecimal amount;
    String currency;
    HpsCreditCard card;
    String token;
    Integer transactionId;
    HpsCardHolder cardHolder;
    HpsTransactionDetails details;
    boolean allowDuplicates = false;
    HpsDirectMarketData directMarketData;

    public CreditRefundBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public CreditRefundBuilder withCurrency(String value) {
        this.currency = value;
        return this;
    }
    public CreditRefundBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }
    public CreditRefundBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public CreditRefundBuilder withTransactionId(Integer value) {
        this.transactionId = value;
        return this;
    }
    public CreditRefundBuilder withCardHolder(HpsCardHolder value) {
        this.cardHolder = value;
        return this;
    }
    public CreditRefundBuilder withDetails(HpsTransactionDetails value) {
        this.details = value;
        return this;
    }
    public CreditRefundBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }
    public CreditRefundBuilder withDirectMarketData(HpsDirectMarketData value) {
        this.directMarketData = value;
        return this;
    }

    public CreditRefundBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsRefund execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditReturn");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");
        Et.subElement(block1, "Amt").text(amount.toString());

        if(cardHolder != null)
            block1.append(service.hydrateCardHolder(cardHolder));

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
        if(directMarketData != null)
            block1.append(service.hydrateDirectMarketData(directMarketData));

        String clientTransactionId = service.getClientTxnId(details);
        ElementTree response = service.submitTransaction(transaction, clientTransactionId);
        HpsRefund trans = new HpsRefund().fromElementTree(response);
        trans.setResponseCode("00");
        trans.setResponseText("");
        return trans;
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
