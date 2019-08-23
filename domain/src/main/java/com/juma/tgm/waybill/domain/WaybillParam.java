package com.juma.tgm.waybill.domain;

import com.juma.tgm.base.domain.BaseDomain;
import com.juma.tgm.project.domain.ValuationWay;
import me.about.poi.ExcelColumn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 运单费用
 *
 * @author weilibin
 */

public class WaybillParam extends BaseDomain {

    private static final long serialVersionUID = -6362236467917554356L;
    private Integer paramId;
    private Integer waybillId;
    private Integer taskId;
    private BigDecimal driverHandlingCost;
    private BigDecimal laborerHandlingCost;
    private String laborerName;
    private Integer distributionPointNo;


    private Integer sopId;

    private Float valuationConst;
    /**
     * 项目 计价方式 系数 json
     */
    private String valuationConstJson;
    /**
     * 项目运单-计价方式
     */
    private List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();

    private String remark;
    private String location;
    private BigDecimal handlingCost;
    @ExcelColumn(name = "结算金额")
    private BigDecimal checkOutFreight;
    @ExcelColumn(name = "收款账户")
    private String receivableAccount;
    @ExcelColumn(name = "收款时间")
    private Date receivableTime;
    @ExcelColumn(name = "备注")
    private String receivableRemark;
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    private BigDecimal agencyTakeFreight;
    private Integer agencyTakeFreightStatus;
    private Date uploadDeliveryPointSupplementTime;
    private Date updateDeliveryPointSupplementTime;
    private Integer driverRead;
    private Date driverReadTime;
    private Integer isCheckGoods;
    private BigDecimal lastEstimateFreight;
    // 项目运费规则json
    private String projectFreightRuleJson;
    // 最低温度
    private Float requiredMinTemperature;
    // 最高温度
    private Float requiredMaxTemperature;

    private Float historyMinTemperature;

    private Float historyMaxTemperature;
    /**
     * 导入ERP结果
     */
    private String erpResult;

    /**
     * 转运单费率
     */
    private BigDecimal transformBillVendorFeeRate;
    /**
     * 转运单关联运单id
     */
    private Integer transformBillLinkId;

