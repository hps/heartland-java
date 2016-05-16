package com.hps.integrator.services;

import com.hps.integrator.entities.payplan.*;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import com.hps.integrator.infrastructure.HpsPayPlanPaymentMethodType;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;

public class HpsPayPlanService extends HpsRestGatewayService {
    public HpsPayPlanService(HpsPayPlanServiceConfig config) {
        super(config);

        byte[] encoded = Base64.encodeBase64(servicesConfig.getSecretAPIKey().getBytes());
        String auth = String.format("Basic %s", new String(encoded));
        _authHeader.put("Authorization", auth);

        String[] components = config.getSecretAPIKey().split("_");
        String env = components[1].toLowerCase();

        if (env.equals("prod")) {
            config.setServiceUri(config.ProdUrl);
        } else if (env.equals("cert")) {
            config.setServiceUri(config.CertUrl);
        } else {
            config.setServiceUri(config.UatUrl);
        }

    }

    private HashMap<String, String> _authHeader = new HashMap<String, String>();
    private HashMap<String, String> _pagination = null;


    /* CUSTOMER CALLS */
    public void setPagination(int limit, int offset)
    {
        _pagination = new HashMap<String, String>();
        _pagination.put("limit", Integer.toString(limit));
        _pagination.put("offset", Integer.toString(offset));

    }

    public void resetPagination()
    {
        _pagination = null;
    }

//    Fluent implementation sample (TBD)
//    public PayPlanAddCustomerBuilder addCustomer(String customerId, String firstName, String lastName, String country) throws HpsException {
//        return new PayPlanAddCustomerBuilder(servicesConfig, customerId, firstName, lastName, country);
//    }

    public HpsPayPlanCustomer addCustomer(HpsPayPlanCustomer customer) throws HpsException {
        if (customer == null)
            throw new HpsInvalidRequestException("customer must be ain instance of HpsPayPlanCustomer");

        String response = this.doRequest("POST", "customers", customer.getEditableFieldsWithValues(), _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanCustomer.class);
    }

    public HpsPayPlanCustomer editCustomer(HpsPayPlanCustomer customer) throws HpsException {
        if (customer == null)
            throw new HpsInvalidRequestException("customer must be ain instance of HpsPayPlanCustomer");

        String response = this.doRequest("PUT", "customers/" + customer.getCustomerKey(), customer.getEditableFieldsWithValues(), _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanCustomer.class);
    }

    public HpsPayPlanCustomerCollection findAllCustomers() throws HpsException {
        return this.findAllCustomers(new HashMap<String, Object>());
    }

    public HpsPayPlanCustomerCollection findAllCustomers(HashMap<String, Object> searchFields) throws HpsException {
        if (searchFields == null)
            throw new HpsInvalidRequestException("searchFields cannot be null.");

        String response = this.doRequest("POST", "searchCustomers", searchFields, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanCustomerCollection.class);
    }

    public HpsPayPlanCustomer getCustomer(HpsPayPlanCustomer customer) throws HpsException {
        if (customer == null)
            throw new HpsInvalidRequestException("customer must be ain instance of HpsPayPlanCustomer");

        return this.getCustomer(customer.getCustomerKey());
    }

    public HpsPayPlanCustomer getCustomer(String customerId) throws HpsException {
        String response = this.doRequest("GET", "customers/" + customerId, null, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanCustomer.class);
    }

    public HpsPayPlanCustomer deleteCustomer(HpsPayPlanCustomer customer) throws HpsException {
        if (customer == null)
            throw new HpsInvalidRequestException("customer must be ain instance of HpsPayPlanCustomer");

        return this.deleteCustomer(customer.getCustomerKey(), false);
    }

    public HpsPayPlanCustomer deleteCustomer(HpsPayPlanCustomer customer, boolean forceDelete) throws HpsException {
        if (customer == null)
            throw new HpsInvalidRequestException("customer must be ain instance of HpsPayPlanCustomer");

        return this.deleteCustomer(customer.getCustomerKey(), forceDelete);
    }

    public HpsPayPlanCustomer deleteCustomer(String customerId) throws HpsException {
        return this.deleteCustomer(customerId, false);
    }

    public HpsPayPlanCustomer deleteCustomer(String customerId, boolean forceDelete) throws HpsException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("forceDelete", forceDelete);

