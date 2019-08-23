package com.juma.tgm.truck.domain;

import java.io.Serializable;

/**
 * 
 * @author weilibin
 * @date 2016年6月30日 下午5:22:34
 * @version V1.0
 */

public class AdditionalFunctionBo implements Serializable {

    private static final long serialVersionUID = -126251070390464894L;
    /** 附加功能ID */
    private Integer additionalFunctionId;
    /** 附加功能名称 */
    private String functionName;
    /** 附加功能KEY */
    private String functionKey;

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

    public String getFunctionKey() {
        return functionKey;
    }

    public void setFunctionKey(String functionKey) {
        this.functionKey = functionKey;
    }

}
