package com.juma.tgm.base.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.giants.common.tools.PageCondition;

/**
 * @ClassName Paging.java
 * @Description 分页计算
 * @author Libin.Wei
 * @Date 2017年11月30日 下午5:22:42
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class Paging implements Serializable {

    private static final long serialVersionUID = -3231583746462754422L;
    private Integer pageNo = 0;
    private Integer startOffSet = 0;
    private Integer pageSize = 10;
    private String orderBy;
    private String orderSort;
    private String order;

    public Paging() {
    }

    public Paging(PageCondition pageCondition, String defaultOrderBy, String defaultOrderSort) {
        super();
        this.pageNo = pageCondition.getPageNo() == null ? 0
                : (pageCondition.getPageNo() > 0 ? (pageCondition.getPageNo() - 1) : pageCondition.getPageNo());
        this.pageSize = pageCondition.getPageSize() == null ? 10 : pageCondition.getPageSize();
        this.orderBy = StringUtils.isBlank(pageCondition.getOrderBy()) ? defaultOrderBy : pageCondition.getOrderBy();
        this.orderSort = StringUtils.isBlank(pageCondition.getOrderSort())
                ? (StringUtils.isBlank(defaultOrderSort) ? "DESC" : defaultOrderSort) : pageCondition.getOrderSort();
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStartOffSet() {
        return pageNo * pageSize;
    }

    public void setStartOffSet(Integer startOffSet) {
        this.startOffSet = startOffSet;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderSort() {
        return StringUtils.isBlank(orderSort) ? "DESC" : orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }

    public String getOrder() {
        return StringUtils.isBlank(orderBy) ? null : (orderBy + " " + orderSort);
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public static void main(String[] args) {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageNo(1);
        pageCondition.setPageSize(20);
        Paging paging = new Paging(pageCondition, "create_time", "desc");
        System.out.println(paging);
        
    }
    
}