    // 冗余字段
    private BigDecimal show4DriverFreight;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public enum AgencyTakeFreightStatus {
        STATUS_DRIVER(1, "司机"), STATUS_CMANAGER(2, "客户经理");

        private Integer code;
        private String desc;

        AgencyTakeFreightStatus(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getDesc() {
            return this.desc;
        }
    }

    public WaybillParam() {}

    public WaybillParam(BigDecimal driverHandlingCost, BigDecimal laborerHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
        this.laborerHandlingCost = laborerHandlingCost;
    }

    public Integer getDriverRead() {
        return driverRead;
    }

    public void setDriverRead(Integer driverRead) {
        this.driverRead = driverRead;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public BigDecimal getDriverHandlingCost() {
        return driverHandlingCost;
    }

    public void setDriverHandlingCost(BigDecimal driverHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
    }

    public BigDecimal getLaborerHandlingCost() {
        return laborerHandlingCost;
    }

    public void setLaborerHandlingCost(BigDecimal laborerHandlingCost) {
        this.laborerHandlingCost = laborerHandlingCost;
    }

    public String getLaborerName() {
        return laborerName;
    }

    public void setLaborerName(String laborerName) {
        this.laborerName = laborerName;
    }

    public Integer getDistributionPointNo() {
        return distributionPointNo;
    }

    public void setDistributionPointNo(Integer distributionPointNo) {
        this.distributionPointNo = distributionPointNo;
    }

    public String getRemark() {
        return remark;
    }

    public BigDecimal getCheckOutFreight() {
        return checkOutFreight;
    }

    public void setCheckOutFreight(BigDecimal checkOutFreight) {
        this.checkOutFreight = checkOutFreight;
    }

    public String getReceivableAccount() {
        return receivableAccount;
    }

    public void setReceivableAccount(String receivableAccount) {
        this.receivableAccount = receivableAccount;
    }

    public Date getReceivableTime() {
        return receivableTime;
    }

    public void setReceivableTime(Date receivableTime) {
        this.receivableTime = receivableTime;
    }

    public String getReceivableRemark() {
        return receivableRemark;
    }

    public void setReceivableRemark(String receivableRemark) {
        this.receivableRemark = receivableRemark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Date getUploadDeliveryPointSupplementTime() {
        return uploadDeliveryPointSupplementTime;
    }

    public void setUploadDeliveryPointSupplementTime(Date uploadDeliveryPointSupplementTime) {
        this.uploadDeliveryPointSupplementTime = uploadDeliveryPointSupplementTime;
    }

    public Date getUpdateDeliveryPointSupplementTime() {
        return updateDeliveryPointSupplementTime;
    }

    public void setUpdateDeliveryPointSupplementTime(Date updateDeliveryPointSupplementTime) {
        this.updateDeliveryPointSupplementTime = updateDeliveryPointSupplementTime;
    }

    public BigDecimal getLastEstimateFreight() {
        return lastEstimateFreight;
    }

    public void setLastEstimateFreight(BigDecimal lastEstimateFreight) {
        this.lastEstimateFreight = lastEstimateFreight;
    }

    public BigDecimal getHandlingCost() {
        if (null != this.driverHandlingCost && this.driverHandlingCost.compareTo(BigDecimal.ZERO) > 0
            && null != this.laborerHandlingCost && this.laborerHandlingCost.compareTo(BigDecimal.ZERO) > 0) {
            return this.driverHandlingCost.add(this.laborerHandlingCost);
        } else if (null != this.driverHandlingCost && this.driverHandlingCost.compareTo(BigDecimal.ZERO) > 0) {
            return this.driverHandlingCost;
        } else if (null != this.laborerHandlingCost && this.laborerHandlingCost.compareTo(BigDecimal.ZERO) > 0) {
            return this.laborerHandlingCost;
        }
        return handlingCost;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public String getProjectFreightRuleJson() {
        return projectFreightRuleJson;
    }

    public void setProjectFreightRuleJson(String projectFreightRuleJson) {
        this.projectFreightRuleJson = projectFreightRuleJson;
    }

    public String getErpResult() {
        return erpResult;
    }

    public void setErpResult(String erpResult) {
        this.erpResult = erpResult;
    }

    public BigDecimal getAgencyTakeFreight() {
        return agencyTakeFreight;
    }

    public void setAgencyTakeFreight(BigDecimal agencyTakeFreight) {
        this.agencyTakeFreight = agencyTakeFreight;
    }

    public Integer getAgencyTakeFreightStatus() {
        return agencyTakeFreightStatus;
    }

    public void setAgencyTakeFreightStatus(Integer agencyTakeFreightStatus) {
        this.agencyTakeFreightStatus = agencyTakeFreightStatus;
    }

    public Date getDriverReadTime() {
        return driverReadTime;
    }

    public void setDriverReadTime(Date driverReadTime) {
        this.driverReadTime = driverReadTime;
    }

    public Integer getIsCheckGoods() {
        return isCheckGoods;
    }

    public void setIsCheckGoods(Integer isCheckGoods) {
        this.isCheckGoods = isCheckGoods;
    }

    public List<ValuationWay> getValuationWays() {
        return valuationWays;
    }

    public void setValuationWays(List<ValuationWay> valuationWays) {
        this.valuationWays = valuationWays;
    }

    public String getValuationConstJson() {
        return valuationConstJson;
    }

    public void setValuationConstJson(String valuationConstJson) {
        this.valuationConstJson = valuationConstJson;
    }

    public Float getValuationConst() {
        return valuationConst;
    }

    public void setValuationConst(Float valuationConst) {
        this.valuationConst = valuationConst;
    }

    public Float getRequiredMinTemperature() {
        return requiredMinTemperature;
    }

    public void setRequiredMinTemperature(Float requiredMinTemperature) {
        this.requiredMinTemperature = requiredMinTemperature;
    }

    public Float getRequiredMaxTemperature() {
        return requiredMaxTemperature;
    }

    public void setRequiredMaxTemperature(Float requiredMaxTemperature) {
        this.requiredMaxTemperature = requiredMaxTemperature;
    }

    public Float getHistoryMinTemperature() {
        return historyMinTemperature;
    }

    public void setHistoryMinTemperature(Float historyMinTemperature) {
        this.historyMinTemperature = historyMinTemperature;
    }

    public Float getHistoryMaxTemperature() {
        return historyMaxTemperature;
    }

    public void setHistoryMaxTemperature(Float historyMaxTemperature) {
        this.historyMaxTemperature = historyMaxTemperature;
    }

    public Integer getSopId() {
        return sopId;
    }

    public void setSopId(Integer sopId) {
        this.sopId = sopId;
    }

    public BigDecimal getTransformBillVendorFeeRate() {
        return transformBillVendorFeeRate;
    }

    public void setTransformBillVendorFeeRate(BigDecimal transformBillVendorFeeRate) {
        this.transformBillVendorFeeRate = transformBillVendorFeeRate;
    }

    public Integer getTransformBillLinkId() {
        return transformBillLinkId;
    }

    public void setTransformBillLinkId(Integer transformBillLinkId) {
        this.transformBillLinkId = transformBillLinkId;
    }

    @Override
    public String toString() {
        return "WaybillParam [paramId=" + paramId + ", waybillId=" + waybillId + ", driverHandlingCost="
            + driverHandlingCost + ", laborerHandlingCost=" + laborerHandlingCost + ", laborerName=" + laborerName
            + ", distributionPointNo=" + distributionPointNo + ", valuationConst=" + valuationConst
            + ", valuationConstJson=" + valuationConstJson + ", valuationWays=" + valuationWays + ", remark="
            + remark + ", location=" + location + ", handlingCost=" + handlingCost + ", checkOutFreight="
            + checkOutFreight + ", receivableAccount=" + receivableAccount + ", receivableTime=" + receivableTime
            + ", receivableRemark=" + receivableRemark + ", waybillNo=" + waybillNo + ", agencyTakeFreight="
            + agencyTakeFreight + ", agencyTakeFreightStatus=" + agencyTakeFreightStatus
            + ", uploadDeliveryPointSupplementTime=" + uploadDeliveryPointSupplementTime
            + ", updateDeliveryPointSupplementTime=" + updateDeliveryPointSupplementTime + ", driverRead="
            + driverRead + ", driverReadTime=" + driverReadTime + ", lastEstimateFreight=" + lastEstimateFreight
            + ", projectFreightRuleJson=" + projectFreightRuleJson + ", requiredMinTemperature="
            + requiredMinTemperature + ", requiredMaxTemperature=" + requiredMaxTemperature
            + ", historyMinTemperature=" + historyMinTemperature + ", historyMaxTemperature="
            + historyMaxTemperature + ", erpResult=" + erpResult + ", show4DriverFreight=" + show4DriverFreight
            + "]";
    }

}
