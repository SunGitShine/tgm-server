package com.juma.tgm.waybill.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 建单转承运人信息
 *
 * @ClassName: WaybillCarrierVo
 * @Description:
 * @author: liang
 * @date: 2018-08-28 17:32
 * @Copyright: 2018 www.jumapeisong.com Inc. All rights reserved.
 */
public class WaybillCarrierVo implements Serializable {

    /**
     * 转运单结算类型
     */
    public enum TransformCarrierSettlementType {
        SETTLEMENT_TYPE_FEE(1,"一口价"), SETTLEMENT_TYPE_RATE(2, "按服务费率");

        private Integer code;
        private String desc;

        TransformCarrierSettlementType(Integer code, String desc) {
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

    /**
     * 转运单业务类型
     */
    public enum  BusinessType {
        JUMA_LOGISTICS(1, "专车"),
        PROJECT(2, "项目"),
        SCATTED(3, "希地");

        private Integer code;
        private String desc;

        BusinessType(int code, String desc){
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

    /**
     * 承运商id
     */
    private Integer vendorId;

    /**
     * 承运商租户下的客户id
     */
    private Integer customerId;

    /**
     * 承运商下的客户租户id
     */
    private Integer customerTenantId;
    /**
     * 运单税率
     * 优先去项目中的税率，没有则取源运单税率
     */
    private BigDecimal billTaxRate;

    /**
     * 承运商结算方式
     */
    private Integer settlementType;
    /**
     * 承运费
     */
    private BigDecimal vendorFees;
    /**
     * 费率
     */
    private BigDecimal vendorFeeRate;

    /**
     * 转运单业务类型
     */
    @JSONField(serialize = false)
    private BusinessType businessType;

    /**
     * 返点率
     * 项目中的返点率
     */
    private BigDecimal rebateRate;

    /**
     * 承运项目id
     */
    private Integer projectId;

    /**
     * 分公司:签约主体
     */
    private String departmentCode;
    private Integer departmentId;
    /**
     * 运营主体
     */
    private Integer payToCompany;

    /**
     * 承运商租户key
     * （服务端内部使用）
     */
    @JSONField(serialize = false)
    private String carrierTenantKey;


    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public BigDecimal getVendorFees() {
        return vendorFees;
    }

    public void setVendorFees(BigDecimal vendorFees) {
        this.vendorFees = vendorFees;
    }

    public BigDecimal getVendorFeeRate() {
        return vendorFeeRate;
    }

    public void setVendorFeeRate(BigDecimal vendorFeeRate) {
        this.vendorFeeRate = vendorFeeRate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBillTaxRate() {
        return billTaxRate;
    }

    public void setBillTaxRate(BigDecimal billTaxRate) {
        this.billTaxRate = billTaxRate;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Integer getCustomerTenantId() {
        return customerTenantId;
    }

    public void setCustomerTenantId(Integer customerTenantId) {
        this.customerTenantId = customerTenantId;
    }


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }


    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
    }

    public String getCarrierTenantKey() {
        return carrierTenantKey;
    }

    public void setCarrierTenantKey(String carrierTenantKey) {
        this.carrierTenantKey = carrierTenantKey;
    }

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getPayToCompany() {
        return payToCompany;
    }

    public void setPayToCompany(Integer payToCompany) {
        this.payToCompany = payToCompany;
    }
}
