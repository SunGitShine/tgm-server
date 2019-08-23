package com.juma.tgm.project.service.impl;

import com.juma.conf.domain.ConfParamOption;
import com.juma.tgm.common.Constants;
import com.juma.tgm.tools.service.AuthCommonService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.FreightEnum;
import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.configure.service.FreightFactorService;
import com.juma.tgm.project.dao.RoadMapPriceRuleMapper;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.RoadMapPriceRule;
import com.juma.tgm.project.domain.RoadMapPriceRuleExample;
import com.juma.tgm.project.domain.ValuationWay;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.project.service.RoadMapPriceRuleService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.project.vo.RoadMapPriceRuleVo;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;

@Service
public class RoadMapPriceRuleServiceImpl implements RoadMapPriceRuleService {

    @Resource
    private RoadMapPriceRuleMapper roadMapPriceRuleMapper;
    @Resource
    private RoadMapService roadMapService;
    @Resource
    private FreightFactorService freightFactorService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private AuthCommonService authCommonService;

    @Override
    public RoadMapPriceRule getRoadMapPriceModel(Integer roadMapPriceRuleId) {
        if (null == roadMapPriceRuleId) {
            return null;
        }

        return roadMapPriceRuleMapper.selectByPrimaryKey(roadMapPriceRuleId);
    }

    @Override
    public void insert(RoadMapPriceRule roadMapPriceRule, LoginUser loginUser) throws BusinessException {
        roadMapPriceRule.setCreateUserId(loginUser == null ? null : loginUser.getUserId());
        roadMapPriceRule.setCreateTime(new Date());
        roadMapPriceRuleMapper.insert(roadMapPriceRule);
    }

    @Override
    public void batchInsert(Integer roadMapId, List<RoadMapPriceRule> listRoadMapPriceRule, LoginUser loginUser)
            throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        this.check(listRoadMapPriceRule, roadMapId);

