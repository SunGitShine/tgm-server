package com.juma.tgm.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.giants.common.tools.PageQueryCondition;
import com.google.common.collect.Lists;
import com.juma.auth.employee.domain.ECompany;
import com.juma.auth.employee.domain.EmployeeFilter;
import com.juma.auth.employee.domain.EmployeeInfo;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.employee.service.EmployeeService;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.conf.domain.ConfParamOption;
import com.juma.crm.contract.domain.ConsignorVo;
import com.juma.crm.contract.domain.Contract;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.id.IdGeneratorTable;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;
import com.juma.tgm.customerManager.service.Task4WaybillService;
import com.juma.tgm.daily.service.ProjectDailyService;
import com.juma.tgm.fms.domain.ReconciliationItem;
import com.juma.tgm.fms.service.ReconciliationService;
import com.juma.tgm.id.service.IdGeneratorService;
import com.juma.tgm.project.dao.ProjectDao;
import com.juma.tgm.project.dao.ProjectMapper;
import com.juma.tgm.project.dao.ProjectMemberMapper;
import com.juma.tgm.project.dao.ext.ProjectExtMapper;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.domain.v2.*;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.service.*;
import com.juma.tgm.project.service.ProjectProcessService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.project.vo.ProjectButtonCtrl;
import com.juma.tgm.project.vo.ProjectFilter;
import com.juma.tgm.project.vo.ProjectVoAndInfo;
import com.juma.tgm.project.vo.RoadMapVo;
import com.juma.tgm.project.vo.v2.ProjectParamVo;
import com.juma.tgm.project.vo.v2.ProjectVo;
import com.juma.tgm.project.vo.v2.ProjectVoApp;
import com.juma.tgm.task.domain.TaskScheduledExample;
import com.juma.tgm.task.enums.TaskEnum;
import com.juma.tgm.task.service.TaskScheduledService;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.BusinessAreaCommonService;
import com.juma.tgm.tools.service.CommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import com.juma.tgm.waybill.domain.TruckRequire;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import com.juma.tgm.waybill.service.WaybillService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final static Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private static final String NEXT_DAY_DELIVERY = "14";
    private static final String DELIVERY_RECEIPT = "13";
    private static final int PROJECT_NAME_MAX = 20;
    private static final int PROJECT_NAME_MIN = 4;
    private static final int PROJECT_DATE_MAX = 60;

    @Resource
    private ProjectDao projectDao;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private WaybillService waybillService;
    @Resource
    private BusinessAreaCommonService businessAreaCommonService;
    @Resource
    private CommonService commonService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private ReconciliationService reconciliationService;
    @Resource
    private RoadMapService roadMapService;
    @Autowired
    private Task4WaybillService task4WaybillService;
    @Resource
    private CrmCommonService crmCommonService;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private ProjectExtMapper projectExtMapper;
    @Resource
    private ProjectProcessService projectProcessService;
    @Resource
    private IdGeneratorService idGeneratorService;
    @Resource
    private AuthCommonService authCommonService;
    @Resource
	private ProjectMemberMapper projectMemberMapper;
    @Resource
    private ProjectMemberService projectMemberService;
    @Resource
    private ProjectDepotService projectDepotService;
    @Resource
	private TaskScheduledService taskScheduledService;
    @Resource
    private ProjectDailyService projectDailyService;

    @Override
    public void addProjectDepots(List<ProjectDepot> projectDepots,LoginUser loginUser) throws BusinessException {
        if (projectDepots == null
                || projectDepots.isEmpty()) {
            throw new BusinessException("paramError","参数错误");
        }

        Set<String> nameSet = new HashSet<>();
        List<ProjectDepot> insertDepots = new ArrayList<>();
        List<ProjectDepot> updateDepots = new ArrayList<>();
        for ( ProjectDepot projectDepot : projectDepots ) {
            if (projectDepot.getProjectId() == null
                    || StringUtils.isBlank(projectDepot.getDepotName())
                    || StringUtils.isBlank(projectDepot.getDepotAddress())
                    || StringUtils.isBlank(projectDepot.getDepotCoordinates())) {
                throw new BusinessException("paramError","项目、仓库名字、地址、坐标必填");
            }
            ProjectDepotExample example = new ProjectDepotExample().createCriteria()
                .andDepotNameEqualTo(projectDepot.getDepotName())
                .andProjectIdEqualTo(projectDepot.getProjectId())
                .andDepotIdNotEqualTo(projectDepot.getDepotId()).example();
            long count = projectDepotService.countByExample(example);
            if(count > 0){
                throw new BusinessException("nameError", "仓库名字不能重复");
            }

            nameSet.add(projectDepot.getDepotName());
            projectDepot.setIsDelete(false);
            projectDepot.setCreateTime(new Date());
            projectDepot.setCreateUserId(loginUser.getUserId());
            projectDepot.setLastUpdateTime(projectDepot.getCreateTime());
            projectDepot.setLastUpdateUserId(projectDepot.getCreateUserId());

            if (projectDepot.getDepotId() == null || projectDepot.getDepotId() == 0) {
                insertDepots.add(projectDepot);
            } else {
                updateDepots.add(projectDepot);
            }
        }
        if(projectDepots.size() != nameSet.size()){
            throw new BusinessException("nameError", "仓库名字不能重复");
        }
        if (!insertDepots.isEmpty()) {
            projectDepotService.insertBatch(insertDepots);
        }
        if (!updateDepots.isEmpty()) {
            projectDepotService.updateBatchByPrimaryKeySelective(updateDepots);
        }
    }

    @Override
    public void deleteProjectDepot(Integer projectDepotId,LoginUser loginUser) throws BusinessException {
        if (projectDepotId == null) return;
        ProjectDepot projectDepot = projectDepotService.withDepotId(projectDepotId);
        if (projectDepot != null) {
            projectDepot.setIsDelete(true);
            projectDepot.setLastUpdateUserId(loginUser.getUserId());
            projectDepot.setLastUpdateTime(new Date());
            projectDepotService.updateByPrimaryKeySelective(projectDepot);
        }
    }

    @Override
    public List<ProjectDepot> listProjectDepot(Integer projectId) {
        if (projectId == null) return Lists.newArrayList();
        List<ProjectDepot> projectDepots = projectDepotService.selectByExample(new ProjectDepotExample().createCriteria()
                .andProjectIdEqualTo(projectId).example());
        return projectDepots;
    }

    @Override
    public Page<com.juma.tgm.project.domain.v2.Project> searchProject(QueryCond<ProjectFilter> filter,LoginUser loginUser) {

        ProjectFilter filters = filter.getFilters();
        //crm id转 tgm id
        Integer crmCustomerId = filters.getCrmCustomerId();
        if(crmCustomerId != null) {
            CustomerInfo customer =  customerInfoService.findByCrmId(crmCustomerId);
            if(customer == null) {
                return new Page<com.juma.tgm.project.domain.v2.Project>();
            }
            filters.setCustomerId(customer.getCustomerId());
        }


        ProjectExample example = buildProjectExample(filter, loginUser);
        long total = projectMapper.countByExample(example);
        List<com.juma.tgm.project.domain.v2.Project> rows = projectMapper.selectByExample(example);
        return new Page<com.juma.tgm.project.domain.v2.Project>(filter.getPageNo(), filter.getPageSize(), Integer.parseInt(total+""), rows);
    }

    @Override
    public List<Project> findProject(int customerId) {
        Project example = new Project();
        example.setCustomerId(customerId);
        List<Project> rawDatas = projectDao.findByExample(example);
        return rawDatas;
    }

    @Override
    public Project getProject(int projectId) {
        return projectDao.get(projectId);
    }

    @Override
    public com.juma.tgm.project.domain.v2.Project getProjectV2(Integer projectId) {
        if (null == projectId) {
            return null;
        }

        return projectMapper.selectByPrimaryKey(projectId);
    }

    @Override
    public com.juma.tgm.project.domain.v2.Project getByProjectNoV2(String projectNo) {
        List<com.juma.tgm.project.domain.v2.Project> projects = projectMapper.selectByExample(
                new ProjectExample().createCriteria().andProjectNoEqualTo(projectNo).example());
        if(CollectionUtils.isEmpty(projects)){
            return null;
        }
        return projects.get(0);
    }

    @Override
    public List<com.juma.tgm.project.domain.v2.Project> listProjectDailySms(String startTime, String endTime) {
        return projectExtMapper.listProjectDailySms(startTime,endTime);
    }

    @Override
    public ProjectVo getProjectVo(Integer projectId, LoginUser loginUser) {
        com.juma.tgm.project.domain.v2.Project project = this.getProjectV2(projectId);
        if (null == project) {
            return null;
        }

        ProjectVo vo = new ProjectVo();
        BeanUtils.copyProperties(project, vo);

        // 客户名称
        CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
        vo.setCustomerName(customerInfo == null ? null : customerInfo.getCustomerName());

        // 项目经理
        User user = authCommonService.loadUser(project.getProjectManagerUserId());
        vo.setProjectManagerUserName(user == null ? null : user.getName());
        vo.setProjectManagerUserPhone(user == null ? null : user.getMobileNumber());

        // 业务区域
        vo.setAreaName(businessAreaCommonService.loadLogicAndSelfAreaName(project.getAreaCode(), loginUser));

        // 物流产品
        Map<String, String> mapLogisticsProduct = crmCommonService.mapLogisticsProduct(loginUser);
        String logisticsName = mapLogisticsProduct.get(project.getLogisticsLabel());
        vo.setLogisticsLabelName(StringUtils.isBlank(logisticsName) ? "" : logisticsName);

        //签约方名称
        if(StringUtils.isNotBlank(project.getContractToCompanyCreditCode())){
            ECompany eCompany = authCommonService.loadCreditCode(project.getContractToCompanyCreditCode());
            vo.setContractToCompanyName(eCompany == null ? "" : eCompany.getCompanyName());
        }
        //运作方名称
        if(StringUtils.isNotBlank(project.getPayToCompanyCreditCode())){
            ECompany eCompany = authCommonService.loadCreditCode(project.getPayToCompanyCreditCode());
            vo.setPayToCompanyName(eCompany == null ? "" : eCompany.getCompanyName());
        }

        if(null != project.getContractNo()){
            Contract contract = crmCommonService.getByContractNo(project.getContractNo(), loginUser);
            if(null != contract){
                vo.setContractName(contract.getContractName());
                //归档附件
                vo.setContractEnclosureUrl(contract.getArchiveContent());
                ConsignorVo consignorVo = JSON.parseObject(contract.getContent(), ConsignorVo.class);
                vo.setPayToCompanyEnclosureUrl(consignorVo == null ? null : JSONArray.toJSONString(consignorVo.getTransportAttachmentList()));
            }
        }

        return vo;
    }

    @Override
    public Page<Project> search(LoginUser loginUser, PageCondition pageCondition) throws BusinessException {
        if (loginUser == null)
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "登录信息");

        Page<Project> pageData = new Page<>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                new ArrayList<Project>());

        if (null == loginUser.getTenantId()) {
            return pageData;
        }

        Map<String, Object> filters = pageCondition.getFilters();
        if (filters == null) {
            filters = new HashMap<>();
        }
        filters.put("isEnable", true);
        filters.put("tenantId", loginUser.getTenantId());

        // 通过客户id获取项目列表
        int count = projectDao.joinSearchCount(pageCondition);
        if (count <= 0) return pageData;

        // 所有物流产品标签
        Map<String, String> mapLogisticsProduct = crmCommonService.mapLogisticsProduct(loginUser);

        List<Project> rawDatas = projectDao.joinSearch(pageCondition);
        if (CollectionUtils.isNotEmpty(rawDatas)) {
            for (Project project : rawDatas) {
                Integer roadNum = this.roadMapService.countRoadNum(project.getProjectId());
                Integer timeOrderCountNum = task4WaybillService.countTaskByProject(project.getProjectId());
                // 设置标签信息
                Object logisticsNameOb = mapLogisticsProduct.get(project.getLogisticsLabel());
                project.setLogisticsName(logisticsNameOb == null ? "" : (String) logisticsNameOb);
                project.setRoadCount(roadNum);
                project.setTimeOrderCount(timeOrderCountNum);
            }
        }
        pageData.setTotal(count);
        pageData.setResults(rawDatas);
        // 用车人信息//客户端按需组装
        // 计价规则//客户端按需组装
        return pageData;
    }

    @Override
    public Page<ProjectVoApp> searchV2(QueryCond<ProjectParamVo> projectQueryCond,
        LoginUser loginUser) throws BusinessException {

        if (loginUser == null)
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "登录信息");

        List<Integer> projectIdList = new ArrayList<>();
		//只查询当前登录人是运营专员或项目经理的项目
		ProjectMemberExample example = new ProjectMemberExample();
		ProjectMemberExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(loginUser.getUserId());
		criteria.andTenantIdEqualTo(loginUser.getTenantId());
		List<ProjectMember> projectMembers = projectMemberMapper.selectByExample(example);
		for(ProjectMember projectMember : projectMembers){
			projectIdList.add(projectMember.getProjectId());
		}

        Page<ProjectVoApp> pageData = new Page<>(projectQueryCond.getPageNo(), projectQueryCond.getPageSize(), 0, new ArrayList<ProjectVoApp>());

        if (null == loginUser.getTenantId() || projectIdList.isEmpty()) {
            return pageData;
        }

		ProjectParamVo projectFilter = projectQueryCond.getFilters();
        if (projectFilter == null) {
			projectFilter = new ProjectParamVo();
        }
		projectFilter.setTenantId(loginUser.getTenantId());
		projectFilter.setIsEnable(true);
		projectFilter.setProjectIdList(projectIdList);
		projectQueryCond.setFilters(projectFilter);

        int count = projectExtMapper.joinSearchCount(projectQueryCond);
        if (count <= 0) return pageData;

        // 所有物流产品标签
        Map<String, String> mapLogisticsProduct = crmCommonService.mapLogisticsProduct(loginUser);

        List<com.juma.tgm.project.domain.v2.Project> rawDatas = projectExtMapper.joinSearch(projectQueryCond);
        List<ProjectVoApp> projectVoApps = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(rawDatas)) {
            for (com.juma.tgm.project.domain.v2.Project project : rawDatas) {
				ProjectVoApp projectVoApp = new ProjectVoApp();
				BeanUtils.copyProperties(project, projectVoApp);
                Integer roadNum = this.roadMapService.countRoadNum(project.getProjectId());
                Integer timeOrderCountNum = task4WaybillService.countTaskByProject(project.getProjectId());
                // 设置标签信息
                Object logisticsNameOb = mapLogisticsProduct.get(project.getLogisticsLabel());
				projectVoApp.setLogisticsName(logisticsNameOb == null ? "" : (String) logisticsNameOb);
				projectVoApp.setRoadCount(roadNum);
				projectVoApp.setTimeOrderCount(timeOrderCountNum);
				//项目经理
				User user = authCommonService.loadUser(project.getProjectManagerUserId());
				projectVoApp.setProjectManagerUserName(user == null ? "" : user.getName());
				projectVoApp.setProjectManagerUserPhone(user == null ? "" : user.getMobileNumber());

				//设置任务数量
				projectVoApp.setTaskWaitingBecomeNum(taskScheduledService.taskNumByProject(project.getProjectId(),
					TaskEnum.TaskStatus.Waiting_Become.getCode()));
				projectVoApp.setTaskRunningNum(taskScheduledService.taskNumByProject(project.getProjectId(),
					TaskEnum.TaskStatus.Running.getCode()));
				projectVoApp.setTaskPauseNum(taskScheduledService.taskNumByProject(project.getProjectId(),
					TaskEnum.TaskStatus.Pause.getCode()));

				projectVoApps.add(projectVoApp);
            }
        }
        pageData.setTotal(count);
        pageData.setResults(projectVoApps);
        // 用车人信息//客户端按需组装
        // 计价规则//客户端按需组装
        return pageData;
    }

    @Override
    public Integer add(Project project, LoginUser loginUser) throws BusinessException {
        if (loginUser == null)
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "登录信息");

        if (project == null) throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目信息");

        if (project.getTaxRateValue() == null) {
            throw new BusinessException("taxRateValue", "errors.paramCanNotNullWithName", "税率");
        }
        if (StringUtils.isBlank(project.getName()))
            throw new BusinessException("projectNameNull", "errors.paramCanNotNullWithName", "项目名称");

        if (project.getCustomerId() == null)
            throw new BusinessException("customerNull", "errors.paramCanNotNullWithName", "客户");

        if (project.getManagerId() == null)
            throw new BusinessException("customerNull", "errors.paramCanNotNullWithName", "客户经理");

        if (project.getTruckCustomerId() == null)
            throw new BusinessException("truckCustomerNull", "errors.paramCanNotNullWithName", "用车人");

        if (StringUtils.isBlank(project.getGoodsType()))
            throw new BusinessException("goodsTypeNull", "errors.paramCanNotNullWithName", "货物类型");

        if (!this.checkProjectName(project.getName(), null, loginUser))
            throw new BusinessException("projectNameNull", "errors.common.prompt", "当前项目名称已存在");

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
        if (customerInfo == null) throw new BusinessException("customerNotExist", "errors.common.prompt", "客户不存在");

        if (crmCommonService.isShowLogisticsProduct(loginUser) && StringUtils.isBlank(project.getLogisticsLabel())) {
            throw new BusinessException("logisticsLabelNotNull", "物流产品标签不能为空");
        }

        project.setTenantCode(customerInfo.getTenantCode());
        project.setAreaCode(customerInfo.getAreaCode());
        project.setTenantId(loginUser.getTenantId());
        project.setTenantCode(loginUser.getTenantCode());
        project.setCreateTime(new Date());
        project.setCreateUserId(loginUser.getUserId());
        project.setIsEnable(true);
        project.setIsReceivableFirst(true);

        // 保存基础信息
        projectMapper.insertSelective(project);
        return project.getProjectId();
    }

    @Override
    public boolean checkProjectName(String name, Integer projectId, LoginUser loginUser) throws BusinessException {
        if (StringUtils.isBlank(name) || null == loginUser || null == loginUser.getTenantId()) {
            return false;
        }

        ProjectExample example = new ProjectExample().createCriteria().andProjectIdNotEqualTo(projectId)
                .andNameEqualTo(name).andTenantIdEqualTo(loginUser.getTenantId()).andIsEnableEqualTo(true).example();

        if (projectMapper.countByExample(example) > 0) {
            return false;
        }

        return true;
    }

    @Override
    public void update(Project project, LoginUser loginUser) throws BusinessException {
        if (project == null) throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目信息");
        if (project.getProjectId() == null)
            throw new BusinessException("projectIdNull", "errors.paramCanNotNullWithName", "项目id");

        if (StringUtils.isBlank(project.getName()))
            throw new BusinessException("projectNameNull", "errors.paramCanNotNullWithName", "项目名称");

        if (StringUtils.isBlank(project.getGoodsType()))
            throw new BusinessException("goodsTypeNull", "errors.paramCanNotNullWithName", "货物类型");

        if (!this.checkProjectName(project.getName(), project.getProjectId(), loginUser))
            throw new BusinessException("projectNameNull", "errors.uniqueness", "项目名称");

        Project fromDb = getProject(project.getProjectId());
        if (fromDb == null) throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目信息");
        // 更新基础信息
        // 项目类型和客户名称不能修改
        project.setCustomerId(null);
        projectDao.update(project);

        // 项目名字变更通知运单变更
        if (fromDb.getName() != null && project.getName() != null && !(fromDb.getName().equals(project.getName()))) {
            // 更新运单表冗余字段
            Waybill example = new Waybill();
            example.setProjectId(project.getProjectId());

            Waybill newValue = new Waybill();
            newValue.setProjectName(project.getName());
            waybillService.updateWaybillBatch(example, newValue, loginUser);
        }

        // 更新ReconciliationItem表中的冗余字段
        ReconciliationItem item = new ReconciliationItem();
        item.setProjectId(project.getProjectId());
        item.setProjectName(project.getName());
        reconciliationService.updateReconciliationItemByProjectId(item);
    }

    @Override
    @Transactional
    public void update(List<Project> projects, LoginUser loginUser) throws BusinessException {
        if (projects == null || projects.isEmpty()) {
            return;
        } else {
            for (Project project : projects) {
                this.projectDao.update(project);
            }
        }
    }

    @Override
    public void del(Integer projectId, LoginUser loginUser) throws BusinessException {
        if (null == projectId) {
            return;
        }
        com.juma.tgm.project.domain.v2.Project project = new com.juma.tgm.project.domain.v2.Project();
        project.setProjectId(projectId);
        project.setIsEnable(false);
        projectMapper.updateByPrimaryKeySelective(project);

    }

    @Override
    public Project findProjectByName(String projectName, LoginUser loginUser) {
        if (StringUtils.isBlank(projectName) || null == loginUser || null == loginUser.getTenantId()) {
            return null;
        }

        Project example = new Project();
        example.setName(projectName);
        example.setTenantId(loginUser.getTenantId());
        List<Project> list = projectDao.findByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Page<Project> searchBackSys(PageCondition pageCondition, LoginUser loginUser) {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new Page<Project>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<Project>());
        }
        buildCondition(pageCondition, loginUser);
        pageCondition.getFilters().put("isEnable", true);
        pageCondition.getFilters().put("tenantId", loginUser.getTenantId());
        Page<Project> page = search(loginUser, pageCondition);
        for (Project project : page.getResults()) {
            project.setTimeOrderCount(task4WaybillService.countTaskByProject(project.getProjectId()));
            // 客户经理
            User user = authCommonService.loadUserByEmployeeId(project.getManagerId(), loginUser);
            if (null != user) {
                project.setCustomerManagerName(user.getName());
            }

            // 业务区域
            project.setAreaName(businessAreaCommonService.loadLogicAndSelfAreaName(project.getAreaCode(), loginUser));
        }
        return page;
    }

    @Override
    public Page<ProjectVo> searchBackSysV2(QueryCond<ProjectFilter> queryCond, LoginEmployee loginEmployee) {
        List<ProjectVo> result = new ArrayList<>();
        if (null == loginEmployee || null == loginEmployee.getTenantId()) {
            return new Page<ProjectVo>(queryCond.getPageNo(), queryCond.getPageSize(), 0, result);
        }

        Boolean isCityManager = authCommonService.isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, loginEmployee);

        // 项目结束后可以续签合同的有效期限
        List<ConfParamOption> options = authCommonService.listOption(Constants.PROJECT_RENEWAL_CONTRACT_TERM_KEY);
        Integer term = options.isEmpty() ? 15 : (StringUtils.isNumeric(options.get(0).getOptionValue()) ?
                Integer.parseInt(options.get(0).getOptionValue()) : 15);


        ProjectExample example = buildProjectExample(queryCond, loginEmployee);

        long total = projectMapper.countByExample(example);
        List<com.juma.tgm.project.domain.v2.Project> rows = projectMapper.selectByExample(example);

        // 所有物流产品标签
        Map<String, String> mapLogisticsProduct = crmCommonService.mapLogisticsProduct(loginEmployee);

        for (com.juma.tgm.project.domain.v2.Project project : rows) {
            ProjectVo vo = new ProjectVo();
            BeanUtils.copyProperties(project, vo);

            //承诺毛利率
            if(null != project.getProfitRate()){
                vo.setProfitRateDesc(BaseUtil.formatDecimal(project.getProfitRate().doubleValue()));
            }

            // 物流产品标签名称
            vo.setLogisticsLabelName(mapLogisticsProduct.get(project.getLogisticsLabel()));

            // 客户名称
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
            vo.setCustomerName(customerInfo == null ? null : customerInfo.getCustomerName());

            // 项目经理
            User user = authCommonService.loadUser(project.getProjectManagerUserId());
            vo.setProjectManagerUserName(user == null ? null : user.getName());

            // 客户经理
            User manager = authCommonService.loadUserByEmployeeId(project.getManagerId(), loginEmployee);
            vo.setCustomerManageName(manager == null ? null : manager.getName());

            // 业务区域
            vo.setAreaName(businessAreaCommonService.loadLogicAndSelfAreaName(project.getAreaCode(), loginEmployee));

            Date projectEndDate = project.getProjectEndDate();

            // 审核状态
            ProjectWorkflow projectWorkflow = projectProcessService.findByProjectId(project.getProjectId());
            if (null == projectWorkflow || null == projectWorkflow.getAuditStatus()
                    || null == projectWorkflow.getProjectWorkflowType()) {
                vo.setProjectButtonContrl(new ProjectButtonCtrl(project.getProjectStatus(), project.getProjectType(),
                        null, null, projectEndDate, term, project.getCreateUserId(),
                        project.getProjectManagerUserId(), isCityManager, loginEmployee));
                result.add(vo);
                continue;
            }

            vo.setProjectWorkflow(projectWorkflow);
            // 若为结束申请审批流程，则获取申请执行时间，否则去项目结束时间
            if (projectWorkflow.getProjectWorkflowType() == ProjectEnum.WorkflowType.END.getCode()
                    && projectWorkflow.getAuditStatus() == ProjectEnum.AuditStatus.AGREE.getCode()) {
                projectEndDate = projectWorkflow.getExcuteTime();
            }
            vo.setProjectButtonContrl(new ProjectButtonCtrl(project.getProjectStatus(), project.getProjectType(),
                    projectWorkflow.getAuditStatus(), projectWorkflow.getProjectWorkflowType(), projectEndDate, term,
                    project.getCreateUserId(), project.getProjectManagerUserId(), isCityManager, loginEmployee));
            vo.setAuditStatusName(ProjectEnum.WorkflowType.getdescByCode(projectWorkflow.getProjectWorkflowType()) + "-"
                    + ProjectEnum.AuditStatus.getdescByCode(projectWorkflow.getAuditStatus()));
            result.add(vo);
        }

        return new Page<ProjectVo>(queryCond.getPageNo(), queryCond.getPageSize(),
                Integer.parseInt(String.valueOf(total)), result);
    }

    @Override
    public List<com.juma.tgm.project.domain.v2.Project> listProjectBy(String name, Integer customerId, Integer backPageSize,
            Boolean isEnable, LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<>();
        }
        backPageSize = backPageSize == null ? 15 : (NumberUtils.compare(backPageSize, 200) == 1 ? 200 : backPageSize);
        ProjectExample example = new ProjectExample().createCriteria().andTenantIdEqualTo(loginUser.getTenantId())
                .andNameLike(StringUtils.isBlank(name) ? null : ("%" + name + "%"))
                .andCustomerIdEqualTo(customerId)
                .andIsEnableEqualTo(isEnable)
                .example();
        example.setStartOffSet(0);
        example.setSize(backPageSize);
        return projectMapper.selectByExample(example);
    }

    @Override
    public List<com.juma.tgm.project.domain.v2.Project> listProjectBy(ProjectFilter projectFilter, Integer backPageSize,
        LoginUser loginUser) throws BusinessException {
        if (null == loginUser || null == loginUser.getTenantId()) {
            return new ArrayList<>();
        }
        List<Integer> projectIds = projectMemberService.findProjectIdsByUserId(loginUser.getUserId(), loginUser.getTenantId());
        if(projectIds == null || projectIds.size() == 0){
            return new ArrayList<>();
        }
        backPageSize = backPageSize == null ? 15 : (NumberUtils.compare(backPageSize, 200) == 1 ? 200 : backPageSize);
        ProjectExample example = new ProjectExample().createCriteria().andTenantIdEqualTo(loginUser.getTenantId())
            .andNameLike(StringUtils.isBlank(projectFilter.getName()) ? null : ("%" + projectFilter.getName() + "%"))
            .andCustomerIdEqualTo(projectFilter.getCustomerId())
            .andProjectStatusIn(projectFilter.getProjectStatusList())
            .andProjectIdIn(projectIds)
            .andIsEnableEqualTo(true)
            .example();
        example.setStartOffSet(0);
        example.setSize(backPageSize);
        return projectMapper.selectByExample(example);
    }

    @Override
    public List<ProjectVo> listProjectByLoginUserV2(String name, Integer customerId,
        Integer backPageSize, Boolean isEnable, LoginUser loginUser) throws BusinessException {
        List<ProjectVo> result = new ArrayList<>();
        if (null == loginUser || null == loginUser.getTenantId() || null == loginUser.getUserId()) {
            return result;
        }

        List<Integer> projectIds = projectMemberService.findProjectIdsByUserId(loginUser.getUserId(), loginUser.getTenantId());
        if(projectIds == null || projectIds.size() == 0){
            return result;
        }

        backPageSize = backPageSize == null ? 15 : (NumberUtils.compare(backPageSize, 200) == 1 ? 200 : backPageSize);
        ProjectExample example = new ProjectExample().createCriteria().andTenantIdEqualTo(loginUser.getTenantId())
            .andNameLike(StringUtils.isBlank(name) ? null : ("%" + name + "%"))
            .andCustomerIdEqualTo(customerId)
            .andIsEnableEqualTo(isEnable)
            .andProjectIdIn(projectIds)
            .example();
        example.setStartOffSet(0);
        example.setSize(backPageSize);
        List<com.juma.tgm.project.domain.v2.Project> projects = projectMapper.selectByExample(example);
        for (com.juma.tgm.project.domain.v2.Project project : projects) {
            ProjectVo vo = new ProjectVo();
            BeanUtils.copyProperties(project, vo);

            // 项目经理
            User user = authCommonService.loadUser(project.getProjectManagerUserId());
            vo.setProjectManagerUserName(user == null ? null : user.getName());
            vo.setProjectManagerUserPhone(user == null ? null : user.getMobileNumber());
            result.add(vo);
        }
        return result;
    }

    @Override
    public List<ProjectVo> listProjectByLoinUser(String name, Integer backPageSize, LoginUser loginUser) throws BusinessException {
        List<ProjectVo> result = new ArrayList<>();
        if (null == loginUser || null == loginUser.getUserId() || null == loginUser.getTenantId()) {
            return result;
        }
        backPageSize = backPageSize == null ? 15 : (NumberUtils.compare(backPageSize, 200) == 1 ? 200 : backPageSize);
        ProjectFilter filter = new ProjectFilter();
        filter.setName(name);
        filter.setUserId(loginUser.getUserId());
        filter.setTenantId(loginUser.getTenantId());
        filter.setProjectStatus(ProjectEnum.ProjectStatus.RUNING.getCode());
        filter.setIsEnable(true);
        filter.setBackPageSize(backPageSize);

        List<com.juma.tgm.project.domain.v2.Project> list = projectExtMapper.listProjectBy(filter);

        for (com.juma.tgm.project.domain.v2.Project project : list) {
            ProjectVo vo = new ProjectVo();
            BeanUtils.copyProperties(project, vo);

            // 客户名称
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
            vo.setCustomerName(customerInfo == null ? null : customerInfo.getCustomerName());

            // 项目经理
            User user = authCommonService.loadUser(project.getProjectManagerUserId());
            vo.setProjectManagerUserName(user == null ? null : user.getName());
            result.add(vo);
        }

        return result;
    }

    // 组装条件
    private ProjectExample buildProjectExample(QueryCond<ProjectFilter> queryCond, LoginEmployee loginUser) {
        ProjectFilter filters = queryCond.getFilters();
        if (null == filters) {
            List<String> areaCodeList = new ArrayList<>();
            areaCodeList.add(loginUser.getLoginDepartment().getBusinessAreas().get(0).getAreaCode());
            ProjectExample example = new ProjectExample().createCriteria()
                            .andIsEnableEqualTo(true)
                            .andAreaCodeLikeList(areaCodeList)
                            .andTenantIdEqualTo(loginUser.getTenantId())
                            .example();
            example.setSize(queryCond.getPageSize());
            example.setStartOffSet(queryCond.getStartOffset());
            if(null != queryCond.getOrderBy()){
                example.orderBy(queryCond.getOrderBy());
            }else {
                example.orderBy(com.juma.tgm.project.domain.v2.Project.Column.projectId.desc());
            }
            return example;
        }

        List<String> areaCodeList = queryCond.getFilters().getAreaCodeList();
        if(areaCodeList == null || areaCodeList.isEmpty()){
            areaCodeList = new ArrayList<>();
            areaCodeList.add(loginUser.getLoginDepartment().getBusinessAreas().get(0).getAreaCode());
			queryCond.getFilters().setAreaCodeList(areaCodeList);
        }
        ProjectExample example = new ProjectExample().createCriteria()
                .andIsEnableEqualTo(true)
                .andTenantIdEqualTo(loginUser.getTenantId())
                .andAreaCodeLikeList(filters.getAreaCodeList())
                .andProjectNoEqualTo(filters.getProjectNo())
                .andCustomerIdEqualTo(filters.getCustomerId())
                .andProjectIdEqualTo(filters.getProjectId())
                .andManagerIdEqualTo(filters.getManagerId())
                .andContractNoEqualTo(filters.getContractNo())
                .andProjectStatusIn(filters.getProjectStatusList())
                .andIsRunningEqualTo(filters.getIsRunning())
                .andProjectTypeEqualTo(filters.getProjectType())
                .andProjectManagerUserIdEqualTo(filters.getProjectManagerUserId())
                .example();
        example.setSize(queryCond.getPageSize());
        example.setStartOffSet(queryCond.getStartOffset());
        if(null != queryCond.getOrderBy()){
            example.orderBy(queryCond.getOrderBy());
        }else {
            example.orderBy(com.juma.tgm.project.domain.v2.Project.Column.projectId.desc());
        }
        return example;
    }

    private ProjectExample buildProjectExample(QueryCond<ProjectFilter> queryCond, LoginUser loginUser) {
        ProjectFilter filters = queryCond.getFilters();
        if (null == filters) {
            ProjectExample example = new ProjectExample().createCriteria()
                .andIsEnableEqualTo(true)
                .andTenantIdEqualTo(loginUser.getTenantId())
                .example();
            example.setStartOffSet(queryCond.getStartOffset());
            example.setSize(queryCond.getPageSize());
            if(null != queryCond.getOrderBy()){
                example.orderBy(queryCond.getOrderBy());
            }else {
                example.orderBy(com.juma.tgm.project.domain.v2.Project.Column.projectId.desc());
            }
            return example;
        }

        ProjectExample example = new ProjectExample().createCriteria()
            .andIsEnableEqualTo(true)
            .andTenantIdEqualTo(loginUser.getTenantId())
            .andAreaCodeLikeList(filters.getAreaCodeList())
            .andProjectNoEqualTo(filters.getProjectNo())
            .andCustomerIdEqualTo(filters.getCustomerId())
            .andProjectIdEqualTo(filters.getProjectId())
            .andManagerIdEqualTo(filters.getManagerId())
            .andContractNoEqualTo(filters.getContractNo())
            .andProjectStatusIn(filters.getProjectStatusList())
            .andIsRunningEqualTo(filters.getIsRunning())
            .andProjectTypeEqualTo(filters.getProjectType())
            .andProjectManagerUserIdEqualTo(filters.getProjectManagerUserId())
            .example();
        example.setSize(queryCond.getPageSize());
        example.setStartOffSet(queryCond.getStartOffset());
        if(null != queryCond.getOrderBy()){
            example.orderBy(queryCond.getOrderBy());
        }else {
            example.orderBy(com.juma.tgm.project.domain.v2.Project.Column.projectId.desc());
        }
        return example;
    }

    // 构造查询条件
    private void buildCondition(PageCondition pageCondition, LoginUser loginUser) {
        if (null != pageCondition.getFilters().get("customerManagerName")) {
            // 根据客户经理(企业客户的客户经理)姓名获取客户经理的userId
            try {
                EmployeeFilter employeeFilter = new EmployeeFilter();
                employeeFilter.setName(pageCondition.getFilters().get("customerManagerName").toString().trim());
                Page<EmployeeInfo> page = employeeService.searchEmployees(
                        new PageQueryCondition<EmployeeFilter>(employeeFilter), Constants.AUTH_KEY_TGM_MANAGE,
                        Constants.CUSTOMER_MANAGER_PERMISSION_KEY, commonService.buildTopLoginEmployee(loginUser));
                if (null != page && CollectionUtils.isNotEmpty(page.getResults())) {
                    List<Integer> customerManagerIdList = new ArrayList<Integer>();
                    for (EmployeeInfo employeeInfo : page.getResults()) {
                        customerManagerIdList.add(employeeInfo.getEmployeeId());
                    }
                    pageCondition.getFilters().put("customerManagerIdList", customerManagerIdList);
                } else {
                    // 查询不到是的策略
                    pageCondition.getFilters().put("customerManagerId", -1);
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Project> listProjectByLoginUser(String projectName, Integer customerId, Integer backPageSize,
            LoginUser loginUser) {
        List<Project> result = new ArrayList<Project>();
        List<String> listTopLevelAreaCode = businessAreaCommonService.listTopLevelAreaCode(loginUser);
        if (listTopLevelAreaCode.isEmpty()) {
            return result;
        }

        PageCondition cond = new PageCondition();
        cond.setPageNo(1);
        cond.setPageSize(backPageSize == null ? Integer.MAX_VALUE : backPageSize);
        cond.getFilters().put("isEnable", true);
        cond.getFilters().put("tenantId", loginUser.getTenantId());
        cond.getFilters().put("customerAreaCodeList", listTopLevelAreaCode);
        if (StringUtils.isNotBlank(projectName)) {
            cond.getFilters().put("name", projectName);
        }
        if (null != customerId) {
            cond.getFilters().put("customerId", customerId);
        }


        return projectDao.joinSearch(cond);
    }

    @Override
    public List<Project> listProjectByCustomerId(Project project, Integer backPageSize) {
        if (null == project.getCustomerId()) {
            return null;
        }
        PageCondition cond = new PageCondition();
        cond.setPageNo(1);
        cond.setPageSize(backPageSize == null ? Integer.MAX_VALUE : backPageSize);
        cond.getFilters().put("isEnable", true);
        if (StringUtils.isNotBlank(project.getName())) {
            cond.getFilters().put("name", project.getName());
        }
        cond.getFilters().put("customerId", project.getCustomerId());
        return projectDao.joinSearch(cond);
    }

    @Override
    public void updateFixedNo(int projectId, int fixedNo, LoginUser loginUser) {
        Project project = new Project();
        project.setProjectId(projectId);
        project.setFixedNo(fixedNo);
        projectDao.update(project);
    }

    @Override
    public Page<Project> searchForOtherSys(PageCondition pageCondition, LoginUser loginUser) {
        if (null == pageCondition.getFilters() || null == pageCondition.getFilters().get("crmCustomerId")) {
            return new Page<Project>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0,
                    new ArrayList<Project>());
        }

        return this.searchBackSys(pageCondition, loginUser);
    }

    @Override
    public WaybillDetailInfo doBuildWaybillInfo(Integer projectId) {
        WaybillDetailInfo waybillDetailInfo = new WaybillDetailInfo();
        Project project = this.getProject(projectId);
        if (null == project) {
            return waybillDetailInfo;
        }

        // 运单信息
        Waybill waybill = new Waybill();
        waybill.setProjectId(project.getProjectId());
        waybill.setProjectName(project.getName());
        waybill.setCustomerId(project.getCustomerId());
        waybill.setTruckCustomerId(project.getTruckCustomerId());
        waybill.setCustomerManagerId(project.getManagerId());
        waybill.setAreaCode(project.getAreaCode());
        waybill.setReceiptType(Waybill.ReceiptType.PROJECTPAY.getCode());
        waybill.setPlanDeliveryTime(new Date());
        waybill.setCmEstimateFinishTime(DateUtil.addMinutes(new Date(),
                project.getEstimateTimeConsumption() == null ? 0 : project.getEstimateTimeConsumption().intValue()));
        waybillDetailInfo.setWaybill(waybill);

        // 用车要求
        TruckRequire truckRequire = new TruckRequire();
        truckRequire.setGoodsType(project.getGoodsType());
        truckRequire.setRemark(project.getTruckRequireRemark());
        truckRequire.setAdditionalFunctionIds(project.getAdditionalFunctionIds());
        truckRequire.setTaxRateValue(project.getTaxRateValue());
        waybillDetailInfo.setTruckRequire(truckRequire);
        return waybillDetailInfo;
    }

    @Override
    public Integer create(ProjectVoAndInfo projectVo, LoginEmployee loginEmployee) throws BusinessException {
        Integer projectId = this.add(projectVo.getProject(), loginEmployee);
        this.buildProjectVoAndInfoProjectId(projectVo, projectId);
        for (RoadMapVo roadMapVo : projectVo.getRoadMapVos()) {
            this.roadMapService.insert(roadMapVo, loginEmployee);
        }
        return projectId;
    }

    @Override
    public Integer update(ProjectVoAndInfo projectVo, LoginEmployee loginEmployee) throws BusinessException {
        this.update(projectVo.getProject(), loginEmployee);
        // 容错
        buildProjectVoAndInfoProjectId(projectVo, projectVo.getProject().getProjectId());
        // 查询 原来存在的
        List<RoadMapVo> roadMapVos = roadMapService.listVoByProjectId(projectVo.getProject().getProjectId());
        Set<Integer> roadMapIds = new HashSet<>();
        if (CollectionUtils.isNotEmpty(roadMapVos)) {
            for (RoadMapVo roadMapVo : roadMapVos) {
                // 将当前数据库已知的所有该项目相关的id全部存在一个set里
                roadMapIds.add(roadMapVo.getRoadMap().getRoadMapId());
            }
        }
        for (RoadMapVo roadMapVo : projectVo.getRoadMapVos()) {
            /// 如果没有就新增 一条
            if (roadMapVo.getRoadMap().getRoadMapId() == null) {
                this.roadMapService.insert(roadMapVo, loginEmployee);
            }
            // 如果存在 则 更新
            else {
                // 存在 id 那么久从已知里面将本次更新的移除掉
                roadMapIds.remove(roadMapVo.getRoadMap().getRoadMapId());
                this.roadMapService.update(roadMapVo, loginEmployee);
            }

        }
        // 移除更新的内容后剩下 的就是要删除的
        if (CollectionUtils.isNotEmpty(roadMapIds)) {
            for (Integer roadMapId : roadMapIds) {
                this.roadMapService.delete(roadMapId, loginEmployee);
            }
        }
        return 1;// 更新数量
    }

    @Override
    public ProjectVoAndInfo info(Integer projectId, LoginEmployee loginEmployee) throws BusinessException {
        Project project = this.getProject(projectId);
        if (project != null) {
            Integer roadNum = this.roadMapService.countRoadNum(project.getProjectId());
            Integer timeOrderCountNum = task4WaybillService.countTaskByProject(project.getProjectId());
            project.setRoadCount(roadNum);
            project.setTimeOrderCount(timeOrderCountNum);
            CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
            if (customerInfo != null) {
                project.setCustomerName(customerInfo.getCustomerName());
            }

            Map<String, String> mapLogisticsProduct = crmCommonService.mapLogisticsProduct(loginEmployee);
            String logisticsName = mapLogisticsProduct.get(project.getLogisticsLabel());
            project.setLogisticsName(StringUtils.isBlank(logisticsName) ? "" : logisticsName);
            ProjectVoAndInfo projectVoAndInfo = new ProjectVoAndInfo();
            projectVoAndInfo.setProject(project);
            // List<RoadMapVo> roadMapVos = this.roadMapService.listVoByProjectId(projectId);
            // projectVoAndInfo.setRoadMapVos(roadMapVos);
            return projectVoAndInfo;
        } else {
            throw new BusinessException("project", "未知的项目id:" + projectId);
        }
    }

    private void buildProjectVoAndInfoProjectId(ProjectVoAndInfo projectVoAndInfo, Integer projectId) {
        for (RoadMapVo roadMapVo : projectVoAndInfo.getRoadMapVos()) {
            roadMapVo.getRoadMap().setProjectId(projectId);
        }
    }

    @Override
    public Page<RoadMapVo> findRoadMapVoPage(PageCondition pageCondition, LoginUser loginUser)
            throws BusinessException {

        Page<RoadMapVo> roadMapVoPage = new Page<>();

        if (null == pageCondition.getFilters() || null == pageCondition.getFilters().get("projectId"))
            throw new BusinessException("project", "项目Id不能为空");

        Page<RoadMap> roadMapPage = roadMapService.search(pageCondition, loginUser);
        roadMapVoPage.setPageNo(roadMapPage.getPageNo());
        roadMapVoPage.setPageSize(roadMapPage.getPageSize());
        roadMapVoPage.setTotal(roadMapPage.getTotal());
        roadMapPage.setPageNumCount(roadMapPage.getPageNumCount());

        if (roadMapPage.getResults().isEmpty()) {
            roadMapVoPage.setResults(new ArrayList<RoadMapVo>());
            return roadMapVoPage;
        }

        List<RoadMap> roadMaps = (ArrayList<RoadMap>) roadMapPage.getResults();
        List<RoadMapVo> roadMapVos = roadMapService.listVoByRoadMapList(roadMaps);

        roadMapVoPage.setResults(roadMapVos);
        return roadMapVoPage;
    }

    @Override
    public boolean checkIsReceivableFirst(Integer projectId) {
        if (null == projectId) {
            return false;
        }

        com.juma.tgm.project.domain.v2.Project project = this.getProjectV2(projectId);
        if (null == project || null == project.getIsReceivableFirst()) {
            return false;
        }

        return project.getIsReceivableFirst();
    }

    @Override
    public Project findProject(Integer customerId, Integer projectId) {
        Project example = new Project();
        example.setCustomerId(customerId);
        example.setProjectId(projectId);
        List<Project> rows = projectDao.findByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

    @Override
    public void updateIsReceivableFirst(Integer projectId, Boolean isReceivableFirst, LoginUser loginUser) {
        if (null == projectId) {
            return;
        }

        com.juma.tgm.project.domain.v2.Project project = new com.juma.tgm.project.domain.v2.Project();
        project.setProjectId(projectId);
        project.setIsReceivableFirst(isReceivableFirst == null ? true : isReceivableFirst);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public Integer coreInfoAdd(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser) throws BusinessException {
        checkCoreParam(project, loginUser);

        if (!this.checkProjectName(project.getName(), null, loginUser)) {
            throw new BusinessException("projectNameNull", "errors.common.prompt", "当前项目名称已存在");
        }

        CustomerInfo customerInfo = customerInfoService.findCusInfoById(project.getCustomerId());
        if (customerInfo == null) {
            throw new BusinessException("customerNotExist", "errors.common.prompt", "客户不存在");
        }

        // 项目编号
        project.setProjectNo(idGeneratorService.genProjectNo(IdGeneratorTable.IdType.XM));
        // 项目状态
        project.setProjectStatus(ProjectEnum.ProjectStatus.NO_START.getCode());
        project.setIsRunning(false);
        project.setAreaCode(customerInfo.getAreaCode());
        project.setTenantId(loginUser.getTenantId());
        project.setTenantCode(loginUser.getTenantCode());
        project.setCreateTime(new Date());
        project.setCreateUserId(loginUser.getUserId());
        project.setIsEnable(true);
        project.setIsReceivableFirst(true);
        project.setManagerId(customerInfo.getCustomerManagerUserId());
        //试运行时签约方赋值为运作方
        if(project.getProjectType() == ProjectEnum.ProjectType.TEST_RUN.getCode()){
            project.setContractToCompany(project.getPayToCompany());
            project.setContractToCompanyCreditCode(project.getPayToCompanyCreditCode());
        }

        // 保存核心信息
        projectMapper.insertSelective(project);

        //项目经理存为项目成员
        insertProjectMember(project, loginUser);

        return project.getProjectId();
    }

    private void checkCoreParam(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser) {
        if (loginUser == null) {
            throw new BusinessException("loginEmployeeNull", "errors.paramCanNotNullWithName", "登录信息");
        }
        if (project == null) {
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "核心信息");
        }
        if (project.getProjectType() == null) {
            throw new BusinessException("projectTypeNull", "errors.paramCanNotNullWithName", "项目运作类型");
        }
        if (project.getCustomerId() == null) {
            throw new BusinessException("customerNull", "errors.paramCanNotNullWithName", "客户名称");
        }
        if (StringUtils.isBlank(project.getName())) {
            throw new BusinessException("projectNameNull", "errors.paramCanNotNullWithName", "项目名称");
        }
        if (project.getName().length() < PROJECT_NAME_MIN || project.getName().length() > PROJECT_NAME_MAX) {
            throw new BusinessException("projectNameNotMatch", "项目名称字数必须大于4个或小于20个");
        }
        if (project.getProjectManagerUserId() == null) {
            throw new BusinessException("projectManagerUserIdNull", "errors.paramCanNotNullWithName", "项目经理");
        }
        if (project.getAreaCode() == null) {
            throw new BusinessException("areaCodeNull", "errors.paramCanNotNullWithName", "业务区域");
        }
        if (project.getPayToCompany() == null) {
            throw new BusinessException("payToCompanyNull", "errors.paramCanNotNullWithName", "运作方");
        }
        if (StringUtils.isBlank(project.getPayToCompanyCreditCode())) {
            throw new BusinessException("payToCompanyCreditCodeNull", "errors.common.prompt",
                "未获取到社会统一信用代码，请联系总部财务核实该问题");
        }
        if (StringUtils.isBlank(project.getTryWorkPassAttachment())) {
            throw new BusinessException("tryWorkPassAttachmentNull", "errors.paramCanNotNullWithName", "已审批项目评估表");
        }
        //试运行
        if (project.getProjectType() == ProjectEnum.ProjectType.TEST_RUN.getCode()) {
            long days = (project.getProjectEndDate().getTime() - project.getProjectStartDate().getTime()) / (1000 * 60 * 60 * 24);
            if(days > PROJECT_DATE_MAX){
                throw new BusinessException("projectDateNotMatch", "试运行截止时间天数应小于等于60天");
            }
        }
        //正式运行
        if (project.getProjectType() == ProjectEnum.ProjectType.REAL_RUN.getCode()) {
            if (StringUtils.isBlank(project.getContractNo())) {
                throw new BusinessException("contractNoNull", "errors.paramCanNotNullWithName", "合同编号");
            }
            if (project.getContractToCompany() == null) {
                throw new BusinessException("contractToCompanyNull", "errors.paramCanNotNullWithName", "签约方");
            }
            if (StringUtils.isBlank(project.getContractToCompanyCreditCode())) {
                throw new BusinessException("contractToCompanyCreditCodeNull", "errors.common.prompt",
                    "未获取到社会统一信用代码，请联系总部财务核实该问题");
            }
        }
    }

    @Override
    public void coreInfoUpdate(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser) throws BusinessException {
        checkCoreParam(project, loginUser);

        if (!this.checkProjectName(project.getName(), project.getProjectId(), loginUser)) {
            throw new BusinessException("projectNameNull", "errors.uniqueness", "项目名称");
        }

        com.juma.tgm.project.domain.v2.Project fromDb = projectMapper.selectByPrimaryKey(project.getProjectId());
        if (null == fromDb) {
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目信息");
        }
        // 更新核心信息(客户名称不能修改)
        project.setCustomerId(null);
        projectMapper.updateByPrimaryKeySelective(project);

        // 项目名字变更通知运单变更
        if (fromDb.getName() != null && project.getName() != null && !(fromDb.getName().equals(project.getName()))) {
            // 更新运单表冗余字段
            Waybill example = new Waybill();
            example.setProjectId(project.getProjectId());

            Waybill newValue = new Waybill();
            newValue.setProjectName(project.getName());
            waybillService.updateWaybillBatch(example, newValue, loginUser);
        }

        //更新projectMember
        projectMemberMapper.deleteByExample(new ProjectMemberExample().createCriteria()
                .andProjectIdEqualTo(project.getProjectId())
                .andIsProjectManagerEqualTo(true).example());

        insertProjectMember(project, loginUser);

    }

    private void insertProjectMember(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser) {
        ProjectMember member = new ProjectMember();
        member.setProjectId(project.getProjectId());
        member.setTenantId(loginUser.getTenantId());
        member.setUserId(project.getProjectManagerUserId());
        member.setIsStayWarehosue(false);
        member.setIsProjectManager(true);
        member.setCreateUserId(loginUser.getUserId());
        member.setCreateTime(new Date());
        projectMemberMapper.insert(member);
    }

    @Override
    public void basicInfoUpdate(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser)
            throws BusinessException {
        if (project == null) {
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目信息");
        }
        if (project.getProjectId() == null) {
            throw new BusinessException("projectIdNull", "errors.paramCanNotNullWithName", "项目id");
        }
        com.juma.tgm.project.domain.v2.Project fromDb = getProjectV2(project.getProjectId());
        if (fromDb == null) {
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目");
        }

        if (StringUtils.isBlank(project.getGoodsType())) {
            throw new BusinessException("goodsTypeNull", "errors.paramCanNotNullWithName", "货物类型");
        }
        if (project.getEstimateTimeConsumption() == null) {
            throw new BusinessException("estimateTimeConsumptionNull", "errors.paramCanNotNullWithName", "预估配送时间");
        }
        if (project.getTaxRateValue() == null) {
            throw new BusinessException("taxRateValueNull", "errors.paramCanNotNullWithName", "税率");
        }
        if (StringUtils.isBlank(project.getDeliveryAddressType())) {
            throw new BusinessException("deliveryAddressTypeNull", "errors.paramCanNotNullWithName", "配送地类型");
        }
        if(crmCommonService.isShowLogisticsProduct(loginUser) && StringUtils.isBlank(project.getLogisticsLabel())){
            throw new BusinessException("logisticsLabelNull", "errors.paramCanNotNullWithName", "物流产品");
        }
        if (StringUtils.isBlank(project.getAdditionalFunctionIds())) {
            throw new BusinessException("additionalFunctionIdsNull", "errors.paramCanNotNullWithName", "用车要求");
        }
        if (StringUtils.isBlank(project.getBusinessLinkman())) {
            throw new BusinessException("businessLinkmanNull", "errors.paramCanNotNullWithName", "业务对接人");
        }
        if (StringUtils.isBlank(project.getBusinessLinktel())) {
            throw new BusinessException("businessLinktelNull", "errors.paramCanNotNullWithName", "业务对接人电话");
        }

        // 更新基本信息
        String additionalFunctionIds = project.getAdditionalFunctionIds();
        // 隔天配送
        if (additionalFunctionIds.contains(NEXT_DAY_DELIVERY)) {
            project.setOnlyLoadCargo(NumberUtils.BYTE_ONE);
        }
        // 上传配送单
        if (additionalFunctionIds.contains(DELIVERY_RECEIPT)) {
            project.setNeedDeliveryPointNote(NumberUtils.BYTE_ONE);
        }
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public void updateProfitRate(com.juma.tgm.project.domain.v2.Project project) throws BusinessException {
        if (null == project || null == project.getProjectId()) {
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目信息");
        }
        com.juma.tgm.project.domain.v2.Project fromDb = getProjectV2(project.getProjectId());
        if (fromDb == null) {
            throw new BusinessException("projectNull", "errors.paramCanNotNullWithName", "项目");
        }
        //设置毛利率
        fromDb.setProfitRate(project.getProfitRate());
        projectMapper.updateByPrimaryKeySelective(fromDb);
    }

    @Override
    public void projectToRealRun(Integer projectId, String contractNo, Integer payToCompany,
            String payToCompanyCreditCode, Integer contractToCompany, String contractToCompanyCreditCode,
            Date projectStartDate, Date projectEndDate, LoginUser loginUser) throws BusinessException {
        this.checkParameter(projectId, contractNo, payToCompany, payToCompanyCreditCode, contractToCompany,
                    contractToCompanyCreditCode, projectStartDate, projectEndDate);

        com.juma.tgm.project.domain.v2.Project project = this.getProjectV2(projectId);
        if (null == project) {
            throw new BusinessException("projectNotExist", "project.error.projectNotExist");
        }

        // 项目类型为试运行且项目状态为运行中的才能转正式运行
        if (project.getProjectType() != ProjectEnum.ProjectType.TEST_RUN.getCode()
            || project.getProjectStatus() != ProjectEnum.ProjectStatus.RUNING.getCode()) {
            throw new BusinessException("theProjectTypeAndSatatusCannotToRealRun",
                "project.error.theProjectTypeAndSatatusCannotToRealRun");
        }

        // 比较项目的运营主体与合同的运营主体是不是一致，不一致则不能转正式运行
        if (NumberUtils.compare(project.getPayToCompany(), payToCompany) != 0) {
            throw new BusinessException("payToCompanyAtypism", "project.error.payToCompanyAtypism");
        }

        project.setContractNo(contractNo);
        project.setPayToCompany(payToCompany);
        project.setPayToCompanyCreditCode(payToCompanyCreditCode);
        project.setContractToCompany(contractToCompany);
        project.setContractToCompanyCreditCode(contractToCompanyCreditCode);
        project.setProjectStartDate(projectStartDate);
        project.setProjectEndDate(projectEndDate);
        project.setProjectType(ProjectEnum.ProjectType.REAL_RUN.getCode());
        project.setProjectStatus(ProjectEnum.ProjectStatus.RUNING.getCode());
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public void projectChangeContract(Integer projectId, String contractNo, Integer payToCompany,
        String payToCompanyCreditCode, Integer contractToCompany, String contractToCompanyCreditCode,
        Date projectStartDate, Date projectEndDate, LoginEmployee loginEmployee) throws BusinessException {
        this.checkParameter(projectId, contractNo, payToCompany, payToCompanyCreditCode, contractToCompany,
            contractToCompanyCreditCode, projectStartDate, projectEndDate);

        // 合同开始时间比较，合同的开始时间必须小于等于明天
        if (DateUtil.compare(projectStartDate, DateUtil.addDays(new Date(), 1), DateUtil.YYYYMMDD).equals(1)) {
            throw new BusinessException("projectStartDateTooLate", "errors.common.prompt",
                "合同的开始时间只有在" + DateUtil.format(DateUtil.addDays(new Date(), 1), DateUtil.YYYYMMDD) + "日及之前的才能变更");
        }

        com.juma.tgm.project.domain.v2.Project project = this.getProjectV2(projectId);
        if (null == project) {
            throw new BusinessException("projectNotExist", "project.error.projectNotExist");
        }

        if (!loginEmployee.getUserId().equals(project.getProjectManagerUserId()) && !authCommonService
            .isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, loginEmployee)) {
            throw new BusinessException("onlyProjectManagerOrCityManagerCanChangeContract", "errors.common.prompt",
                "只有项目经理或城市经理可以变更合同");
        }

        // 只有项目类型为正式运行且项目状态运行中的项目才能变更合同
        if (project.getProjectType() != ProjectEnum.ProjectType.REAL_RUN.getCode()
            || project.getProjectStatus() != ProjectEnum.ProjectStatus.RUNING.getCode()) {
            throw new BusinessException("theProjectTypeAndStatusCannotToChangeContract",
                "project.error.theProjectStatusCannotToChangeContract");
        }

        // 比较项目的运营主体与合同的运营主体是不是一致，不一致则不能转正式运行
        if (NumberUtils.compare(project.getPayToCompany(), payToCompany) != 0) {
            throw new BusinessException("payToCompanyAtypism", "project.error.payToCompanyAtypism");
        }

        // 比较项目的签约主体与合同的签约主体是不是一致，不能变更或续签
        if (NumberUtils.compare(project.getContractToCompany(), contractToCompany) != 0) {
            throw new BusinessException("contractToCompanyAtypism", "project.error.contractToCompanyAtypism");
        }

        project.setContractNo(contractNo);
        project.setPayToCompany(payToCompany);
        project.setPayToCompanyCreditCode(payToCompanyCreditCode);
        project.setContractToCompany(contractToCompany);
        project.setContractToCompanyCreditCode(contractToCompanyCreditCode);
        project.setProjectEndDate(projectEndDate);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public void projectRenewalContract(Integer projectId, String contractNo, Integer payToCompany,
        String payToCompanyCreditCode, Integer contractToCompany, String contractToCompanyCreditCode,
        Date projectStartDate, Date projectEndDate, LoginEmployee loginEmployee) throws BusinessException {
        this.checkParameter(projectId, contractNo, payToCompany, payToCompanyCreditCode, contractToCompany,
            contractToCompanyCreditCode, projectStartDate, projectEndDate);

        // 合同开始时间比较，合同的开始时间必须小于等于今天
        if (DateUtil.compare(projectStartDate, new Date(), DateUtil.YYYYMMDD).equals(1)) {
            throw new BusinessException("projectStartDateTooLate", "errors.common.prompt",
                "合同的开始时间只有在" + DateUtil.format(new Date(), DateUtil.YYYYMMDD) + "日及之前的才能变更");
        }

        com.juma.tgm.project.domain.v2.Project project = this.getProjectV2(projectId);
        if (null == project) {
            throw new BusinessException("projectNotExist", "project.error.projectNotExist");
        }

        if (!loginEmployee.getUserId().equals(project.getProjectManagerUserId()) && !authCommonService
            .isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, loginEmployee)) {
            throw new BusinessException("onlyProjectManagerOrCityManagerCanRenewalContract", "errors.common.prompt",
                "只有项目经理或城市经理可以续签合同");
        }

        if (contractNo.equals(project.getContractNo())) {
            throw new BusinessException("cannotSelectEqualContract", "errors.common.prompt", "续签不能选择原合同");
        }

        // 只有项目类型为正式运行且项目状态为已结束的项目才能续签合同
        if (project.getProjectType() != ProjectEnum.ProjectType.REAL_RUN.getCode()
            || project.getProjectStatus() != ProjectEnum.ProjectStatus.END.getCode()) {
            throw new BusinessException("theProjectTypeAndStatusCannotToRenewalContract",
                "project.error.theProjectTypeAndStatusCannotToRenewalContract");
        }

        // 比较项目的运营主体与合同的运营主体是不是一致，不一致则不能转正式运行
        if (NumberUtils.compare(project.getPayToCompany(), payToCompany) != 0) {
            throw new BusinessException("payToCompanyAtypism", "project.error.payToCompanyAtypism");
        }

        // 比较项目的签约主体与合同的签约主体是不是一致，不能变更或续签
        if (NumberUtils.compare(project.getContractToCompany(), contractToCompany) != 0) {
            throw new BusinessException("contractToCompanyAtypism", "project.error.contractToCompanyAtypism");
        }

        project.setContractNo(contractNo);
        project.setPayToCompany(payToCompany);
        project.setPayToCompanyCreditCode(payToCompanyCreditCode);
        project.setContractToCompany(contractToCompany);
        project.setContractToCompanyCreditCode(contractToCompanyCreditCode);
        project.setProjectEndDate(projectEndDate);
        project.setProjectStatus(ProjectEnum.ProjectStatus.RUNING.getCode());
        projectMapper.updateByPrimaryKeySelective(project);
        // 生成日报
        projectDailyService.createProjectDaily(projectId);
    }

    @Override
    public void projectSupplement(Integer projectId, String contractNo, Integer payToCompany,
        String payToCompanyCreditCode, Integer contractToCompany, String contractToCompanyCreditCode,
        Date projectStartDate, Date projectEndDate, String tryWorkPassAttachment,
        LoginUser loginUser) throws BusinessException {
        this.checkParameter(projectId, contractNo, payToCompany, payToCompanyCreditCode, contractToCompany,
            contractToCompanyCreditCode, projectStartDate, projectEndDate);

        com.juma.tgm.project.domain.v2.Project project = this.getProjectV2(projectId);
        if (null == project) {
            throw new BusinessException("projectNotExist", "project.error.projectNotExist");
        }

        if (StringUtils.isBlank(tryWorkPassAttachment)) {
            throw new BusinessException("tryWorkPassAttachmentNull", "errors.paramCanNotNullWithName", "已审批项目评估表");
        }

        project.setContractNo(contractNo);
        project.setPayToCompany(payToCompany);
        project.setPayToCompanyCreditCode(payToCompanyCreditCode);
        project.setContractToCompany(contractToCompany);
        project.setContractToCompanyCreditCode(contractToCompanyCreditCode);
        project.setProjectStartDate(projectStartDate);
        project.setProjectEndDate(projectEndDate);
        project.setTryWorkPassAttachment(tryWorkPassAttachment);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public void updateProjectToRunning(Integer projectId, Date runningTime) throws BusinessException {
        if (null == projectId || null == runningTime) {
            return;
        }

        com.juma.tgm.project.domain.v2.Project project = this.getProjectV2(projectId);
        if (null == project || project.getIsRunning() ) {
            return;
        }

        project.setIsRunning(true);
        project.setRunningTime(runningTime);
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public boolean isProjectManagerOrOperator(Integer projectId, Integer userId) throws BusinessException {
        if( null == userId ){ return false; }
        if( null == projectId ){ return false; }
        ProjectMemberExample example = new ProjectMemberExample().createCriteria()
                .andProjectIdEqualTo(projectId)
                .andUserIdEqualTo(userId)
                .example();
        List<ProjectMember> members = projectMemberMapper.selectByExample(example);
        return !CollectionUtils.isEmpty(members);
    }

    // 参数合法性校验
    private void checkParameter(Integer projectId, String contractNo, Integer payToCompany,
        String payToCompanyCreditCode, Integer contractToCompany, String contractToCompanyCreditCode
        , Date projectStartDate, Date projectEndDate) {
        if (null == projectId) {
            throw new BusinessException("projectIdRequired", "project.error.projectIdRequired");
        }

        if (StringUtils.isBlank(contractNo)) {
            throw new BusinessException("contractNoRequired", "project.error.contractNoRequired");
        }

        if (contractNo.length() > 45) {
            throw new BusinessException("contractNoTooLong", "project.error.contractNoTooLong");
        }

        if (null == payToCompany) {
            throw new BusinessException("payToCompanyRequired", "未获取到运作方，请联系CRM相关人员或检查合同的运作方是否完善");
        }

        if (StringUtils.isBlank(payToCompanyCreditCode)) {
            throw new BusinessException("contractToCompanyCreditCodeNull", "errors.common.prompt",
                "未获取到社会统一信用代码，请联系总部财务核实该问题");
        }

        if (null == contractToCompany) {
            throw new BusinessException("contractToCompanyRequired", "未获取到签约方，请联系CRM相关人员或检查合同的签约方是否完善");
        }

        if (StringUtils.isBlank(contractToCompanyCreditCode)) {
            throw new BusinessException("contractToCompanyCreditCodeNull", "errors.common.prompt",
                "未获取到社会统一信用代码，请联系总部财务核实该问题");
        }

        if (null == projectStartDate) {
            throw new BusinessException("projectStartDateRequired", "project.error.projectStartDateRequired");
        }

        if (null == projectEndDate) {
            throw new BusinessException("projectEndDateRequired", "project.error.projectEndDateRequired");
        }
    }

    @Override
    public com.juma.tgm.project.domain.v2.Project findByName(String name, LoginUser loginUser) {
        if(StringUtils.isBlank(name)) return null;
        ProjectExample example = new ProjectExample().createCriteria().andNameEqualTo(name)
                .andTenantIdEqualTo(loginUser.getTenantId()).example();
        List<com.juma.tgm.project.domain.v2.Project> rows = projectMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

    @Override
    public List<com.juma.tgm.project.domain.v2.Project> findAllProjectInRunning() {
        ProjectExample projectExample = new ProjectExample().createCriteria()
                .andProjectStatusEqualTo(ProjectEnum.ProjectStatus.RUNING.getCode()).andIsEnableEqualTo(Boolean.TRUE)
                .example();
        return projectMapper.selectByExample(projectExample);
    }

    @Override
    public com.juma.tgm.project.domain.v2.Project findTop1ByProjectIdAndProjectStatus(Integer projectId,
                                                                                      ProjectEnum.ProjectStatus status) {
        ProjectExample projectExample = new ProjectExample().createCriteria().andProjectIdEqualTo(projectId)
                .andProjectStatusEqualTo(status.getCode()).andIsEnableEqualTo(Boolean.TRUE)
                .example();
        List<com.juma.tgm.project.domain.v2.Project> projects = projectMapper.selectByExample(projectExample);
        return  projects == null || 0 == projects.size() ? null : projects.get(0);
    }
}
