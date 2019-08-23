package com.juma.tgm.reportInfo.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.driver.domain.ReportInfoDetails;


/**
 * @ClassName ReportInfoDetailService.java
 * @Description 路况报备详情
 * @author Libin.Wei
 * @Date 2017年4月27日 下午5:06:42
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ReportInfoDetailService {

    /**
     * 
     * 根据路况报备ID查询的报备详情列表
     * 
     * @author YouShuanglin
     * @Date 2017年5月4日 下午1:41:08
     * @param reportId
     * @return List<ReportInfoDetails>
     */
    List<ReportInfoDetails> ListByReportId(Integer reportId);

    /**
     * 
     * 根据路况报备详情ID获查询该报备点详情
     * 
     * @author YouShuanglin
     * @Date 2017年5月4日 下午1:42:33
     * @param detailId
     *            路况报备详情ID
     * @return
     */
    ReportInfoDetails getReportInfoDetail(Integer detailId);

    /**
     * 
     * 根据路况报备ID查询的报备次数
     * 
     * @author YouShuanglin
     * @Date 2017年5月4日 下午1:39:14
     * @param reportId
     *            路况报备ID
     * @return Integer
     */
    Integer findCountByReportId(Integer reportId);

    /**
     * 
     * 添加路况报备详细信息
     * 
     * @author Libin.Wei
     * @Date 2017年5月2日 上午11:07:44
     * @param ReportInfoDetail
     *            路况报备详细信息
     * @param loginUser
     *            当前登录人，数据结构来自用户中心
     * @throws BusinessException
     */
    void insert(ReportInfoDetails reportInfoDetail, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据司机ID与运单ID获取报备详情列表
     * 
     * @author Libin.Wei
     * @Date 2017年5月2日 下午5:13:02
     * @param driverId
     *            司机ID
     * @param waybillId
     *            运单ID
     * @return List<ReportInfoDetails>
     */
    List<ReportInfoDetails> listByDriverIdAndWaybillId(Integer driverId, Integer waybillId, LoginUser loginUser);
    /**
     *
     * 根据运单号查找相关联的全部报备点
     *
     * @author Libin.Wei
     * @Date 2017年5月2日 下午5:13:02
     * @param waybillId
     *           运单id
     * @return List<ReportInfoDetails>
     */
    List<ReportInfoDetails> listByWaybillId(Integer waybillId, String orderDesc);
}
