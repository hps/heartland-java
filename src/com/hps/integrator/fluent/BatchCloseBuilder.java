package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.PosBatchCloseRspType;
import PosGateway.Exchange.Hps.PosRequestVer10Transaction;
import PosGateway.Exchange.Hps.PosResponse;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

public class BatchCloseBuilder extends GatewayTransactionBuilder<BatchCloseBuilder, HpsBatch> {
    public BatchCloseBuilder(IHpsServicesConfig config) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        transaction.BatchClose = "BatchClose";
    }

    @Override
    protected BatchCloseBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsBatch execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosBatchCloseRspType batchClose = resp.Ver10.Transaction.BatchClose;
        HpsBatch batch = new HpsBatch();

        batch.setId(batchClose.BatchId);
        batch.setSequenceNumber(batchClose.BatchSeqNbr);
        batch.setTotalAmount(batchClose.TotalAmt);
        batch.setTransactionCount(batchClose.TxnCnt);

        return batch;
    }
}
