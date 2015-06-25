package com.hps.integrator.tests.testdata;

import com.hps.integrator.entities.gift.HpsGiftCard;

public class TestGiftCards {
	public static class Manual {
        public static HpsGiftCard validGiftCard() {
            HpsGiftCard card = new HpsGiftCard();
            card.setCardNumber("5022440000000000098");

            return card;
        }

        public static HpsGiftCard validGiftCard2() {
            HpsGiftCard card = new HpsGiftCard();
            card.setCardNumber("5022440000000000007");

            return card;
        }
    }
}
