package com.juma.tgm.task.enums;

public enum PricingMethod {

    By_Workload(1,"按工作量"),By_Pack(2,"一口价");

    private int code;

    private String desc;

    PricingMethod(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
