package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunction.FunctionKeys;
import com.juma.tgm.truck.domain.AdditionalFunctionFreight;
import com.juma.tgm.waybill.domain.WaybillParam;

/**
 * 
 * @Description: 税后价格计算BEAN
 * @author weilibin
 * @date 2016年9月6日 下午8:15:30
 * @version V1.0
 */

public class PriceAfterTaxProxy implements Serializable {

    private static final long serialVersionUID = -1103933864084374510L;
    // 总费用
    private BigDecimal finalPrice = BigDecimal.ZERO;
    // 税后费用
    private BigDecimal finalAfterTaxPrice = BigDecimal.ZERO;
    // 收费路段预估应收费用
    private BigDecimal tolls = BigDecimal.ZERO;
    // 收费路段预估距离
    private BigDecimal tollDistance = BigDecimal.ZERO;
    // 代收货款信息
    private CollectionPaymentInfo collectionPaymentInfo = new CollectionPaymentInfo();
    // 冷链信息
    private ColdChainInfo coldChainInfo = new ColdChainInfo();
    // 返仓信息
    private BackStorageInfo backStorageInfo = new BackStorageInfo();
    // 税费信息
    private TaxRateInfo taxRateInfo = new TaxRateInfo();
    // 回单信息
    private ReceiptInfo receiptInfo = new ReceiptInfo();
    // 搬运信息
    private CarryInfo carryInfo = new CarryInfo();
    //返点费用信息
    private RebateRateInfo rebateRateInfo = new RebateRateInfo();
    // 入城证信息
    private EntryLicenseInfo entryLicenseInfo = new EntryLicenseInfo();
    // 搬运费信息
    private WaybillParamInfo waybillParamInfo = new WaybillParamInfo();

    public PriceAfterTaxProxy() {
    }

    /**
     *
     * @param map 附加费用和费率
     * @param price 预估价格
     * @param list 需要计算的附加运费
     */
    public PriceAfterTaxProxy(Map<String, Object> map, BigDecimal price, List<Integer> list) {
        this.finalPrice = price;
        this.collectionPaymentInfo = new CollectionPaymentInfo(
                getValue(map, AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT), list);
        this.coldChainInfo = new ColdChainInfo(getValue(map, AdditionalFunction.FunctionKeys.COLD_CHAIN), list);
        this.backStorageInfo = new BackStorageInfo(getValue(map, AdditionalFunction.FunctionKeys.NEED_BACK_STORAGE), list);
        this.taxRateInfo = new TaxRateInfo(map);
        this.receiptInfo = new ReceiptInfo(getValue(map, AdditionalFunction.FunctionKeys.NEED_RECEIPT), list);
        this.carryInfo = new CarryInfo(getValue(map, AdditionalFunction.FunctionKeys.NEED_CARRY), list);
        this.entryLicenseInfo = new EntryLicenseInfo(getValue(map, AdditionalFunction.FunctionKeys.ENTRY_LICENSE), list);
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getFinalAfterTaxPrice() {
        return finalAfterTaxPrice;
    }

    public void setFinalAfterTaxPrice(BigDecimal finalAfterTaxPrice) {
        this.finalAfterTaxPrice = finalAfterTaxPrice;
    }

    public BigDecimal getRateFrihgt() {
        return this.finalPrice.subtract(this.finalAfterTaxPrice);
    }

    public CollectionPaymentInfo getCollectionPaymentInfo() {
        return collectionPaymentInfo;
    }

    public void setCollectionPaymentInfo(CollectionPaymentInfo collectionPaymentInfo) {
        this.collectionPaymentInfo = collectionPaymentInfo;
    }

    public ColdChainInfo getColdChainInfo() {
        return coldChainInfo;
    }

    public void setColdChainInfo(ColdChainInfo coldChainInfo) {
        this.coldChainInfo = coldChainInfo;
    }

    public BackStorageInfo getBackStorageInfo() {
        return backStorageInfo;
    }

    public void setBackStorageInfo(BackStorageInfo backStorageInfo) {
        this.backStorageInfo = backStorageInfo;
    }

    public TaxRateInfo getTaxRateInfo() {
        return taxRateInfo;
    }

    public void setTaxRateInfo(TaxRateInfo taxRateInfo) {
        this.taxRateInfo = taxRateInfo;
    }

    public ReceiptInfo getReceiptInfo() {
        return receiptInfo;
    }

    public void setReceiptInfo(ReceiptInfo receiptInfo) {
        this.receiptInfo = receiptInfo;
    }

    public CarryInfo getCarryInfo() {
        return carryInfo;
    }

    public void setCarryInfo(CarryInfo carryInfo) {
        this.carryInfo = carryInfo;
    }

    public EntryLicenseInfo getEntryLicenseInfo() {
        return entryLicenseInfo;
    }

    public void setEntryLicenseInfo(EntryLicenseInfo entryLicenseInfo) {
        this.entryLicenseInfo = entryLicenseInfo;
    }

    public WaybillParamInfo getWaybillParamInfo() {
        return waybillParamInfo;
    }

    public void setWaybillParamInfo(WaybillParam waybillParam, WaybillParam oldWaybillParam, BigDecimal rate) {
        this.waybillParamInfo = new WaybillParamInfo(waybillParam, oldWaybillParam, rate);
    }

    public BigDecimal getTolls() {
        return tolls;
    }

    public void setTolls(BigDecimal tolls) {
        this.tolls = tolls;
    }

    public BigDecimal getTollDistance() {
        return tollDistance;
    }

    public void setTollDistance(BigDecimal tollDistance) {
        this.tollDistance = tollDistance;
    }

    private AdditionalFunctionFreight getValue(Map<String, Object> map, FunctionKeys key) {
        Object obj = map.get(key.toString());
        if (null != obj) {
            return (AdditionalFunctionFreight) obj;
        }
        return null;
    }

    @Override
    public String toString() {
        return "PriceAfterTaxProxy [finalPrice=" + finalPrice + ", finalAfterTaxPrice=" + finalAfterTaxPrice
                + ", tolls=" + tolls + ", tollDistance=" + tollDistance + "]";
    }

    public RebateRateInfo getRebateRateInfo() {
        return rebateRateInfo;
    }

    public void setRebateRateInfo(RebateRateInfo rebateRateInfo) {
        this.rebateRateInfo = rebateRateInfo;
    }
}
