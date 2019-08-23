package com.juma.tgm.cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.dd.dataInterface.domain.export.ColumnType;
import com.juma.dd.dataInterface.domain.export.CompareType;
import com.juma.tgm.cms.dao.CustomizedExportMapper;
import com.juma.tgm.cms.dao.ExportTaskDao;
import com.juma.tgm.cms.domain.ExportTask;
import com.juma.tgm.cms.domain.ExportTask.TaskSign;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.Constants.WaybillQuickQueryParameterEnum;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.export.domain.*;
import com.juma.tgm.project.common.LogisticsProduct;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.tools.service.UploadTmsService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.driver.enumeration.DriverTypeEnum;
import com.juma.vms.truck.enumeration.TruckRunTypeEnum;
import com.juma.vms.vendor.enumeration.VendorSourceEnum;
import com.juma.vms.vendor.enumeration.VendorTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ExportTaskServiceImpl implements ExportTaskService {

    private final Logger log = LoggerFactory.getLogger(ExportTaskServiceImpl.class);
    private static List<ExportDynamicParameter> listExportDynamicParameter;
    private static Map<String, SelectParam> selectParamMap;
    private static String BASE_LOGISTICS_LABEL = "BASE_LOGISTICS_LABEL";
    @Resource
    private ExportTaskDao exportTaskDao;

    @Resource
    private UploadTmsService uploadTmsService;

    @Autowired
    private CustomizedExportMapper customizedExportMapper;

    @Resource
    private CrmCommonService crmCommonService;

    @Resource
    private VmsCommonService vmsCommonService;

    @Override
    public Page<ExportTask> search(PageCondition pageCondition, LoginUser loginUser) {
        int total = exportTaskDao.searchCount(pageCondition);
        List<ExportTask> rows = exportTaskDao.search(pageCondition);
        for (ExportTask exportTask : rows) {
            exportTask.setStatusName(ExportTask.Status.getDescByCode(exportTask.getStatus()));
            if (exportTask.getCostTime() != null) {
                BigDecimal divide = new BigDecimal(exportTask.getCostTime().toString()).divide(new BigDecimal("1000"),
                        1, BigDecimal.ROUND_HALF_UP);
                if (divide.compareTo(new BigDecimal("60")) == -1) {
                    exportTask.setCostDate(divide + "秒");
                    continue;
                }
                int minute = divide.intValue() / 60;
                exportTask.setCostDate(minute + "分钟" + (divide.subtract(new BigDecimal((60 * minute) + ""))) + "秒");
            }
        }
        return new Page<ExportTask>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public ExportTask getExportTask(Integer exportTaskId) {
        if (null == exportTaskId) {
            return null;
        }
        return exportTaskDao.get(exportTaskId);
    }

    @Override
    public void insert(ExportTask exportTask, LoginUser loginUser) throws BusinessException {
        exportTask.setCreateUserId(loginUser.getUserId());
        exportTask.setCreateTime(new Date());
        exportTask.setIsDelete(false);
        exportTaskDao.insert(exportTask);
    }

    @Override
    public void update(ExportTask exportTask, LoginUser loginUser) throws BusinessException {
        exportTaskDao.update(exportTask);
    }

    @Override
    public void delete(Integer exportTaskId, LoginUser loginUser) throws BusinessException {
        ExportTask exportTask = new ExportTask();
        exportTask.setExportTaskId(exportTaskId);
        exportTask.setIsDelete(true);
        update(exportTask, loginUser);
    }

    @Override
    public Integer insertInit(TaskSign taskSign, ExportParam exportParam, LoginUser loginUser)
            throws BusinessException {
        ExportTask exportTask = new ExportTask();
        exportTask.setTaskSign(taskSign.getCode());
        exportTask.setName(taskSign.getDesc());
        exportTask.setStatus(ExportTask.Status.DOWN_LOADING.getCode());
        String md5Digest = null;
        if (null != exportParam) {
            String jsonString = JSON.toJSONString(exportParam);
            md5Digest = DigestUtils.md5Hex(jsonString);
            exportTask.setRemark(jsonString);
            exportTask.setMd5Digest(md5Digest);
        }

        // 同一个人、相同类型、条件的数据导出，需要间隔一段时间
        frequentlySameExport(md5Digest, taskSign.getCode(), loginUser);

        insert(exportTask, loginUser);
        return exportTask.getExportTaskId();
    }

    // 同一个人、相同类型、条件的数据导出，需要间隔一段时间
    private void frequentlySameExport(String md5Digest, int taskSign, LoginUser loginUser) {
        ExportTask task = findLastTaskBySign(md5Digest, taskSign, loginUser);
        if (null != task && null != task.getCreateTime()
                && (new Date().getTime() - task.getCreateTime().getTime() < 30 * 1000)) {
            throw new BusinessException("sameParamDataExportTooFrequently",
                    "exportTask.error.sameParamDataExportTooFrequently");
        }
    }

    @Override
    public void failed(Integer exportTaskId, String failedReson, LoginUser loginUser) throws BusinessException {
        ExportTask exportTask = new ExportTask();
        exportTask.setExportTaskId(exportTaskId);
        exportTask.setFailedReson(failedReson);
        exportTask.setStatus(ExportTask.Status.DOWN_FAILED.getCode());
        update(exportTask, loginUser);
    }

    @Override
    public void uploadExcelAndUpdateExportTask(Integer exportTaskId, String fileName, Object obj, Class<?> T,
            long startTime, LoginUser loginUser) throws BusinessException {
        if (null == obj) {
            failed(exportTaskId, "没有数据, 不能导出", loginUser);
            return;
        }

        String fileUrl = uploadTmsService.uploadExcel(fileName, obj, T, exportTaskId, loginUser);

        if (StringUtils.isBlank(fileUrl)) {
            return;
        }

        ExportTask exportTask = new ExportTask();
        exportTask.setExportTaskId(exportTaskId);
        exportTask.setFileUrl(fileUrl);
        exportTask.setStatus(ExportTask.Status.DOWN_FINISH.getCode());
        exportTask.setCostTime(System.currentTimeMillis() - startTime);
        update(exportTask, loginUser);
    }

    @Override
    public ExportTask findLastTaskBySign(String md5Digest, int taskSign, LoginUser loginUser) {
        if (null == loginUser) {
            return null;
        }

        ExportTask example = new ExportTask();
        example.setMd5Digest(md5Digest);
        example.setTaskSign(taskSign);
        example.setCreateUserId(loginUser.getUserId());
        example.setStartTime(DateUtil.dayStartReturnDate(new Date()));
        example.setIsDelete(false);
        List<ExportTask> list = exportTaskDao.findByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ExportDynamicParameter> exportFields(LoginUser loginUser) {

        List<CustomizedExport> customizedExports = this.listCustomzedExport(loginUser);
        if (customizedExports.isEmpty()) {
            return this.isShowLogisticsLabel(listExportDynamicParameter, loginUser);
        }

        CustomizedExport customizedExport = customizedExports.get(0);
        List<ExportDynamicParameter> exportDynamicParameter = JSONArray
                .parseArray(customizedExport.getCustomizedExportJson(), ExportDynamicParameter.class);
        List<String> checked = new ArrayList<String>();
        for (ExportDynamicParameter ep : exportDynamicParameter) {
            checked.add(ep.getKey());
        }

        List<ExportDynamicParameter> exportDynamicParameters
                = this.isShowLogisticsLabel(listExportDynamicParameter, loginUser);
        for (ExportDynamicParameter edp : exportDynamicParameters) {
            for (ExportDynamicParameter e : edp.getChildren()) {
                e.setChecked(false);
                if (checked.contains(e.getKey())) {
                    e.setChecked(true);
                }
            }
        }
        return exportDynamicParameters;
    }

    private List<CustomizedExport> listCustomzedExport(LoginUser loginUser) {
        CustomizedExportExample example = new CustomizedExportExample();
        CustomizedExportExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(loginUser.getTenantId()).andUserIdEqualTo(loginUser.getUserId());
        return customizedExportMapper.selectByExample(example);
    }

    // 若当前租户不需要物流产品标签，则导出不在显示
    private List<ExportDynamicParameter> isShowLogisticsLabel(List<ExportDynamicParameter> list, LoginUser loginUser) {
        boolean showLogisticsProduct = crmCommonService.isShowLogisticsProduct(loginUser);
        boolean isStopLoop = false;
        for (ExportDynamicParameter p : list) {
            if (!p.getKey().equals("basic")) {
                continue;
            }

            List<ExportDynamicParameter> children = p.getChildren();
            Iterator<ExportDynamicParameter> it = children.iterator();
            while(it.hasNext()){
                ExportDynamicParameter ep = it.next();
                if (BASE_LOGISTICS_LABEL.equals(ep.getKey())) {
                    // 先删除物流产品标签
                    it.remove();
                    isStopLoop = true;
                }
            }

            // 若租户需要物流产品标签，则拼接
            if (showLogisticsProduct) {
                children.add(new ExportDynamicParameter(BASE_LOGISTICS_LABEL, "物流产品", 11, false));
            }

            // 排序
            Collections.sort(children,new Comparator<ExportDynamicParameter>(){
                public int compare(ExportDynamicParameter arg0, ExportDynamicParameter arg1) {
                    return arg0.getOrderNo().compareTo(arg1.getOrderNo());
                }
            });

            if (isStopLoop) {
                break;
            }
        }

        return list;
    }

    @Override
    public Object doExport(ExportParam exportParam, LoginUser loginUser) {
        ExportParams exportParams = new ExportParams();
        exportParams.setTenantId(loginUser.getTenantId());
        exportParams.setSystemId(2);
        exportParams.setUserId(loginUser.getUserId());
        exportParams.setFileName("waybill" + DateUtil.format(DateUtil.YYYYMMDDHHMMSS_SIMPLE));

        // 查询条件
        exportParams.setFilterParamList(this.buildFilterParam(exportParam.getFilters(), loginUser));
        // 所选字段
        exportParams.setSelectParamList(this.buildSelectParam(exportParam.getExportDynamicParameters(), loginUser));

        log.info("ExportTaskService->doExport:{}", JSON.toJSONString(exportParams));
        // 去数据中心导出数据
        Object post = this.post(exportParams);
        // 保存最后一次导出字段
        saveCustomizedExport(exportParam, loginUser);
        return post;
    }

    private void saveCustomizedExport(ExportParam exportParam, LoginUser loginUser) {
        List<CustomizedExport> customizedExports = listCustomzedExport(loginUser);
        if (customizedExports.isEmpty()) {
            CustomizedExport customizedExport = new CustomizedExport();
            customizedExport.setTenantId(loginUser.getTenantId());
            customizedExport.setTenantCode(loginUser.getTenantCode());
            customizedExport.setUserId(loginUser.getUserId());
            customizedExport.setCustomizedExportJson(JSON.toJSONString(exportParam.getExportDynamicParameters()));
            customizedExport.setLastUpdateTime(new Date());
            customizedExport.setLastUpdateUserId(loginUser.getUserId());
            customizedExport.setIsDelete(false);
            customizedExport.setCreateTime(new Date());
            customizedExport.setCreateUserId(loginUser.getUserId());
            customizedExportMapper.insert(customizedExport);
        } else {
            CustomizedExport ce = customizedExports.get(0);
            ce.setCustomizedExportJson(JSON.toJSONString(exportParam.getExportDynamicParameters()));
            ce.setLastUpdateTime(new Date());
            ce.setLastUpdateUserId(loginUser.getUserId());
            customizedExportMapper.updateByPrimaryKey(ce);
        }
    }

    // 导出调用
    private Object post(ExportParams exportParams) {
        if (null == exportParams) {
            return null;
        }
        String json = JSON.toJSONString(exportParams);
        log.info("exportParams jpush param : {}.", json);
        try {
            String str = Request.Post(Constants.DATA_EXPORT_BASE_URL + "/customExport.html")
                    .socketTimeout(7000).connectTimeout(5000)
                    .bodyString(json, ContentType.APPLICATION_JSON).execute()
                    .handleResponse(new ResponseHandler<String>() {
                        @Override
                        public String handleResponse(HttpResponse response)
                                throws ClientProtocolException, IOException {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
                        }
                    });
            log.info("exportParams jpush return : {}.", str);
            if (null != str) {
                return JSON.parseObject(str);
            }
        } catch (Exception e) {
            log.warn("exportParams jpush error, errorMsg:{}", e.getMessage());
        }
        return null;
    }

    // 构建导出列表条件
    private List<FilterParam> buildFilterParam(Map<String, Object> filters, LoginUser loginUser) {
        List<FilterParam> FilterParams = new ArrayList<FilterParam>();
        if (null == filters || filters.isEmpty()) {

            FilterParams.add(
                    new FilterParam("tgm", "waybill", "area_code", CompareType.LikeOr.getCompareCode(), null, "-999"));
            return FilterParams;
        }

        // 固定条件
        // FilterParams.add(new FilterParam("tgm", "waybill", "status",
        // CompareType.NotEqual.getCompareCode(),
        // ColumnType.NUMBER.getColumnTypeCode(),
        // Waybill.Status.PENDING_EXAMINE.getCode() + ""));
        FilterParams.add(new FilterParam("tgm", "waybill", "tenant_id", CompareType.Equal.getCompareCode(),
                ColumnType.NUMBER.getColumnTypeCode(), loginUser.getTenantId() + ""));
        FilterParams.add(new FilterParam("tgm", "waybill", "is_delete", CompareType.Equal.getCompareCode(),
                ColumnType.NUMBER.getColumnTypeCode(), "0"));

        // 业务区域
        FilterParams.add(new FilterParam("tgm", "waybill", "area_code", CompareType.LikeOr.getCompareCode(), null,
                filters.get("areaCodeList") == null ? "-999" : filters.get("areaCodeList").toString()));

        // 是否查询项目单
        if (null != filters.get("isQueryProjectWaybill")
                && StringUtils.isNotBlank(filters.get("isQueryProjectWaybill").toString())) {
            String str = filters.get("isQueryProjectWaybill").toString();
            if (str.equals("1")) {
                FilterParams.add(new FilterParam("tgm", "waybill", "project_id", CompareType.IsNull.getCompareCode(),
                        null, null));
            } else if (str.equals("2")) {
                FilterParams.add(new FilterParam("tgm", "waybill", "project_id", CompareType.IsNotNull.getCompareCode(),
                        null, null));
            }
        }

        // 开始时间
        if (null != filters.get("startTime")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "plan_delivery_time",
                    CompareType.EqualOrGreater.getCompareCode(), null, filters.get("startTime").toString()));
        }

        // 结束时间
        if (null != filters.get("endTime")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "plan_delivery_time",
                    CompareType.EqualOrLess.getCompareCode(), null, filters.get("endTime").toString()));
        }

        // 运单完成开始时间
        if (null != filters.get("startFinishTime")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "finish_time",
                CompareType.EqualOrGreater.getCompareCode(), null, filters.get("startFinishTime").toString()));
        }

        // 运单完成结束时间
        if (null != filters.get("endFinishTime")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "finish_time",
                CompareType.EqualOrLess.getCompareCode(), null, filters.get("endFinishTime").toString()));
        }

        // 下单渠道
        if (null != filters.get("waybillSource")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "waybill_source", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("waybillSource").toString()));
        }

        // 付款方式
        if (null != filters.get("receiptType")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "receipt_type", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("receiptType").toString()));
        }

        // 运单号
        if (null != filters.get("waybillNo")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "waybill_no", CompareType.Like.getCompareCode(), null,
                    filters.get("waybillNo").toString()));
        }

        // 派车方式
        if (null != filters.get("receiveWay")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "receive_way", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("receiveWay").toString()));
        }




        // 司机ID
        if (null != filters.get("driverId")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "driver_id", CompareType.Equal.getCompareCode(), null,
                    filters.get("driverId").toString()));
        }

        // 司机姓名: 兼容
        if (null != filters.get("driverName")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "driver_name", CompareType.Like.getCompareCode(), null,
                    filters.get("driverName").toString()));
        }

        // 司机电话：兼容
        if (null != filters.get("driverPhone") && StringUtils.isNumeric(filters.get("driverPhone").toString())) {
            Driver driver = vmsCommonService.loadDriverByPhone(filters.get("driverPhone").toString());
            if (null != driver) {
                FilterParams.add(new FilterParam("tgm", "waybill", "driver_id", CompareType.Equal.getCompareCode(), null,
                        driver.getDriverId().toString()));
            }
        }


        // 用车人姓名
