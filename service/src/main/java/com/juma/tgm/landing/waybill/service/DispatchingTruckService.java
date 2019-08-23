package com.juma.tgm.landing.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.ai.Feature;

@Deprecated
public interface DispatchingTruckService {

    /**
     * 
     * @Title: findTruck   
     * @Description: 给数据平台为运单找车的接口
     * @param: @param waybillId      
     * @return: void      
     * @throws
     */
    void findTruck(Integer waybillId,LoginUser loginUser);
    
    /**
     * 
     * @Title: updateAddressSequence   
     * @Description: 更新地址顺序
     * @param: @param feature      
     * @return: void      
     * @throws
     */
    void updateAddressSequence(Feature feature);
    
    /**
     * 
     * @Title: buildFeature   
     * @Description: 构建Feature
     * @param: @param waybillId
     * @param: @param operate      
     * @return: void      
     * @throws
     */
    void buildFeature(Integer waybillId,Feature.Operate operate, LoginUser loginUser);
    
    /**
     * 
     * @Title: doNoDriverAnswerWaybill   
     * @Description: 处理没有司机响应的运单 
     * @param:       
     * @return: void      
     * @throws
     */
    void doNoDriverAnswerWaybill();
    
    
    /**
     * 
     * @Title: doDriverAnswerWaybill   
     * @Description: 司机确认接单
     * @param:       
     * @return: void      
     * @throws
     */
    void doDriverAnswerWaybill(Integer waybillId,LoginUser loginUser) throws BusinessException;
    
    /**
     * 
     * @Title: removeBlacklistCache   
     * @Description: 清除缓存  
     * @param: @param waybillId      
     * @return: void      
     * @throws
     */
    void removeBlacklistCache(Integer waybillId);
    
    
}
