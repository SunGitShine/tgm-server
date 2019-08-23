package com.juma.tgm.transportReport.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TransportReportSms.java
 * @Description 运输报告
 * @author Libin.Wei
 * @Date 2018年8月13日 下午3:36:45
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class TransportReport implements Serializable {

    private List<SummarizationOfWaybillData> listSumWaybillData = new ArrayList<SummarizationOfWaybillData>();
    private List<WaybillSummary> listWaybillSummary = new ArrayList<WaybillSummary>();
    private String customerServiceTel;

    private Integer pageNo;
    private Integer pageSize;
    private Integer total;
    private Integer totalPage = 1;

    public List<SummarizationOfWaybillData> getListSumWaybillData() {
        return listSumWaybillData;
    }

    public void setListSumWaybillData(List<SummarizationOfWaybillData> listSumWaybillData) {
        this.listSumWaybillData = listSumWaybillData;
    }

    public List<WaybillSummary> getListWaybillSummary() {
        return listWaybillSummary;
    }

    public void setListWaybillSummary(List<WaybillSummary> listWaybillSummary) {
        this.listWaybillSummary = listWaybillSummary;
    }

    public String getCustomerServiceTel() {
        return customerServiceTel;
    }

    public void setCustomerServiceTel(String customerServiceTel) {
        this.customerServiceTel = customerServiceTel;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        if (null == this.total || null == this.pageSize) {
            return totalPage;
        }

        if (this.total.compareTo(this.pageSize) <= 0) {
            return totalPage;
        }

        return new BigDecimal(this.total + "").divide(new BigDecimal(this.pageSize + ""), BigDecimal.ROUND_UP)
                .intValue();
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
