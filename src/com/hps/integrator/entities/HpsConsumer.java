package com.hps.integrator.entities;

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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HpsAddress getAddress() {
        return address;
    }

    public void setAddress(HpsAddress address) {
        this.address = address;
    }
}
