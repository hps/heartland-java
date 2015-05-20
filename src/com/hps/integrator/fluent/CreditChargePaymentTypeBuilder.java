package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditChargePaymentTypeBuilder {
    private CreditChargeBuilder parent;

    public CreditChargePaymentTypeBuilder(CreditChargeBuilder parent) {
        this.parent = parent;
    }

    public CreditChargeBuilder withCard(HpsCreditCard card) {
        parent.transaction.CreditSale.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditChargeBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.CreditSale.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditChargeBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.CreditSale.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }
}
