package com.juma.tgm.fms.domain.v3.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 15:45 2019-05-14
 */
public class AdjustForMasterFilter implements Serializable {
    private Integer adjustId;
    /**调整单编号**/
    private String adjustNo;
    /**运单Id**/
    private Integer waybillId;
    /**运单Id集合**/
    private List<Integer> waybillIds;
    /**主体类型:承运商/客户**/
    private Integer adjustMasterType;
    /**审批状态**/
    private Integer approvalStatus;
    /**调整单Id集合**/
    private List<Integer> adjustIds;
    /**审批状态集合**/
    private List<Integer> approvalStatusList;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(final Integer adjustId) {
        this.adjustId = adjustId;
    }

    public List<Integer> getAdjustIds() {
        return adjustIds;
    }

    public void setAdjustIds(List<Integer> adjustIds) {
        this.adjustIds = adjustIds;
    }

    public String getAdjustNo() {
        return adjustNo;
    }

    public void setAdjustNo(final String adjustNo) {
        this.adjustNo = adjustNo;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(final Integer waybillId) {
        this.waybillId = waybillId;
    }

    public List<Integer> getWaybillIds() {
        return waybillIds;
    }

    public void setWaybillIds(List<Integer> waybillIds) {
        this.waybillIds = waybillIds;
    }

    public Integer getAdjustMasterType() {
        return adjustMasterType;
    }

    public void setAdjustMasterType(final Integer adjustMasterType) {
        this.adjustMasterType = adjustMasterType;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(final Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public List<Integer> getApprovalStatusList() {
        return approvalStatusList;
    }

    public void setApprovalStatusList(final List<Integer> approvalStatusList) {
        this.approvalStatusList = approvalStatusList;
    }
}
