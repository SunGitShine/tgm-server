package com.juma.tgm.common;

import static java.util.Objects.requireNonNull;

import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * @ClassName Base62.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年8月16日 下午4:25:05
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class Base62 {

    private static final Integer INTEGER_ZERO = 0;
    private static final BigInteger BASE_THOUSAND = BigInteger.valueOf(1000);
    private static final BigInteger BASE = BigInteger.valueOf(62);
    private static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * Encodes a number using Base62 encoding.
     *
     * @param number
     *            a positive integer
     * @return a Base62 string
     *
     * @throws IllegalArgumentException
     *             if <code>number</code> is a negative integer
     */
    public static String encode(BigInteger number) {
        if (number.compareTo(BigInteger.ZERO) < 0) {
            throwIllegalArgumentException("number must not be negative");
        }
        StringBuilder result = new StringBuilder();
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = number.divideAndRemainder(BASE);
            number = divmod[0];
            int digit = divmod[1].intValue();
            result.insert(0, DIGITS.charAt(digit));
        }
        return (result.length() == 0) ? DIGITS.substring(0, 1) : result.toString();
    }

    /**
     * 
     * 将制定加密的数据乘以1000后加密
     * 
     * @author Libin.Wei
     * @Date 2018年8月17日 上午9:52:42
     * @param number
     * @return
     */
    public static String encodeByMultiply(Integer number) {
        requireNonNull(number, "EncodeMultiply number must not be null");
        return encode(BigInteger.valueOf(number).multiply(BASE_THOUSAND));
    }

    /**
     * 
     * 将制定解密的数据解密后除以1000
     * 
     * @author Libin.Wei
     * @Date 2018年8月17日 上午9:55:20
     * @param string
     * @return
     */
    public static BigInteger decodeByDivide(final String string) {
        BigInteger number = decode(string, 128);
        return number.divide(BASE_THOUSAND);
    }

    /**
     * Decodes a string using Base62 encoding.
     *
     * @param string
     *            a Base62 string
     * @return a positive integer
     *
     * @throws IllegalArgumentException
     *             if <code>string</code> is empty
     */
    public static BigInteger decode(final String string) {
        return decode(string, 128);
    }

    public static BigInteger decode(final String string, int bitLimit) {
        requireNonNull(string, "Decoded string must not be null");
        if (string.length() == 0) {
            return throwIllegalArgumentException("string '%s' must not be empty", null);
        }

        if (!Pattern.matches("[" + DIGITS + "]*", string)) {
            throwIllegalArgumentException("String '%s' contains illegal characters, only '%s' are allowed", string,
                    DIGITS);
        }
        BigInteger result = BigInteger.ZERO;
        int digits = string.length();
        for (int index = 0; index < digits; index++) {
            int digit = DIGITS.indexOf(string.charAt(digits - index - 1));
            result = result.add(BigInteger.valueOf(digit).multiply(BASE.pow(index)));
            if (bitLimit > 0 && result.bitLength() > bitLimit) {
                throwIllegalArgumentException("String contains '%s' more than 128bit information (%sbit)", string,
                        result.bitLength());
            }
        }
        return result;
    }

    private static BigInteger throwIllegalArgumentException(String format, Object... args) {
        throw new IllegalArgumentException(String.format(format, args));
    }

}
