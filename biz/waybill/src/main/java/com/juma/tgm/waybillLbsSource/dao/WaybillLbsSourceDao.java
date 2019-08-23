package com.juma.tgm.waybillLbsSource.dao;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.driver.domain.ReportInfoDetails;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSource;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSourceQuery;

/**
 * @ClassName WaybillLbsSourceDao.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月19日 下午5:28:53
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillLbsSourceDao extends MybatisDao<WaybillLbsSource> {

    /**
     * 
     * 分页查询
     * 
     * @author Libin.Wei
     * @Date 2017年6月19日 下午6:17:09
     * @param pageCondition
     * @return
     */
    List<WaybillLbsSourceQuery> searchDetail(PageCondition pageCondition);

}
