package com.hps.integrator.infrastructure.validation;

import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsExceptionCodes;
import com.hps.integrator.infrastructure.HpsInvalidRequestException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Common input validation helper for method parameter and related validation.
 */
public class HpsInputValidation {
    /**
     * check the currency amount (can't be negative).
     *
     * @param amount The amount to use for a transaction(s).
     */
    public static void checkAmount(BigDecimal amount) throws HpsException {
        if (amount.compareTo(BigDecimal.ZERO) != 1) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidAmount, "Must be greater than or equal 0.", "amount");
        }
    }

    /**
     * Helper method to ensure the currency entered is valid. If not, throw the appropriate exception.
     *
     * @param currency The currency to use for a transaction(s).
     * @throws HpsException
     */
    public static void checkCurrency(String currency) throws HpsException {
        if (currency == null || currency.length() == 0) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.MissingCurrency, "Currency can't be null.", "currency");
        } else if (!currency.toLowerCase().equals("usd")) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidCurrency, "The only supported currency is \"usd\"", "currency");
        }
    }

    public static void checkDateNotFuture(Date date, String paramName) throws HpsException {
        if (date.after(new Date())) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidDate, "Date can not be in the future.", paramName);
        }
    }
}
