package com.hps.integrator.entities;

import com.hps.integrator.infrastructure.HpsTrackDataMethod;

public class HpsTrackData {

	private HpsTrackDataMethod trackDataMethod;
	private String value;
    private HpsEncryptionData encryptionData;

    public HpsTrackDataMethod getTrackDataMethod() {
        return trackDataMethod;
    }

    public void setTrackDataMethod(HpsTrackDataMethod trackDataMethod) {
        this.trackDataMethod = trackDataMethod;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HpsEncryptionData getEncryptionData() {
        return encryptionData;
    }

    public void setEncryptionData(HpsEncryptionData encryptionData) {
        this.encryptionData = encryptionData;
    }
}
