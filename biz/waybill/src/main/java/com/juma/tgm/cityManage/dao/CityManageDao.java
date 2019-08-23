package com.juma.tgm.cityManage.dao;

import java.util.List;
import java.util.Map;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.cityManage.domain.CityManage;

public interface CityManageDao extends MybatisDao<CityManage>{

    /**
     * @Title: findBySign
     * @Description: 根据标识城市管理列表
     * @param sign
     * @return List<CityManage>
     */
    List<CityManage> findBySign(Integer sign);

    /**
     * @Title: findMaxOederNo
     * @Description: 根据条件获取最大的排序号
     * @param citySign
     * @return int
     */
    Integer findMaxOederNo(Integer citySign);

    /**
     * @Title: getParentListByIds
     * @Description: 根据ID获取父数据
     * @param map
     * @return List<CityManage>
     */
    List<CityManage> getParentListByIds(Map<String, Object> map);

    /**
     * @Title: getCityManageListByMap
     * @Description: 根据父ID与ID获取数据
     * @param map
     * @return List<CityManage>
     */
    List<CityManage> getCityManageListByMap(Map<String, Object> map);

    /**
     * @Title: findCityCodeByIds
     * @Description: 根据ID获取CITYCODE列表
     * @param list
     * @return List<String>
     */
    List<String> findCityCodeByIds(List<Integer> list);
}
