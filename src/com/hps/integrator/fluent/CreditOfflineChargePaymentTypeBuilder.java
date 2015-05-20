package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditOfflineChargePaymentTypeBuilder {
    private CreditOfflineChargeBuilder parent;

    public CreditOfflineChargePaymentTypeBuilder(CreditOfflineChargeBuilder parent) {
        this.parent = parent;
    }

    public CreditOfflineChargeBuilder withCard(HpsCreditCard card) {
        parent.transaction.CreditOfflineSale.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditOfflineChargeBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.CreditOfflineSale.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditOfflineChargeBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.CreditOfflineSale.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }
}
