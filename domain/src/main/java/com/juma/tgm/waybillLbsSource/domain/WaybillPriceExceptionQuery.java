package com.juma.tgm.waybillLbsSource.domain;

import me.about.poi.ExcelColumn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by shawn_lin on 2017/8/23.
 */
public class WaybillPriceExceptionQuery implements Serializable{

    private static final long serialVersionUID = 4030539450741878931L;
    private Integer waybillId;
    //运单号
    @ExcelColumn(name = "运单号")
    private String waybillNo;
    /** 客户名称 */
    @ExcelColumn(name = "所属客户")
    private String customerName;
    /** 客户经理姓名(企业客户) */
    @ExcelColumn(name = "客户经理")
    private String customerManagerName;
    /** 司机名字 */
    @ExcelColumn(name = "司机")
    private String driverName;
    /** 税前费用 */
    @ExcelColumn(name = "税前费用")
    private BigDecimal estimateFreight;
    //时间成本
    @ExcelColumn(name = "时间成本")
    private Double timePrice;
    /** 计划配送时间 */
    @ExcelColumn(name = "用车时间")
    private String planDeliveryDate;
    //停车费；
    @ExcelColumn(name = "停车费")
    private BigDecimal carStopAmount;
    //过路费
    @ExcelColumn(name = "过路费")
    private BigDecimal roadAmount;
    //是否修改路线
    @ExcelColumn(name = "是否修改路线")
    private String isChangeDeliveryPointStr;
    //系统价格（成本价）
    @ExcelColumn(name = "系统价格")
    private BigDecimal calculatedFreight;
    //是否修改路线
    private Integer isChangeDeliveryPoint;
    
    private Date planDeliveryTime;
    
    private Integer customerId;
    private Integer customerManagerId;
    private Integer driverId;
    private Integer projectId;
    private Integer truckId;
    

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public String getIsChangeDeliveryPointStr() {
        return isChangeDeliveryPointStr;
    }

    public void setIsChangeDeliveryPointStr(String isChangeDeliveryPointStr) {
        this.isChangeDeliveryPointStr = isChangeDeliveryPointStr;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getCustomerManagerName() {
        return customerManagerName;
    }

    public void setCustomerManagerName(String customerManagerName) {
        this.customerManagerName = customerManagerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public Integer getIsChangeDeliveryPoint() {
        return isChangeDeliveryPoint;
    }

    public void setIsChangeDeliveryPoint(Integer isChangeDeliveryPoint) {
        this.isChangeDeliveryPoint = isChangeDeliveryPoint;
    }

    public BigDecimal getCalculatedFreight() {
        return calculatedFreight;
    }

    public void setCalculatedFreight(BigDecimal calculatedFreight) {
        this.calculatedFreight = calculatedFreight;
    }

    public BigDecimal getCarStopAmount() {
        return carStopAmount;
    }

    public void setCarStopAmount(BigDecimal carStopAmount) {
        this.carStopAmount = carStopAmount;
    }

    public BigDecimal getRoadAmount() {
        return roadAmount;
    }

    public void setRoadAmount(BigDecimal roadAmount) {
        this.roadAmount = roadAmount;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Double getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(Double timePrice) {
        this.timePrice = timePrice;
    }
}
