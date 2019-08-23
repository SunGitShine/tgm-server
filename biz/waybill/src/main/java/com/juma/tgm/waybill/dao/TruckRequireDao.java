package com.juma.tgm.waybill.dao;

import com.giants.common.exception.BusinessException;
import com.giants.dal.dao.GiantsDao;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.vo.TruckRequireFilter;

import java.util.List;

public interface TruckRequireDao extends GiantsDao<TruckRequire> {

    /**
     * 
     * @Description 根据运单ID获取用车要求
     * @author Libin.Wei
     * @Date 2017年2月16日 上午10:02:01
     * @param waybillId
     * @return
     */
    TruckRequire getTruckRequireByWaybillId(Integer waybillId);

    /**
     * 
     * @Description 清空不需要的信息(例如：页面没有勾选利率，清空数据库中利率)
     * @author Libin.Wei
     * @Date 2017年2月16日 上午9:50:22
     * @param truckRequire 用车要求
     * @throws BusinessException
     */
    void removeNullInfo(TruckRequire truckRequire);

    /**多条件查询**/
    List<TruckRequire> selectByExample(TruckRequireFilter filter);
}
