package com.hps.integrator.entities;

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
		this.mAddress = address;
	}
	
	public String getCity() {
		return mCity;
	}
	
	public void setCity(String city) {
		this.mCity = city;
	}
	
	public String getState()
	{
		return mState;
	}
	
	public void setState(String state)
	{
		mState = state;
	}
	
	public String getZip() {
		return mZip;
	}
	
	public void setZip(String zip) {
		this.mZip = zip;
	}
	
	public String getCountry() {
		return mCountry;
	}
	
	public void setCountry(String country) {
		this.mCountry = country;
	}
}
