package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.PosCreditVoidReqType;
import PosGateway.Exchange.Hps.PosRequestVer10Transaction;
import PosGateway.Exchange.Hps.PosResponse;
import PosGateway.Exchange.Hps.PosResponseVer10Header;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

public class CreditVoidBuilder extends GatewayTransactionBuilder<CreditVoidBuilder, HpsTransaction> {
    public CreditVoidBuilder(IHpsServicesConfig config, int transactionId) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditVoidReqType item = new PosCreditVoidReqType();
        item.GatewayTxnId = transactionId;

        transaction.CreditVoid = item;
    }

    @Override
    protected CreditVoidBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsTransaction execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;

        HpsTransaction result = new HpsTransaction(hydrateTransactionHeader(header));
        result.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        result.setResponseCode("00");
        result.setResponseText("");

        return result;
    }
}
