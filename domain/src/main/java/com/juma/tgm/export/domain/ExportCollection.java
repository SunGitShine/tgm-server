package com.juma.tgm.export.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.juma.tgm.common.DateUtil;

/**
 * @ClassName ExportResponse.java
 * @Description 请填写注释...
 * @author Libin.Wei
 * @Date 2018年5月18日 上午10:38:08
 * @version 1.0.0
 * @Copyright 2016 www.jumapeisong.com Inc. All rights reserved.
 */

public class ExportCollection implements Serializable {

    private Integer id;
    private Integer taskId;
    private Integer status;
    private String fileName;
    private String filePath;
    private Date createTime;
    private Date finishTime;
    private String costDate;
    private String statusName;

    public enum  DownloadTaskStatus {

        TASK_DOING(1,"进行中"),
        TASK_COMPLETE(2,"已完成"),
        TASK_FAIL(3,"失败");

        private Integer taskStatus;
        private String taskEesc;

        DownloadTaskStatus( Integer taskStatus,String taskEesc) {
            this.taskStatus=taskStatus;
            this.taskEesc = taskEesc;
        }

        public Integer getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(Integer taskStatus) {
            this.taskStatus = taskStatus;
        }

        public String getTaskEesc() {
            return taskEesc;
        }

        public void setTaskEesc(String taskEesc) {
            this.taskEesc = taskEesc;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getCostDate() {
        if (StringUtils.isNotBlank(costDate)) {
            return costDate;
        } else if (null != this.createTime && null != this.finishTime) {
            BigDecimal divide = DateUtil.calDate(this.createTime, this.finishTime, DateUtil.TimeUnitEnum.SECOND);
            if (divide.compareTo(new BigDecimal("60")) == -1) {
                return divide + "秒";
            }
            int minute = divide.intValue() / 60;
            return minute + "分钟" + (divide.subtract(new BigDecimal((60 * minute) + ""))) + "秒";
        }
        return costDate;
    }

    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }

    public String getStatusName() {
        if (StringUtils.isNotBlank(statusName)) {
            return statusName;
        } else if (null != this.status) {
            for (DownloadTaskStatus taskStatus : DownloadTaskStatus.values()) {
                if (this.status.equals(taskStatus.getTaskStatus())) {
                    return taskStatus.getTaskEesc();
                }
            }
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
