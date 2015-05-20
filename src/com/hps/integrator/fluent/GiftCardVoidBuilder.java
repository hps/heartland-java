package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCardVoid;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

public class GiftCardVoidBuilder extends GatewayTransactionBuilder<GiftCardVoidBuilder, HpsGiftCardVoid> {
    public GiftCardVoidBuilder(IHpsServicesConfig config, int transactionId) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardVoidReqType item = new PosGiftCardVoidReqType();
        GiftCardVoidReqBlock1Type block1 = new GiftCardVoidReqBlock1Type();

        block1.GatewayTxnId = transactionId;

        item.Block1 = block1;
        transaction.GiftCardVoid = item;
    }

    @Override
    protected GiftCardVoidBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardVoid execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardVoidRspType voidRsp = resp.Ver10.Transaction.GiftCardVoid;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(voidRsp.RspCode), voidRsp.RspText);

        HpsGiftCardVoid voidResult = new HpsGiftCardVoid(hydrateTransactionHeader(resp.Ver10.Header));
        voidResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        voidResult.setAuthorizationCode(voidRsp.AuthCode);
        voidResult.setBalanceAmount(voidRsp.BalanceAmt);
        voidResult.setPointsBalanceAmount(voidRsp.PointsBalanceAmt);
        voidResult.setNotes(voidRsp.Notes);
        voidResult.setResponseCode(Integer.toString(voidRsp.RspCode));
        voidResult.setResponseText(voidRsp.RspText);

        return voidResult;
    }
}
