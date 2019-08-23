package com.juma.tgm.fms.domain.v2.vo;

import com.juma.tgm.fms.domain.v2.ReconciliationItemNew;
import com.juma.tgm.fms.domain.v2.enums.ReconciliationEnums;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;

/**
 * 车辆对账明细
 *
 * @ClassName: ReconciliationForVehicleVo
 * @Description:
 * @author: liang
 * @date: 2018-06-05 20:53
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationForVehicleVo implements Serializable {
    private ReconciliationItemNew reconciliationItemNew;

    public ReconciliationItemNew getReconciliationItemNew() {
        return reconciliationItemNew;
    }

    public void setReconciliationItemNew(ReconciliationItemNew reconciliationItemNew) {
        this.reconciliationItemNew = reconciliationItemNew;
    }


    /**
     * 结算状态名称
     *
     * @return
     */
    public String getPayStatusName() {
        if (this.reconciliationItemNew == null) return null;
        if (this.reconciliationItemNew.getPayStatus() == null)
            return ReconciliationEnums.VehiclePayStatus.NONE.getDesc();

        if (NumberUtils.compare(this.reconciliationItemNew.getPayStatus(), ReconciliationEnums.VehiclePayStatus.NONE.getCode()) == 0)
            return ReconciliationEnums.VehiclePayStatus.NONE.getDesc();

        if (NumberUtils.compare(this.reconciliationItemNew.getPayStatus(), ReconciliationEnums.VehiclePayStatus.ALREADY_PAY.getCode()) == 0) {
            return ReconciliationEnums.VehiclePayStatus.ALREADY_PAY.getDesc();
        }
        if (NumberUtils.compare(this.reconciliationItemNew.getPayStatus(), ReconciliationEnums.VehiclePayStatus.PRE_PAY.getCode()) == 0) {
            return ReconciliationEnums.VehiclePayStatus.PRE_PAY.getDesc();

        }
        return null;
    }
}
