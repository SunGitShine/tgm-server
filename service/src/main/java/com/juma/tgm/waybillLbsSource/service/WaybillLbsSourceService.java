package com.juma.tgm.waybillLbsSource.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSource;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSourceQuery;

/**
 * @ClassName WaybillLbsSourceService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月19日 下午5:34:01
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillLbsSourceService {

    /**
     * 
     * 分页查询
     * 
     * @author Libin.Wei
     * @Date 2017年6月19日 下午5:44:46
     * @param pageCondition
     * @return
     */
    Page<WaybillLbsSourceQuery> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年6月19日 下午5:46:07
     * @param waybillLbsSourceId
     * @return
     */
    WaybillLbsSource getWaybillLbsSource(Integer waybillLbsSourceId);

    /**
     * 
     * @Description 根据条件导出
     * @author Libin.Wei
     * @Date 2017年2月21日 下午4:31:13
     * @param loginEmployee
     * @param pageCondition
     * @return
     */
    void asyncExport(PageCondition pageCondition, Integer exportTaskId, LoginUser loginUser) throws Exception;

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年6月22日 上午10:27:44
     * @param waybillLbsSource
     * @throws Exception
     */
    void insert(WaybillLbsSource waybillLbsSource) throws BusinessException;

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年6月22日 上午10:27:44
     * @param waybillLbsSource
     * @throws Exception
     */
    void update(WaybillLbsSource waybillLbsSource) throws BusinessException;

    /**
     * 
     * 根据运单号与类型获取
     * @author Libin.Wei
     * @Date 2017年6月22日 上午10:40:18
     * @param waybillId
     * @param sign
     * @return
     */
    WaybillLbsSource findSourceByWaybillIdAndSign(Integer waybillId, Integer sign);
}
