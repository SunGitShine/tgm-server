package com.juma.tgm.configure.domain;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * @ClassName ConfParam.java
 * @Description 参数配置
 * @author Libin.Wei
 * @Date 2016年12月28日 下午5:34:29
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ConfigParam extends BaseDomain {

    private static final long serialVersionUID = 4612552875483648099L;

    private Integer paramId;

    private String paramKey;

    private String paramName;

    private String paramDescribed;

    private String regionCode;

    private Integer tenantId;

    private String tenantCode;

    public ConfigParam() {
    }

    public ConfigParam(Integer paramId) {
        this.paramId = paramId;
    }

    public enum ParamKey {
        WECHAT_NEW_WAYBILL, MANAGE_CREATE_EXCEPTION_WAYBILL, CANCEL_WAYBILL;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDescribed() {
        return paramDescribed;
    }

    public void setParamDescribed(String paramDescribed) {
        this.paramDescribed = paramDescribed;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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

    @Override
    public String toString() {
        return "ConfParam [paramId=" + paramId + ", paramKey=" + paramKey + ", paramName=" + paramName
                + ", paramDescribed=" + paramDescribed + "]";
    }
}
