package com.hps.integrator.fluent;

import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.entities.payplan.HpsPayPlanSchedule;

import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCheckService;

import java.math.BigDecimal;

public class CheckRecurringBuilder extends HpsBuilderAbstract<HpsFluentCheckService, HpsCheckResponse> {
    private BigDecimal amount;
    private String paymentMethodKey;
    private String scheduleKey;
    private boolean oneTime = false;

    public CheckRecurringBuilder withAmount(BigDecimal amount){
        this.amount = amount;
        return this;
    }
    public CheckRecurringBuilder withSchedule(HpsPayPlanSchedule schedule){
        return withSchedule(schedule.getScheduleKey());
    }
    public CheckRecurringBuilder withSchedule(String scheduleKey){
        this.scheduleKey = scheduleKey;
        return this;
    }
    public CheckRecurringBuilder withPaymentMethodKey(String paymentMethodKey){
        this.paymentMethodKey = paymentMethodKey;
        return this;
    }
    public CheckRecurringBuilder withOneTime(boolean oneTime){
        this.oneTime = oneTime;
        return this;
    }

    public CheckRecurringBuilder(HpsFluentCheckService service) {
        super(service);
    }

    @Override
    public HpsCheckResponse execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CheckSale");
        Element block1 = Et.subElement(transaction, "Block1");

        Et.subElement(block1, "Amt").text(this.amount.toString());
        Et.subElement(block1, "CheckAction").text("SALE");
        Et.subElement(block1, "PaymentMethodKey").text(this.paymentMethodKey);

        Element recurringData = Et.subElement(block1, "RecurringData");
        if(this.scheduleKey != null)
            Et.subElement(recurringData, "ScheduleID").text(this.scheduleKey);
        Et.subElement(recurringData, "OneTime").text(this.oneTime ? "Y" : "N");

        return this.service.submitTransaction(transaction);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        this.addValidation(new HpsBuilderValidation("paymentMethodIsNotNull", "Payment method key is required for sale."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }

    private boolean paymentMethodIsNotNull(){
        return this.paymentMethodKey != null;
    }
}
