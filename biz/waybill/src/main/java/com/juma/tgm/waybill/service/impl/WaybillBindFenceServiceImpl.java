package com.juma.tgm.waybill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.user.domain.LoginUser;
import com.juma.tgm.waybill.dao.WaybillBindFenceDao;
import com.juma.tgm.waybill.domain.WaybillBindFence;
import com.juma.tgm.waybill.service.WaybillBindFenceService;

@Service
public class WaybillBindFenceServiceImpl implements WaybillBindFenceService {

    @Resource
    private WaybillBindFenceDao waybillBindFenceDao;

    @Override
    public Page<WaybillBindFence> search(PageCondition pageCondition) {
        int total = waybillBindFenceDao.searchCount(pageCondition);
        List<WaybillBindFence> rows = waybillBindFenceDao.search(pageCondition);
        return new Page<WaybillBindFence>(pageCondition.getPageNo(), pageCondition.getPageSize(), total, rows);
    }

    @Override
    public WaybillBindFence getWaybillBindFence(Integer waybillBindFenceId) {
        return waybillBindFenceDao.get(waybillBindFenceId);
    }
    
    @Override
    public WaybillBindFence getWaybillBindFenceForUpdate(Integer waybillBindFenceId) {
        return waybillBindFenceDao.getForUpdate(waybillBindFenceId);
    }

    @Override
    public List<WaybillBindFence> listByAddressIdAndWaybillId(Integer addressId, Integer waybillId) {
        WaybillBindFence example = new WaybillBindFence();
        example.setWaybillId(waybillId);
        example.setAddressId(addressId);
        return waybillBindFenceDao.findByExample(example);
    }

    @Override
    public WaybillBindFence findByFenceId(Integer fenceId) {
        WaybillBindFence example = new WaybillBindFence();
        example.setFenceId(fenceId);
        List<WaybillBindFence> list = waybillBindFenceDao.findByExample(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
    @Override
    public WaybillBindFence findByFenceIdForUpdate(Integer fenceId) {
        WaybillBindFence example = new WaybillBindFence();
        example.setFenceId(fenceId);
        List<WaybillBindFence> list = waybillBindFenceDao.findByExampleForUpdate(example);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<WaybillBindFence> listByWaybillIdAndSign(Integer waybillId, Integer sign) {
        WaybillBindFence example = new WaybillBindFence();
        example.setWaybillId(waybillId);
        example.setSign(sign);
        return waybillBindFenceDao.findByExample(example);
    }

    @Override
    public List<WaybillBindFence> listByWaybillId(Integer waybillId) {
        WaybillBindFence example = new WaybillBindFence();
        example.setWaybillId(waybillId);
        return waybillBindFenceDao.findByExample(example);
    }
    
    @Override
    public List<WaybillBindFence> listByWaybillIdForUpdate(Integer waybillId) {
        WaybillBindFence example = new WaybillBindFence();
        example.setWaybillId(waybillId);
        return waybillBindFenceDao.findByExampleForUpdate(example);
    }
    
    @Override
    public int findReceivePointNo(Integer waybillId, Integer status) {
        if (null != waybillId && null != status) {
            WaybillBindFence waybillBindFence = new WaybillBindFence();
            waybillBindFence.setWaybillId(waybillId);
            waybillBindFence.setStatus(status);
            return waybillBindFenceDao.findReceivePointNo(waybillBindFence);
        }
        return 0;
    }

    @Override
    public void insert(WaybillBindFence waybillBindFence, LoginUser loginUser) {
        waybillBindFence.setCreateUserId(loginUser == null ? null : loginUser.getUserId());
        waybillBindFenceDao.insert(waybillBindFence);
    }

    @Override
    public void update(WaybillBindFence waybillBindFence, LoginUser loginUser) {
        waybillBindFence.setLastUpdateUserId(loginUser == null ? null : loginUser.getUserId());
        waybillBindFenceDao.update(waybillBindFence);
    }

    @Override
    public void updateFenceToInvalid(Integer waybillId, LoginUser loginUser) {
        WaybillBindFence waybillBindFence = new WaybillBindFence();
        waybillBindFence.setWaybillId(waybillId);
        waybillBindFence.setStatus(WaybillBindFence.Status.INVALID.getCode());
        waybillBindFence.setLastUpdateUserId(loginUser == null ? null : loginUser.getUserId());
        waybillBindFenceDao.updateByWaybillId(waybillBindFence);
    }

    @Override
    public WaybillBindFence findByFenceReach(Integer waybillId) {
        WaybillBindFence example = new WaybillBindFence();
        example.setWaybillId(waybillId);
        example.setSign(WaybillBindFence.Sign.DELIVERY_ADDRESS.getCode());
        example.setStatus(WaybillBindFence.Status.TRUCK_ENTRY_INVALID.getCode());
        List<WaybillBindFence> rows =  waybillBindFenceDao.findByExample(example);
        return rows.isEmpty() ? null : rows.get(0);
    }

}
