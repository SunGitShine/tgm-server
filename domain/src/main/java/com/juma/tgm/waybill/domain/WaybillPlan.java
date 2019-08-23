package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: WaybillPlan
 * @Description: 自动派单 预约单
 * @author: Administrator
 * @date: 2017年3月9日 下午3:12:15
 *
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillPlan implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2316543518918485941L;

    private String waybillNo;

    private Integer customerId;
    
    private String customerName;

    private Date planDeliveryTime;
    
    private Integer employeeId;
    
    private String employeeName;
    
    private BigDecimal estimateFreight;
    
    private BigDecimal calculatedFreight;
    
    private Integer estimateDistance;

    private List<WaybillDeliveryAddress> deliveryAddr = new ArrayList<WaybillDeliveryAddress>();

    private List<WaybillReceiveAddress> receiveAddr = new ArrayList<WaybillReceiveAddress>();
    
    private WaybillTruckRequire truckRequire;

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public List<WaybillDeliveryAddress> getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(List<WaybillDeliveryAddress> deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public List<WaybillReceiveAddress> getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(List<WaybillReceiveAddress> receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public WaybillTruckRequire getTruckRequire() {
        return truckRequire;
    }

    public void setTruckRequire(WaybillTruckRequire truckRequire) {
        this.truckRequire = truckRequire;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public BigDecimal getCalculatedFreight() {
        return calculatedFreight;
    }

    public void setCalculatedFreight(BigDecimal calculatedFreight) {
        this.calculatedFreight = calculatedFreight;
    }

    public Integer getEstimateDistance() {
        return estimateDistance;
    }

    public void setEstimateDistance(Integer estimateDistance) {
        this.estimateDistance = estimateDistance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}
