package com.juma.tgm.truck.domain;

import com.juma.tgm.truck.vo.TruckFleetTruckVo;
import java.util.ArrayList;
import java.util.List;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * truck_fleet - 车队
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class TruckFleet extends BaseDomain {

    private static final long serialVersionUID = -4053540765812324078L;
    private Integer truckFleetId;
    private Integer employeeId;
    private Integer userId;
    private String truckFleetName;
    private String ownerCompany;
    private Integer status;
    // 行政区域编码
    private String regionCode;
    // 业务区域编码
    private String areaCode;
    // 租户编码
    private Integer tenantId;
    // 租户编码
    private String tenantCode;

    // 显示冗余
    // 客户经理
    private String accountManager;
    // 客户经理电话
    private String accountManagerPhone;
    // 关联车辆
    private List<TruckFleetTruck> listTruckFleetTruck = new ArrayList<TruckFleetTruck>();
    // 车牌号码
    private String plateNumbers;
    // 业务区域名称
    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getTruckFleetId() {
        return truckFleetId;
    }

    public void setTruckFleetId(Integer truckFleetId) {
        this.truckFleetId = truckFleetId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTruckFleetName() {
        return truckFleetName;
    }

    public void setTruckFleetName(String truckFleetName) {
        this.truckFleetName = truckFleetName;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getAccountManagerPhone() {
        return accountManagerPhone;
    }

    public void setAccountManagerPhone(String accountManagerPhone) {
        this.accountManagerPhone = accountManagerPhone;
    }

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }

    public List<TruckFleetTruck> getListTruckFleetTruck() {
        return listTruckFleetTruck;
    }

    public void setListTruckFleetTruck(List<TruckFleetTruck> listTruckFleetTruck) {
        this.listTruckFleetTruck = listTruckFleetTruck;
    }

}