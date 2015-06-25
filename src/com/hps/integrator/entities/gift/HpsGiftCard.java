package com.hps.integrator.entities.gift;

import com.hps.integrator.entities.HpsEncryptionData;
import com.hps.integrator.infrastructure.Element;

public class HpsGiftCard {
    private String trackData;
    private String cardNumber;
    private String alias;
    private String tokenValue;
    private HpsEncryptionData encryptionData;
    private String pin;

    public String getTrackData() {
        return trackData;
    }

    public void setTrackData(String trackData) {
        this.trackData = trackData;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public HpsEncryptionData getEncryptionData() {
        return encryptionData;
    }

    public void setEncryptionData(HpsEncryptionData encryptionData) {
        this.encryptionData = encryptionData;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public static HpsGiftCard fromElement(Element item) {
        HpsGiftCard card = new HpsGiftCard();
        card.setTrackData(item.getString("TrackData"));
        card.setCardNumber(item.getString("CardNbr"));
        card.setAlias(item.getString("Alias"));
        card.setTokenValue(item.getString("TokenValue"));
//        card.setEncryptionData(null);
        card.setPin(item.getString("PIN"));

        return card;
    }
}