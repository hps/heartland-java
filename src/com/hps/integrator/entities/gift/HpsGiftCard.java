package com.hps.integrator.entities.gift;

public class HpsGiftCard {
    public HpsGiftCard() {
        isTrackData = false;
    }

    private String number;
    private int expMonth;
    private int expYear;
    private boolean isTrackData;
    private HpsEncryptionData encryptionData;

    public boolean isTrackData() {
        return isTrackData;
    }

    public void setTrackData(boolean isTrackData) {
        this.isTrackData = isTrackData;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public HpsEncryptionData getEncryptionData() {
        return encryptionData;
    }

    public void setEncryptionData(HpsEncryptionData encryptionData) {
        this.encryptionData = encryptionData;
    }
}