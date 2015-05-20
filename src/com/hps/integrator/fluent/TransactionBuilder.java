package com.hps.integrator.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;

public abstract class TransactionBuilder<TExecutionResult> {
    protected IHpsServicesConfig servicesConfig;
    public abstract TExecutionResult execute() throws HpsException, HpsCheckException;

    protected TransactionBuilder(IHpsServicesConfig config) {
        servicesConfig = config;
    }
}
