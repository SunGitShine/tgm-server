package com.juma.tgm.receiptManage.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import com.juma.tgm.receiptManage.domain.ReceiptManage;

/**
 * @ClassName ReceiptManageService.java
 * @Description 回单管理
 * @author Libin.Wei
 * @Date 2017年7月10日 下午6:10:38
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface ReceiptManageService {
    
    /**
     * 
     * 根据运单ID获取回单记录
     * @author youShuanglin
     * @Date 2017年7月11日 下午6:26:51
     * @param waybillId
     * @return
     */
    List<ReceiptManage> listByWaybillId(Integer waybillId);
    
    /**
     * 
     * 根据运单ID获取回单上传所有的图片记录
     * @author Libin.Wei
     * @Date 2017年7月12日 上午11:00:22
     * @param waybillId
     * @return
     */
    List<ImageUploadManage> listReceiptImageByWaybillId(Integer waybillId);

    /**
     *
     * 根据运单ID获取货物单上传所有的图片记录
     * @author Libin.Wei
     * @Date 2017年7月12日 上午11:00:22
     * @param waybillId
     * @return
     */
    List<ImageUploadManage> listGoodsImageByWaybillId(Integer waybillId);

    /**
     * 
     * 添加上传记录
     * @author youShuanglin
     * @Date 2017年7月11日 下午6:27:38
     * @param receiptManage
     * @param loginUser
     * @throws BusinessException
     */
    void insert(ReceiptManage receiptManage, LoginUser loginUser) throws BusinessException;

    /**
     *
     * 全删全添
     * @author youShuanglin
     * @Date 2017年7月11日 下午6:27:38
     * @param receiptManage
     * @param loginUser
     * @throws BusinessException
     */
    void deleteAndInsert(ReceiptManage receiptManage, LoginUser loginUser) throws BusinessException;
}
