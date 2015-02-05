package com.hps.integrator.entities;

import java.util.Calendar;

public class HpsDirectMarketData {
    private String invoiceNumber;
    private int shipMonth;
    private int shipDay;

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

    public HpsDirectMarketData (String invoiceNumber, int shipDay, int shipMonth) {
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

    public int getShipDay() {
        return shipDay;
    }

    public void setShipDay(int shipDay) {
        this.shipDay = shipDay;
    }

    public int getShipMonth() {
        return shipMonth;
    }

    public void setShipMonth(int shipMonth) {
        this.shipMonth = shipMonth;
    }
}