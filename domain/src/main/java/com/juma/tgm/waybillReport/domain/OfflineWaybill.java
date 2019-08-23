package com.juma.tgm.waybillReport.domain;

import me.about.poi.ExcelColumn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * offline_waybill - offline_waybill
 * 
 * @author  2017-02-08
 * @version 1.0 
 */
public class OfflineWaybill implements Serializable {

    private Map<String,Integer> receiptTypeMapping = new HashMap<String,Integer>();
    
    {
        receiptTypeMapping.put("微信支付", 1);
        receiptTypeMapping.put("现金支付", 2);
        receiptTypeMapping.put("项目结算", 3);
        receiptTypeMapping.put("付现金，司机收款", 4);
        receiptTypeMapping.put("付现金，客户经理收款", 5);
    }
    
    private Integer offlineWaybillId;
	private Integer ownerEmployeeId;
	
	private Integer customerManagerId;
	@ExcelColumn(name="用车时间",required=true)
	private Date planDeliveryTime;
	private Integer driverId;
	@ExcelColumn(name="司机电话",required=true)
	private String driverPhone;
	@ExcelColumn(name="司机")
	private String driverName;
	private Integer driverType;
	private Integer truckId;
	private Integer truckTypeId;
    @ExcelColumn(name="车牌号",required=true)
	private String plateNumber;
	@ExcelColumn(name="税前费用",required=true)
	private Double estimateFreight;
	private Double afterTaxFreight;
	@ExcelColumn(name="司机结算价",required=true)
	private Double show4DriverFreight;
	private Integer taxRateId;
	@ExcelColumn(name="是否开票")
	private BigDecimal taxRateValue;
	private Integer receiptType;
	@ExcelColumn(name="付款方式")
	private String receiptTypeValue;
	private Integer customerId;
	@ExcelColumn(name="所属客户",required=true)
	private String customerName;
	@ExcelColumn(name="客户经理电话",required=true)
	private String customerManagerPhone;
	private Integer projectId;
	@ExcelColumn(name="所属项目")
	private String projectName;
	private Integer businessBranch;
	@ExcelColumn(name="省",required=true)
	private String province;
	@ExcelColumn(name="市",required=true)
	private String city;
	@ExcelColumn(name="区",required=true)
	private String district;
	private String regionCode;
	@ExcelColumn(name="街道号")
	private String street;
	@ExcelColumn(name="货物重量（吨）",required=true)
	private String goodsWeight;
	@ExcelColumn(name="货物类型")
	private String goodsType;
	@ExcelColumn(name="货物体积（方）")
	private String goodsVolume;
//	@ExcelColumn(name="是否回单")
	private String isBackValue;
	private Integer isBack;
//	@ExcelColumn(name="是否已对账")
	private String reconciliationStr;
    private Integer reconciliationStatus;
	@ExcelColumn(name="是否搬运")
	private String isCarryValue;
	private Integer isCarry;
	@ExcelColumn(name="司机搬运费")
	private Double driverHandlingCost;
	@ExcelColumn(name="小工姓名")
	private String laborerName;
	@ExcelColumn(name="小工搬运费")
	private Double laborerHandlingCost;
	@ExcelColumn(name="配送点数量")
	private Integer distributionPointNo;
	@ExcelColumn(name="下单备注")
	private String remark;
	private String areaCode;
	private String result;
	private Integer status;
	private Integer createUserId;
	private Date createTime;
	private Integer optUserId;
	private Integer tenantId;
	private String tenantCode;
	private Integer vehicleType;
	private Integer vehicleToVendor;
	private String vendorName;
	private String departmentCode;
    @ExcelColumn(name="物流产品")
	private String logisticsLabelName;
	private String logisticsLabel;

	
    public String getDepartmentCode() {
        return departmentCode;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getLogisticsLabelName() {
        return logisticsLabelName;
    }

    public void setLogisticsLabelName(String logisticsLabelName) {
        this.logisticsLabelName = logisticsLabelName;
    }

    public String getLogisticsLabel() {
        return logisticsLabel;
    }

    public void setLogisticsLabel(String logisticsLabel) {
        this.logisticsLabel = logisticsLabel;
    }

    public Map<String, Integer> getReceiptTypeMapping() {
        return receiptTypeMapping;
    }
    public void setReceiptTypeMapping(Map<String, Integer> receiptTypeMapping) {
        this.receiptTypeMapping = receiptTypeMapping;
    }
    public Integer getOfflineWaybillId() {
        return offlineWaybillId;
    }
    public void setOfflineWaybillId(Integer offlineWaybillId) {
        this.offlineWaybillId = offlineWaybillId;
    }

    public Integer getOwnerEmployeeId() {
        return ownerEmployeeId;
    }
    public void setOwnerEmployeeId(Integer ownerEmployeeId) {
        this.ownerEmployeeId = ownerEmployeeId;
    }
    
    public Integer getCustomerManagerId() {
        return customerManagerId;
    }
    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }
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
    public Integer getBusinessBranch() {
        return businessBranch;
    }
    public void setBusinessBranch(Integer businessBranch) {
        this.businessBranch = businessBranch;
    }
    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }
    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }
    public Integer getDriverId() {
        return driverId;
    }
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    public String getDriverPhone() {
        return driverPhone;
    }
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public Integer getDriverType() {
        return driverType;
    }
    public void setDriverType(Integer driverType) {
        this.driverType = driverType;
    }
    public Integer getTruckId() {
        return truckId;
    }
    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }
    public Integer getTruckTypeId() {
        return truckTypeId;
    }
    public void setTruckTypeId(Integer truckTypeId) {
        this.truckTypeId = truckTypeId;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public Double getEstimateFreight() {
        return estimateFreight;
    }
    public void setEstimateFreight(Double estimateFreight) {
        this.estimateFreight = estimateFreight;
    }
    public Double getAfterTaxFreight() {
        return afterTaxFreight;
    }
    public void setAfterTaxFreight(Double afterTaxFreight) {
        this.afterTaxFreight = afterTaxFreight;
    }
    public Integer getTaxRateId() {
        return taxRateId;
    }
    public void setTaxRateId(Integer taxRateId) {
        this.taxRateId = taxRateId;
    }
    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }
    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }
    public Integer getReceiptType() {
        return receiptType;
    }
    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }
    public String getReceiptTypeValue() {
        return receiptTypeValue;
    }
    public void setReceiptTypeValue(String receiptTypeValue) {
        this.receiptTypeValue = receiptTypeValue;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getRegionCode() {
        return regionCode;
    }
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getGoodsWeight() {
        return goodsWeight;
    }
    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }
    public String getGoodsType() {
        return goodsType;
    }
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
    public String getGoodsVolume() {
        return goodsVolume;
    }
    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }
    public String getIsBackValue() {
        return isBackValue;
    }
    public void setIsBackValue(String isBackValue) {
        this.isBackValue = isBackValue;
    }
    public Integer getIsBack() {
        return isBack;
    }
    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }
    public String getIsCarryValue() {
        return isCarryValue;
    }
    public void setIsCarryValue(String isCarryValue) {
        this.isCarryValue = isCarryValue;
    }
    public Integer getIsCarry() {
        return isCarry;
    }
    public void setIsCarry(Integer isCarry) {
        this.isCarry = isCarry;
    }
    public Double getDriverHandlingCost() {
        return driverHandlingCost;
    }
    public void setDriverHandlingCost(Double driverHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
    }
    public Double getLaborerHandlingCost() {
        return laborerHandlingCost;
    }
    public void setLaborerHandlingCost(Double laborerHandlingCost) {
        this.laborerHandlingCost = laborerHandlingCost;
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
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getOptUserId() {
        return optUserId;
    }
    public void setOptUserId(Integer optUserId) {
        this.optUserId = optUserId;
    }

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
    public Integer getReceiptTypeMappingValue(String key){
        return receiptTypeMapping.get(key);
    }
    public Double getShow4DriverFreight() {
        return show4DriverFreight;
    }
    public void setShow4DriverFreight(Double show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }
    public Integer getReconciliationStatus() {
        return reconciliationStatus;
    }
    public void setReconciliationStatus(Integer reconciliationStatus) {
        this.reconciliationStatus = reconciliationStatus;
    }
    public String getReconciliationStr() {
        return reconciliationStr;
    }
    public void setReconciliationStr(String reconciliationStr) {
        this.reconciliationStr = reconciliationStr;
    }
    public String getLaborerName() {
        return laborerName;
    }
    public void setLaborerName(String laborerName) {
        this.laborerName = laborerName;
    }
    public String getCustomerManagerPhone() {
        return customerManagerPhone;
    }
    public void setCustomerManagerPhone(String customerManagerPhone) {
        this.customerManagerPhone = customerManagerPhone;
    }
    public Integer getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }
    public Integer getVehicleToVendor() {
        return vehicleToVendor;
    }
    public void setVehicleToVendor(Integer vehicleToVendor) {
        this.vehicleToVendor = vehicleToVendor;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}