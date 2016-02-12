package com.hps.integrator.entities.altpayment;

public class HpsBuyerData {
    String returnUrl;
    String cancelUrl;
    String emailAddress;
    String payerId;
    String status;
    String countryCode;
    String firstName;
    String lastName;
    String credit;

    public String getReturnUrl() {
        return returnUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    public String getCancelUrl() {
        return cancelUrl;
    }
    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPayerId() {
        return payerId;
    }
    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
    public String getCredit() {
        return credit;
    }
    public void setCredit(String credit) {
        this.credit = credit;
    }
}
