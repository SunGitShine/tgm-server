package com.juma.tgm.truck.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.truck.domain.TruckType;
import io.swagger.annotations.Example;
import java.util.List;

/**
 * Created by  RX on 2016/5/12 0012.
 * 车型DAO
 */
public interface TruckTypeDao extends MybatisDao<TruckType>{

    /**
     * 
     * 获取最大的排序码
     * @author Libin.Wei
     * @Date 2017年4月14日 下午3:23:49
     * @return
     */
    int findMaxOrderNo(Integer tenantId);

    /**
     * 根据租户获取车型信息并且根据厢型去重
     *
     * @param example
     * @return
     */
    List<TruckType> findByExampleGroupByBoxType(TruckType example);
}
