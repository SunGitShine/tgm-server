package com.juma.tgm.fms.businessModule;

import com.juma.tgm.waybill.domain.Waybill;
import com.juma.tgm.waybill.service.WaybillCommonService;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: ReconciliationWaybillUtils
 * @Description:
 * @author: liang
 * @date: 2018-06-07 14:03
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
@Component
public class ReconciliationWaybillUtils {
    private static final Logger log = LoggerFactory.getLogger(ReconciliationWaybillUtils.class);

    @Autowired
    private WaybillCommonService waybillCommonService;

    /**
     *
     * v2版本将运单的对账状态更改为已对账（老版对账）
     *
     * @param reconciliationId
     */
    @Deprecated
    public void updateWaybillToHasReconciliation(Integer reconciliationId) {
        log.info("开始执行对账操作reconciliationId：{}", reconciliationId);
        if (null == reconciliationId) {
            return;
        }

        List<Waybill> waybills = waybillCommonService.findByReconciliationId(reconciliationId);

        for (Waybill waybill : waybills) {
            if (NumberUtils.compare(Waybill.ReconciliationStatus.NOT_RECONCILIATION.getCode(),
                    waybill.getReconciliationStatus()) == 0) {
                waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
                // 结算状态
                waybill.setSettlementStatus(Waybill.SettlementStatus.NOT_CLEAR.getCode());
                // 收款状态
                waybill.setReceiptStatus(Waybill.ReceiptStatus.NOT_COLLECTION.getCode());
            }
        }

        waybillCommonService.batchUpdateHasReconciliation(waybills);
    }

    /**
     * v2 版本通过对账单号更新运单状态
     * @param reconciliationNo
     */
    @Deprecated
    public void updateWaybillToHasReconciliation(String  reconciliationNo) {
        log.info("开始执行对账操作reconciliationId：{}", reconciliationNo);
        if (null == reconciliationNo) {
            return;
        }

        List<Waybill> waybills = waybillCommonService.findByReconciliationNo(reconciliationNo);

        for (Waybill waybill : waybills) {
            if (NumberUtils.compare(Waybill.ReconciliationStatus.IN_THE_ACCOUNT.getCode(),
                    waybill.getReconciliationStatus()) == 0) {
                waybill.setReconciliationStatus(Waybill.ReconciliationStatus.HAS_RECONCILIATION.getCode());
                // 结算状态
                waybill.setSettlementStatus(Waybill.SettlementStatus.NOT_CLEAR.getCode());
                // 收款状态
                waybill.setReceiptStatus(Waybill.ReceiptStatus.NOT_COLLECTION.getCode());
            }
        }

        waybillCommonService.batchUpdateHasReconciliation(waybills);
    }

}
