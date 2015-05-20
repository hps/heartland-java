package com.hps.integrator.fluent;

public class CheckVoidUsingBuilder {
    private CheckVoidBuilder parent;

    public CheckVoidUsingBuilder(CheckVoidBuilder parent) {
        this.parent = parent;
    }

    public CheckVoidBuilder withTransactionId(int transactionId) {
        parent.transaction.CheckVoid.Block1.GatewayTxnId = transactionId;
        return parent;
    }

    public CheckVoidBuilder withClientTransactionId(long clientTransactionId) {
        parent.transaction.CheckVoid.Block1.ClientTxnId = clientTransactionId;
        return parent;
    }
}
