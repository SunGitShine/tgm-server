package com.juma.tgm.mq.rabbit.sync;

import com.juma.tgm.crm.service.RemedySyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Deprecated
@Service
public class RemedySyncServiceImpl implements RemedySyncService {

    private final Logger log = LoggerFactory.getLogger(RemedySyncServiceImpl.class);

    @Resource
    private CustomerInfoSync customerInfoSync;

    @Override
    public void doSync(Integer crmCustomerId, Integer tenantId) {
        if (null == crmCustomerId || null == tenantId) {
            return;
        }

        log.info("crmCustomerId:{},已调用同步方法");
        customerInfoSync.insertOrUpdate(crmCustomerId);
    }
}
