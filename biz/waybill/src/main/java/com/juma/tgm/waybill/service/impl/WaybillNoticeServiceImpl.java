package com.juma.tgm.waybill.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.waybill.dao.WaybillNoticeDao;
import com.juma.tgm.waybill.domain.WaybillNotice;
import com.juma.tgm.waybill.service.WaybillNoticeService;

@Service
public class WaybillNoticeServiceImpl implements WaybillNoticeService {

    private static final Logger log = LoggerFactory.getLogger(WaybillNoticeServiceImpl.class);
    
    @Resource
    private WaybillNoticeDao waybillNoticeDao;
    
    @Override
    public void save(WaybillNotice waybillNotice) throws BusinessException {
        try {
            waybillNoticeDao.insert(waybillNotice);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw new BusinessException("waybill.error.notice", "waybill.error.notice");
        }
        
    }

    @Override
    public WaybillNotice findBy(int waybillId) {
        WaybillNotice example = new WaybillNotice();
        example.setWaybillId(waybillId);
        List<WaybillNotice> rows = waybillNoticeDao.findByExample(example);
        return rows.size() > 0 ? rows.get(0) : null;
    }

}
