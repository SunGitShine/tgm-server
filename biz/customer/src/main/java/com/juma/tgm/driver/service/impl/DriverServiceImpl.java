/**
 *
 */
package com.juma.tgm.driver.service.impl;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vencent.lu
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Resource
    private WaybillService waybillService;
    @Resource
    private TruckService truckService;
    @Resource
    private TruckTypeService truckTypeService;
    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public Driver getDriver(Integer driverId) {
        if (null == driverId) {
            return null;
        }
        return this.buildDriver(vmsCommonService.loadDriverByDriverId(driverId));
    }

    @Override
    public Driver getDriverAndCheckExist(Integer driverId) {
        Driver driver = getDriver(driverId);
        if (driver == null) {
            throw new BusinessException("driverNotfound", "driver.error.not.found");
        }
        return driver;
    }

    @Override
    public Driver findDriverByAmsDriverId(Integer amsDriverId) {
        if (null == amsDriverId) {
            return null;
        }

        return this.buildDriver(vmsCommonService.loadDriverByAmsDriverId(amsDriverId));
    }

    @Override
    public Driver findAllDriverByAmsDriverId(Integer amsDriverId) {
        if (null == amsDriverId) {
            return null;
        }

        return this.buildDriver(vmsCommonService.loadDriverByAmsDriverId(amsDriverId));
    }

    @Override
    public Driver findDriverByAmsDriverIdAndCheckExist(Integer amsDriverId) throws BusinessException {
        Driver driver = findDriverByAmsDriverId(amsDriverId);
        if (null == driver) {
            throw new BusinessException("driverNotfound", "driver.error.not.found");
        }
        return driver;
    }

    @Override
    public Driver findDriverByUserId(Integer userId) {
        if (null == userId) {
            return null;
        }

        return this.buildDriver(vmsCommonService.loadDriverByUserId(userId));
    }

    @Override
    public Driver findAllDriverByUserId(Integer userId) {
        if (null == userId) {
            return null;
        }

        return this.buildDriver(vmsCommonService.loadDriverByUserId(userId));
    }

    @Override
    public Driver findDriverByPhone(String contactPhone) {
        if (StringUtils.isBlank(contactPhone)) {
            return null;
        }

        return this.buildDriver(vmsCommonService.loadDriverByPhone(contactPhone));
    }

    @Override
    public Driver findAllDriverByPhone(String contactPhone) {
        if (StringUtils.isBlank(contactPhone)) {
            return null;
        }

        return this.buildDriver(vmsCommonService.loadDriverByPhone(contactPhone));
    }

    @Override
    public List<Driver> listDriverByName(String nickname) {
        List<Driver> result = new ArrayList<>();
        if (StringUtils.isBlank(nickname)) {
            return new ArrayList<Driver>();
        }
        List<com.juma.vms.driver.domain.Driver> list = vmsCommonService.listDriverByName(nickname);
        for (com.juma.vms.driver.domain.Driver drvier : list) {
            result.add(this.buildDriver(drvier));
        }

        return result;
    }

    @Override
    public List<Driver> findDriverIdListByStatus(int status) {
        return new ArrayList<>();
//        Driver example = new Driver();
//        example.setStatus(status);
//        example.setIsDelete(false);
//        return driverDao.findByExample(example);
    }

    @Override
    public void insert(Driver driver, LoginUser loginUser) {
//        if (null != loginUser) {
//            driver.setCreateUserId(loginUser.getUserId());
//        }
//        driver.setCreateTime(new Date());
//        driverDao.insert(driver);
    }

    @Override
    public void update(Driver driver, LoginUser loginUser) {
//        if (null != loginUser) {
//            driver.setLastUpdateUserId(loginUser.getUserId());
//        }
//        driver.setLastUpdateTime(new Date());
//        driverDao.update(driver);
    }

    @Override
    public void delete(Integer driverId, LoginUser loginUser) {
//        Driver driver = new Driver();
//        driver.setDriverId(driverId);
//        if (null != loginUser) {
//            driver.setLastUpdateUserId(loginUser.getUserId());
//        }
//        driver.setLastUpdateTime(new Date());
//        driverDao.delete(driver);
    }

    @Override
    public void updateDriverTaskStatus(Integer driverId, Driver.TaskStatus status) {
//        Driver driver = new Driver();
//        driver.setDriverId(driverId);
//        driver.setStatus(status.getCode());
//        driverDao.update(driver);
    }

    @Override
    public void updateDriverWhetherAcceptAllocateOrder(Driver driver, LoginUser loginUser) {
        vmsCommonService.updateDriverIsAcceptAllocateOrder(driver.getDriverId(), driver.getWhetherAcceptAllocateOrder(), loginUser);
    }

    @Override
    public void checkDriverAcceptAllocateOrder(Integer driverId, LoginUser loginUser) throws BusinessException {
        Driver driver = getDriver(driverId);
        if (driver == null) {
            throw new BusinessException("driverNotfound", "driver.error.not.found");
        }

        if (null == driver.getWhetherAcceptAllocateOrder()
                || driver.getWhetherAcceptAllocateOrder().compareTo(Driver.AcceptAllocateOrders.BUSY.getCode()) == 0) {
            throw new BusinessException("driverBusy", "driver.error.driverBusy");
        }

        com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByDriverId(driverId, loginUser);
        if (truck == null) {
            throw new BusinessException("truckNobindDriver", "truck.nobind.driver");
        }
    }

    @Override
    public List<Driver> listByAreaCodeLike(Integer tenantId, String areaCode, Integer driverType) {
        List<Driver> result = new ArrayList<Driver>();
        if (null == tenantId || StringUtils.isBlank(areaCode)) {
            return result;
        }

        List<String> areaCodeList = new ArrayList<>();
        areaCodeList.add(areaCode);


        List<com.juma.vms.driver.domain.Driver> list = vmsCommonService.listDriverBy(areaCodeList, driverType, Integer.MAX_VALUE, new LoginUser(tenantId, 1));

        if (CollectionUtils.isEmpty(list)) {
            return result;
        }

        for (com.juma.vms.driver.domain.Driver vmsDriver : list) {
            Driver driver = new Driver();
            driver.setAmsDriverId(vmsDriver.getAmsDriverId());
            driver.setNickname(vmsDriver.getName());
            driver.setContactPhone(vmsDriver.getPhone());
            driver.setType(vmsDriver.getDriverRunType() == null ? 0
                    : vmsDriver.getDriverRunType().intValue());
            driver.setUserId(vmsDriver.getUserId());
            result.add(driver);
        }
        return result;
    }

    @Override
    public DriverTruckInfoBo findDriverTruckInfoByWaybillId(Integer waybillId) {
        DriverTruckInfoBo result = new DriverTruckInfoBo();
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return result;
        }

        // 得到司机信息
        com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
        if (null != driver) {
            result.setDeriverId(driver.getDriverId());
            result.setDervicerName(driver.getName());
            result.setContactPhone(driver.getPhone());
            result.setDeriverStatus(0);
            result.setHeader(driver.getIcon());
            result.setIsAcceptAllocateOrders(driver.getIsReceiveWaybill() == null ? 0 : (driver.getIsReceiveWaybill() ? 1 : 0));
            result.setStarLevel("");
        }

        // 得到货车信息
        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (null != truck) {
            result.setTruckId(truck.getTruckId());
            result.setPlateNumber(truck.getPlateNumber());
            // 得到车型信息
            result.setTruckTypeName(
                    truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()));
            result.setEntryLicense(truck.getEntryLicense());
        }
        return result;
    }

    private Driver buildDriver(com.juma.vms.driver.domain.Driver vmsDriver) {
        if (null == vmsDriver) {
            return null;
        }

        Driver driver = new Driver();
        driver.setDriverId(vmsDriver.getDriverId());
        driver.setUserId(vmsDriver.getUserId());
        driver.setAmsDriverId(vmsDriver.getAmsDriverId());
        driver.setNickname(vmsDriver.getName());
        driver.setContactPhone(vmsDriver.getPhone());
        driver.setHeadPortrait(vmsDriver.getIcon());
        driver.setWhetherAcceptAllocateOrder(vmsDriver.getIsReceiveWaybill() == null ?
                Driver.AcceptAllocateOrders.BUSY.getCode() : (vmsDriver.getIsReceiveWaybill() ?
                Driver.AcceptAllocateOrders.FREE.getCode() : Driver.AcceptAllocateOrders.BUSY.getCode()));
        driver.setSmsRemindSwitch(vmsDriver.getSmsRemindSwitch() == null ? 0 : (vmsDriver.getSmsRemindSwitch() ? 1 : 0));
        driver.setTelRemindSwitch(vmsDriver.getTelRemindSwitch() == null ? 0 : (vmsDriver.getTelRemindSwitch() ? 1 : 0));
        return driver;
    }
}
