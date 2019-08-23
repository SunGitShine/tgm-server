package com.juma.tgm.tools.service;

import java.util.List;

import com.juma.auth.conf.domain.BusinessArea;
import com.juma.auth.user.domain.LoginUser;

/**
 * When I wrote this code, I was wondering if I needed to notify God, but I
 * found the code didn't make God mad, so I decided not to tell him
 * 
 * @ClassName BusinessCommonService.java
 * @Description 业务区域的处理
 * @author Libin.Wei
 * @Date 2017年9月20日 下午5:26:35
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface BusinessAreaCommonService {

    /**
     * 
     * 链式获取所有的父节点及及自己的名字
     * 
     * @author Libin.Wei
     * @Date 2017年9月20日 下午5:30:02
     * @param areaCode
     * @param loginUser
     * @return
     */
    String loadAllLinkNodeAreaName(String areaCode, LoginUser loginUser);

    /**
     * 
     * 获取向上第一个父节点及自己的名字
     * 
     * @author Libin.Wei
     * @Date 2017年9月20日 下午5:30:07
     * @param areaCode
     * @param loginUser
     * @return
     */
    String loadPreAndSelfAreaName(String areaCode, LoginUser loginUser);

    /**
     * 
     * 获取向上第一个逻辑区域及自己的名字
     * 
     * @author Libin.Wei
     * @Date 2017年9月20日 下午5:30:07
     * @param areaCode
     * @param loginUser
     * @return
     */
    String loadLogicAndSelfAreaName(String areaCode, LoginUser loginUser);

    /**
     * 获取业务区域名称
     *
     * @param areaCode
     * @param loginUser
     * @return
     */
    String loadAreaName(String areaCode, LoginUser loginUser);

    /**
     * 
     * 获取顶级业务区域编码列表
     * 
     * @author Libin.Wei
     * @Date 2018年3月23日 上午10:45:31
     * @param loginUser
     * @return
     */
    List<String> listTopLevelAreaCode(LoginUser loginUser);

    /**
     * 根据父ID获取
     *
     * @param parentBusinessAreaId
     * @param loginUser
     * @return
     */
    List<BusinessArea> listChildBusinessArea(Integer parentBusinessAreaId, LoginUser loginUser);
}
