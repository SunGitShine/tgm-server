package com.juma.tgm.customerManager.domain;


import com.alibaba.fastjson.annotation.JSONField;
import com.giants.common.exception.BusinessException;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * fixed_demand - 固定需求
 *
 * @author 2017-07-24
 * @version 1.0
 */
public class FixedDemand implements Serializable {
    private Integer fixedDemandId;
    private Integer customerManagerId;
    private Integer customerId;
    private Integer truckCustomerId;
    private String remark;
    private BigDecimal estimateFreight;
    private BigDecimal show4DriverFreight;
    private String deliveryTimePoint;
    private Integer finishTimePoint;
    private Integer receiptType;
    private String requireJson;
    private Integer vehicleCount;
    private Integer projectId;
    private Integer businessBranch;
    private String projectFreightRuleJson;
    // 最低温度
    private Float requiredMinTemperature;
    // 最高温度
    private Float requiredMaxTemperature;
    /**
     * 派车方式
     */
    private Integer receiveWay;

    /**
     * 自动下单策略（json）
     */
    @JSONField(serialize = false)
    private String autoCreateBillStrategy;
    /**
     * 是否自动下单
     *  1：否
     *  2：是
     */
    private Integer isAutoCreateBill;

    /**
     * 是否需要上传配送单
     */
    private Integer needDeliveryPointNote;
    /**
     * 隔天配送
     */
    private Integer onlyLoadCargo;
    /**
     * 代收货款
     */
    private BigDecimal agencyTakeFreight;
    private Date createTime;
    private Integer createUserId;
    private Date lastUpdateTime;
    private Integer lastUpdateUserId;

    public enum IsAutoCreateBillEnum {
        NO_AUTO_CREATE(1, "不自动下单"),
        AUTO_CREATE(2, "自动下单");
        private Integer code;
        private String desc;

        IsAutoCreateBillEnum(Integer code , String desc){
            this.desc = desc;
            this.code = code;
        }
        public static IsAutoCreateBillEnum getByCode(int code){
            for(IsAutoCreateBillEnum type: IsAutoCreateBillEnum.values()){
                if(NumberUtils.compare(type.code, code) == 0 ){
                    return type;
                }
            }
            throw new BusinessException("paramError","errors.common.prompt","IsAutoCreateBillEnum 参数错误");
        }

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public final static Map<Integer, String> dayOfWeek = new HashMap<>();
    static {
        dayOfWeek.put(1, "sun");
        dayOfWeek.put(2, "mon");
        dayOfWeek.put(3, "tue");
        dayOfWeek.put(4, "wen");
        dayOfWeek.put(5, "thu");
        dayOfWeek.put(6, "fri");
        dayOfWeek.put(7, "sta");

    }


    /**
     * 自动简单周期策略
     */
    public static class BillStrategy implements Serializable {

        /**
         * 所属固定需求
         */
        private Integer fixedDemandId;
        /**
         * 开始日期
         */
        private String startDate;
        /**
         * 结束日期
         */
        private String endDate;

        /**
         * 包含的日期 mon,tue,wen,thu,fri,sta,sun
         */

        private Set<String> days;

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Set<String> getDays() {
            return days;
        }

        public void setDays(Set<String> days) {
            this.days = days;
        }

        public Integer getFixedDemandId() {
            return fixedDemandId;
        }

        public void setFixedDemandId(Integer fixedDemandId) {
            this.fixedDemandId = fixedDemandId;
        }
    }

    public Integer getFixedDemandId() {
        return fixedDemandId;
    }

    public void setFixedDemandId(Integer fixedDemandId) {
        this.fixedDemandId = fixedDemandId;
    }

    public Integer getCustomerManagerId() {
        return customerManagerId;
    }

    public void setCustomerManagerId(Integer customerManagerId) {
        this.customerManagerId = customerManagerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTruckCustomerId() {
        return truckCustomerId;
    }

    public void setTruckCustomerId(Integer truckCustomerId) {
        this.truckCustomerId = truckCustomerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getEstimateFreight() {
        return estimateFreight;
    }

    public void setEstimateFreight(BigDecimal estimateFreight) {
        this.estimateFreight = estimateFreight;
    }

    public String getDeliveryTimePoint() {
        return deliveryTimePoint;
    }

    public void setDeliveryTimePoint(String deliveryTimePoint) {
        this.deliveryTimePoint = deliveryTimePoint;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public String getRequireJson() {
        return requireJson;
    }

    public void setRequireJson(String requireJson) {
        this.requireJson = requireJson;
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Integer lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }


    public Integer getNeedDeliveryPointNote() {
        return needDeliveryPointNote;
    }

    public void setNeedDeliveryPointNote(Integer needDeliveryPointNote) {
        this.needDeliveryPointNote = needDeliveryPointNote;
    }

    public Integer getFinishTimePoint() {
        return finishTimePoint;
    }

    public void setFinishTimePoint(Integer finishTimePoint) {
        this.finishTimePoint = finishTimePoint;
    }

    public BigDecimal getShow4DriverFreight() {
        return show4DriverFreight;
    }

    public void setShow4DriverFreight(BigDecimal show4DriverFreight) {
        this.show4DriverFreight = show4DriverFreight;
    }


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getIsAutoCreateBill() {
        return isAutoCreateBill;
    }

    public void setIsAutoCreateBill(Integer isAutoCreateBill) {
        this.isAutoCreateBill = isAutoCreateBill;
    }

    public String getAutoCreateBillStrategy() {
        return autoCreateBillStrategy;
    }

    public void setAutoCreateBillStrategy(String autoCreateBillStrategy) {
        this.autoCreateBillStrategy = autoCreateBillStrategy;
    }

    public Integer getBusinessBranch() {
        return businessBranch;
    }

    public void setBusinessBranch(Integer businessBranch) {
        this.businessBranch = businessBranch;
    }

    public Integer getReceiveWay() {
        return receiveWay;
    }

    public void setReceiveWay(Integer receiveWay) {
        this.receiveWay = receiveWay;
    }

    public Integer getOnlyLoadCargo() {
        return onlyLoadCargo;
    }

    public void setOnlyLoadCargo(Integer onlyLoadCargo) {
        this.onlyLoadCargo = onlyLoadCargo;
    }

    public BigDecimal getAgencyTakeFreight() {
        return agencyTakeFreight;
    }

    public void setAgencyTakeFreight(BigDecimal agencyTakeFreight) {
        this.agencyTakeFreight = agencyTakeFreight;
    }

    public Float getRequiredMinTemperature() {
        return requiredMinTemperature;
    }

    public void setRequiredMinTemperature(Float requiredMinTemperature) {
        this.requiredMinTemperature = requiredMinTemperature;
    }

    public Float getRequiredMaxTemperature() {
        return requiredMaxTemperature;
    }

    public void setRequiredMaxTemperature(Float requiredMaxTemperature) {
        this.requiredMaxTemperature = requiredMaxTemperature;
    }

    public String getProjectFreightRuleJson() {
        return projectFreightRuleJson;
    }

    public void setProjectFreightRuleJson(String projectFreightRuleJson) {
        this.projectFreightRuleJson = projectFreightRuleJson;
    }
}