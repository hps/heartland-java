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

public class PaxCreditSaleBuilder extends HpsBuilderAbstract<PaxDevice, CreditResponse> {
    BigDecimal amount;
    HpsCreditCard card;
    String token;
    HpsAddress address;
    boolean requestMultiUseToken = false;
    HpsTransactionDetails details;
    boolean allowDuplicates = false;
    BigDecimal gratuity;
    Integer referenceNumber;

    public PaxCreditSaleBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    public PaxCreditSaleBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public PaxCreditSaleBuilder withCard(HpsCreditCard card) {
        this.card = card;
        return this;
    }
    public PaxCreditSaleBuilder withToken(String token) {
        this.token = token;
        return this;
    }
    public PaxCreditSaleBuilder withAddress(HpsAddress address) {
        this.address = address;
        return this;
    }
    public PaxCreditSaleBuilder withRequestMultiUseToken(boolean requestMultiUseToken) {
        this.requestMultiUseToken = requestMultiUseToken;
        return this;
    }
    public PaxCreditSaleBuilder withDetails(HpsTransactionDetails details) {
        this.details = details;
        return this;
    }
    public PaxCreditSaleBuilder withAllowDuplicates(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
        return this;
    }
    public PaxCreditSaleBuilder withGratuity(BigDecimal gratuity) {
        this.gratuity = gratuity;
        return this;
    }

    public PaxCreditSaleBuilder(PaxDevice device) { super(device); }

    @Override
    public CreditResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();
        amounts.setTransactionAmount(HpsStringUtils.toNumeric(amount));
        amounts.setTipAmount(HpsStringUtils.toNumeric(gratuity));

        AccountRequest account = new AccountRequest();
        if (card != null) {
            account.setAccountNumber(card.getNumber());
            account.setExpd(String.format("%s%s", card.getExpMonth(), card.getExpYear()));
            account.setCvvCode(card.getCvv());
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
        if (details != null) {
            trace.setInvoiceNumber(details.getInvoiceNumber());
        }

        CashierSubGroup cashier = new CashierSubGroup();
        CommercialRequest commercial = new CommercialRequest();
        EcomSubGroup ecom = new EcomSubGroup();

        // Additional Info sub group
        ExtDataSubGroup extData = new ExtDataSubGroup();
        if (requestMultiUseToken)
            extData.set(PaxExtData.TOKEN_REQUEST, "1");

        if (token != null)
            extData.set(PaxExtData.TOKEN, token);

        return service.doCredit(PaxTxnType.SALE_REDEEM, amounts, account, trace, avs, cashier, commercial, ecom, extData);
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
    }

    private boolean amountIsNotNull() {
        return amount != null;
    }

    private boolean onlyOnePaymentMethod() {
        int count = 0;
        if (card != null) count++;
        if (token != null) count++;

        return count <= 1;
    }
}
