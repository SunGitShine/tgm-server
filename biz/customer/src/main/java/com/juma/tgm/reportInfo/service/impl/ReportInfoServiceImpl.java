package com.juma.tgm.reportInfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.driver.domain.*;
import com.juma.tgm.driver.service.DriverService;
import com.juma.tgm.reportInfo.dao.ReportInfoDao;
import com.juma.tgm.reportInfo.service.ReportInfoDetailService;
import com.juma.tgm.reportInfo.service.ReportInfoService;
import com.juma.tgm.tools.service.MessagePushService;
import com.juma.tgm.truck.domain.Truck;
import com.juma.tgm.truck.service.TruckService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.GaoDeMapService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class ReportInfoServiceImpl implements ReportInfoService {

    private static final Logger log = LoggerFactory.getLogger(ReportInfoServiceImpl.class);

    @Resource
    private ReportInfoDao reportInfoDao;
    @Resource
    private ReportInfoDetailService reportInfoDetailService;
    @Resource
    private DriverService driverService;
    @Resource
    private TruckService truckService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private GaoDeMapService gaoDeMapService;
    @Resource
    private ConfParamService confParamService;
    @Resource
    private MessagePushService messagePushService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private WaybillCommonService waybillCommonService;
    @Value("${waybill.statistics.freight}")
    private String freightUrl;
    @Value("${waybill.statistics.income}")
    private String incomeUrl;
    @Value("${waybill.statistics.customer}")
    private String customerUrl;

    @Override
    public Page<ReportInfoBo> search(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        structPageCondition(pageCondition);
        int count = reportInfoDao.searchCount(pageCondition);
        List<ReportInfo> reportList = reportInfoDao.search(pageCondition);
        List<ReportInfoBo> reportBoList = new ArrayList<ReportInfoBo>();
        for (ReportInfo report : reportList) {
            ReportInfoBo reportBo = new ReportInfoBo();
            report.setReportInfoCount(reportInfoDetailService.findCountByReportId(report.getReportInfoId()));
            reportBo.setReportInfo(report);
            reportBo.setDriver(driverService.getDriver(report.getDriverId()));
            reportBo.setCustomerInfo(customerInfoService.findCusInfoById(report.getCustomerId()));
            reportBoList.add(reportBo);
        }
        return new Page<ReportInfoBo>(pageCondition.getPageNo(), pageCondition.getPageSize(), count, reportBoList);
    }

    // 组装分页查询条件
    private void structPageCondition(PageCondition pageCondition) {
        Map<String, Object> filters = pageCondition.getFilters();
        if (null == filters) {
            pageCondition.setFilters(new HashMap<String, Object>());
            return;
        }
        structPageConditionByDriverName(filters);
        structPageConditionByPhone(filters);
        pageCondition.setFilters(filters);
    }

    // 组装分页查询条件:根据司机姓名查询
    private void structPageConditionByDriverName(Map<String, Object> filters) {
        Object obj = filters.get("driverName");
        if (null == obj) {
            return;
        }
        filters.remove("driverName");

        // 根据司机姓名获取司机的ID(driverId)
        List<Driver> list = driverService.listDriverByName(obj.toString().trim());
        if (list.isEmpty()) {
            // 查不到司机采取不返回数据的策略
            filters.put("driverId", -1);
            return;
        }

        List<Integer> driverIdList = new ArrayList<Integer>();
        for (Driver driver : list) {
            driverIdList.add(driver.getDriverId());
        }
        if (!driverIdList.isEmpty()) {
            filters.put("driverIdList", driverIdList);
        }
    }

    // 组装分页查询条件:根据司机电话查询
    private void structPageConditionByPhone(Map<String, Object> filters) {
        // 根据司机电话查询
        Object obj = filters.get("driverPhone");
        if (null == obj) {
            return;
        }
        filters.remove("driverPhone");

        // 根据司机电话获取司机的ID(driverId)
        Driver driver = driverService.findDriverByPhone(obj.toString().trim());
        if (null == driver) {
            // 查不到司机采取不返回数据的策略
            filters.put("driverId", -1);
            return;
        }
        filters.put("driverId", driver.getDriverId());
    }

    @Override
    public Integer insert(ReportInfo reportInfo, LoginUser loginUser) throws BusinessException {
        // 验证
        Integer waybillId = reportInfo.getWaybillId();
        if (null == waybillId) {
            throw new BusinessException("unkownWaybill", "errors.unkownWaybill");
        }
        Waybill waybill = waybillService.getWaybillAndCheckExist(waybillId);

        ConfParamOption option = confParamService.findParamOption(Constants.REPORT_INFO_TYPE_KEY,
                reportInfo.getReportInfoType() + "");
        if (null != option && StringUtils.isNotBlank(option.getOptionDescribed())
                && Constants.REQUIRED.equals(option.getOptionDescribed().trim())
                && StringUtils.isBlank(reportInfo.getRemark())) {
            throw new BusinessException("reportInfoRemarkRequired", "reportInfo.error.reportInfoRemarkRequired");
        }

        // 基础信息
        reportInfo.setWaybilNo(waybill.getWaybillNo());
        reportInfo.setDriverId(waybill.getDriverId());
        reportInfo.setCustomerId(waybill.getCustomerId());
        reportInfo.setCustomerName(waybill.getCustomerName());

        // 车辆信息
        Truck truck = truckService.getTruck(waybill.getTruckId());
        if (null != truck) {
            reportInfo.setPlateNumber(truck.getPlateNumber());
        }
        reportInfoDao.insert(reportInfo);
        return reportInfo.getReportInfoId();
    }

    @Override
    public void insertPageAndDetail(ReportInfoParam reportInfoParam, LoginUser loginUser) throws BusinessException {
        // 验证
        Integer waybillId = reportInfoParam.getWaybillId();
        if (null == waybillId) {
            throw new BusinessException("unkownWaybill", "errors.unkownWaybill");
        }
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            throw new BusinessException("waybill.error.notfound", "waybill.error.notfound");
        }
        if (null == reportInfoParam.getReportInfoType()) {
            throw new BusinessException("reportInfoTypeRequire", "reportInfo.error.reportInfoTypeRequire");
        }

        Integer reportInfoId = null;

        ReportInfo reportInfo = findByDriverIdAndWaybillId(reportInfoParam.getReportInfoType(), waybill.getDriverId(),
                waybillId, loginUser);
        // 路况报备中间表，没有就添加
        if (null == reportInfo) {
            ReportInfo report = new ReportInfo();
            report.setWaybillId(waybillId);
            report.setDriverId(waybill.getDriverId());
            report.setRemark(reportInfoParam.getRemark());
            report.setFirstReportTime(
                    reportInfoParam.getFirstReportTime() == null ? new Date() : reportInfoParam.getFirstReportTime());
            report.setReportInfoType(reportInfoParam.getReportInfoType());
            report.setTenantId(waybill.getTenantId());
            report.setTenantCode(waybill.getTenantCode());
            report.setAreaCode(waybill.getAreaCode());
            report.setRegionCode(waybill.getRegionCode());

            reportInfoId = insert(report, loginUser);
        } else {
            reportInfoId = reportInfo.getReportInfoId();
        }

        // 添加详细信息
        ReportInfoDetails detail = new ReportInfoDetails();
        detail.setReportInfoId(reportInfoId);
        detail.setReportInfoType(reportInfoParam.getReportInfoType());
        // GPS转为高德坐标
        String coordinate = gaoDeMapService.convertCoordinate(reportInfoParam.getCoordinate(),
                reportInfoParam.getCoordsys());
        detail.setCoordinate(coordinate);
        detail.setDeviceNo(reportInfoParam.getDeviceNo());
        detail.setDeviceType(reportInfoParam.getDeviceType());
        detail.setReportTime(
                reportInfoParam.getFirstReportTime() == null ? new Date() : reportInfoParam.getFirstReportTime());
        detail.setRemark(reportInfoParam.getRemark());

        // 图片列表
        detail.setImageUrlList(reportInfoParam.getImageUrlList());
        reportInfoDetailService.insert(detail, loginUser);

        // 推送通知
        pushReportInfoMsg(waybillId, reportInfoParam.getReportInfoType(), loginUser);

        // 同步给转运方
        this.modifyTransformBillReportInfo(waybillId, reportInfoId, loginUser);
    }

    // 同步给转运方
    private void modifyTransformBillReportInfo(Integer transformBillLinkId, Integer vendorReportInfoId,
            LoginUser loginUser) {
        if (null == transformBillLinkId || null == vendorReportInfoId) {
            return;
        }

        // 获取转运方运单ID
        Waybill waybill = waybillCommonService.findWaybillByTransformBillId(transformBillLinkId);
        if (null == waybill) {
            return;
        }

        ReportInfo vendorReportInfo = this.getReportInfo(vendorReportInfoId);
        if (null == vendorReportInfo) {
            return;
        }

        // 重新组装loginUser
        loginUser.setTenantId(waybill.getTenantId());
        loginUser.setTenantCode(waybill.getTenantCode());

        ReportInfo reportInfo = findByDriverIdAndWaybillId(vendorReportInfo.getReportInfoType(),
                vendorReportInfo.getDriverId(), waybill.getWaybillId(), loginUser);

        Integer reportInfoId = null;
        // 路况报备中间表，没有就添加
        if (null == reportInfo) {
            // 将承运商的报备信息替换我转运方的信息
            vendorReportInfo.setReportInfoId(null);
            vendorReportInfo.setWaybillId(waybill.getWaybillId());
            vendorReportInfo.setWaybilNo(waybill.getWaybillNo());
            vendorReportInfo.setCustomerId(waybill.getCustomerId());
            vendorReportInfo.setCustomerName(waybill.getCustomerName());
            vendorReportInfo.setTenantId(waybill.getTenantId());
            vendorReportInfo.setTenantCode(waybill.getTenantCode());
            vendorReportInfo.setAreaCode(waybill.getAreaCode());
            vendorReportInfo.setRegionCode(waybill.getRegionCode());
            reportInfoDao.insert(vendorReportInfo);
            reportInfoId = vendorReportInfo.getReportInfoId();
        } else {
            reportInfoId = reportInfo.getReportInfoId();
        }

        // 详情
        List<ReportInfoDetails> detailList = reportInfoDetailService.ListByReportId(vendorReportInfoId);
        for (ReportInfoDetails details : detailList) {
            details.setReportInfoId(reportInfoId);
            reportInfoDetailService.insert(details, loginUser);
        }

        // 推送通知
        pushReportInfoMsg(waybill.getWaybillId(), vendorReportInfo.getReportInfoType(), loginUser);
    }

    // 推送报备消息通知
    private void pushReportInfoMsg(Integer waybillId, Integer reportInfoType, LoginUser loginUser) {
        Waybill waybill = waybillService.getWaybill(waybillId);
        if (null == waybill) {
            return;
        }
        Map<String, Object> extras = messagePushService.buildAppMsgParameter(waybillId);
        extras.put("planDeliveryDate", DateUtil.format(waybill.getPlanDeliveryTime(), DateUtil.YYYYMMDDHHMM));
        ConfParamOption option = confParamService.findParamOption(Constants.REPORT_INFO_TYPE_KEY, reportInfoType + "");
        if (null != option) {
            extras.put("reportInfoType", option.getOptionName());
        }

        // 获取客户经理
        User user = employeeService.loadUserByEmployeeId(waybill.getCustomerManagerId(), loginUser);
        if (null != user) {
            messagePushService.pushAppMessage("DRIVER_REPORT_INFO", extras,loginUser, user.getUserId() + "");
        }
    }

    @Override
    public ReportInfo findByDriverIdAndWaybillId(Integer reportInfoType, Integer driverId, Integer waybillId,
            LoginUser loginUser) {
        if (null == driverId || null == waybillId) {
            return null;
        }
        ReportInfo report = new ReportInfo();
        report.setReportInfoType(reportInfoType);
        report.setWaybillId(waybillId);
        report.setDriverId(driverId);
        report.setTenantId(loginUser.getTenantId());
        List<ReportInfo> list = reportInfoDao.findByExample(report);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReportInfo getReportInfo(Integer reportInfoId) {
        if (null == reportInfoId) {
            return null;
        }
        return reportInfoDao.get(reportInfoId);
    }

    @Override
    public List<ReportInfo> findByWaybillId(Integer waybillId, LoginUser loginUser) {
        ReportInfo example = new ReportInfo();
        example.setWaybillId(waybillId);
        example.setTenantId(loginUser.getTenantId());
        return reportInfoDao.findByExample(example);
    }

    @Override
    public List<ConfParamOption> listRepotInfoType() {
        return confParamService.findParamOptions(Constants.REPORT_INFO_TYPE_KEY);
    }

    @Override
    public List<ReportInfoDetails> listAllReportInfoByWaybillId(Integer waybillId, LoginUser loginUser) {
        List<ReportInfoDetails> result = new ArrayList<ReportInfoDetails>();

        // 所有的报备类型，组装成MAP
        Map<String, String> mapRepotInfoType = new HashMap<String, String>();
        List<ConfParamOption> listRepotInfoType = listRepotInfoType();
        for (ConfParamOption option : listRepotInfoType) {
            mapRepotInfoType.put(option.getOptionValue(), option.getOptionName());
        }

        List<ReportInfo> reportInfoList = findByWaybillId(waybillId, loginUser);
        for (ReportInfo reportInfo : reportInfoList) {
            String reportInfoTypeView = mapRepotInfoType.get(reportInfo.getReportInfoType() + "");

            if (Constants.UPDATE_DELIVERY_ADDRESS_REPORT_TYPE.equals(reportInfo.getReportInfoType())) {
                ReportInfoDetails detail = new ReportInfoDetails();
                detail.setReportTime(reportInfo.getFirstReportTime());
                detail.setReportInfoType(reportInfo.getReportInfoType());
                detail.setReportInfoTypeView(reportInfoTypeView);
                result.add(detail);
                continue;
            }

            List<ReportInfoDetails> listDetails = reportInfoDetailService.ListByReportId(reportInfo.getReportInfoId());
            if (listDetails.isEmpty()) {
                ReportInfoDetails detail = new ReportInfoDetails();
                detail.setReportTime(reportInfo.getFirstReportTime());
                detail.setReportInfoType(reportInfo.getReportInfoType());
                detail.setReportInfoTypeView(reportInfoTypeView);
                result.add(detail);
                continue;
            }

            for (ReportInfoDetails reportInfoDetails : listDetails) {
                reportInfoDetails.setReportInfoTypeView(reportInfoTypeView);
            }
            result.addAll(listDetails);
        }

        // 根据报备时间排序
        Collections.sort(result, new Comparator<ReportInfoDetails>() {
            @Override
            public int compare(ReportInfoDetails o1, ReportInfoDetails o2) {
                return o1.getReportTime().compareTo(o2.getReportTime());
            }
        });

        return result;
    }

    @Override
    public List<Object> waybillStatistics(String date, Map<String, Object> freightMap, Map<String, Object> incomeMap,
        Map<String, Object> customerMap, LoginEmployee loginEmployee) {

        List<Object> resultList = new ArrayList<Object>();

        String freightParamStr = JSON.toJSONString(makeParam(freightMap));
        String freightRequestUrl = freightUrl + "&data=" + freightParamStr;
        log.info("经纪人运费和单量URL:" + freightRequestUrl);
        JSONObject freightResult = requestGet(freightRequestUrl);
        log.info("经纪人运费和单量结果:" + freightResult);

        String incomeParamStr = JSON.toJSONString(makeParam(incomeMap));
        String incomeRequestUrl = incomeUrl + "&data=" + incomeParamStr;
        log.info("货物收益分析top10URL:" + incomeRequestUrl);
        JSONObject incomeResult = requestGet(incomeRequestUrl);
        log.info("货物收益分析top10结果:" + incomeResult);

        String customerParamStr = JSON. toJSONString(makeParam(customerMap));
        String customerRequestUrl = customerUrl + "&data=" + customerParamStr;
        log.info("客户top排行URL:" + customerRequestUrl);
        JSONObject customerResult = requestGet(customerRequestUrl);
        log.info("客户top排行结果:" + customerResult);

        resultList.add(freightResult);
        resultList.add(incomeResult);
        resultList.add(customerResult);
        return resultList;
    }

    public List<Object> makeParam(Map<String, Object> innnerMap){

        List<Object> objectList = new ArrayList<Object>();
        Map<String, Map<String, Object>> paramMap = new HashMap<String, Map<String, Object>>();

        paramMap.put("filters", innnerMap);
        objectList.add(paramMap);
        return objectList;
    }

    private JSONObject requestGet(String httpUrl) {
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            httpURLConnection.setConnectTimeout(5000);
            InputStream in = httpURLConnection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int buffer = 1024;
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = in.read(b, 0, buffer)) > 0) {
                bos.write(b, 0, n);
            }
            String result = new String(bos.toByteArray(), "UTF-8");
            bos.close();
            in.close();
            httpURLConnection.disconnect();
            JSONObject jsObject = JSONObject.parseObject(result);
            return jsObject;
        } catch (Exception e) {
            log.warn("requestGet ERROR!", e);
        }
        return null;
    }
}
