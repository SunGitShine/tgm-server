package com.juma.tgm.waybill.dao;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.giants.dal.dao.GiantsDao;
import com.juma.tgm.waybill.domain.WaybillException;
import com.juma.tgm.waybill.domain.WaybillExceptionBo;

public interface WaybillExceptionDao extends GiantsDao<WaybillException> {

    /**
     * 
     * @Title: searchCount
     * @Description: 获取运单异常总数
     * @param pageCondition
     * @return int
     * @throws
     */
    int searchCount(PageCondition pageCondition);

    /**
     * 
     * @Title: searchDetails
     * @Description: 分页获取运单异常信息
     * @param pageCondition
     * @return List<WaybillExceptionBo>    
     * @throws
     */
    List<WaybillExceptionBo> searchDetails(PageCondition pageCondition);

    /**
     * 
     * @Title: deleteByWaybillId
     * @Description: 根据订单号删除
     * @param waybillException
     * @throws
     */
    void deleteByWaybillId(WaybillException waybillException);
}
