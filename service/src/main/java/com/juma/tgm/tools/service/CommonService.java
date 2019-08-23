package com.juma.tgm.tools.service;

import java.util.List;

import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.domain.TruckDriverGroup;
import com.juma.tgm.waybill.domain.WaybillOwner;

public interface CommonService {

    /**
     * 
     * @Title:       findLineAreaByCode   
     * @Description:向上父节点
     * @return:      List<String>      
     * @throws  
     */
    List<String> findLineAreaByCode(String areaCode,LoginUser loginUser);
    
    /**
     * 
     * @Title:       findAvailableFlightByPage   
     * @Description: 可用班次
     * @return:      Page<FlightBo>      
     * @throws
     */
    @Deprecated
    List<TruckDriverGroup> findAvailableFlightByPage(Integer waybillId,PageCondition condition, LoginEmployee loginEmployee);
    
    
    /**
     * 
     * @Title:       listCustomerManager   
     * @Description: 客户经理列表 
     * @return:      List<CustomerManager>      
     * @throws
     */
    List<WaybillOwner> listCustomerManager(LoginEmployee loginEmployee);
    
    
    
    /**
     * 
     * @Title:       findCustomerManager   
     * @Description: 客户经理 
     * @return:      CustomerManager      
     * @throws
     */
    WaybillOwner findCustomerManager(Integer employeeUserId,LoginUser loginUser);

    /**
     * 
     * 组装登录员工登录信息
     * 
     * @author Libin.Wei
     * @Date 2017年9月14日 下午5:53:45
     * @return
     */
    LoginEmployee buildTopLoginEmployee(LoginUser loginUser);

}
