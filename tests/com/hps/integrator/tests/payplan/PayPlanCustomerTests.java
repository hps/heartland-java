package com.hps.integrator.tests.payplan;

import com.hps.integrator.entities.payplan.HpsPayPlanCustomer;
import com.hps.integrator.entities.payplan.HpsPayPlanCustomerCollection;
import com.hps.integrator.infrastructure.HpsPayPlanCustomerStatus;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsPayPlanService;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PayPlanCustomerTests {
    HpsPayPlanService service;

    public PayPlanCustomerTests() {
        service = new HpsPayPlanService(TestServicesConfig.validPayPlanConfig());
    }

    private String generateCustomerId() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-SecureSubmit-" + UUID.randomUUID().toString().substring(0, 10);
    }

    private String generateRandomPhone() {
        Random random = new Random();

        String rvalue = "";
        for(int i = 0; i < 7; i++)
            rvalue += random.nextInt(10);
        return rvalue;
    }

    @Test
    public void addCustomer() throws HpsException {
        String id = generateCustomerId();

        HpsPayPlanCustomer newCustomer = new HpsPayPlanCustomer();
        newCustomer.setCustomerStatus(HpsPayPlanCustomerStatus.ACTIVE);

        newCustomer.setCustomerIdentifier(id);
        newCustomer.setFirstName("Bill");
        newCustomer.setLastName("Johnson");
        newCustomer.setCompany("Heartland Payment Systems");
        newCustomer.setCountry("USA");
        newCustomer.setCustomerStatus(HpsPayPlanCustomerStatus.ACTIVE);

        HpsPayPlanCustomer result = this.service.addCustomer(newCustomer);

        // Fluent version (to be fully implemented at a later date)
        // HpsPayPlanCustomer result = this.service.addCustomer(id, "Bill", "Johnson", "USA").withCity("Dallas").execute();

        assertNotNull(result);
        assertNotNull(result.getCustomerKey());
    }

    @Test(expected = HpsException.class)
    public void addNullCustomer() throws HpsException {
        this.service.addCustomer(null);
    }

    @Test
    public void findAllCustomers() throws HpsException {
        HpsPayPlanCustomerCollection results = this.service.findAllCustomers();
        assertNotNull(results);
        assertEquals(true, results.getResults().length > 0);
    }

    @Test
    public void findAllCustomersWithPaging() throws HpsException {
        this.service.setPagination(1, 0);

        HpsPayPlanCustomerCollection results = this.service.findAllCustomers();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);
    }

    @Test
    public void findAllCustomersWithFilter() throws HpsException {
        HashMap<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("customerIdentifier", "SecureSubmit");

        HpsPayPlanCustomerCollection results = this.service.findAllCustomers(searchParams);
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);
    }

    @Test(expected = HpsException.class)
    public void findAllCustomersNull() throws HpsException {
        this.service.findAllCustomers(null);
    }

    @Test
    public void getCustomerByCustomer() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanCustomerCollection results = this.service.findAllCustomers();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanCustomer customer = this.service.getCustomer(results.getResults()[0]);
        assertNotNull(customer);
        assertEquals(results.getResults()[0].getCustomerKey(), customer.getCustomerKey());
    }

    @Test
    public void getCustomerByCustomerKey() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanCustomerCollection results = this.service.findAllCustomers();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanCustomer customer = this.service.getCustomer(results.getResults()[0].getCustomerKey());
        assertNotNull(customer);
        assertEquals(results.getResults()[0].getCustomerKey(), customer.getCustomerKey());
    }

    @Test
    public void editCustomer() throws HpsException {
        HashMap<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("customerIdentifier", "SecureSubmit");

        this.service.setPagination(1, 0);
        HpsPayPlanCustomerCollection results = this.service.findAllCustomers(searchParams);
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);

        // Make the edit
        String phoneDay = "555" + generateRandomPhone();
        HpsPayPlanCustomer customer = results.getResults()[0];
        customer.setPhoneDay(phoneDay);

        HpsPayPlanCustomer result = this.service.editCustomer(customer);
        assertNotNull(result);
        assertEquals(customer.getCustomerKey(), result.getCustomerKey());
        assertEquals(phoneDay, result.getPhoneDay());

        // Verify the edit
        result = this.service.getCustomer(customer.getCustomerKey());
        assertNotNull(result);
        assertEquals(customer.getCustomerKey(), result.getCustomerKey());
        assertEquals(phoneDay, result.getPhoneDay());
    }

    @Test(expected = HpsException.class)
    public void editCustomerWithNull() throws HpsException {
        this.service.editCustomer(null);
    }

    @Test
    public void deleteByCustomer() throws HpsException {
        this.addCustomer();

        this.service.setPagination(1, 0);
        HpsPayPlanCustomerCollection results = this.service.findAllCustomers();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanCustomer delete = this.service.deleteCustomer(results.getResults()[0]);
        assertNull(delete);
    }

    @Test
    public void deleteByCustomerKey() throws HpsException {
        this.addCustomer();

        this.service.setPagination(1, 0);
        HpsPayPlanCustomerCollection results = this.service.findAllCustomers();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanCustomer delete = this.service.deleteCustomer(results.getResults()[0].getCustomerKey());
        assertNull(delete);
    }
}
