package com.juma.tgm.common;


import org.apache.commons.lang.math.NumberUtils;

import java.text.DecimalFormat;

/**
 * Created by liang on 2016/6/13 0013.
 */

public class NumberFormatUtils {

    /**
     * 将分转化为元
     * @param value
     * @param format
     * @return
     */
    public static String doFormatToYuan(Integer value, String format){

        if (value == null) value = 0;

        DecimalFormat df = new DecimalFormat();
        df.applyPattern(format);

        return df.format(value.floatValue() / 100);

    }

    /**
     * 将分转化为元(默认保留两位小数:0.00)
     * @param value
     * @return
     */
    public static String doFormatToYuan(Integer value){
        String format = "0.00";

        return doFormatToYuan(value,format);
    }

    /**
     * 将元转化为分
     * @param value
     * @return
     */
    public static Integer doFormatToFen(String value){
        if(!NumberUtils.isNumber(value)) return null;

        Integer ip = Float.valueOf(NumberUtils.toFloat(value) * 100).intValue();

        return ip;
    }

//    public static String doMutiply(){}

    /**
     * 数字转换
     * @param value
     * @param factor
     * @param format
     * @return
     */
    public static String doDivide(Integer value, Integer factor, String format){
        if (value == null) value = 0;
        if (factor == null || factor <= 0) factor = 1000;

        DecimalFormat df = new DecimalFormat();
        df.applyPattern(format);

        return df.format(value.doubleValue() / factor);
    }


}
