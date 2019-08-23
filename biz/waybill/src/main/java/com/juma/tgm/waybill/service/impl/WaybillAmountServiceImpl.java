package com.juma.tgm.waybill.service.impl;

import com.giants.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.tgm.common.Constants;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.waybill.dao.WaybillAmountMapper;
import com.juma.tgm.waybill.domain.AmountStatus;
import com.juma.tgm.waybill.domain.WaybillAmount;
import com.juma.tgm.waybill.domain.WaybillAmountExample;
import com.juma.tgm.waybill.domain.WaybillAmountValidVO;
import com.juma.tgm.waybill.domain.vo.WaybillAmountFilter;
import com.juma.tgm.waybill.service.WaybillAmountService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.mybatis.generator.my.mapper.Mapper;
import org.mybatis.generator.my.service.AbstractMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 运单费用表->基本功能
 * 功能 :
 * 1.插入
 * 2.更新
 * @author : Bruce(刘正航) 20:22 2019-05-13
 */
@Service
public class WaybillAmountServiceImpl extends AbstractMybatisService<WaybillAmount, WaybillAmountExample> implements WaybillAmountService {

    @Autowired
    private WaybillAmountMapper waybillAmountMapper;

    @Autowired
    private AuthCommonService authCommonService;

    @Override
    public Mapper<WaybillAmount, WaybillAmountExample> getMapper() {
        return waybillAmountMapper;
    }

    @Override
    public void add(WaybillAmount amount, LoginUser loginUser) throws BusinessException {
        // 检查并设置默认值
        amount.setCustomerFreightWithTax(
            amount.getCustomerFreightWithTax() == null ? BigDecimal.ZERO : amount.getCustomerFreightWithTax());
        amount.setVendorFreightWithTax(
            amount.getVendorFreightWithTax() == null ? BigDecimal.ZERO : amount.getVendorFreightWithTax());
        amount.setLastCustomerFreightWithTax(
            amount.getLastCustomerFreightWithTax() == null ? BigDecimal.ZERO : amount.getLastCustomerFreightWithTax());
        amount.setLastVendorFreightWithTax(
            amount.getLastVendorFreightWithTax() == null ? BigDecimal.ZERO : amount.getLastVendorFreightWithTax());
        amount.setCreateUserId(loginUser.getUserId());
        amount.setCreateTime(new Date());
        insert(amount);
    }

    @Override
    public void addBatch(final List<WaybillAmount> amounts, final LoginUser loginUser) throws BusinessException {
        if( CollectionUtils.isEmpty(amounts) ){
            return;
        }
        for (WaybillAmount amount : amounts) {
            amount.setAmountStatus(AmountStatus.UNCONFIRM.getCode());
            amount.setCreateUserId(loginUser.getUserId());
            amount.setCreateTime(new Date());
        }
        insertBatch(amounts);
    }

    @Override
    public void update(WaybillAmount amount, LoginUser loginUser) throws BusinessException {
        amount.setCreateUserId(loginUser.getUserId());
        amount.setCreateTime(new Date());
        updateByPrimaryKeySelective(amount);
    }

    @Override
    public void updateBatch(List<WaybillAmount> amounts, LoginUser loginUser) throws BusinessException {
        if (CollectionUtils.isEmpty(amounts)) {
            return;
        }

        updateBatchByPrimaryKeySelective(amounts);
    }

    @Override
    public void updateByWaybillId(WaybillAmount amount, LoginUser loginUser) throws BusinessException {
        WaybillAmountExample example = new WaybillAmountExample().createCriteria()
                .andWaybillIdEqualTo(amount.getWaybillId())
                .example();
        List<WaybillAmount> amounts = selectByExample(example);
        if(CollectionUtils.isEmpty(amounts)){
            return;
        }
        updateByPrimaryKeySelective(amounts.get(0));
    }

    @Override
    public WaybillAmount loadByWaybillId(Integer waybillId) throws BusinessException {
        WaybillAmountExample example = new WaybillAmountExample().createCriteria()
                .andWaybillIdEqualTo(waybillId)
                .example();
        List<WaybillAmount> amounts = selectByExample(example);
        if( CollectionUtils.isEmpty(amounts) ){
            return null;
        }
        return amounts.get(0);
    }

    @Override
    public List<WaybillAmount> findByFilter(final WaybillAmountFilter filter, LoginUser loginUser) throws BusinessException {
        // 所有条件为空,则返回空集合
        if( null == filter.getAmountStatus()
                && null == filter.getWaybillId()
                && CollectionUtils.isEmpty(filter.getWaybillIds()) ){
            return Lists.newArrayList();
        }
        WaybillAmountExample example = new WaybillAmountExample().createCriteria()
                .andAmountStatusEqualTo(filter.getAmountStatus())
                .andWaybillIdEqualTo(filter.getWaybillId())
                .andWaybillIdIn(filter.getWaybillIds())
                .example();
        return waybillAmountMapper.selectByExample(example);
    }

