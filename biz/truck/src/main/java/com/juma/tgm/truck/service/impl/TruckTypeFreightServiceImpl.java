package com.juma.tgm.truck.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.cityManage.domain.CityManage;
import com.juma.tgm.cityManage.service.CityManageService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.algorithm.IntervalSegment;
import com.juma.tgm.costReimbursed.domain.CostReimbursed;
import com.juma.tgm.costReimbursed.service.CostReimbursedService;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.truck.dao.TruckTypeFreightDao;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunction.FunctionKeys;
import com.juma.tgm.truck.domain.AdditionalFunctionFreight;
import com.juma.tgm.truck.domain.AdditionalFunctionFreightBo;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.domain.TruckTypeFreight;
import com.juma.tgm.truck.domain.TruckTypeFreightBo;
import com.juma.tgm.truck.service.AdditionalFunctionFreightService;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.version.service.VersionService;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;
import com.juma.tgm.waybill.domain.TaxRate;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.domain.drools.PriceAfterTaxProxy;
import com.juma.tgm.waybill.domain.drools.PriceProxy;
import com.juma.tgm.waybill.domain.drools.RebateRateInfo;
import com.juma.tgm.waybill.domain.drools.TaxRateInfo;
import com.juma.tgm.waybill.service.TaxRateService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillService;

/**
 * Created by Administrator on 2016/5/11 0011. 车型运费service实现
 */
@Service
public class TruckTypeFreightServiceImpl implements TruckTypeFreightService {

    private final Logger log = LoggerFactory.getLogger(TruckTypeFreightServiceImpl.class);
    @Resource
    private TruckTypeFreightDao truckTypeFreightDao;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private AdditionalFunctionFreightService additionalFunctionFreightService;
    @Resource
    private AdditionalFunctionService additionalFunctionService;
    @Resource
    private TaxRateService taxRateService;
    @Resource
    private TruckRequireService truckRequireService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private CityManageService cityManageService;
    @Resource
    private VersionService versionService;
    @KSession("ksession")
    private KieSession kSession;

    @Resource
    private CostReimbursedService costReimbursedService;

