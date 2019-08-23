package com.juma.tgm.crm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giants.common.tools.PageCondition;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.dao.IncomeStatisticsDao;
import com.juma.tgm.crm.domain.IncomeStatistics;
import com.juma.tgm.crm.service.IncomeStatisticsService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;

/**
 * @ClassName IncomeStatisticsServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2016年12月26日 下午6:03:11
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class IncomeStatisticsServiceImpl implements IncomeStatisticsService {

    @Resource
    private IncomeStatisticsDao incomeStatisticsDao;
    @Resource
    private WaybillCommonService waybillCommonService;

    @Override
    public void insert(int day) {
        if (day > 0) {
            return;
        }

        List<Waybill> list = listDriverIncome(day);
        if (list.isEmpty()) {
            return;
        }

        // 批量添加
        List<IncomeStatistics> result = new ArrayList<IncomeStatistics>();
        for (int i = 0, n = list.size(); i < n; i++) {
            Waybill waybill = list.get(i);
            IncomeStatistics income = new IncomeStatistics();
            income.setDriverId(waybill.getDriverId());
            income.setStatisticsCount(waybill.getShow4DriverFreight());
            income.setRankPercentage(new BigDecimal(n - i - 1).divide(new BigDecimal(n), 4, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100)));
            income.setOrderNo(i + 1);
            result.add(income);
        }
        incomeStatisticsDao.delete(null);
        incomeStatisticsDao.insertAll(result);
    }

    // 获取司机近N天收入
    private List<Waybill> listDriverIncome(int day) {
        PageCondition pageCondition = new PageCondition();
        pageCondition.getFilters().put("startTime", DateUtil.dayAddStart(day));
        pageCondition.getFilters().put("endTime", DateUtil.dayAddStart(0));
        List<Integer> statusList = new ArrayList<Integer>();
        statusList.add(Waybill.Status.DELIVERIED.getCode());
        statusList.add(Waybill.Status.PAIED.getCode());
        pageCondition.getFilters().put("statusList", statusList);
        List<Waybill> wbList = waybillCommonService.listFreightGroupByDriverId(pageCondition);

        if (wbList.isEmpty()) {
            return new ArrayList<Waybill>();
        }

        // 根据司机价排序
        Collections.sort(wbList, new Comparator<Waybill>() {
            @Override
            public int compare(Waybill o1, Waybill o2) {
                return o2.getShow4DriverFreight().compareTo(o1.getShow4DriverFreight());
            }
        });

        return wbList;
    }

    @Override
    public IncomeStatistics findByDriverId(Integer driverId) {
        if (null == driverId) {
            return null;
        }
        return incomeStatisticsDao.get(driverId);
    }

}
