package com.hps.integrator.entities.payplan;

import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsPayPlanPaymentMethodType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HpsPayPlanPaymentMethod extends HpsPayplanResource {
    String paymentMethodKey;
    String paymentMethodType;
    String preferredPayment;
    String paymentStatus;
    String paymentMethodIdentifier;
    String customerKey;
    String customerIdentifier;
    String customerStatus;
    String firstName;
    String lastName;
    String company;
    String nameOnAccount;
    String accountNumber;
    String accountNumberLast4;
    String paymentMethod;
    String cardBrand;
    String expirationDate;
    String cvvResponseCode;
    String avsResponseCode;
    String achType;
    String accountType;
    String routingNumber;
    Boolean telephoneIndicator;
    String addressLine1;
    String addressLine2;
    String city;
    String stateProvince;
    String zipPostalCode;
    String country;
    String accountHolderYob;
    String driversLicenseNumber;
    String driversLicenseState;
    String socialSecurityLast4;
    String hasSchedules;
    String hasActiveSchedules;

    public String getPaymentMethodKey() {
        return paymentMethodKey;
    }

    public void setPaymentMethodKey(String paymentMethodKey) {
        this.paymentMethodKey = paymentMethodKey;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getPreferredPayment() {
        return preferredPayment;
    }

    public void setPreferredPayment(String preferredPayment) {
        this.preferredPayment = preferredPayment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethodIdentifier() {
        return paymentMethodIdentifier;
    }

    public void setPaymentMethodIdentifier(String paymentMethodIdentifier) {
        this.paymentMethodIdentifier = paymentMethodIdentifier;
    }

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

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
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

    public String getNameOnAccount() {
        return nameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumberLast4() {
        return accountNumberLast4;
    }

    public void setAccountNumberLast4(String accountNumberLast4) {
        this.accountNumberLast4 = accountNumberLast4;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvvResponseCode() {
        return cvvResponseCode;
    }

    public void setCvvResponseCode(String cvvResponseCode) {
        this.cvvResponseCode = cvvResponseCode;
    }

    public String getAvsResponseCode() {
        return avsResponseCode;
    }

    public void setAvsResponseCode(String avsResponseCode) {
        this.avsResponseCode = avsResponseCode;
    }

    public String getAchType() {
        return achType;
    }

    public void setAchType(String achType) {
        this.achType = achType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public Boolean getTelephoneIndicator() {
        return telephoneIndicator;
    }

    public void setTelephoneIndicator(Boolean telephoneIndicator) {
        this.telephoneIndicator = telephoneIndicator;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAccountHolderYob() {
        return accountHolderYob;
    }

    public void setAccountHolderYob(String accountHolderYob) {
        this.accountHolderYob = accountHolderYob;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getDriversLicenseState() {
        return driversLicenseState;
    }

    public void setDriversLicenseState(String driversLicenseState) {
        this.driversLicenseState = driversLicenseState;
    }

    public String getSocialSecurityLast4() {
        return socialSecurityLast4;
    }

    public void setSocialSecurityLast4(String socialSecurityLast4) {
        this.socialSecurityLast4 = socialSecurityLast4;
    }

    public String getHasSchedules() {
        return hasSchedules;
    }

    public void setHasSchedules(String hasSchedules) {
        this.hasSchedules = hasSchedules;
    }

    public String getHasActiveSchedules() {
        return hasActiveSchedules;
    }

    public void setHasActiveSchedules(String hasActiveSchedules) {
        this.hasActiveSchedules = hasActiveSchedules;
    }

    private String[] getEditableFields() {
        List<String> fields = new ArrayList<String>();

        fields.add("preferredPayment");
        fields.add("paymentStatus");
        fields.add("paymentMethodIdentifier");
        fields.add("nameOnAccount");
        fields.add("addressLine1");
        fields.add("addressLine2");
        fields.add("city");
        fields.add("stateProvince");
        fields.add("zipPostalCode");

        if(paymentMethodType.equals(HpsPayPlanPaymentMethodType.ACH)) {
            fields.add("telephoneIndicator");
            fields.add("accountHolderYob");
            fields.add("driversLicenseState");
            fields.add("driversLicenseNumber");
            fields.add("socialSecurityLast4");
            fields.add("achType");
            fields.add("routingNumber");
            fields.add("accountNumber");
            fields.add("accountType");
        }
        else if(paymentMethodType.equals(HpsPayPlanPaymentMethodType.CREDIT_CARD)) {
            fields.add("expirationDate");
            fields.add("country");
        }

        return fields.toArray(new String[fields.size()]);
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
