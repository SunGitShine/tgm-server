package com.juma.tgm.project.service.impl;

import com.juma.auth.employee.domain.DepartmentCompany;
import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum.ProjectType;
import com.juma.tgm.project.service.ProjectMemberService;
import com.juma.tgm.project.vo.ContractVo;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.CrmCommonService;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.external.service.ProjectExternalService;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.project.vo.ProjectFilter;
import com.juma.tgm.project.vo.v2.ProjectParamVo;
import com.juma.tgm.project.vo.v2.ProjectVo;
import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunctionBo;
import com.juma.tgm.truck.service.AdditionalFunctionService;
import testng.BaseTestNGTest;

/**
 * @ClassName ProjectServiceImplTest.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年3月23日 上午11:52:10
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ProjectServiceImplTest extends BaseTestNGTest{

    @Resource
    private ProjectService projectService;
    @Resource
    private AdditionalFunctionService additionalFunctionService;
    @Resource
    private ProjectExternalService projectExternalService;
    @Resource
    private RoadMapService roadMapService;
    @Resource
    private AuthCommonService authCommonService;
    @Resource
    private CrmCommonService crmCommonService;
    @Resource
    private ProjectMemberService projectMemberService;
    
    @Test
    public void listProjectByLoginUser() {
        /*List<Project> listProjectByLoginUser = projectService.listProjectByLoginUser(null, 50, new LoginUser(2, 5809));
        
        System.out.println(JSON.toJSONString(listProjectByLoginUser));
        System.out.println(JSON.toJSONString(listProjectByLoginUser));*/
        
        PageCondition pageCondition = new  PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.getFilters().put("projectId", 264);
        Page<RoadMap> p = roadMapService.search(pageCondition, new LoginUser(2, 5809));
        System.out.println(p);
    }
    
    @Test
    public void getFunctionList() {
        List<AdditionalFunctionBo> functionList = additionalFunctionService.getFunctionList();
        for (int i = functionList.size() - 1; i >= 0; i--) {
            AdditionalFunctionBo functionBo = functionList.get(i);
            if (AdditionalFunction.FunctionKeys.DRIVER_CARRY.toString().equals(functionBo.getFunctionKey())
                    || AdditionalFunction.FunctionKeys.LABORER_CARRY.toString().equals(functionBo.getFunctionKey())) {
                functionList.remove(functionBo);
            }
        }
        System.out.println(JSON.toJSONString(functionList));
    }
    
    @Test
    public void getProject() {
        Project project = projectExternalService.getProject(1);
        System.out.println(JSON.toJSONString(project));
    }

    @Test
    public void updateIsReceivableFirst() {
        projectService.updateIsReceivableFirst(258, true, new LoginUser(2,1));
    }

    @Test
    public void infoTest(){
        LoginEmployee employee = new LoginEmployee();
        employee.setTenantId(2);
        projectService.info(726, employee);
    }

    @Test
    public void searchBackSys() {
//        QueryCond<ProjectFilter> queryCond = new QueryCond<>();
//        queryCond.setPageNo(1);
//        queryCond.setPageSize(15);
//        Page<ProjectVo> page = projectService.searchBackSysV2(queryCond, new LoginUser(2, 1));
//        System.out.println(JSON.toJSON(page));
    }

