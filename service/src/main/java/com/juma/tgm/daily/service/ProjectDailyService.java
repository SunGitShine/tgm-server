package com.juma.tgm.daily.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.query.QueryCond;
import com.juma.tgm.daily.domain.ProjectDaily;
import com.juma.tgm.daily.enums.ProjectDailyEnum;
import com.juma.tgm.daily.vo.ProjectDailyFilter;
import com.juma.tgm.daily.vo.ProjectDailyVo;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.domain.UploadFile;
import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.domain.WaybillVO;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProjectDailyService
 * @Description 项目日报
 * @Author weilibin
 * @Date 2019-07-18 18:14
 * @Version 1.0.0
 */

public interface ProjectDailyService {

    /**
     * 分页查询
     *
     * @param queryCond
     * @param loginEmployee
     *
     * @return
     */
    Page<ProjectDailyVo> search(QueryCond<ProjectDailyFilter> queryCond,  LoginEmployee loginEmployee);

    /**
     * 根据主键获取
     *
     * @param projectDailyId
     *
     * @return
     */
    ProjectDailyVo getProjectDailyVo(Integer projectDailyId);


    /**
     * 根据项目ID和日报日期获取
     *
     * @param projectId             项目I，必填
     * @param startProjectDailyDate 日报开始时间，必填
     * @param endProjectDailyDate   日报结束时间，必填
     *
     * @return
     */
    List<ProjectDaily> listProjectDailyByProjectIdAndDailyDate(Integer projectId, Date startProjectDailyDate,
        Date endProjectDailyDate);

    /**
     * 添加
     * @param projectDaily
     * @param loginUser
     * @throws BusinessException
     */
    void insert(ProjectDaily projectDaily, LoginUser loginUser) throws BusinessException;

    /**
     * 更新
     * @param projectDaily
     * @param loginUser
     * @throws BusinessException
     */
    void update(ProjectDaily projectDaily, LoginUser loginUser) throws BusinessException;

    int batchInsert(List<ProjectDaily> projectDailies);
    
    /**
     * 
     * <p>Title: createProjectDaily</p>  
     * <p>Description: 项目状态变更为运行中生成日报，日报状态：未过期，运费状态：未确认</p>
     * @param projectId 项目ID
     * @return
     */
    Boolean createProjectDaily(Integer projectId);

    /**
     * 导入运单时，生成日报，日报状态：已过期，运费状态：全部确认
     * @param projectId 项目ID
     * @param dailyDate 导单传入用车时间
     * @return
     */
    Boolean createProjectDailyOnImport(Integer projectId, Date dailyDate);


    /**
     * 获取日报状态为未到期且满足运费状态条件的日报
     * @param freightStatus NOT_CONFIRMED : 未确认 , PART_CONFIRMED : 部分确认 , HAS_CONFIRMED : 已确认 ; null:此过滤条件不生效
     * @return
     */
    List<ProjectDaily> getListByFreightStatus(ProjectDailyEnum.FreightStatus freightStatus);

    /**
     * 返回日报状态未过期，未删除，日报时间非当前日日报
     * @return
     */
    List<ProjectDaily> getListByFreightStatus();


    /**
     * 获取日报状态为未到期且满足条件的日报
     * @param freightStatus freightStatus NOT_CONFIRMED : 未确认 , PART_CONFIRMED : 部分确认 , HAS_CONFIRMED : 已确认 ; null:此过滤条件不生效
     * @param date 选择某一天的日报，不传则默认当前日期前一天
     * @return
     */
    List<ProjectDaily> getListByFreightStatus(ProjectDailyEnum.FreightStatus freightStatus, Date date);


    /**
     * 逻辑删除日报
     * @param projectDailies 待删除列表
     * @return
     */
    int deleteProjectDaily(List<ProjectDaily> projectDailies);

    /**
     * 显示日报详情
     * @param projectDailyVo
     * @return
     */
    ProjectDailyVo searchProjectDaily(ProjectDailyVo projectDailyVo) throws BusinessException;

    /**
     * 查询日报运单列表
     * @param projectDaily
     * @return
     */
    List<WaybillVO> searchProjectDailyWaybills(ProjectDaily projectDaily) throws BusinessException;

    /**
     * 更新运单确认
     * @param waybillVO
     * @param projectDailyId
     */
    void updateProjectDailyWaybil(WaybillVO waybillVO, Integer projectDailyId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 运营台账上传
     * @param uploadFiles
     * @param projectDailyId
     * @param loginEmployee
     */
    void uploadStandingBook(List<UploadFile> uploadFiles, Integer projectDailyId, LoginEmployee loginEmployee) throws BusinessException;

    /**
     * 查询运营台账
     * @param projectDailyId
     * @return
     */
    List<ImageUploadManage> searchStandingBook(Integer projectDailyId) throws BusinessException;


    /**
     * 根据主键批量更新
     * @param projectDailies
     * @return
     */
    int updateDailysByPrimaryKey(List<ProjectDaily> projectDailies);

}
