package com.hps.integrator.fluent;

import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.services.fluent.HpsFluentCheckService;

public class CheckOverrideBuilder extends HpsBuilderAbstract<HpsFluentCheckService, HpsCheckResponse> {
    public CheckOverrideBuilder(HpsFluentCheckService service) {
        super(service);
    }
}
