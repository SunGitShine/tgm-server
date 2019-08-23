package com.juma.tgm.project.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.project.domain.Project;
import com.juma.tgm.project.domain.v2.ProjectDepot;
import com.juma.tgm.project.domain.v2.enums.ProjectEnum;
import com.juma.tgm.project.vo.ProjectFilter;
import com.juma.tgm.project.vo.ProjectVoAndInfo;
import com.juma.tgm.project.vo.RoadMapVo;
import com.juma.tgm.project.vo.v2.ProjectParamVo;
import com.juma.tgm.project.vo.v2.ProjectVo;
import com.juma.tgm.project.vo.v2.ProjectVoApp;
import com.juma.tgm.waybill.domain.WaybillDetailInfo;
import java.util.Date;
import java.util.List;

public interface ProjectService {


    /**
     * 添加项目仓库
     * @param projectDepots
     */
    void addProjectDepots(List<ProjectDepot> projectDepots,LoginUser loginUser) throws BusinessException;

    /**
     * 删除项目仓库
     * @param projectDepotId
     * @throws BusinessException
     */
    void deleteProjectDepot(Integer projectDepotId,LoginUser loginUser) throws BusinessException;

    /**
     * 项目仓库列表
     * @param projectId
     * @return
     */
    List<ProjectDepot> listProjectDepot(Integer projectId);

    com.juma.tgm.project.domain.v2.Project findByName(String name,LoginUser loginUser);
    
    Page<com.juma.tgm.project.domain.v2.Project> searchProject(QueryCond<ProjectFilter> filter,LoginUser loginUser);
    
    /**
     * @throws
     * @Title: findProjectByCustomerId
     * @Description: 客户 下面的项目列表
     * @param: @param customerId
     * @param: @return
     * @return: List<Project>
     */
    List<Project> findProject(int customerId);

    /**
     * 
     * @Title: findProject   
     * @Description: 
     * @param: @param customerId
     * @param: @param projectId
     * @param: @return      
     * @return: Project      
     * @throws
     */
    Project findProject(Integer customerId,Integer projectId);

    /**
     * @throws
     * @Title: getProject
     * @Description: 项目详情  展开内容
     * @param: @param projectId
     * @param: @return
     * @return: Project
     */
    @Deprecated
    Project getProject(int projectId);

    /**
     * 根据项目ID获取
     *
     * @param projectId
     * @return
     */
    com.juma.tgm.project.domain.v2.Project getProjectV2(Integer projectId);

    /**
     * 根据项目编号获取
     *
     * @param projectNo
     * @return
     */
    com.juma.tgm.project.domain.v2.Project getByProjectNoV2(String projectNo);

    /**
     * 获取项目的业务对接人电话
     *
     * @return
     */
    List<com.juma.tgm.project.domain.v2.Project> listProjectDailySms(String startTime, String endTime);

    /**
     * 根据项目ID获取
     *
     * @param projectId
     * @return
     */
    ProjectVo getProjectVo(Integer projectId, LoginUser loginUser);

    /**
     * 查询经济人项目列表
     * 可以直接通过crmCustomerId 查询相应的项目列表
     *
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<Project> search(LoginUser loginUser, PageCondition pageCondition) throws BusinessException;

    /**
     * 查询经济人项目列表2版
     * 可以直接通过crmCustomerId 查询相应的项目列表
     *
     * @param projectParamVoQueryCond
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<ProjectVoApp> searchV2(QueryCond<ProjectParamVo> projectParamVoQueryCond,LoginUser loginUser) throws BusinessException;

    /**
     * 添加项目
     *
     * @param project
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    Integer add(Project project, LoginUser loginUser) throws BusinessException;

    /**
     * 项目名称同租户是否已经存在
     *
     * @param name
     *         项目名称
     * @param projectId
     *         项目ID
     * @param loginUser
     *         当前登录人信息
     * @return
     * @throws BusinessException
     */
    boolean checkProjectName(String name, Integer projectId, LoginUser loginUser) throws BusinessException;

    /**
     * 修改项目
     *
     * @param project
     * @param loginUser
     * @throws BusinessException
     */
    @Deprecated
    void update(Project project, LoginUser loginUser) throws BusinessException;

    /**
     * 修改项目 批量更新
     *
     * @param projects
     * @param loginUser
     * @throws BusinessException
     */
    @Deprecated
    void update(List<Project> projects, LoginUser loginUser) throws BusinessException;

    /**
     * 删除项目
     *
     * @param projectId
     * @param loginUser
     * @throws BusinessException
     */
    void del(Integer projectId, LoginUser loginUser) throws BusinessException;

    /**
     * 根据项目名称查询
     *
     * @param projectName
     * @return
     * @author Libin.Wei
     * @Date 2017年10月13日 下午2:19:17
     */
    @Deprecated
    Project findProjectByName(String projectName, LoginUser loginUser);

