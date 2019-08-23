package com.juma.tgm.project.service;

import java.util.List;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.domain.RoadMap;
import com.juma.tgm.project.vo.RoadMapQuery;
import com.juma.tgm.project.vo.RoadMapVo;

/**
 * @ClassName RoadMapService.java
 * @Description 路线信息，不要强关连，数据可以物理删除
 * @author Libin.Wei
 * @Date 2018年9月28日 上午10:16:04
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public interface RoadMapService {

    /**
     * 
     * 分页获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:54:14
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<RoadMap> search(PageCondition pageCondition, LoginUser loginUser);

    /**
     *
     * 分页获取,包括地址信息与价格信息
     *
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:54:14
     * @param pageCondition
     * @param loginUser
     * @return
     */
    Page<RoadMapQuery> searchIncludeAddressAndPrice(PageCondition pageCondition, LoginUser loginUser);

    /**
     * 
     * 线路数量
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 下午5:59:53
     * @param projectId
     * @return
     */
    int countRoadMapByProjectId(Integer projectId);

    /**
     * 
     * 根据主键获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:25
     * @param roadMapId
     * @return
     */
    RoadMap getRoadMap(Integer roadMapId);

    /**
     * 根据主键获取,包括地址信息与价格信息
     *
     * @param roadMapId
     * @return
     * @throws BusinessException
     */
    RoadMapVo findRoadMapIncludeAddressAndPrice(Integer roadMapId) throws BusinessException;

    /**
     * 
     * 添加
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:42
     * @param roadMap
     * @param loginUser
     * @throws BusinessException
     */
    Integer insert(RoadMapVo roadMapVo, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 修改
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:22:52
     * @param roadMap
     * @param loginUser
     * @throws BusinessException
     */
    void update(RoadMapVo roadMapVo, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 删除
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:01
     * @param roadMapId
     * @param loginUser
     * @throws BusinessException
     */
    void delete(Integer roadMapId, LoginUser loginUser) throws BusinessException;

    /**
     * 
     * 根据项目ID获取
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:13
     * @param projectId
     * @return
     */
    List<RoadMap> listByProjectId(Integer projectId);

    /**
     * 
     * 根据项目ID获取所有信息
     * 
     * @author Libin.Wei
     * @Date 2018年9月28日 上午10:23:13
     * @param projectId
     * @return
     */
    List<RoadMapVo> listVoByProjectId(Integer projectId);

    /***
     * 按项目id 统计 有多少条 线路信息
     *
     * @param projectId
     *            项目 id
     */
    Integer countRoadNum(Integer projectId) throws BusinessException;

    /**
     * 按 id 获取 路线信息 详情
     * 
     * @param id
     *            路线id
     *
     * @throws BusinessException
     */
    RoadMapVo get(Integer id) throws BusinessException;

    /**
     * 批量设置路线信息的计价规则
     * 
     * @return
     */
    List<RoadMapVo> listVoByRoadMapList(List<RoadMap> roadMaps);

    /**
     * 
     * 根据项目ID、路线名称获取
     * 
     * @author Libin.Wei
     * @Date 2018年11月30日 下午2:48:38
     * @param projectId
     * @param name
     * @return
     */
    List<RoadMap> listBylikeName(Integer projectId, String name);
}
