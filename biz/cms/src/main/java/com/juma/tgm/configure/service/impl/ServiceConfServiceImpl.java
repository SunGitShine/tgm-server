package com.juma.tgm.configure.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.domain.Region;
import com.juma.conf.service.ConfParamService;
import com.juma.conf.service.RegionService;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.configure.dao.ServiceConfMapper;
import com.juma.tgm.configure.domain.ServiceConf;
import com.juma.tgm.configure.domain.ServiceConfExample;
import com.juma.tgm.configure.domain.ServiceConfExample.Criteria;
import com.juma.tgm.configure.service.ServiceConfService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ServiceConfServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年11月27日 上午11:46:13
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class ServiceConfServiceImpl implements ServiceConfService {

    @Resource
    private ServiceConfMapper serviceConfMapper;
    @Resource
    private RegionService regionService;
    @Resource
    private ConfParamService confParamService;

    @Override
    public ServiceConf findByRegionCode(String regionCode, LoginUser loginUser) {
        if (StringUtils.isBlank(regionCode) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        ServiceConfExample example = new ServiceConfExample();
        example.createCriteria().andTenantIdEqualTo(loginUser.getTenantId()).andRegionCodeEqualTo(regionCode);

        List<ServiceConf> rows = serviceConfMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

    @Override
    public Page<ServiceConf> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<ServiceConf>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<ServiceConf>());
        }

        ServiceConfExample example = new ServiceConfExample();
        Paging paging = new Paging(pageCondition, "create_time", null);
        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());
        example.setOrderByClause(paging.getOrder());

        Criteria criteria = example.createCriteria().andTenantIdEqualTo(loginUser.getTenantId());
        if (null != pageCondition.getFilters() && null != pageCondition.getFilters().get("regionCode")) {
            criteria.andRegionCodeLike(pageCondition.getFilters().get("regionCode").toString() + "%");
        }

        long total = serviceConfMapper.countByExample(example);
        List<ServiceConf> list = serviceConfMapper.selectByExample(example);
        return new Page<ServiceConf>(pageCondition.getPageNo(), pageCondition.getPageSize(), (int) total, list);

    }

    @Override
    public ServiceConf getServiceConf(int serviceConfId) {
        return serviceConfMapper.selectByPrimaryKey(serviceConfId);
    }

    @Override
    public void insert(ServiceConf serviceConf, LoginUser loginUser) throws BusinessException {
        serviceConf.setTenantId(loginUser.getTenantId());
        serviceConf.setTenantCode(loginUser.getTenantCode());
        check(serviceConf);

        // 获取城市名称
        Region region = regionService.findByRegionCode(serviceConf.getRegionCode());
        if (null != region) {
            serviceConf.setRegionName(region.getRegionName());
        }

        // 获取省的信息
        Region parentRegion = regionService.findByRegionId(region.getParentRegionId());
        if (null != parentRegion) {
            serviceConf.setParentRegionCode(parentRegion.getRegionCode());
            serviceConf.setParentRegionName(parentRegion.getRegionName());
        }

        serviceConf.setCreateUserId(loginUser.getUserId());
        serviceConf.setCreateTime(new Date());
        serviceConfMapper.insertSelective(serviceConf);
    }

    @Override
    public void update(ServiceConf serviceConf, LoginUser loginUser) throws BusinessException {
        if (null != loginUser) {
            serviceConf.setLastUpdateUserId(loginUser.getUserId());
        }
        serviceConf.setLastUpdateTime(new Date());
        serviceConfMapper.updateByPrimaryKeySelective(serviceConf);
    }

    private void check(ServiceConf serviceConf) {
        if (StringUtils.isBlank(serviceConf.getRegionCode())) {
            throw new BusinessException("regionCodeRequired", "errors.regionCodeRequired");
        }

        if (serviceConf.getRegionCode().length() != 5) {
            throw new BusinessException("regionCodeMustCity", "errors.regionCodeMustCity");
        }

        ServiceConfExample example = new ServiceConfExample();
        example.createCriteria().andTenantIdEqualTo(serviceConf.getTenantId())
                .andRegionCodeEqualTo(serviceConf.getRegionCode());
        List<ServiceConf> list = serviceConfMapper.selectByExample(example);
        if (!list.isEmpty()) {
            throw new BusinessException("regionCodeHasExist", "serviceConf.error.regionCodeHasExist");
        }
    }

    @Override
    public String findCustomerServiceTel(String regionCode, LoginUser loginUser) {
        ServiceConf serviceConf = findByRegionCode(regionCode, loginUser);
        if (serviceConf == null || serviceConf.getCustomerServiceTel() == null || StringUtils.isBlank(serviceConf.getCustomerServiceTel())) {
            List<ConfParamOption> options = confParamService.findParamOptions("CUSTOMER_SERVICE_TEL");
            if (!options.isEmpty()) {
                return options.get(0).getOptionValue();
            }
            return null;
        } else {
            return serviceConf.getCustomerServiceTel();
        }
    }

    @Override
    public List<ServiceConf> listServiceConf(LoginUser loginUser) {
        ServiceConfExample example = new ServiceConfExample();
        example.createCriteria().andTenantIdEqualTo(loginUser.getTenantId());
        return serviceConfMapper.selectByExample(example);
    }
}
