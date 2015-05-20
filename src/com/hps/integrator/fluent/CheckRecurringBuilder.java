package com.hps.integrator.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.entities.payplan.HpsPayPlanSchedule;

import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import java.math.BigDecimal;

public class CheckRecurringBuilder extends SoapTransactionBuilder<CheckRecurringBuilder, HpsCheckResponse> {
    private BigDecimal amount;
    private String paymentMethodKey;
    private String scheduleKey;
    private boolean oneTime = false;

    public CheckRecurringBuilder(IHpsServicesConfig config) {
        super(config, true);
    }

    @Override
    public CheckRecurringBuilder getBuilder() { return this; }

    @Override
    public HpsCheckResponse execute() throws HpsException, HpsCheckException {
        Element transaction = Et.element("CheckSale");
        Element block1 = Et.subElement(transaction, "Block1");

        Et.subElement(block1, "Amt").text(this.amount.toString());
        Et.subElement(block1, "CheckAction").text("SALE");
        Et.subElement(block1, "PaymentMethodKey").text(this.paymentMethodKey);

        Element recurringData = Et.subElement(block1, "RecurringData");
        if(this.scheduleKey != null)
            Et.subElement(recurringData, "ScheduleID").text(this.scheduleKey);
        Et.subElement(recurringData, "OneTime").text(this.oneTime ? "Y" : "N");

        ElementTree response = this.doTransaction(transaction);
        HpsCheckResponse rvalue = HpsCheckResponse.fromElementTree(response);
        if(rvalue.getResponseCode() == null || !"0".equals(rvalue.getResponseCode())) {
            Element item = response.get("CheckSale");
            throw new HpsCheckException(
                    response.get("Header").getInt("gatewayTxnId"),
                    rvalue.getDetails(),
                    item.getInt("RspCode"),
                    item.getString("RspMessage")
            );
        }
        return rvalue;
    }

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

    public CheckRecurringBuilder oneTime(){
        this.oneTime = true;
        return this;
    }
}
