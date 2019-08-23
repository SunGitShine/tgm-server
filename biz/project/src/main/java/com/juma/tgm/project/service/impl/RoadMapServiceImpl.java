package com.juma.tgm.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.juma.tgm.project.domain.*;
import com.juma.tgm.project.enumeration.ValuationWayEnum;
import com.juma.tgm.project.vo.RoadMapQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.base.domain.Paging;
import com.juma.tgm.common.BaseUtil;
import com.juma.tgm.project.dao.RoadMapMapper;
import com.juma.tgm.project.domain.RoadMapExample.Criteria;
import com.juma.tgm.project.service.RoadMapDestAdressService;
import com.juma.tgm.project.service.RoadMapPriceRuleService;
import com.juma.tgm.project.service.RoadMapService;
import com.juma.tgm.project.service.RoadMapSrcAdressService;
import com.juma.tgm.project.vo.RoadMapVo;

@Service
public class RoadMapServiceImpl implements RoadMapService {

    @Resource
    private RoadMapMapper roadMapMapper;
    @Resource
    private RoadMapSrcAdressService roadMapSrcAdressService;
    @Resource
    private RoadMapDestAdressService roadMapDestAdressService;
    @Resource
    private RoadMapPriceRuleService roadMapPriceRuleService;

    @Override
    public Page<RoadMap> search(PageCondition pageCondition, LoginUser loginUser) {
        Paging paging = new Paging(pageCondition, "road_map_id", "desc");
        RoadMapExample example = new RoadMapExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);

        Map<String, Object> filters = pageCondition.getFilters();
        if (null != filters) {
            if (null != filters.get("projectId")) {
                criteria.andProjectIdEqualTo(BaseUtil.strToNum(filters.get("projectId").toString()));
            }

            if (null != filters.get("roadMapId")) {
                criteria.andRoadMapIdEqualTo(BaseUtil.strToNum(filters.get("roadMapId").toString()));
            }

            if (null != filters.get("name")) {
                criteria.andNameLike(filters.get("name") + "%");
            }
        }

        example.setStartOffSet(paging.getStartOffSet());
        example.setSize(paging.getPageSize());
        example.setOrderByClause(paging.getOrder());

        int total = roadMapMapper.countByExample(example);
        List<RoadMap> rows = roadMapMapper.selectByExample(example);
        return new Page<RoadMap>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public Page<RoadMapQuery> searchIncludeAddressAndPrice(PageCondition pageCondition, LoginUser loginUser) {
        List<RoadMapQuery> result = new ArrayList<RoadMapQuery>();

        if (null == pageCondition.getFilters() || null == pageCondition.getFilters().get("projectId")) {
            return new Page<RoadMapQuery>(pageCondition.getPageNo(), pageCondition.getPageSize(), 0, result);
        }

        Page<RoadMap> page = this.search(pageCondition, loginUser);

        for (RoadMap roadMap : page.getResults()) {
            RoadMapQuery query = new RoadMapQuery();
            query.setRoadMap(roadMap);

            // 第一个取货地
            List<RoadMapSrcAdress> listSrcAdress = roadMapSrcAdressService.listByRoadMapId(roadMap.getRoadMapId());
            query.setRoadMapSrcAdress(listSrcAdress.isEmpty() ? null : listSrcAdress.get(0));

            // 第一个配送地
            List<RoadMapDestAdress> listDestAdress = roadMapDestAdressService.listByRoadMapId(roadMap.getRoadMapId());
            query.setRoadMapDestAdress(listDestAdress.isEmpty() ? null : listDestAdress.get(0));

            //所有配送地
            query.setListRoadMapDestAdress(listDestAdress);

            //车型、计价方式、计价维度
            List<RoadMapPriceRule> roadMapPriceRules = roadMapPriceRuleService.listByRoadMapId(roadMap.getRoadMapId());
            query.setListRoadMapPriceRule(roadMapPriceRuleService.buidBeanName(roadMapPriceRules,loginUser));

            result.add(query);
        }

        return new Page<RoadMapQuery>(page.getPageNo(), page.getPageSize(), page.getTotal(), result);
    }

    @Override
    public int countRoadMapByProjectId(Integer projectId) {
        if (null == projectId) {
            return 0;
        }

        RoadMapExample example = new RoadMapExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andIsDeleteEqualTo((byte) 0);
        return roadMapMapper.countByExample(example);
    }

    @Override
    public RoadMap getRoadMap(Integer roadMapId) {
        if (null == roadMapId) {
            return null;
        }
        return roadMapMapper.selectByPrimaryKey(roadMapId);
    }

