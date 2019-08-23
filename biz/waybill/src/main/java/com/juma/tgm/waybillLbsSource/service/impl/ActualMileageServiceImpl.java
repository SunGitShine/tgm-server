package com.juma.tgm.waybillLbsSource.service.impl;

import com.giants.common.SpringContextHelper;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.conf.domain.ConfParamOption;
import com.juma.tgm.basicTruckType.service.ConfParamInfoService;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybillLbsSource.domain.ActualMileageQuery;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSource;
import com.juma.tgm.waybillLbsSource.domain.WaybillLbsSourceQuery;
import com.juma.tgm.waybillLbsSource.service.ActualMileageService;
import com.juma.tgm.waybillLbsSource.service.WaybillLbsSourceService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @ClassName ActualMileageServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年6月22日 上午9:26:07
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class ActualMileageServiceImpl implements ActualMileageService {

    private static final Logger log = LoggerFactory.getLogger(ActualMileageServiceImpl.class);

    @Resource
    private WaybillService waybillService;
    @Resource
    private WaybillLbsSourceService waybillLbsSourceService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private ExportTaskService exportTaskService;
    @Resource
    private ConfParamInfoService confParamInfoService;
    @Autowired
    private VmsCommonService vmsCommonService;

    @Override
    public Page<ActualMileageQuery> search(PageCondition pageCondition, LoginUser loginUser) {
        List<ActualMileageQuery> result = new ArrayList<ActualMileageQuery>();
        pageCondition.getFilters().put("actualMileageWayill", true);
        Page<Waybill> page = waybillService.search(loginUser, pageCondition);

        // 设备类型集合
        Map<String, ConfParamOption> deviceMap = confParamInfoService.findConfParamInfoByParamKey(Constants.DEVICE_PRIORITY);

        for (Waybill waybill : page.getResults()) {
            ActualMileageQuery query = new ActualMileageQuery();
            query.setWaybillId(waybill.getWaybillId());
            query.setWaybillNo(waybill.getWaybillNo());
            query.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
            query.setPlanDeliveryDate(DateUtil.format(waybill.getPlanDeliveryTime()));
            query.setEstimateDistance(waybill.getEstimateDistance());
            query.setCustomerId(waybill.getCustomerId());
            query.setProjectId(waybill.getProjectId());
            query.setTruckCustomerId(waybill.getTruckCustomerId());
            query.setCustomerManagerId(waybill.getCustomerManagerId());
            if (null != waybill.getActualMileage()) {
                query.setActualMileage(new BigDecimal(waybill.getActualMileage())
                        .divide(new BigDecimal("1000"), 1, BigDecimal.ROUND_HALF_UP).toString());
            }
            query.setChangeDeliveryPoint(
                    waybill.getIsChangeDeliveryPoint() == Waybill.ChangeDeliveryPoint.HAS_UPDATE.getCode() ? "是" : "否");

            query.setCustomerName(waybill.getCustomerName());
            query.setTruckId(waybill.getTruckId());
            query.setPlateNumber(waybill.getPlateNumber());

            //vms获取司机
            Driver driver = vmsCommonService.loadDriverByDriverId(waybill.getDriverId());
            if(null != driver){
                query.setDriverName(driver.getName());
                query.setDriverMoble(BaseUtil.interceptPhoneNumber(driver.getPhone()));
                query.setDriverTypeText(DriverTypeEnum.getDescByCode(driver.getDriverRunType()));
            }

            // 数据源
            WaybillLbsSource waybillLbsSource = waybillLbsSourceService.findSourceByWaybillIdAndSign(
                    waybill.getWaybillId(), WaybillLbsSource.Sign.ACTUAL_MILEAGE.getCode());
            if (null != waybillLbsSource && null != waybillLbsSource.getDeviceType()) {
                ConfParamOption option = deviceMap.get(waybillLbsSource.getDeviceType() + "");
                if (null != option) {
                    query.setLbsSource(option.getOptionDescribed());
                }
            }
            result.add(query);
        }
        return new Page<ActualMileageQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
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
                    Page<ActualMileageQuery> rows = search(pageCondition, loginUser);
                    Collection<ActualMileageQuery> list = rows.getResults();
                    for (ActualMileageQuery query : list) {
                        User user = employeeService.loadUserByEmployeeId(query.getCustomerManagerId(), loginUser);
                        if (null != user) {
                            query.setCustomerManagerName(user.getName());
                        }
                    }
                    exportTaskService.uploadExcelAndUpdateExportTask(exportTaskId, "actualMileageException", list,
                            WaybillLbsSourceQuery.class, startTime, loginUser);
                } catch (Exception e) {
                    exceptionMsg.append(e.getMessage()).append("|");
                    exportTaskService.failed(exportTaskId, exceptionMsg.toString(), loginUser);
                    log.warn(e.getMessage(), e);
                }
            }
        });
    }

}
