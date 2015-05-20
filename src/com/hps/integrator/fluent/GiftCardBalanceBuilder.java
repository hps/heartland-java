package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardBalance;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

public class GiftCardBalanceBuilder extends GatewayTransactionBuilder<GiftCardBalanceBuilder, HpsGiftCardBalance> {
    public GiftCardBalanceBuilder(IHpsServicesConfig config, HpsGiftCard giftCard) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardBalanceReqType item = new PosGiftCardBalanceReqType();
        GiftCardBalanceReqBlock1Type block1 = new GiftCardBalanceReqBlock1Type();

        block1.CardData = hydrateGiftCardData(giftCard);

        item.Block1 = block1;
        transaction.GiftCardBalance = item;
    }

    @Override
    protected GiftCardBalanceBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardBalance execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardBalanceRspType balanceRsp = resp.Ver10.Transaction.GiftCardBalance;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(balanceRsp.RspCode), balanceRsp.RspText);

        HpsGiftCardBalance balanceResult = new HpsGiftCardBalance(hydrateTransactionHeader(resp.Ver10.Header));
        balanceResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        balanceResult.setAuthorizationCode(balanceRsp.AuthCode);
        balanceResult.setBalanceAmount(balanceRsp.BalanceAmt);
        balanceResult.setPointsBalanceAmount(balanceRsp.PointsBalanceAmt);
        balanceResult.setRewards(balanceRsp.Rewards);
        balanceResult.setNotes(balanceRsp.Notes);
        balanceResult.setResponseCode(Integer.toString(balanceRsp.RspCode));
        balanceResult.setResponseText(balanceRsp.RspText);

        return balanceResult;
    }
}
