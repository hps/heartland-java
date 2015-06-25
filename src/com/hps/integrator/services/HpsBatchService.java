package com.hps.integrator.services;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

import java.math.BigDecimal;

public class HpsBatchService extends HpsSoapGatewayService {

    public HpsBatchService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }

    public HpsBatch closeBatch() throws HpsException {
        Element transaction = Et.element("BatchClose");

        ElementTree rsp = this.doTransaction(transaction);
        HpsGatewayResponseValidation.checkGatewayResponse(rsp, transaction.tag());

        // Process the response
        Element batchClose = rsp.get("BatchClose");
        HpsBatch batch = new HpsBatch();

        if(batchClose.has("BatchId"))
            batch.setId(batchClose.getInt("BatchId"));
        if(batchClose.has("BatchSeqNbr"))
            batch.setSequenceNumber(batchClose.getInt("BatchSeqNbr"));
        if(batchClose.has("TotalAmount"))
            batch.setTotalAmount(new BigDecimal(batchClose.getString("TotalAmount")));
        if(batchClose.has("TxnCnt"))
            batch.setTransactionCount(batchClose.getInt("TxnCnt"));

        return batch;
    }
}