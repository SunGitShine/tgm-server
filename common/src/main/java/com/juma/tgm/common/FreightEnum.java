package com.juma.tgm.common;

public enum FreightEnum {

    ZHENGCHE(1, "整车"), LINGDAN(2, "零担"),PROJECT(3,"项目");

    FreightEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
