package com.juma.tgm.waybill.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.waybill.domain.view.WaybillViewVo;

import java.util.List;

/**
 * 提供在视图是查询数据的功能
 * @ClassName: WaybillViewService
 * @Description:
 * @author: liang
 * @date: 2018-01-30 14:55
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public interface WaybillViewService {

    /**
     * 手机端运单视图搜索
     * <br/>
     * <b>使用视图实现，有效率问题，请谨慎使用</b>
     * @param pageCondition
     * @return
     * @throws BusinessException
     */
    List<WaybillViewVo> searchForApp(PageCondition pageCondition) throws BusinessException;

    int searchForAppCount(PageCondition pageCondition) throws BusinessException;
    
//    PageCondition buildProjectConditions(PageCondition pageCondition) throws BusinessException;
}
