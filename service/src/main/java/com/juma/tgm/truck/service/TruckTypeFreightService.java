package com.juma.tgm.truck.service;

import java.math.BigDecimal;
import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.truck.domain.TruckTypeFreight;
import com.juma.tgm.truck.domain.TruckTypeFreightBo;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.drools.PriceAfterTaxProxy;
import com.juma.tgm.waybill.domain.drools.PriceProxy;

/**
 * Created by Administrator on 2016/5/11 0011.
 */
public interface TruckTypeFreightService {

    /**
     * 根据ID返回对象
     * 
     * @param id
     * @return
     */
    TruckTypeFreightBo getTruckTypeFreigthById(Integer id);

    /**
     * 根据车型ID返回该车型运费
     * 
     * @param truckTypeId
     * @param regionCode 城市编码
     * @return
     */
    TruckTypeFreight findFreightByTypeIdAndCityManageId(Integer truckTypeId, String regionCode);

    /**
     * 
     * @Title: search
     * @Description: 分页获取价格列表
     * @param pageCondition
     * @return Page<TruckTypeFreightBo>
     */
    Page<TruckTypeFreightBo> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * @Title: insert @Description: 新增价格配置 @param truckTypeFreightBo @param
     *         loginUser @throws
     */
    void insert(TruckTypeFreightBo truckTypeFreightBo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Title: update @Description: 修改价格配置 @param truckTypeFreightBo @param
     *         loginUser @throws
     */
    void update(TruckTypeFreightBo truckTypeFreightBo, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Description 根据城市获取所有的运费规则
     * @author Libin.Wei
     * @Date 2017年3月1日 下午2:35:02
     * @param cityManageId 大区ID
     * @param truckTypeId 车型ID
     * @return
     */
    List<TruckTypeFreight> findByRegionCodeAndTypeId(Integer cityManageId, Integer truckTypeId);

    /**
     * 根据车型ID判断该车型是否配置了运费
     * 
     * @param truckTypeId
     * @param regionCode 城市编码
     * @return
     */
    boolean findTruckTypeFreigthBoByTypeId(Integer truckTypeId, String regionCode);

    
    
    /**
     * @Title: updateToDisable
     * @Description: 启用
     * @param freightId
     * @param loginEmployee
     */
    void updateToEnable(Integer freightId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * @Title: updateToDisable
     * @Description: 停用
     * @param freightId
     * @param loginEmployee
     */
    void updateToDisable(Integer freightId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 
     * @Title: getFright
     * @Description:
     * @param truckRequire
     *            用车要求
     * @param data
     *            总里程及高速里程、高速费用等信息
     * @param siteNo
     *            总配送点数
     * @param regionCode
     *            取货地
     * @return PriceProxy
     * @throws BusinessException
     */
    PriceProxy getFright(WaybillBo waybillBo,  DistanceAndPriceData data, Integer siteNo, String regionCode, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Title: getAfterTaxFrightProxy
     * @Description: 计算税后价格MODEL
     * @param waybill 运单信息
     * @return PriceAfterTaxProxy
     * @throws BusinessException
     */
    PriceAfterTaxProxy getAfterTaxFrightProxy(TruckRequire truckRequire, Waybill waybill, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * @Title: getAfterTaxFright
     * @Description: 计算税后价格
     * @param waybill 运单信息
     * @return BigDecimal
     * @throws BusinessException
     */
    BigDecimal getAfterTaxFright(TruckRequire truckRequire, Waybill waybill) throws BusinessException;

    /**
     * 
     * @Title: getFrightForCost
     * @Description: 根据搬运费信息计算系统报价
     * @param waybillParam 搬运费信息
     * @return BigDecimal
     * @throws BusinessException
     */
    BigDecimal getFrightForCost(WaybillParam waybillParam, WaybillParam oldParam, LoginUser loginUser) throws BusinessException;
}
