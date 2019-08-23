package com.juma.tgm.fms.domain.v3.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 18:38 2019-05-10
 */
@ApiModel("运单临时数据集搜索")
public class AdjustForWaybillTempVO implements Serializable {

    private Integer adjustId;

    public Integer getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(final Integer adjustId) {
        this.adjustId = adjustId;
    }
}
