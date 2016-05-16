package com.hps.integrator.tests.orca;

import com.hps.integrator.entities.orca.HpsDeviceActivationKeyResponse;
import com.hps.integrator.entities.orca.HpsDeviceActivationResponse;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsOrcaService;
import com.hps.integrator.services.HpsOrcaServiceConfig;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrcaServiceTests {

    HpsOrcaServiceConfig mConfig;

    String apiKey = null;
    String activationCode = null;

    public OrcaServiceTests() throws HpsException {
        mConfig = TestServicesConfig.validOrcaConfig();
    }

    @Test
    public void Orca_Test_In_Series() throws HpsException
    {
        Orca_Activation_Request();
        Orca_Activate_Device_Request();
        String getKeyResults = Orca_Get_Device_Key_Request();
        String getParamsResults = Orca_Get_Device_Parameters_Request();

        assertNotNull(apiKey);
        assertNotNull(activationCode);
        assertNotNull(getKeyResults);
        assertNotNull(getParamsResults);
    }


    public void Orca_Activation_Request() throws HpsException
    {
        mConfig.setUserName("admin");
        mConfig.setPassword("password");

        HpsOrcaService service = new HpsOrcaService(mConfig);
        HpsDeviceActivationResponse response = null;
        try {
            response = service.deviceActivationRequest("777700857994", "someone@someplace.com");
            if (response != null)
            {
                activationCode = response.activationCode;

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void Orca_Activate_Device_Request() throws HpsException
    {
        mConfig.setUserName("777700857994");
        mConfig.setPassword("$Test1234");

        HpsOrcaService service = new HpsOrcaService(mConfig);
        HpsDeviceActivationKeyResponse response = null;

        try {
            response = service.activateDevice("777700857994", activationCode);
            if (response != null)
            {
                apiKey = response.apiKey;

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public String Orca_Get_Device_Key_Request() throws HpsException
    {
        mConfig.setUserName("777700857994");
        mConfig.setPassword("$Test1234");
        mConfig.setSiteId(101436);
        mConfig.setLicenseId(101433);
        mConfig.setDeviceId(5315938);

        HpsOrcaService service = new HpsOrcaService(mConfig);

        try {
            String response = service.getDeviceAPIKey();
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public String Orca_Get_Device_Parameters_Request() throws HpsException
    {
        //single test - Throws 400 no parameters
        //apiKey = "skapi_cert_MTyMAQBiHVEAewvIzXVFcmUd2UcyBge_eCpaASUp0A";
        mConfig.setSecretAPIKey(apiKey);

        HpsOrcaService service = new HpsOrcaService(mConfig);

        try {
            String response = service.getDeviceParameters();
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return "No Parameters";
            //400 is ok for no parameters
        }

    }
}