    /**
     * 后台查询项目列表
     *
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    @Deprecated
    Page<Project> searchBackSys(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 后台查询项目列表
     *
     * @param queryCond
     * @param loginEmployee
     * @return
     * @throws BusinessException
     */
    Page<ProjectVo> searchBackSysV2(QueryCond<ProjectFilter> queryCond, LoginEmployee loginEmployee);

    /**
     * 根据名字全模糊获取
     *
     * @param name
     * @param backPageSize
     *         默认15条，最大200条
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    List<com.juma.tgm.project.domain.v2.Project> listProjectBy(String name, Integer customerId, Integer backPageSize, Boolean isEnable,
                                                               LoginUser loginUser) throws BusinessException;

    /**
     * 根据名字全模糊获取当前登录人有权限的项目
     *
     * @param projectFilter
     * @param backPageSize
     *         默认15条，最大200条
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    List<com.juma.tgm.project.domain.v2.Project> listProjectBy(ProjectFilter projectFilter, Integer backPageSize, LoginUser loginUser) throws BusinessException;

    /**
     * 根据名字全模糊获取当前登录人有权限的项目
     *
     * @param name
     * @param backPageSize
     *         默认15条，最大200条
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    List<ProjectVo> listProjectByLoginUserV2(String name, Integer customerId, Integer backPageSize, Boolean isEnable,
                                                               LoginUser loginUser) throws BusinessException;

    /**
     * 根据名字全模糊获取当前登录人有权限的项目
     *
     * @param name
     * @param backPageSize
     *         默认15条，最大200条
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    List<ProjectVo> listProjectByLoinUser(String name, Integer backPageSize, LoginUser loginUser) throws BusinessException;

    /**
     * 获取当前用户权限下所有的项目信息
     *
     * @param projectName  项目名称
     * @param backPageSize 返回条数, 为空返回最大值
     * @param loginUser
     * @return
     * @author Libin.Wei
     * @Date 2018年4月2日 上午11:17:58
     */
    @Deprecated
    List<Project> listProjectByLoginUser(String projectName, Integer customerId, Integer backPageSize, LoginUser loginUser);

    /**
     * 根据客户id获取当前用户权限下所有的项目信息
     */
    @Deprecated
    List<Project> listProjectByCustomerId(Project project, Integer backPageSize);

    /**
     * 修改项目的固定需求的数量
     *
     * @param projectId
     * @param fixedNo
     * @param loginUser
     * @author Libin.Wei
     * @Date 2018年3月28日 上午10:49:04
     */
    @Deprecated
    void updateFixedNo(int projectId, int fixedNo, LoginUser loginUser);

