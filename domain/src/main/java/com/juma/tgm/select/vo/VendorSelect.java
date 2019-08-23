package com.juma.tgm.select.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "承运商")
public class VendorSelect implements Serializable {

    @ApiModelProperty(value = "承运商id")
    private Integer vendorId;

    @ApiModelProperty(value = "承运商名称")
    private String vendorName;

    @ApiModelProperty(value = "承运商联系电话")
    private String vendorContactPhone;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContactPhone() {
        return vendorContactPhone;
    }

    public void setVendorContactPhone(String vendorContactPhone) {
        this.vendorContactPhone = vendorContactPhone;
    }
}
