package com.juma.tgm.waybill.domain.map;

import java.io.Serializable;
import java.util.Map;

/**
 * 地图业务信息
 * 
 * @author weilibin
 *
 */

public class MapBusinessInfo implements Serializable {

    private static final long serialVersionUID = -6430613639252868169L;
    private Map<String, Object> businessInfoMap;
    private String initURL;
    private String callbackURL;
    private String plateNumbers;

    public Map<String, Object> getBusinessInfoMap() {
        return businessInfoMap;
    }

    public void setBusinessInfoMap(Map<String, Object> businessInfoMap) {
        this.businessInfoMap = businessInfoMap;
    }

    public String getInitURL() {
        return initURL;
    }

    public void setInitURL(String initURL) {
        this.initURL = initURL;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }


}
