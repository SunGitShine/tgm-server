/**
 * 
 */
package com.juma.tgm.driver.domain;

import java.io.Serializable;

import com.juma.tgm.waybill.domain.Waybill;

/**
 * 司机操作检查参数
 * 
 * @author weilibin
 *
 */
public class DriverBaseInfo implements Serializable {

    private static final long serialVersionUID = -7195955444399848435L;
    /** 是否开启接单 */
    private Integer isAcceptAllocateOrders;
    /** 待评价运单 */
    private Integer waybillIdNeedToEvaluate = 0;
    /** 司机待确认收款运单 */
    private Integer waybillIdNeedConfirmCeivedFreight = 0;
    /** 配送中的运单ID */
    private Integer deliveryingWaybillId = 0;
    /** 待配送的运单 */
    private Waybill willDeliveryWaybill;
    /** 司机状态 */
    private Integer driverStatus;
    /** 司机姓名 */
    private String nickname;
    /** 司机电话 */
    private String contactPhone;

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

    public Waybill getWillDeliveryWaybill() {
        return willDeliveryWaybill;
    }

    public void setWillDeliveryWaybill(Waybill willDeliveryWaybill) {
        this.willDeliveryWaybill = willDeliveryWaybill;
    }

    public Integer getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(Integer driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

}
