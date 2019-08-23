package com.juma.tgm.user.domain;

import com.juma.auth.conf.domain.BusinessAreaNode;

import java.io.Serializable;
import java.util.*;

public class CurrentUser implements Serializable {

    private Integer tenantId;

    private String tenantCode;

    private List<String> regionCodes = new ArrayList<String>();

    private Set<BusinessAreaNode> businessAreas = new HashSet<>();

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

    public Set<BusinessAreaNode> getBusinessAreas() {
        return businessAreas;
    }

    public void setBusinessAreas(Set<BusinessAreaNode> businessAreas) {
        this.businessAreas = businessAreas;
    }

    public void addBusinessAreas(Collection<BusinessAreaNode> businessAreas) {

        if (this.businessAreas == null) {
            this.businessAreas = new HashSet<>();
        }
        this.businessAreas.addAll(businessAreas);
    }

}
