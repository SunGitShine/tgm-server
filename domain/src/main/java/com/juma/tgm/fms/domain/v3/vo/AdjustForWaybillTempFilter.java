package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 18:38 2019-05-10
 */
@ApiModel("运单临时数据集搜索")
public class AdjustForWaybillTempFilter implements Serializable {
    private Integer adjustId;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("承运商名称")
    private String vendorName;
    @ApiModelProperty("司机名称")
    private String driverName;
    @ApiModelProperty("车牌号")
    private String plateNumber;
    @ApiModelProperty("本次调整金额")
    private BigDecimal adjustFreight;
    @ApiModelProperty("调整后金额(含税)")
    private BigDecimal afterAdjustFreight;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(final Integer adjustId) {
        this.adjustId = adjustId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(final String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(final String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public BigDecimal getAdjustFreight() {
        return adjustFreight;
    }

    public void setAdjustFreight(final BigDecimal adjustFreight) {
        this.adjustFreight = adjustFreight;
    }

    public BigDecimal getAfterAdjustFreight() {
        return afterAdjustFreight;
    }

    public void setAfterAdjustFreight(final BigDecimal afterAdjustFreight) {
        this.afterAdjustFreight = afterAdjustFreight;
    }
}
