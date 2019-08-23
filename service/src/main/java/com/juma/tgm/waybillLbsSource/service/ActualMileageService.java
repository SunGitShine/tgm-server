package com.juma.tgm.waybillLbsSource.service;

import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybillLbsSource.domain.ActualMileageQuery;

/**
 * @ClassName ActualMileageService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月22日 上午9:20:30
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ActualMileageService {

    /**
     * 
     * 分页
     * @author Libin.Wei
     * @Date 2017年6月22日 上午9:24:07
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    Page<ActualMileageQuery> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * @Description 根据条件导出货车列表
     * @author Libin.Wei
     * @Date 2017年2月21日 下午4:31:13
     * @param loginEmployee
     * @param pageCondition
     * @return
     */
    void asyncExport(PageCondition pageCondition, Integer exportTaskId, LoginUser loginUser) throws Exception;
}