    @Override
    public RoadMapVo findRoadMapIncludeAddressAndPrice(Integer roadMapId) throws BusinessException {
        RoadMap roadMap = this.getRoadMap(roadMapId);
        if (null == roadMap) {
            return null;
        }

        RoadMapVo vo = new RoadMapVo();
        vo.setRoadMap(roadMap);
        vo.setListRoadMapPriceRule(roadMapPriceRuleService.listByRoadMapId(roadMapId));
        vo.setListRoadMapSrcAdress(roadMapSrcAdressService.listByRoadMapId(roadMapId));
        vo.setListRoadMapDestAdress(roadMapDestAdressService.listByRoadMapId(roadMapId));
        return vo;
    }

    @Override
    public Integer insert(RoadMapVo roadMapVo, LoginUser loginUser) throws BusinessException {
        List<RoadMapPriceRule> listRoadMapPriceRule = roadMapVo.getListRoadMapPriceRule();
        if (CollectionUtils.isNotEmpty(listRoadMapPriceRule)
                && null != listRoadMapPriceRule.get(0).getValuationWay()
                && ValuationWayEnum.FIXED_PRICE.getCode() == listRoadMapPriceRule.get(0).getValuationWay()) {
            roadMapVo.getRoadMap().setWhoWriteWork(null);
        }

        Integer roadMapId = this.insertDb(roadMapVo.getRoadMap(), loginUser);
        if (null == roadMapId) {
            return null;
        }

        // 添加价格信息
        roadMapPriceRuleService.batchInsert(roadMapId, roadMapVo.getListRoadMapPriceRule(), loginUser);
        // 添加取货地信息
        roadMapSrcAdressService.batchInsert(roadMapId, roadMapVo.getListRoadMapSrcAdress(), loginUser);
        // 添加配送地信息
        roadMapDestAdressService.batchInsert(roadMapId, roadMapVo.getListRoadMapDestAdress(), loginUser);

        return roadMapId;
    }

    // 路线信息主表添加
    private Integer insertDb(RoadMap roadMap, LoginUser loginUser) {
        if (null == roadMap) {
            return null;
        }

        this.check(null, roadMap.getProjectId(), roadMap.getName());

        roadMap.setCreateUserId(loginUser == null ? null : loginUser.getUserId());
        roadMap.setWhoWriteWork(roadMap.getWhoWriteWork() == null ? (byte) 0 : roadMap.getWhoWriteWork());
        roadMap.setIsDelete((byte) 0);
        roadMap.setCreateTime(new Date());
        roadMapMapper.insertSelective(roadMap);

        return roadMap.getRoadMapId();
    }

    @Override
    public void update(RoadMapVo roadMapVo, LoginUser loginUser) throws BusinessException {
        List<RoadMapPriceRule> listRoadMapPriceRule = roadMapVo.getListRoadMapPriceRule();
        if (CollectionUtils.isNotEmpty(listRoadMapPriceRule)
                && null != listRoadMapPriceRule.get(0).getValuationWay()
                && ValuationWayEnum.FIXED_PRICE.getCode() == listRoadMapPriceRule.get(0).getValuationWay()) {
            roadMapVo.getRoadMap().setWhoWriteWork((byte) 0);
        }
        this.updateDb(roadMapVo.getRoadMap(), loginUser);
        // 修改价格信息
        roadMapPriceRuleService.batchUpdate(roadMapVo.getRoadMap().getRoadMapId(),
                roadMapVo.getListRoadMapPriceRule(), loginUser);
        // 修改取货地信息
        roadMapSrcAdressService.batchUpdate(roadMapVo.getRoadMap().getRoadMapId(), roadMapVo.getListRoadMapSrcAdress(),
                loginUser);
        // 修改配送地信息
        roadMapDestAdressService.batchUpdate(roadMapVo.getRoadMap().getRoadMapId(),
                roadMapVo.getListRoadMapDestAdress(), loginUser);
    }

    // 路线信息主表修改
    private void updateDb(RoadMap roadMap, LoginUser loginUser) {
        if (null == roadMap || null == roadMap.getRoadMapId()) {
            return;
        }

        this.check(roadMap.getRoadMapId(), roadMap.getProjectId(), roadMap.getName());

        roadMap.setLastUpdateUserId(loginUser == null ? null : loginUser.getUserId());
        roadMap.setLastUpdateTime(new Date());
        roadMapMapper.updateByPrimaryKeySelective(roadMap);
    }

