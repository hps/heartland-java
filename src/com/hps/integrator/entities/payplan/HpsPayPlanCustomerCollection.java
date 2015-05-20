package com.hps.integrator.entities.payplan;

public class HpsPayPlanCustomerCollection {
    int offset;
    int limit;
    int totalMatchingRecords;
    HpsPayPlanCustomer[] results;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotalMatchingRecords() {
        return totalMatchingRecords;
    }

    public HpsPayPlanCustomer[] getResults() {
        return results;
    }
}
