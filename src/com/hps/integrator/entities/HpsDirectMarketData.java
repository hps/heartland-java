package com.hps.integrator.entities;

import java.util.Calendar;

public class HpsDirectMarketData {
    private String invoiceNumber;
    private Integer shipMonth;
    private Integer shipDay;

    public HpsDirectMarketData () {
        Calendar cal = Calendar.getInstance();

        this.invoiceNumber = "";
        this.shipDay = cal.get(Calendar.DAY_OF_MONTH);
        this.shipMonth = cal.get(Calendar.MONTH) + 1;
    }

    public HpsDirectMarketData (String invoiceNumber) {
        Calendar cal = Calendar.getInstance();

        this.invoiceNumber = invoiceNumber;
        this.shipDay = cal.get(Calendar.DAY_OF_MONTH);
        this.shipMonth = cal.get(Calendar.MONTH) + 1;
    }

    public HpsDirectMarketData (String invoiceNumber, Integer shipDay, Integer shipMonth) {
        this.invoiceNumber = invoiceNumber;
        this.shipDay = shipDay;
        this.shipMonth = shipMonth;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getShipDay() {
        return shipDay;
    }

    public void setShipDay(Integer shipDay) {
        this.shipDay = shipDay;
    }

    public Integer getShipMonth() {
        return shipMonth;
    }

    public void setShipMonth(Integer shipMonth) {
        this.shipMonth = shipMonth;
    }
}