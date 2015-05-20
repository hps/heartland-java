package com.hps.integrator.entities.payplan;

public class HpsPayPlanPaymentMethodCollection {
    int offset;
    int limit;
    int totalMatchingRecords;
    HpsPayPlanPaymentMethod[] results;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotalMatchingRecords() {
        return totalMatchingRecords;
    }

    public HpsPayPlanPaymentMethod[] getResults() {
        return results;
    }
}
