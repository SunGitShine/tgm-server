package com.juma.tgm.fms.domain.v2.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReconciliationVehicleItemToFms implements Serializable {

    private static final long serialVersionUID = 5489640791890326656L;
    private Integer projectId;
    private String projectName;
    /**
     * 税率
     */
    private BigDecimal taxRate;
    private String plateNumber;
    /**
     * 业务区域
     */
    private String areaCode;
    /**
     * 备注
     */
    private String remark;
    private Date createTime;

    /**
     * 不含税初始费用
     */
    private BigDecimal withoutTaxInitialFreight;

    /**
     * 含税初始费用
     */
    private BigDecimal withTaxInitialFreight;

    /**
     * 不含税最终费用
     *
     */
    private BigDecimal withoutTaxFinalFreight;
    /**
     * 含税最终费用
     *
     */
    private BigDecimal withTaxFinalFreight;
    /**
     * 返点费
     */
    private BigDecimal rebateFee;

    /**
     * 小工搬运费
     *
     */
    private BigDecimal laborCarryFee;
    /**
     * 司机搬运费
     *
     */
    private BigDecimal driverCarryFee;

    private Integer vendorId;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    //TODO 承租人
    //TODO 承租人类型
    //TODO 用车时间？

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getWithoutTaxInitialFreight() {
        return withoutTaxInitialFreight;
    }

    public void setWithoutTaxInitialFreight(BigDecimal withoutTaxInitialFreight) {
        this.withoutTaxInitialFreight = withoutTaxInitialFreight;
    }

    public BigDecimal getWithTaxInitialFreight() {
        return withTaxInitialFreight;
    }

    public void setWithTaxInitialFreight(BigDecimal withTaxInitialFreight) {
        this.withTaxInitialFreight = withTaxInitialFreight;
    }

    public BigDecimal getWithoutTaxFinalFreight() {
        return withoutTaxFinalFreight;
    }

    public void setWithoutTaxFinalFreight(BigDecimal withoutTaxFinalFreight) {
        this.withoutTaxFinalFreight = withoutTaxFinalFreight;
    }

    public BigDecimal getWithTaxFinalFreight() {
        return withTaxFinalFreight;
    }

    public void setWithTaxFinalFreight(BigDecimal withTaxFinalFreight) {
        this.withTaxFinalFreight = withTaxFinalFreight;
    }

    public BigDecimal getRebateFee() {
        return rebateFee;
    }

    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
    }

    public BigDecimal getLaborCarryFee() {
        return laborCarryFee;
    }

    public void setLaborCarryFee(BigDecimal laborCarryFee) {
        this.laborCarryFee = laborCarryFee;
    }

    public BigDecimal getDriverCarryFee() {
        return driverCarryFee;
    }

    public void setDriverCarryFee(BigDecimal driverCarryFee) {
        this.driverCarryFee = driverCarryFee;
    }
}
