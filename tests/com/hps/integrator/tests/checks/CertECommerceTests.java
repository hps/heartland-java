package com.hps.integrator.tests.checks;

import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckHolder;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.AccountTypeType;
import com.hps.integrator.infrastructure.emums.CheckTypeType;
import com.hps.integrator.services.HpsCheckService;
import com.hps.integrator.services.HpsServicesConfig;
import com.hps.integrator.tests.testdata.TestCheck;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CertECommerceTests {
    // start ACH Debit Consumer Tests

    @Test
    public void check_ACHDebitConsumer1() throws HpsException {
        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(11.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_ACHDebitConsumer2CheckingBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(12.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitConsumer3SavingsPersonal() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setAccountType(AccountTypeType.savings);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(13.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitConsumer4SavingsBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setAccountType(AccountTypeType.savings);
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(14.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH Debit Consumer Tests

    // start ACH Debit Corporate Tests

    @Test
    public void check_ACHDebitCorporate5() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(15.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_ACHDebitCorporate6CheckingBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(16.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitCorporate7SavingsPersonal() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");
        check.setAccountType(AccountTypeType.savings);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(17.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitCorporate8SavingsBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");
        check.setAccountType(AccountTypeType.savings);
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(18.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH DebitCorporate Tests

    // start ACH eGold Tests

    @Test
    public void check_EGold9() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(11.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EGold10CheckingBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(12.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_EGold11SavingsPersonal() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");
        check.setAccountType(AccountTypeType.savings);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(13.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EGold12SavingsBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");
        check.setAccountType(AccountTypeType.savings);
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(14.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH eGold Tests

    // start ACH eSilver Tests

    @Test
    public void check_ESilver13() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(15.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ESilver14CheckingBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(16.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_ESilver15SavingsPersonal() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");
        check.setAccountType(AccountTypeType.savings);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(17.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ESilver16SavingsBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("POP");
        check.setAccountType(AccountTypeType.savings);
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(18.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH eSilver Tests

    // start ACH eBronze Tests

    @Test
    public void check_EBronzeVerify17CheckingPersonal() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("eBronze");
        check.setCheckVerify(true);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify18CheckingBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("eBronze");
        check.setCheckType(CheckTypeType.business);
        check.setCheckVerify(true);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify19SavingsPersonal() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("eBronze");
        check.setAccountType(AccountTypeType.savings);
        check.setCheckVerify(true);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify20SavingsBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("eBronze");
        check.setAccountType(AccountTypeType.savings);
        check.setCheckType(CheckTypeType.business);
        check.setCheckVerify(true);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH eBronze Tests

    // start checks-by-web

    @Test
    public void check_EBronzeVerify21() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("WEB");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(19.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify22CheckingBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("WEB");
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(20.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify23SavingsPersonal() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("WEB");
        check.setAccountType(AccountTypeType.savings);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(21.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_EBronzeVerify24SavingsBusiness() throws HpsException {
        HpsCheck check = TestCheck.certCheck();
        check.setSecCode("WEB");
        check.setAccountType(AccountTypeType.savings);
        check.setCheckType(CheckTypeType.business);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validServicesConfig());
        HpsCheckResponse response = service.sale(TestCheck.certCheck(), new BigDecimal(22.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end checks-by-web

    @Test
    public void check_HeartlandACHSale() throws HpsException {
        HpsServicesConfig config = new HpsServicesConfig();
        config.setSecretAPIKey("skapi_cert_MbPdAQBL1l4A2ThZoTBKXEdEG1rIi7KAa6Yskl9Nzg");

        HpsCheckHolder checkHolder = new HpsCheckHolder();
        checkHolder.setCheckName("John Doe");

        HpsCheck check = new HpsCheck();
        check.setAccountNumber("12345678");
        check.setRoutingNumber("121122676");
        check.setSecCode("WEB");
        check.setAccountType(AccountTypeType.checking);
        check.setCheckType(CheckTypeType.personal);
        check.setCheckHolder(checkHolder);

        HpsCheckService service = new HpsCheckService(config);
        HpsCheckResponse response = service.sale(check, new BigDecimal(22.00));

        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals(true, response.getResponseText().contains("Transaction Approved"));
    }
}
