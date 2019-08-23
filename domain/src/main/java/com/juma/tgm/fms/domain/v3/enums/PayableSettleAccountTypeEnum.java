package com.juma.tgm.fms.domain.v3.enums;

/**
 * @ClassName PayableSettleAccountTypeEnum.java
 * @Description 结算帐户类型
 * @author Libin.Wei
 * @Date 2018年11月23日 上午10:40:29
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public enum PayableSettleAccountTypeEnum {

    DRIVER(1, "自营司机"), VENDOR(2, "承运商");

    private int code;
    private String desc;

    private PayableSettleAccountTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(Integer code) {
        if (null == code) {
            return null;
        }

        for (PayableSettleAccountTypeEnum p : PayableSettleAccountTypeEnum.values()) {
            if (code == p.getCode()) {
                return p.getDesc();
            }
        }

        return null;
    }

}
