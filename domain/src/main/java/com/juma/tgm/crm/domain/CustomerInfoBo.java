package com.juma.tgm.crm.domain;

import java.io.Serializable;
import java.util.List;

import com.juma.auth.user.domain.User;
import com.juma.crm.customer.domain.ConsignorBaseInfoVo;
import com.juma.tgm.base.domain.RegionBo;
import com.juma.tgm.customer.domain.TruckCustomer;

/**
 * 
 * @Description: 大客户BO
 * @author weilibin
 * @date 2016年6月30日 下午6:31:41
 * @version V1.0
 */

public class CustomerInfoBo implements Serializable {

    private static final long serialVersionUID = -6172103311519340659L;

    private TruckCustomer truckCustomer;

    private CustomerInfo customerInfo;
    private User user;
    /** 地区ID */
    private Integer regionId;
    /** 已选地区ID */
    private List<RegionBo> regionIdList;
    /** 取货地全称  */
    private String fullRegionName;
    private String projectCheckOutStr;

    /**
     * crm货主基本信息
     */
    private ConsignorBaseInfoVo consignorBaseInfoVo;

    public TruckCustomer getTruckCustomer() {
        return truckCustomer;
    }

    public void setTruckCustomer(TruckCustomer truckCustomer) {
        this.truckCustomer = truckCustomer;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public List<RegionBo> getRegionIdList() {
        return regionIdList;
    }

    public void setRegionIdList(List<RegionBo> regionIdList) {
        this.regionIdList = regionIdList;
    }

    public String getFullRegionName() {
        return fullRegionName;
    }

    public void setFullRegionName(String fullRegionName) {
        this.fullRegionName = fullRegionName;
    }

    public String getProjectCheckOutStr() {
        return projectCheckOutStr;
    }

    public void setProjectCheckOutStr(String projectCheckOutStr) {
        this.projectCheckOutStr = projectCheckOutStr;
    }

    public ConsignorBaseInfoVo getConsignorBaseInfoVo() {
        return consignorBaseInfoVo;
    }

    public void setConsignorBaseInfoVo(ConsignorBaseInfoVo consignorBaseInfoVo) {
        this.consignorBaseInfoVo = consignorBaseInfoVo;
    }
}
