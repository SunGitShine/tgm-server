package com.juma.tgm.truck.service.impl;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.server.vm.domain.Driver;
import com.juma.server.vm.domain1.VehicleExtraFunction;
import com.juma.server.vm.domain1.bo.VehicleBo;
import com.juma.tgm.base.domain.BaseTruckDeviceInfo;
import com.juma.tgm.common.Constants;
import com.juma.tgm.tools.service.AmsCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.*;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.vms.transport.domain.CapacityPool;
import com.juma.vms.truck.enumeration.TruckStatusEnum;
import com.juma.vms.vendor.domain.Vendor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Administrator
 * @version V1.0 Created by RX on 2016/5/12 0012. 货车ServiceImpl
 * @Description: 货车接口实现类
 * @date 2016年5月12日 下午4:39:35
 */
@Service
public class TruckServiceImpl implements TruckService {

    private final Logger log = LoggerFactory.getLogger(TruckServiceImpl.class);
    @Autowired
    private TruckTypeService truckTypeService;
    @Autowired
    private ConfParamService confParamService;
    @Autowired
    private WaybillService waybillService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private AmsCommonService amsCommonService;

    @Override
    public Truck findTruckByVehicleId(Integer vehicleId) {
        if (null == vehicleId) {
            return null;
        }

        return this.buildTruck(vmsCommonService.loadTruckByVehicleId(vehicleId));
    }

    @Override
    public Truck findAllTruckByVehicleId(Integer vehicleId) {
        if (null == vehicleId) {
            return null;
        }

        return this.buildTruck(vmsCommonService.loadTruckByVehicleId(vehicleId));
    }

    @Override
    public Truck findTruckByVehicleIdAndCheckExist(Integer vehicleId) throws BusinessException {
        Truck truck = findTruckByVehicleId(vehicleId);
        if (null == truck) {
            throw new BusinessException("truckNotfound", "truck.error.not.found");
        }
        return truck;
    }

    @Override
    public void delete(Integer vehicleId, LoginUser loginUser) {
//        Truck truck = findTruckByVehicleId(vehicleId);
//        if (null == truck) {
//            return;
//        }
//
//        // 删除TMS系统货车信息
//        truck.setIsDelete(true);
//        update(truck, loginUser);
//        // 删除车队关联表信息
//        truckFleetTruckService.deleteByTruckId(truck.getTruckId());
    }

    // TMS系统添加车辆
    @Override
    public void insert(Truck truck, LoginUser loginUser) {
//        if (null != loginUser) {
//            truck.setCreateUserId(loginUser.getUserId());
//        }
//        truck.setCreateTime(new Date());
//        truckDao.insert(truck);
    }

    // TMS系统编辑车辆
    @Override
    public void update(Truck truck, LoginUser loginUser) {
//        if (null != loginUser) {
//            truck.setLastUpdateUserId(loginUser.getUserId());
//        }
//        truck.setLastUpdateTime(new Date());
//        truckDao.update(truck);
    }

    @Override
    public Truck getTruck(Integer truckId) {
        return this.buildTruck(vmsCommonService.loadTruckByTruckId(truckId));
    }

    @Override
    public Truck getTruckAndCheckEixst(Integer truckId) {
        Truck truck = getTruck(truckId);
        if (null == truck) {
            throw new BusinessException("truckNotfound", "truck.error.not.found");
        }
        return truck;
    }

    @Override
    public Truck findTruckByAmsDriverId(Integer amsDriverId, LoginUser loginUser) {
        if (null == amsDriverId) {
            return null;
        }

        com.juma.vms.driver.domain.Driver vmsDriver = vmsCommonService.loadDriverByAmsDriverId(amsDriverId);
        if (null == vmsDriver) {
            return null;
        }

        CapacityPool capacityPool = vmsCommonService.loadCapacityByDriverId(vmsDriver.getDriverId(), loginUser);
        if (null == capacityPool || null == capacityPool.getStatus() || !capacityPool.getStatus()) {
            return null;
        }

        return this.getTruck(capacityPool.getTruckId());
    }

    @Override
    public TruckVehicle findTruckVehicleByAmsDriverId(Integer amsDriverId, LoginUser loginUser) {

        TruckVehicle truckVehicle = new TruckVehicle();

        Truck truck = findTruckByAmsDriverId(amsDriverId, loginUser);
        if (null == truck) {
            return null;
        }

        truck.setTruckTypeName(truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()));
        truckVehicle.setTruck(truck);

        if (null == truck.getVehicleId()) {
            return truckVehicle;
        }

        VehicleBo vehicleBo = amsCommonService.findVehicle(truck.getVehicleId(), loginUser);
        if (null == vehicleBo || null == vehicleBo.getVehicleExtend()) {
            return truckVehicle;
        }
        
