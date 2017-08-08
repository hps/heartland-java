package com.hps.integrator.infrastructure.emums;

public enum TagDataTypeTagValuesSource {
	chip("chip"), msd("msd");

	String value;

	TagDataTypeTagValuesSource(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
