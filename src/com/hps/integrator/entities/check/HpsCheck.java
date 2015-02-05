package com.hps.integrator.entities.check;

import PosGateway.Exchange.Hps.Enums;

public class HpsCheck {
    private String routingNumber;
    private String accountNumber;
    private String checkNumber;
    private String micrNumber;
    private Enums.accountTypeType accountType;
    private Enums.dataEntryModeType dataEntryMode;
    private Enums.checkTypeType checkType;
    private String checkVerify;
    private String secCode;
    private HpsCheckHolder checkHolder;


    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getMicrNumber() {
        return micrNumber;
    }

    public void setMicrNumber(String micrNumber) {
        this.micrNumber = micrNumber;
    }

    public String getCheckVerify() {
        return checkVerify;
    }

    public void setCheckVerify(String checkVerify) {
        this.checkVerify = checkVerify;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public HpsCheckHolder getCheckHolder() {
        return checkHolder;
    }

    public void setCheckHolder(HpsCheckHolder checkHolder) {
        this.checkHolder = checkHolder;
    }

    public Enums.accountTypeType getAccountType() {
        return accountType;
    }

    public void setAccountType(Enums.accountTypeType accountType) {
        this.accountType = accountType;
    }

    public Enums.dataEntryModeType getDataEntryMode() {
        return dataEntryMode;
    }

    public void setDataEntryMode(Enums.dataEntryModeType dataEntryMode) {
        this.dataEntryMode = dataEntryMode;
    }

    public Enums.checkTypeType getCheckType() {
        return checkType;
    }

    public void setCheckType(Enums.checkTypeType checkType) {
        this.checkType = checkType;
    }
}
