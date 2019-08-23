package com.juma.tgm.waybill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.dao.WaybillViewHistoryDao;
import com.juma.tgm.waybill.domain.WaybillViewHistory;
import com.juma.tgm.waybill.service.WaybillViewHistoryService;

@Service
public class WaybillViewHistoryServiceImpl implements WaybillViewHistoryService {

    private static final Logger log = LoggerFactory.getLogger(WaybillViewHistoryServiceImpl.class);
    
    @Resource
    private WaybillViewHistoryDao waybillViewHistoryDao;
    
    @Override
    public void save(WaybillViewHistory waybillViewHistory) throws BusinessException {
        try {
            waybillViewHistoryDao.insert(waybillViewHistory);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw new BusinessException("waybill.error.viewHistory", "waybill.error.viewHistory");
        }
    }

    @Override
    public WaybillViewHistory findBy(int waybillId, int driverId) {
        WaybillViewHistory example = new WaybillViewHistory();
        example.setWaybillId(waybillId);
        example.setDriverId(driverId);
        List<WaybillViewHistory> rows = waybillViewHistoryDao.findByExample(example);
        return rows.size() > 0 ? rows.get(0) : null;
    }

}
