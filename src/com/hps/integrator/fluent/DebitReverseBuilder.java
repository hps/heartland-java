package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

import java.math.BigDecimal;

public class DebitReverseBuilder extends GatewayTransactionBuilder<DebitReverseBuilder, HpsTransaction> {
    public DebitReverseBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosDebitReversalReqType item = new PosDebitReversalReqType();
        DebitReversalReqBlock1Type block1 = new DebitReversalReqBlock1Type();

        block1.Amt = amount;

        item.Block1 = block1;
        transaction.DebitReversal = item;
    }

    @Override
    protected DebitReverseBuilder getBuilder() {
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

    public DebitReverseBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.DebitReversal.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public DebitReverseBuilder withAuthorizedAmount(BigDecimal authorizedAmount) {
        transaction.DebitReversal.Block1.AuthAmt = authorizedAmount;
        return this;
    }

    public DebitReverseBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.DebitReversal.Block1.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }
}
