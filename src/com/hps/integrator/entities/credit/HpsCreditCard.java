package com.hps.integrator.entities.credit;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class HpsCreditCard {
	
	private static final Pattern AmexRegex = Pattern.compile("^3[47][0-9]{13}$");
	private static final Pattern MasterCardRegex = Pattern.compile("^5[1-5][0-9]{14}$");
	private static final Pattern VisaRegex = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
	private static final Pattern DinersClubRegex = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
	private static final Pattern RouteClubRegex = Pattern.compile("^(2014|2149)");
	private static final Pattern DiscoverRegex = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
	
	// TODO: Verify this regex works, had to escape characters
	private static final Pattern JcbRegex = Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$");
	
	private Map<String,Pattern> regexMap;
	private String number;
	private String cvv;
	private int expMonth;
	private int expYear;
	
	public HpsCreditCard()
	{
		regexMap = new HashMap<String,Pattern>();
		regexMap.put("Amex", AmexRegex);
		regexMap.put("MasterCard", MasterCardRegex);
		regexMap.put("Visa", VisaRegex);
		regexMap.put("DinersClub", DinersClubRegex);
		regexMap.put("EnRoute", RouteClubRegex);
		regexMap.put("Discover", DiscoverRegex);
		regexMap.put("Jcb", JcbRegex);
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getCvv() {
		return cvv;
	}
	
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public int getExpMonth() {
		return expMonth;
	}
	
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	
	public int getExpYear() {
		return expYear;
	}
	
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	
	public String getCardType() {
		String cardType = "Unknown";
		
		try {
			String cardNum = number.replace(" ", "").replace("-", "");
            for (Entry<String, Pattern> kvp : regexMap.entrySet()) {
                if (kvp.getValue().matcher(cardNum).find()) {
                    cardType = kvp.getKey();
                    break;
                }
            }
			
		} catch (Exception e) {
            cardType = "Unknown";
		}
		
		return cardType;
	}
}