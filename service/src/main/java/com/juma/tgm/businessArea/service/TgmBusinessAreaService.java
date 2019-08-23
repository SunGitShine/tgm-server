package com.juma.tgm.businessArea.service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.conf.domain.BusinessAreaNode;
import com.juma.auth.user.domain.LoginUser;

import java.util.List;

/**
 * @ClassName: BusinessAreaService
 * @Description:
 * @author: liang
 * @date: 2017-09-07 10:25
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public interface TgmBusinessAreaService {

    /**
     *
     * 获取逻辑业务区域树(树形结构数据)
     * 用于手机端展示逻辑业务区域
     * @return
     * @throws BusinessException
     */
    List<BusinessAreaNode> getLogicBusinessAreaTree(LoginUser loginUser) throws BusinessException;


    /**
     * 获取逻辑业务区域平行树
     * 用于pc端展示逻辑业务区域
     * @return
     * @throws BusinessException
     */
    List<BusinessAreaNode> getLogicBusinessAreaParallel(LoginUser loginUser) throws BusinessException;

    /**
     * 获取所有的父节点
     * @param childId
     * @return
     * @throws BusinessException
     */
    List<BusinessArea> findAllParentNode(int childId, LoginUser loginUser) throws BusinessException;
}
