package com.hps.integrator.fluent;

public class CreditEditUsingBuilder {
    private CreditEditBuilder parent;

    public CreditEditUsingBuilder(CreditEditBuilder parent) {
        this.parent = parent;
    }

    public CreditEditBuilder withTransactionId(int transactionId) {
        parent.transaction.CreditTxnEdit.GatewayTxnId = transactionId;
        return parent;
    }
}
