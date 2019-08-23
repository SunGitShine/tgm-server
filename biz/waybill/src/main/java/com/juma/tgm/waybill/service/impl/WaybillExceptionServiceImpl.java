package com.juma.tgm.waybill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillBo;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillExceptionService;
import com.juma.tgm.waybill.service.WaybillService;

@Service
public class WaybillExceptionServiceImpl implements WaybillExceptionService{

    @Autowired
    private WaybillService waybillService;
    @Autowired
    private WaybillCommonService waybillCommonService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Page<WaybillBo> searchDetails(PageCondition pageCondition, LoginEmployee loginEmployee) {
        structPageCondition(pageCondition);
        List<WaybillBo> result = new ArrayList<WaybillBo>();
        
        // 只可见本业务区域的非分享运单和其他业务区域的分享运单，不可见本业务区域的分享运单
        pageCondition.getFilters().put("ownerAreaSahreCanNotSee", true);
        Page<Waybill> page = waybillService.search(loginEmployee, pageCondition);
        for (Waybill waybill : page.getResults()) {
            WaybillBo bo = new WaybillBo();
            bo.setWaybill(waybill);
            User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginEmployee);
            if (null != user) {
                bo.setCustomerManagePhone(user.getMobileNumber());
            }
            
            if (StringUtils.isBlank(bo.getWaybillRemark())) {
                bo.setWaybillRemark("超时未分配");
            }
            result.add(bo);
        }
        return new Page<WaybillBo>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    // 构造异常订单查询条件
    private void structPageCondition (PageCondition pageCondition) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters) {
            filters = new HashMap<String, Object>();
        }
        filters.put("createTime", DateUtils.addMinutes(new Date(), -15));
        filters.put("status", Waybill.Status.WATING_RECEIVE.getCode());
        pageCondition.setFilters(filters);
    }

    @Override
    public void updateReason(int waybillId, String reason, LoginUser loginUser) throws BusinessException {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        waybill.setWaybillRemark(reason);
        waybill.setLastUpdateUserId(loginUser.getUserId());
        waybillCommonService.update(waybill, loginUser);
    }
}
