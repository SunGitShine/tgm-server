package com.juma.tgm.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "分页结果")
public class Page<T> implements Serializable {

    @ApiModelProperty(value = "页码")
    private int pageNo = 1;

    @ApiModelProperty(value = "每页记录数")
    private int pageSize = 15;

    @ApiModelProperty(value = "总记录数")
    private int total = 0;

    @ApiModelProperty(value = "总页数")
    private int totalPage = 0;

    @ApiModelProperty(value = "每页记录集")
    private List<T> results = new ArrayList<>();


    public Page() {
    }

    public Page(int pageNo, int pageSize, int total, List<T> results) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.results = results;
    }

    public int getTotalPage() {
        if (total % pageSize == 0) {
            totalPage = total/pageSize;
        } else {
            totalPage = total/pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
