package com.hps.integrator.fluent;

public class DebitReverseUsingBuilder {
    private DebitReverseBuilder parent;

    public DebitReverseUsingBuilder(DebitReverseBuilder parent) {
        this.parent = parent;
    }

    public DebitReverseBuilder withTransactionId(int transactionId) {
        parent.transaction.DebitReversal.Block1.GatewayTxnId = transactionId;
        return parent;
    }

    public DebitReverseBuilder withTrackData(String trackData) {
        parent.transaction.DebitReversal.Block1.TrackData = trackData;
        return parent;
    }
}
