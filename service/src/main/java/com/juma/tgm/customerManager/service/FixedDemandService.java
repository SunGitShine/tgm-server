package com.juma.tgm.customerManager.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.service.vo.FixedDemandVo;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;

import java.util.List;

/**
 * @ClassName: FixedDemandService
 * @Description:
 * @author: liang
 * @date: 2017-07-24 19:20
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface FixedDemandService {


    /**
     * 通过客户经理获取固定需求列表
     * @param loginEmployee
     * @param pageCondition
     * @return
     */
    Page<FixedDemandVo> getFixedDemandList(LoginEmployee loginEmployee , PageCondition pageCondition) throws BusinessException;


    /**
     * 新增固定需求
     *
     * @param fixedDemandVo
     * @throws BusinessException
     */
    Integer add(FixedDemandVo fixedDemandVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过固定需求id删除固定需求及其关联表
     *
     * @param fixedDemandId
     * @throws BusinessException
     */
    void del(int fixedDemandId) throws BusinessException;

    /**
     * 编辑
     *
     * @param fixedDemandVo
     * @param loginEmployee
     * @throws BusinessException
     */
    void update(FixedDemandVo fixedDemandVo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 通过固定需求id获取详情
     *
     * @param fixedDemandId
     * @return
     * @throws BusinessException
     */
    FixedDemandVo get(int fixedDemandId) throws BusinessException;


    /***************************批量操作*******************************/

    /**
     * 批量添加司机结算价
     * @param fixedDemands
     * @throws BusinessException
     */
    void updateBatch(List<FixedDemand> fixedDemands) throws BusinessException;


    /**
     * 新增司机结算价字段，维护历史数据
     * @throws BusinessException
     */
    public void add4DriverFreightBatch() throws BusinessException;

    /**
     * 
     * 获取固定需求的车辆信息
     * 
     * @author Libin.Wei
     * @Date 2018年3月28日 下午8:21:05
     * @param fixedDemandId
     * @return
     */
    List<DriverTruckInfoBo> listFixedDemandTruck(Integer fixedDemandId, LoginUser loginUser);

    /**
     * 
     * 组装发单数据
     * 
     * @author Libin.Wei
     * @Date 2018年3月28日 下午10:40:21
     * @param fixedDemandId
     * @return
     */
    WaybillDetailInfo doBuildWaybillInfo(Integer fixedDemandId);

    /**
     * 
     * 固定需求数量
     * 
     * @author Libin.Wei
     * @Date 2018年4月3日 下午4:49:50
     * @param projectId
     * @param loginUser
     * @return
     */
    int searchCount(Integer projectId, LoginUser loginUser);


    /***
     * 按客户id 查询 固定需求
     *
     *@param customerId
     *
     * */
    List<FixedDemand> find( Integer customerId ) ;


    /**
     * 获取所有数据
     * @return
     */
    List<FixedDemand> loadAll();
}
