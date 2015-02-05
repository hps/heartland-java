package com.hps.integrator.entities.check;

import com.hps.integrator.entities.HpsConsumer;

public class HpsCheckHolder extends HpsConsumer {
    private String checkName;
    private String dlNumber;
    private String dlState;
    private String ssn4;
    private Integer dobYear;
    private String courtesyCard;

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getDlState() {
        return dlState;
    }

    public void setDlState(String dlState) {
        this.dlState = dlState;
    }

    public String getDlNumber() {
        return dlNumber;
    }

    public void setDlNumber(String dlNumber) {
        this.dlNumber = dlNumber;
    }

    public String getSsn4() {
        return ssn4;
    }

    public void setSsn4(String ssn4) {
        this.ssn4 = ssn4;
    }

    public Integer getDobYear() {
        return dobYear;
    }

    public void setDobYear(Integer dobYear) {
        this.dobYear = dobYear;
    }

    public String getCourtesyCard() {
        return courtesyCard;
    }

    public void setCourtesyCard(String courtesyCard) {
        this.courtesyCard = courtesyCard;
    }
}
