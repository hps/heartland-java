package com.hps.integrator.entities;

import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import com.hps.integrator.infrastructure.emums.AddressFields;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;

public class HpsAddress {

	private String mAddress;
	private String mCity;
	private String mState;
	private String mZip;
	private String mCountry;
	
	public HpsAddress() {
		
	}
	
	public HpsAddress(String zip) {
		mZip = zip;
	}
	
	public String getAddress() {
		return mAddress;
	}
	
	public void setAddress(String address) {
		try {
			this.mAddress = HpsInputValidation.cardHolderDetails(address,AddressFields.Address);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}
	}
	
	public String getCity() {
		return mCity;
	}
	
	public void setCity(String city) {
		try {
			this.mCity = HpsInputValidation.cardHolderDetails(city,AddressFields.City);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}
	}
	
	public String getState()
	{
		return mState;
	}
	
	public void setState(String state)
	{
		try {
			mState = HpsInputValidation.cardHolderDetails(state,AddressFields.State);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}
	}
	
	public String getZip() {
		return mZip;
	}
	
	public void setZip(String zip) {
		try {
			this.mZip =  HpsInputValidation.checkZipcode(zip);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}
	}
	
	public String getCountry() {
		return mCountry;
	}
	
	public void setCountry(String country) {
		this.mCountry = country;
	}
}
