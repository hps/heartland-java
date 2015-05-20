package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditAddValueUsingBuilder {
    private CreditAddValueBuilder parent;

    public CreditAddValueUsingBuilder(CreditAddValueBuilder parent) {
        this.parent = parent;
    }

    public CreditAddValueBuilder withCard(HpsCreditCard card) {
        parent.transaction.PrePaidAddValue.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditAddValueBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.PrePaidAddValue.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditAddValueBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.PrePaidAddValue.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }
}
