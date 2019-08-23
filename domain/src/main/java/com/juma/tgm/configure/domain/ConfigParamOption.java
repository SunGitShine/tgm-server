package com.juma.tgm.configure.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * @ClassName ConfParamOption.java
 * @Description 参数配置项
 * @author Libin.Wei
 * @Date 2016年12月28日 下午5:35:41
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ConfigParamOption extends BaseDomain {

    private static final long serialVersionUID = -2968019634679617895L;

    private Integer optionId;

    private Integer userId;

    private Integer paramId;

    private String optionName;

    private String optionValue;

    private String optionDescribed;

    private Integer orderNo;

    private Integer departmentId;

    private String regionCode;

    private String areaCode;

    private Integer tenantId;
    
    private String tenantCode;

    private Integer employeeId;

    // 显示冗余
    private String userName;
    private String loginName;

    public ConfigParamOption() {
    }

    public ConfigParamOption(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getOptionDescribed() {
        return optionDescribed;
    }

    public void setOptionDescribed(String optionDescribed) {
        this.optionDescribed = optionDescribed;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "ConfigParamOption [optionId=" + optionId + ", userId=" + userId + ", paramId=" + paramId
                + ", optionName=" + optionName + ", optionValue=" + optionValue + ", optionDescribed=" + optionDescribed
                + ", orderNo=" + orderNo + ", departmentId=" + departmentId + ", regionCode=" + regionCode
                + ", areaCode=" + areaCode + ", tenantCode=" + tenantCode + ", employeeId=" + employeeId + "]";
    }

}
