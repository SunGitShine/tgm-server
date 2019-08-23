package com.juma.tgm.truck.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class AdditionalFunctionFreightBo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8629321650536993815L;
    /** 附加功能 */
    private AdditionalFunctionFreight affFreight;
    /** 附加功能名称 */
    private String functionName;
    /** 冷链溢价比例 */
    private BigDecimal coldChainFreight;
    /** 代收货款费 */
    private BigDecimal collectionPaymentFreight;
    /** 返仓费 */
    private BigDecimal backStorageFreight;
    /** 回单费 */
    private BigDecimal receiptFreight;
    /** 搬运费 */
    private BigDecimal carryFreight;
    /** 司机搬运*/
    private BigDecimal driverHandlingCost;
    /** 小工搬运*/
    private BigDecimal laborerHandlingCost;
    /** 入城证费 */
    private BigDecimal entryLicenseFreight;
    

    public AdditionalFunctionFreight getAffFreight() {
        return affFreight;
    }

    public void setAffFreight(AdditionalFunctionFreight affFreight) {
        this.affFreight = affFreight;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public BigDecimal getColdChainFreight() {
        return coldChainFreight;
    }

    public void setColdChainFreight(BigDecimal coldChainFreight) {
        this.coldChainFreight = coldChainFreight;
    }

    public BigDecimal getCollectionPaymentFreight() {
        return collectionPaymentFreight;
    }

    public void setCollectionPaymentFreight(BigDecimal collectionPaymentFreight) {
        this.collectionPaymentFreight = collectionPaymentFreight;
    }

    public BigDecimal getBackStorageFreight() {
        return backStorageFreight;
    }

    public void setBackStorageFreight(BigDecimal backStorageFreight) {
        this.backStorageFreight = backStorageFreight;
    }

    public BigDecimal getReceiptFreight() {
        return receiptFreight;
    }

    public void setReceiptFreight(BigDecimal receiptFreight) {
        this.receiptFreight = receiptFreight;
    }

    public BigDecimal getCarryFreight() {
        return carryFreight;
    }

    public void setCarryFreight(BigDecimal carryFreight) {
        this.carryFreight = carryFreight;
    }

    public BigDecimal getEntryLicenseFreight() {
        return entryLicenseFreight;
    }

    public void setEntryLicenseFreight(BigDecimal entryLicenseFreight) {
        this.entryLicenseFreight = entryLicenseFreight;
    }

    public BigDecimal getDriverHandlingCost() {
        return driverHandlingCost;
    }

    public void setDriverHandlingCost(BigDecimal driverHandlingCost) {
        this.driverHandlingCost = driverHandlingCost;
    }

    public BigDecimal getLaborerHandlingCost() {
        return laborerHandlingCost;
    }

    public void setLaborerHandlingCost(BigDecimal laborerHandlingCost) {
        this.laborerHandlingCost = laborerHandlingCost;
    }

}
