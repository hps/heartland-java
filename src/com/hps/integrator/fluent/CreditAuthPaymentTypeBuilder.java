package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditAuthPaymentTypeBuilder {
    private CreditAuthBuilder parent;

    public CreditAuthPaymentTypeBuilder(CreditAuthBuilder parent) {
        this.parent = parent;
    }

    public CreditAuthBuilder withCard(HpsCreditCard card) {
        parent.transaction.CreditAuth.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditAuthBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.CreditAuth.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditAuthBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.CreditAuth.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }
}
