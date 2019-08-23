package com.juma.tgm.crm.service;

import com.juma.tgm.crm.domain.IncomeStatistics;

/**
 * @ClassName IncomeStatistics.java
 * @Description 收入统计service
 * @author Libin.Wei
 * @Date 2016年12月26日 下午5:59:16
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface IncomeStatisticsService {

    /**
     * 
     * @Description 定时任务，写入统计数据
     * @param 统计司机近几天的收入，负数
     * @author Libin.Wei
     * @Date 2016年12月26日 下午6:00:51
     */
    void insert(int day);

    /**
     * 
     * @Description 根据司机ID获取
     * @author Libin.Wei
     * @Date 2016年12月26日 下午6:02:14
     * @param driverId
     *            司机ID
     * @return IncomeStatistics
     */
    IncomeStatistics findByDriverId(Integer driverId);
}
