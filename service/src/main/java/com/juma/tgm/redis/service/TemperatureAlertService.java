package com.juma.tgm.redis.service;


/**
 * 
 * @ClassName:   TemperatureAlertService   
 * @Description: 温控报警服务
 * @author:      Administrator
 * @date:        2018年5月15日 下午2:27:52  
 *
 * @Copyright:   2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface TemperatureAlertService {

    /**
     * 
     * @Title: startup  启动监控      电子围栏  离仓
     * @Description:
     * @param: plateNumber
     * @return: void
     * @throws
     */
    void startUp(Integer waybillId);
    
    /**
     * 
     * @Title: close
     * @Description: 关闭监控
     * @param: @param key:waybillId + "," + plateNumber
     * @return: void
     * @throws
     */
    void close(String key);
    /**
     * 
     * @Title: scan   扫描当前车辆列表  温度情况
     * @Description: 
     * @param:       
     * @return: void      
     * @throws
     */
    void scan();
    
    /**
     * 
     * @Title: getMinMaxTemperatureByPlateNumber   
     * @Description: 最低最高温度
     * @param: @param plateNumber
     * @param: @return      
     * @return: Float[]      
     * @throws
     */
    Float[] getMinMaxTemperatureByPlateNumber(String plateNumber, Integer tenantId,String tenantCode );
    /**
     * 
     * @Title: viewRedisToString   
     * @Description: 查看所有的缓存内容
     * @param: @return      
     * @return: String      
     * @throws
     */
    String viewRedisToString();
    
    
}
