package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCardReversal;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class GiftCardReverseBuilder extends GatewayTransactionBuilder<GiftCardReverseBuilder, HpsGiftCardReversal> {
    public GiftCardReverseBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardReversalReqType item = new PosGiftCardReversalReqType();
        GiftCardReversalReqBlock1Type block1 = new GiftCardReversalReqBlock1Type();

        block1.Amt = amount;

        item.Block1 = block1;
        transaction.GiftCardReversal = item;
    }

    @Override
    protected GiftCardReverseBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardReversal execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardReversalRspType reversalRsp = resp.Ver10.Transaction.GiftCardReversal;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(reversalRsp.RspCode), reversalRsp.RspText);

        HpsGiftCardReversal reversal = new HpsGiftCardReversal(hydrateTransactionHeader(resp.Ver10.Header));
        reversal.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        reversal.setAuthorizationCode(reversalRsp.AuthCode);
        reversal.setBalanceAmount(reversalRsp.BalanceAmt);
        reversal.setResponseCode(Integer.toString(reversalRsp.RspCode));
        reversal.setResponseText(reversalRsp.RspText);

        return reversal;
    }

    public GiftCardReverseBuilder usingClientTransactionId(long clientTransactionId) {
        transaction.GiftCardReversal.Block1.ClientTxnId = clientTransactionId;
        return this;
    }
}
