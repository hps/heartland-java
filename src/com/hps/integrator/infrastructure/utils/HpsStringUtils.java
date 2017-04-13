package com.hps.integrator.infrastructure.utils;

import com.hps.integrator.infrastructure.emums.ControlCodes;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class HpsStringUtils {
    public static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public static String trimEnd(String str, ControlCodes code){
        // Strip the nulls off
        str = str.replaceAll("null", "");
        String trimChar = (char)code.getByte() + "";

        while(str.endsWith(trimChar)) {
            int index = str.lastIndexOf(trimChar);
            str = str.substring(0, index);
        }
        return str;
    }

    public static BigDecimal toAmount(String str) {
        if(isNullOrEmpty(str))
            return null;

        BigDecimal amount = new BigDecimal(str);
        return amount.divide(new BigDecimal(100));
    }

    public static String toNumeric(BigDecimal amount) {
        if(amount == null)
            return "";

        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        String currency = fmt.format(amount);
        return currency.replaceAll("[^0-9]", "");
    }

    public static String padLeft(String str, int length, char paddingChar) {
        String rvalue = str;
        while(rvalue.length() < length)
            rvalue = paddingChar + rvalue;
        return rvalue;
    }
}
