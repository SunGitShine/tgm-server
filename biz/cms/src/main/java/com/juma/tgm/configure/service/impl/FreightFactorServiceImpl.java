package com.juma.tgm.configure.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.configure.dao.FreightFactorMapper;
import com.juma.tgm.configure.domain.FreightFactor;
import com.juma.tgm.configure.domain.FreightFactorExample;
import com.juma.tgm.configure.domain.TenantFreightFactor;
import com.juma.tgm.configure.service.FreightFactorService;
import com.juma.tgm.configure.service.PrivateFreightFactorService;

@Service
public class FreightFactorServiceImpl implements FreightFactorService {

    @Resource
    private FreightFactorMapper freightFactorMapper;
    
    @Resource
    private PrivateFreightFactorService privateFreightFactorService;

    @Override
    public void save(FreightFactor domain, LoginUser user) throws BusinessException {

        FreightFactor factor = findByLabelNameOrLabelInputName(domain.getLabelName(), domain.getLabelInputName());
        if (domain.getFreightFactorId() == null) {
            if(factor != null) {
                throw new BusinessException("tgm.configure.freight.factor.repeat", "tgm.configure.freight.factor.repeat");
            }
            domain.setCreateTime(new Date());
            domain.setCreateUserId(user.getUserId());
            domain.setIsEnable((byte)1);
            freightFactorMapper.insertSelective(domain);
        } else {
            if(factor != null && !factor.getFreightFactorId().equals(domain.getFreightFactorId())) {
                throw new BusinessException("tgm.configure.freight.factor.repeat", "tgm.configure.freight.factor.repeat");
            }
            domain.setLastUpdateUserId(user.getUserId());
            domain.setLastUpdateTime(new Date());
            freightFactorMapper.updateByPrimaryKeySelective(domain);
        }
    }

    private FreightFactor findByLabelNameOrLabelInputName(String labelName,String labelInputNam) {
        FreightFactorExample example = new FreightFactorExample();
        FreightFactorExample.Criteria criteria = example.createCriteria();
        criteria.andLabelNameEqualTo(labelName);
        
        FreightFactorExample.Criteria criteria2 = example.createCriteria();
        criteria2.andLabelInputNameEqualTo(labelInputNam);
        
        example.or(criteria2);
        List<FreightFactor> rows = freightFactorMapper.selectByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
        
    }

    @Override
    public void disable(Integer freightFactorId, LoginUser user) throws BusinessException {
        FreightFactor domain = new FreightFactor();
        domain.setFreightFactorId(freightFactorId);
        domain.setLastUpdateUserId(user.getUserId());
        domain.setLastUpdateTime(new Date());
        domain.setIsEnable((byte)0);
        freightFactorMapper.updateByPrimaryKeySelective(domain);
    }

    @Override
    public Page<FreightFactor> getPager(Integer freightWay,PageCondition condition,LoginUser user) {
        
        FreightFactorExample example = new FreightFactorExample();
        
        example.setSize(condition.getPageSize());
        
        example.setStartOffSet(condition.getPageNo() < 1 ? 0:(condition.getPageNo()-1)*condition.getPageSize());

        long total = freightFactorMapper.countByExample(example);

        List<FreightFactor> results = freightFactorMapper.selectByExample(example);

        Map<Integer,Integer> tffMap = new HashMap<Integer,Integer>();
        
        TenantFreightFactor tff = privateFreightFactorService.findByFreightWay(freightWay, user);
        if(tff != null && StringUtils.isNotBlank(tff.getFactorJson())) {
            List<FreightFactor> tffList = JSON.parseArray(tff.getFactorJson(), FreightFactor.class);
            for(FreightFactor ff : tffList){
                tffMap.put(ff.getFreightFactorId(), ff.getFreightFactorId());
            }
        }
        
        for(FreightFactor  ff : results) {
            if(tffMap.containsKey(ff.getFreightFactorId())) {
                ff.set_checked(true);
            }
        }
        
        return new Page<FreightFactor>(condition.getPageNo(), condition.getPageSize(), new Long(total).intValue(), results);
    }

    @Override
    public List<FreightFactor> findAll() {
        FreightFactorExample example = new FreightFactorExample();
        return freightFactorMapper.selectByExample(example);
    }


    @Override
    public Map<String,FreightFactor> mapInputNameToValue() {
        Map<String, FreightFactor> map = new HashMap<String,FreightFactor>();
        for(FreightFactor factor : findAll()) {
            map.put(factor.getLabelInputName(), factor);
        }
        return map;
    }

    @Override
    public List<FreightFactor> findByFreightWay(Integer freightWay,LoginUser user) {
        List<FreightFactor> tffList = new ArrayList<FreightFactor>();
        TenantFreightFactor tff = privateFreightFactorService.findByFreightWay(freightWay, user);
        if(tff != null && StringUtils.isNotBlank(tff.getFactorJson())) {
            tffList = JSON.parseArray(tff.getFactorJson(), FreightFactor.class);
        }
        Collections.sort(tffList);
        return tffList;
    }

}
