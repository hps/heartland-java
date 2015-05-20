package com.hps.integrator.tests.certification;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.serialization.HpsToken;
import com.hps.integrator.infrastructure.HpsTrackDataMethod;
import com.hps.integrator.services.HpsTokenService;

import java.io.IOException;
import java.util.HashMap;

public class TestData {
    private String _publicApiKey;
    private static HpsTokenService _tokenService;
    private static HashMap<String, HpsToken> __tokens = new HashMap<String, HpsToken>();

    public TestData(String publicApiKey) {
        _publicApiKey = publicApiKey;
        _tokenService = new HpsTokenService(_publicApiKey);
    }

    private static HpsCreditCard card() {
        HpsCreditCard card = new HpsCreditCard();
        card.setExpMonth(12);
        card.setExpYear(2015);

        return card;
    }

    public static HpsToken tokenizeCard(HpsCreditCard card) throws IOException {
        if(__tokens.containsKey(card.getNumber())) {
            return __tokens.get(card.getNumber());
        }

        HpsToken token = _tokenService.getToken(card);
        __tokens.put(card.getNumber(), token);

        return token;
    }

    // CREDIT CARDS

    public static HpsCreditCard visaCard(boolean cvv) {
        HpsCreditCard card = card();
        card.setNumber("4012002000060016");
        if(cvv) { card.setCvv("123"); }

        return card;
    }

    public static HpsCreditCard masterCard(boolean cvv) {
        HpsCreditCard card = card();
        card.setNumber("5473500000000014");
        if(cvv) { card.setCvv("123"); }

        return card;
    }

    public static HpsCreditCard discoverCard(boolean cvv) {
        HpsCreditCard card = card();
        card.setNumber("6011000990156527");
        if(cvv) { card.setCvv("123"); }

        return card;
    }
    public static HpsCreditCard amexCard(boolean cvv) {
        HpsCreditCard card = card();
        card.setNumber("372700699251018");
        if(cvv) { card.setCvv("1234"); }

        return card;
    }

    public static HpsCreditCard jcbCard(boolean cvv) {
        HpsCreditCard card = card();
        card.setNumber("3566007770007321");
        if(cvv) { card.setCvv("1234"); }

        return card;
    }

    // CARD MULTI_USE TOKENS

    public enum Industry { ecommerce, retail }

    public static String visaMultiUseToken(Industry industry) {
        switch(industry) {
            case ecommerce: return "Wf4LD1084WQUbf1S6Kcd0016";
            case retail: return "Wf4LD1084WQUbf1S6Kcd0016";
            default: return "Wf4LD1084WQUbf1S6Kcd0016";
        }
    }

    public static String masterCardMultiUseToken(Industry industry) {
        switch(industry) {
            case ecommerce: return "e51Af108vAsB6Tx0PfmG0014";
            case retail: return "e51Af108vAsB6Tx0PfmG0014";
            default: return "e51Af108vAsB6Tx0PfmG0014";
        }
    }

    public static String discoverMultiUseToken(Industry industry) {
        switch(industry) {
            case ecommerce: return "ubeZ3f08W1aaEV643ZJa6527";
            case retail: return "ubeZ3f08W1aaEV643ZJa6527";
            default: return "ubeZ3f08W1aaEV643ZJa6527";
        }
    }

    public static String amexMultiUseToken(Industry industry) {
        switch(industry) {
            case ecommerce: return "94oIbE08e30G0fVcH12d1018";
            case retail: return "94oIbE08e30G0fVcH12d1018";
            default: return "94oIbE08e30G0fVcH12d1018";
        }
    }

    public static HpsCreditCard gsbCardECommerce() {
        HpsCreditCard card = new HpsCreditCard();
        card.setNumber("6277220020010671");
        card.setExpYear(2049);
        card.setExpMonth(12);

        return card;
    }

    // CARD SWIPE DATA

    public static HpsTrackData visaSwipe(HpsTrackDataMethod method) {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B4012002000060016^VI TEST CREDIT^251210118039000000000396?;4012002000060016=25121011803939600000?");
        trackData.setTrackDataMethod(method);

        return trackData;
    }

    public static HpsTrackData masterCardSwipe(HpsTrackDataMethod method) {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B5473500000000014^MC TEST CARD^251210199998888777766665555444433332?;5473500000000014=25121019999888877776?");
        trackData.setTrackDataMethod(method);

        return trackData;
    }

