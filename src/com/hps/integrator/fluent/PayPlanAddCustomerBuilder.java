package com.hps.integrator.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.payplan.HpsPayPlanCustomer;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsInvalidRequestException;

public class PayPlanAddCustomerBuilder extends PayPlanCustomerBuilder<PayPlanAddCustomerBuilder, HpsPayPlanCustomer> {
    public PayPlanAddCustomerBuilder(IHpsServicesConfig config, String customerId, String firstName, String lastName, String country) throws HpsInvalidRequestException {
        super(config);

        if (customer == null) {
            throw new HpsInvalidRequestException("customer must be ain instance of HpsPayPlanCustomer");
        }

        this.customer.setCustomerIdentifier(customerId);
        this.customer.setFirstName(firstName);
        this.customer.setLastName(lastName);
        this.customer.setCountry(country);
    }

    @Override
    public HpsPayPlanCustomer execute() throws HpsException {
        String response = this.doTransaction("POST", "customers", this.customer.getEditableFieldsWithValues());
        return this.hydrateObject(response, HpsPayPlanCustomer.class);
    }

    @Override
    protected PayPlanAddCustomerBuilder getBuilder() {
        return this;
    }
}
