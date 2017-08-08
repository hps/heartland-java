package com.hps.integrator.entities;

import java.io.Serializable;
import com.hps.integrator.infrastructure.emums.EMVchipConditionType;

public class HpsEMVDataType implements Serializable {

	private static final long serialVersionUID = 1L;

	private String emvTagData;
	private String emvPinBlock;
	private transient boolean emvChipConditionSpecified;
	private EMVchipConditionType emvChipCondition;
	private String value;

	public String getEmvTagData() {
		return emvTagData;
	}

	public void setEmvTagData(String emvTagData) {
		this.emvTagData = emvTagData;
	}

	public String getEmvPinBlock() {
		return emvPinBlock;
	}

	public void setEmvPinBlock(String emvPinBlock) {
		this.emvPinBlock = emvPinBlock;
	}

	public boolean isEmvChipConditionSpecified() {
		return emvChipConditionSpecified;
	}

	public void setEmvChipConditionSpecified(boolean emvChipConditionSpecified) {
		this.emvChipConditionSpecified = emvChipConditionSpecified;
	}

	public EMVchipConditionType getEmvChipCondition() {
		return emvChipCondition;
	}

	public void setEmvChipCondition(EMVchipConditionType emvChipCondition) {
		this.emvChipCondition = emvChipCondition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
