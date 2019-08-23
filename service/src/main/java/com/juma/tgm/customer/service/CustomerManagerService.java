package com.juma.tgm.customer.service;

import java.util.List;
import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.customer.domain.TruckCustomerBo;
import com.juma.tgm.customerManager.domain.vo.TruckFleetParamVo;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.domain.bo.LogisticsProductBo;
import com.juma.tgm.truck.domain.vo.TruckFleetQueryVo;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.truck.domain.Truck;

/**
 * @author Libin.Wei
 * @version 1.0.0
 * @ClassName CustomerManagerService.java
 * @Description 客户经理service
 * @Date 2017年5月24日 上午11:45:07
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface CustomerManagerService {

    /**
     * 根据客户经理账号ID查询客户经理车队信息
     *
     * @param waybillId
     * @param pageIndex
     * @param pageSize
     * @param loginUser
     * @return
     * @author Libin.Wei
     * @Date 2017年5月24日 上午11:28:30
     */
    Page<DriverTruckInfoBo> findTruckFleetBy(Integer waybillId, Integer pageIndex, Integer pageSize,
                                             LoginUser loginUser);

    /**
     * 经纪人端用户中心接口
     *
     * @param loginEmployee
     * @return
     */
    TruckCustomerBo getCustomerManagerForClientUserCenter(LoginEmployee loginEmployee);

    /**
     * 获取分公司项目车队信息
     *
     * @param loginUser
     * @param projectId
     * @return
     * @throws BusinessException
     */
    Page<DriverTruckInfoBo> findTruckFleetForFilialeProject(Integer projectId, Integer pageNo, Integer pageSize,
                                                            LoginUser loginUser) throws BusinessException;

    /**
     * 通过车辆id获取司机车辆信息
     *
     * @param truckId
     * @return
     * @throws BusinessException
     */
    @Deprecated
    DriverTruckInfoBo getDriverTruckInfo(int truckId, LoginUser loginUser) throws BusinessException;


    /**
     * for获取固定需求；经纪人的车队信息
     *
     * @param truckFleetParamVo 需要过滤的车型
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    List<DriverTruckInfoBo> findTruckFleetForFixedDemand(TruckFleetParamVo truckFleetParamVo, LoginUser loginUser)
        throws BusinessException;


    /**
     * 搜索车队信息
     * @param queryCondition
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Page<DriverTruckInfoBo> searchTruckFleet(PageQueryCondition<TruckFleetQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException;


    /**
     * 组装数据
     * @param truck
     * @param driver
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    DriverTruckInfoBo buildDriverTruckInfo(Truck truck, Driver driver, CapacityPool capacityPoo, LoginUser loginUser) throws BusinessException;

    /**
     * 通过客户id查询物流产品标签
     * @param customerId tms客户id
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    List<LogisticsProductBo> findLogisticsProduct(Integer customerId, LoginUser loginUser) throws BusinessException;

}
