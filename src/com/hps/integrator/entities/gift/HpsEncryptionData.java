package com.hps.integrator.entities.gift;

/**
 * Defines the HpsEncryptionData type.
 */
public class HpsEncryptionData {
    private String version;
    private String encryptedTrackNumber;
    private String ktb;
    private String ksn;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncryptedTrackNumber() {
        return encryptedTrackNumber;
    }

    public void setEncryptedTrackNumber(String encryptedTrackNumber) {
        this.encryptedTrackNumber = encryptedTrackNumber;
    }

    public String getKtb() {
        return ktb;
    }

    public void setKtb(String ktb) {
        this.ktb = ktb;
    }

    public String getKsn() {
        return ksn;
    }

    public void setKsn(String ksn) {
        this.ksn = ksn;
    }
}
