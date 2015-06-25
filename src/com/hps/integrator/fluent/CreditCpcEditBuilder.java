package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.credit.HpsCpcData;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

public class CreditCpcEditBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsTransaction> {
    Integer transactionId;
    HpsCpcData cpcData;

    public CreditCpcEditBuilder withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public CreditCpcEditBuilder withCpcData(HpsCpcData cpcData) {
        this.cpcData = cpcData;
        return this;
    }

    public CreditCpcEditBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsTransaction execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditCPCEdit");
        Et.subElement(transaction, "GatewayTxnId").text(transactionId.toString());
        transaction.append(service.hydrateCpcData(cpcData));

        ElementTree response = service.submitTransaction(transaction);
        HpsTransaction trans = new HpsTransaction().fromElementTree(response);
        trans.setResponseCode("00");
        trans.setResponseText("");
        return trans;
    }

    @Override
    protected void setupValidations() throws HpsException {
            this.addValidation(new HpsBuilderValidation("transactionIdIsNotNull", "TransactionId is required."));
            this.addValidation(new HpsBuilderValidation("cpcDataIsNotNull", "CpcData is required."));
    }

    private boolean transactionIdIsNotNull(){
        return this.transactionId != null;
    }

    private boolean cpcDataIsNotNull(){
        return this.cpcData != null;
    }
}
