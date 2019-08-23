package com.juma.tgm.costReimbursed.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juma.tgm.common.DateUtil;

import me.about.poi.ExcelColumn;

/**
 * cost_reimbursed - 费用报销
 * 
 * @author 2017-07-10
 * @version 1.0
 */
public class CostReimbursed implements Serializable {

    private static final long serialVersionUID = -7866822542257543603L;
    private Integer costReimbursedId;
    private String costReimbursedKey;
    private Integer costReimbursedType;
    @ExcelColumn(name = "流水号")
    private String costReimbursedNo;
    private Date costProduceTime;
    private Date declareTime;
    @ExcelColumn(name = "报销金额")
    private BigDecimal reimbursedAmount;
    @ExcelColumn(name = "审核结果")
    private Integer auditResult;
    private Integer truckId;
    private Integer driverId;
    private Integer waybillId;
    private String waybillNo;
    @ExcelColumn(name = "备注")
    private String remark;
    private String regionCode;
    private String areaCode;
    private Integer tenantId;
    private String tenantCode;
    private Date createTime;
    private Integer createUserId;
    private Boolean isDelete;
    private Date lastUpdateTime;
    private Integer lastUpdateUserId;

    // 显示冗余
    @ExcelColumn(name = "审核结果")
    private String auditResultplus;
    private Integer count;
    private String plateNumber;
    private String driverName;
    private String driverPhone;
    private String costReimbursedTypeName;
    private List<String> listUrl = new ArrayList<String>();
    private Integer recordNum;
    private String auditResultDesc;

    public String getAuditResultplus() {
        AuditResult result = CostReimbursed.AuditResult.intToEnum.get(auditResult);
        return result == null ? auditResultplus : result.getDesc();
    }

    public void setAuditResultplus(String auditResultplus) {
        this.auditResultplus = auditResultplus;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCostReimbursedId() {
        return costReimbursedId;
    }

    public void setCostReimbursedId(Integer costReimbursedId) {
        this.costReimbursedId = costReimbursedId;
    }

    public String getCostReimbursedKey() {
        return costReimbursedKey;
    }

    public void setCostReimbursedKey(String costReimbursedKey) {
        this.costReimbursedKey = costReimbursedKey;
    }

    public Integer getCostReimbursedType() {
        return costReimbursedType;
    }

    public void setCostReimbursedType(Integer costReimbursedType) {
        this.costReimbursedType = costReimbursedType;
    }

    public String getCostReimbursedNo() {
        return costReimbursedNo;
    }

    public void setCostReimbursedNo(String costReimbursedNo) {
        this.costReimbursedNo = costReimbursedNo;
    }

    public Date getCostProduceTime() {
        return costProduceTime;
    }

    public void setCostProduceTime(Date costProduceTime) {
        this.costProduceTime = costProduceTime;
    }

    public Date getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(Date declareTime) {
        this.declareTime = declareTime;
    }

    public BigDecimal getReimbursedAmount() {
        return reimbursedAmount;
    }

    public void setReimbursedAmount(BigDecimal reimbursedAmount) {
        this.reimbursedAmount = reimbursedAmount;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Integer waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCostReimbursedTypeName() {
        return costReimbursedTypeName;
    }

    public void setCostReimbursedTypeName(String costReimbursedTypeName) {
        this.costReimbursedTypeName = costReimbursedTypeName;
    }

    public List<String> getListUrl() {
        return listUrl;
    }

    public void setListUrl(List<String> listUrl) {
        this.listUrl = listUrl;
    }

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }

    public String getCostReimbursedTypeDesc() {
        AuditResult result = AuditResult.fromInt(costReimbursedType);
        return result == null ? null : result.getDesc();
    }

    public String getAuditResultDesc() {
        return auditResultDesc;
    }

    public void setAuditResultDesc(String auditResultDesc) {
        this.auditResultDesc = auditResultDesc;
    }

    public enum AuditResult {
        WAITING_AUDIT(1, "待审核"), PASS(2, "确认报销"), DID_NOT_PASS(3, "不报销");

        private int code;
        private String desc;

        private AuditResult(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private static final Map<Integer, AuditResult> intToEnum = new HashMap<Integer, AuditResult>();

        static {
            for (AuditResult auditResult : values()) {
                intToEnum.put(auditResult.getCode(), auditResult);
            }
        }

        public static AuditResult fromInt(Integer symbol) {
            return intToEnum.get(symbol);
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByCode(int code) {
            for (AuditResult auditResult : AuditResult.values()) {
                if (code == auditResult.getCode()) {
                    return auditResult.getDesc();
                }
            }
            return null;
        }
    }

    public enum CostReimbursedKey {
        DRIVER_COST_REIMBURSED(1, "司机费用报销");

        private int code;
        private String desc;

        private CostReimbursedKey(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDescByCode(int code) {
            for (CostReimbursedKey costReimbursedKey : CostReimbursedKey.values()) {
                if (code == costReimbursedKey.getCode()) {
                    return costReimbursedKey.getDesc();
                }
            }
            return null;
        }
    }

    public enum TimeParamList {
        THIS_WEEK(1, "本周"), THIS_MONTH(2, "本月"), LAST_MONTH(3, "上月"), HALF_A_YEAR(4, "半年"), ONE_YEAR(5, "一年");

        private int code;
        private String desc;

        private TimeParamList(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static Map<String, Date> getMapByCode(int code) {
            Map<String, Date> map = new HashMap<String, Date>();
            if (code == TimeParamList.THIS_WEEK.getCode()) {
                map.put("startTime", DateUtil.dayStartReturnDate(DateUtil.getFirstDayOfWeek(new Date())));
            } else if (code == TimeParamList.THIS_MONTH.getCode()) {
                map.put("startTime", DateUtil.dayStartReturnDate(DateUtil.getFirstDayOfMonth(new Date())));
            } else if (code == TimeParamList.LAST_MONTH.getCode()) {
                map.put("startTime", DateUtil.dayStartReturnDate(
                        DateUtil.getFirstDayOfMonth(DateUtil.getDayOfMonth(null, DateUtil.curMonth() - 1, null))));
                map.put("endTime", DateUtil.dayEndReturnDate(
                        DateUtil.getLastDayOfMonth(DateUtil.getDayOfMonth(null, DateUtil.curMonth() - 1, null))));
            } else if (code == TimeParamList.HALF_A_YEAR.getCode()) {
                map.put("startTime",
                        DateUtil.dayStartReturnDate(DateUtil.getDayOfMonth(null, DateUtil.curMonth() - 6, null)));
            } else if (code == TimeParamList.ONE_YEAR.getCode()) {
                map.put("startTime",
                        DateUtil.dayStartReturnDate(DateUtil.getDayOfMonth(null, DateUtil.curMonth() - 12, null)));
            }
            return map;
        }

    }
}