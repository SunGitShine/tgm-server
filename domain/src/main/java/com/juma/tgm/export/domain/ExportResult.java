package com.juma.tgm.export.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ExportResult.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年5月18日 上午10:42:37
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ExportResult implements Serializable {

    private Boolean success;
    private String message;
    private Integer count;
    private List<ExportCollection> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ExportCollection> getData() {
        return data;
    }

    public void setData(List<ExportCollection> data) {
        this.data = data;
    }

}
