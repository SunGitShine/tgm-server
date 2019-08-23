package com.juma.tgm.waybillReport.service;

import java.util.List;
import java.util.Map;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybillReport.domain.OfflineWaybill;
import com.juma.tgm.waybillReport.domain.OfflineWaybillResponse;

/**
 * 
 * @ClassName:   OfflineWaybillService   
 * @Description: 线下订单导入
 * @author:      Administrator
 * @date:        2017年2月8日 上午11:17:02  
 *
 * @Copyright:   2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface OfflineWaybillService {

    /**
     * 
     * @Title:       search   
     * @Description: 自己只能看自己导入的数据
     * @param:       @param pageCondition
     * @param:       @param loginUser
     * @param:       @return      
     * @return:      Page<OfflineWaybill>      
     * @throws  
     */
    Page<OfflineWaybill> search(PageCondition pageCondition, LoginUser loginUser);
    
    /**
     * 
     * @Title:       handleOfflineWaybill
     * @Description: 处理线下数据
     * @param:       @param rows
     * @return:      void      
     * @throws
     */
    OfflineWaybillResponse handleOfflineWaybill(List<OfflineWaybill> rows, boolean checkProjectStatus, LoginEmployee loginEmployee);
    
    /**
     * 
     * @Title:       transferToWaybill   
     * @Description: 线下订单入运单表 
     * @param:       @param offlineWaybillIds      
     * @return:      void      
     * @throws
     */
    int transferToWaybill(String departmentCode,List<Integer> offlineWaybillIds,LoginUser loginUser) throws BusinessException;
    
    /**
     * 
     * @Title: templateFieldMapping   
     * @Description: 
     * @param: @param is
     * @param: @return      
     * @return: List<OfflineWaybill>      
     * @throws
     */
    Map<String,String> templateFieldMapping(Integer templateId);
    
    /**
     * 
     * @Title:       deleteByIds   
     * @Description: 批量删除
     * @param:       @param offlineWaybillIds      
     * @return:      void      
     * @throws
     */
    void deleteByIds(List<Integer> offlineWaybillIds);
}
