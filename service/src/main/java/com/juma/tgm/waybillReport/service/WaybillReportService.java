package com.juma.tgm.waybillReport.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.base.domain.MonthDomain;
import com.juma.tgm.crm.domain.YesterdayIncomeInfo;
import com.juma.tgm.driver.domain.DriverLoginUser;
import com.juma.tgm.waybill.domain.ReportForm;
import com.juma.tgm.waybill.domain.ReportQueryDomain;
import com.juma.tgm.waybillReport.domain.WaybillReport;
import com.juma.tgm.waybillReport.domain.WaybillReportExport;

/**
 * @ClassName WaybillReportService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年2月4日 下午4:49:37
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillReportService {

    /**
     * 
     * @Description 运单报表分页列表
     * @author Libin.Wei
     * @Date 2017年2月4日 下午4:51:14
     * @param pageCondition
     * @param loginUser
     *            当前登录人员信息
     * @return
     */
    @Deprecated
    Page<WaybillReportExport> search(PageCondition pageCondition, List<String> areaCodeList,
            LoginEmployee loginEmployee);

    /**
     * 
     * @Description 根据运单ID获取信息
     * @author Libin.Wei
     * @Date 2017年2月4日 下午5:58:26
     * @param waybillId
     *            运单ID
     * @return
     */
    WaybillReport findOneById(Integer waybillId);

    /**
     * 
     * @Description 运单报表导出
     * @author Libin.Wei
     * @Date 2017年1月18日 上午11:26:59
     * @param pageCondition
     * @param loginUser
     * @return
     */
    @Deprecated
    void asyncExport(PageCondition pageCondition, Integer exportTaskId, LoginEmployee loginEmployee) throws Exception;
    
    /**
     * 
     * @Description 用车端报表
     * @author Libin.Wei
     * @Date 2017年2月6日 下午1:57:00
     * @param pageCondition
     * @param driverLoginUser
     * @return
     */
    ReportForm getDriverReport(PageCondition pageCondition, DriverLoginUser driverLoginUser);

    /**
     * 
     * @Description 获取昨日运单收入信息
     * @author Libin.Wei
     * @Date 2017年2月6日 下午2:29:44
     * @param driverLoginUser
     * @return
     */
    YesterdayIncomeInfo getYestodayIncome(DriverLoginUser driverLoginUser);

    /**
     * 
     * @Title: yesterdayIncome
     * @Description: 获取昨日运单收入信息(对外接口)
     * @param loginUser
     *            登录信息
     * @return YesterdayIncomeInfo
     */
    YesterdayIncomeInfo yesterdayIncome(LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Description 用车端报表
     * @author Libin.Wei
     * @Date 2017年2月6日 上午11:20:34
     * @param pageCondition
     * @param loginUser
     *            当前登录人员信息
     * @return ReportForm
     * @throws BusinessException
     */
    @Deprecated
    ReportForm getCustomerReport(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 获取司机条件列表
     * @author Libin.Wei
     * @Date 2017年2月6日 下午2:12:32
     * @param month
     *            月份信息
     * @param driverId
     *            司机ID
     * @return
     */
    @Deprecated
    ReportQueryDomain getDriverQuery(MonthDomain month, Integer driverId);

    /**
     * 
     * @Description 获取货主条件列表
     * @author Libin.Wei
     * @Date 2017年2月6日 下午3:13:00
     * @param month
     * @param loginEmployee
     * @return
     */
    @Deprecated
    ReportQueryDomain getCustomerQuery(MonthDomain month, LoginEmployee loginEmployee);

    /**
     * 
     * @Description 获取用户收入信息（本月与上月）
     * @author Libin.Wei
     * @Date 2017年2月15日 上午10:18:46
     * @param loginUser
     *            当前登录用的的信息，数据由用户中心提供
     * @return WaybillReport
     */
    @Deprecated
    WaybillReport findDriverIncomeInfo(LoginUser loginUser);

    /**
     * 
     * 结算完成
     * 
     * @author Libin.Wei
     * @Date 2017年11月20日 上午11:53:56
     * @param waybillId
     * @param loginUser
     * @throws BusinessException
     */
    @Deprecated
    void updateToCheckout(int waybillId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Description 获取月份列表
     * @author Libin.Wei
     * @Date 2017年2月6日 下午2:09:10
     * @return
     */
    @Deprecated
    MonthDomain getMonthDomain();
}
