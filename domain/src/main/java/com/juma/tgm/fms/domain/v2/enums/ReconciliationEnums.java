package com.juma.tgm.fms.domain.v2.enums;

import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;

/**
 * 对账单枚举类
 *
 * @ClassName: ReconciliationEnums
 * @Description:
 * @author: liang
 * @date: 2018-06-05 20:06
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationEnums implements Serializable {

    /**
     * 货主运费付款状态
     */
    public enum ShipperPayStatus {
        NONE(1, "未收款"),
        PART(2, "部分收款"),
        DONE(3, "已收款");


        private int code;
        private String desc;

        ShipperPayStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static ShipperPayStatus getByCode(int code) {
            for (ShipperPayStatus sps : ShipperPayStatus.values()) {
                if (NumberUtils.compare(code, sps.code) == 0) return sps;
            }

            return null;
        }

    }

    /**
     * 车辆结算状态
     */
    public enum VehiclePayStatus {
        NONE(0, "未结算"),
        ALREADY_PAY(1, "已结算"),
        PRE_PAY(2, "预结");

        private Integer code;
        private String desc;

        VehiclePayStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }


        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static String getDescByCode(int code) {
            for (VehiclePayStatus vps:VehiclePayStatus.values()) {
                if(NumberUtils.compare(code, vps.getCode()) == 0) return vps.getDesc();
            }

            return null;
        }

    }
}
