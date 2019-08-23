package com.juma.tgm.driver.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Driver implements Serializable {

    private static final long serialVersionUID = 8005453193941847244L;
    private Integer driverId;
    private Integer amsDriverId;
    private Integer userId;
    private String nickname;
    private String contactPhone;
    private Integer employeeClass;
    private Integer status;
    private Integer taskStatus;
    private Integer type;
    private Integer whetherAcceptAllocateOrder;
    private Integer telRemindSwitch;
    private Integer smsRemindSwitch;
    private String headPortrait;
    // 搬运能力
    @Deprecated
    private String handlingCapacity;
    // 搬运意向
    @Deprecated
    private String handlingIntention;
    @Deprecated
    private String driverQualification;
    @Deprecated
    private String tags;
    // 备注
    private String remark;
    // 设备号
    private String deviceNo;
    // 设备类型
    @Deprecated
    private Integer deviceType;
    private Boolean isDelete;
    private Date createTime;
    private Integer createUserId;
    private Integer lastUpdateUserId;
    private Date lastUpdateTime;

    /** 平均分 */
    private float score;

    public enum AcceptAllocateOrders {
        BUSY(0, "不可用"), FREE(1, "可用");

        private AcceptAllocateOrders(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        private Integer code;
        private String value;

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum RemindSwitchType {
        TEL, SMS;
    }

    public enum RemindSwitchValue {
        OFF(0, "关闭"), ON(1, "开启");

        private RemindSwitchValue(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        private Integer code;
        private String value;

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getAmsDriverId() {
        return amsDriverId;
    }

    public void setAmsDriverId(Integer amsDriverId) {
        this.amsDriverId = amsDriverId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getEmployeeClass() {
        return employeeClass;
    }

    public void setEmployeeClass(Integer employeeClass) {
        this.employeeClass = employeeClass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWhetherAcceptAllocateOrder() {
        return whetherAcceptAllocateOrder;
    }

    public void setWhetherAcceptAllocateOrder(Integer whetherAcceptAllocateOrder) {
        this.whetherAcceptAllocateOrder = whetherAcceptAllocateOrder;
    }

    public Integer getTelRemindSwitch() {
        return telRemindSwitch;
    }

    public void setTelRemindSwitch(Integer telRemindSwitch) {
        this.telRemindSwitch = telRemindSwitch;
    }

    public Integer getSmsRemindSwitch() {
        return smsRemindSwitch;
    }

    public void setSmsRemindSwitch(Integer smsRemindSwitch) {
        this.smsRemindSwitch = smsRemindSwitch;
    }

    public String getHandlingCapacity() {
        return handlingCapacity;
    }

    public void setHandlingCapacity(String handlingCapacity) {
        this.handlingCapacity = handlingCapacity;
    }

    public String getHandlingIntention() {
        return handlingIntention;
    }

    public void setHandlingIntention(String handlingIntention) {
        this.handlingIntention = handlingIntention;
    }

    public String getDriverQualification() {
        return driverQualification;
    }

    public void setDriverQualification(String driverQualification) {
        this.driverQualification = driverQualification;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    // 任务状态
    public enum TaskStatus {
        ABLE(2, "空闲"), DISPATCHING(3, "配送中");

        private Integer code;
        private String descr;

        private TaskStatus(int code, String descr) {
            this.code = code;
            this.descr = descr;
        }

        public Integer getCode() {
            return code;
        }

        public String getDescr() {
            return descr;
        }

        private static final Map<Integer, TaskStatus> intToEnum = new HashMap<Integer, TaskStatus>();

        static {
            for (TaskStatus status : values()) {
                intToEnum.put(status.getCode(), status);
            }
        }

        public static TaskStatus fromInt(Integer symbol) {
            return intToEnum.get(symbol);
        }

        public static String getDescrByCode(int code) {
            for (TaskStatus status : TaskStatus.values()) {
                if (status.getCode() == code) {
                    return status.getDescr();
                }
            }
            return null;
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public float getScore() {
        return score == 0.0f ? 5f : score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

}