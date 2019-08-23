package com.juma.tgm.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.project.dao.RoadMapDestAdressMapper;
import com.juma.tgm.project.domain.RoadMapDestAdress;
import com.juma.tgm.project.domain.RoadMapDestAdressExample;
import com.juma.tgm.project.service.RoadMapDestAdressService;
import com.juma.tgm.region.service.RegionTgmService;

/**
 * @ClassName RoadMapDestAdressServiceImpl.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年9月28日 上午10:40:30
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Service
public class RoadMapDestAdressServiceImpl implements RoadMapDestAdressService {

    @Resource
    private RoadMapDestAdressMapper roadMapDestAdressMapper;
    @Resource
    private RegionTgmService regionTgmService;

    @Override
    public RoadMapDestAdress getRoadMapDestAdress(Integer roadMapDestAdressId) {
        return null;

    }

    @Override
    public void insert(RoadMapDestAdress roadMapDestAdress, LoginUser loginUser) throws BusinessException {
        roadMapDestAdress.setCreateUserId(loginUser == null ? null : loginUser.getUserId());
        roadMapDestAdress.setCreateTime(new Date());
        roadMapDestAdressMapper.insert(roadMapDestAdress);
    }

    @Override
    public void batchInsert(Integer roadMapId, List<RoadMapDestAdress> listRoadMapDestAdress, LoginUser loginUser)
            throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        this.check(listRoadMapDestAdress);

        for (RoadMapDestAdress r : listRoadMapDestAdress) {
            r.setRoadMapId(roadMapId);
            if (StringUtils.isBlank(r.getRegionCode())) {
                r.setRegionCode(regionTgmService.findRegionCodeByCoordinate(r.getCoordinates()));
            }
            this.insert(r, loginUser);
        }
    }

    @Override
    public void batchUpdate(Integer roadMapId, List<RoadMapDestAdress> listRoadMapDestAdress, LoginUser loginUser)
            throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        this.check(listRoadMapDestAdress);

        this.deleteByRoadMapId(roadMapId, loginUser);
        for (RoadMapDestAdress r : listRoadMapDestAdress) {
            r.setRoadMapId(roadMapId);
            if (StringUtils.isBlank(r.getRegionCode())) {
                String regionCode = regionTgmService.findRegionCodeByCoordinate(r.getCoordinates());
                r.setRegionCode(StringUtils.isBlank(regionCode) ? null
                        : (regionCode.length() > 5 ? regionCode.substring(0, 5) : regionCode));
            }
            this.insert(r, loginUser);
        }
    }

    private void check(List<RoadMapDestAdress> listRoadMapDestAdress) {
        // 计数器
        int temp = 1;

        for (RoadMapDestAdress r : listRoadMapDestAdress) {
            if (StringUtils.isBlank(r.getAddressName())) {
                throw new BusinessException("addressNameRequired", "roadMapDestAdress.error.addressNameRequired", temp);
            }

            if (StringUtils.isBlank(r.getAddressDetail())) {
                throw new BusinessException("addressMustSelect", "roadMapDestAdress.error.addressMustSelect", temp);
            }

            if (StringUtils.isBlank(r.getCoordinates())) {
                throw new BusinessException("addressMustSelect", "roadMapDestAdress.error.addressMustSelect", temp);
            }
            
            if (StringUtils.isNotBlank(r.getContactName()) && StringUtils.isBlank(r.getContactPhone())) {
                throw new BusinessException("contactPhoneRequired", "roadMapDestAdress.error.contactPhoneRequired", temp);
            }

            if (StringUtils.isBlank(r.getContactName()) && StringUtils.isNotBlank(r.getContactPhone())) {
                throw new BusinessException("contactNameRequired", "roadMapDestAdress.error.contactNameRequired", temp);
            }

            temp++;
        }
    }

    @Override
    public void delete(Integer roadMapDestAdressId, LoginUser loginUser) throws BusinessException {
        if (null == roadMapDestAdressId) {
            return;
        }

        roadMapDestAdressMapper.deleteByPrimaryKey(roadMapDestAdressId);
    }

    @Override
    public void deleteByRoadMapId(Integer roadMapId, LoginUser loginUser) throws BusinessException {
        if (null == roadMapId) {
            return;
        }

        RoadMapDestAdressExample example = new RoadMapDestAdressExample();
        example.createCriteria().andRoadMapIdEqualTo(roadMapId);
        roadMapDestAdressMapper.deleteByExample(example);
    }

    @Override
    public List<RoadMapDestAdress> listByRoadMapId(Integer roadMapId) {
        if (null == roadMapId) {
            return new ArrayList<RoadMapDestAdress>();
        }

        RoadMapDestAdressExample example = new RoadMapDestAdressExample();
        example.createCriteria().andRoadMapIdEqualTo(roadMapId);
        return roadMapDestAdressMapper.selectByExample(example);
    }

}
