package com.juma.tgm.truck.dao;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.truck.domain.TruckTypeFreight;

/**
 * Created by Administrator on 2016/5/11 0011. 车型价格
 */
public interface TruckTypeFreightDao extends MybatisDao<TruckTypeFreight> {

    /**
     * 
     * 联表获取总数
     * 
     * @author Libin.Wei
     * @Date 2017年12月7日 下午5:05:56
     * @param pageCondition
     * @return
     */
    int searchCountJoin(PageCondition pageCondition);

    /**
     * 
     * 联表分页
     * 
     * @author Libin.Wei
     * @Date 2017年12月7日 下午5:06:45
     * @param pageCondition
     * @return
     */
    List<TruckTypeFreight> searchJoin(PageCondition pageCondition);
}
