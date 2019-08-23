package com.juma.tgm.fms.domain.v3.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.juma.tgm.fms.domain.v3.ReconcilicationForPayableItem;

public class ReconciliationExtraForPayableBo extends ReconcilicationForPayableItem implements Serializable {
    private Integer extraId;

    private Integer reconciliationId;

    private Integer vendorId;

    private BigDecimal oilCardFee;

    private BigDecimal managementFee;

    private BigDecimal chargeTaxRate;

    private BigDecimal deductionTaxFee;

    private BigDecimal vendorTaxRate;

    private BigDecimal adjustTime;

    private Integer adjustUserId;

    //承运商名称
    private String vendorName;
    //承运商运作类型
    private String vendorSourceName;
    //承运商类型
    private String vendorTypeName;

    //司机名称
    private String driverName;

    //司机类型名称
    private String driverTypeName;

    //承运商不含税金额
    private BigDecimal vendorAfterTaxFreight;

    //用车时间
    private Date planDeliveryTime;

    //客户含税金额
    private BigDecimal estimateFreight;

    //是否开票
    private Boolean isInvoice;
    
    //税率
    private BigDecimal taxRateValue;

    //客户不含税金额
    private BigDecimal afterTaxFreight;

    //计税参考
    private BigDecimal referenceTaxFee;

    //司机结算价
    private BigDecimal show4DriverFreight;

    //调整金额
    private BigDecimal adjustFreight;

    //调整后含税金额
    private BigDecimal afterAdjustFreight;

    //车辆类型
    private Integer vehicleType;

    //承运商是否需要扣款
    private boolean vendorCharge;

    //应付金额
    private BigDecimal payableAmount;

    public BigDecimal getAfterAdjustFreight() {
        return afterAdjustFreight;
    }

    public void setAfterAdjustFreight(BigDecimal afterAdjustFreight) {
        this.afterAdjustFreight = afterAdjustFreight;
    }

    public BigDecimal getAdjustFreight() {
        return adjustFreight;
    }

    public void setAdjustFreight(BigDecimal adjustFreight) {
        this.adjustFreight = adjustFreight;

    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }

    public String getDriverTypeName() {
        return driverTypeName;
    }

    public void setDriverTypeName(String driverTypeName) {
        this.driverTypeName = driverTypeName;
    }

    public BigDecimal getReferenceTaxFee() {
        return referenceTaxFee;
    }

    public void setReferenceTaxFee(BigDecimal referenceTaxFee) {
        this.referenceTaxFee = referenceTaxFee;
    }

    public BigDecimal getVendorAfterTaxFreight() {
        return vendorAfterTaxFreight;
    }

    public void setVendorAfterTaxFreight(BigDecimal vendorAfterTaxFreight) {
        this.vendorAfterTaxFreight = vendorAfterTaxFreight;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public BigDecimal getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(BigDecimal taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public BigDecimal getAfterTaxFreight() {
        return afterTaxFreight;
    }

    public void setAfterTaxFreight(BigDecimal afterTaxFreight) {
        this.afterTaxFreight = afterTaxFreight;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getExtraId() {
        return extraId;
    }

    public void setExtraId(Integer extraId) {
        this.extraId = extraId;
    }

    public Integer getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(Integer reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public BigDecimal getOilCardFee() {
        return oilCardFee;
    }

    public void setOilCardFee(BigDecimal oilCardFee) {
        this.oilCardFee = oilCardFee;
    }

    public BigDecimal getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(BigDecimal managementFee) {
        this.managementFee = managementFee;
    }

    public BigDecimal getChargeTaxRate() {
        return chargeTaxRate;
    }

    public void setChargeTaxRate(BigDecimal chargeTaxRate) {
        this.chargeTaxRate = chargeTaxRate;
    }

    public BigDecimal getDeductionTaxFee() {
        return deductionTaxFee;
    }

    public void setDeductionTaxFee(BigDecimal deductionTaxFee) {
        this.deductionTaxFee = deductionTaxFee;
    }

    public BigDecimal getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(BigDecimal adjustTime) {
        this.adjustTime = adjustTime;
    }

    public Integer getAdjustUserId() {
        return adjustUserId;
    }

    public void setAdjustUserId(Integer adjustUserId) {
        this.adjustUserId = adjustUserId;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

	public String getVendorSourceName() {
		return vendorSourceName;
	}

	public void setVendorSourceName(String vendorSourceName) {
		this.vendorSourceName = vendorSourceName;
	}

	public String getVendorTypeName() {
		return vendorTypeName;
	}

	public void setVendorTypeName(String vendorTypeName) {
		this.vendorTypeName = vendorTypeName;
	}

    public BigDecimal getVendorTaxRate() {
        return vendorTaxRate;
    }

    public void setVendorTaxRate(BigDecimal vendorTaxRate) {
        this.vendorTaxRate = vendorTaxRate;
    }

    public boolean isVendorCharge() {
        return vendorCharge;
    }

    public void setVendorCharge(boolean vendorCharge) {
        this.vendorCharge = vendorCharge;
    }

    public Boolean getInvoice() {
        return isInvoice;
    }

    public void setInvoice(Boolean invoice) {
        isInvoice = invoice;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }
}