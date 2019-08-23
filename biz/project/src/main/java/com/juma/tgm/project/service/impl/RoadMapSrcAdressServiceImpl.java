package com.juma.tgm.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.dao.RoadMapSrcAdressMapper;
import com.juma.tgm.project.domain.RoadMapSrcAdress;
import com.juma.tgm.project.domain.RoadMapSrcAdressExample;
import com.juma.tgm.project.service.RoadMapSrcAdressService;
import com.juma.tgm.region.service.RegionTgmService;

/**
 * @ClassName RoadMapSrcAdressServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年9月28日 上午10:41:22
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class RoadMapSrcAdressServiceImpl implements RoadMapSrcAdressService {

    @Resource
    private RoadMapSrcAdressMapper roadMapSrcAdressMapper;
    @Resource
    private RegionTgmService regionTgmService;

    @Override
    public RoadMapSrcAdress getRoadMapSrcAdress(Integer roadMapSrcAdressId) {
        return null;

    }

    @Override
    public void insert(RoadMapSrcAdress roadMapSrcAdress, LoginUser loginUser) throws BusinessException {
        roadMapSrcAdress.setCreateUserId(loginUser == null ? null : loginUser.getUserId());
        roadMapSrcAdress.setCreateTime(new Date());
        roadMapSrcAdressMapper.insert(roadMapSrcAdress);
    }

    @Override
    public void batchInsert(Integer roadMapId, List<RoadMapSrcAdress> listRoadMapSrcAdress, LoginUser loginUser)
            throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        this.check(listRoadMapSrcAdress);

        for (RoadMapSrcAdress r : listRoadMapSrcAdress) {
            r.setRoadMapId(roadMapId);
            if (StringUtils.isBlank(r.getRegionCode())) {
                String regionCode = regionTgmService.findRegionCodeByCoordinate(r.getCoordinates());
                r.setRegionCode(StringUtils.isBlank(regionCode) ? null
                        : (regionCode.length() > 5 ? regionCode.substring(0, 5) : regionCode));
            }
            this.insert(r, loginUser);
        }

    }

    @Override
    public void batchUpdate(Integer roadMapId, List<RoadMapSrcAdress> listRoadMapSrcAdress, LoginUser loginUser)
            throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        this.check(listRoadMapSrcAdress);

        this.deleteByRoadMapId(roadMapId, loginUser);
        for (RoadMapSrcAdress r : listRoadMapSrcAdress) {
            r.setRoadMapId(roadMapId);
            if (StringUtils.isBlank(r.getRegionCode())) {
                r.setRegionCode(regionTgmService.findRegionCodeByCoordinate(r.getCoordinates()));
            }
            this.insert(r, loginUser);
        }
    }

    private void check(List<RoadMapSrcAdress> listRoadMapSrcAdress) {
        if (CollectionUtils.isEmpty(listRoadMapSrcAdress)) {
            throw new BusinessException("srcAdressRequired", "roadMapSrcAdress.error.srcAdressRequired");
        }

        // 计数器
        int temp = 1;

        for (RoadMapSrcAdress r : listRoadMapSrcAdress) {
            if (StringUtils.isBlank(r.getAddressName())) {
                throw new BusinessException("addressNameRequired", "roadMapSrcAdress.error.addressNameRequired", temp);
            }

            if (StringUtils.isBlank(r.getAddressDetail())) {
                throw new BusinessException("addressMustSelect", "roadMapSrcAdress.error.addressMustSelect", temp);
            }

            if (StringUtils.isBlank(r.getCoordinates())) {
                throw new BusinessException("addressMustSelect", "roadMapSrcAdress.error.addressMustSelect", temp);
            }

            if (StringUtils.isBlank(r.getContactName())) {
                throw new BusinessException("contactNameRequired", "roadMapSrcAdress.error.contactNameRequired", temp);
            }

            if (StringUtils.isBlank(r.getContactPhone())) {
                throw new BusinessException("contactPhoneRequired", "roadMapSrcAdress.error.contactPhoneRequired",
                        temp);
            }

            temp++;
        }
    }

    @Override
    public void delete(Integer roadMapSrcAdressId, LoginUser loginUser) throws BusinessException {
        if (null == roadMapSrcAdressId) {
            return;
        }

        roadMapSrcAdressMapper.deleteByPrimaryKey(roadMapSrcAdressId);
    }

    @Override
    public void deleteByRoadMapId(Integer roadMapId, LoginUser loginUser) throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        RoadMapSrcAdressExample example = new RoadMapSrcAdressExample();
        example.createCriteria().andRoadMapIdEqualTo(roadMapId);
        roadMapSrcAdressMapper.deleteByExample(example);
    }

    @Override
    public List<RoadMapSrcAdress> listByRoadMapId(Integer roadMapId) {
        if (null == roadMapId) {
            return new ArrayList<RoadMapSrcAdress>();
        }

        RoadMapSrcAdressExample example = new RoadMapSrcAdressExample();
        example.createCriteria().andRoadMapIdEqualTo(roadMapId);
        return roadMapSrcAdressMapper.selectByExample(example);
    }

}
