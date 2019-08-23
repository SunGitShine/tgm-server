package com.juma.tgm.crm.service.impl;

import com.giants.common.SpringContextHelper;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.DepartmentService;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginEcoUser;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.domain.UserInfo;
import com.juma.auth.user.service.UserService;
import com.juma.cms.wx.domain.Chanel;
import com.juma.cms.wx.service.ChanelService;
import com.juma.conf.domain.ConfParamOption;
import com.juma.conf.service.ConfParamService;
import com.juma.crm.customer.domain.ConsignorBaseInfoVo;
import com.juma.crm.customer.domain.ConsignorContactsInfo;
import com.juma.crm.customer.domain.ConsignorCustomerElimination;
import com.juma.crm.customer.domain.ConsignorCustomerInfo;
import com.juma.crm.customer.domain.ConsignorCustomerWholeInfoVo;
import com.juma.crm.customer.domain.ConsignorEliminationVo;
import com.juma.crm.customer.domain.ConsignorVisitRecord;
import com.juma.crm.customer.domain.ConsignorVisitRecordVo;
import com.juma.crm.customer.domain.Customer4TmsBo;
import com.juma.crm.customer.domain.CustomerStatusCount;
import com.juma.crm.customer.service.AppConsignorSearchService;
import com.juma.crm.customer.service.ConsignorContactsInfoService;
import com.juma.crm.customer.service.ConsignorCustomerEliminationService;
import com.juma.crm.customer.service.ConsignorCustomerInfoService;
import com.juma.crm.customer.service.ConsignorService;
import com.juma.crm.support.service.ConsignorSupportService;
import com.juma.crm.support.service.Crm4TmsService;
import com.juma.crm.support.service.CrmService;
import com.juma.tgm.cms.service.ExportTaskService;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.event.TMSObservable;
import com.juma.tgm.crm.dao.CustomerInfoDao;
import com.juma.tgm.crm.domain.ConsignorCustomerWholeInfoWithTgmInfo;
import com.juma.tgm.crm.domain.CrmCustomerInfo;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.domain.CustomerInfoBo;
import com.juma.tgm.crm.domain.CustomerInfoResp;
import com.juma.tgm.crm.domain.UserUnderCustomer;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customer.domain.TruckCustomer;
import com.juma.tgm.customer.domain.vo.ScatteredCustomerVo;
import com.juma.tgm.customer.domain.vo.SearchEnterpriseUserAndCargoOwner;
import com.juma.tgm.customer.service.CustomerManagerService;
import com.juma.tgm.project.common.CustomerForProductAndDept;
import com.juma.tgm.project.common.LogisticsProduct;
import com.juma.tgm.project.service.ProjectMemberService;
import com.juma.tgm.region.service.RegionTgmService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.vo.WaybillStatisticsParamVo;
import com.juma.tgm.waybill.service.WaybillCommonService;
import com.juma.tgm.waybillStatistics.service.CustomerManagerStatisticsService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);

    private static final String CRM_OWNED_INDUSTRY = "CRM_OWNED_INDUSTRY";

    private static final String CRM_ENTERPRISE_NATURE = "CRM_ENTERPRISE_NATURE";

    @Resource
    private CustomerInfoDao customerInfoDao;
    @Resource
    private RegionTgmService regionTgmService;
    @Resource
    private UserService userService;
    @Resource
    private ExportTaskService exportTaskService;

    @Resource
    private com.juma.crm.customer.service.CustomerInfoService cmsCustomerInfoService;

    @Resource
    private ConfParamService confParamService;

    @Resource
    private ChanelService chanelService;

    /**
     * crm 货主信息
     */
    @Resource
    private ConsignorService consignorService;

    /**
     * app端企业客户查询
     */
    @Resource
    private AppConsignorSearchService appConsignorSearchService;

    /**
     * 货主
     */
    @Resource
    private ConsignorCustomerInfoService consignorCustomerInfoService;

    @Resource
    private ConsignorContactsInfoService consignorContactsInfoService;

    /**
     * crm淘汰客户
     */
    @Resource
    private ConsignorCustomerEliminationService consignorCustomerEliminationService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private BusinessAreaCommonService businessAreaCommonService;

    @Resource
    private CustomerManagerStatisticsService customerManagerStatisticsService;

    @Resource
    private CrmService crmService;

    @Resource
    private WaybillCommonService waybillCommonService;

    @Resource
    private CustomerManagerService customerManagerService;

    @Resource
    private CrmCommonService crmCommonService;

    @Resource(name = "customerInfoObservable")
    private TMSObservable customerInfoObservable;// 暂时不做增加和删除 的观察 ，如果以后有需要可以加

    @Resource
    private DepartmentService departmentService;

    @Resource
    private Crm4TmsService crm4TmsService;

    @Resource
    private ProjectMemberService projectMemberService;

    @Override
    public Page<CrmCustomerInfo> searchDetails(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<CrmCustomerInfo>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<CrmCustomerInfo>());
        }

        List<CrmCustomerInfo> results = new ArrayList<CrmCustomerInfo>();
        pageCondition.getFilters().put("customerType", com.juma.crm.customer.domain.CustomerInfo.CustomerType.CONSIGNOR.getValue());
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());

        buildChanelCond(pageCondition);

        Page<com.juma.crm.customer.domain.CustomerInfo> page = cmsCustomerInfoService.search(pageCondition);
        for (com.juma.crm.customer.domain.CustomerInfo customerInfo : page.getResults()) {
            CrmCustomerInfo crmCustomer = buildCrmCustomer(customerInfo, loginUser);
            CustomerInfo info = findByCrmId(crmCustomer.getCustomerId());
            if (null != info) {
                crmCustomer.setTmsCustomerId(info.getCustomerId());
            }
            results.add(crmCustomer);
        }
        return new Page<CrmCustomerInfo>(pageCondition.getPageNo(), pageCondition.getPageSize(), page.getTotal(),
                results);
    }

    private void buildChanelCond(PageCondition pageCondition) {
        String all = String.valueOf(pageCondition.getFilters().get("sourceChannelCode"));
        if ("all".equals(all)) {
            List<Chanel> chanels = chanelService.findChildrenByCode("CUSTOMER_MANAGER");
            List<String> sourceChannelCodeList = new ArrayList<String>();
            for (Chanel chanel : chanels) {
                sourceChannelCodeList.add(chanel.getCode());
            }
            pageCondition.getFilters().put("sourceChannelCodeList", sourceChannelCodeList);
            pageCondition.getFilters().remove("sourceChannelCode");// 删除
        }
    }

    @Override
    public List<CrmCustomerInfo> listCrmCustomerInfo(PageCondition pageCondition) {
        List<CrmCustomerInfo> result = new ArrayList<CrmCustomerInfo>();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);

        pageCondition.getFilters().put("customerType", com.juma.crm.customer.domain.CustomerInfo.CustomerType.CONSIGNOR.getValue());
        Page<com.juma.crm.customer.domain.CustomerInfo> page = cmsCustomerInfoService.search(pageCondition);

        if (NumberUtils.compare(page.getTotal(), 0) <= 0) {
            return result;
        }
        List<com.juma.crm.customer.domain.CustomerInfo> rows = (List<com.juma.crm.customer.domain.CustomerInfo>) page
                .getResults();
        for (com.juma.crm.customer.domain.CustomerInfo customerInfo : rows) {
            if (NumberUtils.compare(com.juma.crm.customer.domain.CustomerInfo.CustomerStatus.ELIMINATED.getValue(),
                    customerInfo.getStatus()) != 0) {
                CrmCustomerInfo crmCustomerInfo = new CrmCustomerInfo();
                crmCustomerInfo.setCustomerId(customerInfo.getCustomerId());
                crmCustomerInfo.setCustomerName(customerInfo.getCustomerName());
                result.add(crmCustomerInfo);
            }
        }
        return result;
    }

    private CrmCustomerInfo buildCrmCustomer(com.juma.crm.customer.domain.CustomerInfo customerInfo,
                                             LoginUser loginUser) {
        CrmCustomerInfo crmCustomer = new CrmCustomerInfo();
        crmCustomer.setCustomerId(customerInfo.getCustomerId());
        crmCustomer.setCustomerName(customerInfo.getCustomerName());
        crmCustomer.setContactsName(customerInfo.getContactsName());
        crmCustomer.setContactsPhone(customerInfo.getContactsPhone());
        crmCustomer.setRegionCode(customerInfo.getRegionCode());
        crmCustomer.setCreateTime(customerInfo.getCreateTime());
        if (null != customerInfo.getCreateTime()) {
            crmCustomer.setCreateTimeFormat(DateUtil.format(customerInfo.getCreateTime(), "yyyy-MM-dd"));
        }
        crmCustomer.setCustomerManagerId(customerInfo.getUserId());
        crmCustomer.setCrmStatus(customerInfo.getStatus());
        crmCustomer.setAreaCode(customerInfo.getAreaCode());
        crmCustomer.setTenantCode(customerInfo.getTenantCode());

        crmCustomer.setRegion(regionTgmService.findRegionNameByCode(customerInfo.getRegionCode()));

        logger.info("货主列表错误findByCustomerId->customerId：{}", customerInfo.getCustomerId());
        ConsignorCustomerInfo consignorCustomerInfo = consignorCustomerInfoService
                .findByCustomerId(customerInfo.getCustomerId());
        if (consignorCustomerInfo != null) {
            // 返点率
            crmCustomer.setRebateRate(consignorCustomerInfo.getRebateRate());
            crmCustomer.setConsignorNote(consignorCustomerInfo.getConsignorNote());
            // 详细地址
            crmCustomer.setLivingAddress(consignorCustomerInfo.getLivingAddress());
            // 客户行业
            ConfParamOption confParamOption = confParamService.findParamOption(CRM_OWNED_INDUSTRY,
                    consignorCustomerInfo.getOwnedIndustry() + "");
            if (confParamOption != null) {
                crmCustomer.setCustomerIndustry(confParamOption.getOptionName());
            }
            // 客户性质
            confParamOption = confParamService.findParamOption(CRM_ENTERPRISE_NATURE,
                    consignorCustomerInfo.getEnterpriseNature() + "");
            if (confParamOption != null) {
                crmCustomer.setEnterpriseNature(confParamOption.getOptionName());
            }
            Chanel chanel = getChanelByCode(consignorCustomerInfo.getSourceChannelCode());
            if (null != chanel) {
                crmCustomer.setSourceChannelCodeCnName(chanel.getName());
            }
            // 地区
            if (StringUtils.isNotBlank(customerInfo.getRegionCode())) {
                String regionName = regionTgmService.findRegionNameByCode(customerInfo.getRegionCode());
                crmCustomer.setRegionName(regionName);
            }
            // 业务区域
            try {
                crmCustomer.setAreaName(
                        businessAreaCommonService.loadPreAndSelfAreaName(customerInfo.getAreaCode(), loginUser));
            } catch (Exception e) {
                logger.info(e.getMessage(), e);
            }
            // 客户经理
            if (customerInfo.getUserId() != null) {
                User user = null;
                try {
                    user = employeeService.findUserByEmployeeId(customerInfo.getUserId(), loginUser);
                } catch (BusinessException e) {
                    logger.error("获取用户中心数据错误employeeId:{}", customerInfo.getUserId(), e);
                }
                if (user != null) {
                    crmCustomer.setCustomerManagerName(user.getName());
                }
            }
        }

        return crmCustomer;
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
                    List<CrmCustomerInfo> results = new ArrayList<CrmCustomerInfo>();
                    pageCondition.setPageNo(1);
                    pageCondition.setPageSize(100000);
                    pageCondition.getFilters().put("customerType",
                            com.juma.crm.customer.domain.CustomerInfo.CustomerType.CONSIGNOR.getValue());
                    pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
                    buildChanelCond(pageCondition);
                    Page<com.juma.crm.customer.domain.CustomerInfo> page = cmsCustomerInfoService.search(pageCondition);
                    if (null != page && CollectionUtils.isNotEmpty(page.getResults())) {
                        for (com.juma.crm.customer.domain.CustomerInfo customerInfo : page.getResults()) {
                            CrmCustomerInfo crmCustomer = buildCrmCustomer(customerInfo, loginUser);
                            this.buildCustomerOrgnizationInfo(customerInfo, crmCustomer, loginUser);
                            crmCustomer.setContactsPhone(BaseUtil.interceptPhoneNumber(crmCustomer.getContactsPhone()));
                            results.add(crmCustomer);
                        }
                    }
                    exportTaskService.uploadExcelAndUpdateExportTask(exportTaskId, "customerInfo", results,
                            CrmCustomerInfo.class, startTime, loginUser);
                } catch (Exception e) {
                    exceptionMsg.append(e.getMessage()).append("|");
                    exportTaskService.failed(exportTaskId, exceptionMsg.toString(), loginUser);
                    logger.error(e.getMessage(), e);
                }
            }

            private void buildCustomerOrgnizationInfo(com.juma.crm.customer.domain.CustomerInfo customerInfo,
                                                      CrmCustomerInfo crmCustomer, LoginUser loginUser) {
                try {
                    // 客户经理
                    EmployeeInfo employeeInfo = waybillCommonService.loadEmployeeInfo(customerInfo.getUserId());
                    if (employeeInfo != null) {
                        crmCustomer.setCustomerManagerName(employeeInfo.getName());
                    }
                } catch (Exception e) {
                    logger.error("user not found : {}.", customerInfo.getUserId(), e);
                }
                // 业务系统信息
                if (null != customerInfo.getCustomerId()) {
                    CustomerInfo cus = findByCrmId(customerInfo.getCustomerId());
                    if (cus != null) {
                        crmCustomer.setIsProjectCheckOut(cus.getIsProjectCheckOut());
                        crmCustomer.setLicense(cus.getLicense());
                        crmCustomer.setOrgCodeCertificate(cus.getOrgCodeCertificate());
                        crmCustomer.setTaxRegCertificate(cus.getTaxRegCertificate());
                        crmCustomer.setIsProjectCheckOutView(cus.getIsProjectCheckOut() ? "是" : "否");
                    }
                }
            }
        });
    }

    @Override
    public int searchCount(PageCondition pageCondition) {
        return customerInfoDao.searchCount(pageCondition);
    }

    @Override
    public CustomerInfo findCusInfoByName(String customerName, Boolean isDelete, LoginUser loginUser) {
        List<CustomerInfo> list = listCusInfoByName(customerName, isDelete, loginUser);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void insert(CustomerInfo customerInfo, LoginUser loginUser) {
        check(customerInfo, loginUser);
        customerInfoDao.insert(customerInfo);

    }

    @Override
    public void update(CustomerInfo customerInfo, LoginUser loginUser) {
        customerInfoObservable.notifyObservers( customerInfo );
        check(customerInfo, loginUser);
        customerInfoDao.update(customerInfo);
    }

    @Override
    public void update(CrmCustomerInfo crmCustomerInfo, LoginEmployee loginEmployee) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCrmCustomerId(crmCustomerInfo.getCustomerId());
        customerInfo.setIsProjectCheckOut(crmCustomerInfo.getIsProjectCheckOut());
        customerInfo.setLicense(crmCustomerInfo.getLicense());
        customerInfo.setOrgCodeCertificate(crmCustomerInfo.getOrgCodeCertificate());
        customerInfo.setTaxRegCertificate(crmCustomerInfo.getTaxRegCertificate());
        customerInfo.setIsReceiveDailySms(crmCustomerInfo.getIsReceiveDailySms());

        if (crmCustomerInfo.getCustomerId() != null) {
            CustomerInfo customerInfoDb = findByCrmId(crmCustomerInfo.getCustomerId());
            if (customerInfoDb == null) {
                // 业务系统新建 走自动同步
                // customerInfoDao.insert(customerInfo);
                throw new BusinessException("crm.customer.sync.err", "crm.customer.sync.err");
            } else {
                customerInfo.setCustomerId(customerInfoDb.getCustomerId());
                customerInfoDao.update(customerInfo);
            }
        }
    }

    @Override
    public void delete(Integer customerId, LoginUser loginUser) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerId(customerId);
        customerInfo.setIsDelete(true);
        customerInfoDao.update(customerInfo);

    }

    // 验证
    private void check(CustomerInfo customerInfo, LoginUser loginUser) {
        String customerName = customerInfo.getCustomerName();
        Integer customerId = customerInfo.getCustomerId();
        if (StringUtils.isBlank(customerName)) {
            throw new BusinessException("customerNameNotNull", "customerInfo.error.customerNameNotNull");
        }
        customerName = customerName.trim();
        CustomerInfo cusInfoByName = findCusInfoByName(customerName, false, loginUser);
        if (null == cusInfoByName) {
            return;
        }

        // 客户名称已存在
        if (null == customerId || !customerId.equals(cusInfoByName.getCustomerId())) {
            throw new BusinessException("customerNameExist", "customerInfo.error.customerNameExist");
        }
    }

    @Override
    public List<CustomerInfo> loadAll() {
        return customerInfoDao.loadAll();
    }

    @Override
    public CustomerInfo findCusInfoById(Integer customerId) throws BusinessException{
        if (null == customerId) {
            return null;
        }
        return customerInfoDao.get(customerId);
    }

    @Override
    public void updateToEnable(Integer customerId, LoginEmployee loginEmployee) {
        updateToAble(customerId, loginEmployee, false);
    }

    @Override
    public void updateToDisable(Integer customerId, LoginEmployee loginEmployee) {
        updateToAble(customerId, loginEmployee, true);
    }

    // 启用禁用
    private void updateToAble(Integer customerId, LoginEmployee loginEmployee, boolean flag) {
        CustomerInfo info = new CustomerInfo();
        info.setCustomerId(customerId);
        info.setDelete(flag);
        info.setLastUpdateUserId(loginEmployee.getUserId());
        customerInfoDao.update(info);
    }

    @Override
    public CustomerInfo checkCustomerUsable(Integer customerId) {
        if (null == customerId)
            return null;
        CustomerInfo info = findCusInfoById(customerId);
        if (null == info) {
            throw new BusinessException("notFound", "project.error.not.found");
        }
        if (info.isDelete()) {
            throw new BusinessException("notExist", "project.error.not.exist", info.getCustomerName());
        }
        return info;
    }

    @Override
    public CustomerInfoResp findByEmployeeId(CustomerInfo customerInfo, Integer employeeId) {
        CustomerInfoResp resp = new CustomerInfoResp();
        if (null != employeeId) {

            CustomerInfo example = new CustomerInfo();
            example.setCustomerManagerUserId(employeeId);
            example.setCustomerType(2);
            List<CustomerInfo> tgmCusList = customerInfoDao.findByExample(example);

            for (CustomerInfo cus : tgmCusList) {
                if (cus.getCustomerId() == null) {
                    continue;
                }
                CustomerInfo crmCus = customerInfoDao.get(cus.getCustomerId());
                if (crmCus != null) {
                    cus.setCustomerName(crmCus.getCustomerName());
                    cus.setEmployeeId(crmCus.getEmployeeId());
                    cus.setCustomerManagerUserId(crmCus.getCustomerManagerUserId());
                }
            }
            resp.setCustomerInfoList(tgmCusList);

        }
        return resp;
    }

    @Override
    public void updateCustomerInfoOnly(CustomerInfo customerInfo, LoginEmployee loginEmployee) {
        if (customerInfo == null) {
            throw new BusinessException("customerInfoIsNull", "errors.paramCanNotNull");
        }
        if (loginEmployee == null) {
            throw new BusinessException("loginUserInfoIsNull", "errors.paramCanNotNull");
        }
        customerInfo.setLastUpdateUserId(loginEmployee.getUserId());
        customerInfo.setLastUpdateTime(new Date());
        customerInfoDao.update(customerInfo);
    }

    @Override
    public List<CustomerInfo> findByLoginUserId(int loginUserId) {
        return null;
    }

    @Override
    public Page<ConsignorCustomerWholeInfoVo> searchCrmCustomer(PageCondition condition, LoginEmployee loginEmployee)
            throws BusinessException {

        if (loginEmployee == null) {
            throw new BusinessException("loginUserNull", "errors.paramCanNotNull");
        }
        if (condition == null) {
            throw new BusinessException("PageConditionNull", "errors.paramCanNotNull");
        }

        Map<String, Object> params = condition.getFilters();
        String statusStr = String.valueOf(params.get("status"));
        com.juma.crm.customer.domain.CustomerInfo.CustomerStatus status = null;
        try {
            status = Enum.valueOf(com.juma.crm.customer.domain.CustomerInfo.CustomerStatus.class, statusStr);
            params.put("status", status.getValue());
        } catch (Exception e) {
            throw new BusinessException("statusError", "errors.paramError");
        }

        Page<ConsignorCustomerInfo> pageData = appConsignorSearchService.search(condition, loginEmployee);

        Collection<ConsignorCustomerInfo> consignorCustomerInfoList = pageData.getResults();
        Collection<ConsignorCustomerWholeInfoVo> consignorWholeInfos = new ArrayList<>();
        // 组装联系人信息
        for (ConsignorCustomerInfo cci : consignorCustomerInfoList) {
            // 获取联系人信息
            ConsignorCustomerWholeInfoVo info = consignorService.findEditInfo(cci.getCustomerId(), loginEmployee);
            if (info == null) {
                continue;
            }
            consignorWholeInfos.add(info);
        }

        Page<ConsignorCustomerWholeInfoVo> data = new Page<>();
        data.setPageNo(pageData.getPageNo());
        data.setPageNumCount(pageData.getPageNumCount());
        data.setPageSize(pageData.getPageSize());
        data.setTotal(pageData.getTotal());
        data.setResults(consignorWholeInfos);
        return data;
    }

    @Override
    public List<CustomerStatusCount> countCustomer(PageCondition condition, LoginEmployee loginEmployee)
            throws BusinessException {
        List<CustomerStatusCount> rst = appConsignorSearchService.countCustomer(condition, loginEmployee);
        return rst;
    }

    @Override
    public void updateBaseInfo(ConsignorBaseInfoVo consignorBaseInfoVo, LoginEmployee loginEmployee)
            throws BusinessException {

        if (cmsCustomerInfoService.checkExists(consignorBaseInfoVo.getCustomerId(),
                consignorBaseInfoVo.getCustomerName(),
                com.juma.crm.customer.domain.CustomerInfo.CustomerType.CONSIGNOR.getValue(), loginEmployee)) {
            throw new BusinessException("CustomerNameExist", "errors.exist.with.param", "客户名称");
        }

        consignorService.updateBaseInfo(consignorBaseInfoVo, loginEmployee);
    }

    @Override
    public ConsignorCustomerWholeInfoWithTgmInfo getConsignorCustomerWholeInfoWithTgmInfo(int crmCustomerId,
                                                                                          LoginEmployee loginEmployee) {
        if (loginEmployee == null) {
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNull");
        }
        ConsignorCustomerWholeInfoVo info = consignorService.find(crmCustomerId, loginEmployee);
        if (info == null) {
            throw new BusinessException("ConsignorCustomerWholeInfoVoNull", "errors.notFound");
        }
        // 通过crmCustomerId获取tgm信息
        // this.findCusInfoById(info.getConsignorCustomerInfo().getCustomerId());
        ConsignorCustomerWholeInfoWithTgmInfo crmCustomerInfo = new ConsignorCustomerWholeInfoWithTgmInfo();
        BeanUtils.copyProperties(info, crmCustomerInfo);

        return crmCustomerInfo;
    }

    @Override
    public void saveVisitationRecord(ConsignorVisitRecordVo consignorVisitRecordVo, LoginEmployee loginEmployee)
            throws BusinessException {
        consignorService.saveVisitationRecord(consignorVisitRecordVo, loginEmployee);
    }

    @Override
    public ConsignorContactsInfo updateContact(ConsignorContactsInfo consignorContactsInfo, LoginEmployee loginEmployee)
            throws BusinessException {
        return consignorService.updateContactsInfo(consignorContactsInfo, loginEmployee);
    }

    @Override
    public ConsignorContactsInfo addContactsInfo(ConsignorContactsInfo consignorContactsInfo,
                                                 LoginEmployee loginEmployee) throws BusinessException {
        return consignorService.saveContactsInfo(consignorContactsInfo, loginEmployee);
    }

    @Override
    public void deleteContactsInfo(ConsignorContactsInfo consignorContactsInfo, LoginEmployee loginEmployee) {
        consignorService.deleteContactsInfo(consignorContactsInfo, loginEmployee);
    }

    @Override
    public void insertEliminationRecord(ConsignorEliminationVo consignorEliminationVo, LoginEmployee loginEmployee) {
        consignorService.insertEliminationRecord(consignorEliminationVo, loginEmployee);
    }

    @Override
    public ConsignorVisitRecord findEliminationRecord(Integer customerId) {
        return consignorService.findEliminationRecord(customerId);
    }

    @Override
    public CustomerInfo addCustomerInfo(CustomerInfoBo customerInfoBo, LoginEmployee loginEmployee)
            throws BusinessException {
        if (customerInfoBo == null) {
            throw new BusinessException("customerInfoBoNull", "errors.paramCanNotNull");
        }
        if (customerInfoBo.getConsignorBaseInfoVo() == null) {
            throw new BusinessException("consignorBaseInfoVoNull", "errors.paramCanNotNull");
        }
        // 业务区域
        ConsignorBaseInfoVo consignorBaseInfo = customerInfoBo.getConsignorBaseInfoVo();
        if (StringUtils.isBlank(consignorBaseInfo.getAreaCode())) {
            throw new BusinessException("areaCodeNull", "errors.paramCanNotNullWithName", "业务区域");
        }

        // 重名验证
        if (cmsCustomerInfoService.checkExists(customerInfoBo.getConsignorBaseInfoVo().getCustomerName(),
                com.juma.crm.customer.domain.CustomerInfo.CustomerType.CONSIGNOR.getValue(), loginEmployee)) {
            throw new BusinessException("CustomerNameExist", "errors.exist.with.param", "客户名称");
        }

        // 新建状态--默认:待定
        consignorBaseInfo.setStatus(ConsignorCustomerInfo.CustomerStatus.UNDETERMINED.getValue());
        consignorBaseInfo.setUserId(loginEmployee.getEmployeeId());
        // 新增客户信息到crm
        ConsignorCustomerInfo crmVo = consignorService.saveBaseInfo(customerInfoBo.getConsignorBaseInfoVo(),
                loginEmployee);

        CustomerInfo localCustomerInfo = customerInfoBo.getCustomerInfo();
        if (localCustomerInfo == null) {
            localCustomerInfo = new CustomerInfo();
            customerInfoBo.setCustomerInfo(localCustomerInfo);
        }
        localCustomerInfo.setCrmCustomerId(crmVo.getCustomerId());

        // 新增到本地库
        // this.insertCustomerInfoOnly(localCustomerInfo, loginUser);
        // 不保存到本地库，通过数据同步保存到本地
        return localCustomerInfo;
    }

    @Override
    public CustomerInfo findByCrmId(int crmId) {
        CustomerInfo example = new CustomerInfo();
        example.setCrmCustomerId(crmId);
        example.setIsDelete(false);
        List<CustomerInfo> list = customerInfoDao.findByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public CustomerInfo findAllByCrmId(int crmId) {
        CustomerInfo example = new CustomerInfo();
        example.setCrmCustomerId(crmId);
        List<CustomerInfo> listFalse = customerInfoDao.findByExample(example);
        if (!listFalse.isEmpty()) {
            return listFalse.get(0);
        }

        example.setIsDelete(true);
        List<CustomerInfo> listTrue = customerInfoDao.findByExample(example);
        if (!listTrue.isEmpty()) {
            return listTrue.get(0);
        }
        return null;
    }

    @Override
    public void increaseWaybillCount(int count, int customerId) throws BusinessException {
        customerInfoDao.increaseWaybillCount(count, customerId);
    }

    @Override
    public int addLocalBasicCustomerInfo(CustomerInfo customerInfo, LoginEmployee loginEmployee)
            throws BusinessException {
        if (customerInfo == null) {
            throw new BusinessException("customerInfoNull", "errors.paramCanNotNull");
        }

        if (loginEmployee == null) {
            throw new BusinessException("loginUserNull", "errors.paramCanNotNull");
        }

        if (customerInfo.getCrmCustomerId() == null) {
            throw new BusinessException("crmCustomerIdNull", "errors.paramCanNotNull");
        }
        if (customerInfo.getCustomerId() != null) {
            throw new BusinessException("CustomerIdNotNull", "errors.paramError");
        }

        customerInfo.setCreateUserId(loginEmployee.getUserId());
        customerInfo.setCreateTime(new Date());

        customerInfoDao.insert(customerInfo);

        return customerInfo.getCustomerId();
    }

    @Override
    public com.juma.crm.customer.domain.CustomerInfo getCrmCustomerInfoByTgmCustomerId(int tgmCustomerId)
            throws BusinessException {
        CustomerInfo tgmCustomer = customerInfoDao.get(tgmCustomerId);
        if (tgmCustomer == null) {
            return null;
        }
        if (tgmCustomer.getCrmCustomerId() == null) {
            return null;
        }
        return cmsCustomerInfoService.findIgnoreDelete(tgmCustomer.getCrmCustomerId());
    }

    @Override
    public List<ConsignorContactsInfo> findContactByCustomerId(int crmCustomerId) {
        return consignorContactsInfoService.findByCustomerId(crmCustomerId);
    }

    @Override
    public List<Chanel> findCustomerSourceByCode(String code) {
        return chanelService.findChildrenByCode(code);
    }

    @Override
    public Chanel getChanelByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        return chanelService.findLevelCode(code);
    }

    @Override
    public CustomerInfoResp findAllCustomerInfoWithManager() throws BusinessException {
        CustomerInfoResp resp = new CustomerInfoResp();
        // 获取所有的TGM sourceCode
        List<Chanel> allChanels = chanelService.findChildrenByCode(Constants.TGM_SOURCE_CHANNEL_CODE);
        if (CollectionUtils.isEmpty(allChanels)) {
            return resp;
        }
        // 通过chanelCode获取所有的tgm企业客户
        List<CustomerInfo> tgmCusts = customerInfoDao.findBySourceChanelCode(allChanels);
        if (CollectionUtils.isEmpty(tgmCusts)) {
            return resp;
        }
        resp.setCustomerInfoList(tgmCusts);

        return resp;
    }

    @Override
    public Page<ConsignorContactsInfo> searchConsignorContactsInfo(PageCondition condition, LoginEmployee loginEmployee)
            throws BusinessException {
        return consignorContactsInfoService.search(condition);
    }

    @Override
    public boolean customerBelongToManager(Integer customerId, Integer managerId) throws BusinessException {
        if (customerId == null || managerId == null) {
            return false;
        }
        int rst = customerInfoDao.countByCustomerIdAndManagerId(customerId, managerId);

        return rst > 0 ? true : false;
    }

    @Override
    public void eliminate(ConsignorCustomerElimination elimination, LoginEmployee loginEmployee)
            throws BusinessException {
        if (elimination == null) {
            throw new BusinessException("eliminationNull", "errors.paramCanNotNull");
        }
        if (elimination.getCustomerId() == null) {
            throw new BusinessException("customerIdNull", "errors.paramCanNotNull");
        }
        if (elimination.getEliminationReason() == null) {
            throw new BusinessException("EliminationReasonNull", "errors.paramCanNotNull");
        }
        if (elimination.getPreviousStatus() == null) {
            throw new BusinessException("PreviousStatusNull", "errors.paramCanNotNull");
        }

        consignorCustomerEliminationService.eliminate(elimination, loginEmployee);
    }

    @Override
    public ConsignorCustomerElimination findLastEliminationByCustomerId(int customerId) throws BusinessException {
        return consignorCustomerEliminationService.findLastByCustomerId(customerId);
    }

    @Override
    public Page<UserUnderCustomer> searchUserUnderCustomer(PageCondition pageCondition, LoginUser loginUser) {
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(Integer.MAX_VALUE);
        List<UserUnderCustomer> rows = new ArrayList<UserUnderCustomer>();
        String truckCustomerId = String.valueOf(pageCondition.getFilters().get("truckCustomerId"));
        if (!NumberUtils.isDigits(truckCustomerId)) {
            return new Page<UserUnderCustomer>(1, 1000, 50, rows);
        }
        return new Page<UserUnderCustomer>(pageCondition.getPageNo(), pageCondition.getPageSize(), rows.size(), rows);
    }

    @Override
    public CrmCustomerInfo findCrmCustomerInfoByCustomerId(Integer customerId, LoginUser loginUser) {
        com.juma.crm.customer.domain.CustomerInfo customerInfo = cmsCustomerInfoService.findIgnoreDelete(customerId);
        CrmCustomerInfo crmCustomer = buildCrmCustomer(customerInfo, loginUser);

        // tgm业务数据
        CustomerInfo tgmCustomerInfo = findByCrmId(customerId);
        if (tgmCustomerInfo != null) {
            crmCustomer.setIsProjectCheckOut(tgmCustomerInfo.getIsProjectCheckOut());
            crmCustomer.setIsReceiveDailySms(tgmCustomerInfo.getIsReceiveDailySms());
        }

        // 物流标签与部门
        CustomerForProductAndDept customerForProductAndDept = crmCommonService.loadCustomerForProductAndDeptByCrmCustomerId(customerId, loginUser);
        if (null != customerForProductAndDept) {
            crmCustomer.setDepartmentId(customerForProductAndDept.getDeparmentId());
            StringBuffer sfLogisticsProduct = new StringBuffer();
            for (LogisticsProduct l : customerForProductAndDept.getListLogisticsProduct()) {
                sfLogisticsProduct.append(l.getName()).append(" | ");
            }

            if (sfLogisticsProduct.length() >= 2) {
                crmCustomer.setLogisticsLabelNames(sfLogisticsProduct.deleteCharAt(sfLogisticsProduct.length() - 2).toString().trim());
            }
        }

        return crmCustomer;
    }

    @Override
    public List<CustomerInfo> listCusInfoByName(String customerName, Boolean isDelete, LoginUser loginUser) {
        if (StringUtils.isBlank(customerName)) {
            return new ArrayList<CustomerInfo>();
        }

        CustomerInfo example = new CustomerInfo();
        example.setTenantId(loginUser.getTenantId());
        example.setCustomerName(customerName);
        example.setCustomerType(2);// 客户

        // 由于CustomerInfo里的isDelete的类型是基本类型，所以不能传空，故当为空时，分别查询true和false的数据，合并后返回
        if (null == isDelete) {
            example.setIsDelete(true);
            List<CustomerInfo> infosByTrue = customerInfoDao.findByExample(example);
            example.setIsDelete(false);
            List<CustomerInfo> infosByfalse = customerInfoDao.findByExample(example);
            infosByTrue.addAll(infosByfalse);
            return infosByTrue;
        }

        example.setIsDelete(isDelete);
        return customerInfoDao.findByExample(example);
    }

    @Override
    public Page<CustomerInfo> findCustomerFromTgm(PageCondition pageCondition, LoginUser loginUser) throws BusinessException{
        if (pageCondition == null) {
            throw new BusinessException("pageConditionNull", "errors.paramCanNotNull");
        }
        Map<String, Object> filters = pageCondition.getFilters();
        if (filters.get("customerManagerUserId") == null) {
            throw new BusinessException("customerManagerUserIdNull", "errors.paramCanNotNull");
        }

        pageCondition.getFilters().put("customerType", com.juma.crm.customer.domain.CustomerInfo.CustomerType.CONSIGNOR.getValue());
        filters.put("isDelete", 0);
        filters.put("tenantId" ,loginUser.getTenantId());
        int count = customerInfoDao.searchCount(pageCondition);
        Page<CustomerInfo> pageData = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0);
        if (count == 0) {
            return pageData;
        }
        List<CustomerInfo> datas = customerInfoDao.search(pageCondition);
        for(CustomerInfo customerInfo : datas){
            customerInfo.setLogisticsProducts(customerManagerService.findLogisticsProduct(customerInfo.getCrmCustomerId(), loginUser));
            Department department = this.findDepartmentByCustomerId(customerInfo.getCrmCustomerId(), loginUser);
            customerInfo.setDepartmentName(department == null ? "" : department.getDepartmentName());
        }
        pageData.setTotal(count);
        pageData.setResults(datas);

        return pageData;
    }

    @Override
    public Page<SearchEnterpriseUserAndCargoOwner> searchEnterpriseUserAndCargoOwner(PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
        //当前登录人是项目经理或运营专员的项目id列表
        List<Integer> projectIdList = projectMemberService.findProjectIdsByUserId(loginEmployee.getUserId(), loginEmployee.getTenantId());
        Page<SearchEnterpriseUserAndCargoOwner> pageData = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0);
        if(projectIdList == null || projectIdList.isEmpty()){
            return pageData;
        }

        Map<String, Object> filter = pageCondition.getFilters();
        filter.put("projectIdList", projectIdList);

        // 当前登录人id
