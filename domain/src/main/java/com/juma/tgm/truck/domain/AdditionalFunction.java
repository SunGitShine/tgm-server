package com.juma.tgm.truck.domain;

import com.google.common.base.Objects;
import com.juma.tgm.base.domain.BaseDomain;

/**
 * additional_function - 附加功能
 * 
 * @author 2016-05-09
 * @version 1.0
 */
public class AdditionalFunction extends BaseDomain {

    private static final long serialVersionUID = 2621119527693321238L;
    private Integer additionalFunctionId;
    private Integer parentFunctionId;
    private String functionKey;
    private String functionName;
    private String prefixName;
    private String suffixName;
    private String functionDescription;
    private Integer orderNo;
    private Integer additionalFunctionSign;

    public enum FunctionKeys {
        COLD_CHAIN, NEED_CARRY, NEED_BACK_STORAGE, NEED_RECEIPT, ENTRY_LICENSE, COLLECTION_PAYMENT,DRIVER_CARRY,LABORER_CARRY,DELIVERY_RECEIPT,NEXT_DAY_DELIVERY,
        /**
         * 落地配搬运
        * */
        CARRY,CARRY_COOLIE,LOADING,UNLOAD,UPSTAIRS;
    }

    public Integer getAdditionalFunctionId() {
        return additionalFunctionId;
    }

    public void setAdditionalFunctionId(Integer additionalFunctionId) {
        this.additionalFunctionId = additionalFunctionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public Integer getParentFunctionId() {
        return parentFunctionId;
    }

    public void setParentFunctionId(Integer parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    public String getFunctionKey() {
        return functionKey;
    }

    public void setFunctionKey(String functionKey) {
        this.functionKey = functionKey;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getAdditionalFunctionSign() {
        return additionalFunctionSign;
    }

    public void setAdditionalFunctionSign(Integer additionalFunctionSign) {
        this.additionalFunctionSign = additionalFunctionSign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalFunction that = (AdditionalFunction) o;
        return Objects.equal(additionalFunctionId, that.additionalFunctionId) &&
                Objects.equal(parentFunctionId, that.parentFunctionId) &&
                Objects.equal(functionKey, that.functionKey) &&
                Objects.equal(functionName, that.functionName) &&
                Objects.equal(prefixName, that.prefixName) &&
                Objects.equal(suffixName, that.suffixName) &&
                Objects.equal(functionDescription, that.functionDescription) &&
                Objects.equal(orderNo, that.orderNo) &&
                Objects.equal(additionalFunctionSign, that.additionalFunctionSign);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(additionalFunctionId, parentFunctionId, functionKey, functionName, prefixName, suffixName, functionDescription, orderNo, additionalFunctionSign);
    }
}