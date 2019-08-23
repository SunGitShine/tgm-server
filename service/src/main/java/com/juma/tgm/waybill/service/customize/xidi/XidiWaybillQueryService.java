package com.juma.tgm.waybill.service.customize.xidi;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.domain.WaybillMap;

/**
 * @ClassName: XidiWaybillQueryService
 * @Description:
 * @author: liang
 * @date: 2018-03-14 19:21
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface XidiWaybillQueryService {

    /**
     * @throws @Title: findWaybillMapById
     * @Description: 在途监控 回放
     * @param: @param waybillId
     * @param: @return
     * @return: WaybillMap
     */
    WaybillMap findWaybillMapById(Integer waybillId) throws BusinessException;
}
