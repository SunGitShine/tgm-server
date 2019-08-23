package com.juma.tgm.operateLog.enumeration;

/**
 * @ClassName LogSignEnum.java
 * @Description 日志分类枚举
 * @author Libin.Wei
 * @Date 2018年10月30日 上午10:36:49
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public enum LogSignEnum {

    PROJECT((byte) 1, "项目管理"), RECONCILICATION_AP((byte) 2, "承运商对账"),PROFIT_RATE((byte) 3, "项目承诺毛利率");

    private byte code;
    private String desc;

    private LogSignEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
