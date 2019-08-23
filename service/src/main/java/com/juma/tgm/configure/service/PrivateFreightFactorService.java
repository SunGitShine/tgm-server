package com.juma.tgm.configure.service;

import java.math.BigDecimal;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.PrivateFreightContext;
import com.juma.tgm.configure.domain.PrivateFreightFactor;
import com.juma.tgm.configure.domain.TenantFreightFactor;

public interface PrivateFreightFactorService {
    
    /**
     * 
     * @Title: calFreight   
     * @Description: 计算价格
     * @param: @param regionCode  城市
     * @param: @param freightWay   计价方式
     * @param: @param truckTypeId   车型
     * @param: @param user
     * @param: @return      
     * @return: BigDecimal      
     * @throws
     */
    BigDecimal calFreight(String regionCode,Integer freightWay,Integer truckTypeId,PrivateFreightContext context,LoginUser user) throws BusinessException;
    
    /**
     * 
     * @Title: calFreightForProject   
     * @Description: 项目计价  
     * @param: @return      
     * @return: BigDecimal      
     * @throws
     */
    BigDecimal calFreightForProject(String paramJson,String ruleJson);

    /**
     * 
     * @Title: saveTenantFreightFactor   
     * @Description: 租户对应的计价维度
     * @param: @param domain
     * @param: @param user
     * @param: @throws BusinessException      
     * @return: void      
     * @throws
     */
    void saveTenantFreightFactor(TenantFreightFactor domain,LoginUser user) throws BusinessException;
    
    /**
     * 
     * @Title: findByFreightWay   
     * @Description: 计价方式
     * @param: @param freightWay
     * @param: @param user
     * @param: @return      
     * @return: TenantFreightFactor      
     * @throws
     */
    TenantFreightFactor findByFreightWay(Integer freightWay,LoginUser user);
    
    /**
     * 
     * @Title: findByPrimaryKey   
     * @Description: 计价方式
     * @param: @param privateFreightFactorId
     * @param: @return      
     * @return: PrivateFreightFactor      
     * @throws
     */
    PrivateFreightFactor findByPrimaryKey(Integer privateFreightFactorId);
    
    /**
     * 
     * @Title: save   
     * @Description: 添加或保存 
     * @param: @param domain
     * @param: @param user
     * @param: @throws BusinessException      
     * @return: void      
     * @throws
     */
    void save(PrivateFreightFactor domain,LoginUser user) throws BusinessException;
    
    /**
     * 
     * @Title: disable   
     * @Description: 禁用   
     * @param: @param phaseFreightListId
     * @param: @param user      
     * @return: void      
     * @throws
     */
    void disable(Integer privateFreightFactorId,LoginUser user) throws BusinessException;
    
    Page<PrivateFreightFactor> getPager(String regionCode,Integer freightWay,Integer truckTypeId,PageCondition condition,LoginUser user);
    
}
