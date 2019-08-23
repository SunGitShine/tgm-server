package com.juma.tgm.select.service;

import java.util.List;

import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.vo.Page;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.customer.domain.vo.CustomerInfoFilter;
import com.juma.tgm.select.vo.*;

public interface SelectService {

    /**
     * 运力列表
     * @param filter
     * @param loginUser
     * @return
     */
    Page<Capacity> pageOfCapacity(CapacityFilter filter, Integer backPageSize, LoginUser loginUser);

    /**
     * 司机列表
     * @param filter
     * @param loginUser
     * @return
     */
    List<DriverSelect> pageOfDriver(DriverFilter filter, Integer backPageSize,LoginUser loginUser);

    /**
     * 车辆列表
     * @param filter
     * @param loginUser
     * @return
     */
    List<TruckSelect> pageOfTruck(TruckFilter filter, Integer backPageSize,LoginUser loginUser);

    /**
     * 承运商列表
     * @param filter
     * @param loginUser
     * @return
     */
    List<VendorSelect> pageOfVendor(VendorFilter filter, Integer backPageSize,LoginUser loginUser);

    /**
     * 客户列表
     * @param filter
     * @return
     */
    List<CustomerInfo> listCustomerInfo(CustomerInfoFilter filter, Integer backPageSize, LoginUser loginUser);

}
