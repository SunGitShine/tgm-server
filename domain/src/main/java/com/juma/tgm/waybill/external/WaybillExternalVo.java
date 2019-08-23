package com.juma.tgm.waybill.external;

import com.juma.tgm.waybill.domain.Waybill;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName WaybillExternalVo
 * @Description TODO
 * @Author weilibin
 * @Date 2019-08-07 14:09
 * @Version 1.0.0
 */

public class WaybillExternalVo implements Serializable {

    // 运单id
    private Integer waybillId;

    // 运单编号
    private String waybillNo;

    // 签约方
    private Integer contractToCompany;

    // 签约方统一社会信用代码
    private String contractToCompanyCreditCode;

    // 运作方
    private Integer payToCompany;

    // 运作方统一社会信用代码
    private String payToCompanyCreditCode;

    // 客户侧对账单号
    private String receivableReconcilicationNo;

    // 客户侧对账状态
    private Integer receivableReconcilicationStatus;

    // 承运侧对帐单号
    private String reconciliationNo;

    // 承运侧运单对账状态
    private Integer reconciliationStatus;

    // 货车id
    private Integer truckId;

    // 货车车牌号
    private String plateNumber;

    // 司机id
    private Integer driverId;

    // 司机类型 (自营、非自营)
    private Integer driverType;

    // 企业客户id
    private Integer customerId;

    // 企业客户所属客户经理id
    private Integer customerManagerId;

    // 项目 Id
    private Integer projectId;

    // 项目经理
    private Integer projectManagerUserId;

    // 派车方式
    private Integer receiveWay;

    // 运单展示状态
    private Integer statusView;

    // 用车时间
    private Date planDeliveryTime;

    // 客户侧初始含税价格
    private BigDecimal customerFreightWithTax;

    // 客户侧初始不含税价格（减去税费）
    private BigDecimal customerFreightWithNotTax;

    // 承运侧初始含税价格
    private BigDecimal vendorFreightWithTax;

    // 到仓时间
    private Date arriveDepotTime;

    // 离仓时间
    private Date leaveDepotTime;

    // 接单时间或被指派时间
    private Date receivingTime;

    // 完成时间
    private Date finishTime;

    // 经济人预计完成时间
    private Date cmEstimateFinishTime;

    // 收款方式
    private Integer receiptType;

    // 派车备注
    private String assignWaybillRemark;

    // 运单来源
    private Integer waybillSource;

    // 回单状态
    private Integer needReceipt;

    // 取消渠道
    private Integer cancelChannel;

    // 业务区域
    private String areaCode;

    // 租户Id
    private Integer tenantId;

    // 租户名字
    private String tenantCode;

    // 是不是测试单子
    private Boolean isTest;

    // 是否需要上传配送单
    private Integer needDeliveryPointNote;

    // 司机是否已读
    private boolean isDriverHasRead;

    // 结算状态
    private Integer settlementStatus;

    // 收款状态
    private Integer receiptStatus;

    // 承运商id
    private Integer vendorId;

    // 路线id
    private Integer roadMapId;

    // 车辆类型
    private Integer vehicleType;

    // 运单创建时间
    private Date createTime;

    // 运单创建人
    private Integer createUserId;

    // 最后修改人
    private Integer lastUpdateUserId;

    // 最后修改时间
    private Date lastUpdateTime;

    public WaybillExternalVo() {
    }

