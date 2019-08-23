/**
 * 
 */
package com.juma.tgm.driver.domain;

import java.io.Serializable;
import java.util.Date;

import com.juma.auth.user.domain.LoginEcoUser;

/**
 * @author vencent.lu
 *
 */
public class DriverLoginUser implements Serializable {

    private static final long serialVersionUID = 1333533959614817531L;
    private Integer driverId;
    private String regionCode;
    private Integer isAcceptAllocateOrders;
    private Integer waybillIdNeedToEvaluate = 0;
    private Integer waybillIdNeedConfirmCeivedFreight = 0;
    private Integer deliveryingWaybillId = 0;
    private Integer willDeliveryWaybillId = 0;
    private Integer driverStatus;
    private Integer employeeClass; 
    private String versionCheck;
    //android 原生APK版本号
    private String version;
    private LoginEcoUser loginEcoUser;
    private String headPortrait;

    public LoginEcoUser getLoginEcoUser() {
        return loginEcoUser;
    }

    public void setLoginEcoUser(LoginEcoUser loginEcoUser) {
        this.loginEcoUser = loginEcoUser;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getRegionCode() {
        return regionCode == null ? "" : regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getIsAcceptAllocateOrders() {
        return isAcceptAllocateOrders;
    }

    public void setIsAcceptAllocateOrders(Integer isAcceptAllocateOrders) {
        this.isAcceptAllocateOrders = isAcceptAllocateOrders;
    }

    public Integer getWaybillIdNeedToEvaluate() {
        return waybillIdNeedToEvaluate;
    }

    public void setWaybillIdNeedToEvaluate(Integer waybillIdNeedToEvaluate) {
        this.waybillIdNeedToEvaluate = waybillIdNeedToEvaluate;
    }

    public Integer getWaybillIdNeedConfirmCeivedFreight() {
        return waybillIdNeedConfirmCeivedFreight;
    }

    public void setWaybillIdNeedConfirmCeivedFreight(Integer waybillIdNeedConfirmCeivedFreight) {
        this.waybillIdNeedConfirmCeivedFreight = waybillIdNeedConfirmCeivedFreight;
    }

    public Integer getDeliveryingWaybillId() {
        return deliveryingWaybillId;
    }

    public void setDeliveryingWaybillId(Integer deliveryingWaybillId) {
        this.deliveryingWaybillId = deliveryingWaybillId;
    }

    public Integer getWillDeliveryWaybillId() {
        return willDeliveryWaybillId;
    }

    public void setWillDeliveryWaybillId(Integer willDeliveryWaybillId) {
        this.willDeliveryWaybillId = willDeliveryWaybillId;
    }

    public Integer getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(Integer driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Date getCdate() {
        return new Date();
    }

    public String getVersionCheck() {
        return versionCheck;
    }

    public void setVersionCheck(String versionCheck) {
        this.versionCheck = versionCheck;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "DriverLoginUser [driverId=" + driverId + ", regionCode=" + regionCode + ", isAcceptAllocateOrders="
                + isAcceptAllocateOrders + ", waybillIdNeedToEvaluate=" + waybillIdNeedToEvaluate
                + ", waybillIdNeedConfirmCeivedFreight=" + waybillIdNeedConfirmCeivedFreight + ", deliveryingWaybillId="
                + deliveryingWaybillId + ", willDeliveryWaybillId=" + willDeliveryWaybillId + ", driverStatus="
                + driverStatus + ", versionCheck=" + versionCheck + "]";
    }

    public Integer getEmployeeClass() {
        return employeeClass;
    }

    public void setEmployeeClass(Integer employeeClass) {
        this.employeeClass = employeeClass;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
