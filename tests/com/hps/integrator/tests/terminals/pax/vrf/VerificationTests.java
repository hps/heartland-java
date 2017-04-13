package com.hps.integrator.tests.terminals.pax.vrf;

import com.hps.integrator.abstractions.IMessageSentInterface;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.ConnectionModes;
import com.hps.integrator.terminals.ConnectionConfig;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.BatchCloseResponse;
import com.hps.integrator.terminals.pax.responses.CreditResponse;
import com.hps.integrator.terminals.pax.responses.DebitResponse;
import com.hps.integrator.terminals.pax.responses.GiftResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerificationTests {
    private PaxDevice device;

    public VerificationTests() throws HpsException {
        ConnectionConfig config = new ConnectionConfig();
        config.setConnectionMode(ConnectionModes.HTTP);
        config.setIpAddress("10.12.220.172");
        config.setPort(10009);

        device = new PaxDevice(config);
        device.setOnMessageSent(new IMessageSentInterface() {
            public void messageSent(String message) {
                System.out.println("Request: " + message);
            }
        });
    }

    private void printReceipt(CreditResponse response) {
        String receipt = "x_trans_type=" + response.getTransactionType();
        receipt += "&x_application_label=" + response.getApplicationLabel();
        receipt += "&x_masked_card=" + response.getMaskedCardNumber();
        receipt += "&x_application_id=" + response.getApplicationId();
        receipt += "&x_cryptogram_type=" + response.getApplicationCryptogramType();
        receipt += "&x_application_cryptogram=" + response.getApplicationCryptogram();
        receipt += "&x_expiration_date=" + response.getExpirationDate();
        receipt += "&x_entry_method=" + response.getEntryMethod();
        receipt += "&x_approval=" + response.getApprovalCode();
        receipt += "&x_transaction_amount=" + response.getTransactionAmount();
        receipt += "&x_amount_due=" + response.getAmountDue();
        receipt += "&x_customer_verification_method=" + response.getCustomerVerificationMethod();
        receipt += "&x_signature_status=" + response.getSignatureStatus();
        receipt += "&x_response_text=" + response.getResponseText();
        System.out.println(receipt);
    }

    @Test
    public void test_case_01() throws HpsException {
        System.out.println("EMV MasterCard w/ Offline PIN");
        System.out.println("PIN: 4315");
        CreditResponse response = device.creditSale(1, new BigDecimal(4))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
        printReceipt(response);
    }

    @Test
    public void test_case_02() throws HpsException {
        System.out.println("EMV MasterCard w/ Offline PIN");
        System.out.println("PIN: 4315");
        CreditResponse response = device.creditSale(2, new BigDecimal("5.01"))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
        printReceipt(response);
    }

    @Test
    public void test_case_03a() throws HpsException {
        System.out.println("Magnetic stripe Visa");
        CreditResponse response = device.creditSale(3, new BigDecimal("7.00"))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
        printReceipt(response);

        CreditResponse voidResponse = device.creditVoid(4)
                .withTransactionId(response.getTransactionId())
                .execute();
        assertNotNull(voidResponse);
        assertEquals("00", voidResponse.getResponseCode());
        System.out.println("Response: " + voidResponse.toString());

        System.out.println("Host Reference Number: " + voidResponse.getHostReferenceNumber());
        System.out.println("Transaction ID: " + voidResponse.getTransactionId());
    }

    @Test
    public void test_case_03b() throws HpsException {
        System.out.println("Magnetic stripe Visa");
        CreditResponse response = device.creditSale(5, new BigDecimal("155.00"))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "10");
        assertEquals(response.getTransactionAmount(), new BigDecimal("100"));
        assertEquals(response.getAmountDue(), new BigDecimal("55"));
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
        printReceipt(response);
    }

    @Test
    public void test_case_05() throws HpsException {
        System.out.println("Magnetic stripe MasterCard MANUALLY ENTERED");
        System.out.println("PAN: 5599 9999 9999 9997");
        System.out.println("EXP: 1220");
        System.out.println("CVV: 321");
        System.out.println("AVS: 76321");
        CreditResponse response = device.creditSale(6, new BigDecimal("118.00"))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
        printReceipt(response);
    }

    @Test
    public void test_case_06a() throws HpsException {
        System.out.println("EMV Visa w/ Signature CVM");
        CreditResponse response = device.creditSale(7, new BigDecimal("15.01"))
                .withRequestMultiUseToken(true)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
        System.out.println("Token Value: " + response.getToken());
        printReceipt(response);

        CreditResponse tokenResponse = device.creditSale(9, new BigDecimal("15.02"))
                .withToken(response.getToken())
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(tokenResponse);
        assertEquals(tokenResponse.getResponseCode(), "00");
        System.out.println("Response: " + tokenResponse.toString());

        System.out.println("Host Reference Number: " + tokenResponse.getHostReferenceNumber());
        System.out.println("Transaction ID: " + tokenResponse.getTransactionId());
        System.out.println("Token Value: " + tokenResponse.getToken());
        printReceipt(tokenResponse);
    }

    @Test
    public void test_case_06b() throws HpsException {
        System.out.println("MSD only MasterCard");
        CreditResponse response = device.creditSale(8, new BigDecimal("15.02"))
                .withRequestMultiUseToken(true)
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
        System.out.println("Token Value: " + response.getToken());
        printReceipt(response);

        CreditResponse tokenResponse = device.creditSale(10, new BigDecimal("15.03"))
                .withToken(response.getToken())
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(tokenResponse);
        assertEquals(tokenResponse.getResponseCode(), "00");
        System.out.println("Response: " + tokenResponse.toString());

        System.out.println("Host Reference Number: " + tokenResponse.getHostReferenceNumber());
        System.out.println("Transaction ID: " + tokenResponse.getTransactionId());
        System.out.println("Token Value: " + tokenResponse.getToken());
        printReceipt(tokenResponse);
    }

    @Test
    public void test_case_10() throws HpsException {
        BatchCloseResponse response = device.batchClose();
        assertNotNull(response);
        System.out.println("Response: " + response.toString());

        System.out.println("Batch Number: " + response.getBatchNumber());
    }

    @Test
    public void test_case_12a() throws HpsException {
        System.out.println("Magnetic Stripe Visa");
        System.out.println("PIN: 1234");
        DebitResponse response = device.debitSale(11, new BigDecimal(10))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
    }

    @Test
    public void test_case_12b() throws HpsException {
        System.out.println("Magnetic Stripe Visa");
        System.out.println("PIN: 1234");
        DebitResponse response = device.debitSale(12, new BigDecimal(11))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
    }

    @Test
    public void test_case_13() throws HpsException {
        System.out.println("Magnetic Stripe Visa");
        System.out.println("PIN: 1234");
        DebitResponse response = device.debitReturn(13, new BigDecimal(12))
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
    }

    @Test
    public void test_case_14a() throws HpsException {
        System.out.println("MSD only MasterCard (AVS Required)");
        CreditResponse response = device.creditAuth(14, new BigDecimal(15.12))
                .withAllowDuplicates(true)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "00");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());

        CreditResponse capture = device.creditCapture(15)
                .withAmount(new BigDecimal(18.12))
                .withTransactionId(response.getTransactionId())
                .execute();
        assertNotNull(capture);
        assertEquals(capture.getResponseCode(), "00");
        System.out.println("Response: " + capture.toString());

        System.out.println("Host Reference Number: " + capture.getHostReferenceNumber());
        System.out.println("Transaction ID: " + capture.getTransactionId());
    }

    @Test
    public void test_case_15a() throws HpsException {
        System.out.println("Gift Card (Card Present/Card Swipe)");
        GiftResponse response = device.giftBalance(16)
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "0");
        assertEquals(new BigDecimal(10), response.getBalanceAmount());
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
    }

    @Test
    public void test_case_15b() throws HpsException {
        System.out.println("Gift Card (Card Present/Card Swipe)");
        GiftResponse response = device.giftAddValue(17)
                .withAmount(new BigDecimal(8))
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "0");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
    }

    @Test
    public void test_case_15c() throws HpsException {
        System.out.println("Gift Card (Card Present/Card Swipe)");
        GiftResponse response = device.giftSale(18, new BigDecimal(1))
                .execute();
        assertNotNull(response);
        assertEquals(response.getResponseCode(), "0");
        System.out.println("Response: " + response.toString());

        System.out.println("Host Reference Number: " + response.getHostReferenceNumber());
        System.out.println("Transaction ID: " + response.getTransactionId());
    }

    @Test
    public void test_case_19() throws HpsException {
        BatchCloseResponse response = device.batchClose();
        assertNotNull(response);
        System.out.println("Response: " + response.toString());

        System.out.println("Batch Number: " + response.getBatchNumber());
    }
}
