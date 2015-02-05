package com.hps.integrator.entities.gift;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;

public class HpsGiftCardAlias extends HpsTransaction {
    private HpsGiftCard giftCard;

    public HpsGiftCardAlias(HpsTransactionHeader header) {
        super(header);
    }

    public HpsGiftCard getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(HpsGiftCard giftCard) {
        this.giftCard = giftCard;
    }
}
