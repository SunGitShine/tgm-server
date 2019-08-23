package com.juma.tgm.waybill.service;

import java.math.BigDecimal;
import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.TaxRate;

/**
 * 
 * @Description: 费率
 * @author weilibin
 * @date 2016年8月2日 上午10:37:37
 * @version V1.0
 */

public interface TaxRateService {

    /**
     * 
     * 分页查询
     * 
     * @author Libin.Wei
     * @Date 2018年7月4日 下午6:36:59
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<TaxRate> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * @Title: findOne
     * @Description: 根据ID获取一条
     * @param taxRateId
     * @return TaxRate
     */
    TaxRate getTaxRate(Integer taxRateId);

    /**
     * 
     * @Title: findAll
     * @Description: 获取所有
     * @return List<TaxRate>
     */
    List<TaxRate> loadByTenant(LoginUser loginUser);

    /**
     * 
     * @Title: getTaxRateValue
     * @Description: 获取费率
     * @param taxRateId
     * @return float
     */
    @Deprecated
    BigDecimal findTaxRateValueById(Integer taxRateId);

    /**
     * 
     * 根据税率查询
     * 
     * @author Libin.Wei
     * @Date 2018年7月4日 下午5:28:55
     * @param taxRateValue
     * @param loginUser
     * @return
     */
    TaxRate findTaxRateBy(BigDecimal taxRateValue, LoginUser loginUser);

    /**
     * 
     * 添加税率
     * 
     * @author Libin.Wei
     * @Date 2018年7月4日 下午5:21:27
     * @param taxRate
     * @param loginUser
     */
    void insert(TaxRate taxRate, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改税率
     * 
     * @author Libin.Wei
     * @Date 2018年7月4日 下午5:21:27
     * @param taxRate
     * @param loginUser
     */
    void update(TaxRate taxRate, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 禁用
     * 
     * @author Libin.Wei
     * @Date 2018年7月4日 下午5:21:27
     * @param taxRate
     * @param loginUser
     */
    void updateToDisable(Integer taxRateId, LoginUser loginUser) throws BusinessException;
    
    /**
     * 
     * 启用
     * 
     * @author Libin.Wei
     * @Date 2018年7月4日 下午5:21:27
     * @param taxRate
     * @param loginUser
     */
    void updateToEnable(Integer taxRateId, LoginUser loginUser) throws BusinessException;
}
