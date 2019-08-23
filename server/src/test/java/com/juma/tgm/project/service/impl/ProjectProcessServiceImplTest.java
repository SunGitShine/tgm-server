package com.juma.tgm.project.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.juma.auth.employee.domain.Department;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.project.domain.v2.ProjectWorkflow;
import com.juma.tgm.project.domain.v2.ProjectWorkflowTask;
import com.juma.tgm.project.service.ProjectProcessService;
import com.juma.tgm.project.vo.v2.ProjectWorkflowVo;
import com.juma.workflow.core.domain.TaskDetail;
import testng.BaseTestNGTest;

public class ProjectProcessServiceImplTest extends BaseTestNGTest {

	@Autowired
	private ProjectProcessService projectProcessService;

	@Test
	public void updateProjectWorkflow(){

		ProjectWorkflow projectWorkflow = new ProjectWorkflow();
		projectWorkflow.setProjectWorkflowId(1);
		projectWorkflow.setExcuteTime(new Date());
		projectWorkflow.setReason("理由");
		projectWorkflow.setAttachment("http://juma.com/pic/12.jpg");

//		projectProcessService.updateProjectWorkflow(projectWorkflow);
	}

	@Test
	public void findProjectWorkflowDetail(){
		ProjectWorkflowVo projectWorkflowVo = projectProcessService.findProjectWorkflowDetail(1,makeLoginData());
		System.out.println("==========="+ JSON.toJSONString(projectWorkflowVo));
	}

	@Test
	public void submitApplyStartProject(){

		ProjectWorkflow projectWorkflow = new ProjectWorkflow();
		projectWorkflow.setExcuteTime(new Date());
		projectWorkflow.setProjectId(8328);

		LoginEmployee loginEmployee = makeLoginData();

		projectProcessService.submitApplyStartProject(projectWorkflow, loginEmployee);
	}

	@Test
	public void findTaskDetail(){
		TaskDetail taskDetail = projectProcessService.findTaskByProcessInstanceId("941375", makeLoginData());
		System.out.println(JSON.toJSONString(taskDetail));
	}

	@Test
	public void cancelWorkFlowTask(){
		projectProcessService.cancelWorkFlowTask(43, makeLoginData());
	}

	@Test
	public void finishWorkFlowTask(){
		ProjectWorkflowTask task = new ProjectWorkflowTask();
		task.setApprovalKey("HQ_GM_OK");
		task.setProjectWorkflowId(12);
		task.setTaskId("940711");
		task.setComment("通过");
//		projectProcessService.finishWorkFlowTask(task, makeLoginData());
	}

	@Test
	public void submitApplyPauseProject(){
		ProjectWorkflow projectWorkflow = new ProjectWorkflow();
		projectWorkflow.setExcuteTime(DateUtil.buildDate(0,0,0,0));
		projectWorkflow.setReason("哈哈");
		projectWorkflow.setProjectId(3);

		LoginEmployee loginEmployee = makeLoginData();

		projectProcessService.submitApplyPauseProject(projectWorkflow, loginEmployee);
	}

	@Test
	public void submitApplyEndProject(){
		ProjectWorkflow projectWorkflow = new ProjectWorkflow();
		projectWorkflow.setExcuteTime(DateUtil.buildDate(1,0,0,0));
		projectWorkflow.setReason("哈哈");
		projectWorkflow.setProjectId(1);

		LoginEmployee loginEmployee = makeLoginData();

		projectProcessService.submitApplyEndProject(projectWorkflow, loginEmployee);
	}

	@Test
	public void updateProjectStatusTimer(){
		projectProcessService.updateProjectStatusTimer();
	}

	private LoginEmployee makeLoginData(){
		LoginEmployee loginEmployee = new LoginEmployee();
		loginEmployee.setTenantId(19);
		loginEmployee.setTenantCode("000000010");
		loginEmployee.setUserId(5809);
		loginEmployee.setEmployeeId(1);//2976
		loginEmployee.setCompanyCode("00000400");
		Department department = new Department();
		department.setDepartmentCode("0000040000");
		loginEmployee.initLoginDepartment(department, null);
		return loginEmployee;
	}
}
