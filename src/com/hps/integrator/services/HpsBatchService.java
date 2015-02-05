package com.hps.integrator.services;

import PosGateway.Exchange.Hps.PosBatchCloseRspType;
import PosGateway.Exchange.Hps.PosRequestVer10Transaction;
import PosGateway.Exchange.Hps.PosResponse;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

public class HpsBatchService extends HpsService {

    public HpsBatchService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }

    public HpsBatch closeBatch() throws HpsException {
        PosRequestVer10Transaction transaction = new PosRequestVer10Transaction();
        transaction.BatchClose = "BatchClose";

        this.transaction = transaction;
        PosResponse resp = this.doTransaction();
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