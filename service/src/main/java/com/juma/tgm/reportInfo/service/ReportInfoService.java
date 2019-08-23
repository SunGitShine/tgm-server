package com.juma.tgm.reportInfo.service;

import java.util.List;
import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.tgm.driver.domain.ReportInfo;
import com.juma.tgm.driver.domain.ReportInfoBo;
import com.juma.tgm.driver.domain.ReportInfoDetails;
import com.juma.tgm.driver.domain.ReportInfoParam;


/**
 * @ClassName ReportInfoService.java
 * @Description 路况报备
 * @author Libin.Wei
 * @Date 2017年4月27日 下午5:06:30
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ReportInfoService {

    /**
     * 
     * 查询路况报备列表(分页列表)
     * 
     * @author YouShuanglin
     * @Date 2017年5月5日 下午1:04:38
     * @param pageCondition
     * @return
     */
    Page<ReportInfoBo> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据路况报备ID查询路况报备信息
     * 
     * @author Libin.Wei
     * @Date 2017年5月5日 下午1:06:38
     * @param reportInfoId
     *            路况报备ID
     * @return ReportInfo
     */
    ReportInfo getReportInfo(Integer reportInfoId);

    /**
     * 
     * 添加路况报备
     * 
     * @author Libin.Wei
     * @Date 2017年5月2日 上午11:04:52
     * @param reportInfo
     *            路况报备信息
     * @param loginEcoUser
     *            当前登录人，数据结构来自用户中心
     * @return reportInfoId
     */
    Integer insert(ReportInfo reportInfo, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 添加路况报备
     * 
     * @author Libin.Wei
     * @Date 2017年5月2日 上午11:04:52
     * @param ReportInfoParam
     *            报备信息
     * @param loginEcoUser
     *            当前登录人，数据结构来自用户中心
     * @return reportInfoId
     */
    void insertPageAndDetail(ReportInfoParam reportInfoParam, LoginUser loginUser) throws BusinessException;

    /** 
     * 
     * 根据司机ID与运单ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年5月2日 上午11:42:21
     * @param driverId
     *            司机ID
     * @param waybillId
     *            运单ID
     * @return ReportInfo
     */
    ReportInfo findByDriverIdAndWaybillId(Integer reportInfoType, Integer driverId, Integer waybillId, LoginUser loginUser);
    
    /**
     * 
     * @Title:       findByWaybillId   
     * @Description: 查询运单报备
     * @return:      ReportInfo      
     * @throws
     */
    List<ReportInfo> findByWaybillId(Integer waybillId, LoginUser loginUser);

    /**
     * 
     * 报备类型列表
     * @author Libin.Wei
     * @Date 2017年6月26日 下午5:33:23
     * @return
     */
    List<ConfParamOption> listRepotInfoType();

    /**
     * 
     * 根据运单ID获取所有的报备信息
     * @author Libin.Wei
     * @Date 2017年7月12日 下午3:38:10
     * @param waybillId
     * @return
     */
    List<ReportInfoDetails> listAllReportInfoByWaybillId(Integer waybillId, LoginUser loginUser);

    /**
     * 经纪人首页统计数据
     * @param date
     * @param loginEmployee
     * @return
     */
    List<Object> waybillStatistics(String date, Map<String, Object> freightMap, Map<String, Object> incomeMap,
        Map<String, Object> customerMap,LoginEmployee loginEmployee);
}
