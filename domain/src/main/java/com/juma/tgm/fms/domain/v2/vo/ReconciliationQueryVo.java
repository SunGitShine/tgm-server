package com.juma.tgm.fms.domain.v2.vo;

import com.juma.tgm.fms.domain.v2.ReconciliationNew;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 对账单搜索查询vo
 * @ClassName: ReconciliationQueryVo
 * @Description:
 * @author: liang
 * @date: 2018-06-05 20:32
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class ReconciliationQueryVo extends ReconciliationNew implements Serializable {
    /**
     * 开始配送时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 是否已开票
     */
    private Boolean hasInvoice;

    /**
     * 业务区域list
     */
    private List<String> areaCodeList;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(Boolean hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    public List<String> getAreaCodeList() {
        return areaCodeList;
    }

    public void setAreaCodeList(List<String> areaCodeList) {
        this.areaCodeList = areaCodeList;
    }
}
