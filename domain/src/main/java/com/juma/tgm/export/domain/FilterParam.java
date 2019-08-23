package com.juma.tgm.export.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/7 0007.
 */
public class FilterParam implements Serializable {
    private String dataBaseName;
    private String tableName;
    private String columnName;
    private Integer columnCompareType;
    private Integer columnNumberType;
    private String value;

    public FilterParam() {
    }

    public FilterParam(String dataBaseName, String tableName, String columnName, Integer columnCompareType,
            Integer columnNumberType, String value) {
        super();
        this.dataBaseName = dataBaseName;
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnCompareType = columnCompareType;
        this.columnNumberType = columnNumberType;
        this.value = value;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getColumnCompareType() {
        return columnCompareType;
    }

    public void setColumnCompareType(Integer columnCompareType) {
        this.columnCompareType = columnCompareType;
    }

    public Integer getColumnNumberType() {
        return columnNumberType;
    }

    public void setColumnNumberType(Integer columnNumberType) {
        this.columnNumberType = columnNumberType;
    }
}
