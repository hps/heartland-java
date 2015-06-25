package com.hps.integrator.entities.check;

import com.hps.integrator.infrastructure.emums.AccountTypeType;
import com.hps.integrator.infrastructure.emums.CheckTypeType;
import com.hps.integrator.infrastructure.emums.DataEntryModeType;

public class HpsCheck {
    private String routingNumber;
    private String accountNumber;
    private String checkNumber;
    private String micrNumber;
    private AccountTypeType accountType;
    private DataEntryModeType dataEntryMode;
    private CheckTypeType checkType;
    private Boolean checkVerify;
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

    public Boolean getCheckVerify() {
        return checkVerify;
    }

    public void setCheckVerify(Boolean checkVerify) {
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

    public AccountTypeType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeType accountType) {
        this.accountType = accountType;
    }

    public DataEntryModeType getDataEntryMode() {
        return dataEntryMode;
    }

    public void setDataEntryMode(DataEntryModeType dataEntryMode) {
        this.dataEntryMode = dataEntryMode;
    }

    public CheckTypeType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckTypeType checkType) {
        this.checkType = checkType;
    }
}
