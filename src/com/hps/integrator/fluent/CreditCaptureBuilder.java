package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditCaptureBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsTransaction> {
    Integer transactionId;
    BigDecimal amount;
    BigDecimal gratuity;
    String clientTransactionId;
    HpsDirectMarketData directMarketData;

    public CreditCaptureBuilder withTransactionId(Integer value) {
        this.transactionId = value;
        return this;
    }

    public CreditCaptureBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }

    public CreditCaptureBuilder withGratuity(BigDecimal value) {
        this.gratuity = value;
        return this;
    }

    public CreditCaptureBuilder withClientTransactionId(String value) {
        this.clientTransactionId = value;
        return this;
    }

    public CreditCaptureBuilder withDirectMarketData(HpsDirectMarketData value) {
        this.directMarketData = value;
        return this;
    }

    public CreditCaptureBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsTransaction execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditAddToBatch");
        Et.subElement(transaction, "GatewayTxnId").text(transactionId.toString());
        if(amount != null)
            Et.subElement(transaction, "Amt").text(amount.toString());

        if(gratuity != null)
            Et.subElement(transaction, "GratuityAmtInfo").text(gratuity.toString());

        if(directMarketData != null)
            transaction.append(service.hydrateDirectMarketData(directMarketData));

        ElementTree response = service.submitTransaction(transaction);
        HpsTransaction trans = new HpsTransaction().fromElementTree(response);
        trans.setResponseCode("00");
        trans.setResponseText("");
        return trans;
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("transactionIdIsNotNull", "TransactionID is required."));
    }

    private boolean transactionIdIsNotNull(){
        return this.transactionId != null;
    }
}
