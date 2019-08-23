package com.juma.tgm.mq.rabbit.sync;

import com.juma.auth.tenant.domain.Tenant;
import com.juma.auth.tenant.service.TenantService;
import com.juma.tgm.fms.domain.v3.enums.PayableSettleAccountTypeEnum;
import com.juma.tgm.fms.service.v3.ReconcilicationForPayableService;
import com.juma.tgm.vendor.service.VendorMappingService;
import com.juma.vms.external.service.VmsService;
import com.juma.vms.vendor.domain.Vendor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName VendorMappingSync.java
 * @Description 承运商信息同步
 * @author Libin.Wei
 * @Date 2018年8月29日 下午2:50:07
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

@Component
public class VendorMappingSync {

    @Resource
    private VendorMappingService vendorMappingService;
    @Resource
    private TenantService tenantService;
    @Resource
    private VmsService vmsService;
    @Resource
    private ReconcilicationForPayableService reconcilicationForPayableService;

    public void modify(Integer vendorId, Integer tenantId) {
        if (null == vendorId) {
            return;
        }

        Tenant tenant = tenantService.loadTenant(tenantId);
        if (null == tenant) {
            return;
        }

        Vendor vendor = vmsService.loadByVenorIdTenant(vendorId, tenantId);
        if (null == vendor) {
            return;
        }

        if (null != vendor.getIsDelete() && vendor.getIsDelete() == (byte) 1) {
            vendorMappingService.deleteByVendorId(vendorId, null);
            return;
        }

        // 修改承租商名称
        vendorMappingService.updateVendorName(vendorId, vendor.getVendorName());

        this.updateReconcilicationForPayableItemSettleAccountName(vendorId, vendor.getVendorName());
    }

    // 修改应付对账承运商名称
    private void updateReconcilicationForPayableItemSettleAccountName(Integer vendorId, String vendorName) {
        reconcilicationForPayableService.updateNameBySettleAccountIdAndType(vendorName, vendorId,
                PayableSettleAccountTypeEnum.VENDOR.getCode());
    }
}
