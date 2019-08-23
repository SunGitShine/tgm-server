package com.juma.tgm.waybill.enumeration;

public enum WhoAdjustPriceEnum {

    CUSTOMER(1, "客户侧"),VENODR(2, "承运侧");

    private int code;
    private String desc;

    WhoAdjustPriceEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
