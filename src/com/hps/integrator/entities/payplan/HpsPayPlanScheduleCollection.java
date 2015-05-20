package com.hps.integrator.entities.payplan;

public class HpsPayPlanScheduleCollection {
    int offset;
    int limit;
    int totalMatchingRecords;
    HpsPayPlanSchedule[] results;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotalMatchingRecords() {
        return totalMatchingRecords;
    }

    public HpsPayPlanSchedule[] getResults() {
        return results;
    }
}
