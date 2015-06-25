package com.hps.integrator.fluent;

import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCheckService;

public class CheckVoidBuilder extends HpsBuilderAbstract<HpsFluentCheckService, HpsCheckResponse> {
    private Integer transactionId;
    private String clientTransactionId;

    public CheckVoidBuilder withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }
    public CheckVoidBuilder withClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public CheckVoidBuilder(HpsFluentCheckService service) {
        super(service);
    }

    @Override
    public HpsCheckResponse execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CheckVoid");
        Element block1 = Et.subElement(transaction, "Block1");

        if(this.transactionId != null)
            Et.subElement(block1, "GatewayTxnId").text(this.transactionId.toString());
        else if(this.clientTransactionId != null)
            Et.subElement(block1, "ClientTxnId").text(this.clientTransactionId);

        return this.service.submitTransaction(transaction);
    }

    @Override
    protected void setupValidations() throws HpsException {
        addValidation(new HpsBuilderValidation("onlyOneTransactionId", "You may only use one transaction id."));
    }

    private boolean onlyOneTransactionId() {
        int count = 0;
        if(this.transactionId != null) count++;
        if(this.clientTransactionId != null) count++;
        return count == 1;
    }
}