    public WaybillExternalVo(Waybill waybill) {
        if (null == waybill) {
            return;
        }

        BeanUtils.copyProperties(waybill, this);
        this.contractToCompany = waybill.getDepartmentId();
        this.customerFreightWithTax = waybill.getEstimateFreight();
        this.customerFreightWithNotTax = waybill.getAfterTaxFreight();
        this.vendorFreightWithTax = waybill.getShow4DriverFreight();
        this.vendorId = waybill.getVehicleToVendor();
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getContractToCompany() {
        return contractToCompany;
    }

    public void setContractToCompany(Integer contractToCompany) {
        this.contractToCompany = contractToCompany;
    }

    public String getContractToCompanyCreditCode() {
        return contractToCompanyCreditCode;
    }

    public void setContractToCompanyCreditCode(String contractToCompanyCreditCode) {
        this.contractToCompanyCreditCode = contractToCompanyCreditCode;
    }

    public Integer getPayToCompany() {
        return payToCompany;
    }

    public void setPayToCompany(Integer payToCompany) {
        this.payToCompany = payToCompany;
    }

    public String getPayToCompanyCreditCode() {
        return payToCompanyCreditCode;
    }

    public void setPayToCompanyCreditCode(String payToCompanyCreditCode) {
        this.payToCompanyCreditCode = payToCompanyCreditCode;
    }

    public String getReceivableReconcilicationNo() {
        return receivableReconcilicationNo;
    }

    public void setReceivableReconcilicationNo(String receivableReconcilicationNo) {
        this.receivableReconcilicationNo = receivableReconcilicationNo;
    }

    public Integer getReceivableReconcilicationStatus() {
        return receivableReconcilicationStatus;
    }

    public void setReceivableReconcilicationStatus(Integer receivableReconcilicationStatus) {
        this.receivableReconcilicationStatus = receivableReconcilicationStatus;
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

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getDriverType() {
        return driverType;
    }

    public void setDriverType(Integer driverType) {
        this.driverType = driverType;
    }

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectManagerUserId() {
        return projectManagerUserId;
    }

    public void setProjectManagerUserId(Integer projectManagerUserId) {
        this.projectManagerUserId = projectManagerUserId;
    }

    public Integer getReceiveWay() {
        return receiveWay;
    }

    public void setReceiveWay(Integer receiveWay) {
        this.receiveWay = receiveWay;
    }

    public Integer getStatusView() {
        return statusView;
    }

    public void setStatusView(Integer statusView) {
        this.statusView = statusView;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public BigDecimal getCustomerFreightWithTax() {
        return customerFreightWithTax;
    }

    public void setCustomerFreightWithTax(BigDecimal customerFreightWithTax) {
        this.customerFreightWithTax = customerFreightWithTax;
    }

    public BigDecimal getCustomerFreightWithNotTax() {
        return customerFreightWithNotTax;
    }

    public void setCustomerFreightWithNotTax(BigDecimal customerFreightWithNotTax) {
        this.customerFreightWithNotTax = customerFreightWithNotTax;
    }

    public BigDecimal getVendorFreightWithTax() {
        return vendorFreightWithTax;
    }

    public void setVendorFreightWithTax(BigDecimal vendorFreightWithTax) {
        this.vendorFreightWithTax = vendorFreightWithTax;
    }

    public Date getArriveDepotTime() {
        return arriveDepotTime;
    }

    public void setArriveDepotTime(Date arriveDepotTime) {
        this.arriveDepotTime = arriveDepotTime;
    }

    public Date getLeaveDepotTime() {
        return leaveDepotTime;
    }

    public void setLeaveDepotTime(Date leaveDepotTime) {
        this.leaveDepotTime = leaveDepotTime;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCmEstimateFinishTime() {
        return cmEstimateFinishTime;
    }

    public void setCmEstimateFinishTime(Date cmEstimateFinishTime) {
        this.cmEstimateFinishTime = cmEstimateFinishTime;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public String getAssignWaybillRemark() {
        return assignWaybillRemark;
    }

    public void setAssignWaybillRemark(String assignWaybillRemark) {
        this.assignWaybillRemark = assignWaybillRemark;
    }

    public Integer getWaybillSource() {
        return waybillSource;
    }

    public void setWaybillSource(Integer waybillSource) {
        this.waybillSource = waybillSource;
    }

    public Integer getNeedReceipt() {
        return needReceipt;
    }

    public void setNeedReceipt(Integer needReceipt) {
        this.needReceipt = needReceipt;
    }

    public Integer getCancelChannel() {
        return cancelChannel;
    }

    public void setCancelChannel(Integer cancelChannel) {
        this.cancelChannel = cancelChannel;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public Integer getNeedDeliveryPointNote() {
        return needDeliveryPointNote;
    }

    public void setNeedDeliveryPointNote(Integer needDeliveryPointNote) {
        this.needDeliveryPointNote = needDeliveryPointNote;
    }

    public boolean isDriverHasRead() {
        return isDriverHasRead;
    }

    public void setDriverHasRead(boolean driverHasRead) {
        isDriverHasRead = driverHasRead;
    }

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Integer getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Integer receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
