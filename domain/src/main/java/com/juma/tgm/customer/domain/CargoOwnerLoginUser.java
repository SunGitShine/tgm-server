package com.juma.tgm.customer.domain;

import java.io.Serializable;

import com.juma.auth.user.domain.LoginEcoUser;

/**
 * @ClassName: CustomerManagerLoginUser
 * @Description:
 * @author: liang
 * @date: 2017-05-18 21:26
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class CargoOwnerLoginUser implements Serializable {

    private static final long serialVersionUID = 8250636480432877908L;
    private Integer truckCustomerId;
    private LoginEcoUser loginEcoUser;
    private Integer waybillIdNeedToEvaluate = 0;// 待评价的运单
    private String regionCode;
    private String versionCheck;
    private String areaCode;
    private String tenantCode;

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public LoginEcoUser getLoginEcoUser() {
        return loginEcoUser;
    }

    public void setLoginEcoUser(LoginEcoUser loginEcoUser) {
        this.loginEcoUser = loginEcoUser;
    }

    public Integer getWaybillIdNeedToEvaluate() {
        return waybillIdNeedToEvaluate;
    }

    public void setWaybillIdNeedToEvaluate(Integer waybillIdNeedToEvaluate) {
        this.waybillIdNeedToEvaluate = waybillIdNeedToEvaluate;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getVersionCheck() {
        return versionCheck;
    }

    public void setVersionCheck(String versionCheck) {
        this.versionCheck = versionCheck;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
