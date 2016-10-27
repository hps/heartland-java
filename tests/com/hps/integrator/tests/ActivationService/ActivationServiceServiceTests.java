package com.hps.integrator.tests.ActivationService;

import com.hps.integrator.entities.activation.HpsDeviceActivationKeyResponse;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsActivationService;
import com.hps.integrator.services.HpsActivationServiceConfig;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActivationServiceServiceTests {

    HpsActivationServiceConfig mConfig;


    public ActivationServiceServiceTests() throws HpsException {
        mConfig = TestServicesConfig.validActivationConfig();
    }


    @Test
    public void HpsActivationService_Activate_Device_Request() throws HpsException
    {
        //NOTE !!! You must now get an activation code manually to run the test.

        String apiKey = "";
        String activationCode = "xxxxxxxxxxx";

        mConfig.setUserName("777700857994");
        mConfig.setPassword("$Test1234");

        HpsActivationService service = new HpsActivationService(mConfig);
        HpsDeviceActivationKeyResponse response = null;

        try {
            response = service.activateDevice("777700857994", activationCode);
            if (response != null)
            {
                apiKey = response.apiKey;
                assertNotNull(apiKey);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
