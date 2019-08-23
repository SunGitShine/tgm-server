package com.juma.tgm.project.enumeration;

/**
 * @ClassName ValuationWayEnum.java
 * @Description 计价方式
 * @author Libin.Wei
 * @Date 2018年9月28日 上午9:49:06
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public enum ValuationWayEnum {

    WORKLOAD(1, "按工作量"), FIXED_PRICE(2, "一口价");

    private int code;
    private String desc;

    private ValuationWayEnum(int code, String desc) {
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