        String response = this.doRequest("DELETE", "customers/" + customerId, data, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanCustomer.class);
    }

    /* PAYMENT METHODS */

    public HpsPayPlanPaymentMethod addPaymentMethod(HpsPayPlanPaymentMethod method) throws HpsException {
        if (method == null)
            throw new HpsInvalidRequestException("method must be ain instance of HpsPayPlanPaymentMethod");

        String response;
        if (method.getPaymentMethodType().equals(HpsPayPlanPaymentMethodType.ACH)) {
            response = this.addAch(method);
        } else {
            response = this.addCreditCard(method);
        }
        return this.hydrateObject(response, HpsPayPlanPaymentMethod.class);
    }

    public HpsPayPlanPaymentMethod editPaymentMethod(HpsPayPlanPaymentMethod method) throws HpsException {
        if (method == null)
            throw new HpsInvalidRequestException("method must be ain instance of HpsPayPlanPaymentMethod");

        String response;
        if (method.getPaymentMethodType().equals(HpsPayPlanPaymentMethodType.ACH)) {
            response = this.editAch(method);
        } else {
            response = this.editCreditCard(method);
        }
        return this.hydrateObject(response, HpsPayPlanPaymentMethod.class);
    }

    public HpsPayPlanPaymentMethodCollection findAllPaymentMethods() throws HpsException {
        return this.findAllPaymentMethods(new HashMap<String, Object>());
    }

    public HpsPayPlanPaymentMethodCollection findAllPaymentMethods(HashMap<String, Object> searchFields) throws HpsException {
        if (searchFields == null)
            throw new HpsInvalidRequestException("searchFields cannot be null");
        String response = this.doRequest("POST", "searchPaymentMethods", searchFields, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanPaymentMethodCollection.class);
    }

    public HpsPayPlanPaymentMethod getPaymentMethod(HpsPayPlanPaymentMethod method) throws HpsException {
        if (method == null)
            throw new HpsInvalidRequestException("method must be an instance of HpsPayPlanPaymentMethod.");

        return this.getPaymentMethod(method.getPaymentMethodKey());
    }

    public HpsPayPlanPaymentMethod getPaymentMethod(String methodId) throws HpsException {
        String response = this.doRequest("GET", "paymentMethods/" + methodId, null, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanPaymentMethod.class);
    }

    public HpsPayPlanPaymentMethod deletePaymentMethod(HpsPayPlanPaymentMethod method) throws HpsException {
        if (method == null)
            throw new HpsInvalidRequestException("method must be an instance of HpsPayPlanPaymentMethod.");

        return this.deletePaymentMethod(method.getPaymentMethodKey(), false);
    }

    public HpsPayPlanPaymentMethod deletePaymentMethod(HpsPayPlanPaymentMethod method, boolean forceDelete) throws HpsException {
        if (method == null)
            throw new HpsInvalidRequestException("method must be an instance of HpsPayPlanPaymentMethod.");

        return this.deletePaymentMethod(method.getPaymentMethodKey(), forceDelete);
    }

    public HpsPayPlanPaymentMethod deletePaymentMethod(String methodId) throws HpsException {
        return this.deletePaymentMethod(methodId, false);
    }

    public HpsPayPlanPaymentMethod deletePaymentMethod(String methodId, boolean forceDelete) throws HpsException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("forceDelete", forceDelete);

        String response = this.doRequest("DELETE", "paymentMethods/" + methodId, data, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanPaymentMethod.class);
    }

    private String addCreditCard(HpsPayPlanPaymentMethod method) throws HpsException {
        HashMap<String, Object> data = method.getEditableFieldsWithValues();
        data.put("customerKey", method.getCustomerKey());
        data.put("accountNumber", method.getAccountNumber());
        String response =  this.doRequest("POST", "paymentMethodsCreditCard", data, _authHeader, _pagination);
        resetPagination();
        return  response;
    }

    private String editCreditCard(HpsPayPlanPaymentMethod method) throws HpsException {
        HashMap<String, Object> data = method.getEditableFieldsWithValues();
        String response = this.doRequest("PUT", "paymentMethodsCreditCard/" + method.getPaymentMethodKey(), data, _authHeader, _pagination);
        resetPagination();
        return response;
    }

    private String addAch(HpsPayPlanPaymentMethod method) throws HpsException {
        HashMap<String, Object> data = method.getEditableFieldsWithValues();
        data.put("customerKey", method.getCustomerKey());
        String response = this.doRequest("POST", "paymentMethodsACH", data, _authHeader, _pagination);
        resetPagination();
        return response;
    }

    private String editAch(HpsPayPlanPaymentMethod method) throws HpsException {
        HashMap<String, Object> data = method.getEditableFieldsWithValues();
        String response = this.doRequest("PUT", "paymentMethodsACH/" + method.getPaymentMethodKey(), data, _authHeader, _pagination);
        resetPagination();
        return response;
    }

    /* SCHEDULE METHODS */

    public HpsPayPlanSchedule addSchedule(HpsPayPlanSchedule schedule) throws HpsException {
        if (schedule == null)
            throw new HpsInvalidRequestException("schedule must be an instance of HpsPayPlanSchedule.");

        HashMap<String, Object> data = schedule.getEditableFieldsWithValues();
        data.put("customerKey", schedule.getCustomerKey());
        data.put("numberOfPayments", schedule.getNumberOfPayments());

        String response = this.doRequest("POST", "schedules", data, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanSchedule.class);
    }

    public HpsPayPlanSchedule editSchedule(HpsPayPlanSchedule schedule) throws HpsException {
        if (schedule == null)
            throw new HpsInvalidRequestException("schedule must be an instance of HpsPayPlanSchedule.");

        schedule.setEndDate((schedule.getEndDate() == null || schedule.getEndDate().length() == 0)
                ? null : schedule.getEndDate());
        schedule.setStartDate((schedule.getStartDate() == null || schedule.getStartDate().length() == 0)
                ? null : schedule.getStartDate());
        schedule.setNextProcessingDate((schedule.getNextProcessingDate() == null || schedule.getNextProcessingDate().length() == 0)
                ? null : schedule.getNextProcessingDate());
        schedule.setPreviousProcessingDate((schedule.getPreviousProcessingDate() == null || schedule.getPreviousProcessingDate().length() == 0)
                ? null : schedule.getPreviousProcessingDate());

        String response = this.doRequest("PUT", "schedules/" + schedule.getScheduleKey(), schedule.getEditableFieldsWithValues(), _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanSchedule.class);
    }

    public HpsPayPlanScheduleCollection findAllSchedules() throws HpsException {
        return this.findAllSchedules(new HashMap<String, Object>());
    }

    public HpsPayPlanScheduleCollection findAllSchedules(HashMap<String, Object> searchFields) throws HpsException {
        if (searchFields == null)
            throw new HpsInvalidRequestException("searchFields cannot be null");
        String response = this.doRequest("POST", "searchSchedules", searchFields, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanScheduleCollection.class);
    }

    public HpsPayPlanSchedule getSchedule(HpsPayPlanSchedule schedule) throws HpsException {
        if (schedule == null)
            throw new HpsInvalidRequestException("method must be an instance of HpsPayPlanSchedule.");

        return this.getSchedule(schedule.getScheduleKey());
    }

    public HpsPayPlanSchedule getSchedule(String scheduleId) throws HpsException {
        String response = this.doRequest("GET", "schedules/" + scheduleId, null, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanSchedule.class);
    }

    public HpsPayPlanSchedule deleteSchedule(HpsPayPlanSchedule schedule) throws HpsException {
        if (schedule == null)
            throw new HpsInvalidRequestException("method must be an instance of HpsPayPlanPaymentMethod.");

        return this.deleteSchedule(schedule.getScheduleKey(), false);
    }

    public HpsPayPlanSchedule deleteSchedule(HpsPayPlanSchedule schedule, boolean forceDelete) throws HpsException {
        if (schedule == null)
            throw new HpsInvalidRequestException("method must be an instance of HpsPayPlanPaymentMethod.");

        return this.deleteSchedule(schedule.getScheduleKey(), forceDelete);
    }

    public HpsPayPlanSchedule deleteSchedule(String scheduleId) throws HpsException {
        return this.deleteSchedule(scheduleId, false);
    }

    public HpsPayPlanSchedule deleteSchedule(String scheduleId, boolean forceDelete) throws HpsException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("forceDelete", forceDelete);

        String response = this.doRequest("DELETE", "schedules/" + scheduleId, data, _authHeader, _pagination);
        resetPagination();
        return this.hydrateObject(response, HpsPayPlanSchedule.class);
    }
}
