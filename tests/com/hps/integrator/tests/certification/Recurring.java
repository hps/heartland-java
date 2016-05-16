package com.hps.integrator.tests.certification;

import com.hps.integrator.entities.batch.HpsBatch;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.payplan.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.services.*;
import com.hps.integrator.services.fluent.HpsFluentCheckService;
import com.hps.integrator.services.fluent.HpsFluentCreditService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Recurring {
    private HpsPayPlanService service;
    private HpsBatchService batchService;
    private HpsFluentCreditService creditService;
    private HpsFluentCheckService checkService;

    private static String customerPersonKey;
    private static String customerCompanyKey;
    private static String paymentMethodKeyVisa;
    private static String paymentMethodKeyMasterCard;
    private static String paymentMethodKeyCheckPPD;
    private static String paymentMethodKeyCheckCCD;
    private static String scheduleKeyVisa;
    private static String scheduleKeyMasterCard;
    private static String scheduleKeyCheckPPD;
    private static String scheduleKeyCheckCCD;

    private static String todayDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
    private static String identifierBase = "%s-%s" + UUID.randomUUID().toString().substring(0, 10);

    public Recurring() throws HpsException {
        HpsPayPlanServiceConfig config = new HpsPayPlanServiceConfig();
        config.setSecretAPIKey("skapi_cert_MTyMAQBiHVEAewvIzXVFcmUd2UcyBge_eCpaASUp0A");

        this.service = new HpsPayPlanService(config);
        this.batchService = new HpsBatchService(config);
        this.creditService = new HpsFluentCreditService(config, true);
        this.checkService = new HpsFluentCheckService(config, true);
    }

    private String getIdentifier(String identifier){
        String rvalue = String.format(identifierBase, todayDate, identifier);
        System.out.println(rvalue);
        return rvalue;
    }

    @Test
    public void test_000_CloseBatch() {
        try{
            HpsBatch response = this.batchService.closeBatch();
            if(response == null)
                fail("Response is null");
            System.out.println(String.format("Batch ID: %s", response.getId()));
            System.out.println(String.format("Sequence Number: %s", response.getSequenceNumber()));
        }
        catch(HpsException exc){
            if (!exc.getMessage().equals("Transaction was rejected because it requires a batch to be open."))
                fail(exc.getMessage());
        }
    }

//    @Test
//    public void test_000_CleanUp() throws HpsException {
//        // Remove Schedules
//        HpsPayPlanScheduleCollection schResults = this.service.findAllSchedules();
//        for(HpsPayPlanSchedule schedule : schResults.getResults()) {
//            this.service.deleteSchedule(schedule, true);
//        }
//
//        // Remove Payment Methods
//        HpsPayPlanPaymentMethodCollection pmResults = this.service.findAllPaymentMethods();
//        for(HpsPayPlanPaymentMethod pm : pmResults.getResults()) {
//            this.service.deletePaymentMethod(pm, true);
//        }
//
//        // Remove Customers
//        HpsPayPlanCustomerCollection custResults = this.service.findAllCustomers();
//        for(HpsPayPlanCustomer c : custResults.getResults()){
//            this.service.deleteCustomer(c, true);
//        }
//    }

    // CUSTOMER SETUP

    @Test
    public void test_001_AddCustomerPerson() throws HpsException {
        HpsPayPlanCustomer customer = new HpsPayPlanCustomer();
        customer.setCustomerIdentifier(this.getIdentifier("Person"));
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setCustomerStatus(HpsPayPlanCustomerStatus.ACTIVE);
        customer.setPrimaryEmail("john.doe@email.com");
        customer.setAddressLine1("123 Main St");
        customer.setCity("Dallas");
        customer.setStateProvince("TX");
        customer.setZipPostalCode("98765");
        customer.setCountry("USA");
        customer.setPhoneDay("5551112222");

        HpsPayPlanCustomer response = this.service.addCustomer(customer);
        assertNotNull(response);
        assertNotNull(response.getCustomerKey());
        customerPersonKey = response.getCustomerKey();
    }

    @Test
    public void test_002_AddCustomerBusiness() throws HpsException {
        HpsPayPlanCustomer customer = new HpsPayPlanCustomer();
        customer.setCustomerIdentifier(this.getIdentifier("Business"));
        customer.setCompany("AcmeCo");
        customer.setCustomerStatus(HpsPayPlanCustomerStatus.ACTIVE);
        customer.setPrimaryEmail("acme@email.com");
        customer.setAddressLine1("987 Elm St");
        customer.setCity("Princeton");
        customer.setStateProvince("NJ");
        customer.setZipPostalCode("12345");
        customer.setCountry("USA");
        customer.setPhoneDay("5551112222");

        HpsPayPlanCustomer response = this.service.addCustomer(customer);
        assertNotNull(response);
        assertNotNull(response.getCustomerKey());
        customerCompanyKey = response.getCustomerKey();
    }

    // PAYMENT METHOD SETUP

    @Test
    public void test_003_AddPaymentCreditVisa() throws HpsException {
        HpsPayPlanPaymentMethod paymentMethod = new HpsPayPlanPaymentMethod();
        paymentMethod.setPaymentMethodIdentifier(this.getIdentifier("CreditV"));
        paymentMethod.setPaymentMethodType(HpsPayPlanPaymentMethodType.CREDIT_CARD);
        paymentMethod.setNameOnAccount("John Doe");
        paymentMethod.setAccountNumber("4012002000060016");
        paymentMethod.setExpirationDate("1225");
        paymentMethod.setCustomerKey(customerPersonKey);
        paymentMethod.setCountry("USA");

        HpsPayPlanPaymentMethod response = this.service.addPaymentMethod(paymentMethod);
        assertNotNull(response);
        assertNotNull(response.getPaymentMethodKey());
        paymentMethodKeyVisa = response.getPaymentMethodKey();
    }

    @Test
    public void test_004_AddPaymentCreditMasterCard() throws HpsException {
        HpsPayPlanPaymentMethod paymentMethod = new HpsPayPlanPaymentMethod();
        paymentMethod.setPaymentMethodIdentifier(this.getIdentifier("CreditMC"));
        paymentMethod.setPaymentMethodType(HpsPayPlanPaymentMethodType.CREDIT_CARD);
        paymentMethod.setNameOnAccount("John Doe");
        paymentMethod.setAccountNumber("5473500000000014");
        paymentMethod.setExpirationDate("1225");
        paymentMethod.setCustomerKey(customerPersonKey);
        paymentMethod.setCountry("USA");

        HpsPayPlanPaymentMethod response = this.service.addPaymentMethod(paymentMethod);
        assertNotNull(response);
        assertNotNull(response.getPaymentMethodKey());
        paymentMethodKeyMasterCard = response.getPaymentMethodKey();
    }

    @Test
    public void test_005_AddPaymentCheckPPD() throws HpsException {
        HpsPayPlanPaymentMethod paymentMethod = new HpsPayPlanPaymentMethod();
        paymentMethod.setPaymentMethodIdentifier(this.getIdentifier("CheckPPD"));
        paymentMethod.setPaymentMethodType(HpsPayPlanPaymentMethodType.ACH);
        paymentMethod.setAchType("Checking");
        paymentMethod.setAccountType("Personal");
        paymentMethod.setTelephoneIndicator(false);
        paymentMethod.setRoutingNumber("490000018");
        paymentMethod.setNameOnAccount("John Doe");
        paymentMethod.setDriversLicenseNumber("7418529630");
        paymentMethod.setDriversLicenseState("TX");
        paymentMethod.setAccountNumber("24413815");
        paymentMethod.setAddressLine1("123 Main St");
        paymentMethod.setCity("Dallas");
        paymentMethod.setStateProvince("TX");
        paymentMethod.setZipPostalCode("98765");
        paymentMethod.setCustomerKey(customerPersonKey);
        paymentMethod.setCountry("USA");
        paymentMethod.setAccountHolderYob("1989");

        HpsPayPlanPaymentMethod response = this.service.addPaymentMethod(paymentMethod);
        assertNotNull(response);
        assertNotNull(response.getPaymentMethodKey());
        paymentMethodKeyCheckPPD = response.getPaymentMethodKey();
    }

    @Test
    public void test_006_AddPaymentCheckCCD() throws HpsException {
        HpsPayPlanPaymentMethod paymentMethod = new HpsPayPlanPaymentMethod();
        paymentMethod.setPaymentMethodIdentifier(this.getIdentifier("CheckCCD"));
        paymentMethod.setPaymentMethodType(HpsPayPlanPaymentMethodType.ACH);
        paymentMethod.setAchType("Checking");
        paymentMethod.setAccountType("Business");
        paymentMethod.setTelephoneIndicator(false);
        paymentMethod.setRoutingNumber("490000018");
        paymentMethod.setNameOnAccount("Acme Co");
        paymentMethod.setDriversLicenseNumber("3692581470");
        paymentMethod.setDriversLicenseState("TX");
        paymentMethod.setAccountNumber("24413815");
        paymentMethod.setAddressLine1("987 Elm St");
        paymentMethod.setCity("Princeton");
        paymentMethod.setStateProvince("NJ");
        paymentMethod.setZipPostalCode("13245");
        paymentMethod.setCustomerKey(customerCompanyKey);
        paymentMethod.setCountry("USA");
        paymentMethod.setAccountHolderYob("1989");

        HpsPayPlanPaymentMethod response = this.service.addPaymentMethod(paymentMethod);
        assertNotNull(response);
        assertNotNull(response.getPaymentMethodKey());
        paymentMethodKeyCheckCCD = response.getPaymentMethodKey();
    }

    // PAYMENT SETUP - DECLINED

    @Test(expected = HpsException.class)
    public void test_007_AddPaymentCheckPPD() throws HpsException {
        HpsPayPlanPaymentMethod paymentMethod = new HpsPayPlanPaymentMethod();
        paymentMethod.setPaymentMethodIdentifier(this.getIdentifier("CheckPPD"));
        paymentMethod.setPaymentMethodType(HpsPayPlanPaymentMethodType.ACH);
        paymentMethod.setAchType("Checking");
        paymentMethod.setAccountType("Personal");
        paymentMethod.setTelephoneIndicator(false);
        paymentMethod.setRoutingNumber("490000018");
        paymentMethod.setNameOnAccount("John Doe");
        paymentMethod.setDriversLicenseNumber("7418529630");
        paymentMethod.setDriversLicenseState("TX");
        paymentMethod.setAccountNumber("24413815");
        paymentMethod.setAddressLine1("123 Main St");
        paymentMethod.setCity("Dallas");
        paymentMethod.setStateProvince("TX");
        paymentMethod.setZipPostalCode("98765");
        paymentMethod.setCustomerKey(customerPersonKey);
        paymentMethod.setCountry("USA");
        paymentMethod.setAccountHolderYob("1989");

        this.service.addPaymentMethod(paymentMethod);
    }

    // Recurring Billing using PayPlan - Managed Schedule

    @Test
    public void test_008_AddScheduleCreditVisa() throws HpsException {
        HpsPayPlanSchedule schedule = new HpsPayPlanSchedule();
        schedule.setScheduleIdentifier(this.getIdentifier("CreditV"));
        schedule.setCustomerKey(customerPersonKey);
        schedule.setScheduleStatus(HpsPayPlanScheduleStatus.ACTIVE);
        schedule.setPaymentMethodKey(paymentMethodKeyVisa);
        schedule.setSubtotalAmount(new HpsPayPlanAmount("3001"));
        schedule.setStartDate("02012027");
        schedule.setFrequency(HpsPayPlanScheduleFrequency.WEEKLY);
        schedule.setDuration(HpsPayPlanScheduleDuration.ONGOING);
        schedule.setReprocessingCount(1);

        HpsPayPlanSchedule response = this.service.addSchedule(schedule);
        assertNotNull(response);
        assertNotNull(response.getScheduleKey());
        scheduleKeyVisa = response.getScheduleKey();
    }

    @Test
    public void test_009_AddScheduleCreditMasterCard() throws HpsException {
        HpsPayPlanSchedule schedule = new HpsPayPlanSchedule();
        schedule.setScheduleIdentifier(this.getIdentifier("CreditMC"));
        schedule.setCustomerKey(customerPersonKey);
        schedule.setScheduleStatus(HpsPayPlanScheduleStatus.ACTIVE);
        schedule.setPaymentMethodKey(paymentMethodKeyMasterCard);
        schedule.setSubtotalAmount(new HpsPayPlanAmount("3002"));
        schedule.setStartDate("02012027");
        schedule.setFrequency(HpsPayPlanScheduleFrequency.WEEKLY);
        schedule.setDuration(HpsPayPlanScheduleDuration.END_DATE);
        schedule.setEndDate("04012027");
        schedule.setReprocessingCount(2);

        HpsPayPlanSchedule response = this.service.addSchedule(schedule);
        assertNotNull(response);
        assertNotNull(response.getScheduleKey());
        scheduleKeyMasterCard = response.getScheduleKey();
    }

    @Test
    public void test_010_AddScheduleCCheckPPD() throws HpsException {
        HpsPayPlanSchedule schedule = new HpsPayPlanSchedule();
        schedule.setScheduleIdentifier(this.getIdentifier("CheckPPD"));
        schedule.setCustomerKey(customerPersonKey);
        schedule.setScheduleStatus(HpsPayPlanScheduleStatus.ACTIVE);
        schedule.setPaymentMethodKey(paymentMethodKeyCheckPPD);
        schedule.setSubtotalAmount(new HpsPayPlanAmount("3003"));
        schedule.setStartDate("02012027");
        schedule.setFrequency(HpsPayPlanScheduleFrequency.MONTHLY);
        schedule.setDuration(HpsPayPlanScheduleDuration.LIMITED_NUMBER);
        schedule.setReprocessingCount(1);
        schedule.setNumberOfPayments(2);
        schedule.setProcessingDateInfo("1");

        HpsPayPlanSchedule response = this.service.addSchedule(schedule);
        assertNotNull(response);
        assertNotNull(response.getScheduleKey());
        scheduleKeyCheckPPD = response.getScheduleKey();
    }

    @Test
    public void test_011_AddScheduleCheclCCD() throws HpsException {
        HpsPayPlanSchedule schedule = new HpsPayPlanSchedule();
        schedule.setScheduleIdentifier(this.getIdentifier("CheckCCD"));
        schedule.setCustomerKey(customerCompanyKey);
        schedule.setScheduleStatus(HpsPayPlanScheduleStatus.ACTIVE);
        schedule.setPaymentMethodKey(paymentMethodKeyCheckCCD);
        schedule.setSubtotalAmount(new HpsPayPlanAmount("3004"));
        schedule.setStartDate("02012027");
        schedule.setFrequency(HpsPayPlanScheduleFrequency.BIWEEKLY);
        schedule.setDuration(HpsPayPlanScheduleDuration.ONGOING);
        schedule.setReprocessingCount(1);

        HpsPayPlanSchedule response = this.service.addSchedule(schedule);
        assertNotNull(response);
        assertNotNull(response.getScheduleKey());
        scheduleKeyCheckCCD = response.getScheduleKey();
    }

    // RECURRING BILLING - DECLINED

    @Test(expected=HpsException.class)
    public void test_012_AddScheduleCreditVisa() throws HpsException {
        HpsPayPlanSchedule schedule = new HpsPayPlanSchedule();
        schedule.setScheduleIdentifier(this.getIdentifier("CreditV"));
        schedule.setCustomerKey(customerPersonKey);
        schedule.setScheduleStatus(HpsPayPlanScheduleStatus.ACTIVE);
        schedule.setPaymentMethodKey(paymentMethodKeyVisa);
        schedule.setSubtotalAmount(new HpsPayPlanAmount("3001"));
        schedule.setStartDate("02012027");
        schedule.setFrequency(HpsPayPlanScheduleFrequency.WEEKLY);
        schedule.setDuration(HpsPayPlanScheduleDuration.ONGOING);
        schedule.setReprocessingCount(1);

        this.service.addSchedule(schedule);
    }

    @Test(expected=HpsException.class)
    public void test_013_AddScheduleCCheckPPD() throws HpsException {
        HpsPayPlanSchedule schedule = new HpsPayPlanSchedule();
        schedule.setScheduleIdentifier(this.getIdentifier("CheckPPD"));
        schedule.setCustomerKey(customerPersonKey);
        schedule.setScheduleStatus(HpsPayPlanScheduleStatus.ACTIVE);
        schedule.setPaymentMethodKey(paymentMethodKeyCheckPPD);
        schedule.setSubtotalAmount(new HpsPayPlanAmount("3003"));
        schedule.setStartDate("02012027");
        schedule.setFrequency(HpsPayPlanScheduleFrequency.MONTHLY);
        schedule.setDuration(HpsPayPlanScheduleDuration.LIMITED_NUMBER);
        schedule.setReprocessingCount(1);
        schedule.setNumberOfPayments(2);
        schedule.setProcessingDateInfo("1");

        this.service.addSchedule(schedule);
    }

    // Recurring Billing using PayPlan - Managed Schedule

    @Test
    public void test_014_RecurringBillingVisa() throws HpsException {
        HpsAuthorization response = this.creditService.recurring(new BigDecimal("20.01")).withScheduleId(scheduleKeyVisa)
                .withPaymentMethodKey(paymentMethodKeyVisa)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_015_RecurringBillingMasterCard() throws HpsException {
        HpsAuthorization response = this.creditService.recurring(new BigDecimal("20.02")).withScheduleId(scheduleKeyMasterCard)
                .withPaymentMethodKey(paymentMethodKeyMasterCard)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_016_RecurringBillingCheckPPD() throws HpsException {
        HpsCheckResponse response = this.checkService.recurring(new BigDecimal("20.03"))
                .withSchedule(scheduleKeyCheckPPD)
                .withPaymentMethodKey(paymentMethodKeyCheckPPD)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_017_RecurringBillingCheckCCD() throws HpsException {
        HpsCheckResponse response = this.checkService.recurring(new BigDecimal("20.04"))
                .withSchedule(scheduleKeyCheckCCD)
                .withPaymentMethodKey(paymentMethodKeyCheckCCD)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // One time bill payment

    @Test
    public void test_018_RecurringBillingVisa() throws HpsException {
        HpsAuthorization response = this.creditService.recurring(new BigDecimal("20.06"))
                .withPaymentMethodKey(paymentMethodKeyVisa)
                .withOneTime(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_019_RecurringBillingMasterCard() throws HpsException {
        HpsAuthorization response = this.creditService.recurring(new BigDecimal("20.07"))
                .withPaymentMethodKey(paymentMethodKeyMasterCard)
                .withOneTime(true)
                .execute();
        assertNotNull(response);
        assertEquals("00", response.getResponseCode());
    }

    @Test
    public void test_020_RecurringBillingCheckPPD() throws HpsException {
        HpsCheckResponse response = this.checkService.recurring(new BigDecimal("20.08"))
                .withPaymentMethodKey(paymentMethodKeyCheckPPD)
                .withOneTime(true)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    @Test
    public void test_021_RecurringBillingCheckCCD() throws HpsException {
        HpsCheckResponse response = this.checkService.recurring(new BigDecimal("20.09"))
                .withPaymentMethodKey(paymentMethodKeyCheckCCD)
                .withOneTime(true)
                .execute();
        assertNotNull(response);
        assertEquals("0", response.getResponseCode());
    }

    // Onetime bill payment - declined

    @Test(expected = HpsException.class)
    public void test_022_RecurringBillingVisa() throws HpsException {
        this.creditService.recurring(new BigDecimal("10.08"))
                .withPaymentMethodKey(paymentMethodKeyVisa)
                .withOneTime(true)
                .execute();
    }

    @Test(expected = HpsCheckException.class)
    public void test_023_RecurringBillingCheckPPD() throws HpsException {
        this.checkService.recurring(new BigDecimal("25.02"))
                .withPaymentMethodKey(paymentMethodKeyCheckPPD)
                .withOneTime(true)
                .execute();
    }

    @Test
    public void test_999_CloseBatch() {
        try{
            HpsBatch response = this.batchService.closeBatch();
            if(response == null)
                fail("Response is null");
            System.out.println(String.format("Batch ID: %s", response.getId()));
            System.out.println(String.format("Sequence Number: %s", response.getSequenceNumber()));
        }
        catch(HpsException exc){
            fail(exc.getMessage());
        }
    }
}
