package com.juma.tgm.cityManage.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.cityManage.domain.CityManage;
import com.juma.tgm.cityManage.domain.CityManageBo;
import com.juma.tgm.cityManage.domain.CityManageInfo;

import java.util.List;

public interface CityManageService {

	/**
     * @Description: 获取所有的城市信息
     * @return List<CityManage>
     */
    Page<CityManage> searchDetails(PageCondition pageCondition, LoginEmployee loginEmployee);

    /**
     * @Title: insert
     * @Description: 添加
     * @param cityManageBo
     * @param loginEmployee
     */
    void insert(CityManageBo cityManageBo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @Title: insertArea
     * @Description: 添加地区管理
     * @param cityManageBo
     * @param loginEmployee
     */
    void insertArea(CityManageBo cityManageBo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @Title: updateToEnable
     * @Description: 启用
     * @param cityManageId
     * @param loginEmployee
     */
    void updateToEnable(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @Title: updateToDisable
     * @Description: 停用
     * @param cityManageId
     * @param loginEmployee
     */
    void updateToDisable(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @Title: getCityList
     * @Description: 根据标识回去城市列表
     * @param sign 0:出发城市；1:到达城市
     * @return List<CityManage>
     */
    CityManageInfo getCityList(Integer sign);

    /**
     * @Title: updateToUp
     * @Description: 上移
     * @param cityManageId
     * @param loginEmployee
     */
    void updateToUp(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @Title: updateToDown
     * @Description: 下移
     * @param cityManageId
     * @param loginEmployee
     */
    void updateToDown(Integer cityManageId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @Title: findOne
     * @Description: 根据ID查询
     * @param cityManageId
     * @return CityManage
     */
    CityManage findOne(Integer cityManageId);

    /**
     * @Title: update
     * @Description: 根据ID查询
     * @param cityManage
     * @param loginEmployee
     */
    void update(CityManage cityManage, LoginEmployee loginEmployee);

    /**
     * @Title: getParaentManageList
     * @Description: 获取大区列表
     * @return List<CityManage>
     */
    List<CityManage> getParaentManageList();

    /**
     * @Title: findCityCodeByIds
     * @Description: 根据ID获取CITYCODE列表
     * @param list
     * @return List<String>
     */
    List<String> findCityCodeByIds(List<Integer> list);

    /**
     * 
     * @Description 根据citySign获取省份列表
     * @author Libin.Wei
     * @Date 2017年3月1日 上午9:53:38
     * @param citySign
     * @param loginEmployee
     * @return
     */
    CityManageInfo getProvinceList(Integer citySign, LoginEmployee loginEmployee);

    /**
     * 
     * @Description 获取城市列表
     * @author Libin.Wei
     * @Date 2017年3月1日 上午9:53:38
     * @param cityManage
     * @return
     */
    CityManageInfo getCityList(CityManage cityManage);

    /**
     * 
     * @Title:       findCityBy   
     * @Description: 地区管理  区域权限
     * @param:       @param citySign
     * @param:       @return      
     * @return:      List<CityManage>      
     * @throws
     */
    List<CityManage> findCityBy(Integer citySign);

    /**
     * 
     * @Description 根据城市编码与类型获取
     * @author Libin.Wei
     * @Date 2017年3月6日 上午9:57:19
     * @param citySign
     * @param cityCode 城市编码
     * @return
     */
    CityManage findCityManageByCityCode(Integer citySign, String cityCode);
}
