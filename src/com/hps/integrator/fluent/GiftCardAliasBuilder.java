package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardAlias;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

public class GiftCardAliasBuilder extends GatewayTransactionBuilder<GiftCardAliasBuilder, HpsGiftCardAlias> {
    public GiftCardAliasBuilder(IHpsServicesConfig config, Enums.GiftCardAliasReqBlock1TypeAction action, String alias) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardAliasReqType item = new PosGiftCardAliasReqType();
        GiftCardAliasReqBlock1Type block1 = new GiftCardAliasReqBlock1Type();

        block1.Action = action;
        block1.Alias = alias;

        item.Block1 = block1;
        transaction.GiftCardAlias = item;
    }

    @Override
    protected GiftCardAliasBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardAlias execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardAliasRspType aliasRsp = resp.Ver10.Transaction.GiftCardAlias;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(aliasRsp.RspCode), aliasRsp.RspText);
        HpsGiftCardAlias aliasResult = new HpsGiftCardAlias(hydrateTransactionHeader(resp.Ver10.Header));

        HpsGiftCard responseCard = new HpsGiftCard();
        responseCard.setNumber(aliasRsp.CardData.CardNbr);

        aliasResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        aliasResult.setGiftCard(responseCard);
        aliasResult.setResponseCode(Integer.toString(aliasRsp.RspCode));
        aliasResult.setResponseText(aliasRsp.RspText);

        return aliasResult;
    }

    public GiftCardAliasBuilder withCard(HpsGiftCard giftCard) {
        transaction.GiftCardAlias.Block1.CardData = new GiftCardDataType();
        transaction.GiftCardAlias.Block1.CardData = hydrateGiftCardData(giftCard);
        return this;
    }
}
