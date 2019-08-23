package com.juma.tgm.configure.service;

import java.util.List;
import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.domain.FreightFactor;

public interface FreightFactorService {

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
    void save(FreightFactor domain,LoginUser user) throws BusinessException;
    
    /**
     * 
     * @Title: disable   
     * @Description: 禁用
     * @param: @param freightFactorId
     * @param: @param user
     * @param: @throws BusinessException      
     * @return: void      
     * @throws
     */
    void disable(Integer freightFactorId,LoginUser user) throws BusinessException;
    
    List<FreightFactor> findAll();
    
    Map<String,FreightFactor> mapInputNameToValue();
    
    Page<FreightFactor> getPager(Integer freightWay,PageCondition condition,LoginUser user);
    
    List<FreightFactor> findByFreightWay(Integer freightWay,LoginUser user);
}
