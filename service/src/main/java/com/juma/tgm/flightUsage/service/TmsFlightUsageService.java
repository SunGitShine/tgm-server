package com.juma.tgm.flightUsage.service;

import java.util.Date;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.flight.domain.bo.FlightUsageQuery;
import com.juma.tgm.flight.domain.bo.TransportCapacityBo;

/**
 * @ClassName FlightUsageService.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月9日 下午2:54:53
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
public interface TmsFlightUsageService {

    /**
     *
     * 根据条件获取班次ID
     *
     * @author Libin.Wei
     * @Date 2017年6月9日 下午3:20:34
     * @param startTime
     *            必填
     * @param spendTime
     *            耗时，必填
     * @param amsDriverId
     *            必填
     * @return flightId
     */
    Integer findFlightIdBy(Date startTime, Integer spendTime, Integer amsDriverId, LoginUser loginUser)
            throws BusinessException;

    /**
     *
     * 根据条件获取班次ID
     *
     * @author Libin.Wei
     * @Date 2017年6月9日 下午3:20:34
     * @param startTime
     *            必填
     * @param endTime
     *            必填
     * @param amsDriverId
     *            必填
     * @return flightId
     */
    Integer findFlightIdBy(Date startTime, Date endTime, Integer amsDriverId, LoginUser loginUser)
            throws BusinessException;

    /**
     *
     * 根据条件获取占用班次ID
     *
     * @author Libin.Wei
     * @Date 2017年6月9日 下午3:20:34
     * @param startTime
     *            必填
     * @param spendTime
     *            耗时，必填
     * @param amsDriverId
     *            必填
     * @param loginUser
     *            必填
     * @return flightUsageId
     */
    Integer findFlightUsageIdBy(Date startTime, Integer spendTime, Integer amsDriverId, String remark,
            LoginUser loginUser) throws BusinessException;

    /**
     *
     * 根据条件获取占用班次ID
     *
     * @author Libin.Wei
     * @Date 2017年6月9日 下午3:20:34
     * @param startTime
     *            必填
     * @param spendTime
     *            耗时，必填
     * @param amsDriverId
     *            必填
     * @param loginUser
     *            必填
     * @return flightUsageId
     */
    Integer findFlightUsageIdBy(Integer flightId, Date startTime, Integer spendTime, String remark,
            LoginUser loginUser);

    /**
     *
     * 根据条件获取占用班次ID
     *
     * @author Libin.Wei
     * @Date 2017年6月9日 下午3:20:34
     * @param startTime
     *            必填
     * @param endTime
     *            必填
     * @param amsDriverId
     *            必填
     * @param loginUser
     *            必填
     * @return flightUsageId
     */
    Integer findFlightUsageIdBy(Integer flightId, Date startTime, Date endTime, String remark, LoginUser loginUser);

    /**
     * 统计空闲运力数
     *
     * @param transportCapacityBo
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    TransportCapacityBo countFreeTransportCapacity(TransportCapacityBo transportCapacityBo, LoginEmployee loginEmployee)
            throws BusinessException;

    /**
     * 取消班次占用
     *
     * @param flightUsageId
     * @param remark
     * @param userId
     * @throws BusinessException
     */
    void cancelFlightUsage(int flightUsageId, String remark, int userId) throws BusinessException;

    /**
     *
     * 全部班次分页列表
     *
     * @author Libin.Wei
     * @Date 2017年11月30日 下午9:10:23
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<FlightUsageQuery> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     *
     * 可用班次分页列表：专车、分公司
     *
     * @author Libin.Wei
     * @Date 2017年11月30日 下午9:10:23
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<FlightUsageQuery> availableFlightSearch(PageCondition pageCondition, LoginUser loginUser);

    /**
     *
     * 可用班次分页列表：落地配
     *
     * @author Libin.Wei
     * @Date 2017年11月30日 下午9:10:23
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<FlightUsageQuery> langdingAvailableFlightSearch(PageCondition pageCondition, LoginUser loginUser);

    /**
     *
     * 非希地：所有绑定司机的车辆，并给出车辆不匹配的原因（班次、载重）
     *
     * @author Libin.Wei
     * @Date 2018年7月30日 下午2:14:10
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<FlightUsageQuery> allVehicleHasBindDriverSearch(PageCondition pageCondition, LoginUser loginUser);

    /**
     *
     * 希地:所有绑定司机的车辆，并给出车辆不匹配的原因（班次、载重）
     *
     * @author Libin.Wei
     * @Date 2018年7月30日 下午2:14:10
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<FlightUsageQuery> landingAllVehicleHasBindDriverSearch(PageCondition pageCondition, LoginUser loginUser);
}
