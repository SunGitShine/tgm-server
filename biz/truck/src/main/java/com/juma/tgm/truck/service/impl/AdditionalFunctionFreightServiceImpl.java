package com.juma.tgm.truck.service.impl;

import com.juma.auth.employee.domain.LoginEmployee;
import com.juma.tgm.truck.dao.AdditionalFunctionFreightDao;
import com.juma.tgm.truck.domain.AdditionalFunctionFreight;
import com.juma.tgm.truck.service.AdditionalFunctionFreightService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11 0011. 符加功能运费service实现
 */
@Service
public class AdditionalFunctionFreightServiceImpl implements AdditionalFunctionFreightService {

    @Autowired
    private AdditionalFunctionFreightDao additionalFunctionFreightDao;

    @Override
    public AdditionalFunctionFreight getAddFuncFreightByTypeAF(Integer freightId, Integer additionalFunctionId) {
        AdditionalFunctionFreight example = new AdditionalFunctionFreight();
        example.setFreightId(freightId);
        example.setAdditionalFunctionId(additionalFunctionId);
        List<AdditionalFunctionFreight> freightList = additionalFunctionFreightDao.findByExample(example);
        if (freightList != null && freightList.size() > 0) {
            return freightList.get(0);
        }
        return null;
    }

    @Override
    public void insert(List<AdditionalFunctionFreight> additionalFunctionFreight, LoginEmployee loginEmployee) {
        Integer createUserId = loginEmployee.getUserId();
        if (CollectionUtils.isNotEmpty(additionalFunctionFreight)) {
            for (AdditionalFunctionFreight addfreight : additionalFunctionFreight) {
                addfreight.setCreateUserId(createUserId);
                additionalFunctionFreightDao.insert(addfreight);
            }
        }
    }

    @Override
    public void update(List<AdditionalFunctionFreight> additionalFunctionFreight, Integer freightId, LoginEmployee loginEmployee) {
        delete(freightId);// 删除truckTypeId对应的所有记录
        if (CollectionUtils.isNotEmpty(additionalFunctionFreight)) {
            insert(additionalFunctionFreight, loginEmployee);
        }
    }

    @Override
    public void delete(Integer freightId) {
        AdditionalFunctionFreight addFreight = new AdditionalFunctionFreight();
        addFreight.setFreightId(freightId);
        additionalFunctionFreightDao.delete(addFreight);
    }

    @Override
    public void update(AdditionalFunctionFreight freight) {
        additionalFunctionFreightDao.update(freight);
    }

    @Override
    public List<AdditionalFunctionFreight> findAddFuncFreightByFreightId(int freightId) {
        AdditionalFunctionFreight example = new AdditionalFunctionFreight();
        example.setFreightId(freightId);
        return additionalFunctionFreightDao.findByExample(example);
    }
}
