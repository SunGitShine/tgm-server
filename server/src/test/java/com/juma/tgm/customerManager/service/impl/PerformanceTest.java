package com.juma.tgm.customerManager.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.util.CustomerManagerPerformanceUtil;
import com.juma.tgm.waybill.domain.vo.CustomerManagerDebtDetailVo;
import com.juma.tgm.waybill.domain.vo.CustomerManagerDebtOverviewVo;
import com.juma.tgm.waybill.domain.vo.OverallAchieveMent;

import testng.BaseTestNGTest;

/**
 * @ClassName: PerformanceTest
 * @Description:
 * @author: liang
 * @date: 2017-08-23 09:25
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class PerformanceTest extends BaseTestNGTest {

    /*@Resource
    private CustomerManagerPerformanceUtil customerManagerPerformanceUtil;

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");


    //总欠款
    @Test
    public void getTotalDebt() {
        LoginEmployee employee = new LoginEmployee();
        employee.setEmployeeId(87);
        CustomerManagerDebtVo total = customerManagerPerformanceUtil.getDebtTotal(employee);

        Assert.assertNotNull(total, "欠款总额不能为空");
        System.out.println(JSONObject.toJSON(total));
    }
    //本周回款

    @Test
    public void getWeekGathering() {
        BigDecimal weekGathering = customerManagerPerformanceUtil.getCurrentWeekGathering(87);

        Assert.assertNotNull(weekGathering, "本周回款不能为空");
        System.out.println("回款金额：" + decimalFormat.format(weekGathering));
    }

    //超期欠款

    @Test
    public void getOverdueDebt() {
        BigDecimal overdueDebt = customerManagerPerformanceUtil.getOverdueDebt(87);
        Assert.assertNotNull(overdueDebt, "本周回款不能为空");
        System.out.println("回款金额：" + decimalFormat.format(overdueDebt));
    }

    //分段欠款
    @Test
    public void getSeparateTimeDebt() {
        List<CustomerManagerDebtOverviewVo> debts = customerManagerPerformanceUtil.getSeparationDebt(87);

        Assert.assertNotNull(debts, "分段不能为空");
        Assert.assertEquals(debts.size() < 4, true, "分段不应小于3");

        System.out.println("分段数据:" + JSONObject.toJSONString(debts));
    }


    //概览数据
    @Test
    public void getDebtOverviewVo(){
        String timeRange = "1-20";
        PageCondition pageCondition = new PageCondition();
        pageCondition.put("timeRange", timeRange);
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(10);
        CustomerManagerDebtOverviewVo data = customerManagerPerformanceUtil.getDebtOverviewVo(pageCondition, 87);

        Assert.assertNotNull(data, "概览数据不能为空");
        System.out.println("概览数据:" + JSONObject.toJSONString(data));
    }

    //欠款明细
    @Test
    public void getDebtDetail(){
        int customerId = 141;
        int employeeId = 87;
        String timeRange = "1-20";
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(3);
        pageCondition.setPageSize(3);
        pageCondition.put("timeRange", timeRange);
        pageCondition.put("customerId", customerId);
        CustomerManagerDebtDetailVo detail  = customerManagerPerformanceUtil.getDebtDetail(pageCondition, employeeId);
        Assert.assertNotNull(detail, "欠款明细数据不能为空");

        System.out.println("欠款明细数据:" + JSONObject.toJSONString(detail));

    }

    @Test
    public void getRestData(){
        OverallAchieveMent data = customerManagerPerformanceUtil.getCustomerPerformance(6);
        Assert.assertNotNull(data, "rest接口数据不能为空");

        System.out.println("rest接口数据:" + JSONObject.toJSONString(data));
    }*/

}
