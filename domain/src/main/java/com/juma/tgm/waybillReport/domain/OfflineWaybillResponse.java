package com.juma.tgm.waybillReport.domain;

import java.io.Serializable;

public class OfflineWaybillResponse implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1557210064768489308L;

    private int successNum;
    
    private int failureNum;

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(int failureNum) {
        this.failureNum = failureNum;
    }
}
