package com.hps.integrator.entities;

import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import com.hps.integrator.infrastructure.emums.AddressFields;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;

public class HpsConsumer {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private HpsAddress address;

    public HpsConsumer() {
        address = new HpsAddress();
    }

    public HpsConsumer(String zipCode) {
        address = new HpsAddress(zipCode);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
       try {
			this.firstName = HpsInputValidation.cardHolderDetails(firstName, AddressFields.FirstName);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
       try {
			this.lastName = HpsInputValidation.cardHolderDetails(lastName, AddressFields.LastName);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
		try {
			this.phone = HpsInputValidation.checkPhoneNumber(phone);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
		try {
			this.email = HpsInputValidation.checkValidEmail(email);
		} catch (HpsInvalidRequestException e) {
			e.printStackTrace();
		}		
    }

    public HpsAddress getAddress() {
        return address;
    }

    public void setAddress(HpsAddress address) {
        this.address = address;
    }
}
