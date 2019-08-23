package com.juma.tgm.fms.domain.v3.vo;

import com.juma.tgm.fms.domain.v3.ReconcilicationForReceivable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("客户对账单")
public class ReconcilicationForReceivablePageVo extends ReconcilicationForReceivable {

    @ApiModelProperty(value = "是否有对账凭证，0：未上传，1：已上传")
    private Integer hasEvidence;
    @ApiModelProperty(value = "对账单调整金额")
    private BigDecimal adjustForFreight;
    @ApiModelProperty(value = "对账时毛利额")
    private BigDecimal profit;
    @ApiModelProperty(value = "首次对账毛利率")
    private String profitRate;

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(String profitRate) {
        this.profitRate = profitRate;
    }

    public Integer getHasEvidence() {
        return hasEvidence;
    }

    public void setHasEvidence(Integer hasEvidence) {
        this.hasEvidence = hasEvidence;
    }

    public BigDecimal getAdjustForFreight() {
        return adjustForFreight;
    }

    public void setAdjustForFreight(BigDecimal adjustForFreight) {
        this.adjustForFreight = adjustForFreight;
    }
}
