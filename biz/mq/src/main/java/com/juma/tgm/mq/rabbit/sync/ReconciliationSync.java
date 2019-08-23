package com.juma.tgm.mq.rabbit.sync;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.giants.common.exception.BusinessException;
import com.juma.tgm.fms.service.ReconciliationService;
import com.juma.tgm.waybill.service.WaybillCommonService;

/**
 * @ClassName ReconciliationSync.java
 * @Description MQ运单结算信息
 * @author Libin.Wei
 * @Date 2018年3月28日 上午9:30:06
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Deprecated
@Component
public class ReconciliationSync {

    @Resource
    private ReconciliationService reconciliationService;
    @Resource
    private WaybillCommonService waybillCommonService;

    public void updateToCheckout(String reconciliationNo) {


        throw new BusinessException("useError","没有使用的话请删除");
    }
}