//    @Test
//    public void listProjectByLoinUser() {
//        List<com.juma.tgm.project.domain.v2.Project> list = projectService.listProjectByLoinUser(null, null,
//                new LoginUser(2, 1));
//        System.out.println(JSON.toJSON(list));
//    }

    @Test
    public void searchV2(){

        LoginEmployee employee = new LoginEmployee();
        employee.setTenantId(19);
        employee.setUserId(11600);

        QueryCond<ProjectParamVo> queryCond = new QueryCond<>();
        List<Integer> projectStatusList = new ArrayList<>();
        projectStatusList.add(1);
        projectStatusList.add(2);
        projectStatusList.add(3);

        queryCond.setFilters(new ProjectParamVo());
        queryCond.getFilters().setProjectStatusList(projectStatusList);
        queryCond.getFilters().setIsEnable(true);
        queryCond.getFilters().setTenantId(19);
        queryCond.setPageNo(1);
        queryCond.setPageSize(15);
        System.out.println(JSON.toJSONString(projectService.searchV2(queryCond, employee)));
    }

    @Test
    public void coreInfoAdd() {
        com.juma.tgm.project.domain.v2.Project project = projectService.getProjectV2(8359);
        project.setProjectId(null);
        project.setName("我的测试00002");
        project.setPayToCompanyCreditCode(null);
        project.setContractToCompanyCreditCode(null);
        buildCreditCode(project, new LoginUser(project.getProjectManagerUserId(), project.getTenantId()));
        projectService.coreInfoAdd(project, new LoginUser(project.getProjectManagerUserId(), project.getTenantId()));
    }

    @Test
    public void projectToRealRun() {
        com.juma.tgm.project.domain.v2.Project project = projectService.getProjectV2(8359);
        buildCreditCode(project, new LoginUser(project.getProjectManagerUserId(), project.getTenantId()));

        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setTenantId(project.getTenantId());
        loginEmployee.setUserId(project.getProjectManagerUserId());

        project.setProjectType(1);
        project.setProjectStatus(1);
        projectService.projectToRealRun(project.getProjectId(), project.getContractNo(), project.getPayToCompany(),
            project.getPayToCompanyCreditCode(), project.getContractToCompany(),
            project.getContractToCompanyCreditCode(),project.getProjectStartDate(), project.getProjectEndDate(), loginEmployee);
        project.setProjectType(2);
        project.setProjectStatus(2);
        projectService.projectChangeContract(project.getProjectId(), project.getContractNo(), project.getPayToCompany(),
            project.getPayToCompanyCreditCode(), project.getContractToCompany(),
            project.getContractToCompanyCreditCode(),project.getProjectStartDate(), project.getProjectEndDate(), loginEmployee);
        project.setProjectType(2);
        project.setProjectStatus(4);
        projectService.projectRenewalContract(project.getProjectId(), project.getContractNo(), project.getPayToCompany(),
            project.getPayToCompanyCreditCode(), project.getContractToCompany(),
            project.getContractToCompanyCreditCode(),project.getProjectStartDate(), project.getProjectEndDate(), loginEmployee);
    }

    // 补全社会统一信用代码
    private com.juma.tgm.project.domain.v2.Project buildCreditCode(com.juma.tgm.project.domain.v2.Project project,
        LoginUser loginUser) {
        if (null == project.getContractNo()) {
            // 试运行
            if (null != project.getProjectType() && project.getProjectType().equals(ProjectType.TEST_RUN.getCode())) {
                if (null != project.getPayToCompany() && StringUtils.isBlank(project.getPayToCompanyCreditCode())) {
                    DepartmentCompany company = authCommonService.findDepartmentCompanyByDepartmentId(project.getPayToCompany());
                    project.setPayToCompanyCreditCode(company == null ? "" : company.getUniformSocialCreditCode());
                    project.setContractToCompanyCreditCode(project.getPayToCompanyCreditCode());
                }
            }
            return project;
        }

        if (StringUtils.isNotBlank(project.getContractToCompanyCreditCode()) && StringUtils
            .isNotBlank(project.getPayToCompanyCreditCode())) {
            return project;
        }

        ContractVo contractVo = crmCommonService.loadByContractNo(project.getContractNo(), loginUser);
        project.setContractToCompanyCreditCode(contractVo == null ? "" : contractVo.getContractToCompanyCreditCode());
        project.setPayToCompanyCreditCode(contractVo == null ? "" : contractVo.getPayToCompanyCreditCode());
        return project;
    }

    @Test
    private void addProjectDepot() {
        List<ProjectDepot> projectDepots = new ArrayList<>();
        ProjectDepot p1 = new ProjectDepot();
        p1.setProjectId(1);
        p1.setDepotId(1);
        p1.setDepotName("测试仓库");
        p1.setDepotAddress("324");
        p1.setDepotCoordinates("423");
        ProjectDepot p2 = new ProjectDepot();
        p2.setProjectId(1);
        p2.setDepotName("n1");
        p2.setDepotAddress("324");
        p2.setDepotCoordinates("423");
        projectDepots.add(p1);
        projectDepots.add(p2);
        projectService.addProjectDepots(projectDepots, new LoginUser(19, 1));
    }

    @Test
    public void existUser() {
        boolean b = projectMemberService.existUser(8385, 29961);
    }
}
