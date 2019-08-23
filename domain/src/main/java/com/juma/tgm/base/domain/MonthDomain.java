package com.juma.tgm.base.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 月份
 * @author weilibin
 * @date 2016年8月5日 下午2:29:08
 * @version V1.0
 */

public class MonthDomain implements Serializable {

    private static final long serialVersionUID = 2890281871470695549L;
    private Integer month;
    private String descr;
    private String date;
    private List<MonthDomain> monthList;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MonthDomain> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<MonthDomain> monthList) {
        this.monthList = monthList;
    }

}
