package com.juma.tgm.tools.service;

import com.juma.auth.user.domain.LoginUser;

/**
 * @ClassName UploadService.java
 * @Description 上传接口
 * @author Libin.Wei
 * @Date 2017年6月3日 下午2:45:40
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface UploadTmsService {

    /**
     * 
     * 上传excel文件
     * 
     * @author Libin.Wei
     * @Date 2017年6月3日 下午2:49:52
     * @param fileName
     *            文件名
     * @param obj
     *            上传对象
     * @param T
     *            obj对象类型
     * @param taskId
     *            任务ID
     * @param exportTaskId
     *            导出任务ID
     */
    String uploadExcel(String fileName, Object obj, Class<?> T, Integer taskId, LoginUser loginUser);
}
