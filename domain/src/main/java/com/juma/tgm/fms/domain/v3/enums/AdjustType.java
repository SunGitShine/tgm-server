package com.juma.tgm.fms.domain.v3.enums;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 17:30 2019-05-13
 */
public enum AdjustType {
    //
    BEFORE(1,"对账前"),
    AFTER(2,"对账后");

    private Integer code;
    private String desc;

    AdjustType(final Integer code, final String desc) {
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