//        if (null != filters.get("truckCustomerName")) {
//            FilterParams.add(new FilterParam("tgm", "truck_customer", "nickname", CompareType.Like.getCompareCode(),
//                    null, filters.get("truckCustomerName").toString()));
//        }
//
//        // 用车人电话
//        if (null != filters.get("truckCustomerPhone")) {
//            FilterParams.add(new FilterParam("tgm", "truck_customer", "contact_phone",
//                    CompareType.Equal.getCompareCode(), null, filters.get("truckCustomerPhone").toString()));
//        }

        // 项目经理姓名
        if (null != filters.get("projectManagerUserName")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "project_manager_user_name",
                    CompareType.Like.getCompareCode(), null, filters.get("projectManagerUserName").toString()));
        }

        // 项目经理电话
        if (null != filters.get("projectManagePhone")) {
            FilterParams.add(new FilterParam("auth", "user", "mobile_number", CompareType.Equal.getCompareCode(), null,
                    filters.get("projectManagePhone").toString()));
        }

        // 客户名称
        if (null != filters.get("customerId")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "customer_id", CompareType.Equal.getCompareCode(), null,
                    filters.get("customerId").toString()));
        }

        // 项目名称
        if (null != filters.get("projectId")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "project_id", CompareType.Equal.getCompareCode(), null,
                    filters.get("projectId").toString()));
        }

        // 车牌号
        if (null != filters.get("plateNumber")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "plate_number", CompareType.Like.getCompareCode(), null,
                    filters.get("plateNumber").toString()));
        }

        // 回单状态
        if (null != filters.get("needReceipt")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "need_receipt", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("needReceipt").toString()));
        }

        // 运单类型
        if (null != filters.get("businessBranch")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "business_branch", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("businessBranch").toString()));
        }

        // 是否测试
        if (null != filters.get("isTest")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "is_test", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("isTest").toString()));
        }

        // 派车待定
        if (null != filters.get("status")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "status", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("status").toString()));
        }

        // 司机类型
        if (null != filters.get("driverType")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "driver_type", CompareType.Equal.getCompareCode(),
                    ColumnType.NUMBER.getColumnTypeCode(), filters.get("driverType").toString()));
        }

        // 物流产品
        if (null != filters.get("logisticsLabel")) {
            FilterParams.add(new FilterParam("tgm", "waybill", "logistics_label", CompareType.Equal.getCompareCode(),
                    null, filters.get("logisticsLabel").toString()));
        }

        // 运单状态、客户对账状态、客户收款状态、承运商对账状态、承运商结算状态
        for (WaybillQuickQueryParameterEnum p : Constants.WaybillQuickQueryParameterEnum.values()) {
            String lowerCase = p.toString().toLowerCase();
            if (null != filters.get(lowerCase)) {
                FilterParams.add(new FilterParam("tgm", "waybill", p.getColumnName(), CompareType.In.getCompareCode(),
                        ColumnType.NUMBER.getColumnTypeCode(), filters.get(lowerCase).toString()));
            }
        }

        return FilterParams;
    }

    // 构建所选字段
    private List<SelectParam> buildSelectParam(List<ExportDynamicParameter> parameters, LoginUser loginUser) {
        List<SelectParam> selectParams = new ArrayList<SelectParam>();
        Map<String, String> mapLogisticsLabel = this.mapLogisticsLabel(loginUser);
        for (ExportDynamicParameter parameter : parameters) {
            SelectParam param = selectParamMap.get(parameter.getKey());
            if (null == param) {
                continue;
            }

            // 物流产品标签
            if (parameter.getKey().equals(BASE_LOGISTICS_LABEL)) {
                param.setConvertMap(mapLogisticsLabel);
            }

            param.setShowName(parameter.getName());
            selectParams.add(param);
        }
        return selectParams;
    }

    // 司机类型
    private static Map<String, String> driverTypeMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (DriverTypeEnum driverTypeEnum : DriverTypeEnum.values()) {
            map.put(driverTypeEnum.getCode() + "", driverTypeEnum.getDesc());
        }
        return map;
    }

    // 承运商运作方式
    private static Map<String, String> vendorSourceMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (VendorSourceEnum vendorSourceEnum : VendorSourceEnum.values()) {
            map.put(vendorSourceEnum.getCode() + "", vendorSourceEnum.getDesc());
        }
        return map;
    }

    // 承运商类型
    private static Map<String, String> vendorTypeMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (VendorTypeEnum vendorTypeEnum : VendorTypeEnum.values()) {
            map.put(vendorTypeEnum.getCode() + "", vendorTypeEnum.getDesc());
        }
        return map;
    }

    // 车辆类型
    private static Map<String, String> vehicleTypeMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (TruckRunTypeEnum v : TruckRunTypeEnum.values()) {
            map.put(v.getCode() + "", v.getDesc());
        }
        return map;
    }

    // 是、否
    private static Map<String, String> trueAndFalseMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("0", "否");
        map.put("1", "是");
        return map;
    }

    // 物流产品标签
    private Map<String, String> mapLogisticsLabel(LoginUser loginUser) {
        Map<String, String> map = new HashMap<>();
        List<LogisticsProduct> logisticsProductList = crmCommonService.listLogisticsProduct(loginUser);
        for (LogisticsProduct l : logisticsProductList) {
            map.put(l.getCode(), l.getName());
        }
        return map;
    }

    static {
        listExportDynamicParameter = new ArrayList<ExportDynamicParameter>();
        // 基本信息
        ExportDynamicParameter parameter = new ExportDynamicParameter("basic", "基本信息", 1, false);
        parameter.getChildren().add(new ExportDynamicParameter("basic_waybillNo", "运单号", 1, true));
        parameter.getChildren().add(new ExportDynamicParameter("basic_statusView", "配送状态", 2, false));
        parameter.getChildren()
                .add(new ExportDynamicParameter("basic_receivableReconcilicationStatus", "客户对账状态", 3, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_receiptStatus", "客户收款状态", 4, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_reconciliationStatus", "承运商对账状态", 5, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_settlementStatus", "承运商结算状态", 6, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_businessBranch", "运单类型", 7, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_areaCode", "业务范围", 8, true));
        parameter.getChildren().add(new ExportDynamicParameter("basic_customerName", "所属客户", 9, true));
        parameter.getChildren().add(new ExportDynamicParameter("basic_projectName", "所属项目", 10, false));
//        parameter.getChildren().add(new ExportDynamicParameter(BASE_LOGISTICS_LABEL, "物流产品", 11, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_contactName", "联系人", 12, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_contactPhone", "联系人电话", 13, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_customerManagerName", "客户经理", 14, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_mobileNumber", "客户经理电话", 15, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_createTime", "下单时间", 16, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_waybillSource", "下单渠道", 17, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_orderNum", "订单号", 18, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_crmCustomerId", "客户编号", 19, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_receiveWay", "派车方式", 20, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_createUserName", "下单人", 21, false));
        parameter.getChildren().add(new ExportDynamicParameter("basic_projectManagerUserName", "项目经理", 22, false));
        listExportDynamicParameter.add(parameter);

        // 货物信息
        ExportDynamicParameter goodsParameter = new ExportDynamicParameter("goods", "货物信息", 2, false);
        goodsParameter.getChildren().add(new ExportDynamicParameter("goods_goodsType", "货物类型", 1, false));
        goodsParameter.getChildren().add(new ExportDynamicParameter("goods_goodsWeight", "重量", 2, false));
        goodsParameter.getChildren().add(new ExportDynamicParameter("goods_goodsVolume", "方量", 3, false));
        goodsParameter.getChildren().add(new ExportDynamicParameter("goods_goodsAmount", "件数", 4, false));
        goodsParameter.getChildren().add(new ExportDynamicParameter("goods_goodsCheck", "已检查货物", 5, false));
        listExportDynamicParameter.add(goodsParameter);

        // 路线信息
        ExportDynamicParameter addressParameter = new ExportDynamicParameter("address", "路线信息", 3, false);
        addressParameter.getChildren().add(new ExportDynamicParameter("address_addressDetail", "取货地", 1, false));
        addressParameter.getChildren().add(new ExportDynamicParameter("address_contactName", "取货联系人", 2, false));
        addressParameter.getChildren().add(new ExportDynamicParameter("address_contactPhone", "联系人电话", 3, false));
        addressParameter.getChildren().add(new ExportDynamicParameter("address_estimateDistance", "下单里程", 4, false));
        addressParameter.getChildren().add(new ExportDynamicParameter("address_actualMileage", "实际里程", 5, false));
        addressParameter.getChildren().add(new ExportDynamicParameter("address_reserveAddressCount", "配送点", 6, false));
        addressParameter.getChildren()
                .add(new ExportDynamicParameter("address_reserveAddressDetails", "配送地", 7, false));
        listExportDynamicParameter.add(addressParameter);

        // 用车人信息
        ExportDynamicParameter truckCustomerParameter = new ExportDynamicParameter("truckCustomer", "用车人信息", 4, false);
        truckCustomerParameter.getChildren()
                .add(new ExportDynamicParameter("truckCustomer_nickname", "用车人姓名", 1, false));
        truckCustomerParameter.getChildren()
                .add(new ExportDynamicParameter("truckCustomer_contactPhone", "用车人电话", 2, false));
        listExportDynamicParameter.add(truckCustomerParameter);

        // 用车要求
        ExportDynamicParameter truckRequireParameter = new ExportDynamicParameter("truckRequire", "用车要求", 5, false);
        truckRequireParameter.getChildren()
                .add(new ExportDynamicParameter("truckRequire_planDeliveryTime", "用车时间", 1, true));
        truckRequireParameter.getChildren()
                .add(new ExportDynamicParameter("truckRequire_finishTime", "完成时间", 2, false));
        truckRequireParameter.getChildren().add(new ExportDynamicParameter("truckRequire_requireType", "车型", 3, false));
        truckRequireParameter.getChildren()
                .add(new ExportDynamicParameter("truckRequire_entryLicense", "是否入城", 4, false));
        truckRequireParameter.getChildren()
                .add(new ExportDynamicParameter("truckRequire_needReceipt", "是否回单", 5, false));
        truckRequireParameter.getChildren().add(new ExportDynamicParameter("waybill_needReceipt", "回单状态", 6, false));
        truckRequireParameter.getChildren()
                .add(new ExportDynamicParameter("truckRequire_isNeedBackStorage", "是否返仓", 7, false));
        truckRequireParameter.getChildren()
                .add(new ExportDynamicParameter("truckRequire_isCollectPayment", "代收款", 8, false));
        truckRequireParameter.getChildren().add(new ExportDynamicParameter("truckRequire_remark", "备注", 9, false));
        listExportDynamicParameter.add(truckRequireParameter);

        // 运力信息
        ExportDynamicParameter capacityParameter = new ExportDynamicParameter("capacity", "运力信息", 6, false);
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_driverName", "司机姓名", 1, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_driverType", "司机类型", 2, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_vehicleType", "车辆类型", 3, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_contactPhone", "司机电话", 4, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_plateNumber", "车牌号", 5, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_actualType", "车型", 6, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_vendor", "承运商", 7, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_vendorSource", "承运商运作方式", 8, false));
        capacityParameter.getChildren().add(new ExportDynamicParameter("capacity_vendorType", "承运商类型", 9, false));
        listExportDynamicParameter.add(capacityParameter);

        // 配送信息
        ExportDynamicParameter distributionParameter = new ExportDynamicParameter("distribution", "配送信息", 7, false);
        distributionParameter.getChildren()
                .add(new ExportDynamicParameter("distribution_driverArrivedLoadingTime", "在仓耗时", 1, false));
        distributionParameter.getChildren()
                .add(new ExportDynamicParameter("distribution_loadingTime", "装货耗时", 2, false));
        distributionParameter.getChildren()
                .add(new ExportDynamicParameter("distribution_shippingTime", "配送耗时", 3, false));
        listExportDynamicParameter.add(distributionParameter);

        // 费用信息
        ExportDynamicParameter costParameter = new ExportDynamicParameter("cost", "费用信息", 8, false);
        costParameter.getChildren().add(new ExportDynamicParameter("cost_estimateFreight", "客户侧初始含税金额", 1, true));
        costParameter.getChildren().add(new ExportDynamicParameter("cost_afterTaxFreight", "客户侧初始不含税金额", 2, true));
        costParameter.getChildren().add(new ExportDynamicParameter("cost_show4DriverFreight", "承运侧初始含税金额", 3, true));
        costParameter.getChildren().add(new ExportDynamicParameter("cost_taxRateValue", "税率", 4, true));
        costParameter.getChildren().add(new ExportDynamicParameter("cost_receiptType", "结算方式", 5, false));
        costParameter.getChildren().add(new ExportDynamicParameter("cost_laborerHandlingCost", "小工搬运费", 6, false));
        costParameter.getChildren().add(new ExportDynamicParameter("cost_driverHandlingCost", "司机搬运费", 7, false));
        listExportDynamicParameter.add(costParameter);

        // 运费调整信息
        ExportDynamicParameter costAdjustParameter = new ExportDynamicParameter("costAdjust", "运费调整信息", 9, false);
        costAdjustParameter.getChildren().add(new ExportDynamicParameter("costAdjust_lastCustomerFreightWithTax", "客户侧最终含税金额", 1, true));
//        costParameter.getChildren().add(new ExportDynamicParameter("costAdjust_afterTaxFreight", "客户侧调整后不含税金额", 2, true));
        costAdjustParameter.getChildren().add(new ExportDynamicParameter("costAdjust_lastVendorFreightWithTax", "承运侧最终含税金额", 3, true));
        listExportDynamicParameter.add(costAdjustParameter);

        // 取消信息
        ExportDynamicParameter cancelParameter = new ExportDynamicParameter("cancel", "取消信息", 10, false);
        cancelParameter.getChildren().add(new ExportDynamicParameter("cancel_cancelChannel", "取消渠道", 1, false));
        cancelParameter.getChildren().add(new ExportDynamicParameter("cancel_waybillCancelRemark", "取消原因", 2, false));
        listExportDynamicParameter.add(cancelParameter);
    }

    static {
        selectParamMap = new HashMap<String, SelectParam>();
        selectParamMap.put("basic_waybillNo",
                new SelectParam("tgm", "waybill", "waybill_no", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_statusView", new SelectParam("tgm", "waybill", "status_view",
                Waybill.StatusView.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_reconciliationStatus", new SelectParam("tgm", "waybill", "reconciliation_status",
                Waybill.ReconciliationStatus.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_settlementStatus", new SelectParam("tgm", "waybill", "settlement_status",
                Waybill.SettlementStatus.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_receiptStatus", new SelectParam("tgm", "waybill", "receipt_status",
                Waybill.ReceiptStatus.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_receivableReconcilicationStatus",
                new SelectParam("tgm", "waybill", "receivable_reconcilication_status",
                        Waybill.ReconciliationStatus.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_businessBranch", new SelectParam("tgm", "waybill", "business_branch",
                Waybill.BusinessBranch.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_areaCode",
                new SelectParam(null, null, "selfAreaName", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_customerName",
                new SelectParam("tgm", "waybill", "customer_name", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_projectName",
                new SelectParam("tgm", "waybill", "project_name", null, ColumnType.STRING.getColumnTypeCode()));
//        selectParamMap.put("basic_contactName",
//                new SelectParam("oms", "order", "contact_name", null, ColumnType.STRING.getColumnTypeCode()));
//        selectParamMap.put("basic_contactPhone",
//                new SelectParam("oms", "order", "contact_phone", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_customerManagerName", new SelectParam("tgm", "waybill", "customer_manager_name", null,
                ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_mobileNumber",
                new SelectParam("auth", "user", "mobile_number", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_createTime",
                new SelectParam("tgm", "waybill", "create_time", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_waybillSource", new SelectParam("tgm", "waybill", "waybill_source",
                Waybill.WaybillSource.map(), ColumnType.STRING.getColumnTypeCode()));
//        selectParamMap.put("basic_orderNum",
//                new SelectParam("oms", "order", "order_num", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("goods_goodsType",
                new SelectParam("tgm", "truck_require", "goods_type", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("goods_goodsWeight",
                new SelectParam("tgm", "truck_require", "goods_weight", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("goods_goodsVolume",
                new SelectParam("tgm", "truck_require", "goods_volume", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("goods_goodsAmount",
                new SelectParam("tgm", "truck_require", "goods_amount", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("address_addressDetail", new SelectParam("tgm", "waybill_delivery_address", "address_detail",
                null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("address_contactName", new SelectParam("tgm", "waybill_delivery_address", "contact_name",
                null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("address_contactPhone", new SelectParam("tgm", "waybill_delivery_address", "contact_phone",
                null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("address_estimateDistance",
                new SelectParam("tgm", "waybill", "estimate_distance", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("address_actualMileage",
                new SelectParam("tgm", "waybill", "actual_mileage", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("address_reserveAddressCount",
                new SelectParam(null, null, "reserveAddressCount", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("address_reserveAddressDetails",
                new SelectParam(null, null, "reserveAddressDetails", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_planDeliveryTime",
                new SelectParam("tgm", "waybill", "plan_delivery_time", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_finishTime",
                new SelectParam("tgm", "waybill", "finish_time", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_requireType",
                new SelectParam(null, null, "requireType", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_entryLicense", new SelectParam("tgm", "waybill", "entry_license",
                trueAndFalseMap(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_needReceipt",
                new SelectParam(null, null, "isRecallOrder", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_isNeedBackStorage",
                new SelectParam(null, null, "isComeBackOrder", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_isCollectPayment",
                new SelectParam(null, null, "isAgentCollectFreight", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckRequire_remark",
                new SelectParam("tgm", "truck_require", "remark", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_driverName",
                new SelectParam("tgm", "waybill", "driver_name", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_driverType", new SelectParam("tgm", "waybill", "driver_type", driverTypeMap(),
                ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_vehicleType", new SelectParam("tgm", "waybill", "vehicle_type", vehicleTypeMap(),
                ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_contactPhone",
                new SelectParam("vms", "driver", "phone", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_plateNumber",
                new SelectParam("tgm", "waybill", "plate_number", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_actualType",
                new SelectParam(null, null, "actualType", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_vendor",new SelectParam("vms", "vendor", "vendor_name", null,
                        ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_vendorSource",new SelectParam("vms", "vendor", "vendor_source", vendorSourceMap(),
                        ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("capacity_vendorType",new SelectParam("vms", "vendor", "vendor_type", vendorTypeMap(),
                        ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("distribution_driverArrivedLoadingTime",
                new SelectParam(null, null, "houseTimeConsume", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("distribution_loadingTime",
                new SelectParam(null, null, "loadingTimeConsume", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("distribution_shippingTime",
                new SelectParam(null, null, "deliveryTimeConsume", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("cost_estimateFreight",
                new SelectParam("tgm", "waybill", "estimate_freight", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("cost_taxRateValue",
                new SelectParam("tgm", "truck_require", "tax_rate_value", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("cost_afterTaxFreight",
                new SelectParam("tgm", "waybill", "after_tax_freight", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("cost_rebateFee",
                new SelectParam(null, null, "comeBackFreight", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("cost_show4DriverFreight",
                new SelectParam("tgm", "waybill", "show4_driver_freight", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("costAdjust_lastCustomerFreightWithTax",
                new SelectParam("tgm", "waybill_amount", "last_customer_freight_with_tax", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("costAdjust_lastVendorFreightWithTax",
                new SelectParam("tgm", "waybill_amount", "last_vendor_freight_with_tax", null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("cost_receiptType", new SelectParam("tgm", "waybill", "receipt_type",
                Waybill.ReceiptType.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("cost_updateFreightRemark", new SelectParam("tgm", "waybill", "update_freight_remark", null,
                ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("cancel_cancelChannel", new SelectParam("tgm", "waybill", "cancel_channel",
                Waybill.CancelChannel.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("cancel_waybillCancelRemark", new SelectParam("tgm", "waybill", "waybill_cancel_remark",
                null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("cost_laborerHandlingCost", new SelectParam("tgm", "waybill_param", "laborer_handling_cost",
                null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("cost_driverHandlingCost", new SelectParam("tgm", "waybill_param", "driver_handling_cost",
                null, ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("truckCustomer_nickname",
                new SelectParam("tgm", "truck_customer", "nickname", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("truckCustomer_contactPhone",
                new SelectParam("tgm", "truck_customer", "contact_phone", null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("waybill_needReceipt", new SelectParam("tgm", "waybill", "need_receipt",
                Waybill.NeedReceipt.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("goods_goodsCheck", new SelectParam("tgm", "waybill_param", "is_check_goods",
                trueAndFalseMap(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_crmCustomerId", new SelectParam("tgm", "customer_info", "crm_customer_id", null,
                ColumnType.NUMBER.getColumnTypeCode()));
        selectParamMap.put("basic_receiveWay", new SelectParam("tgm", "waybill", "receive_way",
                Waybill.ReceiveWay.map(), ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put(BASE_LOGISTICS_LABEL, new SelectParam("tgm", "waybill", "logistics_label",null,
                ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_projectManagerUserName", new SelectParam("tgm", "waybill",
                "project_manager_user_name",null, ColumnType.STRING.getColumnTypeCode()));
        selectParamMap.put("basic_createUserName", new SelectParam("tgm", "waybill", "create_user_name",
                null, ColumnType.STRING.getColumnTypeCode()));
    }

    @Override
    public Page<ExportCollection> search(int pageNo, int pageSize, LoginUser loginUser) {
        List<ExportCollection> result = new ArrayList<ExportCollection>();
        if (null == loginUser || null == loginUser.getTenantId() || null == loginUser.getUserId()) {
            return new Page<ExportCollection>(pageNo, pageSize, 0, result);
        }
        String url = Constants.DATA_EXPORT_BASE_URL + "/" + loginUser.getTenantId() + "/" + loginUser.getUserId()
                + "/getUserTasks.html?page=" + pageNo + "&pageSize=" + pageSize;
        try {
            String str = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            log.info("exportParams search return : {}.", str);
            if (null != str) {
                ExportResult response = JSON.parseObject(str, ExportResult.class);
                return new Page<ExportCollection>(pageNo, pageSize, response.getCount(), response.getData());
            }
        } catch (Exception e) {
            log.warn("exportParams jpush error, errorMsg:{}", e.getMessage());
        }

        return new Page<ExportCollection>(pageNo, pageSize, 0, result);
    }

    @Override
    public void deleteData(Integer exportTaskId, LoginUser loginUser) throws BusinessException {
        String url = Constants.DATA_EXPORT_BASE_URL + "/" + loginUser.getTenantId() + "/" + loginUser.getUserId()
                + "/deleteTask.html?taskId=" + exportTaskId;
        try {
            String str = Request.Get(url).socketTimeout(7000).connectTimeout(5000).execute().returnContent().asString();
            log.info("exportDelete return : {}.", str);
        } catch (Exception e) {
            log.warn("exportDelete error, errorMsg:{}", e.getMessage());
        }
    }

}
