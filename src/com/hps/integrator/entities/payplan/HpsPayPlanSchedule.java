package com.hps.integrator.entities.payplan;

import com.hps.integrator.infrastructure.HpsException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HpsPayPlanSchedule extends HpsPayplanResource {
    String scheduleKey;
    String scheduleIdentifier;
    String customerKey;
    String scheduleName;
    String scheduleStatus;
    String paymentMethodKey;
    HpsPayPlanAmount subtotalAmount;
    HpsPayPlanAmount taxAmount;
    HpsPayPlanAmount totalAmount;
    Integer deviceId;
    @FormatDate
    String startDate;
    String processingDateInfo;
    String frequency;
    String duration;
    @FormatDate
    String endDate;
    Integer reprocessingCount;
    String emailReceipt;
    String emailAdvanceNotice;
    String nextProcessingDate;
    String previousProcessingDate;
    Integer approvedTransactionCount;
    Integer failureCount;
    HpsPayPlanAmount totalApprovedAmountToDate;
    Integer numberOfPayments;
    Integer numberOfPaymentsRemaining;
    @FormatDate
    String cancellationDate;
    String scheduleStarted = "false";

    public HpsPayPlanSchedule(){
        this.emailReceipt = "Never";
        this.emailAdvanceNotice = "No";
    }

    public String getScheduleKey() {
        return scheduleKey;
    }

    public void setScheduleKey(String scheduleKey) {
        this.scheduleKey = scheduleKey;
    }

    public String getScheduleIdentifier() {
        return scheduleIdentifier;
    }

    public void setScheduleIdentifier(String scheduleIdentifier) {
        this.scheduleIdentifier = scheduleIdentifier;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public String getPaymentMethodKey() {
        return paymentMethodKey;
    }

    public void setPaymentMethodKey(String paymentMethodKey) {
        this.paymentMethodKey = paymentMethodKey;
    }

    public HpsPayPlanAmount getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(HpsPayPlanAmount subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public HpsPayPlanAmount getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(HpsPayPlanAmount taxAmount) {
        this.taxAmount = taxAmount;
    }

    public HpsPayPlanAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(HpsPayPlanAmount totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getProcessingDateInfo() {
        return processingDateInfo;
    }

    public void setProcessingDateInfo(String processingDateInfo) {
        this.processingDateInfo = processingDateInfo;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getReprocessingCount() {
        return reprocessingCount;
    }

    public void setReprocessingCount(int reprocessingCount) {
        this.reprocessingCount = reprocessingCount;
    }

    public String getEmailReceipt() {
        return emailReceipt;
    }

    public void setEmailReceipt(String emailReceipt) {
        this.emailReceipt = emailReceipt;
    }

    public String getEmailAdvanceNotice() {
        return emailAdvanceNotice;
    }

    public void setEmailAdvanceNotice(String emailAdvanceNotice) {
        this.emailAdvanceNotice = emailAdvanceNotice;
    }

    public String getNextProcessingDate() {
        return nextProcessingDate;
    }

    public void setNextProcessingDate(String nextProcessingDate) {
        this.nextProcessingDate = nextProcessingDate;
    }

    public String getPreviousProcessingDate() {
        return previousProcessingDate;
    }

    public void setPreviousProcessingDate(String previousProcessingDate) {
        this.previousProcessingDate = previousProcessingDate;
    }

    public int getApprovedTransactionCount() {
        return approvedTransactionCount;
    }

    public void setApprovedTransactionCount(int approvedTransactionCount) {
        this.approvedTransactionCount = approvedTransactionCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public HpsPayPlanAmount getTotalApprovedAmountToDate() {
        return totalApprovedAmountToDate;
    }

    public void setTotalApprovedAmountToDate(HpsPayPlanAmount totalApprovedAmountToDate) {
        this.totalApprovedAmountToDate = totalApprovedAmountToDate;
    }

    public Integer getNumberOfPayments() {
        return this.numberOfPayments;
    }

    public void setNumberOfPayments(Integer numberOfPayments) {
        this.numberOfPayments = numberOfPayments;
    }

    public int getNumberOfPaymentsRemaining() {
        return numberOfPaymentsRemaining;
    }

    public void setNumberOfPaymentsRemaining(int numberOfPaymentsRemaining) {
        this.numberOfPaymentsRemaining = numberOfPaymentsRemaining;
    }

    public String getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(String cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getScheduleStarted() {
        return scheduleStarted;
    }

    public void setScheduleStarted(String scheduleStarted) {
        this.scheduleStarted = scheduleStarted;
    }

    private String[] getEditableFields() {
        List<String> list = new ArrayList<String>();

        list.add("scheduleName");
        list.add("scheduleStatus");
        list.add("deviceId");
        list.add("paymentMethodKey");
        list.add("subtotalAmount");
        list.add("taxAmount");
        list.add("numberOfPaymentsRemaining");
        list.add("endDate");
        list.add("cancellationDate");
        list.add("reprocessingCount");
        list.add("emailReceipt");
        list.add("emailAdvanceNotice");
        list.add("processingDateInfo");

        if(this.scheduleStarted.equals("false")) {
            list.add("scheduleIdentifier");
            list.add("startDate");
            list.add("frequency");
            list.add("duration");
        }
        else {
            list.add("nextProcessingDate");
        }

        return list.toArray(new String[list.size()]);
    }

    public HashMap<String, Object> getEditableFieldsWithValues() throws HpsException {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();

            for (String fieldName : this.getEditableFields()) {
                Field field = this.getClass().getDeclaredField(fieldName);
                Object value = field.get(this);
                if(value != null) {
                    if(field.isAnnotationPresent(FormatDate.class))
                        value = formatDate((String)value);
                    map.put(fieldName, value);
                }
            }

            return map;
        }
        catch (NoSuchFieldException e) { throw new HpsException(e.getMessage(), e); }
        catch (IllegalAccessException e) { throw new HpsException(e.getMessage(), e); }
    }

    private String formatDate(String input) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date parsed = formatter.parse(input.substring(0, input.indexOf(' ')));
            formatter.applyPattern("MMddyyyy");
            return formatter.format(parsed);
        } catch(ParseException e) {
            return input;
        } catch(StringIndexOutOfBoundsException e) {
            return input;
        }
    }
}
