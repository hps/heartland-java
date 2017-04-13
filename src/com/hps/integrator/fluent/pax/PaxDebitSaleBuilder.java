package com.hps.integrator.fluent.pax;


import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.fluent.HpsBuilderValidation;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.PaxExtData;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.DebitResponse;
import com.hps.integrator.terminals.pax.subgroups.*;

import java.math.BigDecimal;
public class PaxDebitSaleBuilder extends HpsBuilderAbstract<PaxDevice, DebitResponse> {
    boolean allowDuplicates = false;
    BigDecimal amount;
    BigDecimal cashBack;
    HpsTransactionDetails details;
    Integer referenceNumber;

    public PaxDebitSaleBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public PaxDebitSaleBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }
    public PaxDebitSaleBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public PaxDebitSaleBuilder withCashBack(BigDecimal value) {
        this.cashBack = value;
        return this;
    }
    public PaxDebitSaleBuilder withDetails(HpsTransactionDetails value) {
        this.details = value;
        return this;
    }

    public PaxDebitSaleBuilder(PaxDevice device) {
        super(device);
    }

    @Override
    public DebitResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();
        amounts.setTransactionAmount(HpsStringUtils.toNumeric(amount));
        amounts.setCashBackAmount(HpsStringUtils.toNumeric(cashBack));

        AccountRequest account = new AccountRequest();
        if (allowDuplicates) account.setDupOverrideFlag("1");


        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());
        if (details != null) {
            trace.setInvoiceNumber(details.getInvoiceNumber());
        }

        CashierSubGroup cashier = new CashierSubGroup();

        // Additional Info sub group
        ExtDataSubGroup extData = new ExtDataSubGroup();

        return service.DoDebit(PaxTxnType.SALE_REDEEM, amounts, account, trace, cashier, extData);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }

}
