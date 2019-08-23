package com.juma.tgm.waybillReport.service.impl;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.Employee;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User.UserUniqueAttribute;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.domain.Region;
import com.juma.conf.service.ConfParamService;
import com.juma.conf.service.RegionService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.daily.service.ProjectDailyService;
import com.juma.tgm.project.common.CustomerForProductAndDept;
import com.juma.tgm.project.common.LogisticsProduct;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.sop.domain.Sop;
import com.juma.tgm.sop.service.SopService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.tools.service.VmsCommonService;
import com.juma.tgm.truck.domain.TruckType;
import com.juma.tgm.truck.service.TruckTypeFreightService;
import com.juma.tgm.truck.service.TruckTypeService;
import com.juma.tgm.waybill.dao.OfflineWaybillDao;
import com.juma.tgm.waybill.domain.*;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateApplication;
import com.juma.tgm.waybill.enumeration.WaybillOperateTrackEnum.OperateType;
import com.juma.tgm.waybill.service.TaxRateService;
import com.juma.tgm.waybill.service.TruckRequireService;
import com.juma.tgm.waybill.service.WaybillAmountService;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybill.service.WaybillDeliveryAddressService;
import com.juma.tgm.waybill.service.WaybillOperateTrackService;
import com.juma.tgm.waybill.service.WaybillParamService;
import com.juma.tgm.waybill.service.WaybillReceiveAddressService;
import com.juma.tgm.waybillReport.domain.OfflineWaybill;
import com.juma.tgm.waybillReport.domain.OfflineWaybillResponse;
import com.juma.tgm.waybillReport.service.OfflineWaybillService;
import com.juma.tgm.xlsx.domain.XlsxTemplate;
import com.juma.tgm.xlsx.service.XlsxTemplateService;
import com.juma.vms.driver.domain.Driver;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.vendor.domain.Vendor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class OfflineWaybillServiceImpl implements OfflineWaybillService {

    @Resource
    private OfflineWaybillDao offlineWaybillDao;
    @Resource
    private TaxRateService taxRateService;

    @Resource
    private WaybillParamService waybillParamService;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private TruckRequireService truckRequireService;

    @Resource
    private WaybillDeliveryAddressService waybillDeliveryAddressService;

    @Resource
    private WaybillReceiveAddressService waybillReceiveAddressService;

    @Resource
    private RegionService regionService;

    @Resource
    private TruckTypeService truckTypeService;

    @Resource
    private TruckTypeFreightService truckTypeFreightService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private ProjectService projectService;

    @Resource
    private XlsxTemplateService xlsxTemplateService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private SopService sopService;

    @Resource
    private ConfParamService confParamService;

    @Resource
    private WaybillOperateTrackService waybillOperateTrackService;

    @Resource
    private VmsService vmsService;

    @Resource
    private CrmCommonService crmCommonService;
    @Resource
    private VmsCommonService vmsCommonService;
    @Resource
    private WaybillAmountService waybillAmountService;
    @Resource
    private ProjectDailyService projectDailyService;


    @Override
    public Page<OfflineWaybill> search(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId() || null == loginUser.getUserId()) {
            return new Page<OfflineWaybill>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<OfflineWaybill>());
        }

        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        pageCondition.getFilters().put("optUserId", loginUser.getUserId());
        int total = offlineWaybillDao.searchCount(pageCondition);
        List<OfflineWaybill> rows = offlineWaybillDao.search(pageCondition);
        return new Page<OfflineWaybill>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public OfflineWaybillResponse handleOfflineWaybill(List<OfflineWaybill> rows, boolean checkProjectStatus, LoginEmployee loginEmployee) {
        List<LogisticsProduct> logisticsProductList = new ArrayList<>();
        boolean showLogisticsProduct = crmCommonService.isShowLogisticsProduct(loginEmployee);
        if (showLogisticsProduct) {
            logisticsProductList = crmCommonService.listLogisticsProduct(loginEmployee);
        }

        // 所有的物流标签
        OfflineWaybillResponse resp = new OfflineWaybillResponse();
        int successNum = 0;
        int failureNum = 0;
        Iterator<OfflineWaybill> it = rows.iterator();
        while (it.hasNext()) {
            OfflineWaybill offlineWaybill = it.next();
            if (checkEmptyRow(offlineWaybill)) {
                it.remove();
                continue;
            }
            StringBuffer buffer = new StringBuffer();
            if (checkCustomerInfo(buffer, offlineWaybill, loginEmployee)
                    && checkProject(buffer, offlineWaybill, showLogisticsProduct, checkProjectStatus, loginEmployee)
                    && checkDriver(buffer, offlineWaybill, loginEmployee)
                    && checkTaxRate(buffer, offlineWaybill, loginEmployee)
                    && checkReceiptType(buffer, offlineWaybill)
                    && checkIsBack(buffer, offlineWaybill)
                    && checkIsCarry(buffer, offlineWaybill)
                    && checkPlateNumber(buffer, offlineWaybill, loginEmployee)
                    && checkRegionCode(buffer, offlineWaybill)
                    && checkPlanDeliveryTime(buffer, offlineWaybill)
                    && checkEstimateFreight(buffer, offlineWaybill)
                    && checkReconciliation(buffer, offlineWaybill)
                    && checkGoodsWeight(buffer, offlineWaybill)
                    && checkGoodsVolume(buffer, offlineWaybill)
                    && checkRemark(buffer, offlineWaybill, loginEmployee)
                    && checkLogisticsLabel(buffer, offlineWaybill, showLogisticsProduct, logisticsProductList)) {
                offlineWaybill.setStatus(1);
                offlineWaybill.setResult("数据正确");
                successNum++;
            } else {
                offlineWaybill.setStatus(0);
                offlineWaybill.setResult(buffer.toString());
                failureNum++;
            }
            offlineWaybill.setOptUserId(loginEmployee.getUserId());
            offlineWaybill.setTenantId(loginEmployee.getTenantId());
            offlineWaybill.setTenantCode(loginEmployee.getTenantCode());
            offlineWaybill.setVendorName(offlineWaybill.getVendorName() == null ? "" : offlineWaybill.getVendorName());
        }
        // 批量写入
        if (!rows.isEmpty()) {
            offlineWaybillDao.insertAll(rows);
        }
        resp.setFailureNum(failureNum);
        resp.setSuccessNum(successNum);
        return resp;
    }

    private boolean checkLogisticsLabel(StringBuffer buffer, OfflineWaybill offlineWaybill,
                                        boolean isShowLogisticsProduct, List<LogisticsProduct> logisticsProductList) {
        if (!isShowLogisticsProduct || null != offlineWaybill.getProjectId()) {
            return true;
        }

        if (StringUtils.isBlank(offlineWaybill.getLogisticsLabelName())) {
            buffer.append("物流标签不能为空");
            buffer.append("<br>");
            return false;
        }

        if (logisticsProductList.isEmpty()) {
            buffer.append("物流标签未添加，请在CRM系统添加");
            buffer.append("<br>");
            return false;
        }

        Map<String, String> map = new HashMap<>();
        for (LogisticsProduct l : logisticsProductList) {
            map.put(l.getName(), l.getCode());
        }

        if (!map.containsKey(offlineWaybill.getLogisticsLabelName())) {
            buffer.append("物流标签【");
            buffer.append(offlineWaybill.getLogisticsLabelName());
            buffer.append("】未添加，请在CRM系统添加或更换");
            buffer.append("<br>");
            return false;
        }

        offlineWaybill.setLogisticsLabel(map.get(offlineWaybill.getLogisticsLabelName()));
        return true;
    }

    /**
     * @Title: checkEmptyRow @Description: 检查空行 @param: @return @return:
     * boolean @throws
     */
    private boolean checkEmptyRow(OfflineWaybill offlineWaybill) {
        if (StringUtils.isBlank(offlineWaybill.getCustomerName())
                && StringUtils.isBlank(offlineWaybill.getDriverPhone())
                && StringUtils.isBlank(offlineWaybill.getPlateNumber())) {
            return true;
        }
        return false;
    }

    private boolean checkGoodsWeight(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        if (offlineWaybill.getGoodsWeight() == null || offlineWaybill.getGoodsWeight().trim().length() == 0) {
            buffer.append("货物重量不能为空");
            buffer.append("<br>");
            return false;
        }

        if (!BaseUtil.isNumOrDecimal(offlineWaybill.getGoodsWeight())) {
            buffer.append("货物重量只能是数字");
            buffer.append("<br>");
            return false;
        }
        return true;
    }

    private boolean checkGoodsVolume(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        if (offlineWaybill.getGoodsVolume() == null || offlineWaybill.getGoodsVolume().trim().length() == 0) {
            return true;
        }

        if (!BaseUtil.isNumOrDecimal(offlineWaybill.getGoodsVolume())) {
            buffer.append("货物体积只能是数字");
            buffer.append("<br>");
            return false;
        }
        return true;
    }

    private boolean checkReconciliation(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        // V4.5.1需求：默认未对账
        offlineWaybill.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());

        // if (offlineWaybill.getReconciliationStr() == null) {
        // buffer.append("是否已对帐不能为空");
        // buffer.append("<br>");
        // return false;
        // }
        // if ("是".equals(offlineWaybill.getReconciliationStr())) {
        // offlineWaybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
        // } else if ("否".equals(offlineWaybill.getReconciliationStr())) {
        // offlineWaybill.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        // } else {
        // buffer.append("是否已对帐只能填是或否");
        // buffer.append("<br>");
        // return false;
        // }
        return true;
    }

    private boolean checkRegionCode(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        if (offlineWaybill.getProvince() == null || offlineWaybill.getProvince().trim().length() == 0
                || offlineWaybill.getCity() == null || offlineWaybill.getCity().trim().length() == 0
                || offlineWaybill.getDistrict() == null || offlineWaybill.getDistrict().trim().length() == 0
                || offlineWaybill.getStreet() == null || offlineWaybill.getStreet().trim().length() == 0) {
            buffer.append("省、市、区和街道不能为空:");
            buffer.append("<br>");
            return false;
        } else {
            Region region = regionService.findByRegionName(null, offlineWaybill.getProvince());
            if (region == null) {
                buffer.append("省不存在:");
                buffer.append(offlineWaybill.getProvince());
                buffer.append("<br>");
                return false;
            }
            Region cityRegion = regionService.findByRegionName(region.getRegionId(), offlineWaybill.getCity());
            if (cityRegion == null) {
                buffer.append("市不存在:");
                buffer.append(offlineWaybill.getCity());
                buffer.append("<br>");
                return false;
            }
            offlineWaybill.setRegionCode(cityRegion.getRegionCode());
            return true;
        }
    }

    private boolean checkPlateNumber(StringBuffer buffer, OfflineWaybill offlineWaybill, LoginUser loginUser) {
        if (offlineWaybill.getPlateNumber() == null || offlineWaybill.getPlateNumber().trim().length() == 0) {
            buffer.append("车牌号为空");
            buffer.append("<br>");
            return false;
        } else {
            com.juma.vms.truck.domain.Truck truck = vmsCommonService.loadTruckByPlateNumber(offlineWaybill.getPlateNumber());
            if (truck == null) {
                buffer.append("车牌号不存在:");
                buffer.append(offlineWaybill.getPlateNumber());
                buffer.append("<br>");
                return false;
            }

            if (null == truck.getTruckRunType()) {
                buffer.append("车辆车型不存在:");
                buffer.append(offlineWaybill.getPlateNumber());
                buffer.append("<br>");
                return false;
            }

            TruckType truckType = truckTypeService.findByBoxAndLength(truck.getVehicleBoxType(),
                    truck.getVehicleBoxLength(), loginUser.getTenantId());
            if (null == truckType) {
                buffer.append("车辆车型不存在:");
                buffer.append(offlineWaybill.getPlateNumber());
                buffer.append("<br>");
                return false;
            }

            offlineWaybill.setTruckId(truck.getTruckId());
            offlineWaybill.setVehicleType(truck.getTruckRunType());
            offlineWaybill.setTruckTypeId(truckType.getTruckTypeId());



            return this.buildVehicleTypeAndVendor(buffer, offlineWaybill, loginUser);
        }
    }

    // 获取车辆类型与承运商
    private boolean buildVehicleTypeAndVendor(StringBuffer buffer, OfflineWaybill offlineWaybill,
                                              LoginUser loginUser) {
        Vendor vendor = vmsService.loadVendorByTruckId(offlineWaybill.getTruckId(), loginUser);
        if (null == vendor) {
            buffer.append("车辆没有关联承运商，不能导入，请先维护好承运商相关信息！<br>");
            return false;
        }

        offlineWaybill.setVehicleToVendor(vendor.getVendorId());
        offlineWaybill.setVendorName(vendor.getVendorName());
        return true;
    }

    private boolean checkIsBack(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        // if ("是".equals(offlineWaybill.getIsBackValue())) {
        // offlineWaybill.setIsBack(6);
        // }
        // 所有导入运单默认为需要回单
        offlineWaybill.setIsBack(6);
        return true;
    }

    private boolean checkIsCarry(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        if ("是".equals(offlineWaybill.getIsCarryValue())) {
            offlineWaybill.setIsCarry(4);
        }
        return true;
    }

    private boolean checkCustomerInfo(StringBuffer buffer, OfflineWaybill offlineWaybill, LoginUser loginUser) {

        if (offlineWaybill.getCustomerName() != null) {
            CustomerInfo customerInfo = customerInfoService.findCusInfoByName(offlineWaybill.getCustomerName(), false,
                    loginUser);
            if (customerInfo == null) {
                buffer.append("所属客户不存在:");
                buffer.append(offlineWaybill.getCustomerName());
                buffer.append("<br>");
                return false;
            }
            
            offlineWaybill.setCustomerId(customerInfo.getCustomerId());
            offlineWaybill.setCustomerManagerId(customerInfo.getCustomerManagerUserId());
            offlineWaybill.setOwnerEmployeeId(customerInfo.getCustomerManagerUserId());// 以后去掉
            offlineWaybill.setAreaCode(customerInfo.getAreaCode());// 归属组织
            
            if (null == customerInfo.getStatus() || (NumberUtils.compare(customerInfo.getStatus(),
                    com.juma.crm.customer.domain.CustomerInfo.CustomerStatus.SIGNED.getValue().intValue()) != 0
                    && NumberUtils.compare(customerInfo.getStatus(),
                    com.juma.crm.customer.domain.CustomerInfo.CustomerStatus.SIGN_SOON.getValue()
                            .intValue()) != 0)) {
                buffer.append("所属客户不是试运行或已签约状态，请到CRM维护:");
                buffer.append(offlineWaybill.getCustomerName());
                buffer.append("<br>");
                return false;
            }
            
            if(offlineWaybill.getProjectName() != null) {
                offlineWaybill.setCustomerManagerId(customerInfo.getCustomerManagerUserId());
                return true;
            }
            
            if (customerInfo.getCustomerManagerUserId() == null) {
                buffer.append("客户没有客户经理:");
                buffer.append(offlineWaybill.getCustomerName());
                buffer.append("<br>");
                return false;
            }
            if (offlineWaybill.getCustomerManagerPhone() == null) {
                buffer.append("客户经理电话为空");
                buffer.append("<br>");
                return false;
            }
            Employee employee = employeeService.loadEmployee(UserUniqueAttribute.mobileNumber,
                    offlineWaybill.getCustomerManagerPhone(), loginUser);
            if (employee == null) {
                buffer.append("客户经理不存在:");
                buffer.append(offlineWaybill.getCustomerManagerPhone());
                buffer.append("<br>");
                return false;
            }
            if (!employee.getEmployeeId().equals(customerInfo.getCustomerManagerUserId())) {
                buffer.append("客户和客户经理对应不上");
                buffer.append("<br>");
                return false;
            }

            CustomerForProductAndDept customerForProductAndDept
                    = crmCommonService.loadCustomerForProductAndDeptByCrmCustomerId(customerInfo.getCrmCustomerId(), loginUser);
            if (null == customerForProductAndDept || null == customerForProductAndDept.getDeparmentId()) {
                buffer.append("客户【");
                buffer.append(offlineWaybill.getCustomerName());
                buffer.append("】没有主体（子公司），请联系驹马科技售后人员处理！");
                buffer.append("<br>");
                return false;
            }

            return true;
        } else {
            buffer.append("所属客户为空");
            buffer.append("<br>");
            return false;
        }
    }

    private boolean checkProject(StringBuffer buffer, OfflineWaybill offlineWaybill, boolean isShowLogisticsProduct,
                                 boolean checkProjectStatus, LoginUser loginUser) {

        if (offlineWaybill.getProjectName() == null) {
            if (loginUser.getTenantId() != 4) {
                buffer.append("项目名称不能为空:");
                buffer.append("<br>");
                return false;
            }
            return true;
        }

        Project project = projectService.findByName(offlineWaybill.getProjectName(), loginUser);
        if (project == null) {
            buffer.append("所属项目不存在:");
            buffer.append(offlineWaybill.getProjectName());
            buffer.append("<br>");
            return false;
        }

        if (null != offlineWaybill.getCustomerId() && !project.getCustomerId().equals(offlineWaybill.getCustomerId())) {
            buffer.append("项目[");
            buffer.append(offlineWaybill.getProjectName());
            buffer.append("]不属于客户");
            buffer.append(offlineWaybill.getCustomerName());
            buffer.append("<br>");
            return false;
        }

        offlineWaybill.setProjectId(project.getProjectId());
        offlineWaybill.setProjectName(project.getName());
        
        if(project.getProjectManagerUserId() == null) {
            buffer.append("项目不存在项目经理");
            return false;
        }
        
        if(project.getPayToCompany() == null) {
            buffer.append("项目不存在运营主体");
            return false;
        }
        
        if(project.getContractToCompany() == null) {
            buffer.append("项目不存在签约主体");
            return false;
        }

        if (checkProjectStatus) {
            if (project.getProjectStatus() != ProjectEnum.ProjectStatus.RUNING.getCode()) {
                buffer.append("项目[");
                buffer.append(offlineWaybill.getProjectName());
                buffer.append("]不是运行中状态，请维护或联系运营人员");
                buffer.append("<br>");
                return false;
            }
        }

        //只有物流事业群检查物流标签
        if (!isShowLogisticsProduct) {
            return true;
        }

        if (StringUtils.isBlank(project.getLogisticsLabel())) {
            buffer.append("项目[");
            buffer.append(offlineWaybill.getProjectName());
            buffer.append("]没有物流产品标签，请维护或联系运营人员");
            buffer.append("<br>");
            return false;
        }

        offlineWaybill.setLogisticsLabel(project.getLogisticsLabel());

        return true;
    }

    private boolean checkReceiptType(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        if (offlineWaybill.getReceiptTypeValue() == null || offlineWaybill.getReceiptTypeValue().trim().length() == 0
                || offlineWaybill.getReceiptTypeMappingValue(offlineWaybill.getReceiptTypeValue()) == null) {
            buffer.append("付款方式不存在:");
            buffer.append(offlineWaybill.getReceiptTypeValue());
            buffer.append("<br>");
            return false;
        }
        offlineWaybill.setReceiptType(offlineWaybill.getReceiptTypeMappingValue(offlineWaybill.getReceiptTypeValue()));
        return true;
    }

    private boolean checkEstimateFreight(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        if (null == offlineWaybill.getEstimateFreight()) {
            buffer.append("运费不能为空:");
            buffer.append(offlineWaybill.getEstimateFreight());
            buffer.append("<br>");
            return false;
        }

        if (null == offlineWaybill.getShow4DriverFreight()) {
            buffer.append("司机结算价不能为空:");
            buffer.append(offlineWaybill.getShow4DriverFreight());
            buffer.append("<br>");
            return false;
        }

        offlineWaybill.setReceiptType(offlineWaybill.getReceiptTypeMappingValue(offlineWaybill.getReceiptTypeValue()));
        return true;
    }

    private boolean checkRemark(StringBuffer buffer, OfflineWaybill offlineWaybill, LoginUser loginUser) {
        if (StringUtils.isNotBlank(offlineWaybill.getRemark()) || null == offlineWaybill.getEstimateFreight()) {
            return true;
        }

        // 获取运费上限
        List<ConfParamOption> options = confParamService
                .findParamOptions(Constants.OFFLINE_WAYBILL_COST_UPPER_LINIT_KEY);
        if (CollectionUtils.isEmpty(options)) {
            return true;
        }

        for (ConfParamOption conf : options) {
            if (StringUtils.isBlank(conf.getOptionName()) || StringUtils.isBlank(conf.getOptionValue())) {
                continue;
            }

            if (!BaseUtil.isNumOrDecimal(conf.getOptionValue())) {
                continue;
            }

            if (!conf.getOptionName().equals(loginUser.getTenantId() + "")) {
                continue;
            }

            if (new BigDecimal(offlineWaybill.getEstimateFreight() + "")
                    .compareTo(new BigDecimal(conf.getOptionValue())) == 1) {
                buffer.append("运费超过【" + conf.getOptionValue() + "】元，必须填写备注，导入运费：");
                buffer.append(offlineWaybill.getEstimateFreight());
                buffer.append("<br>");
                return false;
            }
        }

        if (waybillCommonService.checkCustomerPriceUpperLimit(new BigDecimal(offlineWaybill.getEstimateFreight() + ""))) {
            buffer.append("运单税前费用过高，请认真核对！");
            buffer.append(offlineWaybill.getEstimateFreight());
            buffer.append("<br>");
            return false;
        }

        return true;
    }

    private boolean checkPlanDeliveryTime(StringBuffer buffer, OfflineWaybill offlineWaybill) {
        if (offlineWaybill.getPlanDeliveryTime() == null) {
            buffer.append("用车时间不能为空:");
            buffer.append(offlineWaybill.getPlanDeliveryTime());
            buffer.append("<br>");
            return false;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(offlineWaybill.getPlanDeliveryTime());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            now.set(Calendar.HOUR_OF_DAY, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);

            int year = cal.get(Calendar.YEAR);
            if (!(year - now.get(Calendar.YEAR) == 0 || year - now.get(Calendar.YEAR) == -1)) {
                buffer.append("用车时间年份不合理，要在");
                buffer.append(now.get(Calendar.YEAR) - 1);
                buffer.append("和");
                buffer.append(now.get(Calendar.YEAR));
                buffer.append("之间<br>");
                return false;
            }
            if (cal.getTime().getTime() > now.getTime().getTime()) {
                buffer.append("用车时间不能是将来的时间");
                buffer.append("<br>");
                return false;
            }
        }
        return true;
    }

    private boolean checkTaxRate(StringBuffer buffer, OfflineWaybill offlineWaybill, LoginUser loginUser) {
        if (offlineWaybill.getTaxRateValue() == null) {
            buffer.append("税率值不能为空");
            buffer.append("<br>");
            return false;
        } else {
            TaxRate taxRate = taxRateService.findTaxRateBy(offlineWaybill.getTaxRateValue(), loginUser);
            if (taxRate == null) {
                buffer.append("税率值不存在:");
                buffer.append(offlineWaybill.getTaxRateValue());
                buffer.append("<br>");
                return false;
            }
            offlineWaybill.setTaxRateId(taxRate.getTaxRateId());
            return true;
        }
    }

    private boolean checkDriver(StringBuffer buffer, OfflineWaybill offlineWaybill, LoginUser loginUser) {
        if (StringUtils.isBlank(offlineWaybill.getDriverPhone())) {
            buffer.append("司机电话不存在:");
            buffer.append(offlineWaybill.getDriverPhone());
            buffer.append("<br>");
            return false;
        }

        Driver driver = vmsCommonService.loadDriverByPhone(offlineWaybill.getDriverPhone());
        if (driver == null) {
            buffer.append("司机不存在于运力管理系统:");
            buffer.append(offlineWaybill.getDriverPhone());
            buffer.append("<br>");
            return false;
        } else {
            if (driver.getName() != null && !driver.getName().equals(offlineWaybill.getDriverName())) {
                buffer.append("司机名字与电话不一致:");
                buffer.append(offlineWaybill.getDriverName() + "," + offlineWaybill.getDriverPhone());
                buffer.append("<br>");
                return false;
            }

            if (null == driver.getDriverRunType()) {
                buffer.append("司机没有对应司机类型:");
                buffer.append(offlineWaybill.getDriverPhone());
                buffer.append("<br>");
                return false;
            }
            offlineWaybill.setDriverType(driver.getDriverRunType());
            offlineWaybill.setDriverId(driver.getDriverId());
            offlineWaybill.setDriverName(driver.getName());
            return true;
        }
    }

    @Override
    public int transferToWaybill(String departmentCode, List<Integer> offlineWaybillIds, LoginUser loginUser) {
        int success = 0;
        if (!offlineWaybillIds.isEmpty()) {
            List<OfflineWaybill> rows = offlineWaybillDao.findOfflineWaybillByIds(offlineWaybillIds);
            for (OfflineWaybill offlineWaybill : rows) {
                offlineWaybill.setDepartmentCode(departmentCode);
                Waybill waybill = createWaybill(offlineWaybill, loginUser);
                createWaybillParam(offlineWaybill, waybill, loginUser);
                createTruckRequire(offlineWaybill, waybill);
                createWaybillDeliveryAddress(offlineWaybill, waybill, loginUser);
                createWaybillReceiveAddress(offlineWaybill, waybill, loginUser);
                doCompleteWaybillAmount(waybill.getWaybillId(), waybill.getEstimateFreight(),
                    waybill.getShow4DriverFreight(), loginUser);
                success++;
                // 生成日报
                projectDailyService.createProjectDailyOnImport(offlineWaybill.getProjectId(), offlineWaybill.getPlanDeliveryTime());
            }
            offlineWaybillDao.deleteByIds(offlineWaybillIds);
        }
        return success;

    }

    // 运单费用完善
    private void doCompleteWaybillAmount(Integer waybillId, BigDecimal customerFreightWithTax,
        BigDecimal vendorFreightWithTax, LoginUser loginUser) {
        WaybillAmount amount = new WaybillAmount();
        amount.setWaybillId(waybillId);
        amount.setAmountStatus(AmountStatus.CONFIRMED.getCode());
        amount.setCustomerFreightWithTax(customerFreightWithTax);
        amount.setVendorFreightWithTax(vendorFreightWithTax);
        waybillAmountService.addOrUpdate(amount, loginUser);
    }

    private void createWaybillReceiveAddress(OfflineWaybill offlineWaybill, Waybill waybill, LoginUser loginUser) {
        // 5.waybill_receive_address
        WaybillReceiveAddress waybillReceiveAddress = new WaybillReceiveAddress();
        waybillReceiveAddress.setWaybillId(waybill.getWaybillId());
        waybillReceiveAddress.setRegionCode(waybill.getRegionCode());
        waybillReceiveAddress.setAddressDetail(offlineWaybill.getProvince() + offlineWaybill.getCity());
        waybillReceiveAddressService.insert(waybillReceiveAddress, loginUser);
    }

    private void createWaybillDeliveryAddress(OfflineWaybill offlineWaybill, Waybill waybill, LoginUser loginUser) {
        // 4.waybill_delivery_address
        WaybillDeliveryAddress waybillDeliveryAddress = new WaybillDeliveryAddress();
        waybillDeliveryAddress.setWaybillId(waybill.getWaybillId());
        waybillDeliveryAddress.setRegionCode(waybill.getRegionCode());
        waybillDeliveryAddress.setAddressName(offlineWaybill.getDistrict());
        waybillDeliveryAddress.setAddressDetail(offlineWaybill.getProvince() + offlineWaybill.getCity()
                + offlineWaybill.getDistrict() + offlineWaybill.getStreet());
        waybillDeliveryAddressService.insert(waybillDeliveryAddress, loginUser);
    }

    private void createTruckRequire(OfflineWaybill offlineWaybill, Waybill waybill) {
        // 3.truck_require
        TruckRequire truckRequire = new TruckRequire();
        truckRequire.setWaybillId(waybill.getWaybillId());
        truckRequire.setTruckTypeId(offlineWaybill.getTruckTypeId());
        truckRequire.setTaxRateValue(offlineWaybill.getTaxRateValue());
        truckRequire.setAdditionalFunctionIds(buildAdditionalFunctionIds(offlineWaybill));
        truckRequire.setGoodsType(offlineWaybill.getGoodsType());
        truckRequire.setGoodsVolume(offlineWaybill.getGoodsVolume());
        truckRequire.setGoodsWeight(offlineWaybill.getGoodsWeight());
        truckRequire.setRemark(offlineWaybill.getRemark());
        truckRequireService.insert(truckRequire);
    }

    private String buildAdditionalFunctionIds(OfflineWaybill offlineWaybill) {
        StringBuilder functionIds = new StringBuilder();
        if (offlineWaybill.getIsBack() != null) {
            functionIds.append(offlineWaybill.getIsBack());
        }
        if (offlineWaybill.getIsCarry() != null) {
            if (functionIds.length() != 0) {
                functionIds.append(",");
            }
            functionIds.append(offlineWaybill.getIsCarry());
        }
        return functionIds.toString();
    }

    private void createWaybillParam(OfflineWaybill offlineWaybill, Waybill waybill, LoginUser loginUser) {
        // 2.waybill_param
        WaybillParam waybillParam = new WaybillParam();
        waybillParam.setWaybillId(waybill.getWaybillId());
        waybillParam.setLaborerName(offlineWaybill.getLaborerName());
        Double driverHandlingCost = offlineWaybill.getDriverHandlingCost();
        if (null == driverHandlingCost) {
            driverHandlingCost = 0D;
        }
        waybillParam.setDriverHandlingCost(new BigDecimal(driverHandlingCost + ""));
        Double laborerHandlingCost = offlineWaybill.getLaborerHandlingCost();
        if (null == laborerHandlingCost) {
            laborerHandlingCost = 0D;
        }
        waybillParam.setLaborerHandlingCost(new BigDecimal(laborerHandlingCost + ""));
        Integer pointNo = offlineWaybill.getDistributionPointNo();
        if (null == pointNo) {
            pointNo = 0;
        }
        waybillParam.setDistributionPointNo(pointNo);
        waybillParam.setDriverRead(0);
        Sop sop = sopService.findNewestVersionSopByTenantId(loginUser.getTenantId());
        if (sop == null) {
            throw new BusinessException("errors.notFound.Sop", "errors.notFound.Sop", loginUser.getTenantId());
        }
        waybillParam.setSopId(sop.getSopId());
        waybillParamService.insert(waybillParam, loginUser);
    }

    private Waybill createWaybill(OfflineWaybill offlineWaybill, LoginUser loginUser) {
        // 1.waybill
        Waybill waybill = new Waybill();
        waybill.setTenantId(loginUser.getTenantId());
        waybill.setTenantCode(loginUser.getTenantCode());
        waybill.setDriverId(offlineWaybill.getDriverId());
        waybill.setDriverName(offlineWaybill.getDriverName());
        waybill.setDriverType(offlineWaybill.getDriverType());
        waybill.setTruckId(offlineWaybill.getTruckId());
        waybill.setPlateNumber(offlineWaybill.getPlateNumber());
        waybill.setWaybillNo(waybillCommonService.getWaybillNo());
        waybill.setStatus(Waybill.Status.PAIED.getCode());
        waybill.setStatusView(Waybill.StatusView.FINISH.getCode());
        waybill.setEstimateFreight(new BigDecimal(offlineWaybill.getEstimateFreight() + ""));
        waybill.setAreaCode(offlineWaybill.getAreaCode());
        waybill.setReconciliationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        waybill.setReceivableReconcilicationStatus(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode());
        waybill.setSettlementStatus(Waybill.SettlementStatus.INIT.getCode());
        waybill.setReceiptStatus(Waybill.ReceiptStatus.INIT.getCode());
        waybill.setTest(false);
        // waybill.setCheckout(false);
        waybill.setNeedDeliveryPointNote(0);
        waybill.setIsChangeDeliveryPoint(0);
        waybill.setUpdateFreightAuditStatus(0);
        waybill.setVehicleBoxType(36);
        waybill.setWaybillRemark("导入人userId:" + loginUser.getUserId());

        // 计算税后价格
        TruckRequire truckRequire = new TruckRequire();
        truckRequire.setTaxRateValue(offlineWaybill.getTaxRateValue());
        truckRequire.setAdditionalFunctionIds(buildAdditionalFunctionIds(offlineWaybill));

        waybill.setCustomerId(offlineWaybill.getCustomerId());
        waybill.setCustomerName(offlineWaybill.getCustomerName());
        waybill.setCustomerManagerId(offlineWaybill.getCustomerManagerId());
        waybill.setAfterTaxFreight(truckTypeFreightService.getAfterTaxFright(truckRequire, waybill));

        // 项目信息
        waybill.setProjectId(offlineWaybill.getProjectId());
        waybill.setProjectName(offlineWaybill.getProjectName());
        waybill.setBusinessBranch(Waybill.BusinessBranch.BRANCH_FULL.getCode());

        // 添加重量
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsWeight())) {
            waybill.setGoodsWeight(
                    new BigDecimal(truckRequire.getGoodsWeight()).multiply(new BigDecimal("1000")).intValue());
        }
        // 添加体积
        if (null != truckRequire && StringUtils.isNotBlank(truckRequire.getGoodsVolume())) {
            waybill.setGoodsVolume(new BigDecimal(truckRequire.getGoodsVolume()));
        }

        // 返点率入库
        BigDecimal rebateRate = customerInfoService.getRebateRate(waybill.getCustomerId());
        waybill.setRebateRate(rebateRate);

        if (offlineWaybill.getShow4DriverFreight() == null) {
            waybill.setShow4DriverFreight(waybill.getAfterTaxFreight());
        } else {
            waybill.setShow4DriverFreight(new BigDecimal(offlineWaybill.getShow4DriverFreight()));
        }

        waybill.setPlanDeliveryTime(offlineWaybill.getPlanDeliveryTime());

        waybill.setCreateUserId(loginUser.getUserId());
        waybill.setCreateTime(new Date());
        waybill.setReceiveWay(Waybill.ReceiveWay.MANUAL_ASSIGN.getCode());
        waybill.setRegionCode(offlineWaybill.getRegionCode());
        // 所有导入的运单设置为回单未上传
        waybill.setNeedReceipt(Waybill.NeedReceipt.NOT_HAVE_UPLOAD.getCode());
        waybill.setFinishTime(new Date());
        waybill.setReceiptType(offlineWaybill.getReceiptType());
        waybill.setWaybillSource(Waybill.WaybillSource.BACKGROUND_IMPORT.getCode());
        waybill.setUpdateFreightAuditStatus(0);
        waybill.setVehicleToVendor(offlineWaybill.getVehicleToVendor());
        waybill.setVehicleType(offlineWaybill.getVehicleType());
        waybill.setLogisticsLabel(offlineWaybill.getLogisticsLabel());

        waybillCommonService.insert(waybill, loginUser);

        // 导入运单轨迹
        addOperateTrack(waybill, OperateType.IMPORT_WAYBILL, loginUser);

        // 对账完成轨迹
        if (waybill.getReconciliationStatus() == Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode()) {
            addOperateTrack(waybill, OperateType.RECONCILIATION_FINISH, loginUser);
        }

        // 更改项目为开跑状态
        projectService.updateProjectToRunning(waybill.getProjectId(), waybill.getPlanDeliveryTime());

        customerInfoService.increaseWaybillCount(1, offlineWaybill.getCustomerId());
        return waybill;
    }


    // 添加操作轨迹
    private void addOperateTrack(Waybill waybill, OperateType operateType, LoginUser loginUser) {
        if (null == waybill || null == waybill.getWaybillId()) {
            return;
        }

        WaybillOperateTrack waybillOperateTrack = new WaybillOperateTrack();
        waybillOperateTrack.setWaybillId(waybill.getWaybillId());
        waybillOperateTrack.setOperateApplication(OperateApplication.BACKGROUND_SYS.getCode());
        waybillOperateTrack.setOperateType(operateType.getCode());
        waybillOperateTrack.setDriverId(waybill.getDriverId());
        waybillOperateTrack.setPlateNumber(waybill.getPlateNumber());
        waybillOperateTrackService.insert(waybillOperateTrack, loginUser);
    }

    @Override
    public void deleteByIds(List<Integer> offlineWaybillIds) {
        if (!offlineWaybillIds.isEmpty()) {
            offlineWaybillDao.deleteByIds(offlineWaybillIds);
        }
    }

    @Override
    public Map<String, String> templateFieldMapping(Integer templateId) {
        XlsxTemplate template = xlsxTemplateService.getTemplate(templateId);
        if (template == null)
            return null;
        return xlsxTemplateService.transferToMapping(templateId);
    }

    public static void main(String[] args) {
        
        
        Integer a = 123;
        Integer b = 234;
        Integer c = 234;
        
        System.out.println(null != a && !b.equals(c));
                
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parse("2018-12-01", "yyyy-MM-dd"));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        StringBuffer buffer = new StringBuffer();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        int year = cal.get(Calendar.YEAR);
        if (!(year - now.get(Calendar.YEAR) == 0 || year - now.get(Calendar.YEAR) == -1)) {
            buffer.append("用车时间年份不合理，要在");
            buffer.append(now.get(Calendar.YEAR) - 1);
            buffer.append("和");
            buffer.append(now.get(Calendar.YEAR));
            buffer.append("之间<br>");
        }
        if (cal.getTime().getTime() > now.getTime().getTime()) {
            buffer.append("用车时间不能是将来的时间");
            buffer.append("<br>");
        }
        System.out.println(buffer);
    }

}
