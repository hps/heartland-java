package com.hps.integrator.entities.gift;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

public class HpsGiftCardAlias extends HpsTransaction {
    private HpsGiftCard giftCard;

    public HpsGiftCard getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(HpsGiftCard giftCard) {
        this.giftCard = giftCard;
    }

    public HpsGiftCardAlias fromElementTree(ElementTree rsp) {
        Element item = rsp.get("Transaction").firstChild();

        super.fromElementTree(rsp);
        this.setGiftCard(HpsGiftCard.fromElement(item.get("CardData")));
        this.setResponseCode(item.getString("RspCode"));
        this.setResponseText(item.getString("RspText"));

        return this;
    }
}
