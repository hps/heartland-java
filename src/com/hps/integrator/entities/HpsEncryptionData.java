package com.hps.integrator.entities;

public class HpsEncryptionData {
    String version;
    String encryptedTrackNumber;
    String ktb;
    String ksn;

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
