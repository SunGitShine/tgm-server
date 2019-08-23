package com.juma.tgm.configure.service.impl;

import java.util.ArrayList;
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
import com.juma.tgm.configure.dao.PackFreightRuleMapper;
import com.juma.tgm.configure.domain.PackFreightRule;
import com.juma.tgm.configure.domain.PackFreightRuleExample;
import com.juma.tgm.configure.service.PackFreightRuleService;
import com.juma.tgm.truck.service.TruckTypeService;

/**
 * @ClassName PackFreightRuleServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月27日 上午11:42:24
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
@Service
public class PackFreightRuleServiceImpl implements PackFreightRuleService {

    @Resource
    private PackFreightRuleMapper packFreightRuleMapper;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private RegionService regionService;

    @Override
    public PackFreightRule findByRegionAndTruckType(String regionCode, Integer truckTypeId, LoginUser loginUser) {

        PackFreightRuleExample example = new PackFreightRuleExample();
        example.createCriteria().andTenantIdEqualTo(loginUser.getTenantId()).andTruckTypeIdEqualTo(truckTypeId)
                .andRegionCodeEqualTo(regionCode);

        List<PackFreightRule> rows = packFreightRuleMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

    @Override
    public Page<PackFreightRule> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<PackFreightRule>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<PackFreightRule>());
        }
        PackFreightRuleExample example = new PackFreightRuleExample();
        Paging paging = new Paging(pageCondition, "create_time", null);
        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());
        example.setOrderByClause(paging.getOrder());

        example.createCriteria().andTenantIdEqualTo(loginUser.getTenantId());

        long total = packFreightRuleMapper.countByExample(example);
        List<PackFreightRule> list = packFreightRuleMapper.selectByExample(example);
        return new Page<PackFreightRule>(pageCondition.getPageNo(), pageCondition.getPageSize(), (int) total, list);
    }

    @Override
    public PackFreightRule getPackFreightRule(Integer packFreightRuleId) {
        return packFreightRuleMapper.selectByPrimaryKey(packFreightRuleId);
    }

    @Override
    public void insert(PackFreightRule packFreightRule, LoginUser loginUser) throws BusinessException {
        packFreightRule.setTenantId(loginUser.getTenantId());
        packFreightRule.setTenantCode(loginUser.getTenantCode());
        packFreightRule.setCreateUserId(loginUser.getUserId());
        packFreightRule.setIsEnable(true);
        check(packFreightRule, loginUser);

        Region region = regionService.findByRegionCode(packFreightRule.getRegionCode());
        if (null != region) {
            packFreightRule.setRegionName(region.getRegionName());
        }
        packFreightRule.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(packFreightRule.getTruckTypeId()));

        packFreightRuleMapper.insertSelective(packFreightRule);
    }

    @Override
    public void update(PackFreightRule packFreightRule, LoginUser loginUser) throws BusinessException {
        PackFreightRule rule = getPackFreightRule(packFreightRule.getPackFreightRuleId());
        if (null == rule) {
            return;
        }
        packFreightRule.setTenantId(rule.getTenantId());
        packFreightRule.setRegionCode(rule.getRegionCode());
        check(packFreightRule, loginUser);

        packFreightRule.setTruckTypeName(truckTypeService.findTruckTypeNameByTypeId(packFreightRule.getTruckTypeId()));
        packFreightRule.setLastUpdateUserId(loginUser.getUserId());
        packFreightRuleMapper.updateByPrimaryKeySelective(packFreightRule);
    }

    @Override
    public void updateToEnable(Integer packFreightRuleId, LoginUser loginUser) throws BusinessException {
        PackFreightRule rule = new PackFreightRule();
        rule.setPackFreightRuleId(packFreightRuleId);
        rule.setIsEnable(true);
        rule.setLastUpdateUserId(loginUser.getUserId());
        packFreightRuleMapper.updateByPrimaryKeySelective(rule);
    }

    @Override
    public void updateToDisable(Integer packFreightRuleId, LoginUser loginUser) throws BusinessException {
        PackFreightRule rule = new PackFreightRule();
        rule.setPackFreightRuleId(packFreightRuleId);
        rule.setIsEnable(false);
        rule.setLastUpdateUserId(loginUser.getUserId());
        packFreightRuleMapper.updateByPrimaryKeySelective(rule);
    }

    private void check(PackFreightRule packFreightRule, LoginUser loginUser) {
        if (null == packFreightRule.getTenantId()) {
            throw new BusinessException("userTenantCodeNotExist", "errors.userTenantCodeNotExist");
        }

        if (StringUtils.isBlank(packFreightRule.getRegionCode())) {
            throw new BusinessException("regionCodeRequired", "errors.regionCodeRequired");
        }

        if (packFreightRule.getRegionCode().length() != 5) {
            throw new BusinessException("regionCodeMustCity", "errors.regionCodeMustCity");
        }

        if (null == packFreightRule.getTruckTypeId()) {
            throw new BusinessException("truckTypeIdRequired", "errors.truckTypeIdRequired");
        }

        PackFreightRule rule = findByRegionAndTruckType(packFreightRule.getRegionCode(),
                packFreightRule.getTruckTypeId(), loginUser);

        if (null == rule) {
            return;
        }

        if (null == packFreightRule.getPackFreightRuleId()
                || !packFreightRule.getPackFreightRuleId().equals(rule.getPackFreightRuleId())) {
            throw new BusinessException("regionCodeAndTruckTypeHasExist",
                    "packFreightRule.error.regionCodeAndTruckTypeHasExist");
        }
    }
}
