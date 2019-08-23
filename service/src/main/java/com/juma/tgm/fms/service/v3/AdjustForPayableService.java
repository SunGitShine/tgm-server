package com.juma.tgm.fms.service.v3;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.fms.domain.v3.AdjustForPayable;
import com.juma.tgm.fms.domain.v3.bo.AdjustForPayableQuery;

/**
 * @ClassName AdjustForPayableService.java
 * @Description 应付账单调整记录
 * @author Libin.Wei
 * @Date 2018年11月23日 上午10:15:52
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface AdjustForPayableService {

    /**
     * 
     * 分页获取
     * 
     * @author Libin.Wei
     * @Date 2018年11月23日 上午10:53:26
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<AdjustForPayableQuery> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据运单ID获取第一条数据
     * 
     * @author Libin.Wei
     * @Date 2018年11月23日 上午11:00:51
     * @param waybillId
     * @return
     */
    AdjustForPayable findFirstByWaybillId(Integer waybillId);

    /**
     * 
     * 根据运单ID获取最后一条数据
     * 
     * @author Libin.Wei
     * @Date 2018年11月23日 上午11:00:44
     * @param waybillId
     * @return
     */
    AdjustForPayable findLastByWaybillId(Integer waybillId);

    /**
     * 
     * 添加调整明细
     * 
     * @author Libin.Wei
     * @Date 2018年11月23日 上午11:00:23
     * @param adjustForPayable
     * @param loginUser
     */
    void insert(AdjustForPayable adjustForPayable, LoginUser loginUser) throws BusinessException;
}