    public static HpsTrackData amexSwipe(HpsTrackDataMethod method) {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B3727 006992 51018^AMEX TEST CARD^2512990502700?;372700699251018=2512990502700?");
        trackData.setTrackDataMethod(method);

        return trackData;
    }

    public static HpsTrackData discoverSwipe(HpsTrackDataMethod method) {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6011000990156527^DIS TEST CARD^25121011000062111401?;6011000990156527=25121011000062111401?");
        trackData.setTrackDataMethod(method);

        return trackData;
    }

    public static HpsTrackData jcbSwipe(HpsTrackDataMethod method) {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B3566007770007321^JCB TEST CARD^2512101100000000000000000064300000?;3566007770007321=2" +
                "5121011000000076435?");
        trackData.setTrackDataMethod(method);

        return trackData;
    }

    public static HpsTrackData gsbSwipe(HpsTrackDataMethod method) {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("%B6277220020010671^   /                         ^49121010228710000209000000?;6277220020" +
                "010671=49121010228710000209?");
        trackData.setTrackDataMethod(method);

        return trackData;
    }

    public static HpsTrackData visaDebitSwipe() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1050711%B4012001000000016^VI TEST CREDIT^251200000000000000000000?|LO04K0WFOmdkDz0" +
                "um+GwUkILL8ZZOP6Zc4rCpZ9+kg2T3JBT4AEOilWTI|+++++++Dbbn04ekG|11;4012001000000016=251200000000000000" +
                "00?|1u2F/aEhbdoPixyAPGyIDv3gBfF|+++++++Dbbn04ekG|00|||/wECAQECAoFGAgEH2wYcShV78RZwb3NAc2VjdXJlZXhj" +
                "aGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAuRrpzxNi0TN/DWXWSjUC8" +
                "m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr2Mlh2YcAt6gGG1i2bJgi" +
                "EJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        trackData.setEncryptionData(new HpsEncryptionData());
        trackData.getEncryptionData().setVersion("01");

        return trackData;
    }

    public static String visaDebitPinBlock() {
        return "32539F50C245A6A93D123412324000AA";
    }

    public static HpsTrackData masterCardDebitSwipe() {
        HpsTrackData trackData = new HpsTrackData();
        trackData.setValue("&lt;E1052711%B5473501000000014^MC TEST CARD^251200000000000000000000000000000000?|GVEY/" +
                "MKaKXuqqjKRRueIdCHPPoj1gMccgNOtHC41ymz7bIvyJJVdD3LW8BbwvwoenI+|+++++++C4cI2zjMp|11;547350100000001" +
                "4=25120000000000000000?|8XqYkQGMdGeiIsgM0pzdCbEGUDP|+++++++C4cI2zjMp|00|||/wECAQECAoFGAgEH2wYcShV7" +
                "8RZwb3NAc2VjdXJlZXhjaGFuZ2UubmV0PX50qfj4dt0lu9oFBESQQNkpoxEVpCW3ZKmoIV3T93zphPS3XKP4+DiVlM8VIOOmAu" +
                "RrpzxNi0TN/DWXWSjUC8m/PI2dACGdl/hVJ/imfqIs68wYDnp8j0ZfgvM26MlnDbTVRrSx68Nzj2QAgpBCHcaBb/FZm9T7pfMr" +
                "2Mlh2YcAt6gGG1i2bJgiEJn8IiSDX5M2ybzqRT86PCbKle/XCTwFFe1X|&gt;");
        trackData.setTrackDataMethod(HpsTrackDataMethod.Swipe);
        trackData.setEncryptionData(new HpsEncryptionData());
        trackData.getEncryptionData().setVersion("01");

        return trackData;
    }

    public static String masterCardDebitPinBlock() {
        return "F505AD81659AA42A3D123412324000AB";
    }

    // GIFT CARDS

    public static HpsGiftCard giftCard1() {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setNumber("5022440000000000098");

        return giftCard;
    }

    public static HpsGiftCard giftCard2() {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setNumber("5022440000000000098");

        return giftCard;
    }

    public static HpsGiftCard giftCardSwipe1() {
        HpsGiftCard giftCard = new HpsGiftCard();
        giftCard.setNumber("%B5022440000000000098^^391200081613?;5022440000000000098=391200081613?");

        return giftCard;
    }
}
