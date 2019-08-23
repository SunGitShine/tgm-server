package com.juma.tgm.customerManager.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.server.vm.domain1.bo.VehicleBo;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customerManager.dao.FixedDemandDao;
import com.juma.tgm.customerManager.dao.FixedDemandDeliveryPointDao;
import com.juma.tgm.customerManager.dao.FixedDemandTruckMapper;
import com.juma.tgm.customerManager.domain.FixedDemand;
import com.juma.tgm.customerManager.domain.FixedDemandDeliveryPoint;
import com.juma.tgm.customerManager.domain.FixedDemandTruck;
import com.juma.tgm.customerManager.domain.FixedDemandTruckExample;
import com.juma.tgm.customerManager.service.FixedDemandService;
import com.juma.tgm.customerManager.service.vo.FixedDemandVo;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.tools.service.AmsCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.domain.bo.DriverTruckInfoBo;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName: FixedDemandServiceImpl
 * @Description:
 * @author: liang
 * @date: 2017-07-24 19:40
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
@Deprecated
@Service
public class FixedDemandServiceImpl implements FixedDemandService {

    private static final Logger log = LoggerFactory.getLogger(FixedDemandServiceImpl.class);

    /**
     * 客户经理最大维护孤星需求个数
     */
    private static final int MAX_FIXED_DEMAND_SIZE = 1000;

    @Resource
    private FixedDemandDao fixedDemandDao;

    @Resource
    private FixedDemandDeliveryPointDao fixedDemandDeliveryPointDao;

    @Resource
    private TruckTypeFreightService truckTypeFreightService;

    @Resource
    private WaybillService waybillService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private FixedDemandTruckMapper fixedDemandTruckMapper;

    @Resource
    private ProjectService projectService;

    @Resource
    private TruckService truckService;

    @Resource
    private TruckTypeService truckTypeService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Resource
    private VmsCommonService vmsCommonService;

    @Resource
    private AmsCommonService amsCommonService;

    @Override
    public Page<FixedDemandVo> getFixedDemandList(LoginEmployee loginEmployee, PageCondition pageCondition)
            throws BusinessException {
        if (loginEmployee == null)
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "操作人");

        Page<FixedDemandVo> pageData = new Page<>();
        pageData.setTotal(0);
        pageData.setPageSize(pageCondition.getPageSize());
        pageData.setPageNo(pageCondition.getPageNo());

        pageCondition.setOrderBy("fixed_demand_id desc");

        int count = fixedDemandDao.searchCount(pageCondition);

        if (count <= 0) return pageData;

        // 获取基础信息
        List<FixedDemand> baseDemands = fixedDemandDao.search(pageCondition);

        pageData.setTotal(count);
        // 获取配送线路信息
        List<FixedDemandVo> finalData = new ArrayList<>();

        FixedDemandVo fixedDemandVo = null;

        for (FixedDemand demand : baseDemands) {
            fixedDemandVo = new FixedDemandVo();

            // this.buildDeliveryAddress(demand, fixedDemandVo);
            fixedDemandVo.setFixedDemand(demand);
            //司机、车信息
            this.buildFixedDemandTruckDetails(demand, fixedDemandVo, loginEmployee);
            this.buildProjectInfo(fixedDemandVo);
            
            finalData.add(fixedDemandVo);
        }

        pageData.setResults(finalData);

