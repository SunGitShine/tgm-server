package com.juma.tgm.configure.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.Region;
import com.juma.conf.service.RegionService;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.configure.dao.FreightRuleMapper;
import com.juma.tgm.configure.domain.FreightRule;
import com.juma.tgm.configure.domain.FreightRuleExample;
import com.juma.tgm.configure.domain.FreightRuleExample.Criteria;
import com.juma.tgm.configure.service.FreightRuleService;
import com.juma.tgm.truck.service.TruckTypeService;

/**
 * @ClassName LandingDistributionFreightServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月16日 下午2:48:12
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class FreightRuleServiceImpl implements FreightRuleService {

    @Resource
    private FreightRuleMapper freightRuleMapper;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private RegionService regionService;

    @Override
    public Page<FreightRule> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<FreightRule>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<FreightRule>());
        }

        FreightRuleExample example = new FreightRuleExample();
        Paging paging = new Paging(pageCondition, "freight_rule_id", null);
        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());
        example.setOrderByClause(paging.getOrder());

        Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId());
        if (null != pageCondition.getFilters() && null != pageCondition.getFilters().get("regionCode")) {
            criteria.andRegionCodeLike(pageCondition.getFilters().get("regionCode").toString() + "%");
        }

        long total = freightRuleMapper.countByExample(example);
        List<FreightRule> list = freightRuleMapper.selectByExample(example);

        return new Page<FreightRule>(pageCondition.getPageNo(), pageCondition.getPageSize(), (int) total, list);

    }

    @Override
    public FreightRule getFreightRule(Integer freightRuleId) {
        return freightRuleMapper.selectByPrimaryKey(freightRuleId);
    }

    @Override
    public void insert(FreightRule freightRule, LoginUser loginUser) throws BusinessException {
        freightRule.setTenantCode(loginUser.getTenantCode());
        freightRule.setTenantId(loginUser.getTenantId());
        freightRule.setCreateTime(new Date());
        freightRule.setCreateUserId(loginUser.getUserId());
        freightRule.setIsEnable(true);
        check(freightRule, loginUser);

        Region region = regionService.findByRegionCode(freightRule.getRegionCode());
        if (null != region) {
            freightRule.setRegionName(region.getRegionName());
        }

        freightRule.setOverVolumeUnit(
                freightRule.getOverVolumeUnit() == null ? BigDecimal.ZERO : freightRule.getOverVolumeUnit());
        freightRule.setOverWeightUnit(
                freightRule.getOverWeightUnit() == null ? BigDecimal.ZERO : freightRule.getOverWeightUnit());

        freightRuleMapper.insert(freightRule);
    }

    @Override
    public void update(FreightRule freightRule, LoginUser loginUser) throws BusinessException {
        freightRule.setOverVolumeUnit(
                freightRule.getOverVolumeUnit() == null ? BigDecimal.ZERO : freightRule.getOverVolumeUnit());
        freightRule.setOverWeightUnit(
                freightRule.getOverWeightUnit() == null ? BigDecimal.ZERO : freightRule.getOverWeightUnit());
        freightRule.setLastUpdateTime(new Date());
        freightRule.setLastUpdateUserId(loginUser.getUserId());
        freightRuleMapper.updateByPrimaryKeySelective(freightRule);
    }

    @Override
    public void updateToEnable(Integer freightRuleId, LoginUser loginUser) throws BusinessException {
        FreightRule freightRule = new FreightRule();
        freightRule.setFreightRuleId(freightRuleId);
        freightRule.setIsEnable(true);
        freightRule.setLastUpdateTime(new Date());
        freightRule.setLastUpdateUserId(loginUser.getUserId());
        freightRuleMapper.updateByPrimaryKeySelective(freightRule);
    }

    @Override
    public void updateToDisable(Integer freightRuleId, LoginUser loginUser) throws BusinessException {
        FreightRule freightRule = new FreightRule();
        freightRule.setFreightRuleId(freightRuleId);
        freightRule.setIsEnable(false);
        freightRule.setLastUpdateTime(new Date());
        freightRule.setLastUpdateUserId(loginUser.getUserId());
        freightRuleMapper.updateByPrimaryKeySelective(freightRule);
    }

    private void check(FreightRule freightRule, LoginUser loginUser) {
        if (null == freightRule.getTenantId()) {
            throw new BusinessException("userTenantCodeNotExist", "errors.userTenantCodeNotExist");
        }

        if (StringUtils.isBlank(freightRule.getRegionCode())) {
            throw new BusinessException("regionCodeRequired", "errors.regionCodeRequired");
        }

        if (freightRule.getRegionCode().length() != 5) {
            throw new BusinessException("regionCodeMustCity", "errors.regionCodeMustCity");
        }

        // 校验城市
        FreightRule rule = findByRegionCode(freightRule.getRegionCode(), loginUser);

        if (null == rule) {
            return;
        }

        if (null == freightRule.getFreightRuleId() || !freightRule.getFreightRuleId().equals(rule.getFreightRuleId())) {
            throw new BusinessException("regionCodeHasExist", "freightRule.error.regionCodeHasExist");
        }
    }

    @Override
    public FreightRule findByRegionCode(String regionCode, LoginUser loginUser) {
        FreightRuleExample example = new FreightRuleExample();
        Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId());
        criteria.andRegionCodeEqualTo(regionCode);
        List<FreightRule> rows = freightRuleMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }
}
