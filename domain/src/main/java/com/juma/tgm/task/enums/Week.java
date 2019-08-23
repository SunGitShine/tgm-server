package com.juma.tgm.task.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public class Week {

    private static EnumSet<WeekEnum> weekSet = EnumSet.allOf(WeekEnum.class);
    private static  Map<String, String> weekMap = new HashMap<>();
    static {
        for ( WeekEnum weekEnum : weekSet ) {
            weekMap.put(weekEnum.getCode(), weekEnum.getDesc());
        }
    }

    public enum WeekEnum {

        Mon("1", "周一"),
        Tue("2", "周二"),
        Wed("3", "周三"),
        Thu("4", "周四"),
        Fri("5", "周五"),
        Sat("6", "周六"),
        Sun("7", "周日");

        private String code;

        private String desc;

        WeekEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static String getWeekDesc(String code) {
        if (StringUtils.isBlank(code)) return null;
        return weekMap.get(code);
    }
}
