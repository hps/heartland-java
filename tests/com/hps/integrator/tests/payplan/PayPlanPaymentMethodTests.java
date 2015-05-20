package com.hps.integrator.tests.payplan;

import com.hps.integrator.entities.payplan.HpsPayPlanCustomer;
import com.hps.integrator.entities.payplan.HpsPayPlanPaymentMethod;
import com.hps.integrator.entities.payplan.HpsPayPlanPaymentMethodCollection;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsPayPlanPaymentMethodStatus;
import com.hps.integrator.infrastructure.HpsPayPlanPaymentMethodType;
import com.hps.integrator.services.HpsPayPlanService;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PayPlanPaymentMethodTests {
    HpsPayPlanCustomer customer;
    HpsPayPlanService service;

    public PayPlanPaymentMethodTests() throws HpsException {
        service = new HpsPayPlanService(TestServicesConfig.validPayPlanConfig());
        this.service.setPagination(1, 0);
        this.customer = this.service.findAllCustomers().getResults()[0];
    }

    @Test
    public void addPaymentMethod() throws HpsException {
        HpsPayPlanPaymentMethod newMethod = new HpsPayPlanPaymentMethod();
        newMethod.setCustomerKey(this.customer.getCustomerKey());
        newMethod.setPaymentMethodType(HpsPayPlanPaymentMethodType.CREDIT_CARD);
        newMethod.setNameOnAccount(String.format("%s %s", this.customer.getFirstName(), this.customer.getLastName()));
        newMethod.setAccountNumber("4111111111111111");
        newMethod.setExpirationDate("0120");
        newMethod.setCountry("USA");

        HpsPayPlanPaymentMethod result = this.service.addPaymentMethod(newMethod);
        assertNotNull(result);
        assertNotNull(result.getPaymentMethodKey());
    }

    @Test(expected = HpsException.class)
    public void addNullPaymentMethod() throws HpsException {
        this.service.addPaymentMethod(null);
    }

    @Test
    public void editPaymentMethod() throws HpsException {
        service.setPagination(1, 0);

        HashMap<String, Object> searchFilter = new HashMap<String, Object>();
        searchFilter.put("customerIdentifier", "SecureSubmit");
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods(searchFilter);
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);

        // Make the edit
        HpsPayPlanPaymentMethod method = results.getResults()[0];
        String paymentStatus = method.getPaymentStatus().equals(HpsPayPlanPaymentMethodStatus.ACTIVE) ? HpsPayPlanPaymentMethodStatus.INACTIVE : HpsPayPlanPaymentMethodStatus.ACTIVE;
        method.setPaymentStatus(paymentStatus);

        HpsPayPlanPaymentMethod result = this.service.editPaymentMethod(method);
        assertNotNull(result);
        assertEquals(method.getPaymentMethodKey(), result.getPaymentMethodKey());
        assertEquals(method.getPaymentStatus(), result.getPaymentStatus());

        // Verify the edit
        result = this.service.getPaymentMethod(method.getPaymentMethodKey());
        assertNotNull(result);
        assertEquals(method.getPaymentMethodKey(), result.getPaymentMethodKey());
        assertEquals(method.getPaymentStatus(), result.getPaymentStatus());
    }

    @Test(expected = HpsException.class)
    public void editNullPaymentMethod() throws HpsException {
        this.service.editPaymentMethod(null);
    }

    @Test
    public void findAllPaymentMethods() throws HpsException {
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods();
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);
    }

    @Test
    public void findAllPaymentMethodsWithPaging() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);
    }

    @Test
    public void findAllPaymentMethodsWithFilter() throws HpsException {
        HashMap<String, Object> searchFilter = new HashMap<String, Object>();
        searchFilter.put("customerIdentifier", "SecureSubmit");
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods(searchFilter);
        assertNotNull(results);
        assertEquals(true, results.getResults().length >= 1);
    }

    @Test(expected = HpsException.class)
    public void findAllPaymentMethodsNullSearch() throws HpsException {
        this.service.findAllPaymentMethods(null);
    }

    @Test
    public void getPaymentMethodByMethod() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanPaymentMethod result = this.service.getPaymentMethod(results.getResults()[0]);
        assertNotNull(result);
        assertEquals(results.getResults()[0].getPaymentMethodKey(), result.getPaymentMethodKey());
    }

    @Test
    public void getPaymentMethodByMethodId() throws HpsException {
        this.service.setPagination(1, 0);
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanPaymentMethod result = this.service.getPaymentMethod(results.getResults()[0].getPaymentMethodKey());
        assertNotNull(result);
        assertEquals(results.getResults()[0].getPaymentMethodKey(), result.getPaymentMethodKey());
    }

    @Test
    public void deletePaymentMethodByMethod() throws HpsException {
        this.addPaymentMethod();

        this.service.setPagination(1, 0);
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanPaymentMethod result = this.service.deletePaymentMethod(results.getResults()[0]);
        assertNull(result);
    }

    @Test
    public void deletePaymentMethodByMethodId() throws HpsException {
        this.addPaymentMethod();

        this.service.setPagination(1, 0);
        HpsPayPlanPaymentMethodCollection results = this.service.findAllPaymentMethods();
        assertNotNull(results);
        assertEquals(true, results.getResults().length == 1);

        HpsPayPlanPaymentMethod result = this.service.deletePaymentMethod(results.getResults()[0].getPaymentMethodKey());
        assertNull(result);
    }
}
