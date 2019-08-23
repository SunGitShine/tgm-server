package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.domain.WaybillNotice;

public interface WaybillNoticeService {
    /**
     * 
     * @Title:       save   
     * @Description: 运单通知记录
     * @param:       @param waybillNotice
     * @param:       @throws BusinessException      
     * @return:      void      
     * @throws
     */
    void save(WaybillNotice waybillNotice) throws BusinessException;
    
    /**
     * 
     * @Title:       findBy   
     * @Description: 查看运单通知信息
     * @param:       @param waybillId
     * @param:       @return      
     * @return:      WaybillNotice      
     * @throws
     */
    WaybillNotice findBy(int waybillId);
}
