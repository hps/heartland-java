package com.hps.integrator.tests.applepay;

import com.hps.integrator.applepay.ecv1.DecryptService;
import com.hps.integrator.applepay.ecv1.PaymentData;
import com.hps.integrator.applepay.ecv1.PaymentToken;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.tests.testdata.TestData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentDataTests {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void empty_json_throws_exception() throws HpsException
    {
        expectedEx.expect(HpsException.class);
        expectedEx.expectMessage(PaymentData.EMPTY_OR_NULL_JSON_STRING);
        PaymentData token = new PaymentData("");
    }

    @Test
    public void null_json_throws_exception() throws HpsException
    {
        expectedEx.expect(HpsException.class);
        expectedEx.expectMessage(PaymentData.EMPTY_OR_NULL_JSON_STRING);
        PaymentData token = new PaymentData(null);
    }

    @Test
    public void can_parse_token_json() throws HpsException
    {
        PaymentData token = new PaymentData(TestData.PAYMENT_DATA_MC_DECRYPTED_JSON);
        assertNotNull(token);
        assertEquals(token.getApplicationExpirationDate(), "171130");
        assertEquals(token.getApplicationPrimaryAccountNumber(), "5473500000000014");
        assertEquals(token.getCurrencyCode(), "840");
        assertEquals(token.getDeviceManufacturerIdentifier(), "050110030273");
        assertEquals(token.getPaymentData().getPaymentDataTye(), "3DSecure");
        assertEquals(token.getPaymentData().getOnlinePaymentCryptogram(), "XXXXbKK+J9Z5AAWj9GzwAoABFA==");
        assertEquals(token.getTransactionAmount(), "1");
    }
}