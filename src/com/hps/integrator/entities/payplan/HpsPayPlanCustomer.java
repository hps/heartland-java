package com.hps.integrator.entities.payplan;

import com.hps.integrator.infrastructure.HpsException;

import java.util.HashMap;
import java.lang.reflect.*;

public class HpsPayPlanCustomer extends HpsPayplanResource {
    String customerKey;
    String customerIdentifier;
    String firstName;
    String lastName;
    String company;
    String customerStatus;
    String primaryEmail;
    String secondaryEmail;
    String phoneDay;
    String phoneDayExt;
    String phoneEvening;
    String phoneEveningExt;
    String phoneMobile;
    String phoneMobileExt;
    String fax;
    String title;
    String department;
    String addressLine1;
    String addressLine2;
    String city;
    String country;
    String stateProvince;
    String zipPostalCode;
    HpsPayPlanPaymentMethod[] paymentMethods;
    HpsPayPlanSchedule[] schedules;

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(String customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getPhoneDay() {
        return phoneDay;
    }

    public void setPhoneDay(String phoneDay) {
        this.phoneDay = phoneDay;
    }

    public String getPhoneDayExt() {
        return phoneDayExt;
    }

    public void setPhoneDayExt(String phoneDayExt) {
        this.phoneDayExt = phoneDayExt;
    }

    public String getPhoneEvening() {
        return phoneEvening;
    }

    public void setPhoneEvening(String phoneEvening) {
        this.phoneEvening = phoneEvening;
    }

    public String getPhoneEveningExt() {
        return phoneEveningExt;
    }

    public void setPhoneEveningExt(String phoneEveningExt) {
        this.phoneEveningExt = phoneEveningExt;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneMobileExt() {
        return phoneMobileExt;
    }

    public void setPhoneMobileExt(String phoneMobileExt) {
        this.phoneMobileExt = phoneMobileExt;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public HpsPayPlanPaymentMethod[] getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(HpsPayPlanPaymentMethod[] paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public HpsPayPlanSchedule[] getSchedules() {
        return schedules;
    }

    public void setSchedules(HpsPayPlanSchedule[] schedules) {
        this.schedules = schedules;
    }

    private String[] getEditableFields() {
        return new String[]{
                "customerIdentifier",
                "firstName",
                "lastName",
                "company",
                "customerStatus",
                "title",
                "department",
                "primaryEmail",
                "secondaryEmail",
                "phoneDay",
                "phoneDayExt",
                "phoneEvening",
                "phoneEveningExt",
                "phoneMobile",
                "phoneMobileExt",
                "fax",
                "addressLine1",
                "addressLine2",
                "city",
                "stateProvince",
                "zipPostalCode",
                "country"
        };
    }

    private String[] getSearchableFields() {
        return new String[]{
                "customerIdentifier",
                "company",
                "firstName",
                "lastName",
                "primaryEmail",
                "customerStatus",
                "phoneNumber",
                "city",
                "stateProvince",
                "zipPostalCode",
                "country",
                "hasSchedules",
                "hasActiveSchedules",
                "hasPaymentMethods",
                "hasActivePaymentMethods"
        };
    }

    public HashMap<String, Object> getEditableFieldsWithValues() throws HpsException {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();

            for (String fieldName : this.getEditableFields()) {
                Field field = this.getClass().getDeclaredField(fieldName);
                if(field.get(this) != null) {
                    map.put(fieldName, field.get(this));
                }
            }

            return map;
        }
        catch (NoSuchFieldException e) { throw new HpsException(e.getMessage(), e); }
        catch (IllegalAccessException e) { throw new HpsException(e.getMessage(), e); }
    }
}
