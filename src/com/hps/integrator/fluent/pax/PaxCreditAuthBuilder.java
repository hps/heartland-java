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

public class PaxCreditAuthBuilder extends HpsBuilderAbstract<PaxDevice, CreditResponse> {
    private Integer referenceNumber;
    private BigDecimal amount;
    private HpsCreditCard card;
    private String token;
    private HpsAddress address;
    private boolean requestMultiUseToken = false;
    private HpsTransactionDetails details;
    private boolean allowDuplicates = false;
    private BigDecimal gratuity;
    private Integer transactionId;
    private String authCode;

    public PaxCreditAuthBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }
    public PaxCreditAuthBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public PaxCreditAuthBuilder withCard(HpsCreditCard card) {
        this.card = card;
        return this;
    }
    public PaxCreditAuthBuilder withToken(String token) {
        this.token = token;
        return this;
    }
    public PaxCreditAuthBuilder withAddress(HpsAddress address) {
        this.address = address;
        return this;
    }
    public PaxCreditAuthBuilder withRequestMultiUseToken(boolean requestMultiUseToken) {
        this.requestMultiUseToken = requestMultiUseToken;
        return this;
    }
    public PaxCreditAuthBuilder withDetails(HpsTransactionDetails details) {
        this.details = details;
        return this;
    }
    public PaxCreditAuthBuilder withAllowDuplicates(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
        return this;
    }
    public PaxCreditAuthBuilder withGratuity(BigDecimal gratuity) {
        this.gratuity = gratuity;
        return this;
    }
    public PaxCreditAuthBuilder withAuthCode(String value) {
        this.authCode = value;
        return this;
    }
    public PaxCreditAuthBuilder withTransactionId(int value) {
        this.transactionId = value;
        return this;
    }

    public PaxCreditAuthBuilder(PaxDevice device) {
        super(device);
    }

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
        if (!HpsStringUtils.isNullOrEmpty(authCode))
            trace.setAuthCode(authCode);


        CashierSubGroup cashier = new CashierSubGroup();
        CommercialRequest commercial = new CommercialRequest();
        EcomSubGroup ecom = new EcomSubGroup();

        // Additional Info sub group
        ExtDataSubGroup additionalInfo = new ExtDataSubGroup();
        if (requestMultiUseToken)
            additionalInfo.set(PaxExtData.TOKEN_REQUEST, "1");
        if (transactionId != null)
            additionalInfo.set(PaxExtData.HOST_REFERENCE_NUMBER, transactionId.toString());
        if (token != null)
            additionalInfo.set(PaxExtData.TOKEN, token);

        return service.doCredit(PaxTxnType.AUTH, amounts, account, trace, avs, cashier, commercial, ecom, additionalInfo);
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
        addValidation(new HpsBuilderValidation("authCodeIsNotNull", "AuthCode is required when using a transaction id."));
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

    private boolean authCodeIsNotNull() {
        if(transactionId != null) {
            return !HpsStringUtils.isNullOrEmpty(authCode);
        }
        return true;
    }
}
