package com.hps.integrator.infrastructure.validation;

import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsExceptionCodes;
import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import com.hps.integrator.infrastructure.emums.AddressFields;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common input validation helper for method parameter and related validation.
 */
public class HpsInputValidation {
	
    private static int NAME_MAX_LENGTH = 26;
    private static int ADDRESS_MAX_LENGTH = 50;
    private static int CITY_MAX_LENGTH = 20;
    private static int STATE_MAX_LENGTH = 20;
    
    /**
     * check the currency amount (can't be negative).
     *
     * @param amount The amount to use for a transaction(s).
     */
    public static void checkAmount(BigDecimal amount) throws HpsException {
        if (amount.compareTo(BigDecimal.ZERO) != 1) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidAmount, "Must be greater than or equal 0.", "amount");
        }
    }

    /**
     * Helper method to ensure the currency entered is valid. If not, throw the appropriate exception.
     *
     * @param currency The currency to use for a transaction(s).
     * @throws HpsException
     */
    public static void checkCurrency(String currency) throws HpsException {
        if (currency == null || currency.length() == 0) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.MissingCurrency, "Currency can't be null.", "currency");
        } else if (!currency.toLowerCase().equals("usd")) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidCurrency, "The only supported currency is \"usd\"", "currency");
        }
    }

    public static void checkDateNotFuture(Date date, String paramName) throws HpsException {
        if (date.after(new Date())) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidDate, "Date can not be in the future.", paramName);
        }
    }

    public static String checkValidEmail(String emailAddress) throws HpsInvalidRequestException   {
    	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"; 
        if(!emailAddress.matches(regex) || emailAddress.length() > 100){
			throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidEmail, "Must be a valid E-mail address and should not be more than 100 characters", "emailAddress");
        }
		return emailAddress;  
    }

    public static String checkZipcode(String zipCode) throws HpsInvalidRequestException {
	    String zipCode_REGEX = "^[a-zA-Z0-9]*$"; 
	    zipCode=zipCode.replaceAll("[^0-9A-Za-z]", "");
	    Pattern pattern = Pattern.compile(zipCode_REGEX);
	    Matcher matcher = pattern.matcher(zipCode);
	   
	    if(!matcher.matches() || zipCode.length() > 9){
			throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidZipcode, "Must be a valid zipcode and should not be more than 9 characters", "zipCode");
	    }
		return zipCode;  
    }

    public static String checkPhoneNumber(String phoneNumber) throws HpsInvalidRequestException  {

	    String PHONE_REGEX = "^[0-9]*$";
	    phoneNumber=phoneNumber.replaceAll("[^\\d]", "");
	    Pattern pattern = Pattern.compile(PHONE_REGEX);
	    Matcher matcher = pattern.matcher(phoneNumber);
	    
	    if(!matcher.matches() || phoneNumber.length() > 20){
		   throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidPhonenumber, "Must be a valid phone number and should not be more than 20 characters", "phoneNumber");
	    }	
	    return phoneNumber;
	 }
    public static String cardHolderDetails(String cardHolderDetail, AddressFields fieldName)throws HpsInvalidRequestException {
    	@SuppressWarnings("rawtypes")
		HashMap <Enum,Integer> hMap = new HashMap<Enum, Integer>();
    	hMap.put(AddressFields.FirstName, NAME_MAX_LENGTH);
    	hMap.put(AddressFields.LastName, NAME_MAX_LENGTH);
    	hMap.put(AddressFields.Address, ADDRESS_MAX_LENGTH);
    	hMap.put(AddressFields.City, CITY_MAX_LENGTH);
    	hMap.put(AddressFields.State, STATE_MAX_LENGTH);
    	
    	if(cardHolderDetail !=null && !cardHolderDetail.equals("") && cardHolderDetail.length() > hMap.get(fieldName)) {
		   throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidCardHolderDetail, "The value for " + fieldName + " should not more than" + hMap.get(fieldName) + "characters", "cardHolderDetail");
        }  	
    	return cardHolderDetail;  	
    }
}
