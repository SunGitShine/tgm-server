package com.juma.tgm.fms.domain.v3.enums;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 16:20 2019-05-15
 */
public enum AdjustStatus {
    //
    APPROVED(1,"可调价"),
    REJECTED(0,"不可调价");

    private Integer code;
    private String desc;

    AdjustStatus(final Integer code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
