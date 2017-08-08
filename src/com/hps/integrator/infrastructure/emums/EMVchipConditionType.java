package com.hps.integrator.infrastructure.emums;

public enum EMVchipConditionType {

	CHIP_FAILED_PREV_SUCCESS("CHIP_FAILED_PREV_SUCCESS"), 
	CHIP_FAILED_PREV_FAILED("CHIP_FAILED_PREV_FAILED");

	String value;

	EMVchipConditionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
