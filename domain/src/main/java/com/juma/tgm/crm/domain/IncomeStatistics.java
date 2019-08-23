package com.juma.tgm.crm.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.juma.tgm.base.domain.BaseDomain;

/**
 * @ClassName IncomeStatistics.java
 * @Description 收入统计
 * @author Libin.Wei
 * @Date 2016年12月26日 下午4:13:12
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class IncomeStatistics extends BaseDomain {

    private static final long serialVersionUID = 5195223920003126333L;
    private Integer statisticsId;
    private Integer driverId;
    private String statisticsKey;
    private String statisticsName;
    private BigDecimal statisticsCount;
    private BigDecimal rankPercentage;
    private Integer orderNo;
    private Date createTime;

    public Integer getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getStatisticsKey() {
        return statisticsKey;
    }

    public void setStatisticsKey(String statisticsKey) {
        this.statisticsKey = statisticsKey;
    }

    public String getStatisticsName() {
        return statisticsName;
    }

    public void setStatisticsName(String statisticsName) {
        this.statisticsName = statisticsName;
    }

    public BigDecimal getStatisticsCount() {
        return statisticsCount;
    }

    public void setStatisticsCount(BigDecimal statisticsCount) {
        this.statisticsCount = statisticsCount;
    }

    public BigDecimal getRankPercentage() {
        return rankPercentage;
    }

    public void setRankPercentage(BigDecimal rankPercentage) {
        this.rankPercentage = rankPercentage;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
