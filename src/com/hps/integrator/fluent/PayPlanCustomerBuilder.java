package com.hps.integrator.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.payplan.HpsPayPlanCustomer;

public abstract class PayPlanCustomerBuilder<TBuilder, TExecutionResult> extends PayPlanTransactionBuilder<TBuilder, TExecutionResult> {
    protected HpsPayPlanCustomer customer = new HpsPayPlanCustomer();

    protected PayPlanCustomerBuilder(IHpsServicesConfig config) {
        super(config);
    }

    public TBuilder withCompany(String company) {
        customer.setCompany(company);
        return getBuilder();
    }

    public TBuilder withAddress1(String address1) {
        customer.setAddressLine1(address1);
        return getBuilder();
    }

    public TBuilder withAddress2(String address2) {
        customer.setAddressLine1(address2);
        return getBuilder();
    }

    public TBuilder withCity(String city) {
        customer.setCity(city);
        return getBuilder();
    }
}
