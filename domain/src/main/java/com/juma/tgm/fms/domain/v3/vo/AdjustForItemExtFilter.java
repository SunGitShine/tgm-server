package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 11:09 2019-05-16
 */
@ApiModel("调整单详情搜索")
public class AdjustForItemExtFilter implements Serializable {

    @ApiModelProperty("承运商ID")
    private Integer vendorId;
    @ApiModelProperty("运单ID")
    private Integer waybillId;
    @ApiModelProperty("审核状态")
    private Integer approvalStatus;
    @ApiModelProperty("调整阶段")
    private Integer adjustType;
    @ApiModelProperty("调整主体")
    private Integer adjustForWho;
    @ApiModelProperty("对账单号")
    private String reconcilicationNo;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Integer getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(Integer adjustType) {
        this.adjustType = adjustType;
    }

    public Integer getAdjustForWho() {
        return adjustForWho;
    }

    public void setAdjustForWho(Integer adjustForWho) {
        this.adjustForWho = adjustForWho;
    }

    public String getReconcilicationNo() {
        return reconcilicationNo;
    }

    public void setReconcilicationNo(String reconcilicationNo) {
        this.reconcilicationNo = reconcilicationNo;
    }
}
