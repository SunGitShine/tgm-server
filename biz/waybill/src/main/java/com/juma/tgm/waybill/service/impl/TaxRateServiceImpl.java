package com.juma.tgm.waybill.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.waybill.dao.TaxRateMapper;
import com.juma.tgm.waybill.domain.TaxRate;
import com.juma.tgm.waybill.domain.TaxRateExample;
import com.juma.tgm.waybill.domain.TaxRateExample.Criteria;
import com.juma.tgm.waybill.service.TaxRateService;

@Service
public class TaxRateServiceImpl implements TaxRateService {

    @Resource
    private TaxRateMapper taxRateMapper;

    @Override
    public Page<TaxRate> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<TaxRate>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<TaxRate>());
        }

        TaxRateExample example = new TaxRateExample();

        Paging paging = new Paging(pageCondition, "tax_rate_value", "asc");
        example.setOrderByClause(paging.getOrderBy() + " " + paging.getOrderSort());
        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());

        Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId());

        int total = taxRateMapper.countByExample(example);
        List<TaxRate> list = taxRateMapper.selectByExample(example);

        return new Page<TaxRate>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, list);
    }

    @Override
    public TaxRate getTaxRate(Integer taxRateId) {
        if (null == taxRateId) {
            return null;
        }
        return taxRateMapper.selectByPrimaryKey(taxRateId);
    }

    @Override
    public List<TaxRate> loadByTenant(LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<TaxRate>();
        }

        TaxRateExample example = new TaxRateExample();
        Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId());
        criteria.andIsDeleteEqualTo(false);
        example.setOrderByClause(" tax_rate_value asc ");
        return taxRateMapper.selectByExample(example);
    }

    @Override
    public BigDecimal findTaxRateValueById(Integer taxRateId) {
        TaxRate taxRate = getTaxRate(taxRateId);
        if (null == taxRate) {
            return BigDecimal.ZERO;
        }
        return taxRate.getTaxRateValue() == null ? BigDecimal.ZERO : taxRate.getTaxRateValue();
    }

    @Override
    public TaxRate findTaxRateBy(BigDecimal taxRateValue, LoginUser loginUser) {
        if (null == taxRateValue || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        TaxRateExample example = new TaxRateExample();
        Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId());
        criteria.andTaxRateValueEqualTo(taxRateValue);

        List<TaxRate> list = taxRateMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void insert(TaxRate taxRate, LoginUser loginUser) throws BusinessException {
        this.check(taxRate, loginUser);
        taxRate.setTenantId(loginUser.getTenantId());
        taxRate.setTenantCode(loginUser.getTenantCode());
        taxRate.setCreateUserId(loginUser.getUserId());
        taxRate.setIsDelete(false);
        taxRate.setCreateTime(new Date());
        taxRateMapper.insert(taxRate);
    }

    @Override
    public void update(TaxRate taxRate, LoginUser loginUser) throws BusinessException {
        this.check(taxRate, loginUser);
        TaxRate rate = this.getTaxRate(taxRate.getTaxRateId());
        rate.setTaxRateValue(taxRate.getTaxRateValue());
        rate.setTaxRateValueText(taxRate.getTaxRateValueText());
        rate.setLastUpdateTime(new Date());
        rate.setLastUpdateUserId(loginUser.getUserId());
        taxRateMapper.updateByPrimaryKey(rate);
    }


    // 校验
    public void check(TaxRate taxRate, LoginUser loginUser) {
        if (null == taxRate.getTaxRateValue()) {
            throw new BusinessException("taxRateValueRequied", "taxRate.error.taxRateValueRequied");
        }

        TaxRate rate = findTaxRateBy(taxRate.getTaxRateValue(), loginUser);
        if (null == rate) {
            return;
        }

        // 验证税率唯一性
        if (null == taxRate.getTaxRateId() || !rate.getTaxRateId().equals(taxRate.getTaxRateId())) {
            throw new BusinessException("taxRateValueExsit", "taxRate.error.taxRateValueExsit");
        }
    }

    @Override
    public void updateToDisable(Integer taxRateId, LoginUser loginUser) throws BusinessException {
        if (null == taxRateId) {
            return;
        }

        TaxRate taxRate = this.getTaxRate(taxRateId);
        taxRate.setIsDelete(true);
        taxRate.setLastUpdateTime(new Date());
        taxRate.setLastUpdateUserId(loginUser.getUserId());
        taxRateMapper.updateByPrimaryKey(taxRate);
    }

    @Override
    public void updateToEnable(Integer taxRateId, LoginUser loginUser) throws BusinessException {
        if (null == taxRateId) {
            return;
        }

        TaxRate taxRate = this.getTaxRate(taxRateId);
        taxRate.setIsDelete(false);
        taxRate.setLastUpdateTime(new Date());
        taxRate.setLastUpdateUserId(loginUser.getUserId());
        taxRateMapper.updateByPrimaryKey(taxRate);
    }
}
