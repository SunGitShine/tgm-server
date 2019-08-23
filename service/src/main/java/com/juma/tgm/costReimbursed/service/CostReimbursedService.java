package com.juma.tgm.costReimbursed.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.costReimbursed.domain.CostReimbursed;


/**
 * @ClassName CostReimbursedService.java
 * @Description 费用报销
 * @author Libin.Wei
 * @Date 2017年7月10日 下午2:32:25
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface CostReimbursedService {

    /**
     * 
     * 分页获取
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:04:35
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<CostReimbursed> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:05:17
     * @param costReimbursedId
     * @return
     */
    CostReimbursed getCostReimbursed(Integer costReimbursedId);

    /**
     * 
     * 根据ID获取, 包含图片信息
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:05:17
     * @param costReimbursedId
     * @return
     */
    CostReimbursed findCostReimbursedAndUrlById(Integer costReimbursedId);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:07:21
     * @param costReimbursed
     * @param loginUser
     * @throws BusinessException
     */
    void insert(CostReimbursed costReimbursed, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 批量添加
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:07:21
     * @param costReimbursed
     * @param loginUser
     * @throws BusinessException
     */
    void insertBatch(List<CostReimbursed> listCostReimbursed, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:07:45
     * @param costReimbursed
     * @param loginUser
     * @throws BusinessException
     */
    void update(CostReimbursed costReimbursed, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据KEY、报销类型、运单ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:12:47
     * @param costReimbursedKey
     *            不能为空，为空返回空集合对象
     * @param costReimbursedType 可以为空
     * @param waybillId 可以为空
     * @return List<CostReimbursed>
     */
    List<CostReimbursed> listByKeyAndTypeAndWaybillId(CostReimbursed.CostReimbursedKey costReimbursedKey,
            Integer costReimbursedType, Integer waybillId, LoginUser loginUser);

    /**
     * 
     * 根据KEY、司机ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年7月10日 下午3:13:53
     * @param costReimbursedKey 不能为空，为空返回空集合对象
     * @param costReimbursedType
     * @param driverId
     * @return List<CostReimbursed>
     */
    List<CostReimbursed> listByKeyAndDriverId(CostReimbursed.CostReimbursedKey costReimbursedKey,
            Integer costReimbursedType, Integer driverId, LoginUser loginUser);

    /**
     * 根据运单号获取报销对象
     * @param costReimbursedNo
     * @return
     */
    CostReimbursed getByCostReimbursedNO(String costReimbursedNo);

    /**
     * 费用报销导出列表
     * @param pageCondition
     * @param exportTaskId
     * @param loginUser
     * @throws Exception
     */
    void asyncCostReimbursedExport(PageCondition pageCondition, Integer exportTaskId, LoginUser loginUser) throws Exception;

    /**
     * 重新计算价格
     * @param waybillId
     * @param loginUser
     * @throws BusinessException
     */
    void recountThePrice(Integer waybillId, LoginUser loginUser) throws BusinessException;
}
