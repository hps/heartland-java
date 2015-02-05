package com.hps.integrator.tests.testdata;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.credit.HpsCardHolder;

public class TestCardHolders {

	public static HpsCardHolder validCardHolder()
	{
		HpsCardHolder cardHolder = new HpsCardHolder();
		cardHolder.setFirstName("Bill");
		cardHolder.setLastName("Johnson");
		
		HpsAddress address = cardHolder.getAddress();
		address.setAddress("One Heartland Way");
		address.setCity("Jeffersonville");
		address.setState("IN");
		address.setZip("47130");
		address.setCountry("United States");
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderShortZip()
	{
		HpsCardHolder cardHolder = new HpsCardHolder();
		cardHolder.setFirstName("Bill");
		cardHolder.setLastName("Johnson");
		
		HpsAddress address = cardHolder.getAddress();
		address.setAddress("6860 Dallas Pkwy");
		address.setCity("Irvine");
		address.setState("TX");
		address.setZip("75024");
		address.setCountry("United States");
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderShortZipNoStreet()
	{
		HpsCardHolder cardHolder = new HpsCardHolder();
		cardHolder.setFirstName("Bill");
		cardHolder.setLastName("Johnson");
		
		HpsAddress address = cardHolder.getAddress();
		address.setCity("Irvine");
		address.setState("TX");
		address.setZip("75024");
		address.setCountry("United States");
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderLongZip()
	{
		HpsCardHolder cardHolder = new HpsCardHolder();
		cardHolder.setFirstName("Bill");
		cardHolder.setLastName("Johnson");
		
		HpsAddress address = cardHolder.getAddress();
		address.setAddress("6860 Dallas Pkwy");
		address.setCity("Irvine");
		address.setState("TX");
		address.setZip("750241234");
		address.setCountry("United States");
		
		return cardHolder;
	}
	
	public static HpsCardHolder certCardHolderLongZipNoStreet()
	{
		HpsCardHolder cardHolder = new HpsCardHolder();
		cardHolder.setFirstName("Bill");
		cardHolder.setLastName("Johnson");
		
		HpsAddress address = cardHolder.getAddress();
		address.setCity("Irvine");
		address.setState("TX");
		address.setZip("750241234");
		address.setCountry("United States");
		
		return cardHolder;
	}
	
}