    // 基础信息校验
    private void check(Integer roadMapId, Integer projectId, String name) {
        if (null == projectId) {
            throw new BusinessException("projectIdRequred", "roadMap.error.projectIdRequred");
        }

        if (StringUtils.isBlank(name)) {
            throw new BusinessException("roadMapNameRequred", "roadMap.error.roadMapNameRequred");
        }

        if (name.length() > 15) {
            throw new BusinessException("roadMapNameTooLong", "roadMap.error.roadMapNameTooLong");
        }

        RoadMapExample example = new RoadMapExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andNameEqualTo(name).andIsDeleteEqualTo((byte) 0);
        List<RoadMap> list = roadMapMapper.selectByExample(example);
        if (list.isEmpty()) {
            return;
        }

        if (null == roadMapId) {
            throw new BusinessException("roadMapNameIdExist", "roadMap.error.roadMapNameIdExist");
        }

        if (!roadMapId.equals(list.get(0).getRoadMapId())) {
            throw new BusinessException("roadMapNameIdExist", "roadMap.error.roadMapNameIdExist");
        }
    }

    @Override
    public void delete(Integer roadMapId, LoginUser loginUser) throws BusinessException {
        if (null == roadMapId) {
            return;
        }
        // 更改为逻辑删除
        RoadMap roadMap = this.getRoadMap(roadMapId);
        if (null == roadMap) {
            return;
        }
        
        roadMap.setIsDelete((byte) 1);
        roadMapMapper.updateByPrimaryKey(roadMap);

//        roadMapMapper.deleteByPrimaryKey(roadMapId);
        // 删除价格信息
//        roadMapPriceRuleService.deleteByRoadMapId(roadMapId, loginUser);
        // 删除取货地信息
//        roadMapSrcAdressService.deleteByRoadMapId(roadMapId, loginUser);
        // 删除配送地信息
//        roadMapDestAdressService.deleteByRoadMapId(roadMapId, loginUser);
    }

    @Override
    public List<RoadMap> listByProjectId(Integer projectId) {
        if (null == projectId) {
            return new ArrayList<RoadMap>();
        }
        RoadMapExample example = new RoadMapExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andIsDeleteEqualTo((byte) 0);

        return roadMapMapper.selectByExample(example);
    }

    @Override
    public List<RoadMapVo> listVoByProjectId(Integer projectId) {
        ArrayList<RoadMapVo> result = new ArrayList<RoadMapVo>();
        if (null == projectId) {
            return result;
        }

        List<RoadMap> listRoadMap = this.listByProjectId(projectId);
        for (RoadMap r : listRoadMap) {
            RoadMapVo vo = new RoadMapVo();
            vo.setRoadMap(r);
            if( r != null ) {
                vo.setListRoadMapPriceRule(roadMapPriceRuleService.listByRoadMapId(r.getRoadMapId()));
                vo.setListRoadMapSrcAdress(roadMapSrcAdressService.listByRoadMapId(r.getRoadMapId()));
                vo.setListRoadMapDestAdress(roadMapDestAdressService.listByRoadMapId(r.getRoadMapId()));
            }
            result.add(vo);
        }

        return result;
    }

    @Override
    public Integer countRoadNum(Integer projectId) throws BusinessException {
        if (null == projectId) {
            return 0;
        }
        RoadMapExample example = new RoadMapExample();
        example.createCriteria().andProjectIdEqualTo(projectId).andIsDeleteEqualTo((byte) 0);
        return roadMapMapper.countByExample(example);
    }

    @Override
    public RoadMapVo get(Integer id) throws BusinessException {
        RoadMap r = this.getRoadMap( id );
        RoadMapVo vo = new RoadMapVo();
        vo.setRoadMap(r);
        if( r != null ) {
            vo.setListRoadMapPriceRule(roadMapPriceRuleService.listByRoadMapId(r.getRoadMapId()));
            vo.setListRoadMapSrcAdress(roadMapSrcAdressService.listByRoadMapId(r.getRoadMapId()));
            vo.setListRoadMapDestAdress(roadMapDestAdressService.listByRoadMapId(r.getRoadMapId()));
        }
        return vo ;
    }

    @Override
    public List<RoadMapVo> listVoByRoadMapList(List<RoadMap> roadMaps) {

        ArrayList<RoadMapVo> result = new ArrayList<RoadMapVo>();
        for (RoadMap r : roadMaps) {
            RoadMapVo vo = new RoadMapVo();
            vo.setRoadMap(r);
            if( r != null ) {
                vo.setListRoadMapPriceRule(roadMapPriceRuleService.listByRoadMapId(r.getRoadMapId()));
                vo.setListRoadMapSrcAdress(roadMapSrcAdressService.listByRoadMapId(r.getRoadMapId()));
                vo.setListRoadMapDestAdress(roadMapDestAdressService.listByRoadMapId(r.getRoadMapId()));
            }
            result.add(vo);
        }

        return result;
    }

    @Override
    public List<RoadMap> listBylikeName(Integer projectId, String name) {
        if (null == projectId) {
            return new ArrayList<RoadMap>();
        }

        RoadMapExample example = new RoadMapExample();
        Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        criteria.andIsDeleteEqualTo((byte) 0);
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(name + "%");
        }

        return roadMapMapper.selectByExample(example);
    }
}
