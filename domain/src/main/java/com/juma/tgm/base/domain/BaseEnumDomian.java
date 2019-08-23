package com.juma.tgm.base.domain;

import java.io.Serializable;

/**
 * 
 * @Description: 用于构造状态模板
 * @author weilibin
 * @date 2016年6月15日 下午5:07:48
 * @version V1.0
 */

public class BaseEnumDomian implements Serializable {

    private static final long serialVersionUID = 5885368317985455007L;
    private int code;
    private String desc;
    private boolean require;

    public BaseEnumDomian() {
    }

    public BaseEnumDomian(int code, String desc, boolean require) {
        super();
        this.code = code;
        this.desc = desc;
        this.require = require;
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

    public boolean isRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }

}
