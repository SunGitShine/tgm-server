package com.juma.tgm.task.vo.gateway;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "不配送原因分类")
public class NotDeliveryReasonSort implements Serializable {

    @ApiModelProperty(value = "承运商原因")
    private List<String> vendorReasonSort = new ArrayList<>();

    @ApiModelProperty(value = "客户原因")
    private List<String> customerReasonSort = new ArrayList<>();

    @ApiModelProperty(value = "我方原因")
    private List<String> companyReasonSort = new ArrayList<>();

    public List<String> getVendorReasonSort() {
        return vendorReasonSort;
    }

    public void setVendorReasonSort(List<String> vendorReasonSort) {
        this.vendorReasonSort = vendorReasonSort;
    }

    public List<String> getCustomerReasonSort() {
        return customerReasonSort;
    }

    public void setCustomerReasonSort(List<String> customerReasonSort) {
        this.customerReasonSort = customerReasonSort;
    }

    public List<String> getCompanyReasonSort() {
        return companyReasonSort;
    }

    public void setCompanyReasonSort(List<String> companyReasonSort) {
        this.companyReasonSort = companyReasonSort;
    }
}
