package com.juma.tgm.waybill.service;

import java.util.List;

import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillMap;
import com.juma.tgm.waybill.domain.WaybillNav;
import com.juma.tgm.waybill.domain.map.FindTruckMapView;
import com.juma.tgm.waybill.domain.map.FindTruckMapViewCond;

public interface WaybillQueryService {

    /**
    *
    * @Title: findRunningWaybillByTruck 
    * @Description: 在途监控 车辆任务列表 --对外接口
    * @param: @param plateNumber 
    *  @return: List<WaybillMap> 
    *  @throws
    */
   List<WaybillMap> findRunningWaybillByTruck(String plateNumber);

   /**
    * 
    * 在途监控 车辆任务列表  --对外接口
    * @author Libin.Wei
    * @Date 2018年4月16日 下午8:58:41
    * @param plateNumber
    * @param tenantId
    * @return
    */
   List<WaybillMap> findRunningWaybillByTruck(String plateNumber, Integer tenantId);

   /**
    * @throws @Title: findWaybillMapById
    * @Description: 在途监控 回放
    * @param: @param waybillId
    * @param: @return
    * @return: WaybillMap
    */
   WaybillMap findWaybillMapById(Integer waybillId);

   /**
    *
    * @Title: findLastWaybillByPlateNumber 
    * @Description: 在途监控 根据车牌查最新配送中订单 
    * @param plateNumber 
    * @return: WaybillNav @throws
    */
   WaybillNav findLastRunningWaybillByTruck(String plateNumber);

   /**
    *
    * @Title: findTruckMapView 
    * @Description: 在途监控 切换地图 
    * @param cond 
    * @return: FindTruckMapView 
    * @throws
    */
   FindTruckMapView findTruckMapView(FindTruckMapViewCond cond, LoginEmployee loginEmployee);

   /**
    *
    * @Title: assignTask 
    * @Description: 在途监控 指派 
    * @param waybillId 运单Id 
    * @param truckId 车辆Id 
    * @param driverId 司机Id 
    * @param remark 备注 userId 操作人 @return: void @throws
    */
   void assignTask(int waybillId,int flightId, int truckId, int driverId, String remark, int userId);
   
   /**
    * 
    * @Title: findWaybillByIds   
    * @Description: 
    * @param: @param waybillIds
    * @param: @return      
    * @return: List<Waybill>      
    * @throws
    */
   List<Waybill> findWaybillByIds(List<Integer> waybillIds);
   
}
