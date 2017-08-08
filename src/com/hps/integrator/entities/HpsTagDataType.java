package com.hps.integrator.entities;

import com.hps.integrator.infrastructure.emums.TagDataTypeTagValuesSource;

public class HpsTagDataType {

	private String tagData;
	private TagDataTypeTagValuesSource source;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTagData() {
		return tagData;
	}

	public void setTagData(String tagData) {
		this.tagData = tagData;
	}

	public TagDataTypeTagValuesSource getSource() {
		return source;
	}

	public void setSource(TagDataTypeTagValuesSource source) {
		this.source = source;
	}

}
