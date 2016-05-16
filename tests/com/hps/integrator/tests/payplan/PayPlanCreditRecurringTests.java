package com.hps.integrator.tests.payplan;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.payplan.HpsPayPlanSchedule;
import com.hps.integrator.entities.payplan.HpsPayPlanScheduleCollection;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsPayPlanPaymentMethodStatus;
import com.hps.integrator.infrastructure.HpsPayPlanScheduleStatus;
import com.hps.integrator.services.HpsCreditService;
import com.hps.integrator.services.HpsPayPlanService;
import com.hps.integrator.services.HpsPayPlanServiceConfig;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PayPlanCreditRecurringTests {
    HpsCreditService service;

    HpsPayPlanSchedule schedule;

    public PayPlanCreditRecurringTests() throws HpsException {
        HpsPayPlanServiceConfig config = TestServicesConfig.validPayPlanConfig();
        HpsPayPlanService payPlanService = new HpsPayPlanService(config);

        payPlanService.setPagination(1, 0);
        HashMap<String, Object> searchFilters = new HashMap<String, Object>();
        searchFilters.put("scheduleIdentifier", "SecureSubmit");
        searchFilters.put("scheduleStatus", HpsPayPlanScheduleStatus.ACTIVE);
        HpsPayPlanScheduleCollection results = payPlanService.findAllSchedules(searchFilters);
        this.schedule = results.getResults()[0];

        this.service = new HpsCreditService(config);
    }
}
