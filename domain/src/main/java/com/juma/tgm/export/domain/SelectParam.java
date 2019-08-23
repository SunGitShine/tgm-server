package com.juma.tgm.export.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/7 0007.
 */
public class SelectParam implements Serializable {
    private String dataBaseName;
    private String tableName;
    private String columnName;
    private String showName;
    private Map<String, String> convertMap;
    private Integer columnNumberType;

    public SelectParam() {
    }

    public SelectParam(String dataBaseName, String tableName, String columnName, Map<String, String> convertMap, Integer columnNumberType) {
        super();
        this.dataBaseName = dataBaseName;
        this.tableName = tableName;
        this.columnName = columnName;
        this.convertMap = convertMap;
        this.columnNumberType = columnNumberType;
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

    public Map<String, String> getConvertMap() {
        return convertMap;
    }

    public void setConvertMap(Map<String, String> convertMap) {
        this.convertMap = convertMap;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getColumnNumberType() {
        return columnNumberType;
    }

    public void setColumnNumberType(Integer columnNumberType) {
        this.columnNumberType = columnNumberType;
    }
}
