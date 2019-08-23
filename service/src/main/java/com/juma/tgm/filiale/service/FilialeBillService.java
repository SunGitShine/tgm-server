package com.juma.tgm.filiale.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.vo.ProjectBillVo;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 分公司运单业务逻辑接口
 * @ClassName: FilialeBillService
 * @Description:
 * @author: liang
 * @date: 2017-09-27 10:11
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface FilialeBillService {


    /**
     * 项目下单
     * @param projectBillVo
     * @param loginUser
     */
    List<Integer> createProjectBill(ProjectBillVo projectBillVo, LoginUser loginUser) throws BusinessException;

    /**
     * 获取项目运单详情
     * @param waybillId
     * @return
     * @throws BusinessException
     */
    WaybillDetailInfo getProjectBillDetail(int waybillId , LoginUser loginUser) throws BusinessException;


    /**
     * 修改项目运单税前费用
     * @param waybill
     * @param loginEmployee
     * @throws BusinessException
     */
    void modifyPreTaxFreight(Waybill waybill, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 项目运单价格确认
     * @param waybillId
     * @param loginEmployee
     * @throws BusinessException
     */
    void changeToComplete(Integer waybillId, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 提供前端验证用车要求
     * @param projectBillVo
     * @throws BusinessException
     */
//    void truckRequireCheckForUser(ProjectBillVo projectBillVo) throws BusinessException;

    /**
     * 检查所选车型是否有运费规则
     * @param truckType
     * @throws BusinessException
     */
//    void checkTruckTypeHasFreightRule(int truckType, int projectId) throws BusinessException;


    /**
     * 修改运单税率
     * @param waybillId
     * @param taxRateValue
     * @param loginEmployee
     * @throws BusinessException
     */
    void modifyWaybillTaxRate(int waybillId, BigDecimal taxRateValue, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 固定需求自动建单任务
     * @throws BusinessException
     */
//    void autoCreateBillTask() throws BusinessException;

    /**
     * 指定固定需求建单
     * @param fixedDemandIds
     * @throws BusinessException
     */
//    void createBillByFixedDemandId(List<Integer> fixedDemandIds) throws BusinessException;
}
