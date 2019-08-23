package com.juma.tgm.mq.rabbit.sync;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.juma.crm.customer.domain.MigrateCustomerMq;
import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.crm.service.CustomerInfoService;

/**
 * 
 * @ClassName CustomerInfoDataMoveSync.java 更新企业客户表信息
 * @author Libin.Wei
 * @Date 2018年1月18日 上午9:38:41
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Component
public class CustomerInfoDataMoveSync {

    private final Logger log = LoggerFactory.getLogger(CustomerInfoDataMoveSync.class);
    @Resource
    private CustomerInfoService customerInfoService;

    public void update(MigrateCustomerMq mq) {
        if (null != mq.getCustomerIds() && mq.getCustomerIds().length > 0) {
            this.moveAreaAndManagerById(mq);
        } else {
            this.moveByAreaOrManager(mq);
        }
    }

    // 根据企业客户ID迁移到新的业务区域和客户经理
    private void moveAreaAndManagerById(MigrateCustomerMq mq) {
        List<CustomerInfo> example = new ArrayList<CustomerInfo>();
        Integer[] customerIds = mq.getCustomerIds();
        for (Integer customerId : customerIds) {
            CustomerInfo info = new CustomerInfo();
            info.setAreaCode(mq.getToAreaCode());
            info.setCustomerManagerUserId(mq.getToCustomerManagerId());
            info.setCrmCustomerId(customerId);
            example.add(info);
        }

        customerInfoService.updateBatch(example, null);
    }

    // 根据业务区域或客户经理迁移
    private void moveByAreaOrManager(MigrateCustomerMq mq) {
        if (null == mq.getTenantId()) {
            log.info("crm数据迁移同步没有租户ID，不能完成同步迁移");
            return;
        }

        if (null == mq.getFromAreaCode() && null == mq.getFromCustomerManagerId()) {
            log.info("crm数据迁移同步没有原业务区域且没有原客户经理，不能完成同步迁移");
            return;
        }

        if (null == mq.getCustomerType() || com.juma.crm.customer.domain.CustomerInfo.CustomerType.CONSIGNOR
                .getValue().compareTo(mq.getCustomerType()) != 0) {
            log.info("crm数据迁移同步发送的是司机数据，不能完成同步迁移");
            return;
        }

        CustomerInfo to = new CustomerInfo();
        to.setAreaCode(mq.getToAreaCode());
        to.setCustomerManagerUserId(mq.getToCustomerManagerId());

        CustomerInfo from = new CustomerInfo();
        from.setAreaCode(mq.getFromAreaCode());
        from.setCustomerManagerUserId(mq.getFromCustomerManagerId());
        from.setTenantId(mq.getTenantId());

        customerInfoService.update(to, from);
    }
}