        TruckExtend t = new TruckExtend();
        t.setPlateNumber(vehicleBo.getVehicleExtend().getPlateNumber());
        t.setMaxLoadCapacity(vehicleBo.getVehicleExtend().getMaxLoadCapacity());
        t.setVehicleFrameNo(vehicleBo.getVehicleFrameNo());
        truckVehicle.setVehicle(t);

        truckVehicle.setEntryLicenseCnName(getEntryLicenseCnName(vehicleBo.getVehicleExtend().getGoCityLicenseType()));
        truckVehicle.setTailBoardCnName(this.hasTailBoard(truck.getTruckId(), loginUser) ? "有" : "无");

        Driver amsDriver = amsCommonService.findDriver(amsDriverId, loginUser);
        if (null != amsDriver) {
            truckVehicle.setParkingAddressName(amsDriver.getAddress());
        }

        return truckVehicle;
    }

    // 获取入城证中文名
    @Override
    public String getEntryLicenseCnName(Byte entryLicense) {
        if (null == entryLicense) {
            return "无";
        }

        ConfParamOption option = confParamService.findParamOption(Constants.ENTRY_CITY_LICENSE_TYPE,
                entryLicense.toString());
        return (option == null ? "无" : option.getOptionName());
    }

    @Override
    public Truck findTruckByPlateNumber(String plateNumber) {
        if (StringUtils.isBlank(plateNumber)) {
            return null;
        }

        return this.buildTruck(vmsCommonService.loadTruckByPlateNumber(plateNumber));
    }

    @Override
    public String findTruckInfoStrByTruckId(Integer truckId, LoginUser loginUser) {
        StringBuffer sb = new StringBuffer("");
        Truck truck = this.getTruck(truckId);
        if (null == truck) {
            return null;
        }

        // 车型
        String truckTypeName = null;
        try {
            //truckTypeName = truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength());
        } catch (BusinessException e) {
            log.warn(e.getMessage(), e);
        }
        if (StringUtils.isNotBlank(truckTypeName)) {
            sb.append(truckTypeName);
        }

        // 载重
        BigDecimal maxLoadCapacity = buildMaxLoadCapacityFormat(truck.getVehicleId(), loginUser);
        if (null != maxLoadCapacity) {
            sb.append("|").append(maxLoadCapacity).append("吨");
        }

        // 入城证
        Integer entryLicense = truck.getEntryLicense();
        if (null != entryLicense) {
            sb.append("|").append(getEntryLicenseCnName(entryLicense.byteValue()));
        }

        // 附加功能
        String vehicleExtraFunctions = buildVehicleExtraFunctionToStr(truck.getVehicleId(), loginUser);
        if (StringUtils.isNotBlank(vehicleExtraFunctions)) {
            sb.append(vehicleExtraFunctions);
        }

        // 去除头部 |
        String truckInfo = sb.toString();
        if (truckInfo.startsWith("|")) {
            return truckInfo.substring(1, truckInfo.length()).trim();
        }
        return truckInfo.trim();
    }

    // 获取并格式化最大载重(单位由KG转化为吨)，四舍五入保留两位小数
    private BigDecimal buildMaxLoadCapacityFormat(Integer vehicleId, LoginUser loginUser) {
        VehicleBo vehicleBo = amsCommonService.findVehicle(vehicleId, loginUser);
        if (null == vehicleBo || null == vehicleBo.getVehicleExtend()) {
            return null;
        }

        Integer maxLoadCapacity = vehicleBo.getVehicleExtend().getMaxLoadCapacity();
        if (null == maxLoadCapacity || NumberUtils.compare(maxLoadCapacity, 0) != 1) {
            return null;
        }

        // 将车辆的最大载重的单位由KG转为吨
        BigDecimal divide = new BigDecimal(maxLoadCapacity).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        if (divide.compareTo(new BigDecimal("0.00")) == 1) {
            return divide;
        }
        return null;
    }

    // 将车辆的附加功能转换为字符串(格式：|XX|XX|XX)
    private String buildVehicleExtraFunctionToStr(Integer vehicleId, LoginUser loginUser) {
        if (null == vehicleId) {
            return null;
        }

        // 拼接附加功能
        StringBuffer sf = new StringBuffer("");
        List<VehicleExtraFunction> list = amsCommonService.listExtraByVehicleId(vehicleId, loginUser);
        if (null == list) {
            return "";
        }

        for (VehicleExtraFunction vehicleExtraFunction : list) {
            sf.append("|").append(vehicleExtraFunction.getExtraFunctionName());
        }
        return sf.toString();
    }

    @Override
    public TruckInfo findTruckInfo() {
        log.info("wt-monitor use findTruckInfo start");

        TruckInfo info = new TruckInfo();
        List<BaseTruckDeviceInfo> result = new ArrayList<BaseTruckDeviceInfo>();

        Set<Integer> tenantIdSet = new HashSet<>();
        // 获取正在配送中的运单，去除重复的车辆
        List<Waybill> deliveryingList = waybillService
                .findWaybillByStatusView(Waybill.StatusView.DELIVERYING.getCode());
        for (Waybill waybill : deliveryingList) {
            tenantIdSet.add(waybill.getTenantId());
        }

        List<Integer> statusList = new ArrayList<>();
        statusList.add(TruckStatusEnum.ENABLE.getCode());
        List<com.juma.vms.truck.domain.Truck> trucks = new ArrayList<>();

        for (Integer tenantId : new ArrayList<>(tenantIdSet)) {
            trucks.addAll(vmsCommonService.listTruckByStatus(statusList, new LoginUser(tenantId, 1)));
        }

        Map<String, String> allTruckMap = new HashMap<String, String>();
        for (com.juma.vms.truck.domain.Truck truck : trucks) {
            allTruckMap.put(truck.getTruckId() + "", truck.getPlateNumber());
        }

        Map<String, String> truckMap = new HashMap<String, String>();
        for (Waybill waybill : deliveryingList) {
            if (truckMap.containsKey(waybill.getTruckId() + ""))
                continue;
            if (allTruckMap.containsKey(waybill.getTruckId() + "")) {
                truckMap.put(waybill.getTruckId() + "", waybill.getTruckId() + "");

                BaseTruckDeviceInfo truck = new BaseTruckDeviceInfo();
                truck.setPlateNumber(allTruckMap.get(waybill.getTruckId() + ""));
                result.add(truck);
            }
        }
        info.setTruckDeviceInfoList(result);
        log.info("回传在途监控行驶中的车辆--result: {}", result.toString());
        return info;
    }

    @Override
    public void removeVehicleBoxLength(Integer truckId) {
//        truckDao.removeVehicleBoxLength(truckId);
    }
    
    @Override
    public void removeVehicleBoxType(Integer truckId) {
//        truckDao.removeVehicleBoxType(truckId);
    }

    private boolean hasTailBoard(Integer truckId, LoginUser loginUser) {
        // 尾板(去VMS系统查询有无尾板)
        if (null == truckId) {
            return false;
        }

        com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByTruckId(truckId);
        if (null != truck && truck.getIsTailBoard()) {
            return true;
        }

        return false;
    }

    @Override
    public void removeEstimateFinishTime(Integer truckId) {
//        truckDao.removeEstimateFinishTime(truckId);
    }

    @Override
    public boolean judgeTruckIsStopCamp(Integer vehicleId, Driver amsDriver, Date planDeliveryTime, LoginUser loginUser) {
        return false;
    }

    @Override
    public void mutilUpdateTruck(MuilEditTruck truck) {
//        truckDao.mutilUpdateTruck(truck);
    }

    @Override
    public boolean isShowYourPrice(Integer truckId, LoginUser loginUser) {
        Truck truck = getTruck(truckId);
        if(null == truck) {
            return false;
        }

        Vendor vendor = vmsCommonService.loadVendorByTruckId(truckId, loginUser);
        if (null == vendor) {
            return false;
        }

        return vendor.getIsShowYourPrice().intValue() == 1;
    }
    
    public static void main(String[] args) {
        List<Vendor> vendors = new ArrayList<>();
        Vendor v = new Vendor();
        v.setIsShowYourPrice((byte)1);
        vendors.add(v);
        System.out.println(vendors.isEmpty() || vendors.get(0).getIsShowYourPrice().intValue() == 1);
    }
    
    public Truck buildTruck(com.juma.vms.truck.domain.Truck vmsTruck) {
        if (null == vmsTruck) {
            return null;
        }

        Truck truck = new Truck();
        truck.setTruckId(vmsTruck.getTruckId());
        truck.setVehicleId(vmsTruck.getVehicleId());
        truck.setPlateNumber(vmsTruck.getPlateNumber());
        truck.setFrameNo(vmsTruck.getTruckIdentificationNo());
        truck.setEntryLicense(vmsTruck.getGoCityLicenseType());
        truck.setVehicleBoxType(vmsTruck.getVehicleBoxType());
        truck.setVehicleBoxLength(vmsTruck.getVehicleBoxLength());
        truck.setTailBoard(vmsTruck.getIsTailBoard() == null ? 0 : (vmsTruck.getIsTailBoard() ?  1 : 0));
        truck.setTruckRunType(truck.getTruckRunType());
        return truck;
    }
    
}