        return pageData;
    }

    private void buildFixedDemandTruckDetails(FixedDemand demand, FixedDemandVo fixedDemandVo, LoginUser loginUser) {
        // 固定车辆
        FixedDemandTruckExample demandTruckExample = new FixedDemandTruckExample();
        FixedDemandTruckExample.Criteria criteria = demandTruckExample.createCriteria();
        criteria.andFixedDemandIdEqualTo(demand.getFixedDemandId());
        List<FixedDemandTruck> trucks = fixedDemandTruckMapper.selectByExample(demandTruckExample);
        List<DriverTruckInfoBo> fixedDemandTruckDetails = new ArrayList<DriverTruckInfoBo>();
        for(FixedDemandTruck fixedTruck : trucks) {
            DriverTruckInfoBo driverTruckBo = new DriverTruckInfoBo();
            // 车辆信息
            Truck truck = truckService.getTruck(fixedTruck.getTruckId());
            if (null == truck) {
                continue;
            }
            driverTruckBo.setPlateNumber(truck.getPlateNumber());
            com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByTruckId(truck.getTruckId(), loginUser);

            // 司机信息
            if (null != driver) {
                driverTruckBo.setDervicerName(driver.getName());
                driverTruckBo.setContactPhone(driver.getPhone());
            }
            fixedDemandTruckDetails.add(driverTruckBo);
        }
        fixedDemandVo.setFixedDemandTruckDetails(fixedDemandTruckDetails);
    }

    /**
     * 组装司机和项目信息
     *
     * @param fixedDemandVo
     */
    private void buildProjectInfo(FixedDemandVo fixedDemandVo) {
        if (fixedDemandVo == null) return;
        FixedDemand fixedDemand = fixedDemandVo.getFixedDemand();
        if (fixedDemand == null) return;
        if (fixedDemand.getProjectId() == null) return;
        // 获取项目信息
        Project project = projectService.getProject(fixedDemand.getProjectId());
        if (project != null) {
            fixedDemandVo.setProjectName(project.getName());
        }

    }

    /**
     * 组装取货地和配送地
     *
     * @param demand
     * @param fixedDemandVo
     */
    private void buildDeliveryAddress(FixedDemand demand, FixedDemandVo fixedDemandVo) {
        // 配送地
        FixedDemandDeliveryPoint example = new FixedDemandDeliveryPoint();
        example.setFixedDemandId(demand.getFixedDemandId());
        example.setStartPoint(FixedDemandDeliveryPoint.PointType.delivery_address.getCode());
        List<FixedDemandDeliveryPoint> deliveryPoints = fixedDemandDeliveryPointDao.findByExample(example);
        fixedDemandVo.setDeliveryAddresses(deliveryPoints);

        // 取货地
        example.setStartPoint(FixedDemandDeliveryPoint.PointType.receive_address.getCode());
        List<FixedDemandDeliveryPoint> receivePoints = fixedDemandDeliveryPointDao.findByExample(example);
        if( demand.getCustomerId() != null ) {
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(demand.getCustomerId());
            fixedDemandVo.setCustomerInfo( customerInfo );
        }
        if( demand.getProjectId() != null ) {
            Project project = projectService.getProject(demand.getProjectId());
            fixedDemandVo.setProjectName( project .getName() );
        }
        fixedDemandVo.setReceiveAddresses(receivePoints);
    }

    @Override
    public Integer add(FixedDemandVo fixedDemandVo, LoginEmployee loginEmployee) throws BusinessException {
        // 校验最大固定需求个数
        PageCondition pageCondition = new PageCondition();
        Map<String, Object> filter = new HashMap<>();
        filter.put("customerManagerId", loginEmployee.getEmployeeId());
        pageCondition.setFilters(filter);

        int count = fixedDemandDao.searchCount(pageCondition);
        if (NumberUtils.compare(MAX_FIXED_DEMAND_SIZE, count) <= 0) {
            throw new BusinessException("fixedDemandMaxCountError", "errors.canMoreThan",
                    new String[] { "固定需求数量", String.valueOf(MAX_FIXED_DEMAND_SIZE) });
        }

        if (fixedDemandVo == null)
            throw new BusinessException("fixedDemandVoNull", "errors.paramCanNotNullWithName", "参数");
        FixedDemand fixedDemand = fixedDemandVo.getFixedDemand();
        if (fixedDemand == null)
            throw new BusinessException("fixedDemandNull", "errors.paramCanNotNullWithName", "需求内容");

        if (fixedDemandVo.getBillStrategy() != null) {
            fixedDemand.setAutoCreateBillStrategy(JSONObject.toJSONString(fixedDemandVo.getBillStrategy()));
        }
        // 自动下单需要时间策略
        if (fixedDemand.getIsAutoCreateBill() == null) {
            fixedDemand.setIsAutoCreateBill(FixedDemand.IsAutoCreateBillEnum.NO_AUTO_CREATE.getCode());
        }
        if (NumberUtils.compare(fixedDemand.getIsAutoCreateBill(),
                FixedDemand.IsAutoCreateBillEnum.AUTO_CREATE.getCode()) == 0) {
            if (fixedDemandVo.getBillStrategy() == null)
                throw new BusinessException("BillStrategyNull", "errors.paramCanNotNullWithName", "自动下单周期");

            fixedDemand.setAutoCreateBillStrategy(JSONObject.toJSONString(fixedDemandVo.getBillStrategy()));
        }

        if (fixedDemand.getProjectId() == null)
            throw new BusinessException("projectIdNull", "errors.paramCanNotNullWithName", "项目");

        List<FixedDemandDeliveryPoint> deliveryPoints = fixedDemandVo.getDeliveryAddresses();
        if (CollectionUtils.isEmpty(deliveryPoints))
            throw new BusinessException("receivePointsNull", "errors.paramCanNotNullWithName", "取货地");

        if (fixedDemand.getFixedDemandId() != null)
            throw new BusinessException("FixedDemandIdNull", "errors.paramError");
        // 保存基础需求信息
        fixedDemand.setCreateTime(new Date());
        fixedDemand.setCreateUserId(loginEmployee.getUserId());
        fixedDemand.setLastUpdateTime(fixedDemand.getCreateTime());
        fixedDemand.setLastUpdateUserId(fixedDemand.getCreateUserId());
        fixedDemandDao.insert(fixedDemand);

        List<FixedDemandDeliveryPoint> receivePoints = fixedDemandVo.getReceiveAddresses();
        if (CollectionUtils.isNotEmpty(receivePoints)) {
            // 保存配送地信息
            deliveryPoints.addAll(receivePoints);
        }

        for (FixedDemandDeliveryPoint point : deliveryPoints) {
            point.setFixedDemandId(fixedDemand.getFixedDemandId());
            point.setCreateUserId(fixedDemand.getCreateUserId());
            point.setCreateTime(fixedDemand.getCreateTime());
            point.setLastUpdateUserId(fixedDemand.getLastUpdateUserId());
            point.setLastUpdateTime(fixedDemand.getLastUpdateTime());

            this.addDeliveryPoint(point);
        }
        // 保存固定车辆
        this.saveFixedTruckBatch(fixedDemandVo.getFixedDemandTruck(), fixedDemand);

        publisher.publishEvent(fixedDemand);
        return fixedDemand.getFixedDemandId();
    }

    // 删除所有固定车辆
    private void delFixedTruck(int fixedDemandId) {
        FixedDemandTruckExample example = new FixedDemandTruckExample();
        FixedDemandTruckExample.Criteria criteria = example.createCriteria();
        criteria.andFixedDemandIdEqualTo(fixedDemandId);
        fixedDemandTruckMapper.deleteByExample(example);
    }

    // 批量保存固定车辆
    private void saveFixedTruckBatch(List<FixedDemandTruck> fixedDemandTrucks, FixedDemand fixedDemand) {
        if (CollectionUtils.isEmpty(fixedDemandTrucks)) return;
        if (fixedDemand.getFixedDemandId() == null)
            throw new BusinessException("FixedDemandIdNull", "errors.paramCanNotNullWithName", "固定需求id");

        for (FixedDemandTruck ft : fixedDemandTrucks) {
            ft.setFixedDemandId(fixedDemand.getFixedDemandId());
            fixedDemandTruckMapper.insert(ft);
        }
    }

    @Override
    public void del(int fixedDemandId) throws BusinessException {
        // 删除基础信息
        FixedDemand fixedDemand = new FixedDemand();
        fixedDemand.setFixedDemandId(fixedDemandId);
        fixedDemandDao.delete(fixedDemand);
        // 删除配送地信息
        this.delDeliveryPointByDemandId(fixedDemandId);
        // 删除固定车辆
        this.delFixedTruck(fixedDemandId);
        publisher.publishEvent(fixedDemandId);

    }

    @Override
    public void update(FixedDemandVo fixedDemandVo, LoginEmployee loginEmployee) throws BusinessException {
        if (fixedDemandVo == null)
            throw new BusinessException("fixedDemandVoNull", "errors.paramCanNotNullWithName", "参数");

        FixedDemand fixedDemand = fixedDemandVo.getFixedDemand();
        if (fixedDemand == null)
            throw new BusinessException("fixedDemandNull", "errors.paramCanNotNullWithName", "需求内容");

        if (fixedDemandVo.getBillStrategy() != null) {
            fixedDemand.setAutoCreateBillStrategy(JSONObject.toJSONString(fixedDemandVo.getBillStrategy()));
        }
        // 自动下单需要时间策略
        if (fixedDemand.getIsAutoCreateBill() == null) {
            fixedDemand.setIsAutoCreateBill(FixedDemand.IsAutoCreateBillEnum.NO_AUTO_CREATE.getCode());
        }
        if (NumberUtils.compare(fixedDemand.getIsAutoCreateBill(),
                FixedDemand.IsAutoCreateBillEnum.AUTO_CREATE.getCode()) == 0) {
            if (fixedDemandVo.getBillStrategy() == null)
                throw new BusinessException("BillStrategyNull", "errors.paramCanNotNullWithName", "自动下单周期");

            fixedDemand.setAutoCreateBillStrategy(JSONObject.toJSONString(fixedDemandVo.getBillStrategy()));
        }

        if (fixedDemand.getProjectId() == null)
            throw new BusinessException("projectIdNull", "errors.paramCanNotNullWithName", "项目");

        List<FixedDemandDeliveryPoint> receivePoints = fixedDemandVo.getReceiveAddresses();
        // if (CollectionUtils.isEmpty(receivePoints))
        // throw new BusinessException("receivePointsNull", "errors.paramCanNotNullWithName", "收货地");

        List<FixedDemandDeliveryPoint> deliveryPoints = fixedDemandVo.getDeliveryAddresses();
        if (CollectionUtils.isEmpty(deliveryPoints))
            throw new BusinessException("deliveryPointsNull", "errors.paramCanNotNullWithName", "取货地");

        if (fixedDemand.getFixedDemandId() == null)
            throw new BusinessException("FixedDemandIdNull", "errors.paramCanNotNullWithName", "id");

        // 更新基础信息
        fixedDemandDao.update(fixedDemand);
        // 删除原有配送地信息
        this.delDeliveryPointByDemandId(fixedDemand.getFixedDemandId());
        // 新增新的配送地信息
        if (CollectionUtils.isNotEmpty(receivePoints)) {
            // 保存配送地信息
            deliveryPoints.addAll(receivePoints);
        }
        for (FixedDemandDeliveryPoint point : deliveryPoints) {
            point.setFixedDemandId(fixedDemand.getFixedDemandId());
            point.setLastUpdateUserId(fixedDemand.getLastUpdateUserId());
            point.setLastUpdateTime(fixedDemand.getLastUpdateTime());
            this.addDeliveryPoint(point);
        }

        // 删除固定车辆
        this.delFixedTruck(fixedDemand.getFixedDemandId());
        // 保存固定车辆
        this.saveFixedTruckBatch(fixedDemandVo.getFixedDemandTruck(), fixedDemand);

        publisher.publishEvent(fixedDemand);
    }

    @Override
    public FixedDemandVo get(int fixedDemandId) throws BusinessException {
        // 获取基础数据
        FixedDemand data = fixedDemandDao.get(fixedDemandId);

        if (data == null) return null;

        FixedDemandVo vo = new FixedDemandVo();
        vo.setFixedDemand(data);
        // 获取配送地数据
        FixedDemandDeliveryPoint example = new FixedDemandDeliveryPoint();
        example.setFixedDemandId(fixedDemandId);

        // 固定车辆
        FixedDemandTruckExample demandTruckExample = new FixedDemandTruckExample();
        FixedDemandTruckExample.Criteria criteria = demandTruckExample.createCriteria();
        criteria.andFixedDemandIdEqualTo(fixedDemandId);
        List<FixedDemandTruck> trucks = fixedDemandTruckMapper.selectByExample(demandTruckExample);
        vo.setFixedDemandTruck(trucks);
        this.buildDeliveryAddress(data, vo);
        return vo;
    }

    private void delDeliveryPointByDemandId(int fixedDemandId) {
        FixedDemandDeliveryPoint point = new FixedDemandDeliveryPoint();
        point.setFixedDemandId(fixedDemandId);
        fixedDemandDeliveryPointDao.delete(point);
    }

    private void addDeliveryPoint(FixedDemandDeliveryPoint point) {
        fixedDemandDeliveryPointDao.insert(point);
    }

    @Override
    public void updateBatch(List<FixedDemand> fixedDemands) throws BusinessException {
        if (CollectionUtils.isEmpty(fixedDemands)) return;

        for (FixedDemand fd : fixedDemands) {
            fixedDemandDao.update(fd);
        }

    }

    @Override
    public void add4DriverFreightBatch() throws BusinessException {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(500);
        // 1、执行添加司机结算价列的脚本
        // 2、实行批量更新历史司机结算价
        // --获取所有固定需求
        Page pageData = this.getFixedDemandList(new LoginEmployee(), pageCondition);
        Collection<FixedDemandVo> datas = pageData.getResults();
        if (CollectionUtils.isEmpty(datas)) {
            throw new BusinessException("fixedDemandNull", "errors.common.prompt", "找不到固定需求数据");
        }
        // --计算司机结算价
        List<FixedDemand> dest = this.buildCalculateDriverFee(datas);
        // --更新固定需求
        this.updateBatch(dest);
    }

    private List<FixedDemand> buildCalculateDriverFee(Collection<FixedDemandVo> datas) {

        TruckRequire truckRequire = null;
        Waybill waybill = null;
        WaybillBo bo = null;
        List<FixedDemand> demandList = new ArrayList<>();
        for (FixedDemandVo vo : datas) {
            FixedDemand fd = vo.getFixedDemand();
            try {
                truckRequire = JSONObject.parseObject(fd.getRequireJson(), TruckRequire.class);
            } catch (Exception e) {
                log.warn("用车要求json格式化错误.id:{0}", new String[] { fd.getFixedDemandId().toString() }, e);
                continue;
            }

            waybill = new Waybill();
            waybill.setEstimateFreight(fd.getEstimateFreight());

            bo = new WaybillBo();
            bo.setTruckRequire(truckRequire);
            bo.setWaybill(waybill);
            // 司机结算价

            if (fd.getCustomerId() != null) {
                // 获取返点数据
                BigDecimal rebateRate = customerInfoService.getRebateRate(fd.getCustomerId());
                waybill.setRebateRate(rebateRate);
            }

            // --获取税后价格
            BigDecimal afterTaxFee = truckTypeFreightService.getAfterTaxFright(truckRequire, waybill);
            if (afterTaxFee == null) continue;// 没有税后价格则不能计算司机结算价

            waybill.setAfterTaxFreight(afterTaxFee);
            // --获取司机结算价格
            WaybillParam waybillParam = waybillService.settingExtraFee(bo, null);

            fd.setShow4DriverFreight(waybillParam.getShow4DriverFreight());
            demandList.add(fd);
        }

        return demandList;
    }

    @Override
    public List<DriverTruckInfoBo> listFixedDemandTruck(Integer fixedDemandId, LoginUser loginUser) {
        List<DriverTruckInfoBo> result = new ArrayList<DriverTruckInfoBo>();

        FixedDemandTruckExample example = new FixedDemandTruckExample();
        FixedDemandTruckExample.Criteria criteria = example.createCriteria();
        criteria.andFixedDemandIdEqualTo(fixedDemandId);

        List<FixedDemandTruck> list = fixedDemandTruckMapper.selectByExample(example);
        for (FixedDemandTruck f : list) {
            DriverTruckInfoBo bo = new DriverTruckInfoBo();
            bo.setTruckId(f.getTruckId());

            // 车辆信息
            Truck truck = truckService.getTruck(f.getTruckId());
            if (null == truck) {
                continue;
            }
            bo.setPlateNumber(truck.getPlateNumber());

            VehicleBo vehicleBo = amsCommonService.findVehicle(truck.getVehicleId(), loginUser);
            if (null != vehicleBo && null != vehicleBo.getVehicleExtend()) {
                bo.setLoad(vehicleBo.getVehicleExtend().getMaxLoadCapacity());
                bo.setVolume(vehicleBo.getVehicleExtend().getLoadVolume() == null ? BigDecimal.ZERO
                        : new BigDecimal(vehicleBo.getVehicleExtend().getLoadVolume().toString()));
            }

            // 车型
            bo.setTruckTypeName(truckTypeService.findTruckTypeNameBy(truck.getVehicleBoxType(), truck.getVehicleBoxLength()));

            com.juma.vms.driver.domain.Driver driver = vmsCommonService.loadDriverByTruckId(truck.getTruckId(), loginUser);
            // 司机信息
            if (null != driver) {
                bo.setDervicerName(driver.getName());
                bo.setContactPhone(driver.getPhone());
            }
            result.add(bo);
        }
        return result;
    }

    @Override
    public WaybillDetailInfo doBuildWaybillInfo(Integer fixedDemandId) {
        WaybillDetailInfo waybillDetailInfo = new WaybillDetailInfo();
        FixedDemandVo demandVo = this.get(fixedDemandId);
        if (null == demandVo) {
            return waybillDetailInfo;
        }

        FixedDemand fixedDemand = demandVo.getFixedDemand();
        if (null == fixedDemand || null == fixedDemand.getProjectId()) {
            return waybillDetailInfo;
        }

        Project project = projectService.getProject(fixedDemand.getProjectId());
        if (null == project) {
            return waybillDetailInfo;
        }

        // 用车数量
        waybillDetailInfo.setCreateBatchAmount(fixedDemand.getVehicleCount());

        // 运单信息
        Waybill waybill = new Waybill();
        waybill.setProjectId(project.getProjectId());
        waybill.setProjectName(project.getName());
        waybill.setCustomerId(fixedDemand.getCustomerId());
        waybill.setTruckCustomerId(fixedDemand.getTruckCustomerId());
        waybill.setCustomerManagerId(fixedDemand.getCustomerManagerId());
        waybill.setAreaCode(project.getAreaCode());
        waybill.setReceiptType(Waybill.ReceiptType.PROJECTPAY.getCode());
        waybillDetailInfo.setWaybill(waybill);

        // 用车要求
        String requireJson = fixedDemand.getRequireJson();
        if (StringUtils.isNotBlank(requireJson)) {
            waybillDetailInfo.setTruckRequire(JSON.parseObject(requireJson, TruckRequire.class));
        }

        // 取货地
        List<WaybillDeliveryAddress> waybillDeliveryAddresses = new ArrayList<WaybillDeliveryAddress>();
        List<FixedDemandDeliveryPoint> receiveAddresses = demandVo.getReceiveAddresses();
        for (FixedDemandDeliveryPoint point : receiveAddresses) {
            WaybillDeliveryAddress address = new WaybillDeliveryAddress();
            address.setAddressName(point.getAddressName());
            address.setAddressDetail(point.getAddressDetail());
            address.setRegionCode(point.getRegionCode());
            address.setCoordinates(point.getCoordinates());
            address.setContactName(point.getContactName());
            address.setContactPhone(point.getContactPhone());
            waybillDeliveryAddresses.add(address);
        }
        waybillDetailInfo.setWaybillDeliveryAddresses(waybillDeliveryAddresses);

        // 配送地
        List<WaybillReceiveAddress> waybillReceiveAddresses = new ArrayList<WaybillReceiveAddress>();
        List<FixedDemandDeliveryPoint> deliveryAddresses = demandVo.getDeliveryAddresses();
        for (FixedDemandDeliveryPoint point : deliveryAddresses) {
            WaybillReceiveAddress address = new WaybillReceiveAddress();
            address.setAddressName(point.getAddressName());
            address.setAddressDetail(point.getAddressDetail());
            address.setRegionCode(point.getRegionCode());
            address.setCoordinates(point.getCoordinates());
            address.setContactName(point.getContactName());
            address.setContactPhone(point.getContactPhone());
            waybillReceiveAddresses.add(address);
        }
        waybillDetailInfo.setWaybillReceiveAddresses(waybillReceiveAddresses);

        return waybillDetailInfo;
    }

    @Override
    public int searchCount(Integer projectId, LoginUser loginUser) {
        if (null == projectId) {
            return 0;
        }

        PageCondition cond = new PageCondition();
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("projectId", projectId);
        cond.setFilters(filters);
        return fixedDemandDao.searchCount(cond);
    }

    @Override
    public List<FixedDemand> find(Integer customerId) {
        Map<String,Object> query = new HashMap<>();
        query.put("customerId" , customerId);
        PageCondition  pageCondition= new PageCondition();
        pageCondition.setPageSize(Integer.MAX_VALUE);
        pageCondition.setPageNo(1);
        pageCondition.setFilters( query );
        return fixedDemandDao.search( pageCondition);// 这个版本好烦
    }

    @Override
    public List<FixedDemand> loadAll() {
        List<FixedDemand> list = fixedDemandDao.findByExample(new FixedDemand());
        return list;
    }
}
