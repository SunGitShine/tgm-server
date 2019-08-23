package com.juma.tgm.driver.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.domain.Driver.RemindSwitchType;
import com.juma.tgm.driver.domain.Driver.RemindSwitchValue;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;

/**
 * @author vencent.lu
 *
 */
@Deprecated
public interface DriverService {

    /**
     * 
     * 根据司机ID获取
     * 
     * @author Libin.Wei
     * @Date 2017年5月17日 上午9:25:21
     * @param driverId
     * @return
     */
    Driver getDriver(Integer driverId);

    /**
     * 
     * 根据司机的ID获取对应的信息，不存在抛出异常
     * 
     * @param driverId
     *            司机ID
     * @return Driver 司机信息
     * @throws BusinessException
     */
    @Deprecated
    Driver getDriverAndCheckExist(Integer driverId) throws BusinessException;

    /**
     * 
     * 根据【AMS系统的司机ID】获取【TMS系统】的司机信息
     * 
     * @author Libin.Wei
     * @Date 2017年5月18日 下午10:25:39
     * @param amsDriverId
     *            AMS系统的司机ID
     * @return Driver TMS系统司机信息
     */
    Driver findDriverByAmsDriverId(Integer amsDriverId);

    /**
     * 
     * 根据【AMS系统的司机ID】获取【TMS系统】的司机信息（所有数据，包括已经删除的数据）
     * 
     * @author Libin.Wei
     * @Date 2017年5月18日 下午10:25:39
     * @param amsDriverId
     *            AMS系统的司机ID
     * @return Driver TMS系统司机信息
     */
    Driver findAllDriverByAmsDriverId(Integer amsDriverId);

    /**
     * 
     * 根据【AMS系统的司机ID】获取【TMS系统】的司机信息，不存在抛出异常
     * 
     * @author Libin.Wei
     * @Date 2017年5月18日 下午10:25:39
     * @param amsDriverId
     *            AMS系统的司机ID
     * @return Driver TMS系统司机信息
     * @throws BusinessException
     */
    Driver findDriverByAmsDriverIdAndCheckExist(Integer amsDriverId) throws BusinessException;

    /**
     * 
     * 根据userId获取【TMS系统】的司机信息：司机的基础信息只有唯一的一条数据
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午8:44:39
     * @param userId
     *            用户ID
     * @return Driver
     */
    Driver findDriverByUserId(Integer userId);

    /**
     * 
     * 根据userID在所有司机中获取【TMS系统】的司机信息：司机的基础信息只有唯一的一条数据
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午8:44:39
     * @param userId
     *            用户ID
     * @return Driver
     */
    Driver findAllDriverByUserId(Integer userId);

    /**
     * 
     * 根据手机号获取司机的资料
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午8:54:21
     * @param contactPhone
     *            手机号
     * @return Driver
     */
    Driver findDriverByPhone(String contactPhone);

    /**
     * 
     * 根据手机号获取司机的资料（查询所有数据,包含删除的数据）
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午8:54:21
     * @param contactPhone
     *            手机号
     * @return Driver
     */
    Driver findAllDriverByPhone(String contactPhone);

    /**
     * 
     * 根据姓名获取司机的资料
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午8:56:53
     * @param nickname
     *            司机姓名
     * @return List<Driver>
     */
    List<Driver> listDriverByName(String nickname);

    /**
     * 
     * @Description 根据司机的状态获取司机的集合
     * @author Libin.Wei
     * @Date 2017年2月8日 下午4:22:24
     * @param status
     *            司机状态
     * @return
     */
    @Deprecated
    List<Driver> findDriverIdListByStatus(int status);

    /**
     * 
     * 根据运单信息得到司机与货车相关信息
     * 
     * @author Libin.Wei
     * @Date 2017年2月9日 下午2:07:22
     * @param waybillId
     *            运单ID
     * @return
     */
    DriverTruckInfoBo findDriverTruckInfoByWaybillId(Integer waybillId);

    /**
     * 
     * 添加司机
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午6:39:49
     * @param driver
     */
    void insert(Driver driver, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改司机信息
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午6:41:37
     * @param driver
     * @param loginUser
     * @throws BusinessException
     */
    void update(Driver driver, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 删除司机信息，逻辑删除
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午6:42:08
     * @param driverId
     * @param loginUser
     */
    void delete(Integer driverId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 更新司机任务状态
     * 
     * @author Libin.Wei
     * @Date 2017年5月17日 上午9:34:05
     * @param driverId
     *            司机ID
     * @param status
     *            任务状态
     */
    void updateDriverTaskStatus(Integer driverId, Driver.TaskStatus status) throws BusinessException;

    /**
     * 
     * 修改司机接单开关
     * 
     * @author Libin.Wei
     * @Date 2017年5月16日 下午6:41:37
     * @param driver
     * @param loginUser
     * @throws BusinessException
     */
    void updateDriverWhetherAcceptAllocateOrder(Driver driver, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 检查司机状态 认证与否 可接单与否
     * 
     * @author Libin.Wei
     * @Date 2017年5月20日 下午2:46:01
     * @param driverId
     *            TMS系统司机ID
     * @throws BusinessException
     */
    void checkDriverAcceptAllocateOrder(Integer driverId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据司机的业务区域获取,只返回在职的司机
     * 
     * @author Libin.Wei
     * @Date 2017年8月9日 下午2:12:32
     * @param tenantId
     *            租户编码，为空时返回空
     * @param areaCode
     *            司机的业务区域，为空时返回空
     * @param driverType
     *            司机类型（自营，加盟等），可以为空
     * @return List<Driver>
     */
    List<Driver> listByAreaCodeLike(Integer tenantId, String areaCode, Integer driverType);
}
