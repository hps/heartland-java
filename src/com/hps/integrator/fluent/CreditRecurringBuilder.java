package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.payplan.HpsPayPlanSchedule;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditRecurringBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsAuthorization> {
    HpsPayPlanSchedule schedule;
    String scheduleId;
    BigDecimal amount;
    HpsCreditCard card;
    String token;
    String paymentMethodKey;
    boolean oneTime = false;
    HpsCardHolder cardHolder;
    HpsTransactionDetails details;
    boolean allowDuplicates = false;

    public CreditRecurringBuilder withSchedule(HpsPayPlanSchedule value) {
        this.schedule = value;
        return this;
    }
    public CreditRecurringBuilder withScheduleId(String value) {
        this.scheduleId = value;
        return this;
    }
    public CreditRecurringBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public CreditRecurringBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }
    public CreditRecurringBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public CreditRecurringBuilder withPaymentMethodKey(String value) {
        this.paymentMethodKey = value;
        return this;
    }
    public CreditRecurringBuilder withOneTime(boolean value) {
        this.oneTime = value;
        return this;
    }
    public CreditRecurringBuilder withCardHolder(HpsCardHolder value) {
        this.cardHolder = value;
        return this;
    }
    public CreditRecurringBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }
    public CreditRecurringBuilder withDetails(HpsTransactionDetails value) {
        this.details = value;
        return this;
    }

    public CreditRecurringBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsAuthorization execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("RecurringBilling");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");
        Et.subElement(block1, "Amt").text(amount.toString());
        if(cardHolder != null)
            block1.append(service.hydrateCardHolder(cardHolder));
        if(details != null)
            block1.append(service.hydrateAdditionalTxnFields(details));

        if(card != null) {
            Element cardData = Et.subElement(block1, "CardData");
            cardData.append(service.hydrateCardManualEntry(card, false, false));
        }

        if(token != null) {
            Element cardData = Et.subElement(block1, "CardData");
            cardData.append(service.hydrateTokenData(token, false, false));
        }

        if(paymentMethodKey != null)
            Et.subElement(block1, "PaymentMethodKey").text(paymentMethodKey);

        if(scheduleId == null && schedule != null)
            scheduleId = schedule.getScheduleIdentifier();

        Element recurringData = Et.subElement(block1, "RecurringData");
        String scheduleId;
        if(schedule != null)
            scheduleId = schedule.getScheduleKey();
        else scheduleId = this.scheduleId;

        if(scheduleId != null)
            Et.subElement(recurringData, "ScheduleID").text(scheduleId);
        Et.subElement(recurringData, "OneTime").text(oneTime ? "Y" : "N");

        String clientTransactionId = service.getClientTxnId(details);
        ElementTree response = service.submitTransaction(transaction, clientTransactionId);
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
        if(paymentMethodKey != null) count++;
        if(token != null) count++;

        return count == 1;
    }
}
