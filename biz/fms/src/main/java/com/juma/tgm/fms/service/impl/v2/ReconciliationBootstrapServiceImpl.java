package com.juma.tgm.fms.service.impl.v2;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.fms.dao.ReconciliationBootstrapMapper;
import com.juma.tgm.fms.domain.v2.bo.ReconciliationStatistics;
import com.juma.tgm.fms.domain.v2.vo.ReconciliationWaybillDetailQueryVo;
import com.juma.tgm.fms.domain.v2.vo.ReconciliationWaybillDetailVo;
import com.juma.tgm.fms.service.v2.ReconciliationBootstrapService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybillReconciliation.domain.WaybillReconciliation;
import com.juma.tgm.waybillReconciliation.service.WaybillReconciliationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ReconciliationBootstrapServiceImpl implements ReconciliationBootstrapService {

    @Autowired
    private WaybillService waybillService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Autowired
    private CustomerInfoService customerInfoService;


    @Autowired
    private TruckRequireService truckRequireService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TruckService truckService;


    @Autowired
    private ReconciliationBootstrapMapper reconciliationBootstrapMapper;

    @Autowired
    private WaybillParamService waybillParamService;

    @Autowired
    private WaybillReconciliationService waybillReconciliationService;// v1  接口，原来的实现 也在这里适配


    @Override
    public Page<ReconciliationStatistics> search(PageCondition pageCondition, LoginUser loginUser) throws BusinessException {
        String areaCodeList = null;
        String customerName = null;
        if (pageCondition.getFilters() != null) {
            if (pageCondition.getFilters().get("areaCodeList") != null) {
                areaCodeList = pageCondition.getFilters().get("areaCodeList").toString();
            }
            if (pageCondition.getFilters().get("customerName") != null) {
                customerName = pageCondition.getFilters().get("customerName").toString();
            }
        }
        List<ReconciliationStatistics> reconciliationStatistics = reconciliationBootstrapMapper.statistics(Waybill.StatusView.FINISH.getCode(),
            Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode(),
            pageCondition.getStartOffSet(),
            pageCondition.getPageSize(),
            customerName,
            areaCodeList, loginUser.getTenantId());
        Integer count = reconciliationBootstrapMapper.countStatistics(Waybill.StatusView.FINISH.getCode(),
            Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode(),
            pageCondition.getStartOffSet(),
            pageCondition.getPageSize(),
            customerName,
            areaCodeList, loginUser.getTenantId());
        this.setCrmCustomerId(reconciliationStatistics);
        return new Page<>(pageCondition.getPageNo(), reconciliationStatistics.size(), count, reconciliationStatistics);
    }


    private void setCrmCustomerId(List<ReconciliationStatistics> reconciliationStatistics) {
        if (reconciliationStatistics != null) {
            for (ReconciliationStatistics reconciliationStatistics1 : reconciliationStatistics) {
                if (reconciliationStatistics1.getCustomerId() != null) {
                    CustomerInfo customerInfo = customerInfoService.findCusInfoById(reconciliationStatistics1.getCustomerId());
                    if (customerInfo == null) {
                        continue;
                    }
                    reconciliationStatistics1.setCrmCustomerId(customerInfo.getCrmCustomerId());
                }
            }
        }
    }


    private void setVehicleId(Waybill... waybills) {
        if (waybills != null) {
            for (Waybill waybill : waybills) {
                if (waybill.getTruckId() != null) {
                    Truck truck = truckService.getTruck(waybill.getTruckId());
                    if (truck == null) {
                        continue;
                    }
                    waybill.setVehicleId(truck.getVehicleId());
                }
            }
        }
    }

    private void setAmsDriverId(Waybill... waybills) {
        if (waybills != null) {
            for (Waybill waybill : waybills) {
                if (waybill.getDriverId() != null) {
                    Driver driver = driverService.getDriver(waybill.getDriverId());
                    if (driver == null) {
                        continue;
                    }
                    waybill.setAmsDriverId(driver.getAmsDriverId());
                }
            }
        }
    }

    @Override
    public List<ReconciliationStatistics> find(Integer customerId, String projectName, LoginUser loginUser) throws BusinessException {
        return reconciliationBootstrapMapper.statisticsProject(customerId, Waybill.StatusView.FINISH.getCode(), Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode(), projectName, loginUser.getTenantId());
    }


    @Override
    public List<Waybill> findByWaybillIds(List<Integer> waybillIds, LoginUser loginUser) throws BusinessException {

        List<Waybill> waybillList = new ArrayList<>();

        if (CollectionUtils.isEmpty(waybillIds)) {
            return waybillList;
        }

        Waybill waybill = null;
        for (Integer waybillId : waybillIds) {
            waybill = waybillCommonService.getWaybillById(waybillId);
            waybillList.add(waybill);
        }
        return waybillList;
    }

    @Override
    public void update(WaybillReconciliation waybillReconciliation, List<String> areaNodeList, LoginUser loginUser) throws BusinessException {
        // 适配 原来的功能 这里不做任何改变
        waybillReconciliationService.update(waybillReconciliation, areaNodeList, loginUser);
    }

    @Override
    @Transactional
    public void update(List<WaybillReconciliation> waybillReconciliations, List<String> areaNodeList, LoginUser loginUser) throws BusinessException {
        for (WaybillReconciliation waybillReconciliation : waybillReconciliations) {
            waybillReconciliationService.update(waybillReconciliation, areaNodeList, loginUser);
        }
    }

    @Override
    public Page<ReconciliationWaybillDetailVo> searchWaybills(LoginEmployee loginEmployee, PageCondition pageCondition) throws BusinessException {
        if (pageCondition.getFilters() == null || pageCondition.getFilters().get("customerId") == null) {
            throw new BusinessException("customerId", "errors.paramCanNotNull");
        } else {
            pageCondition.getFilters().put("reconciliationStatus", Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());//
            pageCondition.getFilters().put("statusView", Waybill.StatusView.FINISH.getCode());
            pageCondition.getFilters().put("reconciliationNo", "ISNULL");
            List<ReconciliationWaybillDetailVo> reconciliationWaybillDetailVos = new ArrayList<>();
            Page<Waybill> waybillPage = waybillService.search(loginEmployee, pageCondition);

            if (waybillPage.getResults() != null) {
                List<Integer> waybillIds = Lists.newArrayList();
                for (Waybill waybill : waybillPage.getResults()) {
                    waybillIds.add(waybill.getWaybillId());
                }
                List<TruckRequire> truckRequires = truckRequireService.findByWaybillIds(waybillIds);
                Map<Integer,TruckRequire> truckRequireMap = Maps.newConcurrentMap();
                for (TruckRequire truckRequire : truckRequires){
                    truckRequireMap.put(truckRequire.getWaybillId(),truckRequire);
                }

                List<WaybillParam> params = waybillParamService.findByWaybillIds(waybillIds);
                Map<Integer, WaybillParam> waybillParamMap = Maps.newConcurrentMap();
                for (WaybillParam waybillParam : params){
                    waybillParamMap.put(waybillParam.getWaybillId(),waybillParam);
                }

                for (Waybill waybill : waybillPage.getResults()) {
                    ReconciliationWaybillDetailVo reconciliationWaybillDetailVo = new ReconciliationWaybillDetailQueryVo();
                    reconciliationWaybillDetailVo.setWaybill(waybill);
                    TruckRequire truckRequire = truckRequireMap.get(waybill.getWaybillId());
                    reconciliationWaybillDetailVo.setRequire(truckRequire);
                    WaybillParam waybillParam = params.get(waybill.getWaybillId());
                    reconciliationWaybillDetailVo.setWaybillParam(waybillParam);
                    reconciliationWaybillDetailVos.add(reconciliationWaybillDetailVo);
                }
                Waybill[] waybills = waybillPage.getResults().toArray(new Waybill[waybillPage.getResults().size()]);
                this.setVehicleId(waybills);
                this.setAmsDriverId(waybills);
            }
            return new Page<>(waybillPage.getPageNo(), waybillPage.getPageSize(), waybillPage.getTotal(), reconciliationWaybillDetailVos);
        }
    }
}
