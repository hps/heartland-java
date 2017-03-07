package com.hps.integrator.tests.testdata;

import com.hps.integrator.entities.HpsEncryptionData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.infrastructure.HpsTrackDataMethod;

public class TestCreditCards {
    public static HpsCreditCard validVisa() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setNumber("4012002000060016");
        return card;
    }

    public static String validVisaMUT() {
        return "CT25M708HKe55S4a613i0016";
    }

    public static HpsCreditCard validVisaNoCvv() {
        HpsCreditCard card = validVisa();
        card.setCvv(null);

        return card;
    }

    public static HpsTrackData validVisaTrack() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);

        String value = "%B4012001000000016^VI TEST CREDIT^251200000000000000000000?";
        value += ";4012001000000016=25120000000000000000?";

        trackData.setValue(value);

        return trackData;
    }

    public static HpsTrackData validVisaTrackE3V1() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        trackData.setValue("<E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|m3VpZL7Km3cqqty3xc" +
                "IUJ+hKb1lwraqDBvnqQjZybcl95ywOAmdNTKTua|+++++++/q6S49jif|11;4012001000000016=25120000000000000000?" +
                "|1dxxl54agM6av5oo3myo37RH4wo|+++++++/q6S49jif|00|||/wECAQECAoFGAgEH2wMBTDT6jRZwb3NAc2VjdXJlZXhjaGF" +
                "uZ2UubmV0AemN+EBuiWATgwIPfIVLybYKTlitSTmJYek5yD3nxtfsR0Rfd9UAaMTxDEGYeQDVmlgJICy8r0RE3QK5tgGCmWXF+" +
                "GzMmAyB5h4o+jqbIluSs/MbKURSand61aTi5rRhbQSeBNjlvajCtZULmXXjpn6nuXI4QhN89sYwYm5tFwwA6yBN6fDDx8cH5I7" +
                "MB6wn+AjVzrCCvV347WA2iujV0ZjTFDPT|>");

        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("01");

        trackData.setEncryptionData(encryptionData);

        return trackData;
    }

    public static HpsTrackData validVisaTrackE3V2() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        trackData.setValue("1dxxl54agM6av5oo3myo37RH4wo");

        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("02");
        encryptionData.setEncryptedTrackNumber("2");
        encryptionData.setKtb("/wECAQECAoFGAgEH2wMBTDT6jRZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0AemN+EBuiWATgwIPfIVLybYKTlit" +
                "STmJYek5yD3nxtfsR0Rfd9UAaMTxDEGYeQDVmlgJICy8r0RE3QK5tgGCmWXF+GzMmAyB5h4o+jqbIluSs/MbKURSand61aTi5r" +
                "RhbQSeBNjlvajCtZULmXXjpn6nuXI4QhN89sYwYm5tFwwA6yBN6fDDx8cH5I7MB6wn+AjVzrCCvV347WA2iujV0ZjTFDPT");

        trackData.setEncryptionData(encryptionData);

        return trackData;
    }

    public static HpsTrackData validVisaProximity() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setTrackDataMethod(HpsTrackDataMethod.Proximity);

        String value = "%B4012001000000016^VI TEST CREDIT^251200000000000000000000?";
        value += ";4012001000000016=25120000000000000000?";

        trackData.setValue(value);

        return trackData;
    }

    public static HpsTrackData validVisaProximityE3V1() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setTrackDataMethod(HpsTrackDataMethod.Proximity);
        trackData.setValue("<E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|m3VpZL7Km3cqqty3xc" +
                "IUJ+hKb1lwraqDBvnqQjZybcl95ywOAmdNTKTua|+++++++/q6S49jif|11;4012001000000016=25120000000000000000?" +
                "|1dxxl54agM6av5oo3myo37RH4wo|+++++++/q6S49jif|00|||/wECAQECAoFGAgEH2wMBTDT6jRZwb3NAc2VjdXJlZXhjaGF" +
                "uZ2UubmV0AemN+EBuiWATgwIPfIVLybYKTlitSTmJYek5yD3nxtfsR0Rfd9UAaMTxDEGYeQDVmlgJICy8r0RE3QK5tgGCmWXF+" +
                "GzMmAyB5h4o+jqbIluSs/MbKURSand61aTi5rRhbQSeBNjlvajCtZULmXXjpn6nuXI4QhN89sYwYm5tFwwA6yBN6fDDx8cH5I7" +
                "MB6wn+AjVzrCCvV347WA2iujV0ZjTFDPT|>");

        return trackData;
    }

    public static HpsTrackData validVisaProximityE3V2() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setTrackDataMethod(HpsTrackDataMethod.Proximity);
        trackData.setValue("1dxxl54agM6av5oo3myo37RH4wo");

        HpsEncryptionData encryptionData = new HpsEncryptionData();
        encryptionData.setVersion("02");
        encryptionData.setEncryptedTrackNumber("2");
        encryptionData.setKtb("/wECAQECAoFGAgEH2wMBTDT6jRZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0AemN+EBuiWATgwIPfIVLybYKTlit" +
                "STmJYek5yD3nxtfsR0Rfd9UAaMTxDEGYeQDVmlgJICy8r0RE3QK5tgGCmWXF+GzMmAyB5h4o+jqbIluSs/MbKURSand61aTi5r" +
                "RhbQSeBNjlvajCtZULmXXjpn6nuXI4QhN89sYwYm5tFwwA6yBN6fDDx8cH5I7MB6wn+AjVzrCCvV347WA2iujV0ZjTFDPT");

        trackData.setEncryptionData(encryptionData);

        return trackData;
    }

    public static HpsCreditCard validMasterCard() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setNumber("5473500000000014");

        return card;
    }

    public static String validMasterCardMUT() {
        return "w4nucu08708ScCxlFCPM0014";
    }

    public static HpsCreditCard validMasterCardNoCvv() {
        HpsCreditCard card = validMasterCard();
        card.setCvv(null);

        return card;
    }

    public static HpsCreditCard validDiscover() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setNumber("6011000990156527");

        return card;
    }

    public static String validDiscoverMUT() {
        return "Hl4mgB08bzY3CEIoHfaa6527";
    }

    public static HpsCreditCard validDiscoverNoCvv() {
        HpsCreditCard card = validDiscover();
        card.setCvv(null);

        return card;
    }

    public static HpsCreditCard validAmex() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("1234");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setNumber("372700699251018");

        return card;
    }

    public static String validAmexMUT() {
        return "DhIQTo08cwS7f5NG5dHC1018";
    }

    public static HpsCreditCard validAmexNoCvv() {
        HpsCreditCard card = validAmex();
        card.setCvv(null);

        return card;
    }

    public static HpsCreditCard validJcb() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setNumber("3566007770007321");

        return card;
    }

    public static HpsCreditCard validJcbNoCvv() {
        HpsCreditCard card = validJcb();
        card.setCvv(null);

        return card;
    }

    public static HpsCreditCard invalidCard() {
        HpsCreditCard card = new HpsCreditCard();
        card.setCvv("123");
        card.setExpMonth(12);
        card.setExpYear(2025);
        card.setNumber("12345");

        return card;
    }

    public static String invalidMUT() {
        return "Hl4mgB08bzY3CEIoHfsdfsdfdsfaa6527";
    }

    public static String nullMUT() {
        return null;
    }
}
