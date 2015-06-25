package com.hps.integrator.fluent;

import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.services.fluent.HpsFluentCheckService;

public class CheckReturnBuilder extends HpsBuilderAbstract<HpsFluentCheckService, HpsCheckResponse> {
    public CheckReturnBuilder(HpsFluentCheckService service) {
        super(service);
    }
}
