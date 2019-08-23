package com.juma.tgm.imageUploadManage.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.domain.UploadFile;

/**
 * @ClassName ImageUploadManageService.java
 * @Description 图片上传管理
 * @author Libin.Wei
 * @Date 2017年7月10日 上午11:15:39
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ImageUploadManageService {

    /**
     * 
     * 上传图片
     * 
     * @author Libin.Wei
     * @Date 2018年7月26日 上午11:21:20
     * @param imageUploadManage
     * @param loginUser
     * @throws BusinessException
     */
    void insert(ImageUploadManage imageUploadManage, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 批量上传图片
     * 
     * @author Libin.Wei
     * @Date 2018年7月26日 上午11:27:06
     * @param imgUrls
     * @param relationId
     * @param imageUploadManageSign
     * @param loginUser
     * @throws BusinessException
     */
    @Deprecated
    void batchInsert(List<String> imgUrls, Integer relationId,
            ImageUploadManage.ImageUploadManageSign imageUploadManageSign, String fileName, LoginUser loginUser)
            throws BusinessException;

    /**
     *
     * 批量上传图片
     *
     * @author Libin.Wei
     * @Date 2018年7月26日 上午11:27:06
     * @param uploadFiles
     * @param relationId
     * @param imageUploadManageSign
     * @param loginUser
     * @throws BusinessException
     */
    void batchInsert(List<UploadFile> uploadFiles, Integer relationId,
            ImageUploadManage.ImageUploadManageSign imageUploadManageSign, LoginUser loginUser)throws BusinessException;

    /**
     * 
     * 全删全插
     * 
     * @author Libin.Wei
     * @Date 2018年7月26日 上午11:46:58
     * @param imgUrls
     * @param relationId
     * @param imageUploadManageSign
     * @param loginUser
     * @throws BusinessException
     */
    @Deprecated
    void updateByAllDelAndAllAdd(List<String> imgUrls, Integer relationId,
            ImageUploadManage.ImageUploadManageSign imageUploadManageSign, String fileName, LoginUser loginUser)
            throws BusinessException;

    /**
     *
     * 全删全插
     *
     * @author Libin.Wei
     * @Date 2018年7月26日 上午11:46:58
     * @param uploadFiles
     * @param relationId
     * @param imageUploadManageSign
     * @param loginUser
     * @throws BusinessException
     */
    void updateByAllDelAndAllAdd(List<UploadFile> uploadFiles, Integer relationId,
                                 ImageUploadManage.ImageUploadManageSign imageUploadManageSign, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 删除图片
     * 
     * @author Libin.Wei
     * @Date 2018年7月26日 上午11:21:31
     * @param relationId
     * @param imageUploadManageSign
     * @param loginUser
     * @throws BusinessException
     */
    void delete(Integer relationId, ImageUploadManage.ImageUploadManageSign imageUploadManageSign, LoginUser loginUser)
            throws BusinessException;

    /**
     * 
     * 图片回显
     * 
     * @author Libin.Wei
     * @Date 2018年7月26日 上午11:21:48
     * @param relationId
     * @param imageUploadManageSign
     * @return
     */
    List<ImageUploadManage> listByRelationIdAndSign(Integer relationId, Integer imageUploadManageSign);

    /**
     * 
     * 删除文件记录
     * 
     * @Date 2018年5月26日 上午11:22:00
     * @param imageUploadManageId
     * @throws BusinessException
     */
    void delByImageUploadManageId(Integer imageUploadManageId) throws BusinessException;

}
