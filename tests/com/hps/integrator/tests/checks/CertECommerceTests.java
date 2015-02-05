package com.hps.integrator.tests.checks;

import PosGateway.Exchange.Hps.Enums;
import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.HpsCheckService;
import com.hps.integrator.tests.testdata.TestCheck;
import com.hps.integrator.tests.testdata.TestServicesConfig;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CertECommerceTests {
    // start ACH Debit Consumer Tests

    @Test
    public void check_ACHDebitConsumer1() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(11.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_ACHDebitConsumer2CheckingBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(12.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitConsumer3SavingsPersonal() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setAccountType(Enums.accountTypeType.SAVINGS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(13.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitConsumer4SavingsBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setAccountType(Enums.accountTypeType.SAVINGS);
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(14.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH Debit Consumer Tests

    // start ACH Debit Corporate Tests

    @Test
    public void check_ACHDebitCorporate5() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(15.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_ACHDebitCorporate6CheckingBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(16.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitCorporate7SavingsPersonal() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");
        check.setAccountType(Enums.accountTypeType.SAVINGS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(17.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ACHDebitCorporate8SavingsBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("CCD");
        check.getCheckHolder().setCheckName("Heartland Pays");
        check.setAccountType(Enums.accountTypeType.SAVINGS);
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(18.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH DebitCorporate Tests

    // start ACH eGold Tests

    @Test
    public void check_EGold9() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(11.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EGold10CheckingBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(12.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_EGold11SavingsPersonal() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");
        check.setAccountType(Enums.accountTypeType.SAVINGS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(13.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EGold12SavingsBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");
        check.setAccountType(Enums.accountTypeType.SAVINGS);
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(14.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH eGold Tests

    // start ACH eSilver Tests

    @Test
    public void check_ESilver13() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(15.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ESilver14CheckingBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(16.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_ESilver15SavingsPersonal() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");
        check.setAccountType(Enums.accountTypeType.SAVINGS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(17.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_ESilver16SavingsBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("POP");
        check.setAccountType(Enums.accountTypeType.SAVINGS);
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validEGoldConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(18.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH eSilver Tests

    // start ACH eBronze Tests

    @Test
    public void check_EBronzeVerify17CheckingPersonal() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("eBronze");
        check.setCheckVerify("Y");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify18CheckingBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("eBronze");
        check.setCheckType(Enums.checkTypeType.BUSINESS);
        check.setCheckVerify("Y");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify19SavingsPersonal() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("eBronze");
        check.setAccountType(Enums.accountTypeType.SAVINGS);
        check.setCheckVerify("Y");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify20SavingsBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("eBronze");
        check.setAccountType(Enums.accountTypeType.SAVINGS);
        check.setCheckType(Enums.checkTypeType.BUSINESS);
        check.setCheckVerify("Y");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertMultiUseConfig());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(1));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end ACH eBronze Tests

    // start checks-by-web

    @Test
    public void check_EBronzeVerify21() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("WEB");

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(19.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify22CheckingBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("WEB");
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(20.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    @Test
    public void check_EBronzeVerify23SavingsPersonal() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("WEB");
        check.setAccountType(Enums.accountTypeType.SAVINGS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(21.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());

        HpsCheckResponse voidResponse = service.voidSale(response.getTransactionID());
        assertEquals("0", voidResponse.getResponseCode());
        assertEquals("Transaction Approved", voidResponse.getResponseText());
    }

    @Test
    public void check_EBronzeVerify24SavingsBusiness() throws HpsException, HpsCheckException {
        HpsCheck check = TestCheck.certification();
        check.setSecCode("WEB");
        check.setAccountType(Enums.accountTypeType.SAVINGS);
        check.setCheckType(Enums.checkTypeType.BUSINESS);

        HpsCheckService service = new HpsCheckService(TestServicesConfig.validCertServicesConfigWithDescriptor());
        HpsCheckResponse response = service.sale(TestCheck.certification(), new BigDecimal(22.00));
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
        assertEquals("Transaction Approved", response.getResponseText());
    }

    // end checks-by-web
}
