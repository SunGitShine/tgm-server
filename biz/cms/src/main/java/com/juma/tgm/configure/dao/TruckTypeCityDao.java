package com.juma.tgm.configure.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.configure.domain.TruckTypeCity;

public interface TruckTypeCityDao extends MybatisDao<TruckTypeCity> {

    /**
     * 
     * 根据地区编码删除
     * 
     * @author Libin.Wei
     * @Date 2017年11月15日 下午6:08:27
     * @param regionCode
     */
    void deleteByRegion(String regionCode);

    /**
     * 
     * 获取当前排序码的上一个数据
     * 
     * @author Libin.Wei
     * @Date 2018年1月29日 下午3:50:35
     * @param truckTypeCity
     */
    TruckTypeCity findLastOrderNo(TruckTypeCity truckTypeCity);

    /**
     * 
     * 获取当前排序码的下一个数据
     * 
     * @author Libin.Wei
     * @Date 2018年1月29日 下午3:50:35
     * @param truckTypeCity
     */
    TruckTypeCity findNextOrderNo(TruckTypeCity truckTypeCity);
}
