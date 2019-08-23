package com.juma.tgm.vendor.domain;

import java.io.Serializable;

public class VendorProjectMapping implements Serializable {
    private Integer vendorProjectMappingId;

    private Integer vendorMappingId;

    private Integer customerId;

    private Integer projectId;

    private Integer vendorProjectId;

    private static final long serialVersionUID = 1L;

    public Integer getVendorProjectMappingId() {
        return vendorProjectMappingId;
    }

    public void setVendorProjectMappingId(Integer vendorProjectMappingId) {
        this.vendorProjectMappingId = vendorProjectMappingId;
    }

    public Integer getVendorMappingId() {
        return vendorMappingId;
    }

    public void setVendorMappingId(Integer vendorMappingId) {
        this.vendorMappingId = vendorMappingId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getVendorProjectId() {
        return vendorProjectId;
    }

    public void setVendorProjectId(Integer vendorProjectId) {
        this.vendorProjectId = vendorProjectId;
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
        VendorProjectMapping other = (VendorProjectMapping) that;
        return (this.getVendorProjectMappingId() == null ? other.getVendorProjectMappingId() == null : this.getVendorProjectMappingId().equals(other.getVendorProjectMappingId()))
            && (this.getVendorMappingId() == null ? other.getVendorMappingId() == null : this.getVendorMappingId().equals(other.getVendorMappingId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getVendorProjectId() == null ? other.getVendorProjectId() == null : this.getVendorProjectId().equals(other.getVendorProjectId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVendorProjectMappingId() == null) ? 0 : getVendorProjectMappingId().hashCode());
        result = prime * result + ((getVendorMappingId() == null) ? 0 : getVendorMappingId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getVendorProjectId() == null) ? 0 : getVendorProjectId().hashCode());
        return result;
    }
}