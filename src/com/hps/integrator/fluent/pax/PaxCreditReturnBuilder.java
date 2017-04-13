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
import com.hps.integrator.terminals.pax.responses.CreditResponse;
import com.hps.integrator.terminals.pax.subgroups.*;

import java.math.BigDecimal;

public class PaxCreditReturnBuilder extends HpsBuilderAbstract<PaxDevice, CreditResponse> {
    Integer referenceNumber;
    BigDecimal amount;
    HpsCreditCard card;
    String token;
    Integer transactionId;
    HpsAddress address;
    HpsTransactionDetails details;
    boolean allowDuplicates = false;

    public PaxCreditReturnBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    public PaxCreditReturnBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public PaxCreditReturnBuilder withCard(HpsCreditCard value) {
        this.card = value;
        return this;
    }
    public PaxCreditReturnBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public PaxCreditReturnBuilder withTransactionId(int value) {
        this.transactionId = value;
        return this;
    }
    public PaxCreditReturnBuilder withAddress(HpsAddress value) {
        this.address = value;
        return this;
    }
    public PaxCreditReturnBuilder withDetails(HpsTransactionDetails value) {
        this.details = value;
        return this;
    }
    public PaxCreditReturnBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }

    public PaxCreditReturnBuilder(PaxDevice device) { super(device); }

    @Override
    public CreditResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();
        amounts.setTransactionAmount(HpsStringUtils.toNumeric(amount));

        AccountRequest account = new AccountRequest();
        if (card != null) {
            account.setAccountNumber(card.getNumber());
            account.setExpd(String.format("%s%s", card.getExpMonth(), card.getExpYear()));
        }
        if (allowDuplicates) account.setDupOverrideFlag("1");

        // Avs Sub Group
        AvsRequest avs = new AvsRequest();
        if (address != null) {
            avs.setZipCode(address.getZip());
            avs.setAddress(address.getAddress());
        }

        // Trace Sub Group
        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());
        if (details != null)
            trace.setInvoiceNumber(details.getInvoiceNumber());

        CashierSubGroup cashier = new CashierSubGroup();
        CommercialRequest commercial = new CommercialRequest();
        EcomSubGroup ecom = new EcomSubGroup();

        // Additional Info sub group
        ExtDataSubGroup additionalInfo = new ExtDataSubGroup();
        if (token != null)
            additionalInfo.set(PaxExtData.TOKEN, token);
        if (transactionId != null)
            additionalInfo.set(PaxExtData.HOST_REFERENCE_NUMBER, transactionId.toString());

        return service.doCredit(PaxTxnType.RETURN, amounts, account, trace, avs, cashier, commercial, ecom, additionalInfo);
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation ("amountIsNotNull", "Amount is required."));
        addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
    }

    private boolean amountIsNotNull() {
        return amount != null;
    }

    private boolean onlyOnePaymentMethod() {
        int count = 0;
        if (card != null) count++;
        if (transactionId != null) count++;
        if (token != null) count++;

        return count <= 1;
    }
}
