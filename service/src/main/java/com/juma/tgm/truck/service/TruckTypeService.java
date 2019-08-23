package com.juma.tgm.truck.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.base.domain.BaseEnumDomian;
import com.juma.tgm.truck.domain.TruckType;
import java.util.List;

/**
 * @author rx
 * @version V1.0
 * @Description: 车型Service
 * @date 2016/5/13
 */
public interface TruckTypeService {

    /**
     * 
     * 车型列表
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午1:52:22
     * @param pageCondition
     *            条件
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @return
     */
    Page<TruckType> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据车型Id返回车型信息
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:12:16
     * @param truckTypeId
     *            车型ID
     * @return TruckType
     */
    TruckType getTruckType(Integer truckTypeId);

    /**
     * 
     * 根据条件返回车型基础信息，根据排序码正序
     * 
     * @param tenantId
     *            租户编码，为空不返回数据
     * @param isDelete
     *            可以为空
     * @return List<TruckType>
     */
    List<TruckType> listAllTruckTypeSimpleByOrderNoAsc(Integer tenantId, Boolean isDelete);

    /**
     * 
     * 根据条件返回车型信息并且拼接车型名称，根据排序码正序
     * 
     * @param tenantId
     *            租户编码，为空不返回数据
     * @param isDelete
     *            可以为空
     * @return List<TruckType>
     */
    List<TruckType> listAllTruckTypeByOrderNoAsc(Integer tenantId, Boolean isDelete);

    /**
     * 
     * 获取对应城市下的车型列表
     * 
     * @author Libin.Wei
     * @Date 2017年11月15日 下午4:18:01
     * @param regionCode
     *            城市编码，为空不返回数据
     * @param isEnable
     *            true:启用 ， false:禁用
     * @return
     */
    List<TruckType> listByRegionCode(String regionCode, boolean isEnable, LoginUser loginUser);

    /**
     * 
     * @Description 获取厢型的中文名
     * @author Libin.Wei
     * @Date 2017年3月9日 下午3:57:09
     * @param vehicleBoxType
     *            厢型ID
     * @return 厢型中文名
     * @throws BusinessException
     */
    String findVehicleBoxTypeName(Integer vehicleBoxType) throws BusinessException;

    /**
     * 
     * @Description 获取厢长的中文名
     * @author Libin.Wei
     * @Date 2017年3月9日 下午3:57:09
     * @param boxLength
     *            厢长ID
     * @return 厢型中文名
     * @throws BusinessException
     */
    String findVehicleBoxLengthName(Integer boxLength) throws BusinessException;

    /**
     * 
     * @Description 获取车型的中文全称
     * @author Libin.Wei
     * @Date 2017年3月9日 下午3:57:09
     * @param truckTypeId
     *            车型ID
     * @return 车型中文名
     * @throws BusinessException
     */
    String findTruckTypeNameByTypeId(Integer truckTypeId) throws BusinessException;

    /**
     * 
     * 获取车型的中文全称
     * 
     * @author Libin.Wei
     * @Date 2018年5月22日 下午3:24:00
     * @param vehicleBoxType
     * @param vehicleBoxLength
     * @return
     * @throws BusinessException
     */
    String findTruckTypeNameBy(Integer vehicleBoxType, Integer vehicleBoxLength) throws BusinessException;

    /**
     * @Title: findTruckTypeByLengthId
     * @Description: 根据车长ID获取所有车型
     * @param truckLengthId
     *            车长ID
     */
    List<TruckType> findTruckTypeByLengthId(Integer truckLengthId);

    /**
     * 
     * 添加新的车型
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:15:16
     * @param truckType
     *            车型信息
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void insert(TruckType truckType, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改车型信息
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:33:55
     * @param truckType
     *            车型信息
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void update(TruckType truckType, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 启用
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:42:39
     * @param truckTypeId
     *            车型ID
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void updateToEnable(Integer truckTypeId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 停用
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:42:51
     * @param truckTypeId
     *            车型ID
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void updateToDisable(Integer truckTypeId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 上移车型
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:44:12
     * @param truckTypeId
     *            车型ID
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void updateToUp(Integer truckTypeId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 下移车型
     * 
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:44:29
     * @param truckTypeId
     *            车型ID
     * @param loginUser
     *            当前登录人信息，数据结构来源于用户中心
     * @throws BusinessException
     */
    void updateToDown(Integer truckTypeId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 获取所有厢型列表
     * 
     * @author Libin.Wei
     * @Date 2018年3月15日 下午4:15:29
     * @return
     */
    List<BaseEnumDomian> listVehicleBoxType();

    /**
     * 
     * 获取所有厢长级别列表
     * 
     * @author Libin.Wei
     * @Date 2018年5月15日 下午6:49:41
     * @return
     */
    List<BaseEnumDomian> listVehicleBoxlength();

    /**
     * 
     * 根据条件获取
     * 
     * @author Libin.Wei
     * @Date 2018年5月22日 下午3:19:27
     * @param vehicleBoxType
     * @param vehicleBoxlength
     * @param tenantId
     * @return
     */
    TruckType findByBoxAndLength(Integer vehicleBoxType, Integer vehicleBoxlength, Integer tenantId);

    /**
     * 获取租户下的厢型列表
     *
     * @param loginUser
     *
     * @return
     */
    List<TruckType> listVehicleBoxTypeByTenant(LoginUser loginUser);

    /**
     * 根据厢型与租户获取厢长列表
     *
     * @param vehicleBoxType
     * @param loginUser
     *
     * @return
     */
    List<TruckType> listVehicleBoxLengthBytypeAndTenant(Integer vehicleBoxType, LoginUser loginUser);
}
