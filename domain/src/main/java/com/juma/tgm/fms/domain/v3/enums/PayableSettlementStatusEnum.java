package com.juma.tgm.fms.domain.v3.enums;


public enum PayableSettlementStatusEnum {

    NOT_CLEAR(1, "never"), HAS_CLEAR(3, "whole"),PORTION_CLEAR(4, "part");

    private int code;
    private String desc;

    private PayableSettlementStatusEnum(int code, String desc) {
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

        for (PayableSettlementStatusEnum p : PayableSettlementStatusEnum.values()) {
            if (code == p.getCode()) {
                return p.getDesc();
            }
        }

        return null;
    }

}
