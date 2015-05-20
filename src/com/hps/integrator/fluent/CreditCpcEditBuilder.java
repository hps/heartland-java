package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.credit.HpsCpcData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

public class CreditCpcEditBuilder extends GatewayTransactionBuilder<CreditCpcEditBuilder, HpsTransaction> {
    public CreditCpcEditBuilder(IHpsServicesConfig config, int transactionId) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditCPCEditReqType item = new PosCreditCPCEditReqType();
        item.GatewayTxnId = transactionId;

        transaction.CreditCPCEdit = item;
    }

    @Override
    protected CreditCpcEditBuilder getBuilder() {
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

    public CreditCpcEditBuilder withCpcData(HpsCpcData cpcData) {
        transaction.CreditCPCEdit.CPCData = hydrateCpcData(cpcData);
        return this;
    }
}
