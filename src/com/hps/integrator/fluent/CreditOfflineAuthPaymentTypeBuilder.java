package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditOfflineAuthPaymentTypeBuilder {
    private CreditOfflineAuthBuilder parent;

    public CreditOfflineAuthPaymentTypeBuilder(CreditOfflineAuthBuilder parent) {
        this.parent = parent;
    }

    public CreditOfflineAuthBuilder withCard(HpsCreditCard card) {
        parent.transaction.CreditOfflineAuth.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditOfflineAuthBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.CreditOfflineAuth.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditOfflineAuthBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.CreditOfflineAuth.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }
}
