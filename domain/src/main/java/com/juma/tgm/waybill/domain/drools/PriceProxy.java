package com.juma.tgm.waybill.domain.drools;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.juma.tgm.truck.domain.AdditionalFunction;
import com.juma.tgm.truck.domain.AdditionalFunction.FunctionKeys;
import com.juma.tgm.truck.domain.AdditionalFunctionFreight;
import com.juma.tgm.truck.domain.TruckTypeFreight;
import com.juma.tgm.waybill.domain.DistanceAndPriceData;

/**
 * 
 * @Description: 价格计算BEAN
 * @author weilibin
 * @date 2016年9月6日 下午8:15:30
 * @version V1.0
 */

public class PriceProxy implements Serializable {

    private static final long serialVersionUID = 3847480227506581162L;
    // 起步价
    private BigDecimal lowestFreight = BigDecimal.ZERO;
    // 总里程
    private BigDecimal totalMileage = BigDecimal.ZERO;
    // 起步里程
    private BigDecimal lowestMileage = BigDecimal.ZERO;
    // 税费
    private BigDecimal rateFrihgt = BigDecimal.ZERO;
    // 冷链溢价
    private BigDecimal coldChainFrihgt = BigDecimal.ZERO;
    // 总站点数
    private BigDecimal totalSite = BigDecimal.ZERO;
    // 最终价
    private BigDecimal finalPrice = BigDecimal.ZERO;
    // 收费路段预估应收费用
    private BigDecimal tolls = BigDecimal.ZERO;
    // 收费路段预估距离
    private BigDecimal tollDistance = BigDecimal.ZERO;
    
    //时间成本
    private BigDecimal timeCost = BigDecimal.ZERO;
    //========== 费用报销 ===============
    //停车费
    private BigDecimal parkingFee = BigDecimal.ZERO;
    
    // 超里程信息
    private SuperMileageInfo superMileageInfo = new SuperMileageInfo();
    // 配送点信息
    private DistributionPointInfo distributionPointInfo = new DistributionPointInfo();
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
    //司机搬运
    private DriverCarryInfo driverCarryInfo = new DriverCarryInfo();
    //小工搬运
    private LaborerCarryInfo laborerCarryInfo = new LaborerCarryInfo();
    //返点费信息
    private RebateRateInfo  rebateRateInfo = new RebateRateInfo();
    // 入城证信息
    private EntryLicenseInfo entryLicenseInfo = new EntryLicenseInfo();

    public PriceProxy() {
    }

    public PriceProxy(TruckTypeFreight freight, Map<String, Object> map, DistanceAndPriceData data, int siteNo, List<Integer> list) {
        Integer path = data.getDistance();
        Integer mileage = freight.getLowestMileage();
        this.lowestFreight = freight.getLowestFreight();
        this.totalMileage = new BigDecimal(path != null ? path :0);
        this.lowestMileage = new BigDecimal(String.valueOf(mileage == null ? 0 : mileage));
        this.tollDistance = new BigDecimal(data.getTollDistance() == null ? 0 : data.getTollDistance());
        this.tolls = data.getTolls() == null ? BigDecimal.ZERO : data.getTolls();
        this.totalSite = new BigDecimal(String.valueOf(siteNo));
        this.superMileageInfo = new SuperMileageInfo(freight, path);
        this.distributionPointInfo = new DistributionPointInfo(siteNo, freight.getDistributionPointPrice());
        this.collectionPaymentInfo = new CollectionPaymentInfo(
                getValue(map, AdditionalFunction.FunctionKeys.COLLECTION_PAYMENT), list);
        this.coldChainInfo = new ColdChainInfo(getValue(map, AdditionalFunction.FunctionKeys.COLD_CHAIN), list);
        this.backStorageInfo = new BackStorageInfo(getValue(map, AdditionalFunction.FunctionKeys.NEED_BACK_STORAGE), list);
        this.taxRateInfo = new TaxRateInfo(map);
        this.receiptInfo = new ReceiptInfo(getValue(map, AdditionalFunction.FunctionKeys.NEED_RECEIPT), list);
        this.carryInfo = new CarryInfo(getValue(map, AdditionalFunction.FunctionKeys.NEED_CARRY), list);
        
        this.driverCarryInfo = new DriverCarryInfo(getValue(map, AdditionalFunction.FunctionKeys.DRIVER_CARRY),list);
        this.laborerCarryInfo = new LaborerCarryInfo(getValue(map, AdditionalFunction.FunctionKeys.LABORER_CARRY),list);
        
        
        this.entryLicenseInfo = new EntryLicenseInfo(getValue(map, AdditionalFunction.FunctionKeys.ENTRY_LICENSE), list);
    }

    public BigDecimal getLowestFreight() {
        return lowestFreight;
    }

    public void setLowestFreight(BigDecimal lowestFreight) {
        this.lowestFreight = lowestFreight;
    }

    public BigDecimal getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(BigDecimal totalMileage) {
        this.totalMileage = totalMileage;
    }

    public BigDecimal getRateFrihgt() {
        return rateFrihgt;
    }

    public void setRateFrihgt(BigDecimal rateFrihgt) {
        this.rateFrihgt = rateFrihgt;
    }

    public BigDecimal getColdChainFrihgt() {
        return coldChainFrihgt;
    }

    public void setColdChainFrihgt(BigDecimal coldChainFrihgt) {
        this.coldChainFrihgt = coldChainFrihgt;
    }

    public BigDecimal getTotalSite() {
        return totalSite;
    }

    public void setTotalSite(BigDecimal totalSite) {
        this.totalSite = totalSite;
    }

    public void setLowestMileage(BigDecimal lowestMileage) {
        this.lowestMileage = lowestMileage;
    }

    public BigDecimal getLowestMileage() {
        return lowestMileage;
    }

    public SuperMileageInfo getSuperMileageInfo() {
        return superMileageInfo;
    }

    public void setSuperMileageInfo(SuperMileageInfo superMileageInfo) {
        this.superMileageInfo = superMileageInfo;
    }

    public DistributionPointInfo getDistributionPointInfo() {
        return distributionPointInfo;
    }

    public void setDistributionPointInfo(DistributionPointInfo distributionPointInfo) {
        this.distributionPointInfo = distributionPointInfo;
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

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
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

    public BigDecimal getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
    }

    public BigDecimal getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(BigDecimal timeCost) {
        this.timeCost = timeCost;
    }

    public DriverCarryInfo getDriverCarryInfo() {
        return driverCarryInfo;
    }

    public void setDriverCarryInfo(DriverCarryInfo driverCarryInfo) {
        this.driverCarryInfo = driverCarryInfo;
    }

    public LaborerCarryInfo getLaborerCarryInfo() {
        return laborerCarryInfo;
    }

    public void setLaborerCarryInfo(LaborerCarryInfo laborerCarryInfo) {
        this.laborerCarryInfo = laborerCarryInfo;
    }

    public RebateRateInfo getRebateRateInfo() {
        return rebateRateInfo;
    }

    public void setRebateRateInfo(RebateRateInfo rebateRateInfo) {
        this.rebateRateInfo = rebateRateInfo;
    }

    @Override
    public String toString() {
        return "PriceProxy [tolls=" + tolls + ", timeCost=" + timeCost + ", taxRateInfo=" + taxRateInfo + ", driverCarryInfo=" + driverCarryInfo + ", laborerCarryInfo="
                + laborerCarryInfo + ", rebateRateInfo=" + rebateRateInfo + "]";
    }
}
