package com.juma.tgm.basicTruckType.service;

import java.util.Map;

import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.user.domain.CurrentUser;
import com.juma.tgm.waybill.domain.map.MapBusinessInfo;

/**
 * 位置信息
 * 
 * @author weilibin
 *
 */

public interface LocationService {

    /**
     * 获取司机货车业务基础数据
     * 
     * @return MapBusinessInfo
     */
    MapBusinessInfo view(CurrentUser currentUser, LoginEmployee loginEmployee);

    /**
     * 获取司机货车回调业务数据
     * 
     * @param plateNumber
     *            车牌号
     * @return String
     */
    Map<String, Object> callback(String plateNumber, LoginUser loginUser);

    /**
     * 
     * @Description 根据设备号获取坐标信息，通过在途监控获取
     * @author Libin.Wei
     * @Date 2017年2月23日 下午1:52:34
     * @param deviceNo
     * @return
     */
    @Deprecated
    String findCoordinate(String deviceNo);
}
