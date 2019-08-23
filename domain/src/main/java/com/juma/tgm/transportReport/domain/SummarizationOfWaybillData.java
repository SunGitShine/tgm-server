package com.juma.tgm.transportReport.domain;

import java.io.Serializable;

/**
 * @ClassName SummarizationOfWaybillData.java
 * @Description 运单数据汇总
 * @author Libin.Wei
 * @Date 2018年8月15日 下午2:49:52
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class SummarizationOfWaybillData implements Serializable {

    private String statusViewText;
    private int num = 0;

    public SummarizationOfWaybillData() {
    }

    public SummarizationOfWaybillData(String statusViewText, int num) {
        super();
        this.statusViewText = statusViewText;
        this.num = num;
    }

    public String getStatusViewText() {
        return statusViewText;
    }

    public void setStatusViewText(String statusViewText) {
        this.statusViewText = statusViewText;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SummarizationOfWaybillData [statusViewText=" + statusViewText + ", num=" + num + "]";
    }
}
