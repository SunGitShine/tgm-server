package com.juma.tgm.project.vo;

import com.juma.fms.costManage.domain.ApplicationFeeBillBo;
import com.juma.fms.costManage.domain.ReimbursementBillBo;
import com.juma.tgm.fms.domain.v2.vo.ReconciliationVo;
import com.juma.tgm.project.domain.Project;

import java.io.Serializable;
import java.util.List;


public class ProjectDataStatisticsVo extends Project implements Serializable {

    /**
     * 对账信息
     */
    private List<ReconciliationVo> reconciliationVoList;
    /**
     * 请款信息
     */
    private List<ApplicationFeeBillBo> applicationFeeBillBoList;

    /**
     * 报销信息
     */
    private List<ReimbursementBillBo> reimbursementBillBoList;

    public List<ReconciliationVo> getReconciliationVoList() {
        return reconciliationVoList;
    }

    public void setReconciliationVoList(List<ReconciliationVo> reconciliationVoList) {
        this.reconciliationVoList = reconciliationVoList;
    }

    public List<ApplicationFeeBillBo> getApplicationFeeBillBoList() {
        return applicationFeeBillBoList;
    }

    public void setApplicationFeeBillBoList(List<ApplicationFeeBillBo> applicationFeeBillBoList) {
        this.applicationFeeBillBoList = applicationFeeBillBoList;
    }

    public List<ReimbursementBillBo> getReimbursementBillBoList() {
        return reimbursementBillBoList;
    }

    public void setReimbursementBillBoList(List<ReimbursementBillBo> reimbursementBillBoList) {
        this.reimbursementBillBoList = reimbursementBillBoList;
    }
}
