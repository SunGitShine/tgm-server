/**
 * 
 */
package com.juma.tgm.customer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.juma.auth.employee.domain.LoginEmployee;

/**
 * @author vencent.lu
 *
 */
public class CustomerLoginUser implements Serializable {

    private static final long serialVersionUID = 4389282874112105197L;
    private LoginEmployee loginEmployee;
    private Integer waybillIdNeedToEvaluate = 0;// 待评价的运单
    private boolean hasTruckFleet = false;
    private List<String> permissionKeyList = new ArrayList<String>();
    private String versionCheck;

    private String regionCode;

    private boolean isShowLogisticsLabel;//是否展示物流产品标签

    public LoginEmployee getLoginEmployee() {
        return loginEmployee;
    }

    public void setLoginEmployee(LoginEmployee loginEmployee) {
        this.loginEmployee = loginEmployee;
    }

    public Integer getWaybillIdNeedToEvaluate() {
        return waybillIdNeedToEvaluate;
    }

    public void setWaybillIdNeedToEvaluate(Integer waybillIdNeedToEvaluate) {
        this.waybillIdNeedToEvaluate = waybillIdNeedToEvaluate;
    }

    public boolean isHasTruckFleet() {
        return hasTruckFleet;
    }

    public void setHasTruckFleet(boolean hasTruckFleet) {
        this.hasTruckFleet = hasTruckFleet;
    }

    public String getVersionCheck() {
        return versionCheck;
    }

    public void setVersionCheck(String versionCheck) {
        this.versionCheck = versionCheck;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public List<String> getPermissionKeyList() {
        return permissionKeyList;
    }

    public void setPermissionKeyList(List<String> permissionKeyList) {
        this.permissionKeyList = permissionKeyList;
    }

    public Date getCdate() {
        return new Date();
    }

    public boolean isShowLogisticsLabel() {
        return isShowLogisticsLabel;
    }

    public void setShowLogisticsLabel(boolean showLogisticsLabel) {
        isShowLogisticsLabel = showLogisticsLabel;
    }
}
