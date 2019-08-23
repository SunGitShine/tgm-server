package com.juma.tgm.fms.domain.v2.vo;

import com.juma.tgm.crm.domain.CustomerInfo;
import com.juma.tgm.fms.domain.Reconciliation;
import com.juma.tgm.fms.domain.v2.ReconciliationNew;
import com.juma.tgm.fms.domain.v2.enums.ReconciliationEnums;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @ClassName: ReconciliationVo
 * @Description:
 * @author: liang
 * @date: 2018-06-05 18:20
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationVo implements Serializable {

    /**
     * 对账凭证状态
     */
    public enum EvidenceStatus {
        ALREADY_UPLOAD,NONE;
    }


    private ReconciliationNew reconciliationNew;
    /**
     * 客户信息
     */
    private CustomerInfo customerInfo;

    /**
     * 对账凭证
     */
    private String evidenceStatus;


    public ReconciliationNew getReconciliationNew() {
        return reconciliationNew;
    }

    public void setReconciliationNew(ReconciliationNew reconciliationNew) {
        this.reconciliationNew = reconciliationNew;
    }

    public String getEvidenceStatus() {
        return evidenceStatus;
    }

    public void setEvidenceStatus(String evidenceStatus) {
        this.evidenceStatus = evidenceStatus;
    }

    /**
     * 审核状态名称
     *
     * @return
     */
    public String getReconciliationStatusName() {
        if (this.reconciliationNew == null) return null;

        if (this.reconciliationNew.getReconciliationStatus() == null) return null;

        for (Reconciliation.ReconciliationStatus c : Reconciliation.ReconciliationStatus.values()) {
            if (c.getCode() == this.reconciliationNew.getReconciliationStatus()) return c.getMsg();
        }

        return null;
    }


    /**
     * 运费收款状态
     *
     * @return
     */
    public String getReceiveStatusName() {
        if (this.reconciliationNew == null) return null;
        if (this.reconciliationNew.getReceiveStatus() == null) return null;

        ReconciliationEnums.ShipperPayStatus sps = ReconciliationEnums.ShipperPayStatus.getByCode(this.reconciliationNew.getReceiveStatus());

        if (sps == null) return null;

        return sps.getDesc();
    }

    /**
     * 对账凭证
     *
     * @return
     */
    public String getEvidenceStatusName() {
        if(StringUtils.equals(this.getEvidenceStatus(), EvidenceStatus.NONE.name())) {
            return "未上传";
        }else if(StringUtils.equals(this.getEvidenceStatus(), EvidenceStatus.ALREADY_UPLOAD.name())){
            return "已上传";
        }

        return null;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
