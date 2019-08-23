package com.juma.tgm.imageUploadManage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.juma.tgm.imageUploadManage.domain.UploadFile;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.imageUploadManage.dao.ImageUploadManageDao;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage.ImageUploadManageSign;
import com.juma.tgm.imageUploadManage.service.ImageUploadManageService;

@Service
public class ImageUploadManageServiceImpl implements ImageUploadManageService {

    @Resource
    private ImageUploadManageDao imageUploadManageDao;

    @Override
    public void insert(ImageUploadManage imageUploadManage, LoginUser loginUser) throws BusinessException {
        if (null == imageUploadManage.getImageUploadManageSign()) {
            return;
        }

        imageUploadManage.setCreateUserId(loginUser.getUserId());
        imageUploadManage.setImageUploadUrl(BaseUtil.buildImgUrl(imageUploadManage.getImageUploadUrl()));
        imageUploadManageDao.insert(imageUploadManage);
    }

    @Override
    public void batchInsert(List<String> imgUrls, Integer relationId, ImageUploadManageSign imageUploadManageSign, String fileName,
            LoginUser loginUser) throws BusinessException {
        if (CollectionUtils.isEmpty(imgUrls) || null == relationId || null == imageUploadManageSign) {
            return;
        }

        ImageUploadManage imageUploadManage = new ImageUploadManage();
        for (String url : imgUrls) {
            imageUploadManage.setRelationId(relationId);
            imageUploadManage.setImageUploadManageSign(imageUploadManageSign.getCode());
            imageUploadManage.setFileName(fileName);
            imageUploadManage.setImageUploadUrl(url);
            this.insert(imageUploadManage, loginUser);
        }
    }

    @Override
    public void batchInsert(List<UploadFile> uploadFiles, Integer relationId,
                            ImageUploadManageSign imageUploadManageSign, LoginUser loginUser) throws BusinessException {
        if (CollectionUtils.isEmpty(uploadFiles) || null == relationId || null == imageUploadManageSign) {
            return;
        }

        ImageUploadManage imageUploadManage = new ImageUploadManage();
        for (UploadFile file : uploadFiles) {
            imageUploadManage.setRelationId(relationId);
            imageUploadManage.setImageUploadManageSign(imageUploadManageSign.getCode());
            imageUploadManage.setFileName(file.getFileName());
            imageUploadManage.setImageUploadUrl(file.getFileUrl());
            this.insert(imageUploadManage, loginUser);
        }
    }

    @Override
    public void updateByAllDelAndAllAdd(List<String> imgUrls, Integer relationId,
            ImageUploadManageSign imageUploadManageSign, String fileName, LoginUser loginUser)
            throws BusinessException {
        if (null == relationId || null == imageUploadManageSign) {
            return;
        }

        // 删除
        this.delete(relationId, ImageUploadManage.ImageUploadManageSign.GOODS_CHECK, loginUser);

        // 添加
        this.batchInsert(imgUrls, relationId, ImageUploadManage.ImageUploadManageSign.GOODS_CHECK, fileName, loginUser);
    }

    @Override
    public void updateByAllDelAndAllAdd(List<UploadFile> uploadFiles, Integer relationId,
                                        ImageUploadManageSign imageUploadManageSign, LoginUser loginUser) throws BusinessException {
        if (null == relationId || null == imageUploadManageSign) {
            return;
        }

        // 删除
        this.delete(relationId, imageUploadManageSign, loginUser);

        // 添加
        this.batchInsert(uploadFiles, relationId, imageUploadManageSign, loginUser);
    }

    @Override
    public List<ImageUploadManage> listByRelationIdAndSign(Integer relationId, Integer imageUploadManageSign) {

        if (imageUploadManageSign == null) {
            return new ArrayList<ImageUploadManage>();

        }
        ImageUploadManage example = new ImageUploadManage();
        example.setRelationId(relationId);
        example.setImageUploadManageSign(imageUploadManageSign);
        return imageUploadManageDao.findByExample(example);
    }

    @Override
    public void delete(Integer relationId, ImageUploadManageSign imageUploadManageSign, LoginUser loginUser)
            throws BusinessException {
        if (null == relationId || null == imageUploadManageSign) {
            return;
        }

        ImageUploadManage imageUploadManage = new ImageUploadManage();
        imageUploadManage.setRelationId(relationId);
        imageUploadManage.setImageUploadManageSign(imageUploadManageSign.getCode());
        imageUploadManageDao.deleteBy(imageUploadManage);
    }

    @Override
    public void delByImageUploadManageId(Integer imageUploadManageId) throws BusinessException {
        if (null == imageUploadManageId) {
            return;
        }
        imageUploadManageDao.deleteById(imageUploadManageId);
    }
}
