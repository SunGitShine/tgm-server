package com.juma.tgm.waybillLbsSource.service.impl;

import com.giants.common.SpringContextHelper;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.algorithm.IntervalSegment;
import com.juma.tgm.costReimbursed.domain.CostReimbursed;
import com.juma.tgm.costReimbursed.service.CostReimbursedService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybillLbsSource.domain.WaybillPriceExceptionQuery;
import com.juma.tgm.waybillLbsSource.service.WaybillPriceExceptionService;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by shawn_lin on 2017/8/23.
 */
@Service
public class WaybillPriceExceptionServiceImpl implements WaybillPriceExceptionService {

    private static final Logger log = LoggerFactory.getLogger(WaybillPriceExceptionServiceImpl.class);

    @Resource
    private WaybillService waybillService;
    @Resource
    private ExportTaskService exportTaskService;
    @Resource
    private CostReimbursedService costReimbursedService;

    @Override
    public Page<WaybillPriceExceptionQuery> search(PageCondition pageCondition,
            LoginEmployee loginEmployee) {
        // 组装固定条件
        pageCondition.getFilters().put("priceExceptionFlag", true);
        List<Integer> listStatusView = new ArrayList<Integer>();
        listStatusView.add(Waybill.StatusView.FINISH.getCode());
        listStatusView.add(Waybill.StatusView.WATING_PAY.getCode());
        pageCondition.getFilters().put("statusViewList", listStatusView);

        List<Integer> listWaybillSource = new ArrayList<Integer>();
        listWaybillSource.add(Waybill.WaybillSource.JUMA_CLIENT.getCode());
        listWaybillSource.add(Waybill.WaybillSource.WECHAT_CLIENT.getCode());
        listWaybillSource.add(Waybill.WaybillSource.BACKGROUND_NEW.getCode());
        listWaybillSource.add(Waybill.WaybillSource.CARGO_OWNER.getCode());
        pageCondition.getFilters().put("listWaybillSource", listWaybillSource);

        Page<Waybill> rows = waybillService.searchBasicInfo(loginEmployee, pageCondition);
        List<WaybillPriceExceptionQuery> list = buildPriceExceptionQuery((List<Waybill>) rows.getResults(),
                loginEmployee);
        return new Page<WaybillPriceExceptionQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(),
                rows.getTotal(), list);
    }

    @Override
    public void asyncExport(final PageCondition pageCondition, final Integer exportTaskId,
            final LoginEmployee loginEmployee) throws Exception {
        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                StringBuffer exceptionMsg = new StringBuffer("");
                try {
                    pageCondition.setPageNo(1);
                    pageCondition.setPageSize(Integer.MAX_VALUE);
                    Page<WaybillPriceExceptionQuery> rows = search(pageCondition, loginEmployee);
                    List<WaybillPriceExceptionQuery> result = (List) rows.getResults();
                    for (WaybillPriceExceptionQuery priceException : result) {
                        if (null == priceException.getIsChangeDeliveryPoint()) {
                            continue;
                        }
                        priceException.setIsChangeDeliveryPointStr(Waybill.ChangeDeliveryPoint.HAS_UPDATE
                                .getCode() == priceException.getIsChangeDeliveryPoint() ? "是" : "否");
                    }
                    exportTaskService.uploadExcelAndUpdateExportTask(exportTaskId, "waybillPriceException", result,
                            WaybillPriceExceptionQuery.class, startTime, loginEmployee);
                } catch (Exception e) {
                    exceptionMsg.append(e.getMessage()).append("|");
                    exportTaskService.failed(exportTaskId, exceptionMsg.toString(), loginEmployee);
                    log.warn(e.getMessage(), e);
                }
            }
        });
    }

    private List<WaybillPriceExceptionQuery> buildPriceExceptionQuery(List<Waybill> rows, LoginUser loginUser) {
        List<WaybillPriceExceptionQuery> result = new ArrayList<WaybillPriceExceptionQuery>();
        for (Waybill waybill : rows) {
            WaybillPriceExceptionQuery waybillPriceExceptionQuery = new WaybillPriceExceptionQuery();
            waybillPriceExceptionQuery.setWaybillId(waybill.getWaybillId());
            waybillPriceExceptionQuery.setWaybillNo(waybill.getWaybillNo());
            waybillPriceExceptionQuery.setCalculatedFreight(waybill.getReferenceFreight());
            waybillPriceExceptionQuery.setEstimateFreight(waybill.getEstimateFreight());
            waybillPriceExceptionQuery.setCustomerManagerName(waybill.getCustomerManagerName());
            waybillPriceExceptionQuery.setCustomerName(waybill.getCustomerName());
            waybillPriceExceptionQuery.setDriverName(waybill.getDriverName());
            waybillPriceExceptionQuery.setPlanDeliveryTime(waybill.getPlanDeliveryTime());
            waybillPriceExceptionQuery.setPlanDeliveryDate(DateUtil.format(waybill.getPlanDeliveryTime()));
            waybillPriceExceptionQuery.setIsChangeDeliveryPoint(waybill.getIsChangeDeliveryPoint());

            waybillPriceExceptionQuery.setCustomerId(waybill.getCustomerId());
            waybillPriceExceptionQuery.setCustomerManagerId(waybill.getCustomerManagerId());
            waybillPriceExceptionQuery.setDriverId(waybill.getDriverId());
            waybillPriceExceptionQuery.setTruckId(waybill.getTruckId());
            waybillPriceExceptionQuery.setProjectId(waybill.getProjectId());

            // 计算时间成本
            calTimeCost(waybillPriceExceptionQuery, waybill);

            List<CostReimbursed> costReimbursedList = costReimbursedService.listByKeyAndTypeAndWaybillId(
                    CostReimbursed.CostReimbursedKey.DRIVER_COST_REIMBURSED, null, waybill.getWaybillId(), loginUser);
            for (CostReimbursed costReimbursed : costReimbursedList) {
                if (costReimbursed.getCostReimbursedType() == 1) {
                    // 过路费
                    waybillPriceExceptionQuery.setRoadAmount(costReimbursed.getReimbursedAmount());
                }
                if (costReimbursed.getCostReimbursedType() == 2) {
                    // 停车费
                    waybillPriceExceptionQuery.setCarStopAmount(costReimbursed.getReimbursedAmount());
                }
            }

            if (waybillPriceExceptionQuery.getRoadAmount() == null) {
                waybillPriceExceptionQuery.setRoadAmount(waybill.getTolls());
            }
            result.add(waybillPriceExceptionQuery);
        }
        return result;
    }

    // 计算时间成本
    private void calTimeCost(WaybillPriceExceptionQuery waybillPriceExceptionQuery, Waybill waybill) {
        Date finishiTime = null;
        Date planDeliveryTime = null;
        if (null == waybill.getPlanDeliveryTime()) {
            return;
        }

        if (null != waybill.getOnlyLoadCargo() && NumberUtils.compare(waybill.getOnlyLoadCargo(), 1) == 0) {
            Calendar planDeliveryDate = Calendar.getInstance();
            planDeliveryDate.setTime(waybill.getPlanDeliveryTime());
            planDeliveryDate.add(Calendar.DAY_OF_MONTH, 1);
            planDeliveryDate.set(Calendar.HOUR_OF_DAY, 9);
            planDeliveryDate.set(Calendar.MINUTE, 8);
            planDeliveryDate.set(Calendar.SECOND, 0);
            planDeliveryTime = planDeliveryDate.getTime();
        } else {
            Calendar planDeliveryDate = Calendar.getInstance();
            planDeliveryDate.setTime(waybill.getPlanDeliveryTime());
            planDeliveryDate.add(Calendar.HOUR_OF_DAY, 1);
            planDeliveryTime = planDeliveryDate.getTime();
        }

        if (null == planDeliveryTime) {
            return;
        }

        // 完成时间：配送完成时间->预估完成时间
        if (null != waybill.getFinishTime()) {
            Calendar estimateFinishTime = Calendar.getInstance();
            estimateFinishTime.setTime(waybill.getFinishTime());
            estimateFinishTime.add(Calendar.HOUR_OF_DAY, -1);
            finishiTime = estimateFinishTime.getTime();
        } else {
            Calendar estimateFinishTime = Calendar.getInstance();
            estimateFinishTime.setTime(waybill.getCmEstimateFinishTime() == null
                    ? new Date(planDeliveryTime.getTime()
                            + (waybill.getEstimateTimeConsumption() == null ? 0 : waybill.getEstimateTimeConsumption()))
                    : waybill.getCmEstimateFinishTime());
            estimateFinishTime.add(Calendar.HOUR_OF_DAY, -1);
            finishiTime = estimateFinishTime.getTime();
        }

        // 计算时间成本系数
        float weight = IntervalSegment.computeWeight(planDeliveryTime, finishiTime);
        Double timePricePlus = Double.parseDouble(String.valueOf(weight));
        waybillPriceExceptionQuery.setTimePrice(timePricePlus);
    }
}
