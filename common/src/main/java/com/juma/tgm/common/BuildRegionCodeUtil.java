package com.juma.tgm.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.giants.common.tools.PageCondition;

public class BuildRegionCodeUtil {

    public static List<Integer> buildRegionCode(PageCondition pageCondition) {
        String[] regionCodess = getStrings(pageCondition);
        if (null != regionCodess) {
            List<Integer> list = new ArrayList<Integer>();
            for (String string : regionCodess) {
                if (StringUtils.isNotBlank(string)) {
                    list.add(Integer.valueOf(string));
                }
            }
            return list;
        }
        return null;
    }

    public static boolean hasRegionCodes(PageCondition pageCondition) {
        String[] regionCodess = getStrings(pageCondition);
        if (null != regionCodess) {
            for (String string : regionCodess) {
                if (StringUtils.isNotBlank(string)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static String[] getStrings(PageCondition pageCondition) {
        if (null != pageCondition) {
            Map<String, Object> filters = pageCondition.getFilters();
            if (null != filters) {
                Object obj = filters.get("regionCodes");
                if (null != obj) {
                    String regionCodes = obj.toString();
                    return regionCodes.split(",");
                }
            }
        }
        return null;
    }
}
