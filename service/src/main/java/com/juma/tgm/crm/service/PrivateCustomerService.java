package com.juma.tgm.crm.service;

import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.tgm.crm.domain.PrivateCustomer;

/**
 * 
 * @ClassName:   PrivateCustomerService   
 * @Description: 个人货主
 * @author:      Administrator
 * @date:        2017年3月13日 下午1:54:47  
 *
 * @Copyright:   2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface PrivateCustomerService {

    
    /**
     * 
     * @Title:       updatePrivateCustomer   
     * @Description: 更新
     * @param:       @param privateCustomerId      
     * @return:      void      
     * @throws
     */
    void updatePrivateCustomer(PrivateCustomer privateCustomer);
    
    /**
     * 
     * @Title:       searchDetails   
     * @Description: 个人货主分页  
     * @param:       @param pageCondition
     * @param:       @return      
     * @return:      Page<PrivateCustomer>      
     * @throws
     */
    Page<PrivateCustomer> searchDetails(PageCondition pageCondition);
    
    
    /**
     * 
     * @Title:       findPrivateCustomerById   
     * @Description: 个人货主详情
     * @param:       @param privateCustomerId
     * @param:       @return      
     * @return:      PrivateCustomer      
     * @throws
     */
    PrivateCustomer findPrivateCustomerById(Integer privateCustomerId);
    
}
