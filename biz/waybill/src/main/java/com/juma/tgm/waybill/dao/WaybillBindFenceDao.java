package com.juma.tgm.waybill.dao;

import java.util.List;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.waybill.domain.WaybillBindFence;

/**
 * @ClassName WaybillBindFenceDao.java
 * @Description 运单绑定电子围栏DAO
 * @author Libin.Wei
 * @Date 2017年4月19日 下午3:01:47
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface WaybillBindFenceDao extends MybatisDao<WaybillBindFence> {

    /**
     * 
     * 根据运单ID修改
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午3:44:30
     * @param waybillBindFence
     */
    void updateByWaybillId(WaybillBindFence waybillBindFence);

    /**
     * 
     * 获取目的地已完成或未完成的配送点的数量
     * 
     * @author Libin.Wei
     * @Date 2017年4月19日 下午5:37:08
     * @param waybillBindFence
     * @return
     */
    int findReceivePointNo(WaybillBindFence waybillBindFence);

    /**
     * 
     * 修改查询
     * 
     * @author Libin.Wei
     * @Date 2017年9月29日 下午5:28:20
     * @param waybillBindFenceId
     * @return
     */
    WaybillBindFence getForUpdate(Integer waybillBindFenceId);

    /**
     * 
     * 修改查询
     * 
     * @author Libin.Wei
     * @Date 2017年9月29日 下午5:34:59
     * @param waybillBindFence
     * @return
     */
    List<WaybillBindFence> findByExampleForUpdate(WaybillBindFence waybillBindFence);
}
