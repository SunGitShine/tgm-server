package com.juma.tgm.fms.domain.bo;

import java.io.Serializable;

public class ReconciliationLog implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1416668496990990680L;

    private String field;
    
    private String oldValue;
    
    private String oldText;
    
    private String newValue;
    
    private String newText;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }
    
    
}
