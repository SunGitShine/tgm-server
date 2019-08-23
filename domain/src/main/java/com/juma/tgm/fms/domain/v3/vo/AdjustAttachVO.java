package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 11:54 2019-05-14
 */
@ApiModel("附件上传附加信息")
public class AdjustAttachVO implements Serializable {

    private Integer adjustId;

    @ApiModelProperty("调整阶段 1：对账前  2：对账后")
    private Integer adjustType;

    @ApiModelProperty("调整主体  1：客户  2：承运商")
    private Integer adjustForWho;

    @ApiModelProperty("Excel文件地址")
    private String attachUrl;

    @ApiModelProperty("对账单号")
    private String reconciliationNo;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(final Integer adjustId) {
        this.adjustId = adjustId;
    }

    public Integer getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(final Integer adjustType) {
        this.adjustType = adjustType;
    }

    public Integer getAdjustForWho() {
        return adjustForWho;
    }

    public void setAdjustForWho(final Integer adjustForWho) {
        this.adjustForWho = adjustForWho;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(final String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getReconciliationNo() {
        return reconciliationNo;
    }

    public void setReconciliationNo(final String reconciliationNo) {
        this.reconciliationNo = reconciliationNo;
    }
}