        for (RoadMapPriceRule r : listRoadMapPriceRule) {
            r.setRoadMapId(roadMapId);
            this.insert(r, loginUser);
        }
    }

    @Override
    public void batchUpdate(Integer roadMapId, List<RoadMapPriceRule> listRoadMapPriceRule, LoginUser loginUser)
            throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        this.check(listRoadMapPriceRule, roadMapId);

        this.deleteByRoadMapId(roadMapId, loginUser);
        for (RoadMapPriceRule r : listRoadMapPriceRule) {
            r.setRoadMapId(roadMapId);
            this.insert(r, loginUser);
        }
    }

    // 校验基础信息
    private void check(List<RoadMapPriceRule> listRoadMapPriceRule, Integer roadMapId) {
        if (CollectionUtils.isEmpty(listRoadMapPriceRule)) {
            throw new BusinessException("roadMapPriceRequred", "roadMapPriceRule.error.roadMapPriceRequred");
        }

        RoadMap roadMap = roadMapService.getRoadMap(roadMapId);

        // 计数器
        int temp = 1;
        // 已存在计价方式
        Integer firstValuationWay = null;
        // 已存在的车型集合
        List<Integer> listTruckTypsId = new ArrayList<Integer>();
        for (RoadMapPriceRule r : listRoadMapPriceRule) {

            if (null == r.getTruckTypeId()) {
                throw new BusinessException("truckTypeRequred", "roadMapPriceRule.error.truckTypeRequred", temp);
            }

            if (listTruckTypsId.contains(r.getTruckTypeId())) {
                throw new BusinessException("truckTypeExist", "roadMapPriceRule.error.truckTypeExist", temp);
            }
            listTruckTypsId.add(r.getTruckTypeId());

            if (null == r.getValuationWay()) {
                throw new BusinessException("valuationWayRequred", "roadMapPriceRule.error.valuationWayRequred", temp);
            }

            if (temp == 1) {
                firstValuationWay = r.getValuationWay().intValue();
            }

            if (NumberUtils.compare(firstValuationWay, r.getValuationWay().intValue()) != 0) {
                throw new BusinessException("theSameRoadMapValuationWayMustEqual",
                        "roadMapPriceRule.error.theSameRoadMapValuationWayMustEqual");
            }

            if (StringUtils.isBlank(r.getValuationModelJson())) {
                throw new BusinessException("valuationModelJsonRequred",
                        "roadMapPriceRule.error.valuationModelJsonRequred", temp);
            }

            Map<String, Object> factorMap = JSON.parseObject(r.getValuationModelJson(), Map.class);
            if (null == factorMap || factorMap.isEmpty()) {
                throw new BusinessException("valuationModelJsonRequred",
                        "roadMapPriceRule.error.valuationModelJsonRequred", temp);
            }

            for (Entry<String, Object> entry : factorMap.entrySet()) {
                Object value = entry.getValue();
                if (!BaseUtil.isNumOrDecimal(value.toString())) {
                    String errorMsg = "";
                    if (null != roadMap && StringUtils.isNotBlank(roadMap.getName())) {
                        errorMsg = errorMsg + "路线名称【" + roadMap.getName() + "】";
                    }
                    errorMsg = errorMsg + "第" + temp;
                    throw new BusinessException("priceRuleMustNumberType",
                            "roadMapPriceRule.error.priceRuleMustNumberType", errorMsg);
                }

                BigDecimal decimal = new BigDecimal(value.toString());
                if (decimal.compareTo(BigDecimal.ZERO) == -1 || decimal.compareTo(new BigDecimal("999999.99")) == 1) {
                    throw new BusinessException("priceRuleOutOfRange", "roadMapPriceRule.error.priceRuleOutOfRange",
                            temp);
                }

                // 价格上限控制
                List<ConfParamOption> options = authCommonService.listOption(Constants.TMS_FREIGHT_WITH_TAX_UPPER_LIMIT_KEY);
                for (ConfParamOption c : options) {
                    if (StringUtils.isBlank(c.getOptionValue())) {
                        continue;
                    }

                    if (decimal.compareTo(new BigDecimal(c.getOptionValue())) == 1) {
                        throw new BusinessException("roadMapPriceTooUpper", "errors.common.prompt", "第" + temp +
                            "条客户计价有超出上限的价格信息，上限价格：" + c.getOptionValue());
                    }
                }

            }

            temp++;
        }
    }

    @Override
    public void delete(Integer roadMapPriceRuleId, LoginUser loginUser) throws BusinessException {
        if (null == roadMapPriceRuleId) {
            return;
        }

        roadMapPriceRuleMapper.deleteByPrimaryKey(roadMapPriceRuleId);
    }

    @Override
    public void deleteByRoadMapId(Integer roadMapId, LoginUser loginUser) throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        RoadMapPriceRuleExample example = new RoadMapPriceRuleExample();
        example.createCriteria().andRoadMapIdEqualTo(roadMapId);
        roadMapPriceRuleMapper.deleteByExample(example);
    }

    @Override
    public List<RoadMapPriceRule> listByRoadMapId(Integer roadMapId) {
        if (null == roadMapId) {
            return new ArrayList<RoadMapPriceRule>();
        }

        RoadMapPriceRuleExample example = new RoadMapPriceRuleExample();
        example.createCriteria().andRoadMapIdEqualTo(roadMapId);
        List<RoadMapPriceRule> roadMapPriceRules = roadMapPriceRuleMapper.selectByExample(example);

        for(RoadMapPriceRule roadMapPriceRule : roadMapPriceRules){
            Integer truckTypeId = roadMapPriceRule.getTruckTypeId();
            TruckType truckType = truckTypeService.getTruckType(truckTypeId);
            String truckTypeName = truckTypeService.findTruckTypeNameBy(truckType.getVehicleBoxType(), truckType.getTruckLengthId());
			roadMapPriceRule.setTruckTypeName(truckTypeName);
        }

        return roadMapPriceRules;
    }

    @Override
    public RoadMapPriceRule findByRoadMapIdAndTypeId(Integer roadMapId, Integer truckTypeId) {
        if (null == roadMapId || null == truckTypeId) {
            return null;
        }

        RoadMapPriceRuleExample example = new RoadMapPriceRuleExample();
        example.createCriteria().andRoadMapIdEqualTo(roadMapId).andTruckTypeIdEqualTo(truckTypeId);
        List<RoadMapPriceRule> list = roadMapPriceRuleMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<ValuationWay> buildValuationWays(String json) {
        List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();
        if (StringUtils.isBlank(json)) {
            return valuationWays;
        }

        Map<String, FreightFactor> allFactorMap = freightFactorService.mapInputNameToValue();
        Map<String, String> map = JSON.parseObject(json, new TypeReference<HashMap<String, String>>() {
        });
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            ValuationWay valuationWay = new ValuationWay();
            if (allFactorMap.containsKey(key)) {
                FreightFactor freightFactor = allFactorMap.get(key);
                if(freightFactor != null) {
                    valuationWay.setLabelName(freightFactor.getLabelName());// 名字
                    valuationWay.setRequiredMinValue(freightFactor.getRequiredMinValue());
                    valuationWay.setRequiredMaxValue(freightFactor.getRequiredMaxValue());
                }
            }
            valuationWay.setLabelInputName(entry.getKey());
            valuationWay.setValue(entry.getValue());
            valuationWays.add(valuationWay);
        }
        return valuationWays;
    }

    @Override
    public List<RoadMapPriceRuleVo> buidBeanName(List<RoadMapPriceRule> roadMapPriceRules, LoginUser loginUser) throws BusinessException{
        if(null == roadMapPriceRules){
            return new ArrayList<RoadMapPriceRuleVo>();
        }
        List<RoadMapPriceRuleVo> roadMapPriceRuleVos = new ArrayList<RoadMapPriceRuleVo>();
        // 计费方式
        List<FreightFactor> freightFactors = freightFactorService.findByFreightWay(FreightEnum.PROJECT.getCode(),loginUser);
        if (freightFactors.isEmpty()) {
            return null;
        }
        for (RoadMapPriceRule r : roadMapPriceRules) {
            RoadMapPriceRuleVo rVo = new RoadMapPriceRuleVo();
            BeanUtils.copyProperties(r,rVo);
            String factorJson = rVo.getValuationModelJson();
            Map<String, Object> map = JSON.parseObject(factorJson, Map.class);
            Map allTruckType = getAllTruckType(loginUser);
            rVo.setTruckName(String.valueOf(allTruckType.get(rVo.getTruckTypeId())));
            if(rVo.getValuationWay() == ValuationWayEnum.WORKLOAD.getCode()){
                List<ValuationWay> valuationWays = getValuationWays(freightFactors, map);
                rVo.setValuationWays(valuationWays);
            }else if(rVo.getValuationWay() == ValuationWayEnum.FIXED_PRICE.getCode()){
                rVo.setEstimateFreight(new BigDecimal(map.get("estimateFreight").toString()));
            }
            roadMapPriceRuleVos.add(rVo);
        }
        return roadMapPriceRuleVos;
    }

    //构造计价规则
    private List<ValuationWay> getValuationWays(List<FreightFactor> freightFactors,Map<String, Object> map) {
        List<ValuationWay> valuationWays = new ArrayList<ValuationWay>();
        for (FreightFactor freightFactor : freightFactors) {
            if (null == map.get(freightFactor.getLabelInputName())) {
                continue;
            }
            ValuationWay vo = new ValuationWay();
            vo.setLabelInputName(freightFactor.getLabelInputName());
            vo.setLabelName(freightFactor.getLabelName());
            vo.setValue(map.get(freightFactor.getLabelInputName()).toString());
            if (StringUtils.isNotBlank(freightFactor.getLabelInputName())
                    && freightFactor.getLabelInputName().equals("initiateRate")) {
                valuationWays.add(0, vo);
            } else {
                valuationWays.add(vo);
            }
        }
        return valuationWays;
    }

    //获取所有的车型
    private Map getAllTruckType(LoginUser loginUser) {
        Map map = new HashMap();
        List<TruckType> truckTypes = truckTypeService.listAllTruckTypeByOrderNoAsc(loginUser.getTenantId(),null);
        for (TruckType t : truckTypes) {
            map.put(t.getTruckTypeId(), t.getTruckTypeName());
        }
        return map;
    }

}
