package com.juma.tgm.waybillReport.service.impl;

import com.giants.common.SpringContextHelper;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.base.domain.BaseIncomeInfo;
import com.juma.tgm.base.domain.MonthDomain;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.domain.CustomerInfoResp;
import com.juma.tgm.crm.domain.YesterdayIncomeInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.driver.domain.Driver;
import com.juma.tgm.driver.domain.DriverLoginUser;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.CheckInterfaceIsOnUserdService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.dao.WaybillDao;
import com.juma.tgm.waybill.domain.ReportForm;
import com.juma.tgm.waybill.domain.ReportFormDetail;
import com.juma.tgm.waybill.domain.ReportQueryDomain;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillCountResponse;
import com.juma.tgm.waybill.domain.WaybillParam;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillService;
import com.juma.tgm.waybillReport.domain.WaybillReport;
import com.juma.tgm.waybillReport.domain.WaybillReportExport;
import com.juma.tgm.waybillReport.service.WaybillReportService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @ClassName WaybillReportServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2017年2月4日 下午4:50:19
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class WaybillReportServiceImpl implements WaybillReportService {

    private final Logger log = LoggerFactory.getLogger(WaybillReportServiceImpl.class);
    @Resource
    private WaybillDao waybillDao;
    @Resource
    private WaybillService waybillService;
    @Resource
    private TruckService truckService;
    @Resource
    private DriverService driverService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private UserService userService;
    @Resource
    private WaybillParamService waybillParamService;
    @Resource
    private ExportTaskService exportTaskService;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;
    @Resource
    private CheckInterfaceIsOnUserdService checkInterfaceIsOnUserdService;

    @Override
    public Page<WaybillReportExport> search(PageCondition pageCondition, List<String> areaCodeList,
            LoginEmployee loginEmployee) {
        checkInterfaceIsOnUserdService.checkMethodIsUsedThenPushEmail("WaybillReportServiceImpl.search");
        List<WaybillReportExport> result = new ArrayList<WaybillReportExport>();
        // 已完成且已对账的运单
        pageCondition.getFilters().put("statusView", Waybill.StatusView.FINISH.getCode());
        pageCondition.getFilters().put("reconciliationStatus",
                Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());

        Page<Waybill> page = waybillService.search(loginEmployee, pageCondition);
        for (Waybill waybill : page.getResults()) {
            WaybillReportExport export = new WaybillReportExport();

            export.setWaybillId(waybill.getWaybillId());
            export.setWaybillNo(waybill.getWaybillNo());
            export.setReceiptType(waybill.getReceiptType());
            export.setEstimateFreight(waybill.getEstimateFreight());
            export.setEstimateDistance(waybill.getEstimateDistance());
            export.setCalculatedFreight(waybill.getCalculatedFreight());
            export.setAfterTaxFreight(waybill.getAfterTaxFreight());
            export.setReconciliationStatus(waybill.getReconciliationStatus());
            export.setTruckCustomerId(waybill.getTruckCustomerId());
            export.setShow4DriverFreight(waybill.getShow4DriverFreight());
            export.setRebateFee(waybill.getRebateFee());
            export.setAreaCode(waybill.getAreaCode());
            export.setTruckId(waybill.getTruckId());
            export.setDriverId(waybill.getDriverId());
            export.setCustomerId(waybill.getCustomerId());
            export.setCustomerManagerId(waybill.getCustomerManagerId());
            export.setProjectId(waybill.getProjectId());

            if (null != waybill.getPlanDeliveryTime()) {
                export.setPlanDeliveryDate(DateUtil.format(waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDD));
            }

            // 大客户
            export.setCustomerName(waybill.getCustomerName());
            // 项目名称
            export.setProjectName(waybill.getProjectName());
            // 客户经理
            export.setCustomerManagerName(waybill.getCustomerManagerName());
            // 司机
            export.setDriverName(waybill.getDriverName());

            // 运单扩展
            WaybillParam waybillParam = waybillParamService.findByWaybillId(waybill.getWaybillId());
            if (null != waybillParam) {
                export.setErpResult(waybillParam.getErpResult());
                export.setDriverHandlingCost(waybillParam.getDriverHandlingCost());
                export.setLaborerHandlingCost(waybillParam.getLaborerHandlingCost());
                export.setCheckOutFreight(waybillParam.getCheckOutFreight());
                export.setReceivableAccount(waybillParam.getReceivableAccount());
                export.setReceivableRemark(waybillParam.getReceivableRemark());
                if (null != waybillParam.getReceivableTime()) {
                    export.setReceivableTime(DateUtil.format(waybillParam.getReceivableTime()));
                }
            }
            result.add(export);
        }
        return new Page<WaybillReportExport>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    @Override
    public void asyncExport(final PageCondition pageCondition, final Integer exportTaskId,
            final LoginEmployee loginEmployee) throws Exception {
        checkInterfaceIsOnUserdService.checkMethodIsUsedThenPushEmail("WaybillReportServiceImpl.asyncExport");
        SpringContextHelper.getSpringBean(TaskExecutor.class).execute(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                StringBuffer exceptionMsg = new StringBuffer("");
                try {
                    List<WaybillReportExport> result = new ArrayList<WaybillReportExport>();
                    pageCondition.setPageNo(1);
                    pageCondition.setPageSize(Integer.MAX_VALUE);
                    Page<WaybillReportExport> rows = search(pageCondition, new ArrayList<String>(), loginEmployee);
                    for (WaybillReportExport export : rows.getResults()) {
                        try {
                            export.setAreaCodeName(businessAreaCommonService
                                    .loadPreAndSelfAreaName(export.getAreaCode(), loginEmployee));
                            // FIXME 适配新的结算状态
                            throw new BusinessException("noChangeError", "请适配新的结算状态");
                            // export.setCheckoutStr(
                            // export.getReconciliationStatus() ==
                            // Waybill.ReconciliationStatus.CHECKED.getCode()
                            // ? "已结算" : "未结算");
                        } catch (Exception e) {
                            exceptionMsg.append(e.getMessage()).append("|");
                        }

                        // 车辆信息
                        Truck truck = truckService.getTruck(export.getTruckId());
                        if (null != truck) {
                            export.setPlateNumber(truck.getPlateNumber());
                        }
                        result.add(export);
                    }
                    exportTaskService.uploadExcelAndUpdateExportTask(exportTaskId, "waybillReport", result,
                            WaybillReportExport.class, startTime, loginEmployee);
                } catch (Exception e) {
                    exceptionMsg.append(e.getMessage()).append("|");
                    exportTaskService.failed(exportTaskId, exceptionMsg.toString(), loginEmployee);
                    log.warn(e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public WaybillReport findOneById(Integer waybillId) {
        checkInterfaceIsOnUserdService.checkMethodIsUsedThenPushEmail("WaybillReportServiceImpl.findOneById");
        Waybill waybill = waybillDao.get(waybillId);
        if (null != waybill) {
            WaybillReport report = new WaybillReport();
            report.setWaybill(waybill);
            report.setCreateDate(DateUtil.format(waybill.getCreateTime(), DateUtil.YYYYMMDDHHMMSS));
            report.setCustomerInfo(customerInfoService.findCusInfoById(waybill.getCustomerId()));
            return report;
        }
        return null;
    }

    private List<WaybillReport> buildProjectBo(List<Waybill> rows) {
        List<WaybillReport> result = new ArrayList<WaybillReport>();
        if (CollectionUtils.isNotEmpty(rows)) {
            for (Waybill waybill : rows) {
                WaybillReport report = new WaybillReport();
                report.setWaybill(waybill);
                report.setPlanDeliveryDate(DateUtil.format(waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDDHHMMSS));
                // 司机信息
                report.setDriver(driverService.getDriver(waybill.getDriverId()));
                // 获取项目信息
                CustomerInfo customerInfo = customerInfoService.findCusInfoById(waybill.getCustomerId());
                // 修改为从crm系统获取企业客户数据
                this.buildCrmCustomerInfo(customerInfo);
                if (null != customerInfo) {
                    report.setCustomerInfo(customerInfo);
                    // 对应运单的企业客户的客户经理去运单表中的customerManagerId
                    try {
                        User user = userService.findUser(waybill.getCustomerManagerId());
                        TruckCustomer truckCustomer = new TruckCustomer();
                        truckCustomer.setNickname(user.getName());
                        truckCustomer.setContactPhone(user.getMobileNumber());
                        truckCustomer.setUserId(user.getUserId());
                        report.setTruckCustomer(truckCustomer);
                    } catch (Exception e) {
                    }
                }
                result.add(report);
            }
        }
        return result;
    }

    // 从crm中获取客户信息
    private void buildCrmCustomerInfo(CustomerInfo customerInfo) {
        if (customerInfo == null) {
            return;
        }
        if (customerInfo.getCrmCustomerId() == null) {
            return;
        }
        com.juma.crm.customer.domain.CustomerInfo crmCustomerInfo = customerInfoService
                .getCrmCustomerInfoByTgmCustomerId(customerInfo.getCustomerId());
        if (crmCustomerInfo == null) {
            return;
        }
        customerInfo.setCustomerManagerUserId(crmCustomerInfo.getUserId());
        customerInfo.setEmployeeId(crmCustomerInfo.getUserId());
        customerInfo.setCustomerName(crmCustomerInfo.getCustomerName());
    }

    // 构造报表查询条件
    private void buildParam(PageCondition pageCondition, Integer employeeIdOrDriverId, String mode) {
        if (StringUtils.isNotBlank(mode)) {
            if (Constants.STAR_DRIVER.equals(mode)) {
                pageCondition.getFilters().put("driverId", employeeIdOrDriverId);
            } else if (Constants.STAR_CUSTOMER.equals(mode)) {
                pageCondition.getFilters().put("ownerEmployeeId", employeeIdOrDriverId);
            }
        }
        pageCondition.getFilters().put("status", Waybill.Status.PAIED.getCode());
    }

    @Override
    public ReportForm getCustomerReport(PageCondition pageCondition, LoginEmployee loginEmployee)
            throws BusinessException {
        checkInterfaceIsOnUserdService.checkMethodIsUsedThenPushEmail("WaybillReportServiceImpl.getCustomerReport");
        ReportForm reportForm = new ReportForm();
        buildParam(pageCondition, loginEmployee.getEmployeeId(), Constants.STAR_CUSTOMER);
        getCustomerReport(reportForm, pageCondition);
        return reportForm;
    }

    @Override
    public ReportForm getDriverReport(PageCondition pageCondition, DriverLoginUser driverLoginUser) {
        checkInterfaceIsOnUserdService.checkMethodIsUsedThenPushEmail("WaybillReportServiceImpl.getDriverReport");
        ReportForm reportForm = new ReportForm();
        buildParam(pageCondition, driverLoginUser.getDriverId(), Constants.STAR_DRIVER);
        // 汇总到收入统计表
        getAllFreight(reportForm, pageCondition);
        // 汇总到收入统计表
        getYestodayIncome(reportForm, pageCondition);
        getReport(reportForm, pageCondition);
        return reportForm;
    }

    // 获取手机端报表
    private void getReport(ReportForm reportForm, PageCondition pageCondition) {
        buildDateParam(pageCondition, null);
        int total = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
        List<WaybillReport> result = buildProjectBo(rows);

        List<ReportFormDetail> list = new ArrayList<ReportFormDetail>();
        if (CollectionUtils.isNotEmpty(result)) {
            for (WaybillReport report : result) {
                ReportFormDetail reportFormDetail = new ReportFormDetail();
                Waybill waybill = report.getWaybill();
                if (null != waybill) {
                    reportFormDetail = buildDetail(reportFormDetail, waybill);
                    Driver driver = driverService.getDriver(waybill.getDriverId());
                    if (null != driver) {
                        reportFormDetail.setDriverName(substr(driver.getNickname(), 4));
                    }
                }
                CustomerInfo customerInfo = report.getCustomerInfo();
                if (null != customerInfo) {
                    reportFormDetail.setCustomerName(substr(customerInfo.getCustomerName(), 4));
                }
                list.add(reportFormDetail);
            }
        }
        reportForm.setReportPage(
                new Page<ReportFormDetail>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, list));
        getFreight(reportForm, pageCondition);
    }

    private String substr(String str, int endNo) {
        if (StringUtils.isNotBlank(str) && str.length() > endNo) {
            str = str.substring(0, endNo);
        }
        return str;
    }

    // 获取总的税前与税后费用
    private void getAllFreight(ReportForm reportForm, PageCondition pageCondition) {
        WaybillCountResponse countResponse = buildFreight(pageCondition);
        reportForm.setAllPreTaxTotal(countResponse.getEstimateFreight());
        reportForm.setAllAfterTaxTotal(countResponse.getAfterTaxFreight());
        reportForm.setAllShow4DriverFreightTotal(countResponse.getShow4DriverFreight());
        reportForm.setWaybillTotal(waybillDao.searchCount(pageCondition));
    }

    // 计算各种价格
    private WaybillCountResponse buildFreight(PageCondition pageCondition) {
        WaybillCountResponse response = new WaybillCountResponse();
        List<Waybill> listWaybill = waybillDao.getFreight(pageCondition);
        BigDecimal estimateFreight = BigDecimal.ZERO;
        BigDecimal afterTaxFreight = BigDecimal.ZERO;
        BigDecimal show4DriverFreight = BigDecimal.ZERO;
        for (Waybill waybill : listWaybill) {
            if (null == waybill) {
                continue;
            }

            estimateFreight = estimateFreight
                    .add(waybill.getEstimateFreight() == null ? BigDecimal.ZERO : waybill.getEstimateFreight());
            afterTaxFreight = afterTaxFreight
                    .add(waybill.getAfterTaxFreight() == null ? BigDecimal.ZERO : waybill.getAfterTaxFreight());
            show4DriverFreight = show4DriverFreight
                    .add(waybill.getShow4DriverFreight() == null ? BigDecimal.ZERO : waybill.getShow4DriverFreight());
        }
        response.setEstimateFreight(estimateFreight);
        response.setAfterTaxFreight(afterTaxFreight);
        response.setShow4DriverFreight(show4DriverFreight);
        return response;
    }

    // 获取昨天的收益(报表)
    private void getYestodayIncome(ReportForm reportForm, PageCondition pageCondition) {
        YesterdayIncomeInfo income = buildYestodayIncome(pageCondition);
        reportForm.setYesterdayIncome(income.getShow4DriverFreightIncome());
        reportForm.setYesterdayWaybillTotal(income.getYesterdayWaybillTotal());
        Map<String, Object> filters = pageCondition.getFilters();
        filters.remove("repotStartTime");
        filters.remove("repotEndTime");
        pageCondition.setFilters(filters);
    }

    // 获取昨天的收益
    private YesterdayIncomeInfo buildYestodayIncome(PageCondition pageCondition) {
        YesterdayIncomeInfo income = new YesterdayIncomeInfo();
        pageCondition.getFilters().put("repotStartTime", DateUtil.dayAddStart(-1));
        pageCondition.getFilters().put("repotEndTime", DateUtil.dayAddEnd(-1));

        WaybillCountResponse countResponse = buildFreight(pageCondition);
        income.setPreTaxIncome(countResponse.getEstimateFreight());
        income.setAfterTaxIncome(countResponse.getAfterTaxFreight());
        income.setShow4DriverFreightIncome(countResponse.getShow4DriverFreight());
        income.setYesterdayWaybillTotal(waybillDao.searchCount(pageCondition));
        return income;
    }

    // 获取用车端报表
    private void getCustomerReport(ReportForm reportForm, PageCondition pageCondition) {
        buildDateParam(pageCondition, null);
        int total = waybillDao.searchCount(pageCondition);
        List<Waybill> rows = waybillDao.search(pageCondition);
        List<WaybillReport> result = buildProjectBo(rows);
        List<ReportFormDetail> list = new ArrayList<ReportFormDetail>();
        if (CollectionUtils.isNotEmpty(result)) {
            buildReportDetail(reportForm, result, list);
        }
        reportForm.setReportPage(
                new Page<ReportFormDetail>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, list));
        getFreight(reportForm, pageCondition);
        buildCost(reportForm, pageCondition);
        buildCheckCost(reportForm, pageCondition);
        reportForm.setWaybillTotal(total);
    }

    // 构造详细报表信息列表
    private ReportForm buildReportDetail(ReportForm reportForm, List<WaybillReport> result,
            List<ReportFormDetail> list) {
        List<Integer> waybillIdList = new ArrayList<Integer>();
        for (WaybillReport report : result) {
            ReportFormDetail reportFormDetail = new ReportFormDetail();
            Waybill waybill = report.getWaybill();
            if (null != waybill) {
                waybillIdList.add(waybill.getWaybillId());
                reportFormDetail = buildDetail(reportFormDetail, waybill);
                Date planDeliveryTime = waybill.getPlanDeliveryTime();
                if (DateUtil.compare(planDeliveryTime, new Date(), DateUtil.YYYYMMDD) == 0) {
                    reportFormDetail.setPlanDeliveryDate("今天");
                } else {
                    reportFormDetail.setPlanDeliveryDate(DateUtil.format(planDeliveryTime, DateUtil.MMDD_CN));
                }
                reportFormDetail.setHandlingCost(waybillParamService.buildHandlingCost(waybill.getWaybillId()));
                Integer receiptStatus = waybill.getReceiptStatus();
                reportFormDetail
                        .setCheckOut(receiptStatus == Waybill.ReceiptStatus.HAS_COLLECTION.getCode() ? "已收款" : "未收款");
            }
            CustomerInfo customerInfo = report.getCustomerInfo();
            if (null != customerInfo) {
                reportFormDetail.setCustomerName(customerInfo.getCustomerName());
            }
            list.add(reportFormDetail);
        }
        return reportForm;
    }

    // 获取当月的税前与税后费用、司机数
    private void getFreight(ReportForm reportForm, PageCondition pageCondition) {
        WaybillCountResponse countResponse = buildFreight(pageCondition);
        reportForm.setPreTaxTotal(countResponse.getEstimateFreight());
        reportForm.setAfterTaxTotal(countResponse.getAfterTaxFreight());
        reportForm.setShow4DriverFreightTotal(countResponse.getShow4DriverFreight());
    }

    // 获取搬运费
    private void buildCost(ReportForm reportForm, PageCondition pageCondition) {
        WaybillParam param = waybillDao.findCountCostByParam(pageCondition);
        if (null != param) {
            BigDecimal driverHandlingCost = param.getDriverHandlingCost();
            BigDecimal laborerHandlingCost = param.getLaborerHandlingCost();
            reportForm.setEveryMonthHandlingCostSum((driverHandlingCost == null ? BigDecimal.ZERO : driverHandlingCost)
                    .add(laborerHandlingCost == null ? BigDecimal.ZERO : laborerHandlingCost));
        }
    }

    // 未结算费用
    private void buildCheckCost(ReportForm reportForm, PageCondition pageCondition) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null != filters) {
            Object obj = filters.get("isCheckout");
            if (null == obj) {
                pageCondition.getFilters().put("isCheckout", 0);
            } else {
                Integer isCheckOut = Integer.valueOf(obj.toString());
                if (isCheckOut == 1) {
                    return;
                }
            }
        }
        WaybillCountResponse countResponse = buildFreight(pageCondition);
        reportForm.setEveryMonthNotCheckCostSum(countResponse.getEstimateFreight());
    }

    // 构建报表数据列表数据
    private ReportFormDetail buildDetail(ReportFormDetail reportFormDetail, Waybill waybill) {
        reportFormDetail.setCreateDate(DateUtil.format(waybill.getFinishTime(), DateUtil.YYYYMMDD));
        reportFormDetail.setWaybillId(waybill.getWaybillId());
        reportFormDetail.setWaybillNo(waybill.getWaybillNo());
        reportFormDetail.setReceiptType(waybill.getReceiptType());
        reportFormDetail.setPreTaxFreight(waybill.getEstimateFreight());
        reportFormDetail.setAfterTaxFreight(waybill.getAfterTaxFreight());
        reportFormDetail.setShow4DriverFreight(waybill.getShow4DriverFreight());
        return reportFormDetail;
    }

    // 时间查询条件构造
    private void buildDateParam(PageCondition pageCondition, MonthDomain monthDomain) {
        if (null == monthDomain) {
            Map<String, Object> filters = pageCondition.getFilters();
            if (null != filters) {
                Object obj = filters.get("month");
                Object client = filters.get("client");
                if (null != obj) {
                    buildMonth(Integer.parseInt(obj.toString()), pageCondition, client);
                }
                Object objDate = filters.get("deliverydate");
                if (null != objDate) {
                    String str = String.valueOf(objDate);
                    buildPlandDeliverydate(DateUtil.parse(str, DateUtil.YYYYMM), pageCondition);
                }
            }
        } else {
            String date = monthDomain.getDate();
            if (null == date) {
                Integer month = monthDomain.getMonth();
                if (null != month) {
                    buildMonth(month, pageCondition, null);
                }
            } else {
                buildPlandDeliverydate(DateUtil.parse(date, DateUtil.YYYYMM), pageCondition);
            }
        }
    }

    // 月份条件构造
    private PageCondition buildPlandDeliverydate(Date date, PageCondition pageCondition) {
        if (DateUtil.compare(date, new Date(), DateUtil.YYYYMM) == 0) {
            pageCondition.getFilters().put("startTime", DateUtil.dayStart(1));
            pageCondition.getFilters().put("endTime", DateUtil.dayEnd(DateUtil.curDay()));
        } else {
            pageCondition.getFilters().put("planDeliverydate", DateUtil.monthStart(date));
        }
        return pageCondition;
    }

    // 兼容使用月份作为查询条件的请求
    private PageCondition buildMonth(Integer month, PageCondition pageCondition, Object client) {
        if (null != client && String.valueOf(client).equals("smartTruck")) {
            if (month == DateUtil.curMonth()) {
                pageCondition.getFilters().put("repotStartTime", DateUtil.dayStart(1));
                pageCondition.getFilters().put("repotEndTime", DateUtil.dayEnd(DateUtil.curDay()));
            } else {
                pageCondition.getFilters().put("yearMonth", DateUtil.monthStart(month - DateUtil.curMonth()));
            }
        } else {
            if (month == DateUtil.curMonth()) {
                pageCondition.getFilters().put("repotStartTime", DateUtil.dayStart(1));
                pageCondition.getFilters().put("repotEndTime", DateUtil.dayEnd(DateUtil.curDay()));
            } else if (month < DateUtil.curMonth()) {
                pageCondition.getFilters().put("yearMonth", DateUtil.monthStart(month - DateUtil.curMonth()));
            } else if (month > DateUtil.curMonth()) {
                pageCondition.getFilters().put("yearMonth", DateUtil.lastYearMonthStart(month - DateUtil.curMonth()));
            }
        }
        return pageCondition;
    }

    @Override
    public YesterdayIncomeInfo getYestodayIncome(DriverLoginUser driverLoginUser) {
        PageCondition pageCondition = new PageCondition();
        pageCondition.getFilters().put("driverId", driverLoginUser.getDriverId());
        pageCondition.getFilters().put("status", Waybill.Status.PAIED.getCode());
        return buildYestodayIncome(pageCondition);
    }

    @Override
    public ReportQueryDomain getDriverQuery(MonthDomain month, Integer driverId) {
        PageCondition pageCondition = new PageCondition();
        buildParam(pageCondition, driverId, Constants.STAR_DRIVER);
        buildDateParam(pageCondition, month);
        return getOldQueryList(pageCondition);
    }

    // 兼容司机端获取条件列表，不建议使用
    @Deprecated
    private ReportQueryDomain getOldQueryList(PageCondition pageCondition) {
        ReportQueryDomain query = new ReportQueryDomain();
        int count = waybillDao.searchCount(pageCondition);
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(count);
        List<Waybill> rows = waybillDao.search(pageCondition);
        List<TruckCustomer> customerList = new ArrayList<TruckCustomer>();
        List<CustomerInfo> infoList = new ArrayList<CustomerInfo>();
        if (CollectionUtils.isNotEmpty(rows)) {
            List<Integer> customerInfoList = new ArrayList<Integer>();
            for (Waybill waybill : rows) {
                CustomerInfo info = customerInfoService.findCusInfoById(waybill.getCustomerId());
                if (null != info) {
                    Integer customerId = info.getCustomerId();
                    if (!customerInfoList.contains(customerId)) {
                        customerInfoList.add(customerId);
                        infoList.add(info);
                    }
                }
            }
        }
        query.setTruckCustomerList(customerList);
        query.setCustomerInfoList(infoList);
        return query;
    }

    @Override
    public MonthDomain getMonthDomain() {
        MonthDomain month = new MonthDomain();
        List<MonthDomain> monthList = new ArrayList<MonthDomain>();
        List<Integer> list = DateUtil.monthList(3);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer num : list) {
                MonthDomain temp = new MonthDomain();
                temp.setMonth(num);
                temp.setDescr(DateUtil.format(DateUtil.addMonths(new Date(), num), DateUtil.YYYYMM));
                monthList.add(temp);
            }
            month.setMonthList(monthList);
        }
        month.setMonth(DateUtil.curMonth());
        month.setDescr(DateUtil.format(DateUtil.YYYYMM));
        return month;
    }

    @Override
    public ReportQueryDomain getCustomerQuery(MonthDomain month, LoginEmployee loginEmployee) {
        ReportQueryDomain domain = new ReportQueryDomain();
        CustomerInfoResp resp = customerInfoService.findByEmployeeId(new CustomerInfo(), loginEmployee.getEmployeeId());
        domain.setCustomerInfoList(resp.getCustomerInfoList());
        return domain;
    }

    @Override
    public WaybillReport findDriverIncomeInfo(LoginUser loginUser) {
        WaybillReport report = new WaybillReport();
        PageCondition pageCondition = new PageCondition();
        Driver driver = driverService.findDriverByUserId(loginUser.getUserId());
        if (null == driver) {
            return report;
        }

        pageCondition.getFilters().put("driverId", driver.getDriverId());
        Date date = new Date();
        report.setTheMonthIncomeInfo(getBaseIncomeInfo(pageCondition, date));
        report.setPreMonthIncomeInfo(getBaseIncomeInfo(pageCondition, DateUtil.addMonths(date, -1)));
        return report;
    }

    // 获取用户基本收入信息
    private BaseIncomeInfo getBaseIncomeInfo(PageCondition pageCondition, Date date) {
        BaseIncomeInfo incomeInfo = new BaseIncomeInfo();
        pageCondition.getFilters().put("planDeliverydate", date);
        BaseIncomeInfo info = waybillDao.getIncomeInfo(pageCondition);
        if (null != info) {
            incomeInfo.setAfterTaxIncome(info.getAfterTaxIncome());
            incomeInfo.setPreTaxIncome(info.getPreTaxIncome());
            incomeInfo.setDriverHandlingCost(info.getDriverHandlingCost());
            incomeInfo.setLaborerHandlingCost(info.getLaborerHandlingCost());
        }
        List<Integer> statusList = new ArrayList<Integer>();
        statusList.add(Waybill.Status.DELIVERIED.getCode());
        statusList.add(Waybill.Status.PAIED.getCode());
        pageCondition.getFilters().put("statusList", statusList);
        incomeInfo.setWaybillNumber(waybillDao.searchCount(pageCondition));
        return incomeInfo;
    }

    @Override
    public YesterdayIncomeInfo yesterdayIncome(LoginUser loginUser) {
        PageCondition pageCondition = new PageCondition();
        if (null == loginUser) {
            throw new BusinessException("noDriverInfo", "errors.noDriverInfo");
        }
        Integer userId = loginUser.getUserId();
        if (null == userId) {
            throw new BusinessException("noDriverInfo", "errors.noDriverInfo");
        }
        Driver driver = driverService.findDriverByUserId(userId);
        if (null != driver) {
            pageCondition.getFilters().put("driverId", driver.getDriverId());
            pageCondition.getFilters().put("status", Waybill.Status.PAIED.getCode());
            YesterdayIncomeInfo yestodayIncome = buildYestodayIncome(pageCondition);
            log.info("昨日收入yesterdayIncome：{}", yestodayIncome.toString());
            return yestodayIncome;
        }
        log.info("昨日收入yesterdayIncome,根据userId:{},没有查询到TMS系统对应的司机，无法统计昨日收入", userId);
        return new YesterdayIncomeInfo();
    }

    @Override
    public void updateToCheckout(int waybillId, LoginUser loginUser) throws BusinessException {
        Waybill waybill = new Waybill();
        waybill.setWaybillId(waybillId);
        // waybill.setCheckout(true);
        // waybill.setCheckoutTime(new Date());
        waybillDao.update(waybill);
    }
}
