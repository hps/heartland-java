package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditVerifyUsingBuilder {
    private CreditVerifyBuilder parent;

    public CreditVerifyUsingBuilder(CreditVerifyBuilder parent) {
        this.parent = parent;
    }

    public CreditVerifyBuilder withCard(HpsCreditCard card) {
        parent.transaction.CreditAccountVerify.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditVerifyBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.CreditAccountVerify.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditVerifyBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.CreditAccountVerify.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }
}
