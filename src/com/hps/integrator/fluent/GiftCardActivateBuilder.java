package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardActivate;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class GiftCardActivateBuilder extends GatewayTransactionBuilder<GiftCardActivateBuilder, HpsGiftCardActivate> {
    public GiftCardActivateBuilder(IHpsServicesConfig config, BigDecimal amount, HpsGiftCard giftCard) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardActivateReqType item = new PosGiftCardActivateReqType();
        GiftCardActivateReqBlock1Type block1 = new GiftCardActivateReqBlock1Type();

        block1.Amt = amount;
        block1.CardData = hydrateGiftCardData(giftCard);

        item.Block1 = block1;
        transaction.GiftCardActivate = item;
    }

    @Override
    protected GiftCardActivateBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardActivate execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardActivateRspType activationRsp = resp.Ver10.Transaction.GiftCardActivate;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(activationRsp.RspCode), activationRsp.RspText);

        HpsGiftCardActivate activation = new HpsGiftCardActivate(hydrateTransactionHeader(resp.Ver10.Header));
        activation.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        activation.setAuthorizationCode(activationRsp.AuthCode);
        activation.setBalanceAmount(activationRsp.BalanceAmt);
        activation.setPointsBalanceAmount(activationRsp.PointsBalanceAmt);
        activation.setRewards(activationRsp.Rewards);
        activation.setNotes(activationRsp.Notes);
        activation.setResponseCode(Integer.toString(activationRsp.RspCode));
        activation.setResponseText(activationRsp.RspText);

        return activation;
    }

    public GiftCardActivateBuilder withCurrency(Enums.currencyType currency) {
        transaction.GiftCardActivate.Block1.Currency = currency;
        return this;
    }
}
