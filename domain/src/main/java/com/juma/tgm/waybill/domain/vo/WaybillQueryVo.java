package com.juma.tgm.waybill.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WaybillQueryVo.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月21日 下午7:23:19
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class WaybillQueryVo implements Serializable {

    private static final long serialVersionUID = -8866424504361011014L;
    private Integer waybillId;
    private String statusViewText;
    private String waybillNo;
    private Integer driverId;
    private String driverName;
    private Integer customerId;
    private String customerName;
    private Date planDeliveryTime;
    private Date uploadDeliveryPointSupplementTime;
    private Date updateDeliveryPointSupplementTime;
    private Integer changeDeliveryPoint;
    private String changeDeliveryPointText;
    private List<String> imgUrlList;
    // 能否修改路线 true：可以
    private boolean isAllowOperate = true;

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getStatusViewText() {
        return statusViewText;
    }

    public void setStatusViewText(String statusViewText) {
        this.statusViewText = statusViewText;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getPlanDeliveryTime() {
        return planDeliveryTime;
    }

    public void setPlanDeliveryTime(Date planDeliveryTime) {
        this.planDeliveryTime = planDeliveryTime;
    }

    public Date getUploadDeliveryPointSupplementTime() {
        return uploadDeliveryPointSupplementTime;
    }

    public void setUploadDeliveryPointSupplementTime(Date uploadDeliveryPointSupplementTime) {
        this.uploadDeliveryPointSupplementTime = uploadDeliveryPointSupplementTime;
    }

    public Date getUpdateDeliveryPointSupplementTime() {
        return updateDeliveryPointSupplementTime;
    }

    public void setUpdateDeliveryPointSupplementTime(Date updateDeliveryPointSupplementTime) {
        this.updateDeliveryPointSupplementTime = updateDeliveryPointSupplementTime;
    }

    public Integer getChangeDeliveryPoint() {
        return changeDeliveryPoint;
    }

    public void setChangeDeliveryPoint(Integer changeDeliveryPoint) {
        this.changeDeliveryPoint = changeDeliveryPoint;
    }

    public String getChangeDeliveryPointText() {
        return changeDeliveryPointText;
    }

    public void setChangeDeliveryPointText(String changeDeliveryPointText) {
        this.changeDeliveryPointText = changeDeliveryPointText;
    }

    public boolean isAllowOperate() {
        return isAllowOperate;
    }

    public void setAllowOperate(boolean isAllowOperate) {
        this.isAllowOperate = isAllowOperate;
    }

}
