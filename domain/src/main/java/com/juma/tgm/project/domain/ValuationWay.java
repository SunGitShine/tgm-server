package com.juma.tgm.project.domain;

import java.io.Serializable;
/**
 * valuation_way - 计价方式
 * 
 * @author  2017-09-26
 * @version 1.0 
 */
public class ValuationWay implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private String labelName;

    private String labelInputName;
    
    private String value;
    
    private Integer requiredMinValue;

    private Integer requiredMaxValue;
    
    public Integer getRequiredMinValue() {
        return requiredMinValue;
    }

    public void setRequiredMinValue(Integer requiredMinValue) {
        this.requiredMinValue = requiredMinValue;
    }

    public Integer getRequiredMaxValue() {
        return requiredMaxValue;
    }

    public void setRequiredMaxValue(Integer requiredMaxValue) {
        this.requiredMaxValue = requiredMaxValue;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelInputName() {
        return labelInputName;
    }

    public void setLabelInputName(String labelInputName) {
        this.labelInputName = labelInputName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
	
    

}