    /**
     * 后台查询项目列表,仅供外部系统调用
     *
     * @param pageCondition crmCustomerId crm的客户ID required
     * @param loginUser     当前登录人信息 required
     * @return
     * @throws BusinessException
     */
    Page<Project> searchForOtherSys(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 组装发单数据
     *
     * @param projectId
     * @return
     * @author Libin.Wei
     * @Date 2018年3月28日 下午10:40:21
     */
    WaybillDetailInfo doBuildWaybillInfo(Integer projectId);

    /***
     *
     * 创建一个项目
     * @param projectVo post 参数
     *
     * @param loginEmployee 操作人相关信息
     *
     * @see ProjectVoAndInfo
     *
     * @see LoginEmployee
     *
     * @throws BusinessException;
     * */
    Integer create(ProjectVoAndInfo projectVo , LoginEmployee loginEmployee ) throws BusinessException;


    /***
     *
     * 创建一个项目
     * @param projectVo post 参数
     *
     * @param loginEmployee 操作人相关信息
     *
     * @see ProjectVoAndInfo
     *
     * @see LoginEmployee
     *
     * @throws BusinessException;
     * */
    Integer update(ProjectVoAndInfo projectVo , LoginEmployee loginEmployee ) throws BusinessException;

    /**
     *
     *获取 项目详情
     *
     *@param projectId 项目 id
     *
     *@param  loginEmployee 操作人相关信息
     *
     * @see LoginEmployee
     *
     * @throws BusinessException
     * */
    ProjectVoAndInfo info(Integer projectId , LoginEmployee loginEmployee ) throws  BusinessException;

    /**
     * 分页查询项目路线信息
     * @param pageCondition
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Page<RoadMapVo> findRoadMapVoPage(PageCondition pageCondition, LoginUser loginUser) throws  BusinessException;

    /**
     * 
     * 校验是不是按顺序对账
     * 
     * @author Libin.Wei
     * @Date 2018年11月26日 上午11:33:16
     * @param projectId
     * @return boolean false: 否；true: 是
     */
    boolean checkIsReceivableFirst(Integer projectId);

    /**
     * 修改对账权限
     * 
     * @param isReceivableFirst
     * @param loginUser
     */
    void updateIsReceivableFirst(Integer projectId, Boolean isReceivableFirst, LoginUser loginUser);

    /**
     * 添加核心信息
     *
     * @param project
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    Integer coreInfoAdd(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser) throws BusinessException;

    /**
     * 核心信息修改
     *
     * @param project
     * @param loginUser
     * @throws BusinessException
     */
    void coreInfoUpdate(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser) throws BusinessException;

    /**
     * 基本信息修改
     *
     * @param project
     * @param loginUser
     * @return
     * @throws BusinessException
     */
    void basicInfoUpdate(com.juma.tgm.project.domain.v2.Project project, LoginUser loginUser) throws BusinessException;

    /**
     * 修改毛利率
     *
     * @param project
     * @return
     * @throws BusinessException
     */
    void updateProfitRate(com.juma.tgm.project.domain.v2.Project project) throws BusinessException;

    /**
     * 项目转正式运行
     *
     * @param projectId                   项目ID
     * @param contractNo                  合同编号
     * @param payToCompany                运营主体
     * @param payToCompanyCreditCode      运营主体统一信用代码
     * @param contractToCompany           签约主体
     * @param contractToCompanyCreditCode 签约主体统一信用代码
     * @param projectStartDate            项目开始时间
     * @param projectEndDate              项目截止时间
     * @param loginUser                   当前登录人信息
     *
     * @throws BusinessException
     */
    void projectToRealRun(Integer projectId, String contractNo, Integer payToCompany, String payToCompanyCreditCode,
        Integer contractToCompany, String contractToCompanyCreditCode, Date projectStartDate, Date projectEndDate,
        LoginUser loginUser) throws BusinessException;

    /**
     * 项目变更合同
     *
     * @param projectId                   项目ID
     * @param contractNo                  合同编号
     * @param payToCompany                运营主体
     * @param payToCompanyCreditCode      运营主体统一信用代码
     * @param contractToCompany           签约主体
     * @param contractToCompanyCreditCode 签约主体统一信用代码
     * @param projectStartDate            项目开始时间
     * @param projectEndDate              项目截止时间
     * @param loginEmployee               当前登录人信息
     *
     * @throws BusinessException
     */
    void projectChangeContract(Integer projectId, String contractNo, Integer payToCompany,
        String payToCompanyCreditCode, Integer contractToCompany, String contractToCompanyCreditCode,
        Date projectStartDate, Date projectEndDate,
        LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 项目续签合同
     *
     * @param projectId                   项目ID
     * @param contractNo                  合同编号
     * @param payToCompany                运营主体
     * @param payToCompanyCreditCode      运营主体统一信用代码
     * @param contractToCompany           签约主体
     * @param contractToCompanyCreditCode 签约主体统一信用代码
     * @param projectStartDate            项目开始时间
     * @param projectEndDate              项目截止时间
     * @param loginEmployee               当前登录人信息
     *
     * @throws BusinessException
     */
    void projectRenewalContract(Integer projectId, String contractNo, Integer payToCompany,
        String payToCompanyCreditCode, Integer contractToCompany, String contractToCompanyCreditCode,
        Date projectStartDate, Date projectEndDate, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 项目信息补录
     *
     * @param projectId                   项目ID
     * @param contractNo                  合同编号
     * @param payToCompany                运营主体
     * @param payToCompanyCreditCode      运营主体统一信用代码
     * @param contractToCompany           签约主体
     * @param contractToCompanyCreditCode 签约主体统一信用代码
     * @param projectStartDate            项目开始时间
     * @param projectEndDate              项目截止时间
     * @param tryWorkPassAttachment       当前登录人信息
     * @param loginUser
     */
    void projectSupplement(Integer projectId, String contractNo, Integer payToCompany, String payToCompanyCreditCode,
        Integer contractToCompany, String contractToCompanyCreditCode, Date projectStartDate, Date projectEndDate,
        String tryWorkPassAttachment, LoginUser loginUser) throws BusinessException;

    /**
     * 将项目更改为已开跑
     *
     * @param projectId
     *         项目ID
     * @param runningTime
     *         开跑时间
     * @throws BusinessException
     */
    void updateProjectToRunning(Integer projectId, Date runningTime) throws BusinessException;

    /**判断是否是项目经理或运营专员**/
    boolean isProjectManagerOrOperator(Integer projectId,Integer userId) throws BusinessException;

    /**
     *
     * <p>Title: findAllProjectInRunning</p>
     * <p>Description: 查询所有运行中项目</p>
     * @return
     */
    List<com.juma.tgm.project.domain.v2.Project> findAllProjectInRunning();

    /**
     *
     * <p>Title: findTop1ByProjectIdAndProjectStatus</p>
     * <p>Description: 根据项目编号&项目状态查询唯一项目详情</p>
     * @param projectId	项目编号
     * @return the first record of match the condition
     */
    com.juma.tgm.project.domain.v2.Project findTop1ByProjectIdAndProjectStatus(Integer projectId, ProjectEnum.ProjectStatus status);
}
