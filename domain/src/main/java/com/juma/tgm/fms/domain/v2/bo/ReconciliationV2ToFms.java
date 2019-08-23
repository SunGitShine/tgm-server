package com.juma.tgm.fms.domain.v2.bo;

import com.juma.tgm.waybill.domain.Waybill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReconciliationV2ToFms implements Serializable {

    private static final long serialVersionUID = -3361704119801784452L;
    private Integer tenantId;
    private String tenantCode;

    private Integer crmCustomerId;

    private String customerName;


    private Integer type ;

    /**
     * 客户的客户经理userId
     */
    private Integer customerManagerUserId;

    /**
     * 业务区域code
     */
    private String areaCode;

    /**
     * 对账单id
     */
    private Integer reconciliationId;
    /**
     * 对账单号
     */
    private String reconciliationNo;

    /**
     * 司机
     * 初始不含税价格
     */
    private BigDecimal driverWithoutTaxInitialFreight;
    /**
     * 司机
     * 初始含税价格
     */
    private BigDecimal driverWithTaxInitialFreight;

    /**
     * 司机
     * 最终不含税价格
     */
    private BigDecimal driverWithoutTaxFinalFreight;

    /**
     * 司机
     * 最终含税价格
     */
    private BigDecimal driverWithTaxFinalFreight;

    /**
     * 客户
     * 初始不含税价格
     */
    private BigDecimal customerWithoutTaxInitialFreight;
    /**
     * 客户
     * 初始含税价格
     */
    private BigDecimal customerWithTaxInitialFreight;
    /**
     * 客户
     * 最终不含税价格
     */
    private BigDecimal customerWithoutTaxFinalFreight;
    /**
     * 客户
     * 最终含税价格
     */
    private BigDecimal customerWithTaxFinalFreight;

    /***
     *
     * 所有运单相关详情
     *
     * */
    private List<Waybill> waybills = new ArrayList<>();

    /**
     * 对司机结算明细
     */
    private List<ReconciliationVehicleItemToFms> listReconciliationVehicleItemToFms = new ArrayList<ReconciliationVehicleItemToFms>();


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCrmCustomerId() {
        return crmCustomerId;
    }

    public void setCrmCustomerId(Integer crmCustomerId) {
        this.crmCustomerId = crmCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<Waybill> getWaybills() {
        return waybills;
    }

    public void setWaybills(List<Waybill> waybills) {
        this.waybills = waybills;
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

    public BigDecimal getDriverWithoutTaxInitialFreight() {
        return driverWithoutTaxInitialFreight;
    }

    public void setDriverWithoutTaxInitialFreight(BigDecimal driverWithoutTaxInitialFreight) {
        this.driverWithoutTaxInitialFreight = driverWithoutTaxInitialFreight;
    }

    public BigDecimal getDriverWithTaxInitialFreight() {
        return driverWithTaxInitialFreight;
    }

    public void setDriverWithTaxInitialFreight(BigDecimal driverWithTaxInitialFreight) {
        this.driverWithTaxInitialFreight = driverWithTaxInitialFreight;
    }

    public BigDecimal getDriverWithoutTaxFinalFreight() {
        return driverWithoutTaxFinalFreight;
    }

    public void setDriverWithoutTaxFinalFreight(BigDecimal driverWithoutTaxFinalFreight) {
        this.driverWithoutTaxFinalFreight = driverWithoutTaxFinalFreight;
    }

    public BigDecimal getDriverWithTaxFinalFreight() {
        return driverWithTaxFinalFreight;
    }

    public void setDriverWithTaxFinalFreight(BigDecimal driverWithTaxFinalFreight) {
        this.driverWithTaxFinalFreight = driverWithTaxFinalFreight;
    }

    public BigDecimal getCustomerWithoutTaxInitialFreight() {
        return customerWithoutTaxInitialFreight;
    }

    public void setCustomerWithoutTaxInitialFreight(BigDecimal customerWithoutTaxInitialFreight) {
        this.customerWithoutTaxInitialFreight = customerWithoutTaxInitialFreight;
    }

    public BigDecimal getCustomerWithTaxInitialFreight() {
        return customerWithTaxInitialFreight;
    }

    public void setCustomerWithTaxInitialFreight(BigDecimal customerWithTaxInitialFreight) {
        this.customerWithTaxInitialFreight = customerWithTaxInitialFreight;
    }

    public BigDecimal getCustomerWithoutTaxFinalFreight() {
        return customerWithoutTaxFinalFreight;
    }

    public void setCustomerWithoutTaxFinalFreight(BigDecimal customerWithoutTaxFinalFreight) {
        this.customerWithoutTaxFinalFreight = customerWithoutTaxFinalFreight;
    }

    public BigDecimal getCustomerWithTaxFinalFreight() {
        return customerWithTaxFinalFreight;
    }

    public void setCustomerWithTaxFinalFreight(BigDecimal customerWithTaxFinalFreight) {
        this.customerWithTaxFinalFreight = customerWithTaxFinalFreight;
    }

    public List<ReconciliationVehicleItemToFms> getListReconciliationVehicleItemToFms() {
        return listReconciliationVehicleItemToFms;
    }

    public void setListReconciliationVehicleItemToFms(List<ReconciliationVehicleItemToFms> listReconciliationVehicleItemToFms) {
        this.listReconciliationVehicleItemToFms = listReconciliationVehicleItemToFms;
    }


    public Integer getCustomerManagerUserId() {
        return customerManagerUserId;
    }

    public void setCustomerManagerUserId(Integer customerManagerUserId) {
        this.customerManagerUserId = customerManagerUserId;
    }
}
