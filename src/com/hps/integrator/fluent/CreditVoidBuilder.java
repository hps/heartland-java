package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

public class CreditVoidBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsTransaction> {
    Integer transactionId;
    String clientTransactionId;

    public CreditVoidBuilder withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public CreditVoidBuilder withClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public CreditVoidBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsTransaction execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditVoid");
        Et.subElement(transaction, "GatewayTxnId").text(transactionId.toString());

        HpsTransaction response = new HpsTransaction().fromElementTree(service.submitTransaction(transaction, clientTransactionId));
        response.setResponseCode("00");
        response.setResponseText("");
        return response;
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("transactionIdIsNotNull", "TransactionID is required."));
    }

    private boolean transactionIdIsNotNull(){
        return this.transactionId != null;
    }
}
