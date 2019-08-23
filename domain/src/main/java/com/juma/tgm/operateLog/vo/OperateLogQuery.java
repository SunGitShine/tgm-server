package com.juma.tgm.operateLog.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.juma.tgm.operateLog.domain.OperateLog;
import com.juma.tgm.operateLog.enumeration.OperateApplicatoinEnum;
import com.juma.tgm.operateLog.enumeration.OperateTypeEnum;

/**
 * @ClassName OperateLogQuery.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年10月30日 上午10:56:04
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class OperateLogQuery implements Serializable {

    private OperateLog operateLog;
    private String operateTypeDesc;
    private String operateApplicatoinDesc;
    private String operateUserName;

    public OperateLog getOperateLog() {
        return operateLog;
    }

    public void setOperateLog(OperateLog operateLog) {
        this.operateLog = operateLog;
    }

    public String getOperateTypeDesc() {
        if (StringUtils.isNotBlank(this.operateTypeDesc)) {
            return operateTypeDesc;
        }

        if (null == operateLog) {
            return operateTypeDesc;
        }

        return OperateTypeEnum.getTypeDesc(operateLog.getOperateType());
    }

    public void setOperateTypeDesc(String operateTypeDesc) {
        this.operateTypeDesc = operateTypeDesc;
    }

    public String getOperateApplicatoinDesc() {
        if (StringUtils.isNotBlank(this.operateApplicatoinDesc)) {
            return operateApplicatoinDesc;
        }

        if (null == operateLog) {
            return operateApplicatoinDesc;
        }

        return OperateApplicatoinEnum.getApplicatoinDesc(operateLog.getOperateApplicatoin());
    }

    public void setOperateApplicatoinDesc(String operateApplicatoinDesc) {
        this.operateApplicatoinDesc = operateApplicatoinDesc;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

}
