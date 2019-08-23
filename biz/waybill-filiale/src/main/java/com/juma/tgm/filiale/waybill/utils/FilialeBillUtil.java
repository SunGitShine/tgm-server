package com.juma.tgm.filiale.waybill.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * @ClassName: FilialeBillUtil
 * @Description:
 * @author: liang
 * @date: 2018-04-03 11:28
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class FilialeBillUtil {

    /**
     *
     * @param target 要查找的值
     * @param source 查找的源:1,2,3
     * @return
     */
    public static boolean hasAddtionalFunction(String target, String source){
        if(StringUtils.isBlank(target)) return false;
        if(StringUtils.isBlank(source)) return false;

        String[] funId = StringUtils.split(source, ",");

        Arrays.sort(funId);
        if(Arrays.binarySearch(funId, target) >= 0) return true;

        return false;
    }
}
