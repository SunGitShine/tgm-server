package com.juma.tgm.waybillLbsSource.service.impl;

import com.giants.common.SpringContextHelper;
import com.giants.common.collections.CollectionUtils;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.domain.ReportInfo;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.gaode.domain.AmapRegeoResponse;
import com.juma.tgm.reportInfo.service.ReportInfoService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.service.GaoDeMapService;
import com.juma.tgm.waybillLbsSource.dao.WaybillLbsSourceDao;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSource;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSourceQuery;
import com.juma.tgm.waybillLbsSource.service.WaybillLbsSourceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class WaybillLbsSourceServiceImpl implements WaybillLbsSourceService {

    private final Logger log = LoggerFactory.getLogger(WaybillLbsSourceServiceImpl.class);
    @Resource
    private WaybillLbsSourceDao waybillLbsSourceDao;
    @Resource
    private TruckService truckService;
    @Resource
    private DriverService driverService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private ReportInfoService reportInfoService;
    @Resource
    private ExportTaskService exportTaskService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private GaoDeMapService gaoDeMapService;

    @Override
    public Page<WaybillLbsSourceQuery> search(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        structPageCondition(pageCondition, loginUser);
        int total = waybillLbsSourceDao.searchCount(pageCondition);
        List<WaybillLbsSourceQuery> rows = waybillLbsSourceDao.searchDetail(pageCondition);
        for (WaybillLbsSourceQuery query : rows) {
            Truck truck = truckService.getTruck(query.getTruckId());
            if (null != truck) {
                query.setPlateNumber(truck.getPlateNumber());
            }

            query.setPlanDeliveryDate(DateUtil.format(query.getPlanDeliveryTime()));
            Driver driver = driverService.getDriver(query.getDriverId());
            if (null != driver) {
                query.setDriverName(driver.getNickname());
                query.setDriverMobile(driver.getContactPhone());
            }
            if(null != query.getCustomerManagerId()){
                User user = employeeService.loadUserByEmployeeId(query.getCustomerManagerId(),loginUser);
                if(user!=null){
                    query.setCustomerManagerName(user.getName());
                }
            }
            if (null != query.getCustomerId()) {
                CustomerInfo customerInfo = customerInfoService.findCusInfoById(query.getCustomerId());
                if (null != customerInfo) {
                    query.setCustomerName(customerInfo.getCustomerName());
                }
            }

            query.setWaybillTypeStr(
                    DateUtil.compare(query.getCreateTime(), query.getPlanDeliveryTime(), DateUtil.YYYYMMDD) == -1 ? "预约单"
                            : "临时单");

            ReportInfo reportInfo = reportInfoService.findByDriverIdAndWaybillId(null, query.getDriverId(),
                    query.getWaybillId(), loginUser);
            query.setReportStr(reportInfo == null ? "否" : "是");

            if (null != query.getTimeConsuming()) {
                BigDecimal divide = new BigDecimal(query.getTimeConsuming()).divide(new BigDecimal("3600"), 2,
                        BigDecimal.ROUND_HALF_UP);
                query.setTimeConsuming(divide == null ? null : divide.toString());
            }
        }
        return new Page<WaybillLbsSourceQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public WaybillLbsSource getWaybillLbsSource(Integer waybillLbsSourceId) {
        return waybillLbsSourceDao.get(waybillLbsSourceId);
    }

    @Override
    public void asyncExport(final PageCondition pageCondition, final Integer exportTaskId, final LoginUser loginUser)
            throws Exception {
        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                StringBuffer exceptionMsg = new StringBuffer("");
                try {
                    pageCondition.setPageNo(1);
                    pageCondition.setPageSize(Integer.MAX_VALUE);
                    Page<WaybillLbsSourceQuery> rows = search(pageCondition, loginUser);
                    Collection<WaybillLbsSourceQuery> list = rows.getResults();
                    for (WaybillLbsSourceQuery query : list) {
                        User user = employeeService.loadUserByEmployeeId(query.getCustomerManagerId(),
                                loginUser);
                        if (null != user) {
                            query.setCustomerManagerName(user.getName());
                        }
                    }
                    exportTaskService.uploadExcelAndUpdateExportTask(exportTaskId, "driverLate", list,
                            WaybillLbsSourceQuery.class, startTime, loginUser);
                } catch (Exception e) {
                    exceptionMsg.append(e.getMessage()).append("|");
                    exportTaskService.failed(exportTaskId, exceptionMsg.toString(), loginUser);
                    log.warn(e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public void insert(WaybillLbsSource waybillLbsSource) throws BusinessException {
        waybillLbsSource.setCreateTime(new Date());
        if (StringUtils.isNotBlank(waybillLbsSource.getCoordinate())) {
            AmapRegeoResponse regeocode = gaoDeMapService.regeocode(waybillLbsSource.getCoordinate());
            if (null != regeocode) {
                waybillLbsSource.setAddress(regeocode.getRegeocode().getFormattedAddress());
            }
        }
        waybillLbsSourceDao.insert(waybillLbsSource);
    }

    @Override
    public void update(WaybillLbsSource waybillLbsSource) throws BusinessException {
        waybillLbsSourceDao.update(waybillLbsSource);
    }

    @Override
    public WaybillLbsSource findSourceByWaybillIdAndSign(Integer waybillId, Integer sign) {
        WaybillLbsSource example = new WaybillLbsSource();
        example.setWaybillId(waybillId);
        example.setSign(sign);
        List<WaybillLbsSource> list = waybillLbsSourceDao.findByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // 筛选条件的处理
    private void structPageCondition(PageCondition pageCondition, LoginUser loginUser) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters) {
            filters = new HashMap<String, Object>();
        } else {
            // 根据司机手机号查询，只能精确查询
            Object obj = filters.get("driverPhone");
            filters.remove("driverPhone");
            if (null != obj) {
                // 根据司机手机号获取司机的ID(driverId)
                Driver driver = driverService.findDriverByPhone(obj.toString().trim());
                if (null != driver) {
                    filters.put("driverId", driver.getDriverId());
                } else {
                    // 查询不到是的策略
                    filters.put("driverId", -1);
                }
            }
            obj = filters.get("driverName");
            filters.remove("driverName");
            if (null != obj) {
                // 根据司机姓名获取司机的ID(driverId)
                List<Driver> list = driverService.listDriverByName(obj.toString().trim());
                if (CollectionUtils.isNotEmpty(list)) {
                    List<Integer> driverIdList = new ArrayList<Integer>();
                    for (Driver driver : list) {
                        driverIdList.add(driver.getDriverId());
                    }
                    filters.put("driverIdList", driverIdList);
                } else {
                    // 查询不到是的策略
                    filters.put("driverId", -1);
                }
            }
        }
        pageCondition.setFilters(filters);
    }
}
