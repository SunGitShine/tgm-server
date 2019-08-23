package com.juma.tgm.crm.domain;

import java.io.Serializable;

/**
 * @ClassName CrmEvent.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年10月20日 下午5:46:35
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class CrmEvent implements Serializable {

    private static final long serialVersionUID = 3065110473589321923L;
    private Integer customerId;
    private String opType;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public enum OpType {
        INSERT,UPDATE,DELETE;
    }
}
