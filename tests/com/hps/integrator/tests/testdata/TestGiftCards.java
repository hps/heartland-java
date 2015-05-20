package com.hps.integrator.tests.testdata;

import com.hps.integrator.entities.gift.HpsGiftCard;

public class TestGiftCards {
	public static class Manual {
        public static HpsGiftCard validGiftCard() {
            HpsGiftCard card = new HpsGiftCard();
            card.setNumber("5022440000000000098");
            card.setExpMonth(12);
            card.setExpYear(39);

            return card;
        }

        public static HpsGiftCard validGiftCard2() {
            HpsGiftCard card = new HpsGiftCard();
            card.setNumber("5022440000000000007");
            card.setExpMonth(12);
            card.setExpYear(39);

            return card;
        }

        public static HpsGiftCard invalidGiftCard() {
            HpsGiftCard card = new HpsGiftCard();
            card.setNumber("12345");
            card.setExpMonth(12);
            card.setExpYear(39);

            return card;
        }
    }
}
