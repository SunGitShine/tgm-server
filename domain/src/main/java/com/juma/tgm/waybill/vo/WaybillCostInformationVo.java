package com.juma.tgm.waybill.vo;

import com.juma.tgm.common.BaseUtil;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.lucene.analysis.synonym.SolrSynonymParser;

@ApiModel("运单费用信息")
public class WaybillCostInformationVo implements Serializable {

  @ApiModelProperty(value = "客户侧调整前含税金额")
  private BigDecimal customerFreightWithTax;
  @ApiModelProperty(value = "客户侧调整前不含税金额")
  private BigDecimal customerFreightWithNotTax;
  @ApiModelProperty(value = "承运侧调整前含税金额")
  private BigDecimal vendorFreightWithTax;
  @ApiModelProperty(value = "客户侧税率")
  private BigDecimal customerTaxValue;

  @ApiModelProperty(value = "客户侧税费")
  private BigDecimal customerTaxFee;

  @ApiModelProperty(value = "客户侧对账前调整金额")
  private BigDecimal customerAdjustFreightBeforeReconciliation;
  @ApiModelProperty(value = "客户侧对账前绝对调整金额")
  private BigDecimal customerAbsAdjustFeightBeforeReconciliation;

  @ApiModelProperty(value = "客户侧对账后调整金额")
  private BigDecimal customerAdjustFreightAfterReconciliation;
  @ApiModelProperty(value = "客户侧对账后绝对调整金额")
  private BigDecimal customerAbsAdjustFreightAfterReconciliation;

  @ApiModelProperty(value = "客户侧调整后含税金额")
  private BigDecimal customerAdjustFreightWithTax;
  @ApiModelProperty(value = "客户侧调整后不含税金额")
  private BigDecimal customerAdjustFreightWithNotTax;

  @ApiModelProperty(value = "承运侧对账前调整金额")
  private BigDecimal vendorAdjustFreightBeforeReconciliation;
  @ApiModelProperty(value = "承运侧对账前绝对调整金额")
  private BigDecimal vendorAbsAdjustFreightBeforeReconciliation;

  @ApiModelProperty(value = "承运侧对账后调整金额")
  private BigDecimal vendorAdjustFreightAfterReconciliation;
  @ApiModelProperty(value = "承运侧对账后绝对调整金额")
  private BigDecimal vendorAbsAdjustFreightAfterReconciliation;

  @ApiModelProperty(value = "承运侧调整后含税金额")
  private BigDecimal vendorAdjustFreightWithTax;

  @ApiModelProperty(value = "毛利")
  private String grossProfi;

  public BigDecimal getCustomerFreightWithTax() {
    return customerFreightWithTax;
  }

  public void setCustomerFreightWithTax(BigDecimal customerFreightWithTax) {
    this.customerFreightWithTax = customerFreightWithTax;
  }

  public BigDecimal getCustomerFreightWithNotTax() {
    return customerFreightWithNotTax;
  }

  public void setCustomerFreightWithNotTax(BigDecimal customerFreightWithNotTax) {
    this.customerFreightWithNotTax = customerFreightWithNotTax;
  }

  public BigDecimal getVendorFreightWithTax() {
    return vendorFreightWithTax;
  }

  public void setVendorFreightWithTax(BigDecimal vendorFreightWithTax) {
    this.vendorFreightWithTax = vendorFreightWithTax;
  }

  public BigDecimal getCustomerTaxValue() {
    return customerTaxValue;
  }

  public void setCustomerTaxValue(BigDecimal customerTaxValue) {
    this.customerTaxValue = customerTaxValue;
  }

  public BigDecimal getCustomerTaxFee() {
    if (null != this.customerTaxFee) {
      return customerTaxFee;
    }

    return this.customerAdjustFreightWithTax == null ? (this.customerFreightWithTax == null ? BigDecimal.ZERO
        : this.customerFreightWithTax
            .subtract(this.customerFreightWithNotTax == null ? BigDecimal.ZERO : this.customerFreightWithNotTax))
        : this.customerAdjustFreightWithTax.subtract(
            this.customerAdjustFreightWithNotTax == null ? BigDecimal.ZERO : this.customerAdjustFreightWithNotTax);
  }

  public void setCustomerTaxFee(BigDecimal customerTaxFee) {
    this.customerTaxFee = customerTaxFee;
  }

  public BigDecimal getCustomerAdjustFreightBeforeReconciliation() {
    return customerAdjustFreightBeforeReconciliation;
  }

  public void setCustomerAdjustFreightBeforeReconciliation(BigDecimal customerAdjustFreightBeforeReconciliation) {
    this.customerAdjustFreightBeforeReconciliation = customerAdjustFreightBeforeReconciliation;
  }

  public BigDecimal getCustomerAbsAdjustFeightBeforeReconciliation() {
    return customerAbsAdjustFeightBeforeReconciliation;
  }

