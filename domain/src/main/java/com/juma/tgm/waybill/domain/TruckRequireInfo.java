package com.juma.tgm.waybill.domain;

import java.io.Serializable;
import java.util.List;

import com.juma.tgm.truck.domain.AdditionalFunctionBo;
import com.juma.tgm.truck.domain.TruckType;

/**
 * 
 * @Description: 用车要求信息
 * @author weilibin
 * @date 2016年7月4日 下午3:45:12
 * @version V1.0
 */

public class TruckRequireInfo implements Serializable {

    private static final long serialVersionUID = 7688602966420686565L;
    /** 附加功能信息 */
    private List<AdditionalFunctionBo> funList;
    /** 车型 */
    private List<TruckType> truckTypeList;
    /** 税率 */
    private List<TaxRate> taxRateList;

    public List<AdditionalFunctionBo> getFunList() {
        return funList;
    }

    public void setFunList(List<AdditionalFunctionBo> funList) {
        this.funList = funList;
    }

    public List<TruckType> getTruckTypeList() {
        return truckTypeList;
    }

    public void setTruckTypeList(List<TruckType> truckTypeList) {
        this.truckTypeList = truckTypeList;
    }

    public List<TaxRate> getTaxRateList() {
        return taxRateList;
    }

    public void setTaxRateList(List<TaxRate> taxRateList) {
        this.taxRateList = taxRateList;
    }

}
