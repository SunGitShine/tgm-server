package com.juma.tgm.crm.domain;

import com.juma.crm.customer.domain.ConsignorCustomerInfo;

/**
 * @ClassName: ConsignorCustomerInfoVo
 * @Description:
 * @author: liang
 * @date: 2017-03-14 10:26
 * @Copyright: 2017 www.jumapeisong.com Inc. All rights reserved.
 */
public class ConsignorCustomerInfoVo extends ConsignorCustomerInfo {

    /**
     * 客户类型名称
     */
    private String consignorTypeStr;

    /**
     * 客户行业名称
     */
    private String ownedIndustryStr;


    private String customerTypeStr;

    /**
     * 状态名称
     */
    private String statusStr;

    /**
     * 客户性质
     */
    private String enterpriseNatureStr;

    /**
     * 客户来源名称
     */
    private String sourceChannelCodeStr;

    /**
     * 业务区域
     */
    private String areaCodeName;



    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;

    /**
     * 淘汰原因
     */
    private String eliminationReason;

    /**
     * 成单数量
     */
    private String wayBillCount;

    /**
     * tgm 系统的customerId
     */
    private Integer tgmCustomerId;


    /**
     * 是否支持项目结算
     */
    private Boolean isProjectCheckOut;

    public String getEnterpriseNatureStr() {
        return enterpriseNatureStr;
    }

    public void setEnterpriseNatureStr(String enterpriseNatureStr) {
        this.enterpriseNatureStr = enterpriseNatureStr;
    }

    public String getConsignorTypeStr() {
        return consignorTypeStr;
    }

    public void setConsignorTypeStr(String consignorTypeStr) {
        this.consignorTypeStr = consignorTypeStr;
    }

    public String getOwnedIndustryStr() {
        return ownedIndustryStr;
    }

    public void setOwnedIndustryStr(String ownedIndustryStr) {
        this.ownedIndustryStr = ownedIndustryStr;
    }

    public String getCustomerTypeStr() {
        return customerTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {
        this.customerTypeStr = customerTypeStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getEliminationReason() {
        return eliminationReason;
    }

    public void setEliminationReason(String eliminationReason) {
        this.eliminationReason = eliminationReason;
    }


    public String getWayBillCount() {
        return wayBillCount;
    }

    public void setWayBillCount(String wayBillCount) {
        this.wayBillCount = wayBillCount;
    }

    public Integer getTgmCustomerId() {
        return tgmCustomerId;
    }

    public void setTgmCustomerId(Integer tgmCustomerId) {
        this.tgmCustomerId = tgmCustomerId;
    }


    public Boolean getIsProjectCheckOut() {
        return isProjectCheckOut;
    }

    public void setProjectCheckOut(Boolean projectCheckOut) {
        isProjectCheckOut = projectCheckOut;
    }

    public String getSourceChannelCodeStr() {
        return sourceChannelCodeStr;
    }

    public void setSourceChannelCodeStr(String sourceChannelCodeStr) {
        this.sourceChannelCodeStr = sourceChannelCodeStr;
    }

    public String getAreaCodeName() {
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
    }
}
