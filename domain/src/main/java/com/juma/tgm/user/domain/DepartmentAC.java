package com.juma.tgm.user.domain;

import java.io.Serializable;
import java.util.Date;

public class DepartmentAC implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1075153977544438697L;

    private Integer departmentAcId;
    
    private Integer departmentId;
    
    private String departmentCode;
    
    private String linkDepartmentIds;
    
    private String regionIds;
    
    private String regionCodes;
    
    private String ruleKeys;
    
    private Integer createUserId;
    
    private Date createTime;
    
    private Integer lastUpdateUserId;
    
    private Date lastUpdateTime;

    public Integer getDepartmentAcId() {
        return departmentAcId;
    }

    public void setDepartmentAcId(Integer departmentAcId) {
        this.departmentAcId = departmentAcId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(String regionIds) {
        this.regionIds = regionIds;
    }

    public String getRuleKeys() {
        return ruleKeys;
    }

    public void setRuleKeys(String ruleKeys) {
        this.ruleKeys = ruleKeys;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getRegionCodes() {
        return regionCodes;
    }

    public void setRegionCodes(String regionCodes) {
        this.regionCodes = regionCodes;
    }

    public String getLinkDepartmentIds() {
        return linkDepartmentIds;
    }

    public void setLinkDepartmentIds(String linkDepartmentIds) {
        this.linkDepartmentIds = linkDepartmentIds;
    }
    
}
