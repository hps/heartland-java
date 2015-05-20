package com.hps.integrator.tests.payplan;

import com.hps.integrator.entities.payplan.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.services.HpsPayPlanService;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PayPlanScheduleTests {
    HpsPayPlanPaymentMethod paymentMethod;
    HpsPayPlanService service;

    public PayPlanScheduleTests() throws HpsException {
        service = new HpsPayPlanService(TestServicesConfig.validPayPlanConfig());

        this.service.setPagination(1, 0);
        HashMap<String, Object> searchFilters = new HashMap<String, Object>();
        searchFilters.put("customerIdentifier", "SecureSubmit");
        searchFilters.put("paymentStatus", HpsPayPlanPaymentMethodStatus.ACTIVE);
        HpsPayPlanPaymentMethodCollection paymentmethods = this.service.findAllPaymentMethods(searchFilters);
        this.paymentMethod = paymentmethods.getResults()[0];
    }

    private String generateScheduleId() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-SecureSubmit-" + UUID.randomUUID().toString().substring(0, 10);
    }
    private String getLastDayOfMonth() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date lastDayOfMonth = calendar.getTime();

        return new SimpleDateFormat("MMddyyyy").format(lastDayOfMonth);
    }

    @Test
    public void addSchedule() throws HpsException {
        String id = this.generateScheduleId();
        String date = getLastDayOfMonth();

        HpsPayPlanSchedule schedule = new HpsPayPlanSchedule();
        schedule.setScheduleIdentifier(id);
        schedule.setCustomerKey(this.paymentMethod.getCustomerKey());
        schedule.setPaymentMethodKey(this.paymentMethod.getPaymentMethodKey());
        schedule.setSubtotalAmount(new HpsPayPlanAmount("100"));
        schedule.setStartDate(date);
        schedule.setFrequency(HpsPayPlanScheduleFrequency.WEEKLY);
        schedule.setDuration(HpsPayPlanScheduleDuration.LIMITED_NUMBER);
        schedule.setNumberOfPayments(3);
        schedule.setReprocessingCount(2);
        schedule.setEmailReceipt("Never");
        schedule.setEmailAdvanceNotice("No");
        schedule.setScheduleStatus(HpsPayPlanScheduleStatus.ACTIVE);

        HpsPayPlanSchedule result = this.service.addSchedule(schedule);
        assertNotNull(result);
        assertNotNull(result.getScheduleKey());
    }

    @Test(expected = HpsException.class)
    public void addNullSchedule() throws HpsException {
        this.service.addSchedule(null);
    }

    @Test
    public void editSchedule() throws HpsException {
        service.setPagination(1, 0);

        HashMap<String, Object> searchFilter = new HashMap<String, Object>();
        searchFilter.put("scheduleIdentifier", "SecureSubmit");
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules(searchFilter);
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);

        // Make the edit
        HpsPayPlanSchedule schedule = results.getResults()[0];
        String scheduleStatus = schedule.getScheduleStatus().equals(HpsPayPlanScheduleStatus.ACTIVE) ? HpsPayPlanScheduleStatus.INACTIVE : HpsPayPlanScheduleStatus.ACTIVE;
        schedule.setScheduleStatus(scheduleStatus);

        HpsPayPlanSchedule result = this.service.editSchedule(schedule);
        assertNotNull(result);
        assertEquals(schedule.getScheduleKey(), result.getScheduleKey());
        assertEquals(schedule.getScheduleStatus(), result.getScheduleStatus());

        // Verify the edit
        result = this.service.getSchedule(schedule.getScheduleKey());
        assertNotNull(result);
        assertEquals(schedule.getScheduleKey(), result.getScheduleKey());
        assertEquals(schedule.getScheduleStatus(), result.getScheduleStatus());
    }

    @Test(expected = HpsException.class)
    public void editNullSchedule() throws HpsException {
        this.service.editSchedule(null);
    }

    @Test
    public void findAllSchedule() throws HpsException {
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules();
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);
    }

    @Test
    public void findAllSchedulesWithPaging() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);
    }

    @Test
    public void findAllSchedulesWithFilter() throws HpsException {
        HashMap<String, Object> searchFilter = new HashMap<String, Object>();
        searchFilter.put("scheduleIdentifier", "SecureSubmit");
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules(searchFilter);
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);
    }

    @Test(expected = HpsException.class)
    public void findAllSchedulesNullSearch() throws HpsException {
        this.service.findAllSchedules(null);
    }

    @Test
    public void getScheduleBySchedule() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanSchedule result = this.service.getSchedule(results.getResults()[0]);
        assertNotNull(result);
        assertEquals(results.getResults()[0].getScheduleKey(), result.getScheduleKey());
    }

    @Test
    public void getScheduleByScheduleId() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanSchedule result = this.service.getSchedule(results.getResults()[0].getScheduleKey());
        assertNotNull(result);
        assertEquals(results.getResults()[0].getScheduleKey(), result.getScheduleKey());
    }

    @Test
    public void deleteScheduleBySchedule() throws HpsException {
        this.addSchedule();

        this.service.setPagination(1, 0);
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanSchedule result = this.service.deleteSchedule(results.getResults()[0]);
        assertNull(result);
    }

    @Test
    public void deleteScheduleByScheduleId() throws HpsException {
        this.addSchedule();

        this.service.setPagination(1, 0);
        HpsPayPlanScheduleCollection results = this.service.findAllSchedules();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanSchedule result = this.service.deleteSchedule(results.getResults()[0].getScheduleKey());
        assertNull(result);
    }
}
