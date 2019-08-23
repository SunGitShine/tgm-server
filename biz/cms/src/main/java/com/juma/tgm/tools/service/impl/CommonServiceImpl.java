package com.juma.tgm.tools.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.lang.exception.CategoryCodeFormatException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.juma.auth.authority.service.AuthorityService;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.employee.domain.*;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.tgm.common.Constants;
import com.juma.tgm.tools.service.CommonService;
import com.juma.tgm.waybill.domain.TruckDriverGroup;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillOwner;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Resource
    private AuthorityService authorityService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    @Override
    public List<String> findLineAreaByCode(String areaCode,LoginUser loginUser) {

        List<BusinessArea> areaList = null;
        try {
            if (StringUtils.isBlank(areaCode)) {
                return new ArrayList<String>();
            }
            areaList = authorityService.findAllLevelsBusinessArea(areaCode, loginUser);
        } catch (BusinessException e) {
            log.warn(e.getMessage(), e);
        } catch (CategoryCodeFormatException e) {
            log.warn(e.getMessage(), e);
        }
        if (areaList == null)
            return Collections.emptyList();
        List<String> areaCodeList = new ArrayList<String>();
        for (BusinessArea area : areaList) {
            areaCodeList.add(area.getAreaCode());
        }
        return areaCodeList;
    }

    @Override
    public List<TruckDriverGroup> findAvailableFlightByPage(Integer waybillId, PageCondition condition,
            LoginEmployee loginEmployee) {

        Waybill waybill = waybillService.getWaybill(waybillId);
        if (waybill == null || condition == null)
            return Collections.emptyList();
        condition.setPageNo(1);
        condition.setPageSize(Integer.MAX_VALUE);
        condition.getFilters().put("areaCodeLike", waybill.getAreaCode());
        condition.getFilters().put("startTime", waybill.getPlanDeliveryTime());
        condition.getFilters().put("endTime", new Date(waybill.getPlanDeliveryTime().getTime()
                + waybill.getEstimateTimeConsumption() * 60 * 1000 + 60 * 60 * 1000));
//        Page<FlightBo> page = amsServiceV2.getAvailableFlightByPage(condition, loginEmployee);
//        if (page == null || page.getResults() == null)
//            return Collections.emptyList();
//        List<TruckDriverGroup> result = new ArrayList<TruckDriverGroup>();
//        for (FlightBo flight : page.getResults()) {
//            Truck truck = truckService.findTruckByVehicleId(flight.getVehicleId());
//            Driver driver = driverService.findDriverByAmsDriverId(flight.getDriverId());
//            if (truck != null && driver != null) {
//                TruckDriverGroup group = new TruckDriverGroup(truck, driver);
//                result.add(group);
//            }
//        }
        return new ArrayList<>();
    }

    @Override
    public List<WaybillOwner> listCustomerManager(LoginEmployee loginEmployee) {

        EmployeeFilter filter = new EmployeeFilter();

        PageQueryCondition<EmployeeFilter> pageQueryCondition = new PageQueryCondition<EmployeeFilter>(filter);

        Page<EmployeeInfo> p = employeeService.searchEmployees(pageQueryCondition, Constants.AUTH_KEY_TGM_MANAGE,
                Constants.CUSTOMER_MANAGER_PERMISSION_KEY, loginEmployee);

        List<WaybillOwner> result = new ArrayList<WaybillOwner>();

        for (EmployeeInfo employeeInfo : p.getResults()) {
            WaybillOwner customerManager = new WaybillOwner();
            customerManager.setEmployeeId(employeeInfo.getEmployeeId());
            customerManager.setUserId(employeeInfo.getUserId());
            customerManager.setNickname(employeeInfo.getName());
            customerManager.setContactPhone(employeeInfo.getMobileNumber());
            result.add(customerManager);
        }

        return result;
    }

    @Override
    public WaybillOwner findCustomerManager(Integer employeeId, LoginUser loginUser) {

        User user = employeeService.loadUserByEmployeeId(employeeId, loginUser);

        WaybillOwner owner = new WaybillOwner();

        if (user != null) {
            owner.setEmployeeId(employeeId);
            owner.setContactPhone(user.getMobileNumber());
            owner.setNickname(user.getName());
            owner.setUserId(user.getUserId());
        }
        return owner;
    }

    @Override
    public LoginEmployee buildTopLoginEmployee(LoginUser loginUser) {
        // 获取员工信息
        Employee employee = employeeService.loadEmployeeByUserId(loginUser.getUserId(),
                loginUser);
        // 获取部门信息
        Department department = departmentService.findDepartment(Constants.DEFAULT_DEPARTMENT_CODE,
                loginUser);

        // 组装登录人信息
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setEmployeeId(employee.getEmployeeId());
        loginEmployee.setUserId(employee.getUserId());
        loginEmployee.setTenantId(employee.getTenantId());
        loginEmployee.setTenantCode(employee.getTenantCode());
        loginEmployee.initLoginDepartment(department, null);

        return loginEmployee;
    }
}
