package com.juma.tgm.cms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * export_task - export_task
 * 
 * @author 2017-06-03
 * @version 1.0
 */
public class ExportTask implements Serializable {

    private static final long serialVersionUID = -7535308199194942073L;
    private Integer exportTaskId;
    private Integer taskSign;
    private String name;
    private Integer status;
    private Long costTime;
    private String fileUrl;
    private String failedReson;
    private String remark;
    private String md5Digest;
    private Date createTime;
    private Boolean isDelete;
    private Integer createUserId;

    // 显示冗余
    private String createDate;
    private String statusName;
    private Date startTime;
    private String costDate;

    public Integer getExportTaskId() {
        return exportTaskId;
    }

    public void setExportTaskId(Integer exportTaskId) {
        this.exportTaskId = exportTaskId;
    }

    public Integer getTaskSign() {
        return taskSign;
    }

    public void setTaskSign(Integer taskSign) {
        this.taskSign = taskSign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFailedReson() {
        return failedReson;
    }

    public void setFailedReson(String failedReson) {
        this.failedReson = failedReson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMd5Digest() {
        return md5Digest;
    }

    public void setMd5Digest(String md5Digest) {
        this.md5Digest = md5Digest;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCostDate() {
        return costDate;
    }

    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }

    public enum TaskSign {
        WAYBILL(1, "运单"),
        WAYBILL_REPORT(2, "运单报表"),
        CUSTOMER_INFO(3, "企业货主"),
        CUSTOMER_WECHAT(4, "微信货主"),
        DRIVER_LATE(7, "装货迟到"),
        ACTUAL_MILEAGE_EXCEPTION(8, "实际里程异常"),
        COST_REIMBURSED(9,"费用报销"),
        PRICE_EXCEPTION(10,"价格异常");

        private Integer code;
        private String desc;

        private TaskSign(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum Status {
        DOWN_LOADING(1, "正在导出,请稍等..."), DOWN_FINISH(2, "导出完成"), DOWN_FAILED(3, "导出失败");

        private Integer code;
        private String desc;

        private Status(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByCode(int code) {
            for (Status status : Status.values()) {
                if (code == status.getCode()) {
                    return status.getDesc();
                }
            }
            return null;
        }
    }
}