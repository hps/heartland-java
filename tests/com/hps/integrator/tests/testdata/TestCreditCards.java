package com.hps.integrator.tests.testdata;

import com.hps.integrator.entities.credit.HpsCreditCard;

public class TestCreditCards {
    public static HpsCreditCard validVisa() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2015);
        card.setNumber("4012002000060016");
        return card;
    }

    public static HpsCreditCard validMasterCard() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2015);
        card.setNumber("5473500000000014");
        return card;
    }

    public static HpsCreditCard validDiscover() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2015);
        card.setNumber("6011000990156527");
        return card;
    }

    public static HpsCreditCard validAmex() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("1234");
        card.setExpMonth(12);
        card.setExpYear(2015);
        card.setNumber("372700699251018");
        return card;
    }

    public static HpsCreditCard validJcb() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2015);
        card.setNumber("3566007770007321");
        return card;
    }

    public static HpsCreditCard invalidCard() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2015);
        card.setNumber("12345");
        return card;
    }
}
