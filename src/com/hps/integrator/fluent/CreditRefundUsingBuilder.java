package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditRefundUsingBuilder {
    private CreditRefundBuilder parent;

    public CreditRefundUsingBuilder(CreditRefundBuilder parent) {
        this.parent = parent;
    }

    public CreditRefundBuilder withTransactionId(int transactionId) {
        parent.transaction.CreditReturn.Block1.GatewayTxnId = transactionId;
        return parent;
    }

    public CreditRefundBuilder withCard(HpsCreditCard card) {
        parent.transaction.CreditReturn.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditRefundBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.CreditReturn.Block1.CardData.TokenData = tokenData;

        return parent;
    }
}
