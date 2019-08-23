package com.juma.tgm.operateLog.enumeration;

/**
 * @ClassName OperateApplicatoinEnum.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年10月30日 上午10:37:31
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public enum OperateApplicatoinEnum {

    CUSTOMER_APP((byte) 1, "经纪人端"), BACKSTAGE((byte) 2, "后台系统");

    private byte code;
    private String desc;

    private OperateApplicatoinEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getApplicatoinDesc(Byte code) {
        if (null == code) {
            return null;
        }

        for (OperateApplicatoinEnum a : OperateApplicatoinEnum.values()) {
            if (code == a.getCode()) {
                return a.getDesc();
            }
        }
        return null;
    }
}
