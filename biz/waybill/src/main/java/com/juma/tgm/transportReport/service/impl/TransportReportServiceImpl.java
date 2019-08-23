package com.juma.tgm.transportReport.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.Constants;
import com.juma.tgm.transportReport.domain.SummarizationOfWaybillData;
import com.juma.tgm.transportReport.domain.TransportReport;
import com.juma.tgm.transportReport.domain.WaybillSummary;
import com.juma.tgm.transportReport.service.TransportReportService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillCommonService;

@Service
public class TransportReportServiceImpl implements TransportReportService {

    @Resource
    private WaybillCommonService waybillCommonService;
    @Resource
    private TruckRequireService truckRequireService;

    @Override
    public TransportReport loadTransportReport(Integer projectId, Integer tenantId, String startTime,
            String endTime, Integer pageNo, Integer pageSize) {
        TransportReport result = new TransportReport();
        if (null == projectId || null == tenantId || StringUtils.isBlank(startTime)
                || StringUtils.isBlank(endTime)) {
            return result;
        }

        // 公用查询条件
        PageCondition cond = this.buildCommonCond(projectId, tenantId, startTime, endTime, pageNo, pageSize);

        // 列表
        this.waybillSummary(cond, result);

        // 数据汇总
        if (pageNo == 1) {
            result.getListSumWaybillData().add(new SummarizationOfWaybillData("派车中",
                    this.waybillSumByStatusViews(cond, Waybill.StatusView.WATING_RECEIVE.getCode())));
            result.getListSumWaybillData().add(new SummarizationOfWaybillData("待配送", this.waybillSumByStatusViews(cond,
                    Waybill.StatusView.TEMP.getCode(), Waybill.StatusView.WATING_DELIVERY.getCode())));
            result.getListSumWaybillData().add(new SummarizationOfWaybillData("配送中",
                    this.waybillSumByStatusViews(cond, Waybill.StatusView.DELIVERYING.getCode())));
            result.getListSumWaybillData().add(new SummarizationOfWaybillData("完成配送", this.waybillSumByStatusViews(cond,
                    Waybill.StatusView.WATING_PAY.getCode(), Waybill.StatusView.FINISH.getCode())));
        }

        return result;
    }

    // 获取运单数据
    private void waybillSummary(PageCondition cond, TransportReport transportReport) {
        Map<String, Object> filters = cond.getFilters();
        filters.put("statusViewList", this.buildStatusViews());
        cond.setOrderBy(" status_view asc, plan_delivery_time desc ");
        int total = waybillCommonService.searchCount(cond);
        List<Waybill> rows = waybillCommonService.search(cond);
        for (Waybill waybill : rows) {
            transportReport.getListWaybillSummary().add(this.buildWaybillSummary(waybill, Constants.SYS_LOGIN_USER));
        }
        transportReport.setPageNo(cond.getPageNo());
        transportReport.setPageSize(cond.getPageSize());
        transportReport.setTotal(total);
    }

    // 分配送状态数据汇总
    private int waybillSumByStatusViews(PageCondition cond, Integer... statusView) {
        Map<String, Object> filters = cond.getFilters();
        filters.put("statusViewList", Arrays.asList(statusView));
        return waybillCommonService.searchCount(cond);
    }

    // 公共查询条件
    private PageCondition buildCommonCond(Integer projectId, Integer tenantId, String startTime, String endTime,
            Integer pageNo, Integer pageSize) {
        PageCondition cond = new PageCondition();
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("tenantId", tenantId);
        filters.put("projectId", projectId);
        filters.put("startTime", startTime);
        filters.put("endTime", endTime);
        filters.put("waybillSourceNotEqual", Waybill.WaybillSource.BACKGROUND_IMPORT.getCode());
        cond.setFilters(filters);
        cond.setPageNo(pageNo);
        cond.setPageSize(pageSize);
        return cond;
    }

    // 组装配送状态查询条件
    private List<Integer> buildStatusViews() {
        List<Integer> statusViews = new ArrayList<Integer>();
        statusViews.add(Waybill.StatusView.TEMP.getCode());
        statusViews.add(Waybill.StatusView.WATING_RECEIVE.getCode());
        statusViews.add(Waybill.StatusView.WATING_DELIVERY.getCode());
        statusViews.add(Waybill.StatusView.DELIVERYING.getCode());
        statusViews.add(Waybill.StatusView.WATING_PAY.getCode());
        statusViews.add(Waybill.StatusView.FINISH.getCode());
        return statusViews;
    }

    // 组装货物信息
    private WaybillSummary buildWaybillSummary(Waybill waybill, LoginUser loginUser) {
        WaybillSummary s = new WaybillSummary();
        s.setWaybill(waybill);
        loginUser.setTenantId(waybill.getTenantId());
        TruckRequire truckRequire = truckRequireService.findTruckRequireByWaybillId(waybill.getWaybillId(), loginUser);
        if (null == truckRequire) {
            return s;
        }

        s.setGoodsType(truckRequire.getGoodsType());
        s.setGoodsWeight(truckRequire.getGoodsWeight());
        s.setGoodsVolume(truckRequire.getGoodsVolume());
        s.setGoodsAmount(truckRequire.getGoodsAmount());
        return s;
    }
}
