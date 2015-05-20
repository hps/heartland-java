package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardReplace;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

public class GiftCardReplaceBuilder extends GatewayTransactionBuilder<GiftCardReplaceBuilder, HpsGiftCardReplace> {
    public GiftCardReplaceBuilder(IHpsServicesConfig config, HpsGiftCard oldGiftCard, HpsGiftCard newGiftCard) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardReplaceReqType item = new PosGiftCardReplaceReqType();
        GiftCardReplaceReqBlock1Type block1 = new GiftCardReplaceReqBlock1Type();

        block1.OldCardData = hydrateGiftCardData(oldGiftCard);
        block1.NewCardData = hydrateGiftCardData(newGiftCard);

        item.Block1 = block1;
        transaction.GiftCardReplace = item;
    }

    @Override
    protected GiftCardReplaceBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardReplace execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardReplaceRspType replaceRsp = resp.Ver10.Transaction.GiftCardReplace;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(replaceRsp.RspCode), replaceRsp.RspText);

        HpsGiftCardReplace replaceResult = new HpsGiftCardReplace(hydrateTransactionHeader(resp.Ver10.Header));
        replaceResult.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        replaceResult.setAuthorizationCode(replaceRsp.AuthCode);
        replaceResult.setBalanceAmount(replaceRsp.BalanceAmt);
        replaceResult.setPointsBalanceAmount(replaceRsp.PointsBalanceAmt);
        replaceResult.setResponseCode(Integer.toString(replaceRsp.RspCode));
        replaceResult.setResponseText(replaceRsp.RspText);

        return replaceResult;
    }
}
