package com.juma.tgm.mq.domain;

import java.io.Serializable;

/**
 * 功能 : 
 *
 * @author : Bruce(刘正航) 15:35 2019-05-28
 */
public class AdjustToInvoiceEvent implements Serializable {
    /**来源单号（物流传对账单号）**/
    private String sourceDocumentNo;
    /**调整金额（有负号必须带过来）**/
    private String adjustmentAmount;

    public String getSourceDocumentNo() {
        return sourceDocumentNo;
    }

    public void setSourceDocumentNo(final String sourceDocumentNo) {
        this.sourceDocumentNo = sourceDocumentNo;
    }

    public String getAdjustmentAmount() {
        return adjustmentAmount;
    }

    public void setAdjustmentAmount(final String adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }
}
