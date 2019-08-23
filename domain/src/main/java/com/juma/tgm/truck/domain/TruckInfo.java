package com.juma.tgm.truck.domain;

import java.io.Serializable;
import java.util.List;

import com.juma.tgm.base.domain.BaseTruckDeviceInfo;

/**
 * @ClassName TruckInfo.java
 * @Description 车辆信息
 * @author Libin.Wei
 * @Date 2017年2月8日 下午4:08:39
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TruckInfo implements Serializable {

    private static final long serialVersionUID = -2799423475258786626L;
    /**
     * 配送中的车辆集合
     */
    private List<BaseTruckDeviceInfo> truckDeviceInfoList;

    public List<BaseTruckDeviceInfo> getTruckDeviceInfoList() {
        return truckDeviceInfoList;
    }

    public void setTruckDeviceInfoList(List<BaseTruckDeviceInfo> truckDeviceInfoList) {
        this.truckDeviceInfoList = truckDeviceInfoList;
    }

}
