package com.juma.tgm.base.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UploadExcelResult.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年1月12日 下午5:30:40
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class UploadExcelResult implements Serializable {

    private static final long serialVersionUID = -6107174808068947067L;
    private Integer count;
    private Integer success;
    private Integer failed;
    private String errorMsg;
    private List<UploadExcelFailedReason> listFailedReason = new ArrayList<UploadExcelFailedReason>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<UploadExcelFailedReason> getListFailedReason() {
        return listFailedReason;
    }

    public void setListFailedReason(List<UploadExcelFailedReason> listFailedReason) {
        this.listFailedReason = listFailedReason;
    }

}
