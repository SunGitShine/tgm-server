package com.juma.tgm.truck.domain;

import java.io.Serializable;
import java.util.List;

public class TruckTypeFreightBo implements Serializable {

    private static final long serialVersionUID = 100390693625385298L;
    private TruckTypeFreight truckTypeFreight;
    /** 附加功能运费 */
    private AdditionalFunctionFreightBo affFreight;
    /** 车型 */
    private TruckType truckType;
    /** 车型全称 */
    private String truckTypeName;
    /** 附加功能集合 */
    private String[] functions;
    /** 已选附加功能运费 */
    private List<AdditionalFunctionFreightBo> freightList;
    /** 附加功能运费规则 */
    private List<String> functionInfo;
    /** 添加来源 */
    private String addWay;
    private String cityManageName;

    public TruckTypeFreight getTruckTypeFreight() {
        return truckTypeFreight;
    }

    public void setTruckTypeFreight(TruckTypeFreight truckTypeFreight) {
        this.truckTypeFreight = truckTypeFreight;
    }

    public AdditionalFunctionFreightBo getAffFreight() {
        return affFreight;
    }

    public void setAffFreight(AdditionalFunctionFreightBo affFreight) {
        this.affFreight = affFreight;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public String[] getFunctions() {
        return functions;
    }

    public void setFunctions(String[] functions) {
        this.functions = functions;
    }

    public List<AdditionalFunctionFreightBo> getFreightList() {
        return freightList;
    }

    public void setFreightList(List<AdditionalFunctionFreightBo> freightList) {
        this.freightList = freightList;
    }

    public List<String> getFunctionInfo() {
        return functionInfo;
    }

    public void setFunctionInfo(List<String> functionInfo) {
        this.functionInfo = functionInfo;
    }

    public String getAddWay() {
        return addWay;
    }

    public void setAddWay(String addWay) {
        this.addWay = addWay;
    }

    public String getCityManageName() {
        return cityManageName;
    }

    public void setCityManageName(String cityManageName) {
        this.cityManageName = cityManageName;
    }

}