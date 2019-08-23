package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.domain.WaybillViewHistory;

public interface WaybillViewHistoryService {

    /**
     * 
     * @Title:       save   
     * @Description: 运单被查看记录  
     * @param:       @param waybillViewHistory
     * @param:       @throws BusinessException      
     * @return:      void      
     * @throws
     */
    void save(WaybillViewHistory waybillViewHistory) throws BusinessException;
    
  
    /**
     * 
     * @Title:       findBy   
     * @Description: 判断司机是否已经查看该运单
     * @param:       @param waybillId
     * @param:       @param driverId
     * @param:       @return      
     * @return:      WaybillViewHistory      
     * @throws
     */
    WaybillViewHistory findBy(int waybillId,int driverId);
    
}
