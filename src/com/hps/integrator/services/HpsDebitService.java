package com.hps.integrator.services;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.fluent.DebitAddValueBuilder;
import com.hps.integrator.fluent.DebitReturnBuilder;
import com.hps.integrator.fluent.DebitReverseBuilder;
import com.hps.integrator.fluent.DebitReverseUsingBuilder;
import com.hps.integrator.infrastructure.HpsException;

import java.math.BigDecimal;

public class HpsDebitService extends HpsSoapGatewayService {
    public HpsDebitService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }

    public DebitAddValueBuilder addValue(BigDecimal amount, String trackData, String pinBlock) throws HpsException {
        return new DebitAddValueBuilder(servicesConfig, amount, trackData, pinBlock);
    }

    public DebitReturnBuilder refund(BigDecimal amount, String trackData, String pinBlock) throws HpsException {
        return new DebitReturnBuilder(servicesConfig, amount, trackData, pinBlock);
    }

    public DebitReverseUsingBuilder reverse(BigDecimal amount) throws HpsException {
        return new DebitReverseUsingBuilder(new DebitReverseBuilder(servicesConfig, amount));
    }
}
