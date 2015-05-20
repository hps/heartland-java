package com.hps.integrator.fluent;

import com.hps.integrator.entities.gift.HpsGiftCard;

public class GiftCardReverseUsingBuilder {
    private GiftCardReverseBuilder parent;

    public GiftCardReverseUsingBuilder(GiftCardReverseBuilder parent) {
        this.parent = parent;
    }

    public GiftCardReverseBuilder usingTransactionId(int transactionId) {
        parent.transaction.GiftCardReversal.Block1.GatewayTxnId = transactionId;
        return parent;
    }

    public GiftCardReverseBuilder usingGiftCard(HpsGiftCard giftCard) {
        parent.transaction.GiftCardReversal.Block1.CardData = GatewayTransactionBuilder.hydrateGiftCardData(giftCard);
        return parent;
    }
}
