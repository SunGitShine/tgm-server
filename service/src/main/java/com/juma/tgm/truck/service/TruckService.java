package com.juma.tgm.truck.service;

import java.util.Date;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain.Driver;
import com.juma.tgm.truck.domain.MuilEditTruck;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.TruckInfo;
import com.juma.tgm.truck.domain.TruckVehicle;

/**
 * 
 * @Description: 车辆接口
 * @author Administrator
 * @date 2016年5月12日 下午4:38:10
 * @version V1.0
 */

@Deprecated
public interface TruckService {

    /**
     * 
     * @Description 根据AMS系统中的货车ID获取，只返回TMS系统的车辆信息
     * @author Libin.Wei
     * @Date 2017年3月9日 下午6:18:13
     * @param vehicleId
     *            VM系统中的货车ID
     * @return Truck
     */
    Truck findTruckByVehicleId(Integer vehicleId);

    /**
     * 
     * @Description 根据AMS系统中的货车ID获取，只返回TMS系统的车辆信息,包含已删除的信息
     * @author Libin.Wei
     * @Date 2017年3月9日 下午6:18:13
     * @param vehicleId
     *            VM系统中的货车ID
     * @return Truck
     */
    Truck findAllTruckByVehicleId(Integer vehicleId);

    /**
     * 
     * 根据AMS系统中的货车ID获取，只返回TMS系统的车辆信息，不存在抛出异常
     * 
     * @author Libin.Wei
     * @Date 2017年3月9日 下午6:18:13
     * @param vehicleId
     *            VM系统中的货车ID
     * @return Truck
     * @throws BusinessException
     */
    Truck findTruckByVehicleIdAndCheckExist(Integer vehicleId) throws BusinessException;

    /**
     * 
     * @Description 新增TMS系统的货车信息
     * @author Libin.Wei
     * @Date 2017年3月10日 上午10:02:25
     * @param truck
     *            货车信息
     * @param loginEmployee
     *            当前登录用户信息
     * @throws BusinessException
     */
    void insert(Truck truck, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Description 编辑TMS系统的货车信息
     * @author Libin.Wei
     * @Date 2017年3月10日 上午10:02:25
     * @param truck
     *            货车信息
     * @param loginEmployee
     *            当前登录用户信息
     * @throws BusinessException
     */
    void update(Truck truck, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Description 禁用货车
     * @author Libin.Wei
     * @Date 2017年3月10日 上午10:02:25
     * @param vehicleId
     *            VM系统中的货车ID
     * @param loginEmployee
     *            当前登录用户信息
     */
    void delete(Integer vehicleId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Description 根据TMS系统的货车ID车型，只返回TMS系统的信息
     * @author Libin.Wei
     * @Date 2017年3月10日 上午11:49:21
     * @param truckId
     *            TMS系统的货车ID
     * @return Truck
     */
    Truck getTruck(Integer truckId);

    /**
     * 
     * @Description 根据TMS系统的货车ID车型，只返回TMS系统的信息
     * @author Libin.Wei
     * @Date 2017年3月10日 上午11:49:21
     * @param truckId
     *            TMS系统的货车ID
     * @throws BusinessException
     * @return Truck
     */
    Truck getTruckAndCheckEixst(Integer truckId) throws BusinessException;

    /**
     * 
     * @Description 删除TMS系统的厢长
     * @author Libin.Wei
     * @Date 2017年4月7日 下午4:17:51
     * @param truckId
     *            TMS系统的车辆ID
     */
    void removeVehicleBoxLength(Integer truckId);

    /**
     * 
     * @Description 删除TMS系统的厢型
     * @author Libin.Wei
     * @Date 2017年4月7日 下午4:17:51
     * @param truckId
     *            TMS系统的车辆ID
     */
    void removeVehicleBoxType(Integer truckId);

    /**
     * 
     * @Title: removeEstimateFinishTime @Description:
     *         删除自动派车的预计完成时间 @param: @param truckId @return: void @throws
     */
    void removeEstimateFinishTime(Integer truckId);

    /**
     * 
     * @Description 获取车辆信息----->>>对外接口
     * @author Libin.Wei
     * @Date 2017年2月8日 下午4:13:27
     * @return
     */
    TruckInfo findTruckInfo();

    /**
     * 
     * 根据TMS车辆ID，构造车辆信息全称
     * 
     * @author Libin.Wei
     * @Date 2017年4月5日 下午9:22:33
     * @param truckId
     *            TMS车辆ID
     * @return String 结构：（车型 | 载重 | 入城证 | 附加功能 | 尾板）
     */
    String findTruckInfoStrByTruckId(Integer truckId, LoginUser loginUser);

    /**
     * 
     * @Description 根据车牌号查询TMS系统货车信息
     * @author Libin.Wei
     * @Date 2017年4月5日 下午9:32:46
     * @param plateNumber
     *            车牌号
     * @return Truck
     */
    Truck findTruckByPlateNumber(String plateNumber);

    /**
     * 
     * 判断车辆是否停运：1、车辆状态为停运；2、计划用车时间点司机停工
     * 
     * @author Libin.Wei
     * @Date 2017年4月13日 下午3:36:52
     * @param vehicleId
     *            AMS系统的车辆信息,不能为空(为空返回true)
     * @param planDeliveryTime
     *            运单的计划用车时间,不能为空(为空返回true)
     * @return true 停运；false 运营
     */
    boolean judgeTruckIsStopCamp(Integer vehicleId, Driver amsDriver, Date planDeliveryTime, LoginUser loginUser);

    /**
     * 
     * @Title: mutilUpdateTruck
     * @Description: 批量更新 
     * @param truck 
     * @return: void 
     * @throws
     */
    void mutilUpdateTruck(MuilEditTruck truck);

    /**
     * 
     * 根据AMS系统司机ID获取TMS系统车辆的数据
     * 
     * @author Libin.Wei
     * @Date 2017年5月20日 上午10:59:09
     * @param amsDriverId
     *            AMS系统司机ID
     * @return
     */
    Truck findTruckByAmsDriverId(Integer amsDriverId, LoginUser loginUser);

    /**
     * 
     * 根据AMS系统司机ID获取TMS系统与AMS系统的车辆数据
     * 
     * @author Libin.Wei
     * @Date 2017年5月20日 上午10:59:54
     * @param amsDriverId
     *            AMS系统司机ID
     * @return TruckVehicle
     */
    TruckVehicle findTruckVehicleByAmsDriverId(Integer amsDriverId, LoginUser loginUser);

    /**
     * 
     * 获取车辆入城证中文名
     * 
     * @author Libin.Wei
     * @Date 2018年1月24日 下午3:52:36
     * @param entryLicense
     * @return
     */
    String getEntryLicenseCnName(Byte entryLicense);
    
    /**
     * 
     * @Title: showYourPrice   
     * @Description: 
     * @param: @return      
     * @return: boolean      
     * @throws
     */
    boolean isShowYourPrice(Integer truckId,LoginUser loginUser);

}
