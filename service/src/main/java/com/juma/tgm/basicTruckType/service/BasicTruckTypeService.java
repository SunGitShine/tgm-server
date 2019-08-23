package com.juma.tgm.basicTruckType.service;

import com.juma.tgm.waybill.domain.TruckRequireInfo;

/**
 * 
 * @Description: 车型的基础信息
 * @author weilibin
 * @date 2016年6月30日 下午4:42:19
 * @version V1.0
 */
public interface BasicTruckTypeService {

    /**
     * 
     * 获取用车要求基本信息
     * 
     * @author Libin.Wei
     * @Date 2017年11月14日 下午2:36:26
     * @param tenantCode
     *            租户编码，不能为空
     * @param userId
     * @return
     */
    TruckRequireInfo getBasicTruckRequireInfo(int tenantId, Integer userId);

}
