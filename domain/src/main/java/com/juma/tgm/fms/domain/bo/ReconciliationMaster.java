package com.juma.tgm.fms.domain.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;

import me.about.poi.ExcelColumn;

public class ReconciliationMaster implements Serializable {

    private static final long serialVersionUID = 30797847199483131L;

    private Integer reconciliationId;

//    @ExcelColumn(name = "对帐单号", width = 30)
    private String reconciliationNo;

    private Integer reconciliationStatus;

    private Integer reconciliationItemId;

    private Integer waybillId;
    @ExcelColumn(name = "运单单号", width = 30)
    private String waybillNo;
    @ExcelColumn(name = "运单生成日期", width = 30)
    private String waybillCreateDate;
    @ExcelColumn(name = "车牌号", width = 30)
    private String plateNumber;
    @ExcelColumn(name = "运费金额", width = 30)
    private BigDecimal estimateFreight;
    @ExcelColumn(name = "税率", width = 30)
    private BigDecimal taxRateValue;
    @ExcelColumn(name = "税额", width = 30)
    private BigDecimal taxFee;
    @ExcelColumn(name = "不含税金额", width = 30)
    private BigDecimal afterTaxFreight;
    @ExcelColumn(name = "司机搬运费", width = 30)
    private BigDecimal driverHandlingFee;
    @ExcelColumn(name = "小工搬运费", width = 30)
    private BigDecimal laborerHandlingFee;
    private Integer customerId;
//    @ExcelColumn(name = "客户名称", width = 30)
    private String customerName;
//    @ExcelColumn(name = "项目名称", width = 30)
    private Integer projectId;
    private String projectName;
//    @ExcelColumn(name = "返点费", width = 30)
    private BigDecimal rebateFee;
//    @ExcelColumn(name = "司机价", width = 30)
    private BigDecimal show4DriverFreight;
    private Integer truckId;

    private String processInstanceId;

    private String areaCode;

    private Date submitTime;

    private Integer total;

    private String updateFreightRemark;

    private Date startTime;

    private Date endTime;

    private List<Integer> reconciliationItemIds = new ArrayList<Integer>();

    private List<ImageUploadManage> listImage = new ArrayList<ImageUploadManage>();

    // 对账单生成时间
    private Date createTime;
    
    private BigDecimal totalFee = BigDecimal.ZERO;

    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(String reconciliationNo) {
        this.reconciliationNo = reconciliationNo;
    }

    public Integer getReconciliationStatus() {
        return reconciliationStatus;
    }

    public void setReconciliationStatus(Integer reconciliationStatus) {
        this.reconciliationStatus = reconciliationStatus;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public BigDecimal getAfterTaxFreight() {
        return afterTaxFreight;
    }

    public void setAfterTaxFreight(BigDecimal afterTaxFreight) {
        this.afterTaxFreight = afterTaxFreight;
    }

    public BigDecimal getRebateFee() {
        return rebateFee;
    }

    public void setRebateFee(BigDecimal rebateFee) {
        this.rebateFee = rebateFee;
    }

    public BigDecimal getDriverHandlingFee() {
        return driverHandlingFee;
    }

    public void setDriverHandlingFee(BigDecimal driverHandlingFee) {
        this.driverHandlingFee = driverHandlingFee;
    }

    public BigDecimal getLaborerHandlingFee() {
        return laborerHandlingFee;
    }

    public void setLaborerHandlingFee(BigDecimal laborerHandlingFee) {
        this.laborerHandlingFee = laborerHandlingFee;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTaxFee() {
        if (estimateFreight != null && afterTaxFreight != null) {
            taxFee = estimateFreight.subtract(afterTaxFreight).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return taxFee;
    }

    public void setTaxFee(BigDecimal taxFee) {
        this.taxFee = taxFee;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getWaybillCreateDate() {
        return waybillCreateDate;
    }

    public void setWaybillCreateDate(String waybillCreateDate) {
        this.waybillCreateDate = waybillCreateDate;
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public String getUpdateFreightRemark() {
        return updateFreightRemark;
    }

    public void setUpdateFreightRemark(String updateFreightRemark) {
        this.updateFreightRemark = updateFreightRemark;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Integer> getReconciliationItemIds() {
        return reconciliationItemIds;
    }

    public void setReconciliationItemIds(List<Integer> reconciliationItemIds) {
        this.reconciliationItemIds = reconciliationItemIds;
    }

    public List<ImageUploadManage> getListImage() {
        return listImage;
    }

    public void setListImage(List<ImageUploadManage> listImage) {
        this.listImage = listImage;
    }

    public Integer getReconciliationItemId() {
        return reconciliationItemId;
    }

    public void setReconciliationItemId(Integer reconciliationItemId) {
        this.reconciliationItemId = reconciliationItemId;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

}
