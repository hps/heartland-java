package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditReverseUsingBuilder {
    private CreditReverseBuilder parent;

    public CreditReverseUsingBuilder(CreditReverseBuilder parent) {
        this.parent = parent;
    }

    public CreditReverseBuilder withTransactionId(int transactionId) {
        parent.transaction.CreditReversal.Block1.GatewayTxnId = transactionId;
        return parent;
    }

    public CreditReverseBuilder withCard(HpsCreditCard card) {
        parent.transaction.CreditReversal.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditReverseBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.CreditReversal.Block1.CardData.TokenData = tokenData;

        return parent;
    }
}
