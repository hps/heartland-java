package com.hps.integrator.fluent;

import com.hps.integrator.entities.credit.HpsReportTransactionDetails;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

public class CreditGetBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsReportTransactionDetails> {
    Integer transactionId;

    public CreditGetBuilder withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public CreditGetBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsReportTransactionDetails execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("ReportTxnDetail");
        Et.subElement(transaction, "TxnId").text(transactionId.toString());

        ElementTree response = service.submitTransaction(transaction);
        return new HpsReportTransactionDetails().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("transactionIdIsNotNull", "TransactionId is required."));
    }

    private boolean transactionIdIsNotNull(){
        return this.transactionId != null;
    }
}
