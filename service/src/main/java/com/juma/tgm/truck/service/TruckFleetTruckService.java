package com.juma.tgm.truck.service;

import com.giants.common.tools.Page;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.truck.domain.TruckFleetTruck;
import com.juma.tgm.truck.vo.TruckFleetTruckFilter;
import com.juma.tgm.truck.vo.TruckFleetTruckVo;
import java.util.List;

/**
 * 
 * @ClassName TruckFleetTruckService.java 车队车辆关联信息
 * @author Libin.Wei
 * @Date 2017年5月19日 下午3:15:49
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */
public interface TruckFleetTruckService {

    /**
     * 
     * 分页查询
     * 
     * @author Libin.Wei
     * @Date 2018年1月12日 下午2:46:56
     * @param queryCond
     * @param loginUser
     * @return
     */
    Page<TruckFleetTruckVo> search(QueryCond<TruckFleetTruckFilter> queryCond, LoginUser loginUser);

    /**
     * 
     * 根据车队ID获取车队绑定的货车ID集合
     * 
     * @author Libin.Wei
     * @Date 2017年5月19日 下午2:59:20
     * @param truckFleetId
     *            车队ID
     * @return
     */
    List<TruckFleetTruckVo> listByTruckFleetId(Integer truckFleetId);

    /**
     * 
     * 根据车队ID集合获取车队绑定的货车ID集合：支持分页
     * 
     * @author Libin.Wei
     * @Date 2017年9月11日 上午9:28:11
     * @param truckFleetIds
     * @param pagenNo
     * @param pageSize
     * @return
     */
    Page<TruckFleetTruck> listByTruckFleetIds(List<Integer> truckFleetIds, Integer pagenNo, Integer pageSize);

    /**
     * 
     * 根据车辆ID获取车辆绑定的车队ID集合
     * 
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:00:10
     * @param truckId
     *            车辆ID
     * @return
     */
    List<TruckFleetTruck> listByTruckId(Integer truckId);

    /**
     * 
     * @Title: insert
     * @Description: 添加
     * @param truckFleetId
     * @param listTruckId
     * @return void
     */
    void insert(Integer truckFleetId, List<Integer> listTruckId);

    /**
     * 
     * @Title: delete
     * @Description: 删除
     * @param truckFleetTruckId
     * @return void
     */
    void delete(Integer truckFleetTruckId);

    /**
     *
     * @Title: deleteByTruckId
     * @Description: 根据货车ID删除
     * @param truckId
     *            货车ID
     * @return void
     */
    @Deprecated
    void deleteByTruckId(Integer truckId);

    /**
     * 
     *
     * 根据车队ID删除
     *
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:05:53
     * @param truckFleetId
     */
    void deleteByTruckFleetId(Integer truckFleetId);

    /**
     *
     * 根据车队ID和车辆ID集合进行修改更新
     *
     * @author Libin.Wei
     * @Date 2017年5月19日 下午3:05:53
     * @param truckFleetId
     * @param listTruckId
     */
    void changeTruckFleetTrucks(Integer truckFleetId, List<Integer> listTruckId);
}
