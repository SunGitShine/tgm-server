package com.juma.tgm.truck.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.capacity.domian.vo.CapacityFilter;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.truck.domain.TruckFleet;
import com.juma.tgm.truck.domain.vo.TruckDriverVo;
import com.juma.tgm.truck.domain.vo.TruckFleetDriverRelationVo;
import com.juma.tgm.truck.domain.vo.TruckFleetQueryVo;

import java.util.List;

/**
 * 
 * @Description: 车队管理接口
 * @author Administrator
 * @date 2016年5月12日 下午4:26:39
 * @version V1.0
 * Created by Administrator on 2016/5/12 0012.
 * 车队service
 */
public interface TruckFleetService {
    
    /**
     * 
     * @Title: searchDetails
     * @Description: 分页查询获取车队信息
     * @param pageCondition
     * @return Page<TruckFleetQuery>
     * @throws
     */
    Page<TruckFleet> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据车队ID获取
     * @author Libin.Wei
     * @Date 2017年5月19日 下午1:54:09
     * @param truckFleetId 队ID
     * @return
     */
    TruckFleet getTruckFleet(Integer truckFleetId);

    /**
     * 
     * 根据客户经理账号ID查询车队
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:35:09
     * @param userId
     * @return List<TruckFleet>
     */
    List<TruckFleet> listTruckFleetByUserId(LoginUser loginUser);

    /**
     * 
     * @Description 批量查询
     * @author Libin.Wei
     * @Date 2017年3月9日 下午4:17:08
     * @param truckFleetIdList 车队ID集合
     * @return List<TruckFleet>
     */
    List<TruckFleet> listTruckFleetByListTruckFleetId(List<Integer> truckFleetIdList);

    /**
     * 
     * @Title: insert
     * @Description: 创建车队
     * @param truckFleetBo   
     * @param loginEmployee
     * @return void    
     * @throws
     */
    void insert(TruckFleet truckFleet, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Title: update
     * @Description: 编辑车队
     * @param truckFleetBo   
     * @param loginEmployee
     * @return void    
     * @throws
     */
    void update(TruckFleet truckFleet, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 启用车队
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:44:04
     * @param truckFleetId
     * @param loginUserId
     */
    void updateToEnable(Integer truckFleetId, LoginUser loginUser);

    /**
     * 
     * 禁用车队
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:44:14
     * @param truckFleetId
     * @param loginUserId
     */
    void updateToDisable(Integer truckFleetId, LoginUser loginUser);

    /**
     * 
     * 根据客户经理账号ID解除客户经理与车队的关联
     * 
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:37:09
     * @param userId
     *            客户经理账号ID
     * @param loginUserId
     *            当前登录人
     */
    void updateToUnbundlingFleetAndUser(Integer userId, LoginUser loginUser);

    /**
     * 统计经济人下车队数量
     * @param loginEmployee
     * @return
     */
    int countTruckFleet(LoginEmployee loginEmployee);

    /**
     * 
     * 根据客户经理ID获取
     * @author Libin.Wei
     * @Date 2018年3月27日 下午5:34:20
     * @param employeeId
     * @param tenantId
     * @return
     */
    TruckFleet findTruckFleetByEmployeeId(Integer employeeId, Integer tenantId);


    /**
     *  app端车队搜索
     *  @param queryCondition 车队名称、司机名称、车牌号
     * @return
     * @throws BusinessException
     */
    Page<TruckFleetDriverRelationVo> searchTruckFleet(PageQueryCondition<TruckFleetQueryVo> queryCondition, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 车队可选车辆列表
     *
     * @param queryCond
     * @param loginUser
     * @return
     */
    Page<TruckDriverVo> availableTruckSearch(QueryCond<CapacityFilter> queryCond, LoginUser loginUser);
}
