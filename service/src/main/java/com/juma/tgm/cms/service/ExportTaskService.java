package com.juma.tgm.cms.service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.cms.domain.ExportTask;
import com.juma.tgm.export.domain.ExportCollection;
import com.juma.tgm.export.domain.ExportDynamicParameter;
import com.juma.tgm.export.domain.ExportParam;

import java.util.List;

/**
 * @ClassName ExportTaskService.java
 * @Description 下载中心
 * @author Libin.Wei
 * @Date 2017年6月3日 下午6:22:44
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ExportTaskService {

    /**
     * 
     * 分页列表
     * 
     * @author Libin.Wei
     * @Date 2017年6月3日 下午6:27:07
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<ExportTask> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 根据ID查询
     * 
     * @author Libin.Wei
     * @Date 2017年6月3日 下午6:27:22
     * @param exportTaskId
     * @return
     */
    ExportTask getExportTask(Integer exportTaskId);

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2017年6月3日 下午6:27:37
     * @param ExportTask
     * @param loginUser
     */
    void insert(ExportTask exportTask, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年6月4日 上午10:19:52
     * @param exportTask
     * @param loginUser
     * @throws BusinessException
     */
    void update(ExportTask exportTask, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2017年6月4日 上午10:19:52
     * @param exportTask
     * @param loginUser
     * @throws BusinessException
     */
    void delete(Integer exportTaskId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 初始化
     * 
     * @author Libin.Wei
     * @Date 2017年6月4日 上午11:48:24
     * @param taskSign
     *            导出模块
     * @param exportParam
     *            条件
     * @param loginUser
     *            当前登录人
     * @throws BusinessException
     */
    Integer insertInit(ExportTask.TaskSign taskSign, ExportParam exportParam, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 初始化
     * 
     * @author Libin.Wei
     * @Date 2017年6月4日 上午11:48:24
     * @param taskSign
     * @param loginUser
     * @throws BusinessException
     */
    void failed(Integer exportTaskId, String failedReson, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 上传文件并更新任务流程
     * 
     * @author Libin.Wei
     * @Date 2017年6月4日 下午2:01:30
     * @param exportTaskId
     * @param fileName
     * @param obj
     * @param T
     * @param startTime
     * @param loginUser
     * @throws BusinessException
     */
    void uploadExcelAndUpdateExportTask(Integer exportTaskId, String fileName, Object obj, Class<?> T, long startTime,
            LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据任务类型获取当前登录用户最新一条
     * 
     * @author Libin.Wei
     * @Date 2017年7月18日 下午2:31:57
     * @param md5Digest
     * @param loginUser
     * @return
     */
    ExportTask findLastTaskBySign(String md5Digest, int taskSign, LoginUser loginUser);

    /**
     * 
     * 导出可选字段
     * 
     * @author Libin.Wei
     * @Date 2018年5月18日 上午9:23:52
     * @return
     */
    List<ExportDynamicParameter> exportFields(LoginUser loginUser);

    /**
     * 
     * 执行导出操作
     * 
     * @author Libin.Wei
     * @Date 2018年5月18日 上午9:31:53
     * @return
     */
    Object doExport(ExportParam exportParam, LoginUser loginUser);

    /**
     * 
     * 数据中心查询导出数据
     * 
     * @author Libin.Wei
     * @Date 2018年5月18日 上午10:01:50
     * @param pageNo
     * @param pageSize
     * @param loginUser
     * @return
     */
    Page<ExportCollection> search(int pageNo, int pageSize, LoginUser loginUser);

    /**
     * 运单导出数据中心删除接口
     * @param exportTaskId
     * @param loginUser
     * @throws BusinessException
     */
    void deleteData(Integer exportTaskId, LoginUser loginUser) throws BusinessException;
}