  public void setCustomerAbsAdjustFeightBeforeReconciliation(BigDecimal customerAbsAdjustFeightBeforeReconciliation) {
    this.customerAbsAdjustFeightBeforeReconciliation = customerAbsAdjustFeightBeforeReconciliation;
  }

  public BigDecimal getCustomerAdjustFreightAfterReconciliation() {
    return customerAdjustFreightAfterReconciliation;
  }

  public void setCustomerAdjustFreightAfterReconciliation(BigDecimal customerAdjustFreightAfterReconciliation) {
    this.customerAdjustFreightAfterReconciliation = customerAdjustFreightAfterReconciliation;
  }

  public BigDecimal getCustomerAbsAdjustFreightAfterReconciliation() {
    return customerAbsAdjustFreightAfterReconciliation;
  }

  public void setCustomerAbsAdjustFreightAfterReconciliation(BigDecimal customerAbsAdjustFreightAfterReconciliation) {
    this.customerAbsAdjustFreightAfterReconciliation = customerAbsAdjustFreightAfterReconciliation;
  }

  public BigDecimal getCustomerAdjustFreightWithTax() {
    return customerAdjustFreightWithTax;
  }

  public void setCustomerAdjustFreightWithTax(BigDecimal customerAdjustFreightWithTax) {
    this.customerAdjustFreightWithTax = customerAdjustFreightWithTax;
  }

  public BigDecimal getCustomerAdjustFreightWithNotTax() {
    if (null != customerAdjustFreightWithNotTax) {
      return customerAdjustFreightWithNotTax;
    }

    if (this.customerAdjustFreightWithTax == null) {
      return customerAdjustFreightWithNotTax;
    }

    return BaseUtil.calFreightWithNotTax(customerAdjustFreightWithTax, customerTaxValue);
  }

  public void setCustomerAdjustFreightWithNotTax(BigDecimal customerAdjustFreightWithNotTax) {
    this.customerAdjustFreightWithNotTax = customerAdjustFreightWithNotTax;
  }

  public BigDecimal getVendorAdjustFreightBeforeReconciliation() {
    return vendorAdjustFreightBeforeReconciliation;
  }

  public void setVendorAdjustFreightBeforeReconciliation(BigDecimal vendorAdjustFreightBeforeReconciliation) {
    this.vendorAdjustFreightBeforeReconciliation = vendorAdjustFreightBeforeReconciliation;
  }

  public BigDecimal getVendorAbsAdjustFreightBeforeReconciliation() {
    return vendorAbsAdjustFreightBeforeReconciliation;
  }

  public void setVendorAbsAdjustFreightBeforeReconciliation(BigDecimal vendorAbsAdjustFreightBeforeReconciliation) {
    this.vendorAbsAdjustFreightBeforeReconciliation = vendorAbsAdjustFreightBeforeReconciliation;
  }

  public BigDecimal getVendorAdjustFreightAfterReconciliation() {
    return vendorAdjustFreightAfterReconciliation;
  }

  public void setVendorAdjustFreightAfterReconciliation(BigDecimal vendorAdjustFreightAfterReconciliation) {
    this.vendorAdjustFreightAfterReconciliation = vendorAdjustFreightAfterReconciliation;
  }

  public BigDecimal getVendorAbsAdjustFreightAfterReconciliation() {
    return vendorAbsAdjustFreightAfterReconciliation;
  }

  public void setVendorAbsAdjustFreightAfterReconciliation(BigDecimal vendorAbsAdjustFreightAfterReconciliation) {
    this.vendorAbsAdjustFreightAfterReconciliation = vendorAbsAdjustFreightAfterReconciliation;
  }

  public BigDecimal getVendorAdjustFreightWithTax() {
    return vendorAdjustFreightWithTax;
  }

  public void setVendorAdjustFreightWithTax(BigDecimal vendorAdjustFreightWithTax) {
    this.vendorAdjustFreightWithTax = vendorAdjustFreightWithTax;
  }

  public String getGrossProfi() {
    if (grossProfi != null) {
      return grossProfi;
    }

    BigDecimal customerfreight = customerAdjustFreightWithTax == null ?  BigDecimal.ZERO  : customerAdjustFreightWithTax;

    BigDecimal vendorFreight = vendorAdjustFreightWithTax == null ? BigDecimal.ZERO : vendorAdjustFreightWithTax;

    if (customerfreight.compareTo(BigDecimal.ZERO) == 0) {
      return grossProfi;
    }

    BigDecimal decimal = (customerfreight.subtract(vendorFreight)).divide(customerfreight, 4, BigDecimal.ROUND_HALF_UP);

    return BaseUtil.formatDecimal(decimal.doubleValue());
  }

  public void setGrossProfi(String grossProfi) {
    this.grossProfi = grossProfi;
  }
}
