package com.hps.integrator.tests.testdata;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.credit.HpsCardHolder;

public class TestCardHolders {

	private static HpsCardHolder baseCardHolder() {
		HpsCardHolder cardHolder = new HpsCardHolder();
		cardHolder.setFirstName("Bill");
		cardHolder.setLastName("Johnson");

		return cardHolder;
	}

	public static HpsAddress heartlandAddress() {
		HpsAddress address = new HpsAddress();

		address.setAddress("One Heartland Way");
		address.setCity("Jeffersonville");
		address.setState("IN");
		address.setZip("47130");
		address.setCountry("United States");

		return address;
	}

	public static HpsAddress certAddress() {
		HpsAddress address = new HpsAddress();

		address.setAddress("6860 Dallas Pkwy");
		address.setCity("Irvine");
		address.setState("TX");
		address.setZip("750241234");
		address.setCountry("United States");

		return address;
	}

	public static HpsCardHolder validCardHolder() {
		HpsCardHolder cardHolder = baseCardHolder();
		cardHolder.setAddress(heartlandAddress());
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderShortZip()
	{
		HpsCardHolder cardHolder = baseCardHolder();
		cardHolder.setAddress(certAddress());
		cardHolder.getAddress().setZip("75024");
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderShortZipNoStreet()
	{
		HpsCardHolder cardHolder = baseCardHolder();
		cardHolder.setAddress(certAddress());
		cardHolder.getAddress().setAddress("6860");
		cardHolder.getAddress().setZip("75024");
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderLongZip()
	{
		HpsCardHolder cardHolder = baseCardHolder();
		cardHolder.setAddress(certAddress());
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderLongZipNoStreet()
	{
		HpsCardHolder cardHolder = baseCardHolder();
		cardHolder.setAddress(certAddress());
		cardHolder.getAddress().setAddress("6860");

		return cardHolder;
	}
}