    // 编辑回显
    @Override
    public TruckTypeFreightBo getTruckTypeFreigthById(Integer id) {
        TruckTypeFreightBo bo = new TruckTypeFreightBo();
        TruckTypeFreight freight = truckTypeFreightDao.get(id);// 基本功能价格
        if (null != freight) {
            bo.setAffFreight(additionalFunction(freight.getFreightId()));
            bo.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(freight.getTruckTypeId()));
            bo.setTruckTypeFreight(freight);
            CityManage cityManage = cityManageService.findOne(freight.getCityManageId());
            if (null != cityManage) {
                bo.setCityManageName(cityManage.getCityName());
            }
        }
        return bo;
    }

    // 编辑
    @Override
    public void update(TruckTypeFreightBo bo, LoginEmployee loginEmployee) {
        TruckTypeFreight truckTypeFreight = bo.getTruckTypeFreight();
        TruckTypeFreight typeFreight = truckTypeFreightDao.get(truckTypeFreight.getFreightId());
        if (null != typeFreight) {
            truckTypeFreight.setFreightId(typeFreight.getFreightId());
            AdditionalFunctionFreightBo additionalFunctionFreightBo = bo.getAffFreight();
            List<AdditionalFunctionFreight> list = addfreightToList(truckTypeFreight, additionalFunctionFreightBo);
            truckTypeFreight.setLastUpdateUserId(loginEmployee.getUserId());
            truckTypeFreight.setCreateUserId(loginEmployee.getUserId());
            if (null == truckTypeFreight.getDistributionPointPrice()) {
                truckTypeFreight.setDistributionPointPrice(BigDecimal.ZERO);
            }
            truckTypeFreightDao.update(truckTypeFreight);
            additionalFunctionFreightService.update(list, typeFreight.getFreightId(), loginEmployee);
        }
    }

    // 分页查询
    @Override
    public Page<TruckTypeFreightBo> search(PageCondition pageCondition, LoginUser loginUser) {
        List<TruckTypeFreightBo> result = new ArrayList<TruckTypeFreightBo>();
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<TruckTypeFreightBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        int count = truckTypeFreightDao.searchCountJoin(pageCondition);
        List<TruckTypeFreight> rows = truckTypeFreightDao.searchJoin(pageCondition);
        for (TruckTypeFreight freight : rows) {
            TruckTypeFreightBo bo = new TruckTypeFreightBo();
            bo.setTruckTypeFreight(freight);
            // 根据车型ID获取附加功能运费信息
            Integer truckTypeId = bo.getTruckTypeFreight().getTruckTypeId();
            bo.setAffFreight(additionalFunction(freight.getFreightId()));
            TruckType truckType = truckTypeService.getTruckType(truckTypeId);
            bo.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(truckTypeId));
            bo.setTruckType(truckType);
            CityManage cityManage = cityManageService.findOne(freight.getCityManageId());
            if (null != cityManage) {
                bo.setCityManageName(cityManage.getCityName());
            }
            result.add(bo);
        }
        return new Page<TruckTypeFreightBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, result);
    }

    // 增
    @Override
    public void insert(TruckTypeFreightBo bo, LoginEmployee loginEmployee) {
        Integer createUserId = loginEmployee.getUserId();
        TruckTypeFreight truckTypeFreight = bo.getTruckTypeFreight();
        List<TruckTypeFreight> rows = findByRegionCodeAndTypeId(truckTypeFreight.getCityManageId(), truckTypeFreight.getTruckTypeId());
        if (CollectionUtils.isNotEmpty(rows)) {
            throw new BusinessException("truckTypeFreightExist", "errors.truckTypeFreightExist");
        }
        AdditionalFunctionFreightBo additionalFunctionFreightBo = bo.getAffFreight();
        List<AdditionalFunctionFreight> list = addfreightToList(truckTypeFreight, additionalFunctionFreightBo);
        truckTypeFreight.setCreateUserId(createUserId);
        truckTypeFreightDao.insert(truckTypeFreight);// 插入基本功能费用
        if (!list.isEmpty()) {
            for (AdditionalFunctionFreight additionalFunctionFreight : list) {
                additionalFunctionFreight.setFreightId(truckTypeFreight.getFreightId());
            }
        }
        additionalFunctionFreightService.insert(list, loginEmployee);// 插入附加功能记录组
    }

    @Override
    public boolean findTruckTypeFreigthBoByTypeId(Integer truckTypeId, String regionCode) {
        TruckTypeFreight truckTypeFreight = findFreightByTypeIdAndCityManageId(truckTypeId, regionCode);
        if (null != truckTypeFreight) {
            return true;
        }
        return false;
    }

    @Override
    public TruckTypeFreight findFreightByTypeIdAndCityManageId(Integer truckTypeId, String regionCode) {
        if (StringUtils.isBlank(regionCode)) {
            // default freight
            List<TruckTypeFreight> typeFreightList = findByRegionCodeAndTypeId(getDefaultCityManageId(), truckTypeId);
            if (CollectionUtils.isNotEmpty(typeFreightList)) {
                return typeFreightList.get(0);
            }
        } else {
            if (regionCode.length() > 5) {
                regionCode = regionCode.substring(0, 5);
            }
            CityManage cityManage = cityManageService.findCityManageByCityCode(CityManage.Sign.AREA_MANAGE.getCode(), regionCode);
            if (null == cityManage) {
                // default freight
                List<TruckTypeFreight> freightList = findByRegionCodeAndTypeId(getDefaultCityManageId(), truckTypeId);
                if (CollectionUtils.isNotEmpty(freightList)) {
                    return freightList.get(0);
                }

            } else {
                List<TruckTypeFreight> typeFreightList = findByRegionCodeAndTypeId(cityManage.getParentCityManageId(), truckTypeId);
                if (CollectionUtils.isNotEmpty(typeFreightList)) {
                    return typeFreightList.get(0);
                }
                // default freight
                List<TruckTypeFreight> freightList = findByRegionCodeAndTypeId(getDefaultCityManageId(), truckTypeId);
                if (CollectionUtils.isNotEmpty(freightList)) {
                    return freightList.get(0);
                }
            }
        }
        return null;
    }

    // 获取默认的大区ID
    private Integer getDefaultCityManageId() {
        CityManage cityManage = cityManageService.findCityManageByCityCode(CityManage.Sign.AREA_MANAGE.getCode(),
                versionService.findDefaultRegionCode(Constants.DEFAULT_FRIGHT_CODE_KEY));
        if (null == cityManage || null == cityManage.getParentCityManageId() || cityManage.getParentCityManageId().equals(0)) {
            throw new BusinessException("regionCoceNottruckTypeFreightErr", "errors.regionCoceNottruckTypeFreightErr");
        }
        return cityManage.getParentCityManageId();
    }

    @Override
    public PriceProxy getFright(WaybillBo waybillBo, DistanceAndPriceData data, Integer siteNo, String regionCode, LoginUser loginUser) throws BusinessException {
        TruckRequire require = waybillBo.getTruckRequire();
        if (null == require) {
            throw new BusinessException("truckTypeMustSelect", "truckTypeFreight.not.truckTypeMustSelect");
        }
        Integer truckTypeId = require.getTruckTypeId();
        if (null == truckTypeId) {
            throw new BusinessException("truckTypeMustSelect", "truckTypeFreight.not.truckTypeMustSelect");
        }
        // 基础运费规则
        TruckTypeFreight typeFreight = findFreightByTypeIdAndCityManageId(truckTypeId, regionCode);
        if (null == typeFreight) {
            throw new BusinessException("truckTypeFreightNotFound", "truckTypeFreight.not.found");
        }
        log.info("基础运费规则:{}.", typeFreight);

        // 附加功能运费
        String functionIds = require.getAdditionalFunctionIds();
        List<Integer> list = new ArrayList<Integer>();
        if (StringUtils.isNotBlank(functionIds)) {
            String[] split = functionIds.split(",");
            for (String str : split) {
                list.add(BaseUtil.strToNum(str));
            }
        }

        // 时间成本
        BigDecimal timeCost = BigDecimal.ZERO;
        BigDecimal parkingFee = BigDecimal.ZERO;
        BigDecimal tolls = data.getTolls() == null ? BigDecimal.ZERO : data.getTolls();

        // 返点率 从客户上取
        RebateRateInfo rebateRateInfo = new RebateRateInfo();

        Waybill waybill = waybillBo.getWaybill();

        if (waybill != null) {

            if (waybill.getCustomerId() != null && waybill.getCustomerId() != 0) {
                BigDecimal rebateRate = customerInfoService.getRebateRate(waybill.getCustomerId());
                rebateRateInfo = new RebateRateInfo(rebateRate);
                log.info("客户{},返点率:{}.", waybill.getCustomerId(), rebateRate);
            }

            if (require.getWaybillId() != null) {
                // 1=停车费
                List<CostReimbursed> rows = costReimbursedService.listByKeyAndTypeAndWaybillId(CostReimbursed.CostReimbursedKey.DRIVER_COST_REIMBURSED, 1, require.getWaybillId(), loginUser);
                for (CostReimbursed row : rows) {
                    parkingFee = parkingFee.add(row.getReimbursedAmount());
                }
                log.info("停车费:{}.", parkingFee);
                // 2=过路费
                // 取值优先级
                List<CostReimbursed> _rows = costReimbursedService.listByKeyAndTypeAndWaybillId(CostReimbursed.CostReimbursedKey.DRIVER_COST_REIMBURSED, 2, require.getWaybillId(), loginUser);
                if (!_rows.isEmpty()) {
                    tolls = BigDecimal.ZERO;// 重置
                }
                for (CostReimbursed row : _rows) {
                    tolls = tolls.add(row.getReimbursedAmount());
                }
                log.info("过路费:{}.", tolls);
            }

            Date planDeliveryTime = null;
            Date finishTime = null;

            // 用车时间：头天装第二天送的运单，取第二天的8:08, 然后推后一小时，相当于9:08；其它的取运单中的用车时间
            if (waybill.getPlanDeliveryTime() != null) {
                if (null != waybill.getOnlyLoadCargo() && NumberUtils.compare(waybill.getOnlyLoadCargo(), 1) == 0) {
                    log.info("运单{},是头天装第二天送的运单", require.getWaybillId());
                    Calendar planDeliveryDate = Calendar.getInstance();
                    planDeliveryDate.setTime(waybill.getPlanDeliveryTime());
                    planDeliveryDate.add(Calendar.DAY_OF_MONTH, 1);
                    planDeliveryDate.set(Calendar.HOUR_OF_DAY, 9);
                    planDeliveryDate.set(Calendar.MINUTE, 8);
                    planDeliveryDate.set(Calendar.SECOND, 0);
                    planDeliveryTime = planDeliveryDate.getTime();
                } else {
                    log.info("运单{},不是头天装第二天送的运单", require.getWaybillId());
                    Calendar planDeliveryDate = Calendar.getInstance();
                    planDeliveryDate.setTime(waybill.getPlanDeliveryTime());
                    planDeliveryDate.add(Calendar.HOUR_OF_DAY, 1);
                    planDeliveryTime = planDeliveryDate.getTime();
                }
            }

            if (null != planDeliveryTime) {
                // 完成时间：配送完成时间->预估完成时间
                if (null != waybill.getFinishTime()) {
                    log.info("运单{},有配送完成时间", require.getWaybillId());
                    Calendar estimateFinishTime = Calendar.getInstance();
                    estimateFinishTime.setTime(waybill.getFinishTime());
                    estimateFinishTime.add(Calendar.HOUR_OF_DAY, -1);
                    finishTime = estimateFinishTime.getTime();
                } else {
                    log.info("运单{},没有配送完成时间", require.getWaybillId());
                    Calendar estimateFinishTime = Calendar.getInstance();
                    long minue = StringUtils.isBlank(data.getDuration()) ? 0 : Integer.parseInt(data.getDuration());
                    log.info("预估分钟{}.", minue);
                    estimateFinishTime.setTime(waybill.getCmEstimateFinishTime() == null
                            ? new Date(planDeliveryTime.getTime() + minue * 60 * 1000) : waybill.getCmEstimateFinishTime());
                    estimateFinishTime.add(Calendar.HOUR_OF_DAY, -1);
                    finishTime = estimateFinishTime.getTime();
                }

                // 计算时间成本系数
                if (null != planDeliveryTime) {
                    log.info("运单{},计算时间成本系数，开始时间：{}", require.getWaybillId(),planDeliveryTime);
                    log.info("运单{},计算时间成本系数，结束时间：{}", require.getWaybillId(),finishTime);
                    float weight = IntervalSegment.computeWeight(planDeliveryTime, finishTime);
                    log.info("运单{},时间成本系数:{}.", require.getWaybillId(), weight);
                    timeCost = timeCost.add(typeFreight.getPricePerDay().multiply(new BigDecimal(weight)));
                    log.info("运单{},时间成本:{}.", require.getWaybillId(), timeCost);
                }
            }
        }

        PriceProxy priceProxy = new PriceProxy(typeFreight, getAffMap(require, typeFreight.getFreightId(), loginUser), data, siteNo, list);
        priceProxy.setTolls(tolls);
        priceProxy.setParkingFee(parkingFee);
        priceProxy.setTimeCost(timeCost);
        priceProxy.setRebateRateInfo(rebateRateInfo);
        log.info("PriceProxy {}.", priceProxy);
        return priceProxy;
    }

    @Override
    public PriceAfterTaxProxy getAfterTaxFrightProxy(TruckRequire require, Waybill waybill, LoginUser loginUser) throws BusinessException {
        // 基础运费规则
        TruckTypeFreight typeFreight = findFreightByTypeIdAndCityManageId(require.getTruckTypeId(), waybill.getRegionCode());
        if (null == typeFreight) {
            throw new BusinessException("truckTypeFreightNotFound", "truckTypeFreight.not.found");
        }
        List<Integer> list = new ArrayList<Integer>();
        String functionIds = require.getAdditionalFunctionIds();
        if (StringUtils.isNotBlank(functionIds)) {
            String[] split = functionIds.split(",");
            for (String str : split) {
                list.add(BaseUtil.strToNum(str));
            }
        }
        return new PriceAfterTaxProxy(getAffMap(require, typeFreight.getFreightId(), loginUser), waybill.getEstimateFreight(), list);
    }

    @Override
    public BigDecimal getAfterTaxFright(TruckRequire require, Waybill waybill) throws BusinessException {
        if(waybill.getEstimateFreight() == null) return null;

        if (null == require) {
            require = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), null);
        }
        /*
         * log.info("计算税后价格waybillId:{}", waybill.getWaybillId()); Integer taxRateId = require.getTaxRateId(); if (null != taxRateId) { PriceAfterTaxProxy proxy =
         * getAfterTaxFrightProxy(require, waybill); kSession.insert(proxy); kSession.fireAllRules(); BigDecimal price = proxy.getFinalAfterTaxPrice(); log.info("计算税后价格result:{}",
         * price == null ? "没有得到税后价格": proxy.toString()); return price == null ? waybill.getEstimateFreight() : price.setScale(2, BigDecimal.ROUND_HALF_UP); }
         */
        if (require.getTaxRateValue() == null) {
            return waybill.getEstimateFreight();
        }
        BigDecimal rate = require.getTaxRateValue().add(new BigDecimal(1));
        return waybill.getEstimateFreight().divide(rate, 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public BigDecimal getFrightForCost(WaybillParam waybillParam, WaybillParam oldParam, LoginUser loginUser) throws BusinessException {
        if (null == waybillParam) {
            return BigDecimal.ZERO;
        }

        Waybill waybill = waybillService.getWaybill(waybillParam.getWaybillId());
        if (null == waybill) {
            return BigDecimal.ZERO;
        }

        BigDecimal driverHandlingCost = waybillParam.getDriverHandlingCost();
        BigDecimal laborerHandlingCost = waybillParam.getLaborerHandlingCost();

        BigDecimal cost = (waybill.getCalculatedFreight())
                .add(driverHandlingCost == null ? BigDecimal.ZERO : driverHandlingCost)
                .add(laborerHandlingCost == null ? BigDecimal.ZERO : laborerHandlingCost);
        if (null != oldParam) {
            BigDecimal oldDriverHandlingCost = oldParam.getDriverHandlingCost();
            BigDecimal oldLaborerHandlingCost = oldParam.getLaborerHandlingCost();
            cost = cost.subtract(oldDriverHandlingCost == null ? BigDecimal.ZERO : oldDriverHandlingCost)
                    .subtract(oldLaborerHandlingCost == null ? BigDecimal.ZERO : oldLaborerHandlingCost);
        }

        TruckRequire require = truckRequireService.findTruckRequireByWaybillId(waybillParam.getWaybillId(), loginUser);
        if (null == require || null == require.getTaxRateValue()) {
            return cost;
        }

        PriceAfterTaxProxy proxy = getAfterTaxFrightProxy(require, waybill, loginUser);
        TaxRateInfo taxRateInfo = proxy.getTaxRateInfo();
        if (null != taxRateInfo && taxRateInfo.isFlag()) {
            proxy.setWaybillParamInfo(waybillParam, oldParam, proxy.getTaxRateInfo().getRate());
            proxy.setFinalPrice(waybill.getCalculatedFreight());
            kSession.insert(proxy);
            kSession.fireAllRules();
            BigDecimal price = proxy.getFinalAfterTaxPrice();
            return price == null ? cost : price.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return cost;
    }

    /**
     * 获取需要计算费用的附加功能与税费
     * 
     * @param require
     * @param freightId
     * @return
     */
    private Map<String, Object> getAffMap(TruckRequire require, Integer freightId, LoginUser loginUser) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<AdditionalFunction> list = additionalFunctionService.getAllAdditionalFunction();
        for (AdditionalFunction function : list) {
            AdditionalFunctionFreight freight = additionalFunctionFreightService.getAddFuncFreightByTypeAF(freightId, function.getAdditionalFunctionId());
            if (null != freight) {
                map.put(function.getFunctionKey(), freight);
            }
        }
        TaxRate rate = taxRateService.findTaxRateBy(require.getTaxRateValue(), loginUser);
        if (null != rate) {
            map.put(Constants.TAX_RATE, rate);
        }
        log.info("车型{},附加功能费用:{}.", freightId, map);
        return map;
    }

    // 启用
    @Override
    public void updateToEnable(Integer freightId, LoginEmployee loginEmployee) throws BusinessException {
        enableOrDisable(freightId, false, loginEmployee.getUserId());
    }

    // 禁用
    @Override
    public void updateToDisable(Integer freightId, LoginEmployee loginEmployee) throws BusinessException {
        enableOrDisable(freightId, true, loginEmployee.getUserId());

    }

    // 启用或停用
    private void enableOrDisable(Integer freightId, boolean flag, Integer userId) {
        TruckTypeFreight truckTypeFreight = new TruckTypeFreight();
        truckTypeFreight.setFreightId(freightId);
        truckTypeFreight.setDelete(flag);
        truckTypeFreight.setLastUpdateUserId(userId);
        truckTypeFreightDao.update(truckTypeFreight);
        TruckTypeFreight typeFreight = truckTypeFreightDao.get(freightId);
        if (null != typeFreight) {
            AdditionalFunctionFreight freight = new AdditionalFunctionFreight();
            freight.setDelete(flag);
            freight.setLastUpdateUserId(userId);
            freight.setFreightId(freightId);
            additionalFunctionFreightService.update(freight);
        }

    }

    // 得到确定车型id获得附加功能信息并保存到AdditionalFunctionFreightBo中
    private AdditionalFunctionFreightBo additionalFunction(Integer freightId) {
        AdditionalFunctionFreightBo bo = new AdditionalFunctionFreightBo();
        AdditionalFunctionFreight addFreight = new AdditionalFunctionFreight();
        addFreight.setFreightId(freightId);
        List<AdditionalFunctionFreight> list = additionalFunctionFreightService.findAddFuncFreightByFreightId(freightId);
        if (CollectionUtils.isNotEmpty(list)) {
            for (AdditionalFunctionFreight addfunctionFreight : list) {
                AdditionalFunction additionFunction = additionalFunctionService.getAdditionFunction(addfunctionFreight.getAdditionalFunctionId());
                // 根据附加功能ID获取附加功能信息
                if (null != additionFunction) {
                    String functionKey = additionFunction.getFunctionKey();

                    // 属性值
                    if (AdditionalFunction.FunctionKeys.COLD_CHAIN.toString().equals(functionKey)) {
                        BigDecimal freight = addfunctionFreight.getLowestFreight();
                        bo.setColdChainFreight(decomalFmt(freight, AdditionalFunction.FunctionKeys.COLD_CHAIN));
                    } else if (AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT.toString().equals(functionKey)) {
                        bo.setCollectionPaymentFreight(decomalFmt(addfunctionFreight.getLowestFreight(), null));
                    } else if (AdditionalFunction.FunctionKeys.ENTRY_LICENSE.toString().equals(functionKey)) {
                        bo.setEntryLicenseFreight(decomalFmt(addfunctionFreight.getLowestFreight(), null));
                    } else if (AdditionalFunction.FunctionKeys.NEED_BACK_STORAGE.toString().equals(functionKey)) {
                        bo.setBackStorageFreight(decomalFmt(addfunctionFreight.getLowestFreight(), null));
                    } else if (AdditionalFunction.FunctionKeys.NEED_CARRY.toString().equals(functionKey)) {
                        bo.setCarryFreight(decomalFmt(addfunctionFreight.getLowestFreight(), null));
                    } else if (AdditionalFunction.FunctionKeys.NEED_RECEIPT.toString().equals(functionKey)) {
                        bo.setReceiptFreight(decomalFmt(addfunctionFreight.getLowestFreight(), null));
                    } else if (AdditionalFunction.FunctionKeys.DRIVER_CARRY.toString().equals(functionKey)) {
                        bo.setDriverHandlingCost(decomalFmt(addfunctionFreight.getLowestFreight(), null));
                    } else if (AdditionalFunction.FunctionKeys.LABORER_CARRY.toString().equals(functionKey)) {
                        bo.setLaborerHandlingCost(decomalFmt(addfunctionFreight.getLowestFreight(), null));
                    }
                }
            }
        }
        return bo;
    }

    // 把TruckTypeFreightBo中的AdditionalFunctionFreightBo属性转为List
    private List<AdditionalFunctionFreight> addfreightToList(TruckTypeFreight truckTypeFreight, AdditionalFunctionFreightBo additionalFunctionFreightBo) {
        List<AdditionalFunctionFreight> list = new ArrayList<AdditionalFunctionFreight>();
        if (null != additionalFunctionFreightBo && null != truckTypeFreight) {
            Integer freightId = truckTypeFreight.getFreightId();
            BigDecimal coldChainFreight = additionalFunctionFreightBo.getColdChainFreight();
            if (null != coldChainFreight) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, coldChainFreight.divide(new BigDecimal("100")), AdditionalFunction.FunctionKeys.COLD_CHAIN);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }
            BigDecimal collectionPaymentFreight = additionalFunctionFreightBo.getCollectionPaymentFreight();
            if (null != collectionPaymentFreight) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, collectionPaymentFreight, AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }
            BigDecimal backStorageFreight = additionalFunctionFreightBo.getBackStorageFreight();
            if (null != backStorageFreight) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, backStorageFreight, AdditionalFunction.FunctionKeys.NEED_BACK_STORAGE);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }
            BigDecimal receiptFreight = additionalFunctionFreightBo.getReceiptFreight();
            if (null != receiptFreight) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, receiptFreight, AdditionalFunction.FunctionKeys.NEED_RECEIPT);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }
            BigDecimal carryFreight = additionalFunctionFreightBo.getCarryFreight();
            if (null != carryFreight) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, carryFreight, AdditionalFunction.FunctionKeys.NEED_CARRY);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }

            BigDecimal driverHandlingCost = additionalFunctionFreightBo.getDriverHandlingCost();
            if (null != driverHandlingCost) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, driverHandlingCost, AdditionalFunction.FunctionKeys.DRIVER_CARRY);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }

            BigDecimal laborerHandlingCost = additionalFunctionFreightBo.getLaborerHandlingCost();
            if (null != laborerHandlingCost) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, laborerHandlingCost, AdditionalFunction.FunctionKeys.LABORER_CARRY);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }

            BigDecimal entryLicenseFreight = additionalFunctionFreightBo.getEntryLicenseFreight();
            if (null != entryLicenseFreight) {
                AdditionalFunctionFreight addfreight = transforFreight(freightId, entryLicenseFreight, AdditionalFunction.FunctionKeys.ENTRY_LICENSE);
                if (null != addfreight) {
                    list.add(addfreight);
                }
            }
        }
        return list;
    }

    private AdditionalFunctionFreight transforFreight(Integer freightId, BigDecimal freight, FunctionKeys fKeys) {
        AdditionalFunction additionalFunction = additionalFunctionService.findAdditionFunctionByKey(fKeys.toString());
        if (null != additionalFunction) {
            AdditionalFunctionFreight addfreight = new AdditionalFunctionFreight();
            addfreight.setAdditionalFunctionId(additionalFunction.getAdditionalFunctionId());
            addfreight.setFreightId(freightId);
            addfreight.setLowestFreight(freight);
            return addfreight;
        }
        return null;
    }

    // 保留两位小时
    private BigDecimal decomalFmt(BigDecimal num, FunctionKeys keys) {
        if (null != num) {
            if (AdditionalFunction.FunctionKeys.COLD_CHAIN.equals(keys)) {
                num = num.multiply(new BigDecimal("100")).setScale(2);
            } else {
                num = num.setScale(2);
            }
        }
        return num;
    }

    @Override
    public List<TruckTypeFreight> findByRegionCodeAndTypeId(Integer cityManageId, Integer truckTypeId) {
        TruckTypeFreight truckTypeFreight = new TruckTypeFreight();
        truckTypeFreight.setCityManageId(cityManageId);
        truckTypeFreight.setTruckTypeId(truckTypeId);
        return truckTypeFreightDao.findByExample(truckTypeFreight);
    }
}
