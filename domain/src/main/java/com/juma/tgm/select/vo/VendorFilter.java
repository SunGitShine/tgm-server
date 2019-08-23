package com.juma.tgm.select.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "承运商过滤条件")
public class VendorFilter implements Serializable {

    @ApiModelProperty(value = "承运商名字")
    private String vendorName;

    @ApiModelProperty(value = "承运商电话")
    private String vendorContactPhone;

    private Integer pageSize = 15;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getVendorContactPhone() {
        return vendorContactPhone;
    }

    public void setVendorContactPhone(String vendorContactPhone) {
        this.vendorContactPhone = vendorContactPhone;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
