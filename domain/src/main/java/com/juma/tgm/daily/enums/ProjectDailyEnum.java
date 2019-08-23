package com.juma.tgm.daily.enums;

/**
 * @ClassName ProjectDailyEnum
 * @Description TODO
 * @Author weilibin
 * @Date 2019-07-18 20:07
 * @Version 1.0.0
 */

public class ProjectDailyEnum {

    // 日报状态
    public enum DailyStatus {
        UNEXPIRED(1, "未到期"),
        HAS_EXPIRED(2, "已到期");

        private Integer code;
        private String desc;

        DailyStatus(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByCode(Integer code) {
            if (null == code) {
                return null;
            }

            for (DailyStatus d : DailyStatus.values()) {
                if (d.getCode().equals(code)) {
                    return d.getDesc();
                }
            }

            return null;
        }
    }

    // 台账状态
    public enum StandingBookStatus {
        NO_UPLOADED(1, "未上传"),
        HAS_UPLOADED(2, "已上传");

        private Integer code;
        private String desc;

        StandingBookStatus(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByCode(Integer code) {
            if (null == code) {
                return null;
            }

            for (StandingBookStatus s : StandingBookStatus.values()) {
                if (s.getCode().equals(code)) {
                    return s.getDesc();
                }
            }

            return null;
        }
    }

    // 运费状态
    public enum FreightStatus {
        NOT_CONFIRMED(1, "未确认"),
        PART_CONFIRMED(2, "部分确认"),
        HAS_CONFIRMED(3, "全部确认");

        private Integer code;
        private String desc;

        FreightStatus(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByCode(Integer code) {
            if (null == code) {
                return null;
            }

            for (FreightStatus f : FreightStatus.values()) {
                if (f.getCode().equals(code)) {
                    return f.getDesc();
                }
            }

            return null;
        }
    }

}
