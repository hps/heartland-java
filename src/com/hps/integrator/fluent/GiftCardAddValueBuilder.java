package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardAddValue;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class GiftCardAddValueBuilder extends GatewayTransactionBuilder<GiftCardAddValueBuilder, HpsGiftCardAddValue> {
    public GiftCardAddValueBuilder(IHpsServicesConfig config, BigDecimal amount, HpsGiftCard giftCard) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardAddValueReqType item = new PosGiftCardAddValueReqType();
        GiftCardAddValueReqBlock1Type block1 = new GiftCardAddValueReqBlock1Type();

        block1.Amt = amount;
        block1.CardData = hydrateGiftCardData(giftCard);

        item.Block1 = block1;
        transaction.GiftCardAddValue = item;
    }

    @Override
    protected GiftCardAddValueBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardAddValue execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardAddValueRspType addValueRsp = resp.Ver10.Transaction.GiftCardAddValue;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(addValueRsp.RspCode), addValueRsp.RspText);

        HpsGiftCardAddValue addValue = new HpsGiftCardAddValue(this.hydrateTransactionHeader(resp.Ver10.Header));
        addValue.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        addValue.setAuthorizationCode(addValueRsp.AuthCode);
        addValue.setBalanceAmount(addValueRsp.BalanceAmt);
        addValue.setPointsBalanceAmount(addValueRsp.PointsBalanceAmt);
        addValue.setRewards(addValueRsp.Rewards);
        addValue.setNotes(addValueRsp.Notes);
        addValue.setResponseCode(Integer.toString(addValueRsp.RspCode));
        addValue.setResponseText(addValueRsp.RspText);

        return addValue;
    }

    public GiftCardAddValueBuilder withCurrency(Enums.currencyType currency) {
        transaction.GiftCardAddValue.Block1.Currency = currency;
        return this;
    }
}
