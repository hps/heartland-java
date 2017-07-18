package com.hps.integrator.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import com.hps.integrator.infrastructure.emums.AddressFields;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;

public class HpsInputValidationTests {

	/* Positive Test cases for Phone Number */
	@Test
	public void phoneNumberWithHyphens() throws HpsException {
		String phone = "555-569-5626";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "5555695626");
	}

	@Test
	public void phoneNumberWithParanthesis() throws HpsException {
		String phone = "(256)555-{5555}";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithDots() throws HpsException {
		String phone = "256.555.5555";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithAlphabets() throws HpsException {
		String phone = "abcdf2565555555";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void PhoneNumberWithDoubleQuotation() throws HpsException {
		String phone = "256'5555'555";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithSingleQuotation() throws HpsException {
		String phone = "256'5555'555";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithQuestionMark() throws HpsException {
		String phone = "256?5555?555";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithSpecialCharacter() throws HpsException {
		String phone = "256&#5555?^%555*!";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithSquareBracketsr() throws HpsException {
		String phone = "256[5555][555]";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithOr() throws HpsException {
		String phone = "256|5555|555|";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "2565555555");
	}

	@Test
	public void phoneNumberWithEmpty() throws HpsException {
		String phone = "";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "");
	}

	@Test
	public void phoneNumberWithOnlyAlphabets() throws HpsException {
		String phone = "abcdefghijk";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setPhone(phone);
		assertEquals(validCardHolder.getPhone(), "");
	}

	/* Positive Test cases for Zip Code */
	@Test
	public void zipCodeWithNumbers() throws HpsException {
		String zipCode = "9654266";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "9654266");
	}

	@Test
	public void zipCodeWithSpecialCharacters() throws HpsException {
		String zipCode = "96-54-(266)";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "9654266");
	}

	@Test
	public void zipCodeWithAlphaNumericalValues() throws HpsException {
		String zipCode = "-d@#@^fd324(789)";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "dfd324789");
	}

	@Test
	public void zipCodeWithSpecialCharacter() throws HpsException {
		String zipCode = "-d@#@&*^fd32%!~`4(789)";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "dfd324789");
	}

	@Test
	public void zipCodeWithSquareBracket() throws HpsException {
		String zipCode = "-d[fd32]4[789]";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "dfd324789");
	}

	@Test
	public void zipCodeWithQuestionMark() throws HpsException {
		String zipCode = "-d?fd32?4?789?";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "dfd324789");
	}

	@Test
	public void zipCodeWithDoubleQuotation() throws HpsException {
		String zipCode = "-d\"fd32\"4\"789\"";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "dfd324789");
	}

	@Test
	public void zipCodeWithSingleQuotation() throws HpsException {
		String zipCode = "-d'fd32'4'789'";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "dfd324789");
	}

	@Test
	public void zipCodeWithOrMark() throws HpsException {
		String zipCode = "-d|fd32|4|789?";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "dfd324789");
	}

	@Test
	public void zipCodeWithEmptyString() throws HpsException {
		String zipCode = "";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setZip(zipCode);
		assertEquals(validCardHolder.getAddress().getZip(), "");
	}

	/* Positive Test cases for Email */
	@Test
	public void emailWithDoubleDot() throws HpsException {
		String email = "Abc..123@example.com";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "Abc..123@example.com");
	}

	@Test
	public void emailWithOutCom() throws HpsException {
		String email = "email@example.web";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "email@example.web");
	}

	@Test
	public void emailAlphabetsWithOutSpace() throws HpsException {
		String email = "email@example.com";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "email@example.com");
	}

	@Test
	public void emailWithPostNumber() throws HpsException {
		String email = "email@111.222.333.44444";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "email@111.222.333.44444");
	}

	@Test
	public void emailWithNumbers() throws HpsException {
		String email = "testmail123456@hps.com";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "testmail123456@hps.com");
	}

	@Test
	public void emailWithExtraDots() throws HpsException {
		String email = "test.email@gmail.io.uk";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "test.email@gmail.io.uk");
	}

	@Test
	public void emailWithUnderScoresValue() throws HpsException {
		String email = "_____@example.com";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "_____@example.com");
	}

	@Test
	public void emailWithHyphenValue() throws HpsException {
		String email = "firstname-lastname@example.com";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "firstname-lastname@example.com");
	}

	@Test
	public void emailWithExtraPostfixValue() throws HpsException {
		String email = "email@example.co.jp";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "email@example.co.jp");
	}

	@Test
	public void emailWithPlusValue() throws HpsException {
		String email = "firstname+lastname@example.com";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "firstname+lastname@example.com");
	}

	@Test
	public void emailWithSingleInvertedCommaValue() throws HpsException {
		String email = "email@example.com";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setEmail(email);
		assertEquals(validCardHolder.getEmail(), "email@example.com");
	}

	/* Positive Test cases for First Name - CardDeatils */
	@Test
	public void cardDetailsForFirstname() throws HpsException {
		String firstName = "Testfirstname";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setFirstName(firstName);
		assertEquals(validCardHolder.getFirstName(), "Testfirstname");
	}

	@Test
	public void firstnamewithSpecialcharacter() throws HpsException {
		String firstName = "$%^!~@^&*()firstname";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setFirstName(firstName);
		assertEquals(validCardHolder.getFirstName(), "$%^!~@^&*()firstname");
	}

	@Test
	public void firstnamewithQuestionMark() throws HpsException {
		String firstName = "?f?irstna?me";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setFirstName(firstName);
		assertEquals(validCardHolder.getFirstName(), "?f?irstna?me");
	}

	@Test
	public void firstnamewithSingleQuotation() throws HpsException {
		String firstName = "\"f\"irstna\"me";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setFirstName(firstName);
		assertEquals(validCardHolder.getFirstName(), "\"f\"irstna\"me");
	}

	@Test
	public void firstnamewithDoubleQuotation() throws HpsException {
		String firstName = "'f'irstna'me";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setFirstName(firstName);
		assertEquals(validCardHolder.getFirstName(), "'f'irstna'me");
	}

	/* Positive Test cases for Last Name - CardDeatils */
	@Test
	public void cardDetailsForLastname() throws HpsException {
		String lastName = "Testlastname";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setLastName(lastName);
		assertEquals(validCardHolder.getLastName(), "Testlastname");
	}

	@Test
	public void lastnamewithSpecialcharacter() throws HpsException {
		String lastName = "$%^!~@^&*()Lastname";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setLastName(lastName);
		assertEquals(validCardHolder.getLastName(), "$%^!~@^&*()Lastname");
	}

	@Test
	public void lastnamewithQuestionMark() throws HpsException {
		String lastName = "?f?irstna?me";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setLastName(lastName);
		assertEquals(validCardHolder.getLastName(), "?f?irstna?me");
	}

	@Test
	public void lastnamewithDoubleQuotation() throws HpsException {
		String lastName = "\"f\"irstna\"me";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setLastName(lastName);
		assertEquals(validCardHolder.getLastName(), "\"f\"irstna\"me");
	}

	@Test
	public void lastnamewithSingleQuotation() throws HpsException {
		String lastName = "'f'irstna'me";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		validCardHolder.setLastName(lastName);
		assertEquals(validCardHolder.getLastName(), "'f'irstna'me");
	}

	/* Positive Test cases for Address - CardDeatils */
	@Test
	public void cardDetailsForAddress() throws HpsException {
		String address = "Testaddress, sample address";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setAddress(address);
		assertEquals(validCardHolder.getAddress().getAddress(), "Testaddress, sample address");
	}

	@Test
	public void addresswithSpecialcharacter() throws HpsException {
		String address = "~`!@#$%^&*adress, sample address";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setAddress(address);
		assertEquals(validCardHolder.getAddress().getAddress(), "~`!@#$%^&*adress, sample address");
	}

	@Test
	public void addresswithQuestionMark() throws HpsException {
		String address = "?Testaddress?, sample?address";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setAddress(address);
		assertEquals(validCardHolder.getAddress().getAddress(), "?Testaddress?, sample?address");
	}

	@Test
	public void addresswithSingleQuotation() throws HpsException {
		String address = "Testaddress, sample address";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setAddress(address);
		assertEquals(validCardHolder.getAddress().getAddress(), "Testaddress, sample address");
	}

	@Test
	public void addresswithDoubleQuotation() throws HpsException {
		String address = "Testaddress,\" sample\"address";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setAddress(address);
		assertEquals(validCardHolder.getAddress().getAddress(), "Testaddress,\" sample\"address");
	}

	/* Positive Test cases for City - CardDeatils */
	@Test
	public void cardDetailsForCity() throws HpsException {
		String city = "TestCity";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setCity(city);
		assertEquals(validCardHolder.getAddress().getCity(), "TestCity");
	}

	@Test
	public void citywithSpecialcharacter() throws HpsException {
		String city = "~`!@#$%^&*city,";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setCity(city);
		assertEquals(validCardHolder.getAddress().getCity(), "~`!@#$%^&*city,");
	}

	@Test
	public void citywithQuestionMark() throws HpsException {
		String city = "?city?";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setCity(city);
		assertEquals(validCardHolder.getAddress().getCity(), "?city?");
	}

	@Test
	public void citywithDoubleQuotation() throws HpsException {
		String city = "city\"";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setCity(city);
		assertEquals(validCardHolder.getAddress().getCity(), "city\"");
	}

	@Test
	public void citywithSingleQuotation() throws HpsException {
		String city = "'sample'";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress objAddress = new HpsAddress();
		validCardHolder.setAddress(objAddress);
		objAddress.setCity(city);
		assertEquals(validCardHolder.getAddress().getCity(), "'sample'");
	}

	/* Positive Test cases for State - CardDeatils */
	@Test
	public void cardDetailsForState() throws HpsException {
		String state = "Teststate";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setState(state);
		assertEquals(validCardHolder.getAddress().getState(), "Teststate");
	}

	@Test
	public void statewithSpecialcharacter() throws HpsException {
		String state = "~`!@#$%^&*state";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setState(state);
		assertEquals(validCardHolder.getAddress().getState(), "~`!@#$%^&*state");
	}

	@Test
	public void statewithQuestionMark() throws HpsException {
		String state = "?state?";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setState(state);
		assertEquals(validCardHolder.getAddress().getState(), "?state?");
	}

	@Test
	public void statewithDoubleQuotation() throws HpsException {
		String state = "State\"";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setState(state);
		assertEquals(validCardHolder.getAddress().getState(), "State\"");
	}

	@Test
	public void statewithSingleQuotation() throws HpsException {
		String state = "'state''";
		HpsCardHolder validCardHolder = new HpsCardHolder();
		HpsAddress address = new HpsAddress();
		validCardHolder.setAddress(address);
		address.setState(state);
		assertEquals(validCardHolder.getAddress().getState(), "'state''");
	}

	/* Negative Test cases for Email */
	@Test(expected = HpsInvalidRequestException.class)
	public void emailWithInvalidFormats() throws HpsException {
		String email = "www.testemailgmail.com";
		HpsInputValidation.checkValidEmail(email);
	}

	@Test(expected = HpsInvalidRequestException.class)
	public void emailtMatchPattern() throws HpsException {
		String email = "www.testemail.gmail.com";
		HpsInputValidation.checkValidEmail(email);
	}

	@Test(expected = HpsInvalidRequestException.class)
	public void emailWithLimitExceed() throws HpsException {
		String email = "loremipsum.loremipsrrrumloremipsumloremipsumloremipsumloremipsumloremipsumloremipsumloremipsumloremipsum@test.com";
		HpsInputValidation.checkValidEmail(email);
	}

	@Test(expected = HpsInvalidRequestException.class)
	public void emailWithSpecialCharacter() throws HpsException {
		String email = "#@%^%#$@#$@#.com";
		HpsInputValidation.checkValidEmail(email);
	}

	@Test(expected = HpsInvalidRequestException.class)
	public void InCompleteEmailValue() throws HpsException {
		String email = "@example.com";
		HpsInputValidation.checkValidEmail(email);
	}

	@Test(expected = HpsInvalidRequestException.class)
	public void emailWithSpaceAndSpecialcharacter() throws HpsException {
		String email = "JoeSmith<email@#example.com";
		HpsInputValidation.checkValidEmail(email);
	}

	@Test(expected = HpsInvalidRequestException.class)
	public void emailWithExtraAt() throws HpsException {
		String email = "emailexample@@example@.com";
		HpsInputValidation.checkValidEmail(email);
	}

	// Negative Test case for Phone Number
	@Test(expected = HpsInvalidRequestException.class)
	public void phoneNumberWithLimitExceed() throws HpsException {
		String phoneNumber = "1234567894560321654987";
		HpsInputValidation.checkPhoneNumber(phoneNumber);
	}

	// Negative Test cases for Zip Code
	@Test(expected = HpsInvalidRequestException.class)
	public void zipCodeWithLimitExceed() throws HpsException {
		String zipCode = "1234567890";
		HpsInputValidation.checkZipcode(zipCode);
	}

	// Negative Test cases for First Name
	@Test(expected = HpsInvalidRequestException.class)
	public void firstNameWithLimitExceed() throws HpsException {
		String firstName = "Testdata sample for FirstName Testdata sample lorem Testdata sample lorem";
		HpsInputValidation.cardHolderDetails(firstName, AddressFields.FirstName);
	}

	// Negative Test cases for Last Name
	@Test(expected = HpsInvalidRequestException.class)
	public void lastNameWithLimitExceed() throws HpsException {
		String lastName = "Testdata sample for LastName Testdata sample lorem Testdata sample lorem";
		HpsInputValidation.cardHolderDetails(lastName, AddressFields.LastName);
	}

	// Negative Test cases for City Name
	@Test(expected = HpsInvalidRequestException.class)
	public void cityWithLimitExceed() throws HpsException {
		String city = "Testdata sample for City";
		HpsInputValidation.cardHolderDetails(city, AddressFields.City);
	}

	// Negative Test cases for Address Name
	@Test(expected = HpsInvalidRequestException.class)
	public void addressWithLimitExceed() throws HpsException {
		String address = "Testdata sample for Address && Testdata sample for City && Testdata sample for City";
		HpsInputValidation.cardHolderDetails(address, AddressFields.Address);
	}

	// Negative Test cases for State Name
	@Test(expected = HpsInvalidRequestException.class)
	public void stateWithLimitExceed() throws HpsException {
		String state = "TestStateUSUSUSUSSatte";
		HpsInputValidation.cardHolderDetails(state, AddressFields.State);
	}
}