    @Override
    public List<WaybillAmount> findByWaybillIds(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException {
        if( CollectionUtils.isEmpty(waybillIds) ){
            return Lists.newArrayList();
        }
        WaybillAmountFilter filter = new WaybillAmountFilter();
        filter.setWaybillIds(waybillIds);
        return findByFilter(filter,loginUser);
    }

    @Override
    public Map<Integer, WaybillAmount> findDictionaryByWaybillIds(List<Integer> waybillIds) throws BusinessException {
        List<WaybillAmount> waybillAmounts = findByWaybillIds(waybillIds,null);
        Map<Integer, WaybillAmount> waybillAmountMap = Maps.newConcurrentMap();
        for (WaybillAmount waybillAmount : waybillAmounts){
            waybillAmountMap.put(waybillAmount.getWaybillId(),waybillAmount);
        }
        return waybillAmountMap;
    }

    @Override
    public void addOrUpdate(WaybillAmount waybillAmount, LoginUser loginUser) throws BusinessException {
        if (null == waybillAmount || null == waybillAmount.getWaybillId()) {
            return;
        }

        WaybillAmount amount = this.loadByWaybillId(waybillAmount.getWaybillId());
        if (null == amount) {
            waybillAmount.setAmountStatus(waybillAmount.getAmountStatus() == null ? AmountStatus.UNCONFIRM.getCode(): waybillAmount.getAmountStatus());
            waybillAmount.setLastCustomerFreightWithTax(waybillAmount.getCustomerFreightWithTax());
            waybillAmount.setLastVendorFreightWithTax(waybillAmount.getVendorFreightWithTax());
            this.add(waybillAmount, loginUser);
            return;
        }

        waybillAmount.setWaybillAmountId(amount.getWaybillAmountId());
        this.update(waybillAmount, loginUser);
    }

    @Override
    public void addOrUpdateBatch(List<WaybillAmount> amounts, LoginUser loginUser) throws BusinessException {
        if (CollectionUtils.isEmpty(amounts)) {
            return;
        }

        List<WaybillAmount> amountsNeedAdd = new ArrayList<>();
        List<WaybillAmount> amountsNeedUpdate = new ArrayList<>();
        for (WaybillAmount w : amounts) {
            if (null == w.getWaybillId()) {
                continue;
            }

            WaybillAmount amount = this.loadByWaybillId(w.getWaybillId());
            if (null == amount) {
                w.setLastCustomerFreightWithTax(w.getCustomerFreightWithTax());
                w.setLastVendorFreightWithTax(w.getVendorFreightWithTax());
                w.setCreateUserId(loginUser.getUserId());
                w.setCreateTime(new Date());
                amountsNeedAdd.add(w);
                continue;
            }

            w.setWaybillAmountId(amount.getWaybillAmountId());
            amountsNeedUpdate.add(w);
        }

        this.addBatch(amountsNeedAdd, loginUser);
        this.updateBatch(amountsNeedUpdate, loginUser);
    }

    @Override
    public WaybillAmountValidVO getWaybillAmountLimit() throws BusinessException {
        String ceilingAmount = fetchConstants(Constants.ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT_KEY,Constants.ALLOW_WAYBILL_CHANGE_PRICE_CEILING_LIMIT.toString());
        Double floorRate = fetchPercentage(Constants.ALLOW_WAYBILL_GROSS_PROFIT_FLOOR,Constants.ALLOW_WAYBILL_GROSS_PROFIT_FLOOR_DEFAULT);
        Double ceilingRate = fetchPercentage(Constants.ALLOW_WAYBILL_GROSS_PROFIT_CEILING,Constants.ALLOW_WAYBILL_GROSS_PROFIT_CEILING_DEFAULT);
        WaybillAmountValidVO validVO = new WaybillAmountValidVO();
        validVO.setWaybillAmountCeiling(new BigDecimal(ceilingAmount));
        validVO.setGrossProfitRateFloor(BigDecimal.valueOf(floorRate));
        validVO.setGrossProfitRateCeiling(BigDecimal.valueOf(ceilingRate));
        return validVO;
    }

    private String fetchConstants(String key, String defaultValue) {
        String timeLimit = defaultValue;
        List<ConfParamOption> options = authCommonService.listOption(key);
        for (ConfParamOption c : options) {
            if (StringUtils.isBlank(c.getOptionValue()) || !NumberUtils.isNumber(c.getOptionValue())) {
                continue;
            }

            timeLimit = c.getOptionValue();
        }
        return timeLimit;
    }

    private Double fetchPercentage(String key, String defaultValue) {
        List<ConfParamOption> options = authCommonService.listOption(key);
        NumberFormat nf= NumberFormat.getPercentInstance();
        for (ConfParamOption option : options) {
            String optionValue = option.getOptionValue();
            if (StringUtils.isBlank(optionValue)) {
                continue;
            }
            try {
                Number optionAmount = nf.parse(optionValue);
                return optionAmount.doubleValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Number optionAmount = nf.parse(defaultValue);
            return optionAmount.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.valueOf(0.2);
    }

    private String formatPercentage(Double value){
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(3);
        num.setMaximumFractionDigits(2);
        return num.format(value);
    }
}
