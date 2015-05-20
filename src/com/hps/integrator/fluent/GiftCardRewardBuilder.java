package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardReward;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class GiftCardRewardBuilder extends GatewayTransactionBuilder<GiftCardRewardBuilder, HpsGiftCardReward> {
    public GiftCardRewardBuilder(IHpsServicesConfig config, BigDecimal amount, HpsGiftCard giftCard) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardRewardReqType item = new PosGiftCardRewardReqType();
        GiftCardRewardReqBlock1Type block1 = new GiftCardRewardReqBlock1Type();

        block1.Amt = amount;
        block1.CardData = hydrateGiftCardData(giftCard);

        item.Block1 = block1;
        transaction.GiftCardReward = item;
    }

    @Override
    protected GiftCardRewardBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardReward execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardRewardRspType rewardRsp = resp.Ver10.Transaction.GiftCardReward;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(rewardRsp.RspCode), rewardRsp.RspText);

        HpsGiftCardReward reward = new HpsGiftCardReward(hydrateTransactionHeader(resp.Ver10.Header));
        reward.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        reward.setAuthorizationCode(rewardRsp.AuthCode);
        reward.setBalanceAmount(rewardRsp.BalanceAmt);
        reward.setPointsBalanceAmount(rewardRsp.PointsBalanceAmt);
        reward.setResponseCode(Integer.toString(rewardRsp.RspCode));
        reward.setResponseText(rewardRsp.RspText);

        return reward;
    }

    public GiftCardRewardBuilder withCurrency(Enums.currencyType currency) {
        transaction.GiftCardReward.Block1.Currency = currency;
        return this;
    }

    public GiftCardRewardBuilder withGratuity(BigDecimal gratuity) {
        transaction.GiftCardReward.Block1.GratuityAmtInfo = gratuity;
        return this;
    }

    public GiftCardRewardBuilder withTax(BigDecimal tax) {
        transaction.GiftCardReward.Block1.TaxAmtInfo = tax;
        return this;
    }
}
