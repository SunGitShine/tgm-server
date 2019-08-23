package com.juma.tgm.vendor.domain;

import java.io.Serializable;
import java.util.Date;

public class VendorMapping implements Serializable {
    private Integer vendorMappingId;

    private Integer vendorId;

    private Integer vendorTenantId;

    private String vendorName;

    private Integer vendorCustomerId;

    private Integer customerTenantId;

    private String vendorCustomerName;

    private Date createTime;

    private Integer createUserId;

    private String departmentCode;

    private Boolean isDelete;

    private Integer lastUpdateUserId;

    private Date lastUpdateUserName;

    private static final long serialVersionUID = 1L;

    public Integer getVendorMappingId() {
        return vendorMappingId;
    }

    public void setVendorMappingId(Integer vendorMappingId) {
        this.vendorMappingId = vendorMappingId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getVendorTenantId() {
        return vendorTenantId;
    }

    public void setVendorTenantId(Integer vendorTenantId) {
        this.vendorTenantId = vendorTenantId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName == null ? null : vendorName.trim();
    }

    public Integer getVendorCustomerId() {
        return vendorCustomerId;
    }

    public void setVendorCustomerId(Integer vendorCustomerId) {
        this.vendorCustomerId = vendorCustomerId;
    }

    public Integer getCustomerTenantId() {
        return customerTenantId;
    }

    public void setCustomerTenantId(Integer customerTenantId) {
        this.customerTenantId = customerTenantId;
    }

    public String getVendorCustomerName() {
        return vendorCustomerName;
    }

    public void setVendorCustomerName(String vendorCustomerName) {
        this.vendorCustomerName = vendorCustomerName == null ? null : vendorCustomerName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateUserName() {
        return lastUpdateUserName;
    }

    public void setLastUpdateUserName(Date lastUpdateUserName) {
        this.lastUpdateUserName = lastUpdateUserName;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VendorMapping other = (VendorMapping) that;
        return (this.getVendorMappingId() == null ? other.getVendorMappingId() == null : this.getVendorMappingId().equals(other.getVendorMappingId()))
            && (this.getVendorId() == null ? other.getVendorId() == null : this.getVendorId().equals(other.getVendorId()))
            && (this.getVendorTenantId() == null ? other.getVendorTenantId() == null : this.getVendorTenantId().equals(other.getVendorTenantId()))
            && (this.getVendorName() == null ? other.getVendorName() == null : this.getVendorName().equals(other.getVendorName()))
            && (this.getVendorCustomerId() == null ? other.getVendorCustomerId() == null : this.getVendorCustomerId().equals(other.getVendorCustomerId()))
            && (this.getCustomerTenantId() == null ? other.getCustomerTenantId() == null : this.getCustomerTenantId().equals(other.getCustomerTenantId()))
            && (this.getVendorCustomerName() == null ? other.getVendorCustomerName() == null : this.getVendorCustomerName().equals(other.getVendorCustomerName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getDepartmentCode() == null ? other.getDepartmentCode() == null : this.getDepartmentCode().equals(other.getDepartmentCode()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getLastUpdateUserId() == null ? other.getLastUpdateUserId() == null : this.getLastUpdateUserId().equals(other.getLastUpdateUserId()))
            && (this.getLastUpdateUserName() == null ? other.getLastUpdateUserName() == null : this.getLastUpdateUserName().equals(other.getLastUpdateUserName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVendorMappingId() == null) ? 0 : getVendorMappingId().hashCode());
        result = prime * result + ((getVendorId() == null) ? 0 : getVendorId().hashCode());
        result = prime * result + ((getVendorTenantId() == null) ? 0 : getVendorTenantId().hashCode());
        result = prime * result + ((getVendorName() == null) ? 0 : getVendorName().hashCode());
        result = prime * result + ((getVendorCustomerId() == null) ? 0 : getVendorCustomerId().hashCode());
        result = prime * result + ((getCustomerTenantId() == null) ? 0 : getCustomerTenantId().hashCode());
        result = prime * result + ((getVendorCustomerName() == null) ? 0 : getVendorCustomerName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getDepartmentCode() == null) ? 0 : getDepartmentCode().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getLastUpdateUserId() == null) ? 0 : getLastUpdateUserId().hashCode());
        result = prime * result + ((getLastUpdateUserName() == null) ? 0 : getLastUpdateUserName().hashCode());
        return result;
    }
}