package com.juma.tgm.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.project.service.ProjectDepotService;
import com.juma.tgm.task.service.ProjectStatusChangeService;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.tgm.tools.service.WorkflowCommonService;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.auth.user.domain.User;
import com.juma.auth.user.service.UserService;
import com.juma.tgm.common.Constants;
import com.juma.tgm.common.DateUtil;
import com.juma.tgm.common.workflow.ApprovalStatus;
import com.juma.tgm.daily.service.ProjectDailyService;
import com.juma.tgm.mq.domain.WorkFlowMessage;
import com.juma.tgm.project.dao.ProjectMapper;
import com.juma.tgm.project.dao.ProjectWorkflowExtraMapper;
import com.juma.tgm.project.dao.ProjectWorkflowMapper;
import com.juma.tgm.project.dao.RoadMapMapper;
import com.juma.tgm.project.domain.RoadMapExample;
import com.juma.tgm.project.domain.v2.Project;
import com.juma.tgm.project.domain.v2.ProjectExample;
import com.juma.tgm.project.domain.v2.ProjectWorkflow;
import com.juma.tgm.project.domain.v2.ProjectWorkflowExample;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.service.ProjectProcessService;
import com.juma.tgm.project.service.ProjectService;
import com.juma.tgm.project.vo.v2.ProjectVo;
import com.juma.tgm.project.vo.v2.ProjectWorkflowVo;
import com.juma.tgm.tool.domain.ExecuteWorkflowInfo;
import com.juma.tgm.tools.service.AuthCommonService;
import com.juma.tgm.tools.service.WorkflowCommonService;
import com.juma.workflow.core.domain.ProcessInstance;
import com.juma.workflow.core.domain.TaskDetail;
import com.juma.workflow.core.service.ProcessService;
import com.juma.workflow.core.service.TaskService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class ProjectProcessServiceImpl implements ProjectProcessService {

	private static final Logger log = LoggerFactory.getLogger(ProjectProcessServiceImpl.class);

	private static final String START_PROJECT_KEY = "project_start";
	private static final String PAUSE_PROJECT_KEY = "project_pause";
	private static final String END_PROJECT_KEY = "project_end";
	private static final String RECOVER_PROJECT_KEY = "project_recover";

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private ProjectWorkflowMapper projectWorkflowMapper;

	@Autowired
	private ProjectWorkflowExtraMapper projectWorkflowExtraMapper;

	@Autowired
	private ProcessService processService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RoadMapMapper roadMapMapper;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectDepotService projectDepotService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthCommonService authCommonService;

	@Autowired
	private WorkflowCommonService workflowCommonService;

	@Autowired
	private ProjectStatusChangeService projectStatusChangeService;
    @Resource
	private ProjectDailyService projectDailyService;

	@Override
	public void submitApplyStartProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) {

		if(projectWorkflow == null || projectWorkflow.getProjectId() == null
			|| projectWorkflow.getExcuteTime() == null ){
			throw new BusinessException("params", "errors.paramCanNotNull");
		}

		int i = projectDepotService.countWithProjectId(projectWorkflow.getProjectId());
		if (i == 0) {
			throw new BusinessException("depotError","项目仓库为空");
		}

		projectWorkflow.setProjectWorkflowType(ProjectEnum.WorkflowType.START.getCode());
		Project project = checkProject(projectWorkflow, false, loginEmployee);
		checkStartDate(project, projectWorkflow);
		createCommonApply(START_PROJECT_KEY, projectWorkflow, project.getProjectNo(), loginEmployee);
	}

	@Override
	public void submitApplyPauseProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) {

		if(projectWorkflow == null || projectWorkflow.getProjectId() == null
			|| projectWorkflow.getExcuteTime() == null || StringUtils.isBlank(projectWorkflow.getReason())){
			throw new BusinessException("params", "errors.paramCanNotNull");
		}
		projectWorkflow.setProjectWorkflowType(ProjectEnum.WorkflowType.PAUSE.getCode());
		Project project = checkProject(projectWorkflow, true, loginEmployee);
		checkPauseDate(project, projectWorkflow);
		createCommonApply(PAUSE_PROJECT_KEY, projectWorkflow, project.getProjectNo(), loginEmployee);
	}

	@Override
	public void submitApplyEndProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) {

		if(projectWorkflow == null || projectWorkflow.getProjectId() == null
			|| projectWorkflow.getExcuteTime() == null || StringUtils.isBlank(projectWorkflow.getReason())){
			throw new BusinessException("params", "errors.paramCanNotNull");
		}
		projectWorkflow.setProjectWorkflowType(ProjectEnum.WorkflowType.END.getCode());
		Project project = checkProject(projectWorkflow, true, loginEmployee);
		checkEndDate(project, projectWorkflow);
		createCommonApply(END_PROJECT_KEY, projectWorkflow, project.getProjectNo(), loginEmployee);
	}

	@Override
	public void submitApplyRecoverProject(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) {

		if(projectWorkflow == null || projectWorkflow.getProjectId() == null
			|| StringUtils.isBlank(projectWorkflow.getReason())){
			throw new BusinessException("params", "errors.paramCanNotNull");
		}
		projectWorkflow.setProjectWorkflowType(ProjectEnum.WorkflowType.RECOVER.getCode());
		Project project = projectMapper.selectByPrimaryKey(projectWorkflow.getProjectId());
		checkRecoverDate(project, projectWorkflow, loginEmployee);
		createCommonApply(RECOVER_PROJECT_KEY, projectWorkflow, project.getProjectNo(), loginEmployee);
	}

	@Override
	public void cancelWorkFlowTask(Integer projectWorkflowId, LoginEmployee loginEmployee) {

		ProjectWorkflow projectWorkflow = projectWorkflowMapper.selectByPrimaryKey(projectWorkflowId);
		if(projectWorkflow == null || StringUtils.isBlank(projectWorkflow.getProcessInstanceId())){
			throw new BusinessException("projectWorkflowNotExist", "不存在审批流程");
		}
		if(projectWorkflow.getSubmitter().compareTo(loginEmployee.getUserId()) != 0
			&& !authCommonService.isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, loginEmployee)){
			throw new BusinessException("notApplyUser", "errors.common.prompt", "只有项目经理或城市经理可撤销");
		}
		if(projectWorkflow.getAuditStatus().compareTo(ProjectEnum.AuditStatus.REJECT.getCode()) == 0
			|| projectWorkflow.getAuditStatus().compareTo(ProjectEnum.AuditStatus.AGREE.getCode()) == 0){
			throw new BusinessException("projectWorkflowStart", "审批流程已被审批，不能撤销");
		}
		try {
			//审核中撤回工作流
			processService.revokeProcessInstance(projectWorkflow.getProcessInstanceId(), "", loginEmployee);
		} catch (Exception e) {
			log.error("cancelProcess is error,processInstanceId is {},error is {}: " , projectWorkflow.getProcessInstanceId(), e.getMessage());
			if(e instanceof BusinessException) {
				throw e;
			}
			e.printStackTrace();
			throw new BusinessException("unknown.error", "外部错误:"+e.getMessage());
		}
		projectWorkflowMapper.deleteByPrimaryKey(projectWorkflowId);
	}

	@Override
	public void finishWorkFlowTask(WorkFlowMessage message) throws BusinessException {
		if(null == message || StringUtils.isBlank(message.getNumber())){
			return;
		}

		ProjectWorkflow projectWorkflow = null;
		if(StringUtils.isNotBlank(message.getBusinessKey()) && NumberUtils.isNumber(message.getBusinessKey())){
			projectWorkflow = projectWorkflowMapper.selectByPrimaryKey(Integer.valueOf(message.getBusinessKey()));
			if(projectWorkflow == null){
				throw new BusinessException("projectWorkflowNotExist", "审批流程不存在");
			}
		}else {
			//对工作流未返回businessKey容错处理
			Project project = projectService.getByProjectNoV2(message.getNumber());
            List<ProjectWorkflow> projectWorkflows = null;
			if(null != project){
			    projectWorkflows = projectWorkflowMapper.selectByExample(new ProjectWorkflowExample().createCriteria()
                        .andProjectIdEqualTo(project.getProjectId()).andAuditStatusEqualTo(ProjectEnum.AuditStatus.SUBMIT.getCode()).example());
            }
			if(CollectionUtils.isEmpty(projectWorkflows)){
				throw new BusinessException("projectWorkflowNotExist", "审批流程不存在");
			}
			projectWorkflow = projectWorkflows.get(0);
		}

		if(projectWorkflow.getAuditStatus().compareTo(ProjectEnum.AuditStatus.AGREE.getCode()) == 0){
			throw new BusinessException("projectWorkflowAgree", "该审批流程已审核通过");
		}

		try {
			this.handleWorkFlowMsg(projectWorkflow, message);
		} catch (Exception e) {
			log.error("completeProcess is error, processInstanceId is {},error is {}: " ,projectWorkflow.getProcessInstanceId(),e.getMessage());
			if(e instanceof BusinessException) {
				throw e;
			}
			e.printStackTrace();
			throw new BusinessException("unknown.error", "外部错误:"+e.getMessage());
		}
	}

	/**
	 * 处理工作流消息
	 * @param projectWorkflow
	 * @param message
	 */
	private void handleWorkFlowMsg(ProjectWorkflow projectWorkflow, WorkFlowMessage message) {
		if(null == message.getStatus()){ return;}
		String workFlowKey = message.getStatus().getWorkFlowKey();
		projectWorkflow.setAuditStatus(ProjectEnum.AuditStatus.getCodeByWorkFlowKey(workFlowKey));
		projectWorkflowMapper.updateByPrimaryKeySelective(projectWorkflow);

		if (ApprovalStatus.APPROVAL_AGREE.getWorkFlowKey().equals(workFlowKey)) {

			Project project = projectMapper.selectByPrimaryKey(projectWorkflow.getProjectId());
			Project updateProject = new Project();
			updateProject.setProjectId(projectWorkflow.getProjectId());
			/**
			 *	如果当前时间晚于项目截止日期，直接更新为已结束
			 */
			if(DateUtil.compare(new Date(), project.getProjectEndDate(), DateUtil.YYYYMMDD) == 1){
				updateProject.setProjectStatus(ProjectEnum.ProjectStatus.END.getCode());
			}else{
				//如果为申请恢复，直接更新为运行中
				if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.RECOVER.getCode()) == 0){
					updateProject.setProjectStatus(ProjectEnum.ProjectStatus.RUNING.getCode());
				}else if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.START.getCode()) == 0){
					/**
					 *当前时间大于等于执行时间更新为相应状态
					 *当前时间小于执行时间时定时任务更新状态
					 */
					if(DateUtil.compare(projectWorkflow.getExcuteTime(), new Date(), DateUtil.YYYYMMDD) == 0
						|| DateUtil.compare(projectWorkflow.getExcuteTime(), new Date(), DateUtil.YYYYMMDD) == -1){
						updateProject.setProjectStatus(ProjectEnum.ProjectStatus.RUNING.getCode());
						updateProject.setProjectStartDate(projectWorkflow.getExcuteTime());
					}
				}else{
					//申请结束项目的日期可以下单，如果执行时间小于等于昨天的开启
					if(DateUtil.compare(projectWorkflow.getExcuteTime(), DateUtil.buildDate(-1,0,0,0), DateUtil.YYYYMMDD) == 0
						|| DateUtil.compare(projectWorkflow.getExcuteTime(), DateUtil.buildDate(-1,0,0,0), DateUtil.YYYYMMDD) == -1){
						if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.END.getCode()) == 0){
							updateProject.setProjectStatus(ProjectEnum.ProjectStatus.END.getCode());
							//更新任务相关信息
							projectStatusChangeService.finish(projectWorkflow.getProjectId());
						}
						if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.PAUSE.getCode()) == 0){
							updateProject.setProjectStatus(ProjectEnum.ProjectStatus.PAUSE.getCode());
							//更新任务相关信息
							projectStatusChangeService.pause(projectWorkflow.getProjectId());
						}
					}
				}
			}
			//如果有需要更新时
			if(updateProject.getProjectStatus() != null){
				projectMapper.updateByPrimaryKeySelective(updateProject);
				//生成日报
				if(updateProject.getProjectStatus() == ProjectEnum.ProjectStatus.RUNING.getCode()){
					projectDailyService.createProjectDaily(updateProject.getProjectId());
				}
			}
		}

		if (ApprovalStatus.APPROVAL_GIVE_UP.getWorkFlowKey().equals(workFlowKey)) {
			projectWorkflowMapper.deleteByPrimaryKey(projectWorkflow.getProjectWorkflowId());
		}

	}

	@Override
	public ProjectWorkflow findByProjectId(Integer projectId) throws BusinessException {
		if (null == projectId) {
			return null;
		}

		ProjectWorkflowExample example =
				new ProjectWorkflowExample().createCriteria().andProjectIdEqualTo(projectId).example();
		example.setSize(1);
		example.setStartOffSet(0);
		example.setOrderByClause(" project_workflow_id desc ");

		List<ProjectWorkflow> list = projectWorkflowMapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void reapplyProjectWorkflow(ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee) throws BusinessException {

		ProjectWorkflow projectWorkflowDB = projectWorkflowMapper.selectByPrimaryKey(projectWorkflow.getProjectWorkflowId());
		if(projectWorkflowDB.getSubmitter().compareTo(loginEmployee.getUserId()) != 0){
			User user = userService.loadUser(projectWorkflowDB.getSubmitter());
			String applyUser = user == null ? "" : "，申请人：" + user.getName();
			throw new BusinessException("notApplyUser", "errors.common.prompt", "非申请人，不能修改申请" + applyUser);
		}

		Project project = projectMapper.selectByPrimaryKey(projectWorkflow.getProjectId());
		if(project == null){
			throw new BusinessException("projectNotExist", "errors.notExistWithName", projectWorkflow.getProjectId());
		}
		if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.START.getCode()) == 0){
			checkStartDate(project, projectWorkflow);
		}else if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.PAUSE.getCode()) == 0){
			checkPauseDate(project, projectWorkflow);
		}else if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.RECOVER.getCode()) == 0){
			checkRecoverDate(project, projectWorkflow, loginEmployee);
		}else if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.END.getCode()) == 0){
			checkEndDate(project, projectWorkflow);
		}
		projectWorkflowMapper.updateByPrimaryKeySelective(projectWorkflow);
	}

	@Override
	public ProjectWorkflowVo findProjectWorkflowDetail(Integer projectWorkflowId, LoginUser loginUser) throws BusinessException {

		ProjectWorkflowVo projectWorkflowVo = new ProjectWorkflowVo();

		ProjectWorkflow projectWorkflow = projectWorkflowMapper.selectByPrimaryKey(projectWorkflowId);
		if(projectWorkflow == null){
			throw new BusinessException("projectWorkflowNotExist", "审批流程不存在");
		}
		Integer projectId = projectWorkflow.getProjectId();
		ProjectVo project = projectService.getProjectVo(projectId, loginUser);
		BeanUtils.copyProperties(projectWorkflow, projectWorkflowVo);
		projectWorkflowVo.setProject(project);

		return projectWorkflowVo;
	}

	@Override
	public TaskDetail findTaskByProcessInstanceId(String processInstanceId, LoginEmployee loginEmployee) throws BusinessException {
		return taskService.findTask(processInstanceId, loginEmployee);
	}

	@Override
	public void updateProjectStatusTimer() {

		log.info("更新项目状态定时任务开始");
		List<Project> updateProjects = new ArrayList<>();

		List<ProjectWorkflow> projectWorkflows = projectWorkflowExtraMapper.findNeedExecuteData();
		log.info("需要执行状态变更的审核：{}", JSON.toJSONString(projectWorkflows));
		for(ProjectWorkflow projectWorkflow : projectWorkflows){
			Project updateProject = new Project();
			updateProject.setProjectId(projectWorkflow.getProjectId());
			if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.START.getCode()) == 0){
				updateProject.setProjectStatus(ProjectEnum.ProjectStatus.RUNING.getCode());
			}else if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.PAUSE.getCode()) == 0){
				updateProject.setProjectStatus(ProjectEnum.ProjectStatus.PAUSE.getCode());
				//更新任务相关信息
				projectStatusChangeService.pause(projectWorkflow.getProjectId());
			}else if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.END.getCode()) == 0){
				updateProject.setProjectStatus(ProjectEnum.ProjectStatus.END.getCode());
				//更新任务相关信息
				projectStatusChangeService.finish(projectWorkflow.getProjectId());
			}
			updateProjects.add(updateProject);
		}

		ProjectExample projectExample = new ProjectExample();
		ProjectExample.Criteria projectExampleCriteria = projectExample.createCriteria();
		projectExampleCriteria.andProjectEndDateEqualTo(DateUtil.buildDate(-1,0,0,0));
		projectExampleCriteria.andProjectStatusNotEqualTo(ProjectEnum.ProjectStatus.END.getCode());
		List<Project> projects = projectMapper.selectByExample(projectExample);
		for(Project project : projects){
			Project updateProject = new Project();
			updateProject.setProjectId(project.getProjectId());
			updateProject.setProjectStatus(ProjectEnum.ProjectStatus.END.getCode());
			updateProjects.add(updateProject);
			//更新任务相关信息
			projectStatusChangeService.finish(project.getProjectId());
			//生成日报
			if(project.getProjectStatus() == ProjectEnum.ProjectStatus.RUNING.getCode()){
				projectDailyService.createProjectDaily(project.getProjectId());
			}
		}
		log.info("需要执行到期结束的项目：{}", JSON.toJSONString(projects));
		log.info("最终执行修改的所有项目及状态：{}", JSON.toJSONString(updateProjects));

		projectMapper.updateBatchByPrimaryKeySelective(updateProjects);
		log.info("更新项目状态定时任务结束");
	}

	/**
	 * 创建工作流
	 * @param processKey
	 * @param projectId
	 * @param projectNo
	 * @param loginEmployee
	 * @return
	 */
	private ProcessInstance createProcess(String processKey, Integer projectId, String projectNo, LoginEmployee loginEmployee){

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("number", projectNo);
		ProcessInstance ins = null;
		try {
			ins = processService.startProcessInstance(processKey, projectId.toString(), variables, loginEmployee);
		} catch (Exception e) {
			log.error("startProcess is error,projectId is {},error is {}: " , projectId, e.getMessage());
			if(e instanceof BusinessException) {
				throw e;
			}
			e.printStackTrace();
			throw new BusinessException("unknown.error", "外部错误:"+e.getMessage());
		}
		return ins;
	}

	private void createCommonApply(String processKey, ProjectWorkflow projectWorkflow, String projectNo, LoginEmployee loginEmployee){

		projectWorkflow.setSubmitter(loginEmployee.getUserId());
		projectWorkflow.setAuditStatus(ProjectEnum.AuditStatus.SUBMIT.getCode());
		projectWorkflow.setSubmitTime(new Date());
		projectWorkflowExtraMapper.insert(projectWorkflow);

		ProcessInstance	processInstance = createProcess(processKey, projectWorkflow.getProjectWorkflowId(), projectNo, loginEmployee);

		ProjectWorkflow updateProjectWorkflow = new ProjectWorkflow();
		updateProjectWorkflow.setProjectWorkflowId(projectWorkflow.getProjectWorkflowId());
		updateProjectWorkflow.setProcessInstanceId(processInstance.getProcessInstanceId());
		projectWorkflowMapper.updateByPrimaryKeySelective(updateProjectWorkflow);

	}

	/**
	 * 基础校验
	 * @param projectWorkflow
	 * @param loginEmployee
	 * @return
	 */
	private Project checkProject(ProjectWorkflow projectWorkflow, boolean isIncludeToday, LoginEmployee loginEmployee){

		Project project = projectMapper.selectByPrimaryKey(projectWorkflow.getProjectId());
		if(project == null){
			throw new BusinessException("projectNotExist", "errors.notExistWithName", projectWorkflow.getProjectId());
		}
		if(loginEmployee.getUserId().compareTo(project.getProjectManagerUserId()) != 0
			&& !authCommonService.isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, loginEmployee)){
			throw new BusinessException("noPermission", "只有项目经理或城市经理才有权限操作");
		}
		if(projectWorkflow.getProjectWorkflowType().compareTo(ProjectEnum.WorkflowType.START.getCode()) != 0
			&& DateUtil.compare(projectWorkflow.getExcuteTime(), new Date(), DateUtil.YYYYMMDD) == -1){
			throw new BusinessException("dateError", "申请执行日期需大于等于当前日期");
		}
		Date date = new Date();
		//申请结束和暂停的包含执行时间（执行时间可下单）
		if(isIncludeToday){
			date = DateUtil.buildDate(-1, 0,0,0);
		}
		//查询该项目未完成审批流程的数量（审核中、被驳回）、审核已通过但是还没执行的审核
		long unCompleteCount = projectWorkflowExtraMapper.unCompleteCount(projectWorkflow.getProjectId(), date, projectWorkflow.getProjectWorkflowType());
		if(unCompleteCount > 0){
			throw new BusinessException("dateError", "该项目存在未完成的审批流程或未执行的审核任务，不能提交申请");
		}
		if(null == project.getProfitRate()){
			throw new BusinessException("profitRateIsNull", "请联系总部运营管理部，设置承诺毛利率后再启动");
		}
		return project;
	}

	/**
	 * 项目信息是否完善
	 * @param project
	 * @return
	 */
	private boolean isCompleteProjectInfo(Project project){

		RoadMapExample roadMapExample = new RoadMapExample();
		RoadMapExample.Criteria criteria = roadMapExample.createCriteria();
		criteria.andIsDeleteEqualTo((byte) 0);
		criteria.andProjectIdEqualTo(project.getProjectId());

		Integer roadMapCount = roadMapMapper.countByExample(roadMapExample);
		if(roadMapCount == 0){
			return false;
		}

		if(project.getGoodsType() == null || StringUtils.isBlank(project.getAdditionalFunctionIds())
			|| StringUtils.isBlank(project.getBusinessLinkman())){
			return false;
		}

		return true;
	}

	/**
	 * 申请启动校验
	 * @param project
	 * @param projectWorkflow
	 */
	private void checkStartDate(Project project, ProjectWorkflow projectWorkflow){
		//只有未启动的项目才能申请启动
		if(project.getProjectStatus().compareTo(ProjectEnum.ProjectStatus.NO_START.getCode()) != 0){
			throw new BusinessException("projectStatusError", "只有未启动的项目才能申请启动");
		}
		if(DateUtil.compare(new Date(), project.getProjectEndDate(), DateUtil.YYYYMMDD) == 1){
			throw new BusinessException("projectExpire", "项目已过期，不能启动项目");
		}
		if(!isCompleteProjectInfo(project)){
			throw new BusinessException("projectInfoNotComplete", "项目信息不完善，不能启动项目");
		}
		if(project.getProjectType().compareTo(ProjectEnum.ProjectType.REAL_RUN.getCode()) == 0
			&& DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectStartDate(), DateUtil.YYYYMMDD) != 0){
			throw new BusinessException("dateError", "正式运行项目不能修改项目开始时间");
		}
		if(DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectEndDate(), DateUtil.YYYYMMDD) == 1
			&& project.getProjectType().compareTo(ProjectEnum.ProjectType.TEST_RUN.getCode()) == 0){
			throw new BusinessException("dateError", "试运行开始时间应小于等于试运行截止时间");
		}
		if(DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectEndDate(), DateUtil.YYYYMMDD) == 1
			&& project.getProjectType().compareTo(ProjectEnum.ProjectType.REAL_RUN.getCode()) == 0){
			throw new BusinessException("dateError", "项目开始日期应小于等于项目截止日期");
		}
	}

	/**
	 * 申请暂停校验
	 * @param project
	 * @param projectWorkflow
	 */
	private void checkPauseDate(Project project, ProjectWorkflow projectWorkflow){
		//只有运行中的项目才能申请暂停
		if(project.getProjectStatus().compareTo(ProjectEnum.ProjectStatus.RUNING.getCode()) != 0){
			throw new BusinessException("projectStatusError", "只有运行中的项目才能申请暂停");
		}
		if(DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectEndDate(), DateUtil.YYYYMMDD) == 1
			|| DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectStartDate(), DateUtil.YYYYMMDD) == -1
			|| DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectEndDate(), DateUtil.YYYYMMDD) == 0){
			throw new BusinessException("dateError", "申请执行日期需大于等于项目开始日期、小于项目截止日期");
		}
	}

	/**
	 * 申请结束校验
	 * @param project
	 * @param projectWorkflow
	 */
	private void checkEndDate(Project project, ProjectWorkflow projectWorkflow){
		if(DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectEndDate(), DateUtil.YYYYMMDD) == 1
			|| DateUtil.compare(projectWorkflow.getExcuteTime(), project.getProjectStartDate(), DateUtil.YYYYMMDD) == -1){
			throw new BusinessException("dateError", "申请执行日期需介于项目开始日期和截止日期之间");
		}
		//只有运行中和暂停的项目才能申请结束
		if(project.getProjectStatus().compareTo(ProjectEnum.ProjectStatus.RUNING.getCode()) != 0
			&& project.getProjectStatus().compareTo(ProjectEnum.ProjectStatus.PAUSE.getCode()) != 0){
			throw new BusinessException("projectStatusError", "只有运行中和暂停的项目才能申请结束");
		}
	}

	/**
	 * 申请恢复校验
	 * @param project
	 * @param projectWorkflow
	 * @param loginEmployee
	 */
	private void checkRecoverDate(Project project, ProjectWorkflow projectWorkflow, LoginEmployee loginEmployee){
		if(project == null){
			throw new BusinessException("projectNotExist", "errors.notExistWithName", projectWorkflow.getProjectId());
		}
		if(loginEmployee.getUserId().compareTo(project.getProjectManagerUserId()) != 0
			&& !authCommonService.isPermission(Constants.CITY_MANAGER_PERMISSION_KEY, loginEmployee)){
			throw new BusinessException("noPermission", "只有项目经理或城市经理才有权限操作");
		}
		//只有暂停的项目才能申请结束
		if(project.getProjectStatus().compareTo(ProjectEnum.ProjectStatus.PAUSE.getCode()) != 0){
			throw new BusinessException("projectStatusError", "只有暂停的项目才能申请恢复");
		}
	}

	@Override
	public void reapplyToWorkflow(ExecuteWorkflowInfo executeWorkflowInfo, LoginEmployee loginEmployee) throws BusinessException {
        log.info("send eventbus to workflow for project reapply start:{}", JSON.toJSONString(executeWorkflowInfo));
        workflowCommonService.reopenWorkflow(executeWorkflowInfo,loginEmployee);
        log.info("send eventbus to workflow for project reapply end");
    }

	@Override
	public void giveUpToWorkflow(ExecuteWorkflowInfo executeWorkflowInfo, LoginEmployee loginEmployee) throws BusinessException {
        log.info("send eventbus to workflow for project give up start:{}", JSON.toJSONString(executeWorkflowInfo));
        if(StringUtils.isNotBlank(executeWorkflowInfo.getProjectWorkflowId())){
			ProjectWorkflow projectWorkflow = projectWorkflowMapper.selectByPrimaryKey(Integer.valueOf(executeWorkflowInfo.getProjectWorkflowId()));
			if(null != projectWorkflow){
				projectWorkflow.setAuditStatus(ProjectEnum.AuditStatus.PROCESS.getCode());
				projectWorkflowMapper.updateByPrimaryKeySelective(projectWorkflow);
			}
		}
        workflowCommonService.giveUpWorkflow(executeWorkflowInfo,loginEmployee);
        log.info("send eventbus to workflow for project give up end");
    }

}