//        Map<String, Object> filter = pageCondition.getFilters();
//        filter.put("employeeId", loginEmployee.getEmployeeId());
        // filter.put("allChanels", allChanels);

        // 搜索的名称
        List<SearchEnterpriseUserAndCargoOwner> datas = customerInfoDao.searchEnterpriseUserAndCargoOwner(pageCondition);
        pageData.setPageNo(pageCondition.getPageNo());
        pageData.setPageSize(pageCondition.getPageSize());
        pageData.setResults(datas);
        pageData.setTotal(datas.size());

        return pageData;
    }

    @Override
    public List<CustomerInfo> listCustomerInfo(PageCondition pageCondition) {
        return customerInfoDao.search(pageCondition);
    }

    @Override
    public BigDecimal getRebateRate(Integer tgmCustomerId) {
        if (tgmCustomerId == null || tgmCustomerId == 0) {
            return BigDecimal.ZERO;
        }
        CustomerInfo customerInfo = customerInfoDao.get(tgmCustomerId);
        if (customerInfo == null || customerInfo.getCrmCustomerId() == null || customerInfo.getCrmCustomerId() == 0) {
            return BigDecimal.ZERO;
        }
        try {
            ConsignorCustomerInfo consignorCustomerInfo = consignorCustomerInfoService
                    .findByCustomerId(customerInfo.getCrmCustomerId());

            if (consignorCustomerInfo == null) {
                return BigDecimal.ZERO;
            }

            return consignorCustomerInfo.getRebateRate() == null ? BigDecimal.ZERO
                    : consignorCustomerInfo.getRebateRate();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return BigDecimal.ZERO;
    }

    @Override
    public List<CustomerInfo> findCustomerInfoByCustomerManagerId(int customerManagerId) {
        CustomerInfo example = new CustomerInfo();
        example.setCustomerManagerUserId(customerManagerId);
        List<CustomerInfo> datas = customerInfoDao.findByExample(example);
        return datas;
    }

    @Override
    public Page<ScatteredCustomerVo> scatteredCustomerSearch(PageCondition pageCondition,
                                                             ScatteredCustomerVo.CustomerType type) throws BusinessException {
        if (type == null)
            throw new BusinessException("customerTypeNull", "errors.paramCanNotNullWithName", "成单类型");

        Page<ScatteredCustomerVo> pageData = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0);
        pageCondition.getFilters().put("type", type.getCode());
        pageCondition.getFilters().put("customerType", 2);// 货主
        pageCondition.setOrderBy("ci.customer_id");
        pageCondition.setOrderSort("DESC");
        // 按类型获取客户列表
        int count = customerInfoDao.scatteredCustomerSearchCount(pageCondition);
        if (count <= 0)
            return pageData;

        pageData.setTotal(count);
        List<CustomerInfo> rawData = customerInfoDao.scatteredCustomerSearch(pageCondition);
        List<ScatteredCustomerVo> finalData = new ArrayList<>();

        ScatteredCustomerVo vo = null;
        for (CustomerInfo info : rawData) {
            vo = new ScatteredCustomerVo();
            vo.setCustomerInfo(info);
            finalData.add(vo);
            // 联系人
            List<ConsignorContactsInfo> contactsInfos = consignorContactsInfoService
                    .findByCustomerId(info.getCrmCustomerId());
            vo.setConsignorContactsInfos(contactsInfos);

            // 统计每个客户的成单量和金额
            if (NumberUtils.compare(type.getCode(), ScatteredCustomerVo.CustomerType.TYPE_DEAL.getCode()) != 0)
                continue;

            this.countCustomerBillInfo(info, vo);
        }

        pageData.setResults(finalData);

        return pageData;
    }

    /**
     * 计算每个客户的成单量和金额
     *
     * @param info
     * @param vo
     */
    private void countCustomerBillInfo(CustomerInfo info, ScatteredCustomerVo vo) {
        List<Integer> statusViewList = Arrays.asList(new Integer[] { Waybill.StatusView.FINISH.getCode() });
        WaybillStatisticsParamVo paramVo = new WaybillStatisticsParamVo();
        paramVo.put("statusViewList", statusViewList);
        paramVo.put("customerId", info.getCustomerId());
        ScatteredCustomerVo countInfo = customerManagerStatisticsService.countFreightAndBillCount(paramVo);

        vo.setTotalCost(countInfo.getTotalCost());
        vo.setWaybillAmount(countInfo.getWaybillAmount());
    }

    @Override
    public ScatteredCustomerVo.OverViewDataVo countOverViewData(int customerManagerId, ScatteredCustomerVo.CustomerType type) throws BusinessException {
        ScatteredCustomerVo.OverViewDataVo overViewDataVo = customerManagerStatisticsService.countCustomerOverViewData(customerManagerId, type);

        return overViewDataVo;
    }

    @Override
    public List<TruckCustomer> findCargoOwnerFromCrm(int crmCustomerId, LoginEmployee loginUser)
            throws BusinessException {
        // 获取crm联系人
        List<ConsignorContactsInfo> crmData = crmService.listContactsInfo(crmCustomerId,
                ConsignorSupportService.ConsignorContactsListType.IS_AUTH_USER, loginUser);
        if (CollectionUtils.isEmpty(crmData))
            return null;
        //找到企业货主
        com.juma.crm.customer.domain.CustomerInfo customerInfo = crmService.find(crmCustomerId, loginUser);
        if(customerInfo == null) return null;

        LoginEcoUser loginEcoUser = new LoginEcoUser();
        loginEcoUser.setTenantId(customerInfo.getTenantId());
        // 在本地查找货主
        List<TruckCustomer> truckCustomers = new ArrayList<>();
        for (ConsignorContactsInfo contactsInfo : crmData) {
            //先找到货主
            loginEcoUser.setUserId(contactsInfo.getUserId());
        }

        return truckCustomers;
    }

    @Override
    public ConsignorContactsInfo findConsignorContactsByUser(LoginUser loginUser) throws BusinessException {
        // 获取用户手机号
        UserInfo userInfo = null;
        try {
            userInfo = userService.findUserInfo(loginUser.getUserId());
        } catch (BusinessException e) {
            logger.error("获取用户数据错误，参数:" + loginUser.getUserId(), e);
            return null;
        }

        // 查询crm
        ConsignorContactsInfo contactsInfo = null;
        try {
            contactsInfo = crmService.getConsignorContactsInfo(userInfo.getMobileNumber(), loginUser);
        } catch (Exception e) {
            logger.error("crm联系人数据错误，userId:{},phone:{}",
                    new String[] { loginUser.getUserId() + "", userInfo.getMobileNumber()});
            return null;
        }

        return contactsInfo;
    }

    @Override
    public void addContactToCustomer(CustomerInfo customerInfo, LoginUser loginUser) throws BusinessException {
        if (customerInfo == null)
            return;

        ConsignorContactsInfo contactsInfo = new ConsignorContactsInfo();
        UserInfo userInfo = null;
        try {
            userInfo = userService.findUserInfo(loginUser.getUserId());
        } catch (BusinessException e) {
            logger.error("获取用户中心数据错误,参数:{}", new String[] { loginUser.getUserId() + "" }, e);
        }
        contactsInfo.setContactsName(userInfo.getName());
        contactsInfo.setContactsPhone(userInfo.getMobileNumber());
        contactsInfo.setUserId(loginUser.getUserId());
        contactsInfo.setCustomerId(customerInfo.getCrmCustomerId());

        contactsInfo.setCreateUserId(loginUser.getUserId());
        try {
            consignorContactsInfoService.create(contactsInfo);
        } catch (Exception e) {
            logger.error("创建企业货主联系人错误,参数:{}", new String[] { contactsInfo.getContactsPhone() }, e);
        }

    }

    @Override
    public void updateBatch(List<CustomerInfo> example, LoginUser loginUser) throws BusinessException {
        customerInfoDao.updateBatch(example);
    }

    @Override
    public void update(CustomerInfo toCustomerInfo, CustomerInfo fromCustomerInfo) throws BusinessException {
        customerInfoDao.updateByAreaOrManager(toCustomerInfo, fromCustomerInfo);
    }

    @Override
    public Department findDepartmentByCustomerId(Integer crmCustomerId, LoginUser loginUser) throws BusinessException {

        if(crmCustomerId == null){
            return null;
        }
        Customer4TmsBo customer4TmsBo = crm4TmsService.findProductAndDepId(crmCustomerId, loginUser);
        if(customer4TmsBo == null || customer4TmsBo.getDepartmentId() == null){
            return null;
        }
        return departmentService.loadDepartment(customer4TmsBo.getDepartmentId());
    }
}
