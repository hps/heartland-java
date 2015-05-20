package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardDeactivate;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

public class GiftCardDeactivateBuilder extends GatewayTransactionBuilder<GiftCardDeactivateBuilder, HpsGiftCardDeactivate> {
    public GiftCardDeactivateBuilder(IHpsServicesConfig config, HpsGiftCard giftCard) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardDeactivateReqType item = new PosGiftCardDeactivateReqType();
        GiftCardDeactivateReqBlock1Type block1 = new GiftCardDeactivateReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);

        item.Block1 = block1;
        transaction.GiftCardDeactivate = item;
    }

    @Override
    protected GiftCardDeactivateBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardDeactivate execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardDeactivateRspType deactivateRsp = resp.Ver10.Transaction.GiftCardDeactivate;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(deactivateRsp.RspCode), deactivateRsp.RspText);

        HpsGiftCardDeactivate deactivateResult = new HpsGiftCardDeactivate(this.hydrateTransactionHeader(resp.Ver10.Header));
        deactivateResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        deactivateResult.setAuthorizationCode(deactivateRsp.AuthCode);
        deactivateResult.setPointsBalanceAmount(deactivateRsp.PointsBalanceAmt);
        deactivateResult.setResponseCode(Integer.toString(deactivateRsp.RspCode));
        deactivateResult.setResponseText(deactivateRsp.RspText);

        return deactivateResult;
    }
}
