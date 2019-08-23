package com.juma.tgm.select.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.juma.tgm.common.vo.Page;
import com.juma.tgm.select.vo.*;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.vms.truck.service.TruckService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customer.domain.vo.CustomerInfoFilter;
import com.juma.tgm.select.service.SelectService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.driver.vo.DriverQuery;
import com.juma.vms.external.service.DriverExternalService;
import com.juma.vms.external.service.TruckExternalService;
import com.juma.vms.external.service.VendorExternalService;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.truck.domain.Truck;
import com.juma.vms.truck.external.TruckExternalFilter;
import com.juma.vms.truck.vo.TruckFilters;
import com.juma.vms.vendor.domain.Vendor;
import com.juma.vms.vendor.vo.VendorQuery;

@Service
public class SelectServiceImpl implements SelectService {

    @Resource
    private VmsCommonService vmsCommonService;

    @Resource
    private VmsService vmsService;

    @Resource
    private VendorExternalService vendorExternalService;

    @Resource
    private TruckExternalService truckExternalService;

    @Resource
    private TruckTypeService truckTypeService;

    @Resource
    private DriverExternalService driverExternalService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Override
    public Page<Capacity> pageOfCapacity(CapacityFilter filter, Integer backPageSize, LoginUser loginUser) {
        backPageSize = defaultBackSize(backPageSize);

        com.juma.vms.common.query.QueryCond<TruckFilters> queryCond = new com.juma.vms.common.query.QueryCond<>();
        queryCond.setPageNo(filter.getPageNo());
        queryCond.setPageSize(backPageSize);

        TruckFilters filters = new TruckFilters();
        filters.setTenantId(loginUser.getTenantId());
        filters.setIsDelete(false);
        filters.setStatus(filter.getStatus());
        filters.setPlateNumber(filter.getPlateNumber());
        filters.setVendorId(filter.getVendorId());
        filters.setDriverId(filter.getDriverId());
        filters.setTruckId(filter.getTruckId());
        queryCond.setFilters(filters);

        com.giants.common.tools.Page<CapacityPool> capacityPoolPage = vmsService.loadCapacityPoolByPlateNumber(queryCond);

        List<Capacity> capacityList = new ArrayList<>();
        for ( CapacityPool pool : capacityPoolPage.getResults() ) {
            Capacity capacity = new Capacity();
            capacity.setVendorId(pool.getVendorId());
            capacity.setDriverId(pool.getDriverId());
            capacity.setTruckId(pool.getTruckId());
            capacity.setIsValid(pool.getStatus());

            Vendor vendor = vmsService.loadByVenorId(pool.getVendorId());
            if (vendor != null) {
                capacity.setVendorName(vendor.getVendorName());
                capacity.setVendorContactPhone(vendor.getContactPhone());
            }
            Driver driver = vmsService.loadByDriverId(pool.getDriverId());
            if (driver != null) {
                capacity.setDriverName(driver.getName());
                capacity.setDriverContactPhone(driver.getPhone());
            }
            Truck truck = vmsService.loadByTruckId(pool.getTruckId());
            if (truck != null) {
                capacity.setPlateNumber(truck.getPlateNumber());
            }
            capacityList.add(capacity);
        }
        return  new Page<>(capacityPoolPage.getPageNo(),capacityPoolPage.getPageSize(),capacityPoolPage.getTotal(),capacityList);

    }

    @Override
    public List<DriverSelect> pageOfDriver(DriverFilter filter, Integer backPageSize, LoginUser loginUser) {
        backPageSize = defaultBackSize(backPageSize);
        List<DriverSelect> driverSelects = new ArrayList<>();
        DriverQuery driverQuery = new DriverQuery();
        driverQuery.setName(filter.getDriverName());
        driverQuery.setPhone(filter.getDriverContactPhone());
        List<DriverQuery> driverQueries = vmsService.listByDriver(driverQuery, backPageSize, loginUser);
        for (DriverQuery query : driverQueries) {
            DriverSelect driverSelect = new DriverSelect();
            driverSelect.setDriverId(query.getDriverId());
            driverSelect.setDriverName(query.getName());
            driverSelect.setDriverContactPhone(query.getPhone());
            driverSelects.add(driverSelect);
        }
        return driverSelects;
    }

    @Override
    public List<TruckSelect> pageOfTruck(TruckFilter filter, Integer backPageSize, LoginUser loginUser) {
        backPageSize = defaultBackSize(backPageSize);
        List<TruckSelect> truckSelects = new ArrayList<>();
        TruckExternalFilter truckExternalFilter = new TruckExternalFilter();
        truckExternalFilter.setPlateNumber(filter.getPlateNumber());
        List<Truck> trucks = vmsService.listTruckBy(truckExternalFilter, backPageSize, loginUser);
        for ( Truck truck : trucks ) {
            TruckSelect truckSelect = new TruckSelect();
            truckSelect.setTruckId(truck.getTruckId());
            truckSelect.setPlateNumber(truck.getPlateNumber());
            String truckTypeName = truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength());
            truckSelect.setTruckTypeName(truckTypeName);
            truckSelects.add(truckSelect);
        }
        return truckSelects;
    }

    @Override
    public List<VendorSelect> pageOfVendor(VendorFilter filter, Integer backPageSize, LoginUser loginUser) {
        backPageSize = defaultBackSize(backPageSize);
        List<VendorSelect> vendorSelects = new ArrayList<>();
        com.juma.vms.vendor.vo.VendorFilter vendorFilter = new com.juma.vms.vendor.vo.VendorFilter();
        vendorFilter.setVendorName(filter.getVendorName());
        vendorFilter.setContactPhone(filter.getVendorContactPhone());
        List<VendorQuery> vendorQueries = vmsService.listByVendorFilter(vendorFilter, backPageSize, loginUser);
        for ( VendorQuery vendorQuery : vendorQueries ) {
            VendorSelect vendorSelect = new VendorSelect();
            vendorSelect.setVendorId(vendorQuery.getVendorId());
            vendorSelect.setVendorName(vendorQuery.getVendorName());
            vendorSelect.setVendorContactPhone(vendorQuery.getContactPhone());
            vendorSelects.add(vendorSelect);
        }
        return vendorSelects;
    }

    @Override
    public List<CustomerInfo> listCustomerInfo(CustomerInfoFilter filter, Integer backPageSize, LoginUser loginUser) {

        backPageSize = defaultBackSize(backPageSize);
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(backPageSize);
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        pageCondition.getFilters().put("customerName", filter.getCustomerName());
        pageCondition.getFilters().put("customerType", filter.getCustomerType());
        pageCondition.getFilters().put("statusNotEquals", filter.getStatusNotEquals());
        pageCondition.getFilters().put("isDelete", 0);
        return customerInfoService.listCustomerInfo(pageCondition);
    }

    private Integer defaultBackSize(Integer backPageSize){
        return backPageSize == null ? 15
            : (NumberUtils.compare(backPageSize, 200) == 1 ? 200 : backPageSize);
    }
}
