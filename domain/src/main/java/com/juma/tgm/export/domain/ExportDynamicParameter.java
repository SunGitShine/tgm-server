package com.juma.tgm.export.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExportDynamicParameter.java
 * @Description 数据导出动态参数
 * @author Libin.Wei
 * @Date 2018年5月9日 上午10:49:46
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ExportDynamicParameter implements Serializable {

    private static final long serialVersionUID = -5175302270147497380L;
    private String key;
    private String name;
    private String columnName;
    private Integer orderNo;
    private boolean checked = false;
    private List<ExportDynamicParameter> children = new ArrayList<ExportDynamicParameter>();

    public ExportDynamicParameter() {
    }

    public ExportDynamicParameter(String key, String name, Integer orderNo, boolean checked) {
        super();
        this.key = key;
        this.name = name;
        this.orderNo = orderNo;
        this.checked = checked;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public List<ExportDynamicParameter> getChildren() {
        return children;
    }

    public void setChildren(List<ExportDynamicParameter> children) {
        this.children = children;
    }

}
